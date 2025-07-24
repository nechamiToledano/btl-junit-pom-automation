package com.btl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UnemploymentCalculatorPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // אלמנטים
    private By stopWorkDateInput = By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_DynDatePicker_PiturimDate_Date");
    private By ageOver28Radio = By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_rdb_age_1");
    private By continueButton = By.className("btnNext");

    // תוצאות
    private By avgDailySalary = By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_AverageDaySal");
    private By dailyUnemploymentAmount = By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_DayBenefit");
    private By monthlyUnemploymentAmount = By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_lbl_MonthBenefit");

    public UnemploymentCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterStopWorkDate(String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stopWorkDateInput)).clear();
        driver.findElement(stopWorkDateInput).sendKeys(date);
    }

    public void selectAgeOver28() {
        wait.until(ExpectedConditions.elementToBeClickable(ageOver28Radio)).click();
    }

    public void clickContinue() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
        Thread.sleep(3000);
    }

    // מילוי 6 שדות שכר עם לולאה - מזהה דינמי לפי ctl02 עד ctl07
    public void enterSalaryAmounts(String[] salaries) {
        for (int i = 0; i < salaries.length; i++) {
            String id = String.format(
                "ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl%02d_Txt_Sallary",
                i + 2); // מתחיל מ-ctl02
            By salaryInput = By.id(id);
            if (salaries[i] != null) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(salaryInput)).clear();
                driver.findElement(salaryInput).sendKeys(salaries[i]);
            }
        }
    }

    public boolean isResultsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(avgDailySalary)).isDisplayed()
                && driver.findElement(dailyUnemploymentAmount).isDisplayed()
                && driver.findElement(monthlyUnemploymentAmount).isDisplayed();
    }
}
