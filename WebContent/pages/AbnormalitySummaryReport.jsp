<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				
				var url = jQuery('#hiddenUrl').val();
				//viewGrid(url,"q=2");
				setLoadFormCallBackFrmId("frmabnormalsummaryreport");
				invokeAfterLoadFormCallBack();
			});

			
	function viewGrid(url,dataString)
	{
		processGridnew(url,dataString,"abnSmryViewGrid","pager","","");
		
		return true;
	}
	
	function frmabnormalsummaryreport_afterLoadCallBack(){
		toggleCommonFilter();			
}

</script>


<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
   			
	<table id='abnSmryViewGrid'>
		<tr>
			<td></td>
		<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>			
	
