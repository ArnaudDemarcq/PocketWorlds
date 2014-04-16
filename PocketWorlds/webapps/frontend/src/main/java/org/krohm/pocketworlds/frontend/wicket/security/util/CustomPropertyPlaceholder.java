/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.krohm.pocketworlds.frontend.wicket.security.util;

import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author Arnaud
 */
public class CustomPropertyPlaceholder extends PropertyPlaceholderConfigurer {

    private final Properties properties = new Properties();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        properties.clear();
        properties.putAll(props);
    }

    public Properties getProperties() {
        return properties;
    }
}
