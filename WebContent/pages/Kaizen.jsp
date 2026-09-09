
<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm('frmKaizen');
	
	jQuery('#submitForm').val('frmKaizen');
	var val;
	formatDateBox('dteKzbnDate','dd-MMM-yyyy');
	formatDateBox('dteKzbnTargetdate','dd-MMM-yyyy');
	//alert(jQuery('hdnSftysugg').val());
	formatDateBox('dtehdnKzbnDate','dd-MMM-yyyy');
	fillWithCurrentDate("dtehdnKzbnDate");
//	jQuery('#chkKzbnNonJhEsp').attr('checked',false); // 16aug2024

	if(getFieldValue('dteKzbnDate')==null || getFieldValue('dteKzbnDate')=="" || getFieldValue('dteKzbnDate')==" ")
		fillWithCurrentDate("dteKzbnDate");
	
	
	fillComboBox("frmKaizen", "cmbKzbnSuggestedby", "employee.commonFilter");
	fillComboBox("frmKaizen", "cmbKzbnResponsibility", "employee.commonFilter");
	fillComboBox("frmKaizen", "cmbKzbnAcrejby", "employee.commonFilter");
	fillComboBox("frmKaizen","cmbkzbnBenefit","kaizenactegoryfillcombo.kaizen?flid="+flid);
	formatDateBox('dteKzbnAcceptrejon','dd-MMM-yyyy'); 
	 var factId = jQuery("#frmKaizen input[id='factory']").val();
	 var sectionId = jQuery("#frmKaizen input[id='section']").val();
	 var cellId = jQuery("#frmKaizen input[id='cell']").val();
	 var machId = jQuery("#frmKaizen input[id='machine']").val();
	 var flid = jQuery("#frmKaizen input[id='flid']").val();
	 

	 var mchId = jQuery("#hdnMachineId").val();
	 
	 if(mchId != null || mchId != '' || mchId != ' ')
		 machId = mchId;
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid+"&disable=N";
	 
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
		loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmKaizen",dataStr);
	 }
	 else  if(jQuery("#hdnAccSingle").val().trim()=="N"){
		 	jQuery("#hdnKzbnStatus").val("-");	
			loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmKaizen",dataStr);
		 }
	 else{
		 jQuery('#targetdate').removeClass("mandatory-lbl");
		 jQuery('#response').removeClass("mandatory-lbl");
		 jQuery('#lbldate').removeClass("mandatory-lbl");
		jQuery('#typelbl').removeClass("mandatory-lbl");
		 loadFunctionalLocation("frmKaizenfunloc","functionalLoc.kznbnk","kaizenfunLocationValues","frmKaizen",dataStr);
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

	  jQuery("#chkKzbnNonJhEsp").click(function(){ 
			 if(jQuery("#chkKzbnNonJhEsp").is(':checked') == true){
				 jQuery("#chkKzbnNonJhEsp").val("Y");
			 }
			 else if(jQuery("#chkKzbnNonJhEsp").is(':checked') == false){
				 jQuery("#chkKzbnNonJhEsp").val("N");
			 } 
		  });
	 jQuery('#btnSubmit').click(function(){
			// jQuery('#chkKzbnEhsrelated').attr('checked',true);
	         var keyid=jQuery('#hdnKzbnKeyid').val();
	         var hdnsafety=jQuery('#hdnsafety').val();
	         var chkvalue=jQuery('#hdnSftysugg').val();
	        // alert("chkvalue"+chkvalue);
	         //if(hdnsafety=="Y")
	        // alert("hdnsafety KeyId : " + hdnsafety);
	         if(hdnsafety=="Y")
	        	 jQuery('#chkKzbnEhsrelated').attr('checked',true);
	         else
	        	 jQuery('#chkKzbnEhsrelated').attr('checked',false);
	        // alert("btnSubmit: " + " txtapproavalflag=Y");
	         var hdkey=jQuery("#hdnfrmhdkeyid").val();
	        // alert("kaizen submit hd keyid::"+hdkey);
			 saveForm('frmKaizen',"KaizenBankSuggestion_save.kznbnk?txtapproavalflag=Y&AccSingle=N&Keyid="+keyid+"&hdkey="+hdkey+"&HD="+"HD"+"&chkvalue="+chkvalue);
			
		 });


		 jQuery('#btnSave').click(function(){
			 
			 var keyid=jQuery('#hdnKzbnKeyid').val();
	         var hdkey=jQuery("#hdnfrmhdkeyid").val();
	         var hdnsafety=jQuery('#hdnsafety').val();
	         var chkvalue=jQuery('#hdnSftysugg').val();
	        
	      
	      
	         if(hdnsafety=="Y")
	        	 jQuery('#chkKzbnEhsrelated').attr('checked',true);
	         else
	        	 jQuery('#chkKzbnEhsrelated').attr('checked',false);
	        // alert("Kaizen save hd keyid::::"+hdkey);
			 //alert("btnSave KeyId : " + keyid);
			 //jQuery('#chkKzbnEhsrelated').attr('checked',true);
			 saveForm('frmKaizen',"KaizenBankSuggestion_save.kznbnk?txtapproavalflag=N&AccSingle=N&Keyid="+keyid+"&hdkey="+hdkey+"&HD="+"HD"+"&chkvalue="+chkvalue);
			 
		 });

	    var hsesfty=jQuery('#hdnehssfty').val();
	   // alert("hsesfty123"+hsesfty);
		if(hsesfty.trim().length>0)
		 {
			jQuery('#hsesafety').css('display','block');
			

			jQuery('#chkKzbnEhsrelated').attr('checked',true);
			//alert(jQuery('#chkKzbnEhsrelated').val());
			//alert(jQuery('#chkKzbnEhsrelated').val());
			disableField("frmKaizen", "chkKzbnEhsrelated");
		 }
		else 
		  {
			 jQuery('#hseothers').css('display','block');
			// alert(jQuery('#chkKzbnEhsrelated').val());
			jQuery('#chkKzbnEhsrelated').attr('checked',false);
			disableField("frmKaizen", "chkKzbnEhsrelated");
          }
          
		
		var others=jQuery('#hdnOthers').val();
	     
		if(others.trim() == "Y"){
			jQuery("#chkkzbnOthers").attr("checked","checked");
	    }
		

		//jQuery('#chkKzbnEhsrelated').attr('checked',true);
		//disableField("frmKaizen", "chkKzbnEhsrelated");
		
		var frmmode=jQuery('#hdnfrmmode').val();
		
		if(frmmode=="View" || frmmode=="view"){
			jQuery('#frmKaizenFuntKeyIds').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:100%;z-index:2;opacity:0.4;height:30%;"> </div>');
			disableForm("frmKaizen");
		}

		jQuery('#btnSubmit').val('Save and Send to Leader');
		jQuery('#btnSave').val('Save');
		
});

jQuery('#chkkzbnOthers').click(function() {
	     
	     //alert(" Others "+jQuery('#chkkzbnOthers').val());
	     
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

function  frmKaizencmbkzbnBenefit_onSelect(record)
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
	 
	 var cellId = jQuery("#frmKaizen input[id='cell']").val();
	 var flid = jQuery("#frmKaizen input[id='flid']").val();
	 if(sat=="Y")
	 {
		  reloadCombo("frmKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+cellId+"&others=Y");
	 }
	 else
	 {  
		  reloadCombo("frmKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+cellId);
	 }	
}

	
function disableAccRej(){
	disableField("frmKaizen", "dteKzbnTargetdate");
	 disableField("frmKaizen", "cmbKzbnResponsibility");
// 	 disableField("frmKaizen", "txtKzbnBenefit");
	 disableField("frmKaizen", "txtKzbnAccrejremarks");
	 disableField("frmKaizen", "cmbKzbnAcrejby");
	 disableField("frmKaizen", "dteKzbnAcceptrejon");
	 disableField("frmKaizen", "chkResultAreaA");
	 disableField("frmKaizen", "chkResultAreaR");
	 disableField("frmKaizen", "chkResultAreaP");
	 disableField("frmKaizen", "chkResultAreaQ");
	 disableField("frmKaizen", "chkResultAreaC");
	 disableField("frmKaizen", "chkResultAreaD");
	 disableField("frmKaizen", "chkResultAreaS");
	 disableField("frmKaizen", "chkResultAreaM");
	 disableField("frmKaizen", "chkResultAreaE");
}

function enableAccRej(){
	enableFields("dteKzbnTargetdate");
	enableFields("cmbKzbnResponsibility");
	enableFields("txtKzbnAccrejremarks");
	enableFields("cmbKzbnAcrejby");
	enableFields("dteKzbnAcceptrejon");
	enableFields("chkA");
	enableFields("chkR");
	/*
	enableFields("txtKzbnBenefit");
	enableFields("chkP");
	enableFields("chkQ");
	enableFields("chkC");
	enableFields("chkD");
	enableFields("chkS");
	enableFields("chkM");
	enableFields("chkE");*/
} 
function frmKaizen_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFunctionalLocWidth('frmKaizen','550px');
	jQuery("#cmbKzbnFlid").val(keyIds.flId);
	reloadCombo("frmKaizen","cmbKzbnSuggestedby","employee.commonFilter?&cellId="+keyIds.cellId);
	
}
function frmKaizen_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	navigateToPrevForm();
	clearForm('frmKaizen');
}
function frmKaizen_successsCallback(result){
      var keyid=jQuery("#hdnKzbnKeyid").val();
	//clearForm('frmKaizen');
	//alert(result.successData.msg);
	//navigateToPrevForm();
	//clearForm('frmKaizen');
       
//alert("keyid"+keyid);
       if(keyid.length!=0)
           {
    	   //alert("keyid.length"+keyid.length);
    	   if(result.successData.direct != 'N'){
    		   navigateToPrevForm("KaizenBankSuggestionMainGrid_input.kznbnk");
    	   }
    	   closePopUpDialoge("Kaizen");  	
	  clearForm('frmKaizen');
	 // clearForm('frmKaizen');
          }
       else{
      //navigateToNextForm("KaizenBankSuggestionMainGrid_input.kznbnk");
    	   clearForm('frmKaizen');
    	   if(result.successData.direct != 'N')
    		   navigateToPrevForm();
    	   
    	   closePopUpDialoge("Kaizen");  
      
       
                 }

}
function frmKaizen_beforeDelete(){
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
 
function frmKaizen_beforeSubmit()
{
	//alert(jQuery('#hdnKzbnStatus').val());
   
	var date=getFieldValue("dteKzbnDate");
	var hdkey=jQuery("#hdnfrmhdkeyid").val();
	//alert("kaizen hdkeyid::"+hdkey);
	
	//date = "0" + date;
	var hdndtevaldtn=getFieldValue("dtehdnKzbnDate");

	if(date.substring(0,1)!=0 )
    	date = "0" + date;

	//alert(" date :: "+(date.substring(0,2)).length);
	
    //if(hdndtevaldtn.substring(0,1)!=0)
    	//hdndtevaldtn = "0" + hdndtevaldtn;

    var date1=date.substring(3,6);
	var date2=hdndtevaldtn.substring(3,6);
	var frmmode=jQuery('#hdnfrmmode').val();
    //alert(" date1 "+date1+" date2 "+date2);
    
    
	/*if(date1!=date2){
		popupCommonErrorMsg("Kaizen Date Can not be Select for previous month.");
		fillWithCurrentDate("dteKzbnDate");
		return false;
	}*/
	
	if(frmmode=="View" || frmmode=="view"){
		popupCommonErrorMsg("Data Can not be Saved in View mode.");
		return false;
	}
		
	var keyid= jQuery("#hdnKzbnKeyid").val();
	
	var cellId = getFieldValue('cell','frmKaizen');
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
	     
	     if(jQuery("#chkKzbnNonJhEsp").is(':checked')== true){
	    	 jQuery('#chkKzbnNonJhEsp').val('Y');
	    	 
	     }else if(jQuery("#chkKzbnNonJhEsp").is(':checked')== false){
	         jQuery('#chkKzbnNonJhEsp').val('N');
	         
		 }

	  var r =confirm("Data Changed. Do You want to Proceed");
	  if(r){
		 // alert("end of beforsubmit 1 : txtapproavalflag=N" );
	     return '&txtapproavalflag=N';
	  }
	  else
		  return false;
  }
 // alert("end of beforsubmit 2 : txtapproavalflag=N" );
 confirm("Submittng will be sent to Leader")
  return '&txtapproavalflag=Y';
  
}
</script>

<form name="frmKaizen" id="frmKaizen" >
<div id="wrapper" style="width:90%;">
	<div style="padding-left:5%;padding-top:0.5%;">
		<div class="sub-header"   style="width:800px;">Suggestion</div>
				<div class="main-cntborder" style="height: 400px;width:800px;margin-left:0%;">
					<div>
						<div>
						<table cellspacing="2" style="padding-left:5%;">
							<tr>
								<td colspan="3">
									<div id="frmKaizenFuntKeyIds"  >							
										<input type="hidden" id="factory" name="factory"  value="" ></input>
										<input type="hidden" id="section" name="section"  value=""></input>
										<input type="hidden" id="cell"    name="cell"     value=""></input>
										<input type="hidden" id="machine" name="machine"  value=""></input>
										<input type="hidden" id="flid" name="cmbKzbnFlid"  value="${requestScope.kaizenbank.kzbnFlid }"></input>
										<input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>							
									</div>
									
									
									
									<input type="hidden" id="elementId" name="cmbKzbnElementid"  value="${requestScope.kaizenbank.kzbnElementid}"></input>						
									<div id="frmKaizenfunloc" style="width:100%;"></div>
								</td>
                                                                 <td>
                                                                                <span style="padding-left: 10px;">
											<input type="button" id="btnSave"  name="btnSave" class="easyui-button" value=""  />
										</span></td>

							</tr>
							
							<tr>
								<td  valign="top" >
										<div>
										<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Suggestion</label></div>
					                    <div class="easyui-paddingbfpx">
					                    	<textarea style="width: 650px; height : 90px;" id="txtKzbnKaizen" maxlength="490" name="txtKzbnKaizen" >${requestScope.kaizenbank.kzbnKaizen }</textarea>
					                    </div>
				                    </div>
								</td>
							</tr>
							<tr>
							<td   colspan="2">
							<div><label >Theme Category </label>
						<span style="padding-left: 215px;"><label>Benefit Area </label></span></div>
<!--          				<div class="easyui-paddingbfpx" style="border:1px solid;width:70%;padding-left:5px;">  -->
         				<input id="cmbkzbnBenefit" name="cmbkzbnBenefit" class="easyui-combobox" style="width:290px;" value="${requestScope.kaizenbank.kzbnBenefit}" ></input>
									<span class="easyui-paddingbfpx" style="padding-top:90px;padding-left:14px">
									<input type="checkbox" id="chkResultAreaP" name="chkResultAreaP" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaP == 'P' ? ' checked':''}/><label style="padding-left:5px;">P</label>
									<input type="checkbox" id="chkResultAreaQ" name="chkResultAreaQ" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaQ == 'Q' ? ' checked':''}/><label style="padding-left:5px;" >Q</label>
									<input type="checkbox" id="chkResultAreaC" name="chkResultAreaC" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaC == 'C' ? ' checked':''}/><label style="padding-left:5px;">C</label>
									<input type="checkbox" id="chkResultAreaD" name="chkResultAreaD" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaD == 'D' ? ' checked':''}/><label style="padding-left:5px;">D</label>
									<input type="checkbox" id="chkResultAreaS" name="chkResultAreaS" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaS == 'S' ? ' checked':''}/><label style="padding-left:5px;">S</label>
									<input type="checkbox" id="chkResultAreaM" name="chkResultAreaM" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaM == 'M' ? ' checked':''}/><label style="padding-left:5px;">M</label>
									<input type="checkbox" id="chkResultAreaE" name="chkResultAreaE" onclick="chkboxCheck(this.id);" ${ requestScope.kaizenFormBean.disableForm == true ? ' disabled':''} ${ requestScope.kaizenFormBean.resultAreaE == 'E' ? ' checked':''}/><label style="padding-left:5px;">E</label>
						
				
							</div>
						</td>
						
						
							<tr>
								<td style="padding-left:0%;"  valign="top">
									<div class="easyui-paddingbfpx">
											<label class="mandatory-lbl">Suggested By</label>
											
											<input type="checkbox" id="chkkzbnOthers" name="chkkzbnOthers"    style="margin-left:10px;" />
										    <span>
											    <label>	Others</label>
										    </span>
										     <span style="padding-left:10px;">						 
										  <input type="checkbox" id="chkKzbnNonJhEsp" name="chkKzbnNonJhEsp"  ${ requestScope.kaizenbank.kzbnNonJhEsp == 'Y' ? ' checked':''}  />
										 
											    <label>	Non JH Esp</label>
										    </span>
										
											<span style="padding-left: 52px;"><label class="mandatory-lbl">Date</label> </span>
											<span style="padding-left: 59px;"><label >ESP Name's</label> </span>
									
									</div>
									<div class="easyui-paddingbfpx">
										<input class="easyui-combobox" id="cmbKzbnSuggestedby" name="cmbKzbnSuggestedby" style="width: 200px;/*  height: 21px; */" value="${requestScope.kaizenbank.kzbnSuggestedby }"/>
										<span style="padding-left: 100px;">
											<input id="dteKzbnDate"  name="dteKzbnDate" clear="false" class="easyui-datebox" value="${requestScope.kaizenbank.kzbnDate }"  style="width:80px;height:21px;"  />
										</span>
										</div>
										<div style="padding-left: 400px; margin-top:-27px;">
										<span class="easyui-paddingbfpx">
					                    	<textarea style="width: 280px; height : 60px;" id="txtKzbnEspName" maxlength="490" name="txtKzbnEspName" > ${requestScope.kaizenbank.kzbnEspName } </textarea>
					                    </span>
										
	                                    <span style="padding-left: 10px;display:none;">
											<input id="dtehdnKzbnDate"  name="dtehdnKzbnDate" clear="false" class="easyui-datebox" value=""  style="width:80px;height:21px;display:none;"  />
										</span>
										<span id="hsesafety" style="padding-left: 320px;margin-top:-20px;display:none;">
											<input type="checkbox" id="chkKzbnEhsrelated" name="chkKzbnEhsrelated" ${ requestScope.kaizenbank.kzbnEhsrelated == 'Y' ? ' checked':''}/>
										    <span>
											    <label>EHS Related</label>
										    </span>
										   
										</span>
									
					                    
									</div>
									<div>
										
										<span style="padding-left: 10px;">
											<input type="button" id="btnSubmit"  name="btnSubmit" class="easyui-button" value="" />
										</span>
									
										<span style="padding-left: 10px;">
											<input type="checkbox" id="isMailRequired"  name="isMailRequired" checked/>  <label>Mail For Approval</label>
										</span>
									</div>
									<div style="margin-top:4px;background-color:#CCCCFF;height:30px;">
									<P style="padding-top:6px;"> Click 'Save and Send to Leader' to send this Suggestion for Approval </P>
									</div>
									<div id="err_cmbKzbnSuggestedby" class="tpm-errormsg" style="display: block;padding-left: 0px;"></div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
<!-- 	 		
			<div class="sub-header" style="width:700px;">Kaizen Accept/Reject</div>
			<div id="kznAccRej"  class="main-cntborder" style="height: 250px;width:700px;margin-left:0%;">
				<table style="padding-left:5%;">
					<tr>
						<td width="20%">
							<div id="chkAccRej" >
								<div class="easyui-paddingbfpx" style="border:1px solid;width:100%;padding-left:5px;">
									<span style="margin-top:5px;"><input type="checkbox" id="chkA" name="chkA"/><label style="padding-left:5px;">Accept</label></span>
									<span><input type="checkbox" id="chkR" name="chkR"/><label style="padding-left:5px;">Reject</label></span>
								</div>
							</div>
						</td>
						<td style="padding-left:4%;">
							<div class="easyui-paddingbfpx"><label id="lbldate">Accepted on</label></div>
							<div id="AccDate">
								<div class="easyui-paddingbfpx">
									<input id="dteKzbnAcceptrejon"  name="dteKzbnAcceptrejon" clear="false" class="easyui-datebox"style="width:100px;height:21px;" value="${requestScope.kaizenbank.kzbnAcceptrejon}" />
								</div>
								<div id="err_dteKzbnAcceptrejon" class="tpm-errormsg"></div>
							</div>
						</td>
						<td style="padding-left:4%;">
							<div id="TarDate">
								<div class="easyui-paddingbfpx"><label id="targetdate">Target Date </label></div>
								<div class="easyui-paddingbfpx">
									<input id="dteKzbnTargetdate"  name="dteKzbnTargetdate" clear="false" class="easyui-datebox" style="width:110px;height:21px;" value="${requestScope.kaizenbank.kzbnTargetdate}" />
								</div>
							</div>
						</td>
						
					</tr>
					<tr>
						<td   colspan="2">
						<div  class="easyui-paddingbfpx"><label id="typelbl" >Accepted By</label></div>
							<div id="AccRej">
								<div class="easyui-paddingbfpx">
									<input class="easyui-combobox" id="cmbKzbnAcrejby" name="cmbKzbnAcrejby" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnAcrejby}" />
								</div>
								<div id="err_cmbKzbnAcrejby" class="tpm-errormsg"></div>
							</div>
							
						</td>
						
						<td style="padding-left:4%;">
							<div id="resShow">
								<div class="easyui-paddingbfpx"><label id="response" >Responsibility</label></div>
								<div class="easyui-paddingbfpx">
									<input class="easyui-combobox" id="cmbKzbnResponsibility" name="cmbKzbnResponsibility" style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnResponsibility }"/>
								</div>
							</div>	
						</td>
					</tr>
					<tr>
						<td   colspan="2">
							<div class="easyui-paddingbfpx"><label>Benefit </label></div>
							<div class="easyui-paddingbfpx">
								<input id="txtKzbnBenefit" name="txtKzbnBenefit" type="text" class="easyui-text"  maxlength="95"  style="width: 250px; height: 21px;" value="${requestScope.kaizenbank.kzbnBenefit }" />								
							</div>
						</td>
						<td style="padding-left:4%;" rowspan="2" valign="top">
							<div class="easyui-paddingbfpx"><label>Remarks</label></div>
		                    <div class="easyui-paddingbfpx">
								<textarea rows="2" cols="90" style="width: 250px; height : 65px;"  maxlength="490"  id="txtKzbnAccrejremarks" name="txtKzbnAccrejremarks" >${requestScope.kaizenbank.kzbnAccrejremarks}</textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td   colspan="2">
							<div class="easyui-paddingbfpx"><label >Category </label></div>
							<div class="easyui-paddingbfpx" style="border:1px solid;width:70%;padding-left:5px;">
									<input type="checkbox" id="chkP" name="chkP"/><label style="padding-left:5px;">P</label>
									<input type="checkbox" id="chkQ" name="chkQ"/><label style="padding-left:5px;">Q</label>
									<input type="checkbox" id="chkC" name="chkC"/><label style="padding-left:5px;">C</label>
									<input type="checkbox" id="chkD" name="chkD"/><label style="padding-left:5px;">D</label>
									<input type="checkbox" id="chkS" name="chkS"/><label style="padding-left:5px;">S</label>
									<input type="checkbox" id="chkM" name="chkM"/><label style="padding-left:5px;">M</label>
									<input type="checkbox" id="chkE" name="chkE"/><label style="padding-left:5px;">E</label>
							</div>
						</td>
						<td style="padding-left:4%;" >
							
						</td>
					</tr>
				</table>
			</div>
		</div>
-->

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
