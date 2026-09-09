package com.akranta.tpm.model;

import java.util.List;

public class QtmTlPhenomenaMapping {

    private Object[] saveArray = null;
    private List<QtmTlPhenomenaMapping> phenomenaMappingList;

    public enum tableFldConstants {
        keyid, qphmKeyid, sectFlid, phenomena,
        tempfield1, tempfield2, tempfield3,
        active, createdby, createdon, modifiedon
    }

    public QtmTlPhenomenaMapping() {
        saveArray = new Object[11];
    }

    public Object[] getSaveArray() {
        return saveArray;
    }

    public void setSaveArray(Object[] saveArray) {
        this.saveArray = saveArray;
    }

    public List<QtmTlPhenomenaMapping> getPhenomenaDetails() {
        return phenomenaMappingList;
    }

    public void setPhenomenaDetails(List<QtmTlPhenomenaMapping> phenomenaMappingList) {
        this.phenomenaMappingList = phenomenaMappingList;
    }

    public String getPhnmKeyid() {
        return (String) saveArray[tableFldConstants.keyid.ordinal()];
    }

    public void setPhnmKeyid(String phnmKeyid) {
        saveArray[tableFldConstants.keyid.ordinal()] = phnmKeyid;
    }

    public String getPhnmQphmKeyid() {
        return (String) saveArray[tableFldConstants.qphmKeyid.ordinal()];
    }

    public void setPhnmQphmKeyid(String phnmQphmKeyid) {
        saveArray[tableFldConstants.qphmKeyid.ordinal()] = phnmQphmKeyid;
    }

    public String getPhnmSectFlid() {
        return (String) saveArray[tableFldConstants.sectFlid.ordinal()];
    }

    public void setPhnmSectFlid(String phnmSectFlid) {
        saveArray[tableFldConstants.sectFlid.ordinal()] = phnmSectFlid;
    }

    public String getPhnmPhenomena() {
        return (String) saveArray[tableFldConstants.phenomena.ordinal()];
    }

    public void setPhnmPhenomena(String phnmPhenomena) {
        saveArray[tableFldConstants.phenomena.ordinal()] = phnmPhenomena;
    }

    public String getPhnmTempfield1() {
        return (String) saveArray[tableFldConstants.tempfield1.ordinal()];
    }

    public void setPhnmTempfield1(String phnmTempfield1) {
        saveArray[tableFldConstants.tempfield1.ordinal()] = phnmTempfield1;
    }

    public String getPhnmTempfield2() {
        return (String) saveArray[tableFldConstants.tempfield2.ordinal()];
    }

    public void setPhnmTempfield2(String phnmTempfield2) {
        saveArray[tableFldConstants.tempfield2.ordinal()] = phnmTempfield2;
    }

    public String getPhnmTempfield3() {
        return (String) saveArray[tableFldConstants.tempfield3.ordinal()];
    }

    public void setPhnmTempfield3(String phnmTempfield3) {
        saveArray[tableFldConstants.tempfield3.ordinal()] = phnmTempfield3;
    }

    public String getPhnmActive() {
        return (String) saveArray[tableFldConstants.active.ordinal()];
    }

    public void setPhnmActive(String phnmActive) {
        saveArray[tableFldConstants.active.ordinal()] = phnmActive;
    }

    public String getPhnmCreatedby() {
        return (String) saveArray[tableFldConstants.createdby.ordinal()];
    }

    public void setPhnmCreatedby(String phnmCreatedby) {
        saveArray[tableFldConstants.createdby.ordinal()] = phnmCreatedby;
    }

    public String getPhnmCreatedon() {
        return (String) saveArray[tableFldConstants.createdon.ordinal()];
    }

    public void setPhnmCreatedon(String phnmCreatedon) {
        saveArray[tableFldConstants.createdon.ordinal()] = phnmCreatedon;
    }

    public String getPhnmModifiedon() {
        return (String) saveArray[tableFldConstants.modifiedon.ordinal()];
    }

    public void setPhnmModifiedon(String phnmModifiedon) {
        saveArray[tableFldConstants.modifiedon.ordinal()] = phnmModifiedon;
    }
}