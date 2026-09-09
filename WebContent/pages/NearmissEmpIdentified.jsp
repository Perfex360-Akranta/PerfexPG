<script>
jQuery(document).ready(function(){
	var actionPart = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmNewNearmissmonth");
	invokeAfterLoadFormCallBack();
	//viewGrid(actionPart,"?q=1");
	
	});

function frmNewNearmissmonth_afterLoadCallBack(){
	toggleCommonFilter();
	}
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		filterString += '&drillFlag=f&firstClick=Y';
		//alert(filterString);
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"nearmissmonthgrid","monthpager",tableCaption,"doubleClickGrid","","monthwiseGrid");
		return true;
	}
	
	return false;	
}

function validateFilterSelection(filterString){
	
	if(filterString == "?q=")
		return true;
		
	if(getFilterValue(filterString, "cmbCellid") == "" ){
        alert(" Select JH ");
        return false;
    }
    if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
    {
        alert("Select Monthwise Checkbox");
        return false;
    }
		/*if(getFilterValue(filterString, "flid") == "" ){
			alert(" Select Functional Location ");
			return false;
		}*/
		else{
			return true;
		}
}

function frmNewNearmissmonth_loadComplete()
{
	setTotalRowCss('frmNewNearmissmonth');
}
</script>
<form id="frmNewNearmissmonth">
<div id="wrapperRpt">
	<div>
		<table style="width: 1021px;"><tr>

			<td align="left">
			</td>
		</tr></table>
		
	</div>
	<table id="nearmissmonthgrid" ></table>
	<div id="monthpager"></div>
</div>
<input type="hidden" id="hdnFnlnKeyid" />

</form>
	
	