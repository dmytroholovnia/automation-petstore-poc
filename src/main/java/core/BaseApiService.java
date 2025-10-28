package core;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.specification.RequestSpecification;

public abstract class BaseApiService {
    private static final String BASE_URL = "https://petstore.swagger.io/";
    protected RequestSpecification requestSpecification;

    public BaseApiService() {
        this.requestSpecification = getSpecification();
    }

    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setConfig(getConfig())
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    private RestAssuredConfig getConfig() {
        return RestAssured.config = RestAssured.config()
                .encoderConfig(RestAssured.config().getEncoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false))
                .objectMapperConfig(
                        ObjectMapperConfig.objectMapperConfig()
                                .jackson2ObjectMapperFactory((cls, charset) ->
                                        CustomObjectMapper.getObjectMapper()
                                )
                )
                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
    }
}
