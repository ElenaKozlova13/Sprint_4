package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    //кнопка "Заказать" вверху страницы
    private By orderButtonTop = By.xpath(".//div[@class='Header_Nav__AGCXC']" +
            "/button[@class='Button_Button__ra12g']");
    //кнопка "Заказать" в середине страницы
    private By orderButtonMiddle = By.xpath(".//div[@class='Home_FinishButton__1_cWm']" +
            "/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Стрелочка выпадающего списка, Элемент списка "Вопросы о важном" AccordionItem id=accordion__heading-69
    private By itemFAQButton = By.className("accordion__button");
    //Скрытый текст элемента списка "Вопросы о важном" id=accordion__panel-69
    private By itemFAQText = By.xpath(".//div[@class='accordion__panel']/p");
    //Кнопка куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");
    //кнопка "Статус заказа"
    private By orderStatusButton = By.className("Header_Link__1TAG7");
    //Поле "Номер заказа"
    private By orderNumberInput = By.className("Header_Input__xIoUq");
    //Кнопка поиска заказа Go!
    private By searchOrderButton = By.className("Header_Button__28dPO");
    //логотип Яндекс
    private By logoYandexLink = By.className("Header_LogoYandex__3TSOI");

    //конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод скролит и кликает по кнопке "Заказать", расположение кнопки на странице - параметр locationOrderButton
    public void clickOrderButton(String locationOrderButton) {
        By orderButton;
        switch (locationOrderButton) {
            case "top":
            orderButton = orderButtonTop;
            break;
            case "middle":
            orderButton = orderButtonMiddle;
            break;
            default: throw new RuntimeException("orderButton location '" + locationOrderButton + "' not exist");
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButton));
        driver.findElement(orderButton).click();
    }
    //метод кликает по стрелочке i-ого элемента выпадающего списка "Вопросы о важном"
    public void clickItemButton(int indexItemFAQ) {
        WebElement element = driver.findElements(itemFAQButton).get(indexItemFAQ);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }
    //метод возвращает скрытый текст i-ого элемента "Вопросы о важном", открывающийся при нажатии по стрелочке
    public String getTextItemFAQ(int indexItemFAQ) {
        List<WebElement> elements = driver.findElements(itemFAQText);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(elements.get(indexItemFAQ)));//ожидание элемента
        return elements.get(indexItemFAQ).getText();
    }
    //метод закрывает аллерт Куки
    public void clickCookieButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(cookieButton))).click();
    }
    //метод кликает на логотип Яндекс
    public void clickLogoYandexLink() {
        driver.findElement(logoYandexLink).click();
    }
    //метод кликает на кнопке "Статус заказа"
    public void clickOrderStatusButton() {
        driver.findElement(orderStatusButton).click();
    }
    //метод заполняет поле "Номер заказа"
    public void setOrderNumberInput(String orderNumber) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(orderNumberInput))).isDisplayed();
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }
    //метод кликает на кнопке поиска заказа Go!
    public void clickSearchOrderButton() {
        driver.findElement(searchOrderButton).click();
    }
    //шаг - поиск по номеру заказа
    public void searchOrderByNumber(String orderNumber) {
        clickOrderStatusButton();
        setOrderNumberInput(orderNumber);
        clickSearchOrderButton();
    }
}

