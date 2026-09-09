<script>
jQuery(document).ready(function(){
	initialiseForm("frmEnergyIdeabank");
	jQuery("#submitForm").val("frmEnergyIdeabank");
	var url = jQuery("#hiddenUrl").val();
	fillComboBox("frmEnergyIdeabank", "cmbIbkmIdeaCat","Ideacategory.ewib");
	processGridnew(url,"&q=1","EnergyIdeaBankGrd","pager","","","","");	
});

function viewGrid(filterString){
   processGridnew("Energywaterbank_input.ewib",filterString,"EnergyIdeaBankGrd","pager","","","");
	
}

jQuery("#btnAdd").click(function(){
	var row=jQuery("#EnergyIdeaBankGrd").jqGrid("getDataIDs");
	addRow(row);
});

function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnIbkmKeyid:" ",
			 dteIbkmIdeaDate:" ",
			 txtIbkmIdeaDesc:" ",
			 cmbIbkmManagerId:" ", ManagerId:" ", txtIbkmOthers:" ", cmbIbkmLocation:" ", Location:" ", cmbIbkmIdeaCat:" ", IdeaCat:" ",
			 txtIbkmIdeaBenefit:" ", txtIbkmIdeaInvestment:" ", txtIbkmPayBack:" ", txtIbkmVendorName:" ", txtIbkmContactNo:" ", txtIbkmMobileNo:" ",
			 txtIbkmEmail:" ", btnFilManage:" ", btnFilManage1:" ", cmbIbkmStatus:" ", dteIbkmStartDate:" ", dteIbkmCompletedDate:" "}];
	jQuery("#EnergyIdeaBankGrd").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnIbkmKeyid:" ",
			dteIbkmIdeaDate:" ",
			txtIbkmIdeaDesc:" ",
			cmbIbkmManagerId:" ", ManagerId:" ", txtIbkmOthers:" ", cmbIbkmLocation:" ", Location:" ", cmbIbkmIdeaCat:" ",
			IdeaCat:" ", txtIbkmIdeaBenefit:" ", txtIbkmIdeaInvestment:" ", txtIbkmPayBack:" ", txtIbkmVendorName:" ", txtIbkmContactNo:" ", txtIbkmMobileNo:" ",
			txtIbkmEmail:" ", btnFilManage:" ", btnFilManage1:" ", cmbIbkmStatus:" ", dteIbkmStartDate:" ", dteIbkmCompletedDate:" "}];
		jQuery("#EnergyIdeaBankGrd").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}
    
function frmEnergyIdeabank_beforeSubmit(){
	 var EnergyKeyid=jQuery("#hdnkeyid").val();
	 //alert(EnergyKeyid);
	 var gridval=getGridSelectArray('EnergyIdeaBankGrd');
	// alert(gridval);
	 if(gridval != ""){	
	    return 'IdeaDetails='+gridval+"&EnergyKeyid="+EnergyKeyid;
	 }
	 return false;
	
}


function EnergyIdeaBankGrdbtnFilManage_onClick(result){
	  //alert("Click");
      var documentNo=jQuery("#hdnkeyid").val();
   //   alert("Document"+documentNo);
	   if(documentNo != null && documentNo != ''){
        
			fileManagerPopUp(documentNo,"IBK","","","","");
		}
	   else {    
		 saveForm('frmEnergyIdeabank','Energywaterbank_save.ewib?openfilemgr=openfilemgr');
	    }	
}




function EnergyIdeaBankGrd_selectRow(rowId){
	
	var jqGridId="EnergyIdeaBankGrd";
	enableFormFields('frmEnergyIdeabank',"dteIbkmCompletedDate_"+jqGridId+"_"+rowId);
	
	//numericTextBox("txtIbkmMobileNo");
    
	jQuery("#cmbIbkmStatus_EnergyIdeaBankGrd_"+rowId).combobox({  	   
    	onSelect:function(recordid)
    		{ 
    		var status=jQuery("#cmbIbkmStatus_EnergyIdeaBankGrd_"+rowId).combobox('getValue');
    	    if(status=="I"){
    	    	enableFormFields('frmEnergyIdeabank',"dteIbkmCompletedDate_"+jqGridId+"_"+rowId);
    	    }
    	    else{ 	
    	      disableField('frmEnergyIdeabank',"dteIbkmCompletedDate_"+jqGridId+"_"+rowId);
    	    }
    	    
    		}
    	});
}

function frmEnergyIdeabank_successsCallback(result){
	var keyid=result.successData.keyId;
	var openfilemgr = result.successData.openfilemgr;
	//alert(openfilemgr);  
	 if(openfilemgr==true ){ 
		 fileManagerPopUp(keyid,"IBK","","","", "");
	 }
	 else{
		 navigateToPrevForm();	 
	} 
}


</script>
<form id="frmEnergyIdeabank">
<div id='wrapperRpt' >
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;">
<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
			<div  id="frmMultipleAbnormalityFuntKeyIds">
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="factory"  value="" ></input>
			<input type="hidden" id="section" name="section"  value=""></input>
			<input type="hidden" id="cell"    name="cell"     value=""></input>
			<input type="hidden" id="machine" name="machine"  value=""></input>
			<input type="hidden" id="flid" name="cmbKzbnFlid"  value="${requestScope.kaizenbank.kzbnFlid }"></input>
			</div>
			<div id="frmKaizenfunloc" style="width: 50%; "></div>
			<table>
			<tr>
			<td>	
			<span id="err_abnmfunLocation" class="tpm-errormsg"></span>
			</td>
			</tr>
			</table>
             <div style="margin-left:0px; margin-top:10px;">
						  <input type="button" class="easyui-button" value ="Add" id="btnAdd" style="height:25px;" >
						  </div>
						   <div style="margin-left:55px; margin-top:-25px;">
						  <span>
						  <input type="button" class="easyui-button" value ="Delete" id="btndelete" style="height:23px;" >
						  </span>	
						  </div>
						  </div>
						  </td>
						  </tr></table>
<div style="margin-top: 0px">
<table  id='EnergyIdeaBankGrd'>
<tr>
<td>
</td>
</tr>
</table>
<div id='pager'></div>
</div>
</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.keyid}"/>
</form>