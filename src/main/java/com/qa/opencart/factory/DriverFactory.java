package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exceptions.FrameWorkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    WebDriver driver;
    Properties prop;
    OptionsManager opetionManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public static Logger log = Logger.getLogger(DriverFactory.class);

    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        log.info("browser name is : " + browserName);

        opetionManager = new OptionsManager(prop);
        if (browserName.equalsIgnoreCase("chrome"))
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            // remote execution:
            init_remoteDriver(browserName);
        } else {
            // local execution
            initializeLocalDriver(browserName);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    private void initializeLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(opetionManager.getChromeOption()));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver(opetionManager.getFirefoxOption()));
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        } else {
            System.out.println("Please pass the right browser: " + browserName);
        }
    }

    private void init_remoteDriver(String browserName) {
        System.out.println("Running tests on remote grid server: " + browserName);
        try {
            tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),
                    getRemoteCapabilities(browserName)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private DesiredCapabilities getRemoteCapabilities(String browserName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (browserName.equalsIgnoreCase("chrome")) {
            capabilities.setBrowserName("chrome");
            // Add more capabilities as needed
        } else if (browserName.equalsIgnoreCase("firefox")) {
            capabilities.setBrowserName("firefox");
            // Add more capabilities as needed
        } else if (browserName.equalsIgnoreCase("edge")) {
            capabilities.setBrowserName("MicrosoftEdge");
            // Add more capabilities as needed
        } else {
            System.out.println("Please pass the right browser: " + browserName);
        }
        return capabilities;
    }

    public synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties init_prop() {
        FileInputStream ip = null;
        prop = new Properties();
        String envName = System.getProperty("env");
        System.out.println("Running test on environment: " + envName);
        log.info("Running test on environment: " + envName);

        if (envName == null) {
            System.out.println("No env is given, hence running it on QA env");
            log.info("No env is given, hence running it on QA env");
            try {
                ip = new FileInputStream("./src/test/resources/Config/qa.config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/Config/qa.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/Config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/Config/stage.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/Config/uat.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/Config/config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right environment values: " + envName);
                        log.error("Please pass the right environment values: " + envName);
                        throw new FrameWorkException("No env found");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (FrameWorkException e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getScreenshot() {
        File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = "./screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(scrFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public int getTestCount() {
        return 100;
    }
}
