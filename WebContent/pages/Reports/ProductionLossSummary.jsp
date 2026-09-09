
<script>
jQuery(document).ready(function(){	
	
						
	var url = jQuery('#hiddenUrl').val();	
	var FromDate=null;
	var ToDate = null;
	
	//viewGrid(url,dataString);
	setLoadFormCallBackFrmId('frmProductionLossSummary');
	invokeAfterLoadFormCallBack();
var dataStr ="";
	
	dataStr +="?q=2";
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
		var filterString = 	dataStr;
	//toggleCommonFilter();
	//viewGrid(url,filterString);
	jQuery('#machineWise').click(function(){
		var filtStr=jQuery.cookie("filterString");
		var filterString = filterString + filtStr;
 		if(jQuery('#machineWise').is(':checked') == true)
		{
 			jQuery('input:checkbox[id=lossWise]').attr('checked',false);
 			jQuery('#hdnchkVal').val('Mach');
 			viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
		}
 		else{
 			jQuery('input:checkbox[id=machineWise]').attr('checked',false);
 			jQuery('input:checkbox[id=lossWise]').attr('checked',true);
 			jQuery('#hdnchkVal').val('Loss');
 			viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
 	 	}
 	 		
	 });
	
		jQuery('#chkSubLoss').click(function(){
			var filtStr=jQuery.cookie("filterString");	
			filterString = filterString + filtStr;
			var url = jQuery('#hiddenUrl').val();	
			if(jQuery('#chkSubLoss').is(':checked'))
			{			
				jQuery("#hdnSubLoss").val("Y");
				viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
				//var tableCaption = "ProductionLossSummary Report";
				//processGridnew("ProductionLossSummary_input.ProdLossSmry",filtStr+'&chkSubLoss=Y',"list","pager",tableCaption,"ProductionLossSmry_doubleClick","","ProductionLossSmry_loadComplete");
				
			}
			else
			{						
				jQuery("#hdnSubLoss").val("N");
				viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
				//var tableCaption = "ProductionLossSummary Report";
				//processGridnew("ProductionLossSummary_input.ProdLossSmry",filtStr+'&chkSubLoss=N',"list","pager",tableCaption,"ProductionLossSmry_doubleClick","","ProductionLossSmry_loadComplete");				
			}
		});
	jQuery('#lossWise').click(function(){
		var filtStr=jQuery.cookie("filterString");
		filterString = filterString + filtStr;
		
 		if(jQuery('#lossWise').is(':checked') == true)
		{
 			jQuery('input:checkbox[id=machineWise]').attr('checked',false);
 			jQuery('#hdnchkVal').val('Loss');
 			viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
		}
 		else {
 			jQuery('input:checkbox[id=lossWise]').attr('checked',false);
 			jQuery('input:checkbox[id=machineWise]').attr('checked',true);
 			jQuery('#hdnchkVal').val('Mach');
 			viewGrid("ProductionLossSummary_input.ProdLossSmry",filtStr);	
 	 	}
	 });
	});

function frmProductionLossSummary_afterLoadCallBack(){
	//toggleCommonFilter();
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
		toggleCommonFilter();
	else
		jQuery('#hdnSetFilterValues').val('Y');	
}

function viewGrid(url,filterString)
{	
	if(filterString == "?q=2")
	{	
		alert("Select JH");
		return false;
	}
	
	if( validateFilterSelection(filterString))
	{		
		var chkedVal =jQuery('#hdnchkVal').val();
		var chkSubLoss = jQuery("#hdnSubLoss").val();
		if(chkSubLoss == "Y")
			filterString +="&chkSubLoss=Y";
		else
			filterString +="&chkSubLoss=N";
		filterString +="&chkRemoveBlank=Y";
		var newFilter = filterString;
		if(chkedVal == 'Loss' || jQuery('#lossWise').is(':checked') == true){
			newFilter = newFilter+"&chdVal=Loss";	
		}
		else if(chkedVal == 'Mach' || jQuery('#machineWise').is(':checked') == true){
			newFilter = newFilter+"&chdVal=Mach";
		}
			
		var tableCaption = "ProductionLossSummary Report";
		processGridnew(url,newFilter,"list","pager",tableCaption,"ProductionLossSmry_doubleClick","","ProductionLossSmry_loadComplete");
	
		jQuery('#hdnchkVal').val(' ');
		return true;
	}	
	return false;
}
function frmFilter_enableDisableSuccessCallBack(){
//alert("gd");
	if(jQuery('#hdnSetFilterValues').val() == 'Y')
		setFilterValues();	
	//jQuery("#dtefromMonth").datebox("setValue","");
	//jQuery("#dtetoMonth").datebox("setValue","");
	/*readOnlyFields('chkMonthwise');
	readOnlyFields('dtefromMonth');
	readOnlyFields('dtetoMonth');*/
	//jQuery("#chkDatewise").attr("checked",true);
	//jQuery("#chkMonthwise").attr("checked",false);
}

function validateFilterSelection(filterString){
	var actionPart = jQuery('#hiddenUrl').val();	
	jQuery.cookie("filterString","");	
jQuery.cookie("filterString",filterString);
if(actionPart.indexOf('filter')<0)
{

		 if( ! checkFilterValueExist(filterString,"cmbSectid") && !checkFilterValueExist(filterString,"cmbCircle"))
		{
			alert("Select DMT");
			return false;
		}	
		 if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
				alert("Either Datewise or Monthwise Checkbox to be Selected");
				return false;
			}
			  if(jQuery('#chkDatewise').is(':checked') == true){
				if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
				{
					alert("Select  FromDate");
					return false;
				}
				if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
				{
					alert("Select  ToDate");
					return false;
				}
			}
			else if(jQuery('#chkMonthwise').is(':checked') == true){
				if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromMonth"))
				{
					alert("Select  FromMonth");
					return false;
				}
				if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToMonth"))
				{
					alert("Select  ToMonth");
					return false;
				}
			} 	
}
		 return true;	
}

function ProductionLossSmry_doubleClick(id)
{
	
	var filterString = jQuery.cookie("filterString");
	jQuery("#hdnFilterStr").val(filterString);
	var url = jQuery('#hiddenUrl').val();	
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var keyId = rowData.KEYID;
	if(keyId.substring(0,3)!="QCM")
	{	filterString += "&cmbPhenomenaId="+keyId;
		viewGrid(url,filterString);
	}
	
}
function ProductionLossSmry_loadComplete()
{



if(jQuery('#machineWise').is(':checked') == true){
	var rowIds = jQuery('#list').jqGrid().getDataIDs();
	var cm = jQuery("#list").jqGrid("getGridParam", "colModel");
	//hideJqGridRow("list", "1");
	for(var i=0;i<rowIds.length;i++)
	{
		 for(var j=1;j<cm.length;j++)
     	 { 
	     	// alert(cm[j].name);
	     	 //alert(cm[j].name +" - -  "+cm.length);
	       //var lossVal = jQuery("#list").jqGrid('getCell',rowIds[i],cm[j].name);
	    
	     
	        	//jQuery("#list").jqGrid('setCell',rowIds[i],cm[j].name,'',{'font-size':'12px'});
		 
     	 }
	}
} 
if(jQuery('#list tr').hasClass('totalRow')){
  jQuery(' tr.totalRow').find(' td:first-child').css('color','#000');
  jQuery(' tr.totalRow').find(' td:first-child').css('border-right','solid 1px #8DB2E3');
  jQuery("#list tr" ).removeClass("totalRow");
}
	var filterString = jQuery.cookie("filterString");
	
	if(getFilterValue(filterString, "chkboxPcsShift")=="Y")
		{}
	else{
		if(jQuery('#lossWise').is(':checked') == true)
		{
			setTotalRowCss('list');
		}
	}
	
	
	var url = jQuery('#hiddenUrl').val();	
	var rowIds = jQuery("#list").getDataIDs();
	var parentId = jQuery("#list").jqGrid('getCell', rowIds[0], 'KEYID');			
	
	if(parentId.substr(0,3) == 'QPH')			
		hideShowBack(false);	
	else	
		hideShowBack(true);	
	
	

	
}

function list_onProcessGridBack(){

	
	var actionPart = jQuery("#hiddenUrl").val();//document.getElementById('hiddenUrl').value;					
	var backVal=true;
	
	var rowid=jQuery("#list").getDataIDs()[0];
	var rowData = jQuery("#list").jqGrid('getRowData',rowid);
	var keyid = rowData.KEYID;
	var line = getFieldValue('cell');
	if(keyid.substring(0,3) == 'QCM')
	{	var month =	jQuery("#hdnMonth").val();
		//var dataString = jQuery.cookie("filterString");
		var dataString = '?q=2&backVal='+backVal;
		dataString += '&cmbCellid='+line;
		var dataStr = jQuery("#hdnFilterStr").val();
		jQuery("#jhlist").GridUnload();		
		viewGrid(actionPart,dataStr);
	}	
  }
  
</script>
<form id="frmProductionLossSummary">
<div>
<!--<input type="button" style="float: right;" id="btnDefectGraph" name="btnDefectGraph" class="easyui-button"  value="Graph"></input>-->
</div>

<div id="wrapperRpt" style="margin-top:5px;">
<div class="clear"></div>
<div id='chkBxDiv' style='margin-left:1%;margin-bottom:1%;border:solid 2px #c1c1c1;width:40%;padding:3px; float:left' >
<input type='checkbox' id='machineWise' value='Y' checked="checked"/><label style='margin-left:10px;'>Machine Wise View</label>
<span style='margin-left:10px;'>
<input type='checkbox' id='lossWise' value='Y' /><label style='margin-left:10px;'>Loss Wise View</label>
</span>
</div>
<span style='margin-left:10px; position:relative;top:7px;'>
<input type='checkbox' id='chkSubLoss'  checked="checked"/><label style='margin-left:10px;'>Include SubLoss</label>
</span>
<br><br>
<table id="list" style="width:100%; padding-top:2%;">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
<input type="hidden" id="hdnFilterStr" name="hdnFilterStr" />
<input type="hidden" id="hdnchkVal" name="hdnchkVal" />
<input type="hidden" id="hdnSubLoss" name="hdnSubLoss" />
<input type="hidden" id="hdnDate" name="hdnDate"/>
<input type="hidden" id="hiddenfact" name="hiddenfact" value="${requestScope.hdnfactId}" />
<input type="hidden" id="hiddensect" name="hiddensect" value="${requestScope.hdnsectId}" />
<input type="hidden" id="hiddencell" name="hiddencell" value="${requestScope.hdncellId}" />
<input type="hidden" id="hiddendate" name="hiddedate" value="${requestScope.hdndateId}" />
<input type="hidden" id="hiddenshift" name="hiddeshift" value="${requestScope.hdnshftId}" />
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hdnPcsDataUrl" name="hdnPcsDataUrl" />
<input type="hidden" id="hiddeniCol"  />
<input type="hidden" id="hiddencelVal"  />
<input type="hidden" id="hdnfromdate" name="hdnfromdate" value="${requestScope.hdnfromdate}" />
<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.hdntodate}" />
<input type="hidden" id="hdnfromBackPcs" name="hdnfromBackPcs"  />
<input type="hidden" id="hdnSetFilterValues" name="hdnSetFilterValues"  />
<input type="hidden" id="hdnfrmPcsRpt" name="hdnfrmPcsRpt"  value="${requestScope.frmPcsRpt}"/>
<input type="hidden" id="hdnfilterClicked" name="hdnfilterClicked"  />
</form>