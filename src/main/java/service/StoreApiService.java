package service;

import core.BaseApiService;
import dto.OrderDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StoreApiService extends BaseApiService {

    private static final String INVENTORY_URL = "/store/inventory";
    private static final String ORDER_URL = "/store/order";
    private static final String ORDER_BY_ID_URL = ORDER_URL + "/{orderId}";

    @Step("Send GET request to " + INVENTORY_URL)
    public Response getInventory() {
        return given()
                .when()
                .get(INVENTORY_URL)
                .then()
                .extract().response();
    }

    @Step("Send POST request to " + ORDER_URL)
    public Response postOrder(OrderDto requestDto) {
        return given()
                .body(requestDto)
                .when()
                .post(ORDER_URL)
                .then()
                .extract().response();
    }

    @Step("Send GET request to " + ORDER_BY_ID_URL)
    public Response getOrder(String id) {
        return given()
                .pathParam("orderId", id)
                .when()
                .get(ORDER_BY_ID_URL)
                .then()
                .extract().response();
    }

    @Step("Send DELETE request to " + ORDER_BY_ID_URL)
    public Response deleteOrder(String id) {
        return given()
                .pathParam("orderId", id)
                .when()
                .delete(ORDER_BY_ID_URL)
                .then()
                .extract().response();
    }
}
