package com.btl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.btl.utils.TopMenuEnum;

public class NationalInsurancePage extends BtlBasePage {

    @FindBy(xpath = "//a[strong[contains(text(), 'מחשבון לחישוב דמי הביטוח')]]")
    private WebElement calculatorLink;

    public NationalInsurancePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToNationalInsurance() {
        clickMainMenu(TopMenuEnum.INSURANCE_FEES);
        clickSubMenu("דמי ביטוח לאומי");
    }

    public void goToCalculator() {
        wait.until(ExpectedConditions.elementToBeClickable(calculatorLink)).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}