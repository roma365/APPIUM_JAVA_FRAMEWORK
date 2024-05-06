package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class eCommerce_tc_4_Hybrid extends BaseTest {

    @Test
    public  void  fillForm() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vulkan");
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.className("android.widget.Spinner")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.className("android.widget.Button")).click();

        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        //driver.findElement(By.xpath("//android.widget.TextView[@text='ADD TO CART'][0]")).click(); - there is error

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.
                attributeContains(driver.findElement(
                        By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart" ));
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

        int counter = productPrices.size();
        double sum = 0;
        for (int i = 0; i < counter; i++) {
            String amount = productPrices.get(i).getText();
            double price = getFormattedAmount(amount);
            sum=sum+price;
        }
        String totalPrice = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double total = getFormattedAmount(totalPrice);
        Assert.assertEquals(total, sum);
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longPressAction(element);
        String popupTitle = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(popupTitle, "Terms Of Conditions");
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
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
