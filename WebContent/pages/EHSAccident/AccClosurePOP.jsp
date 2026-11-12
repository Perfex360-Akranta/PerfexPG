<script type="text/javascript">
var glpdate="";
jQuery(document).ready(function(){
	initialiseForm('frmAuditVerify');
	//alert(jQuery('#frmmsttype').val());
	//jQuery('#submitForm').val('frmAuditVerify');	
	fillComboBox("frmAuditVerify","cmbsinmResponsibilty","employee.commonFilter" );
	//jQuery("#cmbsinmResponsibilty").combobox("disable");

	formatDateBox('dtesinmClosureDate','dd-MMM-yyyy');
	fillWithCurrentDate('dtesinmClosureDate');
	var date=jQuery('#dtesinmClosureDate').datebox('getValue');
	 glpdate=date;
	jQuery("#btnsaveres").attr("disabled", true).addClass("ui-state-disabled"); 
	var createmode=jQuery('#frmcreatemode').val();
	 var keyid=jQuery("#hdndtlkeyid").val();
	 jQuery('#cmbSdamAudittype').combobox('setValue','Y');
	 jQuery('#dtesinmClosureDate').datebox('setValue',date);
	 var status=jQuery('#cmbSdamAudittype').combobox('getValue');
	   if(status=='Y')
		   {
		   jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
		   }
	 //alert("keyid::"+keyid);

});


jQuery("#cmbSdamAudittype").combobox({
	onSelect:function(recordid){	
	
		//alert(recordid.id);
		var currentDate = getServerDateTime();
		//var stringdate=convertStringToDate(currentDate)
		//alert("TETS");
		//alert(recordid.id);
		var verifystatus=jQuery("#cmbSdamAudittype").combobox('getValue');
		var flid=jQuery("#hdnflid").val();
		var responsevalue=jQuery("#hdnempid").val();
		
	
      if(recordid.id=='Y')
		 {
		 //jQuery("#dtesinmClosureDate").val(stringdate);
		 jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
		 reloadCombo("frmAuditVerify","cmbsinmResponsibilty","employee.commonFilter?flid="+flid);
		 fillWithCurrentDate('dtesinmClosureDate');
		 jQuery("#cmbsinmResponsibilty").combobox('setValue',responsevalue);
		 jQuery("#cmbsinmResponsibilty").combobox('disable');
		 }
      else if(recordid.id=='N')
    	  {
    	  //alert("INSIDE THE ELSE");
    	  jQuery("#dtesinmClosureDate").datebox("clear");
    	  jQuery("#cmbsinmResponsibilty").combobox("clear");
    	jQuery("#btnsaveres").attr("disabled", true).addClass("ui-state-disabled"); 
    	    //clearFields('');
    	  }
	}
});


jQuery("#dtesinmClosureDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		//alert("currentDate"+currentDate);
		//alert(recordid);
		var datevalue=jQuery("#dtesinmClosureDate").datebox('getValue');
		//alert("datevalue:::"+datevalue);
		var stringdate=convertStringToDate(datevalue);
		//alert("stringdate"+stringdate);
		if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dtesinmClosureDate');
			return false;
		}
		    	
		} 
	});





jQuery("#btnsaveres").click(function(){
	
 // alert("INSIDE THE CLICK FUNCTION");
   var keyid=jQuery("#hdndtlkeyid").val();
   var KEYID=jQuery("#hdnkeyid").val();
  // alert("keyid::"+keyid);
   var responsevalue=jQuery("#cmbsinmResponsibilty").combobox('getValue');
   var verifystatus=jQuery("#cmbSdamAudittype").combobox('getValue');
 //  alert("responsevalue::"+responsevalue.length);
   var targetdate=jQuery("#dtesinmClosureDate").datebox("getValue");
   var remarks=jQuery("#txtsinmcloseRemarks").val();
  // alert("targetdate::"+targetdate.length);
     

 if(verifystatus=='Y')
	 {
 if(targetdate.length==0)
	  {
	 //popupCommonErrorMsg("Enter Verified Date");
	 jQuery("#dtesinmClosureDate").datebox("setValue",glpdate);
	  }
  
// alert("glddate"+glpdate);
  if(responsevalue.length==0)
  {
  popupCommonErrorMsg("Select Verified By");
  }
   
  
  if(responsevalue.length!=0&&targetdate.length!=0)
    	{
	     // alert("INSIDE THE NAVIGATE FORM");
    	navigateToNextForm("AccClosurePOP_save.ehsaccIncRpt?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&Keyid="+KEYID+"&verifystatus="+verifystatus+"&rmrks="+remarks);
    	closePopUpDialoge("IncidentClosure");
    	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
    	//alert("Response Assigned Successfully");
    	//refreshForm();
    	}
	 }
 //jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
 setTimeout(function() {
 	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
 	},1000);	
   
});

</script>


<form id="frmAuditVerify" >
<table align="center" >
 <tr>
      <td style="width: 172px; "><div id="Status"  style="margin-left:50%; width: 130px">
			<label class="mandatory-lbl"><b>Closure Status</b></label>
			<div style="width: 138px; ">
		<select id="cmbSdamAudittype" class="easyui-combobox" name="cmbSdamAudittype"   style="width:110px; ">  		
			   <option value='Y'><b>Yes</b></option>
				<option value='N'><b>No</b></option>
		   
				
			</select>
		    </div>
		    </div>
      </td>
      
       <td>
               <div style="margin-left:15%;"><label class="mandatory-lbl">Closured By</label></div>
								<div style="margin-left:15%;">
									<input id="cmbsinmResponsibilty" name="cmbsinmResponsibilty" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Empid}"/>
				</div>
          </td>
 
                  <td>
      <div style="margin-left:10%;margin-top:10%;">
				   <input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:100px;height:25px;display:;" value="Excel View" />
            </div>
            </td>
 
 </tr>



     <tr>
         
          <!--     <td>
     <div style="margin-left:90%;;margin-top:10%;">
				   <input type="button" id="btncrtView" name="btncrtView" class="easyui-button" style="width:100px;height:22px;display:;" value="View" />
 
				  </div>
	</td>
          -->
      <td>
 
               <div style="margin-left:50%;"><label class="mandatory-lbl">Closure Date</label></div>
								<div style="margin-left:50%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 110px;" id="dtesinmClosureDate" name="dtesinmClosureDate" 
								 													value=""/>
							      	</span>
								</div>
		
          </td>
           <td>
              <div style="margin-left:15%;"><label>Remarks</label></div>
					<div style="margin-left:15%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmcloseRemarks" name="txtsinmcloseRemarks" style="resize:none;width:230px;" maxlength="100"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					</div>
				
          </td>
     
     </tr>
     <tr>
          
     </tr>
    
  
</table>

          <div style="margin-left:43%; margin-top:3%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.Keyid}"></input>
<input type="hidden" id="hdnempid" name="hdnempid" value="${requestScope.Empid}"></input>
</form>
