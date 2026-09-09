<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmNewKaizen');
	jQuery('#submitForm').val('frmNewKaizen');
	var val;
	formatDateBox('dteKzbnDate','dd-MMM-yyyy');
	formatDateBox('dteKzbnTargetdate','dd-MMM-yyyy');
	formatDateBox('dtehdnKzbnDate','dd-MMM-yyyy');
	fillWithCurrentDate("dtehdnKzbnDate");
        
	if(getFieldValue('dteKzbnDate')==null || getFieldValue('dteKzbnDate')=="" || getFieldValue('dteKzbnDate')==" ")
		fillWithCurrentDate("dteKzbnDate");
	
	
	fillComboBox("frmNewKaizen", "cmbKzbnSuggestedby", "employee.commonFilter");
	fillComboBox("frmNewKaizen", "cmbKzbnResponsibility", "employee.commonFilter");
	fillComboBox("frmNewKaizen", "cmbKzbnAcrejby", "employee.commonFilter");
	fillComboBox("frmNewKaizen","cmbkzbnBenefit","kaizenactegoryfillcombo.kaizen?flid="+flid);
	formatDateBox('dteKzbnAcceptrejon','dd-MMM-yyyy');
	jQuery('#chkKzbnEhsrelated').attr('checked',false);
	 var factId = jQuery("#frmNewKaizen input[id='factory']").val();
	 var sectionId = jQuery("#frmNewKaizen input[id='section']").val();
	 var cellId = jQuery("#frmNewKaizen input[id='cell']").val();
	 var machId = jQuery("#frmNewKaizen input[id='machine']").val();
	 var flid = jQuery("#frmNewKaizen input[id='flid']").val();
	 
	
	 var mchId = jQuery("#hdnMachineId").val();
	 
	 if(mchId != null || mchId != '' || mchId != ' ')
		 machId = mchId;
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid+"&disable=N";
	 jQuery("#chkKzbnNonJhEsp").click(function(){ 
		 if(jQuery("#chkKzbnNonJhEsp").is(':checked') == true){
			 jQuery("#chkKzbnNonJhEsp").val("Y");
		 }
		 else if(jQuery("#chkKzbnNonJhEsp").is(':checked') == false){
			 jQuery("#chkKzbnNonJhEsp").val("N");
		 } 
	  });
	 disableAccRej();
	 if(jQuery("#hdnAccSingle").val().trim()=="Y"){
		 
		 if(getFieldValue('dteKzbnAcceptrejon')==null || getFieldValue('dteKzbnAcceptrejon')=="" || getFieldValue('dteKzbnAcceptrejon')==" "){ 
			fillWithCurrentDate("dteKzbnAcceptrejon");
		 }
		 if(getFieldValue('dteKzbnTargetdate')==null || getFieldValue('dteKzbnTargetdate')=="" || getFieldValue('dteKzbnTargetdate')==" ")
				fillWithCurrentDate("dteKzbnTargetdate");
		 if(jQuery("#hdnKzbnStatus").val().trim()=="R"){
			jQuery("#chkR").attr('checked',true);
			jQuery("#hdnKzbnStatus").val("R");
			jQuery('#typelbl').html('Rejected By');
			jQuery('#lbldate').html('Rejected On');
			jQuery("#TarDate").hide();
			jQuery("#resShow").hide();
		 }
		 else{
			jQuery("#chkA").attr('checked',true);
			jQuery("#hdnKzbnStatus").val("A");
		 }
		 enableAccRej();
		jQuery('#targetdate').addClass("mandatory-lbl");
		jQuery('#response').addClass("mandatory-lbl");
		jQuery('#lbldate').addClass("mandatory-lbl");
		jQuery('#typelbl').addClass("mandatory-lbl");
		enableFields("chkA");
		loadFunctionalLocation("frmNewKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmNewKaizen",dataStr);
	 }
	 else  if(jQuery("#hdnAccSingle").val().trim()=="N"){
		 	jQuery("#hdnKzbnStatus").val("-");	
			loadFunctionalLocation("frmNewKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmNewKaizen",dataStr);
		 }
	 else{
		 jQuery('#targetdate').removeClass("mandatory-lbl");
		 jQuery('#response').removeClass("mandatory-lbl");
		 jQuery('#lbldate').removeClass("mandatory-lbl");
		jQuery('#typelbl').removeClass("mandatory-lbl");
		 loadFunctionalLocation("frmNewKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmNewKaizen",dataStr);
	 }

	 jQuery('#chkA').click(function(){
			if(jQuery("#chkA").is(':checked') == true)		
			{
				jQuery("#chkR").attr('checked',false);
				jQuery("#hdnKzbnStatus").val("A");
				jQuery('#typelbl').html('Accepted By');
				jQuery('#lbldate').html('Accepted On');
				jQuery("#chkA").attr('checked',true);
				jQuery("#resShow").show();
				jQuery("#TarDate").show();
			}else{
				jQuery("#chkR").attr('checked',true);
				jQuery("#hdnKzbnStatus").val("R");
				jQuery('#typelbl').html('Rejected By');
				jQuery('#lbldate').html('Rejected On');
				jQuery("#TarDate").hide();
				jQuery("#resShow").hide();
			}
		 });
	 
	 
		jQuery('#chkR').click(function(){
			if(jQuery("#chkR").is(':checked') == true)		
			{
				jQuery("#chkA").attr('checked',false);
				jQuery('#typelbl').html('Rejected By');
				jQuery('#lbldate').html('Rejected On');
				jQuery("#hdnKzbnStatus").val("R");
				jQuery("#TarDate").hide();
				jQuery("#resShow").hide();
			}else{
				jQuery("#chkA").attr('checked',true);
				jQuery('#typelbl').html('Accepted By');
				jQuery('#lbldate').html('Accepted On');
				jQuery("#hdnKzbnStatus").val("A");
				jQuery("#TarDate").show();
				jQuery("#resShow").show();
			}
			
		 });
		 
	 var split;
	 var keyid = jQuery("#hdnKzbnKeyid").val();
	 if(keyid.trim().length!="0"){
		 var chkbox= jQuery("#hdnKzbnPqcdsme").val();
		 for(var i=0;i<chkbox.length;i++){ //
			 var arraylist = chkbox[i];
			 if(arraylist =="P"){
				 jQuery("#chkResultAreaP").attr('checked',true);
			}else if(arraylist =="Q"){
				 jQuery("#chkResultAreaQ").attr('checked',true);
			}else if(arraylist =="C"){
				 jQuery("#chkResultAreaC").attr('checked',true);
			}else if(arraylist =="D"){
				 jQuery("#chkResultAreaD").attr('checked',true);
			}else if(arraylist =="S"){
				 jQuery("#chkResultAreaS").attr('checked',true);
			}else if(arraylist =="M"){
				 jQuery("#chkResultAreaM").attr('checked',true);
			}else if(arraylist =="E"){
				 jQuery("#chkResultAreaE").attr('checked',true);
			}
				
		}
		
					 	 
	 }
	 jQuery('#chkResultAreaP').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val();	 
		 if(jQuery('#chkResultAreaP').is(':checked') == true){
		 jQuery("#hdnKzbnPqcdsme").val(val+"P,");
		 }else{
			 split = val.replace("P,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaQ').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val();
		 if(jQuery('#chkResultAreaQ').is(':checked') == true){
			  
			 jQuery("#hdnKzbnPqcdsme").val(val+"Q,");
			 }
		 else{
			 
			 split = val.replace("Q,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaC').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val(); 
		 if(jQuery('#chkC').is(':checked') == true){
			  
			 jQuery("#hdnKzbnPqcdsme").val(val+"C,");
			 }
		 else{
			 split = val.replace("C,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaD').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val();
		 if(jQuery('#chkResultAreaD').is(':checked') == true){ 
		 
		 jQuery("#hdnKzbnPqcdsme").val(val+"D,");
		 }
		 else{
			 split = val.replace("D,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaS').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val();
		 if(jQuery('#chkResultAreaS').is(':checked') == true){ 
			
			 jQuery("#hdnKzbnPqcdsme").val(val+"S,");
			 }
		 else{
			 split = val.replace("S,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaM').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val(); 
		 if(jQuery('#chkResultAreaM').is(':checked') == true){ 
			 jQuery("#hdnKzbnPqcdsme").val(val+"M,");
			 }
		 else{
			 split = val.replace("M,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 jQuery('#chkResultAreaE').click(function(){
		 val = jQuery("#hdnKzbnPqcdsme").val();
		 if(jQuery('#chkResultAreaE').is(':checked') == true){ 
			 jQuery("#hdnKzbnPqcdsme").val(val+"E,");
			 }
		 else{
			 split = val.replace("E,","");
			 jQuery("#hdnKzbnPqcdsme").val(split);
			 }
	 });
	 
	  jQuery("#chkKzbnEhsrelated").click(function(){
			 if(jQuery("#chkKzbnEhsrelated").is(':checked') == true){
				 jQuery("#chkKzbnEhsrelated").val("Y");
			 }
			 else if(jQuery("#chkKzbnEhsrelated").is(':checked') == false){
				 jQuery("#chkKzbnEhsrelated").val("N");
			 } 
		  });

	 jQuery('#btnKaizenSheet').click(function(){
	         var keyid=jQuery('#hdnKzbnKeyid').val();
	 		 var ehs=(jQuery("#chkKzbnEhsrelated").is(':checked')) ? "Y": "N";
	         var hdkey=jQuery("#hdnfrmhdkeyid").val();
			 saveForm('frmNewKaizen',"NewKaizenBankSuggestion_save.dkzn?txtapproavalflag=Y&AccSingle=N&Keyid="+keyid+"&hdkey="+hdkey+"&HD="+"HD"+"&ehs="+ehs);
		 });
		
	 
		var others=jQuery('#hdnOthers').val();
		if(others.trim() == "Y"){
			jQuery("#chkkzbnOthers").attr("checked","checked");
	    } 
		var frmmode=jQuery('#hdnfrmmode').val();
		if(frmmode=="View" || frmmode=="view"){
			jQuery('#frmNewKaizenFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
			disableForm("frmNewKaizen");
		}
});

jQuery('#chkkzbnOthers').click(function() {
	    	     
	     if(jQuery("#chkkzbnOthers").is(':checked')== true){
	    	 jQuery('#chkkzbnOthers').val('Y');
	    	 var sat = jQuery('#chkkzbnOthers:checked').val();
		     jQuery("#cmbKzbnSuggestedby").combobox('setValue',"");
			 othersClickAction(sat);
	     }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
	         jQuery('#chkkzbnOthers').val('N');
	         var sat = jQuery('#chkkzbnOthers').val();
	    	 jQuery("#cmbKzbnSuggestedby").combobox('setValue',"");
			 othersClickAction(sat);
		 }
	});

function  frmNewKaizencmbkzbnBenefit_onSelect(record)
{
	var KEYID=record.id;
	var kzbnKeyid=jQuery("#hdnKzbnKeyid").val();
	var thmcategory=jQuery("#cmbkzbnBenefit").combobox('getValue');
	if(kzbnKeyid.trim().length==0||kzbnKeyid==null||kzbnKeyid.trim().length==null)
		{
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");

		}
	else{
		var r=confirm("Do you want change the Benefit Area and Theme Category");
	  if(r==true)
		 {
		 processAjaxCalls("kaizenCategory_recall.kaizen?&KEYID="+KEYID,"","CategoryrecallsuccessCallBackCategory","CategoryerrorCallBack");
		 }
	 else{
		 jQuery("#cmbkzbnBenefit").combobox('clear');
		 return false;
	     }
		}
}


function CategoryrecallsuccessCallBackCategory(result){ 
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
	setFieldValue('hdnKzbnPqcdsme',result[0][1]);
	
	var chkValue =result[0][1];
	
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	
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



function CategoryrecallsuccessCallBackCategory(result){ 
	setFieldValue('cmbkznmTpmpillarid',result[0][0]);
	setFieldValue('txtkznmTheme',result[0][2]);
	setFieldValue('hdnKzbnPqcdsme',result[0][1]);
	
	var chkValue =result[0][1];
	
	jQuery("#cmbKznmActivitypillarid").combobox('setValue',' ');
	
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



function othersClickAction(sat)
{
	 
	 var cellId = jQuery("#frmNewKaizen input[id='cell']").val();
	 var flid = jQuery("#frmNewKaizen input[id='flid']").val();
	 if(sat=="Y")
	 {
		  reloadCombo("frmNewKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+cellId+"&others=Y");
	 }
	 else
	 {  
		  reloadCombo("frmNewKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+cellId);
	 }	
}

	
function disableAccRej(){
	disableField("frmNewKaizen", "dteKzbnTargetdate");
	 disableField("frmNewKaizen", "cmbKzbnResponsibility");
// 	 disableField("frmNewKaizen", "txtKzbnBenefit");
	 disableField("frmNewKaizen", "txtKzbnAccrejremarks");
	 disableField("frmNewKaizen", "cmbKzbnAcrejby");
	 disableField("frmNewKaizen", "dteKzbnAcceptrejon");
	 disableField("frmNewKaizen", "chkResultAreaA");
	 disableField("frmNewKaizen", "chkResultAreaR");
	 disableField("frmNewKaizen", "chkResultAreaP");
	 disableField("frmNewKaizen", "chkResultAreaQ");
	 disableField("frmNewKaizen", "chkResultAreaC");
	 disableField("frmNewKaizen", "chkResultAreaD");
	 disableField("frmNewKaizen", "chkResultAreaS");
	 disableField("frmNewKaizen", "chkResultAreaM");
	 disableField("frmNewKaizen", "chkResultAreaE");
}

function enableAccRej(){
	enableFields("dteKzbnTargetdate");
	enableFields("cmbKzbnResponsibility");
	enableFields("txtKzbnAccrejremarks");
	enableFields("cmbKzbnAcrejby");
	enableFields("dteKzbnAcceptrejon");
	enableFields("chkA");
	enableFields("chkR");
} 
function frmNewKaizen_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFunctionalLocWidth('frmNewKaizen','550px');
	jQuery("#cmbKzbnFlid").val(keyIds.flId);
	reloadCombo("frmNewKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+keyIds.cellId);
	
}
function frmNewKaizen_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	navigateToPrevForm();
	clearForm('frmNewKaizen');
}
function frmNewKaizen_successsCallback(result){
      var keyid=jQuery("#hdnKzbnKeyid").val();
       if(keyid.length!=0)
           {
	        navigateToPrevForm("KaizenBankSuggestionMainGrid_input.kznbnk");	
	 // clearForm('frmNewKaizen');
          }
       else{
    	var DKaizen="DKaizen";
    	var Kaizen= unescape(encodeURIComponent(getFieldValue("txtKzbnKaizen")));
    	var Suggestedby = jQuery('#cmbKzbnSuggestedby').combobox('getValue');
    	var ThemeCategory=jQuery("#cmbkzbnBenefit").combobox('getValue');
    	var themeName=jQuery("#cmbkzbnBenefit").combobox('getText');
    	var flId = jQuery("#frmNewKaizen input[id='flid']").val();
    	//alert(flid)
		var BenefitArea=jQuery("#hdnKzbnPqcdsme").val();
		var KZNBKeyid=result.successData.SuggestionNo;
		var kaizen=Kaizen.replaceAll('#', '_');
        var ds="?&Kaizen="+kaizen+"&DKaizen="+DKaizen+"&DirectKaizen=Y&mode=create&Suggestedby="+Suggestedby+"&flId="+flId+"&ThemeCat="+ThemeCategory+"&themeName="+encodeURIComponent(themeName);
        ds+="&BenefitArea="+BenefitArea+"&KZNBKeyid="+KZNBKeyid;
     //   alert(ds+"lk");
    	navigateToNextForm("kaizen_input.kaizen"+ds);
        }
}
function frmNewKaizen_beforeDelete(){
	if(jQuery("#hdnAccSingle").val().trim()=="Y")
		return false;
	var keyid= jQuery("#hdnKzbnKeyid").val();
	if(keyid==null || keyid=="" || keyid== " ")
		return false;
	else{
		var r=confirm("Are You Sure to Delete?");
		if(r)
			return true;
		else
			return false;
	}
}
function frmNewKaizen_beforeSubmit()
{
   
	var date=getFieldValue("dteKzbnDate");
	var hdkey=jQuery("#hdnfrmhdkeyid").val();
	var hdndtevaldtn=getFieldValue("dtehdnKzbnDate");

	if(date.substring(0,1)!=0 )
    	date = "0" + date;

    var date1=date.substring(3,6);
	var date2=hdndtevaldtn.substring(3,6);
	var frmmode=jQuery('#hdnfrmmode').val();
	if(frmmode=="View" || frmmode=="view"){
		popupCommonErrorMsg("Data Can not be Saved in View mode.");
		return false;
	}
		
	var keyid= jQuery("#hdnKzbnKeyid").val();
	
	var cellId = getFieldValue('cell','frmNewKaizen');
	if (cellId =='' || cellId ==' ') {
		popupCommonErrorMsg("Select JH");
		return false;
	}
  var status=jQuery("#hdnKzbnStatus").val();
  if(status=='R'){
	  var rejBy=getFieldValue("cmbKzbnAcrejby");
	  var rejOn=getFieldValue("dteKzbnAcceptrejon");
	  if(rejOn=="" || rejOn==null){
			setTimeout(function(){
				showValidationErrorMsg("dteKzbnAcceptrejon","Select Rejected on");
			},50);
		  
		  if(rejBy=="" || rejBy==null){
			setTimeout(function(){
				showValidationErrorMsg("cmbKzbnAcrejby","Select Rejected by");
			},50);
			  
		  }
		  return false;
	  }
	  else if(rejBy=="" || rejBy==null){
			setTimeout(function(){
			  showValidationErrorMsg("cmbKzbnAcrejby","Select Rejected by");
			},50);
		  if(rejOn=="" || rejOn==null){
			setTimeout(function(){
				showValidationErrorMsg("dteKzbnAcceptrejon","Select Rejected on");
			},50);
		  }
		  return false;
	  }
  }
  if(keyid!=null && keyid!="" && keyid!= " "){
	     if(jQuery("#chkkzbnOthers").is(':checked')== true){
	    	 jQuery('#chkkzbnOthers').val('Y');
	    	 
	     }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
	         jQuery('#chkkzbnOthers').val('N');
	         
		 }

	  var r =confirm("Data Changed. Do You want to Proceed");
	  if(r){
	     return '&txtapproavalflag=N';
	  }
	  else
		  return false;
  }
  return '&txtapproavalflag=N';
}
</script>

<form name="frmNewKaizen" id="frmNewKaizen" >
<div id="wrapper" style="width:90%;">
	<div style="padding-left:5%;padding-top:0%;">
		<div class="sub-header"   style="width:850px;">Suggestion</div>
				<div class="main-cntborder" style="height: 400px;width:850px;margin-left:0%;">
					<div>
						<div>
						<table cellspacing="2" style="padding-left:5%;">
							<tr>
								<td colspan="3">
									<div id="frmNewKaizenFuntKeyIds"  >							
										<input type="hidden" id="factory" name="factory"  value="" ></input>
										<input type="hidden" id="section" name="section"  value=""></input>
										<input type="hidden" id="cell"    name="cell"     value=""></input>
										<input type="hidden" id="machine" name="machine"  value=""></input>
										<input type="hidden" id="flid" name="cmbKzbnFlid"  value="${requestScope.kaizenbank.kzbnFlid }"></input>
										<input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>							
									</div>
									
									<input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>						
									<div id="frmNewKaizenfunloc" style="width:100%;"></div>
								</td>
							</tr>
							
							<tr>
								<td  valign="top" >
										<div>
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Suggestion</label></div>
					                    <div class="easyui-paddingbfpx">
					                    	<textarea style="width: 550px; height : 120px;" id="txtKzbnKaizen" maxlength="490" name="txtKzbnKaizen" >${requestScope.kaizenbank.kzbnKaizen }</textarea>
					                    </div>
				                    </div>
								</td>
							</tr>
							<tr>
							<td   colspan="2">
							<div><label >Theme Category </label>
						<span style="padding-left: 215px;"><label>Benefit Area </label></span></div>
         				<input id="cmbkzbnBenefit" name="cmbkzbnBenefit" class="easyui-combobox" style="width:290px;" value="${requestScope.kaizenbank.kzbnBenefit}" ></input>
									<span class="easyui-paddingbfpx" style="padding-top:90px;padding-left:14px">
									<input type="checkbox" id="chkResultAreaP" name="chkResultAreaP" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaP == 'P' ? ' checked':''}"/><label style="padding-left:5px;">P</label>
									<input type="checkbox" id="chkResultAreaQ" name="chkResultAreaQ" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}"/><label style="padding-left:5px;" >Q</label>
									<input type="checkbox" id="chkResultAreaC" name="chkResultAreaC" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}"/><label style="padding-left:5px;">C</label>
									<input type="checkbox" id="chkResultAreaD" name="chkResultAreaD" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}"/><label style="padding-left:5px;">D</label>
									<input type="checkbox" id="chkResultAreaS" name="chkResultAreaS" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}"/><label style="padding-left:5px;">S</label>
									<input type="checkbox" id="chkResultAreaM" name="chkResultAreaM" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}"/><label style="padding-left:5px;">M</label>
									<input type="checkbox" id="chkResultAreaE" name="chkResultAreaE" onclick="chkboxCheck(this.id);"  "${ requestScope.kaizenFormBean.disableForm ? 'disabled="disabled"' : ''}  "${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}"/><label style="padding-left:5px;">E</label>

							</div>
							
						  <span id="hsesafety" style="padding-left: 10px;padding-top:50px;">
						  <input type="checkbox" id="chkKzbnEhsrelated" name="chkKzbnEhsrelated"  "${ requestScope.kaizenbank.kzbnEhsrelated == 'Y' ? ' checked':''}"/>
						  <span>
						  <label>EHS Related</label>
						  </span>
						  </span>
						  </td>
						  </tr>
							<tr>
								<td style="padding-left:0%;"  valign="top">
									<div class="easyui-paddingbfpx">
											<label class="mandatory-lbl">Suggested By</label>
											
											<input type="checkbox" id="chkkzbnOthers" name="chkkzbnOthers"    style="margin-left:10px;" />
										    <span>
											    <label>	Others</label>
										    </span>
										     <span style="padding-left:10px;">						 
										  <input type="checkbox" id="chkKzbnNonJhEsp" name="chkKzbnNonJhEsp" value="N"  ${ requestScope.kaizenbank.kzbnNonJhEsp == 'Y' ? ' checked':''}  />
										 
											    <label>	Non JH Esp</label>
										    </span>
										
											<span style="padding-left: 52px;"><label class="mandatory-lbl">Date</label> </span>
											<!-- <span style="padding-left: 59px;"><label >ESP Name's</label> </span> -->
									</div>
									<div class="easyui-paddingbfpx">
										<input class="easyui-combobox" id="cmbKzbnSuggestedby" name="cmbKzbnSuggestedby" style="width: 200px;/*  height: 21px; */" value="${requestScope.kaizenbank.kzbnSuggestedby }"/>
										<span style="margin-left: 80px;">
											<input id="dteKzbnDate"  name="dteKzbnDate" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnDate }"  style="width:80px;/* height:21px; */"  />
										</span>
										<%-- <span class="easyui-paddingbfpx">
					                    	<textarea style="width: 280px; height : 60px;" id="txtKzbnEspName" maxlength="490" name="txtKzbnEspName" > ${requestScope.kaizenbank.kzbnEspName } </textarea>
					                    </span> --%>
	                                    <span style="padding-left: 10px;display:none;">
											<input id="dtehdnKzbnDate"  name="dtehdnKzbnDate" clear="false" class="easyui-datebox" value=""  style="width:80px;height:21px;display:none;"  />
										</span>
										<div style="padding-left:375px;margin-top:-45px;">					
										<div class="easyui-paddingbfpx"><label >ESP Names</label></div>
					                    <div class="easyui-paddingbfpx">
					                    	<textarea style="width: 290px; height : 60px;" id="txtKzbnEspName" maxlength="490" name="txtKzbnEspName" >${requestScope.kaizenbank.kzbnEspName }</textarea>
					                    </div>
					                    </div>
					                    
					                   </div>
					               </td>
									 <td  valign="top" >
										
										
				                   
								</td>
									</tr>
									<tr><td>    
					                    
									<div style="margin-top:10px;" >	
									<span style="margin-left: 30px;">
									<input type="button" id="btnKaizenSheet"  name="btnKaizenSheet" class="easyui-button" value="Generate Kaizen Sheet"/>
									</span>
									
									
										 
									</div>
									<div style="margin-top:4px;background-color:#CCCCFF;height:30px;">
									<P style="padding-top:6px;"> Click 'Generate Kaizen Sheet' </P> 
							
									</div>
									<div id="err_cmbKzbnSuggestedby" class="tpm-errormsg" style="display: block;padding-left: 0px;"></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
	</div>

 <input type="hidden" id="mode"/>
 <input type="hidden" id="hdnKzbnKeyid" name="hdnKzbnKeyid" value="${requestScope.kaizenbank.kzbnKeyid }"/>
 <input type="hidden" id="hdnKzbnPqcdsme" name="hdnKzbnPqcdsme" value="${requestScope.kaizenbank.kzbnPqcdsme }"/>
 <input type="hidden" id="hdnKzbnStatus" name="hdnKzbnStatus" value="${requestScope.kaizenbank.kzbnStatus}"/>
 <input type="hidden" id="hdnAccSingle" name="hdnAccSingle" value="${requestScope.AccSingle}"/>
 <input type="hidden" id="hdnMachineId" name="hdnMachineId" value="${requestScope.mchId}"/>
 <input type="hidden" id="hdnehssfty" name="hdnehssfty" value="${requestScope.ehssfty}"/>
 <input type="hidden" id="hdnOthers" name="hdnOthers" value="${requestScope.kaizenbank.kzbnOthers}" /> 
 <input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />
 <input type="hidden" id="hdnehssfty" name="hdnehssfty" value="${requestScope.ehssfty}"/>
 <input type="hidden" id="hdnfrmhdkeyid" name="hdnfrmhdkeyid" value="${requestScope.hdkeyid}"/>
 <input type="hidden" id="hdnfrmmode" name="hdnfrmmode" value="${requestScope.frmmode}"/>
 <input type="hidden" id="hdnsafety" name="hdnsafety" value="${requestScope.safety}"/>
 <input type="hidden" id="hdnSftysugg" name="hdnSftysugg" value="${requestScope.Sftysugg}"/>
 
</form>