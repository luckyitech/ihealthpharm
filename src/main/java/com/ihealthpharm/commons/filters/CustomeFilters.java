package com.ihealthpharm.commons.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class CustomeFilters {

	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean() {
	    CharacterEncodingFilter filter = new CharacterEncodingFilter();
	    filter.setEncoding("UTF-8");
	    filter.setForceEncoding(true);

	    FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
	    registrationBean.setFilter(filter);
	    registrationBean.addUrlPatterns("/*");
	    return registrationBean;
	}
}
