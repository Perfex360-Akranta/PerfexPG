<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
var glbKznmStatus ="-";
var glbKznmApprovallevel ="-";
var glbKznmRoleName ="-";
jQuery(document).ready(function(){
	
	initialiseForm('frmImprovementPrj');
	jQuery('#submitForm').val('frmImprovementPrj');
    numericTextBox("txtkznmdelar");
    numericTextBox("txtdistributorcode");
    numericTextBox("txttargetval");
    numericTextBox("txtbenchmarkval");
    numericTextBox("txtticketno");
    numericTextBox("txtbenefitofkznrs");
    numericTextBox("txtcostofkzn");
    formatDateBox('dtekznmStartdate','dd-MMM-yyyy');
    formatDateBox('dtekznmEnddate','dd-MMM-yyyy');
    formatDateBox('dtekznmRegisterdate','dd-MMM-yyyy');

	   var chknewoneid=jQuery('#hdnnewBenefit').val(); 
	  
	   var typebenefit=jQuery('#hdnnewPcdqsme').val(); 
	  
	   var themename=jQuery('#hdnnewthemename').val(); 
	   
	  // setFieldValue("cmbkznmThemecategoryid",chknewoneid);
	  // setFieldValue("txtkznmTheme",themename);
	  // disableField("frmImprovementPrj","cmbkznmThemecategoryid");
{
	  // setFieldValue("txtkznmTheme",chknewoneid);
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
		  disableField("frmImprovementPrj","cmbkznmThemecategoryid");
		}

	var factId = jQuery("#frmImprovementPrj input[id='factory']").val();
	var sectionId = jQuery("#frmImprovementPrj input[id='section']").val();
	var cellId = jQuery("#frmImprovementPrj input[id='cell']").val();
	var machId = jQuery("#frmImprovementPrj input[id='machine']").val();
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
     
	 var funclocn="";
	 funclocn= "functionalLoc.edp";
//	loadFunctionalLocation("frmKaizenideafunloc",funclocn,"kaizenfunLocationValues","frmImprovementPrj",dataStr);
   	loadFunctionalLocation("frmKaizenideafunloc",funclocn,"frmKaizenideafunloc","frmImprovementPrj",dataStr);
    var compId = getFieldValue('company','frmImprovementPrj');
	var locnId = getFieldValue('location','frmImprovementPrj');
	var factId = getFieldValue('factory','frmImprovementPrj');
	var sectId = getFieldValue('section','frmImprovementPrj');
	var cellId = getFieldValue('cell','frmImprovementPrj');
	var machId = getFieldValue('machine','frmImprovementPrj');
	var flid = getFieldValue('flid','frmImprovementPrj');
	

    fillComboBox("frmImprovementPrj","cmbkznmMachineid","machineCombo.commonFilter?q&cellId="+cellId +"&machId="+machId+"&flid="+flid);
    fillComboBox("frmImprovementPrj","cmbKznmActivitypillarid","pillar.commonFilter");
    fillComboBox("frmImprovementPrj","cmbkznmPreparedid","employee.commonFilter");
    fillComboBox("frmImprovementPrj","cmbkznmKpiid","kaizenfillcombo.kaizen?flid="+flid);
    fillComboBox("frmImprovementPrj","cmbkznmThemecategoryid","kaizenactegoryfillcombo.kaizen?flid="+flid);
    fillComboBox("frmImprovementPrj","cmbkznmFipNumber","Kazennoname_combo.kaizen");
    
    var wwmsKey = jQuery('#cmbkznmKeyid').combobox('getValue');
	//var kznFiprojNoname = jQuery('#cmbkznmFipNumber').combobox('getValue');
    fillComboBox("frmImprovementPrj","cmbkznmWwmsKeyid","combo_whywhy.kaizen?wwmsKeyid="+wwmsKey);
    
    var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	
	var request=jQuery("#cmbkznmPreparedid").combobox('getValue');

	var Mode=jQuery('#mode').val();
	var frmMode=jQuery('#frmMode').val();
	var apprvalMode=jQuery('#hdnMode').val();
	var keyid=jQuery('#txtKznmKeyid').val();
	//alert(" Mode :: "+Mode+" apprvalMode :: "+apprvalMode+" frmMode :: "+frmMode);
    if(Mode=="create"){
    	setFieldValue('cmbkznmPreparedid',jQuery('#hdnSuggestedby').val());
    	disableField("frmImprovementPrj","chkkznmIsworthformp");
    }
    
	if(Mode=="modify"||Mode=="view"){
		jQuery('#WorkFlowid').css('display','none');
		jQuery('#maindivhght').css('height','1000');
		disableField("frmImprovementPrj","chkkznmIsworthformp");
		disableField("frmImprovementPrj","txtKznmBenefitvalue");
		disableField("frmImprovementPrj","cmbkznmFipNumber");
		
		var category=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
		
		if(category.length!=0||category!=null)
			{
		jQuery('#chkResultAreaP').attr('disabled', true);
		jQuery('#chkResultAreaQ').attr('disabled', true);
		jQuery('#chkResultAreaC').attr('disabled', true);
		jQuery('#chkResultAreaD').attr('disabled', true);
		jQuery('#chkResultAreaS').attr('disabled', true);
		jQuery('#chkResultAreaM').attr('disabled', true);
		jQuery('#chkResultAreaE').attr('disabled', true);
			}
		//disableField("frmImprovementPrj","chkkznmfipRequired");
		//readOnlyFields("txtKznmBenefitvalue");
	}
	if(Mode=="approval" || Mode=="view"){
		disableField("frmImprovementPrj","btnnavigateHd");
		disableField("frmImprovementPrj","cmbkznmKpiid");
		disableField("frmImprovementPrj","cmbKznmActivitypillarid");
		//readOnlyFields("txtKznmBenefitvalue");
		//enableFields("chkkznmIsworthformp");
		disableField("frmImprovementPrj","chkkznmIswhywhy");
		disableField("frmImprovementPrj","txtKznmAnalysis");
		disableField("frmImprovementPrj","cmbkznmFipNumber");
		disableField("frmImprovementPrj","chkkznmfipRequired");
		disableField("frmImprovementPrj","cmbkznmThemecategoryid");
		jQuery('#WorkFlowid').css('display','block');
    	jQuery('#maindivhght').css('height','1000');
    	readOnlyFields("txtKznmBenefitvalue");
        var category=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
		
		if(category.length!=0||category!=null)
			{
		jQuery('#chkResultAreaP').attr('disabled', true);
		jQuery('#chkResultAreaQ').attr('disabled', true);
		jQuery('#chkResultAreaC').attr('disabled', true);
		jQuery('#chkResultAreaD').attr('disabled', true);
		jQuery('#chkResultAreaS').attr('disabled', true);
		jQuery('#chkResultAreaM').attr('disabled', true);
		jQuery('#chkResultAreaE').attr('disabled', true);
			}
    	//disableField("frmImprovementPrj","txtKznmBenefitvalue");
	}
	
	if(apprvalMode=="Approval")
	{
	   enableFields("chkkznmIsworthformp");
	}
	
    if(((Mode=="create" && frmMode=="approval"))){//alert(12);
    	
        jQuery('#WorkFlowid').css('display','block');
    	jQuery('#maindivhght').css('height','1000');
    	
    }

	 var wwmsKeyid = jQuery('#cmbkznmKeyid').combobox('getValue');
 

	imageUpload(jQuery("#divImgCurr" ),'ImageUpload.commonFilter','divImgCurr',"imgKznmPresentimage","imgKznPresentImgFilename","310","140",false);
    imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgkznmAfterimage","imgKznAfterImgFilename","310","140",false);
 	imageUpload(jQuery( "#dlgImgResult" ),'ImageUpload.commonFilter','dlgImgResult',"imgkznmResultimage","imgKznResultImgFilename","310","140",false);
 	imageUpload(jQuery( "#dlgImgBenefit" ),'ImageUpload.commonFilter','dlgImgBenefit',"imgKznmbenefitimage","imgKznBenefitImgFilename","310","140",false);


//	jQuery('#frmImprovementPrj .easyui-text').css('text-transform', 'uppercase');
//	jQuery('#frmImprovementPrj textarea').css('text-transform', 'uppercase');
	formatDateBox('dtekznmDate','dd-MMM-yyyy');
	formatDateBox('dtekznmStartdate','dd-MMM-yyyy');
	formatDateBox('dtekznmEnddate','dd-MMM-yyyy');
	

	kaizenFrmLoad();
	
	
	fileManagerPopUp("","KZN","frmImprovementPrj","btnfilemgr","KZNFilemgr");
	
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
			 //alert(" mainTask :: "+mainTask);
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
				jQuery('#chkkznmIswhywhy').attr('checked',true);	
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
		//jQuery("#chkkznmIswhywhy").attr("checked",true);
		//jQuery("#kznYYAnstxt").hide();
		   jQuery( "#chkkznmIswhywhy" ).click(function() {
		    	//var chkVal = getFieldValue('chkkznmIswhywhy');
		    	if(jQuery('#chkkznmIswhywhy').is(':checked')==true){
		    		//jQuery("#chkkznmIswhywhy").attr("checked",true);
		    		//jQuery("#chkkznmIswhywhy").val();
		    		enableUIButton('btnKznWhyWhy');
		    		jQuery("#kznYYAnswerf").show();
		    		jQuery("#kznYYAnstxt").hide();
					}
		    	else {
		    		//jQuery("#chkkznmIswhywhy").attr("checked",false);
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
			 jQuery('#chkHdRequiredN').attr('checked',false);
		 }
		 else if((!jQuery('#chkHdRequiredY').is(':checked')))
		 {	  
		 	  if (jQuery('#chkHdRequiredN').is(':checked'))
		 	  {
		 		 jQuery('#chkHdRequiredN').attr('checked',true);
		 	  }else{
		 	     jQuery('#chkHdRequiredY').attr('checked',true);
		 	  }
		 }
		
	});
	
	
	
	
	
	
	
	
	
	jQuery( "#chkHdRequiredN" ).click(function() {
		
		if((jQuery('#chkHdRequiredN').is(':checked')))
		 {	  //jQuery("#btnnavigateHd").attr('disabled','disabled');
		 	  disableUIButton('btnnavigateHd');
		 	  jQuery('#chkHdRequiredY').attr('checked',false);
		 }
		 else if((!jQuery('#chkHdRequiredN').is(':checked')))
		 {	  
		 	  if (jQuery('#chkHdRequiredY').is(':checked'))
		 	  {
		 		 jQuery('#chkHdRequiredY').attr('checked',true);
		 	  }else{
		 	     jQuery('#chkHdRequiredN').attr('checked',true);
		 	  }
		 }
		
	});
	
	jQuery( "#chkIdeagroupindividualI" ).click(function() {
		
		 if((jQuery('#chkIdeagroupindividualI').is(':checked'))){
			 jQuery('#chkIdeagroupindividualG').attr('checked',false);
		 }
		 
		 else if((!jQuery('#chkIdeagroupindividualI').is(':checked')))
		 {	  
		 	  if (jQuery('#chkIdeagroupindividualI').is(':checked'))
		 	  {
		 		 jQuery('#chkIdeagroupindividualI').is(':checked');
		 	  }else{
		 	     jQuery('#chkIdeagroupindividualI').attr('checked',true);
		 	  }
		 }
		
	});
	
	jQuery( "#chkIdeagroupindividualG" ).click(function() {
		
		if((jQuery('#chkIdeagroupindividualG').is(':checked')))
		 {	  
		 	  jQuery('#chkIdeagroupindividualI').attr('checked',false);
		 }
		
		else if((!jQuery('#chkIdeagroupindividualG').is(':checked')))
		 {	  
		 	  if (jQuery('#chkIdeagroupindividualI').is(':checked'))
		 	  { 
		 		 jQuery('#chkIdeagroupindividualI').is(':checked');
		 	  }else{ 
		 	 jQuery('#chkIdeagroupindividualG').attr('checked',true);
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
	
	
	if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val().trim() !='')
	{		  
			   jQuery("#cmbkznmRelatedto").combobox('setValue',jQuery("#relatedToCMB").val());
			   fillComboBox("frmImprovementPrj","cmbkznmMouldid","mould.commonFilter");
			  
	}

	 if(jQuery('#cmbkznmRelatedto').combobox('getValue')=="MLD")
		   jQuery('#cmbkznmMouldid').combobox('enable');
	   else 
		   jQuery('#cmbkznmMouldid').combobox('disable');
		   		  
		
	//jQuery("#chkLineGraph").attr({'checked':true});
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
		        if (!/^\d{0,6}(?:\.\d{0,2})?$/.test(this.value)) {
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
			        if (!/^\d{0,6}(?:\.\d{0,2})?$/.test(this.value)) {
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
	 	var benselval=jQuery("#cboKznmBenefittype").val();
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
		jQuery('#imgKznmPresentimage').attr('src', " ");
	});
	
	jQuery( "#btnImgAfterClear" ).click(function() {
		processAjaxCalls("kznAfterImageClear.kaizen","&afterImage=after");
		jQuery('#imgKznAfterImgFilename').val(" ");
		jQuery('#imgkznmAfterimage').attr('src', " ");
	});
	jQuery( "#btnImgBenefitClear" ).click(function() {
		processAjaxCalls("kznBenefitImageClear.kaizen","&benefitImage=null");
		jQuery('#imgKznBenefitImgFilename').val(" ");
		jQuery('#imgKznmbenefitimage').attr('src', " ");
	});
	jQuery( "#btnImgResultClear" ).click(function() {
		processAjaxCalls("kznResultImageClear.kaizen","&resultImage=result");
		jQuery('#imgkznmResultimage').attr('src', " ");
		jQuery('#imgKznResultImgFilename').val(" ");
	});
	
	
	jQuery("#imgkznmAfterimage").load(function() {
		
		
		/*if((jQuery(this).width()>415)||(jQuery(this).height()>264))
		{	jQuery('#imgkznmAfterimage').attr('src', " ");
		 	jQuery('#imgKznAfterImgFilename').val(" ");
			alert('Select Image with width not greater than 8.1 cms and height not greater than 3.8 cms');
			return false;
		}*/
   });

	/*jQuery("#imgKznmPresentimage").load(function() {
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
		
   });*/

	var valbenefittype = jQuery('#hdnBenefittype').val();
   //alert("valbenefittype"+valbenefittype);
	jQuery("#frmImprovementPrj select[id= cboKznmBenefittype]").attr('value',valbenefittype);
	if(valbenefittype.length>0){
		if(!(valbenefittype.contains("NS"))){
			getSelectType(valbenefittype);
			CalculateBTS();	
			if(valbenefittype=="GE5")
				{
				jQuery("#txtKznmBenefitvalue").attr('maxlength','8');
				}
			jQuery('#txtKznmBenefitvalue').val(jQuery('#hdnBenefitvalue').val());
		}else{
			jQuery("#btnkpi").hide();
			//disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").attr('maxlength','8');
			jQuery("#spninrbox").css('padding-left','0');
			
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
		    
	 		readOnlyFields("txtKznmBenefitvalue");
	 		//disableField("frmImprovementPrj","txtKznmBenefitvalue");
	 		
	 	 }

	 
	    var benefitype=jQuery('#cboKznmBenefittype').val();
	    
	    if(benefitype=="NS" ){
			readOnlyFields("txtKznmBenefitvalue");
		}
	    
	 
});



function data_successCallBack(result){  

	/*if((jQuery('#chkIdeagroupindividualG').attr('checked','checked'))){
		jQuery('#chkIdeagroupindividualI').attr('checked',false);
	    setFieldValue('txtkznmTeammembers',result[0]);
	}else if((jQuery('#chkIdeagroupindividualI').attr('checked','checked'))){
		jQuery('#chkIdeagroupindividualI').attr('checked',false);
	}*/
	
	var Mode=jQuery('#mode').val();
	
	if(Mode=="create"){
	   //setFieldValue('txtkznmTeammembers',result[0]);
	}
}


function  frmImprovementPrjcmbkznmMachineid_onSelect(record){
	
 	//loadFunctionalLocation("frmKaizenideafunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmImprovementPrj","&machId="+record.id);
}

function getSelectType(value){
	//alert("value"+value);
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
		//jQuery("#btnkpi").show();
		//alert("mode"+mode);
		if(mode=="view" || mode =="completion")
			{
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
			jQuery("#spninrbox").css('padding-left','20');
			jQuery("#inid").text('(INR)');
			jQuery("#txtKznmBenefitvalue").attr('maxlength','10');
			}
		else
			{
			
		enableFields("txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','10');
			}
		}
	else if(value=="LE5"){   
		jQuery("#txtKznmBenefitvalue").attr('maxlength','6');
		jQuery("#btnkpi").hide();
		if(mode!="view" && mode !="completion")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmImprovementPrj", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','6');
		}
	else if(value=="S"){
		disableField("frmImprovementPrj", "txtKznmBenefitvalue");
	}
	else{
		//jQuery("#btnkpi").hide(); 
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
	var valbenefittype = jQuery('#cboKznmBenefittype').val();
	
	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be lesser than or equal to 500000");
		return false;
	}

	if(valbenefittype=="GE5" && parseInt(valSavings)<=500000){
	jQuery('#txtKznmBenefitvalue').val("");
	alert("Amount Should be greater than 500000");
	return false;
}
}


function validateBTS(){
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cboKznmBenefittype').val();

	if(valbenefittype=="LE5" && parseInt(valSavings)>500000){
		jQuery('#txtKznmBenefitvalue').val("");
		alert("Amount Should be lesser than or equal to 500000");
		return false;
	}
}


function validate(){
	var valSavings = jQuery('#txtKznmBenefitvalue').val();
	var valbenefittype = jQuery('#cboKznmBenefittype').val();

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


function frmImprovementPrj_beforeSubmit()
{     
	//var Mode=jQuery('#mode').val();
	var frmMode=jQuery('#frmMode').val();
    var roleName=jQuery('#hdnRoleName').val();
    var kznId=jQuery("#cmbkznmKeyid").val();
    var fipreq=jQuery('#chkkznmfipRequired').is(':checked');
    var fipno=getFieldValue("cmbkznmFipNumber");
    var hdkaizenid=jQuery('#txtKznmKeyid').val();
    var categorythm=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
   // alert("categorythm"+categorythm);
   // alert("frmMode"+frmMode);
	
        
    if((frmMode=="view")){//mode=="Approval" ||  frmMode=="APPROVAL"||  &&(roleName!="FINANCE")
		popupCommonErrorMsg("Kaizen Can not be save data in " + frmMode + " Mode. ");
		return false;
	}
    
    
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
    

   	    
	//var benefitype=jQuery('#cboKznmBenefittype').val();
	var cost=jQuery('#txtKznmBenefitvalue').val();
	if(benefitype=="LE5" || benefitype=="GE5"){
		
	  if( cost.trim() == "" ||cost.trim() == "-" ){
		  alert(" Enter Benefit Amount ");
		  setFocusOnField("txtKznmBenefitvalue");
		  return false;
	  }
	}
	
	if(benefitype=="GE5")
		{
		    if(cost.length<=5)
		    	{
		    	alert("Amount Should be Greater than or equal to 500000");
				return false;
		         }
		
		}
	
	
           //analysis
	/*var iswhywhy=jQuery('#chkkznmIswhywhy').is(':checked');
	if(iswhywhy == true){
		 jQuery('#chkkznmIswhywhy').val('Y');
			setFocusOnField("btnKznWhyWhy");
		}else{
				setFocusOnField("txtKznmAnalysis");
			}*/
		      
    var rtrndata;
	rtrndata=jQuery('#chkkznmfipRequired').val(); 
    if (jQuery('#chkkznmfipRequired').is(':checked'))
   	    rtrndata = "&value=Y";
    else 
		rtrndata = "&value=N";
	  		
    if(glbKznmStatus=="A" && glbKznmApprovallevel=="-")
    	rtrndata+="&status="+glbKznmStatus+"&Approvallevel="+glbKznmRoleName;
    else
    	rtrndata+="&status="+glbKznmStatus+"&Approvallevel="+glbKznmApprovallevel;
	 
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
  	var frmMode=jQuery('#frmMode').val();
  	//alert(" frmMode :: "+frmMode);
  	if(keyid.trim().length<=0){
  		saveForm('frmImprovementPrj','kaizen_save.kaizen?filemanager=filemanager'+'&status='+glbKznmStatus);
  	}else if(keyid != null && keyid != ''){
  		
  		apMode = "create";
		if(frmMode=="view")
		   apMode = "view";
			fileManagerPopUp(keyid,"KZN","","","",apMode);
	    }		
}


function frmImprovementPrj_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	
	var flid = jQuery("#frmImprovementPrj input[id='flid']").val();
	setFunctionalLocWidth('frmImprovementPrj','720px');
	
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
		jQuery('#chkkznmfipRequired').attr('checked', true);
		jQuery('#lblFIProject').addClass('mandatory-lbl');
	}

}


function frmImprovementPrj_successsCallback(result)
{
	 var kznKeyid=result.successData.kznKeyid;
	 jQuery('#txtKznmKeyid').val(kznKeyid);
	 
     var modes = result.successData.mode;
     var type=result.successData.Type;
     //alert(" result.successData :: alone "+result.successData);
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
		
		//clearForm('frmImprovementPrj');
		navigateToPrevForm();
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
	 {	  jQuery("#btnnavigateHd").attr('disabled','disabled');
	 		disableUIButton('btnnavigateHd');
	  	  //jQuery('#chkHdRequiredN').attr('checked','checked');
	 }else if((jQuery('#chkHdRequiredY').is(':checked'))){
		 enableUIButton('btnnavigateHd');
	 }*/
		 
	 if(!(jQuery('#chkIdeagroupindividualG').is(':checked')))
	 {
		 jQuery("#individualImg").show();
		 jQuery('#refType').addClass("refTypepadding");
	  	 jQuery('#chkIdeagroupindividualI').attr('checked','checked');
	  	 if(jQuery('#cmbkznmPreparedid').combobox('getValue') != null && jQuery('#cmbkznmPreparedid').combobox('getValue') != '')
			{
				//processAjaxCalls("get_empImage.emp","?q=2&empId="+jQuery('#cmbkznmPreparedid').combobox('getValue') ,"getEmpImgSuccess","getEmpImgErr");
			}
	 }
	  if(!(jQuery('#chkWoRequiredR').is(':checked')))
		  jQuery('#chkWoRequiredS').attr('checked','checked');
}

function loadWorkFlow() { 
	var benselval=jQuery("#cboKznmBenefittype").val();
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
	
	var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	//alert(kaizenId);
    var minDate=getFieldValue('dtekznmDate');
    var Mode=jQuery('#mode').val();
    if(Mode=="view" && Mode.trim().length>0)
    	workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate,"","N");
    else
    	workFlow("divKznWorkFlow",false,benTypeVal,kaizenId,"KZNBTS",flid,minDate);

}

function BTSG5L_beforeSubmit()
{
	
	//alert("BTSG5L");
	var benefitype=jQuery('#cboKznmBenefittype').val();
	var frmMode=jQuery('#frmMode').val();
	 var roleName=jQuery('#hdnRoleName').val();
	//alert(123);
	if(benefitype=="GE5"&&roleName=="FINANCE"&&frmMode=="APPROVAL")
		{
		var verify=jQuey("#txtkznmVerifyamount").val();
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
	
	var benefitype=jQuery('#cboKznmBenefittype').val();
	var benefitval=jQuery("#txtKznmBenefitvalue").val();
	var frmMode=jQuery('#frmMode').val();
	 var roleName=jQuery('#hdnRoleName').val();
	//alert(123);
	if(benefitype=="LE5"&&roleName=="PBU HEAD"&&frmMode=="APPROVAL")
		{
		//alert("inside the if");
		var verify=jQuey("#txtkznmVerifyamount").val(benefitval);
		 if(verify.length==0||verify==null)
			 {
		      alert("Enter Verify Amount");
		      return false;
			 }
		}
	//return false;

}



function kaizenFrmLoad()
{
	 //jQuery('#imgkznmAfterimage').attr('src', "");
	 //jQuery('#imgKznAfterImgFilename').val("");
	 //jQuery('#imgKznmPresentimage').attr('src', "");
	 //jQuery('#imgKznPresentImgFilename').val("");
	 //jQuery('#imgkznmResultimage').attr('src', "");
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
		  flid = jQuery('#hdnKZNBFlid').val();
		  var funclocn="";
			 funclocn= "functionalLoc.edp";
		  loadFunctionalLocation("frmKaizenideafunloc",funclocn,"frmKaizenideafunloc","frmImprovementPrj","&flid="+flid);
		  // loadFunctionalLocation("kznmfunLocation","functionalLoc.edp","kznmfunLocationValues","frmImprovementPrj","&flid="+flid);
	 
	 var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznCategory"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"kznLossNo"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectionFlag"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectLossFlag"," ");
		} 

		var hdnEMPillar= jQuery("#hdnEMPillar").val();

	    if (jQuery('#chkkznmIsworthformp').is(':checked')==true ){
			if (hdnEMPillar.length>0 ){
				disableForm("frmImprovementPrj");
				enableFields("chkkznmUtiliseforfuture");
				enableUIButton('btnActionPlan');
				enableUIButton('btnExcelVw');
				disableField("frmImprovementPrj", "txtKznmBenefitvalue");	
			}
		}else{
			jQuery('#chkkznmUtiliseforfuture').attr({'checked':false});
			disableField("frmImprovementPrj", "chkkznmUtiliseforfuture");
		}

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
	loadFunctionalLocation("kznmfunLocation","functionalLoc.edp","kznmfunLocationValues","frmImprovementPrj","&machId="+record.id);
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

function  frmImprovementPrjcmbkznmThemecategoryid_onSelect(record)
{
	var KEYID=record.id;
	
	var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();

	var kzbnkznm=jQuery('#hdnkznKeyid').val();
	
	var znmKeyid=jQuery('#txtKznmKeyid').val();
	
	
	var thmcategory=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
	
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
	  var locnId = jQuery("#frmImprovementPrj input[id='location']").val();
	if(locnId !=null && locnId !='LCN0000005'){
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	}
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


function recallsuccessCallBack(result){  //alert(" Result :: "+result[0][0]+" Result :: 1 "+result[0][1]+" Result :: 2 "+result[0][2]);
   
   var thmcategory=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
   //alert("thmcategory"+thmcategory.length);
   
   if(thmcategory.length==0)
	   {
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
     
	var chkValue=result[0][1];
	//alert(" chkValue :: "+chkValue);
	
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
		jQuery("#txtkznmVerifyamount").val(" ");
		
	}
	
	if("PBU HEAD" == role.trim().toUpperCase())
	{
		jQuery("#hdnEnableVerfyAmnt").val("Y");
		enableFields("txtkznmVerifyamount");
		//clear("txtkznmVerifyamount");
		//jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
		jQuery("#txtkznmVerifyamount").val(jQuery("#txtKznmBenefitvalue").val());
		
	}	
}

function BTSG5L_Submit(refId,empId,flId,refRoleId,status,roleName,record){
	
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
</script>
<form id="frmImprovementPrj" name="frmImprovementPrj" >
<div id = "wrapperRpt" >

<div id="maindivhght" class="main-cntborder" style="width: 1200px;height:1064px;">
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
        <div id="frmKaizenideafunloc"   style="width:100%;" ></div>	
		</td>
		
<td valign="bottom">
     
							
</td>
<td>

 <div style="padding-top:4px;padding-left:184px;">
<div  class="easyui-paddingbfpx" ><label>FORMAT NO.</label> </div>

<div class="easyui-paddingbfpx" style="margin-left:77px;margin-top:-23px;">
<input class="easyui-text" id="txtformatNo" name="txtformatNo"  style="width: 106px; height: 21px;" value=""/>			       
</div>						

</div>

<div style="padding-top:4px;padding-left:184px;">
<div  class="easyui-paddingbfpx" ><label>VERSION NO.</label> </div>

<div class="easyui-paddingbfpx" style="margin-left:77px;margin-top:-23px;">
<input class="easyui-text" id="txtVersonNo" name="txtVersonNo"  style="width: 106px; height: 21px;" value=""/>			       
</div>						

</div>

<div style="padding-top:4px;padding-left:184px;">
<div  class="easyui-paddingbfpx" ><label>REVISION NO.</label> </div>

<div class="easyui-paddingbfpx" style="margin-left:77px;margin-top:-23px;">
<input class="easyui-text" id="txtVersonNo" name="txtVersonNo"  style="width: 106px; height: 21px;" value=""/>			       
</div>						

</div>


</td>

</table>

<table style="width: 100%;">
<tr>



<td >
<div style="margin-top:-4px;margin-left:650px;">
<div style="margin-top:6px;">
 <label>ACTIVITY PILLAR</label>
	<span id="resultArea" class="easyui-paddingbfpx" style="margin-left:4px;">
            						<input id="chkResultAreaQ" name="chkResultAreaQ" type="checkbox" value="KK" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>JH</label> </span><span Style="margin-left: 2px"></span>
    								<input id="chkResultAreaC" name="chkResultAreaC" type="checkbox" value="JH" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}" /> <c:out value = "${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>KK</label></span> <span Style="margin-left:2px"></span>
									<input id="chkResultAreaD" name="chkResultAreaD" type="checkbox" value="QM" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>QM</label> </span><span Style="margin-left: 2px"></span>
									<input id="chkResultAreaS" name="chkResultAreaS" type="checkbox" value="E&T" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>E&T</label> </span><span Style="margin-left:2px"></span>
									<input id="chkResultAreaM" name="chkResultAreaM" type="checkbox" value="PM" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>PM</label> </span><span Style="margin-left:2px"></span>
									<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="SHE" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>SHE</label> </span><span Style="margin-left:2px"></span>
	                                <input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="OTPM" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>OPTM</label> </span><span Style="margin-left:2px"></span>
	                               	<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="DM" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>DM</label> </span><span Style="margin-left:2px"></span>
	                                <input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="SYSTEM" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>SYSTEM</label> </span><span Style="margin-left:2px"></span>                     
	</span>
	</div>
	
<div style="padding-top:6px;">
<label class="mandatory-lbl">BENEFIT AREA</label>
	<span id="resultArea" class="easyui-paddingbfpx" style="padding-left:14px;">
		<input id="chkResultAreaP" name="chkResultAreaP" type="checkbox" value="P" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaP == 'P' ? ' checked':''}"/>  /><span Style="padding-left: 2px"><label>P </label></span><span Style="padding-left: 10px"></span>
            						<input id="chkResultAreaQ" name="chkResultAreaQ" type="checkbox" value="Q" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>Q</label> </span><span Style="padding-left: 10px"></span>
    								<input id="chkResultAreaC" name="chkResultAreaC" type="checkbox" value="C" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}" /> <c:out value = "${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>C</label></span> <span Style="padding-left: 10px"></span>
									<input id="chkResultAreaD" name="chkResultAreaD" type="checkbox" value="D" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>D</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaS" name="chkResultAreaS" type="checkbox" value="S" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>S</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaM" name="chkResultAreaM" type="checkbox" value="M" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>M</label> </span><span Style="padding-left: 10px"></span>
									<input id="chkResultAreaE" name="chkResultAreaE" type="checkbox" value="E" onclick="chkboxCheck(this.id);" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> <c:out value = "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/>/><span Style="padding-left:2px"><label>E</label> </span><span Style="padding-left: 10px"></span>
	</span>
	</div>
	
	</div>
	
	
	
	
	</td>
</tr>

<tr>
<td>

<div class="easyui-paddingbfpx" style="margin-top:-61px;"><label>DEALEAR CODE</label></div>
<div class="easyui-paddingbfpx">
<input class="easyui-text" id="txtdealercode" name="txtdealercode"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div class="easyui-paddingbfpx" style="margin-left:200px; margin-top:-46px;"><label>DISTRIBUTORE CODE</label></div>
<div class="easyui-paddingbfpx" style="margin-left:200px";>
<input class="easyui-text" id="txtdistributorcode" name="txtdistributorcode"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div class="easyui-paddingbfpx" style="margin-left:410px; margin-top:-46px;"><label>AREA OF KAIZEN:</label></div>
<div class="easyui-paddingbfpx" style="margin-left:410px";>
<input class="easyui-text" id="txtareakaizen" name="txtareakaizen"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div class="easyui-paddingbfpx"><label>LOCATION OF DEALERSHIP</label></div>
<div class="easyui-paddingbfpx">
	<textarea id="txtkznmdelar" name="txtkznmdelar"  rows="1" cols="25" title="Maximum Length is 50" maxlength="50" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
</div>


<table>
<tr>
<td>
<div class="easyui-paddingbfpx" style="margin-left:900px;margin-top:-55px;"><label>AVERAGE VOLUME / MONTH</label></div>
<div class="easyui-paddingbfpx"  style="margin-left:610px;margin-top:-100px;">
<div  style="margin-left:286px;margin-top:100px;"><label>Sales</label></div>
<div  style="margin-left:286px;margin-top:2px;">
<input class="easyui-text" id="txtsales" name="txtsales"  style="width: 190px; height: 21px;" value=""/>
</div>
<div  style="margin-left:285px;margin-top:5px;"><label>Services</label></div>
<div  style="margin-left:286px;margin-top:5px;">
<input class="easyui-text" id="txtservices" name="txtservices"  style="width: 190px; height: 21px;" value=""/>			       
</div>
</div>
</tr>
</td>
</table>


<div class="easyui-paddingbfpx" style="margin-top:-55px;"><label >KPI</label> </div>
	 		<div class="easyui-paddingbfpx">
         		<input id="cmbkznmKpiid" name="cmbkznmKpiid" class="easyui-combobox" style="width:350px;" value="${requestScope.kznTlMst.kznmKpiid}" ></input>
</div>

<div class="easyui-paddingbfpx"><label>THEME</label></div>
<div class="easyui-paddingbfpx">
	<textarea id="txtkznmTheme" name="txtkznmTheme"  rows="1" cols="40" title="Maximum Length is 250" maxlength="250" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
</div>

<div class="sub-header" style="margin-left:0px;width:342px;">
<label class="easyui-paddingbfpx" >BACKGROUND</label></div>
<div class="easyui-paddingbfpx">
	<textarea id="txtkznmBackground" name="txtkznmBackground"  rows="4" cols="40" title="Maximum Length is 250" maxlength="250" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
</div>




    <table>
    <tr>
    <td> 
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header" style="margin-left:365px;height:19px;width:200px;">
	<label class="mandatory-lbl">Target Condition</label>
	</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:365px;"> 
		<textarea id="txtkznmtargetcondition" name="txtkznmtargetcondition" rows="1" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>
	</div>
	
	
	
	<table>
	<tr>
	<td>	
	<div id="divImgTarget" style="padding-top:0px;padding-left:364px; ">
	<div style="width: 310px;height: 140px;margin-top:5px; padding-left:-100px;border: 1px solid #a4a4a4; ">
			<img alt=""  id="imgKznmTargetcon"  name="imgKznmTargetcon" src="${requestScope.kznTlMst.kznmPresentimage}"  >
	</div>
	</div>
	</td>	
	<td>
	<div>
	<input class="easyui-button" type="button" value ="+" id="divImgTarget"style="height:20px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>  
	</div>
	 <div style="margin-top:10px;">   
		<input class="easyui-button" type="button" value ="-" id="btnImgTargetContionClear"style="height:20px;width:30px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
	</div>
	</td>
    </tr>
    </table>

	
	
	</tr>
	</td>
   </table>


<td>
<div class="sub-header" style="width:342px;margin-top:-64px; margin-left:-820px;" >Countermeasure</div>
	<div class="easyui-paddingbfpx" style="margin-top:8px;margin-left:-820px;"> 
         <textarea id="txtkznmCounter" name="txtkznmCounter" rows="4" cols="28" maxlength="250" title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmIdea}</textarea>
	</div>
</td>



<tr>
<td>
<div class="easyui-paddingbfpx"  style="margin-left:610px;margin-top:-259px;">
<div  style="margin-left:130px;margin-top:-442px;"><label>Kaizen Serial Number</label></div>
<div  style="margin-left:130px;margin-top:2px;">
<input class="easyui-text" id="txtsales" name="txtsales"  style="width: 190px; height: 21px;" value=""/>
</div>
<div  style="margin-left:130px;margin-top:5px;"><label>Kaizen Registration Date</label></div>
<div  style="margin-left:130px;margin-top:5px;">
<input class="easyui-datebox" id="dtekznmRegisterdate" name="dtekznmRegisterdate"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div  style="margin-left:130px;margin-top:5px;"><label>Kaizen Start Date</label></div>
<div  style="margin-left:130px;margin-top:5px;">
<input class="easyui-datebox" id="dtekznmStartdate" name="dtekznmStartdate"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div  style="margin-left:130px;margin-top:5px;"><label>Kaizen Finish Date</label></div>
<div  style="margin-left:130px;margin-top:5px;">
<input class="easyui-datebox" id="dtekznmEnddate" name="dtekznmEnddate"  style="width: 190px; height: 21px;" value=""/>			       
</div>

<div  style="margin-left:130px;margin-top:2px;"><label>Bench Mark Value</label></div>
<div  style="margin-left:130px;margin-top:2px;">
<input class="easyui-text" id="txtbenchmarkval" name="txtbenchmarkval"  style="width: 190px; height: 21px;" value=""/>
</div>
<div  style="margin-left:130px;margin-top:2px;"><label>Target Value</label></div>
<div  style="margin-left:130px;margin-top:2px;">
<input class="easyui-text" id="txttargetval" name="txttargetval"  style="width: 190px; height: 21px;" value=""/>
</div>


<div class="sub-header" style="margin-left:130px;height:19px;width:200px;">
		<label class="mandatory-lbl">SUSTENANCE PLAN</label>
		</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:739px;"> 
		<textarea id="txtkznmnsustanceplan" name="txtkznmnsustanceplan" rows="1" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 226px;height:40px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>


<div class="sub-header" style="margin-left:739px;height:19px;width:200px;">
		<label class="mandatory-lbl">SYSTEM PLAN</label>
		</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:739px;"> 
		<textarea id="txtkznmnsystemplan" name="txtkznmnsystemplan" rows="1" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 226px;height:40px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>
	
	
<div class="sub-header" style="margin-left:739px;height:19px;width:200px;">
		<label class="mandatory-lbl">TEAM MEMBERS (BAL STAFF)</label>
		</div>
	
<div  style="margin-left:739px;margin-top:2px;"><label>NAME</label></div>
<div  style="margin-left:739px;margin-top:2px;">
<input class="easyui-text" id="txtname" name="txtname"  style="width: 125px; height: 21px;" value=""/>
</div>

<div  style="margin-left:880px;margin-top:-37px;"><label>TICKET NUMBER</label></div>
<div  style="margin-left:880px;margin-top:2px;">
<input class="easyui-text" id="txtticketno" name="txtticketno"  style="width: 125px; height: 21px;" maxlength="6"  value=""/>
</div>


<div class="sub-header" style="margin-left:739px;height:19px;width:200px;">
		<label class="mandatory-lbl">TEAM MEMBERS (DEALER STAFF)</label>
		</div>
	
<div  style="margin-left:739px;margin-top:2px;"><label>Name</label></div>
<div  style="margin-left:739px;margin-top:2px;">
<input class="easyui-text" id="txtname" name="txtname"  style="width: 125px; height: 21px;" value=""/>
</div>

<div  style="margin-left:874px;margin-top:-37px;"><label>Contact Number</label></div>
<div  style="margin-left:874px;margin-top:2px;">
<input class="easyui-text" id="txtcontactno" name="txtcontactno"  style="width: 125px; height: 21px;" maxlength="6"  value=""/>
</div>

<div  style="margin-left:1025px;margin-top:-37px;"><label>Email ID</label></div>
<div  style="margin-left:1025px;margin-top:2px;">
<input class="easyui-text" id="txtemailid" name="txtemailid"  style="width: 125px; height: 21px;" maxlength="6"  value=""/>
</div>	
	

<div class="sub-header" style="margin-left:739px;height:19px;width:200px;">
		<label class="mandatory-lbl">KAIZEN IMPLEMENTATION PLAN</label>
		</div>	
	
	<div  style="margin-left:739px;margin-top:2px;"><label>What Task</label></div>
<div  style="margin-left:739px;margin-top:2px;">
<input class="easyui-text" id="txttast" name="txttast"  style="width: 111px; height: 21px;" value=""/>
</div>

	<div  style="margin-left:854px;margin-top:-37px;"><label>Who</label></div>
<div  style="margin-left:854px;margin-top:2px;">
<input class="easyui-text" id="txtwho" name="txtwho"  style="width: 110px; height: 21px;" value=""/>
</div>	
	
<div  style="margin-left:967px;margin-top:-34px;"><label>When</label></div>
<div  style="margin-left:967px;margin-top:-1px;">
<input class="easyui-text" id="txtwhen" name="txtwhen"  style="width: 110px; height: 21px;" value=""/>
</div>	

<div  style="margin-left:1082px;margin-top:-34px;"><label>Where</label></div>
<div  style="margin-left:1082px;margin-top:-1px;">
<input class="easyui-text" id="txtwhere" name="txtwhere"  style="width: 104px; height: 21px;" value=""/>
</div>		
	
	
</div>
</tr>
</td>
</table>

</tr>
<tr>
<td valign="top">
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header" style="margin-left:3px;height:19px;width:200px;margin-top:-446px;">
		<label class="mandatory-lbl">Current Condition</label>
		</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:-840px;"> 
		<textarea id="txtkznmcurrcondition" name="txtkznmcurrcondition" rows="1" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>
	
	<table>
	<tr>
	<td>	
	<div id="divImgCurr" style="padding-top:0px;padding-left:0px; ">
	<div style="width: 310px;height: 140px;margin-top:5px; padding-left:-100px;border: 1px solid #a4a4a4; ">
			<img alt=""  id="imgKznmCurrCondition"  name="imgKznmCurrCondition" src="${requestScope.kznTlMst.kznmPresentimage}"  >
	</div>
	</div>
	</td>	
	<td>
	<div>
	<input class="easyui-button" type="button" value ="+" id="divImgCurr"style="height:20px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>  
	</div>
	 <div style="margin-top:10px;">   
		<input class="easyui-button" type="button" value ="-" id="btnImgCurrContionClear"style="height:20px;width:30px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
	</div>
	</td>
    </tr>
    </table>


	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header">
	<label class="mandatory-lbl">CAUSE ANALYSIS</label>
   </div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:-845px;"> 
		<textarea id="txtkznmcauseanalysis" name="txtkznmcauseanalysis" rows="6" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>
	
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header" style="margin-left:371px;height:19px;width:200px;margin-top:-163px;">
	<label class="mandatory-lbl">RESULT</label>
   </div>
	</div>
	
	 <div class="easyui-paddingbfpx" style="margin-left:-97px;"> 
		<textarea id="txtkznmresult" name="txtkznmresult" rows="5" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>
	</div>
	 
	 
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header" style="margin-left:374px;height:19px;width:200px;margin-top:6px;">
	<label class="mandatory-lbl">COST VS BENEFIT ANALYSIS</label>
   </div>
	</div> 
	 
	 
<div  style="margin-left:-291px;margin-top:2px;"><label>COST OF KAIZEN (RS.)</label></div>
<div  style="margin-left:-291px;margin-top:2px;">
<input class="easyui-text" id="txtcostofkzn" name="txtcostofkzn"  style="width: 125px; height: 21px;" value=""/>
</div>

<div  style="margin-left:-272px;margin-top:2px;"><label>BENEFIT OF KAIZEN (RS.)</label></div>
<div  style="margin-left:-290px;margin-top:2px;">
<input class="easyui-text" id="txtbenefitofkznrs" name="txtbenefitofkznrs"  style="width: 125px; height: 21px;" value=""/>
</div>

<div  style="margin-left:-302px;margin-top:2px;"><label>BENEFIT OF KAIZEN</label></div>
<div  style="margin-left:-291px;margin-top:2px;">
<input class="easyui-text" id="txtbenefitofkzn" name="txtbenefitofkzn"  style="width: 125px; height: 21px;" value=""/>
</div>		 
	 
	
	<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header" style="margin-top:-147px;">
		<label class="mandatory-lbl">ROOT CAUSE</label>
		</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-left:-845px;"> 
		<textarea id="txtkznmrootcause" name="txtkznmrootcause" rows="2" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>
	
		<div class="easyui-paddingbfpx" style="width:350px;">
	<div class="sub-header">
		<label class="mandatory-lbl">IDEA</label>
		</div>
	</div>
	<div class="easyui-paddingbfpx" style="margin-top:2px;margin-left:-843px;"> 
		<textarea id="txtkznmidea" name="txtkznmidea" rows="2" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmPresentproblem}</textarea>

	</div>		
					
</td>


</tr>

<tr>

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

<input type="hidden" id="hdnEnableVerfyAmnt" value=" " /> 
<input type="hidden" id="hdnRoleName" name="hdnRoleName" value=" " />

<input type="hidden" id="hdnapprovallevel" name="hdnapprovallevel" value=" " /> 
<input type="hidden" id="hdnchkfipreq" name="hdnchkfipreq" value="${requestScope.chkfipreq}" />
	
	<div id="rtdtomld" style="width: 330px\9;display: none;">	
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
</div>
</div>
</form>