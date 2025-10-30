import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.TestName;
import service.StoreApiService;

public class DeleteOrderTests {

    private StoreApiService storeApiService;

    @BeforeTest
    public void setup() {
        storeApiService = new StoreApiService();
    }

    @Test
    @TestName("DELETE - order by id")
    public void deleteOrderTest() {
        Response response = storeApiService.deleteOrder(String.valueOf(10));
        ResponseValidator.validateStatusOk(response);
    }

    @Test(dataProvider = "invalidOrderIdParams")
    @TestName("GET - find order with invalid orderId")
    public void getOrderInvalidTest(String category, String orderId) {
        Response response = storeApiService.getOrder(orderId);
        ResponseValidator.validateExceptionalResponse(response);
    }


    @DataProvider(name = "invalidOrderIdParams")
    public Object[][] invalidOrderIdParams() {
        return new Object[][]{
                // Empty / Missing
                {"empty_or_missing", ""},
                {"missing", "null"},

                // Wrong type
                {"wrong_type_string", "abc"},
                {"wrong_type_boolean", "true"},
                {"wrong_type_array", "[]"},
                {"wrong_type_float", "1.2"},

                // Out of business range
                {"below_range_zero", "0"},
                {"above_range_11", "11"},
                {"above_range_999", "999"},

                // Negative values
                {"negative_value_minus_1", "-1"},
                {"negative_value_minus_100", "-100"},

                // Overflow
                {"overflow_big_number", "9223372036854775808"}
        };
    }

}
