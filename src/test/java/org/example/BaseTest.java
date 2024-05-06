package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    public   AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public  void configureAppium() throws MalformedURLException {
         service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Admin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 6 Pro API 33");
        options.setChromedriverExecutable("D:\\Program\\Work\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe");
        //options.setApp("D:\\Desktop\\MyGithub\\APPIUM_JAVA_\\Appium_Java\\src\\resources\\ApiDemos-debug.apk");
        options.setApp("D:\\Desktop\\MyGithub\\APPIUM_JAVA_\\Appium_Java\\src\\resources\\General-Store.apk");
        //options.setApp("D:\\Desktop\\MyGithub\\APPIUM_JAVA_\\Appium_Java\\src\\resources\\realFan.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement element){
        //WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]\n"));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId(),
                        "duration", 2000));
    }

    public  void  scrollToEnd(){
        //NO PRIOR IDEA
        boolean canScrollMore;
        do{
            canScrollMore = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("mobile: scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percent", 1.0
                    ));
        }while(canScrollMore);
    }

    public  void  swipeAction(WebElement element, String duration){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", duration,
                "percent", 0.75
        ));
    }

    public  void  dragAndDropAction(WebElement source, int endX, int endY){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) source).getId(),
                "endX", endX,
                "endY", endY
        ));
    }

    public Double getFormattedAmount(String amount){
       Double price = Double.parseDouble(amount.substring(1));
       return price;
    }


    @AfterClass
    public  void tearDown(){
        driver.quit();
        service.stop();
    }
}
