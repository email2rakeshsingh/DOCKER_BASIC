package com.qa.opencart.factory;

import java.util.Properties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
// Uncomment and use if needed
// import org.openqa.selenium.ie.InternetExplorerOptions; 

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    // private InternetExplorerOptions io; // Uncomment if you need IE options

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();

        // Set browser version if specified
        if (prop.getProperty("browserversion") != null) {
            co.setBrowserVersion(prop.getProperty("browserversion"));
        }

        // Set other options
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.setHeadless(true);
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }

        // If remote is true, configure remote options accordingly (excluding deprecated capabilities)
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            // No direct support for VNC in W3C WebDriver standard; set other remote capabilities if needed
        }

        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();

        // Set browser version if specified
        if (prop.getProperty("browserversion") != null) {
            fo.setBrowserVersion(prop.getProperty("browserversion"));
        }

        // Set other options
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.setHeadless(true);
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }

        // If remote is true, configure remote options accordingly (excluding deprecated capabilities)
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            // Set other remote capabilities if needed
        }

        return fo;
    }

    // Uncomment and use if you need Internet Explorer options
    // public InternetExplorerOptions getInternetOptions() {
    //     io = new InternetExplorerOptions();
    //     if (Boolean.parseBoolean(prop.getProperty("headless"))) {
    //         io.setHeadless(true); // Internet Explorer does not support headless mode
    //     }
    //     if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
    //         io.addArguments("--incognito"); // IE does not support incognito mode through WebDriver
    //     }
    //     return io;
    // }
}
