<style>

</style>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(
			function() {
				var url = jQuery('#hiddenUrl').val();
				setLoadFormCallBackFrmId("frmKaizenSummaryReport"); 
				invokeAfterLoadFormCallBack();
				//viewGrid(url,"q=2");
			});

	function frmKaizenSummaryReport_afterLoadCallBack(){
		toggleCommonFilter();
		 }		
	function viewGrid(url,dataString)
	{
		processGridnew(url,dataString,"kaizenSmryViewGrid","pager","","doubleClickGrid");
		return true;
	}
	
	function doubleClickGrid(rowid){
			var url=jQuery("#kaizenSmryViewGrid").jqGrid('getGridParam', 'url');
			url=url.replace('getData','input');
			url=escape(url);
			var kznKeyid=jQuery('#kaizenSmryViewGrid').getCell(rowid,"KZNM_KEYID");
			//alert(kznKeyid);
			//navigateToNextForm("kaizen_input.kaizen?kznKeyid="+kznKeyid+"&mode=view&filterButton=false","",null,{"filterString":url});
			LoadPopUp("KznSumm","kaizen_input.kaizen?kznKeyid="+kznKeyid+"&mode=view&filterButton=false",true,"1170px","590px","6px","5%","multiSelectOk_Callback","Kaizen Summary Report","",true);
	}
	
</script>
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >	
Double Click on Grid to View Data in Popup
<table id='kaizenSmryViewGrid'>
		<tr>
		<td></td>
		<tr>
	</table>
<div id='pager'></div>	
</div>
<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>