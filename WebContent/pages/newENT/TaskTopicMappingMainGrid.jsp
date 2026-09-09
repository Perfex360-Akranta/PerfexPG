
<script>

jQuery(document).ready(function(){	
	
	
    
var url = jQuery('#hiddenUrl').val();
	
	var mode = "VIEW";
	var frmMode = jQuery('#mode').val();
    
	if(frmMode == "view"){
		jQuery('#btnNew').hide();
	}	
    var dataString ="";
	dataString += "&FORM_MODE="+mode + "&ViewClicked=Y";
	
	var filterStr = jQuery("#hdnFilterStr").val();
	
	if( filterStr != null && filterStr.length > 0 )
	{
		var dataString = jQuery("#hdnFilterStr").val();
		//var tableCaption = "Equipment Query";
		processGridnew("TaskTopicMapping_input.tmtopic","TaskTopicList","pager","","docDoubleClick","");
	}
	else{
		
		viewGrid("TaskTopicMapping_input.tmtopic",dataString);
	}
	if(frmMode != "view"){
	jQuery ("#btnNew").click(function()
	{    
		     var mode="create";
		     navigateToNextForm("TaskTopicDetails_input.tmtopic?&createmode="+mode,"Task Topic Mapping ");	
	});  
	}
	
});	
function docDoubleClick(id)
{		
	var rowData = jQuery("#TaskTopicList").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	var Flid =  rowData.FLN;
	var Uniquepos=rowData.UNIQUEPOSITION;
	var Topic=rowData.TOPIC;
	
	//alert("keyid "+keyid);
	navigateToNextForm('TaskTopicDetails_input.tmtopic?&grid=true&keyId='+keyid+"&Flid="+Flid+"&Up="+Uniquepos+"&Topic="+Topic,"Task Topic Mapping");  
}
function viewGrid(url,dataString)
{
	if(validateFilterSelection(dataString))
	{   
		var flid = getFilterValue(dataString, 'flid');
		//var momType = jQuery('#hdnMomType').val(); 
		if (flid.length<3)
			flid=jQuery('#hdnLoginFlid').val();
		//dataString += "&momRefDocId="+jQuery("#hdnMomRefDocId").val();
		//dataString += "&momRefDocType="+jQuery("#hdnMomRefDocType").val();
		dataString += '&flid='+flid;
		processGridnew("TaskTopicMapping_input.tmtopic",dataString,"TaskTopicList","pager","","docDoubleClick","");
	    return true;
	}
	
}
function validateFilterSelection(filterString){
	 return true;
}
</script>
<form id="frmTaskTopic">
<div id="WrapperRpt" >
<table>
 <tr>

	<td style="padding-left: 5px;">
    <!--<div><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">
    --><span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span>
    
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="TaskTopicList" ><tr><td></td></tr></table>
	<div id="pager"></div>
</div>	
<input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
</form>
	
	