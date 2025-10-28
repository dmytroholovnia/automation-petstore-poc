import dto.InventoryResponseDto;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.StoreApiService;

public class OrderTests {

    private StoreApiService storeApiService;

    @BeforeTest
    public void setup() {
        storeApiService = new StoreApiService();
    }

    @Test
    public void getOrdersTest() {
        InventoryResponseDto actualDto = storeApiService.getInventory();
    }
}
