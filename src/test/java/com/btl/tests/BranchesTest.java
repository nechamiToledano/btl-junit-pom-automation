package com.btl.tests;

import com.btl.pages.BtlBasePage;
import com.btl.pages.BranchesPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BranchesTest extends BaseTest {

    @Test
    public void testBranchesInfo() throws InterruptedException {
        BtlBasePage basePage = new BtlBasePage(driver);
        BranchesPage branchesPage = basePage.navigateToBranchesPage();
        Thread.sleep(3000);

        assertEquals("סניפים וערוצי שירות", branchesPage.getTitleText());

        branchesPage.clickFirstBranch();

        assertTrue(branchesPage.pageContains("כתובת"), "כתובת לא הופיעה");
        assertTrue(branchesPage.pageContains("קבלת קהל"), "קבלת קהל לא הופיעה");
        assertTrue(branchesPage.pageContains("מענה טלפוני"), "מענה טלפוני לא הופיע");
    }
}
