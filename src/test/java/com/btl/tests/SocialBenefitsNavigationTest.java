package com.btl.tests;

import com.btl.pages.SocialBenefitsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SocialBenefitsNavigationTest extends BaseTest {

    private SocialBenefitsPage socialBenefitsPage;

    @ParameterizedTest(name = "Navigate to ''{0}'' → expected title ''{1}''")
    @CsvSource({
            "נכות כללית, נכות כללית",
            "שיקום מקצועי, שיקום מקצועי",
            "קצבת ילדים, ילדים",
            "מילואים, מילואים",
            "סיעוד, סיעוד",

    })
    public void testNavigateToBenefitsSubPages(String subMenuText, String expectedTitlePart) {
        socialBenefitsPage = new SocialBenefitsPage(driver);

        socialBenefitsPage.goToBenefitsMenu();
        socialBenefitsPage.clickSubMenu(subMenuText);

        String actualTitle = driver.getTitle();

        Assertions.assertTrue(actualTitle.contains(expectedTitlePart),
                "כותרת הדף לא תואמת עבור '" + subMenuText + "': " + actualTitle);
    }
}