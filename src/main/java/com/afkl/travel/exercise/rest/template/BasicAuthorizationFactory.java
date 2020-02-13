package com.afkl.travel.exercise.rest.template;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class BasicAuthorizationFactory extends HttpComponentsClientHttpRequestFactory {

	private final HttpHost host;

	public BasicAuthorizationFactory(HttpHost host) {
		super();
		this.host = host;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		AuthCache authCache = new BasicAuthCache();

		BasicScheme basicAuth = new BasicScheme();
		authCache.put(host, basicAuth);

		HttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(HttpClientContext.AUTH_CACHE, authCache);
		return localcontext;
	}

}
