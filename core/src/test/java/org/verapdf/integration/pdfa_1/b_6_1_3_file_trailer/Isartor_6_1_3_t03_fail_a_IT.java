package org.verapdf.integration.pdfa_1.b_6_1_3_file_trailer;

import org.junit.Assert;
import org.junit.Test;
import org.verapdf.integration.base.BasePDFAIT;

/**
 * File trailer - data after last EOF
 */
public class Isartor_6_1_3_t03_fail_a_IT extends BasePDFAIT {

    @Test
    public void testValidation() throws Exception {
        Assert.assertTrue(testValidationSuccessful());
    }

    @Override
    protected String getExpectedReportFilePath() throws Exception {
        return getSystemIndependentPath(ISARTOR_EXPECTED_REPORTS_REPO_NAME + "pdfa_1/b_6_1_3_file_trailer/6-1-3-t03-fail-a-report.xml");
    }

    @Override
    protected String getValidationProfileFilePath() throws Exception {
        return getSystemIndependentPath(VALIDATION_PROFILES_REPO_NAME + "PDF_A/1b/6.1 File structure/6.1.3 File trailer/verapdf-profile-6-1-3-t03.xml");
    }

    @Override
    protected String getPdfFilePath() throws Exception {
        return getSystemIndependentPath(ISARTOR_TEST_FILES_REPO_NAME + "PDFA-1b/6.1 File structure/6.1.3 File trailer/isartor-6-1-3-t03-fail-a.pdf");
    }
}
