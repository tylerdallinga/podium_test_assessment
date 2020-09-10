package UI;

import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.remote.server.handler.WebElementHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;


import java.awt.*;
import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.*;


import static javax.swing.text.html.CSS.getAttribute;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;
import static UI.mainFunctions.*;

public class tests {
    //    public WebDriver driver;
    private WebDriver driver;
    private String baseUrl;
    private WebElement element;

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
    }

    @BeforeClass
    public void startClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/tyler/Desktop/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void stopSelenium() {
        driver.quit();
    }

    @BeforeMethod()
    public void setUp() throws Exception {
//        driver = new ChromeDriver();
//        baseUrl = "https://www.podiium.com/";
//        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

    }

    @AfterMethod()
    public void afterMethod() {
    }

    public void assertLaunchScreen() throws Exception {
        driver.get("https://www.podium.com/");
        Assert.assertTrue(driver.getTitle().contains("Podium"));
        driver.findElement(By.className("demo-header"));
    }

    public void watchDemo() throws Exception {
        WebElement elems = driver.findElement(By.xpath("//*[@id=\"site-navigation\"]/div[2]/div[2]/div[2]/div/a"));

        Actions builder = new Actions(driver);
        Action seriesOfActions = builder
                .moveToElement(elems)
                .click()
                .build();
        seriesOfActions.perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("mktoButton")));
        driver.findElement(By.className("mktoButton"));
        SoftAssert softAssert = new SoftAssert();
        String continueButton = "mktoButton";
        softAssert.assertEquals(continueButton, "Continue Watching");
        System.out.println("This is all of the Demo we can watch before signing in");
        //wanted to continue this test but I didn't want to spam your guy's sells team
    }

    public void productDropdownNavigation(String xpath, String text) throws Exception {
        WebElement elems1 = driver.findElement(By.xpath("//*[@id=\"wp-megamenu-item-2312\"]"));
        WebElement elems2 = driver.findElement(By.xpath(xpath));

        Actions builder = new Actions(driver);
        Action seriesOfActions = builder
                .moveToElement(elems1)
                .moveToElement(elems2)
                .click()
                .build();
        seriesOfActions.perform();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/div/h1")));
        WebElement reviewPage = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/div/h1"));
        String pageText = reviewPage.getText();
        String expectedText = text;
        Assert.assertEquals(pageText, expectedText);
    }

    public void navigationDropdownGoBack() throws Exception {
        driver.navigate().back();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"site-navigation\"]/div[2]/div[2]/div[2]")));
        Assert.assertTrue(driver.getTitle().contains("Podium"));
    }

    public void signIn(String username, String password) throws Exception {

        WebElement login = driver.findElement(By.xpath("//*[@id=\"menu-item-1317\"]/a"));
//        WebElement usernameSelector=driver.findElement(By.xpath("//*[@id=\"emailOrPhoneInput\"]"));
//        WebElement passwordSelector=driver.findElement(By.xpath("//*[@id=\"passwordInput\"]"));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(login));

        Actions builder = new Actions(driver);
        Action clickLogin = builder
                .moveToElement(login)
                .click()
                .build();
        clickLogin.perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailOrPhoneInput")));

        WebElement usernameSelector = driver.findElement(By.id("emailOrPhoneInput"));


        Action usernameEnter = builder
                .moveToElement(usernameSelector)
                .click()
                .sendKeys(username)
                .sendKeys(Keys.ENTER)
                .build();
        usernameEnter.perform();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwordInput")));

        WebElement passwordSelector = driver.findElement(By.id("passwordInput"));


        Action seriesOfActions = builder
                .moveToElement(passwordSelector)
                .click()
                .sendKeys(password)
                .sendKeys(Keys.ENTER)
                .build();
        seriesOfActions.perform();
    }

    public void signInError() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]")));
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div[2]"));
        String pageText = errorMessage.getText();
        String expectedText = "Incorrect email or password. Please try again.";
        Assert.assertEquals(pageText, expectedText);
        System.out.println("Testing that error message is correct");
    }

//    public List WebElementsByXpathwebview(String xpath) throws Exception{
//      String xPathofText = xpath;
//      System.out.println("Xpath of current item is: "+xPathofText+"");
//      List tempElement = driver.findElements(By.xpath(xPathofText));
//      if (tempElement.size()<1){
//          System.out.println("/n" + xpath + "was not found on the screen. xpath was: " + xPathofText);
//      }
//      return tempElement;
//    }

    private boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;
    }

    public void sendChatMessage() throws Exception {


        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("ContactBubble")));

        WebElement chatBubble = driver.findElement(By.xpath("//*[@id=\"podium-bubble\"]"));

        Actions builder = new Actions(driver);
        Action openBubble = builder
                .moveToElement(chatBubble)
                .click()
                .build();
        openBubble.perform();

//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Name\"]")));
//        Thread.sleep(2000);
//        WebElement name=driver.findElement(By.xpath("//*[@id=\"Name\"]"));

        Action enterName = builder
                .sendKeys("Tyler Dallinga")
                .sendKeys((Keys.TAB))
                .build();
        enterName.perform();


//      WebElement sendButton=driver.findElement(By.xpath("//*[@id=\"ComposeMessage\"]/form/div[2]/div[2]/button/div"));

//        isAttribtuePresent(sendButton,"disabled");

        Action enterPhone = builder
                .sendKeys("8017918182")
                .sendKeys((Keys.TAB))
                .pause(2000)
                .build();
        enterPhone.perform();

//        WebElement message=driver.findElement(By.id("Message"));

        Action enterMessage = builder
                .sendKeys("This is a message")
                .sendKeys(Keys.TAB)
//                .sendKeys(Keys.ENTER) //don't want to send a form
                .build();
        enterMessage.perform();

        //After this point I would like to assert the actual message sent and that I could get one back
        //I ran into a little issue with tyring to select elements in the chat bubble so would probably try to figure that out as well

    }





    //*************************************************************** Tests ***************************************************************

    @Test()
    public void goToPodium() throws Exception {
        assertLaunchScreen();
    }

    @Test()
    public void testDemoVideo() throws Exception{
        assertLaunchScreen();
        watchDemo();
    }

    @Test()
    public void testPoductMainDropdownNav() throws Exception{
        assertLaunchScreen();
        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2307\"]", "Contact-free business starts with reviews.™");
        navigationDropdownGoBack();
        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2308\"]", "Collect no-contact payments via text.™");
        navigationDropdownGoBack();

        //I had to comment out the next two dropdown items do to the fact that the selector on the page is the same one for payments, webchat, and video chat.
        //Not sure if this is a bug or would be a problem but it would have to be something I would bring up, and if it wasn't a bug then I would have to find another way to select these elements.
//
//        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2308\"]","Connect remotely with website visitors.™");
//        navigationDropdownGoBack();
//        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2308\"]", "Get face to face with Videochat™"); //also the text here is missing a period. lol
//        navigationDropdownGoBack();
        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2309\"]", "Gather Feedback.™" );
        navigationDropdownGoBack();
        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2310\"]", "Collaborate with Teamchat.™");
        navigationDropdownGoBack();
        productDropdownNavigation("//*[@id=\"wp-megamenu-item-2311\"]", "Centralize interactions.");
    }

    @Test()
    public void testingSignInErrorMessage() throws Exception{
        assertLaunchScreen();
        signIn("tyler.dallinga@gmail.com", "password");
        signInError();
    }

    @Test()
    public void testChatbubble() throws Exception{
        assertLaunchScreen();
        Thread.sleep(5000);
        sendChatMessage();
    }



}