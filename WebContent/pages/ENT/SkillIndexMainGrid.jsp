
<script>
var drillLevel = "0";
var frmDrillNo = "0";
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	//alert(url);
	var reportName = jQuery('#hdnReportName').val();

	setDrillLevelNo();
	
	var flid=jQuery('#hdnLoginFlid').val();
	//alert(flid);
	jQuery('#olapfilter').show();
	if(reportName=="ASSEMENT") {
		jQuery( '#spnTrigger').show();
		//var flid = jQuery("#frmEmpPage input[id='flid']").val();
		viewGrid(url, "q=2"+"&drillFlag=N&flid="+flid);
	}
	else if(reportName=="REPORT") {
		jQuery( '#spnTrigger').hide();
		jQuery( '#divNewBtn').hide();
		//var flid = jQuery("#frmEmpPage input[id='flid']").val();
		viewGrid(url, "q=2"+"&drillFlag=N&flid="+flid);

	} else {
		jQuery( '#spnTrigger').hide();
		viewGrid(url, "q=2");
		jQuery('#olapfilter').hide();
	}
	
	jQuery ("#btnNew").click(function()
	{
		var dataStr ="?q=2&";
		var rowData = jQuery("#skillIndexGrid").jqGrid('getRowData',1);
		var flid= rowData.FLID;
		
		if(reportName=="REVIEW") {
			dataStr ="?q=2&keyId=&flid="+flid+"&uniqPosid=";
			dataStr+= "&filterButton=false";
		     navigateToNextForm("RePointDetail_input.sirp"+dataStr,"Skill Index Review point ",null,{"filterString":dataStr});
		}
		else {
			dataStr+= "&filterButton=false";
			navigateToNextForm("SkillIndexAssement_input.sirp"+dataStr,"Skill Index Review point ",null,{"filterString":dataStr});
		}
	});
  
	
	jQuery ("#btnView").click(function()		{
		if(reportName != "REVIEW") {
			var url = jQuery('#hiddenUrl').val();
			var auto="";
			//alert(url);
			if(jQuery('#chkTrigger').is(':checked')==true )
				auto="AUTO";
			viewGrid(url, "q=2&trigger="+auto+"&drillFlag=F");
		}
	});
		  
});


function setDrillLevelNo() {
	var elementType = jQuery("#frmEmpPage input[id='elementType']").val();
	if (elementType=="COMP") frmDrillNo=1; 
	else if (elementType=="LOCN") frmDrillNo=2;
	else if (elementType=="SBU") frmDrillNo=3;
	else if (elementType=="PBU") frmDrillNo=4;
	else if (elementType=="SECT") frmDrillNo=5;
	else if (elementType=="CELL") frmDrillNo=6;
	else if (elementType=="MCHM") frmDrillNo=7;
	//alert(frmDrillNo);
	drillLevel = frmDrillNo;
	
}

function viewGrid(url, filterStr) {
	var reportName = jQuery('#hdnReportName').val();
	if (reportName=="REPORT") {
		//reportName="ASSEMENT";
		url ="SkillAssementRpMain_input.sirp";
	}
	
	filterStr=filterStr+"&reportName="+reportName;
	//alert(filterStr);
	processGridnew(url,filterStr,"skillIndexGrid","pager","","docDoubleClick","","loadComplete");
	return true;
}

function loadComplete(result) {
	jQuery('#exptxl').hide();
	console.log(result+"result");
	if (drillLevel>frmDrillNo)
		hideShowBack(true);
	else
		hideShowBack(false);
	var reportName = jQuery('#hdnReportName').val();
	if (reportName=="REPORT") {
		var rowIds = jQuery("#skillIndexGrid").getDataIDs();
		//hideJqGridRow('skillIndexGrid',rowIds.length);
	}
}

function skillIndexGrid_onProcessGridBack(){
	
	var url = jQuery('#hiddenUrl').val();		
	var rowIds = jQuery("#skillIndexGrid").getDataIDs();	
	//var parentId =  jQuery("#skillIndexGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var flid =  jQuery("#skillIndexGrid").jqGrid('getCell', rowIds[0], 'FLID');
	var filterStr = "q=2&flid="+flid+"&drillFlag=B";
	
	drillLevel = parseInt(drillLevel) - 1;
	jQuery('#hdnDrillLevel').val(drillLevel);

	//if(drillLevel > 0) 
	viewGrid(url, filterStr);	
			
			
}	
function btnShowFormmter(id, options, rowObject){
	var rowId = options.rowId;
	return '<input type= "button" class="easyui-button"  id="btnShow_'+rowId+'" name="btnShow_'+rowId+'" onclick="openReviewPoint('+rowId+')" value=".." style="width:80px;" />';
}


function docDoubleClick(id) {
	
	return false;
	var rowData = jQuery("#skillIndexGrid").jqGrid('getRowData',id);
	var flid = rowData.FLID;
	var url = jQuery('#hiddenUrl').val();
	var filterStr = "q=2&flid="+flid+"&drillFlag=F";

	var reportName = jQuery('#hdnReportName').val();
	if(reportName=="REPORT" || reportName=="ASSEMENT") 
	{
		if (drillLevel<6) {
			drillLevel = parseInt(drillLevel) + 1;
			jQuery('#hdnDrillLevel').val(drillLevel);

			viewGrid(url, filterStr);
			hideShowBack(true);
		}
	}
}

function openReviewPoint(id)
{	
	var rowData = jQuery("#skillIndexGrid").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	//var Flid =  rowData.FLN;
	var reportName = jQuery('#hdnReportName').val();
	//alert(reportName);
	if(reportName=="REVIEW") {
		var flid = rowData.FLID;
		var uniqPosid = rowData.UNIQPOSID;
		
		if(uniqPosid=="undefined" || uniqPosid==null || uniqPosid==" ")
			uniqPosid="";
		
		var dataStr ="?q=2&keyId="+keyid+"&flid="+flid+"&uniqPosid="+uniqPosid;
		dataStr+= "&filterButton=false";
		//navigateToNextForm("Abnormality_input.abnForm?q=1"+filterString,"Abnormality",null,{"filterString":url});
		navigateToNextForm('RePointDetail_input.sirp'+dataStr,"Skill Index Review point",null,{"filterString":dataStr});
	}
	else if(reportName=="ASSEMENT") {
		var flid = rowData.FLID;
		var uniqPosid = rowData.UNIQUEPOSID;
		//var creteriaId = rowData.CRITERIAID;
		var reviewDate = "";
		reviewDate = rowData.REVIEWDATE;
		if(jQuery('#chkTrigger').is(':checked')==true )
			reviewDate = "";
		//+'&creteriaId='+creteriaId
		var dataStr = '?&grid=true&flId='+flid+'&uniqPosid='+uniqPosid+'&reviewDate='+reviewDate;
		//alert(dataStr);
		dataStr+= "&filterButton=false";
		navigateToNextForm('SkillIndexAssement_input.sirp'+dataStr,"Skill Index Assement");
	}
	else if(reportName=="REPORT") {
			var flid = rowData.FLID;
			var uniqPosid = rowData.UNIQUEPOSID;
			var reviewDate = rowData.REVIEWDATE;
			var reviewType = rowData.UNIQUEPOSITION;
			var dataStr ="?q=2&keyId=&flid="+flid+"&uniqPosid="+uniqPosid+"&reviewDate="+reviewDate+"&reviewType="+reviewType;
			if(uniqPosid=="" || uniqPosid==" " || uniqPosid==null || flid=="" || flid==" " || flid==null) {
				return false;
			}
			//alert(dataStr );
			dataStr+= "&filterButton=false";
			navigateToNextForm("SkillIndexRpt_input.skillIndex"+dataStr,"Skill Index Radar Chart ",null,{"filterString":dataStr});
	}
}

</script>
<form id="frmSkillindex">
  <div id="WrapperRpt" style="width:100%">
<table style="width: 120%;">
 <tr>

	<td width="50%">
    <%--<div id="divNewBtn" ><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry">--%>
<!--     <span style="padding-left:10px;"><label class="notes"> Double Click on row to input/view details </label></span> -->
    <span style="padding-left:10px;"><label class="notes"> Double Click the Data row to view the Details </label></span>
   </div></td>
   
   	<td style="padding-left: 10%;" width="50%">
   	<div id="spnTrigger" style="width: 300px;">
   	<input type="checkbox" id="chkTrigger" name="chkTrigger" value="Y" > 
					<label>Trigger</label>
   	<span  ><input class="easyui-button" type="button" id="btnView" name="btnView" value="View"> </span>
   	</div>
   	</td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="skillIndexGrid" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="paramDiv" style="display:none;" title="param">
	</div>
</div>

<input type="hidden" id="hdnReportName" name="hdnReportName" value="${requestScope.reportName}"> 
<input type="hidden" id="hdnDrillLevel" name="hdnDrillLevel" value="0">
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
</form>
	
	