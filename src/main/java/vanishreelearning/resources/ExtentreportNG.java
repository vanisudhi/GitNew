package vanishreelearning.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreportNG 
{
	
	public static ExtentReports getreportObject()
	{
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Framework Results");
		reporter.config().setDocumentTitle("Framework");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Newtest");
		return extent;
	}

}
