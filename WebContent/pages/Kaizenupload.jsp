<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="js/fileuploader.js"></script>

<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
var glbKznmStatus ="-";
var glbKznmApprovallevel ="-";
var glbKznmRoleName ="-";
jQuery(document).ready(function(){
	    initialiseForm('frmKaizenupld');
	    jQuery('#submitForm').val('frmKaizenupld');
		var factId = jQuery("#frmKaizenupld input[id='factory']").val();
		var sectionId = jQuery("#frmKaizenupld input[id='section']").val();
		var cellId = jQuery("#frmKaizenupld input[id='cell']").val();
		var machId = jQuery("#frmKaizenupld input[id='machine']").val();
		var flid = jQuery("#frmKaizenupld input[id='flid']").val();
		var keyid =jQuery("#hdnkznKeyid").val();
		//alert(keyid);
		var Mode=jQuery("#hdnMode").val();
		//alert(Mode);
		var caption=jQuery("#hdncaption").val();
		//alert(caption);
		var kznmstatus =jQuery("#hdnstatus").val();
		var kzbnkznmKeyid=jQuery('#hdnKznmKzbnkeyid').val();			
		var hdnflid = jQuery("#hdnflid").val();
		if (hdnflid!=null && hdnflid.trim().length>0 )
			flid=hdnflid;
		var thmcategory=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
		var problem = escape(encodeURIComponent(getFieldValue("txtkznmPresentproblem")));
		var  categorykeyid=jQuery("#thmecategory").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("kznfunLocation","functionalLoc.kaizen","kznfunLocationValues","frmKaizenupld",dataStr);
	fileManagerPopUp("","KZN","frmKaizenupld","btnFilManage","KZNUPLOADFilemgr");
	formatDateBox('dtekznmDate','dd-MMM-yyyy');
	fillComboBox("frmKaizenupld","cmbKznmActivitypillarid","pillar.commonFilter");
    fillComboBox("frmKaizenupld","cmbkznmThemecategoryid","kaizenactegoryfillcombo.kaizen?flid="+flid);
    fillComboBox("frmKaizenupld","cmbkznmFipNumber","Kazennoname_combo.kaizen");
	
    if(Mode=="view"){
    	disableForm("frmKaizenupld");
    }
    var category=jQuery("#cmbkznmThemecategoryid").combobox('getValue');	
	{		
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
		jQuery('#chkResultAreaP').attr('disabled', true);
		jQuery('#chkResultAreaQ').attr('disabled', true);
		jQuery('#chkResultAreaC').attr('disabled', true);
		jQuery('#chkResultAreaD').attr('disabled', true);
		jQuery('#chkResultAreaS').attr('disabled', true);
		jQuery('#chkResultAreaM').attr('disabled', true);
		jQuery('#chkResultAreaE').attr('disabled', true);
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
		numericTextBox("txtBTSTotalcost");
		numericTextBox("txtkznmVerifyamount");
		readOnlyFields("txtBTSTotalcost");
		readOnlyFields("txtkznmVerifyamount");
		readOnlyFields("txtKznmBenefitvalue");
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
	var valbenefittype = jQuery('#hdnBenefittype').val();
	jQuery("#frmKaizenupld select[id= cboKznmBenefittype]").attr('value',valbenefittype);
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
	var Mode=jQuery('#mode').val();
	var keyid=jQuery('#txtKznmKeyid').val();
	 if(Mode=="create" && keyid.trim().length<=0){
	    setFieldValue('txtkznmTeammembers',getText);
	 }
	 if(keyid.trim().length>0 && (Mode=="approval" || Mode=="view"||frmMode=="view"))
	 	 { 
		    
	 		readOnlyFields("txtKznmBenefitvalue");	 		
	 	 }
	 
	    var benefitype=jQuery('#cboKznmBenefittype').val();
	    
	    if(benefitype=="NS" ){
			readOnlyFields("txtKznmBenefitvalue");
		}

	}
	
	
	if(Mode=="modify"||Mode=="view"){	
		var category=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
		disableField("frmKaizenupld","txtKznmBenefitvalue");
		disableField("frmKaizenupld","cmbkznmFipNumber");
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
	}
	
});




function  frmKaizenupldcmbkznmThemecategoryid_onSelect(record)
{
	var KEYID=record.id;
	var keyid=jQuery('#hdnkznKeyid').val();
	var thmcategory=jQuery("#cmbkznmThemecategoryid").combobox('getValue');
	if(thmcategory.length!=0||thmcategory!=null)
		{
	var r=confirm("Do you want change the Benefit Area and Theme Catgeory");
	 if(r==true)
		 {
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
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
function KZNBTS_successCallback(result)
{

}

jQuery('#txtKznmCostperequipment').keyup(function() {
	CalculateBTS();
});
jQuery('#txtKznmCostperhour').keyup(function() {
	CalculateBTS();		
});

jQuery('#txtKznmBenefitvalue').keyup(function() {
	validateBTS();
}); 
function enableVerifyamount(role){
    
    jQuery('#hdnRoleName').val(role);

	if("FINANCE" == role.trim().toUpperCase())
	{
		jQuery("#hdnEnableVerfyAmnt").val("Y");
		enableFields("txtkznmVerifyamount");
		jQuery("#lblVerifyAmount").addClass("mandatory-lbl");
		jQuery("#txtkznmVerifyamount").val(" ");
		
	}
	
	if("PBU HEAD" == role.trim().toUpperCase())
	{
		jQuery("#hdnEnableVerfyAmnt").val("Y");
		enableFields("txtkznmVerifyamount");
		jQuery("#txtkznmVerifyamount").val(jQuery("#txtKznmBenefitvalue").val());
		
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


function getSelectType(value){
	var mode=jQuery("#mode").val();
	jQuery("#txtKznmBenefitvalue").val('');
	if(value=="NS"){
		disableField("frmKaizenupld", "txtKznmBenefitvalue");
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','5');
		jQuery("#spninrbox").css('padding-left','0');
	
		}
	else if(value=="GE5"){
		if(mode=="view" || mode =="completion")
			{
			disableField("frmKaizenupld", "txtKznmBenefitvalue");
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
			disableField("frmKaizenupld", "txtKznmBenefitvalue");
		jQuery("#spninrbox").css('padding-left','0');
		jQuery("#inid").text('(INR)');
		jQuery("#txtKznmBenefitvalue").attr('maxlength','6');
		}
	else if(value=="S"){
		disableField("frmKaizenupld", "txtKznmBenefitvalue");
	}
	else{
		if(mode!="view" && mode !="completion")
			enableFields("txtKznmBenefitvalue");
		else
			disableField("frmKaizenupld", "txtKznmBenefitvalue");
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


function CategoryrecallsuccessCallBackCategory(result){ 
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
	var chkValue=result[0][1];	
	var locnId = jQuery("#frmKaizenupld input[id='location']").val();
	/*if(locnId !=null && locnId !='LCN0000005'){
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
}

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



 function btnFilManage_click(){
	 var kaizenId = jQuery('#cmbkznmKeyid').combobox('getValue');
	 var frmMode=jQuery('#hdnMode').val();
	if(kaizenId != null && kaizenId != ''){
		apMode = "create";
		if(frmMode=="view")
		   apMode = "view";
		fileManagerPopUp(kaizenId,"KZN","","","",apMode);		
	} else  
    {   var id=jQuery("#hdnFilemngr").val("Y");
		 popupCommonErrorMsg("Please save Kaizen Upload");
		 return false;
     }	
} 


function frmKaizenupld_beforeSubmit()
{ 
	var Mode=jQuery("#hdnMode").val();
	var keyid=jQuery("#hdnkznKeyid").val();
	 var fipreq=jQuery('#chkkznmfipRequired').is(':checked');
	 var fipno=getFieldValue("cmbkznmFipNumber");
	 var activitypillar=getFieldValue("cmbKznmActivitypillarid");
	 var  countermeasure=jQuery("#txtkznmCountermeasure").val();
     var themecategory=getFieldValue("cmbkznmThemecategoryid");
     var  presentproblem=jQuery("#txtkznmPresentproblem").val();
     var benefitype=jQuery('#cboKznmBenefittype').val();
     
    if((Mode=="view")){
		popupCommonErrorMsg("Kaizen Can not be save data in " + Mode + " Mode. ");
		return false;
	}
    
    if (fipreq==true){
		if(fipno.trim().length==0){
			popupCommonErrorMsg(" Select FI Project ");
			return false;
	     }
	}
    
    if(activitypillar.length==0 || activitypillar==null){
    	popupCommonErrorMsg("Select the Activity Pillar");
    	return false;
    }
    
    if(countermeasure.length==0 || countermeasure==null){
    	popupCommonErrorMsg(" Enter the Counter Measure");
    	return false;
    }
    
    if(themecategory.length==0 || themecategory==null){
    	popupCommonErrorMsg("Select the Theme Category");
    	return false;
    }
    
    if(presentproblem.length==0 || presentproblem==null){
    	popupCommonErrorMsg("Enter Present/Status");
    	return false;
    }
    if(benefitype==null ||benefitype.length==0){
    	
      popupCommonErrorMsg("Select Benefit Type");
      return false;
    }
    
	    var rtrndata;
		rtrndata=jQuery('#chkkznmfipRequired').val(); 
	    if (jQuery('#chkkznmfipRequired').is(':checked'))
	   	    rtrndata = "&value=Y";
	    else 
			rtrndata = "&value=N";
		  		
	    if(glbKznmStatus=="A" && glbKznmApprovallevel=="-")
	    	rtrndata+="&status="+glbKznmStatus+"&approvelevel="+glbKznmRoleName;
	    else
	    	rtrndata+="&status="+glbKznmStatus+"&approvelevel="+glbKznmApprovallevel;
		return rtrndata;
}



function frmKaizenupld_successsCallback(result)
{
	 var kznKeyid=result.successData.kznKeyid;
	 jQuery('#txtKznmKeyid').val(kznKeyid); 
	 
     var modes = result.successData.mode;
     var type=result.successData.Type;
     if(type=="workflow"){
         workFlowSubmit(result.successData.record,false);
     }
     else{	
     var persistentData = result.persistentData;
	 var forwardData = result.forwardData;
	 var savemode=result.saveMode;	 
	 jQuery('#cmbkznmKeyid').combobox('setValue',kznKeyid);
	 var flid = jQuery("#frmImprovementPrj input[id='flid']").val();		
	//	navigateToPrevForm();
	// fileManagerPopUp(kznKeyid,"KZN","","","");
     }
}






 </script>
<form name="frmKaizenupld" id="frmKaizenupld" action="" method="post">
<input type="hidden" id="hdnempty" name ="hdnempty" value="R"/>
<div id="wrapper" style="width:100%;">
<div class="main-cntborder" style="height: 500px;width:106%;">
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;" >
<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
			<div  id="frmKaizenupldFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">	
			<input type="hidden" id="factory" name="cmbKznmFactoryid" value="${requestScope.kznTlMst.kznmFactoryid}"></input>
			<input type="hidden" id="section" name="cmbKznmSectionid" value="${requestScope.kznTlMst.kznmSectionid}"></input>
			<input type="hidden" id="cell" name="cmbKznmCellid" value="${requestScope.kznTlMst.kznmCellid}"></input>
			<input type="hidden" id="machine" name="cmbKznmMachineid" value="${requestScope.kznTlMst.kznmMachineid}"></input>
			<input type="hidden" id="flid" name="cmbKznmFlid" value="${requestScope.flid}"  ></input>
			</div>
			<div id="kznfunLocation" style="width: 50%; "></div>
			</div>
			<div style="position:relative;">
			 <span  id="KZNUPLOADFilemgr" style="right:150px;top:-24px;position:absolute;right:140px\9;top: -28px\9;" >
             </span> 
          </div> 
			
</td>
</tr>
<tr><td colspan="3" >
</td>
</tr>
<tr style=" top:80px" >		
<td valign="top" style="padding-left: 30px; width : 360px;">
			<div   style="padding-left: 0px;">
			<label>Kaizen No</label>
			</div>
			<div class="easyui-paddingbfpx" >
			<input id="cmbkznmKeyid" name="cmbkznmKeyid" class="easyui-combobox"  style="width:96px;" value="${requestScope.kznKeyid}" disabled="disabled" />
	        </div>				
			<div  style="margin-top:-43px;margin-left:152px;">
		<div><label>Date</label></div>
		<div class="easyui-paddingbfpx">
		<input id="dtekznmDate" name="dtekznmDate" class="easyui-datebox" disabled="disabled" style="width:90px;" value="${requestScope.date}" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> />  
		</div>
		</div>
		<div style="padding-top:6px;margin-left:375px; margin-top:-35px">
<label class="mandatory-lbl">Benefit Area</label>
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
			
		
		<div style="margin-left:0px;; margin-top:10px;">
			<label class="mandatory-lbl">Theme Category</label> 
			</div>
	 		<div class="easyui-paddingbfpx">
         		<input id="cmbkznmThemecategoryid" name="cmbkznmThemecategoryid" class="easyui-combobox" style="width:350px;" value="${requestScope.themecategory}" ></input>
</div>

<div class="easyui-paddingbfpx" style="margin-left:0px;; margin-top:10px"><label>Theme</label></div>
<div class="easyui-paddingbfpx">
	<textarea id="txtkznmTheme" name="txtkznmTheme"  rows="4" cols="40" title="Maximum Length is 250" maxlength="250" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.kznTlMst.kznmTheme}</textarea>
</div>

<div style="margin-left:382px;margin-top:-2px;">	
<input id="chkkznmfipRequired" name="chkkznmfipRequired" type="checkbox"  /> 
<span style="padding-left:5px;"><label id="lblFIProject" class="" style="">FI Project</label></span>
</div>

<div style="margin-left:-63px;margin-top:1px;">		
<span style="padding-left:445px;">	
<input type="text" id="cmbkznmFipNumber"  name="cmbkznmFipNumber" class="easyui-combobox" style="width:250px;" value="${requestScope.kznTlMst.kznmFipNumber}">
</span>
</div>


<div style="margin-left:376px; margin-top:-213px;">
<label class="mandatory-lbl">Activity Pillar</label>
</div>
<div class="easyui-paddingbfpx" style="margin-left:376px;">
<input id="cmbKznmActivitypillarid" name="cmbKznmActivitypillarid" class="easyui-combobox"  style="width:350px;" value="${requestScope.actPillar}" />
</div>

	
	<div style="margin-top:125px;" class="mandatory-lbl" ><label id="lblAbnDesc">Problem Present/Status</label></div>		
	<div class="easyui-paddingbfpx">
 	<textarea id="txtkznmPresentproblem" name="txtkznmPresentproblem" rows="3" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.Problem}</textarea>	
	</div>
	
	
<div style="margin-left:376px;margin-top:-208px;">	
<span style="padding-left:8px;"><label id="lblCoumeasure" class="mandatory-lbl" style="">Counter Measure</label></span>
</div>
<div style="margin-left:376px;margin-top:0px;" class="easyui-paddingbfpx">
 <textarea id="txtkznmCountermeasure" name="txtkznmCountermeasure" rows="3" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.CountMeasure}</textarea>	
	
	</div>
	
	 <div class="sub-header" style="width:1115px;margin-top:108px; margin-left:-30px;"> Cost Details</div>
					<div class="easyui-paddingbfpx" >
					    <label id="bt" class="mandatory-lbl">Benefit Type</label>
					
						<span><label style="padding-left: 186px;">Cost of Product /hr</label></span>
						<span><label style="padding-left: 46px;">Cost of Equipment /hr</label></span>
						<span><label style="padding-left: 36px;">Total Cost /hr</label></span>
						<span id="lblVerifyAmount"><label style="padding-left: 36px;">Verified Amount</label></span>
					</div> 
               		<div class="easyui-paddingbfpx" > 
               		    <select class="easyui-text" id="cboKznmBenefittype" name="cboKznmBenefittype" panelHeight=80px;  style="width:  100px; height: 21px;"   <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/> onchange='getSelectType(this.value)'>
							<option value='NS'>No Savings</option>
							<option value='S'>Safety</option>
							<option value='LE5'><=5 Lakh</option>
							<option value='GE5'>>5 Lakh</option>
						</select>
						<span style="padding-left: 0px" id="spninrbox">
						<input type="text" id="txtKznmBenefitvalue" name="txtKznmBenefitvalue" class="easyui-text"  style=" width : 100px; text-align: right; " value="${requestScope.benefitvalue}"  onfocus="gotFocus(this.id)" onchange="noBenfitChange(this.id)"  onkeypress="return validate(event)" align="right"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/>
						<label id="inid" style="padding-left: 3px;">(INR)</label>
						</span> 
						<span><input class="easyui-text" type="text" id="txtKznmCostperhour" name="txtKznmCostperhour"   maxlength="7" value="${requestScope.costhrproduct}"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span>
						<span style="padding-left: 40px;">	<input class="easyui-text" type="text" id="txtKznmCostperequipment" name="txtKznmCostperequipment"  value="${requestScope.costhrequipment}"  maxlength="7"  <c:out value = "${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''}"/>/></span>
						<span style="padding-left: 43px;">	<input class="easyui-text" type="text" id="txtBTSTotalcost" name="txtkznmBTSTotalcost" /></span>
				        <span style="padding-left: 16px;">	<input class="easyui-text" type="text" id="txtkznmVerifyamount" name="txtkznmVerifyamount" maxlength="8" value="${requestScope.kznTlMst.kznmVerifyamount}" /></span>
				 </div>
				   <!--  </div>				
 <div class="sub-header" style="width:1115px;margin-top:6px; margin-left:-30px;"> Work Flow</div> -->
</td>
</tr>
<tr>
<td colspan="3">
</td>
</tr>
<tr>
</tr>
</table>
</div>
</div>
<input type="hidden" id="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnkznKeyid" value="${requestScope.kznKeyid}" />
<input type="hidden" id="hdnkznmKeyid" value="${requestScope.kznTlMst.kznmKeyid}" />
<input type="hidden" id="hdndate" value="${requestScope.date}" />
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>   
<input type="hidden" id="verifyamount" name="verifyamount" value="${requestScope.verifyamount}"/> 
<input type="hidden" id="hdnthemecategory" name="hdnthemecategory" value="${requestScope.themecategory}"/> 
<input type="hidden" id="problem" name="problem" value="${requestScope.problem}"/> 
<input type="hidden" id="hdnCountMeasure" name="hdnCountMeasure" value="${requestScope.CountMeasure}">
<input type="hidden" id="actPillar" name="actPillar" value="${requestScope.actPillar }"/>
<input type="hidden" id="frmMode" name="frmMode" value="${requestScope.frmMode}"/> 
<input type="hidden" id="hdnApprovLevel" name="hdnApprovLevel" value="${requestScope.approvelevel}"/>
<input type="hidden" id="hdnstatus" name="hdnstatus" value="${requestScope.status}"/>
 <input type="hidden" id="hdnKznmKzbnkeyid" name="hdnKznmKzbnkeyid"  value="${requestScope.kznbankkeyid}"/>
 <input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.Mode}">
 <input type="hidden" id="hdnFrmActionMode" value="${requestScope.kaizenFormBean.formActionMode}"/>
<input type="hidden" id="hdncosthrproduct" name="hdncosthrproduct" value="${requestScope.costhrproduct}">
<input type="hidden" id="hdncosthrequipment" name="hdncosthrequipment" value="${requestScope.costhrequipment}">
<input type="hidden" id="hdnbenefitvalue" name="hdnbenefitvalue" value="${requestScope.benefitvalue}">
<input type="hidden" id="hdnbenefittype" name="hdnbenefittype" value="${requestScope.benefittype}">
</form>

 