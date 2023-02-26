import PageObject.MainPage;
import PageObject.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class RedirectTestClass {
    private WebDriver driver;
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void newTabYandexOpenTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        String mainPageYandexURL = "https://dzen.ru/?yredirect=true";
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickLogoYandexLink();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        assertEquals("expected to redirect to new tab with URL " + mainPageYandexURL, mainPageYandexURL,driver.getCurrentUrl());
    }
    @Test
    public void redirectToMainPageTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        String mainPageURL = "https://qa-scooter.praktikum-services.ru/";
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.clickLogoScooterLink();
        assertEquals("expected to redirect to page with url " + mainPageURL, mainPageURL,driver.getCurrentUrl());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}