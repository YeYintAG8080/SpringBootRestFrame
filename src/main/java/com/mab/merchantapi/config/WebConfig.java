package com.mab.merchantapi.config;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.mab.merchantapi.http.CustomRestTemplateCustomizer;
import com.mab.merchantapi.http.RestTemplateResponseErrorHandler;

@Configuration
@PropertySource({ "classpath:application.properties" })
public class WebConfig implements WebMvcConfigurer {

	@Value("${content.data.dir}")
	public String contentDir;

	@Bean
	@DependsOn(value = { "restTemplateBuilder" })
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = restTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
		// file upload
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(-1);// 52428800 max 50 MB
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.setOrder(0).addResourceHandler("/profile/**")
				.addResourceLocations("file://MerchantService", "file://" + contentDir)

				/*
				 * file:/// means the sys read %SYSTEMROOT% dir in filesys DON'T CHANGE THE PATH
				 * "file:///u01/intercom/public/"
				 * 
				 */
				.setCachePeriod(0)// 1 year max cache = 31556926
				.resourceChain(true).addResolver(new PathResourceResolver());
		// ref: https://www.baeldung.com/spring-mvc-static-resources
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
			// log.debug("Registering CORS filter");
			source.registerCorsConfiguration("/api/**", config);
			// source.registerCorsConfiguration("/management/**", config);
			// source.registerCorsConfiguration("/v2/api-docs", config);
		}
		return new CorsFilter(source);
	}

	@Bean
	@Qualifier("customRestTemplateCustomizer")
	public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
		return new CustomRestTemplateCustomizer();
	}

	@Bean
	@DependsOn(value = { "customRestTemplateCustomizer" })
	@Qualifier("restTemplateBuilder")
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder(customRestTemplateCustomizer());
	}
}
