import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ReportTests {

    static ExtentReports extent;
    static ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test
    public void sampleTest1() {
        test = extent.createTest("Sample Test 1");
        test.pass("This test passed!");
    }

    @Test
    public void sampleTest2() {
        test = extent.createTest("Sample Test 2");
        test.fail("This test failed intentionally.");
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

}
