<script type="text/javascript">
	
	jQuery(document).ready(function(){
	//alert("hi");
	jQuery('#spnEqp').hide();
	
	fillComboBox("frmInternalRejectionView","cmbMachineid1","machineCombo.commonFilter" );
	//fillComboBox("frmInternalRejectionView","cmbShiftid1","shift.commonFilter" );
	var dataString ="";
	var sectId = jQuery("#frmInternalRejectionView input[id='section']").val();
	var date=jQuery('#txtLabelDate').val();
	dataString+= "?q=2&sectId="+sectId+"&entryDate="+date;	
	//alert(dataString);
	processAjaxCalls("getDetailTableName.pcs",dataString,"setDetailTableNameRecallSuccess","setDetailTableNameRecallError");

	processAjaxCalls("getIsHourlyEntry.ire",dataString,"isHoyrlyRecallSuccess","isHoyrlyRecallError");	
//String condParam="MACHINEID=MCH000002;CELLID=CEL024;SHIFTID=SFT008;FROMDATE=05-Jul-2012;TODATE=05-Jul-2012;IsForHeat=N;PLDetailsTbl=PCS_TL_MLD#IP";
	
	});

	function isHoyrlyRecallSuccess(result) {		
		//alert(result.value);
		if (result.value=="Y")
			jQuery('#hdnFormType').val("HOURLY");
		else
			jQuery('#hdnFormType').val("");
	}
	
	function frmInternalRejectionViewcmbMachineid1_onLoadSuccess() 	{
		fillComboBox("frmInternalRejectionView","cmbShiftid1","shift.commonFilter?q=2&frmRfilter=yes");
	}

	function setDetailTableNameRecallSuccess(result) {
		//alert(result.detailTableName);
		jQuery('#hdnTableName').val(result.detailTableName);

		var dataString="?q=2";
		var machId = jQuery('#cmbMachineid1').val();
		//alert(machId );
		//var machId = "";
		var cellId =jQuery("#frmInternalRejectionView input[id='cell']").val();
		var sectId = jQuery("#frmInternalRejectionView input[id='section']").val();
		var shift =jQuery('#hdnQirmShiftid').val();
		var fromDate=jQuery('#hdnQirmEntrydate').val();
		var toDate =jQuery('#hdnQirmEntrydate').val();
		fromDate='01-Nov-2013';
		toDate='30-Dec-2013';
		var pldetailsId = jQuery('#hdnTableName').val();

		var mode = jQuery('#hdnMode').val();
		if(mode=="view") mode="SCRAPENTRY";

		var entryDate=jQuery('#txtLabelDate').val();
		
		dataString+= "&sectId="+sectId+"&entryDate="+entryDate+"&condParam=MACHINEID="+machId+";CELLID="+cellId+";SHIFTID="+shift+";FROMDATE="+fromDate+";TODATE="+toDate+";IsForHeat=N;FORMMODE="+mode;
		//dataString+= ";PLDetailsTbl="+pldetailsId;
		//alert(dataString);
		processGridnew('intRejViewGrid_view.ire',dataString,"intRejViewGrid","intRejPager","Internal Rejection Entry","intRejdblClick","","intRejViewGrid_loadComplete","intRejViewGridError");

	}

	function intRejViewGrid_loadComplete() {
			hideJqGridRow('intRejViewGrid', '1');
			jQuery("#intRejViewGrid").jqGrid('hideCol',"InspShift23");			
			processAjaxCalls('getFieldsForPcs.pcs','',"getFieldsForPcsSuccess");

			var rowIds = jQuery("#intRejViewGrid").getDataIDs();
			for(var id = 1; id<=rowIds.length; id++) {
				jQuery("#intRejViewGrid").jqGrid('setCell',id,"OffQty18","",{'background-color':'#00a600'});			
			}
	}

	function getFieldsForPcsSuccess(result) {		
		
		
		
		if (result.value.indexOf("EQ") != -1) {
			jQuery("#intRejViewGrid").jqGrid('hideCol',"ExpQty17");
			jQuery('#spnQuality').hide();			
		}
		else {
			jQuery('#spnQuality').show();
		}

		if (result.value.indexOf("WO") != -1) {
			jQuery("#intRejViewGrid").jqGrid('hideCol',"WONo21");			
			
		}

	 	if (result.value.indexOf("QAHOLD") != -1) {
			jQuery("#intRejViewGrid").jqGrid('hideCol',"QAHoldQty27");					
		}
		
			
		if (result.value.indexOf("%QAHOLD") != -1) {
			jQuery("#intRejViewGrid").jqGrid('hideCol',"%QAHoldQty28");					
		}

		
			
		if (result.value.indexOf("TQ") != -1) {
			jQuery("#intRejViewGrid").jqGrid('hideCol',"TrimQty16");			
		}		
	}
	
	function intRejdblClick(id) {
		var dataString="?q=2";
		var date=jQuery('#txtLabelDate').val();				
		var shift=jQuery('#cmbShiftid1').combobox('getValue');
		var factId = jQuery("#frmInternalRejectionView input[id='factory']").val();
		var sectId = jQuery("#frmInternalRejectionView input[id='section']").val();
		var cellId = jQuery("#frmInternalRejectionView input[id='cell']").val();
		//var machId = jQuery('#cmbMachineid1').combobox('getValue');
		
		var rowData = jQuery("#intRejViewGrid").jqGrid('getRowData',id);
/*		var machId = rowData.txtMachineid;		
		var Plmasterid = rowData.txtMasterid;		
		var Pldetailsid =rowData.txtDetailsid;
		var QirmKeyid = rowData.txtParentid.trim();		
		var acceptedQty = rowData.txtAcceptedqty;
		var rejectedQty = rowData.txtRejectedqty;
		var inspectedBy = rowData.txtEmpid ;  //rowData.txtInspectedname;		
		var inspectedDate = rowData.dteInspecteddate; //txtInspectedshift
		var inspectedShift = rowData.txtShiftid;
		var inspectedQty = rowData.txtInspectedqty;
		//alert(Pldetailsid);
		var prdId = rowData.txtProductId;	 //jQuery("#intRejViewGrid").jqGrid('getCell',id,6);
		var totalProduced = rowData.txtTotalproduced;	//jQuery("#intRejViewGrid").jqGrid('getCell',id,9);
*/
		/*masterid1,detailid2,interailid3,Linkid6,Empid7,Shiftid8,Machineid9,MachineNo10,ProducedDate11,Shift12,
		ProductId13,Product14,ProdQty15,Trim.Qty16,Exp.Qty17,Off.Qty18,Ins.Qty19,InpsectedDate20,WONo21,Acp.Qty22,InspectName23,InspectShift24,Rej.Qty25,%Rej.Qty26,QAHoldQty27,PerQAHoldQty28
		*/
		var machId = rowData.MachineID4;		
		var Plmasterid = rowData.masterid1;		
		var Pldetailsid =rowData.detailid2;
		var QirmKeyid = rowData.interailid3.trim();//rowData.Parentid5.trim();		
		var acceptedQty = rowData.AcpQty22;
		var rejectedQty = rowData.RejQty25;
		var inspectedBy = rowData.Empid7;  //rowData.txtInspectedname;		
		var inspectedDate = rowData.InspDate20; //txtInspectedshift
		var inspectedShift = rowData.Shiftid8;
		var inspectedQty = rowData.InsQty19;
		var expansionQty = rowData.ExpQty17;
		var offQty = rowData.OffQty18;
		var parentId = rowData.Parentid5.trim();	
		var linkId = rowData.Linkid6.trim();	
		//alert(expansionQty);
		
		if (expansionQty== " " ||  expansionQty == "") expansionQty=0;
		if (offQty== " " ||  offQty == "") offQty=0;
		if (inspectedQty== " " ||  inspectedQty == "") inspectedQty=0;
		
//		if (!(expansionQty =="" ||  expansionQty == "" || expansionQty==0 || expansionQty== undefined ))
//			inspectedQty =expansionQty;
		
		//alert(inspectedQty);
		//alert(Pldetailsid);
		var prdId = rowData.ProductId13;	 //jQuery("#intRejViewGrid").jqGrid('getCell',id,6);
		var totalProduced = rowData.ProdQty15;	//jQuery("#intRejViewGrid").jqGrid('getCell',id,9);

		if (acceptedQty== " " ||  acceptedQty == "") acceptedQty=0;
		if (rejectedQty== " " ||  rejectedQty == "") rejectedQty=0;
		if (inspectedQty== " " ||  inspectedQty == "") inspectedQty=0;
		
		var dataString = "?q=2&factId="+factId+"&sectId="+sectId+"&cellId="+cellId+"&machId="+machId+"&prdId="+prdId+"&date="+date+"&shift="+shift;
		dataString+= "&Plmasterid="+Plmasterid+"&Pldetailsid="+Pldetailsid+"&QirmKeyid="+QirmKeyid;
		dataString+= "&totalProduced="+totalProduced+"&acceptedQty="+acceptedQty+"&rejectedQty="+rejectedQty;
		dataString+= "&inspectedBy="+inspectedBy+"&inspectedDate="+inspectedDate+"&inspectedShift="+inspectedShift;
		dataString+= "&inspectedQty="+inspectedQty+"&expansionQty="+expansionQty;
		dataString+= "&parentId="+parentId+"&linkId="+linkId;
			
		//alert(dataString);
		//alert(prdId );
		if (prdId == " " ||  prdId == "" ||  machId == "" ||  machId == " " )
			return;
		
		if (jQuery('#hdnFormType').val()=="HOURLY") {
			
			dataString+= "&reportLocation=HALOL&reportType="; 
			navigateToNextForm('intRejEntry_input.ire'+dataString,'Internal Rejection Hourly Breakups');
		}
		else {
			dataString+= "&reportLocation=&reportType=";
			navigateToNextForm('intRejEntry_input.ire'+dataString,'Internal Rejection Entry');
			//LoadPopUp("loadIntRejEntryPopup", "intRejEntry_input.ire"+dataString,  true,"95%","94%","1px","1px", "showEntry_successCallBack","Process - Phenomena - Cause Details",true);
		}
	}
	
</script>

<form id="frmInternalRejectionView" >
<div id="wrapper" style="width:100%;">
<div class="easyui-paddingbfpx" style="padding-left: 10px;width:100%" >
	
	<div> <input type="hidden" id="hdnFormType" name="hdnFormType" value=""></div>
	<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />

	<div> <input type="hidden" id="hdnTableName" name="hdnTableName"></div>
	<div> <input type="hidden" id="hdnPlmMasterId"></div>
	<div> <input type="hidden" id="hdnPldetailsid"></div>	
	<div> <input type="hidden" id="hdnPtwokeyid"></div>
	<input type="hidden" id="factory" name="cmbQirmFactoryid" value="${requestScope.factId}"></input>
	<input type="hidden" id="hdnmachineid" name="hdnmachineid" value="${requestScope.machId}"  ></input>
	<input type="hidden" id="hdnQirmEntrydate" name="hdnQirmEntrydate" value="${requestScope.date}"  ></input>	
	<input type="hidden" id="hdnQirmShiftid" name="hdnQirmShiftid" value="${requestScope.shift}"  ></input>
	<input type="hidden" id="cell" name="cmbQirmCellid" value="${requestScope.cellId}"  ></input>
	<input type="hidden" id="section" name="cmbQirmSectionid" value="${requestScope.sectId}"  ></input>
	
<!--	<div  class="sub-header" align=left style="width:100%;width:105%\9;"> Internal Rejection Entry</div>-->
	
	<div style="margin-top: 10px;">
	<div >
		<span id="spnEqp">
			<label class="mandatory-lbl">Equipment</label>
			<span style="padding-left: 5px">
				<input type="text" id="cmbMachineid1" name="cmbMachineid1" disabled class="easyui-combobox" value="${requestScope.machId}" style="width: 180px;"/>
			</span>				
		</span>	
		<label style="padding-left: 10px;" class="mandatory-lbl"> Date </label>
		<span style="padding-left: 10px">
			<input id="txtLabelDate" name="txtLabelDate" type="text" value="${requestScope.date}"  disabled="disabled" style="border:1px solid black; font-size:10px ; width:90px;height:20px;color:black;font-weight:bold;text-align:center; " />
		</span>							
		<label style="padding-left: 10px;" class="mandatory-lbl">Shift</label>
		<span style="padding-left: 10px;width: 85px;">
			<input type="text" class="easyui-combobox" id="cmbShiftid1" name="cmbShiftid1" readonly="readonly" disabled="disabled" style="width: 85px;"  value="${requestScope.shift}"/>
		</span>
		<span id="spnQuality" style="padding-left: 50px;display: none;">
			<input type="text" value="" disabled="disabled" style="margin-top:12px;width:20px;height:20px;border:1px solid rgba(51, 153, 255, 0.8) ;color:black;background-color:#00a600;font-weight:bold;text-align:center; " />
			<label style="padding-left: 5px;" class="mandatory-lbl">Quantity offer to Quality</label>
		</span>
					
	</div>	
<div style="padding-top: 10px"></div>
	<div style="" class="internalRejectionViewDiv">			
			<table id="intRejViewGrid" style="float: left: ;"></table>
	</div>	
</div>
</div>
</div>
</form>