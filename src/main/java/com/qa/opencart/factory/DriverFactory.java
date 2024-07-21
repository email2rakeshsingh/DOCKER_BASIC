package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private WebDriver driver;
    private Properties prop;
    private OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * Initializes the WebDriver based on the provided properties.
     * 
     * @param prop Properties object containing configuration
     * @return Initialized WebDriver
     */
    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is: " + browserName);

        optionsManager = new OptionsManager(prop);

        if (browserName.equalsIgnoreCase("chrome")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                initRemoteDriver("chrome");
            } else {
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            }

        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                initRemoteDriver("firefox");
            } else {
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
            }

        } else if (browserName.equalsIgnoreCase("edge")) {
            if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                initRemoteDriver("edge");
            } else {
                WebDriverManager.edgedriver().setup();
                //tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
            }

        } else {
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        WebDriver driver = getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    private void initRemoteDriver(String browserName) {
        System.out.println("Running tests on remote web server: " + browserName);
        try {
            URL hubUrl = new URL(prop.getProperty("huburl"));
            if (browserName.equalsIgnoreCase("chrome")) {
                tlDriver.set(new RemoteWebDriver(hubUrl, optionsManager.getChromeOptions()));
            } else if (browserName.equalsIgnoreCase("firefox")) {
                tlDriver.set(new RemoteWebDriver(hubUrl, optionsManager.getFirefoxOptions()));
            } else if (browserName.equalsIgnoreCase("edge")) {
                //tlDriver.set(new RemoteWebDriver(hubUrl, optionsManager.getEdgeOptions()));
            } else {
                throw new IllegalArgumentException("Remote browser not supported: " + browserName);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Initializes properties from the respective environment file.
     * 
     * @return Properties object with all config properties
     */
    public Properties initProperties() {
        FileInputStream ip = null;
        prop = new Properties();

        String envName = System.getProperty("env");
        System.out.println("Running tests on environment: " + envName);

        try {
            if (envName == null) {
                ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
            } else {
                switch (envName.toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/config/config.properties");
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid environment value: " + envName);
                }
            }
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    /**
     * Takes a screenshot and saves it to the specified path.
     * 
     * @return Path to the screenshot
     */
    public String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = "./screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
