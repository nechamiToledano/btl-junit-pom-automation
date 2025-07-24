package com.btl.tests;

import com.btl.pages.SocialBenefitsPage;
import com.btl.pages.UnemploymentCalculatorPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnemploymentCalculationTest extends BaseTest {

    private SocialBenefitsPage socialBenefitsPage;
    private UnemploymentCalculatorPage unemploymentPage;

    @Test
    public void testCalculateUnemploymentBenefits() throws InterruptedException {
        socialBenefitsPage = new SocialBenefitsPage(driver);
        unemploymentPage = new UnemploymentCalculatorPage(driver);

        // 1. כניסה לתפריט קצבאות והטבות > אבטלה
        socialBenefitsPage.goToBenefitsMenu();
        socialBenefitsPage.goToUnemployment();
        socialBenefitsPage.goToUnemploymentCalculators();
        socialBenefitsPage.goToUnemploymentCalculation();
        // 2. מילוי טופס החישוב
        unemploymentPage.enterStopWorkDate("01/05/2025");
        unemploymentPage.selectAgeOver28();
        unemploymentPage.clickContinue();

        // 3. מילוי סכומי שכר בחודשים האחרונים (לדוגמה)
        String[] salaries = { "5000", "5200", "5100", "5300", "5400", "5500" };
        unemploymentPage.enterSalaryAmounts(salaries);
        unemploymentPage.clickContinue();

        // 4. בדיקה שהתוצאות מופיעות
        Assertions.assertTrue(unemploymentPage.isResultsDisplayed(), "דף תוצאות החישוב לא מוצג");
    }
}
