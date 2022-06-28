import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

public class RemoveElementsTests {
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
    public void theInternetAddRemoveElementsTest() throws InterruptedException {
        var url = "http://the-internet.herokuapp.com/add_remove_elements/";

        log.info("Voy a la pagina: " + url);
        driver.get(url);

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var addElementBotonLocator = By.tagName("button");
        var addElementBoton = driver.findElement(addElementBotonLocator);

        log.info("Haciendo click en boton add Element");
        for (int i = 0; i < 10; i++) {
            addElementBoton.click();
        }

        var deleteBotonLocator=By.className("added-manually");
        var deleteBotonLista =  driver.findElements(deleteBotonLocator);

        log.info("Haciendo click en cada boton delete");
        for (var elemento:deleteBotonLista) {
            elemento.click();
        }
    }

    @AfterMethod
    public void tearDown() {
        log.debug("Matando el driver");
        driver.quit();
        log.info("");
    }
}
