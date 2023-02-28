import PageObject.MainPage;
import PageObject.SearchOrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StatusOrderTestClass {
    private WebDriver driver;
    private String incorrectOrderNumber;

    public StatusOrderTestClass(String incorrectOrderNumber) {
        this.incorrectOrderNumber = incorrectOrderNumber;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderNumber() {
        return new Object[][]{
                {" "},
                {""},
                {"text"},
        };
    }

    @Test
    public void errorIncorrectOrderNumberTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.searchOrderByNumber(incorrectOrderNumber);
        SearchOrderPage objSearchOrderPage = new SearchOrderPage(driver);
        assertTrue("expected picture with error message: Точно верный заказ?",
                objSearchOrderPage.isIncorrectOrderNumberErrorDisplayed());
    }
    @After
    public void teardown() {
        driver.quit();
    }
}
