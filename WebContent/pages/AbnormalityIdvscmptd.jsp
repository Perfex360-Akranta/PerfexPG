<script type="text/javascript">
jQuery(document).ready(function(){
setLoadFormCallBackFrmId("frmabncumulative");
invokeAfterLoadFormCallBack();
var actionPart = jQuery('#hiddenUrl').val();		
	
});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType;		
		filterString += '&drillFlag=f';
		//alert(filterString);
		//alert(url);
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"abnCumulativeGrid","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
		return true;
	}
	return false;	
}

function frmabncumulative_afterLoadCallBack(){
toggleCommonFilter();
}

function validateFilterSelection(filterString){
	
	if(filterString=="&q=1&firstClick=Y")
		return true;
	else{ 
	if(!filterMonthnDateDifference(filterString,40,24))
		return false;
	}
	if(getFilterValue(filterString, "cmbCellid") == "" && !checkFilterValueExist(filterString,"cmbCircle")){
        alert(" Select JH ");
        return false;
    }
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		alert("Select either Date or Month ");
		return false;
	}	
	return  true;
}


function abnCumulativeGrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("abnCumulativeGrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	

function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("abnCumulativeGrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function cumulativeGrid()
{ 
	setDrillDownHeader("CH0-0","abnCumulativeGrid","keyid");
	var row = jQuery("#abnCumulativeGrid").jqGrid('getDataIDs');
	var rowno=row.length;
	if(rowno < 100 ){
		setTotalRowCss('abnCumulativeGrid');
	}else{
		if(rowno % 100 != 0){
			setTotalRowCss('abnCumulativeGrid');
		}
	}
}



function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();
	enableDisableDatenMonthFilter();	
	if(url=="HSE_AbnCumulative_input.abnCumulative")
	{
		//jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
		setComboValueSilent("cmbAbnmTypeid", "ABT0007");
		
		readOnlyFields('cmbAbnmTypeid');				
		reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
		setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
		reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
	}
}
</script>
<form>
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
<div id="wrapperRpt" style="max-width: 1210px;">
<div id="divGraphContainer" ></div>	
<div class="clear"></div>
<table id="abnCumulativeGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnFnlnKeyid" />
</div>
</form>
