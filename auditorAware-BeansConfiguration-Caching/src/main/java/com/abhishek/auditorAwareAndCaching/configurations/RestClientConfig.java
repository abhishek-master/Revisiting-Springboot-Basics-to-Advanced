package com.abhishek.auditorAwareAndCaching.configurations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${jpaService.base.url}")
    private String JPA_SERVICE_BASE_URL ;

    @Bean
    @Qualifier("getJpaMicroserviceRestClient")
    RestClient getJpaMicroserviceRestClient () {
        return RestClient.builder()
                .baseUrl(JPA_SERVICE_BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .build();

    }
}

//--------------WHY NO NEED TO EXPLICITLY MARK THE REST-CLIENT AS JPA REST CLIENT---------------------//
/*
* The application knows to use the custom RestClient because of Spring's Dependency Injection and bean configuration. This happens automatically through the configuration class and constructor injection patterns that are common in Spring Boot applications.

---- Bean Declaration and Injection ----
The RestClientConfig class uses @Configuration and @Bean annotations to register a RestClient bean with the Spring context.

Spring scans this configuration and creates an instance of the bean at startup.

---- Wiring with Constructor Injection ----
In the DoctorClientImpl service, the presence of private final RestClient restClient; and the use of
@RequiredArgsConstructor (from Lombok) mean that Spring will automatically inject the matching RestClient
bean when constructing the service.

If only one bean of type RestClient exists, Spring injects it without needing explicit reference.

If multiple beans exist (for example, if you use different qualifiers), @Qualifier("getJpaMicroserviceRestClient")
can be added to the constructor parameter.

---- How Spring Resolves the Dependency ----
When Spring initializes DoctorClientImpl, it finds the constructor and attempts to
satisfy its parameters using beans in the Application Context.

It matches by type, and optionally by qualifier if specified.

The RestClient bean is discovered and injected, so every call to restClient.get()
in DoctorClientImpl uses your configured client.

---- No Explicit Mention Needed ----
Manual mention or wiring is unnecessary because Spring manages dependencies using annotations and context scanning.*/



/*
* If there are 10 beans of the same type in your Spring context, use the @Qualifier annotation to specify exactly which bean should be injected. Without a qualifier, Spring throws a NoUniqueBeanDefinitionException due to ambiguity.

Using @Qualifier with Multiple Beans
Annotate the injection point: Add @Qualifier("beanName") alongside @Autowired in your constructor, field, or setter to indicate which exact bean is required.

Bean Name Matches: The string value inside @Qualifier must match the bean name as declared in your configuration or component class.

Example for Constructor Injection:

JAVA
@Autowired
public DoctorClientImpl(@Qualifier("getJpaMicroserviceRestClient") RestClient restClient) {
    this.restClient = restClient;
}
Example for Field/Setter Injection:

JAVA
@Autowired
@Qualifier("getJpaMicroserviceRestClient")
private RestClient restClient;
Repeat: For each component needing a different bean, use a different qualifier value.

What Happens Without @Qualifier
Spring cannot determine which bean to inject and throws an error indicating too many candidates are found for the type*/
