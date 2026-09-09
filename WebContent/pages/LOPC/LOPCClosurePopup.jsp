
<script type="text/javascript">
var glbtargetdate="";
var glbrevisedate="";
jQuery(document).ready(function(){
	initialiseForm('frmLOPCActionClosure');
	//alert(jQuery('#frmmsttype').val());
	//jQuery('#submitForm').val('frmLOPCActionClosure');	
	 //disableField("frmLOPCActionClosure","dteWwbdCompleteddate");
	var empid=jQuery('#hdnEmpid').val();
	fillComboBox("frmLOPCActionClosure","cmbWwbdCompletedby","employee.commonFilter?");
	//jQuery("#cmbWwbdCompletedby").combobox("disable");
	
	
	var newmode=jQuery("#hdnnewmode").val();
	
	if(newmode=="viewpop"){
		//disableUIButton("#btncrtView");
		
		jQuery("#excel").hide();
		
		jQuery("#txtWwbdActiontaken").attr("disabled",true);
		jQuery("#txtWwbdRemarks").attr("disabled",true);
		 jQuery("#cmbWwbdStatus").combobox('disable');
		 //jQuery('#btnexcelview').attr('disabled','disabled');
	// 	 disableField("frmLOPCActionClosure","dteWwbdCompleteddate");
		
	//	 jQuery("#btnexcelview").attr("disabled",true).addClass("ui-state-disabled");
	     //jQuery("#btnsaveres").attr("disabled",true).addClass("ui-state-disabled");
	     jQuery("#btnsaveres").css('display','none');
	   //  jQuery("#btnexcelview").css('display','none');
		
	}
	
	formatDateBox('dteWwbdCompleteddate','dd-MMM-yyyy');
	//fillWithCurrentDate('dteWwbdCompleteddate');
	//
	var createmode=jQuery('#frmcreatemode').val();
	 var keyid=jQuery("#hdndtlkeyid").val();
	 jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled");
	 glbtargetdate=jQuery('#dteWwbdCompleteddate').datebox('getValue');
	 //glbrevisedate=jQuery('#dteSdadRevisedDate').datebox('getValue');

	// jQuery("#dteSdadRevisedDate").datebox("disable");
	 
	 //disableField("frmLOPCActionClosure","dteSdadRevisedDate");
	 var status=jQuery('#cmbWwbdStatus').combobox('getValue');
	 //alert(status);
	 if(status.length!=0)
		 {
		    if(status=='C')
		    	{//alert("inside the if");
		    	/// disableField("frmLOPCActionClosure","dteSdadRevisedDate");
		    	 //disableField("frmLOPCActionClosure","dteSdadRevisedDate");
		         //jQuery("#dteSdadRevisedDate").datebox('setValue',' ');
		     
		    	
		    	 
		    	}
		 }
	 //alert("keyid::"+keyid);
	 var popup=jQuery("#hdnpopup").val();
	 if(popup=="Y")
		 {
		 jQuery("#cmbWwbdCompletedby").combobox('disable');
		 jQuery("#cmbWwbdStatus").combobox('disable');
		jQuery("#btncrtView").hide();
		 jQuery("#txtWwbdRemarks").val(jQuery("#hdnremarks").val());
		 jQuery("#dteWwbdCompleteddate").datebox('setValue', jQuery("#hdntargetdate").val());
			// jQuery("#chkkzbnOthers").attr("disabled", true);
		 }

});

jQuery("#dteWwbdCompleteddate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		var occurDate=jQuery("#hdnoccdate").val();
	    var datevalue=jQuery("#dteWwbdCompleteddate").datebox('getValue');
		var stringdate=convertStringToDate(datevalue);
		var stringdatecon=convertStringToDate(occurDate.substring(0,11));
		if(stringdate > currentDate)
		{  
			popupCommonErrorMsg('Should Not Enter Future Date');
			fillWithCurrentDate('dteWwbdCompleteddate');
			return false;
		}
		
		if(stringdatecon > stringdate)
		{  
			popupCommonErrorMsg('Should Not Enter Pass Date');
			fillWithCurrentDate('dteWwbdCompleteddate');
			return false;
		}
		
		} 
	});
	
	
jQuery("#btnsaveres").click(function(){
	
var keyid=jQuery("#hdndtlkeyid").val();
//alert(keyid);
var remarks=jQuery("#txtWwbdRemarks").val();
//alert(remarks);
var CompletedBy=jQuery("#cmbWwbdCompletedby").combobox('getValue');
//alert(CompletedBy);
var status=jQuery("#cmbWwbdStatus").combobox('getValue');
//alert(status);
var targetdate=jQuery("#dteWwbdCompleteddate").datebox("getValue");
//alert(targetdate);
var correctiveaction=jQuery("#txtWwbdActiontaken").val();
//alert(correctiveaction);

    if(status=="C")
	   {
	   if(targetdate.length==0)
		  {
		jQuery('#dteWwbdCompleteddate').datebox('setValue',glbtargetdate);
		}
	  if(correctiveaction.length==0){
	    	 popupCommonErrorMsg("Enter Corrective Action");
	     }
	      
	  if(CompletedBy.length==0)
	  {
	  popupCommonErrorMsg("Select Completed By");
	  }
	   
	  
	  if(CompletedBy.length!=0&&targetdate.length!=0&&correctiveaction.length!=0)
	   	{
	    
		  processAjaxCalls("LOPCActionClosure_update.lopc" ,"q=2&CompletedBy="+CompletedBy+"&targetdate="+targetdate+"&keyid="+keyid+"&status="+status+"&correctiveaction="+correctiveaction+"&remarks="+remarks,"LOPCActionClosure_Success"," ");
	   
        closePopUpDialoge("loadactionclosure");
        setTimeout(function() {
		    	
	    	 jQuery("#LOPCActionClosureGrid").trigger("reloadGrid");
	    	},1000);
	    	}
	   
	   }
});

function LOPCActionClosure_Success(result){
	alert(result.successData.msg);	
}

</script>
<form id="frmLOPCActionClosure" >
<table align="center" >
     <tr>
        <td>
               	<div id="Status"  style="margin-left:30%;">
			<label class="mandatory-lbl"><b>Completion Status</b></label>
			<div>
		<select id="cmbWwbdStatus" class="easyui-combobox" name="cmbWwbdStatus"   style="width:180px; ">  		
				<option value='C'><b>Completed</b></option>
				<option value='P'><b>Pending</b></option>
				
				<!-- <option value='W'><b>Work In Progress</b></option> -->
		    <!-- <option value='C'><b>Completed</option> -->
				
			</select>
		    </div>
		    </div>
        
        
        </td>
        
        <td>
         <div style="margin-left:40%;"><label class="mandatory-lbl">Completed By</label></div>
								<div style="margin-left:40%;">
									<input id="cmbWwbdCompletedby" name="cmbWwbdCompletedby" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Response}"/>
        
        </td>
        
          <td>
          <div style="margin-left:100%;"><label class="mandatory-lbl">Completed Date</label></div>
								<div style="margin-left:100%;">
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 100px;" id="dteWwbdCompleteddate" name="dteWwbdCompleteddate" 
								 													value=""/>
							      	</span>
								</div>
      </td>
     
     
     
     </tr>
     
     
     
     <tr>
          <td>
              <div style="margin-left:30%;"><label class="mandatory-lbl">Action Taken</label></div>
					<div style="margin-left:30%;">
						<textarea id="txtWwbdActiontaken" name="txtWwbdActiontaken" style="resize:none;width:180px;" maxlength="100"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					</div>
				
          </td>
          
           <td>
              <div style="margin-left:40%;"><label>Remarks</label></div>
					<div style="margin-left:40%;">
						<textarea id="txtWwbdRemarks" name="txtWwbdRemarks" style="resize:none;width:230px;" maxlength="100"  
																		value="${requestScope.remarks}">${requestScope.remarks}</textarea>
					</div>
				
          </td>
     
           
          
	</tr>	

  
<tr>

          <td>


							</td>
							</tr>
							</table>
							
		
          <div style="margin-left:40%; margin-top:4%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>					
	
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.keyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnEmpid" name="hdnEmpid" value="${requestScope.Response}"></input>
<input type="hidden" id="hdnpopup" name="hdnpopup" value="${requestScope.popup}"></input>
<input type="hidden" id="hdnremarks" name="hdnremarks" value="${requestScope.remarks}"></input>
<input type="hidden" id="hdntargetdate" name="hdntargetdate" value="${requestScope.targetdate}"></input>
<input type="hidden" id="hdnnewmode" name="hdnnewmode" value="${requestScope.newmode}"></input>
</form>
