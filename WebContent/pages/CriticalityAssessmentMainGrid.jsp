
<script>

jQuery(document).ready(function(){    
	viewGrid("","&q=2"); 
	jQuery ("#btnNew").click(function()
	{    
		     var mode="create";
		     var ds = "&createmode="+mode+"&Remarks=&filterButton=false";
		     navigateToNextForm("criticalityassessmentMst_input.cras?"+ds,"Criticality Assessment");	
	});  	
});	

function viewGrid(url,filterString)
{
	processGridnew("criticalityassessment_input.cras",filterString,"CriAssessmentGrid","pager","","docDoubleClick","");
	return true;
}
function docDoubleClick(id)
{		
	var rowData = jQuery("#CriAssessmentGrid").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	var Flid =  rowData.FLID;
	var DATES=  rowData.DATES;
	var DoneBy= rowData.EMPID;
	var Remarks= rowData.REMARKS;
	// alert("Flid "+Flid);
	 //alert('&keyId='+keyid+"&Flid="+Flid+"&Dates="+DATES+"&DoneBy="+DoneBy);
	 navigateToNextForm('criticalityassessmentMst_input.cras?&filterButton=false&grid=true&keyId='+keyid+"&Flid="+Flid+"&Dates="+DATES+"&DoneBy="+DoneBy+"&Remarks="+Remarks,"Criticality Assessment");  
}
</script>
<form id="frmCritiAssessment">
<div id="WrapperRpt" >
<table>
 <tr>

	<td style="padding-left: 5px;">
    <div style="margin-top: -28px"><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
    <span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>
    
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="CriAssessmentGrid" ><tr><td></td></tr></table>
	<div id="pager"></div>
</div>	
</form>
	
	