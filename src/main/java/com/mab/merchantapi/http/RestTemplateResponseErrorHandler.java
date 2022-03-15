package com.mab.merchantapi.http;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			// Handle SERVER_ERROR
			throw new HttpClientErrorException(response.getStatusCode());
		} else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
			// Handle CLIENT_ERROR
			if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new RuntimeException("No handler found in intercomservice");
			}
			if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Frontend server made bad request");
			}
		}
	}

}
