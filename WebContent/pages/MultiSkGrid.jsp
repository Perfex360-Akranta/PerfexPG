	
<script type="text/javascript">

jQuery(document).ready(function(){//alert(12);

	jQuery('#submitForm').val('frmMulti');
	//alert('inside');
	
	 var url = jQuery('#hiddenUrl').val();
	//alert(url);
    viewGrid("Multiskillassessment_input.postass","q=2");
	jQuery("#btnnew").click(function(){
	navigateToNextForm("MultiskillassessmentGrid_input.postass");
	});


	
});

function viewGrid(url,filterString)
{
	    var tableCaption = "MultiSkill";
		 processGridnew(url,filterString,"MultiGrid","pager",tableCaption,"doubleClickGrid","","","","");
	    return true;
		
	
} 

function doubleClickGrid(rowid) //GRID DOUBLE CLICK function
	{ 
	  var rowData = jQuery('#MultiGrid').jqGrid('getRowData',rowid );
		var flid = rowData.flid;
		//var date =rowData.Datess;
	  navigateToNextForm("MultiskillassessmentGrid_input.postass?flid="+flid);
	}
</script>	

<form name="frmMulti" id="frmMulti" >
<div id="wrapperRpt">
<div class="easyui-paddingbfpx"  style="padding-left:0%;padding-top:0px;" >
<input type="button" class="easyui-button" id = "btnnew"  value = "New" />

</div>


		<table id="MultiGrid"  ></table>
		<div id="pager"></div>
		
		 <input type="hidden" id="mode"/>
		
</div>
</form>