package com.btl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UnemploymentCalculatorPage extends BtlBasePage {

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_DynDatePicker_PiturimDate_Date")
    private WebElement stopWorkDateInput;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_rdb_age_1")
    private WebElement ageOver28Radio;

    @FindBy(className = "btnNext")
    private WebElement continueButton;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_AverageDaySal")
    private WebElement avgDailySalary;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_DayBenefit")
    private WebElement dailyUnemploymentAmount;

    @FindBy(id = "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_MonthBenefit")
    private WebElement monthlyUnemploymentAmount;

    public UnemploymentCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void enterStopWorkDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(stopWorkDateInput)).clear();
        stopWorkDateInput.sendKeys(date);
    }

    public void selectAgeOver28() {
        wait.until(ExpectedConditions.elementToBeClickable(ageOver28Radio)).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void enterSalaryAmounts(String[] salaries) {
        for (int i = 0; i < salaries.length; i++) {
            String id = String.format(
                "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl%02d_Txt_Sallary",
                i + 2); 
            WebElement salaryInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
            salaryInput.clear();
            salaryInput.sendKeys(salaries[i]);
        }
    }

    public boolean isResultsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(avgDailySalary)).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOf(dailyUnemploymentAmount)).isDisplayed()
                && wait.until(ExpectedConditions.visibilityOf(monthlyUnemploymentAmount)).isDisplayed();
    }
}
