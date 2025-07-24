package com.btl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsuranceCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // אלמנטים עיקריים בדף החישוב
    private By pageTitleLocator = By
            .xpath("//h1[normalize-space(.)='חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד']");
    private By studentOption = By
            .id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_2");
    private By genderMaleOption = By
            .id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0");

    private By birthDateInput = By.id(
            "ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date");

    private By disabilityYesNoRadio = By
            .id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1");

    private By continueButton = By.className("btnNext");
    private By finalStepHeader = By.id("header");
    private By nationalInsuranceAmount = By.xpath("//ul[@class='CalcResult']/li[1]/strong");
    private By healthInsuranceAmount = By.xpath("//ul[@class='CalcResult']/li[2]/strong");
    private By totalAmount = By.xpath("//ul[@class='CalcResult']/li[3]/strong");

    public InsuranceCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitleLocator)).getText();
    }

    public void selectStudentOption() {
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();
    }

    public void selectGenderMale() {
        driver.findElement(genderMaleOption).click();
    }

    public void enterBirthDate(LocalDate birthDate) {
        String formattedDate = birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateInput)).clear();
        driver.findElement(birthDateInput).sendKeys(formattedDate);
    }

    public void selectDisabilityNo() {
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(disabilityYesNoRadio));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", radio);
        wait.until(ExpectedConditions.elementToBeClickable(radio)).click();
    }


    public void clickContinue() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        Thread.sleep(3000); // מחכה 3 שניות

    }

    public boolean isFinalStepDisplayed() {
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(finalStepHeader));
        return header.getText().contains("סיום");
    }

    public String getNationalInsuranceAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nationalInsuranceAmount)).getText().trim();
    }

    public String getHealthInsuranceAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(healthInsuranceAmount)).getText().trim();
    }

    public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmount)).getText().trim();
    }
}
