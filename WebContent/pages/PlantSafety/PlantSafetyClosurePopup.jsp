<script type="text/javascript">
var glbtargetdate="";
var glbrevisedate="";
jQuery(document).ready(function(){
	jQuery("#loadAuditclosure").css('border',"1px solid  #d8fbfb").css("border-radius", "1px");
	initialiseForm('frmAuditClosure');
	var empid=jQuery('#hdnEmpid').val();
	//var empid=jQuery('#hdnEmpid').val();
	fillComboBox("frmAuditClosure","cmbSdadResponsibilty","employee.commonFilter?");
	jQuery("#cmbSdadResponsibilty").combobox("disable");
	formatDateBox('dteSdadTargetDate','dd-MMM-yyyy');
	formatDateBox('dteSdadRevisedDate','dd-MMM-yyyy');		
	fillWithCurrentDate('dteSdadTargetDate');
	fillWithCurrentDate('dteSdadRevisedDate');
	
	 disableField("frmAuditClosure","dteSdadRevisedDate");
	var createmode=jQuery('#frmcreatemode').val();
	 var keyid=jQuery("#hdndtlkeyid").val();
	 jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled");
	 glbtargetdate=jQuery('#dteSdadTargetDate').datebox('getValue');
	 var status=jQuery('#cmbSdamAudittype').combobox('getValue');
	 if(status.length!=0)
		 {
		    if(status=='C'){	
				disableField("frmAuditClosure","dteSdadTargetDate");

		    	}
		 }
	 
	 jQuery("#btnsaveres").click(function(){
			
		 var keyid=jQuery("#hdndtlkeyid").val();
		 var Aplmmst=jQuery("#hdnAplmmst").val();
		 //alert(Aplmmst);
		 var Aplmdtl=jQuery("#hdnAplmdtl").val();
		 var abnormality=jQuery("#hdnAbnKeyid").val();

		var Suggestion=jQuery("#hdnSuggestion").val();
		   var responsevalue=jQuery("#cmbSdadResponsibilty").combobox('getValue');
		   
		   var status=jQuery("#cmbSdamAudittype").combobox('getValue');

		   var targetdate=jQuery("#dteSdadTargetDate").datebox("getValue");
		   

		   var reviseddate=jQuery("#dteSdadRevisedDate").datebox("getValue");
		   var remarks=jQuery("#txtSdadCloseRemarks").val();
		   
		   var correctiveaction=jQuery("#txtSdadCorrective").val();
		//alert("targetdate::"+targetdate);
		   if(status=='P')
			   {
			     
			      if(reviseddate.length==0)
				  {
				   //popupCommonErrorMsg("Enter Revised Date");
			    	jQuery('#dteSdadRevisedDate').datebox('setValue',glbrevisedate);
			    	
				  }
			     
			    
			      if(reviseddate.length!=0)
			     	{
			  	     // alert("INSIDE THE NAVIGATE FORM");
			 	navigateToNextForm("plantsafetyActionClosure_save.safetyinspect?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&dtlkeyid="+keyid+"&reviseddate="+reviseddate+"&status="+status+"&correctiveaction="+correctiveaction+"&Aplmmst="+Aplmmst+"&Aplmdtl="+Aplmdtl+"&Suggestion="+Suggestion);
			      	closePopUpDialoge("loadAuditclosure");
			      	
			      	jQuery("#safetyinspectgrid").trigger("reloadGrid");
			      	//alert("Response Assigned Successfully");
			      	//refreshForm();
			      	}
			   }
		   else if(status=="C")
			   {
			   if(targetdate.length==0)
				  {
				  //popupCommonErrorMsg("Enter Completed Date");
				  jQuery('#dteSdadTargetDate').datebox('setValue',glbtargetdate);
				
				  }
			  
			   if(correctiveaction.length==0){
			    	 popupCommonErrorMsg("Enter Corrective Action");
			    	 return false;
			     }
			      
			  if(responsevalue.length==0)
			  {
			  popupCommonErrorMsg("Select Completed By");
			  return false;
			  }
			  
			  
			 
				  //  alert("INSIDE THE NAVIGATE FORM   " +targetdate);
				     processAjaxCalls("plantsafetyActionClosure_save.safetyinspect","q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&dtlkeyid="+keyid+"&reviseddate="+reviseddate+"&status="+status+"&correctiveaction="+correctiveaction+"&Aplmmst="+Aplmmst+"&Aplmdtl="+Aplmdtl+"&Suggestion="+Suggestion+"&rmrks="+remarks+"&abnormality="+abnormality,"PSIClosure_successcallback","");
					    
			    //	navigateToNextForm("plantsafetyActionClosure_save.safetyinspect?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&dtlkeyid="+keyid+"&reviseddate="+reviseddate+"&status="+status+"&correctiveaction="+correctiveaction+"&Aplmmst="+Aplmmst+"&Aplmdtl="+Aplmdtl+"&rmrks="+remarks+"&abnormality="+abnormality);
			    	closePopUpDialoge("loadAuditclosure");
			    	
			    	setTimeout(function() {
			    	
			    	jQuery("#safetyinspectgrid").trigger("reloadGrid");
			    	},1000);
			    	//alert("Response Assigned Successfully");
			    	//refreshForm();
			    	
			   
			   }
		   
		});


});


jQuery("#btnexcelview").click(function(){
	
    var plansafKeyid=jQuery("#hdnpalnkey").val();
  //  alert("Keyid"+plansafKeyid);
	  var flid = jQuery("#hdnflid").val();
	//  alert("The Flid"+flid);
   if(plansafKeyid != null && plansafKeyid.length >0){
       window.open("Plantsafety_Excelview.safetyinspect?plansafetyKeyid="+plansafKeyid+"&flid="+flid);
   }
   else
  	 {
  	 return false;
  	 }
});

jQuery("#cmbSdamAudittype").combobox({
	onSelect:function(recordid){	
	var status=jQuery("#cmbSdamAudittype").combobox('getValue');
	var flid=jQuery("#hdnflid").val();
	if(recordid.id=='P')
	  {
	jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled");
		 fillWithCurrentDate('dteSdadRevisedDate');
		 disableField("frmAuditClosure","dteSdadTargetDate");
		 disableField("frmAuditClosure","cmbSdadResponsibilty");
		 readOnlyFields('txtSdadCorrective');
		 enableFields('dteSdadRevisedDate');
		
	  }	
	if(recordid.id=='C')
		{
		jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
		 disableField("frmAuditClosure","dteSdadRevisedDate");
		reloadCombo("frmAuditResponsibilty","cmbSdadResponsibilty","employee.commonFilter?flid="+flid);
		
		fillWithCurrentDate('dteSdadTargetDate');
		enableFields("cmbSdadResponsibilty");
		enableFields("txtSdadCorrective");
		
		}
	jQuery("#cmbSdadResponsibilty").combobox("disable");
		
	}

});


/* jQuery("#dteSdadTargetDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dteSdadTargetDate").datebox('getValue');
		var stringdate=convertStringToDate(datevalue);
		if(stringdate<currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dteSdadTargetDate');
			return false;
		}
		if(stringdate>currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Future Date');
			fillWithCurrentDate('dteSdadTargetDate');
			return false;
		}
		return true;
		} 
	});
	 */
	
	
jQuery("#dteSdadRevisedDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dteSdadRevisedDate").datebox('getValue');
	    var stringdate=convertStringToDate(datevalue);
	    if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dteSdadRevisedDate');
			return false;
		}
		    	
		} 
	});



jQuery("#btnview").click(function(){
	var Keyid=jQuery("#hdnpalnkey").val();
	var role=jQuery("#hdnrolename").val();
	var flid=jQuery("#hdnflid").val();
	LoadPopUp("Plantsafety","safetyinspection_input.safetyinspect?keyid="+Keyid+"&mode="+"modify"+"&audittype="+role+"&auditflid="+flid+"&popup=Y", true,"90%","80%","10%","5%", "","Investigation"," ",true,true );
	
});



function PSIClosure_successcallback(result){
	alert("Data Saved SuccessFully");
	
}
</script>


<form id="frmAuditClosure" >
<table align="center" style="width:100%;">
     <tr>
        <td>
               	<div id="Status"  style="margin-left:10%;">
			<label style="margin-left:0%;" class="mandatory-lbl"><b>Completion Status</b></label>
			<div>
		<select id="cmbSdamAudittype" class="easyui-combobox" name="cmbSdamAudittype"   style="width:180px; ">  		
				  <option value='C'><b>Completed</b></option>
				<option value='P'><b>Pending</b></option>
				<!-- <option value='W'><b>Work In Progress</b></option> -->
		    <!-- <option value='C'><b>Completed</option> -->
				
			</select>
		    </div>
		    </div>
        
        
        </td>
        
        <td>
         <div style="margin-left:07%;"><label class="mandatory-lbl">Completed By</label></div>
								<div style="margin-left:07%;">
									<input id="cmbSdadResponsibilty" name="cmbSdadResponsibilty" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Empid}"/>
        </div>
        </td>
         <td>
          <div style="margin-left:0%;"><label class="mandatory-lbl">Completed Date</label></div>
								<div style="margin-left:01%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-datebox" style=" width : 100px;" id="dteSdadTargetDate" name="dteSdadTargetDate" 
								 													/>
							      	</span>
								</div>
      </td>
     
        <td>
       <div style="margin-left:10%;margin-top:15%;">
				   <input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:25px;display:;" value="Excel View" />
            </div>
      </td>
     
     </tr>
     
     
     
     <tr>
          <td>
              <div style="margin-left:10%;"><label class="mandatory-lbl">Action Taken</label></div>
					<div style="margin-left:10%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSdadCorrective" name="txtSdadCorrective" style="resize:none;width:180px;" maxlength="100"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					</div>
				
          </td>
          <td>
              <div style="margin-left:6%;"><label class="mandatory-lbl">Remarks</label></div>
					<div style="margin-left:6%;">
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSdadCloseRemarks" name="txtSdadCloseRemarks" style="resize:none;width:230px;" maxlength="1000"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					</div>
				
          </td>
         
          <td>
          <!-- <div style="margin-left:08%;margin-top:-2%;"> --><label style="margin-left:00%;" class="mandatory-lbl">Revised Date</label><!--</div>  -->
								<div style="margin-left:00%;">
									<span style="margin-left:0px;">					
								 		<input class="easyui-datebox" style=" width : 100px;" id="dteSdadRevisedDate" name="dteSdadRevisedDate" 
								 													 disabled="disabled"/>
							      	</span>
								</div>
          </td>
         
          
          <td>
       <div style="margin-left:10%;margin-top:5%;">
				   <input type="button" id="btnview" name="btnview" class="easyui-button" style="width:80px;height:25px;display:;" value="View" />
            </div>
      </td>
     </tr>
         <tr> <td></td><td>     
		
<div style="margin-left:30%; margin-top:4%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
<td>  </tr>
</table>

          
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnAplmmst" name="hdnAplmmst" value="${requestScope.Aplmmst}"></input>
<input type="hidden" id="hdnAplmdtl" name="hdnAplmdtl" value="${requestScope.Aplmdtl}"></input>
<input type="hidden" id="hdnEmpid" name="hdnEmpid" value="${requestScope.Empid}"></input>
<input type="hidden" id="hdnname" name="hdnname" value="${requestScope.rname}"></input>
<input type="hidden" id="hdnpalnkey" name="hdnpalnkey" value="${requestScope.modiefiedid}"></input>
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.modiefiedid}"></input>
<input type="hidden" id="hdnAbnKeyid" name="hdnAbnKeyid" value="${requestScope.Abnormality}"></input>
<input type="hidden" id="hdnSuggestion" name="hdnSuggestion" value="${requestScope.Suggestion}"></input>
</form>

