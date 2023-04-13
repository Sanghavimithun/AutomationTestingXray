package logisticsDb;

import static org.testng.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

public class CalcTest {
 
	Calculator calc;
    @Before
    public void setUp() throws Exception {
    	 calc=new Calculator();
    }
 
    /*@AfterSuite
    public void tearDown() throws Exception {
    }*/
 
 
 
    /*@DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
            {  1, 2, 3 },
            {  2, 3, 4 },  // error or the data itself :)
            { -1, 1, 0 }
        };
    }*/
 
 
//    @Test
//    public void CanAddNumbersFromGivenData()
//    {
//        Assert.assertEquals(calc.add(1, 2), 3);
//        
//    }
 
 
    @Test
    public void CanAddNumbers()
    {
        assertEquals(calc.add(2, 3),4);
        assertEquals(calc.add(-1, 1),0);
//        ITestResult result = Reporter.getCurrentTestResult();
//        result.setAttribute("requirement", "CALC-1234");   // Xray will try to create a link to this requirement issue
//        result.setAttribute("test", "CALC-2");             // Xray will try to find this Test issue and report result against it
//        result.setAttribute("labels", "core addition");    // Xray will add this(ese) label(s) to the associated Test issue
    }
 
   
}