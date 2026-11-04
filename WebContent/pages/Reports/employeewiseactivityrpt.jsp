<!--//  Author ManiKandan-->
<script type="text/javascript">
jQuery(document).ready(function(){	
var url = jQuery("#hiddenUrl").val();

	viewGrid(url,"?q=");

});
	function viewGrid(url,filterString)
		{
			
			if( validateFilterSelection(filterString))
			{				
				processGridnew(url,filterString,"employee","pager","","employeeDoubleClick");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
		
			return  true;
		}
		function employeeDoubleClick(id)
		{
			var rowData = jQuery("#employee").jqGrid('getRowData',id);

			var workOrderno=rowData.WoNo;
			var empKeyId=rowData.empmid;
			window.open("employeeWiseExl_view.map?empkeyId="+empKeyId+"&WONo="+workOrderno);	
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			
			//jQuery("#chkMonthwise").attr('checked',true);	
			/*setTimeout(function() {jQuery('#flsFrmMonth').val(' ');},200);
			jQuery('#flsToMonth').val(' ');
			jQuery('#flsAssembly').val(' ');*/
			var fromMonth = jQuery("#hdnfromMonth").val();
			var toMonth = jQuery("#hdntoMonth").val();
			jQuery('#dtefromMonth').datebox('setValue',fromMonth);
			jQuery('#dtetoMonth').datebox('setValue',toMonth);	
			var fromDate=jQuery("#hdnfromDate").val();
			var toDate=jQuery("#hdntoDate").val();
			jQuery('#dtefromDate').datebox('setValue',fromDate);
			jQuery('#dtetoDate').datebox('setValue',toDate);
			readOnlyFields('dteyear');
			readOnlyFields('dteyear');
			readOnlyFields('dtetoMonth');
			readOnlyFields('dtefromMonth');
			readOnlyFields('dtefromDate');
			readOnlyFields('dtetoDate');
			readOnlyFields('dtefromDate');
			readOnlyFields('chkMonthwise');
			readOnlyFields('chkDatewise');
			
			jQuery('#cmbCircle').combobox('disable');
			
		}
		/*function frmFilter_enableDisableSuccessCallBack()
		//{
			jQuery("#chkMonthwise").attr('checked',true);
			enableFields('chkMonthwise');
			enableFields('chkDatewise');
		}*/
	

</script>
<form id="frmEmployeeWiseActivity">
<div id="wrapperRpt">
<table id="employee" ></table>
<div id="pager"></div>
</div>
</form>


