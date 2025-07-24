package com.btl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.btl.utils.TopMenuEnum;

public class BtlBasePage extends BasePage {

    public BtlBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "TopQuestions")
    private WebElement searchInput;

    @FindBy(id = "ctl00_SiteHeader_reserve_btnSearch")
    private WebElement searchButton;

    @FindBy(id = "ctl00_Topmneu_BranchesHyperLink")
    private WebElement branchesButton;

    public void performSearch(String term) {
        wait.until(ExpectedConditions.visibilityOf(searchInput)).clear();
        searchInput.sendKeys(term);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickMainMenu(TopMenuEnum item) {
        driver.findElement(By.linkText(item.getDisplayText())).click();
    }

    public void clickSubMenu(String subMenuText) {
        driver.findElement(By.xpath("//div//ul//li//a[contains(text(),'" + subMenuText + "')]")).click();
    }

    public BranchesPage navigateToBranchesPage() {
        wait.until(ExpectedConditions.elementToBeClickable(branchesButton)).click();
        return new BranchesPage(driver);
    }
}
