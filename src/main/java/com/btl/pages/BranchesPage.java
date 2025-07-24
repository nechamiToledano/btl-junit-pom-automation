package com.btl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BranchesPage extends BtlBasePage {

    private By title = By.xpath("//*[@id='mainContent']/div[1]/h1");
    private By firstBranch = By.className("SnifName");

    public BranchesPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public void clickFirstBranch() {
        driver.findElement(firstBranch).click();
    }

    public boolean pageContains(String text) {
        return driver.getPageSource().contains(text);
    }
}
