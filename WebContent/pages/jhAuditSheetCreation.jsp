<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	
var url = jQuery('#hiddenUrl').val();


var dataString ="";

	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"&s=1");
jQuery('#btnnewAudit').click(function(event){	
	navigateToNextForm("jhAuditCreation_input.jhAudit","Jh Audit Creation");
});
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{	
		
		processGridnew(url,filterString,"jhAudit","pager","","doubleClickGrid","","");
		return true;
	}
	return false;
}
function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentMonth('dtetoMonth');
	disableField('frmFilter','dtetoMonth');
	
	/*disableField('frmAbnormalityRelated', 'chkdectbychkbox');
	disableField('frmAbnormalityRelated', 'chkdectdtchkbox');
	disableField('frmAbnormalityRelated', 'chkcauschkbox');
	disableField('frmAbnormalityRelated', 'chkabncatchkbox');
	disableField('frmAbnormalityRelated', 'chkabnimpchkbox');
	disableField('frmAbnormalityRelated', 'chkallchkbox');
	
	jQuery("#chkDatewise").attr('checked',false);
	jQuery("#chkMonthwise").attr('checked',true);
	clearField('dtefromDate');
	clearField('dtetoDate');
	disableField('frmFilter', 'dtefromDate');
	disableField('frmFilter', 'dtetoDate');
	enableFields('chkMonthwise');
	enableFields('dtefromMonth');
	enableFields('dtetoMonth');
	disableField('frmAbnormalityRelated', 'cboabnstatus');*/
			
	
}


function validateFilterSelection(filterString){
		return  true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );
	
	navigateToNextForm("jhAuditCreation_input.jhAudit?keyId="+rowData.MasterId+"&mode=view","Jh Audit Creation");
}	
	
</script>
<form id="frmAuditGrid">
 
 
 <div id="wrapperRpt">
 <div style="height:20px;">
	<input id="btnnewAudit" class="easyui-button" type="button" value="New Audit" style="width:80px;height:21px;margin-top:0px;margin-left:1000px;" />
	
	</div>
	<table id="jhAudit" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
</form>
