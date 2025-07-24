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

        // 1. Navigate to "Benefits and Allowances" > "Unemployment"
        socialBenefitsPage.goToBenefitsMenu();
        socialBenefitsPage.goToUnemployment();
        socialBenefitsPage.goToUnemploymentCalculators();
        socialBenefitsPage.goToUnemploymentCalculation();

        // 2. Fill out the calculation form
        unemploymentPage.enterStopWorkDate("01/05/2025");
        unemploymentPage.selectAgeOver28();
        unemploymentPage.clickContinue();

        // 3. Enter salary amounts for the last few months (example)
        String[] salaries = { "5000", "5200", "5100", "5300", "5400", "5500" };
        unemploymentPage.enterSalaryAmounts(salaries);
        unemploymentPage.clickContinue();

        // 4. Verify that the results page is displayed
        Assertions.assertTrue(unemploymentPage.isResultsDisplayed(), "Results page is not displayed");
    }
}
