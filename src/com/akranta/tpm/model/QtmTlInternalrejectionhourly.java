package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class QtmTlInternalrejectionhourly {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, qtm_keyid, shifthour, shifttiming, inspectedqty, acceptedqty
		, balanceqty, rejectedqty, testingqty, mrbqty, qahold, displayorder
		, reserve1, reserve2, reserve3, reserve4, reserve5, active, userindex
		, timestamp, modtimestamp
	}

	public QtmTlInternalrejectionhourly()
	{
		saveArray = new  Object [ 21 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getQihbKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setQihbKeyid(String qihbKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = qihbKeyid;
	}

	public String getQihbQtmKeyid() {
		return (String) saveArray[ tableFldConstants.qtm_keyid.ordinal() ];
	}

	public void setQihbQtmKeyid(String qihbQtmKeyid) {
		saveArray[ tableFldConstants.qtm_keyid.ordinal() ] = qihbQtmKeyid;
	}

	public String getQihbShifthour() {
		return (String) saveArray[ tableFldConstants.shifthour.ordinal() ];
	}

	public void setQihbShifthour(String qihbShifthour) {
		saveArray[ tableFldConstants.shifthour.ordinal() ] = qihbShifthour;
	}

	public String getQihbShifttiming() {
		return (String) saveArray[ tableFldConstants.shifttiming.ordinal() ];
	}

	public void setQihbShifttiming(String qihbShifttiming) {
		saveArray[ tableFldConstants.shifttiming.ordinal() ] = qihbShifttiming;
	}

	public String getQihbInspectedqty() {
		return (String) saveArray[ tableFldConstants.inspectedqty.ordinal() ];
	}

	public void setQihbInspectedqty(String qihbInspectedqty) {
		saveArray[ tableFldConstants.inspectedqty.ordinal() ] = qihbInspectedqty;
	}

	public String getQihbAcceptedqty() {
		return (String) saveArray[ tableFldConstants.acceptedqty.ordinal() ];
	}

	public void setQihbAcceptedqty(String qihbAcceptedqty) {
		saveArray[ tableFldConstants.acceptedqty.ordinal() ] = qihbAcceptedqty;
	}

	public String getQihbBalanceqty() {
		return (String) saveArray[ tableFldConstants.balanceqty.ordinal() ];
	}

	public void setQihbBalanceqty(String qihbBalanceqty) {
		saveArray[ tableFldConstants.balanceqty.ordinal() ] = qihbBalanceqty;
	}

	public String getQihbRejectedqty() {
		return (String) saveArray[ tableFldConstants.rejectedqty.ordinal() ];
	}

	public void setQihbRejectedqty(String qihbRejectedqty) {
		saveArray[ tableFldConstants.rejectedqty.ordinal() ] = qihbRejectedqty;
	}

	public String getQihbTestingqty() {
		return (String) saveArray[ tableFldConstants.testingqty.ordinal() ];
	}

	public void setQihbTestingqty(String qihbTestingqty) {
		saveArray[ tableFldConstants.testingqty.ordinal() ] = qihbTestingqty;
	}

	public String getQihbMrbqty() {
		return (String) saveArray[ tableFldConstants.mrbqty.ordinal() ];
	}

	public void setQihbMrbqty(String qihbMrbqty) {
		saveArray[ tableFldConstants.mrbqty.ordinal() ] = qihbMrbqty;
	}

	public String getQihbQahold() {
		return (String) saveArray[ tableFldConstants.qahold.ordinal() ];
	}

	public void setQihbQahold(String qihbQahold) {
		saveArray[ tableFldConstants.qahold.ordinal() ] = qihbQahold;
	}

	public String getQihbDisplayorder() {
		return (String) saveArray[ tableFldConstants.displayorder.ordinal() ];
	}

	public void setQihbDisplayorder(String qihbDisplayorder) {
		saveArray[ tableFldConstants.displayorder.ordinal() ] = qihbDisplayorder;
	}

	public String getQihbReserve1() {
		return (String) saveArray[ tableFldConstants.reserve1.ordinal() ];
	}

	public void setQihbReserve1(String qihbReserve1) {
		saveArray[ tableFldConstants.reserve1.ordinal() ] = qihbReserve1;
	}

	public String getQihbReserve2() {
		return (String) saveArray[ tableFldConstants.reserve2.ordinal() ];
	}

	public void setQihbReserve2(String qihbReserve2) {
		saveArray[ tableFldConstants.reserve2.ordinal() ] = qihbReserve2;
	}

	public String getQihbReserve3() {
		return (String) saveArray[ tableFldConstants.reserve3.ordinal() ];
	}

	public void setQihbReserve3(String qihbReserve3) {
		saveArray[ tableFldConstants.reserve3.ordinal() ] = qihbReserve3;
	}

	public String getQihbReserve4() {
		return (String) saveArray[ tableFldConstants.reserve4.ordinal() ];
	}

	public void setQihbReserve4(String qihbReserve4) {
		saveArray[ tableFldConstants.reserve4.ordinal() ] = qihbReserve4;
	}

	public String getQihbReserve5() {
		return (String) saveArray[ tableFldConstants.reserve5.ordinal() ];
	}

	public void setQihbReserve5(String qihbReserve5) {
		saveArray[ tableFldConstants.reserve5.ordinal() ] = qihbReserve5;
	}

	public String getQihbActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setQihbActive(String qihbActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = qihbActive;
	}

	public String getQihbUserindex() {
		return (String) saveArray[ tableFldConstants.userindex.ordinal() ];
	}

	public void setQihbUserindex(String qihbUserindex) {
		saveArray[ tableFldConstants.userindex.ordinal() ] = qihbUserindex;
	}

	public String getQihbTimestamp() {
		return (String) saveArray[ tableFldConstants.timestamp.ordinal() ];
	}

	public void setQihbTimestamp(String qihbTimestamp) {
		saveArray[ tableFldConstants.timestamp.ordinal() ] = qihbTimestamp;
	}

	public String getQihbModtimestamp() {
		return (String) saveArray[ tableFldConstants.modtimestamp.ordinal() ];
	}

	public void setQihbModtimestamp(String qihbModtimestamp) {
		saveArray[ tableFldConstants.modtimestamp.ordinal() ] = qihbModtimestamp;
	}

}

