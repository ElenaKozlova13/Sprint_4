package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchOrderPage {
    private WebDriver driver;

    //сообщение об ошибке
    private By incorrectOrderNumberError = By.xpath(".//img[@alt = 'Not found']");

    //конструктор класса
    public SearchOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод проверяет видимость сообщения об ошибке при поиске заказа с несуществуюшим номером
    public boolean isIncorrectOrderNumberErrorDisplayed() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(incorrectOrderNumberError)));//ожидание элемента
        return driver.findElement(incorrectOrderNumberError).isDisplayed();
    }
}
