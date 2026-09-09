<script>
jQuery(document).ready(function(){
	 initialiseForm('frmActionPlanClosure'); 
	 var url = jQuery('#hiddenUrl').val();
	 formatDateBox('dteFromdate','dd-MMM-yyyy');
	 formatDateBox('dteTodate','dd-MMM-yyyy');
	 fillComboBox("frmActionPlanClosure","cmbDMT","sectionCombo.commonFilter");
	 fillComboBox("frmActionPlanClosure","cmbJH","cellCombo.commonFilter");
     fillComboBox("frmActionPlanClosure","cmbActionPlanType","ActionPlanType_Combo.appm","",false); 
	 viewGrid(url,"q=2");
	});


function ActionPlanClosureGrdbtnAPClosure_onClick(result){	
    var rowid=result.rowId;
	var btnid=result.btnId;
    var ActionPlanId=jQuery("#ActionPlanClosureGrd").jqGrid('getCell',rowid,"AplmKeyid");
    var DetailId=jQuery("#ActionPlanClosureGrd").jqGrid('getCell',rowid,"ApldKeyid");
    var Status=getFieldValue("cmbApldStatus_ActionPlanClosureGrd_"+rowid);
  //  alert(Status);
    var CompletedOn=getFieldValue("dteApldCompleatedon_ActionPlanClosureGrd_"+rowid);
	var CompletedBy=getFieldValue("cmbApldCompletedby_ActionPlanClosureGrd_"+rowid).trim();
    var CounterMeasure=getFieldValue("ApldCountermeasure_ActionPlanClosureGrd_"+rowid).trim();
    processAjaxCalls("ActionPlanClosure_Update.appm","ActionPlanId="+ActionPlanId+"&DetailId="+DetailId+"&Status="+Status+"&CompletedOn="+CompletedOn+"&CompletedBy="+CompletedBy+"&CounterMeasure="+CounterMeasure,'update_successCallBack','remove_errorCallBack');

	}
	
	

function ActionPlanClosureGrd_selectRow(rowId){

	jQuery("#cmbApldStatus_ActionPlanClosureGrd_"+rowId).change(function (){
		
		if( this.value != "C")
		{
			jQuery("#dteApldCompleatedon_ActionPlanClosureGrd_"+rowId).datebox("clear");
			disableField("frmActionPlanClosure", "dteApldCompleatedon_ActionPlanClosureGrd_"+rowId);		
		}
		else{
			enableFields("dteApldCompleatedon_ActionPlanClosureGrd_"+rowId);
			fillWithCurrentDate("dteApldCompleatedon_ActionPlanClosureGrd_"+rowId);
		}
	});
	jQuery("#dteApldCompleatedon_ActionPlanClosureGrd_"+rowId).datebox({  	   
		onSelect:function(value){
			
			var compdate = jQuery("#dteApldCompleatedon_ActionPlanClosureGrd_"+rowId).datebox("getValue");
			
			if( compareDate(compdate,currentDate) < 0 )
			{
				alert("Completed date can not be greater than current date");
				fillWithCurrentDate("dteApldCompleatedon_ActionPlanClosureGrd_"+rowId);
				return false;
			}
		} 
	});
	 
 
		
		}

function update_successCallBack(result){
	alert(result.successData.msg);
	jQuery('#ActionPlanClosureGrd').trigger("reloadGrid");
}

function viewGrid(url,dataString){  
	processGridnew(url, dataString, "ActionPlanClosureGrd", "Pager", "", "");
	return true;
}

jQuery("#btnview").click(function(){
	
	var fromDate=jQuery("#dteFromdate").datebox("getValue");
	var toDate=jQuery("#dteTodate").datebox("getValue");
	var Type=jQuery("#cmbActionPlanType").combobox("getValue");
	var DMT=jQuery("#cmbDMT").combobox("getValue");
	var JH=jQuery("#cmbJH").combobox("getValue");
	/* if(fromDate.length==0){
		alert("Select From Date");
		return false;
	}
	if(toDate.length==0){
		alert("Select To Date");
		return false;
	} */
	processGridnew("ActionPlanClosure_input.appm","?q=2&fromDate="+fromDate+"&toDate="+toDate+"&Type="+Type+"&DMT="+DMT+"&JH="+JH,"ActionPlanClosureGrd","grdUserPager","","");
});

jQuery("#btnRefresh").click(function(){
	var url=jQuery('#hiddenUrl').val();
	processGridnew(url,"q=2","ActionPlanClosureGrd", "Pager", "", "");
	jQuery("#dteFromdate").datebox("clear");
	jQuery("#dteTodate").datebox("clear");
	jQuery("#cmbActionPlanType").combobox("clear");
	jQuery("#cmbDMT").combobox("clear");
	jQuery("#cmbDMT").combobox("clear");
});

</script>
<form id="frmActionPlanClosure">
<div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tr>
		
			 <td>
		<div>
	<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>                       
	                 </div> 
			    <div style="margin-left:1px;margin-bottom: 5px;">
			    
		        <input id="cmbDMT" name="cmbDMT" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			  </div>
			 </td>
			 
			 
			 	 <td>
			 <div>
	<label style="margin-left:20px;margin-bottom: 1px;">JH</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom: 5px;">
			    
		        <input id="cmbJH" name="cmbJH" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			        </div>
			 </td>
			 
	
		<td>
		     <div  class="easyui-paddingbfpx">
		    <label style="margin-left:20px;margin-bottom: 1px;">ActionPlan Type</label> 
						                </div> 
						                <div style="margin-left:20px;margin-bottom: 5px;">
						         <input id="cmbActionPlanType" name="cmbActionPlanType" class="easyui-combobox"  style="width:130px">
						                </div>
						                </td>
			 <td>
			 <div>
	<label style="margin-left:20px;margin-bottom: 1px;">From Date</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom: 5px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteFromdate" name="dteFromdate" value=""/>
			        </div>
			 </td>
			 <td>
			    <label style="margin-left:15px;margin-bottom: 1px;">To Date</label>  
			    <div style="margin-left:15px;margin-bottom: 5px;">
			         <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteTodate" name="dteTodate" value=""/>
			          </div>
			  </td>
			  
			   <td>
			    <div style="margin-left:10px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;">
		  </div>
			  </td>
			    <td>
			    <div style="margin-left:20px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="Refresh" id="btnRefresh" style="height: 24px; width : 87px;">
		  </div>
			  </td>
		  </tr>
		 </table>
<table id="ActionPlanClosureGrd"></table>
<div id="Pager"></div>
</div>
</form>