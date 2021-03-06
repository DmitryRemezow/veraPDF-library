package org.verapdf.validation.report.model;

/**
 * Structure of the profile info in validation info.
 * Created by bezrukov on 5/4/15.
 *
 * @author Maksim Bezrukov
 * @version 1.0
 */
public class Profile {
    private String name;
    private String hash;

    /**
     * Creates model of an information about used profile
     * @param name - name of the validation profile
     * @param hash - hash code of the validation profile
     */
    public Profile(String name, String hash) {
        this.name = name;
        this.hash = hash;
    }

    /**
     * @return Name of the validation profile.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Hash of the validation profile.
     */
    public String getHash() {
        return hash;
    }
}
