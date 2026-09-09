<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<!-- <script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	 -->
<script>
var glbKznmStatus ="-";
var glbKznmApprovallevel ="-";
var glbKznmRoleName ="-";
jQuery(document).ready(function(){
//alert('Idea sheet');
	
	initialiseForm('frmImprovementPrj');
	jQuery('#submitForm').val('frmImprovementPrj');
	disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
	var chknewoneid=jQuery('#hdnnewBenefit').val(); 
	var typebenefit=jQuery('#hdnnewPcdqsme').val();   
	var themename=jQuery('#hdnnewthemename').val(); 
	var benArea=jQuery("#hdnBenFitArea").val();
	var ThemeName=jQuery("#hdnThemeName").val();
	var locationId=jQuery("#hdnlocationId").val();
	fillComboBox("frmImprovementPrj","cmbkznmIndustry","IndustryCategory.kaizen","",false); 
	 jQuery("#cmbKznmThemecategoryid").combobox('setValue',chknewoneid); 
	// alert(themename +"  "+ ThemeName+"  "+benArea +""+ chknewoneid);
 	 if(themename===''||themename==='undefined'||themename===null){
	 // alert(2);
	           jQuery("#txtkznmTheme").val(ThemeName);
     }
     /* else{
          jQuery("#txtkznmTheme").val(themename);
     
     } */
     if(typebenefit===null||typebenefit==='undefined'||typebenefit===''){
     typebenefit=benArea; 

}
     /* var isAuthorizedForEMPillar = jQuery('#hdnIsAuthorizedForEMPillar').val();

 	if(isAuthorizedForEMPillar === "Y") {
 	    // Enable the checkbox for authorized roles (AROL0006, AROL0003)
 	    enableFields("chkkznmUtiliseforfuture");
 	    console.log("EM Pillar checkbox enabled for authorized role");
 	} else {
 	    // Disable for all other roles
 	    disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
 	    console.log("EM Pillar checkbox readonly for unauthorized role");
 	} */
 	
var thmCate=jQuery("#cmbKznmThemecategoryid").combobox('getValue'); //jQuery('#hdnKznmThemecategoryid').val();
//alert(thmCate);
	//var ThemeCategory=jQuery('#hdnKznmThemecategoryid').val();
	var kaizen=jQuery("#hdnKaizen").val();
//	alert('123' + ThemeCategory);
	  // setFieldValue("#cmbKznmThemecategoryid",ThemeCategory);
	  // setFieldValue("txtkznmTheme",themename);
	  // disableField("frmImprovementPrj","cmbKznmThemecategoryid");
{
	  // setFieldValue("txtkznmTheme",chknewoneid);
	  
	  //setFieldValue("#cmbKznmThemecategoryid",chknewoneid);
		if(typebenefit=="P"){
			jQuery('#chkResultAreaP').prop('checked', true);
			jQuery('#hdnResultAreaP').val(typebenefit);
		}else if(typebenefit=="Q"){
			jQuery('#chkResultAreaQ').prop('checked', true);
			jQuery('#hdnResultAreaQ').val(typebenefit);
		}else if(typebenefit=="C"){
			jQuery('#chkResultAreaC').prop('checked', true);
			jQuery('#hdnResultAreaC').val(typebenefit);
		}else if(typebenefit=="D"){
			jQuery('#chkResultAreaD').prop('checked', true);
			jQuery('#hdnResultAreaD').val(typebenefit);
		}else if(typebenefit=="S"){
			jQuery('#chkResultAreaS').prop('checked', true);
			jQuery('#hdnResultAreaS').val(typebenefit);
		}else if(typebenefit=="M"){
			jQuery('#chkResultAreaM').prop('checked', true);
			jQuery('#hdnResultAreaM').val(typebenefit);
		}else if(typebenefit=="E"){
			jQuery('#chkResultAreaE').prop('checked', true);
			jQuery('#hdnResultAreaE').val(typebenefit);
		}
		jQuery('#chkResultAreaP').prop('disabled', true);
		jQuery('#chkResultAreaQ').prop('disabled', true);
		jQuery('#chkResultAreaC').prop('disabled', true);
		jQuery('#chkResultAreaD').prop('disabled', true);
		jQuery('#chkResultAreaS').prop('disabled', true);
		jQuery('#chkResultAreaM').prop('disabled', true);
		jQuery('#chkResultAreaE').prop('disabled', true);
		//disableField("frmImprovementPrj","cmbKznmThemecategoryid");
		}

	var factId = jQuery("#frmImprovementPrj input[id='factory']").val();
	var sectionId = jQuery("#frmImprovementPrj input[id='section']").val();
	var cellId = jQuery("#frmImprovementPrj input[id='cell']").val();
	var machId = jQuery("#frmImprovementPrj input[id='machine']").val();
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;

	loadFunctionalLocation("frmKaizenideafunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmImprovementPrj",dataStr);

    var compId = getFieldValue('company','frmImprovementPrj');
	var locnId = getFieldValue('location','frmImprovementPrj');
	var factId = getFieldValue('factory','frmImprovementPrj');
	var sectId = getFieldValue('section','frmImprovementPrj');
	var cellId = getFieldValue('cell','frmImprovementPrj');
	var machId = getFieldValue('machine','frmImprovementPrj');
	var flid = getFieldValue('flid','frmImprovementPrj');
	var ICOC=jQuery('#ICOC').val();
	
	/* var flId = getFieldValue('flid','frmImprovementPrj');
	alert(flId); */
    fillComboBox("frmImprovementPrj","cmbkznmKpiid","kaizenfillcombo.kaizen?flId="+flid);
    fillComboBox("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?q&cellId="+cellId +"&machId="+machId+"&flid="+flid);
    fillComboBox("frmImprovementPrj","cmbKznmActivitypillarid","pillar.commonFilter");
    fillComboBox("frmImprovementPrj","cmbkznmPreparedid","employee.commonFilter");
    fillComboBox("frmImprovementPrj","cmbKznmBenefittype","");

    fillComboBox("frmImprovementPrj","cmbKznmThemecategoryid","kaizenactegoryfillcombo.kaizen?flId="+flid);
    fillComboBox("frmImprovementPrj","cmbkznmFipNumber","Kazennoname_combo.kaizen");
    
    var wwmsKey = jQuery('#cmbkznmKeyid').combobox('getValue');
	//var kznFiprojNoname = jQuery('#cmbkznmFipNumber').combobox('getValue');
    fillComboBox("frmImprovementPrj","cmbkznmWwmsKeyid","combo_whywhy.kaizen?wwmsKeyid="+wwmsKey);
    
    var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	//processGridnew("Pillar_input.kaizen","?kaizenId="+kaizenId,"pillarGrid","pillarPager","","","","pillarGrid_OnCompleteLoad");
	//processGridnew("kznGraphData_input.kaizen","?kaizenId="+kaizenId,"khzGraphDataGrid","khzGraphDataPager","","","","kznGraphDataOnload");
	processGridnew("kznHdScan_input.kaizen","?row=0","kznHDGrid","kznHDPager","","","","kznHDGrid_OnGridload");

	processGridnew("kznGraphData_input.kaizen","?kaizenId="+kaizenId,"khzGraphDataGrid","khzGraphDataPager","","","","kznGraphDataOnload");
	
    
	var request=jQuery("#cmbkznmPreparedid").combobox('getValue');
	
	processAjaxCalls("kaizen_modify.kaizen", "keyid="+request, 'data_successCallBack','data_errorCallBack');
	var Mode=jQuery('#mode').val();
	var frmMode=jQuery('#frmMode').val();
	var apprvalMode=jQuery('#hdnMode').val();
	var keyid=jQuery('#txtKznmKeyid').val();
	var KEType=jQuery("#hdnKEType").val();
	if(Mode===' '||Mode==='undefined'||Mode===null){
	//alert(234);
	Mode=frmMode;
	
	}
	//alert(apprvalMode +" apprvalMode  "+frmMode+"  frmMode  " +" Mode"+Mode);
	//alert(" Mode :: "+Mode+" apprvalMode :: "+apprvalMode+" frmMode :: "+frmMode);
	//madhan
      if(Mode=="create"){
    	setFieldValue('cmbkznmPreparedid',jQuery('#hdnSuggestedby').val());
    	
    }
      disableField("frmImprovementPrj","chkkznmIsworthformp");
    
	if(Mode=="modify"){
		jQuery('#WorkFlowid').css('display','block'); // change to none to block
		jQuery('#maindivhght').css('height','1000');
		disableField("frmImprovementPrj","chkkznmIsworthformp");
		//disableField("frmImprovementPrj","txtKznmBenefitvalue");
		disableField("frmImprovementPrj","cmbkznmFipNumber");
		disableField("frmImprovementPrj","cmbkznmIndustry");
		
		
		var category=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
		
		if(category.length!=0||category!=null)
			{
		jQuery('#chkResultAreaP').prop('disabled', true);
		jQuery('#chkResultAreaQ').prop('disabled', true);
		jQuery('#chkResultAreaC').prop('disabled', true);
		jQuery('#chkResultAreaD').prop('disabled', true);
		jQuery('#chkResultAreaS').prop('disabled', true);
		jQuery('#chkResultAreaM').prop('disabled', true);
		jQuery('#chkResultAreaE').prop('disabled', true);
			}
		//disableField("frmImprovementPrj","chkkznmfipRequired");
		//readOnlyFields("txtKznmBenefitvalue");
	}
	//alert(Mode +" Mode");
	
	 if(Mode=="Approval"){
		jQuery('#WorkFlowid').css('display','block');
		jQuery('#maindivhght').css('height','1100');
	} 
	if(Mode=="approval" || Mode=="view" || Mode=="Approval"|| Mode=="View" || frmMode =="view"){
		//alert(569);
		disableField("frmImprovementPrj","btnnavigateHd");
		disableField("frmImprovementPrj","cmbkznmKpiid");
		disableField("frmImprovementPrj","cmbKznmActivitypillarid");
		readOnlyFields("txtKznmAnalysis");
		readOnlyFields("txtkznmTheme");
		readOnlyFields("txtkznmIdea");
		readOnlyFields("txtkznmEspNames");
		readOnlyFields("txtkznmTeammembers");
		readOnlyFields("txtkznmCountermeasure");
		readOnlyFields("txtkznmBenefits");
		readOnlyFields("txtkznmResultdescription"); 
		readOnlyFields("txtkznmPresentproblem");	
		
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");

		disableField("frmImprovementPrj","cmbKznmBenefittype");
		
		disableField("frmImprovementPrj","chkkznmIswhywhy");
		disableField("frmImprovementPrj","txtKznmAnalysis");
		disableField("frmImprovementPrj","cmbkznmFipNumber");
		disableField("frmImprovementPrj","chkkznmfipRequired");
		disableField("frmImprovementPrj","cmbKznmThemecategoryid");
		jQuery('#WorkFlowid').css('display','block');
    	jQuery('#maindivhght').css('height','1000');
    	//readOnlyFields("txtKznmBenefitvalue");
        var category=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
		
		if(category.length!=0||category!=null)
			{
		jQuery('#chkResultAreaP').prop('disabled', true);
		jQuery('#chkResultAreaQ').prop('disabled', true);
		jQuery('#chkResultAreaC').prop('disabled', true);
		jQuery('#chkResultAreaD').prop('disabled', true);
		jQuery('#chkResultAreaS').prop('disabled', true);
		jQuery('#chkResultAreaM').prop('disabled', true);
		jQuery('#chkResultAreaE').prop('disabled', true);
			}
    	//disableField("frmImprovementPrj","txtKznmBenefitvalue");
	}
	
	if(apprvalMode=="Approval")
	{
	   enableFields("chkkznmIsworthformp");
	   disableUIButton("btnswitchtoupload");
	}
	
    if(((Mode=="create" && frmMode=="approval"))){
    	
    	
    	
        jQuery('#WorkFlowid').css('display','block');
    	jQuery('#maindivhght').css('height','1000');
    	
    }

	/*else if(title=="Analysis")
	{
		var wwmsKeyid = jQuery('#cmbkznmWwmsKeyid').combobox('getValue');//
		processGridnew("kznWhyWhy_input.kaizen","?wwmsKeyid="+wwmsKeyid,"kznYYGrid","kznYYPager");
	//	jQuery('#kznYYGrid').trigger('reloadGrid');
	}*/
      if(KEType=="KaizenEvaluation"){
    	  disableUIButton("btnswitchtoupload");
      }
	 var wwmsKeyid = jQuery('#cmbkznmKeyid').combobox('getValue');
     processGridnew("kznWhyWhy_input.kaizen","?wwmsKeyid="+wwmsKeyid,"kznYYGrid","kznYYPager","","","","loadCompletewhywhy");
	//processGridnew("kznWhyWhy_input.kaizen","?row=0","kznYYGrid","kznYYPager");
	//processGridnew("kznBTSGrid_input.kaizen","?row=0","kznBTSGrid","kznBTSPager","","","","kznBTSGrid_OnGridload");

	imageUpload(jQuery("#dlgImgPresent" ),'ImageUpload.commonFilter','dlgImgPresent',"imgKznmPresentimage","imgKznPresentImgFilename","310","140",false);
    imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgkznmAfterimage","imgKznAfterImgFilename","310","140",false);
 	imageUpload(jQuery( "#dlgImgResult" ),'ImageUpload.commonFilter','dlgImgResult',"imgkznmResultimage","imgKznResultImgFilename","310","140",false);
 	imageUpload(jQuery( "#dlgImgBenefit" ),'ImageUpload.commonFilter','dlgImgBenefit',"imgKznmbenefitimage","imgKznBenefitImgFilename","310","140",false);


//	jQuery('#frmImprovementPrj .easyui-text').css('text-transform', 'uppercase');kpi
//	jQuery('#frmImprovementPrj textarea').css('text-transform', 'uppercase');
	formatDateBox('dtekznmDate','dd-MMM-yyyy');
	formatDateBox('dtekznmStartdate','dd-MMM-yyyy');
	formatDateBox('dtekznmEnddate','dd-MMM-yyyy');
	

	kaizenFrmLoad();
	
	var documentno=jQuery('#txtKznmKeyid').val();
	fileManagerPopUp(documentno,"KZN","frmImprovementPrj","btnfilemgr","KZNFilemgr");
	
	jQuery("#btnImgBenefit").click(function(){
		
		//LoadPopUp("divbenimagepopup","kaizenbenifit_image.kaizen", false, "41%", "46%", "22%", "40%", " ", "BENEFIT IMAGES","",false);
		
	});
	
	
	    jQuery("#btnActionPlan").click(function(){
	    	var keyid = jQuery('#txtKznmKeyid').val();
		    //alert(" Keyid :: "+keyid);   //escape(mainTask)  txtkznmIdea
		 if(keyid.trim().length>0)
	    { 
			 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
			 var mainTask= getFieldValue("txtkznmTheme");
			 var frmMode=jQuery('#frmMode').val();

			 apMode = "create";
			if(frmMode=="view")
			   apMode = "view";
     		   openActionPlan("KZNActionPlan",keyid,"KZN",flid,mainTask,keyid,"",apMode);
     		 
	    }
		 else
	    {
			 saveForm('frmImprovementPrj','kaizen_save.kaizen?openactnpln=openactnpln'+'&status='+glbKznmStatus);
	    }		 

	});	

	    var yyIdval= jQuery('#cmbkznmWwmsKeyid').combobox('getValue');
		if(yyIdval.length>2){
				disableField("frmImprovementPrj","chkkznmIswhywhy");
				jQuery('#chkkznmIswhywhy').prop('checked',true);	
			}
			
		jQuery('#btnnavigateHd').click(function()
		{  if(jQuery('#chkHdRequiredY').is(':checked')==true)
		{
			 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
			// alert("Check Flid"+flid);
	         var hdkaizenid=jQuery('#txtKznmKeyid').val();
	       //  alert("HD"+hdkaizenid);
			saveForm('frmImprovementPrj','horizontalDeplymnt.kaizen?hdkaizenid='+hdkaizenid+'');
			}
		});
		if(jQuery('#chkkznmIswhywhy').is(':checked')==true){
    		enableUIButton('btnKznWhyWhy');
    		jQuery("#kznYYAnswerf").show();
    		jQuery("#kznYYAnstxt").hide();
			}
    	else {
    		
				disableUIButton('btnKznWhyWhy');
				jQuery("#kznYYAnswerf").hide();
				jQuery("#kznYYAnstxt").show();
				}
		
		   jQuery( "#chkkznmIswhywhy" ).click(function() {
		    	//var chkVal = getFieldValue('chkkznmIswhywhy');
		    	if(jQuery('#chkkznmIswhywhy').is(':checked')==true){
		    		//jQuery("#chkkznmIswhywhy").prop("checked",true);
		    		//jQuery("#chkkznmIswhywhy").val();
		    		enableUIButton('btnKznWhyWhy');
		    		jQuery("#kznYYAnswerf").show();
		    		jQuery("#kznYYAnstxt").hide();
					}
		    	else {
		    		//jQuery("#chkkznmIswhywhy").prop("checked",false);
		    		//jQuery("#chkkznmIswhywhy").val();
						disableUIButton('btnKznWhyWhy');
						jQuery("#kznYYAnswerf").hide();
						jQuery("#kznYYAnstxt").show();
						}
				
			});	
		   /*jQuery('input[name$="chkkznmIswhywhy"]').each(function(){
			     if(this.checked){
			         $(this).val('Y');
			         // or this.value = "YES";
			     } else {
			         $(this).val('N');
			         // or this.value = "NO";
			     }
			});*/
		


	
	//var wwmsKeyid = jQuery('#cmbkznmKeyid').combobox('getValue');
    //alert(" wwmsKeyid :: "+wwmsKeyid);
	//processGridnew("kznWhyWhy_input.kaizen","?wwmsKeyid="+wwmsKeyid,"kznYYGrid","kznYYPager");

	//alert(1);
	
	//alert(2);
	
	/* 
	jQuery('#dteRfrommonth').datebox({  
		 
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
		 onSelect:function(date){
			var onSelectFunctionName = 'dteRfrommonth_onSelectMonth';
			if( typeof eval('('+onSelectFunctionName +')') == 'function')
			{
				eval('( '+ onSelectFunctionName +'(date))');
			}
		 }
		 
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
	 }); 
	 */
	//formatDateBox('dtetargeton','dd-MMM-yyyy');
	 numericTextBox("txtkznmTarget",true);
	 numericTextBox("txtkznmBenchmark",true);
	numericTextBox("txtkznmLabourcost");
	numericTextBox("txtkznmMaterialcost");
	numericTextBox("txttotalcost");
	readOnlyFields("txttotalcost");
	readOnlyFields("txtkznmNoofhds");
	readOnlyFields("txtkznmRefdocno");
	readOnlyFields("txtkznmRefdoctype");
	numericTextBox("txtKznmCostperhour");
	numericTextBox("txtKznmCostperequipment");
	//numericTextBox("txtBTSTotalcost");
	//readOnlyFields("txtBTSTotalcost");
	//numericTextBox("txtkznmVerifyamount");
	
	numericTextBox("txtBTSTotalcost");
	numericTextBox("txtkznmVerifyamount");
	
	readOnlyFields("txtBTSTotalcost");
	readOnlyFields("txtkznmVerifyamount");
	
	
	//disableField("frmImprovementPrj", "txtKznmBenefitvalue");
	var kznmDate = jQuery('#dtekznmDate').datebox('getValue');
	var kznmStartDate = jQuery("#dtekznmStartdate").datebox("getValue");
	var kznmEndDate =jQuery("#dtekznmEnddate").datebox("getValue");

	/*if( kznmDate == null || kznmDate.length <= 0)
	{
		fillWithCurrentDate("dtekznmDate");
	}
	if( kznmApprvDate == null || kznmApprvDate.length <= 0)
	{
		fillWithCurrentDate("dtekznmApproveddate");
	}

	if( kznmStartDate == null || kznmStartDate.length<=0)
	{
		fillWithCurrentDate("dtekznmStartdate");
	}
	
	if(kznmEndDate == null || kznmEndDate.length<=0)	
	{
		fillWithCurrentDate("dtekznmEnddate");
	}
	*/
	
	fillWithCurrentMonth("dteRfrommonth");
	fillWithCurrentMonth("dteRtomonth");
	
	
	
	kaizenOnload();
	
	jQuery( "#chkHdRequiredY" ).click(function() {
		
		 if((jQuery('#chkHdRequiredY').is(':checked'))){
			 enableUIButton('btnnavigateHd');
			 jQuery('#chkHdRequiredN').prop('checked',false);
		 }
		 else if((!jQuery('#chkHdRequiredY').is(':checked')))
		 {	  
		 	  if (jQuery('#chkHdRequiredN').is(':checked'))
		 	  {
		 		 jQuery('#chkHdRequiredN').prop('checked',true);
		 	  }else{
		 	     jQuery('#chkHdRequiredY').prop('checked',true);
		 	  }
		 }
		
	});
	
	
	
	
	
	
	
	
	
	jQuery( "#chkHdRequiredN" ).click(function() {
		
		if((jQuery('#chkHdRequiredN').is(':checked')))
		 {	  //jQuery("#btnnavigateHd").prop('readonly','readonly');
		 	  disableUIButton('btnnavigateHd');
		 	  jQuery('#chkHdRequiredY').prop('checked',false);
		 }
		 else if((!jQuery('#chkHdRequiredN').is(':checked')))
		 {	  
		 	  if (jQuery('#chkHdRequiredY').is(':checked'))
		 	  {
		 		 jQuery('#chkHdRequiredY').prop('checked',true);
		 	  }else{
		 	     jQuery('#chkHdRequiredN').prop('checked',true);
		 	  }
		 }
		
	});
	
	jQuery( "#chkIdeagroupindividualI" ).click(function() {
		
		 if((jQuery('#chkIdeagroupindividualI').is(':checked'))){
			 jQuery('#chkIdeagroupindividualG').prop('checked',false);
		 }
		 
		 else if((!jQuery('#chkIdeagroupindividualI').is(':checked')))
		 {	  
		 	  if (jQuery('#chkIdeagroupindividualI').is(':checked'))
		 	  {
		 		 jQuery('#chkIdeagroupindividualI').is(':checked');
		 	  }else{
		 	     jQuery('#chkIdeagroupindividualI').prop('checked',true);
		 	  }
		 }
		
	});
	
	jQuery( "#chkIdeagroupindividualG" ).click(function() {
		
		if((jQuery('#chkIdeagroupindividualG').is(':checked')))
		 {	  
		 	  jQuery('#chkIdeagroupindividualI').prop('checked',false);
		 }
		
		else if((!jQuery('#chkIdeagroupindividualG').is(':checked')))
		 {	  
		 	  if (jQuery('#chkIdeagroupindividualI').is(':checked'))
		 	  { 
		 		 jQuery('#chkIdeagroupindividualI').is(':checked');
		 	  }else{ 
		 	 jQuery('#chkIdeagroupindividualG').prop('checked',true);
		 	  }
		 }
		
	});
	
	jQuery("#chkKznmCsmValue").click(function(){
		if(jQuery('#chkKznmCsmValue').is(':checked') == true)
		{	     
				
			    jQuery('#chkKznmCsmValue').val("Y");
			    
		}
			else{
				
				jQuery('#chkKznmCsmValue').val("N");
			}
		
	});	
	
	
	
	jQuery( "#chkKznmIcoe" ).click(function() {
		
		 if((jQuery('#chkKznmIcoe').is(':checked'))){
			 jQuery('#chkKznmPcoe').prop('checked',false);
			 jQuery('#chkKznmFip').prop('checked',false);

		 }
		 
		 else if((!jQuery('#chkKznmIcoe').is(':checked')))
		 {	  
		 	  if (jQuery('#chkKznmPcoe').is(':checked'))
		 	  {
		 		 jQuery('#chkKznmPcoe').is(':checked');
		 	  }
		 	  else if(jQuery('#chkKznmFip').is(':checked')){
		 		 jQuery('#chkKznmFip').is(':checked');
		 	  }
		 	  }else{
		 	     jQuery('#chkKznmIcoe').prop('checked',true);
		 	  }
		 
		
	});
	
	jQuery( "#chkKznmPcoe" ).click(function() {
		
		if((jQuery('#chkKznmPcoe').is(':checked')))
		 {	  
		 	  jQuery('#chkKznmIcoe').prop('checked',false);
		 	  jQuery('#chkKznmFip').prop('checked',false);

		 }
		
		else if((!jQuery('#chkKznmPcoe').is(':checked')))
		 {	  
		 	  if (jQuery('#chkKznmIcoe').is(':checked'))
		 	  { 
		 		 jQuery('#chkKznmIcoe').is(':checked');
		 	  }
		 	   else if(jQuery('#chkKznmFip').is(':checked')){
		 		 jQuery('#chkKznmFip').is(':checked');
		 	  }
		 	  else{ 
		 	 jQuery('#chkKznmPcoe').prop('checked',true);
		 	  }
		 }
		
	});
	
jQuery( "#chkKznmFip" ).click(function() {
		
		if((jQuery('#chkKznmFip').is(':checked')))
		 {	  
		 	  jQuery('#chkKznmIcoe').prop('checked',false);
		 	  jQuery('#chkKznmPcoe').prop('checked',false);

		 }
		
		else if((!jQuery('#chkKznmFip').is(':checked')))
		 {	  
		 	  if (jQuery('#chkKznmIcoe').is(':checked'))
		 	  { 
		 		 jQuery('#chkKznmIcoe').is(':checked');
		 	  }
		 	 else if (jQuery('#chkKznmPcoe').is(':checked'))
		 	  { 
		 		 jQuery('#chkKznmPcoe').is(':checked');
		 	  }
		 	   
		 	  else{ 
		 	 jQuery('#chkKznmFip').prop('checked',true);
		 	  }
		 }
		
	});
	
	
	
	
jQuery("#chkkznmfipRequired").click(function(){
	if(jQuery('#chkkznmfipRequired').is(':checked') == true)
	{	     
			jQuery('#lblFIProject').addClass('mandatory-lbl');
		    jQuery('#chkkznmfipRequired').val("Y");
		    enableFields("cmbkznmFipNumber");
	}
		else{
			jQuery('#lblFIProject').removeClass('mandatory-lbl');
			jQuery('#chkkznmfipRequired').val("N");
		}
	
	
	
	
});	
jQuery("#chkkznmIndustryReq").click(function(){
	if(jQuery('#chkkznmIndustryReq').is(':checked') == true)
	{	     
			jQuery('#lblIndustryProject').addClass('mandatory-lbl');
		    jQuery('#chkkznmIndustryReq').val("Y");
		    enableFields("cmbkznmIndustry");
	}
		else{
			jQuery('#lblFIProject').removeClass('mandatory-lbl');
			jQuery('#chkkznmIndustryReq').val("N");
		}
	
	
	
	
});	
	
	
	
	if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val().trim() !='')
	{		  
			   jQuery("#cmbkznmRelatedto").combobox('setValue',jQuery("#relatedToCMB").val());
			   fillComboBox("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter");
			  
	}

	 if(jQuery('#cmbkznmRelatedto').combobox('getValue')=="MLD")
		   jQuery('#cmbkznmMouldid').combobox('enable');
	   else 
		   jQuery('#cmbkznmMouldid').combobox('disable');
		   		  
		
	//jQuery("#chkLineGraph").prop({'checked':true});
	/* jQuery( "#btnKaizenEvaluation" ).click(function() {
		var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
		var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	//	processAjaxCalls("KaizenEvaluation_input.kazev","&kaizenId="+kaizenId+"&flid="+flid);
		navigateToNextForm("KaizenEvaluation_input.kazev?q=2&kaizenId="+kaizenId+"&flid="+flid);
	});
	 */
	 var kznmBenefitvalue = document.getElementById('txtKznmBenefitvalue');

	 kznmBenefitvalue.addEventListener('input', function (prev) {
		    return function (evt) {
		        if (!/^\d{0,9}(?:\.\d{0,2})?$/.test(this.value)) {
		          this.value = prev;
		        }
		        else {
		          prev = this.value;
		        }
		    };
		}(kznmBenefitvalue.value), false);

	 var KznmCostperhour = document.getElementById('txtKznmCostperhour');

	 KznmCostperhour.addEventListener('input', function (prev) {
		    return function (evt) {
		        if (!/^\d{0,14}(?:\.\d{0,2})?$/.test(this.value)) {
		          this.value = prev;
		        }
		        else {
		          prev = this.value;
		        }
		    };
		}(KznmCostperhour.value), false);
	 
	 var KznmCostperequipment = document.getElementById('txtKznmCostperequipment');

	 KznmCostperequipment.addEventListener('input', function (prev) {
		    return function (evt) {
		        if (!/^\d{0,6}(?:\.\d{0,2})?$/.test(this.value)) {
		          this.value = prev;
		        }
		        else {
		          prev = this.value;
		        }
		    };
		}(KznmCostperequipment.value), false);  
		
		var kznmVerifyamount = document.getElementById('txtkznmVerifyamount');

		kznmVerifyamount.addEventListener('input', function (prev) {
			    return function (evt) {
			        if (!/^\d{0,10}(?:\.\d{0,2})?$/.test(this.value)) {
			          this.value = prev;
			        }
			        else {
			          prev = this.value;
			        }
			    };
			}(kznmVerifyamount.value), false);
	  /*  jQuery(function(){

		  jQuery('.easyui-text').keypress(function(e) {
			if(isNaN(this.value+""+String.fromCharCode(e.charCode))) return false;
		  })
		  
		  .on("cut copy paste",function(e){
			e.preventDefault();
			
		  });

		}); 
	  */
	 
  
	 
	/* /*  jQuery( "#txtKznmBenefitvalue" ).keydown(function(e) {
		 
			if (e.shiftKey || e.ctrlKey || e.altKey) {
			e.preventDefault();
			} else {
			var key = e.keyCode;
			if (!((key == 8) || (key == 46) || (key >= 35 && key <= 40) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
			e.preventDefault();
}
}
}); */
	//	});

/* jQuery( "#txtKznmCostperhour" ).keydown(function(e) {
	  
			if (e.shiftKey || e.ctrlKey || e.altKey) {
			e.preventDefault();
			} else {
			var key = e.keyCode;
			if (!((key == 8) || (key == 46) || (key >= 35 && key <= 40) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
			e.preventDefault();
}
}
});  
jQuery( "#txtKznmCostperequipment" ).keydown(function(e) {
	 
			if (e.shiftKey || e.ctrlKey || e.altKey) {
			e.preventDefault();
			} else {
			var key = e.keyCode;
			if (!((key == 8) || (key == 46) || (key >= 35 && key <= 40) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
			e.preventDefault();
}
}
});
jQuery( "#txtkznmVerifyamount" ).keydown(function(e) {
	 
		if (e.shiftKey || e.ctrlKey || e.altKey) {
		e.preventDefault();
		} else {
		var key = e.keyCode;
		if (!((key == 8) || (key == 46) || (key >= 35 && key <= 40) || (key >= 48 && key <= 57) || (key >= 96 && key <= 105))) {
		e.preventDefault();
}
}
});  */
	 jQuery('#btnExcelVw').click(function()
	 {
	 	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	 	var benselval=jQuery('#cmbKznmBenefittype').combobox('getValue');
	 	//alert(" benselval :: "+benselval);
	 	
	 	var benTypeVal="";
		
		if(benselval=="GE5")
			benTypeVal="BTSG5L";
		else if(benselval=="NS")
			benTypeVal="BTSNOSAVIN";
		else if(benselval=="S")
			benTypeVal="BTSSAFETY";
		else if(benselval=="LE5")
			benTypeVal="BTSL5L";
		else if(benselval=="GE1C") 
			benTypeVal="BTSG1C";
		
		var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
		
	 	//alert(" benTypeVal :: "+benTypeVal);
	 	
	 	//alert(" flid :: "+flid);
	 	
	 	var Mode=jQuery('#mode').val();
	 	
	 	if(kaizenId != null && kaizenId.length > 0)
	 		window.open("ImpprojSht_view.ipsrpt?&kaizenId="+kaizenId+"&benTypeVal="+benTypeVal+"&flid="+flid+"&workFlow="+Mode);
	    });

	/* var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
		if(!(kaizenId.length>0)){
			jQuery("#ExcelVw").hide();
	 		checkSingle();
		}else
			jQuery("#ExcelVw").show();
		jQuery("#btnExcelView").click(function(){
			window.open("ImpprojSht_view.ipsrpt?kaizenId="+kaizenId);
		});*/
	 
	 jQuery('#btnKznWhyWhy').click(function (){
			saveForm('frmImprovementPrj','kaizen_whywhy.kaizen?Why=Why');
		});
	 
	 jQuery( "#btnImgPresentClear" ).click(function() {
		processAjaxCalls("kznPresentImageClear.kaizen","&presentImage=present");
		jQuery('#imgKznPresentImgFilename').val(" ");
		jQuery('#imgKznmPresentimage').prop('src', " ");
	});
	
	jQuery( "#btnImgAfterClear" ).click(function() {
		processAjaxCalls("kznAfterImageClear.kaizen","&afterImage=after");
		jQuery('#imgKznAfterImgFilename').val(" ");
		jQuery('#imgkznmAfterimage').prop('src', " ");
	});
	jQuery( "#btnImgBenefitClear" ).click(function() {
		processAjaxCalls("kznBenefitImageClear.kaizen","&benefitImage=null");
		jQuery('#imgKznBenefitImgFilename').val(" ");
		jQuery('#imgKznmbenefitimage').prop('src', " ");
	});
	jQuery( "#btnImgResultClear" ).click(function() {
		processAjaxCalls("kznResultImageClear.kaizen","&resultImage=result");
		jQuery('#imgkznmResultimage').prop('src', " ");
		jQuery('#imgKznResultImgFilename').val(" ");
	});
	
	
	jQuery("#imgkznmAfterimage").load(function() {
		
		
		/*if((jQuery(this).width()>415)||(jQuery(this).height()>264))
		{	jQuery('#imgkznmAfterimage').prop('src', " ");
		 	jQuery('#imgKznAfterImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}*/
   });

	/*jQuery("#imgKznmPresentimage").load(function() {
		if((jQuery(this).width()>306.15)||(jQuery(this).height()>143.63))
		{	jQuery('#imgKznmPresentimage').prop('src', " ");
			jQuery('#imgKznPresentImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}
       
   });
			
	jQuery("#imgkznmResultimage").load(function() {
		if((jQuery(this).width()>306.15)||(jQuery(this).height()>143.63))
		{	jQuery('#imgkznmResultimage').prop('src', " ");
		 	jQuery('#imgKznResultImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}
		
   });*/

	var valbenefittype = jQuery('#hdnBenefittype').val();
  // alert(valbenefittype);
    if(valbenefittype=='GE5' || valbenefittype=='GE1C' ){
    	jQuery("#lblkpi").addClass("mandatory-lbl");
    	jQuery("#lblbaseline").addClass("mandatory-lbl");
    	jQuery("#lblkznmTarget").addClass("mandatory-lbl");
    }
    else{
    	jQuery("#kpilbl").removeClass("mandatory-lbl");
    	jQuery("#kznbaseline").removeClass("mandatory-lbl");
    	jQuery("#txtkznmTarget").removeClass("mandatory-lbl");
    }
	//jQuery("#frmImprovementPrj select[id= cmbKznmBenefittype]").prop('value',valbenefittype);
	jQuery('#cmbKznmBenefittype').combobox('setValue',valbenefittype);
	if(valbenefittype.length>0){
		if(!(valbenefittype == "NS")){
			//alert("if");
			getSelectType(valbenefittype);
			CalculateBTS();	
			if(valbenefittype=="GE5")
				{
				jQuery("#txtKznmBenefitvalue").prop('maxlength','8');
				}
			
			if(valbenefittype=="GE1C")
			{
			jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
			}
			jQuery('#txtKznmBenefitvalue').val(jQuery('#hdnBenefitvalue').val());
			//alert("if end");
		}else{
			//alert("else");
			jQuery("#btnkpi").hide();
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").prop('maxlength','8');
			jQuery("#spninrbox").css('padding-left','0');
			//alert("else end");
			
		}
	}
	
	var getText=jQuery('#cmbkznmPreparedid').combobox('getText');
    //alert(" Checking "+getText);
    
     var Mode=jQuery('#mode').val();
     var keyid=jQuery('#txtKznmKeyid').val();
	 
	 if(Mode=="create" && keyid.trim().length<=0){
	    setFieldValue('txtkznmTeammembers',getText);
	 }
	 
	 //var Mode=jQuery('#mode').val();
	 //var keyid=jQuery('#txtKznmKeyid').val();

	 if(keyid.trim().length>0 && (Mode=="approval" || Mode=="view"||frmMode=="view"))
	 	 { 
	 		//readOnlyFields("txtKznmBenefitvalue");
	 		disableField("frmImprovementPrj","txtKznmBenefitvalue");
	 		
	 	 }

	 
	    var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	    
	    if(benefitype=="NS" ){
			readOnlyFields("txtKznmBenefitvalue");
		}
	    
	 
});



function data_successCallBack(result){  

	/*if((jQuery('#chkIdeagroupindividualG').prop('checked','checked'))){
		jQuery('#chkIdeagroupindividualI').prop('checked',false);
	    setFieldValue('txtkznmTeammembers',result[0]);
	}else if((jQuery('#chkIdeagroupindividualI').prop('checked','checked'))){
		jQuery('#chkIdeagroupindividualI').prop('checked',false);
	}*/
	
	var Mode=jQuery('#mode').val();
	
	if(Mode=="create"){
	   //setFieldValue('txtkznmTeammembers',result[0]);
	}
}


function  frmImprovementPrjcmbkznmMachineid_onSelect(record){
	
 	//loadFunctionalLocation("frmKaizenideafunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmImprovementPrj","&machId="+record.id);
}

function  getSelectType(value){
	//alert("getSelectType:"+record.id);
	//var value = record.id;
	var mode=jQuery("#mode").val();
	var locationId=jQuery("#hdnlocationId").val();

	jQuery("#txtKznmBenefitvalue").val('');
	if(value=="NS"){
		jQuery("#btnkpi").hide();
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','5');
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");  
		}
	 
	   else if(value=="GE5"){
		   if(locationId=='LCN0000001'){   
		jQuery("#lblkpi").addClass("mandatory-lbl");
		jQuery("#lblbaseline").addClass("mandatory-lbl");
		jQuery("#lblkznmTarget").addClass("mandatory-lbl");
		
		if(mode=="view" || mode=="completion" || mode=="Approval")
			{
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#spninrbox").css('padding-left','20');
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
			}
		else
			{
			
		enableFields("txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
			}
		}
		     else{
			   jQuery("#lblkpi").removeClass("mandatory-lbl");
			   jQuery("#lblbaseline").removeClass("mandatory-lbl");
			   jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		   }		   
	   }

	   else if(value=="GE1C"){
		   if(locationId=='LCN0000001'){   
		jQuery("#lblkpi").addClass("mandatory-lbl");
		jQuery("#lblbaseline").addClass("mandatory-lbl");
		jQuery("#lblkznmTarget").addClass("mandatory-lbl");
		
		if(mode=="view" || mode=="completion" || mode=="Approval")
			{
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#spninrbox").css('padding-left','20');
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").prop('maxlength','12');
			}
		else
			{
			
		enableFields("txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','12');
			}
		}
		     else{
			   jQuery("#lblkpi").removeClass("mandatory-lbl");
			   jQuery("#lblbaseline").removeClass("mandatory-lbl");
			   jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		   }		   
	   }

	
	
	else if(value=="LE5"){   
		jQuery("#txtKznmBenefitvalue").prop('maxlength','6');
		jQuery("#btnkpi").hide();
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		if(mode!="view" && mode !="completion" && mode!="Approval")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','6');
		}
	else if(value=="S"){
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
	}
	else{
		//jQuery("#btnkpi").hide(); 
		if(mode!="view" && mode !="completion" && mode!="Approval" )
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
		}
	var benselval=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var benTypeVal="";
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	 var minDate=getFieldValue('dtekznmDate');
		if(benselval=="GE5")
			benTypeVal="BTSG5L";
		else if(benselval=="GE1C")
			benTypeVal="BTSG1C";
		else if(benselval=="NS")
			benTypeVal="BTSNOSAVIN";
		else if(benselval=="S")
			benTypeVal="BTSSAFETY";
		else if(benselval=="LE5")
			benTypeVal="BTSL5L";
		
			//workFlow("divKznWorkFlow",false,benTypeVal, kaizenId, "KZNBTS");
			workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate,"","N");
}


//Changed by Tamil July-5-2018// 
function frmImprovementPrjcmbKznmBenefittype_onSelect(record){ // getSelectType(value){
	//alert("getSelectType:"+record.id);
	var value = record.id;
	var mode=jQuery("#mode").val();
	var locationId=jQuery("#hdnlocationId").val();

	jQuery("#txtKznmBenefitvalue").val('');
	if(value=="NS"){
		jQuery("#btnkpi").hide();
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','5');
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");  
		}
	 
	   else if(value=="GE5"){
		   if(locationId=='LCN0000001'){   
		jQuery("#lblkpi").addClass("mandatory-lbl");
		jQuery("#lblbaseline").addClass("mandatory-lbl");
		jQuery("#lblkznmTarget").addClass("mandatory-lbl");
		
		if(mode=="view" || mode=="completion" || mode=="Approval")
			{
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#spninrbox").css('padding-left','20');
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
			}
		else
			{
			
		enableFields("txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
			}
		}
		     else{
			   jQuery("#lblkpi").removeClass("mandatory-lbl");
			   jQuery("#lblbaseline").removeClass("mandatory-lbl");
			   jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		   }		   
	   }

	   else if(value=="GE1C"){
		   if(locationId=='LCN0000001'){   
		jQuery("#lblkpi").addClass("mandatory-lbl");
		jQuery("#lblbaseline").addClass("mandatory-lbl");
		jQuery("#lblkznmTarget").addClass("mandatory-lbl");
		
		if(mode=="view" || mode=="completion" || mode=="Approval")
			{
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#spninrbox").css('padding-left','20');
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").prop('maxlength','12');
			}
		else
			{
			
		enableFields("txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','12');
			}
		}
		     else{
			   jQuery("#lblkpi").removeClass("mandatory-lbl");
			   jQuery("#lblbaseline").removeClass("mandatory-lbl");
			   jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		   }		   
	   }

	
	
	else if(value=="LE5"){   
		jQuery("#txtKznmBenefitvalue").prop('maxlength','6');
		jQuery("#btnkpi").hide();
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
		if(mode!="view" && mode !="completion" && mode!="Approval")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','6');
		}
	else if(value=="S"){
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#lblkpi").removeClass("mandatory-lbl");
		jQuery("#lblbaseline").removeClass("mandatory-lbl");
		jQuery("#lblkznmTarget").removeClass("mandatory-lbl");
	}
	else{
		//jQuery("#btnkpi").hide(); 
		if(mode!="view" && mode !="completion" && mode!="Approval" )
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").prop('maxlength','10');
		}
	var benselval=record.id ;//jQuery('#cmbKznmBenefittype').combobox('getValue');
	var benTypeVal="";
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	 var minDate=getFieldValue('dtekznmDate');
		if(benselval=="GE5")
			benTypeVal="BTSG5L";
		else if(benselval=="GE1C")
			benTypeVal="BTSG1C";
		else if(benselval=="NS")
			benTypeVal="BTSNOSAVIN";
		else if(benselval=="S")
			benTypeVal="BTSSAFETY";
		else if(benselval=="LE5")
			benTypeVal="BTSL5L";
		
			//workFlow("divKznWorkFlow",false,benTypeVal, kaizenId, "KZNBTS");
			workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate,"","N");
}

function KZNBTS_successCallback(result)
{
	//alert(Object.keys(result));
}

jQuery('#txtKznmCostperequipment').keyup(function() {
	CalculateBTS();
});
jQuery('#txtKznmCostperhour').keyup(function() {
	CalculateBTS();		
});

jQuery('#txtKznmBenefitvalue').keyup(function() {
	validateBTS();
	//alert(1);
	//txtKznmBenefitvalue_onBlur();
});

function txtBenfitChange(id) {
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cmbKznmBenefittype').combobox('getValue');
	
	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be lesser than or equal to 500000");
		return false;
	}

	if(valbenefittype=="GE5" && parseInt(valSavings)<=500000){
	jQuery('#txtKznmBenefitvalue').val("");
	//alert(1);
	alert("Amount Should be greater than 500000");
	return false;
}
	
	if(valbenefittype=="GE1C" && parseInt(valSavings)<10000000){
		jQuery('#txtKznmBenefitvalue').val("");
		//alert(1);
		alert("Amount Should be greater than 10000000");
		return false;
	}
}


function validateBTS(){
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cmbKznmBenefittype').combobox('getValue');

	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be lesser than or equal to 500000");
		return false;
	}
}


function validate(){
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cmbKznmBenefittype').combobox('getValue');

	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be lesser than or equal to 500000");
		return false;
	}
}

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


function kznHDGrid_OnGridload()
{
	 getHdCount();
}

function getHdCount()
{
	var hdCount=jQuery("#kznHDGrid").getGridParam('reccount'); 
	jQuery('#txtkznmNoofhds').val(hdCount);
}

	
function loadCompletewhywhy(id){
    
    var row = jQuery("#"+id).jqGrid('getDataIDs');
      
  	 for(var i=0;i<row.length;i++)
  	 {
  		 var combovalue = jQuery("#"+id).jqGrid('getCell',row[i],"whywhy"); 
  		 //alert(" combovalue :: Checking "+ combovalue);
  		 setFieldValue("cmbkznmWwmsKeyid",combovalue);
  		 
 	 }
}


/*
function frmImprovementPrj_Submit()
{       //alert(" Submit :: ");
		if(convertToJSONGraphArr('khzGraphDataGrid').trim()!=null && convertToJSONGraphArr('khzGraphDataGrid').trim()!="")
	 		gridData += '&KznGraphData='+convertToJSONGraphArr('khzGraphDataGrid');
			//alert(" gridData :: "+gridData);
		var kznmKeyid=jQuery('#cmbkznmKeyid').combobox('getValue');
		if(kznmKeyid.length>0)
			gridData += '&saveMode=U';	
		
		return gridData; 
}*/

function frmImprovementPrj_beforeDelete()
{ 
	
	return true;
}



function frmImprovementPrj_beforeSubmit(){     
	var frmMode=jQuery('#frmMode').val();
    var roleName=jQuery('#hdnRoleName').val();
    var kznId=jQuery("#cmbkznmKeyid").val();
    var fipreq=jQuery('#chkkznmfipRequired').is(':checked');
    var fipno=getFieldValue("cmbkznmFipNumber");
    var IndustryReq=jQuery('#chkkznmIndustryReq').is(':checked');
    var IndustryCategory=getFieldValue("cmbkznmIndustry");
    var hdkaizenid=jQuery('#txtKznmKeyid').val();
    var categorythm=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
	var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var locationId=jQuery("#hdnlocationId").val();
	//mano
	// alert(1);
	var emppillar = jQuery("#hdnEMPillar").val();
	var ds = "?&type=Emppillar" + "&keyid=" + kznId;  
	 // alert(2);
	if(emppillar == "Y"){
	    if (jQuery('#chkkznmUtiliseforfuture').is(':checked') == true){
	        ds += "&value=Y";
	    } else if (jQuery('#chkkznmUtiliseforfuture').is(':checked') == false){
	        ds += "&value=N";
	    }
	    
	    processAjaxCalls("kaizen_update.kaizen", ds, "updateUtiliseSuccess", "");
	    return false;
	}
	// alert(3);
	//mano
    if(benefitype=='GE5' || benefitype=='GE1C'){
    	 if(locationId=='LCN0000001'){   
    	   var kpi=jQuery("#cmbkznmKpiid").combobox('getValue');
    	 //  alert(kpi);
    	   var benchMark=jQuery("#txtkznmBenchmark").val();
    	   var target=jQuery("#txtkznmTarget").val();
    	   if(kpi.length==0){
    		  popupCommonErrorMsg("Enter the KPI");
    		  return false;
    	   } 
    	   if(benchMark.length==0){
    		   popupCommonErrorMsg("Enter the Base Line");
    		   return false;
    	   }
    	   if(target.length==0){
    		   popupCommonErrorMsg("Enter the Target"); 
    		   return false;
    	   }
    }
    }
    // alert(3);
    if((frmMode=="view")){//mode=="Approval" ||  frmMode=="APPROVAL"||  &&(roleName!="FINANCE")
		popupCommonErrorMsg("Kaizen Can not be save data in " + frmMode + " Mode. ");
		return false;
	}
    
    // alert(4);
    if((frmMode!="view")||(!frmMode!="APPROVAL"))
    {
    	
    if(categorythm===null||categorythm.length==0)
    	{
    	popupCommonErrorMsg("Select Theme Category");
    	return false;
    	}
    }
	if (fipreq==true){
		if(fipno.trim().length==0){
			popupCommonErrorMsg(" Select FI Project ");
			return false;
	     }
	}
	
	if(benefitype==null || benefitype.length==0){
		popupCommonErrorMsg("Select Benefit Type");
		return false;
	}
	
	var emppillar= jQuery("#hdnEMPillar").val();
    var ds = "?&type=Emppillar"+"&keyid="+kznId;  
    
    if(emppillar=="Y"){
		if (jQuery('#chkkznmUtiliseforfuture').is(':checked')==true ){
			 ds += "&value=Y";
		}else if (jQuery('#chkkznmUtiliseforfuture').is(':checked')==false ){
			 ds += "&value=N";
		}
	 	
		processAjaxCalls("kaizen_update.kaizen",ds,"updateUtiliseSuccess","");
		return false;
	}
    
   // alert(5);
   	    
	//var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var cost=jQuery('#txtKznmBenefitvalue').val();
	
	if(benefitype=="LE5" || benefitype=="GE5" ||  benefitype=="GE1C"){
		
	  if( cost.trim() == "" ||cost.trim() == "-" ){
		  alert(" Enter Benefit Amount ");
		  setFocusOnField("txtKznmBenefitvalue");
		  return false;
	  }
	}
	//alert(6);
	
	if(benefitype=="GE5")
		{
		var fleName=jQuery('#fileName').val();
		
		if(cost.trim()<=500000)   
		// if(cost.length<=5)
		    	{
		    	
		    	alert("Amount Should be Greater than 500000");
				return false;
		         }
		
		}
	
	//alert(7);
	
	if(benefitype=="GE1C")
	{
	var fleName=jQuery('#fileName').val();
	
	if(cost.trim()<10000000)   
	// if(cost.length<=5)
	    	{
	    	
	    	alert("Amount Should be Greater than 10000000");
			return false;
	         }
	
	}
	
	//alert(8);

	
           //analysis
	/*var iswhywhy=jQuery('#chkkznmIswhywhy').is(':checked');
	if(iswhywhy == true){
		 jQuery('#chkkznmIswhywhy').val('Y');
			setFocusOnField("btnKznWhyWhy");
		}else{
				setFocusOnField("txtKznmAnalysis");
			}*/
			//alert(9);     
    var rtrndata;
	rtrndata=jQuery('#chkkznmfipRequired').val(); 
    if (jQuery('#chkkznmfipRequired').is(':checked'))
   	    rtrndata = "&value=Y";
    else 
		rtrndata = "&value=N";
    var rtrnIndustrydata;
    rtrnIndustrydata=jQuery('#chkkznmIndustryReq').val(); 
    if (jQuery('#chkkznmIndustryReq').is(':checked'))
    	rtrnIndustrydata = "&value=Y";
    else 
    	rtrnIndustrydata = "&value=N";
    
    //alert(10);
    if(glbKznmStatus=="A" && glbKznmApprovallevel=="-")
    	rtrndata+="&status="+glbKznmStatus+"&Approvallevel="+glbKznmRoleName;
    else
    	rtrndata+="&status="+glbKznmStatus+"&Approvallevel="+glbKznmApprovallevel;
	 
    //alert(rtrndata);
	return rtrndata;
}

function updateUtiliseSuccess(result){
    alert(result.msg);
    navigateToPrevForm();
}

function updatestatusUtiliseSuccess(result){
    //alert(result.msg);
    //navigateToPrevForm();
}



function frmImprovementPrj_Delete()
{       
	
	var hdnEMPillar= jQuery("#hdnEMPillar").val();
    var Mode=jQuery('#mode').val();
	var frmMode=jQuery('#frmMode').val();

	if(frmMode=="APPROVAL"||frmMode=="view"){//mode=="Approval" ||
		popupCommonErrorMsg("Kaizen Can not be delete data in " + frmMode + " Mode. ");
		return false;
	}
	
	if (hdnEMPillar.length>0 ){
		return false;
    }

}

function kznGraphDataOnload()
{
	if(!(jQuery('#mode').val().trim()=="view"||jQuery('#mode').val().trim()=="completion"||jQuery('#hdnFrmActionMode').val().trim()=="Completed"))
	{
		var graphType=jQuery("#khzGraphDataGrid").getCell(1,"txtkzgdCharttype");
		if(graphType=="L")
			{jQuery('#chkLineGraph').prop("checked","checked");jQuery('#chkBarGraph').prop("checked",false);}
		else if(graphType="B")
			{jQuery('#chkBarGraph').prop("checked","checked");jQuery('#chkLineGraph').prop("checked",false);}
			
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


function convertToJSONGraphArr(jqGridId){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var Row = allRows[0];
	var afterRow = allRows[1];
	var colCnt = 0;
	var forboth=0;
	var jsonArrO='[';
	
	for(var colName in Row) {
		if(Row[colName].trim()=="0" && afterRow[colName].trim() =="0")
		{
			forboth++;
		}
		
		if( colCnt++ > 1){
			jsonArrO += '{';
			jsonArrO += '"'+"txtkzgdDatemonthyear" +'":"'+"01-" + colName +'",'; 
			
			if(Row[colName].substring(0,6)!='<input')
			{//alert('inside');
				jsonArrO += '"'+colName +'":"' + Row[colName].replace(/(\r\n|\n|\r)/gm,"") +'",'; 
			}
			else
			{
				//alert("else");
				var x=Row[colName].indexOf("id=")+4;
				//alert("x="+x);
				var y=Row[colName].substring(x);
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
			
			jsonArrO += '"'+"txtkzgddata" +'":"' + Row[colName] +'",';
			jsonArrO += '"'+"txtkzgdAfterdata" +'":"' + afterRow[colName] +'",';
			if(jQuery('#chkBarGraph').is(':checked')==true)
				jsonArrO += '"'+"txtkzgdCharttype" +'":"'+"B"+'",'; 
			else if(jQuery('#chkLineGraph').is(':checked')==true)
				jsonArrO += '"'+"txtkzgdCharttype" +'":"' + "L"+'",';
			else	
			   jsonArrO += '"'+"txtkzgdCharttype" +'":"' + Row.txtkzgdCharttype +'",'; 
			
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		} 
	}

	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert(jsonArrO);
	if(forboth > 0)
		return "";
	else
		return jsonArrO;
}

function btnfilemgr_click()
{
  	var keyid = jQuery('#txtKznmKeyid').val();
  	//alert(keyid +"keyidkeyidkeyid");
  	var frmMode=jQuery('#frmMode').val();
  	if(keyid.trim().length<=0){
  		saveForm('frmImprovementPrj','kaizen_save.kaizen?filemanager=filemanager'+'&status='+glbKznmStatus);
  	}else if(keyid != null && keyid != ''){
  		//alert("frmMode f:"+frmMode);
  		apMode = "create";
		if(frmMode=="view")
		   apMode = "view";
		    //alert("apMode f:"+apMode);
		    //alert("keyid :"+keyid);
			fileManagerPopUp(keyid,"KZN","","","",apMode);
	    }
  			
}


function frmImprovementPrj_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	setFunctionalLocWidth('frmImprovementPrj','680px');
	
	var actPillar = jQuery('#cmbKznmActivitypillarid').combobox('getValue');
    var locnId = jQuery("#frmImprovementPrj input[id='location']").val();
    
	var cellId = keyIds.cellId;
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?q&cellId="+cellId);
	//reloadCombo("frmImprovementPrj","cmbkznmFipNumber","Kazennoname_combo.kaizen");
	
	loadWorkFlow();
	jQuery('#kznHDGrid').trigger("reloadGrid");
	
	if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() !=null)
	{
		setTimeout(function() {setFieldValue("cmbkznmMachineid",keyIds.machId);},500);
	}

	jQuery('#kznmfunLocation').append('<div id="divhide1" style="position:absolute;top:0;left:20px;width:50%;z-index:2;opacity:0.4;height:20%;"> </div>');

	glbKznmStatus = jQuery('#txtStatus').val();
	glbKznmRoleName=jQuery('#txtApprovLevel').val();
	
	if (( locnId!=null && locnId=='LCN0000005') && (actPillar=='' || actPillar ==' '||actPillar!=null || actPillar.trim().length>0) ) {
		
		setFieldValue("cmbKznmActivitypillarid",  "TGT004");
        jQuery("#cmbKznmActivitypillarid").combobox("disable");
        
    }
	//alert(glbKznmStatus);
	var fipreq=jQuery("#hdnchkfipreq").val();
	if (fipreq=="Y"){
		jQuery('#chkkznmfipRequired').prop('checked', true);
		jQuery('#lblFIProject').addClass('mandatory-lbl');
	}

}

function divKaizenSheet_afterClose(){
	var jqGridId="KZBankGrid";
	var sugg=jQuery("#hdnSuggestnid").val();
	var row=jQuery("#KZBankGrid").jqGrid('getDataIDs');
	 for(i=1;i<=row.length;i++)	
	 {		 
	 var rowId=parseInt(i);
	 var colVal =jQuery("#KZBankGrid").jqGrid("getCell", i,5);
	// alert("The row number:"+rowId+", ColVal:"+colVal);
	 if(colVal==sugg){
		disableUIButton("btnKznIdeaSheet_"+rowId+'_10');
		jQuery("#KZBankGrid").jqGrid('setCell',rowId,"KAIZEN","",{'color':'#000','font-size':'12px','background-color':'green'});
	 }	
	 }
	}
function filecallsuccessCallBack(){
	//alert(sucess);
}
function frmImprovementPrj_successsCallback(result)
{
	 var kznKeyid=result.successData.kznKeyid;
	 if(kznKeyid!=null){
		 var sugg=jQuery("#hdnSuggestnid").val();
		 closePopUpDialoge("divKaizenSheet");
	 }
	/*  var fileName=jQuery("#fileName").val();
	
	 if(fileName!=null||fileName!=""||fileName.length>0){
		
		var url= "file_save.dcm?filename="+fileName+"&notModify=N&hdnDmdmRefdocno="+result.successData.kznKeyid+"&hdnDmdmRefdoctype=KZN";
		processAjaxCalls(url,"","filecallsuccessCallBack","errorCallBack");

	 } */ 
	 jQuery('#txtKznmKeyid').val(kznKeyid);
     var modes = result.successData.mode;
     var type=result.successData.Type;
     if(type=="workflow"){
         workFlowSubmit(result.successData.record,false);
     }else{
    	
     var persistentData = result.persistentData;
	 var forwardData = result.forwardData;
     //alert(Object.keys(persistentData)+" persistentData :: "+Object.keys(forwardData));
	 var savemode=result.saveMode;	 
	 //alert(" kznKeyid :: "+kznKeyid);
	 var filemanager=result.successData.filemanager;
	 //alert(" filemanager :: "+filemanager);
	 var openactnpln=result.successData.openactnpln;
	 //alert(" openactnpln :: "+openactnpln);
	 
	 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
      
	 var hdkaizenkeyid = jQuery('#txtKznmKeyid').val();
	 jQuery('#cmbkznmKeyid').combobox('setValue',kznKeyid);
	 
		if(modes == "whywhy")
		{	//alert(1234);
			
		    var keyid = jQuery('#txtKznmKeyid').val();
		    var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
			var flid = getFieldValue('flid','frmImprovementPrj');
			
			//var problem = getFieldValue("txtkznmPresentproblem");
			var problem = escape(encodeURIComponent(getFieldValue("txtkznmPresentproblem")));
			
			if (problem=="" || problem==" ") 
				problem = "-";
			var refDocDate = jQuery('#dtekznmDate').datebox('getValue');
			openWhyWhy("divWhyWhy",false,keyid,"KZN",flid, refDocDate, problem, "create",null,null,'');
			//navigateToNextForm("whywhy_input.why",result.formHeader,forwardData,persistentData);
			//openWhyWhy();
			return false;
			
		}  else if(modes != null && modes == "hdScan" )
		{
			// jQuery("#hdnhdScan").val('');
			 
		//	 navigateToNextForm("hrzdplymnt_input.hrzdply","Horizontal Deployment",forwardData,persistentData );
		//	 return false;
			 
			 jQuery("#hdnhdScan").val('');
			   // alert("hdkaizenkeyid:::"+hdkaizenkeyid);
			 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
			// alert("The Flid:::"+flid);
			 var cellkeyid = jQuery("#frmImprovementPrj input[id='cell']").val();
			// alert("The Cellid:::"+cellkeyid);
			 navigateToNextForm("hrzdplymnt_input.hrzdply?q=2&hdkaizenkeyid="+hdkaizenkeyid+"&flid="+flid+"&cellkeyid="+cellkeyid,"Horizontal Deployment",forwardData,persistentData );
			 return false;
	    }
		
		else{
			 if( modes != null && modes == "whywhy")
			 {
				 //openWhyWhy();
				 //navigateToNextForm("whywhy_input.why",result.formHeader,forwardData,persistentData);
			 }	 

		 }
		
		if(openactnpln==true){
			//var keyid = jQuery('#txtKznmKeyid').val();
			//alert(" keyid :: action "+keyid);
			
		 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
		 var mainTask= getFieldValue("txtkznmTheme");
		 //alert(" mainTask :: "+mainTask);
		 openActionPlan("KZNActionPlan",kznKeyid,"KZN",flid,mainTask,kznKeyid);
		 return false;
			
		}
		
		if(filemanager==true){
			
			//var keyid = jQuery('#txtKznmKeyid').val();
			//alert(" keyid :: file "+keyid);
			fileManagerPopUp(kznKeyid,"KZN","","","");		
			return false;
		}

		var rowId = jQuery("#frmImprovementPrj input[id=hdnRowId]").val();

		closePopUpDialoge("KaizenApp");
		//jQuery("#kaizenApproval").trigger("reloadGrid");
		jQuery('#list').jqGrid('setCell',rowId,"IMPRVDATE","",{'color':'#000','font-size':'12px','background-color':'green'});

		//clearForm('frmImprovementPrj');
		//navigateToPrevForm();
    }
}

/*
function openWhyWhy() {
	alert(" Inside Why Why :: ");
	//var keyid = jQuery('#cmbkznmKeyid').combobox('getValue');
	var keyid = jQuery('#txtKznmKeyid').val();
	var flid = getFieldValue('flid','frmImprovementPrj');
	var problem = getFieldValue("txtkznmTheme");
	
	if (problem=="" || problem==" ") 
		problem = "-";
	var refDocDate = jQuery('#dtekznmDate').datebox('getValue');
	//alert(refDocDate);
	openWhyWhy("divWhyWhy",false,keyid,"KZN",flid, refDocDate, problem, "create");
}
*/

function kaizenOnload()
{
	// resultImage();
	 //afterImage();
	 //presentImage();
	
	 /* if(!(jQuery('#chkHdRequiredY').is(':checked')))
	 {	  jQuery("#btnnavigateHd").prop('readonly','readonly');
	 		disableUIButton('btnnavigateHd');
	  	  //jQuery('#chkHdRequiredN').prop('checked','checked');
	 }else if((jQuery('#chkHdRequiredY').is(':checked'))){
		 enableUIButton('btnnavigateHd');
	 }*/
		 
	 if(!(jQuery('#chkIdeagroupindividualG').is(':checked')))
	 {
		 jQuery("#individualImg").show();
		 jQuery('#refType').addClass("refTypepadding");
	  	 jQuery('#chkIdeagroupindividualI').prop('checked','checked');
	  	 if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
			{
				//processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
			}
	 }
	  if(!(jQuery('#chkWoRequiredR').is(':checked')))
		  jQuery('#chkWoRequiredS').prop('checked','checked');
}

function loadWorkFlow() { 
	var benselval=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	
	//alert(" flid :: "+flid);
	
	var benTypeVal="";
	
	if(benselval=="GE5")
		benTypeVal="BTSG5L";
	else if(benselval=="NS")
		benTypeVal="BTSNOSAVIN";
	else if(benselval=="S")
		benTypeVal="BTSSAFETY";
	else if(benselval=="LE5")
		benTypeVal="BTSL5L";
	else if(benselval=="GE1C")
		benTypeVal="BTSG1C";
	
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	//alert(kaizenId);
    var minDate=getFieldValue('dtekznmDate');
    var Mode=jQuery('#mode').val();
    var frmMode=jQuery('#frmMode').val();
   // alert("Mode workflow :" + Mode);
  //  alert("frmMode workflow :" + frmMode);
    if(frmMode=="view" && frmMode.trim().length>0)
    	workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate,"","N");
    else if(frmMode=="modify" && frmMode.trim().length>0)
    	workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate,"","N");
    else
    	workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate);

}

function BTSG5L_beforeSubmit()
{
	
	//alert("BTSG5L");
	var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var frmMode=jQuery('#frmMode').val();
	 var roleName=jQuery('#hdnRoleName').val();
	 //var fileName=jQuery('#fileName').val();
	//alert("benefitype:"+benefitype);
	//alert("frmMode:"+frmMode);
	//alert("roleName:"+roleName);
	benefitype = benefitype ? benefitype.trim() : "";
	frmMode    = frmMode ? frmMode.trim() : "";
	roleName   = roleName ? roleName.trim() : "";

	//alert("benefitype: [" + benefitype + "]");
	//alert("frmMode: [" + frmMode + "]");
	//alert("roleName: [" + roleName + "]");
	
	if(benefitype==="GE5" && roleName==="FINANCE" && frmMode==="APPROVAL")
		{
	//	alert("check");
		var verify=jQuery("#txtkznmVerifyamount").val();
		verify = verify ? verify.trim() : "";
		//alert("verify:"+verify);
		//alert(verify.length);
		 if(verify.length==0||verify==null)
			 {
		      alert("Enter Verify Amount");
		      return false;
			 }
		 
		}
	

}

function BTSG1C_beforeSubmit()
{
	
	//alert("BTSG5L");
	var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var frmMode=jQuery('#frmMode').val();
	 var roleName=jQuery('#hdnRoleName').val();
	 var fileName=jQuery('#fileName').val();
	
	if( benefitype=="GE1C" &&roleName=="FINANCE"&&frmMode=="APPROVAL")
		{
		var verify=jQuery("#txtkznmVerifyamount").val();
		verify = verify ? verify.trim() : "";
		 if(verify.length==0||verify==null)
			 {
		      alert("Enter Verify Amount");
		      return false;
			 }
		}
	

}


function BTSL5L_beforeSubmit()
{
	
	//alert("BTSL5L");
	
	var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
	var benefitval=jQuery("#txtKznmBenefitvalue").val();
	var frmMode=jQuery('#frmMode').val();
	 var roleName=jQuery('#hdnRoleName').val();
	//alert(123+"1"+benefitval);
	if(benefitype=="LE5"&&roleName=="PBU HEAD"&&frmMode=="APPROVAL")
		{
		//alert("inside the if");
		var verify=jQuery("#txtkznmVerifyamount").val();
		verify = verify ? verify.trim() : "";
		//alert("1"+verify+"11"+verify.length);
		 if(verify.length==0||verify==null)
			 {
		      alert("Enter Verify Amount");
		      return false;
			 }
		}
	//return false;

}

jQuery("#btnswitchtoupload").click(function(){
		var caption="Kiazen Upload";
		var kznKeyid = jQuery('#hdnkznKeyid').val();
		var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
  	    var date = jQuery('#dtekznmDate').datebox('getValue');
		var themecategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
		var problem = unescape(encodeURIComponent(getFieldValue("txtkznmPresentproblem")));
	    var countermeasure=unescape(encodeURIComponent(getFieldValue("txtkznmCountermeasure")));
	    var actPillar = jQuery('#cmbKznmActivitypillarid').combobox('getValue');
		var KznmKzbnkeyid=jQuery("#hdnKznmKzbnkeyid").val();
		var Status=jQuery("#hdnKznmStatus").val();
		var kznmApprovellevel=jQuery("#hdnApprovLevel").val();
	    var costhrproduct=jQuery("#txtKznmCostperhour").val();
	    var costhrequipment=jQuery("#txtKznmCostperequipment").val();
	    var benefitvalue=jQuery("#txtKznmBenefitvalue").val();
	    var benefitype=jQuery('#cmbKznmBenefittype').combobox('getValue');
		var Mode=jQuery('#mode').val();
	var ds ="?&filterButton=false&kznKeyid="+kznKeyid+"&flid="+flid+"&date="+date+"&themecategory="+themecategory+"&problem="+problem;
	ds= ds+"&countermeasure="+countermeasure+"&actPillar="+actPillar+"&KznmKzbnkeyid="+KznmKzbnkeyid+"&Status="+Status+"&kznmApprovellevel="+kznmApprovellevel;
	ds= ds+"&costhrproduct="+costhrproduct+"&costhrequipment="+costhrequipment+"&benefitvalue="+benefitvalue+"&benefitype="+benefitype+"&Mode="+Mode;
	navigateToNextForm("KaizenUpload_input.kznUpd"+ds);
})

function kaizenFrmLoad()
{
	 //jQuery('#imgkznmAfterimage').prop('src', "");
	 //jQuery('#imgKznAfterImgFilename').val("");
	 //jQuery('#imgKznmPresentimage').prop('src', "");
	 //jQuery('#imgKznPresentImgFilename').val("");
	 //jQuery('#imgkznmResultimage').prop('src', "");
	 //jQuery('#imgKznResultImgFilename').val("");
	 
	 var MstKeyid=jQuery('#txtKznmKeyid').val();
	 
	 if(MstKeyid.length==0){
	 fillWithCurrentDate("dtekznmDate");
	 fillWithCurrentDate("dtekznmStartdate");
	 fillWithCurrentDate("dtekznmEnddate");
	 }
	 
	 fillWithCurrentMonth("dteRfrommonth");
	 fillWithCurrentMonth("dteRtomonth");
	// fillWithCurrentDate("dtekznmApproveddate");
	 jQuery("#cmbkznmRelatedto").combobox("setValue","MCH");
	 jQuery('#cmbkznmMouldid').combobox('disable');
	 jQuery("#KznGraphData").clearGridData();
	 //jQuery("#kznYYGrid").clearGridData();
	 jQuery("#kznHDGrid").clearGridData();
	 var Mode=jQuery('#mode').val();
	 var flid="";
	 if(Mode=="create"){
		 flid = jQuery('#hdnKZNBFlid').val();
		 //alert('create'+flid);
		 loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&flid="+flid);
	  }else {
		  flid = jQuery("#frmImprovementPrj input[id='flid']").val();
		//alert('else'+flid);
	    loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&flid="+flid);
	  }

	 
	 var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznCategory"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznLossNo"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectionFlag"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectLossFlag"," ");
		} 

		var hdnEMPillar= jQuery("#hdnEMPillar").val();
		
		if(hdnEMPillar == "Y"){
			if (jQuery('#chkkznmIsworthformp').is(':checked')==true ){
				if (hdnEMPillar.length>0 ){
					disableForm("frmImprovementPrj");
					enableFields("chkkznmUtiliseforfuture");
					enableUIButton('btnActionPlan');
					enableUIButton('btnExcelVw');
					disableField("frmImprovementPrj", "txtKznmBenefitvalue");	
				}
			}else{
				jQuery('#chkkznmUtiliseforfuture').prop({'checked':false});
				enableFields("chkkznmUtiliseforfuture");
				//disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
			}
		}

	    /* if (jQuery('#chkkznmIsworthformp').is(':checked')==true ){
			if (hdnEMPillar.length>0 ){
				disableForm("frmImprovementPrj");
				enableFields("chkkznmUtiliseforfuture");
				enableUIButton('btnActionPlan');
				enableUIButton('btnExcelVw');
				disableField("frmImprovementPrj", "txtKznmBenefitvalue");	
			}
		}else{
			jQuery('#chkkznmUtiliseforfuture').prop({'checked':false});
			disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
		} */

	    var frmName=jQuery('#hdnfrmName').val();
		
        if(frmName=="kznEvtn"){
			enableUIButton('btnActionPlan');
			enableUIButton('btnExcelVw');
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			disableField("frmImprovementPrj","cmbKznmActivitypillarid");
			disableField("frmImprovementPrj","cmbKznmActivitypillarid");
			disableField("frmImprovementPrj","cmbkznmKpiid");
		}
				
}

function  frmImprovementPrjcmbkznmMachineid_onSelect(record)
{
	//alert(" Record :: "+record.id);
	loadFunctionalLocation("kznmfunLocation","functionalLoc.kaizen","kznmfunLocationValues","frmImprovementPrj","&machId="+record.id);
	reloadCombo("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilte?machineId="+ record.id );
 	
}

function  frmImprovementPrjcmbkznmFipNumber_onSelect(record){
	reloadCombo("frmImprovementPrj","cmbkznmFipNumber","Kazennoname_combo.kaizen");
}

function  frmImprovementPrjcmbkznmKpiid_onSelect(record)
{
	var KEYID=record.id;
	//alert(" Checking Now :: "+KEYID);
	processAjaxCalls("kaizen_recall.kaizen?&KEYID="+KEYID,"","recallsuccessCallBack","errorCallBack");
	
}

function  frmImprovementPrjcmbKznmThemecategoryid_onSelect(record)
{
	var KEYID=record.id;
	var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();

	var kzbnkznm=jQuery('#hdnkznKeyid').val();
	
	var znmKeyid=jQuery('#txtKznmKeyid').val();
	
	
	var thmcategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
	var newbenid=jQuery('#txtNewBenefit').val();
	
	if(thmcategory.length!=0||thmcategory!=null)
		{
	var r=confirm("Do you want change the Benefit Area and Theme Catgeory");
	 if(r==true)
		 {
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		
		// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		 }
	 else{
		 jQuery("#cmbKznmThemecategoryid").combobox('clear');
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
	  var locnId = jQuery("#frmImprovementPrj input[id='location']").val();
	if(locnId !=null && locnId !='LCN0000005'){
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	}
	jQuery('#chkResultAreaP').prop('checked', false);
	jQuery('#chkResultAreaQ').prop('checked', false);
	jQuery('#chkResultAreaC').prop('checked', false);
	jQuery('#chkResultAreaD').prop('checked', false);
	jQuery('#chkResultAreaS').prop('checked', false);
	jQuery('#chkResultAreaM').prop('checked', false);
	jQuery('#chkResultAreaE').prop('checked', false);
	jQuery('#hdnResultAreaP').val("");
	jQuery('#hdnResultAreaQ').val("");
	jQuery('#hdnResultAreaC').val("");
	jQuery('#hdnResultAreaD').val("");
	jQuery('#hdnResultAreaS').val("");
	jQuery('#hdnResultAreaM').val("");
	jQuery('#hdnResultAreaE').val("");
	

	if(chkValue=="P"){
		jQuery('#chkResultAreaP').prop('checked', true);
		jQuery('#hdnResultAreaP').val(chkValue);
		
	}else if(chkValue=="Q"){
		jQuery('#chkResultAreaQ').prop('checked', true);
		jQuery('#hdnResultAreaQ').val(chkValue);
	}else if(chkValue=="C"){
		jQuery('#chkResultAreaC').prop('checked', true);
		jQuery('#hdnResultAreaC').val(chkValue);
	}else if(chkValue=="D"){
		jQuery('#chkResultAreaD').prop('checked', true);
		jQuery('#hdnResultAreaD').val(chkValue);
	}else if(chkValue=="S"){
		jQuery('#chkResultAreaS').prop('checked', true);
		jQuery('#hdnResultAreaS').val(chkValue);
	}else if(chkValue=="M"){
		jQuery('#chkResultAreaM').prop('checked', true);
		jQuery('#hdnResultAreaM').val(chkValue);
	}else if(chkValue=="E"){
		jQuery('#chkResultAreaE').prop('checked', true);
		jQuery('#hdnResultAreaE').val(chkValue);
	}

	jQuery('#chkResultAreaP').prop('disabled', true);
	jQuery('#chkResultAreaQ').prop('disabled', true);
	jQuery('#chkResultAreaC').prop('disabled', true);
	jQuery('#chkResultAreaD').prop('disabled', true);
	jQuery('#chkResultAreaS').prop('disabled', true);
	jQuery('#chkResultAreaM').prop('disabled', true);
	jQuery('#chkResultAreaE').prop('disabled', true);
	//setFieldValue('hdnNewBenefit',chkValue);
//	var KEYID=result[0][0];
	
//	var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();
	
	// var chkValue=result[0][1];
	
	// processAjaxCalls("kaizenCategory_update.kaizen?&KEYID="+KEYID+"&KzbnkznmKeyid="+kzbnkznmKeyid+"&chkValue="+chkValue,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
}
jQuery("#txtKznmBenefitvalue").focusout("blur", function() {
	 
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cmbKznmBenefittype').combobox('getValue');
	if(valbenefittype=="GE5"||valbenefittype=="GE1C"){
	jQuery('#fileName').show();
	jQuery("#fileName").addClass("mandatory-lbl");
	var url="amountInwrds_input.kaizen";
	  LoadPopUp("KaizenBenAmt",url+'?valSavings='+valSavings , true,"45%","55%","4%","1%", "popup_callback()","Amount in Words","",false,true);	 
	}
	}); 

function recallsuccessCallBack(result){  //alert(" Result :: "+result[0][0]+" Result :: 1 "+result[0][1]+" Result :: 2 "+result[0][2]);
   
   var thmcategory=jQuery("#cmbKznmThemecategoryid").combobox('getValue');
   //alert("thmcategory"+thmcategory.length);
   
   if(thmcategory.length==0)
	   {
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
     
	var chkValue=result[0][1];
	//alert(" chkValue :: "+chkValue);
	
	jQuery('#chkResultAreaP').prop('checked', false);
	jQuery('#chkResultAreaQ').prop('checked', false);
	jQuery('#chkResultAreaC').prop('checked', false);
	jQuery('#chkResultAreaD').prop('checked', false);
	jQuery('#chkResultAreaS').prop('checked', false);
	jQuery('#chkResultAreaM').prop('checked', false);
	jQuery('#chkResultAreaE').prop('checked', false);
	
	jQuery('#hdnResultAreaP').val("");
	jQuery('#hdnResultAreaQ').val("");
	jQuery('#hdnResultAreaC').val("");
	jQuery('#hdnResultAreaD').val("");
	jQuery('#hdnResultAreaS').val("");
	jQuery('#hdnResultAreaM').val("");
	jQuery('#hdnResultAreaE').val("");
	
	
	if(chkValue=="P"){
		jQuery('#chkResultAreaP').prop('checked', true);
		jQuery('#hdnResultAreaP').val(chkValue);
	}else if(chkValue=="Q"){
		jQuery('#chkResultAreaQ').prop('checked', true);
		jQuery('#hdnResultAreaQ').val(chkValue);
	}else if(chkValue=="C"){
		jQuery('#chkResultAreaC').prop('checked', true);
		jQuery('#hdnResultAreaC').val(chkValue);
	}else if(chkValue=="D"){
		jQuery('#chkResultAreaD').prop('checked', true);
		jQuery('#hdnResultAreaD').val(chkValue);
	}else if(chkValue=="S"){
		jQuery('#chkResultAreaS').prop('checked', true);
		jQuery('#hdnResultAreaS').val(chkValue);
	}else if(chkValue=="M"){
		jQuery('#chkResultAreaM').prop('checked', true);
		jQuery('#hdnResultAreaM').val(chkValue);
	}else if(chkValue=="E"){
		jQuery('#chkResultAreaE').prop('checked', true);
		jQuery('#hdnResultAreaE').val(chkValue);
	}
	   }
		
	//jQuery('txtkznmTheme').val(result[0][2]);
	//setFieldValue('txtUpsdRawmaterial',result[0][2]);
	/*setFieldValue('cmbUpsdDefect',result[0][3]);
	setFieldValue('txtUpsdInformto',result[0][4]);
	setFieldValue('txtUpsdCorrectionaction',result[0][5]);
	setFieldValue('txtUpsdPreventiveaction',result[0][6]);
    */
}

function gotFocus(id){ 	
	numericTextBox(id);
}

function saveKaizen(wfStatus,lastLevel, nextLevel)
{
	

	if((wfStatus == "A" ||wfStatus == "E"||wfStatus == "R") ){//&& lastLevel == "Y"){
		var status="";
	     if(wfStatus == "A" && lastLevel == "Y")
	    	 status="C";
	     else if(wfStatus == "A")
	    	 status="A";
	     else if(wfStatus == "E") {
	    	 status="E";
	    	 nextLevel="REWORK";
	     }else if(wfStatus == "R") {
	    	 status="R";
	     }
	     
	     glbKznmStatus= status;
	     glbKznmApprovallevel=nextLevel; 
	     var mpvalue=jQuery('#chkkznmIsworthformp').is(':checked');
	    	 
	     if(nextLevel.trim().length==0)
	    	 nextLevel="-";
	     
	     var keyid=jQuery('#txtKznmKeyid').val();
	     var verifyamnt=jQuery('#txtkznmVerifyamount').val();
	     //alert("KeyID"+keyid);
		 ds = "?&status="+status+"&keyid="+keyid+"&nextLevel="+nextLevel;
		 
	     if (jQuery('#chkkznmIsworthformp').is(':checked')==true ){
			 ds += "&mpvalue=Y";
		}else if (jQuery('#chkkznmIsworthformp').is(':checked')==false ){
			 ds += "&mpvalue=N";
		}
	     
	     ds+="&veramnt="+verifyamnt;
	    // ds+="&mpvalue="+mpvalue;
	processAjaxCalls("kaizen_update.kaizen",ds,"updateSuccess","");
		 
	}	
	
}
	
function BTSL5L_successCallback(result)
{
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}
function BTSSAFETY_successCallback(result)
{
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}
function successCallback(result)
{
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}
function BTSNOSAVIN_successCallback(result)
{
    
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}


function BTSG5L_successCallback(result)
{
    
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}


function BTSG5L_workFlowLoaded(currentWorkFlowEmpIds,role){
	
	enableVerifyamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"BTSG5L");
	
}
function BTSG1C_successCallback(result)
{
    
	saveKaizen(result.wfStatus,result.lastLevel,result.nextRoleName);
}

function BTSG1C_workFlowLoaded(currentWorkFlowEmpIds,role){
	
	enableVerifyamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"BTSG1C");
	
}

function BTSL5L_workFlowLoaded(currentWorkFlowEmpIds,role){
	
	enableVerifyamount(role);
	afterWorkFlowLoaded(currentWorkFlowEmpIds,role,"BTSL5L");
	
}


function enableVerifyamount(role){
    
    jQuery('#hdnRoleName').val(role);

	if("FINANCE" == role.trim().toUpperCase())
	{
		jQuery("#hdnEnableVerfyAmnt").val("Y");
		enableFields("txtkznmVerifyamount");
		//clear("txtkznmVerifyamount");
		jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
		jQuery("#txtkznmVerifyamount").val("");
		
	}
	
	if("PBU HEAD" == role.trim().toUpperCase())
	{
		var val = jQuery('#cmbKznmBenefittype').combobox('getValue');
		//alert("benifitval:"+val);
		if(val == "LE5"){
			jQuery("#hdnEnableVerfyAmnt").val("Y");
			enableFields("txtkznmVerifyamount");
			
			//clear("txtkznmVerifyamount");
			//jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
			jQuery("#txtkznmVerifyamount").val(jQuery("#txtKznmBenefitvalue").val());
		}
		
		
	}	
}

function BTSG5L_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	
	var verifyamnt=jQuery("#txtkznmVerifyamount").val();

	glbKznmRoleName=roleName;
	saveForm("frmImprovementPrj","kaizen_save.kaizen?&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId+'&status='+glbKznmStatus+"&vamnt="+verifyamnt,"");
	return false;
	
	
}

function BTSG1C_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	
	var verifyamnt=jQuery("#txtkznmVerifyamount").val();

	glbKznmRoleName=roleName;
	saveForm("frmImprovementPrj","kaizen_save.kaizen?&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId+'&status='+glbKznmStatus+"&vamnt="+verifyamnt,"");
	return false;
	
	
}

function BTSSAFETY_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	glbKznmRoleName=roleName;
	saveForm("frmImprovementPrj","kaizen_save.kaizen?&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId+'&status='+glbKznmStatus,"");
	return false;
	
}

function BTSNOSAVIN_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	glbKznmRoleName=roleName;
	saveForm("frmImprovementPrj","kaizen_save.kaizen?&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId+'&status='+glbKznmStatus,"");
	return false;

}

function BTSL5L_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	glbKznmRoleName=roleName;
	var verifyamnt=jQuery("#txtkznmVerifyamount").val();
	saveForm("frmImprovementPrj","kaizen_save.kaizen?&Type=workflow&gridId="+record.gridId+"&rowId="+record.rowId+'&status='+glbKznmStatus+"&vamnt="+verifyamnt,"");
	return false;

}

function GridRefresh_Callback(record){
	var rowId = jQuery("#frmImprovementPrj input[id=hdnRowId]").val();

	closePopUpDialoge("KaizenApp");
	//jQuery("#kaizenApproval").trigger("reloadGrid");
	jQuery('#kaizenApproval').jqGrid('setCell',rowId,"IMPRVDATE","",{'color':'#000','font-size':'12px','background-color':'green'});

}

</script>
<form id="frmImprovementPrj" name="frmImprovementPrj" >
<div id = "wrapperRpt" >

<div id="maindivhght" class="main-cntborder" style="width: 1200px;height:1000px;">
<div style="margin-left:10px;">
<table style="padding-left:0%;">
		<tr>
			<td colspan="2">
			<div  id="frmImprovementPrjFuntKeyIds" >
			<input type="hidden" id="factory" name="cmbKznmFactoryid" value="${requestScope.kznTlMst.kznmFactoryid}"></input>
			<input type="hidden" id="section" name="cmbKznmSectionid" value="${requestScope.kznTlMst.kznmSectionid}"></input>
			<input type="hidden" id="cell" name="cmbKznmCellid" value="${requestScope.kznTlMst.kznmCellid}"></input>
			<input type="hidden" id="machine" name="cmbKznmMachineid" value="${requestScope.kznTlMst.kznmMachineid}"></input>
			
			
			
			<input type="hidden" id="flid" name="cmbKznmFlid" value="${requestScope.kznTlMst.kznmFlid}"  ></input>
			
			
			</div>
							
								
			<div id="kznmfunLocation" style="width: 150%;width: 174%\9;padding-left:0px;padding-left:0px\9;" ></div>
			
		</td>
		
<td valign="bottom">
        <div  style="margin-top:10px;margin-left:56px;">
		<div><label>Date</label></div>
		<div class="easyui-paddingbfpx" >
		        	<input id="dtekznmDate" name="dtekznmDate" class="easyui-datebox" readonly="readonly" style="width:90px;" value="${requestScope.kznTlMst.kznmDate}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} />  
		</div>
		</div>
							
</td>
<td>
<div style="padding-top:4px;padding-left:10px;">
<div  class="easyui-paddingbfpx" ><label>Kaizen No</label> </div>
						
<div class="easyui-paddingbfpx" >
	<span><input id="cmbkznmKeyid" name="cmbkznmKeyid" class="easyui-combobox"  style="width:96px;" value="${requestScope.kznTlMst.kznmKeyid}" readonly="${requestScope.kaizenFormBean.disableImprvNo}" />
	</span>
</div>
</div>
</td>

<td>
	<!-- ch1 -->
<!-- <div>
<span  id="KZNFilemgr" style="padding-left:10px;padding-top:-5px;" >
</span>
</div> -->
</td>
<td >
<div style="padding-left:4px;margin-top:20px;">
      <input id="btnActionPlan" class="easyui-button" type="button"  style="height:25px;width:80px;" name="btnActionPlan" value=" Action Plan "/>
</div>
</td>
</table>
<table style="width: 100%;">
<tr>
<td >

	  <div class="easyui-paddingbfpx" ><label>Requested By</label></div>
           	 <div class="easyui-paddingbfpx">
               <input id="cmbkznmPreparedid" name="cmbkznmPreparedid" readonly="readonly" class="easyui-combobox"  style="width:346px;" value="${requestScope.kznTlMst.kznmPreparedid}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} />
		</div>
	
		<input id="chkIdeagroupindividualI" name="chkIdeagroupindividualI" type="checkbox" value="I"  ${ requestScope.kaizenFormBean.ideagroupindividualI == 'I' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  />
		<input id="hdnIdeagroupindividualI" name="hdnIdeagroupindividualI" type="hidden" value="${ requestScope.kaizenFormBean.ideagroupindividualI == 'I' ? 'I':''}"  ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}  />
		<span Style="padding-left:5px;"> <label>Individual  </label> </span>
		<span style="padding-left: 10px;">
			<input id="chkIdeagroupindividualG" name="chkIdeagroupindividualG" type="checkbox" value="G"  ${ requestScope.kaizenFormBean.ideagroupindividualG == 'G' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   />
			<input id="hdnIdeagroupindividualG" name="hdnIdeagroupindividualG" type="hidden" value="${ requestScope.kaizenFormBean.ideagroupindividualG == 'G' ? 'G':''}"    ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   />
			<span Style="padding-left:5px;"> <label> Group Idea</label> </span>
		</span>
		<span style="padding-left: 20px;">
			<input id="chkKznmCsmValue" name="chkKznmCsmValue" type="checkbox" value="Y"  ${ requestScope.kznTlMst.kznmCsmValue == 'Y' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   />
			<input id="hdnKznmCsmValue" name="hdnKznmCsmValue" type="hidden" value="${ requestScope.kznTlMst.kznmCsmValue == 'Y' ? 'Y':''}"    ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   />
			<span Style="padding-left:5px;"> <label> SCM</label> </span>
		</span>
		
		<div ><!-- style="margin-top:50px;" -->
		
		<input id="chkKznmIcoe" name="chkKznmIcoe" type="checkbox" value="IC"  ${ requestScope.kznTlMst.kznmIcoe == 'IC' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   />
		<input id="hdnKznmIcoe" name="hdnKznmIcoe" type="hidden" value="${ requestScope.kznTlMst.kznmIcoe == 'IC' ? 'IC':''}"    ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   />
		<span Style="padding-left:5px;"> <label>ICoE  </label> </span>
		<span style="padding-left: 35px;">
			<input id="chkKznmPcoe" name="chkKznmPcoe" type="checkbox" value="PC"  ${ requestScope.kznTlMst.kznmPcoe == 'PC' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   />
			<input id="hdnKznmPcoe" name="hdnKznmPcoe" type="hidden" value="${ requestScope.kznTlMst.kznmPcoe == 'PC' ? 'PC':''}"    ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   />
			<span Style="padding-left:5px;"> <label> PCoE</label> </span>
		</span>
		<span style="padding-left: 47px;">
			<input id="chkKznmFip" name="chkKznmFip" type="checkbox" value="FI"  ${ requestScope.kznTlMst.kznmFip == 'FI' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   />
			<input id="hdnKznmFip" name="hdnKznmFip" type="hidden" value="${ requestScope.kznTlMst.kznmFip == 'FI' ? 'FI':''}"    ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   />
			<span Style="padding-left:5px;"> <label> FIP</label> </span>
		</span>
		
		
	</div>
		
		<div id="reference" style="display:none	;">
		<div id="refType" class="easyui-paddingbfpx">
		<label>Ref Type</label><span class="lblr" style="padding-left:110px;"><label>Reference No</label></span></div> 
		<div class="easyui-paddingbfpx"> 
		  <input class="easyui-text" type="text" id="txtkznmRefdoctype" name="txtkznmRefdoctype" maxlength="15"  style="width:156px;" value="${requestScope.refdocType}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}> 
		<span class="floatR2"> 
		<input class="easyui-text" type="text" id="txtkznmRefdocno" name="txtkznmRefdocno" maxlength="20" value="${requestScope.refDocId}" style="width:156px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>
		</span> </div>	
		</div>
</td>

<td valign="top">
<div class="easyui-paddingbfpx" style="margin-top:8px;"><label>Equipment</label></div>
    <div class="easyui-paddingbfpx">
           		<input id="cmbkznmMachineid" name="cmbkznmMachineid1" readonly="readonly" class="easyui-combobox"  style="width:350px;" value="${requestScope.kznTlMst.kznmMachineid}"   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} />
	</div>
	   
	    <input id="chkkznmfipRequired" name="chkkznmfipRequired" type="checkbox"  />
	    <input id="hdnkznmfipRequired" name="hdnkznmfipRequired" type="hidden" disabled=disabled  />  
		<span style="margin-left:10px;"><label id="lblFIProject" class="" style="">FI Project</label></span>
		<span style="magin-left:50px;">
       </span>  	
			
		<span style="margin-left:18px;">	
		<input type="text" id="cmbkznmFipNumber"  name="cmbkznmFipNumber" class="easyui-combobox" style="width:250px;" value="${requestScope.kznTlMst.kznmFipNumber}">
		</span>
	
</td>

<td>
<div style="padding-left:0px;margin-top:-20px;margin-left:-6px;">

<div  style="padding-left:4px;padding-top:10px;">
	<span style="padding-right: 5px;">
			<input type="checkbox" id="chkkznmUtiliseforfuture" name="chkkznmUtiliseforfuture"  ${ requestScope.kznTlMst.kznmUtiliseforfuture == 'Y' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}/>
			<input type="hidden" id="hdnkznmUtiliseforfuture" name="hdnkznmUtiliseforfuture"  value="${ requestScope.kznTlMst.kznmUtiliseforfuture == 'Y' ? 'Y':''}"  ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}/>
	</span>
	<span><label>Utilize For Future Projects</label></span>		
			<span>
			<input type="checkbox" id="chkkznmIsworthformp" name="chkkznmIsworthformp" ${ requestScope.kznTlMst.kznmIsworthformp == 'Y' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''} />
			<input type="hidden" id="hdnkznmIsworthformp" name="hdnkznmIsworthformp" value="${ requestScope.kznTlMst.kznmIsworthformp == 'Y' ? 'Y':''}"  ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'} />
			</span>
	</span>
	<span><label>MP Worthy</label></span>
	
	
	</div>
	
	
	
<div style="padding-top:6px;">
		<label class="mandatory-lbl">Benefit Area</label>

	<span id="resultArea" class="easyui-paddingbfpx" style="padding-left:14px;">
		<input id="chkResultAreaP" name="chkResultAreaP" type="checkbox" value="P" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}  ${ requestScope.kaizenFormBean.resultAreaP == 'P' ? ' checked':''}  /><span Style="padding-left: 2px"><label>P </label></span><span Style="padding-left: 10px"></span>
            						<input id="chkResultAreaQ" name="chkResultAreaQ" type="checkbox" value="Q" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  ${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}/><span Style="padding-left:2px"><label>Q</label> </span><span Style="padding-left: 10px"></span>
    								<input id="chkResultAreaC" name="chkResultAreaC" type="checkbox" value="C" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   ${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}/><span Style="padding-left:2px"><label>C</label></span> <span Style="padding-left: 10px"></span>
									<input id="chkResultAreaD" name="chkResultAreaD" type="checkbox" value="D" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  ${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}/><span Style="padding-left:2px"><label>D</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaS" name="chkResultAreaS" type="checkbox" value="S" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  ${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}/><span Style="padding-left:2px"><label>S</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaM" name="chkResultAreaM" type="checkbox" value="M" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  ${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}/><span Style="padding-left:2px"><label>M</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="E" onclick="chkboxCheck(this.id);"  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  ${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}/><span Style="padding-left:2px"><label>E</label> </span><span Style="padding-left: 10px"></span>
									
									<input id="hdnResultAreaP" name="hdnResultAreaP" type="hidden" />
									<input id="hdnResultAreaQ" name="hdnResultAreaQ" type="hidden" />
    								<input id="hdnResultAreaC" name="hdnResultAreaC" type="hidden"   />
									<input id="hdnResultAreaD" name="hdnResultAreaD" type="hidden"   />
									<input id="hdnResultAreaS" name="hdnResultAreaS" type="hidden"  />
									<input id="hdnResultAreaM" name="hdnResultAreaM" type="hidden"  />
									<input id="hdnResultAreaE" name="hdnResultAreaE" type="hidden"  />
									
	</span>
	</div>
	<div>
<label class="mandatory-lbl">Activity Pillar</label>
</div>
<div class="easyui-paddingbfpx">
		<input id="cmbKznmActivitypillarid" name="cmbKznmActivitypillarid" class="easyui-combobox"  style="width:350px;" value="${requestScope.kznTlMst.kznmActivitypillarid}" />
</div>
	</div>
	</td>
</tr>

<tr>

<td>

<div class="easyui-paddingbfpx"><label id="lblkpi">KPI</label> </div>
	 		<div class="easyui-paddingbfpx">
         		<input id="cmbkznmKpiid" name="cmbkznmKpiid" class="easyui-combobox" style="width:350px;" value="${requestScope.kznTlMst.kznmKpiid}" ></input>
</div>

<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Theme Category</label> </div>
	 		<div class="easyui-paddingbfpx">
         		<input id="cmbKznmThemecategoryid" name="cmbKznmThemecategoryid" class="easyui-combobox" style="width:350px;" value="${requestScope.kznTlMst.kznmThemecategoryid}"></input>
</div>

<div class="easyui-paddingbfpx"><label>Theme</label></div>

<textarea 
    id="txtkznmTheme" 
    name="txtkznmTheme" 
    rows="4" 
    cols="40"
    maxlength="250"
    title="Maximum Length is 250"
    onkeyup="restrict(this.value, this.id)" 
    onpaste="restrictPaste(this.value, this.id)" 
    onkeypress="return alpha(event)"
    ${requestScope.kaizenFormBean.disableForm ? 'readonly' : ''}>
    ${requestScope.kznTlMst.kznmTheme}
</textarea>

<%-- <div class="easyui-paddingbfpx">
	<textarea id="txtkznmTheme" name="txtkznmTheme"  rows="4" cols="40" title="Maximum Length is 250" maxlength="250"  "${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmTheme}</textarea>
</div> --%>
</td>

<td>
<div class="easyui-paddingbfpx" style="margin-top:30px;"><Span><label>ESP Names</label>

</span>
<span>
<input style="margin-left:10px;margin-top:5px;" id="btnswitchtoupload" class="easyui-button" name="btnswitchtoupload"  type="button" value="Switch to Kaizen Upload" style="height:30px;"/>
</span>
 <span  style="margin-left:10px;margin-top:7px;">

	<input class="easyui-button" type="button"   value ="Excel View" id="btnExcelVw" name="btnExcelVw"   style="height:30px"/>

</span> 
</div>
	<div class="easyui-paddingbfpx" style="margin-top:2px;"> 
         <textarea id="txtkznmEspNames" name="txtkznmEspNames" rows="2" cols="28" maxlength="500" title="Maximum Length is 250" style=" width : 350px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.SuggByEsp}</textarea>
	</div>
<div class="sub-header" style="width:342px;margin-top:5px;" >Idea</div>
	<div class="easyui-paddingbfpx" style="margin-top:6px;"> 
         <textarea id="txtkznmIdea" name="txtkznmIdea" rows="4" cols="28" maxlength="250" title="Maximum Length is 250" style=" width : 350px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmIdea}</textarea>
	</div>
</td>


<td valign="top">
	 <div style="">	 
			</div>
			<div>
	<div class="easyui-paddingbfpx" >
			<label id="lblbaseline">Base Line</label>
			<span style="padding-left:5px;"><label id="lblkznmTarget">Target</label><span style="padding-left:20px;"><label>Kaizen Start</label><span style="padding-left:27px;"><label>Kaizen Finish</label></span>
			
			<div style="padding-left:0px;">
		
		<input  class="easyui-text" id="txtkznmBenchmark" name="txtkznmBenchmark" maxlength="6" style="width:50px;text-align:right;" value="${requestScope.kznTlMst.kznmBenchmark} "  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>	
			
	         <span style="padding-left:10px;">	    
			<input  class="easyui-text" id="txtkznmTarget" name="txtkznmTarget" maxlength="6" style="width:50px;text-align:right;" value="${requestScope.kznTlMst.kznmTarget}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>	
			</span>
			<span style="padding-left:0px;"> <input id="dtekznmStartdate" name="dtekznmStartdate" class="easyui-datebox" style="width:80px;" value="${requestScope.kznTlMst.kznmStartdate}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/> </span>
				<span style="padding-left:12px;" > 
				<input id="dtekznmEnddate" name="dtekznmEnddate" class="easyui-datebox" style="width:80px;" value="${requestScope.kznTlMst.kznmEnddate}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
			</span>    		
			<div class="easyui-paddingbfpx" style="width:340px;padding-top:6px;">
			
			<span style="padding-left:50px;padding-top:6px;"></span> 
			</div>
			 <div class="sub-header" style=" width : 380px; margin-bottom:5px; " ><label>Team Members</label></div>
          <div class="easyui-paddingbfpx"> 
            <textarea id="txtkznmTeammembers" name="txtkznmTeammembers" rows="4" cols="43" maxlength="150" title="Maximum Length is 150" style="height:70px;width : 380px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmTeammembers}</textarea>
		  </div>	
			
	
</td>
</tr>
<tr>
<td valign="top">
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header">
		<label class="mandatory-lbl">Problem / Present Status </label>
		</div>
	</div>
	<div class="easyui-paddingbfpx"> 
		<textarea id="txtkznmPresentproblem" name="txtkznmPresentproblem" rows="3" cols="28" maxlength="250" 
		 title="Maximum Length is 250" style=" width : 350px;"  
		  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmPresentproblem}</textarea>
	<table>
	<tr>
	<td>	
	<div id="divImgPresent" style="padding-top:0px;padding-left:0px; ">
	<div style="width: 310px;height: 140px;margin-top:5px; padding-left:-100px;border: 1px solid #a4a4a4; ">
			<img alt=""  id="imgKznmPresentimage"  name="imgKznmPresentimage" src="${requestScope.kznTlMst.kznmPresentimage}"  >
	</div>
	</div>
	</td>	
	<td>
	<div>
	 	<input class="easyui-button" type="button" value ="+" id="dlgImgPresent"style="height:20px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>  
	</div>
	 <div style="margin-top:10px;">   
		<input class="easyui-button" type="button" value ="-" id="btnImgPresentClear"style="height:20px;width:30px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
	</div>
	</td>
    </tr>
    </table>
	</div>
	</div>
	<div title="Analysis" style="padding-right:20px;">
		<div class="easyui-paddingbfpx mndlbl sub-header" style="width:341px;" ><label>Analysis</label></div> 
              	 <div class="easyui-paddingbfpx">       	 
              <span> <input type="checkbox" name="chkkznmIswhywhy" id="chkkznmIswhywhy" value=""  ${ requestScope.kznTlMst.kznmIswhywhy == 'Y' ? ' checked':''}/> 
              <input type="hidden" name="hdnkznmIswhywhy" id="hdnkznmIswhywhy" value="${ requestScope.kznTlMst.kznmIswhywhy == 'Y' ? 'Y':''}" disabled=disabled /></span>
               <input id="cmbkznmWwmsKeyid" name="cmbkznmWwmsKeyid" class="easyui-combobox"  style="width:210px;" value="${requestScope.kznTlMst.kznmWwmsKeyid}" readonly="${requestScope.kaizenFormBean.disablewwmsKeyid}" >
               <input class="easyui-button " type="button" id="btnKznWhyWhy" name="btnKznWhyWhy" value ="..."   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
              
		</div>
	<!---------	For grid ----->
	<div id="kznYYAnswerf">
		<table id="kznYYGrid" style="width:50%"><tr><td/></tr>
		</table>
	</div>
	
		<div id="kznYYPager"></div>
		</div>	
		<div class="easyui-paddingbfpx" id="kznYYAnstxt" >
		
	 <textarea id="txtKznmAnalysis" name="txtKznmAnalysis" rows="5" cols="28" maxlength="500" title="Maximum Length is 500" style="height:200px ;width :350px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${ requestScope.kznTlMst.kznmAnalysis}</textarea>
	</div>
	</div>				
</td>
<td valign="top">
	<div class="easyui-paddingbfpx"  style="width:352px;">
	<div class="sub-header" ><label>Countermeasure</label></div>
    </div>
	<div class="easyui-paddingbfpx"> 
			<textarea id="txtkznmCountermeasure" name="txtkznmCountermeasure" maxlength="250" title="Maximum Length is 250" rows="3" cols="28" style=" width : 350px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmCountermeasure}</textarea>
			<div></div>
			<table>
			<tr>
			<td>
			<div style="width: 310px;height: 140px;margin-top:5px; padding-left:-100px;border: 1px solid #a4a4a4; ">
				<img alt="" id="imgkznmAfterimage" name="imgkznmAfterimage" src="${requestScope.kznTlMst.kznmAfterimage}">
			</div>
			</td>
			<td>
			<div>
			<input class="easyui-button" type="button" value ="+" id="dlgImgAfter"style="height:20px"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
			</div>
			<div style="margin-top:10px;">
			<input class="easyui-button" type="button" value ="-" id="btnImgAfterClear" onclick="" style="height:20px;width:30px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
			</div>
			</td>
			</tr>
			</table>
		</div>
		 <div  class="easyui-paddingbfpx" style="width:400px;"> 
			<div class="sub-header " style="width:342px;" > Result  
			</div>
		
			<textarea id="txtkznmResultdescription" name="txtkznmResultdescription" rows="4" cols="28" title="Maximum Length is 250" maxlength="250" style=" width : 350px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmResultdescription}</textarea>
			<table>
			  <tr>
			    <td>
				<div style="width: 310px;height: 140px;margin-top:5px; padding-left:-100px;border: 1px solid #a4a4a4; ">
					<img alt="" id="imgkznmResultimage" name="imgkznmResultimage"  src="${requestScope.kznTlMst.kznmResultimage}" />
				</div>
				</td>
				<td>
				<div>
						<input class="easyui-button" type="button" value ="+" id="dlgImgResult" onclick="" style="height:20px"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
				</div>	
				<div style="margin-top:10px;">
						<input class="easyui-button" type="button" value ="-" id="btnImgResultClear" style="height:20px;width:30px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} /></span>
				</div>
				</td>
				</tr>
				</table>
	</div>					
</td>
<td valign="top" style="margin-top:-25 px">
		
	<div class="sub-header" style="width:380px;"><label>Benefits</label>
	<span style="padding-left:154px;display:none""><label>Images</label>
	<input class="easyui-button" type="button" value ="+" id="btnImgBenefit" onclick="" style="height:20px;display:none"/></span></div>
	<div class="easyui-paddingbfpx"> 
	        <textarea id="txtkznmBenefits" name="txtkznmBenefits" rows="3" cols="28" maxlength="600" title="Maximum Length is 600" style=" width : 380px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>${requestScope.kznTlMst.kznmBenefits}</textarea>
	</div>
    <table>
	<tr>
	<td>
	<div style="width: 310px;margin-left:0px;height: 140px;border: 1px solid #a4a4a4; ">
			        <img alt=""  id="imgKznmbenefitimage"  name="imgKznmbenefitimage" src="${requestScope.kznTlMst.kznmBenefitsimage}"  >
			        </div>
			        </td>
			        <td>
			        <span style="margin-top:-100px;">
			        <input class="easyui-button" type="button" value ="+" id="dlgImgBenefit"style="height:20px"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
			        <div style="margin-top:10px;">
			        <input class="easyui-button" type="button" value ="-" id="btnImgBenefitClear" onclick="" style="height:20px;width:30px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>
			        </div>
                    </span>
                    </td>
                    </tr>
                    <div>
            <table>        
			<label>HD Required </label> 
			<span Style="padding-left: 10px">
					<input id="chkHdRequiredY" name="chkHdRequiredY" type="checkbox" value="Y"  ${ requestScope.kaizenFormBean.hdRequiredY == 'Y' ? ' checked':''}  ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}   /> <label> Yes</label>
					<input id="chkHdRequiredN"  name="chkHdRequiredN" type="checkbox" value="N"  ${ requestScope.kaizenFormBean.hdRequiredN == 'N' ? ' checked':''}   ${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  />  <label> No</label>
					<input id="hdnHdRequiredY" name="hdnHdRequiredY" type="hidden" value="${ requestScope.kaizenFormBean.hdRequiredY == 'Y' ? 'Y':''}"  ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}   /> 
					<input id="hdnHdRequiredN"  name="hdnHdRequiredN" type="hidden" value="${ requestScope.kaizenFormBean.hdRequiredN == 'N' ? 'N':''}"   ${ requestScope.kaizenFormBean.disableForm ? '' : 'disabled="disabled"'}  />  
			
			</span>
			<span Style="padding-left: 10px">
				<label>HD Nos</label>
			</span>		
			<span>
		        <input class="easyui-text"type="text" id="txtkznmNoofhds" name="txtkznmNoofhds" value="${requestScope.kznTlMst.kznmNoofhds}" style="width:100px;"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} />
		        <input class="easyui-button" type="button" value ="..." id="btnnavigateHd"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}  />
			</span>		
	</div>
	</table>
	<div>
		<table id="kznHDGrid" style="width:100%"><tr><td/></tr></table>
		<div id="kznHDPager"></div>
	</div>
                    
                   </table>
	</div>
	</td>
</tr>

<tr>

<td valign="top" style= "margin-top:-20 px;">
	
			
</td>

<td valign="top">
				</td>
			   </tr>			   	
				</div>		
</td>
</tr>
<tr>
			<tr>
				<td class="easyui-paddingbfpx">
					<div class="easyui-paddingbfpx"></div>
					<div class="easyui-paddingbfpx">		
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					
		     </td>
		   </tr>
		   <tr>
		   <td>
		   <div class="sub-header" style="width:1152px;margin-top:-85px"> Cost Details</div>
					<div class="easyui-paddingbfpx" >
					    <label id="bt" class="mandatory-lbl">Benefit Type</label>
					<span><label style="padding-left: 26px;">Benefit Value</label></span>
						<span><label style="padding-left: 60px;">Cost of Product /hr</label></span>
						<span><label style="padding-left: 46px;">Cost of Equipment /hr</label></span>
						<span><label style="padding-left: 36px;">Total Cost /hr</label></span>
						<span id="lblVerifyAmount"><label style="padding-left: 66px;">Verified Amount</label></span>
					</div> 
               		<div   style="width:1152px;"> 
               		    <select class="easyui-text" id="cmbKznmBenefittype" name="cmbKznmBenefittype" panelHeight=80px;  style="width:  100px; /* height: 21px; */"    ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} onchange='getSelectType(this.value)'>
							<option value='NS'>No Savings</option>
							<%
							if(!"modify".equals(request.getAttribute("frmMode"))){
							
							 %> 
							<option value='S'>Safety</option>
							<%
							}
							%>
							<option value='LE5'><=5 Lakh</option>
							<option value='GE5'>>5 Lakh</option>
							<option value='GE1C'>>=1 Crore</option>
							
						</select>
						
						<input type="button" class="easyui-button" id="btnkpi"	name="btnkpi" style="width:100px;height:21px;display: none;"value="KPI" onclick="openKPIPOP()"   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} />
						<span style="padding-left: 0px" id="spninrbox">
							<input type="text" id="txtKznmBenefitvalue" name="txtKznmBenefitvalue" class="easyui-text"  style=" width : 100px; text-align: right; " value="${requestScope.kznTlMst.kznmBenefitvalue}"  onfocus="gotFocus(this.id)" onchange="noBenfitChange(this.id)"  onkeypress="return validate(event)" align="right"   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/>							
							<label id="inid" style="padding-left: 3px;">(INR)</label>
						</span>
						<span>	<input class="easyui-text" type="text" id="txtKznmCostperhour" name="txtKznmCostperhour"   maxlength="14" value="${requestScope.kznTlMst.kznmCostperhour}"   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/></span>
						<span style="padding-left: 20px;">	<input class="easyui-text" type="text" id="txtKznmCostperequipment" name="txtKznmCostperequipment"  value="${requestScope.kznTlMst.kznmCostperequipment}"  maxlength="7"   ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}/></span>
						<span style="padding-left: 33px;">	<input class="easyui-text" type="text" id="txtBTSTotalcost" name="txtkznmBTSTotalcost" /></span>
				        <span style="padding-left: 16px;">	<input class="easyui-text" type="text" id="txtkznmVerifyamount" name="txtkznmVerifyamount" maxlength="10" value="${requestScope.kznTlMst.kznmVerifyamount}" /></span>
				  		  <div style="padding-left:830px;margin-top:-40px">
				        <span  id="KZNFilemgr" style="padding-left:10px;" ></span>
				  		<!--  <span style="padding-left: 16px;" > <input type="file" name="fileName" id="fileName" /></span> 
						</div>
						<span  style="padding-left:20px; width:220px;height:20px;"  id = "fileUploadDocMgr"></span>
						  --> 

					</div>
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
				   <div id="WorkFlowid" style="display:block;">
                                  
					<div class="sub-header" style="width:1152px;" >Work Flow</div>
						<div id="divKznWorkFlow" style="">
						</div>
				 	<!--  <table id="kznBTSGrid" style="width:100%"><tr><td/></tr></table>
						<div id="kznBTSPager"></div>  -->
						
					</div>
				</td>
			</tr>
		   <tr>
		     <td>
		        <div id="WorkFlowid" style="display:none;">
		        <table id="khzGraphDataGrid" style="width:100%"><tr><td/></tr></table>
			    <div id="khzGraphDataPager"></div>
			    </div>	
			   
			    
		</table>
 </div>
 <input type="hidden" id="imgKznBenefitImgFilename" name="imgKznBenefitImgFilename" value="" />
 <input type="hidden" id="imgKznPresentImgFilename" name="imgKznPresentImgFilename" value="" />
<input type="hidden" id="imgKznAfterImgFilename" name="imgKznAfterImgFilename" value="" />	
<input type="hidden" id="imgKznResultImgFilename" name="imgKznResultImgFilename" value="" />
<input type="hidden" id="hdnlossLinkclickId" name="hdnlossLinkclickId" value="" />	
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>   
<input type="hidden" id="frmMode" name="frmMode" value="${requestScope.frmMode}"/>
<input type="hidden" id="hdnFrmActionMode" value="${requestScope.kaizenFormBean.formActionMode}"/>	  
<input type="hidden" id="hdnbdmode" value="${requestScope.bdmmode}"/>
<input type="hidden" id="hdnflidmode" value="${requestScope.cellId}"/>
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

<input type="hidden" id="hdnFIProject" name="hdnFIProject" value="">
<input type="hidden" id="hdnNewBenefit" name="hdnNewBenefit" value="">
<input type="hidden" id="txtKznmKeyid" name="txtKznmKeyid" value="${requestScope.kznTlMst.kznmKeyid}"/>
<input type="hidden" id="hdnKznmStatus" name="hdnKznmStatus" value="${requestScope.kznTlMst.kznmStatus}"/>

<input type="hidden" id="hdnSuggestedby"	name="hdnSuggestedby" value="${requestScope.Suggestedby}"/>
<input type="hidden" id="hdnflId" value="${requestScope.flId}" />
<input type="hidden" id="hdnKZNBFlid" value="${requestScope.KZNBFlid}" />
<input type="hidden" id="hdnMode" value="${requestScope.Mode}" />
<input type="hidden" id="hdnfrmName" value="${requestScope.frmName}" /> 
<input type="hidden" id="hdnnewBenefit" value="${requestScope.benefit}" /> 
<input type="hidden" id="hdnnewPcdqsme" value="${requestScope.pcdqsme}" /> 
<input type="hidden" id="hdnnewthemename" value="${requestScope.themename}" /> 
<input type="hidden" id="hdnApprovLevel" name="hdnApprovLevel" value="${requestScope.kznTlMst.kznmApprovLevel}"/>
<input type="hidden" id="hdnchkResultArea" name="hdnchkResultArea" value=" " />
<input type="hidden" id="hdnEnableVerfyAmnt" value=" " /> 
<input type="hidden" id="hdnRoleName" name="hdnRoleName" value=" " />
<input type="hidden" id="hdnapprovallevel" name="hdnapprovallevel" value=" " /> 
<input type="hidden" id="hdnchkfipreq" name="hdnchkfipreq" value="${requestScope.chkfipreq}" />	
<div id="rtdtomld" style="width: 330px\9;display: none;">	
<input type="hidden" id="hdnKEType" name="hdnKEType" value="${requestScope.KEType}"/>
<input type="hidden" id="hdnlocationId" name="hdnlocationId" value="${requestScope.locationId}">
<input type="hidden" id="hdnSuggestnid" name="hdnSuggestnid" value="${requestScope.Suggestnid}">
<input type="hidden" id="hdnKznmThemecategoryid" value="${requestScope.ThemeCategory}" />
<input type="hidden" id="ICOC" value="${requestScope.kznTlMst.kznmCsmValue}" />
<!--  TO Upload the finance sheet for greater than 5 Lakh Kaizen -->
<input type="hidden" id="cmbDmdmType"  name="cmbDmdmType" value="DTM1305002"/>
<input type="hidden" id="hdnDmdmRefdoctype" name="hdnDmdmRefdoctype" value="KZN"/> 
<input type="hidden" id="hdnDmdmRefdocno" name="hdnDmdmRefdocno"/>
<!--   END  -->
<input type="hidden" id="hdnBenFitArea" name="hdnBenFitArea" value="${requestScope.benFitArea}">
<input type="hidden" id="hdnThemeName" name="hdnThemeName" value="${requestScope.themeName}">
<input type="hidden" id="hdnIsAuthorizedForEMPillar" name="hdnIsAuthorizedForEMPillar" value="${requestScope.isAuthorizedForEMPillar}"/>
<input type="hidden" id="hdnRowId" name="hdnRowId" value="${requestScope.rowId}">

request.setAttribute("benFitArea",benFitArea);
				request.setattribute("themeName", themeName);
				
<div>
			<span> <label class="mandatory-lbl"> Related to</label></span>
		<span> <label id="lblMld" style="padding-left:52px;">Mould</label></span>
	 </div> 
	<div style="padding-bottom: 8px;padding-right:10px;"> 
			<select id="cmbkznmRelatedto" class="easyui-combobox" name="cmbkznmRelatedto"  style="height: 22px;width:105px;" ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''}>
	<option value="MCH">MACHINE</option>
					<option value="MLD">MOULD</option>							
	  </select> 
	  <input id="relatedToCMB" name="relatedToCMB" type="hidden" value="${requestScope.kznTlMst.kznmRelatedto }"/>
	<input type="text" id="cmbkznmMouldid"  name="cmbkznmMouldid" class="easyui-combobox"  value="${requestScope.kznTlMst.kznmMouldid}"  ${ requestScope.kaizenFormBean.disableForm ? 'readonly="readonly"' : ''} style="width: 195px;"/>
	
		</div>	
	</div>	
</div>
</div>
<div style="margin-left:433px;margin-top:-570px;">
 <input id="chkkznmIndustryReq" name="chkkznmIndustryReq" type="checkbox"  /> 
		<span style="margin-left:10px;"><label id="lblIndustryProject" class="" style="">Industry 4.0</label></span>
	<span style="margin-left:9px;">	
		<input type="text" id="cmbkznmIndustry"  name="cmbkznmIndustry" class="easyui-combobox" style="width:250px;" value="${requestScope.kznTlMst.kznmFipNumber}">
		</span>

</div>

<div  class="KaizenUploadd" style="margin-left:428px;margin-top:-588px;color:green;font-size:40px;"><label><b>Kaizen Upload</b></label> </div>
	
</form>

