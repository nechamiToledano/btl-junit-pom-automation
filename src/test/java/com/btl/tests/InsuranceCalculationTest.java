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

        // 1. כניסה לתפריט דמי ביטוח > דמי ביטוח לאומי
        socialInsurancePage.goToNationalInsurance();

        // 2. בדיקת כותרת דף דמי ביטוח לאומי
        Assertions.assertTrue(socialInsurancePage.getPageTitle().contains("דמי ביטוח לאומי"),
                "לא נמצא דף דמי ביטוח לאומי");

        // 3. כניסה למחשבון
        socialInsurancePage.goToCalculator();

        // 4. בדיקת כותרת מחשבון
        Assertions.assertTrue(calculatorPage.getPageTitle().contains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד"),"לא נמצא דף מחשבון");

        // 5. מילוי הטופס
        calculatorPage.selectStudentOption();
        calculatorPage.selectGenderMale();
        LocalDate birthDate = LocalDate.of(2006, 11, 1);
        calculatorPage.enterBirthDate(birthDate);
        calculatorPage.clickContinue();
        calculatorPage.selectDisabilityNo();
        calculatorPage.clickContinue();

        // 6. בדיקת שלב סיום
        Assertions.assertTrue(calculatorPage.isFinalStepDisplayed(), "שלב סיום לא מוצג");

        // 7. בדיקת סכומי ביטוח
        Assertions.assertEquals("43", calculatorPage.getNationalInsuranceAmount(), "סכום דמי ביטוח לאומי שגוי");
        Assertions.assertEquals("120.00", calculatorPage.getHealthInsuranceAmount(), "סכום דמי ביטוח בריאות שגוי");
        Assertions.assertEquals("163", calculatorPage.getTotalAmount(), "סכום כולל שגוי");
  }
}
