package com.btl.tests;

import com.btl.pages.BtlBasePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class SearchTest extends BaseTest {

    @Test
    public void testSearchResults() {
        BtlBasePage basePage = new BtlBasePage(driver);
        String query = "חישוב סכום דמי לידה ליום";
        basePage.performSearch(query);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement title = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='results']/h2")));

        assertEquals("תוצאות חיפוש עבור " + query, title.getText().trim());
    }
}
