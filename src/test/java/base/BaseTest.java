package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.URL;
import java.time.Duration;
import java.util.Map;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.withBrowserName("Chrome");
        options.setCapability("chromedriverExecutable",
                "/Users/alejandrapacheco/Drivers/chromedriver142/chromedriver");
        options.setCapability("ensureWebviewsHavePages", true);
        options.setCapability("chromeOptions", Map.of("w3c", false));
        options.setCapability("noReset", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



        cambiarAWebView();
        driver.get("https://stage.vitawallet.io/login");
    }

    public void cambiarAWebView() {
        System.out.println("Contextos: " + driver.getContextHandles());

        for (String ctx : driver.getContextHandles()) {
            if (ctx.contains("WEB") || ctx.contains("CHROMIUM")) {
                System.out.println("➡ Cambiando a: " + ctx);
                driver.context(ctx);
                return;
            }
        }

        throw new RuntimeException("No se encontró WebView");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
