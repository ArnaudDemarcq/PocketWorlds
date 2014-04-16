/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.security.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.krohm.pocketworlds.frontend.wicket.security.WicketWebSession;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Valentin
 */
//@AuthorizeInstantiation("CEO")
public class LoginPage extends WebPage {

    @SpringBean
    //private UserEntityDao userEntityDao;
    /*
     * The captcha password
     */
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    private String password;
    private String userName;

    /**
     * Constructor method It defines the behavior of onSubmit form method and
     * defines the fields of the web page
     *
     * @param PageParameters parameters of the page
     */
    public LoginPage(final PageParameters parameters) {

        super(parameters);

        Form<LoginPage> inscriptionForm = new Form<LoginPage>("loginForm") {
            @Override
            protected void onSubmit() {
                LOGGER.info("User attempts to log in :" + userName);
                try {
                    //UserEntity currentUser = userEntityDao.findByEmail(userEmail);
                    WicketWebSession.get().setUserId(userName);
                    WicketWebSession.get().setPassword(password);
                    boolean logged = WicketWebSession.get().isSignedIn();
                    if (logged) {
                        setResponsePage(this.getApplication().getHomePage());
                    } else {
                        error("wrong username and/or password entered, please retry");
                    }
                } catch (RuntimeException rex) {
                    //error("REDIRECT IS NOT WORKING");
                    error(rex.getClass());
                } catch (Exception ex) {
                    error("Sorry, bad credentials");
                }
            }
        };
        add(inscriptionForm);
        /*
         * Feedback
         */
        inscriptionForm.add(new FeedbackPanel("feedback"));
        /*
         * Email Field
         */
        RequiredTextField<String> nameField = new RequiredTextField<String>("name", new PropertyModel(this, "userName"));
        inscriptionForm.add(nameField);
        /*
         * Password Field
         */
        TextField<String> passwordField = new PasswordTextField("password", new PropertyModel(this, "password"));   // (3)
        passwordField.setRequired(true);
        inscriptionForm.add(passwordField);

        /*
         * Submit button
         */
        Button submitButton = new Button("submitButton");
        inscriptionForm.add(submitButton);

    }
}
