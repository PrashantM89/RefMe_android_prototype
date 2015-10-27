package org.refme.refme_android_prototype.librefme.model;

import java.io.Serializable;

/**
 * Created by prashant on 27/9/15.
 */
public class ReferalItems implements Serializable {

    private String companyName;
    private String exp;
    private String location;
    private String renumeration;
    private String designation;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRenumeration() {
        return renumeration;
    }

    public void setRenumeration(String renumeration) {
        this.renumeration = renumeration;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
