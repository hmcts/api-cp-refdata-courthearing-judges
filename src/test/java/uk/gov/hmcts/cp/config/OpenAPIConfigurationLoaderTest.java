package uk.gov.hmcts.cp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpenAPIConfigurationLoaderTest {

    @Test
    void openAPI_bean_should_have_expected_properties() {
        OpenAPIConfigurationLoader config = new OpenAPIConfigurationLoader();
        OpenAPI openAPI = config.openAPI();
        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("API CP Refdata Court Hearing Judges", info.getTitle());
        assertEquals("Reference Data API providing information on Judges associated with a Court Hearing", info.getDescription());

        String apiGitHubRepository = "api-cp-refdata-courthearing-judges";
        String expectedVersion = "0.0.0";

        assertEquals(expectedVersion, info.getVersion());

        License license = info.getLicense();
        assertNotNull(license);
        assertEquals("MIT", license.getName());
        assertEquals("https://opensource.org/licenses/MIT", license.getUrl());

        assertNotNull(info.getContact());
        assertEquals("no-reply@hmcts.com", info.getContact().getEmail());

        assertNotNull(openAPI.getServers());
        assertFalse(openAPI.getServers().isEmpty());
        assertEquals("https://virtserver.swaggerhub.com/HMCTS-DTS/" + apiGitHubRepository + "/" + expectedVersion,
                     openAPI.getServers().get(0).getUrl());
    }

    @Test
    void loadOpenApiFromClasspath_should_throw_for_missing_resource() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                OpenAPIConfigurationLoader.loadOpenApiFromClasspath("nonexistent-file.yaml")
        );
        assertTrue(exception.getMessage().contains("Missing resource"));
    }
} 