package com.mab.merchantapi.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class RestTemplateHeaderModifierInterceptor implements ClientHttpRequestInterceptor {
	
	private String publicDomainHost="localhost";
	private int publicDomainPosr=8084;
	
	//ref: https://www.baeldung.com/spring-rest-template-interceptor
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		//String requestDomain="http://"+request.getURI().getHost()+":"+request.getURI().getPort();		
			 		
		request.getHeaders().clear();
		MediaType mediaType=new MediaType("application", "json", StandardCharsets.UTF_8);
        request.getHeaders().setContentType(mediaType);
		request.getHeaders().add("Accept",MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		if(request.getURI().getHost().equals("localhost") && request.getURI().getPort()==8084) {
			request.getHeaders().add("Authorization", "Bearer "+this.getPublicKeyForPublicServer());
		}
		
		ClientHttpResponse response = execution.execute(request, body);
        return response;
	}
	
	private String getPublicKeyForPublicServer() {
		SimpleDateFormat format=new SimpleDateFormat("yyyyddMM");
    	String publickey=format.format(new Date())+"C09M";
    	return new BCryptPasswordEncoder(11).encode(publickey);
	}

}
