package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("http://live.demoguru99.com/");
    }

    @Test
    public void TC_01_Login_with_empty_Email_and_Password() {
        String errorMessage = "This is a required field.";

        //Open browser
        driver.get("http://live.demoguru99.com/");

        //Click My Account to open register
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Leave Username and Password empty
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("send2")).click();

        //Verify the error message is shown
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),errorMessage);
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),errorMessage);

    }

    @Test
    public void TC_02_Login_with_invalid_Email() {
        String errorMessage = "Please enter a valid email address. For example johndoe@domain.com.";

        //Open browser
        driver.get("http://live.demoguru99.com/");
        //Click My Account to open register
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Input invalid Email
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys("12334@1234");

        //Click Login button
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("send2")).click();

        //Verify the error message is shown
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),errorMessage);

    }

    @Test
    public void TC_03_Login_with_nvalid_Password() {
        String errorMessage = "Please enter 6 or more characters without leading or trailing spaces.";

        //Open browser
        driver.get("http://live.demoguru99.com/");
        //Click My Account to open register
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Input invalid password
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("pass")).sendKeys("1234");

        //Click Login button
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("send2")).click();

        //Verify the error message is shown
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),errorMessage);

    }

    @Test
    public void TC_04_Login_with_correct_Email_and_Password() {
        String errorMessage = "Invalid login or password.";

        //Open browser
        driver.get("http://live.demoguru99.com/");
        //Click My Account to open register
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Input invalid email
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
        //Input invalid password
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("pass")).sendKeys("123123123");

        //Click Login button
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("send2")).click();

        //Verify the error message is shown
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),errorMessage);

        //Close browser
        driver.quit();
    }
}
