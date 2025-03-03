package com.example.swmd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@ComponentScan("com.example")
@EnableWebMvc
//public class SpringWebMvcDemoConfig implements WebMvcConfigurationSupport {
// we dont need to perform the implementation as we have the @EnableMcv
public class SpringWebMvcDemoConfig  {
	// SimleUrlHandlerMapping is used by default. we just need to configure the viewResolver
	// We will use InternalReourceViewResover.
	// As its not a bean that we are creating, we need to set it here
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//setting prefix and suffix
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		// setting priority - if there are multiple resolvers
		viewResolver.setOrder(1);
		return viewResolver;
	}
}
