<script>
jQuery(document).ready(function(){
	 initialiseForm('frmKaizenApprovalDelete');
	 var url = jQuery('#hiddenUrl').val();
	 //fillComboBox("frmKaizenApprovalDelete","cmbKapKznmKeyid","kaizenno_combo.appm");
	 // added here 
	 viewGrid("KaizenView_input.kaizen","q=2");
	 // end
	});
	


function KaizenApprovalDeleteGrdbtnKaizenApprovalDelete_onClick(result){	
	
    var rowid=result.rowId;
	var btnid=result.btnId;
    var KaizenApprovalKeyid=jQuery("#KaizenApprovalDeleteGrd").jqGrid('getCell',rowid,"KaizenApprovalKeyid");
   
	processAjaxCalls("KaizenApprovalDelete_Delete.appm","KaizenApprovalKeyid="+KaizenApprovalKeyid,'delete_successCallBack','remove_errorCallBack');

	}


function delete_successCallBack(result){
	alert(result.successData.msg);
	jQuery('#KaizenApprovalDeleteGrd').trigger("reloadGrid");
}

function viewGrid(url,dataString){  
	//processGridnew(url, dataString, "KaizenApprovalDeleteGrd", "Pager", "", "");
	processGridnew(url, dataString, "KaizenApprovalDeleteGrd", "Pager", "", "kapDblClickGrid", "");
	return true;
}

//jQuery("#btnviewKaizenNo").click(function(){
	
	//var KznKeyid=jQuery("#cmbKapKznmKeyid").combobox("getValue");
	//if(KznKeyid.length==0){
		//alert("Select Kaizen No");
		//return false;
	//}
		//processGridnew("KaizenView_input.kaizen","?q=2&empkzn="+KznKeyid,"KaizenApprovalDeleteGrd","Pager","","kapDblClickGrid","");
//});


// added function
function kapDblClickGrid(rowid){
	var kznKeyId=jQuery('#KaizenApprovalDeleteGrd').getCell(rowid,"IMPRVNO");
	LoadPopUp("DivKapWorkFlowPopup","KaizenApprovalWorkFlowPopup_input.appm?q=2&kznKeyid="+kznKeyId,true,"70%","70%","10%","15%","","Kaizen Work Flow","",false,true);
}
// end

</script>
<form id="frmKaizenApprovalDelete">
<!-- <div id="wrapperRpt" style="margin-top:20px;">
<table style="margin-top:-16px;">
		<tbody>
		<tr>
		 <td>
		<div>
			<label style="margin-left:1px;margin-bottom: 1px;">Kaizen No</label>                       
		</div> 
		<div style="margin-left:1px;margin-bottom:1px;">
			<input id="cmbKapKznmKeyid" name="cmbKapKznmKeyid" type="text" class="easyui-combobox" style="width: 212px; height: 24px; margin-left: 0px;" value="">
		</div>
	</td>
	<td>
			    <div style="margin-left:20px;margin-top:16px;">
			         <input type="button"  class="easyui-button" value="View" id="btnviewKaizenNo" style="height: 24px; width : 87px;">
		  </div>
			  </td>
		  </tr>
		 </table>
		 </div> -->
<table id="KaizenApprovalDeleteGrd"></table>
<div id="Pager"></div>

<div class="sub-header" style="width:1152px;margin-top:10px;">Work Flow</div>
<div id="divKapWorkFlow" style="margin-top:5px;">
</div> 

</form>