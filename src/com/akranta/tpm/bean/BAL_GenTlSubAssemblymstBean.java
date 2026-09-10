package com.akranta.tpm.bean;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;

public class BAL_GenTlSubAssemblymstBean {
    private String formActionMode;
    private FormModes formMode;
    private String formHeader;
    private String assemblyId;   // parent assembly FK

    private boolean disableForm;
    private boolean disableSbamKeyid;
    private boolean disableSbamName;
    private boolean disableSbamDescription;
    private boolean disableSbamRemarks;
    private String machineId;

    public BAL_GenTlSubAssemblymstBean() {
        this.formMode = FormModes.create;
        this.formActionMode = FormModeConsts.create;
        this.disableForm = false;
    }

    public BAL_GenTlSubAssemblymstBean(FormModes mode) {
        formModeSettings(mode);
    }

    public BAL_GenTlSubAssemblymstBean(FormModes mode, String lockFields) {
        formModeSettings(mode);
        lockFormControls(lockFields);
    }
    
    public String getMachineId() { 
        return machineId; 
    }

    private void formModeSettings(FormModes mode) {
        this.formMode = mode;
        if (mode == FormModes.view) {
            this.disableForm = true;
            this.formActionMode = FormModeConsts.view;
            this.disableSbamKeyid = this.disableSbamName =
                this.disableSbamDescription = this.disableSbamRemarks = true;
        } else if (mode == FormModes.modify) {
            this.formActionMode = FormModeConsts.modify;
        } else {
            this.formActionMode = FormModeConsts.create;
        }
    }

    private void lockFormControls(String lockFields) {
        if (UIUtils.isValidKeyId(lockFields)) {
            for (String f : lockFields.split(",")) {
                if (f.equals("KEYID"))       this.disableSbamKeyid = true;
                else if (f.equals("NAME"))   this.disableSbamName = true;
                else if (f.equals("CODE"))   this.disableSbamDescription = true;
                else if (f.equals("ADDRESS"))this.disableSbamRemarks = true;
            }
        }
    }

    // --- getters/setters ---
    public String getFormActionMode() { return formActionMode; }
    public void setFormActionMode(String v) { this.formActionMode = v; }
    public FormModes getFormMode() { return formMode; }
    public void setFormMode(FormModes v) { this.formMode = v; }
    public String getFormHeader() { return formHeader; }
    public void setFormHeader(String v) { this.formHeader = v; }
    public String getAssemblyId() { return assemblyId; }
    public void setAssemblyId(String v) { this.assemblyId = v; }
    public boolean isDisableForm() { return disableForm; }
    public void setDisableForm(boolean v) { this.disableForm = v; }
    public boolean isDisableSbamKeyid() { return disableSbamKeyid; }
    public void setDisableSbamKeyid(boolean v) { this.disableSbamKeyid = v; }
    public boolean isDisableSbamName() { return disableSbamName; }
    public void setDisableSbamName(boolean v) { this.disableSbamName = v; }
    public boolean isDisableSbamDescription() { return disableSbamDescription; }
    public void setDisableSbamDescription(boolean v) { this.disableSbamDescription = v; }
    public boolean isDisableSbamRemarks() { return disableSbamRemarks; }
    public void setDisableSbamRemarks(boolean v) { this.disableSbamRemarks = v; }
    
    public void setMachineId(String machineId) { 
        this.machineId = machineId; 
    }
}