package org.appiumjava.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appiumjava.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public  FormPage(AndroidDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
   //driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vulkan");
    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
    private WebElement femaleOption;
    @AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
    private WebElement maleOption;
    //driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
    @AndroidFindBy(className="android.widget.Spinner")
    private WebElement countryDroplist;
    //        driver.findElement(By.className("android.widget.Spinner")).click();
    public static final String droplistLocation ="//android.widget.TextView";
    @AndroidFindBy(xpath=droplistLocation)
    private WebElement droplistItem;
    //      driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();;
    @AndroidFindBy(className="android.widget.Button")
    private WebElement submitButton;
    //        driver.findElement(By.className("android.widget.Button")).click();

    public void  setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }
    public void  setGender(String gender){
        if(gender.equals("Male")){
            maleOption.click();
        }else {
            femaleOption.click();
        }
    }
    public void  setCountry(String country){
        countryDroplist.click();
        scrollToText(country);
        driver.findElement(By.xpath(droplistLocation+"[@text='"+country+"']")).click();
        //driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();

    }
    public ProductCatalogPage submitForm() throws InterruptedException {
        submitButton.click();
//        Thread.sleep(2000);
        ProductCatalogPage productCatalogPage = new ProductCatalogPage(driver);
        return productCatalogPage;
    }




}

