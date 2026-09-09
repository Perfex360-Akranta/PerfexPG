
<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	setLoadFormCallBackFrmId("frmEmployeeNominationGrid");
	invokeAfterLoadFormCallBack();
	//viewGrid(url,"?q=2");
	//processGridnew(url, "q=2", "empnominationgrid", "empnominationpager","","doubleclick","","");	
});

function frmEmployeeNominationGrid_afterLoadCallBack(){	
	toggleCommonFilter();		
}

function btnFormatter(id, options, rowObject){
	var rowId = options.rowId;
	return "<input type='button' id='btnEmpNom_"+options.rowId+"' class='easyui-button' style='height: 20px; width : 52px;' value='..' onClick=loadNominate('"+rowObject[0].trim() + "','"+rowObject[3]+"','"+rowObject[5]+"','"+rowObject[1]+"'); />";
}

function doubleclick(id)
{	 
	var rowData = jQuery("#empnominationgrid").jqGrid('getRowData',id );
	var keyId = rowData.keyid;
	var progId=rowData.progid;
	var batchId=rowData.batchid;
	keyId=keyId.trim();
	loadNominate(keyId,progId,batchId);
}

function loadNominate(keyId,progId,batchId,flid){	
	//width,height,top,left
	LoadPopUp("divEmpNomiReq","EmployeeNomEntry_input.empnom?&nommmode=request&keyid="+keyId+"&progid="+progId+"&batchid="+batchId+"&flid="+flid,true,"82%","83%","5%","7%","","Employee Nomination",false,true);
}

function divEmpNomiReq_onClose(request){
	//jQuery("#empnominationgrid").trigger("reloadGrid");  
	return true;
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
		var tableCaption = "Employee Nomination List";
		processGridnew(url, filterString, "empnominationgrid", "empnominationpager",tableCaption,"doubleclick","","");
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

<form name="frmEmployeeNominationGrid" id="frmEmployeeNominationGrid">
<div id="wrapperRpt">   
	<table id='empnominationgrid'>
		<tr>
			<td></td>
		</tr>
	</table>
	<div id='empnominationpager'></div>
</div>


</form>
