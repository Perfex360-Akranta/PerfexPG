<!--Author Manikandan-->
<script type="text/javascript">

jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();
	var selId = jQuery("#hdnSelId").val();
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	//alert(prevDataUrl);
	var filter = jQuery("#hiddenStr").val();
	var hdnselId = jQuery("#hdnkeyId").val();
	
	if(filter != null && filter.length>0)
	{
		var filterString = jQuery("#hiddenStr").val();
		var tableCaption = "Abnormality Summary";
		filterString += '&drillFlag=f&';
		filterString += getParamName(hdnselId)+'='+hdnselId;
		
		processGridnew(actionPart,filterString,"GenMainGrid","pager",tableCaption,"GenMainGrid_doubleClickGrid","","GenMainGrid_loadComplete");	
	}	
	else if( prevDataUrl == null || prevDataUrl.length <=0){	
		if(actionPart == 'genMainMould_input.genmainRpt')		
			viewGrid("genMainMould_input.genmainRpt","q=1&firstClick=Y");
		else if(actionPart == 'genMainSetupAndAdjust_input.genmainRpt')
			viewGrid("genMainSetupAndAdjust_input.genmainRpt","q=1&firstClick=Y");
		else 
			viewGrid("genMain_input.genmainRpt","q=1&firstClick=Y");
	}
	else{
		viewGrid(unescape(prevDataUrl),"q=1");
	}	
	
});
function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();
	setTimeout(function() {disableField("frmBD","cbostatus");},1250);
	if(url == "genMainMould_input.genmainRpt"){
		disableField("frmFilter","cboRelatedTo");
		setFieldValue("cboRelatedTo", "MLD","frmFilter");
		setTimeout(function() {enableFields("cmbMould");},1250);
	}
	else{
		enableFields("cboRelatedTo");
		setFieldValue("cboRelatedTo", "MCH","frmFilter");
		}	
	fillWithCurrentDate("dtefromDate");			
	jQuery("#chkDatewise").attr('checked',false);
	jQuery("#chkMonthwise").attr('checked',true);
	jQuery("#chkMonthwise").attr('disabled',false);		
	jQuery("#chkDatewise").attr('disabled',false);	
	     					
}

jQuery("#gmbtn").click(function(rowid){
	var rowid = jQuery("#GenMainGrid").jqGrid('getGridParam','selrow');
	var rowData = jQuery("#GenMainGrid").jqGrid('getRowData',rowid);
	var keyData = jQuery("#GenMainGrid").jqGrid('getCell', rowid, 'keyid');
	//var selId = rowData.keyidh;
	var selId = rowData.keyid;
	
			if( rowid == null)
				alert("Select on row ");
			else{
				var selid = jQuery("#hiddenrowid").val();				
				var url = jQuery("#GenMainGrid").jqGrid('getGridParam', 'url');
				url = url.replace("genMain_getData.genmainRpt","genMain_input.genmainRpt");
				url = escape(url); 
				var filterStr =jQuery("#GenMainGrid").jqGrid('getGridParam', 'url');
				var jsonstr = '{"filterString":"'+ url + '" ,"selid":"'+selid+'","filterStr":"'+filterStr+'"}';
				var perstData = jQuery.parseJSON(jsonstr);	
				
				var filter = jQuery("#hiddenStr").val();			
				var fromDate = getFilterValue(filter, "dtFromDate");
				var toDate = getFilterValue(filter, "dtToDate");
				var mchId = getFilterValue(filter, "cmbMchid");
				
				var jsonstr = '{"filter":"'+ filter +'","keyId":"'+selId+'","filterStr":"'+filterStr+'"}';
			
				if(selId.substr(0,3)=="ASM" && mchId=='')
					{
					mchId = jQuery("#hdnParentId").val();
					jsonstr = '{"filter":"'+ filter +'" ,"mchId":"'+mchId+'","keyId":"'+selId+'"}';				
					}				
				jQuery("#hdnFromDate").val(fromDate);
				jQuery("#hdnToDate").val(toDate);	
				var ifMould = jQuery('#hdnrelatedTo').val();
				var redirectUrl = null;
				if(jQuery('#hdnsetupadj').val().trim() != '' && jQuery('#hdnsetupadj').val().length>0)
					redirectUrl = 'generalSetAndAdj_view.genmain?';
				else{	
					redirectUrl = 'generalMaint_view.genmain?';
							
					if(ifMould == 'MOULD')
						redirectUrl = 'generalMaintMould_view.genmain?';	
				}	
				var perstData = jQuery.parseJSON(jsonstr);
				
					if(selid != "codeFiel"){			
						//alert(selId);
						navigateToNextForm(redirectUrl + getParamName(selId)+'='+selId+'&selid='+selid+"&keyId="+selId+"&parentId="+selId ,"GeneralMaintanince  View",null,perstData);
					}
					else{	
						navigateToNextForm(redirectUrl + getParamName(selId)+'='+selId+"&selId="+selId ,"GeneralMaintanince  View",null,perstData);
					}
				
			}
});

function viewGrid(url,filterString)
{
	if(filterString == "q=1&firstClick=Y")
	{
		jQuery("#hiddenStr").val(filterString);
		var tableCaption = "General Maintainence Report";
		filterString += '&drillFlag=f';
		processGridnew(url,filterString,"GenMainGrid","pager",tableCaption,"GenMainGrid_doubleClickGrid","","GenMainGrid_loadComplete");
		return true;
	}
	else if( validateFilterSelection(filterString))
	{
		jQuery("#hiddenStr").val(filterString);
		var tableCaption = "General Maintainence Report";
		filterString += '&drillFlag=f';
		
		processGridnew(url,filterString,"GenMainGrid","pager",tableCaption,"GenMainGrid_doubleClickGrid","","GenMainGrid_loadComplete");
		return true;
	}
	return false;	
	}

function GenMainGrid_doubleClickGrid(id)
{
	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("GenMainGrid","keyid",id,keyfieldData);
	if(keyfieldData != "false")
		jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function GenMainGrid_loadComplete()
{
	setDrillDownHeader("CH0-0","GenMainGrid","keyid");
	setTotalRowCss('GenMainGrid');
}
function GenMainGrid_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("GenMainGrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	
function gmView(rowid,iCol,cellcontent){
	 alert('x');
	var colm = jQuery("#GenMainGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;
	
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);
	//alert(selId);
	 jQuery("#hiddenrowid").val(selId);
	// }); 
}


jQuery('#btnGraph').click(function(){	
		var rowid = jQuery("#GenMainGrid").jqGrid('getGridParam','selrow');		
		var url = "chart.genmainRpt?" +  (rowid != null && rowid != undefined ?  "flid="+rowid:"chType=p")+"&isGraphYes=Y";	
		showGraphData(url);
	});

function validateFilterSelection(filterString){

	if(filterString == "q=1&firstClick=Y")
		return true;
	
	
	if(jQuery('#chkMonthwise').is(':checked') && jQuery('#chkDatewise').is(':checked')){
		alert("select either Month or date");
		return false;
	}
	else if(!jQuery('#chkMonthwise').is(':checked') && !jQuery('#chkDatewise').is(':checked')){
		alert("select either Month or date");
		return false;
	}
	 if(jQuery('#chkDatewise').is(':checked')) {
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Select  FromDate");
			return false;
		}
		else if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Select  ToDate");
			return false;
		}
		else
			return true;
	 }
	else if(jQuery('#chkMonthwise').is(':checked')){
		 if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
		{
			alert("Select  FromMonth");
			return false;
		}
		else if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
		{
			alert("Select  ToMonth");
			return false;
		}
		else
			return  true;
		
	}
		
	
	 
	
}
jQuery('#btnSelectTab').click(function(){
var rowId =jQuery('#GenMainGrid').jqGrid('getGridParam','selrow');
alert(rowId);
if(rowId == null || rowId == '' || rowId =='undefined'){
	alert('Select Row To Drildown');
	}
else
	GenMainGrid_doubleClickGrid(rowId);
});


</script>
<!--<div class="floatright"><input type="button" id="btnBack" class="easyui-button" value="Back"/></div>-->
<form name="frmGenMainReport" id="frmGenMainReport" >

<div id="wrapperRpt">
<div class="floatright " style="margin-top: -28px">
<input type="button" id="btnSelectTab" onclick="" class="easyui-button" value="For Tab"/>
<input type="button" id="gmbtn" onclick="" class="easyui-button" value="View GM"/>
<input type="button" id="btnGraph" onclick="" class="easyui-button" value="Graph"/>
</div>

<div class="notes"  style="margin-top: 0px">Double click on the Company/factory/Section/Cell to DrillDown</div>

<div id="divGraphContainer" ></div>	
<div class="clear"></div>
<table id="GenMainGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenUrl" value="${requestScope.hiddenUrl}" />
<input type="hidden" id="hiddenrowid"  />
<input type="hidden" id="hiddenStr" value="${requestScope.filter}" />
<input type="hidden" id="hdnPrevDataUrl" value="${requestScope.filterStr }" />
<input type="hidden" id="hdnParentId" value="${requestScope.mchId}"/>
<input type="hidden" id="hdnSelId" value="${requestScope.selid}" />
<input type="hidden" id="hdnrelatedTo" value="${requestScope.relatedTo}" />
<input type="hidden" id="hdnkeyId" value="${requestScope.keyId }"/>
<input type="hidden" id="hdnFilterString" name="hdnFilterString" value="" />
<input type="hidden" id="hdnsetupadj" name ="hdnsetupadj" class="easyui-text" value="${requestScope.setupandadjustment } "/>
<input type="hidden" id="hdnFnlnKeyid" />
</div>
</form>
	