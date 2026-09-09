<script>
jQuery(document).ready(function(){
	 initialiseForm('frmKaizenDateChange'); 
	 var url = jQuery('#hiddenUrl').val();
	 formatDateBox('dteFromdate','dd-MMM-yyyy');
	 formatDateBox('dteTodate','dd-MMM-yyyy');
	 fillComboBox("frmKaizenDateChange","cmbDMT","sectionCombo.commonFilter");
	 fillComboBox("frmKaizenDateChange","cmbJH","cellCombo.commonFilter");
	 viewGrid(url,"q=2");
	});


function tblKaizenDateChangebtnDateUpdate_onClick(result){	
    var rowid=result.rowId;
	var btnid=result.btnId;
    var KaizenKeyid=jQuery("#tblKaizenDateChange").jqGrid('getCell',rowid,"KznmKeyid");
   // alert(KaizenKeyid);
   	var KaizenDate=getFieldValue("KznmDate_tblKaizenDateChange_"+rowid);
    var r=confirm("Are You Sure Want To Change Date?");
    if(r==true){
        processAjaxCalls("KaizenDateChange_Update.appm","KaizenKeyid="+KaizenKeyid+"&KaizenDate="+KaizenDate,'update_successCallBack','remove_errorCallBack');	
    }
    else{   	
    	return false;
    }

	}

function update_successCallBack(result){
	alert(result.successData.msg);
	jQuery('#tblKaizenDateChange').trigger("reloadGrid");
}

function viewGrid(url,dataString){  
	processGridnew(url, dataString, "tblKaizenDateChange", "Pager", "", "");
	return true;
}

jQuery("#btnview").click(function(){
	
	var fromDate=jQuery("#dteFromdate").datebox("getValue");
	var toDate=jQuery("#dteTodate").datebox("getValue");
	var DMT=jQuery("#cmbDMT").combobox("getValue");
	var JH=jQuery("#cmbJH").combobox("getValue");
	if(fromDate.length==0){
		alert("Select From Date");
		return false;
	}
	if(toDate.length==0){
		alert("Select To Date");
		return false;
	}
	
	processGridnew("KaizenDateChange_input.appm","?q=2&fromDate="+fromDate+"&toDate="+toDate+"&DMT="+DMT+"&JH="+JH,"tblKaizenDateChange","grdUserPager","","");
});

jQuery("#btnRefresh").click(function(){
	var url = jQuery('#hiddenUrl').val();
	processGridnew(url,"q=2", "tblKaizenDateChange", "Pager", "", "");
	jQuery("#dteFromdate").datebox("clear");
	jQuery("#dteTodate").datebox("clear");
	jQuery("#cmbDMT").combobox("clear");
	jQuery("#cmbJH").combobox("clear");
});

</script>
<form id="frmKaizenDateChange">
<div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tbody>
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
			 <div>
	<label style="margin-left:25px;margin-bottom: 1px;">From Date</label>                       
	                 </div> 
			    <div style="margin-left:25px;margin-bottom: 5px;">
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
			    <div style="margin-left:10px;margin-bottom:-10px;">
			         <input type="button"  class="easyui-button" value="Refresh" id="btnRefresh" style="height: 24px; width : 87px;">
		  </div>
			  </td>
		  </tr>
		 </table>
<table id="tblKaizenDateChange"></table>
<div id="Pager"></div>
</div>
</form>