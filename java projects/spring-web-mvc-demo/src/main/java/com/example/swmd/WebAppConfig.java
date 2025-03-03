package com.example.swmd;

import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


// equivalent to  web.xml
public class WebAppConfig implements WebApplicationInitializer{
	
	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
		// TODO Auto-generated method stub
AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
		
		// registering the config cass
		webCtx.register(SpringWebMvcDemoConfig.class);
		// associating web application context with servlet context
		// web app context managed by spring
		// servlet context is managed by tomcat.
		// this merges both of them so that we can use it as one single context.
		webCtx.setServletContext(servletContext);
		
		//		if we use wen xml
		/*
		 * <web-app>
		 * 	<servlet>
		 * 	<servlet-name>
		 * 		dispatcher
		 *  </servlet-name>
		 *  <servlet-class>
		 *  	org.springframework.web.servlet.DispatcherServlet
		 *  </servlet-class>
		 *  <load-on-startup>1</load-onstartup>
		 *  <servlet-mapping>
		 *  	<servlet-name>
		 * 			dispatcher
		 *  	</servlet-name>
		 *  	<url-pattern>
		 *  		/
		 *  	</url-pattern>
		 *  </servlet-mapping>
		 * 	</servlet>
		 *  </webapp>*/
		
//		Providing custom name for the dispatcher servlet.
		ServletRegistration.Dynamic servlet =
				servletContext.addServlet("dispatcher", new DispatcherServlet(webCtx));
		servlet.setLoadOnStartup(1);
//		mapping all requests to the dispatcher servlet.
		servlet.addMapping("/"); // the / and /* are considered as same.
	}

	


	

}
