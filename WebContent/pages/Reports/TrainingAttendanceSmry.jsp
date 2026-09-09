<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
		
			  var dataString = " ";
			  var url = jQuery('#hiddenUrl').val();
			  	
				viewGrid("trnAttendanceSmry_input.empatt","?q=1");
		});
	
		function viewGrid(url,filterString)
		{
			
			processGridnew(url,filterString,"TrnAttSmry","pageTASmry","Training Program Calender Report","","","");			
			return true;
	
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
		
			
			jQuery('#disableFuncLoc').val('disable');
		}
		
		function fillForm(id)
		{
			 var row = jQuery("#list").jqGrid('getDataIDs');
				//alert("row"+row);
				 var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
				// alert("cm"+Object.keys(cm));
				
				
						
			
		}	
		
</script>
<form name = 'frmTrainingAttSmry'>
<div id="wrapperRpt"> 


	<div style=""> 
			 <table id="TrnAttSmry" style=""><tr><td/></tr></table>
			 <div id="pageTASmry"></div>
	</div>
</div>



</form>