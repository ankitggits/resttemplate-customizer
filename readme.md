##

**RestTemplate Customizer**

###

This utility intercepts all your outbound http requests and modify it (add headers and change urls).  


##
***How To Use***

###
***Configuration***

utility.subscriptionkey=headerToAdd

utility.test=https://urlToChange/something

***RestTemplate***

	@Bean
    public IRestTemplateCustomizer customize(Environment environment){
        return new RestTemplateCustomizer(environment);
    }

    @Bean(name = "customizedRestTemplate")
    public RestTemplate customizedRestTemplate(IRestTemplateCustomizer customizer){
        RestTemplate restTemplate = new RestTemplate();
        customizer.customize(restTemplate, OutboundStream.TEST);
        return restTemplate;
    }









