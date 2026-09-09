<script type="text/javascript">
	jQuery(document).ready(function(){
		initialiseForm('frmAbnormalitypopup');
		var empid=jQuery('#hdnEmpid').val();
		fillComboBox("frmAbnormalitypopup","cmbAbnmpopStatus","Combo_Status.abnForm","",false);
		fillComboBox("frmAbnormalitypopup","cmbAbnmCompletedby","employee.commonFilter");
		formatDateBox('dteAbnmpopWoendtime','dd-MMM-yyyy');
		fillWithCurrentDate('dteAbnmpopWoendtime');
		//alert('dteAbnmpopWoendtime'+dteAbnmpopWoendtime);
		jQuery("#cmbAbnmpopStatus").combobox('setValue','P');
		readOnlyFields('txtAbnmpopRemarks');
		readOnlyFields('cmbAbnmCompletedby');
		readOnlyFields('dteAbnmpopWoendtime');
		readOnlyFields('txtAbnmCountermeasure');
		var keyid=jQuery("#hdnAbnmabnKeyid").val();
		processAjaxCalls("AbnFill_modify.abnForm","keyid="+keyid,"updateAbnSuccess","");
		//jQuery("#compltd").hide();
	});
	function frmAbnormalitypopupcmbAbnmpopStatus_onSelect(record){
		if(record.id=="C"){
			 jQuery("#compltd").show();
			 //jQuery("#contreid").show();
			 enableFields('txtAbnmCountermeasure');
			 enableFields('cmbAbnmCompletedby');
			 enableFields('dteAbnmpopWoendtime');
			 enableFields('txtAbnmpopRemarks');
			 jQuery('#countrlbl').addClass('mandatory-lbl');
			 jQuery('#compltdby').addClass('mandatory-lbl');
			 jQuery('#compltdte').addClass('mandatory-lbl'); 
		}else if(record.id=="P"){
			 //jQuery("#compltd").hide();
			 //jQuery("#contreid").hide();
			 readOnlyFields('txtAbnmCountermeasure');
			 readOnlyFields('cmbAbnmCompletedby');
			 readOnlyFields('dteAbnmpopWoendtime');
			 readOnlyFields('txtAbnmpopRemarks');
			 jQuery('#countrlbl').removeClass('mandatory-lbl');
			 jQuery('#compltdby').removeClass('mandatory-lbl');
			 jQuery('#compltdte').removeClass('mandatory-lbl');
		 }
	}
	
	function dteAbnmpopWoendtime_onSelect(date){
		
		var completeDate = jQuery('#dteAbnmpopWoendtime').datebox('getValue') ;
	 	var dedectedDate = jQuery('#hdndedectiondate').val() ;
	 	var currentDate = getCurrentDate();
	 /* 	alert(completeDate);
	 	alert(dedectedDate);
	 	alert(currentDate); */
	 	//alert(compareDateTime(currentDate,completeDate));
	 	//alert(compareDateTime(completeDate,dedectedDate));
	 	
	 	if(compareDateTime(completeDate,dedectedDate) > 0 )
	 	{
	 		popupCommonErrorMsg('Completed Date Should Not Less than Dedected Date');
	 		fillWithCurrentDate('dteAbnmpopWoendtime');
	 	}
	 	else if(compareDateTime(currentDate,completeDate) > 0)
	 	{
	 		popupCommonErrorMsg('Should Not Exceed Current Date/Time');
	 		fillWithCurrentDate('dteAbnmpopWoendtime');
	 	}
	 	else{
	 	    clearValidationErrorMsg('dteAbnmpopWoendtime');
	 	}
	}
	
	jQuery("#btnvwabnrmlty").click(function(){
		var pageUrl = "Abnormality_input.abnForm";
		var keyid = jQuery("#hdnAbnmabnKeyid").val();
		var check=jQuery("#hdncheck").val();
		jQuery("#loadkaizenbank").hide();
		
		if(check.trim().length>0){
		   navigateToNextForm(pageUrl+"?q=2&AbnId="+keyid+"&filterButton=false","Abnormality",null);
		   closePopUpDialoge("loadAbnrmltycom");
		}
		
	});
	jQuery("#btnSubmit").click(function(){
		 var keyid=jQuery("#hdnAbnmabnKeyid").val();
		 var abncomptby=getFieldValue("cmbAbnmCompletedby"); 
		 var abncomptdte=getFieldValue("dteAbnmpopWoendtime");
		 var abncotrmsre=getFieldValue("txtAbnmCountermeasure");
		 var abnstatus=getFieldValue("cmbAbnmpopStatus");
		 var abnremarks=getFieldValue("txtAbnmpopRemarks"); 
		 var hdndedectiondate=jQuery("#hdndedectiondate").val();
		 var hdnabnRowid=jQuery("#hdnabnRowid").val();
		 var hdnsts=jQuery("#hdnloadsts").val();
		 jQuery("#hdncheck").val("check");
			 
		 if(hdnsts=="P"){
	         if (abnstatus=="C" && abncomptby.trim().length==0 ){ 
	 			alert(" Select Completed by ");
	 			return false;
	 		 }
			 if (abnstatus=="C" && abncomptdte.trim().length==0){ 
				alert(" Select Completed Date ");
				return false;
			 }
			 if (abnstatus=="C" &&  compareDateTime(abncomptdte,hdndedectiondate) > 0 ){ 
					alert(" Completed Date  Should Not less than Dedected Date");
					return false;
			}
	 		 if (abnstatus=="C" && abncotrmsre.trim().length==0){ 
				alert(" Enter Counter measure ");
				return false;
			 }	
	 		 
	 		 if (abnstatus=="C" && hasSpecialCharacters(abncotrmsre)){ 
					popupCommonErrorMsg('Special Characters Are Not Allowed In CounterMeasure.');
					return false;
				 }
	 		if (abnstatus=="C" && hasSpecialCharacters(abnremarks)){ 
				popupCommonErrorMsg('Special Characters Are Not Allowed In Remarks.');
				return false;
			 }
		//}
		//alert('dataString'+dataString);
		if (abnstatus=="C"){ 
			var dataString="?&keyid="+keyid+"&abncotrmsre="+escape(abncotrmsre)+"&abnstatus="+abnstatus;
		   	dataString+="&abncomptby="+abncomptby+"&abnremarks="+escape(abnremarks)+"&abncomptdte="+abncomptdte+"&abnrowid="+hdnabnRowid;
			processAjaxCalls("AbnCompletionVerifypopup_save.abnForm",dataString,"updateKaizenDatapopup","");
		}

	}
   });
	
	 /* function hasSpecialCharacters(str) {
			//alert(1);
		  const regex = /[^a-zA-Z0-9 _.\-()/%]/; 
		  return regex.test(str);
		}  */
	
	function loadAbnrmltycom_afterClose(){
 		var row=jQuery("#list").jqGrid('getDataIDs');
	 	 var AbnStatus=getFieldValue("cmbAbnmpopStatus");
		 for(i=1;i<=row.length;i++)	
		 {		 
		 var rowId=parseInt(i);
		 var isChecked = jQuery('#AbnCompcheckbox_'+rowId+'_2').is(':checked');
		 if(isChecked==true && AbnStatus=="P"){
			 jQuery('#list input[id=AbnCompcheckbox_'+rowId+'_2]').attr('disabled',false);
		     return false;   
		 } 
		 else if(isChecked==true){
			 jQuery('#list input[id=AbnCompcheckbox_'+rowId+'_2]').attr('disabled',true);
			 jQuery("#list").jqGrid('setCell',rowId,"ITEM","",{'color':'#000','font-size':'12px','background-color':'green'});
		 }
			}
			}

	function loadAbnrmltycom_onClose(){
		//jQuery("#list").trigger("reloadGrid");
		//clear();
		return true;
	}
	function updateAbnSuccess(result){
	    setFieldValue("txtAbnmpopRemarks",result[0][0]);
	    setFieldValue("cmbAbnmpopStatus",result[0][1]);
	    setFieldValue("cmbAbnmCompletedby",result[0][2]);
	    setFieldValue("txtAbnmCountermeasure",result[0][3]);
	    jQuery("#hdndedectiondate").val(result[0][5]);
	    jQuery("#hdnloadsts").val(result[0][1]);
	    if(result[0][1]=="C"){
	    	readOnlyFields("cmbAbnmpopStatus");
        }
	    
	}
	function updateKaizenDatapopup(result){
		alert("Data Updated Successfully");
		setFieldValue("cmbAbnmpopStatus","");
		setFieldValue("txtAbnmpopRemarks","");
		setFieldValue("cmbAbnmCompletedby","");
		setFieldValue("dteAbnmpopWoendtime","");
		setFieldValue("txtAbnmCountermeasure","");
		closePopUpDialoge("loadAbnrmltycom");
		AbnormalityRowUpdate(result.rowId,result.keyId,result.updatedRow);
		
		//jQuery("#list").trigger("reloadGrid");
		
	}
</script>

<form id="frmAbnormalitypopup" name="frmAbnormalitypopup">
<table align="center">
     <tr>
        <td valign="top">
            <div style="width:220px;">
           	<div id="Status"  style="margin-left:20px;">
				<label><b>Status</b></label>
			<div>
         		<input id="cmbAbnmpopStatus" name="cmbAbnmpopStatus" class="easyui-combobox" style="width:100px;" value="${requestScope.abnormalityBean.status}"/>
		    </div>
		    </div>
		    <div id="compltd" style="display:block;margin-top:10px;">
			    <div style="margin-left:20px;"><label id="compltdby">Completed By</label> </div>	
				<div style="margin-left:20px;" class="easyui-paddingbfpx">
					<input id="cmbAbnmCompletedby" name="cmbAbnmCompletedby" tabindex="21" class="easyui-combobox"  style="width:200px;" value="" /> 
				</div>
				<div style="margin-left:20px;"><span><label id="compltdte" >Completed Date</label></span></div>
				<div class="easyui-paddingbfpx" style="margin-left:20px;">
					<span> <input id="dteAbnmpopWoendtime" name="dteAbnmpopWoendtime" tabindex="22" class="easyui-datebox" style="width:125px;" value="" />  </span>
				</div>
			</div>
			</div>
         </td>
 		 <td valign="top">
          	<div id="Status" style="margin-left:30px;">
				<label><b>Remarks</b></label>
			<div>
         		<textarea style="width: 300px; height : 60px;" id="txtAbnmpopRemarks" maxlength="490" name="txtAbnmpopRemarks" style="width: 300px; height: 60px;" value="" ></textarea>
         	</div>
		    </div>
		    <div id="contreid">
		        <div id="countrlbl" style="margin-left:30px;"><label>Countermeasure</label></div>
				<div class="easyui-paddingbfpx" style="margin-left:30px;">
					<textarea class="" maxlength="490" rows="1"  tabindex="24" style="width:300px;resize:none; height:60px;" cols="" id="txtAbnmCountermeasure" name="txtAbnmCountermeasure" ></textarea>
				</div>	
			</div>	
	     </td> 
	     <td valign="top" style="display:none;">
	     <div style="margin-left:30px;">
	     <label>View Abnormality</label>
	     </div>
          	<div class="easyui-paddingbfpx" style="margin-left:30px;">
				<input type="button" id="btnvwabnrmlty" name="btnvwabnrmlty" class="easyui-button" value="" style="width:40px;"/>
			</div>	
	     </td>            
        </tr>
        </table>
        <table>
        <tr>
	   <td colspan="3">
	   <div style="margin-left: 300px;margin-top:14px;">
	   	 <input type="button" id="btnSubmit" name="btnSubmit" class="easyui-button" value="Submit"/>
	   </div>
	   </td> 
     </tr>
   </table>           
<input type="hidden"id="hdnApmode" name="hdnApmode" value="${requestScope.apMode}" />
<input type="hidden" id="hdnAbnmabnKeyid" name="hdnAbnmabnKeyid" value="${requestScope.abnKeyid}"></input>
<input type="hidden" id="hdnabnRowid" name="hdnabnRowid" value="${requestScope.abnRowid}"></input>
<input type="hidden" id="hdncheck" name="hdncheck" value=""></input>
<input type="hidden" id="hdndedectiondate" name="hdndedectiondate" value=""></input>
<input type="hidden" id="hdnloadsts" name="hdnloadsts" value=""></input>
</form>