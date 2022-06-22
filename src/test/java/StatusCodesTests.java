import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

public class StatusCodesTests {
    private WebDriver driver;
    private final Logs log = new Logs();

    @BeforeMethod
    public void setUp() {
        log.debug("Inicializando el driver de chrome");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        log.debug("Maximizando la ventana");
        driver.manage().window().maximize();

        log.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();
    }

    @Test
    public void theInternetUrlValidateTest() {
        var url = "http://the-internet.herokuapp.com/status_codes";

        log.info("Voy a la pagina: " + url);
        driver.get(url);

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var statusCodeLocator = By.linkText("404");
        var hereLocator = By.linkText("here");

        var statusCodeURL = driver.findElement(statusCodeLocator);
        var hereURL = driver.findElement(hereLocator);

        log.info("Haciendo click en 404");
        statusCodeURL.click();

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        hereURL = driver.findElement(hereLocator);
        log.info("Haciendo click en here");
        hereURL.click();

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando que la url actual sea la misma que la inicial");
        Assert.assertEquals(driver.getCurrentUrl(),url);
    }

    @AfterMethod
    public void tearDown() {
        log.debug("Matando el driver");
        driver.quit();
        log.info("");
    }
}
