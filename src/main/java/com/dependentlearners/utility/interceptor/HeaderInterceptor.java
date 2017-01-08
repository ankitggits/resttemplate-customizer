package com.dependentlearners.utility.interceptor;

import com.dependentlearners.utility.util.OutboundStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

import static com.dependentlearners.utility.util.Constants.*;

/**
 *
 * Intercepts client-side HTTP requests which is going through Azure APIM. It addes two headers based 1. subscryption key 2. request url .Implementations of this interface can be {@linkplain
 * org.springframework.web.client.RestTemplate#setInterceptors(java.util.List) registered} with the
 * {@link org.springframework.web.client.RestTemplate RestTemplate}, as to modify the outgoing {@link ClientHttpRequest}
 * and/or the incoming {@link ClientHttpResponse}.
 *
 * <p>The main entry point for interceptors is {@link #intercept(HttpRequest, byte[], ClientHttpRequestExecution)}.
 *
 * @author Ankit Singh
 * @since Dependent_Learners founded
 */

public class HeaderInterceptor implements ClientHttpRequestInterceptor {

    private OutboundStream stream;

    private String subscriptionKey;

    public HeaderInterceptor(String subscriptionKey, OutboundStream stream){
        this.subscriptionKey = subscriptionKey;
        this.stream = stream;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders httpHeaders = request.getHeaders();
        httpHeaders.set(SUBSCRIPTION_HEADER, "2ff63313ea324dea91a355443b10168e");
        if(stream.isUrlHeaderEnabled) httpHeaders.set(REDIRECT_URL_HEADER, request.getURI().toString());
        return execution.execute(request, body);
    }

}