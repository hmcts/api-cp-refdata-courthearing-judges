package uk.gov.hmcts.cp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;


public class OpenAPIConfigurationLoader {

    private static final String JUDGES_OPENAPI = "openapi/judges.openapi.yml";

    public final OpenAPI openAPI() {
        return loadOpenApiFromClasspath(JUDGES_OPENAPI);
    }

    public static OpenAPI loadOpenApiFromClasspath(final String path) {
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Missing resource: " + path);
            }
            final String yaml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return new OpenAPIV3Parser().readContents(yaml, null, null).getOpenAPI();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}