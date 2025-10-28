import org.testng.Assert;
import org.testng.annotations.Test;
import report.TestName;

public class InitTest extends BaseTest {

    @Test
    @TestName("Positive test")
    public void testPositive() {
        Assert.assertEquals(2, 2);
    }

    @Test
    @TestName("Fail test")
    public void testNegative() {
        Assert.assertEquals(2,3);
    }
}
