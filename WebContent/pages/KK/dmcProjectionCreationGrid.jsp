<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	/*var jhamAuditpillar=jQuery("#hdnJhamAuditpillar").val();
	var url = jQuery('#hiddenUrl').val();
	var jhamAudittype=jQuery("#hdnJhamAuditType").val();	
	viewGrid(url,"&s=1&jhamAuditpillar="+jhamAuditpillar+"&jhamAudittype="+jhamAudittype);*/
	jQuery('#btnNew').click(function(event){
		getProjectCreation("dmcprojectsprotoview_input.prpo?q=2&mode=insert");
	});
	viewGrid("","");	
	//setLoadFormCallBackFrmId("frmProjectCreationGrid");
});

function frmProjectCreationGrid_afterLoadCallBack(){	
	toggleCommonFilter();		
}

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{		 
		var type=jQuery("#hdnType").val();
		var url = jQuery('#hiddenUrl').val();		
		filterString+="&type="+type;
		//alert("url:"+url+",filterString:"+filterString);
		processGridnew(url,filterString,"projectgrid","pager","","doubleClickGrid","","");
		return true;
	}
	return false;
}

function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentMonth('dtetoMonth');
	disableField('frmFilter','dtetoMonth');	
}

function getProjectCreation(url){
	var type=jQuery("#hdnType").val();
	var checkList=jQuery("#hdnCheckList").val();
	
	navigateToNextForm(url+"&type="+type+"&filterButton=false&checkList="+checkList,"Project Creation");
}

function validateFilterSelection(filterString){
	return  true;
}


function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#projectgrid").jqGrid('getRowData',rowid );
	var keyId=rowData.KEYID;
	var dfiwkeyId=rowData.DFIWKEYID;
	getProjectCreation("dmcprojectsprotoview_input.prpo?keyid="+keyId+"&dfiwkeyId="+dfiwkeyId+"&mode=modifycreate");
}	

</script>
<form id="frmProjectCreationGrid">
 	<div id="wrapperRpt">
 	<div style="height:20px;margin-top: -28px">
		<input id="btnNew" class="easyui-button" type="button" value="New Project" style="width:80px;height:21px;margin-top:0px;" />
    	<span> <label class="notes" style="padding-left:10px;"> Double Click on row to input/view details </label></span>
	</div>
	<table id="projectgrid" style="width:100%">
		<tr><td/></tr>
	</table>
	<div id="pager"></div>
</div>
 <input type="hidden" id="hdnType" name="hdnType" value="${requestScope.type}"/>
 <input type="hidden" id="hdnFrmMode" name="hdnFrmMode" value="${requestScope.mode}"/>
 <input type="hidden" id="hdnCheckList" name="hdnCheckList" value="${requestScope.checkList}"/>
</form>

