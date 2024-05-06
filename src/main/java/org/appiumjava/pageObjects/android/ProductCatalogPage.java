package org.appiumjava.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appiumjava.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AndroidActions {
    AndroidDriver driver;
    public ProductCatalogPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartButtons;
    //driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public   WebElement cartButton;
    //driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
    public void addItemToCartByIndex(int index) {
        addToCartButtons.get(index).click();
    }
    public CartPage goToCartPage() throws InterruptedException {
        cartButton.click();
        Thread.sleep(2000);
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }
}
