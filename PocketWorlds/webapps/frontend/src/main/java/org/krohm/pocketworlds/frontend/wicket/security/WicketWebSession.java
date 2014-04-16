/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.wicket.security;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.krohm.pocketworlds.frontend.wicket.security.util.CustomPropertyPlaceholder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class WicketWebSession extends AbstractAuthenticatedWebSession {

    private String userId;
    private String password;
    private Boolean isLogged = null;
    private final List<String> userGroups = new ArrayList<String>();
    private static final Logger LOGGER = LoggerFactory.getLogger(WicketWebSession.class);
    private static CustomPropertyPlaceholder propertyPlaceholderConfigurer;

    public static void setPropertyPlaceholderConfigurer(CustomPropertyPlaceholder propertyPlaceholderConfigurer) {
        WicketWebSession.propertyPlaceholderConfigurer = propertyPlaceholderConfigurer;
    }

    public WicketWebSession(Request request) {
        super(request);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        this.isLogged = null;
    }

    public void setPassword(String password) {
        this.password = password;
        this.isLogged = null;
    }

    @Override
    public Roles getRoles() {
        Roles currentRoles = new Roles();
        for (String currentGroup : userGroups) {
            currentRoles.add(currentGroup);
        }
        return currentRoles;
    }

    @Override
    public boolean isSignedIn() {
        getHashTableForLdap();
        // Do not check again if user has been verified
        if (this.isLogged != null && this.isLogged) {
            return true;
        }
        // Then try to log in
        this.isLogged = null;
        try {
            //this.isLogged = (userId != null);
            this.isLogged = performLdapAuth();
        } catch (Exception ex) {
            LOGGER.warn("Error validating credentials :" + ex.getMessage());
            if (LOGGER.isTraceEnabled()) {
                LOGGER.trace("", ex);
            }
            return false;
        }
        return (this.isLogged);
    }

    public static WicketWebSession get() {
        return (WicketWebSession) Session.get();
    }

    private boolean performLdapAuth() {
        try {
            List<String> groups = getUserGroupsWithReplacements(userId);
            userGroups.clear();
            userGroups.addAll(groups);
            LOGGER.info("Groups are : " + groups);
            return (userGroups.size() > 0);
        } catch (NamingException ex) {
            LOGGER.warn("cannot connect to LDAP" + ex);
            if (userId == null) {
                if (LOGGER.isTraceEnabled()) {
                    LOGGER.trace("cannot connect to LDAP" + ex, ex);
                }
            }
        }
        return false;
    }

    private Hashtable getHashTableForLdap() {
        //build a hashtable containing all the necessary configuration parameters
        Hashtable<String, String> environment = new Hashtable<String, String>();
        Properties conf = propertyPlaceholderConfigurer.getProperties();
        environment.put(LdapContext.CONTROL_FACTORIES, conf.getProperty("ldap.factories.control"));
        //Classe Fournisseur
        environment.put(Context.INITIAL_CONTEXT_FACTORY, conf.getProperty("ldap.factories.initctx"));
        //URL pour localiser l'annuaire
        environment.put(Context.PROVIDER_URL, conf.getProperty("ldap.host"));
        //Type de securité utilisé
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        //Distinguished Name
        environment.put(Context.SECURITY_PRINCIPAL, userId + conf.getProperty("ldap.login.postfix"));
        //Mot de passe Utilisateur
        environment.put(Context.SECURITY_CREDENTIALS, "" + password);

        environment.put(Context.STATE_FACTORIES, "PersonStateFactory");
        environment.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");
        return environment;
    }

    private List<String> getUserGroups(String userName) throws NamingException {
        List<String> returnGroups = new ArrayList<String>();
        Properties conf = propertyPlaceholderConfigurer.getProperties();
        //Connexion à l'annuaire et positionnement à un endroit precis de l'arborescence
        DirContext ctx = new InitialDirContext(getHashTableForLdap());
        SearchControls searchCtls = new SearchControls();
        String returnedAtts[] = {"memberOf"};
        searchCtls.setReturningAttributes(returnedAtts);
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        String searchFilter = "(&(objectClass=user)(sAMAccountName=" + userName + "))";
        //String searchBase = "DC=expma, DC=local";
        String searchBase = conf.getProperty("ldap.searchbase");
        //Recherche d'un ou +sieurs attribut 
        //NamingEnumeration search(String stringName, Attributes attributesToMatch, String [] rgstringAttributesToReturn)

        NamingEnumeration answer = ctx.search(searchBase, searchFilter, searchCtls);
        while (answer.hasMoreElements()) {
            SearchResult sr = (SearchResult) answer.next();
            //La méthode getAttributes() permet d'obtenir tous les attributs d'un objet à partir de son DN
            Attributes attrs = sr.getAttributes();
            // Specifier l'id de l'attribut à retourner
            Attribute groupMembers = attrs.get("memberOf");
            for (int i = 0; i < groupMembers.size(); i++) {
                try {
                    String currentString = groupMembers.get(i).toString().split(",")[0];
                    String currentGroup = currentString.split("=")[1];
                    LOGGER.info("Number of Group detected :" + groupMembers.size());
                    LOGGER.info("Attribut recherche :" + groupMembers.get());
                    LOGGER.info("currentString detected :" + currentString);
                    LOGGER.info("Group detected :" + currentGroup);
                    returnGroups.add(currentGroup.toUpperCase());
                } catch (Exception ex) {
                    LOGGER.warn("Something went wrong while parsing groups. Skipping faulty group");
                }
            }
        }
        return returnGroups;
    }

    private List<String> getUserGroupsWithReplacements(String userName) throws NamingException {
        List<String> rawList = getUserGroups(userName);
        List<String> returnlist = new ArrayList<String>();
        //returnlist.addAll(rawList);
        Properties conf = propertyPlaceholderConfigurer.getProperties();
        for (String currentKey : rawList) {
            String propertyName = "ldap.groups.rights.mapping." + currentKey;
            Object currentValue = conf.get(propertyName);
            if (currentValue != null) {
                for (String currentRight : currentValue.toString().split(",")) {
                    returnlist.add(currentRight.trim());
                }
            }
        }
        returnlist.add("_AUTH");
        return returnlist;
    }
}