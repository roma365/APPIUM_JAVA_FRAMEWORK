package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.appiumjava.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AndroidBaseTest {
    public   AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;

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
        formPage = new FormPage(driver);

    }

    @AfterClass
    public  void tearDown(){
        driver.quit();
        service.stop();
    }
}
