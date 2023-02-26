import PageObject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MainPageTestClass {
    private WebDriver driver;
    private int indexItemFAQ;
    private String itemFAQText;
    public MainPageTestClass(int indexItemFAQ, String itemFAQText) {
        this.indexItemFAQ = indexItemFAQ;
        this.itemFAQText = itemFAQText;
    }
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }
    @Parameterized.Parameters
    public static Object[][] getItemFAQ() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    //    Тестовый сценарий Выпадающий список в разделе «Вопросы о важном». Тебе нужно проверить: когда нажимаешь на стрелочку, открывается соответствующий текст.
    @Test
    public void itemsFAQTest() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieButton();
        objMainPage.clickItemButton(indexItemFAQ);
        assertEquals(itemFAQText, objMainPage.getTextItemFAQ(indexItemFAQ));
    }
    @After
    public void teardown() {
        driver.quit();
    }
}

