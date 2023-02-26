import PageObject.MainPage;
import PageObject.OrderPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class CreateNewOrderTestClass {
    private WebDriver driver;
    private String locationOrderButton;
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String date;
    private int indexRentTerm;
    private int indexColor;
    private String comment;
    private boolean isOrderSuccess;

    public CreateNewOrderTestClass(String locationOrderButton, String name, String surname, String address,
                                   String metro, String phone, String date, int indexRentTerm, int indexColor,
                                   String comment, boolean isOrderSuccess) {
        this.locationOrderButton = locationOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.indexRentTerm = indexRentTerm;
        this.indexColor = indexColor;
        this.comment = comment;
        this.isOrderSuccess = isOrderSuccess;
    }

    @Before //для запуска Chrome
     public void startUp() {
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
     }
/*     @Before //для запуска Firefox
    public void startBrowser() {
        String driverPath = "C:\\webdriver\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);
    }*/

    @Parameterized.Parameters
        public static Object[][] getNewOrderData() {
            return new Object[][]{
                    {"top", "Вася", "Пупкин", "Красная площадь, д.1", "Охотный Ряд", "81111111111", "27.02.2023", 1, 0,
                            "позвонить за час", true},
                    {"middle", "Ян", "Ли", "2-ой кратер луны", "Курская", "+79111111111", "31.12.2024", 5, 1,
                            "нечего сказать", true},
            };
        }

    //    Тестовый сценарий - Заказ самоката
    @Test
    public void createNewOrderTest() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookieButton();
        objMainPage.clickOrderButton(locationOrderButton);
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.setFirstOrderForm(name, surname, address, metro, phone);
        objOrderPage.setSecondOrderForm(date, indexRentTerm, indexColor, comment);
        MatcherAssert.assertThat(objOrderPage.getConfirmationHeaderText(), containsString("Заказ оформлен"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}