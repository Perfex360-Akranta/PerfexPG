<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('rptRoleView');
		jQuery('#submitForm').val('rptRoleView');
		var url = jQuery('#hiddenUrl').val();
		//alert("URL: " +url);

		viewGrid(url, "q=1");

	});
/*
	function viewGrid(url, dataString) {

		processGridnew(url, dataString, "roleViewGrid", "pager", "","docDoubleClick");
		return true;
	}*/
	
	
	
	function viewGrid(url,filterString)
	{
		//alert("Filter String :" + filterString);
		if(filterString == "q=1" ){
			processGridnew(url, filterString, "roleViewGrid", "pager", "","docDoubleClick","","oplcumulativeOnload");
			return true;
		}
		if( validateFilterSelection(filterString))
		{
			
			processGridnew(url, filterString, "roleViewGrid", "pager", "","docDoubleClick","","oplcumulativeOnload");
			return true;
		}	
		return false;
	}
	
	function validateFilterSelection(filterString){
		/*var sectid=getFilterValue(filterString, "cmbSectid");
		//alert("Cell value :" + cellid);
		if(isEmpty(cellid)){
			alert("Select The JH");
			//showValidationErrorMsg("cmbSectid","Select JH");
 			//return false;
			return false;
		}	*/	
		if(filterString == "?q=1"){
			return true;
		}
		
		return true;
	}		
	
	function oplcumulativeOnload()
	{
		//setDrillDownHeader("jqgh_oplGrid_Company2","oplGrid","keyid2");
		//setTotalRowCss('roleViewGrid');
	}
	
	
	function docDoubleClick(id) { 
		//var rowData = jQuery("#list").jqGrid('getRowData',id);
		//var level = rowData.frl_level;
		//var Flid =  rowData.flid;
		//navigateToNextForm('roleteamall_input.roleteam?flid='+Flid+'&level='+level,"Role & Team");
	}
</script>


<form action="" method="post" id="rptRoleView">
	<div id="wrapper" style="width: 100%; padding: 0%;">
		
			<div style="margin-top: 0px; padding-top: 0px;"></div>
				<table id='roleViewGrid'>
					<tr>
						<td></td>
					<tr>
				</table>
			<div id='pager'></div>
		
	</div>


</form>



