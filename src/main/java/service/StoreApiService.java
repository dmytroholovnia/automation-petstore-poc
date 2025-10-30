package service;

import core.BaseApiService;
import dto.OrderDto;
import io.restassured.response.Response;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StoreApiService extends BaseApiService {

    private static final String INVENTORY_URL = "/store/inventory";
    private static final String ORDER_URL = "/store/order";
    private static final String ORDER_BY_ID_URL = ORDER_URL + "/{orderId}";

    /**
     * @return Returns a map of status codes to quantities
     */
    public Response getInventory() {
        return given()
                .when()
                .get(INVENTORY_URL)
                .then()
                .extract().response();
    }

    /**
     *
     * @param requestDto
     * @return order placed for purchasing the pet
     */
    public Response postOrder(OrderDto requestDto) {
        return given()
                .body(requestDto)
                .when()
                .post(ORDER_URL)
                .then()
                .extract().response();
    }

    public Response getOrder(String id) {
        return given()
                .pathParam("orderId", id)
                .when()
                .get(ORDER_BY_ID_URL)
                .then()
                .extract().response();
    }

    public Response deleteOrder(String id) {
        return given()
                .pathParam("orderId", id)
                .when()
                .delete(ORDER_BY_ID_URL)
                .then()
                .extract().response();
    }
}
