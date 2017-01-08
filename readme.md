##

**RestTemplate Customizer**

###

This utility intercepts all your outbound http requests and modify it (add headers and change urls).  


##
****How To Use****

###
****Configuration****

utility.subscriptionkey=headerToAdd

utility.test=https://urlToChange/something

****RestTemplate****

	@Bean
    public IAPIMRestTemplateCustomizer customize(Environment environment){
        return new APIMRestTemplateCustomizer(environment);
    }

    @Bean(name = "customizedRestTemplate")
    public RestTemplate apimCustomizedRestTemplate(IAPIMRestTemplateCustomizer customizer){
        RestTemplate restTemplate = new RestTemplate();
        customizer.customize(restTemplate, APIMOutboundStream.NETAXEPT);
        return restTemplate;
    }









