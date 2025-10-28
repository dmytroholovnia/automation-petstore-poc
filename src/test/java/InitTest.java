import org.testng.Assert;
import org.testng.annotations.Test;

public class InitTest {

    @Test
    public void testPositive() {
        Assert.assertEquals(2, 2);
    }

    @Test
    public void testNegative() {
        Assert.assertEquals(2,3);
    }
}
