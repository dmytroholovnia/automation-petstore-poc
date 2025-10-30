import org.testng.annotations.BeforeTest;
import service.StoreApiService;

abstract class BaseTest {
    protected StoreApiService storeApiService;

    @BeforeTest
    public void setup() {
        storeApiService = new StoreApiService();
    }

}
