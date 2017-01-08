package com.dependentlearners.utility.factory;

import com.dependentlearners.utility.util.OutboundStream;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;

import java.net.URI;
import java.util.List;

/**
 * Wrapper for a {@link InterceptingClientHttpRequestFactory} that has support for {@link ClientHttpRequestInterceptor}s.
 *
 * @author Ankit Singh
 * @since Dependent_Learners founded
 */
public class RequestFactory extends InterceptingClientHttpRequestFactory {

    private OutboundStream stream;
    private Environment environment;


    public RequestFactory(Environment environment , OutboundStream stream, ClientHttpRequestFactory requestFactory, List<ClientHttpRequestInterceptor> interceptors) {
        super(requestFactory, interceptors);
        this.stream = stream;
        this.environment = environment;
    }

    @Override
    protected ClientHttpRequest createRequest(URI requestURI, HttpMethod httpMethod, ClientHttpRequestFactory requestFactory) {
        return super.createRequest(stream.getURL(requestURI, environment), httpMethod, requestFactory);
    }
}
