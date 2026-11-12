<script type="text/javascript">
var glbtargetdate="";
var glbrevisedate="";
jQuery(document).ready(function(){
	initialiseForm('frmAuditClosure');
	var empid=jQuery('#hdnEmpid').val();
	//var empid=jQuery('#hdnEmpid').val();
	fillComboBox("frmAuditClosure","cmbsinmResponsibilty","employee.commonFilter?");
	//jQuery("#cmbsinmResponsibilty").combobox("disable");
	formatDateBox('dtesinmTargetDate','dd-MMM-yyyy');
	formatDateBox('dtesinmRevisedDate','dd-MMM-yyyy');		
	fillWithCurrentDate('dtesinmTargetDate');
	 disableField("frmAuditClosure","dtesinmRevisedDate");
	var createmode=jQuery('#frmcreatemode').val();
	 var keyid=jQuery("#hdndtlkeyid").val();
	 //jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled");
	 jQuery("#btnsaveres").attr("readonly", false).removeClass("ui-state-disabled");
	 glbtargetdate=jQuery('#dtesinmTargetDate').datebox('getValue');
	 var status=jQuery('#cmbSdamAudittype').combobox('getValue');
	 if(status.length!=0)
		 {
		    if(status=='C'){	 
		    	}
		 }
	 jQuery("#cmbSdamAudittype").combobox({
			onSelect:function(recordid)
			{
				alert("recordid"+recordid.id);
				alert(recordid);
				console.log(recordid);
			var status=jQuery("#cmbSdamAudittype").combobox('getValue');
			var flid=jQuery("#hdnflid").val();
			if(recordid.id=='P')
			  {
			jQuery("#btnsaveres").attr("disabled", true).addClass("ui-state-disabled");
				 fillWithCurrentDate('dtesinmRevisedDate');
				 disableField("frmAuditClosure","dtesinmTargetDate");
				 disableField("frmAuditClosure","cmbsinmResponsibilty");
				 readOnlyFields('txtsinmCorrective');
				 enableFields('dtesinmRevisedDate');
				
			  }	
			if(recordid.id=='C')
				{
				//alert(123);
				//alert(jQuery("#hdnEmpid").val());
				jQuery("#btnsaveres").attr("disabled", false).removeClass("ui-state-disabled"); 
				 disableField("frmAuditClosure","dtesinmRevisedDate");
				 //jQuery("#dtesinmRevisedDate").textbox("readonly");
				 //jQuery("#dtesinmRevisedDate").datebox("readonly", true);
				reloadCombo("frmAuditResponsibilty","cmbsinmResponsibilty","employee.commonFilter?flid="+flid);
				enableFields("dtesinmTargetDate");
				enableFields("cmbsinmResponsibilty");
				enableFields("txtsinmCorrective");
				fillWithCurrentDate('dtesinmTargetDate');
			
				
				}
			//jQuery("#cmbsinmResponsibilty").combobox("readonly", true);
			
			setTimeout(function() {
		    if (jQuery("#cmbsinmResponsibilty").length && jQuery("#cmbsinmResponsibilty").data('combobox')) {
		    	jQuery("#cmbsinmResponsibilty").combobox("readonly", true);
		    }
		}, 100); // 100 milliseconds delay
				
			}

		});

});





jQuery("#dtesinmTargetDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dtesinmTargetDate").datebox('getValue');
		var stringdate=convertStringToDate(datevalue);
		if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dtesinmTargetDate');
			return false;
		}
		if(stringdate > currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Future Date');
			fillWithCurrentDate('dtesinmTargetDate');
			return false;
		}
		    	
		} 
	});
	
	
	
jQuery("#dtesinmRevisedDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dtesinmRevisedDate").datebox('getValue');
	    var stringdate=convertStringToDate(datevalue);
	    if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dtesinmRevisedDate');
			return false;
		}
		    	
		} 
	});




jQuery("#btnsaveres").click(function(){
		
 var KEYID=jQuery("#hdnKeyid").val();
 var keyid=jQuery("#hdndtlkeyid").val();
 //var Aplmmst=jQuery("#hdnAplmmst").val();
 //var Aplmdtl=jQuery("#hdnAplmdtl").val();
	var responsevalue=jQuery("#cmbsinmResponsibilty").combobox('getValue');
    var status=jQuery("#cmbSdamAudittype").combobox('getValue');
    var targetdate=jQuery("#dtesinmTargetDate").datebox("getValue");
    var reviseddate=jQuery("#dtesinmRevisedDate").datebox("getValue");
    var correctiveaction=jQuery("#txtsinmCorrective").val();
    if(status=='P'){
	     if(reviseddate.length==0){
          jQuery('#dtesinmRevisedDate').datebox('setValue',glbrevisedate);
	       }
	     if(reviseddate.length!=0){
	  	    navigateToNextForm("AccActionPOP_save.ehsaccIncRpt?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&reviseddate="+reviseddate+"&status="+status+"&correctiveaction="+correctiveaction+"&Keyid="+KEYID);
	      	closePopUpDialoge("IncidentAction");
	      	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
	      	}
	   }
   else if(status=="C"){
	        if(targetdate.length==0){
		  		jQuery('#dtesinmTargetDate').datebox('setValue',glbtargetdate);
			}
	 		if(correctiveaction.length==0){
	    	 popupCommonErrorMsg("Enter Corrective Action");
	    	 return false;
	         }
	        if(responsevalue.length==0){
	         popupCommonErrorMsg("Select Completed By");
	         return false;
	         }
	        if(responsevalue.length!=0&&targetdate.length!=0&&correctiveaction.length!=0)
	   	     {
		     navigateToNextForm("AccActionPOP_save.ehsaccIncRpt?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&reviseddate="+reviseddate+"&status="+status+"&correctiveaction="+correctiveaction+"&Keyid="+KEYID);
	    	 closePopUpDialoge("IncidentAction");
	    	 jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
	    	 }
	    }
   
    setTimeout(function() {
    	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
    	},1000);	
});

jQuery("#btnexcelview").click(function(){
	//alert("CLICK EXCEL");
    var incKeyid= jQuery('#hdnkeyid').val();
    //alert("Keyid"+incKeyid);
    var flid = jQuery("#hdnflid").val();
 //    alert("The Flid"+flid);
     if(incKeyid != null && incKeyid.length >0){
         window.open("Accincident_Excelview.ehsaccIncRpt?incKeyid="+incKeyid+"&flid="+flid);
     }
     else
    	 {
    	 return false;
    	 }
});




jQuery("#btncrtView").click(function(){
    var keyid=jQuery("#hdnKeyid").val();
   /// alert("keyid::"+keyid);
 	var flid = jQuery("#hdnflid").val();
 	var type=jQuery("hdntype").val();
 	var Rolename=jQuery("hdnrolename").val();
 	if(type=="ATY00006"&&Rolename=="SHIFT INCHARGE")
 		{
 		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
        }
 	else if(type=="ATY00006"&&Rolename=="EHS PILLAR CHAMPION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
 	else if(type=="ATY00007"&&Rolename=="EHS PILLAR CHAMPION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
 	else if(type=="ATY00007"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
 	else if(type=="ATY00008"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
 	else if(type=="ATY00013"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00013"&&Rolename=="PBU HEAD")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00011"&&Rolename=="EHS PILLAR CHAMPION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00012"&&Rolename=="EHS PILLAR CHAMPION")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00011"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00012"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00010"&&Rolename=="SHIFT INCHARGE")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00010"&&Rolename=="FIRE OFFICER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
 	
    else if(type=="ATY00014"&&Rolename=="FIRE OFFICER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00014"&&Rolename=="UNIT EHS MANAGER")
		{
		LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
    }
    else if(type=="ATY00008"&&Rolename=="UNIT GM OPERATION")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
    else if(type=="ATY00013"&&Rolename=="UNIT GM OPERATION")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
    else if(type=="ATY00011"&&Rolename=="UNIT GM OPERATION")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
 	
    else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
 	
    else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
 	
    else if(type=="ATY00014"&&Rolename=="FIRE MARSHAL")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
 	
    else if(type=="ATY00010"&&Rolename=="FIRE MARSHAL")
	{
	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
}
 	
    else{
    	LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",false,true );
        
    }
	
});

jQuery("#btnInvstView").click(function(){
    var Keyid=jQuery("#hdnKeyid").val();
   // alert("keyid::"+Keyid);
 	var flid = jQuery("#hdnflid").val();
 	var type=jQuery("#hdntype").val();
 	//alert("type:::"+type);
 	var Rolename=jQuery("#hdnrolename").val();
 	//alert("Rolename:::"+Rolename);
 	if(type=="ATY00006"&&Rolename=="SHIFT INCHARGE")
		{
		//LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
 		LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
		}
	else if(type=="ATY00006"&&Rolename=="EHS PILLAR CHAMPION")
	{
	//LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
		LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
	}
	else if(type=="ATY00007"&&Rolename=="EHS PILLAR CHAMPION")
	{
		LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
	}
	else if(type=="ATY00007"&&Rolename=="UNIT EHS MANAGER")
	{
	
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
	}
	else if(type=="ATY00008"&&Rolename=="UNIT EHS MANAGER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
	}
	else if(type=="ATY00013"&&Rolename=="UNIT EHS MANAGER")
	{
		LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
		
	}
else if(type=="ATY00013"&&Rolename=="PBU HEAD")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	
}
else if(type=="ATY00011"&&Rolename=="EHS PILLAR CHAMPION")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	
	}
else if(type=="ATY00012"&&Rolename=="EHS PILLAR CHAMPION")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	
	}
else if(type=="ATY00011"&&Rolename=="UNIT EHS MANAGER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	
	}
else if(type=="ATY00012"&&Rolename=="UNIT EHS MANAGER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	
	}
else if(type=="ATY00010"&&Rolename=="SHIFT INCHARGE")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	}
else if(type=="ATY00010"&&Rolename=="FIRE OFFICER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	}
	
else if(type=="ATY00014"&&Rolename=="FIRE OFFICER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	}
else if(type=="ATY00014"&&Rolename=="UNIT EHS MANAGER")
	{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
	}
else if(type=="ATY00008"&&Rolename=="UNIT GM OPERATION")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
else if(type=="ATY00013"&&Rolename=="UNIT GM OPERATION")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
else if(type=="ATY00011"&&Rolename=="UNIT GM OPERATION")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
	
else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
	
else if(type=="ATY00012"&&Rolename=="UNIT GM OPERATION")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
	
else if(type=="ATY00014"&&Rolename=="FIRE MARSHAL")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
	
else if(type=="ATY00010"&&Rolename=="FIRE MARSHAL")
{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );

}
	
else{
	LoadPopUp("IncidentCA","safteyincidentanalysis_input.ehsaccIncRpt?q=2&keyid="+Keyid, true,"90%","80%","10%","5%", "","Investigation"," "," " );
 
}

});
</script>


<form id="frmAuditClosure" >
<table align="center" style="width:100%;" >
     <tr>
    <td style="vertical-align: top; text-align: left; padding-left:80px;">
        <label class="mandatory-lbl"><b>Completion Status</b></label><br/>
        <select id="cmbSdamAudittype"
                class="easyui-combobox"
                name="cmbSdamAudittype"
                style="width:180px;">
            <option value='C'>Completed</option>
            <option value='P'>Pending</option>
        </select>
    </td>

    <td style="vertical-align: top; text-align: left;">
        <label class="mandatory-lbl">Completed By</label><br/>
        <input id="cmbsinmResponsibilty"
               name="cmbsinmResponsibilty"
               class="easyui-combobox"
               style="width:250px;"
               value="${requestScope.Empid}" />
    </td>

    <td style="vertical-align: top; text-align: left; padding-left:10px;">
        <br/>
        <input type="button"
               id="btnexcelview"
               name="btnexcelview"
               class="easyui-button"
               style="width:100px;height:25px;"
               value="Excel View" />
    </td>
</tr>

     <tr>
          <td style="vertical-align: top; text-align: left; padding-left:80px;">
              <label class="mandatory-lbl">Action Taken</label><br/>
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmCorrective" name="txtsinmCorrective" style="resize:none;width:180px;" maxlength="100"  
																		value="${requestScope.sheTlAudittmst.sdamMembers}"></textarea>
					
				
          </td>
          <td>
          <table>
          <tr>
          <td td style="vertical-align: top; text-align: left;">
          		<label class="mandatory-lbl">Completed Date</label><br/>
						<!--  	<span style="float:left;padding-right:0px;">-->					
						<input class="easyui-text" style=" width : 110px;" id="dtesinmTargetDate" name="dtesinmTargetDate" 
								 													value=""/>
							      		<!-- </span>-->
				</td>
		<td td style="vertical-align: top; text-align: left;padding-left:40px;padding-top:15px">
     
				   <input type="button" id="btncrtView" name="btncrtView" class="easyui-button" style="width:100px;height:22px;display:;" value="View" />
 
				  
	</td>
          </tr>
       <tr>
          <td style="vertical-align: top; text-align: left;">
          <label class="mandatory-lbl">Revised Date</label><br/>
								
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 110px;" id="dtesinmRevisedDate" name="dtesinmRevisedDate" 
								 													value=""/>
							      	</span>
								
								 </td>
								 
	<td style="vertical-align: top; text-align: left; padding-left:40px;padding-top:15px">
     
				   <input type="button" id="btnInvstView" name="btnInvstView" class="easyui-button" style="width:100px;height:22px;display:;" value="Investigation" />
 
				  
	</td>
								</tr>
								
         
            </table>
       
		

  
</table>

          <div style="margin-left:49%; margin-top:3%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnAplmmst" name="hdnAplmmst" value="${requestScope.Aplmmst}"></input>
<input type="hidden" id="hdnAplmdtl" name="hdnAplmdtl" value="${requestScope.Aplmdtl}"></input>
<input type="hidden" id="hdnEmpid" name="hdnEmpid" value="${requestScope.Empid}"></input>
<input type="hidden" id="hdnname" name="hdnname" value="${requestScope.rname}"></input>
<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.Keyid}"></input>
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>
</form>
