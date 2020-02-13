package com.afkl.travel.exercise.rest.template;

import java.util.Map;

import org.apache.commons.codec.Charsets;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.afkl.travel.exercise.config.ApplicationConfiguration;
import com.afkl.travel.exercise.utils.constants.Constants;

@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

	private Map<String, String> locationsMap;
	private RestTemplate restTemplate;

	@Autowired
	public RestTemplateFactory(ApplicationConfiguration appConfig) {
		this.locationsMap = appConfig.getLocations();
	}

	public RestTemplate getObject() {
		return restTemplate;
	}

	public Class<RestTemplate> getObjectType() {
		return RestTemplate.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		HttpHost host = new HttpHost(locationsMap.get(Constants.LOCAL_HOST), 8080,
				locationsMap.get(Constants.PROTOCOL));
		restTemplate = new RestTemplate(new BasicAuthorizationFactory(host));
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(locationsMap.get(Constants.USER_NAME),
				locationsMap.get(Constants.PASSWORD), Charsets.UTF_8));
	}
}