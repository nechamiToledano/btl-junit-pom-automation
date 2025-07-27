package com.btl.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsuranceCalculatorPage extends BtlBasePage {

    @FindBy(xpath = "//h1[normalize-space(.)='חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד']")
    private WebElement pageTitle;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2")
    private WebElement studentOption;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0")
    private WebElement genderMaleOption;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date")
    private WebElement birthDateInput;

    @FindBy(id = "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1")
    private WebElement disabilityNoRadio;

    @FindBy(className = "btnNext")
    private WebElement continueButton;

    @FindBy(id = "header")
    private WebElement finalStepHeader;

    @FindBy(xpath = "//ul[@class='CalcResult']/li[1]/strong")
    private WebElement nationalInsuranceAmount;

    @FindBy(xpath = "//ul[@class='CalcResult']/li[2]/strong")
    private WebElement healthInsuranceAmount;

    @FindBy(xpath = "//ul[@class='CalcResult']/li[3]/strong")
    private WebElement totalAmount;

    @FindBy(id = "header")
    private WebElement stepHeader;

    public InsuranceCalculatorPage(WebDriver driver) {
        super(driver);
    }


    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle)).getText();
    }

    public void selectStudentOption() {
        wait.until(ExpectedConditions.visibilityOf(studentOption));
        scrollToElement(studentOption);
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();
    }

    public void selectGenderMale() {
        scrollToElement(genderMaleOption);
        wait.until(ExpectedConditions.elementToBeClickable(genderMaleOption)).click();
    }

    public void enterBirthDate(LocalDate birthDate) {
        String formattedDate = birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        wait.until(ExpectedConditions.visibilityOf(birthDateInput));
        birthDateInput.clear();
        birthDateInput.sendKeys(formattedDate);
    }

        public String getStepHeaderText() {
        wait.until(ExpectedConditions.visibilityOf(stepHeader));
        return stepHeader.getText();
    }
    
    public void selectDisabilityNo() {
        wait.until(ExpectedConditions.visibilityOf(disabilityNoRadio));
        scrollToElement(disabilityNoRadio);
        wait.until(ExpectedConditions.elementToBeClickable(disabilityNoRadio)).click();
    }

    public void clickContinue() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        scrollToElement(continueButton);
        continueButton.click();
        Thread.sleep(3000);
    }

    public boolean isFinalStepDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(finalStepHeader)).getText().contains("סיום");
    }

    public String getNationalInsuranceAmount() {
        return wait.until(ExpectedConditions.visibilityOf(nationalInsuranceAmount)).getText().trim();
    }

    public String getHealthInsuranceAmount() {
        return wait.until(ExpectedConditions.visibilityOf(healthInsuranceAmount)).getText().trim();
    }

    public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOf(totalAmount)).getText().trim();
    }


    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
