<script type="text/javascript">
var glpdate="";
jQuery(document).ready(function(){
	initialiseForm('frmAuditVerify');
	//alert(jQuery('#frmmsttype').val());
	//jQuery('#submitForm').val('frmAuditVerify');	
	fillComboBox("frmAuditVerify","cmbSdadResponsibilty","employee.commonFilter" );
	jQuery("#cmbSdadResponsibilty").combobox("disable");

	formatDateBox('dteSdadTargetDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteSdadTargetDate');
	var date=jQuery('#dteSdadTargetDate').datebox('getValue');
	 glpdate=date;
	jQuery("#btnsaveres").attr("disabled", true).addClass("ui-state-disabled"); 
	var createmode=jQuery('#frmcreatemode').val();
	 var keyid=jQuery("#hdndtlkeyid").val();
	 jQuery('#cmbSdamAudittype').combobox('setValue','Y');
	 jQuery('#dteSdadTargetDate').datebox('setValue',date);
	 var status=jQuery('#cmbSdamAudittype').combobox('getValue');
	   if(status=='Y')
		   {
		   jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
		   }
	 //alert("keyid::"+keyid);

});


jQuery("#cmbSdamAudittype").combobox({
	onSelect:function(recordid){	
	
		var currentDate = getServerDateTime();
		//var stringdate=convertStringToDate(currentDate)
		//alert("TETS");
		//alert(recordid.id);
		var verifystatus=jQuery("#cmbSdamAudittype").combobox('getValue');
		var flid=jQuery("#hdnflid").val();
		
	
      if(recordid.id=='Y')
		 {
		 //jQuery("#dteSdadTargetDate").val(stringdate);
		 var empid=jQuery('#cmbSdadResponsibilty').combobox('getValue');
		 alert("empid"+empid);
		 jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
		 reloadCombo("frmAuditVerify","cmbSdadResponsibilty","employee.commonFilter?flid="+empid);
		 fillWithCurrentDate('dteSdadTargetDate');
		 }
      else if(recordid.id=='N')
    	  {
    	  //alert("INSIDE THE ELSE");
    	  
    	  jQuery("#dteSdadTargetDate").datebox("clear");
    	  //jQuery("#cmbSdadResponsibilty").combobox("clear");
    	jQuery("#btnsaveres").attr("disabled", true).addClass("ui-state-disabled"); 
    	    //clearFields('');
    	  }
	}
});


jQuery("#dteSdadTargetDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		//alert("currentDate"+currentDate);
		//alert(recordid);
		var datevalue=jQuery("#dteSdadTargetDate").datebox('getValue');
		//alert("datevalue:::"+datevalue);
		var stringdate=convertStringToDate(datevalue);
		//alert("stringdate"+stringdate);
		if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dteSdadTargetDate');
			return false;
		}
		    	
		} 
	});





/* jQuery("#btnsaveres").click(function(){
	
 // alert("INSIDE THE CLICK FUNCTION");
   var keyid=jQuery("#hdndtlkeyid").val();
  // alert("keyid::"+keyid);
   var responsevalue=jQuery("#cmbSdadResponsibilty").combobox('getValue');
   var verifystatus=jQuery("#cmbSdamAudittype").combobox('getValue');
 //  alert("responsevalue::"+responsevalue.length);
   var targetdate=jQuery("#dteSdadTargetDate").datebox("getValue");
   var remarks=jQuery("#txtSdadVerifyRemarks").val();
  // alert("targetdate::"+targetdate.length);
     

 if(verifystatus=='Y')
	 {
 if(targetdate.length==0)
	  {
	 //popupCommonErrorMsg("Enter Verified Date");
	 jQuery("#dteSdadTargetDate").datebox("setValue",glpdate);
	  }
  
// alert("glddate"+glpdate);
  if(responsevalue.length==0)
  {
  popupCommonErrorMsg("Select Verified By");
  }
   
  
  if(responsevalue.length!=0&&targetdate.length!=0)
    	{
	     // alert("INSIDE THE NAVIGATE FORM");
    	navigateToNextForm("PlantsafetyVerify_save.safetyinspect?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&dtlkeyid="+keyid+"&verifystatus="+verifystatus+"&rmrks="+remarks);
    	closePopUpDialoge("loadAuditverify");
    	setTimeout(function() {
    	jQuery("#safetyinspectgrid").trigger("reloadGrid");
    
    	},1000);
    	}
	 }

   
}); */


jQuery("#btnsaveres").click(function(){
	   var keyid = jQuery("#hdndtlkeyid").val();
	   var responsevalue = jQuery("#cmbSdadResponsibilty").combobox('getValue');
	   var verifystatus = jQuery("#cmbSdamAudittype").combobox('getValue');
	   var targetdate = jQuery("#dteSdadTargetDate").datebox("getValue");
	   var remarks = jQuery("#txtSdadVerifyRemarks").val();

	   if(verifystatus == 'Y') {
	      if(targetdate.length == 0) {
	         jQuery("#dteSdadTargetDate").datebox("setValue", glpdate);
	         targetdate = glpdate;
	      }

	      if(responsevalue.length == 0) {
	         popupCommonErrorMsg("Select Verified By");
	         return false;
	      }

	      if(responsevalue.length != 0 && targetdate.length != 0) {
	         // Use AJAX call instead of navigateToNextForm to capture response
	         processAjaxCalls(
	            "PlantsafetyVerify_save.safetyinspect",
	            "q=2&responsevalue=" + responsevalue +
	            "&targetdate=" + targetdate +
	            "&dtlkeyid=" + keyid +
	            "&verifystatus=" + verifystatus +
	            "&rmrks=" + remarks,
	            "PSIVerify_successcallback",
	            "PSIVerify_errorcallback"
	         );

	         closePopUpDialoge("loadAuditverify");

	         setTimeout(function() {
	            jQuery("#safetyinspectgrid").trigger("reloadGrid");
	         }, 1000);
	      }
	   }
	});

	// Success callback
	function PSIVerify_successcallback(result) {
	   alert("Data Saved Successfully");
	}

	// Error callback
	function PSIVerify_errorcallback(result) {
	   popupCommonErrorMsg("Data Not Saved");
	}

</script>


<form id="frmAuditVerify" >
<table align="center" >
 <tr>
      <td>
      
     	<div id="Status"  style="margin-left:50%;width:210px; "">
			<label class="mandatory-lbl"><b>Verify Status</b></label>
			<div>
		<select id="cmbSdamAudittype" class="easyui-combobox" name="cmbSdamAudittype"   style="width:110px; ">  		
			   <option value='Y'><b>Yes</b></option>
				<option value='N'><b>No</b></option>
		   
				
			</select>
		    </div>
		    </div>
      
      
      </td>

          <td>
               <div style="margin-left:25%;"><label class="mandatory-lbl">Verified By</label></div>
								<div style="margin-left:25%;">
									<input id="cmbSdadResponsibilty" name="cmbSdadResponsibilty" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Empid}"/>
				</div>
          </td>
          
           <td>
       <div style="margin-left:40%;margin-top:15%;">
				   <input style="margin-left:588%;" type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:25px;display:;" value="Excel View" />
            </div>
      </td>
            
     
     </tr>
     <tr>
        
        <td>
               <div style="margin-left:50%;"><label class="mandatory-lbl">Verified Date</label></div>
								<div style="margin-left:50%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 110px;" id="dteSdadTargetDate" name="dteSdadTargetDate" 
								 										disabled="disabled"			value=""/>
							      	</span>
								</div>
		
          </td>
          <td>
              <div style="margin-left:20%;"><label class="mandatory-lbl">Remarks</label></div>
					<div style="margin-left:20%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSdadVerifyRemarks" name="txtSdadVerifyRemarks" style="resize:none;width:230px;" maxlength="1000"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					</div>
				
          </td>
     </tr>
    
  
</table>

          <div style="margin-left:37%; margin-top:3%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
</form>
