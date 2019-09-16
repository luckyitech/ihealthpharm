package com.ihealthpharm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.ihealthpharm.config.JwtFilter;

@SpringBootApplication
public class IHealthPharmApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(IHealthPharmApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/getallemployeecredentials","/getemployeecredentialsbyid");
		return registrationBean;
	}

}
