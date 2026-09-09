
<script type="text/javascript">

jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	//alert(url);
	viewGrid(url,"?q=2");
	
});
	
function viewGrid(url,filterString)
{
	var tableCaption = "Inspection List";
	//alert(url);
	processGridnew(url,filterString,"inspectionListgrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
    return true;
}
function doubleClickGrid(rowid) 
{ 
	//alert(1);
	var rowData = jQuery("#inspectionListgrid").jqGrid('getRowData',rowid );
	var keyid = rowData.keyid;
	//alert(keyid);
	navigateToNextForm("hseinspecEntry_input.hseinsp?keyid="+keyid);
}
</script>
<form name="frmInspectionList" id="frmInspectionList" >
<div id="wrapperRpt"  >
	<table id="inspectionListgrid"></table>
	<div id="pager"></div>
</div>

</form>


















<!-- <script>

		jQuery.noConflict();
		jQuery(document).ready(function(){
			
			var url = jQuery('#hiddenUrl').val();
	
			var dataStr="?q=2";			
			viewGrid(url,dataStr);													
		});

function viewGrid(url,filterString)
	{
		alert("test grid");					
		processGridnew(url ,filterString,"list","pager","","");
		return true;
	}
	
</script>

<form>
<div id="WrapperRpt">
	 <div> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
	 </div>
</div>
</form>

 -->