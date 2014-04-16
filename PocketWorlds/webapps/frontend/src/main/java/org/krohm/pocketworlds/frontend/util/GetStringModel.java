/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.util;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;

/**
 *
 * @author Arnaud
 */
public class GetStringModel implements IModel<String> {
    Component component;
    String getStringKey;

    public GetStringModel(Component component, String getStringKey) {
        this.component = component;
        this.getStringKey = getStringKey;
    }

    @Override
    public String getObject() {
        return component.getString(getStringKey);
    }

    @Override
    public void setObject(String t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void detach() {
    }
    
    
    
}
