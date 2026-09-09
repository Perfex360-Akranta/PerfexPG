<script type="text/javascript">
jQuery(document).ready(function()
{
	
	var url = jQuery('#hiddenUrl').val();
	var frmTgtOrActual=getFrmTgtOrActual();
	if(frmTgtOrActual=="target")
		jQuery("#divTargetBtn").css('display','block');
	else if(frmTgtOrActual=="actual")
		jQuery("#divActualBtn").css('display','block');
	var dataString = "?q=1"; 
	viewGrid(url,dataString);	
}
);
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "";
		processGridnew(url,filterString,"grdIndicatorActual","grdIndicatorActualPager",tableCaption,"doubleClickGrid","","loadComplete");
		return true;
	}	
}
function validateFilterSelection(filterString)
{
	return  true;
}
function doubleClickGrid(rowid) 
{
	var rowData = jQuery("#grdIndicatorActual").jqGrid('getRowData',rowid);
	var url=getUrl();
	var pillCode= getPillCode();
	var dataStr ="&deptId="+rowData.getDeptKeyid;
	if(rowData.CompanyId!='-')
	{
		dataStr+="&compId="+rowData.CompanyId;
	}
	if(rowData.LocationId!='-')
	{
		dataStr+="&locnId="+rowData.LocationId;
	}
	if(rowData.FactoryId!='-')
	{
		dataStr+="&factId="+rowData.FactoryId;
	}
	if(rowData.SectionId!='-')
	{
		dataStr+="&sectionId="+rowData.SectionId;
	}
	if(rowData.CellId!='-')
	{
		dataStr+="&cellId="+rowData.CellId;
	}
	if(rowData.MachineId!='-')
	{
		dataStr+="&machId="+rowData.MachineId;
	}
	dataStr+="&year="+rowData.Year;
	dataStr+="&pillarId="+pillCode;
	dataStr+="&filterButton=false";
	var pillCode= getPillCode();
	var type=getIndicatorType();
	var frmTgtOrActual=getFrmTgtOrActual();
	var hdnfrmBtnOrMenu=getFrmButtonOrMenu();
	if(hdnfrmBtnOrMenu=="button")
		navigateToNextForm(url+"&q=1"+dataStr+"&loadContentDivId=loadPopUpdivShowActual&preLoadContentDivId=divpreShowActual&pillCode="+pillCode+"&type="+type.charAt(1)+"&frmButton="+frmTgtOrActual+"&frmBtnOrMenu="+hdnfrmBtnOrMenu);
	else if(hdnfrmBtnOrMenu=="menu")
		navigateToNextForm(url+"&q=1"+dataStr+"&pillCode="+pillCode+"&type="+type.charAt(1)+"&frmButton="+frmTgtOrActual+"&frmBtnOrMenu="+hdnfrmBtnOrMenu);
}
function getFullYear()
{
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var year = currentTime.getFullYear();
	return year;	
}
function getFrmButtonOrMenu()
{
	return getFieldValue("hdnfrmBtnOrMenu","frmActMain");
}
function getFrmTgtOrActual()
{
	return  getFieldValue("hdnfrmTgtOrActual","frmActMain");
}
function getIndicatorType()
{
	return  getFieldValue("hdntype","frmActMain");
}
function getUrl()
{
	var pillCode= getPillCode();
	var type=getIndicatorType();
	var frmTgtOrActual=getFrmTgtOrActual();
	if(type=='M')
	{
		if(frmTgtOrActual=="target")
		{
			if(pillCode=='KK')
			 	url="kmiActualKKTarget_view.kpiActKk?";
			else if(pillCode=='PM')
				url="kmiActualPMTarget_view.kpiActKk?";
		}
		else if(frmTgtOrActual=="actual")
		{
			if(pillCode=='KK')
				 url="kmiActualKkActual_view.kpiActKk?";
			else if(pillCode=='PM')
				url="kmiActualPMActual_view.kpiActKk?";
		}
	}
	else if(type=='P')
	{
		if(frmTgtOrActual=="target")
		{
			if(pillCode=='KK')
				url="kpiActualKkTarget_view.kpiActKk?";
			else if(pillCode=='PM')
				url="kpiActualPMTarget_view.kpiActKk?";
		}
		else if(frmTgtOrActual=="actual")
		{
			if(pillCode=='KK')
				url="kpiActualKkActual_view.kpiActKk?";
			else if(pillCode=='PM')
				url="kpiActualPMActual_view.kpiActKk?";
		}
	}
	return url;
}
jQuery("#btnActNew").click(
function()
{
	getFnClkEvents();
}
);
jQuery("#btnTgtNew").click(
function()
{
	getFnClkEvents();
}
);
function getFnClkEvents()
{
	var url=getUrl();
	var dataStr="";
	dataStr+="&year="+getFullYear();
	dataStr+="&pillCode="+ getPillCode();
	dataStr+="&filterButton=false";
	var frmTgtOrActual=getFrmTgtOrActual();
	var hdnfrmBtnOrMenu=getFrmButtonOrMenu();
	var type=getIndicatorType();
	if(hdnfrmBtnOrMenu=="button")
		navigateToNextForm(url+"&q=1"+dataStr+"&loadContentDivId=loadPopUpdivShowActual&preLoadContentDivId=divpreShowActual&type="+type.charAt(1)+"&frmButton="+frmTgtOrActual+"&frmBtnOrMenu="+hdnfrmBtnOrMenu);
	else if(hdnfrmBtnOrMenu=="menu")
		navigateToNextForm(url+"&q=1"+dataStr+"&type="+type.charAt(1)+"&frmButton="+frmTgtOrActual+"&frmBtnOrMenu="+hdnfrmBtnOrMenu);
}
function getPillCode()
{
	var pillarId=getFieldValue("hdnpillCode","frmActMain");
	return pillarId;
}

function loadComplete()
{
}
</script>
<!--<div style="float:left"><input type="button" class="easyui-button" id="btnActNew" name="btnActNew" value="new Actual Entry"></div>
-->
<form id="frmActMain" name="frmActMain">
<div id="wrapperRpt" style= "margin-top:10px;">
<div id="divActualBtn" style="display:none">	
	<input type="button" class="easyui-button" id="btnActNew" name="btnActNew" value="New Actual Entry"/>
</div>
<div id="divTargetBtn" style="display:none">	
	<input type="button" class="easyui-button" id="btnTgtNew" name="btnTgtNew" value="New Target Entry"/>
</div>
<table style="marigin-top:10%" id="grdIndicatorActual" ></table>
	<div id="grdIndicatorActualPager"></div>	

	<input type="hidden" id="hdntype" value="${requestScope.type}"/>
<input type="hidden" id="hdnpillCode" value="${requestScope.pillCode}"/>
<input type="hidden" id="hdnfrmTgtOrActual" value="${requestScope.frmTgtOrActual}"/>
<input type="hidden" id="hdnfrmBtnOrMenu" value="${requestScope.frmBtnOrMenu}"/>
</div>
</form>

