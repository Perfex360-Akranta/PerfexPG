	
<script type="text/javascript">

jQuery(document).ready(function(){//alert(12);
     jQuery('#submitForm').val('frmcriticalReport');
	 var url = jQuery('#hiddenUrl').val();
	 viewGrid("criticalReport_input.cras","?q=2");

});
function viewGrid(url,filterString)
{
		var tableCaption = "CriticalReport";
		
		
		processGridnew(url,filterString,"criticalReportGrid","pager",tableCaption,"doubleClickGrid","","onloadcomplete");
	    return true;
} 

function onloadcomplete() {
	jQuery("#criticalReportGrid").setGridParam({
	onCellSelect:function(id,cellidx,cellvalue) {
		//alert(cellidx + id + cellvalue);	
			//alert(cellidx);
		if(cellidx > 8)
		{
			var colModel = jQuery("#criticalReportGrid").jqGrid("getGridParam","colModel" );			
			var colindex = colModel[cellidx].index;
			//alert(colindex);
			setFieldValue("hdncrytype",colindex,"frmcriticalReport");
		}
		else
		{
			setFieldValue("hdncrytype",'',"frmcriticalReport");
		}
		
	}
	});
}
	
function doubleClickGrid(id)
{	
	var rowData = jQuery("#criticalReportGrid").jqGrid('getRowData',id);
	var crytype = getFieldValue("hdncrytype");
	var TradeKeyid = rowData.TradeKeyid;
	var parentFlid = rowData.DMTJH;
	//alert(crytype);
	if(crytype.length > 0)
	{
	//navigateToNextForm('criticalityassessmentRpt_input.cras?&keyid='+keyid+'&parentFlid='+parentFlid);
		navigateToNextForm('criticalityassessmentMst_input.cras?&fromrpt=Y&filterButton=false&grid=true&parentFlid='+parentFlid+"&tradekeyid="+TradeKeyid+"&crytype="+crytype,"Criticality Assessment");
	}
	else
	{
		navigateToNextForm('criticalityassessmentMst_input.cras?&fromrpt=Y&filterButton=false&grid=true&parentFlid='+parentFlid+"&tradekeyid="+TradeKeyid,"Criticality Assessment");
	}
	
}


</script>	
<form name="frmcriticalReport" id="frmcriticalReport" >
<div id="wrapperRpt"  >
 <label  style="font:bolder ;color: blue; ">Double Click on row to view details</label>	
<table id="criticalReportGrid"  ></table>
		<div id="pager">
		</div>
</div>
<input type="hidden" class="easyui-button" id="hdncrytype"	name="hdncrytype"  />
</form>