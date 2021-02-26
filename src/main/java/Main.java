import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import static org.junit.Assert.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Users\\ELG\\Documents\\ELG\\Escuela\\Universidad\\Semestre 6\\Gestión de la Calidad del Software II\\Files\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280,720));
        driver.get("https://es.aliexpress.com/");

        closeMessages(driver);
        waitForMainSite(driver);
        submitSearch(driver, "nintendo switch smash edition", "Productos electrónicos");
        waitForSearchOptions(driver);

        String result = getResultsText(driver);
        Assert.assertNotEquals("(0 Resultados)", result.substring(result.lastIndexOf("(")));

        //driver.quit();
    }

    public static void closeMessages(WebDriver driver) {
        driver.findElement(By.className("_24EHh")).click();
        driver.findElement(By.className("btn-close")).click();
    }

    public static void submitSearch(WebDriver driver, String search, String category) {
        driver.findElement(By.id("search-key")).sendKeys(search);
        new Select(driver.findElement(By.id("search-dropdown-box"))).selectByVisibleText(category);
        driver.findElement(By.className("search-button")).click();
    }

    public static void waitForSearchOptions(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("next-breadcrumb")));
    }

    public static void waitForMainSite(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-key")));
    }

    public static String getResultsText(WebDriver driver) {
        List<WebElement> list = driver.findElements(By.className("next-breadcrumb-item"));
        return list.get(2).getText();
    }
}
