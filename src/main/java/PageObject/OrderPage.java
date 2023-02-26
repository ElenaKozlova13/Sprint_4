package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //Поле Имя
    private By orderNameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //Поле Фамилия
    private By orderSurnameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //Поле Адрес
    private By orderAddressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //Выпадающий список Метро, поле поиска
    private By orderMetroSelectInput = By.className("select-search__input");
    //Выпадающий список Метро, кнопка выбора опции
    private By orderMetroSelectButton = By.xpath(".//div[@class = 'Order_Text__2broi']/parent::button");
    //Поле телефон
    private By orderPhoneInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //Кнопка Далее
    private By orderNextButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Поле ввода Выбор даты
    private By datePickerInput = By.xpath(".//div[@class = 'react-datepicker__input-container']/input");
    //Выпадающий список Срок аренды
    private By rentTermDropdown = By.className("Dropdown-root");
    //Элементы выпадающего списока Срок аренды
    private By rentTermDropdownOption = By.className("Dropdown-option");
    //Чек-бокс цвет
    private By colorCheckBox = By.className("Checkbox_Label__3wxSf");
    //Поле комментарий
    private By commentInput = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    //Кнопка Заказать
    private By orderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Кнопка подтверждения Да
    private By confirmOrderButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div" +
            "/button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Текст подтверждения заказа: Заказ оформлен
    private By confirmationHeaderText = By.className("Order_ModalHeader__3FDaJ");

    //логотип Самокат
    private By logoScooterLink = By.className("Header_LogoScooter__3lsAR");

    //конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполняет поле Имя
    public void setOrderNameInput(String name) {
        driver.findElement(orderNameInput).sendKeys(name);
    }
    //метод заполняет поле Фамилия
    public void setOrderSurnameInput(String surname) {
        driver.findElement(orderSurnameInput).sendKeys(surname);
    }
    //метод заполняет поле Адрес
    public void setOrderAddressInput(String address) {
        driver.findElement(orderAddressInput).sendKeys(address);
    }
    //метод заполняет поле Метро
    public void setOrderMetroSelectInput(String metro) {
        driver.findElement(orderMetroSelectInput).sendKeys(metro);
        driver.findElements(orderMetroSelectButton).get(0).click();
    }
    //метод заполняет поле Телефон
    public void setOrderPhoneInput(String phone) {
        driver.findElement(orderPhoneInput).sendKeys(phone);
    }
    //метод скролит и кликает по кнопке "Далее"
    public void clickOrderNextButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderNextButton));
        driver.findElement(orderNextButton).click();
    }
    //метод заполняет первую часть формы заказа "Для кого самокат"
    public void setFirstOrderForm(String name, String surname, String address, String metro, String phone) {
        setOrderNameInput(name);
        setOrderSurnameInput(surname);
        setOrderAddressInput(address);
        setOrderMetroSelectInput(metro);
        setOrderPhoneInput(phone);
        clickOrderNextButton();
    }
    //метод устанавливает дату начала аренды, формат дд.мм.гггг
    public void setDatePickerInput(String date) {
        driver.findElement(datePickerInput).sendKeys(date);
        driver.findElement(datePickerInput).sendKeys(Keys.ENTER);
    }
    //метод устанавливает срок аренды, принимает индекс опции из списка
    public void setRentTermSelectInput(int indexRentTerm) {
        driver.findElement(rentTermDropdown).click();
        driver.findElements(rentTermDropdownOption).get(indexRentTerm).click();
    }
    //метод устанавливает цвет, принимает индекс опции из списка
    public void setColorCheckBox(int indexColor) {
        driver.findElements(colorCheckBox).get(indexColor).click();
    }
    //метод заполняет поле Комментарий
    public void setCommentInput(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }
    //метод нажимает кнопку Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //метод нажимает кнопку подтверждения заказа Да
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
    //метод заполняет вторую часть формы заказа "Про аренду"
    public void setSecondOrderForm(String date, int indexRentTerm, int indexColor, String comment) {
        setDatePickerInput(date);
        setRentTermSelectInput(indexRentTerm);
        setColorCheckBox(indexColor);
        setCommentInput(comment);
        clickOrderButton();
        clickConfirmOrderButton();
    }
    //метод получает текст заголовка об успешном создании заказа
    public String getConfirmationHeaderText() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(confirmationHeaderText))).isDisplayed();
        return driver.findElement(confirmationHeaderText).getText();
    }
    //метод кликает на логотип Самоката
    public void clickLogoScooterLink() {
        driver.findElement(logoScooterLink).click();
    }
}
