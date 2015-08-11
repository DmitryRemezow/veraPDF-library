package org.verapdf.validation.profile.parser;

/**
 * @author Maksim Bezrukov
 */

import org.junit.Test;
import org.verapdf.validation.profile.model.Rule;
import org.verapdf.validation.profile.model.ValidationProfile;
import org.verapdf.validation.profile.model.Variable;

import static org.junit.Assert.*;

public class ValidationProfileParserTest {

    @Test
    public void test() throws Exception {
        ValidationProfile prof = ValidationProfileParser.parseValidationProfile("src/test/resources/test.xml", false);

        assertEquals("org.verapdf.model.PDFA1a", prof.getAttrModel());
        assertEquals("PDF/A-1a validation profile", prof.getName());
        assertEquals("STR_ID_101", prof.getDescription());
        assertEquals("User1", prof.getCreator());
        assertEquals("2015-01-23T17:30:15Z", prof.getCreated());
        assertNull(prof.getHash());

        assertEquals(1, prof.getRoolsForObject("PDXObject").size());

        Rule rule1 = prof.getRuleById("rule1");

        assertEquals(rule1, prof.getRoolsForObject("CosDocument").get(0));

        assertEquals("CosDocument", rule1.getAttrObject());
        assertEquals(0, rule1.getFixes().size());
        assertEquals("STR_ID_401", rule1.getDescription());
        assertEquals("fileHeaderOffset == 0", rule1.getTest());
        assertTrue(rule1.isHasError());
        assertEquals("STR_ID_402", rule1.getRuleError().getMessage());
        assertEquals(1, rule1.getRuleError().getArgument().size());
        assertEquals("fileHeaderOffset", rule1.getRuleError().getArgument().get(0));
        assertEquals("ISO19005-1", rule1.getReference().getSpecification());
        assertEquals("6.1.2", rule1.getReference().getClause());

        Rule rule53 = prof.getRuleById("rule53");

        assertEquals("PDMetadata", rule53.getAttrObject());
        assertEquals("STR_ID_608", rule53.getDescription());
        assertEquals("isInfoDictConsistent", rule53.getTest());
        assertFalse(rule53.isHasError());
        assertEquals("STR_ID_609", rule53.getRuleError().getMessage());
        assertEquals(0, rule53.getRuleError().getArgument().size());
        assertEquals("ISO19005-1", rule53.getReference().getSpecification());
        assertEquals("6.7.3", rule53.getReference().getClause());
        assertEquals(1, rule53.getFixes().size());
        assertEquals("STR_ID_893", rule53.getFixes().get(0).getDescription());
        assertEquals("STR_ID_894", rule53.getFixes().get(0).getInfo().getMessage());
        assertEquals("STR_ID_895", rule53.getFixes().get(0).getError().getMessage());

        Rule rule35 = prof.getRuleById("rule35");

        assertEquals("PDXObject", rule35.getAttrObject());
        assertFalse(rule35.isHasError());
        assertNull(rule35.getDescription());
        assertNull(rule35.getTest());
        assertNull(rule35.getRuleError());
        assertNull(rule35.getReference());
        assertEquals(0, rule35.getFixes().size());


    }

    @Test
    public void testCyrillic() throws Exception {
        ValidationProfile prof = ValidationProfileParser.parseValidationProfile("src/test/resources/testCyrillic.xml", false);

        assertEquals("org.verapdf.model.PDFA1a", prof.getAttrModel());
        assertEquals("PDF/A-1a validation profile", prof.getName());
        assertEquals("STR_ID_101", prof.getDescription());
        assertEquals("Какой-то русский человек", prof.getCreator());
        assertEquals("2015-01-23T17:30:15Z", prof.getCreated());
        assertNull(prof.getHash());

        assertNull(prof.getRoolsForObject("PDXObject"));

        Rule rule1 = prof.getRuleById("правило1");

        assertEquals("CosDocument", rule1.getAttrObject());
        assertEquals(0, rule1.getFixes().size());
        assertEquals("STR_ID_401", rule1.getDescription());
        assertEquals("fileHeaderOffset == 0", rule1.getTest());
        assertEquals("STR_ID_402", rule1.getRuleError().getMessage());
        assertEquals(1, rule1.getRuleError().getArgument().size());
        assertEquals("fileHeaderOffset", rule1.getRuleError().getArgument().get(0));
        assertEquals("ISO19005-1", rule1.getReference().getSpecification());
        assertEquals("6.1.2", rule1.getReference().getClause());

        Rule rule53 = prof.getRuleById("rule53");

        assertEquals("PDMetadata", rule53.getAttrObject());
        assertEquals("STR_ID_608", rule53.getDescription());
        assertEquals("isInfoDictConsistent", rule53.getTest());
        assertEquals("STR_ID_609", rule53.getRuleError().getMessage());
        assertEquals(0, rule53.getRuleError().getArgument().size());
        assertEquals("ISO19005-1", rule53.getReference().getSpecification());
        assertEquals("6.7.3", rule53.getReference().getClause());
        assertEquals(1, rule53.getFixes().size());
        assertEquals("STR_ID_893", rule53.getFixes().get(0).getDescription());
        assertEquals("STR_ID_894", rule53.getFixes().get(0).getInfo().getMessage());
        assertEquals("STR_ID_895", rule53.getFixes().get(0).getError().getMessage());

        Variable var1 = prof.getVariablesForObject("Object").get(0);

        assertEquals("Object", var1.getAttrObject());
        assertEquals("varName1", var1.getAttrName());
        assertEquals("null", var1.getDefaultValue());
        assertEquals("5", var1.getValue());

    }
}
