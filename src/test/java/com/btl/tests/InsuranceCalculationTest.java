package com.btl.tests;

import com.btl.pages.InsuranceCalculatorPage;
import com.btl.pages.SocialInsurancePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class InsuranceCalculationTest extends BaseTest {

    private SocialInsurancePage socialInsurancePage;
    private InsuranceCalculatorPage calculatorPage;

    @Test
    public void testCalculateInsuranceForYeshivaStudent() throws InterruptedException {
        socialInsurancePage = new SocialInsurancePage(driver);
        calculatorPage = new InsuranceCalculatorPage(driver);

        // 1. Navigate to "Insurance Fees" > "National Insurance Fees"
        socialInsurancePage.goToNationalInsurance();

        // 2. Verify page title of National Insurance Fees page
        Assertions.assertTrue(socialInsurancePage.getPageTitle().contains("דמי ביטוח לאומי"),
                "National Insurance Fees page not found");

        // 3. Enter the calculator page
        socialInsurancePage.goToCalculator();

        // 4. Verify calculator page title
        Assertions.assertTrue(calculatorPage.getPageTitle().contains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד"),
                "Calculator page not found");

        // 5. Fill out the form
        calculatorPage.selectStudentOption();
        calculatorPage.selectGenderMale();
        LocalDate birthDate = LocalDate.of(2006, 11, 1);
        calculatorPage.enterBirthDate(birthDate);
        calculatorPage.clickContinue();
        calculatorPage.selectDisabilityNo();
        calculatorPage.clickContinue();

        // 6. Verify final step is displayed
        Assertions.assertTrue(calculatorPage.isFinalStepDisplayed(), "Final step is not displayed");

        // 7. Verify insurance amounts
        Assertions.assertEquals("43", calculatorPage.getNationalInsuranceAmount(), "Incorrect national insurance amount");
        Assertions.assertEquals("120.00", calculatorPage.getHealthInsuranceAmount(), "Incorrect health insurance amount");
        Assertions.assertEquals("163", calculatorPage.getTotalAmount(), "Incorrect total amount");
    }
}
