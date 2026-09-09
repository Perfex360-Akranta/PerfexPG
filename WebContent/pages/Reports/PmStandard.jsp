
<script>
jQuery(document).ready(function(){	
					
	var url = jQuery('#hiddenUrl').val();
	hideShowBack(true);
	//setLoadFormCallBackFrmId("frmPMStd");
	viewGrid(url,"?q=2");
	});

/*function frmPMStd_afterLoadCallBack(){
	toggleCommonFilter();		
}*/

function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "PM Standards";
		processGridnew(url,filterString,"list","pager",tableCaption,'PM_DoubleClick','',"PmStd_LoadComplete");
		
		return true;
	}	
	return false;
}

function PM_DoubleClick(id)
{
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var formName = rowData.MaintType;
	var keyid = rowData.KEYID;
	var filter = jQuery("#hdnFilterStr").val();
	var jsonstr = '{"filter":"'+ filter+'"}';
	var perstData = jQuery.parseJSON(jsonstr);	
	var filter ='&pmstdKeyid='+keyid;
	

		navigateToNextForm('prvnt_mntncform_modify.prv?q=2&mode=view&filterData='+filter+'&activity=B&filterButton=false',"Preventive Maintaince",perstData);
}
function PmStd_LoadComplete()
{
	var rowIds = jQuery('#list').jqGrid().getDataIDs();
	
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#list').getCell(rowIds[i],"groupByMachine");
		jQuery(' tr#listghead_'+i).find(' td:first-child').css('background-color','#BECBD6');		
	} 		
	
}


function validateFilterSelection(filterString){	
	
	/* if( getFilterValue(filterString, "cmbSectid") == "") 
		{
		alert("Select Section");
		return false;
		}
	else*/
		return  true;
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#chkMonthwise").attr('checked',false);
	jQuery('#cboPmjobtype').children('option[value="MBM"]').hide();
	jQuery('#cboPmjobtype').children('option[value="RBM"]').hide();
	jQuery('#cboPmjobtype').children('option[value="CAL"]').hide();
	jQuery('#cboPmjobtype').children('option[value="SDM"]').hide();
	
}
</script>
<form id="frmPMStd">
<div id="wrapperRpt">
<div style="margin-top: -28px">
<table id="list" style="width:100%">
	<tr><td/></tr></table>
	
	<div id="pager"></div></div>
</div>
</form>