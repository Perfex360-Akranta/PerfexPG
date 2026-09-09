<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				var url = "kaizenrewardreport_input.krsr";//jQuery('#hiddenUrl').val();
				//alert(123);
				setLoadFormCallBackFrmId("frmKaizenRewardSchemeReport"); 
				invokeAfterLoadFormCallBack();
				viewGrid(url,"?q=2");
			});

	 function frmKaizenSummaryReport_afterLoadCallBack(){
		toggleCommonFilter();
		 } 		
	function viewGrid(url,dataString)
	{
		processGridnew(url,dataString,"kaizenSmryViewGrid","pager","","");
		return true;
	}
	
	
	
</script>
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >

<table id='kaizenSmryViewGrid'>
		<tr>
		<td></td>
		<tr>
	</table>
<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>