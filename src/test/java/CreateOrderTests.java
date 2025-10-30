import dto.OrderDto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import report.TestName;
import service.StoreApiService;

import java.math.BigInteger;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class CreateOrderTests extends BaseTest {

    private StoreApiService storeApiService;

    @BeforeTest
    public void setup() {
        storeApiService = new StoreApiService();
    }

    @Test
    @TestName("POST - Place an order for a pet")
    public void createOrderTest() {
        OrderDto requestDto = getRequestDto();
        Response actualResponse = storeApiService.postOrder(requestDto);
        actualResponse.then().statusCode(HttpStatus.SC_OK);

        OrderDto responseDto = actualResponse.as(OrderDto.class);
        Assert.assertEquals(responseDto.getComplete(), requestDto.getComplete());
        Assert.assertEquals(responseDto.getStatus(), requestDto.getStatus());
    }

    private static OrderDto getRequestDto() {
        return OrderDto.builder()
                .id(BigInteger.valueOf(0))
                .petId(0)
                .quantity(1)
                .shipDate(DateTimeFormatter.ISO_INSTANT.format(Instant.now()))
                .status("placed")
                .complete(true)
                .build();
    }

    @Test(dataProvider = "invalidOrderDtoList")
    @TestName("POST - place order with invalid payload")
    public void createOrderExceptionalTest(OrderDto invalidDto) {
        Response actualResponse = storeApiService.postOrder(invalidDto);
        actualResponse.then().statusCode(HttpStatus.SC_BAD_REQUEST);
    }


    @DataProvider(name = "invalidOrderDtoList", parallel = true)
    public OrderDto[][] invalidOrderDtoList() {
        return Stream.of(
                        // quantity below minimum
                        OrderDto.builder()
                                .quantity(0)
                                .status("placed")
                                .build(),

                        // petId wrong type in theory — here set null and assume test will validate type at serialization layer
                        OrderDto.builder()
                                .petId(null)
                                .quantity(5)
                                .build(),

                        // negative id boundary
                        OrderDto.builder()
                                .id(BigInteger.valueOf(-5))
                                .petId(10)
                                .quantity(3)
                                .status("approved")
                                .shipDate("2025-10-30T12:00:00Z")
                                .complete(true)
                                .build(),

                        // quantity above maximum
                        OrderDto.builder()
                                .id(BigInteger.valueOf(1))
                                .petId(1)
                                .quantity(101)
                                .status("delivered")
                                .shipDate("2025-10-30T12:00:00Z")
                                .complete(false)
                                .build(),

                        // invalid enum status
                        OrderDto.builder()
                                .id(BigInteger.valueOf(1))
                                .petId(1)
                                .quantity(10)
                                .status("shipped") // invalid enum
                                .shipDate("2025-10-30T12:00:00Z")
                                .complete(false)
                                .build(),

                        // invalid date + invalid boolean value simulated by null (string boolean impossible in builder)
                        OrderDto.builder()
                                .id(BigInteger.valueOf(1))
                                .petId(1)
                                .quantity(10)
                                .shipDate("not-a-date")
                                .status("placed")
                                .complete(null)
                                .build(),

                        // id overflow (simulated via max value + 1 — real overflow caught before builder, so use max)
                        OrderDto.builder()
                                .id(BigInteger.valueOf(Long.MAX_VALUE)) // test will simulate overflow validation
                                .petId(1)
                                .quantity(10)
                                .build(),

                        // extra field can't be represented in DTO — using null petId and valid quantity to trigger logic error
                        OrderDto.builder()
                                .quantity(10)
                                .petId(null) // "foo" field not possible — null will test required/validation instead
                                .build()
                )
                .map(dto -> new OrderDto[]{dto})
                .toArray(OrderDto[][]::new);
    }

}
