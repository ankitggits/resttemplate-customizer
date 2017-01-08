package com.dependentlearners.utility.component;

import com.dependentlearners.utility.factory.RequestFactory;
import com.dependentlearners.utility.interceptor.HeaderInterceptor;
import com.dependentlearners.utility.util.OutboundStream;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.dependentlearners.utility.util.Constants.*;

/**
 * Created by AB75448 on 06.01.2017.
 */
public class RestTemplateCustomizer implements IRestTemplateCustomizer {

    private Environment environment;

    private String subscriptionKey;

    public RestTemplateCustomizer(){}

    public RestTemplateCustomizer(Environment environment){
        this.environment = environment;
        this.subscriptionKey = environment.getProperty(UTILITY_SUBSCRIPTION_KEY);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void customize(RestTemplate restTemplate, OutboundStream stream) {
        List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<>(restTemplate.getInterceptors());
        interceptorList.add(new HeaderInterceptor(subscriptionKey, stream));
        restTemplate.setInterceptors(Collections.<ClientHttpRequestInterceptor>emptyList());
        restTemplate.setRequestFactory(new RequestFactory(environment, stream ,restTemplate.getRequestFactory(), interceptorList));
    }

    @Override
    public void setSubscriptionkey(String subscriptionkey) {
        this.subscriptionKey = subscriptionkey;
    }
}
