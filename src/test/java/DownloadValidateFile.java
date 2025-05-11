import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class DownloadValidateFile {
    private static String fileDownloadPath = System.getProperty("user.dir") + "\\src\\test\\java\\file";
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", fileDownloadPath);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void downloadValidateFile() throws InterruptedException {
        driver.get("http://omayo.blogspot.com/p/page7.html");
        driver.findElement(By.linkText("ZIP file")).click();

        Thread.sleep(5000);

        String filePathUpdated = fileDownloadPath + "\\DownloadDemo-master.zip";

        File file = new File(filePathUpdated);

        boolean condition = file.exists();
        if (condition) {
            System.out.println("File exist");
        } else {
            System.out.println("File doesn't exist");
        }

        if (file.exists()) {

            file.delete();

        }
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}

