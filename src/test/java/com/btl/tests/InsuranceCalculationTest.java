package com.btl.tests;

import com.btl.pages.InsuranceCalculatorPage;
import com.btl.pages.NationalInsurancePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class InsuranceCalculationTest extends BaseTest {

    private NationalInsurancePage socialInsurancePage;
    private InsuranceCalculatorPage calculatorPage;

    @Test
    public void testCalculateInsuranceForYeshivaStudent() throws InterruptedException {
        socialInsurancePage = new NationalInsurancePage(driver);
        calculatorPage = new InsuranceCalculatorPage(driver);

        socialInsurancePage.goToNationalInsurance();

        Assertions.assertTrue(socialInsurancePage.getPageTitle().contains("דמי ביטוח לאומי"),
                "National Insurance Fees page not found");

        socialInsurancePage.goToCalculator();

        Assertions.assertTrue(
                calculatorPage.getPageTitle()
                        .contains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד"),
                "Calculator page not found");

        calculatorPage.selectStudentOption();
        calculatorPage.selectGenderMale();
        LocalDate birthDate = LocalDate.of(2006, 11, 1);
        calculatorPage.enterBirthDate(birthDate);
        calculatorPage.clickContinue();
        String stepHeaderText = calculatorPage.getStepHeaderText();
        Assertions.assertTrue(stepHeaderText.contains("צעד שני"), "Did not reach step 2");
        calculatorPage.selectDisabilityNo();
        calculatorPage.clickContinue();

        Assertions.assertTrue(calculatorPage.isFinalStepDisplayed(), "Final step is not displayed");

        Assertions.assertEquals("43", calculatorPage.getNationalInsuranceAmount(),
                "Incorrect national insurance amount");
        Assertions.assertEquals("120.00", calculatorPage.getHealthInsuranceAmount(),
                "Incorrect health insurance amount");
        Assertions.assertEquals("163", calculatorPage.getTotalAmount(), "Incorrect total amount");
    }
}
