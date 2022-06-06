import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.Logs;

public class LoginTests {
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
    public void theInternetLoginTest() {
        var url = "http://the-internet.herokuapp.com/login";

        log.info("Voy a la pagina: " + url);
        driver.get(url);

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var usernameInputLocator = By.id("username");
        var passwordInputLocator = By.id("password");
        var loginButtonLocator = By.className("fa-sign-in");

        var usernameInput = driver.findElement(usernameInputLocator);
        var passwordInput = driver.findElement(passwordInputLocator);
        var loginButton = driver.findElement(loginButtonLocator);

        log.info("Verificando que username  sea visible");
        Assert.assertTrue(usernameInput.isDisplayed());

        log.info("Verificando que password  sea visible");
        Assert.assertTrue(passwordInput.isDisplayed());

        log.info("Verificando que boton login  sea visible");
        Assert.assertTrue(loginButton.isDisplayed());

        log.info("Escribiendo username");
        usernameInput.sendKeys("tomsmith");

        log.info("Escribiendo password");
        passwordInput.sendKeys("SuperSecretPassword!");

        log.info("Haciendo click en login button");
        loginButton.click();

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var cuadradoVerdelocator = By.className("success");
        var logoutlocator = By.className("button");

        var cuadradoVerde = driver.findElement(cuadradoVerdelocator);
        var logoutBoton = driver.findElement(logoutlocator);

        log.info("Verificando que cuadrado verde  sea visible");
        Assert.assertTrue(cuadradoVerde.isDisplayed());

        log.info("Verificando que el boton logout sea visible");
        Assert.assertTrue(logoutBoton.isDisplayed());

        log.info("Haciendo click en login button");
        logoutBoton.click();

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando que username  sea visible");
        usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed());
    }

    @Test
    public void theInternetLoginInvalidTest() {
        var url = "http://the-internet.herokuapp.com/login";

        log.info("Voy a la pagina: " + url);
        driver.get(url);

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var usernameInputLocator = By.id("username");
        var passwordInputLocator = By.id("password");
        var loginButtonLocator = By.className("fa-sign-in");

        var usernameInput = driver.findElement(usernameInputLocator);
        var passwordInput = driver.findElement(passwordInputLocator);
        var loginButton = driver.findElement(loginButtonLocator);

        log.info("Verificando que username  sea visible");
        Assert.assertTrue(usernameInput.isDisplayed());

        log.info("Verificando que password  sea visible");
        Assert.assertTrue(passwordInput.isDisplayed());

        log.info("Verificando que boton login  sea visible");
        Assert.assertTrue(loginButton.isDisplayed());

        log.info("Escribiendo username");
        usernameInput.sendKeys("hola123");

        log.info("Escribiendo password");
        passwordInput.sendKeys("hehehe");

        log.info("Haciendo click en login button");
        loginButton.click();

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        var cuadradoRojolocator = By.className("error");

        var cuadradoRojo = driver.findElement(cuadradoRojolocator);

        log.info("Verificando que cuadrado rojo  sea visible");
        Assert.assertTrue(cuadradoRojo.isDisplayed());

        try {
            log.info("Espero por 2 segundos");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }

        log.info("Verificando que username  sea visible");
        usernameInput = driver.findElement(usernameInputLocator);
        Assert.assertTrue(usernameInput.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        log.debug("Matando el driver");
        driver.quit();
        log.info("");
    }
}
