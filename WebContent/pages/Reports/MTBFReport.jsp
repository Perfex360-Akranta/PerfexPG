

<script >
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	//viewGrid(actionPart,"q=1");
	setLoadFormCallBackFrmId("frmMTBF");
	invokeAfterLoadFormCallBack();
	
	jQuery('#btnRefresh').click(function(){
		
		var filterString = jQuery("#hiddenDataString").val();
		filterString += '&refresh=Y';
		
		 if(getFilterValue(filterString, "cmbSectid") == "" ){
			alert("Select Section");
			return false;
		 }
		 else
			processAjaxCalls("MTBF_getRefresh.MTTR",filterString,"getEmpImgSuccess","getEmpImgErr");
			
	});
	jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#mttrGrid").jqGrid('getGridParam','selrow');
		
			if((rowid == null) || (rowid ==""))
			{
				alert("Select Row For View Graph");
			}
			else
			{
				if(checkForZeroes("mttrGrid",rowid,2)){				
					var url = "chart.MTTR?type=MTBF&" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);
				}else 
					alert("NoRecord to View Graph");
			}
	});
	
	var factId =jQuery('#hiddenfact').val();
	var sectId =jQuery('#hiddensect').val();
	var cellId =jQuery('#hiddencell').val();
	var date =jQuery('#hiddendate').val(); 
	var shift =jQuery('#hiddenshift').val();				
	var dataStr ="q=2";
	if(factId != null && factId != '' && factId != ' ')
		dataStr+="&cmbFactid="+factId;
	if(sectId!= null && sectId != '' && sectId != ' ')
		dataStr+="&cmbSectid="+sectId;
	if(cellId!= null && cellId != '' && cellId != ' ')
		dataStr+="&cmbCellid="+cellId;
	if(date!= null && date != '' && date != ' ')
	{
		dataStr+= "&dtFromDate="+escape(date);
		dataStr+= "&dtToDate="+escape(date);
	}
	if(shift!= null && shift != '' && shift != ' ')
		dataStr+= "&shift="+shift;
		dataStr+= "&chkMonthwise=0";
	//if(factId.length>0)
		filterString = 	dataStr;
	
if(jQuery('#hdnPrevDataUrl').val().length > 0) {

viewGrid(actionPart,filterString);
}
});



function frmMTBF_afterLoadCallBack(){
	
	var actionPart = jQuery('#hiddenUrl').val();
	toggleCommonFilter();  
	
/* 	if(actionPart.indexOf('filter')<0)
		toggleCommonFilter();		
	else
		jQuery('#hdnSetFilterValues').val('Y');
 */
	/*
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
	toggleCommonFilter();		
	else
		jQuery('#hdnSetFilterValues').val('Y');
	*/
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		jQuery.cookie("filterString",filterString);
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		
		jQuery("#hiddenDataString").val(filterString);

		if(jQuery('#hdnPrevDataUrl').val().length > 0){
			//alert("inside");
			var factId =jQuery('#hiddenfact').val();
			var sectId =jQuery('#hiddensect').val();
			var cellId =jQuery('#hiddencell').val();
			var date =jQuery('#hiddendate').val(); 
			var shift =jQuery('#hiddenshift').val();
				 //alert(factId +" -- "+sectId+" -- "+cellId+" -- "+date);
			var dataStr ="";
			
			    dataStr+="&cmbFactid="+factId;
			//if(sectId.substring(0,3)=='LIN')
				dataStr+="&cmbSectid="+sectId;
			//if(cellId.substring(0,3)=='CEL')
				dataStr+="&cmbCellid="+cellId;
			//if(date.substring(0,3)=='MCH')
				//datStr+="&cmbMchid="+rowid;
				dataStr+= "&dtFromDate="+escape(date);
				dataStr+= "&dtToDate="+escape(date);
				dataStr+= "&shift="+shift;
				dataStr+= "&chkMonthwise=0";
				if(factId.length>0)
					filterString = 	dataStr;			
		}
		var tableCaption = "DrillDown (MTBF) Report";
		filterString += '&drillFlag=f&firstClick=Y';
		filterString += '&refresh=N';
		
		processGridnew(url,filterString,"mttrGrid","pager",tableCaption,"doubleClickGrid","","mttrGrid_loadComplete");
		//alert(filterString);
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	
	alert(filterString);
	
	if(filterString == "q=1")
		return false;
	else if(getFilterValue(filterString, "cmbSectid") == "" && getFilterValue(filterString, "cmbSectid") == "" ){
		alert("Select Section");
		return false;
		}
	/*else if(getFilterValue(filterString, "cmbSectid") == "" && getFilterValue(filterString, "cmbCircle") == "" ){
			alert("Select Section");
			return false;
			}*/
		else
			return true;
	
}
function mttrGrid_loadComplete(){


var rowIds = jQuery("#mttrGrid").getDataIDs();
var circle = jQuery("#hiddencircle").val();
var parentId = null;
if(rowIds.length==0){
	hideShowBack(true);
	
}
	
	if(rowIds.length>=0)
		{
			 parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'ELEMENTID0');		
			
			if(parentId.substr(0,3) == 'FCT'){	 // || circle.substr(0,3) == 'CRC'
					
				hideShowBack(false);				
			}	
			else{				
					hideShowBack(true);	
			}	
			jQuery("#hdnParentId").val(parentId);
		}	
	
}
function mttrGrid_onProcessGridBack(){
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#mttrGrid").getDataIDs();	
	var parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'ELEMENTID0');
	
	var dataString  ="";			

	if(rowIds.length==0){
		hideShowBack(true);
			
		parentId = jQuery("#hdnParentId").val();
	}
	
	
	if(rowIds.length>=0)
	{	
		if(parentId != null && parentId.length>0 &&  parentId.substr(0,3) != 'FCT'){		

			/*if(parentId.substr(0,3) == 'CEL'){
				var filtStr=jQuery.cookie("filterString");
				
				viewGrid(url,filtStr);
			}
			else*/{
			dataString = 'drillFlag=b';
			processGridnew(url,dataString,"mttrGrid","pager",'',"doubleClickGrid","","mttrGrid_loadComplete");
			}		
		}
		
	}
	
	
	
			
}			
					
function doubleClickGrid(id){ 
	var rowData = jQuery("#mttrGrid").jqGrid('getRowData',id);
	var circle = jQuery("#hiddencircle").val();
	var selId = rowData.ELEMENTID0;
	alert(selId);
	console.log("id =", id);
	console.log("selId =", selId);
	console.log("rowData =", rowData);
	console.log("AUG =", rowData["AUG-20252"]);
	console.log("SEP =", rowData["SEP-20253"]);
	console.log("14 =", rowData["14"]);
	alert("id = " + id);
	alert("selId = " + selId);
	console.log("rowData =", rowData);
	//var selId =rowData.KEYFIELD0;
	alert(selId.substr(0,3) +"       "+selId);
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	if(checkForZeroes("mttrGrid",id,2)){		
	if(selId.substr(0,3) != 'SPR' )//&& circle.substr(0,3) != "CRC")
		{
		var filterData ="";//"?";		
		filterData += '&parentId='+ selId+'&drillFlag=f';
		filterData += "&chkRemoveBlank="+removeBlank;
		
		var url = jQuery('#hiddenUrl').val();
		
		processGridnew(url,filterData,"mttrGrid","pager",'',"doubleClickGrid","","mttrGrid_loadComplete");	
		
	}
	if(selId.substr(0,3) == 'SPR'){
		
	}
	}else
		alert("No Record to view");
}

function frmFilter_enableDisableSuccessCallBack()
{        

	if(jQuery('#hdnSetFilterValues').val() == 'Y')
		setFilterValues();		
	
	jQuery('#dtefromDate').datebox('clear');
	jQuery('#dtetoDate').datebox('clear');	
	readOnlyFields('dtefromDate');
	readOnlyFields('dtetoDate');
	enableFields("chkMonthwise");
	jQuery("#chkMonthwise").attr('checked',true);	
	readOnlyFields('cboRelatedTo');
	jQuery("#cboRelatedTo").val("MCH");
}

jQuery('#btnSelectTab').click(function(){
	var rowid= jQuery('#mttrGrid').jqGrid('getGridParam','selrow');
	if(rowid=='' || rowid== null || rowid=='undefined'){
		alert('Select Row To View the Records ');
		}
	else
		doubleClickGrid(rowid);
		
});
</script>
<form id="frmMTBF">
<div id="divGraphContainer" ></div>	

<div style="padding-top: 25px; padding-left: 40px;padding-bottom:10px;">
<input id="btnGraph" class="easyui-button"  type="button" value="Graph" style="height:21px;width:54px;"/>
<input id="btnRefresh" class="easyui-button" name="btnRefresh" type="button" value="Refresh" style="height:21px;width:60px;"/>
<input id="btnSelectTab" class="easyui-button"  type="button" value="For Tab" style="height:21px;width:54px;"/>

<label id="mttrnotes"  class="notes"   style="font-weight: bold; padding-left:20px; "> ${requestScope.drilldownMsg}</label>
<label id="mttrnotesgraph"  class="notes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.GraphMsg}</label>

</div> 
<div id="wrapperRpt" style="margin-top:0px;">
<table id="mttrGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenDataString" value="" />
<input type="hidden" id="hiddencircle" value=""  />
<input type="hidden" id="hdnParentId" value=""  />
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</div>	

</form>
