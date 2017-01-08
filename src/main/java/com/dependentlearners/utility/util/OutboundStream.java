package com.dependentlearners.utility.util;

import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import java.net.URI;
import java.net.URISyntaxException;

import static com.dependentlearners.utility.util.Constants.*;

/**
 * Created by AB75448 on 05.01.2017.
 */
public enum OutboundStream {
    TEST_STREAM_AND_SEPERATED_URL_PARAMETER_WITH_EXTENSION_REMOVAL(false){
        @Override
        public URI getURL(URI fromURL, Environment environment){
            try {
                String toURL = super.getURLFromEnv(environment);
                String fromURLAsString = fromURL.toString();
                fromURLAsString = fromURLAsString.split(".aspx")[0];
                String operation = fromURLAsString.substring(fromURLAsString.lastIndexOf("/"),fromURLAsString.length());;
                return URIBuilderUtility.copyAndSeperatedURLParameter(fromURL, toURL.concat(operation));
            } catch (URISyntaxException e) {
                throw super.createException(e);
            }
        }
    },
    TEST_STREAM_AND_SEPERATED_URL_PARAMETER(true){
        @Override
        public URI getURL(URI fromURL, Environment environment){
            try {
                return URIBuilderUtility.copyAndSeperatedURLParameter(fromURL, super.getURLFromEnv(environment));
            } catch (URISyntaxException e) {
                throw super.createException(e);
            }
        }
    },
    TEST_STREAM_SLASH_SEPERATED_URL_PARAMERER(false){
        @Override
        public URI getURL(URI fromURL, Environment environment){
            try {
                return URIBuilderUtility.copySlashSeperatedURLParameter(fromURL, super.getURLFromEnv(environment));
            } catch (URISyntaxException e) {
                throw super.createException(e);
            }
        }
    },
    TEST_STREAM_COMPLETE_URI_WITH_HEADER_AS_OLD_URL(true);

    public boolean isUrlHeaderEnabled;

    OutboundStream(boolean isUrlHeaderEnabled){
        this.isUrlHeaderEnabled = isUrlHeaderEnabled;
    }

    public URI getURL(URI fromURL, Environment environment){
        try {
            return new URI(getURLFromEnv(environment));
        } catch (URISyntaxException e) {
            throw createException(e);
        }
    }

    private String getURLFromEnv(Environment environment){
        String key = UTILITY.concat(this.name().toLowerCase());
        String url = environment.getProperty(key);
        Assert.isTrue(url!=null, String.format("URL must not be null, Please check environment propery for key {}", key ));
        return url;
    }

    private RuntimeException createException(URISyntaxException e){
        throw new RuntimeException("Environment property for stream based outbound uri is incorrect", e);
    }
}
