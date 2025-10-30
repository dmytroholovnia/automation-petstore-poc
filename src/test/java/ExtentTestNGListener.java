import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.*;
import report.TestName;

import java.util.Date;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
        spark.config().setReportName("Test Execution Timeline");
        spark.config().setDocumentTitle("Parallel Execution Report");
        spark.config().setTimelineEnabled(true);
        spark.config().setReportName("Automation run");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Parallel", "true");
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        synchronized (this) {
            String testName = result.getMethod().getDescription();
            if (testName == null || testName.isEmpty()) {
                testName = result.getMethod().getMethodName();
            }
            String threadInfo = "Thread-" + Thread.currentThread().getId();

            ExtentTest test = extent.createTest(testName)
                    .assignCategory(result.getTestClass().getRealClass().getSimpleName())
                    .assignDevice(threadInfo);  // Show thread ID as “device” tag
            testThread.set(test);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testThread.get();
        test.getModel().setStartTime(new Date(result.getStartMillis()));
        test.getModel().setEndTime(new Date(result.getEndMillis()));
        test.pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip(result.getThrowable());
    }

}

