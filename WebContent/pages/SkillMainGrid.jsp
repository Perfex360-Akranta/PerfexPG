<script type="text/javascript">

		jQuery.noConflict();
		jQuery(document).ready(function(){
			
			var url = jQuery('#hiddenUrl').val();
			var mode = "VIEW";	
			viewGrid(url,"?q=");										
		});

		function viewGrid(url,filterString)
		{		
			processGridnew(url,filterString+'&closeOnSave=true',"list","pager","","eqpDoubleClick");			
			return true;	
		}

		
		function eqpDoubleClick(id)
		{	
			var rowData = jQuery("#list").jqGrid('getRowData',id);			
			var keyid = rowData.KEYID;			
			navigateToNextForm('SkillCheckList_input.checkList'+'?keyId='+keyid+'&closeOnSave=true&eqpMode=update',"");
		}
		
	
		
		
		
	
		
		
		
</script>
<form id="equipmentMainGrid">
<div id="wrapper">

	<div class="clear"></div>
	<div style="width: 100%;">
	<label class="notes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.DoubleClick}</label>
	</div>
<table id="list" ></table>
<div id="pager"></div>
</div>
</form>
<input type="hidden" id="hiddenArr" name ="hiddenArr"/>
