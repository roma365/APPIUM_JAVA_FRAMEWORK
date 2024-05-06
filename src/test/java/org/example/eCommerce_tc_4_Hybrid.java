package org.example;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.appiumjava.pageObjects.android.CartPage;
import org.appiumjava.pageObjects.android.FormPage;
import org.appiumjava.pageObjects.android.ProductCatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class eCommerce_tc_4_Hybrid extends BaseTest {

    @Test
    public  void  fillForm() throws InterruptedException {
        formPage.setNameField("TODO");
        formPage.setGender("Male");
        formPage.setCountry("Brazil");
        ProductCatalogPage productCatalogPage = formPage.submitForm();
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        //driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART'][0]")).click(); - there is error

        CartPage cartPage = productCatalogPage.goToCartPage();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.
                attributeContains(driver.findElement(
                        By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart" ));


        Assert.assertEquals(cartPage.getActualTotalPrice(), cartPage.getCountedTotalPrice());
        Thread.sleep(2000);
        cartPage.termsPopupCheck();
        cartPage.termsAgreementSelectCheckbox();
        cartPage.proceedButtonClick();
        Thread.sleep(6000);

        //Hybrid - Google page ->
        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts)
        {
            System.out.println("contextName: " + contextName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("perturaba primarch", Keys.ENTER);
        //driver.findElement(By.name("q")).sendKeys("perturaba primarch");
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");
    }
}
