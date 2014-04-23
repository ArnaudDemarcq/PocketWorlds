/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Lelo Nyaka
 */
public class ModelPropertyModel implements IModel {

    private Object constantObject;
    private String object1Name;
    private String object2Name;
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelPropertyModel.class);

    public ModelPropertyModel(Object constantObject, String object1Name, String object2Name) {
        this.constantObject = constantObject;
        this.object1Name = object1Name;
        this.object2Name = object2Name;
    }

    @Override
    public Object getObject() {
        return getLevel2PropertyModel().getObject();
    }

    @Override
    public void setObject(Object t) {
        LOGGER.info("VALUE is : " + t);
        getLevel2PropertyModel().setObject(t);
    }

    private PropertyModel getLevel2PropertyModel() {
        PropertyModel level1 = new PropertyModel(constantObject, object1Name);
        Object tmpObject = level1.getObject();
        //LOGGER.info("Property Model Level1 : " + level1);
        PropertyModel level2 = new PropertyModel(tmpObject, object2Name);
        //LOGGER.info("Property Model Level2 : " + level2);
        return level2;
    }

    @Override
    public void detach() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
