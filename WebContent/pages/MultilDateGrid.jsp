	
<script type="text/javascript">

jQuery(document).ready(function(){//alert(12);
	 initialiseForm("frmMultiGrid");

	jQuery('#submitForm').val('frmMultiGrid');
	//alert('inside');
	
	 var url = jQuery('#hiddenUrl').val();
	//alert(url);
    viewGrid("Multiskill_input.postass","q=2");
	jQuery("#btnnew").click(function(){
	navigateToNextForm("MultiskillGrid_input.postass");
	});


	
});

function viewGrid(url,filterString)
{
	    var tableCaption = "MultiSkill";
		 processGridnew(url,filterString,"MultiDateGrid","pager",tableCaption,"doubleClickGrid","","","","");
	    return true;
		
	
} 

function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 
	  var rowData = jQuery('#MultiDateGrid').jqGrid('getRowData',rowid );
		var flid = rowData.flid;
		var date = rowData.Datess;
		//alert(date);
	  navigateToNextForm("MultiskillGrid_input.postass?flid="+flid+"&date="+date);
	}
</script>	

<form name="frmMultiGrid" id="frmMultiGrid" >
<div id="wrapperRpt">
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:0px;" >
<input type="button" class="easyui-button" id = "btnnew"  value = "New" />

</div>


		<table id="MultiDateGrid"  ></table>
		<div id="pager"></div>
		
		 <input type="hidden" id="mode"/>
		
</div>
</form>