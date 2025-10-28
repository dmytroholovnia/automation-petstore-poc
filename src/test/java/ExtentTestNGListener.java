import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;
import report.TestName;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        // Initialize report once per suite
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Test Execution Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Your Name");
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getDescription(); // TestNG description
        if (name == null || name.isEmpty()) {
            TestName annotation = result.getMethod()
                    .getConstructorOrMethod()
                    .getMethod()
                    .getAnnotation(TestName.class);
            if (annotation != null) {
                name = annotation.value();
            } else {
                name = result.getMethod().getMethodName();
            }
        }

        ExtentTest test = extent.createTest(name)
                .assignCategory(result.getTestClass().getName());
        testThread.set(test);
    }

//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
//        testThread.set(test);
//    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip(result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }
}

