<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		var mode = "VIEW";
		var dataString ="";	
		viewGrid(url);
		jQuery('#btnAdd').click(function(){
			navigateToNextForm("roletopicEntry_input.tatnd?q=2&mode=create","Role Topic Link");
		});										
	});

	function viewGrid(url)
	{	
		processGridnew(url,"q=2","list","pager","","docDoubleClick");
		return true;	
	}
	
	function docDoubleClick(id)
	{	
		
		//var rowData = jQuery("#list").jqGrid('getRowData',id);
		//var keyId=rowData.KeyID;
		navigateToNextForm("roletopicEntry_input.tatnd"+"?q=2&mode=MODIFY" ,"Role Topic Link");
		
	}
	
	function docSuccessCallback()
	{
	}		
</script>
<form id="frmRoleTopicLink">	
	<div id="wrapperRpt" style="padding-left:4%;">
		<div style="padding-left:0%;margin-top: -28px">
			<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="New"  style=" width : 49px;"/>
		<span style="padding-left:10px; ">Double Click on row to input/view details</span>
		</div>
		<div class="clear" ></div>
		<div style="margin-top:-25px\9;">
		<label class="notes"   style="font-weight: bold; padding-left:0px; " > ${requestScope.DoubleClick}</label>
		<div style="margin-top:6px;margin-top:10px\9;">
		<table id="list" ></table>
		<div id="pager"></div>
		</div>
		
		</div>
	</div>	
</form>