package au.com.ip.api.customerprofiles.config;
import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(paths())
                .apis(RequestHandlerSelectors.basePackage("au.com.ip.api"))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaData());
    }

    private Predicate<String> paths() {
        return not(or(regex("/"), regex("/webjars.*")));
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "Customer Profile REST API ",
                "REST API for Customer Profile Management Project",
                "1.1",
                "Terms of service",
                new Contact("IP", "https://www.intelligentpathways.com.au", ""),
                "",
                "",
                new ArrayList<>());
    }
}