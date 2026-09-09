<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<script type="text/javascript" src="js/jquery.easyui.min.js"></script>-->

<script type="text/javascript" >
jQuery(document).ready(function(){	
	jQuery('#submitForm').val('frmOPL');
	var url=jQuery("#hiddenUrl").val();
	initialiseForm('frmOPL');	
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }
	
	
	if(url=="kaizenSynopsisReport_input.kaz"){
		disableField("frmOPL", "chkboxBK");
		disableField("frmOPL", "chkboxIC");
		disableField("frmOPL", "chkboxTC");
		disableField("frmOPL", "chkboxSectWise");
		disableField("frmOPL", "chkboxCellWise");
		disableField("frmOPL", "chkboxEqptWise");
		disableField("frmOPL", "chkboxLossWise");
		disableField("frmOPL", "chkboxPillarWise");
		disableField("frmOPL", "chkboxResultWise");
		disableField("frmOPL", "chkboxEqptGrpWise");
		disableField("frmOPL", "cboOplTypeid");
		disableField("frmOPL", "chkboxDM");
		disableField("frmOPL", "chkboxET");
		disableField("frmOPL", "chkboxJH");
		disableField("frmOPL", "chkboxKK");
		disableField("frmOPL", "chkboxOTpm");
		disableField("frmOPL", "chkboxPm");
		disableField("frmOPL", "chkboxQm");
		disableField("frmOPL", "chkboxShe");
		setTimeout(function(){disableField("frmOPL","cmbOplNoid")},500);
		setTimeout(function(){disableField("frmOPL","cmbLossid")},500);
	}
	else if(url=="kaizenSummaryReport_input.kaz"){
		disableField("frmOPL", "chkboxBK");
		disableField("frmOPL", "chkboxIC");
		disableField("frmOPL", "chkboxTC");
		disableField("frmOPL", "chkboxSectWise");
		disableField("frmOPL", "chkboxCellWise");
		disableField("frmOPL", "chkboxEqptWise");
		disableField("frmOPL", "chkboxLossWise");
		disableField("frmOPL", "chkboxPillarWise");
		disableField("frmOPL", "chkboxResultWise");
		disableField("frmOPL", "chkboxEqptGrpWise");
		disableField("frmOPL", "cboOplTypeid");
		disableField("frmOPL", "chkboxDM");
		disableField("frmOPL", "chkboxET");
		disableField("frmOPL", "chkboxJH");
		disableField("frmOPL", "chkboxKK");
		disableField("frmOPL", "chkboxOTpm");
		disableField("frmOPL", "chkboxPm");
		disableField("frmOPL", "chkboxQm");
		disableField("frmOPL", "chkboxShe");

		setTimeout(function(){enableFormFields("frmOPL","chkMPWorthy")},500);
		setTimeout(function(){disableField("frmOPL","cmbOplNoid")},500);
		setTimeout(function(){disableField("frmOPL","cmbLossid")},500);
		
	}
	else if(url=="KaizenView_input.kaizen"){
		setTimeout(function(){disableField("frmOPL","cmbLossid")},500);
	}
	
	else if(url=="oplVw_input.opl?mod=view"){
	    setTimeout(function() {readOnlyFields('cmbKznmThemecategoryid');},500);
		setTimeout(function(){enableFormFields("frmOPL","cboOplTypeid")},500);
		setTimeout(function(){enableFormFields("frmOPL","cboKznStatus")},500);
	}
	else if(url=="oplSummaryReport_input.oplrpt"){
		setTimeout(function() {readOnlyFields('cmbKznmThemecategoryid');},500);
		setTimeout(function(){readOnlyFields("cmbImprovmntNoid")}, 500);
		setTimeout(function() {readOnlyFields("cmbLossid")},500);
		setTimeout(function() {readOnlyFields("cmbPillarid")},500);
	}	
	
	
	
	





	var emppillar=jQuery('#hdnEmPillar').val();
  //  alert(emppillar);
    if(emppillar="EmPillar" && emppillar!=undefined && emppillar!='undefined' && emppillar.trim().length>0){
    	jQuery("#chkMPWorthy").attr({'checked':true});
    	disableField("frmOPL", "chkMPWorthy");
    }else if(emppillar!=undefined && emppillar!='undefined'){
    	jQuery("#chkMPWorthy").attr({'checked':false});
    	disableField("frmOPL", "chkMPWorthy");
    	disableField("frmOPL", "chkUtiliseFuture");
    	//enableFields("chkMPWorthy");
    }
    	
    //fillComboBox("frmOPL","cmbkznmThemecategoryid","kaizenactegoryfillcombo.kaizen");
    fillComboBox("frmOPL","cmbOplNoid","oplno.commonFilter" );
    fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter");
	fillComboBox("frmOPL","cmbKznmThemecategoryid","kaizenThemecty.commonFilter"); 
	var thmcategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
	var typebenefit=jQuery('#hdnnewPcdqsme').val();
	if(typebenefit=="P"){
		jQuery('#chkResultAreaP').attr('checked', true);
	}else if(typebenefit=="Q"){
		jQuery('#chkResultAreaQ').attr('checked', true);
	}else if(typebenefit=="C"){
		jQuery('#chkResultAreaC').attr('checked', true);
	}else if(typebenefit=="D"){
		jQuery('#chkResultAreaD').attr('checked', true);
	}else if(typebenefit=="S"){
		jQuery('#chkResultAreaS').attr('checked', true);
	}else if(typebenefit=="M"){
		jQuery('#chkResultAreaM').attr('checked', true);
	}else if(typebenefit=="E"){
		jQuery('#chkResultAreaE').attr('checked', true);
	}
	jQuery('#chkResultAreaP').attr('disabled', true);
	jQuery('#chkResultAreaQ').attr('disabled', true);
	jQuery('#chkResultAreaC').attr('disabled', true);
	jQuery('#chkResultAreaD').attr('disabled', true);
	jQuery('#chkResultAreaS').attr('disabled', true);
	jQuery('#chkResultAreaM').attr('disabled', true);
	jQuery('#chkResultAreaE').attr('disabled', true);

	if(  !jQuery('#cmbOplNoid').is(':disabled') )
		//fillComboBox("frmOPL","cmbOplNoid","oplno.commonFilter" );
	if(  !jQuery('#cmbImprovmntNoid').is(':disabled') )
	 	//fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter" );
	
	 fillComboBox("frmOPL","cmbPillarid","pillar.commonFilter");
	 
	// fillComboBox("frmOPL","cmbKaizenCategory","kaizenCategory.commonFilter" );
	 fillComboBox("frmOPL","cmbLossid","loss.commonFilter");
	 
	 formatDateBox('dteKznDate','dd-MMM-yyyy');
	
	 
	jQuery("#chkboxSectWise").click(function(){		
		if(jQuery('#chkboxSectWise').is(':checked') == true)
		{	
			jQuery("#chkboxCellWise").attr("checked",false);
			jQuery("#chkboxEqptWise").attr("checked",false);
		}		
	});
		
	jQuery("#chkboxCellWise").click(function(){
		if(jQuery('#chkboxCellWise').is(':checked') == true)
		{
			jQuery("#chkboxSectWise").attr("checked",false);
			jQuery("#chkboxEqptWise").attr("checked",false);
		}
	});
	
	jQuery("#chkboxEqptWise").click(function(){
		if(jQuery('#chkboxEqptWise').is(':checked') == true)
		{
			jQuery("#chkboxSectWise").attr("checked",false);
			jQuery("#chkboxCellWise").attr("checked",false);
		}
	});
	jQuery("#chkboxLossWise").click(function(){
		if(jQuery('#chkboxLossWise').is(':checked') == true)
		{
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxPillarWise").click(function(){
		if(jQuery('#chkboxPillarWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxResultWise").click(function(){
		if(jQuery('#chkboxResultWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxEqptGrpWise").attr("checked",false);
		}
	});
	jQuery("#chkboxEqptGrpWise").click(function(){
		if(jQuery('#chkboxEqptGrpWise').is(':checked') == true)
		{
			jQuery("#chkboxLossWise").attr("checked",false);
			jQuery("#chkboxPillarWise").attr("checked",false);
			jQuery("#chkboxResultWise").attr("checked",false);
		}
	});
	
	jQuery("#chkboxBK").click(function(){
		if(jQuery("#chkboxBK").is(':checked')==true){
			jQuery("#chkboxIC").attr("checked",false);
			jQuery("#chkboxTC").attr("checked",false);
		}
	});
	
	jQuery("#chkboxIC").click(function(){
		if(jQuery("#chkboxIC").is(':checked')==true){
			jQuery("#chkboxBK").attr("checked",false);
			jQuery("#chkboxTC").attr("checked",false);
		}
	});
	
	jQuery("#chkboxTC").click(function(){
		if(jQuery("#chkboxTC").is(':checked')==true){
			jQuery("#chkboxIC").attr("checked",false);
			jQuery("#chkboxBK").attr("checked",false);
		}
	});
	
	});
/*function frmOPLcmbOplNoid_onLoadSuccess()
{
	 fillComboBox("frmOPL","cmbImprovmntNoid","impno.commonFilter" );
}
/*function frmOPLcmbPillarid_onLoadSuccess()
{
	 fillComboBox("frmOPL","cmbPillarid","cmbPillarid" );	
}
function frmOPLcmbImprovmntNoid_onLoadSuccess()
{
	
}*/

function  frmOPLcmbKznmThemecategoryid_onSelect(record)
{
	var KEYID=record.id;
	
	var keyid=jQuery('#hdnkeyid').val();
	
	//alert("keyid>>>>>"+keyid);

	/* var kzbnkznm=jQuery('#hdnkznKeyid').val();
	
	var znmKeyid=jQuery('#txtKznmKeyid').val(); */
	
	
	var thmcategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
	
	//alert("thmcategory>>>>>>>"+thmcategory);
	
// 	var newbenid=jQuery('#txtNewBenefit').val();
	
	if(thmcategory.length!=0||thmcategory!=null)
		{
	var r=confirm("Do you want change the Benefit Area and Theme Catgeory");
	 if(r==true)
		 {
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		
		// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		 }
	 else{
		 jQuery("#cmbkznmThemecategoryid").combobox('clear');
		 return false;
	     }
		}
	else{
		
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
	}
	
	
	
}
function CategoryrecallsuccessCallBackCategory(result){ 
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
	
	var chkValue=result[0][1];
	//
	
	//jQuery("hdnNewBenefit").val();
	//  var locnId = jQuery("#frmKaizenupld input[id='location']").val();
	/* if(locnId !=null && locnId !='LCN0000005'){
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	} */
	jQuery('#chkResultAreaP').attr('checked', false);
	jQuery('#chkResultAreaQ').attr('checked', false);
	jQuery('#chkResultAreaC').attr('checked', false);
	jQuery('#chkResultAreaD').attr('checked', false);
	jQuery('#chkResultAreaS').attr('checked', false);
	jQuery('#chkResultAreaM').attr('checked', false);
	jQuery('#chkResultAreaE').attr('checked', false);
	

	if(chkValue=="P"){
		jQuery('#chkResultAreaP').attr('checked', true);
		
	}else if(chkValue=="Q"){
		jQuery('#chkResultAreaQ').attr('checked', true);
		
	}else if(chkValue=="C"){
		jQuery('#chkResultAreaC').attr('checked', true);
		
	}else if(chkValue=="D"){
		jQuery('#chkResultAreaD').attr('checked', true);
	
	}else if(chkValue=="S"){
		jQuery('#chkResultAreaS').attr('checked', true);
		
	}else if(chkValue=="M"){
		jQuery('#chkResultAreaM').attr('checked', true);
		
	}else if(chkValue=="E"){
		jQuery('#chkResultAreaE').attr('checked', true);
		
	}

	jQuery('#chkResultAreaP').attr('disabled', true);
	jQuery('#chkResultAreaQ').attr('disabled', true);
	jQuery('#chkResultAreaC').attr('disabled', true);
	jQuery('#chkResultAreaD').attr('disabled', true);
	jQuery('#chkResultAreaS').attr('disabled', true);
	jQuery('#chkResultAreaM').attr('disabled', true);
	jQuery('#chkResultAreaE').attr('disabled', true);
	//setFieldValue('hdnNewBenefit',chkValue);
//	var KEYID=result[0][0];
	
//	var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();
	
	// var chkValue=result[0][1];
	
	// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
}

function  frmOPLcmbOplNoid_onSelect(record)
{
	//jQuery("#cmbPillarid").combobox('clear');
	jQuery("#cmbImprovmntNoid").combobox('clear');
	reloadCombo("frmOPL","cmbOplNoid","oplno.commonFilter?oplNoid="+record.id);
	//reloadCombo("frmFilter","cmbCell","cellCombo.commonFilter?factId="+record.id  );
	//reloadCombo("frmFilter","cmbMachine","machineCombo.commonFilter?factId="+ record.id );
	//jQuery("#cmbOplNoid").combobox('clear');
}

/*function  frmOPLcmbPillarid_onSelect(record)
{
	jQuery("#cmbOplNoid").combobox('clear');
	reloadCombo("frmOPL","cmbPillarid","pillar.commonFilter?pillarid="+record.id);
}*/


function  frmOPLcmbKznmThemecategoryid_onSelect(record)
{
	var KEYID=record.id;
	
	var keyid=jQuery('#hdnkeyid').val();
	
	//alert("keyid>>>>>"+keyid);

	/* var kzbnkznm=jQuery('#hdnkznKeyid').val();
	
	var znmKeyid=jQuery('#txtKznmKeyid').val(); */
	
	
	var thmcategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
	
	//alert("thmcategory>>>>>>>"+thmcategory);
	
// 	var newbenid=jQuery('#txtNewBenefit').val();
	
	if(thmcategory.length!=0||thmcategory!=null)
		{
	var r=confirm("Do you want change the Benefit Area and Theme Catgeory");
	 if(r==true)
		 {
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		
		// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		 }
	 else{
		 jQuery("#cmbkznmThemecategoryid").combobox('clear');
		 return false;
	     }
		}
	else{
		
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
	}
	
	
	
}
function CategoryrecallsuccessCallBackCategory(result){ 
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
	
	var chkValue=result[0][1];
	//
	
	//jQuery("hdnNewBenefit").val();
	//  var locnId = jQuery("#frmKaizenupld input[id='location']").val();
	/* if(locnId !=null && locnId !='LCN0000005'){
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	} */
	jQuery('#chkResultAreaP').attr('checked', false);
	jQuery('#chkResultAreaQ').attr('checked', false);
	jQuery('#chkResultAreaC').attr('checked', false);
	jQuery('#chkResultAreaD').attr('checked', false);
	jQuery('#chkResultAreaS').attr('checked', false);
	jQuery('#chkResultAreaM').attr('checked', false);
	jQuery('#chkResultAreaE').attr('checked', false);
	

	if(chkValue=="P"){
		jQuery('#chkResultAreaP').attr('checked', true);
		
	}else if(chkValue=="Q"){
		jQuery('#chkResultAreaQ').attr('checked', true);
		
	}else if(chkValue=="C"){
		jQuery('#chkResultAreaC').attr('checked', true);
		
	}else if(chkValue=="D"){
		jQuery('#chkResultAreaD').attr('checked', true);
	
	}else if(chkValue=="S"){
		jQuery('#chkResultAreaS').attr('checked', true);
		
	}else if(chkValue=="M"){
		jQuery('#chkResultAreaM').attr('checked', true);
		
	}else if(chkValue=="E"){
		jQuery('#chkResultAreaE').attr('checked', true);
		
	}

	jQuery('#chkResultAreaP').attr('disabled', true);
	jQuery('#chkResultAreaQ').attr('disabled', true);
	jQuery('#chkResultAreaC').attr('disabled', true);
	jQuery('#chkResultAreaD').attr('disabled', true);
	jQuery('#chkResultAreaS').attr('disabled', true);
	jQuery('#chkResultAreaM').attr('disabled', true);
	jQuery('#chkResultAreaE').attr('disabled', true);
	//setFieldValue('hdnNewBenefit',chkValue);
//	var KEYID=result[0][0];
	
//	var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();
	
	// var chkValue=result[0][1];
	
	// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
}










function  frmOPLcmbImprovmntNoid_onSelect(record)
{
	jQuery("#cmbOplNoid").combobox('clear');
	//reloadCombo("frmOPL","cmbImprovmntNoid","impno.commonFilter?improvmntNoid="+record.id);
}
	
function getRelatedFilterValues()
{
	var filterStr="";

	var cmbOplNoid = jQuery("#cmbOplNoid").combobox("getValue");
	filterStr += "&cmbOplNoid="+cmbOplNoid;
	//var cmbKaizenCategory = jQuery("#cmbKaizenCategory").combobox("getValue");
	//filterStr += "&cmbKaizenCategory="+cmbKaizenCategory;
	var cmbKznmThemecategoryid=jQuery("#cmbKznmThemecategoryid").combobox("getValue");
	filterStr += "&cmbKznmThemecategoryid="+cmbKznmThemecategoryid;
	var cmbImprovmntNoid = jQuery("#cmbImprovmntNoid").combobox("getValue");
	filterStr += "&cmbImprovmntNoid="+cmbImprovmntNoid;
	var cmbPillarid = jQuery("#cmbPillarid").combobox("getValue");
	filterStr += "&cmbPillarid="+cmbPillarid;
	var cboOplTypeid = jQuery("#cboOplTypeid").val();
	filterStr += "&cboOplTypeid="+cboOplTypeid;
	var cboKznStatus= jQuery("#cboKznStatus").val();
	//alert(cboKznStatus);
	filterStr += "&cboKznStatus="+cboKznStatus;
	var cmbLossid = jQuery('#cmbLossid').combobox('getValue');
	filterStr += "&cmbLossid="+cmbLossid;
	//var cmbResultArea=jQuery("#cmbResultArea").combobox("getValue");
	//filterStr += "&cmbResultArea="+cmbResultArea;
	
	/*var dteKznDate = jQuery('#dteKznDate').datebox('getValue');
	filterStr += "&dteKznDate="+dteKznDate;*/
	//var Classification ="";
	filterStr += "&chkGrpByCellid="+getChkBoxVal('chkGrpByCellid');
	filterStr += "&chkboxBK="+getChkBoxValBK('chkboxBK');
//	Classification="BIS";
	filterStr += "&chkboxIC="+getChkBoxValIC('chkboxIC');
	filterStr += "&chkboxTC="+getChkBoxValTC('chkboxTC');
	filterStr += "&chkboxDM="+getChkBoxVal('chkboxDM');
	filterStr += "&chkboxET="+getChkBoxVal('chkboxET');
	filterStr += "&chkboxJH="+getChkBoxVal('chkboxJH');

	
	
	filterStr += "&chkboxKK="+getChkBoxVal('chkboxKK');
	filterStr += "&chkboxOTpm="+getChkBoxVal('chkboxOTpm');
	filterStr += "&chkboxPm="+getChkBoxVal('chkboxPm');
	filterStr += "&chkboxQm="+getChkBoxVal('chkboxQm');
	filterStr += "&chkboxShe="+getChkBoxVal('chkboxShe');
	//alert("FILTER"+filterStr);
	
	filterStr += "&chkResultAreaP="+getChkValResultAreaP('chkResultAreaP');
   	filterStr += "&chkResultAreaQ="+getChkValResultAreaQ('chkResultAreaQ');
   	filterStr += "&chkResultAreaC="+getChkValResultAreaC('chkResultAreaC');
  	filterStr += "&chkResultAreaD="+getChkValResultAreaD('chkResultAreaD');
  	filterStr += "&chkResultAreaS="+getChkValResultAreaS('chkResultAreaS');
  	filterStr += "&chkResultAreaM="+getChkValResultAreaM('chkResultAreaM');
  	filterStr += "&chkResultAreaE="+getChkValResultAreaE('chkResultAreaE');
   //	alert("FILTER"+filterStr);
   	var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');
	filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
	var chkMPWorthy = getChkBoxVal('chkMPWorthy');
	filterStr += "&chkMPWorthy="+(chkMPWorthy=="1" || chkMPWorthy==1? 'Y':'N');
	var chkUtiliseFuture = getChkBoxVal('chkUtiliseFuture');
	filterStr += "&chkUtiliseFuture="+(chkUtiliseFuture=="1" || chkUtiliseFuture==1? 'Y':'N');
	
	var mainGroup;
	
		if(jQuery('#chkboxSectWise').is(':checked') == true)
		mainGroup = "SECTION";
	else if(jQuery('#chkboxCellWise').is(':checked') == true)
		mainGroup = "CELL";
	else if(jQuery('#chkboxEqptWise').is(':checked') == true)
		mainGroup = "MACHINE";
	
	filterStr += "&mainGroup="+mainGroup;
	
	/*filterStr += "&chkboxSectWise="+getChkBoxVal('chkboxSectWise');
	filterStr += "&chkboxCellWise="+getChkBoxVal('chkboxCellWise');
	filterStr += "&chkboxEqptWise="+getChkBoxVal('chkboxEqptWise');*/

	var subGroupHD = " ";
	if(jQuery('#chkboxLossWise').is(':checked') == true)
		subGroupHD = "LOSS";
	if(jQuery('#chkboxPillarWise').is(':checked') == true)
		subGroupHD = "PILLAR";
	if(jQuery('#chkboxResultWise').is(':checked') == true)
		subGroupHD = "RESULT";
	if(jQuery('#chkboxEqptGrpWise').is(':checked') == true)
		subGroupHD = "EQPGRP";
	
	filterStr += "&subGroupHD="+subGroupHD;
	filterStr += "&ViewClicked=Y";
	return filterStr;
}


function getChkValResultAreaP(Id){
	if(jQuery("#chkResultAreaP").is(':checked')==true){
		return "P";
	}
	else{
		 return null;
	}
}

function getChkValResultAreaQ(Id){
	if(jQuery("#chkResultAreaQ").is(':checked')==true){
		return "Q";
	}
	else{
		 return null;
	}
}

function getChkValResultAreaC(Id){
	if(jQuery("#chkResultAreaC").is(':checked')==true){
		return "C";
	}
	else{
		 return null;
	}
}

function getChkValResultAreaD(Id){
	if(jQuery("#chkResultAreaD").is(':checked')==true){
		return "D";
	}
	else{
		 return null;
	}
}
function getChkValResultAreaS(Id){
	if(jQuery("#chkResultAreaS").is(':checked')==true){
		return "S";
	}
	else{
		 return null;
	}
}

function getChkValResultAreaM(Id){
	if(jQuery("#chkResultAreaM").is(':checked')==true){
		return "M";
	}
	else{
		 return null;
	}
}
function getChkValResultAreaE(Id){
	if(jQuery("#chkResultAreaE").is(':checked')==true){
		return "E";
	}
	else{
		 return null;
	}
}
function getChkBoxValBK(Id){
	//alert("getChkBoxValBK"+Id);
	if(jQuery('#'+Id).is(':checked')==true){
	 //  alert("sss");	
		return "B";
	}
	else{
		return null;
	}
}
function getChkBoxValIC(Id){
	if(jQuery('#'+Id).is(':checked')==true){
		return "I";
	}
	else{
		return null;
	}
}

function getChkBoxValTC(Id){
	if(jQuery('#'+Id).is(':checked')==true){
		return "T";
	}
	else{
		return null;
	}
}

function getChkBoxVal(Id) {		
	if(jQuery('#'+Id).is(':checked') == true)  		
		return 1;
	else
		return 0;
}
</script>
<form name="frmOPL" id="frmOPL" >
                        <!--  One Point Lesson & Kaizen Idea Sheet Tab  -->
<!--		<div title="One Point Lesson & Kaizen Idea Sheet Tab" style="padding:10px;">-->
							   <div class="sub-header">Regular</div>
							   	<div style="padding-left:75px;">
								<table><tr><td>
							   <div  class="easyui-paddingbfpx">
                        			<label>OPL No</label>                       
                    		  </div>
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbOplNoid" name="cmbOplNoid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                 </div>
			                    <div  class="easyui-paddingbfpx">
                        			<label>Kaizen No</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbImprovmntNoid" name="cmbImprovmntNoid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                 	
			                   <td style="padding-left:20px;margin:0px;">    
			                    <div  class="easyui-paddingbfpx">
                        			<label>Pillar</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbPillarid" name="cmbPillarid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                 
							     <div  class="easyui-paddingbfpx">
                        			<label>Loss</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbLossid" name="cmbLossid" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                   
			                   
							    
					<!--     <div  class="easyui-paddingbfpx">
                  					 <label>Improvement Date</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                    <input id="dteKznDate" class="easyui-datebox" name="dteKznDate" style="width:160px;" >
							    </div> -->		
							    
							   </td>
							 <td style="padding-left:40px;margin:0px;">
							   
								<div  class="easyui-paddingbfpx">
                  					 <label>OPL Type</label>
								</div> 
								<div class="easyui-paddingbtpx"> 
			                    <select id="cboOplTypeid" class="easyui-combobox" name="cboOplTypeid" style="width:160px;" >
											<option value=" ">  </option>
											<option value="R"> Regular </option>
											<option value="S"> Stepwise </option>
											<option value="I"> Information  </option>
									  </select> 
							    </div>								
								
								<div  class="easyui-paddingbfpx">
                  					 <label>Status</label>
								</div> 
                    		    <div class="easyui-paddingbtpx"> 
			                    <select id="cboKznStatus" class="easyui-combobox" name="cboKznStatus" style="width:160px;" >
											<option></option>
											<option value="A"> Pending </option>
											<option value="C"> Completed </option>
									  </select> 
							    </div>
							   </td> 
							   <td style="padding-left:20px;margin:0px;">
							     <div  class="easyui-paddingbfpx ">
                        		   <span><input type="checkbox" id="chkGrpByCellid" name="chkGrpByCellid" ></span>                          
			     					<span><label>Group By Cell</label></span>                       
                    		   	</div> 
			                    
							   </td> 
						  </tr>
						  <tr>
						<!--   <td style="margin:0px;">    
							     <div  class="easyui-paddingbfpx">
                        			<label>Kaizen Category</label>                       
                    		   </div> 
			                    <div id="" class="easyui-paddingbfpx"> 
			                        <input id="cmbKaizenCategory" name="cmbKaizenCategory" class="easyui-combobox"  style="width:200px" value=""  >                       
			                   </div>
			                   
							   </td> -->
	        
	        <td style="margin:0px;"> 		                  
 <div style="margin-left:0px;; margin-top:0px;">
			<label >Theme Category</label> 
			</div>
	 		<div class="easyui-paddingbfpx" style="margin-left:0px;">
         		<input id="cmbKznmThemecategoryid" name="cmbKznmThemecategoryid" class="easyui-combobox" style="width:200px;margin-top:0px;" value="" ></input>
</div>
  </td>  
  
  
  <td>
  <div  class="easyui-paddingbfpx"style="margin-left:19px;">
 <label><b>Benefit Area</b></label>
  </div>
							    
<!--  <div class="easyui-paddingbtpx" style="margin-left:25px;"> 
<select id="cmbResultArea" class="easyui-combobox" name="cmbResultArea" style="width:50px;" >
<option value=" ">   </option>
<option value="P"> P  </option>
<option value="Q"> Q </option>
<option value="C"> C </option>
<option value="D"> D  </option>
<option value="S"> S  </option>
<option value="M"> M  </option>
<option value="E"> E </option>
 </select> 
 </div> -->
 
  <span id="resultArea" class="easyui-paddingbfpx" style="padding-left:14px;">
		                            <input id="chkResultAreaP" name="chkResultAreaP" type="checkbox" value="P" onclick="chkboxCheck(this.id);"/><span Style="padding-left: 2px"><label>P </label></span><span Style="padding-left: 10px"></span>
            						<input id="chkResultAreaQ" name="chkResultAreaQ" type="checkbox" value="Q" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>Q</label> </span><span Style="padding-left: 10px"></span>
    								<input id="chkResultAreaC" name="chkResultAreaC" type="checkbox" value="C" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>C</label></span> <span Style="padding-left: 10px"></span>
									<input id="chkResultAreaD" name="chkResultAreaD" type="checkbox" value="D" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>D</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaS" name="chkResultAreaS" type="checkbox" value="S" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>S</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaM" name="chkResultAreaM" type="checkbox" value="M" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>M</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="E" onclick="chkboxCheck(this.id);" /><span Style="padding-left:2px"><label>E</label> </span><span Style="padding-left: 10px"></span>
	</span>
</td>

			           <td style="padding-left:20px;margin:0px;">
					     	<div  class="easyui-paddingbfpx "style="margin-left:22px;margin-top:30px; ">
                      		   <span><input type="checkbox" id="chkRemoveBlank" name="chkRemoveBlank" ></span>                          
	     					<span><label>Remove Blank</label></span>                       
                  		   	</div> 
			                    
			             </td>
			          
			             <td style="padding-left:40px;margin:0px;">
					     	<div  class="easyui-paddingbfpx "style="width:240px;margin-left:-200px;margin-top:-20px;" >
                      		   <span><input type="checkbox" id="chkMPWorthy" name="chkMPWorthy" ></span>                          
	     					   <span><label>MP Worthy</label></span> 
	     					   
	     					   <span><input type="checkbox" id="chkUtiliseFuture" name="chkUtiliseFuture" ></span>                          
	     					   <span><label>Utilize For Future Projects</label></span>  
	     					                    
                  		   	</div> 
			             </td>
			                                    
                  		 </tr>
			                   </table></div>
			                   <div class="sub-header">Advance</div>
			                   	<div style="height:360px;" align="center">
								<table><tr><td valign="top">
			                    
							    
							     <div  class="easyui-paddingbfpx">
                  					 <label>Classification</label>
							     </div> 
							     <div id="divClassfn" class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <span><input id="chkboxBK" type="checkbox"/> <label>Basic Knowledge</label></span><br/><br/>
                  					  <span>  <input id="chkboxIC" type="checkbox"/> <label>Improvement Cases</label></span><br/><br/>
                  					  <span>  <input id="chkboxTC" type="checkbox"/> <label>Trouble Cases</label></span>
								</div>
								<div valign="top" style="padding-left:2px;">
								 <div  class="easyui-paddingbfpx">
                  					 <label>Main Group</label>
							     </div> 
							     <div id="divMainGrp" class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxSectWise" type="checkbox" checked="checked"/> <label>Section Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxCellWise" type="checkbox"/> <label>Line Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxEqptWise" type="checkbox"/> <label>Equipment Wise</label></span>
								</div>
								
								 <div  class="easyui-paddingbfpx">
                  					 <label>Sub Group</label>
							     </div> 
							     <div id="divSubGrp"   class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxLossWise" type="checkbox"/> <label>Loss Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxPillarWise" type="checkbox"/> <label>Pillar Wise</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkboxResultWise" type="checkbox"/> <label>Result Wise</label></span><br/><br/>
                  					  <span>  <input id="chkboxEqptGrpWise" type="checkbox"/> <label>Equipment Group Wise</label></span>
                  					  </div>
								</div>
								 </td>
								 <td style="padding-left:120px;" valign="top">
								  <div  class="easyui-paddingbfpx " >
                  					 <label>Pillar Name</label>
							     </div> 
							     <div id="divPillar"  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;padding-left:5px;">
                  					  <input id="chkboxDM" type="checkbox"/> <label> Development Management(DM)</label><br/><br/>
                  					  <span>  <input id="chkboxET" type="checkbox"/> <label>Education and Training (ET)</label></span><br/><br/>
                  					  <span>  <input id="chkboxJH" type="checkbox"/> <label>Jishu Hozen(JH)</label></span><br/><br/>
                  					  <span>  <input id="chkboxKK" type="checkbox"/> <label>Kobetsu Kaizen(KK)</label></span><br/><br/>
                  					  <span>  <input id="chkboxOTpm" type="checkbox"/> <label>Office TPM (OTPM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxPm" type="checkbox"/> <label> Planned Maintenance (PM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxQm" type="checkbox"/> <label>Quality Maintenance(QM)</label></span><br/><br/>
                  					  <span>  <input id="chkboxShe" type="checkbox"/> <label>Safety Health and Environment(SHE)</label></span>
								 </div>
								
								 </td>
					</tr></table></div>
					
                
		
						</form>