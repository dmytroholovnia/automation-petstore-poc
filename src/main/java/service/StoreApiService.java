package service;

import core.BaseApiService;
import dto.InventoryResponseDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StoreApiService extends BaseApiService {

    private static final String INVENTORY_URL = "v2/store/inventory";

    public StoreApiService() {
        super();
    }

    public InventoryResponseDto getInventory() {
        Response response = RestAssured.given(requestSpecification)
                .log().uri()
                .when()
                .get(INVENTORY_URL)
                .then()
                .log()
                .body()
                .statusCode(200)
                .extract().response();
        return response.as(InventoryResponseDto.class);
    }
}
