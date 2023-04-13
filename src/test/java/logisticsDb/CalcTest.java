package logisticsDb;
 
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.Reporter;
import org.testng.reporters.XMLReporter;
import org.testng.ITestResult;

 
public class CalcTest {
 
	Calculator calc;
    @BeforeSuite
    public void setUp() throws Exception {
    	 calc=new Calculator();
    }
 
    /*@AfterSuite
    public void tearDown() throws Exception {
    }*/
 
 
 
    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
            {  1, 2, 3 },
            {  2, 3, 4 },  // error or the data itself :)
            { -1, 1, 0 }
        };
    }
 
 
    @Test(dataProvider = "ValidDataProvider",priority=1)
    public void CanAddNumbersFromGivenData(final int a, final int b, final int c)
    {
        Assert.assertEquals(calc.add(a, b), c);
        
    }
 
 
    @Test(priority=2)
    public void CanAddNumbers()
    {
        Assert.assertEquals(calc.add(1, 1),2);
        Assert.assertEquals(calc.add(-1, 1),0);
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("requirement", "CALC-1234");   // Xray will try to create a link to this requirement issue
        result.setAttribute("test", "CALC-2");             // Xray will try to find this Test issue and report result against it
        result.setAttribute("labels", "core addition");    // Xray will add this(ese) label(s) to the associated Test issue
    }
 
   
}