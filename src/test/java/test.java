import dev.failsafe.internal.util.Assert;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.mitm.CertificateAndKeySource;
import net.lightbody.bmp.mitm.KeyStoreFileCertificateSource;
import net.lightbody.bmp.mitm.manager.ImpersonatingMitmManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import sample.HttpRequest;
import sample.actionocr;
import sample.pyyzm;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class test {


    public static void main(String[] args) throws IOException, InterruptedException {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Desktop\\java\\jsburp\\jsburp\\out\\artifacts\\jsburp_jar\\Tools\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver(options);


        for (int i = 0; i < 20; i++) {
            driver.get("https://vpn.scgsdsj.com:65443/vpn/theme/auth_home.html");

            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(100L));
            Boolean until = webDriverWait.until((ExpectedCondition<Boolean>) webDriver -> driver.findElement(By.xpath("//*[@id=\"verifycode\"]/td[3]/img[1]")).isDisplayed());

//            Assert.assertTrue(until);
//            driver.findElement(By.xpath("//*[@id=\"verifycode\"]/td[3]/img[1]")).click();

            File screenshotAs = driver.findElement(By.xpath("//*[@id=\"verifycode\"]/td[3]/img[1]")).getScreenshotAs(OutputType.FILE);
            String absolutePath = screenshotAs.getAbsolutePath();
            String canonicalPath = screenshotAs.getCanonicalPath();
//            System.out.println(canonicalPath);

            String print = HttpRequest.uploadFile("http://127.0.0.1:9898//ocr/file", "image", canonicalPath);

            boolean letterDigit = isLetterDigit(print);
            int length = print.length();
//
            System.out.print(print+"---");

            if (length == 4 && letterDigit) {
                System.out.println(i+"---"+print);
            }else {
                System.out.println();
                i--;
            }

        }

        System.out.println(" 嘿嘿！宏哥，你已经成功跳过证书信任步骤啦！");

        driver.quit();
    }

    public static boolean isLetterDigit(String str) {

        String regex = "^[a-z \\d A-Z]+$";

        return str.matches(regex);

    }
}
