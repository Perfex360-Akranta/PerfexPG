<script type="text/javascript">
jQuery(document).ready(function()
{	
	var from=jQuery("#hdnFrom").val();
	jQuery('#submitForm').val('frmpcsNoPlanReport');
	if(from=="noPlanEntry" )
		jQuery("#NoPlanEntry").css('display','block');
	else if(from=="pcsCompliance" || from==" " || from==null )
		jQuery("#pcsComp").css('display','block');
	setLoadFormCallBackFrmId("frmpcsNoPlanReport");
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();	
	var prevData = unescape(jQuery('#hdnPrevDataUrl').val());
	var bckfrmPcsUrl = actionPart.substring(0,actionPart.indexOf('?'));
	var bckpcsFiltrStr =prevData.substring(prevData.indexOf('?'),prevData.length) ;
	bckpcsFiltrStr = bckpcsFiltrStr.substring(1);
	jQuery('#hdnfromBackPcs').val(bckpcsFiltrStr.length);
	if(jQuery('#hdnPrevDataUrl').val().length <= 0) 
	{
		viewGrid(actionPart,"");
	}
	else
	{
		viewGrid(prevData,"");
	}
});
function frmpcsNoPlanReport_afterLoadCallBack()
{
	if(jQuery('#hdnfromBackPcs').val() < 150)
		toggleCommonFilter();
}
function pcs_deleteSuccessCallback()
{
}
function viewGrid(url,filterString)
{
	if(  validateFilterSelection(filterString))
	{
		var tableCaption = "PCS Report";
		filterString += '&drillFlag=f';
		processGridnew(url,filterString,"pcs","pager",tableCaption,"pcs_doubleClickGrid","","pcs_loadComplete");
		return true;
	}
	return false;
}
function pcs_loadComplete()
{ 
	jQuery("#pcsnote").css('display','block');
	hideJqGridRow("pcs","1");
	hideJqGridRow("pcs","2");
	jQuery("#pcs").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
	{
		jQuery("#hiddencelVal").val(cellcontent);
		jQuery("#hdnRowId").val(rowid);
		jQuery("#hiddeniCol").val(iCol);
	}});
	jQuery(".jqgrid-rownum").each(function()
	{
		jQuery(this).html(parseInt(jQuery(this).html()) - 2 );
	}); 
	var getPage = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('w'),jQuery('.ui-paging-info').html().indexOf('-'));
	getPage = getPage.substring(1);
	var tworow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('-'),jQuery('.ui-paging-info').html().indexOf('of'));
	tworow =tworow.substring(1);
	var totrow = jQuery('.ui-paging-info').html().substring(jQuery('.ui-paging-info').html().indexOf('of'));
	totrow =totrow.substring(2);
	jQuery('.ui-paging-info').html('View '+getPage +' - '+totrow+' of '+totrow);
}
function frmFilter_enableDisableSuccessCallBack()
{
	if(jQuery('#hdnPrevDataUrl').val().length > 0)
	{
		var factId =jQuery('#hiddenfact').val();
		var sectId =jQuery('#hiddensect').val();
		var cellId =jQuery('#hiddencell').val();
		var date =jQuery('#hiddendate').val(); 
		var shift =jQuery('#hiddenshift').val();
		var dataStr ="";
		dataStr+="&factId="+factId;
		dataStr+="&sectId="+sectId;
		dataStr+="&cellId="+cellId;
		dataStr+= "&date="+escape(date);
		dataStr+= "&shift="+shift;
		loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter",dataStr);
		var frmdte = jQuery("#hdnfromdate").val();
		var todte = jQuery("#hdntodate").val();
		if(date != null && date != "")
		{
			jQuery('#dtefromDate').datebox("setValue",date);
			jQuery('#dtetoDate').datebox("setValue",date);
		}
		else
		{
			jQuery('#dtefromDate').datebox('getValue');
			jQuery('#dtetoDate').datebox('getValue');
		}
	}		
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',true);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
	jQuery('#chkDatewise').click(function(){
	if(jQuery('#chkDatewise').is(':checked') == false)
	{
	 	jQuery("#dtefromDate").datebox('disable');
		jQuery("#dtetoDate").datebox('disable');
	 	alert("Select Datewise Checkbox");			
	}});
}
function validateFilterSelection(filterString)
{
	if( ! checkFilterValueExist(filterString,"cmbSectid"))
	{
		if(filterString==null || filterString==''||filterString==""){}
		else
		{
			alert("Select Section");
			return false;
		}
	}	
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false))
	{
		alert("Either Datewise or Monthwise Checkbox to be Selected");
		return false;                    
	}
	if(jQuery('#chkDatewise').is(':checked') == true)
	{
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
		compareFromToDate(getFilterValue(filterString, "dtFromDate"),getFilterValue(filterString, "dtToDate"),"40");
	}
	else if(jQuery('#chkMonthwise').is(':checked') == true)
	{
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
	return  true;
}
function pcs_loadComplete_afterLoad(data)
{
	var from=jQuery("#hdnFrom").val();
	if(from=="noPlanEntry")
	{
		var rowid = jQuery("#pcs").jqGrid('getDataIDs');
		var cm = getColModel();
		for(var i=2;i<rowid.length;i++)
		{
			for(var j=8;j<cm.length;j++)
		    {
			   var zeroVal = jQuery("#pcs").jqGrid('getCell',rowid[i],cm[j].name);	
			   if(zeroVal != "" && zeroVal != " " && zeroVal != null)
			   {
				  if(zeroVal =='-1')	
				  {
					setCellPcsEntry(rowid[i],j,cm,zeroVal);
				  }				  
				  else if(zeroVal =='-2')
				  {
					 setCellFullNoPlan(rowid[i],j,cm,zeroVal);
				  }
				  else if(zeroVal =='-3')
				  {
					var date= cm[j].name.substring(0,9);
					var day=date.substring(0,2);
					var mon=date.substring(2,5);
					var yr=date.substring(5,9);
					date=day+"-"+mon+"-"+yr;
					jQuery("th:contains("+date+")").css("background-color","#9d99f2");
					setCellHolidayStatus(rowid[i],j,cm,zeroVal);
				  }	 
				  else if(zeroVal =='0')
				  {
					  setCellNoEntryExists(rowid[i],j,cm,zeroVal,"#ffffff");
				  }
				  else if(zeroVal>0)
				  {
					  setCellPartialNoPlan(rowid[i],j,cm,zeroVal,"#fffffff"); 
				  }	  
				}
			}
		}
	}
}
function pcs_doubleClickGrid(id)
{
	 var from=jQuery("#hdnFrom").val();
	 var iCol = jQuery("#hiddeniCol").val();
	 var colVal =jQuery("#hiddencelVal").val();
	 var cm = getColModel();
	 if(from=="noPlanEntry")
	 {
		var identifier=getPartialNoPlanIdentifier(iCol);
		var partialNoplan =getPartialNoPlanAttr(id,identifier);
		if (iCol <= 7) return ; 
		getCellStatusChanges(partialNoplan,colVal,id,iCol,cm);
	}	 		
} 
/*function noPlanSave(iCol,id) { 

	var rowData = jQuery("#pcs").jqGrid('getRowData',iCol);
    var date = jQuery("#pcs").jqGrid('getCell',1,iCol);
    jQuery("#hdnDateNP").val(date);
  	var mchId = jQuery("#pcs").jqGrid('getCell',id,4);
	jQuery("#hdnMchIdNP").val(mchId);
	var factId=jQuery("#pcs").jqGrid('getCell',id,1);
	var shiftCode = jQuery("#pcs").jqGrid('getCell',2,iCol);
	processAjaxCalls('checkIsOpenMsr.pcs','?q=2&shiftCode='+shiftCode+'&factId='+factId+'&mchId='+mchId+'&date='+date,'openMsrSuccForNoPlan');	
}*/
function getNoPlanForm(iCol,id,partialNoplan)
{
		var mchId = getMachineId(id);
		var date=getDate(iCol);
		var shiftCode = getShiftCode(iCol);
		var facId = getFactId(id);
		var cellId = getCellId(id);
		var secId = getSectId(id);
		LoadPopUp("divNoPlanSave","noPlan_input.pcscomp?&date="+date+"&shiftCode="+shiftCode+"&mchId="+mchId+"&factId="+facId+"&partialNoplanSatus="+partialNoplan+"&cellId="+cellId+"&secId="+secId, true,"325px","220px","1px","40%", "","No Plan Save","",false);                               	
}
function frmpcsNoPlanReport_successsCallback(result)
{
	jQuery("#hdnSaveStatus").val("");
	jQuery("#pcs").find('td[fullNoplansave="false"]').each (function()
	{
		var id=jQuery(this).parent().attr('id');
		var aria=jQuery(this).attr('aria-describedby');
		var cm = getColModel();
		var iCol=aria.substring(13);
		var identifier=getPartialNoPlanIdentifier(iCol);
		setCellFullNoPlan(id,iCol,cm,"-2");
		removeFullNoPlanSaveAttr(id,identifier);
	});
}
function noPlanSaveSuccess(result)
{
	var duration=parseInt(result.successData.duration);
	var type=result.successData.type;
	var rowId=jQuery("#hdnRowId").val();
	var colId=jQuery("#hiddeniCol").val();
	var colVal =jQuery("#pcs").jqGrid('getCell',rowId,colId);
	var cm =getColModel();
	var identifier=getPartialNoPlanIdentifier(colId);
	if(duration==480 && type=="NOPLAN")
	{
		setCellFullNoPlan(rowId,colId,cm,"");
	}
	else if(duration>0 && duration<480 && type=="NOPLAN")
	{
		if(getIsHolidayStatus(rowId,identifier)=="true")
			setCellPartialNoPlan(rowId,colId,cm,duration,"#9d99f2");
		else
			setCellPartialNoPlan(rowId,colId,cm,duration,"#ffffff");
		if(getFullNoPlanSaveAttrStatus(rowId,identifier)=="false")
			removeFullNoPlanSaveAttr(rowId,identifier);
	}
	else if(type=="DELETE")
	{
		if(getIsHolidayStatus(rowId,identifier)=="true")
			setCellNoEntryExists(rowId,colId,cm,"0","#9d99f2");
		else
			setCellNoEntryExists(rowId,colId,cm,"0","#ffffff");
		removePartNoPlanAttr(rowId,identifier);
		removeFullNoPlanSaveAttr(rowId,identifier);
	}
	jQuery("#hdnSaveStatus").val("");
	closePopUpDialoge("divNoPlanSave",true);
}		 
function getPCSView(result)
{
	var cellId = "";
	var facId = "";
	var secId= "";
	
	var from=jQuery("#hdnFrom").val();
 	var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
 	if(from=="noPlanEntry")
 	{
 		cellId = getCellId(rowid);
 		facId = getFactId(rowid);
 		secId= getSectId(rowid);
	}
	var date = jQuery('#hdnDate').val();
	var shift = result.shift;
	var formLock="lock";
	var url = jQuery("#pcs").jqGrid('getGridParam', 'url');
	url = url.replace("PcsnoPlanEntryRpt_getData.pcscomp","PcsnoPlanEntryRpt_input.pcscomp");
	url = escape(url);
	var fo = {"filterString":url};
	var persistentData = {"factId":facId,"sectId":secId,"cellId":cellId,"shift":shift,"date":date,"mode":"view","formLock":formLock}; 
	navigateToNextForm("pcsViewTest_input.pcs?"+"&mode=view" ,"PCS Entry-Time Based (View Mode)", persistentData,{"filterString":url});
 }
function divNoPlanSave_afterClose(direct)
{
	if(direct!=false)
		return true;
	var iCol=jQuery("#hiddeniCol").val();
	var id=jQuery("#hdnRowId").val();
	var date = getDate(iCol);
	jQuery('#hdnDate').val(date);
	var shiftCode = getShiftCode(iCol);
	var facId = getFactId(id);
	var frmPcsRpt = jQuery('#hdnfrmPcsRpt').val();
	if(frmPcsRpt.trim().length<=0)
		processAjaxCalls('getShiftKeyid.pcscomp','?q=2&factId='+facId+'&shiftCode='+shiftCode,"getPCSView");	
}
jQuery('#chkFullNoPlan').click(function()
{	
	if(jQuery('#chkFullNoPlan').is(':checked'))
	{
		jQuery('input:checkbox[id=chkPartialNoPlan]').attr('checked',false); 	
	}
	else
	{
		jQuery('input:checkbox[id=chkPartialNoPlan]').attr('checked',true);
	}
});
jQuery('#chkPartialNoPlan').click(function()
{	
	var saveStatus=jQuery("#hdnSaveStatus").val();
	if(saveStatus=='false')
	{	
		var retVal = confirm("Full No Plan Marked For Save.Do You Want To Continue?");
		if( retVal == true )
		{
			if(jQuery('#chkPartialNoPlan').is(':checked') )
			{
				jQuery('input:checkbox[id=chkFullNoPlan]').attr('checked',false); 	
			}
			else
			{
				jQuery('input:checkbox[id=chkFullNoPlan]').attr('checked',true);
			}
		 }
		 else
		 {
			 return false;
		 }
	}
	else
	{
		if(jQuery('#chkPartialNoPlan').is(':checked'))
		{
			jQuery('input:checkbox[id=chkFullNoPlan]').attr('checked',false); 	
		}
		else
		{
			jQuery('input:checkbox[id=chkFullNoPlan]').attr('checked',true);
		}
	}
});
function getSelectdRows()
{
	var jsonArrO='[';
	jQuery("#pcs").find('td[fullNoplansave="false"]').each (function()
	{
		var id=jQuery(this).parent().attr('id');
		var aria=jQuery(this).attr('aria-describedby');
		var iCol=aria.substring(13);
		jsonArrO += '{';
		var mchId = getMachineId(id);
		var date=getDate(iCol);
		var shiftCode =getShiftCode(iCol);
		jsonArrO += "getMachineKeyid :\""+ mchId+"\"";
		jsonArrO += ",";
		jsonArrO += "getShiftOrder :\""+ shiftCode+"\"";
		jsonArrO += ",";
		jsonArrO += "getShiftDate :\""+ date+"\"";
		jsonArrO +=  "},";
	});
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
} 
function frmpcsNoPlanReport_beforeSubmit()
{
	var gridData = '&noPlanSave='+getSelectdRows();
	var check=gridData.substr(12);
	if(check==null||check==""||check=='')
	{ 
		alert('Select Cells To Save Full No Plan');
		return false;
	}
	return gridData; 	
}
function pcs_keypress(keycode,iRow,iCol)
{
	var cm = getColModel();
	jQuery("#hdnRowId").val(iRow);
	var identifier= getPartialNoPlanIdentifier(iCol);
	var partialNoplan =getPartialNoPlanAttr(iRow,identifier);
	var colVal =jQuery("#pcs").jqGrid('getCell',iRow,iCol);
	if(keycode==13)
	{
		getCellStatusChanges(partialNoplan,colVal,iRow,iCol,cm);
	}
	else if(keycode==113)
	{
		saveForm("frmpcsNoPlanReport","PcsnoPlanEntryRpt_save.pcscomp");
	}
}
function getCellStatusChanges(partialNoplan,colVal,id,iCol,cm)
{
	var identifier=getPartialNoPlanIdentifier(iCol);
	var status="false";
	if(partialNoplan>0 ||partialNoplan==-2 ||colVal=="0" || colVal=="-3")
	{
		if(jQuery('#chkPartialNoPlan').is(':checked'))
		{
			getNoPlanForm(iCol,id,partialNoplan);
		}
		else if(jQuery('#chkFullNoPlan').is(':checked'))
		{	
			if(colVal!="-1")
			{		
				if(getFullNoPlanSaveAttrStatus(id,identifier)=="false")
				{
					if(getIsHolidayStatus(id,identifier)=="true")
						setCellNoEntryExists(id,iCol,cm,"0","#9d99f2");
					else
						setCellNoEntryExists(id,iCol,cm,"0","#ffffff");
					removePartNoPlanAttr(id,identifier);
					removeFullNoPlanSaveAttr(id,identifier);
					jQuery("#pcs").find('td[fullNoplansave="false"]').each (function()
					{	
						status="true";
					});
					if(status=="false")
					{
						jQuery("#hdnSaveStatus").val("");
					}		
				}
				else if(getPartialNoPlanAttr(id,identifier)=="-2")
				{
					return false;
				}
				else
				{	
					if(getIsHolidayStatus(id,identifier)=="true")
					{
						setCellFullNoPlanBeforeSave(id,iCol,cm,"","#9d99f2");
						setCellNoPlanNotSaveStatus(id,iCol,cm,"","#9d99f2");
						jQuery("#hdnSaveStatus").val('false');
					}
					else
					{
						setCellFullNoPlanBeforeSave(id,iCol,cm,"","#ffffff");
						setCellNoPlanNotSaveStatus(id,iCol,cm,"","#ffffff");
						jQuery("#hdnSaveStatus").val('false');
					}
					
				}
			}
		}                                                                                                                                                                                                                                                                                                                                                                           
	}	
	else
	{
		var saveStatus=jQuery("#hdnSaveStatus").val();
		if(saveStatus=="false")
		{
			var retVal = confirm("Full No Plan Marked For Save.Do You Want To Continue?");
			if( retVal == true )
				getShiftKeyid(id,iCol);
			else
				return false;
		}
		else 
			getShiftKeyid(id,iCol);
	}
}
function getShiftKeyid(id,iCol)
{              
	var date = getDate(iCol);
	jQuery('#hdnDate').val(date);
	var shiftCode = getShiftCode(iCol);
	var facId =getFactId(id);
	var frmPcsRpt = jQuery('#hdnfrmPcsRpt').val();
	if(frmPcsRpt.trim().length<=0)
		processAjaxCalls('getShiftKeyid.pcscomp','?q=2&factId='+facId+'&shiftCode='+shiftCode,"getPCSView");
}
function getPartialNoPlanIdentifier(iCol)
{
	var date = getDate(iCol);
	jQuery('#hdnDate').val(date);
	var day=date.substring(0,2);
	var mon=date.substring(3,6);
	var yr=date.substring(7,11);
	var colNum=iCol;
	var identifier="pcs_"+day+mon+yr+colNum;
	return identifier; 	
}
function getCellId(id)
{
	return jQuery("#pcs").jqGrid('getCell',id,3);
}
function getDate(iCol)
{
	return jQuery("#pcs").jqGrid('getCell',1,iCol);
}
function getShiftCode(iCol)
{
	return jQuery("#pcs").jqGrid('getCell',2,iCol);
}
function getFactId(id)
{
	return jQuery("#pcs").jqGrid('getCell',id,1);
}
function getMachineId(id)
{
	return jQuery("#pcs").jqGrid('getCell',id,4);
}
function getSectId(id)
{
	return jQuery("#pcs").jqGrid('getCell',id,2);
}
function setCellFullNoPlan(rowid,iCol,cm,zeroVal,color)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,"&#10003",{'color':'#800000','font-weight':'bold','font-size':'15px','background-color':color},{'partialNoplan':zeroVal});
}
function setCellPartialNoPlan(rowid,iCol,cm,zeroVal,color)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,"&#9734",{'color':'#800000','font-weight':'bold','font-size':'15px','background-color':color},{'partialNoplan':zeroVal});
}
function setCellPcsEntry(rowid,iCol,cm,zeroVal)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,zeroVal ,{'color':'#339933','font-weight':'bold','font-size':'15px','background-color':'#339933'});
}
function setCellNoPlanNotSaveStatus(rowid,iCol,cm,zeroVal,color)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,"&#10003",{'color':'#0C1D72','font-weight':'bold','font-size':'15px','background-color':color},{'fullNoplansave':'false'});
}
function setCellNoEntryExists(rowid,iCol,cm,zeroVal,color)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,"0",{'color':color,'font-weight':'bold','font-size':'15px','background-color':color});
}
function setCellHolidayStatus(rowid,iCol,cm,zeroVal)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,zeroVal,{'color':'#9d99f2','font-weight':'bold','font-size':'15px','background-color':'#9d99f2'},{'isHoliday':'true'});
}
function setCellFullNoPlanBeforeSave(rowid,iCol,cm,zeroVal,color)
{
	jQuery("#pcs").jqGrid('setCell',rowid,cm[iCol].name,"&#10003",{'color':'#0C1D72','font-weight':'bold','font-size':'15px','background-color':color},{'partialNoplan':-2});
}
function getPartialNoPlanAttr(id,identifier)
{
	return jQuery("#pcs tr#"+id+" td[aria-describedby="+identifier+"]").attr('partialnoplan');
}
function getFullNoPlanSaveAttrStatus(id,identifier)
{
	return jQuery("#pcs tr#"+id+" td[aria-describedby="+identifier+"]").attr('fullNoplansave');
}
function getIsHolidayStatus(id,identifier)
{
	return jQuery("#pcs tr#"+id+" td[aria-describedby="+identifier+"]").attr('isHoliday');
}
function removePartNoPlanAttr(id,identifier)
{
	 jQuery("#pcs tr#"+id+" td[aria-describedby="+identifier+"]").removeAttr('partialnoplan');
}
function removeFullNoPlanSaveAttr(id,identifier)
{
	 jQuery("#pcs tr#"+id+" td[aria-describedby="+identifier+"]").removeAttr('fullNoplansave');
}
function getColModel()
{
	 var cm = jQuery("#pcs").jqGrid("getGridParam", "colModel");
	 return cm;
}
</script>
<form name="frmpcsNoPlanReport" id="frmpcsNoPlanReport" >
<div style="margin-left:37px;">
<label class="notes">Use Double Click/Enter Key to Select Cells</label></div>
<div style="margin-left:3%;">
<table style="float:left;margin-left:5px;" width="88%">
<tr>
<td style="border: solid 1px #c1c1c1;border-right:none;width:96px;">
<label id="pcsnote1" style="font-weight:bold;">Double Click On</label>
</td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;">  
<span class="wo-priority" style="background-color:#ffffff;border: solid 1px #c1c1c1;"></span>
</td>
<td style="border: solid 1px #c1c1c1;border-left:none;width:98px;">
<label style="font-weight: bold;">To Apply No Plan</label>
</td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;">
<span class="wo-priority" style="background-color:#339933"></span></td>
<td style="border: solid 1px #c1c1c1;border-left:none;width:63px;"><label style="color: ;font-weight: bold">Pcs Entry </label></td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;">
<span style="color:#800000">&#10003</span></td>
<td style="border: solid 1px #c1c1c1;border-left:none;width:70px;"><label style="color: ;font-weight: bold">Full No Plan</label></td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;">
<span style="color:#800000">&#9734</span></td>
<td style="border: solid 1px #c1c1c1;border-left:none;width:88px;"><label style="color: ;font-weight: bold">Partial No Plan </label></td>
<td style="border: solid 1px #c1c1c1;border-left:none;border-right:none;">
<span class="wo-priority" style="background-color:#9d99f2"></span></td>
<td style="border:solid 1px #c1c1c1;border-left:none;"><label style="color: ;font-weight: bold">Holiday</label></td>
<td align="" style="width:342px;">
<table style="float:right;margin-left:25px;">
<tr>
<td><input type="checkbox" id="chkFullNoPlan" checked="checked" /></td>
<td><label style="font-weight:bold;">Full No Plan</label></td>
<td style="padding-left:10px;"><input  type="checkbox" id="chkPartialNoPlan" /></td>
<td><label style="font-weight:bold;">Partial No Plan</label></td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<br><br>
<div id="wrapperRpt" style= margin-top:2px;>
<table id="pcs" ></table>
<div id="pager"></div></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
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
<input type="hidden" id="hdnfrmPcsRpt" name="hdnfrmPcsRpt"  value="${requestScope.frmPcsRpt}"/>
<input type="hidden" id="hdnFrom" name="hdnFrom" value="${requestScope.fromPcsornoPlanEntry}"/>
<input type="hidden" id="hdnDateNP"/>
<input type="hidden" id="hdnMchIdNP"/>
<input type="hidden" id="hdnShiftNP"/>
<input type="hidden" id="hdnEscFutureDateVal" value="true"/>
<input type='hidden' id="partialNoplan" value="480"/>
<input type="hidden" id="hdnRowId" value=""/>
<input type="hidden" id="mode"  name="mode" value=""/>
<input type="hidden" id="hdnSaveStatus" value=""/>
 </form>
