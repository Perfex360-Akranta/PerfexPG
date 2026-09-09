<script type="text/javascript">
	jQuery(document).ready(function()
	{
		/*----------------------Load Functional Location----------------------------*/	
		var dataStr = getFnLocnValues();
		loadFunctionalLocation("SafetyAuditGrdfunLocation","functionalLocGrd.SafetyAudit","SafetyAuditGrdfunLocationValues","frmSafetyAuditGrd",dataStr);
		/*----------------------Filling Grid----------------------------*/	
		var url = jQuery('#hiddenUrl').val();
		var dataString = "?q=1"; 
		viewGrid(url,dataString);
	});
	function viewGrid(url,filterString)
	{
		if( validateFilterSelection(filterString))
		{
			var tableCaption = "";
			processGridnew(url,filterString,"grdSafetyAudit","grdSafetyAuditPager",tableCaption,"doubleClickGrid","","loadComplete");
			return true;
		}	
	}
	function loadComplete()
	{
		
	}
	jQuery("#btnSafetyAudit").click(function()
	{
		navigateToNextForm("SafetyAudit_input.SafetyAudit?q=2");
	});
	function validateFilterSelection(filterString)
	{
		return  true;
	}
	function doubleClickGrid(rowid) 
	{
		var rowData = jQuery("#grdSafetyAudit").jqGrid('getRowData',rowid );
		navigateToNextForm("SafetyAudit_input.SafetyAudit?&auditId="+rowData.AUDITID);
	}
	function frmSafetyAuditGrd_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		var dataStr="?q=1";
		var status="false";
		if(keyIds.machId!=null)
		{
			dataStr="&keyid="+keyIds.machId;
			status="true";
		}
		else if(keyIds.cellId!=null)
		{
			dataStr="&keyid="+keyIds.cellId;
			status="true";
		}
		else if(keyIds.sectId!=null)
		{
			dataStr="&keyid="+keyIds.sectId;
			status="true";
		}
		else if(keyIds.factId!=null)
		{
			dataStr="&keyid="+keyIds.factId;
			status="true";
		}
		else if(keyIds.locnId!=null)
		{
			dataStr="&keyid="+keyIds.locnId;
			status="true";
		}
		var url=jQuery('#hiddenUrl').val();
		if(status=="true") 
			viewGrid(url,dataStr);
	}
	function getFnLocnValues()
	{
		var companyId =getCompany("frmSafetyAuditGrd");
		var locnId=getLocation("frmSafetyAuditGrd");
		var factId = getFactory("frmSafetyAuditGrd");
		var sectionId = getSection("frmSafetyAuditGrd");
		var cellId = getCell("frmSafetyAuditGrd");
		var machineId=getMachine("frmSafetyAuditGrd");	
		var dataStr = "&compId="+companyId+"&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId;
		if(machineId.trim().length>0)
		{
			dataStr+="&machId="+machineId;
		} 
		return dataStr;   
	}
	function getCompany(frmId)
	{
		var companyId = jQuery("#"+frmId+ "input[id='company']").val();
		return companyId;
	}
	function getLocation(frmId)
	{
		var locnId=jQuery("#"+frmId+" input[id='location']").val();
		return locnId;
	}
	function getFactory(frmId)
	{
		var factId=jQuery("#"+frmId+" input[id='factory']").val();
		return factId;
	}
	function getSection(frmId)
	{
		var sectionId = jQuery("#"+frmId+" input[id='section']").val();
		return sectionId;
	}
	function getCell(frmId)
	{
		var cellId = jQuery("#"+frmId+" input[id='cell']").val();
		return cellId;
	}
	function getMachine(frmId)
	{
		var machineId=jQuery("#"+frmId+" input[id='machine']").val();
		return machineId;
	}
</script>
<form id="frmSafetyAuditGrd">
	<div  id="frmSafetyAuditGrdFuntKeyIds"  >
		<input type="hidden" id="location" name="cmbSaumGrdLocationid" value="${requestScope.sheTlAuditmst.saumLocationid}"/>
		<input type="hidden" id="factory" name="cmbSaumGrdFactoryid" value="${requestScope.sheTlAuditmst.saumFactoryid}" / >
		<input type="hidden" id="section" name="cmbSaumGrdSectionid" value="${requestScope.sheTlAuditmst.saumSectionid}" / >
		<input type="hidden" id="cell" name="cmbSaumGrdCellid" value="${requestScope.sheTlAuditmst.saumCellid}" / >
		<input type="hidden" id="machine" name="cmbSaumGrdMachineid" value="${requestScope.sheTlAuditmst.saumMachineid}" / >
		<span id="SafetyAuditGrdfunLocation" style="width:790px;margin-left:70px;"></span>
		<span style="margin-left:1100px;"><input type="button" class="easyui-button" id="btnSafetyAudit" value="New Safety Audit"></span>
	</div>
	<div id="wrapperRpt" style="margin-top:10px;margin-left:70px" >
		<table id="grdSafetyAudit" ></table>
		<div id="grdSafetyAuditPager"></div>
	</div>
</form>
	