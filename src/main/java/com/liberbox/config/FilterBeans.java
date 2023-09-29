package com.liberbox.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liberbox.config.domain.UserFilter;

@Configuration
public class FilterBeans {

	@Bean
	public FilterRegistrationBean<UserFilter> companyFilter() {
		FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new UserFilter());
		registrationBean.addUrlPatterns("/v1/*");
		return registrationBean;
	}
}
