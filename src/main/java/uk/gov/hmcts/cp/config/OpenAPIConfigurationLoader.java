package uk.gov.hmcts.cp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;


public class OpenAPIConfigurationLoader {

    private static final String JUDGES_OPENAPI_YML = "openapi/judges.openapi.yml";

    public final OpenAPI openAPI() {
        return loadOpenApiFromClasspath(JUDGES_OPENAPI_YML);
    }

    public final static OpenAPI loadOpenApiFromClasspath(String path) {
        try (InputStream inputStream = OpenAPIConfigurationLoader.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Missing resource: " + path);
            }
            String yaml = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            return new OpenAPIV3Parser().readContents(yaml, null, null).getOpenAPI();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}