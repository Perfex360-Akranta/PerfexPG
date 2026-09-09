<script>
jQuery(document).ready(function(){
	 initialiseForm('frmAbnDelete');
	 var url = jQuery('#hiddenUrl').val();
	 formatDateBox('dteAbnFromdate','dd-MMM-yyyy');
	 formatDateBox('dteAbnTodate','dd-MMM-yyyy');
	 fillComboBox("frmAbnDelete","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");
	 fillComboBox("frmActionPlanClosure","cmbAbnDelDMT","sectionCombo.commonFilter");
	 fillComboBox("frmActionPlanClosure","cmbAbnDelJH","cellCombo.commonFilter");
	 viewGrid(url,"q=2");
	});


function AbnDeleteGrdbtnAbnDelete_onClick(result){	
	
    var rowid=result.rowId;
	var btnid=result.btnId;
    var AbnmKeyid=jQuery("#AbnDeleteGrd").jqGrid('getCell',rowid,"AbnmKeyid");
    var Status=getFieldValue("cmbAbnmStatus_AbnDeleteGrd_"+rowid);
   
   
	
	processAjaxCalls("AbnDelete_Delete.appm","AbnmKeyid="+AbnmKeyid,'delete_successCallBack','remove_errorCallBack');

	}
	
	
/* function AbnDeleteGrd_selectRow(rowId){

	jQuery("#cmbAbnmStatus_AbnDeleteGrd_"+rowId).change(function (){
		
		if( this.value != "C")
		{
			jQuery("#dteAbnmWoendtime_AbnDeleteGrd_"+rowId).datebox("clear");
			disableField("frmAbnDelete", "dteAbnmWoendtime_AbnDeleteGrd_"+rowId);		
		}
		else{
			enableFields("dteAbnmWoendtime_AbnDeleteGrd_"+rowId);
			fillWithCurrentDate("dteAbnmWoendtime_AbnDeleteGrd_"+rowId);
		}
	});
	jQuery("#dteAbnmWoendtime_AbnDeleteGrd_"+rowId).datebox({  	   
		onSelect:function(value){
			var compdate = jQuery("#dteAbnmWoendtime_AbnDeleteGrd_"+rowId).datebox("getValue");
			if( compareDate(compdate,currentDate) < 0)
			{
				alert("Completed date can not be greater than current date");
				fillWithCurrentDate("dteAbnmWoendtime_AbnDeleteGrd_"+rowId);
				return false;
			}
		} 
	});
		} */

function delete_successCallBack(result){
	alert(result.successData.msg);
	jQuery('#AbnDeleteGrd').trigger("reloadGrid");
}

function viewGrid(url,dataString){  
	processGridnew(url, dataString, "AbnDeleteGrd", "Pager", "", "");
	return true;
}

jQuery("#btnview").click(function(){
	
	var fromDate=jQuery("#dteAbnFromdate").datebox("getValue");
	var toDate=jQuery("#dteAbnTodate").datebox("getValue");
	var TagClassId=jQuery("#cmbAbnmTagclassid").combobox("getValue");
	var DMT=jQuery("#cmbAbnDelDMT").combobox("getValue");
	var JH=jQuery("#cmbAbnDelJH").combobox("getValue");
	if(fromDate.length==0){
		alert("Select From Date");
		return false;
	}
	if(toDate.length==0){
		alert("Select To Date");
		return false;
	}
	processGridnew("AbnormalityDelete_input.appm","?q=2&fromDate="+fromDate+"&toDate="+toDate+"&TagClassId="+TagClassId+"&DMT="+DMT+"&JH="+JH,"AbnDeleteGrd","Pager","","");
});


jQuery("#btnRefresh").click(function(){
	 var url = jQuery('#hiddenUrl').val();
	processGridnew(url,"q=2","AbnDeleteGrd", "Pager", "", "");
	jQuery("#dteAbnFromdate").datebox("clear");
	jQuery("#dteAbnTodate").datebox("clear");
    jQuery("#cmbAbnmTagclassid").combobox("clear");
	jQuery("#cmbAbnDelDMT").combobox("clear");
	jQuery("#cmbAbnDelJH").combobox("clear");
});



</script>
<form id="frmAbnDelete">
<div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tbody>
		<tr>
		
		
	<td>
		<div>
	<label style="margin-left:1px;margin-bottom: 1px;">DMT</label>                       
	                 </div> 
			    <div style="margin-left:1px;margin-bottom:1px;">
			    
		        <input id="cmbAbnDelDMT" name="cmbAbnDelDMT" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			  </div>
			 </td>
			 
			 
		<td>
	<div>
	<label style="margin-left:20px;margin-bottom: 1px;">JH</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom:1px;">
			    
		        <input id="cmbAbnDelJH" name="cmbAbnDelJH" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
			        
			        </div>
			 </td>
	
		
		
			 <td>
			 <div>
	       <label style="margin-left:20px;margin-bottom: 1px;">From Date</label>                       
	                 </div> 
			    <div style="margin-left:20px;margin-bottom: 5px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteAbnFromdate" name="dteAbnFromdate" value=""/>
			        </div>
			 </td>
			 <td>
			    <label style="margin-left:15px;margin-bottom: 1px;">To Date</label>  
			    <div style="margin-left:15px;margin-bottom: 5px;">
			         <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteAbnTodate" name="dteAbnTodate" value=""/>
			          </div>
			  </td>
			  
			   <td>
			    <label style="margin-left:25px;margin-bottom: 1px;">Type</label>  
			    <div style="margin-left:25px;margin-bottom: 5px;">
  		<input id="cmbAbnmTagclassid" name="cmbAbnmTagclassid" tabindex="14" class="easyui-combobox"  style="width:120px;" value=""/>
  

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
		 </div>
<table id="AbnDeleteGrd"></table>
<div id="Pager"></div>

</form>