import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import report.TestName;
import service.StoreApiService;

public class InventoryTests extends BaseTest {

    private StoreApiService storeApiService;

    @BeforeTest
    public void setup() {
        storeApiService = new StoreApiService();
    }

    @Test
    @TestName("GET - get inventory api test")
    public void getOrdersTest() {
        Response actualResponse = storeApiService.getInventory();
        ResponseValidator.validateStatusOk(actualResponse);
    }
}
