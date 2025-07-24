package com.btl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.btl.utils.TopMenuEnum;

public class SocialBenefitsPage extends BtlBasePage {

    @FindBy(linkText = "אבטלה")
    private WebElement unemploymentLink;

    @FindBy(xpath = "//a[strong[contains(text(),'למחשבוני דמי אבטלה')]]")
    private WebElement unemploymentCalculatorsLink;

    @FindBy(xpath = "//a[contains(text(),'חישוב דמי אבטלה')]")
    private WebElement unemploymentCalculationLink;

    public SocialBenefitsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToBenefitsMenu() {
        clickMainMenu(TopMenuEnum.BENEFITS_ALLOWANCES);
    }

    public void goToUnemployment() {
        wait.until(ExpectedConditions.elementToBeClickable(unemploymentLink)).click();
    }

    public void goToUnemploymentCalculators() {
        wait.until(ExpectedConditions.elementToBeClickable(unemploymentCalculatorsLink)).click();
    }

    public void goToUnemploymentCalculation() {
        wait.until(ExpectedConditions.elementToBeClickable(unemploymentCalculationLink)).click();
    }
}