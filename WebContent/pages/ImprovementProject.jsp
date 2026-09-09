<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<style>
	.refTypepadding
{
	margin-top:60px;
}
</style>
<script type="text/javascript">

jQuery(document).ready(function(){

	jQuery('#submitForm').val('frmImprovementPrj'); // set the id of form to submit
	initialiseForm('frmImprovementPrj');
	var url = jQuery('#hiddenUrl').val();
	var flid = jQuery('#flid').val();
	var kaizenApproval = jQuery('#hdnkaizenApproval').val();
	var modeVal=jQuery("#mode").val();
	if(modeVal=="modify"){
		jQuery("#viewWhywhy").show();
		jQuery("#viewKznEva").show();
		
	}else{
		jQuery("#viewWhywhy").hide();
		jQuery("#viewKznEva").hide();
	}
	disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	var hdnEMPillar= jQuery("#hdnEMPillar").val();
	if (jQuery('#chkkznmIsworthformp').is(':checked')==true ){
		if (hdnEMPillar.length>0 ){
			enableFields("chkkznmUtiliseforfuture");
		}
	}else{
		jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
		disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	}
	jQuery('#chkkznmIsworthformp').click(function(){
		if (jQuery('#chkkznmIsworthformp').is(':checked')==false ){
			jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
			disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
		}
	});
	jQuery("#individualImg").hide();
	jQuery('#refType').removeClass("refTypepadding");
	if(kaizenApproval=="true"){
		fillComboBox("frmImprovementPrj","cmbkznmApprovedBy","employee.commonFilter");
		formatDateBox('dtekznmDateadd','dd-MMM-yyyy');
		jQuery('#mainborder').css('height','1142px');
		jQuery('#kznapplswodiv').show();
		}else{
			jQuery('#mainborder').css('height','1020px');
			jQuery('#kznapplswodiv').hide();
			}
	//alert(flid);
    //jQuery("input[type=file][name=dlgImgPresent]" ).remove();
	
	imageUpload(jQuery("#dlgImgPresent" ),'ImageUpload.commonFilter','dlgImgPresent',"imgKznmPresentimage","imgKznPresentImgFilename");
    imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgkznmAfterimage","imgKznAfterImgFilename");
 	imageUpload(jQuery( "#dlgImgResult" ),'ImageUpload.commonFilter','dlgImgResult',"imgkznmResultimage","imgKznResultImgFilename");

 	var url = jQuery('#hiddenUrl').val();
	
	var factId = jQuery("#frmImprovementPrj input[id='factory']").val();
	var sectionId = jQuery("#frmImprovementPrj input[id='section']").val();
	var cellId = jQuery("#frmImprovementPrj input[id='cell']").val();
	var machId = jQuery("#frmImprovementPrj input[id='machine']").val();
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	//alert("machine ==="+jQuery('#machine').val());
	//alert("machId ="+machId);
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	
	loadFunctionalLocation("kznmfunLocation","functionalLoc.commonFilter","kznmfunLocationValues","frmImprovementPrj",dataStr);
	//--------------------------Fill Combobox Values-------------------------------//	
	fillComboBox("frmImprovementPrj","cmbkznmKeyid","combo_improvemnetNo.kaizen" );
	var compId = getFieldValue('company','frmImprovementPrj');
	var locnId = getFieldValue('location','frmImprovementPrj');
	var factId = getFieldValue('factory','frmImprovementPrj');
	var sectId = getFieldValue('section','frmImprovementPrj');
	var cellId = getFieldValue('cell','frmImprovementPrj');
	var machId = getFieldValue('machine','frmImprovementPrj');
	var flid = getFieldValue('flid','frmImprovementPrj');
//	alert("getFieldValue cellId ="+cellId +" machId ="+machId);
//fillComboBox("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);
	fillComboBox("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?compId="+compId + "&locnId="+locnId +"&factId="+factId+"&sectId="+sectId+"&cellId="+cellId +"&machId="+machId);

	//fillComboBox("frmImprovementPrj","cmbkznmPhenomenaid","phenomena.commonFilter");	
	fillComboBox("frmImprovementPrj","cmbkznmWwmsKeyid","combo_whywhy.kaizen");
	fillComboBox("frmImprovementPrj","cmbkznmPreparedid","employee.commonFilter");
	fillComboBox("frmImprovementPrj","cmbkznmCircleid","circle.commonFilter");
	 fillComboBox("frmImprovementPrj","cmbKznmCostcentreid","costCenter.commonFilter");
	//fillComboBox("frmImprovementPrj","cmbkznmAssemblyid","assembly.commonFilter");
	//fillComboBox("frmImprovementPrj","cmbkznmCauseid","cause.commonFilter");
	//jQuery("#cmbkznmPhenomenaid").combobox('disable');	
	
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	processGridnew("Pillar_input.kaizen","?kaizenId="+kaizenId,"pillarGrid","pillarPager","","","","pillarGrid_OnCompleteLoad");
	processGridnew("kznGraphData_input.kaizen","?kaizenId="+kaizenId,"khzGraphDataGrid","khzGraphDataPager","","","","kznGraphDataOnload");
	processGridnew("kznHdScan_input.kaizen","?row=0","kznHDGrid","kznHDPager","","","","kznHDGrid_OnGridload");
	processGridnew("kznWhyWhy_input.kaizen","?row=0","kznYYGrid","kznYYPager");
	//processGridnew("kznBTSGrid_input.kaizen","?row=0","kznBTSGrid","kznBTSPager","","","","kznBTSGrid_OnGridload");
	
	jQuery('#frmImprovementPrj .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmImprovementPrj textarea').css('text-transform', 'uppercase');
	formatDateBox('dtekznmDate','dd-MMM-yyyy');
	formatDateBox('dtekznmStartdate','dd-MMM-yyyy');
	formatDateBox('dtekznmEnddate','dd-MMM-yyyy');
	
	 jQuery('#dteRfrommonth').datebox({  
		 
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
		 onSelect:function(date){
			var onSelectFunctionName = 'dteRfrommonth_onSelectMonth';
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}
		 }/*
			onChange:function(date){
				var onChangeFunctionName = 'dteRfrommonth_onChangeMonth';
				var args = [ date ];
				dynamicFunctionCall(onChangeFunctionName, args);	
			}*/
		 
	 });  

	 jQuery('#dteRtomonth').datebox({  
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
	 	onSelect:function(date){
			var onSelectFunctionName = 'dteRtomonth_onSelectMonth';
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}
		 }
		/*	onChange:function(date){
				var onChangeFunctionName = 'dteRtomonth_onChangeMonth';
				var args = [ date ];
				dynamicFunctionCall(onChangeFunctionName, args);	
			}*/
	 }); 
	//formatDateBox('dtekznmApproveddate','dd-MMM-yyyy');
	formatDateBox('dtetargeton','dd-MMM-yyyy');
	numericTextBox("txtkznmLabourcost");
	numericTextBox("txtkznmMaterialcost");
	numericTextBox("txttotalcost");
	readOnlyFields("txttotalcost");
	readOnlyFields("txtkznmNoofhds");
	readOnlyFields("txtkznmRefdocno");
	readOnlyFields("txtkznmRefdoctype");
	numericTextBox("txtKznmCostperhour");
	numericTextBox("txtKznmCostperequipment");
	numericTextBox("txtBTSTotalcost");
	readOnlyFields("txtBTSTotalcost");
	disableField("frmImprovementPrj", "txtKznmBenefitvalue");
	var kznmDate = jQuery('#dtekznmDate').datebox('getValue');
	//var kznmApprvDate = jQuery("#dtekznmApproveddate").datebox("getValue");
	var kznmStartDate = jQuery("#dtekznmStartdate").datebox("getValue");
	var kznmEndDate =jQuery("#dtekznmEnddate").datebox("getValue");

	if( kznmDate == null || kznmDate.length <= 0)
	{
		fillWithCurrentDate("dtekznmDate");
	}
/*	if( kznmApprvDate == null || kznmApprvDate.length <= 0)
	{
		fillWithCurrentDate("dtekznmApproveddate");
	}
*/
	if( kznmStartDate == null || kznmStartDate.length<=0)
	{
		fillWithCurrentDate("dtekznmStartdate");
	}

	if(kznmEndDate == null || kznmEndDate.length<=0)	
	{
		fillWithCurrentDate("dtekznmEnddate");
	}
	
	fillWithCurrentMonth("dteRfrommonth");
	fillWithCurrentMonth("dteRtomonth");
	kaizenOnload();
	
	if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val().trim() !='')
	{		  
			   jQuery("#cmbkznmRelatedto").combobox('setValue',jQuery("#relatedToCMB").val());
			   fillComboBox("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter");
			  
	}

	 if(jQuery('#cmbkznmRelatedto').combobox('getValue')=="MLD")
		   jQuery('#cmbkznmMouldid').combobox('enable');
	   else 
		   jQuery('#cmbkznmMouldid').combobox('disable');
		   		  
		
	jQuery("#chkLineGraph").attr({'checked':true});
	jQuery( "#btnKaizenEvaluation" ).click(function() {
		var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
		var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	//	processAjaxCalls("KaizenEvaluation_input.kazev","&kaizenId="+kaizenId+"&flid="+flid);
		navigateToNextForm("KaizenEvaluation_input.kazev?q=2&kaizenId="+kaizenId+"&flid="+flid);
	});
	jQuery( "#btnImgPresentClear" ).click(function() {
		processAjaxCalls("kznPresentImageClear.kaizen","&presentImage=present");
		jQuery('#imgKznPresentImgFilename').val(" ");
		jQuery('#imgKznmPresentimage').attr('src', " ");
	});
	
	jQuery( "#btnImgAfterClear" ).click(function() {
		processAjaxCalls("kznAfterImageClear.kaizen","&afterImage=after");
		jQuery('#imgKznAfterImgFilename').val(" ");
		jQuery('#imgkznmAfterimage').attr('src', " ");
	});
	
	jQuery( "#btnImgResultClear" ).click(function() {
		processAjaxCalls("kznResultImageClear.kaizen","&resultImage=result");
		jQuery('#imgkznmResultimage').attr('src', " ");
		jQuery('#imgKznResultImgFilename').val(" ");
	});
	
	
	jQuery("#imgkznmAfterimage").load(function() {
 		if((jQuery(this).width()>306.15)||(jQuery(this).height()>143.63))
		{	jQuery('#imgkznmAfterimage').attr('src', " ");
		 	jQuery('#imgKznAfterImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}
    });

	jQuery("#imgKznmPresentimage").load(function() {
 		if((jQuery(this).width()>306.15)||(jQuery(this).height()>143.63))
		{	jQuery('#imgKznmPresentimage').attr('src', " ");
			jQuery('#imgKznPresentImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}
        
    });
			
	jQuery("#imgkznmResultimage").load(function() {
		if((jQuery(this).width()>306.15)||(jQuery(this).height()>143.63))
		{	jQuery('#imgkznmResultimage').attr('src', " ");
		 	jQuery('#imgKznResultImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}
		
    });

	var valbenefittype = jQuery('#hdnBenefittype').val();
	jQuery("#frmImprovementPrj select[id= cboKznmBenefittype]").attr('value',valbenefittype);
	if(valbenefittype.length>0){
		if(!(valbenefittype.contains("NS"))){
			getSelectType(valbenefittype);
			CalculateBTS();	
			jQuery('#txtKznmBenefitvalue').val(jQuery('#hdnBenefitvalue').val());
		}else{
			jQuery("#btnkpi").hide();
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").attr('maxlength','5');
			jQuery("#spninrbox").css('padding-left','0');
		}
	}
	/*if(valbenefittype.contains("GE5")){
		//jQuery("#txtKznmBenefitvalue").val();
	}*/
	jQuery("#tabKaizen").tabs({ onSelect:function(title){ 
		var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
		if(jQuery("#khzGraphDataGrid").getGridParam('reccount') <= 0 && title=="Results")
		{
			//processGridnew("kznGraphData_getData.kaizen","?kaizenId="+kaizenId,"khzGraphDataGrid","khzGraphDataPager");
			jQuery('#kznHDGrid').trigger('reloadGrid');
		}
		else if(title=="Analysis")
		{
			var wwmsKeyid = jQuery('#cmbkznmWwmsKeyid').combobox('getValue');
			processGridnew("kznWhyWhy_input.kaizen","?wwmsKeyid="+wwmsKeyid,"kznYYGrid","kznYYPager");
		//	jQuery('#kznYYGrid').trigger('reloadGrid');
		}
		else if( title=="HD" )
		{
			jQuery('#kznHDGrid').trigger('reloadGrid');	
		//	processGridnew("kznHdScan_getData.kaizen","?row=0","kznHDGrid","kznHDPager","","","","kznHDGrid_OnGridload");
		}
		else if(title=="Cost")
		{
			//jQuery('#pillarGrid').trigger('reloadGrid');	
			//processGridnew("Pillar_input.kaizen","?kaizenId="+kaizenId,"pillarGrid","pillarPager","","","","pillarGrid_OnCompleteLoad");
		}
		else if( title=="BTS"){
			var benselval=jQuery("#cboKznmBenefittype").val();
			var flid = jQuery('#flid').val();
			var benTypeVal="";
			
		if(benselval=="GE5")
			benTypeVal="BTSG5L";
		else if(benselval=="NS")
			benTypeVal="BTSNOSAVING";
		else if(benselval=="S")
			benTypeVal="BTSSAFETY";
		else if(benselval=="LE5")
			benTypeVal="BTSL5L";
			workFlow("divKznWorkFlow",false,benTypeVal, kaizenId, "KZNBTS",flid);
		}
		
	}});
	/*
	if(jQuery('#mode').val()=="view" || jQuery('#mode').val()=="completion")
	{
		changeFormInputBackGround('frmImprovementPrj');
	}
	*/
	if(jQuery('#mode').val()=="create" )
	{
		jQuery("#btnExcelVw").css("display","none");	
	}

	if(jQuery('#hdnbdmode').val()=="bdmmode")
		jQuery('#cmbkznmMachineid').combobox('disable');
	
	
	if(getFilterValue(url + '&',"kznStatus")=="PENDING")
	{	jQuery('#txtStatus').css("display","block");jQuery("#txtStatus").val(getFilterValue(url + '&',"kznStatus"));
		//jQuery('#ktab').css({'margin-top':'60px'});
	}

	if(jQuery('#hdnqtmmode').val().trim()!= null && jQuery('#hdnqtmmode').val().trim()!=""
			&& jQuery('#hdnqtmmode').val().trim()!=false && jQuery('#hdnqtmmode').val().trim() == true
			|| jQuery('#hdnqtmmode').val().trim() == "true")
	{
		jQuery("#cmbkznmMachineid").combobox("disable");
		jQuery("#cmbKznmCostcentreid").combobox("disable");
		jQuery("#cmbkznmCircleid").combobox("disable");
	}	

	fileManagerPopUp(jQuery('#cmbkznmMachineid').combobox('getValue'),"IMP","frmImprovementPrj","btnFilManage","impFilemgr");

	jQuery('#rtdtomld').hide();
	    
});

function btnFilManage_click(){
    
    var documentNo =jQuery("#hdnkznKeyid").val();
	
	if(documentNo != null && documentNo != ''){
		fileManagerPopUp(documentNo,"IMP","","","");
	}
	
		}
function frmImprovementPrjcmbkznmKeyid_onLoadSuccess(){}
function frmImprovementPrjcmbkznmWwmsKeyid_onLoadSuccess(){}
function frmImprovementPrjcmbkznmFactoryid_onLoadSuccess(){
	fillComboBox("frmImprovementPrj","cmbkznmSectionid","sectionCombo.commonFilter" );
}
function frmImprovementPrjcmbkznmSectionid_onLoadSuccess(){
	
	fillComboBox("frmImprovementPrj","cmbkznmCellid","cellCombo.commonFilter" );
}
function frmImprovementPrjcmbkznmCellid_onLoadSuccess(){
	//fillComboBox("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter" );
}
function frmImprovementPrjcmbkznmMachineid_onLoadSuccess(){
	 var cellId = jQuery("#cell").val();
	 fillComboBox("frmImprovementPrj","cmbKznmCostcentreid","costCenter.commonFilter?cellId="+cellId  );
	
}	
function frmImprovementPrjcmbKznmCostcentreid_onLoadSuccess(){
	setComboDefaultValue("frmImprovementPrj","cmbKznmCostcentreid");
	fillComboBox("frmImprovementPrj","cmbkznmCircleid","circle.commonFilter");
	
}
function frmImprovementPrjcmbkznmCircleid_onLoadSuccess(){
	setComboDefaultValue("frmImprovementPrj","cmbkznmCircleid");
	
	//fillComboBox("frmImprovementPrj","cmbResponsibility","employee.commonFilter");
}

function frmImprovementPrjcmbkznmPreparedid_onLoadSuccess(){
	
}

function  frmImprovementPrjcmbkznmFactoryid_onSelect(record)
{
	jQuery("#cmbkznmSectionid").combobox('clear');
	jQuery("#cmbkznmCellid").combobox('clear');
	jQuery("#cmbkznmMachineid").combobox('clear');
	reloadCombo("frmImprovementPrj","cmbkznmSectionid","sectionCombo.commonFilter?factId="+record.id);
	reloadCombo("frmImprovementPrj","cmbkznmCellid","cellCombo.commonFilter?factId="+record.id  );
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?factId="+ record.id );
}

function  frmImprovementPrjcmbkznmSectionid_onSelect(record)
{
	jQuery("#cmbkznmCellid").combobox('clear');
	jQuery("#cmbkznmMachineid").combobox('clear');
	reloadCombo("frmImprovementPrj","cmbkznmCellid","cellCombo.commonFilter?sectId="+record.id  );
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?sectId="+ record.id );
}

function  frmImprovementPrjcmbkznmCellid_onSelect(record)
{
	jQuery("#cmbkznmMachineid").combobox('clear');
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?cellId="+ record.id );
}

function  frmImprovementPrjcmbkznmMachineid_onSelect(record)
{
	loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&machId="+record.id);
	jQuery("#cmbkznmAssemblyid").combobox('clear');
	
 	reloadCombo("frmImprovementPrj","cmbkznmAssemblyid","assembly.commonFilter?machineId="+ record.id );
 	reloadCombo("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter?q=2&mchId="+record.id );
}
function  frmImprovementPrjcmbKznmCostcentreid_onSelect(record)
{
	//loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&cellId="+record.id);
	//alert("onSelect");
	//fillcostCenterHierarchy("costcenterHierarchy.commonFilter",record.id);
}
function  frmImprovementPrjcmbkznmMouldid_onSelect(record)
{
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?mouldId="+ record.id ); 
}

function  frmImprovementPrjcmbkznmCircleid_onSelect(record)
{}

function frmImprovementPrjcmbkznmAssemblyid_onSelect(record)
{
	var assembly = getFieldValue('cmbkznmAssemblyid','frmImprovementPrj');
	if(assembly != null && assembly.length > 0)
	{
		jQuery("#cmbkznmPhenomenaid").combobox('enable');	
	}
	else 
		jQuery("#cmbkznmPhenomenaid").combobox('disable');	
}

function  frmImprovementPrjcmbkznmPhenomenaid_onSelect(record)
{}

jQuery("#cmbkznmRelatedto").combobox({
	onSelect:function(recordid){	
		jQuery('#relatedToCMB').val(recordid.id);			
		if(recordid.id == 'MLD')
		{		
			jQuery("#lblMld").addClass('mandatory-lbl');	
			enableFields('cmbkznmMouldid');
			var mchId = jQuery("#cmbkznmMachineid").combobox('getValue');
			 fillComboBox("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter");	
			if(mchId != null && mchId != ' ' && mchId !='')
			{
				jQuery('#cmbkznmMouldid').combobox('clear');
				reloadCombo("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter?q=2&mchId="+mchId );
			}
			
		}
		if(recordid.id == 'MCH')
		{				
			jQuery("#lblMld").removeClass('mandatory-lbl');
			jQuery("#cmbkznmMouldid").combobox('clear');
			readOnlyFields('cmbkznmMouldid'); 
			reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter");		
		}
			
	}

});    

function frmImprovementPrjcmbkznmMachineid_onClear()
{
	loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&cellId="+jQuery("#frmOplCreation input[id='cell']").val());
	jQuery("#frmImprovementPrj input[id='machine']").val('');
	setTimeout(function() {jQuery('#cmbkznmMachineid').combobox('clear');},1250);
}

function frmImprovementPrj_FuntLocHierarchy_SuccessCallBack(keyIds)
{//alert("cell="+jQuery('#hdncellid').val());
	//alert("keyIds.machId ="+keyIds.machId);
	if(keyIds.machId.trim()!= null && keyIds.machId.trim()!= "null" )
	{
		setFieldValue('cmbkznmMachineid',keyIds.machId);
		reloadMachine("frmImprovementPrj",'cmbkznmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	}

	jQuery("#cmbKznmCostcentreid").combobox('clear');
	jQuery("#cmbkznmCircleid").combobox('clear');
	reloadCombo("frmImprovementPrj","cmbKznmCostcentreid","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );
	// fillComboBox("frmImprovementPrj","cmbKznmCostcentreid","costCenter.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  );

	reloadCombo("frmImprovementPrj","cmbkznmCircleid","circle.commonFilter?cellId="+keyIds.cellId + "&factId="+keyIds.factId +"&machId="+keyIds.machId  +"&sectId="+keyIds.sectId  );
	
}

jQuery('#btnKznWhyWhy').click(function (){
	saveForm('frmImprovementPrj','kaizen_whywhy.kaizen');
});


function frmImprovementPrj_beforeCloseCurrentForm()
{}
 
 function frmImprovementPrj_errorCallback(result) {
 }

 function frmImprovementPrj_successsCallback(result)
 {
	 //alert("Inside success");
	 var modes = result.successData.mode;
	 var persistentData = result.persistentData;
	 var forwardData = result.forwardData;
	 var savemode=result.saveMode;

	 jQuery('#chkIdeagroupindividualI').attr('checked',true);
	 jQuery("#chkResultAreaP").attr("value","P");
	 jQuery("#chkResultAreaQ").attr("value","Q");
	 jQuery("#chkResultAreaC").attr("value","C");
	 jQuery("#chkResultAreaD").attr("value","D");
	 jQuery("#chkResultAreaS").attr("value","S");
	 jQuery("#chkResultAreaM").attr("value","M");
	 jQuery("#chkResultAreaE").attr("value","E");
	 jQuery("#chkResultAreaSecP").attr("value","P");
	 jQuery("#chkResultAreaSecQ").attr("value","Q");
	 jQuery("#chkResultAreaSecC").attr("value","C");
	 jQuery("#chkResultAreaSecD").attr("value","D");
	 jQuery("#chkResultAreaSecS").attr("value","S");
	 jQuery("#chkResultAreaSecM").attr("value","M");
	 jQuery("#chkResultAreaSecE").attr("value","E");
	 jQuery("#chkIdeagroupindividualG").attr("value","G");
	 jQuery("#chkIdeagroupindividualI").attr("value","I");

     //jQuery("#hdnhdScan").val(result.successData.keyId);
    jQuery('#pillarGrid').trigger('reloadGrid');
     
	 
	 jQuery("#btnFilManage").click(function(){
		    
		    var documentNo =result.successData.keyId;
			
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"IMP","","","");
			}
	 });

	 if(result.successData.chkDuplicate==null ||result.successData.chkDuplicate=="undefined"||result.successData.chkDuplicate=="")
	 {
		if(  modes == "whywhy")
		{
			navigateToNextForm("whywhy_input.why",result.formHeader,forwardData,persistentData);
			return false;
		}
		  if(modes != null && modes == "hdScan" )
		{
			 jQuery("#hdnhdScan").val('');
			 navigateToNextForm("hrzdplymnt_input.hrzdply","Horizontal Deployment",forwardData,persistentData );
			 return false;
	    }
		if(result.successData.successData == "bdmmode")
		{
			popFormNavigation();
			popFormNavigation();
		}
		 else{
			 if( modes != null && modes == "whywhy")
			 {
				 navigateToNextForm("whywhy_input.why",result.formHeader,forwardData,persistentData);
			 }	 
			 else if(modes != null && modes == "hdScan" )
			 {
				 jQuery("#hdnhdScan").val('');
				 navigateToNextForm("hrzdplymnt_input.hrzdply","Horizontal Deployment",forwardData,persistentData );
			 }
		
			 else if(modes != null && modes == "category" )
			 {
				 navigateToNextForm("kaizen_category.kcat",result.formHeader,forwardData,persistentData );
			 }
			
			 else if(modes=="Modify"&& mode != "category" && mode != "whywhy"&& mode != "hdScan")
			 {
			//	  navigateToPrevForm();
			 }
			 else
				kaizenFrmLoad();
		 }
	 }
	
	 var  kznKeyid = result.successData.kznKeyid;
	 var openactnpln =result.successData.openactnpln;
	 //alert("openactnpln:openactnpln"+openactnpln);
	 if(openactnpln==true ){
	 var keyid = result.successData.kznKeyid;
	 //alert("Inside successcallback::keyid"+keyid);
	 var flid = result.successData.flid;
	 //alert("Inside successcallback::flid"+flid);
	 openActionPlan("impActionPlan",keyid,"kzn",flid);
	 //fileManagerPopUp(keyid,"IMP","","","");
	 
	 }
	 else
	 {
		 return false;
	 }
		 
}

 function frmImprovementPrj_beforeSubmit()
 {
	
	 var mchId=jQuery('#cmbkznmMachineid').combobox('getValue');
	 var theme =jQuery('#txtkznmTheme').val().trim();
	 var bnchMrk=jQuery('#txtkznmBenchmark').val().trim();
	 var target=jQuery('#txtkznmTarget').val().trim();
	 var wwmsKeyid = jQuery('#cmbkznmWwmsKeyid').combobox('getValue');
	 var checked =jQuery("input:checked[name='common_checkbox']").length >0;
	 var row = jQuery("#pillarGrid").jqGrid('getDataIDs');
		var countcommon=0;
		var countpillar=0;
		var countLoss=0;
		var count=0;
		for (var i = 0; i < row.length; i++) {
			rowid = row[i];
			if (jQuery('#common_checkbox_'+(i+1)).is(':checked')==true){
				countcommon++;
			}
			if (jQuery('#pillar_checkbox_'+(i+1)).is(':checked')==true || jQuery('#loss_checkbox_'+(i+1)).is(':checked')==true){
				countpillar++;
			}	
		}	
		
		if((parseInt(countcommon)==parseInt(countpillar) ) ){
			count++;
		}
	if(!(jQuery('#mode').val()=="view"||jQuery('#mode').val()=="completion"||jQuery('#hdnFrmActionMode').val()=="Completed"))
	{	
		 if(mchId!=null&&mchId!=""&&theme!=null&&theme!=""&&bnchMrk!=null&&bnchMrk!=""&&target!=null&&target!="" )
		 { 
			 clearValidationErrorMessages('frmImprovementPrj','dispErr');
		//	if(wwmsKeyid!=null &&wwmsKeyid!=""||wwmsKeyid!="undefined")
		//	{ 
			/*	 if (!checked){
					jQuery("#tabKaizen").tabs('select',"Cost");
		     		alert("Select Pillar ");
		    		 return false;
		  		}
			//}
			/*else if(wwmsKeyid!=null &&wwmsKeyid!="")
			{
				alert()
			}*/

			 var pillarId=jQuery('#hdnpillarId').val();
				if(pillarId!=null && pillarId.trim()!="")
				{
					var kznCategory=jQuery("#pillarGrid").getCell(pillarId,"kznCategory");
					var kznLossno=jQuery("#pillarGrid").getCell(pillarId,"kznLossNo");
					if(parseInt(count)==0)
					{
						if((kznCategory==null ||kznCategory.trim()=="")&&(kznLossno==null ||kznLossno.trim()==""))
						{
							jQuery("#tabKaizen").tabs('select',"Cost");
							alert("Select Category/Loss for the selected pillar");	
							return false;
						}
					}	
		 		}		
				
		 } 
	 //if( jQuery("#hdnhdScan").val().trim()==""&&jQuery('#cmbkznmKeyid').combobox('getValue').trim()=="")
	// {

	
	/*	  if((jQuery('#chkHdRequiredY').is(':checked')==true)&&(jQuery('#txtkznmNoofhds').val()=="0" || jQuery('#txtkznmNoofhds').val().trim()=="" ))
		  {
			 jQuery("#tabKaizen").tabs('select',"HD");
			 alert("Select HD ");
			 return false;	
		  }*/
		// }
	 
	}
	/*  var mchId=jQuery('#cmbkznmMachineid').combobox('getValue');
	  var theme =jQuery('#txtkznmTheme').val();
	  var bnchMrk=jQuery('#txtkznmBenchmark').val();
	  var target=jQuery('#txtkznmTarget').val();
	  if(mchId!=null&&mchId!=""&&theme!=null&&theme!=""&&bnchMrk!=null&&bnchMrk!=""&&target!=null&&target!="" )
	  { 
	    if(jQuery('#cmbkznmWwmsKeyid').val()==null||jQuery('#cmbkznmWwmsKeyid').val()=="")
		 {
			div_err();							
			jQuery('#dispErr').html('Enter Analysis');
			return false;
		 }
	    else
		{
	    	clearValidationErrorMessages('frmImprovementPrj','dispErr');
		}
	  }
	  */
		/*var rowData = jQuery("#"+jqGridId).jqGrid('getRowData');
		alert('rowData ='+rowData);
		*/	
		
		//jQuery("#KznGraphData tr[id=1]").addClass("edit-cell ui-state-highlight");
		//jQuery("td[aria-describedby=khzGraphDataGrid_MonthYr]").focus();
		//jQuery("#KznGraphData").jqGrid('setGridParam', {cellEdit: false});
		
		
		var gridData ='&KznPillarLink='+JqGridToJsonSelectdRows('pillarGrid','pillar_checkbox','txtselectionFlag');
			gridData += '&KznLossLink='+JqGridToJsonSelectdRows('pillarGrid','loss_checkbox','txtselectLossFlag');

		//alert('gridData ='+gridData);
		if(convertToJSONGraphArr('khzGraphDataGrid').trim()!=null && convertToJSONGraphArr('khzGraphDataGrid').trim()!="")
	 		gridData += '&KznGraphData='+convertToJSONGraphArr('khzGraphDataGrid');
			
		var kznmKeyid=jQuery('#cmbkznmKeyid').combobox('getValue');
		if(kznmKeyid.length>0)
			gridData += '&saveMode=U';	
		//alert('gridData ='+gridData);
		return gridData; 
 }
 function frmImprovementPrj_exceptionCallback(){ }

 function frmImprovementPrj_beforeDelete()
 {
	 if(jQuery('#mode').val() !="view")
	{
	 var delMsg = "Do You Want To Delete This Kaizen ("+jQuery('#cmbkznmKeyid').combobox('getValue')+") ?";
		if(confirm(delMsg) == false)
		{
				return false;
		}
	}
 }

 function frmImprovementPrj_deleteSuccessCallback(result)
 {
	if(result.successData.frmMode=="Modify")
	{
		navigateToPrevForm("KaizenModify_input.kaizen");
	}
}

 function kaizenFrmLoad()
 {
	 jQuery('#imgkznmAfterimage').attr('src', "");
	 jQuery('#imgKznAfterImgFilename').val("");
	 jQuery('#imgKznmPresentimage').attr('src', "");
	 jQuery('#imgKznPresentImgFilename').val("");
	 jQuery('#imgkznmResultimage').attr('src', "");
	 jQuery('#imgKznResultImgFilename').val("");
	 fillWithCurrentDate("dtekznmDate");
	 fillWithCurrentDate("dtekznmStartdate");
	 fillWithCurrentDate("dtekznmEnddate");
 	 fillWithCurrentMonth("dteRfrommonth");
	 fillWithCurrentMonth("dteRtomonth");
	// fillWithCurrentDate("dtekznmApproveddate");
	 jQuery("#cmbkznmRelatedto").combobox("setValue","MCH");
	 jQuery('#cmbkznmMouldid').combobox('disable');
	 jQuery("#KznGraphData").clearGridData();
	 jQuery("#kznYYGrid").clearGridData();
	 jQuery("#kznHDGrid").clearGridData();
	 loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","");

	 var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznCategory"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznLossNo"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectionFlag"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectLossFlag"," ");
		} 
		
	 }
	
//------------- FOR IMPROVEMENT PILLARLINK-------------//

function pillarcboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	
	return '<input name="pillar_checkbox" id="pillar_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){pillarLinkSelect(\''+rowId + '\');}else{pillarLinkUncheck(\''+ rowId +'\')}"/>';
}

function pillarLinkSelect(id)
{
	jQuery('#hdnpillarId').val(id);
	var pillarId = jQuery("#pillarGrid").getCell(id,"txtKzplTpmpillarid");
	multiSelectPop("imprvCategory_input.kaizen","pillarId%3D"+pillarId, "pillarGrid",id,"txtKzplKzncategoryid,kznCategory",false,"MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Kaizen Category");
	//multiSelectPop("imprvCategory_input.kaizen","pillarId%3D"+pillarId, "pillarGrid",id,"txtKzplKzncategoryid,kznCategory",true,"MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
	var utilise = jQuery("#pillarGrid").getCell(id,"kznCategory");
	jQuery('#loss_checkbox_'+id).attr({'disabled':false});
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");

	/*var kznCat=jQuery("#pillarGrid").getCell(id,"kznCategory");
	if ((kznCat.toLowerCase().indexOf("design change/improvement") >= 0) )
		enableFields("chkkznmUtiliseforfuture");
	else{
		jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
		disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	}*/
}
/*function pillarGrid_multiselectPopUpId_onClose(){
	alert("in");
}*/
function pillarLinkUncheck(id)
{
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";

	jQuery("#pillarGrid").setCell(id,"kznCategory"," ");
	jQuery("#pillarGrid").setCell(id,"txtKzplKzncategoryid"," ");
	jQuery("#pillarGrid").setCell(id,"txtselectionFlag",mode);
	jQuery("#loss_checkbox_"+id).attr('checked',false);
	jQuery('#loss_checkbox_'+id).attr({'disabled':true});
	if(jQuery('#loss_checkbox_'+id).is(':checked')==true)
	{
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","UPDATE");
	}else 
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag",mode);

	/*if (jQuery('#chkkznmUtiliseforfuture').is(':checked')==true )
		enableFields("chkkznmUtiliseforfuture");
	else{
		jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
		disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	}*/
}

function pillarGrid_OnCompleteLoad()
{
	var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++)
	{
		jQuery('#pillar_checkbox_'+rowIds[i]).attr({'disabled':true});
		jQuery('#loss_checkbox_'+rowIds[i]).attr({'disabled':true});

		var categoryVal =jQuery('#pillarGrid').getCell(rowIds[i],"kznCategory");
		var lossVal =jQuery('#pillarGrid').getCell(rowIds[i],"kznLossNo");
		var kznCat=jQuery("#pillarGrid").getCell(rowIds[0],"kznCategory");
		
		if( (categoryVal !=null && categoryVal.trim() != "" && categoryVal.trim().length > 0 )|| lossVal.trim()!=""){
			jQuery("#common_checkbox_"+rowIds[i]).attr('checked','checked');
			jQuery('#pillar_checkbox_'+rowIds[i]).attr({'disabled':false});
			//jQuery('#loss_checkbox_'+rowIds[i]).attr({'disabled':false});
			if((categoryVal !=null && categoryVal.trim() != "" && categoryVal.trim().length > 0 )){
				jQuery('#pillar_checkbox_'+rowIds[i]).attr('checked','checked');
				jQuery('#loss_checkbox_'+rowIds[i]).attr({'disabled':false});
			}
			if((lossVal !=null && lossVal.trim() != "" && lossVal.trim().length > 0 )){
				jQuery('#loss_checkbox_'+rowIds[i]).attr({'disabled':false});
				jQuery('#loss_checkbox_'+rowIds[i]).attr('checked','checked');
			}
		}
		if(jQuery('#mode').val()=="view"||jQuery('#mode').val()=="completion"||jQuery('#hdnFrmActionMode').val()=="Completed")
		{
				jQuery("#common_checkbox_"+rowIds[i]).attr('disabled','disabled');
		}
		/*if ((kznCat.toLowerCase().indexOf("design change/improvement") >= 0) )
			enableFields("chkkznmUtiliseforfuture");
		else{
			jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
			disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
		}*/
	}
}
 
function MultiSelectCancel_CallBack(id)
{
	//jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	//jQuery("#pillarGrid").setCell(id,"txtselectionFlag","DELETE");
	jQuery("#pillarGrid").setCell(id,"kznCategory"," ");
	jQuery("#pillarGrid").setCell(id,"common_checkbox","false");
	if(jQuery('#loss_checkbox_'+id).is(':checked')!=true)
	{
		jQuery('#common_checkbox_'+id).attr('checked',false);
		jQuery('#loss_checkbox_'+id).attr({'disabled':true});
		jQuery('#pillar_checkbox_'+id).attr({'disabled':true});
	}
	jQuery('#pillar_checkbox_'+id).attr('checked',false);
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";
	jQuery("#pillarGrid").setCell(id,"kznCategory"," ");
	jQuery("#pillarGrid").setCell(id,"txtKzplKzncategoryid"," ");
	jQuery("#pillarGrid").setCell(id,"txtselectionFlag",mode);
	
}

function MultiSelectOk_CallBack(id)
{
	//id=jQuery('#hdnpillarId').val();
	//jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	//jQuery("#pillarGrid").setCell(id,"kznCategory"," ");
	//jQuery("#pillarGrid").setCell(id,"common_checkbox","False");
	
}

function pillarGrid_keypress(keycode,iRow,iCol)
{
	var id=iRow;
	if(!(jQuery('#mode').val().trim()=="view"||jQuery('#mode').val().trim()=="completion"||jQuery('#hdnFrmActionMode').val().trim()=="Completed"))
	{	
		if(jQuery('#common_checkbox_'+id).is(':checked')==true)
		{	
			if(keycode == 119 && iCol=="8")
			{
				var pillarId = jQuery("#pillarGrid").getCell(id,"txtKzplTpmpillarid");
				//var catId = jQuery("#pillarGrid").getCell(id,"txtKzplKzncategoryid");
				//	alert("catId ="+catId);
	
				multiSelectPop("imprvCategory_input.kaizen","pillarId%3D"+pillarId, "pillarGrid",id,"txtKzplKzncategoryid,kznCategory",false,"MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Kaizen Pillar");
			
				var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
				if( mode == "INSERT")
					jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
				else if(mode=="VIEW")
					jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");
			}
			
			else if(keycode == 119 && iCol=="10")
			{
				jQuery('#hdnlossLinkclickId').val(id);
				jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","INSERT");
				var rowId=jQuery("#pillarGrid").getCell(id,"kznLossNo");
				multiSelectPop("genLoss_input.kaizen","", "",rowId,"kznLossNo,LosmKeyid",true,"kznLossmultiSelectCancel_CallBack","kznLossmultiSelectOk_Callback","Kaizen Pillar");
			
				var mode = jQuery("#pillarGrid").getCell(id,"txtlossFlag");
			
				if( mode == "INSERT")
					jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","INSERT");
				else if(mode=="VIEW")
					jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","UPDATE");
				
			}
		}
	}
}

function  _multiselectPopUpId_onClose(){
	var id=jQuery('#hdnlossLinkclickId').val();
	var kznLoss=jQuery("#pillarGrid").getCell(id,"kznLossNo");
	if(kznLoss==null || kznLoss=="" || kznLoss==" "){
		jQuery('#loss_checkbox_'+id).attr('checked',false);
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag"," ");
	}
}
function pillarGrid_multiselectPopUpId_onClose()
{
	var id=jQuery('#hdnpillarId').val();
	var kznCat=jQuery("#pillarGrid").getCell(id,"kznCategory");
	/*if ((kznCat.toLowerCase().indexOf("design change/improvement") >= 0) )
		enableFields("chkkznmUtiliseforfuture");
	else{
		jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
		disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	}*/
	if(kznCat==null || kznCat=="" || kznCat==" "){
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
		jQuery('#loss_checkbox_'+id).attr({'disabled':true});
		jQuery('#pillar_checkbox_'+id).attr({'disabled':true});
		jQuery('#pillar_checkbox_'+id).attr('checked',false);
		jQuery('#common_checkbox_'+id).attr('checked',false);
	}
	
}

//----------------------FOR LOSS TYPE-------------------------------//

function losscboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input name="loss_checkbox" id="loss_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){lossLinkSelect(\''+rowId + '\');}else{lossLinkUncheck(\''+ rowId +'\')}"/>';
}

function lossLinkSelect(id)
{
	jQuery('#hdnlossLinkclickId').val(id);
	jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","INSERT");
	multiSelectPop("genLoss_input.kaizen","","","","kznLossNo",true,"kznLossmultiSelectCancel_CallBack","kznLossmultiSelectOk_Callback");
	var mode = jQuery("#pillarGrid").getCell(id,"txtlossFlag");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","INSERT");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","UPDATE");
}

function lossLinkUncheck(id)
{
	var mode = jQuery("#pillarGrid").getCell(id,"txtlossFlag");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";
	jQuery("#pillarGrid").setCell(id,"txtselectLossFlag",mode);
	if(jQuery('#pillar_checkbox_'+id).is(':checked')==true)
	{
		var selectFlag=jQuery("#pillarGrid").getCell(id,"txtselectionFlag");
		if(selectFlag == "VIEW")
			selectFlag ="UPDATE";
		else
			selectFlag = "INSERT";
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag",selectFlag);
	}else
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag",mode);
}

function kznLossmultiSelectOk_Callback(id)
{
	var rowId = jQuery('#hdnlossLinkclickId').val();
	var kznLossNo = "";
	var kznLossKeyId = "";
	var partsArray = id.split(',');
	for(var i=0;i<partsArray.length-1;i++)
	{
		var rowObject = jQuery("#multiSelectGrid").getRowData(partsArray[i]);
		
		kznLossNo+=rowObject.kznLossNo + ',';
		kznLossKeyId+=rowObject.LosmKeyid + ',';
	}
	
	kznLossNo = kznLossNo.slice(0,-1);
	kznLossKeyId=kznLossKeyId.slice(0,-1);
	
	if(kznLossNo.trim()=="" && kznLossKeyId.trim()=="")
	{	kznLossNo=" ";kznLossKeyId=" ";
		var mode = jQuery("#pillarGrid").getCell(jQuery('#hdnlossLinkclickId').val(),"txtlossFlag");
		
		if(mode == "VIEW")
			mode ="DELETE";
		else 
			mode = " ";
		jQuery("#pillarGrid").setCell(jQuery('#hdnlossLinkclickId').val(),"txtselectLossFlag",mode);
	}
	jQuery("#pillarGrid").setCell(rowId,"txtkzllLossid",kznLossKeyId);
	jQuery("#pillarGrid").setCell(rowId,"kznLossNo",kznLossNo);
	var losskey=jQuery("#pillarGrid").getCell(rowId,"txtkzllLossid");
	var lossNo=jQuery("#pillarGrid").getCell(rowId,"kznLossNo");
	if((losskey==""|| losskey ==null || losskey==" ") && (lossNo==""|| lossNo ==null || lossNo==" ")){
		if(jQuery('#pillar_checkbox_'+rowId).is(':checked')!=true)
		{
			jQuery('#common_checkbox_'+rowId).attr('checked',false);
			jQuery('#loss_checkbox_'+rowId).attr({'disabled':true});
			jQuery('#pillar_checkbox_'+rowId).attr({'disabled':true});
		}
		jQuery('#loss_checkbox_'+rowId).attr('checked',false);
	}
}

function kznLossmultiSelectCancel_CallBack(id)
{
	id=jQuery('#hdnpillarId').val();
	jQuery("#pillarGrid").setCell(id,"txtselectLossFlag"," ");
	if(jQuery('#pillar_checkbox_'+id).is(':checked')!=true)
	{
		jQuery('#common_checkbox_'+id).attr('checked',false);
		jQuery('#loss_checkbox_'+id).attr({'disabled':true});
		jQuery('#pillar_checkbox_'+id).attr({'disabled':true});
	}
	var mode = jQuery("#pillarGrid").getCell(id,"txtlossFlag");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";
	jQuery("#pillarGrid").setCell(id,"txtselectLossFlag",mode);
	jQuery('#loss_checkbox_'+id).attr('checked',false);
}


//------------------------------------------------------------//

//-------------------commoncboxFormatter----------------------//
function commoncboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	return '<input name="common_checkbox" id="common_checkbox_'+ rowId +'" '+ (rowObject[3]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){commonSelect(\''+rowId + '\');}else{commonUncheck(\''+ rowId +'\')}"/>';
}

function commonSelect(id)
{
	jQuery('#hdnpillarId').val(id);
	jQuery('#pillar_checkbox_'+id).attr({'disabled':false});
	//jQuery('#loss_checkbox_'+id).attr({'disabled':false});
	if(jQuery("#pillarGrid").getCell(id,"txtDbMode") == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");
	else
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
}

function commonUncheck(id)
{
	jQuery('#hdnpillarId').val('');
	
	jQuery("#pillarGrid").setCell(id,"kznCategory"," ");
	jQuery("#pillarGrid").setCell(id,"kznLossNo"," ");
	jQuery('#pillar_checkbox_'+id).attr('checked',false);
	jQuery('#loss_checkbox_'+id).attr('checked',false);
	jQuery('#pillar_checkbox_'+id).attr({'disabled':true});
	jQuery('#loss_checkbox_'+id).attr({'disabled':true});
	
	var pillarMode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if( pillarMode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	else if(pillarMode == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","DELETE");


	var mode = jQuery("#pillarGrid").getCell(id,"txtlossFlag");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag"," ");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectLossFlag","DELETE");
	
}
//-----------------------------------------------------------//




/** **/
 function kznHDGrid_OnGridload()
 {
	 getHdCount();
 }

 function kaizenOnload()
 {
	// resultImage();
	 //afterImage();
	 //presentImage();
	 if(!(jQuery('#chkHdRequiredY').is(':checked')))
	 {	  jQuery("#btnnavigateHd").attr('disabled','disabled');
	 		disableUIButton('btnnavigateHd');
	  	  jQuery('#chkHdRequiredN').attr('checked','checked');
	 }else
		 enableUIButton('btnnavigateHd');
	 if(!(jQuery('#chkIdeagroupindividualG').is(':checked')))
	 {
		 jQuery("#individualImg").show();
		 jQuery('#refType').addClass("refTypepadding");
	  	 jQuery('#chkIdeagroupindividualI').attr('checked','checked');
	  	 if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
			{
				processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
			}
	 }
	  if(!(jQuery('#chkWoRequiredR').is(':checked')))
		  jQuery('#chkWoRequiredS').attr('checked','checked');
	  
 }
function getEmpImgSuccess(result){
	/*if(result.empImg.Data == true && result.empImg.Data != 'undefined'){
		jQuery('#imgEmpIndividual').attr('src','');
		jQuery('#imgEmpIndividual').attr('src',result.empImg.imgToimBlobimage);
	}*/
	
	if(result.empImg.Data == false ){
		jQuery('#imgEmpIndividual').attr('src',"images/EmpDefaultImg.jpg");
	}else{
		var imgval=result.empImg.imgToimBlobimage;
		if(result.empImg.Data == true ){
			jQuery('#imgEmpIndividual').attr('src',result.empImg.imgToimBlobimage);
		}
	}
}
function getEmpImgErr(result){
	//alert("end");
}
 function tickOtherCheckbx(checkBoxId,checkBoxId1)
 {
 	jQuery("#"+checkBoxId).attr({'checked':true});
 	jQuery("#"+checkBoxId1).attr({'checked':false});
 
 }

jQuery('#chkLineGraph').click(function()
{
	tickOtherCheckbx('chkLineGraph','chkBarGraph');
});

jQuery('#chkBarGraph').click(function()
{
	tickOtherCheckbx('chkBarGraph','chkLineGraph');
});

jQuery('#chkProviding').click(function(){
	tickOtherCheckbx('chkProviding','chkChanging');
});

jQuery('#chkChanging').click(function(){
	tickOtherCheckbx('chkChanging','chkProviding');
});

jQuery('#chkReversible').click(function(){
	tickOtherCheckbx('chkReversible','chkIrreversible');
});

jQuery('#chkIrreversible').click(function(){
	tickOtherCheckbx('chkIrreversible','chkReversible');
});

jQuery('#chkHdRequiredN').click(function(){
	tickOtherCheckbx('chkHdRequiredN','chkHdRequiredY');
	jQuery("#btnnavigateHd").removeAttr('class', 'easyui-button');
    jQuery("#btnnavigateHd").attr('disabled','disabled');
});

jQuery('#chkHdRequiredY').click(function(){
	tickOtherCheckbx('chkHdRequiredY','chkHdRequiredN');
	//jQuery('#btnnavigateHd').attr({'disabled':true});
	jQuery("#btnnavigateHd").attr('class', 'easyui-button');
	jQuery("#btnnavigateHd").removeAttr('disabled');
});

jQuery('#chkIdeagroupindividualI').click(function(){
	tickOtherCheckbx('chkIdeagroupindividualI','chkIdeagroupindividualG');
	if(jQuery("#chkIdeagroupindividualI").is(':checked') == true)
	{
		 if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
		{
			processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
		} 
		jQuery("#individualImg").show();
		jQuery('#refType').addClass("refTypepadding");
	}
	else{
		jQuery("#individualImg").hide();
		jQuery('#refType').removeClass("refTypepadding");
	}
});
function  frmImprovementPrjcmbkznmPreparedid_onSelect(record)
{
	if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
	{
		processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
	}
}
jQuery('#chkIdeagroupindividualG').click(function(){
	tickOtherCheckbx('chkIdeagroupindividualG','chkIdeagroupindividualI');
	if(jQuery("#chkIdeagroupindividualI").is(':checked') == true)
	{
		 if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
		{
			processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
		} 
		jQuery("#individualImg").show();
		jQuery('#refType').addClass("refTypepadding");
	}
	else{
		jQuery("#individualImg").hide();
		jQuery('#refType').removeClass("refTypepadding");
	}
});
jQuery('#chkWoRequiredR').click(function(){
	tickOtherCheckbx('chkWoRequiredR','chkWoRequiredS');
});

jQuery('#chkWoRequiredS').click(function(){
	tickOtherCheckbx('chkWoRequiredS','chkWoRequiredR');
});

jQuery('#txtkznmMaterialcost').keyup(function() {
	CalculateCost();		
});
jQuery('#txtkznmLabourcost').keyup(function() {
	CalculateCost();
});

function CalculateCost()
{
	var matCost = jQuery('#txtkznmMaterialcost').val();
	var labCost = jQuery('#txtkznmLabourcost').val();
	var totalCost= parseFloat(matCost) + parseFloat(labCost);
		jQuery('#txttotalcost').val(totalCost);
		if(jQuery('#txtkznmMaterialcost').val().trim()=="")
			jQuery('#txttotalcost').val(labCost);
		else if(jQuery('#txtkznmLabourcost').val().trim()=="")
			jQuery('#txttotalcost').val(matCost);
}

jQuery('#txtKznmBenefitvalue').keyup(function() {
	validateBTS();
});
function validateBTS(){
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cboKznmBenefittype').val();
	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be less than 500000");
		return false;
	}
}
jQuery('#txtKznmCostperequipment').keyup(function() {
	CalculateBTS();
});
jQuery('#txtKznmCostperhour').keyup(function() {
	CalculateBTS();		
});
function CalculateBTS()
{
	var matCost = jQuery('#txtKznmCostperhour').val();
	var labCost = jQuery('#txtKznmCostperequipment').val();
	var totalCost= parseFloat(matCost) + parseFloat(labCost);
		jQuery('#txtBTSTotalcost').val(totalCost);
		if(jQuery('#txtKznmCostperhour').val().trim()=="")
			jQuery('#txtBTSTotalcost').val(labCost);
		else if(jQuery('#txtKznmCostperequipment').val().trim()=="")
			jQuery('#txtBTSTotalcost').val(matCost);
}


function getHdCount()
{
	var hdCount=jQuery("#kznHDGrid").getGridParam('reccount'); 
	jQuery('#txtkznmNoofhds').val(hdCount);
}

jQuery('#btnnavigateHd').click(function()
{
	if(jQuery('#chkHdRequiredY').is(':checked')==true)
		saveForm('frmImprovementPrj','horizontalDeplymnt.kaizen');
	
});
	
jQuery('#btnImprvCat').click(function()
{
	LoadPopUp("divCatCreate","kaizenCategory.kaizen?q=2",true,"92%","75%","1%","1%","","Kaizen Category");
	//saveForm('frmImprovementPrj','kaizenCategory.kaizen');
});

jQuery('#btnExcelVw').click(function()
{
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	if(kaizenId != null && kaizenId.length > 0)
		window.open("ImpprojSht_view.ipsrpt?kaizenId="+kaizenId);
});

//----------------CODE FOR KZN GRAPHDATA--------------//
function divCatCreate_onClose(){
	var row=jQuery("#pillarGrid").jqGrid('getDataIDs');	
	for(var i=0;i<row.length;i++){
		id=row[i];
		if(jQuery('#common_checkbox_'+id).is(':checked')==true){
			jQuery('#pillar_checkbox_'+id).attr({'disabled':false});
			if(jQuery('#pillar_checkbox_'+id).is(':checked')==true){
				jQuery('#loss_checkbox_'+id).attr({'disabled':false});
			}else
				jQuery('#loss_checkbox_'+id).attr({'disabled':true});
		}else{
			jQuery('#loss_checkbox_'+id).attr({'disabled':true});
			jQuery('#pillar_checkbox_'+id).attr({'disabled':true});
			jQuery('#pillar_checkbox_'+id).attr('checked',false);
		}
		
	}
	
	return true;
}
function dteRtomonth_onSelectMonth(date)
{
	compareMonths('dteRfrommonth','dteRtomonth');
}

function dteRfrommonth_onSelectMonth(date)
{
	compareMonths('dteRfrommonth','dteRtomonth');
}

jQuery('#btnInsert').click(function(){
	var fromMonth=jQuery('#dteRfrommonth').datebox('getValue');
	var toMonth =jQuery('#dteRtomonth').datebox('getValue');
	var kaizenId=jQuery('#cmbkznmKeyid').combobox('getValue');
	//clearValidationErrorMsg(toDateId);	
	processGridnew("kznGraphData_input.kaizen","?fromMonth="+fromMonth+"&toMonth="+toMonth+"&isRequest=true&kaizenId="+kaizenId,"khzGraphDataGrid","khzGraphDataPager","","","","kznGraphDataOnload");
});
jQuery("#btnViewWhyWhy").click(function(){
	 var keyid=jQuery("#hdnkznKeyid").val();
	navigateToNextForm("whywhyanalysis_input.why?q=1&kznKeyid="+keyid,"Why Why Analysis");
});
jQuery("#btnimpActionplan").click(function(){

	 var hdnkznKeyid=jQuery("#hdnkznKeyid").val();
	 if(hdnkznKeyid.trim().length>0)
    { 
		 var keyid=jQuery("#hdnkznKeyid").val();
		 //alert("keyid::keyid"+keyid);
		 var flid=jQuery("#flid").val();
		 //alert("flid::flid"+flid);
		 openActionPlan("impActionPlan",keyid,"Kzn",flid);
    }
	 else
	{	 
	
	saveForm('frmImprovementPrj','kaizen_save.kaizen?openactnpln=openactnpln');
	
	}
	 
 });

jQuery('#btnClear').click(function(){
	processGridnew("kznGraphData_input.kaizen","?row=2","khzGraphDataGrid","khzGraphDataPager","","","","kznGraphDataOnload");
});

function kznGraphDataOnload()
{
	if(!(jQuery('#mode').val().trim()=="view"||jQuery('#mode').val().trim()=="completion"||jQuery('#hdnFrmActionMode').val().trim()=="Completed"))
	{
		var graphType=jQuery("#khzGraphDataGrid").getCell(1,"txtkzgdCharttype");
		if(graphType=="L")
			{jQuery('#chkLineGraph').attr("checked","checked");jQuery('#chkBarGraph').attr("checked",false);}
		else if(graphType="B")
			{jQuery('#chkBarGraph').attr("checked","checked");jQuery('#chkLineGraph').attr("checked",false);}
			
		jQuery("#khzGraphDataGrid").setGridParam({
			
			afterEditCell: function(rowid, name, value, iRow, iCol) {	
				 var graphData = jQuery('#' + (iRow) + '_' + name);	
				 //graphData,0,context,selector
				 var cnt=0;
				 graphData.keydown(function(event)
				 {	
					  if (event.keyCode == 46 || event.keyCode == 8  || event.keyCode == 9 ) {
			                // let it happen, don't do anything
			                if(event.keyCode==8)
			                	cnt--;
		                	if(event.keyCode == 46)
			                	cnt=0;
			            }
			            else {
			                // Ensure that it is a number and stop the keypress
			                	if (((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 96 && event.keyCode <= 105))&& cnt <=8) {
				                	cnt++;
			               	 }
			                else {
			                    event.preventDefault();
			                }
			            }
					 // jQuery(this.jqGrid('setGridParam', {cellEdit: false}));		            
				 });//.bind('focusout', function (e) {
                	// jQuery("#khzGraphDataGrid").jqGrid('setGridParam', {cellEdit: true});
                
                	// jQuery("#khzGraphDataGrid").jqGrid('restoreCell', iRow, iCol, true);
                	// jQuery("#khzGraphDataGrid").jqGrid('setGridParam', {cellEdit: false});
               
				// });
			}
		});
	}
	else
		jQuery('#khzGraphDataGrid').jqGrid('setGridParam', {cellEdit: false});
}

function IsNumeric(input)
{
    return (input - 0) == input && input.length > 0;
}



function convertToJSONGraphArr(jqGridId){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var beforeRow = allRows[0];
	var afterRow = allRows[1];
	var colCnt = 0;
	var forboth=0;
	var jsonArrO='[';
	
	for(var colName in beforeRow) {
		if(beforeRow[colName].trim()=="0" && afterRow[colName].trim() =="0")
		{
			forboth++;
		}
		
		if( colCnt++ > 1){
			jsonArrO += '{';
			jsonArrO += '"'+"txtkzgdDatemonthyear" +'":"'+"01-" + colName +'",'; 
			
			if(beforeRow[colName].substring(0,6)!='<input')
			{//alert('inside');
				jsonArrO += '"'+colName +'":"' + beforeRow[colName].replace(/(\r\n|\n|\r)/gm,"") +'",'; 
			}
			else
			{
				//alert("else");
				var x=beforeRow[colName].indexOf("id=")+4;
				//alert("x="+x);
				var y=beforeRow[colName].substring(x);
				var z = y.indexOf('"');					
				jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val().replace(/(\r\n|\n|\r)/gm,"") +'",'; 
			}
			if(afterRow[colName].substring(0,6)!='<input')
			{//alert('inside');
				jsonArrO += '"'+colName +'":"' + afterRow[colName].replace(/(\r\n|\n|\r)/gm,"") +'",'; 
			}
			else
			{
				//alert("else");
				var x=afterRow[colName].indexOf("id=")+4;
				//alert("x="+x);
				var y=afterRow[colName].substring(x);
				var z = y.indexOf('"');					
				jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val().replace(/(\r\n|\n|\r)/gm,"") +'",'; 
			}
			
			jsonArrO += '"'+"txtkzgdBeforedata" +'":"' + beforeRow[colName] +'",';
			jsonArrO += '"'+"txtkzgdAfterdata" +'":"' + afterRow[colName] +'",';
			if(jQuery('#chkBarGraph').is(':checked')==true)
				jsonArrO += '"'+"txtkzgdCharttype" +'":"'+"B"+'",'; 
			else if(jQuery('#chkLineGraph').is(':checked')==true)
				jsonArrO += '"'+"txtkzgdCharttype" +'":"' + "L"+'",';
			else	
			   jsonArrO += '"'+"txtkzgdCharttype" +'":"' + beforeRow.txtkzgdCharttype +'",'; 
			
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		} 
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	if(forboth > 0)
		return "";
	else
		return jsonArrO;
}

function compareMonths(fromDateId,toDateId)
{
	var fromMonth = jQuery('#dteRfrommonth').datebox('getValue') ;
	var toMonth = jQuery('#dteRtomonth').datebox('getValue') ;

	var fromMon=changeFormatStringtoNumber(fromMonth.substring(0, 3))+1;
	var toMon=changeFormatStringtoNumber(toMonth.substring(0, 3))+1;
    toMonth='01-'+toMonth;
    fromMonth='01-'+fromMonth;
    var currentTime = getServerDateTime();

    var a = fromMon+'-'+fromMonth.substring(7,12);
    var b = toMon+'-'+toMonth.substring(7, 12);
    a = a.split('-');
    b = b.split('-');
    months = (b[1]-a[1]) * 12 + (b[0]-a[0]);
    
	if(convertStringToDate(toMonth) > currentTime)
	{
		showValidationErrorMsg(toDateId,'To Month should Not Exceed Current Month');
        fillWithCurrentMonth(toDateId);			
		fillWithCurrentMonth(toTimeId);
		
	}
	else if( compareDateTime(toMonth,fromMonth) > 0 )
    {
	   	showValidationErrorMsg(toDateId,"To Month Cannot be Less Than From Month");
    	fillWithCurrentMonth(toDateId);				
    	fillWithCurrentMonth(fromDateId);		
    }
	else if(months > 12)
	{
		showValidationErrorMsg(toDateId,"Selected month should not be greater than 12 months");
		fillWithCurrentMonth(toDateId);				
   	 	fillWithCurrentMonth(fromDateId);
	}
    
	else
    	clearValidationErrorMsg(toDateId);	
	
}
function getSelectType(value){
	var mode=jQuery("#mode").val();
	jQuery("#txtKznmBenefitvalue").val('');
	if(value=="NS"){
		jQuery("#btnkpi").hide();
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','5');
		jQuery("#spninrbox").css('padding-left','0');
	
		}
	else if(value=="GE5"){
		jQuery("#btnkpi").show();
		if(mode!="view" && mode !="completion")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','20');
		jQuery("#inid").text('(Lakhs)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','10');
		}
	else if(value=="LE5"){   
		jQuery("#txtKznmBenefitvalue").attr('maxlength','6');
		jQuery("#btnkpi").hide();
		if(mode!="view" && mode !="completion")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(Lakhs)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','6');
		}
	else{
		jQuery("#btnkpi").hide(); 
		if(mode!="view" && mode !="completion")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','10');
		}
	var benselval=jQuery("#cboKznmBenefittype").val();
	var benTypeVal="";
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	
		if(benselval=="GE5")
			benTypeVal="BTSG5L";
		else if(benselval=="NS")
			benTypeVal="BTSNOSAVING";
		else if(benselval=="S")
			benTypeVal="BTSSAFETY";
		else if(benselval=="LE5")
			benTypeVal="BTSL5L";
			workFlow("divKznWorkFlow",false,benTypeVal, kaizenId, "KZNBTS");
}
function gotFocus(id){ 	
	numericTextBox(id);
}
function openKPIPOP(){
	//LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?from=mom&pillar="+pillarCode+"&kpikeyid="+kpikeyid,true, "38%", "75%", "0px", "40%", "multiSelectOk_Callback","KPI Indicator", false);
	LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk?from=BTS&pillar=EM",true, "38%", "75%", "0px", "40%", "multiSelectOk_Callback","KPI Indicator", false);
}
function multiSelectOk_Callback(){
	//alert("ok");
}
function chkboxCheck(id){
	var count=0;
	if(jQuery("#chkResultAreaP").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaQ").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaS").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaC").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaD").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaM").is(':checked') == true)		
		count++;
	if(jQuery("#chkResultAreaE").is(':checked') == true)		
		count++;

	if(count>2)
	{
		jQuery("#"+id).attr('checked',false);
		alert("Select Maximum 2");
		return false;
	}
	
}
</script> 
<!--end of grid-->
<form name="frmImprovementPrj" id="frmImprovementPrj" action="kaizen_create.kaizen" method="post">
 <div id="wrapper" style="width:90%"> 
<div id ="mainborder"; align="center"  class="main-cntborder" style="width: 1100;margin-left:0px;" >
	

<!--left  pane -->
     <table width=80% >
        <tr>
        	<td style="width:60%;padding-left:50px;padding-left:0px\9;" valign='top'>
        		<div  class="easyui-paddingbfpx" ><label>Kaizen No</label> </div>
						
							<div class="easyui-paddingbfpx" >
								<span><input id="cmbkznmKeyid" name="cmbkznmKeyid" class="easyui-combobox"  style="width:135px;" value="${requestScope.kznTlMst.kznmKeyid}" disabled="${requestScope.kaizenFormBean.disableImprvNo}" /></span>
								<span  style="padding-left: 15px;">
								<span style="padding-right: 5px;">
										<input type="checkbox" id="chkkznmUtiliseforfuture" name="chkkznmUtiliseforfuture" <c:out value = "${ requestScope.kznTlMst.kznmUtiliseforfuture == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
								</span>
								<span><label>Utilize For Future Projects</label></span></span>
							</div>
		 
        	</td>
        	<td style="width: 40%;">
        	
	        	<!--<span id="viewWhywhy" style="margin-left: 10px;position:absolute;">
	        		 <input type="button" id="btnViewWhyWhy" name="btnViewWhyWhy" class="easyui-button" style="width:150px; height: 23px" value="View Why Why Analysis" />
	        	</span>
	        	-->
	        	<div style="margin-left:8%;">
		        	<table>
		        		<tr>
		        			<td>
		        				<span style="margin-left:10px;"><input type="text" id="txtStatus"  class="easyui-text" style="display:none;width:100px; font-size:13px;font-weight:bold; text-align:center;" /></span>
		        			</td>
		        			<td>
		        				<span  style="margin-left:10px;" ><input class="easyui-button" type="button"  value ="Excel View" id="btnExcelVw" style="height:22px"/></span>
		        			</td>
		        			<td>
		        				<span id="abnactnplan" style="margin-left:10px;">
								   <input type="button" id="btnimpActionplan" name="btnimpActionplan" class="easyui-button" style="width:80px; height: 23px" value="Action Plan" />
								</span>
		        			</td>
		        			<td>
		        				<span id="viewKznEva"   style='margin-left:10px;'><input type="button" class="easyui-button" id="btnKaizenEvaluation" name="btnKaizenEvaluation" value="Kaizen Evaluation" style="height: 25px; width : 110px;margin-left:10px;"/></span>
		        			</td>
		        			<td>
					        	<div  class="easyui-paddingbfpx" style="padding-top: 10;position:relative;">
									<span id="impFilemgr" style="position:absolute;left:10px;top:-6px;">
									 <!-- <input class="easyui-button" id="btnFilManage" type="button" value="File Manager" style=" height : 21px;"> </input> -->
									</span>
					        	</div> 
					        </td>
		        		</tr>
		        	</table>
	        	</div>
        	</td>
        	
        </tr>
        <tr>
        <td colspan="3">
        <div  id="frmImprovementPrjFuntKeyIds" >
			<input type="hidden" id="factory" name="cmbKznmFactoryid" value="${requestScope.kznTlMst.kznmFactoryid}"></input>
			<input type="hidden" id="section" name="cmbKznmSectionid" value="${requestScope.kznTlMst.kznmSectionid}"></input>
			<input type="hidden" id="cell" name="cmbKznmCellid" value="${requestScope.kznTlMst.kznmCellid}"></input>
			<input type="hidden" id="machine" name="cmbKznmMachineid1" value="${requestScope.kznTlMst.kznmMachineid}"></input>
			<input type="hidden" id="flid" name="cmbKznmFlid" value="${requestScope.kznTlMst.kznmFlid}"  ></input>
			</div>
							
								
			<div id="kznmfunLocation" style="width: 150%;width: 174%\9;padding-left:50px;padding-left:0px\9;" ></div>
			</td>
        </tr>
        <tr >
			<td style="width:70%;padding-left:50px;padding-left:0px\9;" valign='top'  class="easyui-paddingbfpx cntborder" >
		 	
			
							<div class="sub-header" style="width : 330px; " ><label>Kaizen</label></div>
						<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Requested Date</label></div>
		                  	<div class="easyui-paddingbfpx">
		                		<input id="dtekznmDate" name="dtekznmDate" class="easyui-datebox" style="width:155px;" value="${requestScope.kznTlMst.kznmDate}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />  
							</div>
							
							<div class="sub-header" style="width : 330px; " ><label> Details</label></div>
							
					<div class="easyui-paddingbfpx" ><label>Cost Center</label></div>
							 <div class="easyui-paddingbfpx">
		                		<input id="cmbKznmCostcentreid" name="cmbKznmCostcentreid" class="easyui-combobox"  style="width:318px;" value="${requestScope.kznTlMst.kznmCostcentreid}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
							</div>
						
			             <div id="rtdtomld" style="width: 330px\9">
			 				<div>
			  					<span> <label class="mandatory-lbl"> Related to</label></span>
						 		<span> <label id="lblMld" style="padding-left:52px;">Mould</label></span>
							 </div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
		                		<select id="cmbkznmRelatedto" class="easyui-combobox" name="cmbkznmRelatedto"  style="height: 22px;width:105px;"<c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>
									<option value="MCH">MACHINE</option>
		   		 					<option value="MLD">MOULD</option>							
							    </select> 
							    <input id="relatedToCMB" name="relatedToCMB" type="hidden" value="${requestScope.kznTlMst.kznmRelatedto }"/>
								<input type="text" id="cmbkznmMouldid"  name="cmbkznmMouldid" class="easyui-combobox"  value="${requestScope.kznTlMst.kznmMouldid}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> style="width: 195px;"/>
			
							</div>	
						</div>	
							     <div id="err_cmbkznmMouldid" class="tpm-errormsg" style="display: block;padding-left: 110px;margin-top: 5px;"></div>
							<div class="easyui-paddingbfpx"><label>Equipment</label></div>
							<div class="easyui-paddingbfpx">
		                		<input id="cmbkznmMachineid" name="cmbkznmMachineid" class="easyui-combobox"  style="width:318px;" value="${requestScope.kznTlMst.kznmMachineid}"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />
								</div>
							<div class="easyui-paddingbfpx" ><label>Circle</label></div>
							 <div class="easyui-paddingbfpx">
		                		<input id="cmbkznmCircleid" name="cmbkznmCircleid" class="easyui-combobox"  style="width:318px;" value="${requestScope.kznTlMst.kznmCircleid}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
							</div>
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Theme</label> </div>
                 	 		<div class="easyui-paddingbfpx">
                				<textarea id="txtkznmTheme" name="txtkznmTheme"  rows="4" cols="36" maxlength="250" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
							</div>
					
					<div class="easyui-paddingbfpx" ><label>Operation</label></div>
                 	 <div class="easyui-paddingbfpx">
                		<input  class="easyui-text" id="txtkznmOperations" name="txtkznmOperations"  maxlength="250" style="width:318px;" value="${requestScope.kznTlMst.kznmOperations}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>
					</div>
					
					
					
					<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Benchmark</label>
					<span class="easyui-paddingbfpx" style="padding-left:90px;"><label class="mandatory-lbl"> Target</label></span></div>
						<div class="easyui-paddingbfpx"> 
                			<input  class="easyui-text" id="txtkznmBenchmark" name="txtkznmBenchmark" maxlength="30" style="width:156px;" value="${requestScope.kznTlMst.kznmBenchmark} " <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/> 
                  			<input  class="easyui-text" id="txtkznmTarget" name="txtkznmTarget" maxlength="30" style="width:156px;" value="${requestScope.kznTlMst.kznmTarget}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>

                   			<table>
                   		<tr>
                   		<td>
                   <span id="err_txtkznmBenchmark" class="tpm-errormsg floatleft" style="display: block;width:156px; "></span>
                   </td>
                   <td>
                   <span id="err_txtkznmTarget" class="tpm-errormsg floatright" style="display: block;width:156px;"></span>
                   
                   </td>
                   </tr>
                   </table>
                   		
                   		</div>
                   	
                   		
					<div class="easyui-paddingbfpx" style="width:340px;"><label class="mandatory-lbl">Start Date</label><span style="padding-left:105px;"><label class="mandatory-lbl">End Date</label></span></div> 
						 <div class="easyui-paddingbfpx"> 
                			<span style="padding-right: 45px;"> <input id="dtekznmStartdate" name="dtekznmStartdate" class="easyui-datebox" style="width:110px;" value="${requestScope.kznTlMst.kznmStartdate}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/> </span>
                  			<span > 
                  				<input id="dtekznmEnddate" name="dtekznmEnddate" class="easyui-datebox" style="width:110px;" value="${requestScope.kznTlMst.kznmEnddate}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
                  			</span> 
                  		</div>	
              <div class="sub-header" style=" width : 330px; margin-bottom:10px; " ><label>Team Members</label></div>
					
		          <div class="easyui-paddingbfpx"> 
		            <textarea id="txtkznmTeammembers" name="txtkznmTeammembers" rows="4" cols="36" maxlength="150" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTeammembers}</textarea>
				  </div>	
				<div class="easyui-paddingbfpx" ><label class="mandatory-lbl">Requested By</label></div>
                 	 <div class="easyui-paddingbfpx">
		                <input id="cmbkznmPreparedid" name="cmbkznmPreparedid" class="easyui-combobox"  style="width:318px;" value="${requestScope.kznTlMst.kznmPreparedid}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> >
					</div>
					<div>	
					<div class="easyui-paddingbfpx">
					<input id="chkIdeagroupindividualI" name="chkIdeagroupindividualI" type="checkbox" value="I" <c:out value = "${ requestScope.kaizenFormBean.ideagroupindividualI == 'I' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>  /><span Style="padding-left: 5px;"><label>Individual  </label></span>
					</div>
					<div class="easyui-paddingbfpx" >
					<input id="chkIdeagroupindividualG" name="chkIdeagroupindividualG" type="checkbox" value="G" <c:out value = "${ requestScope.kaizenFormBean.ideagroupindividualG == 'G' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>  /><span Style="padding-left:5px;"><label> Group Idea</label> </span>
					</div>	
					</div>
					<div id="individualImg" style="position: relative;top:-45px;">
						<div style="position: absolute;left:150px; ">
							<img id="imgEmpIndividual" name="imgEmpIndividual" src="images/EmpDefaultImg.jpg" width="100px" height="100px"/>
							<!--<span style="position:absolute;bottom:-20px;left:0px;top:100px;"><input type="button" class="easyui-button" id="btnimgEmpAdd" name="imgEmpAdd" value="+" style="width:25px;"/></span>
							<span style="position:absolute;bottom:-20px;left:76px;top:100px;"><input type="button" class="easyui-button" id="btnimgEmpDel" name="imgEmpDel" value="-" style="width:25px;"/></span>-->
						</div>
					</div>
				<div id="refType" class="easyui-paddingbfpx"><label>Ref Type</label><span class="lblr" style="padding-left:110px;"><label>Reference No</label></span></div> 
				<div class="easyui-paddingbfpx"> 
                			 <input class="easyui-text" type="text" id="txtkznmRefdoctype" name="txtkznmRefdoctype" maxlength="15"  style="width:156px;" value="${requestScope.kznTlMst.kznmRefdoctype}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>> 
                  <span class="floatR2"> 
                  <input class="easyui-text" type="text" id="txtkznmRefdocno" name="txtkznmRefdocno" maxlength="20" value="${requestScope.kznTlMst.kznmRefdocno}" style="width:156px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>
                  </span> </div>	
				<div class="easyui-paddingbfpx">
					<span style="padding-right: 5px;"><input type="checkbox" id="chkkznmIsworthformp" name="chkkznmIsworthformp"<c:out value = "${ requestScope.kznTlMst.kznmIsworthformp == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> /></span>
					<span><label>MP Worthy</label></span>
					
				</div>
				<div id ="kznapplswodiv";style="display: none;">
				<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Approved By</label><span class="lblr" style="padding-left:90px;"><label class="mandatory-lbl">Date</label></span></div> 
				<div class="easyui-paddingbfpx"> 
                			 <input class="easyui-text" type="text" id="cmbkznmApprovedBy" name="cmbkznmApprovedBy" maxlength="15"  style="width:156px;" value="" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>> 
                  <span class="floatR2"> 
                  <input class="easyui-text" type="text" id="dtekznmDateadd" name="dtekznmDateadd" maxlength="20" value="" style="width:156px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>
                  </span> 
                  </div>
                  <div class="easyui-paddingbfpx"><label>Remarks</label></div>
                    <div class="easyui-paddingbfpx"> 
		            <textarea id="txtkznmRemarks" name="txtkznmRemarks" rows="4" cols="36" maxlength="150" ></textarea>
				  </div>
				  </div>
				
			</td>
		<!--Right  pane -->
			<td valign='top' class="cntborder" style="width:30%;" class="easyui-paddingbfpx">
			
			
				<div id="ktab" style="padding-left:40px;width : 600px; height : 780px;margin-top: 0px;">
					<div  id="tabKaizen" class="easyui-tabs" fit="true" plain="true" >
						<div title="Present"  style="padding:10px;" >
							
							
		                 	 <div class="easyui-paddingbfpx"> 
		                		<textarea id="txtkznmPresentproblem" name="txtkznmPresentproblem" rows="4" cols="28" maxlength="250"  style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>
							</div>
							<div class="sub-header "><label>Present Condition</label><span style="padding-left:5px;font-size:9px;">width:8.1 cm height:3.8 cm</span><span style="padding-left:0px;height:20px">
						 	<input class="easyui-button" type="button" value ="Image" id="dlgImgPresent"style="height:20px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>  
							
							<input class="easyui-button" type="button" value ="Clear" id="btnImgPresentClear"style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span></div>
							<div id="divImgPresent" style="padding-top:20px;padding-left:135px; ">
							<div style="width: 307px;height:144px;" class="sub-cntborder" align="center">
								<span ><img alt=""  id="imgKznmPresentimage" name="imgKznmPresentimage" src="${requestScope.kznTlMst.kznmPresentimage}" ></span>
							</div>
							</div>
							
						</div>
						
						<div title="Analysis" style="padding:10px;">
						<div class="easyui-paddingbfpx mndlbl" ><label>Analysis</label></div> 
	                 	 <div class="easyui-paddingbfpx"> 
			                <input id="cmbkznmWwmsKeyid" name="cmbkznmWwmsKeyid" class="easyui-combobox"  style="width:255px;" value="${requestScope.kznTlMst.kznmWwmsKeyid}" disabled="${requestScope.kaizenFormBean.disablewwmsKeyid}" >
			                <input class="easyui-button " type="button" id="btnKznWhyWhy" name="btnKznWhyWhy" value ="..."  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
						</div>

					<!---------	For grid ----->
						<table id="kznYYGrid" style="width:50%"><tr><td/></tr>
						</table>
						<div id="kznYYPager"></div>
					<!-----------end of grid-------->
		
					<div class="sub-header" ><label>Root Cause</label></div>
					<div class="easyui-paddingbfpx"> 
	                		<textarea id="txtkznmRootcause" name="txtkznmRootcause" maxlength="250" rows="4" cols="28" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmRootcause}</textarea>
						</div>
						<div class="sub-header" >Idea</div>
					<div class="easyui-paddingbfpx"> 
	                		<textarea id="txtkznmIdea" name="txtkznmIdea" rows="4" cols="28" maxlength="250" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmIdea}</textarea>
						</div>
						
						</div>
						
						
						<div title="After" style="padding:10px;">
						<div class="sub-header"  > Counter Measure</div>
						
	                 	 <div class="easyui-paddingbfpx"> 
	                		<textarea id="txtkznmCountermeasure" name="txtkznmCountermeasure" maxlength="250" rows="4" cols="28" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmCountermeasure}</textarea>
						</div>
						<div class="sub-header "  > After Condition <span style=" padding-left:5px;font-size:9px;height:20px;">width:8.1 cm height:3.8 cm
						<input class="easyui-button" type="button" value ="Image" id="dlgImgAfter"style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
						<input class="easyui-button" type="button" value ="Clear" id="btnImgAfterClear" onclick="" style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span></div>
						<div style="padding-top:20px;">
						<div align="center"  class="main-cntborder" style="width: 307px;height: 144px; ">
							<img alt="" id="imgkznmAfterimage" name="imgkznmAfterimage" src="${requestScope.kznTlMst.kznmAfterimage}"></div>
						</div>
						</div>
						<div title="Results" style="padding:10px;">
							<div class="sub-header "  > Result Description<span style="float:right;">
						<!-- 	<input class="easyui-button" type="button" value ="Export to excel" id="Exprttoexl"/>--></span> </div>
						
	                 	 <div  class="easyui-paddingbfpx"> 
	                		<textarea id="txtkznmResultdescription" name="txtkznmResultdescription" rows="4" cols="28" maxlength="250" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmResultdescription}</textarea>
						</div>
						<div class="sub-header "  > Result Image 
						 <span style="padding-left:5px;font-size:9px;">width:8.1 cm height:3.8 cm<span style="padding-left:10px;height:20px">
						 <input class="easyui-button" type="button" value ="Image" id="dlgImgResult" onclick="" style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
						 <input class="easyui-button" type="button" value ="Clear" id="btnImgResultClear" style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> /></span></span>
						</div>
						<div style="padding-top:10px;">
						<div style="padding-right: 30px;">
						</div>
							<div align="center"  class="main-cntborder" style="width: 307px;height: 144px; ">
							<img alt="" id="imgkznmResultimage" name="imgkznmResultimage"  src="${requestScope.kznTlMst.kznmResultimage}" />
							</div>
						</div>
						<div class="easyui-paddingbfpx"><br></br>
						<div class="sub-header" > Result Area </div>
							<div class="easyui-paddingbfpx "><label>From Month</label><span class="lblr" style='padding-left: 55px;'>To  Month</span></div> 
						 <div >
				              <input id="dteRfrommonth" name="dteRfrommonth" class="easyui-datebox" value="${requestScope.kznTlMst.kznmDate}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			                  <input id="dteRtomonth" name="dteRtomonth" class="easyui-datebox" value="${requestScope.kznTlMst.kznmDate}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			                  <input id="btnInsert" value="Insert"  type="button" class="easyui-button" width="50px" style="height:20px"<c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			                  <input id="btnClear" value ="Clear" type="button" class="easyui-button"  width="50px" style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			              </div>
			              <div id="err_dteRtomonth" class="tpm-errormsg"></div>
			                  <div class="sub-header"> Enter Values on data row <span style="float:right;margin-top:-15px\9;">
			                   <span style="padding-left:10px;"><input type="checkbox" value ="LineGraph" id="chkLineGraph" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			                  <label style="vertical-align: top;">Line Graph</label></span>
			                  <input type="checkbox" value ="BarGraph" id="chkBarGraph" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
			                  <label style="vertical-align: top;">Bar Graph</label>
			                 </span> </div>
									<!--	For grid					-->
							    <table id="khzGraphDataGrid" style="width:100%"><tr><td/></tr></table>
							   
							    <div id="khzGraphDataPager">
							    </div>
							<!-- End of grid -->
	</div>
</div>
						<div title="Benefits" style="padding:10px;">
							<div class="sub-header"  >Benefit Description </div>
					 			<div class="easyui-paddingbfpx"> 
					            <textarea id="txtkznmBenefits" name="txtkznmBenefits" rows="4" cols="28" maxlength="250"  style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmBenefits}</textarea>
								</div>
								
								<div class="sub-header "  >Improvement Sustainance </div>
								<div class="easyui-paddingbfpx "><label>What To Do</label></div> 
					 			<div class="easyui-paddingbfpx"> 
					            <textarea id="txtkznmWhattosustain" name="txtkznmWhattosustain" rows="4" cols="28" maxlength="500" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmWhattosustain}</textarea>
								</div>
								<div class="easyui-paddingbfpx" ><label>How To Do</label></div> 
					 			<div class="easyui-paddingbfpx"> 
					            <textarea id="txtkznmHowtosustain" name="txtkznmHowtosustain" rows="4" cols="28" maxlength="250" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmHowtosustain}</textarea>
								</div>
								<div class="easyui-paddingbfpx " ><label>Frequency</label></div> 
					 			<div class="easyui-paddingbfpx"> 
					            <textarea id="txtkznmSustainfreq" name="txtkznmSustainfreq" rows="4" cols="28" maxlength="250" style=" width : 575px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmSustainfreq}</textarea>
								</div>
						</div>
						<div title="Cost" style="padding:10px;">
							<div style="width:100%;">
								<div class="sub-header" > Improvement Details</div>
								<div  class="easyui-paddingbfpx" style="margin-left: 10px;" >
                  					  <input id="chkProviding" name="chkProviding" type="checkbox" value="P" <c:out value = "${ requestScope.kaizenFormBean.providing == 'P' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> /> <label>Providing</label>
                   					  <span  style="margin-left: 10px;"> 
                   					  <input id="chkChanging" name="chkChanging" value="C" type="checkbox" <c:out value = "${ requestScope.kaizenFormBean.changing == 'C' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> /> <label>Changing</label></span>
                    		    </div> 
                    		    <div  class="easyui-paddingbfpx" style="margin-left: 10px;">
                  					  <input id="chkReversible" name="chkReversible" type="checkbox" value="R" <c:out value = "${ requestScope.kaizenFormBean.reversible == 'R' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/> <label>Reversible</label>
                   					  <span  style="margin-left: 2px;">  <input id="chkIrreversible" name="chkIrreversible" type="checkbox" value="I" <c:out value = "${ requestScope.kaizenFormBean.irreversible == 'I' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>  /> <label>Irreversible</label></span>
                    		    </div>
                    		    </div>
								<div class="sub-header" > Cost Details</div>
								<div class="easyui-paddingbfpx" >
									<span><label style="padding-right: 65px;">Material Cost Rs</label></span>
								   <span><label style="padding-right: 65px;">Labour Cost Rs</label></span>
								   <span><label>Total Cost Rs</label></span>
								</div> 
		                  		<div class="easyui-paddingbfpx"> 
		                		<span>	<input class="easyui-text" type="text" id="txtkznmMaterialcost" name="txtkznmMaterialcost" value="${requestScope.kznTlMst.kznmMaterialcost}" style="width:155px;" maxlength="7" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> /></span>
								<span>	<input class="easyui-text" type="text" id="txtkznmLabourcost" name="txtkznmLabourcost" value="${requestScope.kznTlMst.kznmLabourcost}" style="width:155px;" maxlength="7" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> ></span>
								<span>	<input class="easyui-text" type="text" id="txttotalcost" name="txtkznmTotalcost" value="${requestScope.kznTlMst.kznmTotalcost}" style="width:155px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> ></span>
							    </div>
							    <div>
								    <span id="err_txtkznmLabourcost"  style="display: block; width: 155px"></span>
							    	<span id="err_txtkznmMaterialcost"  style="display: block;width: 155px;"></span>
							    	<span>&nbsp;</span>
							    </div>
							 	<div class="clear"></div>
							    <div class="sub-header" style="height:20px\9"><label class="notes">Pillar (Press F8 in Category/Loss to Select )</label>
							    <span style="margin-top:-80px\9;"><input class="easyui-button" type="button" value ="Kaizen Category" id="btnImprvCat" style="height:20px" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> ></span></div>
									<table    id="pillarGrid" style="width:100%"><tr><td/></tr>
									</table>
									<div id="pillarPager"></div>
									<div id="KznCategoryPopUp">
										<div id="KznCategory"></div>
									</div>
									
								<div class="sub-header" ><label class="mandatory-lbl"> Results & Benefits</label> </div>
								<div class="easyui-paddingbfpx">
									<label>Primary</label>
								</div>
								<div id="resultArea" class="easyui-paddingbfpx">
									<input id="chkResultAreaP" name="chkResultAreaP" type="checkbox" value="P" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaP == 'P' ? ' checked':''}"/>  /><span Style="padding-left: 10px"><label>P </label></span><span Style="padding-left: 20px"></span>
            						<input id="chkResultAreaQ" name="chkResultAreaQ" type="checkbox" value="Q" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>Q</label> </span><span Style="padding-left: 20px"></span>
    								<input id="chkResultAreaC" name="chkResultAreaC" type="checkbox" value="C" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}" /> <c:out value = "${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>C</label></span> <span Style="padding-left: 20px"></span>
									<input id="chkResultAreaD" name="chkResultAreaD" type="checkbox" value="D" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>D</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaS" name="chkResultAreaS" type="checkbox" value="S" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>S</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaM" name="chkResultAreaM" type="checkbox" value="M" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>M</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="E" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>E</label> </span><span Style="padding-left: 20px"></span>
								</div>
								<div id="err_txtresultArea" class="tpm-errormsg" style="display: block;"></div>
								<div class="easyui-paddingbfpx">
									<label>Secondary</label>
								</div>
								<div id="resultAreaSec" class="easyui-paddingbfpx">
									<input id="chkResultAreaSecP" name="chkResultAreaSecP" type="checkbox" value="P" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecP == 'P' ? ' checked':''}"/>  /><span Style="padding-left: 10px"><label>P </label></span><span Style="padding-left: 20px"></span>
            						<input id="chkResultAreaSecQ" name="chkResultAreaSecQ" type="checkbox" value="Q" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecQ == 'Q' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>Q</label> </span><span Style="padding-left: 20px"></span>
    								<input id="chkResultAreaSecC" name="chkResultAreaSecC" type="checkbox" value="C" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}" /> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecC == 'C' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>C</label></span> <span Style="padding-left: 20px"></span>
									<input id="chkResultAreaSecD" name="chkResultAreaSecD" type="checkbox" value="D" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecD == 'D' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>D</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaSecS" name="chkResultAreaSecS" type="checkbox" value="S" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecS == 'S' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>S</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaSecM" name="chkResultAreaSecM" type="checkbox" value="M" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecM == 'M' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>M</label> </span><span Style="padding-left: 20px"></span>
									<input id="chkResultAreaSecE" name="chkResultAreaSecE" type="checkbox" value="E" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaSecE == 'E' ? ' checked':''}"/>/><span Style="padding-left: 10px"><label>E</label> </span><span Style="padding-left: 20px"></span>
								</div>
								<div id="err_txtresultAreaSec" class="tpm-errormsg" style="display: block;"></div>
						</div>
						
				
						
						
						<div title="HD" style="padding:10px;">
							
							<div class="sub-header" style=" width : 100%; height : 17px;margin-bottom:10px;"> Results & Benifits</div>
		<div style="width: ;">
			<div style="float:left;">
				<div style="padding-left:30px;padding-bottom:35px;">
					<label>HD Required </label> <span Style="padding-left: 10px">
					<input id="chkHdRequiredY" name="chkHdRequiredY" type="checkbox" value="Y" <c:out value = "${ requestScope.kaizenFormBean.hdRequiredY == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>   /> <label> Yes</label>
					<input id="chkHdRequiredN"  name="chkHdRequiredN" type="checkbox" value="N" <c:out value = "${ requestScope.kaizenFormBean.hdRequiredN == 'N' ? ' checked':''}"/>  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>  />  No</span>
					<br><br>
					<label>WO Required</label> 
					<span Style="padding-left: 8px">
					<input id="chkWoRequiredR"  name="chkWoRequiredY" type="checkbox" value="Y" <c:out value = "${ requestScope.kaizenFormBean.woRequiredY == 'Y' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>   />  Yes
					<input id="chkWoRequiredS"  name="chkWoRequiredN" type="checkbox" VALUE="N" <c:out value = "${ requestScope.kaizenFormBean.woRequiredN == 'N' ? ' checked':''}"/> <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>  />  No</span>
				</div>
			</div>
				<div style="float:right;">
					<div class="easyui-paddingbfpx  " >
						<label>HD Nos</label>
					</div> 
				    <div class="easyui-paddingbfpx"> 
				        <input class="easyui-text"type="text" id="txtkznmNoofhds" name="txtkznmNoofhds" value="${requestScope.kznTlMst.kznmNoofhds}" style="width:100px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />
				        <input class="easyui-button" type="button" value ="..." id="btnnavigateHd" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />
					</div>
				</div>
		</div>
		<br><br>
				<br><br>
				
		<div class="sub-header"  style=" width : 578px ; height : 17px;margin-bottom:10px;"> 
	      Responsibility and Target For HD 
		</div>
			
			<table id="kznHDGrid" style="width:100%"><tr><td/></tr></table>
				<div id="kznHDPager"></div>
				<input type="hidden" id="imgKznPresentImgFilename" name="imgKznPresentImgFilename" value="" />
					<input type="hidden" id="imgKznAfterImgFilename" name="imgKznAfterImgFilename" value="" />	
					<input type="hidden" id="imgKznResultImgFilename" name="imgKznResultImgFilename" value="" />
					<input type="hidden" id="hdnlossLinkclickId" name="hdnlossLinkclickId" value="" />	
					<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
					<input type="hidden" id="hdnFrmActionMode" value="${requestScope.kaizenFormBean.formActionMode}"/>	  
					<input type="hidden" id="hdnbdmode" value="${requestScope.bdmmode}"/>
					<input type="hidden" id="hdnhdScan" value=""/>
					<input type="hidden" id="hdncellid"/>
					<input type="hidden" id="hdnpillarId"/>
					<input type="hidden" id="hdnimgClear" name="hdnimgClear" value="${requestScope.imgClear}" />
					<input type="hidden" id="hdnimageType" name="hdnimageType" value="" />
					<input type="hidden" id="hdnqtmmode" name="hdnqtmmode" value="${requestScope.qtmmode}"/>
					<input type="hidden" id="hdnkznKeyid" value="${requestScope.kznKeyid}"/>
					<input type="hidden" id="hdnkaizenApproval" value="${requestScope.kaizenApproval}"/>
					<input type="hidden" id="hdnKznmKzbnkeyid" name="hdnKznmKzbnkeyid"  value="${requestScope.kznTlMst.kznmKzbnkeyid}"/>
					<input type="hidden" id="hdnBenefittype" name="hdnBenefittype"  value="${requestScope.kznTlMst.kznmBenefittype}"/>
					<input type="hidden" id="hdnBenefitvalue" name="hdnBenefitvalue"  value="${requestScope.kznTlMst.kznmBenefitvalue}"/>
					<input type="hidden" id="hdnEMPillar" name="hdnEMPillar"  value="${requestScope.EMPillar}"/>
					
		</div>
		<div title="BTS" style="padding:10px;">
		<table width="100%">
			<tr>
				<td class="easyui-paddingbfpx">
					<div class="easyui-paddingbfpx"><label>Benefit Type</label></div>
					<div class="easyui-paddingbfpx">
						<select class="easyui-text" id="cboKznmBenefittype" name="cboKznmBenefittype" panelHeight=80px;  style="width:  100px; height: 21px;"   <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> onchange='getSelectType(this.value)'>
							<option value='NS'>No Savings</option>
							<option value='S'>Safety</option>
							<option value='LE5'><=5 Lakh</option>
							<option value='GE5'>>5 Lakh</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="easyui-paddingbfpx">
						<input type="button" class="easyui-button" id="btnkpi"	name="btnkpi" style="width:100px;height:21px;display: none;"value="KPI" onclick="openKPIPOP()"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />
						<span style="padding-left: 0px" id="spninrbox">
						<input type="text" id="txtKznmBenefitvalue" name="txtKznmBenefitvalue" class="easyui-text"  style=" width : 100px; text-align: right;" value="${requestScope.kznTlMst.kznmBenefitvalue}"  onfocus="gotFocus(this.id)" maxlength="10" align="right"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
						<label id="inid" style="padding-left: 3px;">(INR)</label>
						</span>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sub-header" > Cost Details</div>
					<div class="easyui-paddingbfpx" >
						<span><label>Cost of Product /hr</label></span>
						<span><label style="padding-left: 37px;">Cost of Equipment /hr</label></span>
						<span><label style="padding-left: 23px;">Total Cost /hr</label></span>
					</div> 
               		<div class="easyui-paddingbfpx"> 
						<span>	<input class="easyui-text" type="text" id="txtKznmCostperhour" name="txtKznmCostperhour"   maxlength="7" value="${requestScope.kznTlMst.kznmCostperhour}"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span>
						<span style="padding-left: 40px;">	<input class="easyui-text" type="text" id="txtKznmCostperequipment" name="txtKznmCostperequipment" value="${requestScope.kznTlMst.kznmCostperequipment}" maxlength="7"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span>
						<span style="padding-left: 43px;">	<input class="easyui-text" type="text" id="txtBTSTotalcost" name="txtkznmBTSTotalcost" /></span>
				    </div>
				    <div>
					    <span id="err_txtKznmCostperhour"  style="display: block; width: 155px"></span>
				    	<span id="err_txtKznmCostperequipment"  style="display: block;width: 155px;"></span>
				    	<span>&nbsp;</span>
				    </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="sub-header" >Work Flow</div>
						<div id="divKznWorkFlow" style="">
						</div>
						<!--  <table id="kznBTSGrid" style="width:100%"><tr><td/></tr></table>
						<div id="kznBTSPager"></div>
						-->
				</td>
			</tr>
			
		</table>
			
			
		</div>
	<!-- 
		<div title="Imp Link" >
						<div class="sub-header" > IMPROVEMENT PROJECT LINK DETAILS:</div>
							 <div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Assembly</label></div> 
                  <div class="easyui-paddingbfpx" style="margin-left: 20px;" > 
                	<input id="cmbkznmAssemblyid" name="cmbkznmAssemblyid" class="easyui-combobox" value="${requestScope.kznTlMst.kznmAssemblyid}" style="width:255px;"/>
				</div>
					<div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Phenomena</label></div> 
                  <div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
                  <input id="cmbkznmPhenomenaid" name="cmbkznmPhenomenaid" class="easyui-combobox" value="${requestScope.kznTlMst.kznmPhenomenaid}" style="width:255px;"/>
                			  
					</div>
					<div class="easyui-paddingbfpx " style="margin-left: 20px;"><label>Cause</label></div> 
                  <div class="easyui-paddingbfpx" style="margin-left: 20px;"> 
                  <input id="cmbkznmCauseid" name="cmbkznmCauseid" class="easyui-combobox"  value="${requestScope.kznTlMst.kznmCauseid}" disabled="${requestScope.kaizenFormBean.disableCause}" style="width:255px;  "/>
                	
					</div>
			
					</div>		
				</div>
	 -->				
				</div>
					
			</div></td>
		</tr>
	</table>
</div>
</div>
</form>
