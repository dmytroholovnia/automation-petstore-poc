import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class ResponseValidator {

    public static void validateStatusOk(Response response) {
        response.then().statusCode(HttpStatus.SC_OK);
    }

    public static void validateExceptionalResponse(Response response) {
        response.then().statusCode(anyOf(
                is(HttpStatus.SC_BAD_REQUEST),
                is(HttpStatus.SC_NOT_FOUND)
        ));
    }
}
