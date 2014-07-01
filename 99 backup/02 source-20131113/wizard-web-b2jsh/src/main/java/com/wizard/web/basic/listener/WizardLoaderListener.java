package com.wizard.web.basic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.wizard.web.utils.MessageUtils;

public class WizardLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		MessageUtils.load();
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}

}
