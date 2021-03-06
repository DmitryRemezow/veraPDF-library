package org.verapdf.integration.pdfa_1.b_6_1_6_string_objects;

import org.junit.Assert;
import org.junit.Test;
import org.verapdf.integration.base.BasePDFAIT;

/**
 * String objects
 */
public class VeraPDF_6_1_6_t01_pass_a_2_IT extends BasePDFAIT {

    @Test
    public void testValidation() throws Exception {
        Assert.assertTrue(testValidationSuccessful());
    }

    @Override
    protected String getExpectedReportFilePath() throws Exception {
        return getSystemIndependentPath(VERAPDF_EXPECTED_REPORTS_REPO_NAME + "pdfa_1/b_6_1_6_string_objects/6-1-6-t01-pass-a-2-report.xml");
    }

    @Override
    protected String getValidationProfileFilePath() throws Exception {
        return getSystemIndependentPath(VALIDATION_PROFILES_REPO_NAME + "PDF_A/1b/6.1 File structure/6.1.6 String objects/verapdf-profile-6-1-6-t02.xml");
    }

    @Override
    protected String getPdfFilePath() throws Exception {
        return getSystemIndependentPath(VERA_PDF_TEST_FILES_REPO_NAME + "PDF_A-1b/6.1 File structure/6.1.6 String objects/veraPDF test suite 6-1-6-t01-pass-a.pdf");
    }
}
