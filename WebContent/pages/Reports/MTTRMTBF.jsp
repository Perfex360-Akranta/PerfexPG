

<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();		
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	//alert("prevDataUrl  "+getFieldValue('hiddenfact'));
	if( prevDataUrl == null || prevDataUrl.length <=0)		
		actionPart=actionPart;//viewGrid(actionPart,"?q=1");
	else{
		viewGrid(unescape(prevDataUrl),"");
	}	
	//viewGrid(actionPart,"q=1");
	setLoadFormCallBackFrmId("frmMTTR");
	invokeAfterLoadFormCallBack();
	jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#mttrGrid").jqGrid('getGridParam','selrow');
		
			if((rowid == null) || (rowid =="")){
			alert("Select Row For View Graph");
		}
			else{
				if(checkForZeroes("mttrGrid",rowid,2)){				
				var url = "chart.MTTR?type=MTTR&" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p"+"type=MTTR");
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
	if(factId.length>0)
		filterString = 	dataStr;
if(jQuery('#hdnPrevDataUrl').val().length <= 0) {

viewGrid(actionPart,filterString);
}
/*else{

viewGrid(prevData,"");
}*/



});

function frmMTTR_afterLoadCallBack(){
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
	toggleCommonFilter();		
	else
		jQuery('#hdnSetFilterValues').val('Y');
}

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");

		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
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
		var tableCaption = "DrillDown (MTTR) Report";
		filterString += '&drillFlag=f&firstClick=Y';

		jQuery("#hdnFilterString").val(filterString);
		
		processGridnew(url,filterString,"mttrGrid","pager",tableCaption,"doubleClickGrid","","mttrGrid_loadComplete");
		//alert(filterString);
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	if(filterString == "q=1")
		return false;	
	else if(getFilterValue(filterString, "cmbSectid") == "" && getFilterValue(filterString, "cmbCircle") == ""){
			alert("Select Section");
			return false;
			}
		else
			return true;
	
}
function mttrGrid_loadComplete()
{
	var circle = jQuery("#hiddencircle").val();
    var rowIds = jQuery("#mttrGrid").getDataIDs();
    var parentId = null;
    if(rowIds.length==0){
	hideShowBack(true);	
     }
	if(rowIds.length>=0)
		{
			 parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'KEYFIELD0');		
			if(parentId.substr(0,3) != 'LIN'){				
				hideShowBack(true);				
			}		
			if(circle.substr(0,3) == 'CRC')
				 hideShowBack(false);	
			jQuery("#hdnParentId").val(parentId);
		}	

	 jQuery("#mttrGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
		 {
		     jQuery("#hiddeniCol").val(cellcontent);
		     //alert("rowid "+rowid+ '   '  +iCol+  '  '  +cellcontent);
		  
			 /* var grid = jQuery('#mttrGrid');
			  var sel_id = grid.jqGrid('getGridParam', 'selrow');
			  var CellData = grid.jqGrid('getCell', sel_id,iCol );
				//if(CellData =='0')
					//alert("No Data to view");*/
		 	  MttrMtbfView(rowid,iCol,cellcontent);
	 
		}	
	});
}
function MttrMtbfView(rowid,iCol,cellcontent)
{   
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	
	var filterString = jQuery("#hdnFilterString").val();	
	var sectId = getFilterValue(filterString, "cmbSectid");
	var colm = jQuery("#mttrGrid").jqGrid ('getGridParam', 'colModel');
	var month1 = colm[iCol].name; 
	var month=month1.substring(0,month1.length-1);
	
	//alert(month.substring(0,month.length-1));
	//var Date=getFieldValue("hdnbdmsEntrydate");
	//alert("Date && sect ..." +Date+"--"+sectId);
//	LoadPopUp("MttrMtbfView","MTTRMTBFDetails_input.MTTR?sectId="+sectId+"&month="+month, true, "87%", "80%", "7%", "6%", " ", "MTTR Report","",false);
	
}
function mttrGrid_onProcessGridBack(){
	var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#mttrGrid").getDataIDs();	
	var parentId =  jQuery("#mttrGrid").jqGrid('getCell', rowIds[0], 'KEYFIELD0');
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	
	var dataString  ="";			
	if(rowIds.length==0){
		hideShowBack(true);			
		parentId = jQuery("#hdnParentId").val();
	}
	if(rowIds.length>=0)
	{	
		if(parentId.substr(0,3) != 'LIN'){
			dataString = 'drillFlag=b';
			dataString += "&chkRemoveBlank="+removeBlank;
			processGridnew(url,dataString,"mttrGrid","pager",'',"doubleClickGrid","","mttrGrid_loadComplete");	
		}
		if(parentId.substr(0,3) == 'LIN'){}
	}
}			
					
function doubleClickGrid(id){ 
	var rowData = jQuery("#mttrGrid").jqGrid('getRowData',id);
	var selId = rowData.KEYFIELD0;
	alert("selid  "+selId);
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var circle = jQuery("#hiddencircle").val();
	if(checkForZeroes("mttrGrid",selId,2)){		
	if(selId.substr(0,3) != 'SPR' && circle.substr(0,3) != "CRC"){
		var filterData ="?";		
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

	jQuery("#chkMonthwise").attr('checked',true);
	enableFields("chkMonthwise");
}
jQuery('#btnSelectTab').click(function(){
	var row=jQuery('#mttrGrid').jqGrid('getGridParam','selrow');
	if(row==null || row == '' || row == 'undefined'){
		alert('Select The to View The Details');
		}
	else
		doubleClickGrid(row);
});

</script>
<form id="frmMTTR">
<div id="divGraphContainer" ></div>	

<div style="width:100%;padding-top:10px;margin-left:26;">
	<div style="float: left;width:94%">
	<div style="margin-left:16px;"><input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>
	<input id="btnSelectTab" class="easyui-button"  type="button" value="For Tab"/>	
	<label id="mttrnotes"  class="notes"   style="font-weight: bold; padding-left:20px; "> ${requestScope.drilldownMsg}</label>
	<label id="mttrnotesgraph"  class="notes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.GraphMsg}</label>
	</div >
	</div>
	
</div> 
<div id="wrapperRpt" >
<div style="margin-top:-30px\9;">
<table id="mttrGrid" ></table>
<div id="pager"></div>
</div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenRemoveBlank" value=""/>
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hiddencircle" value=""  />
<input type="hidden" id="hdnParentId" value=""  />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hdnFilterString"  />
</div>	
</form>

