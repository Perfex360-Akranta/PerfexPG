<script type="text/javascript">
jQuery(document).ready(function(){	
	var actionPart = jQuery('#hiddenUrl').val();	
	jQuery('#chkidentifed').attr('checked',true);
	var filter = jQuery("#hiddenStr").val();
	
	if(filter != null && filter.length>0)
	{
		var filterString = jQuery("#hiddenStr").val();
		filterString = removeValueFromUrl(filterString, "firstClick");
		filterString+="&firstClick=Y";
		var tableCaption = "Abnormality Summary";			
		//viewGrid(unescape(filterString),"");			
		processGridnew(actionPart,filterString,"abnGrid","pager",tableCaption,"doubleClickGrid","","smryGrid");			
	}
	else
		viewGrid(actionPart,"?q=1&firstClick=Y");	

	
	jQuery('#chkidentifed').click(function() {
		if(jQuery('#chkidentifed').is(':checked') ==  true){
			jQuery('#chkremoved').attr('checked',false);
		}else if(jQuery('#chkidentifed').is(':checked') ==  false){
			jQuery('#chkremoved').attr('checked',true);
		}
	});
	jQuery('#chkremoved').click(function() {
		if(jQuery('#chkremoved').is(':checked') == true){
			jQuery('#chkidentifed').attr('checked',false);
		}else if(jQuery('#chkremoved').is(':checked') == false){
			jQuery('#chkidentifed').attr('checked',true);
		}
	});

	jQuery("#btnPiechart").click(function(){
		if((jQuery('#chkremoved').is(':checked') == false)&& (jQuery('#chkidentifed').is(':checked') == false))
		{
			alert(" Select Identified or Removed");
				return false;     
		}
		
				var rowid = jQuery("#abnGrid").jqGrid('getGridParam','selrow');
				var url="";
				if(rowid!=null && rowid!=''&& rowid!=' '&& rowid!=undefined && rowid!='undefined'){
					var rowData = jQuery("#abnGrid").jqGrid('getRowData',rowid);
					var selId = rowData.keyid;
					var colData = selId.split("#");
					var flid=colData[0];
					url = "piechart.abnSmrRpt";
	
					if(jQuery('#chkremoved').is(':checked') == true){
						var flag ="Y";
					}else
						flag="";
					if(checkForZeroes("abnGrid",rowid,2)){	
						
						  url = url+"?rownum="+rowid+"&flag="+flag;
						  showGraphData(url);	
					}else{ 
						alert("No Record to View Graph");
						return false;
					}
				}else{
					rowid="";
					url = "piechart.abnSmrRpt";
					
					if(jQuery('#chkremoved').is(':checked') == true){
						var flag ="Y";
					}else
						flag="";
					url = url+"?rownum="+rowid+"&flag="+flag;
					  showGraphData(url);	
				}
			
				
	});
	
	
});
jQuery("#btnViewAbn").click(function(id){
  	 
	var rowid = jQuery("#abnGrid").jqGrid('getGridParam','selrow');
	var keyData = jQuery("#abnGrid").jqGrid('getCell', rowid, 'keyid');	
	
	var filter = jQuery("#hiddenStr").val();
		
	var fromDate = getFilterValue(filter, "dtFromDate");
	var toDate = getFilterValue(filter, "dtToDate");
	var mchId = getFilterValue(filter, "cmbMchid");
	var fromMonth = getFilterValue(filter,"dtFromMonth");
	var chkTradewise  =getFilterValue(filter,"chkTradewise");
	var toMonth = getFilterValue(filter,"dtToMonth");
	var abnType=jQuery('#hdnAbnType').val();	
	var url = jQuery("#abnGrid").jqGrid('getGridParam', 'url');
   
	url = url.replace("getData","input");
	url = url.replace("drillFlag=b","");
	url = escape(url); 
	var jsonstr = '{"filter":"'+ filter +'"}';
	
	/*if(keyData.substr(0,3)=="ASM" && mchId=='')
	{
		mchId = jQuery("#hdnParentId").val();
		jsonstr = '{"filter":"'+ filter +'" ,"mchId":"'+mchId+'"}';	
	}*/
	
	jQuery("#hdnFromDate").val(fromDate);
	jQuery("#hdnToDate").val(toDate);
	jQuery("#hdnFromMonth").val(fromMonth);
	jQuery("#hdnToMonth").val(toMonth);
	
	var perstData = jQuery.parseJSON(jsonstr);
	
	if( rowid == null)
		alert("Select Row to View Abnormalities");
	
	var colId = jQuery("#hdnActiveCell").val();	
	var colmodel = jQuery("#abnGrid").jqGrid('getGridParam','colModel');
	
	var colIndexName = colmodel[colId].name;
	var colIndex ="";
	if(chkTradewise.trim()=='Y'){
		colIndex=colIndexName.split("_");
		colIndexName=colIndex[0];
	}else
		colIndexName="";
	if(colId !=0 && colId !=1 && colId!=2&& colId!=3)
	{
		var column="ABNORMALITY";
		/*if(colId == 3)
			column = "ABNORMALITYIDENTIFIED";
		else if(colId == 4)
			column = "ABNORMALITYREMOVED";
		else if(colId == 5)
			column = "REDIDENTIFIED";
		else if(colId == 6)
			column = "REDREMOVED";
		else if(colId == 7)
			column = "WHITEIDENTIFIED";
		else if(colId == 8)
			column = "WHITEREMOVED";*/
		if(chkTradewise.trim()=='Y'){
			if(colId == 5 || colId == 6){
				colIndexName="";
				if(colId==5){
					header2="I";
					}
				else if(colId==6){
					header2="R";
					}
				column = "ABNORMALITY";
			}else{
				column = "TRADE";
				if(colIndex[1]=='I')
					header2="I";
				else
					header2="R";
			}
			
			
		}else{
			if(colId == 5 || colId == 6){
				if(colId==5){
					header2="I";
					}
				else if(colId==6){
					header2="R";
					}
				column = "ABNORMALITY";
			}
			else if(colId == 7 || colId ==8)
				{
				
				column = "RED";
				if(colId==7){
					header2="I";
					}
				else if(colId==8){
					header2="R";
					}
				}
			else if(colId == 9 || colId == 10){ 

				  column = "WHITE";
				  if(colId==9){
						header2="I";
						}
					else if(colId==10){
						header2="R";
						}
			}
		}
		var url1 = url.substring(0,30);
		
		if(url1=="HSE_AbnSummary_input.abnSmrRpt")
		{
			 if(colId == 5)
				column = "GREENIDENTIFIED";
			 else if(colId == 6)
					column = "GREENREMOVED";
		}
		
		var fromDate = jQuery("#hdnFromDate").val();
		var toDate = jQuery("#hdnToDate").val();
		var fromMonth = jQuery("#hdnFromMonth").val();
		var toMonth = jQuery("#hdnToMonth").val();
		if(keyData != null && keyData != '')
		{
			var cellcontent = jQuery("#hdnCellContent").val();
			if(cellcontent == "0")
			{
				alert("No Data Found");
				return false;
			}
			else{
				var flid=keyData.split("#");
				var ftrStr="&flid="+flid[0]+"&column="+column+"&dtFromDate="+fromDate+"&dtToDate="+toDate+"&dtFromMonth="+fromMonth+"&dtToMonth="+toMonth+"&mchId="+mchId+"&abnType="+abnType+"&chkTradewise="+chkTradewise+"&colIndexName="+colIndexName+"&header2="+header2;
				navigateToNextForm("AbnormalityDetails_view.abnRpt?q=2"+ftrStr,"",null,{"filterString":url});
			}
		}		
	}
});


function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{		
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");		
		jQuery("#hiddenRemoveBlank").val(removeBlank);
		
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType;	
		
		jQuery("#hiddenStr").val(filterString);	
		//var drillFlag = getFilterValue(filterString, "drillFlag");		
		filterString += '&drillFlag=f';
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"abnGrid","pager",tableCaption,"doubleClickGrid","","smryGrid");
		
		return true;
	}
	return false;	
}

function validateFilterSelection(filterString){
	return  true;
}


function abnGrid_onProcessGridBack()
{
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("abnGrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
	/*
	
	var url = jQuery('#hiddenUrl').val();				
	var rowIds = jQuery("#abnGrid").getDataIDs();	
	var parentId =  jQuery("#abnGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var dataString  ="";	
	dataString = 'drillFlag=b';
	if(url == "TeamSummary_input.abnSmrRpt"){
		var levNo = jQuery("#hdnTeamLevel").val();
		levNo = parseInt(levNo)-1;
		jQuery("#hdnTeamLevel").val(levNo);
		var teamid = jQuery("#hdnTeamId").val();
		var indexval = teamid.indexOf("/");
		
			teamid = teamid.substr(0,teamid.lastIndexOf("/"));
		if(indexval == "-1")
			teamid="";
		jQuery("#hdnTeamId").val(teamid);
		dataString += '&teamId='+teamid;
		dataString += '&levelNo='+levNo;
		
	}
			//&parentId='+parentId	
	processGridnew(url,dataString,"abnGrid","pager",'',"doubleClickGrid","","smryGrid");	
	*/	
}	

function doubleClickGrid(id){ 	
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("abnGrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
/*	var hdnUrl = jQuery('#hiddenUrl').val();
	
	var rowData = jQuery("#abnGrid").jqGrid('getRowData',id);	
	var selId = rowData.keyid;	
	var removeBlank = jQuery("#hiddenRemoveBlank").val();
	var circle = jQuery("#hiddencircle").val();
	var filterData ="?";
	
	filterData += '&flid='+ selId+'&drillFlag=f';
	filterData += "&chkRemoveBlank="+removeBlank;
	
	if(hdnUrl == "TeamSummary_input.abnSmrRpt"){
		var teamid = jQuery("#hdnTeamId").val();
		var levNo = jQuery("#hdnTeamLevel").val();
		levNo = parseInt(levNo)+1;
		jQuery("#hdnTeamLevel").val(levNo);
		if(teamid != null && teamid.length>0)
			teamid = teamid+'/';
		jQuery("#hdnTeamId").val(teamid+selId);
		filterData += '&teamId='+teamid+selId;
		filterData += '&levelNo='+levNo;
	}
	if(selId.substr(0,3) == "MCH")
		jQuery("#hdnParentId").val(selId);
		
	var url = jQuery('#hiddenUrl').val();
	if(rowData.Description != 'TOTAL' && selId.substr(0,3) != "ASM" && circle.substr(0,3) != "CRC")		
		processGridnew(url,filterData,"abnGrid","pager",'',"doubleClickGrid","","smryGrid");
	*/
}

function smryGrid()
{
	setDrillDownHeader("CH0-0","abnGrid","keyid");
	setTotalRowCss('abnGrid');
	/*var row = jQuery("#abnGrid").jqGrid('getDataIDs');
	var circle = jQuery("#hiddencircle").val();
	
	if(row.length>=0)
	{
		var parentId =  jQuery("#abnGrid").jqGrid('getCell', row[0], 'keyid');		
		if(parentId.substr(0,3) != 'CMP')
			hideShowBack(true);		
		 if(circle.substr(0,3) == 'CRC')
			 hideShowBack(false);	
	}
	setTotalRowCss('abnGrid');	
*/
	jQuery("#abnGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e)
		{		
			jQuery('#hdnActiveCell').val(iCol);
			jQuery("#hdnCellContent").val(cellcontent);		   
	 	}
 	});
	 
	 var url = jQuery('#hiddenUrl').val();	
	 if(url=="HSE_AbnSummary_input.abnSmrRpt")
		 jQuery("#btnViewAbn").val("HSE Abnormality");
}

function frmFilter_enableDisableSuccessCallBack()
{
	enableDisableDatenMonthFilter();
	enableFields('cmbAbnmTypeid');
	var url = jQuery('#hiddenUrl').val();
	if(url=="HSE_AbnSummary_input.abnSmrRpt")
	{
		jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");		
		readOnlyFields('cmbAbnmTypeid');		
		reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
		setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
		reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
	}
}
function abnDetailsView(rowid,iCol,cellcontent){
	 
	var colm = jQuery("#abnGrid").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;	
	var dateflag = selId.substring(0, 1);
	if(dateflag == '0' || dateflag == '1' || dateflag == '2' ||dateflag == '3' )
		selId = selId.substring(0,11);
	else
		selId = selId.substring(0,8);	
	jQuery("#hiddenrowid").val(selId);	
}

</script>
<form>
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >

<div style="margin-top:10px">
			<span style="border: solid 2px #c1c1c1;margin-left:40px ;" ><input id="chkidentifed" name="chkidentifed" type="checkbox" /><label>  Identified</label>
			<input id="chkremoved" name="chkremoved" type="checkbox" style="margin-left:6px ;"/><label> Removed</label>
			<input type="button" name="btnPiechart"id="btnPiechart" class="easyui-button" style="width:70px;margin-left:10px ;height:20px" value="Pie Chart"/></span>
			<span><input type="button" id="btnViewAbn" class="easyui-button" style="width:116px;margin-left:30px ;height:23px"" value="View Abnormality"/></span>
</div>
<br>
<div id="wrapperRpt" style="margin-top:-10px;">
	<input type="hidden" id="hdnActiveCell" name="hdnActiveCell"/>
	<div class="clear"></div>
	<table id="abnGrid" ></table>
	<div id="pager"></div>
	<input type="hidden" id="hiddenStr" value="${requestScope.filterStr}" />
	<input type="hidden" id="hiddenrowid" value=""/>
	<input type="hidden" id="hdnFromDate" value=""/>
	<input type="hidden" id="hdnToDate" value=""/>
	<input type="hidden" id="hdnFromMonth" value=""/>
	<input type="hidden" id="hdnToMonth" value=""/>
	<input type="hidden" id="hdnParentId" value="${requestScope.mchId}"/>
	<input type="hidden" id="hdnCellContent" value=""/>
	<input type="hidden" id="hiddenRemoveBlank" value=""  />
	<input type="hidden" id="hiddencircle" value=""  />
	<input type="hidden" id="hdnTeamId" value=""  />
	<input type="hidden" id="hdnFnlnKeyid" />
</div>	
</form>


