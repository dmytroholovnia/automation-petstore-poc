package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public abstract class BaseApiService {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
//    private final RequestSpecification requestSpecification;
//
//    public BaseApiService() {
//        this.requestSpecification = getSpecification();
//    }

    public RequestSpecification given() {
        return RestAssured.given(getSpecification());
    }

    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setConfig(getConfig())
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
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
