package com.dependentlearners.utility.component;

import com.dependentlearners.utility.util.OutboundStream;
import org.springframework.context.EnvironmentAware;
import org.springframework.web.client.RestTemplate;

/**
 * Created by AB75448 on 06.01.2017.
 */
public interface IRestTemplateCustomizer extends EnvironmentAware, SubsriptionKeyAware {
    void customize(RestTemplate restTemplate, OutboundStream stream);
}
