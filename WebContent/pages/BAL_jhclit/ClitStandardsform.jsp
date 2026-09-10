<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 
  <script type="text/javascript">
	jQuery(document).ready(function(){

		var  mchId = getFieldValue('cmbClisMachineid');//jQuery("#hdnMchId").val();
	 
		jQuery('#hien').val(mchId);
		  		
		initialiseForm('frmJhClitStd');
		jQuery('#submitForm').val('frmJhClitStd');
		jQuery('#fourthfrm').css('display','block');
		jQuery("#showEqpArea").css('display','block');	
		var hdnUrl =jQuery('#hdnUrl').val();
		
		if(hdnUrl.trim()!="")
			jQuery('#hiddenUrl').val(hdnUrl);
		var url;
		var bdmModeC = jQuery('#bdmHdnM').val();
		
		
		if(bdmModeC != " " && bdmModeC != 'undefined' && bdmModeC !="" && bdmModeC !="null"){
			
				url = 'jhClitModification_input.baljhclit';
		
		}
		else{
			
			 url = jQuery('#hiddenUrl').val();
			}
		var chkfil = 	jQuery('#hiddenChk').val();//alert("hdnonred  "+chkfil);
		fillComboBox("frmJhClitStd","cmbClisTradeid","combo_clitTrade.baljhclit");
		
		jQuery('#firstGrid').css('display','none');
		jQuery('#secondGrid').css('display','none');
		jQuery("#thridGrid").css('display','none');
		chk_select();
		jQuery("#vwebtn").css('display','none');
		jQuery('#addMachine').css('display','none');	
		

		jQuery('#filemgr').show();
		//var mchId =jQuery('#hien').val();//alert(mchId);jhClit_save.jhclitjhClit_frm.baljhclit
		var filterData = '?q=2&mchId='+mchId;
		var gridID =jQuery('#txtClisKeyid').val();//alert("grid Id  :"+gridID);
		toolgrid();
		jQuery('#addInfo').css('display','none');
		//if(gridID.length> 0 && gridID.trim() != '' )
		
		if(url == "jhClitview_input.baljhclit"){
			
		jQuery('#addInfo').css('display','none');
		jQuery("#chkClisIstoolsreq").attr("disabled", true);
		jQuery("#howmtd").css('background','lightgray');
		 jQuery("#howmtd").css('width' , '41px');
		jQuery("#howmtd").removeAttr('class', 'easyui-button');
		
		
		}
		numericTextBox('txtClisHowmuchduration');//for number only validation
		
	if(url == 'jhClitModification_input.baljhclit')
	{
		jQuery('#ClisKeyid').val(gridID);
	}
	else{//alert("key Id not set");
	}
	jQuery("#newstndard").css('display','none');
		
		if(url == 'jhClitModification_input.baljhclit')
		 {
		   processAjaxCalls('jhClit_frmload.baljhclit','','frmModifySuccess','frmError');
		   
		 }
		else if((url == 'jhClit_input.baljhclit')||( 'jhClitview_input.baljhclit'))
		 {//alert("gridID  "+gridID);
			if(gridID != " " && gridID !=" "&& gridID != null)
		   		processAjaxCalls('jhClit_frmload.baljhclit',gridID,'frmSuccess','frmError');
			
		 }
		   jQuery('#submitForm').val('frmJhClitStd'); // set the id of form to submit
		 //for getting values in combobox call the action
				//for changing the case in the text area  to caps 
				//jQuery('#frmJhClitStd .easyui-text').css('text-transform', 'uppercase');
				//jQuery('#frmJhClitStd textarea').css('text-transform', 'uppercase');
				//for date conversion to oracle format
				formatDateBox('dteClisNextduedate','dd-MMM-yyyy');
				formatDateBox('dteClisEffectivedate','dd-MMM-yyyy');

				var effdate = jQuery('#dteClisEffectivedate').val();
				if(effdate != " " && effdate != "" && effdate != null)
					{}
				else
					{
				fillWithCurrentDate('dteClisEffectivedate');
				fillWithCurrentDatePlOne('dteClisNextduedate');
					}

				 /*for file manager*/

		 		jQuery("#btnClitFilManage").click(function(){
		 			/*var documentNo =jQuery('#txtClisKeyid').val();	
		 			openFilemgr(documentNo);*/ 		
		 			
		 		});
		 		var kid =jQuery('#txtClisKeyid').val();
		 		var fm=jQuery('#btnClitFilManage').length;
		 		//if (kid.length> 0 && kid.trim() != '' && fm==0)
		 			//alert(jQuery('#btnClitFilManage').is(":visible"));
		 		
		 		if (jQuery('#btnClitFilManage').is(":visible")==false)		 			
			 	{	
		 			fileManagerPopUp(jQuery('#txtClisKeyid').val(),"CLI","frmJhClitStd","btnClitFilManage","filemgr");
		 			//jQuery('#filemgr').show();
			 	}	
		 		//jQuery('#btnClitFilManage').css('height','28px');

		 		jQuery("#closebtn").click(function(){
		 			var tableDatas = jQuery("#list").jqGrid('getRowData');
		 			
		 			var rowIds = "";
		 			jQuery( "#txtClisHowmethod").val(tableDatas[0].txtMlmmMethoddescription);
		 			if(tableDatas==null && tableDatas[0] == null)
		 				 alert("No Rows Available - ");
		 			
		 			jQuery( "#howmethodgrid").dialog('close');
		 		});
		 		jQuery('#clrbtn').click(function (){
		 			
		 			var rowid = jQuery("#list").jqGrid('getGridParam', 'selrow');
		 			//alert("rowid="+rowid);
		 			
		 			jQuery("#list").delRowData(rowid);
		 			}); 

	
		 		jQuery("#cmbClisMachineid").combobox({onRequest:function(opts){		
		 			var mchId = jQuery("#frmJhClitStd input[id='machine']").val();
		 			return "&combokey="+mchId+"&machId="+mchId; 		
		 			}
		 		});

	});
	function btnClitFilManage_click(){
			var documentNo =jQuery('#txtClisKeyid').val();
			if(documentNo == null || documentNo == '' || documentNo.length<2){
				 saveForm('frmJhClitStd','jhClit_save.baljhclit?openfilemgr=Y');
			}
		   else {    
				 var fmgMode = "";
				fileManagerPopUp(documentNo,"CLI","","","", fmgMode);
		    }		
 			//openFilemgr(documentNo); 		
 			
 		}
	function openFilemgr(documentNo){
		if(documentNo.length> 0 && documentNo.trim() != '' ){
				//fileManagerPopUp(documentNo,"CLI","","","");
			}
			else{
	 			//alert("Program should be selected to view FileManager");
			}
		}
	function currentDatePlusOne(){
		 var now = getServerDateTime();
		    now.setDate(now.getDate()+1);
		    
	}
	function  frmJhClitStdcmbClisResponsibilityid_onSelect(record)
	{
		
		jQuery("#cmbClisResponsibilitydesgid").combobox('clear');
		//reloadCombo("frmJhClitStd","cmbClisResponsibilitydesgid","combo_clitDesignation.baljhclit?desgId="+ record.id );
		fillDesignation("fillDesignation.commonFilter?",record.id,'cmbClisResponsibilitydesgid');
	}
		/*processAjaxCalls("fillDesg.baljhclit?",'&empId='+record.id ,'filldesg_OnSuccess','filldesg_OnError');
	}
	function filldesg_OnSuccess(result){
		alert(result);
		alert(Object.keys(result));
		//setFieldValue('cmbClisResponsibilitydesgid',result.id);
	}
	function filldesg_OnError(){
	alert("error");
	}*/
	
// 	fillComboBox("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter");
 	fillComboBox("frmJhClitStd","cmbClisFrequencyunit","combo_clitwhtfreq.baljhclit");
	fillComboBox("frmJhClitStd","cmbClisShiftid","combo_clitShift.baljhclit");
	fillComboBox("frmJhClitStd","cmbClisResponsibilitydesgid","combo_clitDesignation.baljhclit");
	fillComboBox("frmJhClitStd","cmbClisResponsibilityid","employee.commonFilter");
	fillComboBox("frmJhClitStd","cmbClisPreparedbyid","employee.commonFilter");
	fillComboBox("frmJhClitStd","cmbClisActivitytype","combo_clitclassification.baljhclit");
	function  frmJhClitStdcmbClisTradeid_onLoadSuccess()
	{
		
// 		fillComboBox("frmJhClitStd","cmbClisFrequencyunit","combo_clitwhtfreq.baljhclit");
		var chkfil = 	jQuery('#hiddenChk').val();//alert("hdn  "+chkfil);
		
// 		if(chkfil != 'loded')
		//	fillComboBox("frmJhClitStd","cmbClisActivitytype","combo_clitclassification.baljhclit","","");
	}
	function  frmJhClitStdcmbClisActivitytype_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisFrequencyunit","combo_clitwhtfreq.baljhclit","","");
	}
	function  frmJhClitStdcmbClisFrequencyunit_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisShiftid","combo_clitShift.baljhclit");
	}
	function  frmJhClitStdcmbClisShiftid_onLoadSuccess()
	{
		//alert("shift--design");
		//alert('bfr ' + getFieldValue('cmbClisResponsibilitydesgid'));
		
		//fillComboBox("frmJhClitStd","cmbClisResponsibilitydesgid","combo_clitDesignation.baljhclit");
		
	}
	function  frmJhClitStdcmbClisResponsibilitydesgid_onLoadSuccess()
	{
		//alert("desg--resp");
		/* var cliFactId = jQuery('#clisFactID').val();
		reloadCombo("frmJhClitStd","cmbClisShiftid","combo_clitShift.baljhclit?factId="+cliFactId );
		 */
		 //fillComboBox("frmJhClitStd","cmbClisResponsibilityid","employee.commonFilter");
		//alert("desg--resp");
	}
	function  frmJhClitStdcmbClisResponsibilityid_onLoadSuccess()
	{
		//alert("resp--prepby");
		//fillComboBox("frmJhClitStd","cmbClisPreparedbyid","employee.commonFilter");
		
	}
	
	function  frmJhClitStdcmbClisPreparedbyid_onLoadSuccess()
	{
		//alert("prepby--");
		setFieldValue('cmbClisMachineid',jQuery('#hien').val());
		jQuery('#hiddenChk').val('loded');
		//fillComboBox("frmJhClitStd","cmbClisAssemblyid","combo_clitMachineArea.baljhclit");
	}
	function  frmJhClitStdcmbClisAssemblyid_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisDepartmentmgr","combo_clitDeptmgr.baljhclit");
	}
	function  frmJhClitStdcmbClisDepartmentmgr_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisSectionmgr","combo_clitSectMgr.baljhclit");
	}
	function  frmJhClitStdcmbClisSectionmgr_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisGroupleader","combo_clitGrouplead.baljhclit");		
	}
	function  frmJhClitStdcmbClisGroupleader_onLoadSuccess()
	{
				
	}
	//for save details
	function frmJhClitStd_beforeSubmit(){
		//if(jQuery("#chkClisIstoolsreq").is(":checked")&&)
		var tableDatas = jQuery("#tools").jqGrid('getRowData');
		var duration =  jQuery("#txtClisHowmuchduration").val();
		
		
		if(duration.trim() == "0")
		{
			alert("Time Value is More than 0 ");
			jQuery("#txtClisHowmuchduration").val("");
			return false;
		}
		if(jQuery("#chkClisIstoolsreq").is(":checked")&&tableDatas.length==0)
			{
				alert("Select TOOLS");
				return false;
			}
			
		var gridData  = '&multiplemethods='+convertJqGridToJSONObjectArr('list');
			gridData +='&toolsGrid='+convertJqGridToJSONObjectArr('tools');
			//alert("toolsGrid..."+gridData);
			return gridData; 
		
	}
	
	
	
	//function if modification is clicked
	function frmModifySuccess(result)
	{
		//alert(result.jhclitsstdid.ClisResponsibilitydesgid);
		//alert(result.jhclitsstdid.ClisKeyid);

		var toolClisId = result.jhclitsstdid.ClisKeyid;
		toolgrid(toolClisId);
		
		jQuery("#filemgr").show();
		jQuery("#cmbClisTradeid").combobox('setValue',result.jhclitsstdid.ClisTradeid);
		jQuery("#txtClisWherelocation").val(result.jhclitsstdid.ClisWherelocation);
		jQuery("#txtClisWhatactivity").val(result.jhclitsstdid.ClisWhatactivity );
		jQuery("#cmbClisFrequencyunit").combobox('setValue',result.jhclitsstdid.ClisFrequencyunit);
		jQuery("#cmbClisActivitytype").combobox('setValue',result.jhclitsstdid.ClisActivitytype);
		//jQuery('#cmbClisShiftid').combobox('setValue',result.jhclitsstdid.ClisShiftid);
		setFieldValue('cmbClisShiftid',result.jhclitsstdid.ClisShiftid);
		//alert("shift   "+getFieldValue('cmbClisShiftid'));
		jQuery("#txtClisHowmethod").val(result.jhclitsstdid.ClisHowmethod);
		jQuery("#txtClisFormatno").val(result.jhclitsstdid.ClisFormatno);
		jQuery("#txtClisStandard").val(result.jhclitsstdid.ClisStandard);
		jQuery("#txtClisHowmuchduration").val(result.jhclitsstdid.ClisHowmuchduration);
		//jQuery("#dteClisNextduedate").datebox('disable');
		var nxtduedte = result.jhclitsstdid.ClisNextduedate;
		//jQuery("#dteClisNextduedate").datebox('setValue',nxtduedte.substring(0,11));
		jQuery("#dteClisEffectivedate").datebox('disable');
		var effdueDte =result.jhclitsstdid.ClisEffectivedate; 
		//jQuery("#dteClisEffectivedate").datebox('setValue',effdueDte.substring(0,11));
		jQuery("#txtClisTime").val(result.jhclitsstdid.ClisTime);
		jQuery("#txtClisWhyifnotdone").val(result.jhclitsstdid.ClisWhyifnotdone);
		jQuery("#txtClisCorrectiveaction").val(result.jhclitsstdid.ClisCorrectiveaction);
		jQuery("#txtClisStandard").val(result.jhclitsstdid.ClisStandard);
		readOnlyFields('cmbClisShiftid');
		jQuery('#cmbClisResponsibilitydesgid').combobox("disable");
		//setFieldValue('cmbClisResponsibilitydesgid',result.jhclitsstdid.ClisResponsibilitydesgid);
		//readOnlyFields('cmbClisResponsibilitydesgid');
		
		//jQuery('#cmbClisResponsibilitydesgid').combobox('setValue',result.jhclitsstdid.ClisResponsibilitydesgid);
		
		jQuery('#cmbClisResponsibilityid').combobox('setValue',result.jhclitsstdid.ClisResponsibilityid);
		jQuery('#cmbClisPreparedbyid').combobox('setValue',result.jhclitsstdid.ClisPreparedbyid);
		  
	  // alert("Modify "+result.jhclitsstdid.ClisActivitytype);
		
		//alert("modify "+getFieldValue('cmbClisResponsibilitydesgid'));
		/*jQuery('#cmbClisFactoryid').combobox("disable");
		jQuery('#cmbClisSectionid').combobox("disable");
		jQuery('#cmbClisCellid').combobox("disable");
		jQuery('#cmbClisMachineid').combobox("disable");*/	
	}
	//function if creation or View is clicked
	function frmSuccess(result)
	{
		//alert("View form success");
		// alert("asd"+ result.jhclitsstdid.ClisKeyid);
		//alert('hey');
		var toolClisId = result.jhclitsstdid.ClisKeyid;
		toolgrid(toolClisId); 
		jQuery("#cmbClisTradeid").combobox('setValue',result.jhclitsstdid.ClisTradeid);
		jQuery("#txtClisWherelocation").val(result.jhclitsstdid.ClisWherelocation);
		jQuery("#txtClisWhatactivity").val(result.jhclitsstdid.ClisWhatactivity );
		//jQuery('#cmbClisShiftid').combobox('setValue',result.jhclitsstdid.ClisShiftid);
		setFieldValue('cmbClisShiftid',result.jhclitsstdid.ClisShiftid);
		jQuery("#txtClisHowmethod").val(result.jhclitsstdid.ClisHowmethod);
		jQuery("#txtClisFormatno").val(result.jhclitsstdid.ClisFormatno);
		jQuery("#txtClisStandard").val(result.jhclitsstdid.ClisStandard);
		jQuery("#txtClisHowmuchduration").val(result.jhclitsstdid.ClisHowmuchduration);
		var nxtduedte = result.jhclitsstdid.ClisNextduedate;
		//jQuery("#dteClisNextduedate").datebox('setValue',nxtduedte.substring(0,11));
		var effdueDte =result.jhclitsstdid.ClisEffectivedate;
		//alert('effdueDte'+effdueDte);
		jQuery("#dteClisEffectivedate").datebox('setValue', effdueDte.substring(0,11));
		jQuery("#txtClisTime").val(result.jhclitsstdid.ClisTime);
		jQuery("#txtClisWhyifnotdone").val(result.jhclitsstdid.ClisWhyifnotdone);
		jQuery("#txtClisCorrectiveaction").val(result.jhclitsstdid.ClisCorrectiveaction);
		jQuery("#txtClisStandard").val(result.jhclitsstdid.ClisStandard);
		setFieldValue('cmbClisResponsibilitydesgid',result.jhclitsstdid.ClisResponsibilitydesgid);
		//jQuery('#cmbClisResponsibilitydesgid').combobox('setValue',result.jhclitsstdid.ClisResponsibilitydesgid);
		jQuery('#cmbClisResponsibilityid').combobox('setValue',result.jhclitsstdid.ClisResponsibilityid);
		jQuery('#cmbClisPreparedbyid').combobox('setValue',result.jhclitsstdid.ClisPreparedbyid);
		//jQuery('#cmbClisPreparedbyid').combobox('disable');
		//alert('ClisActivitytype');
		jQuery("#cmbClisActivitytype").combobox('setValue',result.jhclitsstdid.ClisActivitytype);
		jQuery("#cmbClisFrequencyunit").combobox('setValue',result.jhclitsstdid.ClisFrequencyunit);
        if(jQuery("#chkClisIstoolsreq").val(result.jhclitsstdid.ClisFrequencyunit)!='Y')
		jQuery("#chkClisIstoolsreq").attr("disabled", true);
       // alert("via "+getFieldValue('cmbClisResponsibilitydesgid'));
	}
		/*
		jQuery('#cmbClisFactoryid').combobox("disable");
		jQuery('#cmbClisSectionid').combobox("disable");
		jQuery('#cmbClisCellid').combobox("disable");
		jQuery('#cmbClisMachineid').combobox("disable");*/
		//jQuery('#cmbClisFrequencyunit').combobox('setValue',result.jhclitsstdid.ClisFrequencyunit);
		
		
	
	function frmError(result)
	{
		//alert('Err');
	}

	function  frmJhClitStdcmbClisFrequencyunit_onSelect(record)
	{	
		  if(record.id == "S"){
			jQuery("#lblshift").removeClass('mandatory-lbl');
		  
			}
			else{ 
				jQuery("#lblshift").addClass('mandatory-lbl');
			  
			}
	}
	//save sucesscallback for BDM return 
	function frmJhClitStd_successsCallback(result)
	{		
		var mchId = jQuery("#frmJhClitStd input[id='machine']").val();
		setFieldValue('cmbClisMachineid',mchId);
		jQuery('#hien').val(mchId);
		fillWithCurrentDatePlOne('dteClisNextduedate');
		jQuery("#toolsinfo").css('color','black');
		jQuery("#hdnUrl").val(jQuery("#hiddenUrl").val());
		processGridnew("tool_input.baljhclit","&q=0","tools","toolspager","","","toolsLoadComplete");
		
		
		if(result.successData.successData == "bdmmode")
		{
			popFormNavigation();
			popFormNavigation();
			popFormNavigation();
			navigateToPrevForm();
		}
		else
		{
			//popFormNavigation();
			//popFormNavigation();
			var clisKeyid = result.successData.clisKeyid;
			
			if(clisKeyid.trim() != '' && clisKeyid.length>0){
				jQuery('#hdnsavedClitKeyId').val(clisKeyid);
				if (result.successData.openfilemgr==true) { 
					var fmgMode = "";
					fileManagerPopUp(clisKeyid,"CLI","","","", fmgMode);
				}
				
				/*var openFilemgrtab=confirm("Do u Want To add Files/Documents");
				if (openFilemgrtab)
				  {
					openFilemgr(clisKeyid);
				  }
				else{
					//refreshForm();
					//navigateToPrevForm();
					//jQuery("#clistdGrid").trigger("reloadGrid") ;
				}*/
			}
			 
		}
		
	}
	
	function fileManagerDivId_onClose(){
		var savedCliKey = jQuery('#hdnsavedClitKeyId').val();
		if(savedCliKey.trim() != '' && savedCliKey.length>0){
			
			
			jQuery("#clistdGrid").trigger("reloadGrid") ;
		}
		return true;
	}
//function for tool  grid
function toolgrid(toolClisId){
	//alert(toolClisId);
	if(toolClisId == null || toolClisId == " ")
		processGridnew("tool_input.baljhclit","&q=0","tools","toolspager","","","toolsLoadComplete");
	else
		processGridnew("toolmod_input.baljhclit","?q=0&toolClisId="+toolClisId,"tools","toolspager","","","toolsLoadComplete");	
}


function toolsLoadComplete() {
	var tableDatas = jQuery("#tools").jqGrid('getRowData');
	if (tableDatas.length>0 ) {
		jQuery("#chkClisIstoolsreq").attr('checked',true);
		jQuery("#toolsinfo").css('color','red');
		jQuery("#tooltree").attr('class', 'easyui-button');
		jQuery("#tooltree").removeAttr('disabled');
	}
}
//function tools pop up
function toolpop(){
	
	//multiSelectPop("tool_pop.mselect","","","","","")
	var tableDatas = jQuery("#tools").jqGrid('getRowData');
	var rowIds = "";
	if(tableDatas!=null && tableDatas[0] != null)
		// alert("tableDatas -"+tableDatas[0].elementid);
	
	 for(var i=0;i<tableDatas.length;i++){
		 rowIds += tableDatas[i].txtPtldToolid+',';
	 }

	 var colNames = 'txtPtldToolid,displaycode';
	 ToolsTree('tool_tree.baljhclit','?rowIds='+rowIds,'tools','1',colNames,'true');
}
//function for Additional Information
  jQuery('#addInfo').click(function(){
		//alert("howmthd");
		var mchareaid=jQuery('#txtClisAssemblyid').val();//alert(mchId);
		jQuery("#cmbClisAssemblyid").combobox('setValue',mchareaid);
		jQuery( "#AddInfoDiv" ).show();
		jQuery( "#AddInfoDiv" ).dialog({
			autoOpen: false,
			modal: true,
			height: 280,
			width : 330,
			title:"Additional Information"		
		});
  });		 
  jQuery("#ad_close").click(function(){
			
		jQuery( "#AddInfoDiv" ).dialog('close');
	});

//fumction for how method button click
	function grdHowmethod_gridComplete(result){
		//alert('s');
		jQuery('#list').setGridParam({cellEdit:false});	
	}
	function howmethod()
	{
		//alert("howmthd");
		var jhclitkeyID =	jQuery('#txtClisKeyid').val();
		processGridnew("howmthd_input.baljhclit?jhclitkeyID="+jhclitkeyID,"&q=0","list","pager","","","","grdHowmethod_gridComplete");
		
		
		//jQuery('#howmethodgrid').css('display','block');
		jQuery( "#howmethodgrid" ).show();
		jQuery( "#howmethodgrid" ).dialog({
			autoOpen: false,
			modal: true,
			height: 460,
			width: 550		
		});	 
		
	  }
//for close grid
	
	

//function for edit row
	jQuery("#edtbtn").click(function(){
		var row_id = jQuery("#list").jqGrid('getGridParam','selrow');
	
		 if( row_id != null ){ 
			 addRow("edit",row_id);
		 }
		 else{
			 alert("Select Row");
		 }
	});
//function for add row
	jQuery("#addmthd").click(function(id){
		var howMethod = jQuery('#txtClisHowmethod').val();
		 // alert("adDDhowMethod  :"+howMethod);
	 addRow("add");
	 });
	 
//function for clear/delete row

	 
	 function howmethodRemove_onSuccess(result)
	 {
	 	
	 	if( result.tpmException != null )
	 	{
	 		showCommonErrorMsg(result.tpmException);
	 	}
	 	else
	 		jQuery("#list").delRowData(howmethodId);	
	 	
	 }
	 function howmethodRemove_onError(status)
	 {
	 	
	 	alert(status.responseText);
	 	showCommonErrorMsg(status.error);
	 }
//FUNCTION FOR ADD ROW	 
	function addRow(para,id){
		
	  var val=null;
	  var txtHowMethod ;	
	  var txtDuration ;
	  var jhclitkeyID =	jQuery('#txtClisKeyid').val();
		 if(para=="edit")
			val=id;
		 else if(para=="add")
			val="new";
		jQuery("#list").jqGrid('editGridRow',val,{
			height:150,
			reloadAfterSubmit:true,
			top : 70,
			left:20,
			beforeSubmit:function()
			{
			  var  msg ;	
			  var val;
			  
			   txtHowMethod = jQuery("#txtMlmmMethoddescription").val();	
			   txtDuration = jQuery("#txtMlmmDuration").val();
	   		   if((txtHowMethod==null||txtHowMethod==""))
				 msg = [false,"Enter Method"];
		 	   else if( (txtDuration==null||txtDuration==""))
		 		 msg = [false,"Enter Duration"];
	 		   else
	 			 msg = [true,null];
	 	//jQuery("#list").jqGrid('addRowData',0,[{"hmkeyid":"0","txthowmethod":txtHowMethod,"txtduration":txtDuration }]);
		 	   return  msg; 
			}
		
//	afterSubmit:
	});
//getting rowCount
	//alert(id);
  	 var rowCount = jQuery("#list").getGridParam("reccount");
	 var mlmmKeyid = jQuery("#list").jqGrid('getRowData',id);
	 //alert("mlmmKeyid -"+mlmmKeyid.txtmethodKeyid);
	 //alert(Object.keys(mlmmKeyid));
	 var tmpMlmKeyid =( mlmmKeyid.txtmethodKeyid ? mlmmKeyid.txtmethodKeyid:null);//alert("tmpMlmKeyid  -"+tmpMlmKeyid);

		 jQuery("#list").jqGrid().setGridParam({editurl :'howmthd_input.baljhclit?jhclitkeyID='+jhclitkeyID +'&txtMlmmKeyid='+tmpMlmKeyid},
		 function(response, status,result, xhr)
		  {
			   if (status == "error") 
			  {
			       var msg = "Sorry but there was an error: ";
			       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		      }
			  
				 	
		});	
	
	 
  }
//function for checkbox selection
//jQuery(":checkbox").click(chk_select);
 function chk_select()
	  {
	 jQuery("#tooltree").removeAttr('class', 'easyui-button');
     jQuery("#tooltree").attr('disabled','disabled');
		 // var toolreq = jQuery('#chkClisIstoolsreqchecked').val();		
		  jQuery("#chkClisIstoolsreq").click(function(){
				if (jQuery("#chkClisIstoolsreq").is(":checked"))
				{
					//show the hidden div 
					
					jQuery("#toolsinfo").css('color','red');
					jQuery("#tooltree").attr('class', 'easyui-button');
					jQuery("#tooltree").removeAttr('disabled');
				}
				else{
					jQuery("#toolsinfo").css('color','#000');
					 jQuery("#tooltree").removeAttr('class', 'easyui-button');
				     jQuery("#tooltree").attr('disabled','disabled');
					}
			
			  });
		
		}
	/*before delete confirmation*/
	function frmJhClitStd_beforeDelete(){
		var inactive=confirm("Are you sure to Inactivate the standard!");
		if (inactive==true)
		  {
			  var delstd=confirm("Deleting standard will affect the related transactions in JH Schedule Do you want to proceed?");
				if (delstd==true)
				  {
					alert("Select Date for Inactivation");
					formatDateBox('dteClisInactivateddate','dd-MMM-yyyy');
					fillWithCurrentDate('dteClisInactivateddate');
					jQuery('#selInactiveDate').css('display','block');
					return false;
				  }
				else{}
		  }
		else
		  {
		  //alert("You pressed Cancel!");
		  }
		  return false;
	}
	jQuery('#btnOk').click(function(){
		
		var currentDate = getServerDateTime();
		var chkDate = getFieldValue('dteClisInactivateddate');
		if(convertStringToDate(chkDate) <= currentDate)
		{   
		//alert(currentDate);
		alert("Inactivating Date should be Greater than Current Date");
			
		}
		else{
			var inactivedate =  getFieldValue('dteClisInactivateddate');
			processAjaxCalls("jhClitModification_delete.baljhclit?",'inactivedate='+inactivedate,'inactive_OnSuccess','inactive_OnError');
			jQuery('#selInactiveDate').css('display','none');
			}
		});
	function inactive_OnSuccess(){
		alert("Standard inactivated sucessfully");
		navigateToPrevForm();
		}
</script>
<style>
#showEqpArea{
/*background-color:#FCEEAB;*/
font-weight: bold;
line-height: 20px;
width:45%;
margin-left:50px;
}
</style>


<div id ="jhclitfilfrm">

<table width="80%"  border="0" cellpadding="0" cellspacing="0" class="normaltxt" style="padding-top: 10px;margin-left:60px">  
<%--        <tr>
       		<td colspan="3">
       			<div id="showEqpArea"  class = "highlightText" style="margin-left:0px;margin-bottom:10px;"><span style="font-size:13px;font-weight:bold; " >Equipment Area:</span>  <label id="machareaname" class="notes" style="vertical-align: middle;" >${requestScope.machAreaName }</label> </div>
       		</td>
       </tr>
 --%>
        <tr width="50%" >
       	 
          <td valign="top" style="padding-left: 0px;"> 
          <input type=hidden id="fourth" class="easyui-text" value="d"/>
            <div ><label>Maint.Section</label> </div>
            <div class="easyui-paddingbfpx" >
<!--            <input type="hidden" id="ClisKeyid" name="ClisKeyid"  style=" height : 21px;" class="easyui-text"/>-->
            <input id="cmbClisTradeid" name="cmbClisTradeid" class="easyui-combobox" style="width:255px;"value=""  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/></div>
         <!-- yet to add id-->
          <div class="mandatory-lbl"><label>Standard</label></div>
          <div class="easyui-paddingbfpx" >
          	<textarea rows="2" style="width: 255px;" cols="" id="txtClisStandard" name="txtClisStandard"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">${requestScope.cliTlStandards.clisStandard}</textarea>
          <span id="err_txtClisStandard" class="tpm-errormsg" style=" "></span>
          </div>
       
             <!-- yet to add id-->
            <div class="" ><label  class="mandatory-lbl">Item</label></div>
            <div class="easyui-paddingbfpx"  >
            <textarea rows="2" style="width: 255px;" cols="" id="txtClisWhatactivity" name="txtClisWhatactivity"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></textarea></div>
            
            <div style="width:130%;" ><label>Classification</label>
            <span style="padding-left:50px;"><label class="mandatory-lbl">What (Freq)</label></span></div>
            <div class="easyui-paddingbfpx" style="width:130%;" >
            <input id="cmbClisActivitytype" name="cmbClisActivitytype" class="easyui-combobox"style="width:123px;"
            value="${requestScope.cliTlStandards.clisActivitytype}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">
            <%-- <select id="cmbClisActivitytype" name="cmbClisActivitytype" class="easyui-combobox" style="width:123px;" <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>>
				<option value=""> </option>
				<option value="C"> CLEAN</option>
				<option value="L"> LUBE</option>
				<option value="I"> INSPECT</option>
				<option value="T"> TIGHTEN</option>
				<option value="R"> RE-TIGHTEN</option>
			</select> --%>
            <span style="padding-left:5px;">
            <input id="cmbClisFrequencyunit" name="cmbClisFrequencyunit" class="easyui-combobox"style="width:123px;"
            value="${requestScope.cliTlStandards.clisFrequencyunit}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">
            <!--
<%--             <select id="cmbClisFrequencyunit" name="cmbClisFrequencyunit" class="easyui-combobox" style="width:125px;" required="true" <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>> --%>
<!-- 				<option value=""> </option> -->
<!-- 				<option value="S">SHIFT WISE</option> -->
<!-- 				<option value="D">DAILY</option> -->
<!-- 				<option value="W">WEEKLY</option> -->
<!-- 				<option value="F">FORTNIGHTLY</option> -->
<!-- 				<option value="M">MONTHLY</option> -->
<!-- 				<option value="Q">QUARTELY</option> -->
<!-- 			</select>--->
</span> 
			</div> 
             <div  style=" height : 20px;width:255px;">
           		<span id="err_cmbClisActivitytype" class="tpm-errormsg" style="float:left;" ></span>
           		
            
            	<span  style="padding-left:5px;float:right;" id="err_cmbClisFrequencyunit" class="tpm-errormsg"></span>
               
			 </div>
            
            <div  ><label  id="" >Shift</label></div>
            <div class="easyui-paddingbfpx" >
            <input id="cmbClisShiftid" name="cmbClisShiftid" class="easyui-combobox"style="width:255px;" value="${requestScope.cliTlStandards.clisShiftid}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></div>
             <!-- yet to add id-->
            <div  ><label  class="mandatory-lbl">Method</label></div>
            <div class="easyui-paddingbfpx" >
            <span><textarea rows="2" style="width: 255px;" cols="" id="txtClisHowmethod" name="txtClisHowmethod"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></textarea>
            </span>
<!--            <input type="button" value="..." class="easyui-button" style="vertical-align:top;" onclick="howmethod()"/>-->
           <span style="float:right; position:absolute;bottom:-42px;left:290px;">
<%--            <input type="button" class ="easyui-button" value="..." id="howmtd" style="vertical-align:top; width:20px; " onclick='howmethod();' <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>/> --%>
           </span>
            </div>
            	<span id="err_txtClisHowmethod" class="tpm-errormsg" style="float:left;" ></span>
          </td>
          <td valign="top" style="padding-right: 40px;padding-left: 60px;"> <!-- yet to add id-->
           <div >
          
          <label class="mandatory-lbl" >Next Due</label>
           <span style="padding-left:73px;">
           <label class="mandatory-lbl">Time</label>
<!--            <label  class="mandatory-lbl">JH Std Effective From</label> -->
           </span> 
          </div>
          <div class="easyui-paddingbfpx" style="width:130%;" >
          <input id="dteClisNextduedate" name="dteClisNextduedate" class="easyui-datebox easyui-text" style="width:120px;"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></input>
      	    <span style="padding-left:5px;">
      	    <input type="text"  id = "txtClisHowmuchduration" name="txtClisHowmuchduration"  style=" height : 21px;" class="easyui-text" maxlength="3"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>
      	    <label style="font-size:10px;">(in Minutes)</label> 
<%--       	    <input class="easyui-datebox easyui-text" id="dteClisEffectivedate" name="dteClisEffectivedate" style="width:120px;" <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>></input> --%>
			
			</span>
          </div>   
           
           <div  style=" height : 20px;width:255px;">
           		<span id="err_dteClisNextduedate" class="tpm-errormsg" style="float:left;" ></span>
<!--             	<span  style="padding-left:5px;float:right;" id="err_dteClisEffectivedate" class="tpm-errormsg"></span> -->
					<span class="tpm-errormsg" id="err_txtClisHowmuchduration" style=" padding-left:5px;float:right;"> </span>
			 </div>
		
          
          <div  class="easyui-paddingbfpx" ><label>What (Tools)</label> 
          <input type="checkbox" id="chkClisIstoolsreq" name="chkClisIstoolsreq" value="Y" value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/><label id="toolsinfo">Tools Information</label></div>
          <span style="float:right;vertical-align:top;margin-top:-28;margin-right:12">
          <input type="button"  value="..." id="tooltree" style="vertical-align:top;background-color:lightgray; width : 41px;" onclick='toolpop();'  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>
           </span>
          <div  class="easyui-paddingbfpx" style="width:190px;height:150px;"><table id="tools"></table><div id="toolspager"></div></div>
           <!-- yet to add id-->
          <div   ><label>Block Diagram Reference</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" maxlength="30" cols="" id="txtClisFormatno" name="txtClisFormatno"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></textarea></div>
          
           <div   ><label >Part / Location</label></div>
           <div class="easyui-paddingbfpx" >
            <textarea rows="2" style="width: 255px;" cols="" id="txtClisWherelocation" name="txtClisWherelocation"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">--${requestScope.cliTlStandards.clisWherelocation}</textarea>
            </div>
          
          
          
<!--           <div   ><label class="mandatory-lbl">Time</label></div> -->
<!--           <div class="easyui-paddingbfpx" >           -->
<%--           <input type="text"  id = "txtClisHowmuchduration" name="txtClisHowmuchduration"  style=" height : 21px;" class="easyui-text" maxlength="3" <c:out value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"/>/><label>(in Seconds)</label></div> --%>
           
         	 </td>
          <td valign="top" style="padding-left: 50px;">
          
          <div   ><label >Shift Time</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="txtClisTime" name="txtClisTime"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">${requestScope.cliTlStandards.clisTime}</textarea></div>
          
          <div><label>If Not Done</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="txtClisWhyifnotdone" name="txtClisWhyifnotdone"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">${requestScope.cliTlStandards.clisWhyifnotdone}</textarea></div>
          
          <div  ><label>Corrective Action</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="txtClisCorrectiveaction" name="txtClisCorrectiveaction"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">${requestScope.cliTlStandards.clisCorrectiveaction}</textarea></div>
          
          <div  ><label >Designation</label></div>
          <div class="easyui-paddingbfpx" >
          <input id="cmbClisResponsibilitydesgid" name="cmbClisResponsibilitydesgid" class="easyui-combobox" style="width:255px;" value="${requestScope.cliTlStandards.clisResponsibilitydesgid}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}"></div>
          
          
          <div   ><label >Responsibility</label> </div>
          <div class="easyui-paddingbfpx">
          <input id="cmbClisResponsibilityid" name="cmbClisResponsibilityid" class="easyui-combobox" style="width:255px;" value="${requestScope.cliTlStandards.clisResponsibilityid}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">
          </div>
          
          <div  ><label >Prepared By</label> </div>
          <div class="easyui-paddingbfpx">
          <input id="cmbClisPreparedbyid" name="cmbClisPreparedbyid" class="easyui-combobox" style="width:255px;" value="${requestScope.cliTlStandards.clisPreparedbyid}"  value = "${requestScope.cliTlStandardFormBean.disableForm == true ? ' disabled':''}">
          <input id="hiddenChk" name="hiddenChk"  style="width:255px;" type="hidden"></div>  
                  
          </td>
       </tr>

</table>
</div>



<!--how Method-->
<form id="frmHowMethod" name="frmHowMethod"  action="" method="post">
<div id="howmethodgrid" style="display:none; margin-left:1%;" title="How Method">
<table id="list" ></table>
<div id="pager"></div>

<input type="button" class ="easyui-button" value="ADD METHOD" id="addmthd" style="margin-left:35px;width:110px;"/>
<input type="button" class ="easyui-button" value="EDIT" id="edtbtn" style="margin-left:50px;"/>
<input type="button" class ="easyui-button" value="CLEAR" id="clrbtn" style="margin-left:50px;" />
<input type="button" class ="easyui-button" value="CLOSE" id="closebtn" />
</div>
</form>
<!--end-->
<!--for Additional Informatoin-->
<div id="AddInfoDiv"  style="display:none;">
<div style="padding-left:30px;padding-bottom:5px;">
	<div  ><label>Machine Area</label></div>
	<div class="easyui-paddingbfpx" >
	  	<input id="cmbClisAssemblyid" name="cmbClisAssemblyid" class="easyui-combobox"  type="text" style="width:255px;" value=""  > </div>
	  	
	<div  ><label>Group No</label></div>
	<div class="easyui-paddingbfpx" >
	<input type="text" class="easyui-text"; id="txtClisGroupno" name="txtClisGroupno"  maxlength="8" style=" height: 21px;width:255px;"/></div>
	
	<div   ><label>Dept.Mgr</label></div>
	<div class="easyui-paddingbfpx" >
	<input id="cmbClisDepartmentmgr" name="cmbClisDepartmentmgr" class="easyui-combobox" style="width:255px;"value="">
	</div> 
	
	<div  ><label>Section.Mgr</label></div>
	<div class="easyui-paddingbfpx" >
	<input id="cmbClisSectionmgr" name="cmbClisSectionmgr" class="easyui-combobox" style="width:255px;"value=""></div>
	
	<div   ><label>Group Lead</label></div>
	<div class="easyui-paddingbfpx" >
	<input id="cmbClisGroupleader" name="cmbClisGroupleader" class="easyui-combobox" style="width:255px;"value=""></div> 
	
	<!--<div class="easyui-paddingbfpx" style="float:right;" >
	<input type="button" align="middle" value="Close"  style=" height : 21px;" class="easyui-button"/> 
	<input type="reset" align="middle" value="Clear" style=" height : 21px;" class="easyui-button"/> </div>
	</div>-->
	</div>
</div>

<!--end-->

<div id="toolspop" title="Tools" style="hidden"></div>
<input type="hidden" id="hdnHowmethdId" value=""/>
<input type="hidden" id="hdnHowmethd" value=""/>
<input type="hidden" id="hdnDuration" value=""/>
<input type="hidden" id="hdnsavedClitKeyId" value=""/>
