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
			
				processGridnew(url,filterString,"list","pager","","");
				return true;
			}	
		}

		function validateFilterSelection(filterString){
		
			return  true;
		}
		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery("#chkMonthwise").attr('checked',true);
			enableFields('chkMonthwise');
			enableFields('chkDatewise');
		}
	

</script>
<form id="frmElapsed">
<div id="wrapperRpt">
	<div class="clear"></div>
<table id="list" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
</div>
</form>


