package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.appiumjava.pageObjects.android.CartPage;
import org.appiumjava.pageObjects.android.ProductCatalogPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {

    @BeforeMethod
    public void preSetUp() throws Exception {
        //Navigate directly to the particular app page
        //Terminal command to define the app package and activity: adb shell dumpsys window | find "mCurrentFocus"
        //[packageName]/activityName
        //io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies

        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity}");
        ((JavascriptExecutor)driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity}"));

    }
    @Test
    public void test1_() throws InterruptedException {
        formPage.setGender("Female");
        formPage.setCountry("Angola");
        ProductCatalogPage productCatalogPage = formPage.submitForm();
        String toastMassage = driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
        Assert.assertEquals(toastMassage, "Please enter your name");
    }
    @Test
    public  void  test2_fillForm() throws InterruptedException {
        formPage.setNameField("TODO");
        formPage.setGender("Male");
        formPage.setCountry("Brazil");
        ProductCatalogPage productCatalogPage = formPage.submitForm();
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        //driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART'][0]")).click(); - there is error

        CartPage cartPage = productCatalogPage.goToCartPage();
        WebElement pageTitle = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        cartPage.waitForElementToAppear(pageTitle, "text", "Cart");
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
