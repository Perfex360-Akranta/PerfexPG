
<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();	
	setLoadFormCallBackFrmId("frmEmployeeNominationAppGrid");
	invokeAfterLoadFormCallBack();
	viewGrid(url,"?q=2");
	//processGridnew(url, "q=2", "empnominationappgrid", "empnominationapppager","","doubleclick","","");	
});

function frmEmployeeNominationAppGrid_afterLoadCallBack(){	
	toggleCommonFilter();		
}
function divEmpNomiApp_onClose(request){
	jQuery("#empnominationappgrid").trigger("reloadGrid");  
	return true;
}
function doubleclick(id)
{	
	var rowData = jQuery("#empnominationappgrid").jqGrid('getRowData',id );
	var keyId = rowData.keyid;
	var progId=rowData.progid;
	var batchId=rowData.batchid;	
	keyId=keyId.trim();
	if(keyId.length<=0){		
    	setTimeout(function() {
			showCommonErrorMsg('No Employee Nominated For Approval');
		}, 200);
		div_err();		
        return false;
	}
	loadNominate(keyId,progId,batchId);
}

function loadNominate(keyId,progId,batchId){	
	LoadPopUp("divEmpNomiApp","EmployeeNomEntry_input.empnom?&keyid="+keyId+"&progid="+progId+"&batchid="+batchId+"&nommmode=approval",true,"82%","89%","1%","7%","","Employee Nomination",false,true);
}

function viewGrid(url,filterString)
{    //alert(" filterString :: "+filterString);   
   if( validateFilterSelection(filterString))
	{   
		var flid = getFilterValue(filterString, 'flid');
		var program = getFilterValue(filterString, 'cmbprogm');
		var batch = getFilterValue(filterString, 'cmbbatch');
		/*var FromDte = getFilterValue(filterString, "dtFromDate");
		//alert(" FromDte :: "+FromDte);
		var ToDte = getFilterValue(filterString, "dtToDate");
		//alert(" ToDte :: "+ToDte);
		daydiff(FromDte,ToDte);
		var Frommonth = getFilterValue(filterString, "dtFromMonth");
		var Tomonth = getFilterValue(filterString, "dtToMonth");
	    //alert(" Inside :::: flid :::: "+flid+" FromDte :: "+FromDte+" ToDte :: "+ToDte+" Frommonth :: "+Frommonth+" Tomonth :: "+Tomonth);
	    //+'&FromDte='+FromDte+'&ToDte='+ToDte+'&Frommonth='+Frommonth+'&Tomonth='+Tomonth*/
	    filterString += '&flid='+flid;	
	    filterString += '&program='+program;
	    filterString += '&batch='+batch;
		//alert(" MomReportJsp ::::: "+filterString);	
		var tableCaption = "Employee Nomination Approval List";
		processGridnew(url, filterString, "empnominationappgrid", "empnominationapppager",tableCaption,"doubleclick","","");
		return true; 
	}
	
	return false;
}

function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
	
	if(filterString=="?q=2")
		return true;

	if(getFilterValue(filterString, "flid") == "" ){
		alert(" Select Functional Location ");
		return false;
	}
	/*if(getFilterValue(filterString, "dtFromDate") == "" ){
		alert(" Select From Date ");
		return false;
	}
	if(getFilterValue(filterString, "dtToDate") == "" ){
		alert(" Select To Date ");
		return false;
	}*/
	    return true;
}
</script>

<form name="frmEmployeeNominationAppGrid" id="frmEmployeeNominationAppGrid">
	<div id="wrapperRpt">
		<table id='empnominationappgrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='empnominationapppager'>
		</div>
	</div>
</form>
