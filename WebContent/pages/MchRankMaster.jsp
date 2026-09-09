<script>

jQuery(document).ready(function(){
	
	var url = jQuery('#hiddenUrl').val();
	jQuery('#submitForm').val('frmMachineRank');
	initialiseForm('frmMachineRank');	
	viewGrid(url,"?q=2");	
	
});
jQuery("#btnneweval").click(function(){
	 var formTitle;
	
	 navigateToNextForm('MchrnkmstrMain_input.mchrnkmstr',"Machine Rank Sheet");
	
});
function viewGrid(url,filterString)
{		
	processGridnew(url,filterString,"MchRank","Machinepager","","MachineDoubeClick");			
	return true;	
}
function MachineDoubeClick(id){
	
	var rowData = jQuery("#MchRank").jqGrid('getRowData',id);	
	var keyId = rowData.Keyid;
	 navigateToNextForm("MchrnkmstrMain_input.mchrnkmstr?&grid=true&keyId="+keyId ,"Machine Rank Sheet");
}

</script>
<form id="frmMachineRank">

 <div id="wrapperRpt">
 	<div class="easyui-paddingbfpx" id="divtop" >
 	<span ><input id="btnneweval" class="easyui-button btn-HeightSmall"  type="button" value="New Evaluation" /></span>
		</div>
		<table id="MchRank" ></table>
			<div id="Machinepager"></div>

	</div>

</form>