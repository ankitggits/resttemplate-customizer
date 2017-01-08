package com.dependentlearners.utility.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by AB75448 on 06.01.2017.
 */
public class URIBuilderUtility {

    public static URI copySlashSeperatedURLParameter(URI fromURI, String toURL) throws URISyntaxException {
        String[] fromParams = fromURI.toString().split("/");
        String[] toParams = toURL.split("/");
        int count = fromParams.length-1;
        for(int i = toParams.length-1 ; i >= 0; i--){
            String param = toParams[i];
            if(param.startsWith("{") && param.endsWith("}")){
                toURL = toURL.replace(param, fromParams[count]);
                count-=1;
            }else{
                break;
            }
        }
        return new URI(toURL);
    }

    public static URI copyAndSeperatedURLParameter(URI fromURI, String toURL)  throws URISyntaxException{
        String fromURL = fromURI.toString();
        String params = fromURL.substring(fromURL.indexOf("?"), fromURL.length());
        return new URI(toURL.concat(params));
    }


}
