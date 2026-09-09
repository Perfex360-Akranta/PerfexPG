<script  type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){	
		/*var auditType=jQuery("#hdnAuditType").val();
		var url = jQuery('#hiddenUrl').val();
		viewGrid(url,"&s=1&auditType="+auditType);*/
		setLoadFormCallBackFrmId("frmAuditGrid");
		invokeAfterLoadFormCallBack();
	});

	function frmAuditGrid_afterLoadCallBack(){	
		toggleCommonFilter();		
	}

	function viewGrid(url,filterString)
	{	
		if( validateFilterSelection(filterString))
		{		
			var auditType=jQuery("#hdnAuditType").val();
			var url = jQuery('#hiddenUrl').val();	
			filterString+="&auditType="+auditType;
			processGridnew(url,filterString,"jhAudit","pager","","doubleClickGrid","","");
			return true;
		}
		return false;
	}

	function frmFilter_enableDisableSuccessCallBack()
	{
		fillWithCurrentMonth('dtetoMonth');
		disableField('frmFilter','dtetoMonth');
	}
	
	function validateFilterSelection(filterString){
			return  true;
	}
	
	/*function doubleClickGrid(rowid) 
	{
		var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );
		var heading="" ;
		//var jhamAudittype=jQuery("#hdnAuditType").val();	
		//if (jhamAudittype=="JH"){heading="Jh Audit Report";}
		//else if (jhamAudittype=="DMT"){heading="Dmt Audit Report";}
		
		//String  paramid = rowData.keyid;	
		String  flid = rowData.flid;		
		//navigateToNextForm("dmtMultiLevel_input.jhAuditItc?s=2&paramid="+paramid+"&flid="+flid,"df");
		//navigateToNextForm("dmtMultiLevel_input.jhAuditItc?paramid="+paramid+"&flid="+flid,heading);
	}	*/
	function doubleClickGrid(rowid) 
	{
		var rowData = jQuery("#jhAudit").jqGrid('getRowData',rowid );	
		var audittype=jQuery("#hdnAuditType").val();
		var heading=audittype + " Audit Report" ;	
		var paramid=rowData.KEYID;
		console.log("Param id "+paramid);
		
		//alert("Param id "+paramid);
		//alert("rowid  "+rowid);
		
		var  flid = rowData.FLID;
		//alert("flid  "+flid);
		var  month = rowData.AUDITMONTH;
		//alert("month  "+month);
		var  level = rowData.LEVELV;
		//alert("level  "+level);
		
		//alert(month);
		navigateToNextForm("dmtMultiLevel_input.jhAuditItc?s=2&paramid="+paramid+"&flid="+flid+"&audittype="+audittype+"&month="+month+"&level="+level,heading);	
	}		
</script>

<form id="frmAuditGrid">
 <div id="wrapperRpt">
 <div style="margin-top: -20px">
 	<div style="width:100%;margin-left:4%;">
		<table id="jhAudit" style="width:100%">
		<tr><td/></tr></table>
		<div id="pager"></div>
		</div>
	</div>
</div>
<input type="hidden" id="hdnAuditType" name="hdnAuditType" value="${requestScope.auditType}"/>
<input type="hidden" id="hdnAuditpillar" name="hdnAuditpillar" value="${requestScope.auditpillar}"/>
</form>
