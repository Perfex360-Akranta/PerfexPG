<script type="text/javascript">
var glbdate="";
jQuery(document).ready(function(){
	initialiseForm('frmResponseAudit');
    jQuery('#submitForm').val('frmResponseAudit');	
	fillComboBox("frmResponseAudit","cmbsinmResponsibilty","employee.commonFilter" );
	formatDateBox('dtesinmTargetDate','dd-MMM-yyyy');
	fillWithCurrentDate('dtesinmTargetDate');
	glbdate=jQuery("#dtesinmTargetDate").datebox('getValue');

});


jQuery("#dtesinmTargetDate").datebox({  	   
	onSelect:function(recordid)
		{ 
		var currentDate = getServerDateTime();
		//alert("currentDate"+currentDate);
		//alert(recordid);
		var datevalue=jQuery("#dtesinmTargetDate").datebox('getValue');
		//alert("datevalue:::"+datevalue);
		var stringdate=convertStringToDate(datevalue);
		//alert("stringdate"+stringdate);
		if(stringdate < currentDate)
		{  
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dtesinmTargetDate');
			return false;
		}
		    	
		} 
	});


jQuery("#btnsaveres").click(function(){
	
 // alert("INSIDE THE CLICK FUNCTION");
   var keyid=jQuery("#hdndtlkeyid").val();
   var KEYID=jQuery("#hdnKeyid").val();
   var responsevalue=jQuery("#cmbsinmResponsibilty").combobox('getValue');
   var rmrks=jQuery("#txtsinmrepsonseRemarks").val();
 //  alert("responsevalue:::"+responsevalue);
   var targetdate=jQuery("#dtesinmTargetDate").datebox("getValue");
   if(targetdate.length==0){
	  jQuery("#dtesinmTargetDate").datebox("setValue",glbdate);
	  }
  if(responsevalue.length==0){
  popupCommonErrorMsg("Select Responsibilty");
  return false;
  }
  
  if(responsevalue.length!=0&&targetdate.length!=0){
    navigateToNextForm("AccResponsePOP_save.ehsaccIncRpt?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&Keyid="+KEYID+"&rmrks="+rmrks);
  	closePopUpDialoge("IncidentResponse");
  	navigateToNextForm("Accincident_input.ehsaccIncRpt?mode=Response&");
  	
  	alert("Response Assigned Successfully");
  	//refreshForm();
}
  else{
	  return false;
  }

  setTimeout(function() {
  	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
  	},1000);	
  
   
});

jQuery('#chkkzbnOthers').click(function() {
    
    //alert(" Others "+jQuery('#chkkzbnOthers').val());
    
    if(jQuery("#chkkzbnOthers").is(':checked')== true){
   	 jQuery('#chkkzbnOthers').val('Y');
   	 var sat = jQuery('#chkkzbnOthers:checked').val();
	     jQuery("#cmbsinmResponsibilty").combobox('setValue',"");
		 othersClickAction(sat);
    }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
        jQuery('#chkkzbnOthers').val('N');
        var sat = jQuery('#chkkzbnOthers').val();
   	 jQuery("#cmbsinmResponsibilty").combobox('setValue',"");
		 othersClickAction(sat);
	 }
});


function othersClickAction(sat)
{

	//var cellId = jQuery("#frmNearMissNew input[id='cell']").val();
	//var flid = jQuery("#frmNearMissNew input[id='flid']").val();
	if(sat=="Y")
	{
	  reloadCombo("frmResponseNear","cmbsinmResponsibilty","employee.commonFilter?&others=Y");
	}
	else
	{  
	  reloadCombo("frmResponseNear","cmbsinmResponsibilty","employee.commonFilter?&others=Y");
	}	
}

jQuery("#btnexcelview").click(function(){
	//alert("CLICK EXCEL");
    var incKeyid= jQuery('#hdnKeyid').val();
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
  //  alert("keyid::"+keyid);
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


jQuery("#btnInvestview").click(function(){
	//alert(123);
    var Keyid=jQuery("#hdnKeyid").val();
   // alert("keyid::"+Keyid);
 	var flid = jQuery("#hdnflid").val();
 	var type=jQuery("#hdntype").val();
 	//alert("type:::"+type);
 	var Rolename=jQuery("#hdnrolename").val();
 //	alert("Rolename:::"+Rolename);
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


<form id="frmResponseAudit" >
<div id="loadresponse">
<table align="center" style="width:100%;" >
     <tr>
          <td style="vertical-align: top; text-align: left; padding-left:100px; padding-top:30px">
               <label class="mandatory-lbl">Responsibily</label>
                <span style="padding-left:10px;">
                <input type="checkbox" id="chkkzbnOthers" name="chkkzbnOthers"    style="margin-left:10px;" />
            </span>
            <span style="padding-left:05px;">
                <label>    Others</label><br/>
            </span>
								
									<input id="cmbsinmResponsibilty" name="cmbsinmResponsibilty" class="easyui-combobox"  style="width: 230px;"
																					value="${requestScope.Response}"/>
				
          </td>
          
          <td style="vertical-align: top; text-align: left;padding-top:30px ">
               <label class="mandatory-lbl">Target Date</label><br/>
								
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 100px;" id="dtesinmTargetDate" name="dtesinmTargetDate" 
								 													value=""/>
							      	</span>
								
		
          </td>
            <td style="vertical-align: top; text-align: left;">
            
				   <input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:83px;height:25px;display:;" value="Excel View" />
            
          </td> 
     
     </tr>
     <tr>
          <td style="vertical-align: top; text-align: left;padding-left:100px">
               <label>Remarks</label><br/>
					
						<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmrepsonseRemarks" name="txtsinmrepsonseRemarks" style="resize:none;width:230px;" maxlength="1000"  
																		value="${requestScope.remarks}">${requestScope.remarks}</textarea>
					
				
		
          </td>
          
          <td>
          <table>
          <tr>
            <td style="vertical-align: top; text-align: left;">
            
				   <input type="button" id="btncrtView" name="btncrtView" class="easyui-button" style="width:100px;height:22px;display:;" value="View" />
             
            
          </td>
          </tr>
          <tr>
          <td style="vertical-align: top; text-align: left; padding-top:30px;">
            
             <input type="button" id="btnInvestview" name="btnInvestview" class="easyui-button" style="width:100px;height:25px;display:;" value="Investigation" />
            
			
             </td>
          </tr>
          </table>
 </td>
     </tr>
    
  
</table>

          <div style="margin-left:37%; margin-top:3%;">
								<input type="button" class="easyui-button" value ="submit" id="btnsaveres" style="height:23px;"/>	
							</div>
	
           


<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnaplmid" name="hdnaplmid" value="${requestScope.aplmaster}"></input>
<input type="hidden" id="hdnapldid" name="hdnapldid" value="${requestScope.apldetail}"></input>
<input type="hidden" id="hdnrspn" name="hdnrspn" value="${requestScope.Response}"></input>
<input type="hidden" id="hdnobserve" name="hdnobserve" value="${requestScope.observe}"></input>
<input type="hidden" id="hdnKeyid" name="hdnKeyid" value="${requestScope.Keyid}"></input>
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.rolename}"/>
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>

</div>
</form>
