package org.verapdf.validation.profile.model;

import java.util.Collections;
import java.util.List;

/**
 * Structure of the rule in a validation profile.
 *
 * @author Maksim Bezrukov
 */
public class Rule {
    private final String attrID;
    private final String attrObject;
    private final String description;
    private final String test;
    private final RuleError ruleError;
    private final boolean isHasError;
    private final Reference reference;
    private final List<Fix> fixes;

    /**
     * Creates new rule model.
     *
     * @param attrID      - id of the rule
     * @param attrObject  - name of the object to which this rule applied
     * @param description - description of the rule
     * @param ruleError   - rule error
     * @param isHasError  - is the rule error of type error or warning
     * @param test        - test of the rule as JavaScript context
     * @param reference   - reference of the rule
     * @param fix         - list of fixes for this rule
     */
    public Rule(String attrID, String attrObject, String description, RuleError ruleError, boolean isHasError, String test, Reference reference, List<Fix> fix) {
        this.attrID = attrID;
        this.attrObject = attrObject;
        this.description = description;
        this.ruleError = ruleError;
        this.isHasError = isHasError;
        this.test = test;
        this.reference = reference;
        this.fixes = Collections.unmodifiableList(fix);
    }

    /**
     * @return Text provided by attribute "id".
     */
    public String getAttrID() {
        return attrID;
    }

    /**
     * @return Text provided by attribute "object".
     */
    public String getAttrObject() {
        return attrObject;
    }

    /**
     * @return Text in tag "description".
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Text in tag "test".
     */
    public String getTest() {
        return test;
    }

    /**
     * @return Class which represents an error/warning in this rule.
     */
    public RuleError getRuleError() {
        return ruleError;
    }

    /**
     * Get what {@code ruleError} (if {@code this} rule has it) represents: an error or a warning.
     *
     * @return true if {@code ruleError} represents an error, and false if {@code ruleError} represents a warning (or null).
     */
    public boolean isHasError() {
        return isHasError;
    }

    /**
     * @return Class which represents a reference in this rule.
     */
    public Reference getReference() {
        return reference;
    }

    /**
     * @return List of classes which represents fixes in this rule.
     */
    public List<Fix> getFixes() {
        return fixes;
    }
}
