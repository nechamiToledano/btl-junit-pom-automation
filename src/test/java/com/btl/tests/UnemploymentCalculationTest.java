package com.btl.tests;

import com.btl.pages.SocialBenefitsPage;
import com.btl.pages.UnemploymentCalculatorPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnemploymentCalculationTest extends BaseTest {

    private SocialBenefitsPage socialBenefitsPage;
    private UnemploymentCalculatorPage unemploymentPage;

    @Test
    public void testCalculateUnemploymentBenefits()  {
        socialBenefitsPage = new SocialBenefitsPage(driver);
        unemploymentPage = new UnemploymentCalculatorPage(driver);

        socialBenefitsPage.goToBenefitsMenu();
        socialBenefitsPage.goToUnemployment();
        socialBenefitsPage.goToUnemploymentCalculators();
        socialBenefitsPage.goToUnemploymentCalculation();

        unemploymentPage.enterStopWorkDate("01/05/2025");
        unemploymentPage.selectAgeOver28();
        unemploymentPage.clickContinue();

        String[] salaries = { "10000", "10100", "10200", "10300", "10400", "10500" };
        unemploymentPage.enterSalaryAmounts(salaries);
        unemploymentPage.clickContinue();

        Assertions.assertTrue(unemploymentPage.isResultsDisplayed(), "Results page is not displayed");
    }
}
