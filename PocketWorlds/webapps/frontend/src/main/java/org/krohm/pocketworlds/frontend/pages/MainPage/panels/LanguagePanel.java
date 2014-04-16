/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.pages.MainPage.panels;

import java.util.Locale;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arnaud
 */
public class LanguagePanel extends Panel {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguagePanel.class);

    public LanguagePanel(String id) {
        super(id);

        Form languageForm = new Form("languageForm");   // (1)

        languageForm.add(createLocaleChangingButton("vietButton", "vi_VN"));
        languageForm.add(createLocaleChangingButton("englishButton", "en_US"));

        add(languageForm);
    }

    private Button createLocaleChangingButton(final String buttonId, final String localeString) {
        return new Button(buttonId) {
            @Override
            public void onSubmit() {
                LOGGER.info("Old Locale is : " + getSession().getLocale());
                LOGGER.info("Locale changed to : " + localeString);
                changeUserLocaleTo(localeString);    // (2)
                LOGGER.info("New Locale is : " + getSession().getLocale());
                //setResponsePage(this.getPage());
            }
        };
    }

    private void changeUserLocaleTo(String localeString) {
        getSession().setLocale(new Locale(localeString));      // (3)
    }
}