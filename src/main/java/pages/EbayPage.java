package pages;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbayPage {
	private WebDriver driver;
	private By searchIdLocator = By.id("gh-ac");
	private By searchButtonLocator = By.id("gh-btn");
	private By bookList = By.xpath("//div[@id='srp-river-results']/ul/li[1]/div/div[2]/a");
	private By addToCart = By.xpath("//a[@id='atcBtn_btn_1']");
	private By cart = By.xpath("//a/i[@id='gh-cart-n']");

	public EbayPage(WebDriver driver) {
		this.driver = driver;
	}
	public void search(String book) {
		driver.findElement(searchIdLocator).sendKeys(book);
	}
	public void clickSearchButton() {
		driver.findElement(searchButtonLocator).click();
	}
	public void clickBook() {
		driver.findElement(bookList).click();
	}

	public void clickAddToCart() {
		Set<String> handles = driver.getWindowHandles();
		for(String window : handles ) {
			driver.switchTo().window(window);
		}
		driver.findElement(addToCart).click();
	}

	public int verifyCart() {
		int cartValue = Integer.valueOf(driver.findElement(cart).getText());
		return cartValue;
	}

}
