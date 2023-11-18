package client;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class GlobalApiProperties {

    private static GlobalApiProperties instance;
    private static CompositeConfiguration configuration;

    private GlobalApiProperties() {
        configuration = new CompositeConfiguration();
        try {
            //not a good practice to have the properties file on the remote repo especially if it contains sensitive data as passwords
            //if it contains test environemnt/infrastructure configuration it might be considered as a OK practice
            configuration.addConfiguration(new PropertiesConfiguration("testing.properties"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static GlobalApiProperties getInstance() {
        if (instance == null) {
            synchronized (GlobalApiProperties.class) {
                if (instance == null) {
                    instance = new GlobalApiProperties();
                }
            }
        }

        return instance;
    }

    //fetch the property from the system properties first and if it's not there then from the properties file
    //this is useful for running the tests from the command line with -D<property>=<value> 
    //example: mvn clean test -Dapp.casumo.api=http://casumo.production.com:8080 or -Dapp.casumo.api=http://casumo.test.com:3000
    public String get(String key) {
        return System.getProperty(key, configuration.getString(key));
    }
}