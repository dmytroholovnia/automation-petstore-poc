import io.restassured.response.Response;
import org.testng.annotations.Test;

public class InventoryTests extends BaseTest {

    @Test(testName = "GET - get inventory api test")
    public void getOrdersTest() {
        Response actualResponse = storeApiService.getInventory();
        ResponseValidator.validateStatusOk(actualResponse);
    }
}
