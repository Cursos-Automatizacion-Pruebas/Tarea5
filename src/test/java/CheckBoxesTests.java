import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;
public class CheckBoxesTests {

    // usar for https://www.guru99.com/checkbox-and-radio-button-webdriver.html
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
    public void theInternetCheckboxesTest() {
        var url = "http://the-internet.herokuapp.com/checkboxes";

        log.info("Voy a la pagina: " + url);
        driver.get(url);

        try {
            log.debug("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var checkboxesLocator = By.tagName("input");
        var checkboxImput = driver.findElements(checkboxesLocator);
        var checkbox1=checkboxImput.get(0);
        var checkbox2=checkboxImput.get(1);

        log.debug("Verificando que checkbox 1 este desmarcado");
        Assert.assertFalse(checkbox1.isSelected());

        log.debug("Verificando que checkbox 2 este marcado ");
        Assert.assertTrue(checkbox2.isSelected());

        log.info("Haciendo click en checkbox 1");
        checkbox1.click();

        log.info("Haciendo click en checkbox 2");
        checkbox2.click();

        log.info("Verificando que checkbox 1 este marcado ");
        Assert.assertTrue(checkbox1.isSelected());

        log.info("Verificando que checkbox 2 este  desmarcado");
        Assert.assertFalse(checkbox2.isSelected());
    }

    @AfterMethod
    public void tearDown() {
        log.debug("Matando el driver");
        driver.quit();
        log.info("");
    }
}
