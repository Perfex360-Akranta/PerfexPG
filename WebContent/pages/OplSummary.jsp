	
<script type="text/javascript">
jQuery(document).ready(function(){	

	viewGrid("OplSummary_input.OplSummaryRpt","?q=");

});

function testCountFunction(val, name, record) {
	//alert('inside');
    return "$"+val;
}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{   
		var tableCaption = "OPL Report";
		processGridnew(url,filterString,"oplSum","pager",tableCaption,"doubleClickGrid","loadComFunction");
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	if(filterString == "?q=")
		return true;
	if(getFilterValue(filterString, "flid")==null || getFilterValue(filterString, "flid")==" " || getFilterValue(filterString, "flid")=="" ){
		alert("Select JH");
		return false;
	}
     var actionPart = jQuery('#hiddenUrl').val();
	 if(actionPart.indexOf('filter')<0){
         if(jQuery('#chkDatewise').is(':checked') == false && jQuery('#chkMonthwise').is(':checked') == false ){
             alert("Select Date or Month ");
             return false;
           }
      }
   return  true;
}



function doubleClickGrid(rowid)
{
	//alert("oplgroupby"+rowid);
	
	navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=view","OPL View");
		/*var actionPart = document.getElementById('hiddenUrl').value;
		var rowData = jQuery("#list").jqGrid('getRowData',rowid);
		alert(actionPart);
		var keyid = rowData.OPLNo;
		alert(keyid);
		if(keyid.substring(0,3) != 'MMA')
		{
			var dataString = '?rowid='+keyid;
			viewGrid(actionPart,dataString);
		}*/
}


function loadComFunction()
{ 
	
	jQuery('tr[id^="oplSumghead_"]').css("background-color","#56A8FE");
	jQuery('tr[id^="oplSumghead_"]').css("border-color","#000000");
	
}


function actionFormatterimageC(cellvalue, options, rowObject) {	
	var formatStr  = '<span ' ;
	if(cellvalue == "B" || cellvalue.trim() == 'I'||cellvalue.trim()=='T' )
		formatStr  += ' style=\"color:blue;font-size:20px;\"> &#10003;';// tick 
	else if(cellvalue=="X")	
		formatStr  += ' style=\"color:red;font-size:20px;\"> &#10005;'; //cross
	else
		formatStr =" ";
	
	formatStr  +=  '</span>';
	return formatStr.trim() == '</span>'?" ": formatStr;
}

function actionFormatterimageI(cellvalue, options, rowObject) {	
	//alert(cellvalue);
	var formatStr = "<img src =\"images/";
	if(cellvalue=="I")
		formatStr += "completed.png" + "\"  />" ;
	else if(cellvalue=="X")
		formatStr += "wrong.png" + "\"  />" ;
	else
		formatStr ="";	

	return formatStr;
}


function actionFormatterimageX(cellvalue, options, rowObject) {	
	//alert(cellvalue);
	var formatStr = "<img src =\"images/";
	if(cellvalue=="I")
		formatStr += "completed.png" + "\"  />" ;
	else if(cellvalue=="X")
		formatStr += "wrong.png" + "\"  />" ;
	else
		formatStr ="";	
	return formatStr;
}

</script>
<div id="wrapperRpt">
	<div style="margin-top: -28px">
	<span>
		<label class="notes" style="font-weight: bold;">Double Click on Grid to View Data</label>
	</span>
	<span>
		<label class="notes" style="font-weight: bold;">BK - Basic Knowledge  IC - Improvement Cases  TC - Trouble Cases </label>
	
	</span>
	
	
	</div>
	<table id="oplSum" style="width:100%">
		<tr><td/></tr></table>
	<div id="pager"></div>
</div>