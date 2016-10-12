package com.rbcamelhawtio.springboot;


import java.util.HashMap;

import org.apache.camel.component.restlet.RestletComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.restlet.Component;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig extends CamelConfiguration{
	
	private static final String CAMEL_URL_MAPPING = "/api/*";
//	private static final String CAMEL_SERVLET_NAME = "SpringServerServlet";

	
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		ServletRegistrationBean registration =
				new ServletRegistrationBean(new org.restlet.ext.spring.SpringServerServlet(), CAMEL_URL_MAPPING);
		//registration.addInitParameter("org.restlet.application", CamelrestletApplication.class.getName());
		
		java.util.Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("org.restlet.component", "restletComponent");
		
		registration.setInitParameters(initParameters);
		
		
		//registration.setName(CAMEL_SERVLET_NAME);
		//registration.addInitParameter("org.restlet.component", "org.restlet.Component");
		return registration;
	}
	
	@Bean
	public Component restletComponent(){
		return new Component();
		
	}
	
	@Bean RestletComponent RestletComponentService(){
		return new RestletComponent(restletComponent());
	}

}