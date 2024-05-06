package org.appiumjava.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumUtils{
    IOSDriver driver;
    public IOSActions(IOSDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
