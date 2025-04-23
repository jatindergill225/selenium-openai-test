import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
public class OpenAISearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        try {
           
            driver.get("https://www.google.com");

            
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement acceptBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Accept all')]"))
                );
                acceptBtn.click();
            } catch (Exception ignored) {}

           
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("OpenAI");
            searchBox.submit();

        
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

            List<WebElement> results = driver.findElements(By.cssSelector("div#search .g"));
            for (int i = 0; i < 3 && i < results.size(); i++) {
                String resultText = results.get(i).getText().toLowerCase();
                if (resultText.contains("openai")) {
                    System.out.println(" Result " + (i + 1) + " contains 'OpenAI'");
                } else {
                    System.out.println(" Result " + (i + 1) + " does NOT contain 'OpenAI'");
                }
            }

        } finally {
          
            driver.quit();
	}

}
}
