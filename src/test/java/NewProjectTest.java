import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class NewProjectTest {

    @Test
    public void createNewProject () {

        WebDriver driver = new ChromeDriver();
        driver.get("http://10.7.2.3:43025/");

        WebElement login = waitForElementLocatedBy(driver, By.name("login"));
        login.sendKeys("admin");

        WebElement password = waitForElementLocatedBy(driver, By.name("password"));
        password.sendKeys("password");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        //Thread.sleep(5000);

        WebElement projectsSection = waitForElementLocatedBy(driver, By.xpath("//button[text()='Проекты']"));
        projectsSection.click();

        WebElement createProject = waitForElementLocatedBy(driver, By.xpath("//a[contains(text(),'Создать проект')]"));
        createProject.click();

        WebElement nameOfTheProject = waitForElementLocatedBy(driver, By.xpath("//div/input[@name='name']"));
        nameOfTheProject.sendKeys("Create Project");

        WebElement codeOfTheProject = waitForElementLocatedBy(driver, By.xpath("//div/input[@name='code']"));
        codeOfTheProject.sendKeys("test-t4");

        WebElement managerField = waitForElementLocatedBy(driver,
                By.xpath("//legend/span[text()='Руководители проекта']/../../../div/button"));
        managerField.click();

// без первого listOfTheManagers почему-то не работает(((

        WebElement listOfTheManagers1 = waitForElementLocatedBy(driver, By.xpath("//li"));
        List<WebElement> listOfTheManagers = driver.findElements(By.xpath("//div[@role='presentation']//li"));
        System.out.println(listOfTheManagers.stream().map(WebElement::getText).collect(Collectors.toList()));
        listOfTheManagers.get(36).click();


        WebElement resourcesField = waitForElementLocatedBy(driver,
                By.xpath("//label[text()='Ресурсы']/.."));
        resourcesField.click();

        WebElement listOfTheResources1 = waitForElementLocatedBy(driver, By.xpath("//li"));
        List<WebElement> listOfTheRecources = driver.findElements(By.xpath("//div[@role='presentation']//li"));
        listOfTheRecources.get(1).click();

        WebElement saveButton = waitForElementLocatedBy(driver, By.xpath("//button[@type='submit']"));
        saveButton.click();

        WebElement teamButton = waitForElementLocatedBy(driver, By.xpath("//button[text()='Команда']"));

        Assert.assertTrue(teamButton.isEnabled());

        driver.quit();

    }

    @Test
    public void verifyField () {
        WebDriver driver = new ChromeDriver();
        driver.get("http://10.7.2.3:43025/projects/205/team");

        WebElement login = waitForElementLocatedBy(driver, By.name("login"));
        login.sendKeys("admin");

        WebElement password = waitForElementLocatedBy(driver, By.name("password"));
        password.sendKeys("password");

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        WebElement teamButton = waitForElementLocatedBy(driver, By.xpath("//button[text()='Команда']"));

        Assert.assertTrue(teamButton.isEnabled());

        driver.quit();
    }


    private static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
