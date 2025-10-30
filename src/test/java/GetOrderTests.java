import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

public class GetOrderTests extends BaseTest {

    @Test(testName = "GET - order by id")
    public void getOrderTest() {
        String id = String.valueOf(getRandomValidOrderId());
        Response actualResponse = storeApiService.getOrder(id);
        ResponseValidator.validateStatusOk(actualResponse);
    }

    private int getRandomValidOrderId() {
        return new Random().nextInt(0, 10);
    }

    @Test(testName = "GET - get order exceptional", dataProvider = "invalidOrderIds")
    public void getOrderInvalidTest(String category, String orderId) {
        Response response = storeApiService.getOrder(orderId);
        ResponseValidator.validateExceptionalResponse(response);
    }

    @DataProvider(name = "invalidOrderIds", parallel = true)
    public Object[][] invalidOrderIds() {
        return new Object[][]{
                // Empty / Missing
                {"empty_or_missing", ""},
                {"missing", "null"},

                // Wrong type
                {"wrong_type_string", "abc"},
                {"wrong_type_boolean", "true"},
                {"wrong_type_array", "\"[]\""},
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
