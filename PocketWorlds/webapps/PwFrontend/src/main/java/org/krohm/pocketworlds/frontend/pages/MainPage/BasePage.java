/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.pages.MainPage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.krohm.pocketworlds.frontend.pages.MainPage.panels.LanguagePanel;
import org.krohm.pocketworlds.frontend.util.GetStringModel;
import org.krohm.pocketworlds.frontend.wicket.security.WicketWebSession;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lelo Nyaka
 */
@AuthorizeInstantiation({BasePage.ROLE_AUTH})
public abstract class BasePage extends WebPage {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    public static final String ROLE_AUTH = "_AUTH";
    public static final String ROLE_CREDIT_HISTORY = "_CREDITHISTORY";
    public static final String ROLE_REPORTS = "_REPORTS";
    public static final String ROLE_STARTSTOP = "_STARTSTOP";

    public BasePage(final PageParameters params) {
        super(params);
        init();
    }

    abstract protected IModel<String> getTitle();

    abstract protected IModel<String> getSlogan();

    private void init() {
        // Feedback
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        add(feedbackPanel);
        // language
        add(new LanguagePanel("languagePanel"));

        Link logoutLink = new Link("logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
            }
        };
        add(logoutLink);
        //Add getUsername
        String userID = WicketWebSession.get().getUserId();
        Label userNameLabel = new Label("username", userID);
        add(userNameLabel);

        Link homelink = new BookmarkablePageLink("home", HomePage.class);
        add(homelink);
        Label titleLabel = new Label("title", getTitle());
        add(titleLabel);
        Label sloganLabel = new Label("slogan", getSlogan());
        add(sloganLabel);
        add(new ListView<ClassLinkLabel>("menu", getClassLinkLabel()) {
            @Override
            protected void populateItem(ListItem<ClassLinkLabel> listItem) {
                ClassLinkLabel currentPageClass = listItem.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", currentPageClass.getLinkClass());
                link.add(new Label("label", currentPageClass.getLinkLabel()));
                listItem.add(link);
                listItem.add(new ListView<ClassLinkLabel>("subMenu", listItem.getModelObject().getSubLinkLabel()) {
                    @Override
                    protected void populateItem(ListItem<ClassLinkLabel> subListItem) {
                        ClassLinkLabel currentPageClass = subListItem.getModelObject();
                        BookmarkablePageLink link = new BookmarkablePageLink("subLink", currentPageClass.getLinkClass());
                        link.add(new Label("subLabel", currentPageClass.getLinkLabel()));
                        subListItem.add(link);
                    }
                });
            }
        });/**/
    }

    private List<ClassLinkLabel> getClassLinkLabel() {
        List<ClassLinkLabel> returnList = new ArrayList<ClassLinkLabel>();

        return returnList;
    }

    private List<ClassLinkLabel> getReportingLabels() {
        List<ClassLinkLabel> returnList = new ArrayList<ClassLinkLabel>();
        Roles currentUserRoles = WicketWebSession.get().getRoles();

        return returnList;
    }

    private ClassLinkLabel getClassLinkLabel(Class clazz, String key) {
        return new ClassLinkLabel(clazz, new GetStringModel(this, key));
    }

    private ClassLinkLabel getClassLinkLabel(Class clazz, String key, List<ClassLinkLabel> subLinkLabel) {
        return new ClassLinkLabel(clazz, new GetStringModel(this, key), subLinkLabel);
    }

    private class ClassLinkLabel implements Serializable {

        private Class linkClass;
        private IModel<String> linkLabel;
        private final List<ClassLinkLabel> subLinkLabel = new ArrayList<ClassLinkLabel>();

        public ClassLinkLabel(Class linkClass, IModel<String> linkLabelModel, List<ClassLinkLabel> subLinkLabel) {
            this.linkClass = linkClass;
            this.linkLabel = linkLabelModel;
            this.subLinkLabel.addAll(subLinkLabel);
        }

        public ClassLinkLabel(Class linkClass, IModel<String> linkLabelModel) {
            this.linkClass = linkClass;
            this.linkLabel = linkLabelModel;
        }

        public List<ClassLinkLabel> getSubLinkLabel() {
            return subLinkLabel;
        }

        public void setSubLinkLabel(List<ClassLinkLabel> subLinkLabel) {
            this.subLinkLabel.addAll(subLinkLabel);
        }

        public Class getLinkClass() {
            return linkClass;
        }

        public void setLinkClass(Class linkClass) {
            this.linkClass = linkClass;
        }

        public IModel<String> getLinkLabel() {
            return linkLabel;
        }

        public void setLinkLabel(IModel<String> linkLabel) {
            this.linkLabel = linkLabel;
        }
    }
}