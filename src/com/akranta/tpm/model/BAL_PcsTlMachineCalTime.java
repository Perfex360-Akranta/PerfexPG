package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

public class BAL_PcsTlMachineCalTime {
	private Object[] saveArray= null;
	private List<BAL_PcsTlMachineCalTime> macheCal;
	public enum fieldConstants
	{
		factoryid , sectionid ,cellid, machineid ,shiftid ,shiftdate,caltime                                                                                                                                                                                                                 

	}
	public BAL_PcsTlMachineCalTime(){
		setPcsTlMachineCalTime(new ArrayList<BAL_PcsTlMachineCalTime>());
		saveArray= new Object[7];
	}
	public Object[] getSaveArray(){
		return saveArray;
		
	}
	public List<BAL_PcsTlMachineCalTime> getpcsTlMachineCalTime(){
		return macheCal;
		
	}
	public void setPcsTlMachineCalTime(List<BAL_PcsTlMachineCalTime> macheCal){
		this.macheCal=macheCal;
	}
	public String getMchCalFactory(){
		return (String) saveArray[fieldConstants.factoryid.ordinal()];
	}
	public void setMchCalFactory(String bdmsFactoryid){
		saveArray[fieldConstants.factoryid.ordinal()]=bdmsFactoryid;
	}
	public String getMchCalSectionid(){
		return (String) saveArray[fieldConstants.sectionid.ordinal()];
	}
	
	
	public void setMchCalSectionid(String bdmsSectionid){
		saveArray[fieldConstants.sectionid.ordinal()]=bdmsSectionid;
	}
	public String getMchCalCellId(){
		return (String)saveArray[fieldConstants.cellid.ordinal()];
	}
	public void setMchCalCellId(String bdmsCellid){
		saveArray[fieldConstants.cellid.ordinal()]=bdmsCellid;
	}
	public String getMchCalMachineId(){
		return (String) saveArray[fieldConstants.machineid.ordinal()];
	}
	public void setMchCalMachineId(String bdmsMachineid){
		saveArray[fieldConstants.machineid.ordinal()]=bdmsMachineid;
		
	}
	public String getMchCalShift(){
		
		return (String) saveArray[fieldConstants.shiftid.ordinal()];
	}
	public void setMchCalShift(String bdmsShiftid){
		saveArray[fieldConstants.shiftid.ordinal()]=bdmsShiftid;
	}
	public String getMchCalShiftDt(){
		return (String) saveArray[fieldConstants.shiftdate.ordinal()];
	}
	public void setMchCalShiftDt(String bdmsEntrydate){
		saveArray[fieldConstants.shiftdate.ordinal()]=bdmsEntrydate;
	}
	public int getMchCaltime(){
		return  (Integer) saveArray[fieldConstants.caltime.ordinal()];
	}
	public void setMchCalgetMchCaltime(String mchCalTime){
		saveArray[fieldConstants.caltime.ordinal()]=mchCalTime;
	}



}

