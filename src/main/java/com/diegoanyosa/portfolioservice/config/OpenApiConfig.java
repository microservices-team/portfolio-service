package com.diegoanyosa.portfolioservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableDiscoveryClient
public class OpenApiConfig {

    private static final String BEARER_SCHEME = "bearerAuth";

    @Value("${server.port:8082}")
    private String serverPort;

    @Bean
    public OpenAPI portfolioOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local development"),
                        new Server()
                                .url("https://api.diegoanyosa.com")
                                .description("Production (via API Gateway)")
                ))
                .addSecurityItem(new SecurityRequirement().addList(BEARER_SCHEME))
                .components(new Components()
                        .addSecuritySchemes(BEARER_SCHEME,
                                new SecurityScheme()
                                        .name(BEARER_SCHEME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description(
                                                "JWT token forwarded by the API Gateway as a Bearer token. " +
                                                "Write operations require ADMIN role."
                                        )
                        )
                );
    }

    private Info apiInfo() {
        return new Info()
                .title("Portfolio Service API")
                .description("""
                        REST API that exposes portfolio data: experience, skills, projects,
                        certifications, and education.

                        **Public endpoints** – all `GET /api/portfolio/**` paths are open.

                        **Protected endpoints** – `POST`, `PUT`, `DELETE` require a valid JWT
                        with the `ADMIN` role, issued by the auth-service and forwarded by the
                        API Gateway via the `X-User-Id` / `X-User-Roles` headers.
                        """)
                .version("1.0.0")
                .contact(new Contact()
                        .name("Diego Anyosa")
                        .email("contact@diegoanyosa.com")
                        .url("https://diegoanyosa.com"))
                .license(new License()
                        .name("MIT License")
                        .url("https://opensource.org/licenses/MIT"));
    }
}
