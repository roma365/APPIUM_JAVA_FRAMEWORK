package org.appiumjava.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appiumjava.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;
    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productPrice;
//    List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;
//    String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement termsButton;
//    WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
    @AndroidFindBy(className="android.widget.CheckBox")
    private WebElement termsAgreementCheckBox;
//    driver.findElement(By.className("android.widget.CheckBox")).click();
    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceedButton;
//      driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

    public Double getCountedTotalPrice() {
        int counter = productPrice.size();;
        double sum = 0;
        for (int i = 0; i < counter; i++) {
            String amount = productPrice.get(i).getText();
            double price = getFormattedAmount(amount);
            sum=sum+price;
        }
        return sum;
    }
    public  Double getActualTotalPrice() {
        Double actualTotalPrice = getFormattedAmount(totalPrice.getText());
        return actualTotalPrice;
    }
    //    Double total = getFormattedAmount(totalPrice);
    public void termsPopupCheck(){
        longPressAction(termsButton);
        String popupTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(popupTitle, "Terms Of Conditions");
        driver.findElement(By.id("android:id/button1")).click();
    }
    public void termsAgreementSelectCheckbox(){
        termsAgreementCheckBox.click();
    }
    public  void proceedButtonClick(){
        proceedButton.click();
    }




}
