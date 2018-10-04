package com.tmdb.suites.listeners;

import java.util.Map;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {

    public void onStart(ISuite suite) {
        Map<String, String> parameters = suite.getXmlSuite().getParameters();
        for (Map.Entry<String, String> parameter : parameters.entrySet()) {
            String parameterName = parameter.getKey();
            if (parameterName.equals("apiUrl")){
            	if (System.getProperty("apiUrl") != null){
            		parameter.setValue(System.getProperty("apiUrl"));
            	}
            }
            else if (parameterName.equals("apiKey")){
            	if (System.getProperty("apiKey") != null){
            		parameter.setValue(System.getProperty("apiKey"));
            	}
            }
        }
    }

    public void onFinish(ISuite suite) {
    }
}
