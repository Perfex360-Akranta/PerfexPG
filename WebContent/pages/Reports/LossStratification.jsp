
<script type="text/javascript">

jQuery(document).ready(function(){	
	
	var actionPart = jQuery('#hiddenUrl').val();	
	var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	jQuery("#chkboxvai").attr("checked",false);
	if( prevDataUrl == null || prevDataUrl.length <= 0)		
		viewGrid("LossStratification_input.losstr","?q=2&firstClick=Y");
	else{
		 	viewGrid(unescape(prevDataUrl),"?q=2");
	}		
	
	jQuery('#btnGraph').click(function(){		
		var rowid = jQuery("#hdnselid").val();	 
		
		if(rowid.trim().length<=0){ 
			alert("Select on the Column or Data  to View Graph");
		}
		else{
			 graph_dialog();			
		}
	}); 	
});
jQuery('#chkboxviewSubloss').click(function(){
	if(jQuery('#chkboxviewSubloss').is(':checked')==true){
		jQuery('#LossGrid td:contains("(+)")').trigger('click');
	}
	else{
		jQuery('#LossGrid td:contains("(-)")').trigger('click');
	}
});
jQuery('#chkboxvai').click(function(){
	if(jQuery('#chkboxvai').is(':checked')==true)
		viewAddInfo();		
	else 		
		hideAddInfo();		
});
function viewAddInfo()
{
	var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');	
	for(var i=0;i<allRowsId.length;i++)
	{
		var ID=allRowsId[i];
		
		var nextTr = jQuery("#LossGrid tr[id="+ID+"]").next('tr').attr("id");
		
		var nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);		
		var color=parseInt(nextRowData[3]).toString(16);
		
		if(nextRowData.GroupF == "A" ){
			
			while(nextRowData.GroupF=="A")
			{				
				
				
				jQuery("#LossGrid tr[id="+nextTr +"]").show();					
				 jQuery("#" +nextTr).find("td").css("background-color", "#"+color);
				 nextTr = jQuery("#LossGrid tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);	
				
			}	
		}
	}
}

function hideAddInfo()
{	
	var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');	
	for(var i=0;i<allRowsId.length;i++)
	{
		var ID=allRowsId[i];		
		var nextTr = jQuery("#LossGrid tr[id="+ID+"]").next('tr').attr("id");		
		var nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);
			
		if(nextRowData.GroupF == "A"){
			
			while(nextRowData.GroupF=="A")
			{			
				//hideJqGridRow("LossGrid", nextTr);
				
				jQuery("#LossGrid"+ ' tr[id='+ nextTr +"]").css({display:"none"});
				 nextTr = jQuery("#LossGrid tr[id="+nextTr+"]").next('tr').attr("id");				 
				 nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);					
			}	}
		}
}

jQuery("#btnoee").click(function(rowid){
	var rowid = jQuery("#hdnselid").val();
	if(rowid == null || rowid ==''){ 
		alert("Select on the Column   ");
	}	
	else{
		if(rowid == "no" )
			rowid = jQuery("#hiddenkey").val();
		var datStr ="?q=2";
		if(rowid.substring(0,3)=='CMP'){
			datStr +="&cmbCompid="+rowid;	
	}
	else if(rowid.substring(0,3)=='LCN'){		
		datStr +="&cmbLocnid="+rowid;	
	}
	else if(rowid.substring(0,3)=='FCT'){		
		datStr+="&cmbFactid="+rowid;
	}
	else if(rowid.substring(0,3)=='LIN'){		
		datStr+="&cmbSectid="+rowid;
	}
	else if(rowid.substring(0,3)=='CEL'){		
		datStr+="&cmbCellid="+rowid;
	}
	else if(rowid.substring(0,3)=='MCH'){		
		datStr+="&cmbMchmid="+rowid;
	}	
		
		//&parentId=FCT002
	var url = jQuery("#LossGrid").jqGrid('getGridParam', 'url');
	url = url.replace("LossStratification_getData.losstr","LossStratification_input.losstr");
	url = escape(url); 
	
	navigateToNextForm("Oeearprqr_input.oeear"+datStr+"&parentId="+ jQuery('#hdnPrevParent').val(),"OEE,AR,PR,OR - Trend",null,{"filterString":url});
	}
});
jQuery("#btnloss").click(function(rowid){	
	var rowid = jQuery("#hdnselid").val();	
	if(rowid == null || rowid ==''){ 
		alert("Select on the Column ");
		return false;
	}
	if(rowid.substring(0,3)=='CMP'){
		datStr+="cmbCompid="+rowid;
		alert("From Unit Level Onwards Loss Analysis Can Viewed");
		return false;
	}
	if(rowid.substring(0,3)=='LCN'){
		datStr+="cmbLocnid="+rowid;
		alert("From Unit Level Onwards Loss Analysis Can Viewed");
		return false;
	}
	else{
		if(rowid == "no" )
			rowid = jQuery("#hiddenkey").val();
		var datStr ="?q=2&";	
		datStr+=getParamName(selId)+"="+rowid;
		var url = jQuery("#LossGrid").jqGrid('getGridParam', 'url');
		url = url.replace("LossStratification_getData.losstr","LossStratification_input.losstr");
		url = escape(url); 
		jQuery('#isCommonFilterSlideOpen').val('N');
			navigateToNextForm("ProductionLoss_input.prdloss"+datStr,"Production Loss Analysis",null,{"filterString":url});
	}
});
jQuery("#btnmon").click(function(rowid){	
	var rowid = jQuery("#hdnselid").val();	
	if(rowid == null || rowid ==''){ 
		alert("Select on the Column");
		return false;
	}	
	if(rowid.substring(0,3)=='CMP'){
		datStr+="cmbCompid="+rowid;
		alert("From Equipment Level Onwards Monthwise Can Viewed");
		return false;
	}
	if(rowid.substring(0,3)=='LCN'){
		datStr+="cmbLocnid="+rowid;
		alert("From Equipment Level Onwards Monthwise Can Viewed");
		return false;
	}
	if(rowid.substring(0,3)=='FCT'){
		datStr+="cmbFactid="+rowid;
		alert("From Equipment Level Onwards Monthwise Can Viewed");
		return false;
	} 
	if(rowid.substring(0,3)=='LIN'){
		datStr+="&cmbSectid="+rowid;
		alert("From Equipment Level Onwards Monthwise Can Viewed");
		return false;
	}	 
	if(rowid.substring(0,3)=='CEL'){
		datStr+="&cmbCellid="+rowid;
		alert("From Equipment Level Onwards Monthwise Can Viewed");
		return false;
	} 
	else{
		if(rowid == "no" )
			rowid = jQuery("#hiddenkey").val();		 
		var datStr ="?q=2&";
		datStr+=getParamName(selId)+"="+rowid;
		var url = jQuery("#LossGrid").jqGrid('getGridParam', 'url');
		url = url.replace("LossStratification_getData.losstr","LossStratification_input.losstr");
		url = escape(url); 
		jQuery('#isCommonFilterSlideOpen').val('N');
		navigateToNextForm("PrdLogRpt_input.prdlogMon"+datStr,"Production Log Report(Month Wise)",null,{"filterString":url});
	}	
});
jQuery("#btnpcs").click(function(){	 
	var rowid = jQuery("#hdnselid").val();	
	if(rowid == null || rowid ==''){ 
		alert("Select on the Column   ");
		return false;
	}
	if(rowid.substring(0,3)=='MCH'){
		datStr+="cmbMchid="+rowid;
		alert("For Machine Level PCS Cannot View");
		return false;
	}
	else{
		if(rowid == "no" )
			rowid = jQuery("#hiddenkey").val();
		var datStr ="?q=2&fromLossStratifi=true&";		
		datStr+=getParamName(selId)+"="+rowid;	
		var url = jQuery("#LossGrid").jqGrid('getGridParam', 'url');
		url = url.replace("LossStratification_getData.losstr","LossStratification_input.losstr");
		url = escape(url); 		
		navigateToNextForm("PcsReport_input.pcsrpt"+datStr,"PCS Report",null,{"filterString":url});	
	}
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		var circle = getFilterValue(filterString, "cmbCircle");
		jQuery("#hiddencircle").val(circle);
		var comp =  getFilterValue(filterString, "cmbCompid");
		if(comp.trim() != "" && comp != null)
			jQuery("#hiddenBack").val("N");
		var tableCaption = "Loss Stratification";	

		var removeBlank = getFilterValue(filterString, "chkRemoveBlank");//alert(removeBlank);
		if(removeBlank.trim().length<=0)
			removeBlank = 'Y';
		//alert("dd  "+ removeBlank);
		jQuery("#hiddenLStfiRemoveBlank").val(removeBlank);
		filterString += "&chkRemoveBlank="+removeBlank;
		filterString += '&drillFlag=f';
		jQuery.cookie("sessionFilterString",filterString);
			
		processGridnew(url,filterString,"LossGrid","pager",tableCaption,"doubleClickGrid","","LossGrid_loadComplete");
		return true;
	}
	return false;	
}
function getParamName(selId)
{
	if(selId.substring(0,3)=='CMP')
		return 'cmbCompid';
	else if(selId.substring(0,3)=='LCN')
		return 'cmbLocnid';
	else if(selId.substring(0,3)=='FCT')
		return 'cmbFactid';
	else if(selId.substring(0,3)=='LIN')
		return 'cmbSectid';
	else if(selId.substring(0,3)=='CEL')
		return 'cmbCellid';
	else if(selId.substring(0,3)=='MCH')
		return 'cmbMchid';
	else
		return null;
}
function addSubLoss(rowid)
{
 	var rowData = jQuery("#LossGrid").jqGrid('getRowData',rowid);
	var keyId = rowData.keyid;		 
	var sectid = getFieldValue('section');
	var fctid = getFieldValue('factory');	
	var celid = getFieldValue('cell');
	var mchid = getFieldValue('machine');	
	
	if(sectid != null){		
		var filterString = "keyID="+keyId;
			filterString +="&sectid="+sectid;
			filterString +="&fctid="+fctid;
			filterString +="&rowId="+rowid;
			filterString +="&celid="+celid;
			filterString +="&mchid="+mchid;		
		var url = "LossStratificationSubGrid_getData.losstr";
		jQuery("#hiddenChkVal").val("1");
		processAjaxCalls(url,filterString,"subGrid_successCallBack","subGrid_errCallBack","");
		return false;
	}
	else{	 
		var selId =jQuery('#hdnselid').val();
		var filterString ="?";
		filterString = "keyID="+keyId;
		filterString +="&rowId="+rowid;
		
		if(selId == null || selId =="")
			alert("From Line Level Sub Losses Can View");
		if(selId.substr(0,3) =='CMP')
			alert("From Line Level Sub Losses Can View");
		if(selId.substr(0,3) =='LCN')
			alert("From Line Level Sub Losses Can View");
		if(selId.substr(0,3) =='FCT')
			alert("From Line Level Sub Losses Can View");
			
		var url = "LossStratificationSubGrid_getData.losstr";
		jQuery("#hiddenChkVal").val("1");
		processAjaxCalls(url,filterString,"subGrid_successCallBack","subGrid_errCallBack","");
		return false;
	}	 
	return false;
}

function LossGrid_loadComplete_afterLoad()
{
	//hideAddInfo();	
	
	if(jQuery('#chkboxvai').is(':checked'))
		viewAddInfo();		
	else 		
		hideAddInfo();		
	fillColor();
	/**For Hiding sublosses when uncheck check box***/
	jQuery('#LossGrid td:contains("(-)")').bind("click",function(){
		var id = jQuery(this).parent().attr("id");			
		var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');				
		for(var i=0; i<allRowsId.length;i++)
		{	 
			if(allRowsId[i] == id )
			{	 			
				for(var j=i+1; i<allRowsId.length;j++){
					var rowData = jQuery("#LossGrid").jqGrid('getRowData',allRowsId[j]);					 					
					if( rowData.GroupF=="S"){//s--Sub Loss							 
						hideJqGridRow("LossGrid", allRowsId[j]);	
						jQuery("#LossGrid").jqGrid('setCell',id,"GroupField",jQuery("#LossGrid").jqGrid('getCell',id,3).replace('-','+'));
					}
					else
						return;
				}	
			}
		}
	});
	jQuery('#LossGrid td:contains("(+)")').bind("click",function(){
		var hideVal = this.title;
   		
	if(hideVal.substring(1,2)=="+"){
		var rowid = jQuery(this).parent().attr("id");
		var nextTr = jQuery("#LossGrid tr[id="+rowid+"]").next('tr').attr("id");
		var nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);
		if(nextRowData.GroupF=="S")	{
			while(nextRowData.GroupF=="S")
			{		
				jQuery("#LossGrid tr[id="+nextTr +"]").show();				
				var nextTr = jQuery("#LossGrid tr[id="+nextTr+"]").next('tr').attr("id");				
				var nextRowData = jQuery("#LossGrid").jqGrid('getRowData',nextTr);					
			}			
			jQuery("#LossGrid").jqGrid('setCell',rowid,"GroupField",jQuery("#LossGrid").jqGrid('getCell',rowid,3).replace('+','-'));
			}
			else{
				addSubLoss(rowid);		
		}}
		 else if(hideVal.substring(1,2)=="-"){			 
			var id = jQuery(this).parent().attr("id");			
			var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');				
			for(var i=0; i<allRowsId.length;i++)
			{	 
				if(allRowsId[i] == id )
				{	 			
					for(var j=i+1; i<allRowsId.length;j++){
						var rowData = jQuery("#LossGrid").jqGrid('getRowData',allRowsId[j]);					 					
						if( rowData.GroupF=="S"){//s--Sub Loss							 
							hideJqGridRow("LossGrid", allRowsId[j]);	
							jQuery("#LossGrid").jqGrid('setCell',id,"GroupField",jQuery("#LossGrid").jqGrid('getCell',id,3).replace('-','+'));
						}
						else
							return;
					}	
				}
			}			 			 
		 }		
		 return false;
	});
	}
function LossGrid_loadComplete(){
	
	jQuery("#LossGrid").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){		
		 doubleClickGrid(rowid,iCol);
	}});	
	
	var rowIds = jQuery("#LossGrid").getDataIDs();
	if(rowIds.length==0)
		hideShowBack(true);	
	if(rowIds.length>=0)
		{
			var parentId =  jQuery("#LossGrid").jqGrid('getCell', rowIds[0], 'keyid');
			jQuery('#hdnParentId').val(parentId);	
			 var cm = jQuery("#LossGrid").jqGrid("getGridParam", "colModel");
			 var circle = jQuery("#hiddencircle").val();
			 for(var j=5;j<cm.length;j++)
	     	 {
		     	
				hideShowBack(false);		
				if(cm[j].name.substr(0,3) != 'CMP' && circle.substr(0,3) != 'CRC' )			
				hideShowBack(true);		
				if(cm[j].name.substr(0,3)=='MCH'||cm[j].name.substr(0,3)=='CEL'){
					jQuery('#chkboxviewSubloss').attr('disabled',false);
					jQuery('#chksubLos').attr('disabled',false);
				}
				else{
					jQuery('#chkboxviewSubloss').attr('disabled',true);
					jQuery('#chksubLos').attr('disabled',true);
				}
	     	 }	
			 
		}
jQuery("#LossGrid").setGridParam({		
		onCellSelect:function(id,cellidx,cellvalue) { 
			var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
			selId = colm[cellidx].name;
			
			jQuery('#hdnselid').val(selId );
		}
	});			
jQuery("tr.jqgridheaderrow1").children().bind("dblclick", function(){		
		var cell = jQuery(this);
		var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +3;		
		var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
		selId = colm[index].name;
		jQuery('#hdnselid').val(selId);
		/*var removeBlank = jQuery("#hiddenLStfiRemoveBlank").val();
		alert(selId);
		var filterData ="?";			
		filterData += '&parentId='+ selId+'&drillFlag=f';
		filterData += "&chkRemoveBlank="+removeBlank;
		var url = jQuery('#hiddenUrl').val();					
		processGridnew(url,filterData,"LossGrid","pager",'',"","","LossGrid_loadComplete");*/	
		doubleClickGrid();
	});
jQuery("tr.jqgridheaderrow1").children().bind("click", function(){	 
		var cell = jQuery(this);
		var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +3;		
		var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
		selId = colm[index].name;
		jQuery("#hiddenkey").val(selId);
		jQuery("#hdnselid").val("no");
	});	
jQuery('#hdnselid').val('');
setDrillDownHeader();
}

function setDrillDownHeader(){
	var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
	if(colm.length>=6)
	{
		var keyField = colm[5].name;
		var celldata= keyField.split("#");	
		var parentId=getValueBySeparator(celldata[2],"N","#");
		var url=jQuery("#LossGrid").jqGrid('getGridParam','url');
		
		var firstClick = getValueBySeparator(url,"firstClick","&");
		url = removeValueFromUrl(url, "firstClick");
		var elementType=jQuery("#hdnElementVal").val();
		var eleType=getValueBySeparator(celldata[1],"L","#");
		
		if(firstClick.length>0){
			jQuery("#hdnElementVal").val(eleType);
			parentId="B";
		}
		if(eleType.trim()==elementType.trim())
			parentId="B";
		if(parentId.contains('B'))			
			hideShowBack(false);
		else			
			hideShowBack(true);		
    	var levHeader= setDrillHeader(eleType);
    	jQuery("#CH0-0").html(levHeader);
	}
}

function graph_dialog(){	
	jQuery( "#graphOption" ).show();
	jQuery( "#graphOption" ).dialog({
		autoOpen: false,
		modal: true,		
		title:"graphOption"
	});
	jQuery('div .panel-title ').filter(function() { 
		  return jQuery(this).html() == 'graphOption'; 
		}).remove();
	jQuery(".window-shadow").hide();
}
jQuery('#btnCancel').click(function(){	
	jQuery( "#graphOption" ).dialog("close");	
});
jQuery('#btnGraphOk').click(function(){
	 var filterString ="";
	if(jQuery('#chkPercnt').is(':checked') == true)
	{ 
		filterString +="chkPercnt";		
		var rowid = jQuery("#hdnselid").val();		
		if(rowid == "no" )
			rowid = jQuery("#hiddenkey").val();		
		var url = "chartpercent.losstr?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
		showGraphData(url);
	}
	else if(jQuery('#chksubLos').is(':checked') == true)
		{
			filterString +="chksubLos";   		
			var rowid = jQuery("#hdnselid").val();			
			if(rowid == "no" )
				rowid = jQuery("#hiddenkey").val();			 
			var url = "chart.losstr?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);			
		}    
   	else if(jQuery('#chkZeroLoss').is(':checked') == true)
		{
   	 		filterString +="chkZeroLoss";
	   	 	var rowid = jQuery("#hdnselid").val();			
			if(rowid == "no" )
				rowid = jQuery("#hiddenkey").val();			 
			var url = "chartzero.losstr?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);			 
		}
   		else{
			var rowid = jQuery("#hdnselid").val();			
			if(rowid == "no" )
				rowid = jQuery("#hiddenkey").val();			 
			var url = "chartzero.losstr?" + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
			showGraphData(url);	
   		}		
		jQuery( "#graphOption" ).dialog("close");
   });
function doubleClickGrid(id,iCol,colVal)
{
	
	selId =jQuery('#hdnselid').val();
	var prevParent = null;
	
	if(selId.substring(0,3)=="CMP" ){
		prevParent = selId;
	}
	else if(selId.substring(0,3)=="LCN"){
		prevParent = selId;
	}
	else if(selId.substring(0,3)=="FCT" ){
		prevParent = selId;
	}
	else if(selId.substring(0,3)=="LIN"){
		prevParent = selId;
	}
	else if(selId.substring(0,3)=="CEL"){
		prevParent = selId;
	}
	else if(selId.substring(0,3)=="MCH"){
		prevParent = selId;
	}
	jQuery('#hdnPrevParent').val(prevParent);
	try{ 
		fillDrilFunctlocCombo(selId);
	}catch(Exception	){
	}
	setDrillDoubleClick(id);
	/*if(selId.substr(0,3) != 'MCH'){
		var filterData ="?";	
		var removeBlank = jQuery("#hiddenLStfiRemoveBlank").val();
		filterData += "&chkRemoveBlank="+removeBlank;
		filterData += '&parentId='+ selId+'&drillFlag=f';
		var url = jQuery('#hiddenUrl').val();
				
		processGridnew(url,filterData,"LossGrid","pager",'',"doubleClickGrid","","LossGrid_loadComplete");	
	}	*/
	
} 
function setDrillDoubleClick(id){
	var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
	if(colm.length>=6)
	{
		var keyField =  jQuery('#hdnselid').val();
		var celldata= keyField.split("#");	
		var click=getValueBySeparator(celldata[2],"N","#");
		if(click.contains('Y') || click.contains('B')){
			jQuery("#hdnFnlnKeyid").val(getValueBySeparator(celldata[3],"PF","#"));
			loadNextDrillLevel('f','N');	
		}else
			return false;
	}
}
function loadNextDrillLevel( drillflag,backData){
	var url=jQuery('#hiddenUrl').val();	
	url = removeValueFromUrl(url, "flid");
	url = removeValueFromUrl(url, "drillFlag");
	url = removeValueFromUrl(url, "firstClick");
	var filterData ="";	
	var selId="";
	if(backData.contains('N')){
	   	var keyField = jQuery('#hdnselid').val();
	   	if(drillflag.contains('b')){
	   		var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
	   		keyField = colm[5].name;
	   	}
	   	var celldata= keyField.split("#");	
	   	selId=celldata[0];
	}else{
		selId=jQuery('#hdnFnlnKeyid').val();
	}
	filterData += '?flid='+ selId+'&drillFlag='+drillflag;
	url+=filterData;
	viewGrid(url,"");
}
function LossGrid_onProcessGridBack(){	
	/*var url = jQuery('#hiddenUrl').val();					
	var rowIds = jQuery("#LossGrid").getDataIDs();
	var parentId =  jQuery("#LossGrid").jqGrid('getCell', rowIds[0], 'keyid');
	var removeBlank = jQuery("#hiddenLStfiRemoveBlank").val();
	
	var dataString = '&drillFlag=b';		
	dataString+= "&chkRemoveBlank="+removeBlank;
	processGridnew(url,dataString,"LossGrid","pager",'',"doubleClickGrid","","LossGrid_loadComplete");*/
    	var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
    	if(colm.length>=6)
    	{
    		var colm = jQuery("#LossGrid").jqGrid ('getGridParam', 'colModel');
    		var keyField = colm[5].name;
    		if((keyField.trim()).length>0){
    			var celldata= keyField.split("#");	
    			var click=getValueBySeparator(celldata[2],"N","#");	
    			if(click.contains('B'))
    				return false;
    		}
    		loadNextDrillLevel('b','N');	
    	}else
    		loadNextDrillLevel('f','Y');	
}
function validateFilterSelection(filterString){
	if( filterString.substring(0,4) == "?q=2")
		return true;
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
	return  true;
} 	

function fillColor()
{
	var allRowsId = jQuery('#LossGrid').jqGrid('getDataIDs');
	
	var ID=	"";
	var flag= true;
		for(var i=0;i<allRowsId.length;)
			{
			ID=allRowsId[i];			
			var nextRowData = jQuery("#LossGrid").jqGrid('getRowData',ID);
			var color=parseInt(nextRowData.Group).toString(16);
			
			if( nextRowData.GroupF == "M" ){
				flag = true;
				while( flag )
				{				
					jQuery("#LossGrid").jqGrid('setCell',ID,"GroupField","",{'background-color':'#'+color});
					ID= allRowsId[++i]			;
					 nextRowData = jQuery("#LossGrid").jqGrid('getRowData',ID);
					 if( nextRowData.GroupF.trim() != "S")
						 flag =false;							 	
				}	
			}
			color=parseInt(nextRowData.Group).toString(16);
			jQuery("#LossGrid").jqGrid('setCell',ID,"GroupField","",{'background-color':'#'+color});
			i++;
		}		
}
function subGrid_errCallBack(result)
{
	var id = result.gridRowId;
	 alert("No sub Loss Found");
	 jQuery("#LossGrid").jqGrid('setCell',id,"GroupField",jQuery("#LossGrid").jqGrid('getCell',id,3).replace('(+)','*'));
	 
}
function subGrid_successCallBack(result)
{	
	 var gridRowId = result.gridRowId;	 
	 if(result.records=="0")
	 {	
		 var id = result.gridRowId;
		 alert("No sub Loss Found");
		 jQuery("#LossGrid").jqGrid('setCell',id,"GroupField",jQuery("#LossGrid").jqGrid('getCell',id,3).replace('(+)','*'));
	}
	else{		
		var val = result.rows[0].cell;		
		var rowId = result.rowId;
		var cm = jQuery("#LossGrid").jqGrid("getGridParam", "colModel");	
		var toaddRow;	
		for(var i=0; i<result.rows.length; i++)
		{			
			var rowObject = jQuery("#LossGrid").getRowData(gridRowId);
			for(var j=0;j<val.length;j++)
			{				 
				rowObject[cm[j].name] = result.rows[i].cell[j];
			}				 
			jQuery("#LossGrid").jqGrid('addRowData',result.rows[i].cell[0],rowObject,'after',gridRowId);
			jQuery("#" + result.rows[i].cell[0]).find("td").css("color", "green");
			jQuery("#" + result.rows[i].cell[0]).find("td").css("background-color", "#BACEDB");	
		}			  
		jQuery("#LossGrid").jqGrid('setCell',gridRowId,"GroupField",jQuery("#LossGrid").jqGrid('getCell',gridRowId,3).replace('+','-'));
	}	
}
function getRelatedFilterValues()
{
var filterStr="";		
var cboGrpSelectBox = jQuery("#cboGrpSelectBox").val();
filterStr += "&cboGrpSelectBox="+cboGrpSelectBox;
filterStr += "&chkPercnt="+getChkBoxVal('chkPercnt');
filterStr += "&chkLegend="+getChkBoxVal('chkLegend');
filterStr += "&chksubLos="+getChkBoxVal('chksubLos');
filterStr += "&chkZeroLoss="+getChkBoxVal('chkZeroLoss');
return filterStr;
}

function getChkBoxVal(Id) {		
if(jQuery('#'+Id).is(':checked') == true)  		
	return 1;
else
	return 0;
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#dtefromDate").datebox('disable');
	jQuery("#dtetoDate").datebox('disable');
}

</script>
<form name="frmEdtreport" id="frmEdtreport" >

<div  style="height:10px;padding-top:10;width:1200px;">
<div class="floatleft" style="padding-left:40px;">
<input id="chkboxvai" name="chkboxvai" type="checkbox" /> <label>View Results</label>
<span>
<input id="chkboxviewSubloss" name="chkboxviewSubloss" type="checkbox" disabled="disabled"/> <label>View Subloss</label>
</span>
</div>
<div class="floatright" style="padding-right:5px;"><input id="btnGraph" class="easyui-button"  type="button" value="Pie-Graph"/> </div>
<div class="floatright" style="padding-right:5px;"><input id="btnoee" class="easyui-button"  type="button" value="OEE Analysis"/> </div>
<div class="floatright" style="padding-right:5px;"><input id="btnloss" class="easyui-button"  type="button" value="Loss Trend & Pareto"/></div>
<div class="floatright" style="padding-right:5px;"><input id="btnmon" class="easyui-button"  type="button" value="Date/Month Wise Report"/></div>
<div class="floatright" style="padding-right:5px;"><input id="btnpcs" class="easyui-button"  type="button" value="PCS Report"/>
</div>
 </div>
 <div id="wrapperRpt" style="top: 0px;">

<div id="divGraphContainer" ></div>	
<label id="lossnote" class="notesloss" style="font-weight: bold;width:470px;padding-left:20px;padding-right:20px;display: none;">${requestScope.drilldownMsg}</label>
<label id="lossgraph" class="notesloss" style="font-weight: bold;width:470px;padding-left:20px;padding-right:20px;display: none;">${requestScope.lossstrgraph}</label>
<label id="subloss" class="notesloss" style="font-weight: bold;width:470px;padding-left:20px;padding-right:20px;display: none;">${requestScope.losssub}</label>
<div id ='graphOption' style="display: none">
<div id="divPillar"  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:265px;padding-left:5px;">
                  					  <input id="chkPercnt" name ="chkBox" type="checkbox" value="Y"/> <label> With Percentage</label><br/><br/>
<!--                  					  <span>  <input id="chkLegend" name = "chkLegend" type="checkbox" value="Y"/> <label>With Legends</label></span><br/><br/>-->
                  					  <span>  <input id="chksubLos" name="chkBox" type="checkbox"  value="Y"/> <label>Include Sub Losses</label></span><br/><br/>
                  					  <span>  <input id="chkZeroLoss" name= "chkBox" type="checkbox" checked="checked" value="Y"/> <label>Include Zero Losses</label></span> 
									 <div  style="padding-left:70px;"><input id="btnGraphOk" name="btnGraphOk" class="easyui-button"  type="button" value="Ok"/> 
								 <span class="floatR3  " style= "padding-left:10px" ><input id="btnCancel" name="btnCancel" class="easyui-button"  type="button" value="Cancel"/> 
								 </span></div> </div></div>
<table id="LossGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hiddenkey"  />
<input type="hidden" id="hdncmpid"  />
<input type="hidden" id="hdnselid"  />
<input type="hidden" id="hdnlcnid"  />
<input type="hidden" id="hdnfctid" />
<input type="hidden" id="hdnlinid" />
<input type="hidden" id="hdncelid" />
<input type="hidden" id="hdnmchid" />
<input type="hidden" id="hiddenOcc" value=""/>
<input type="hidden" id="hiddenTim" value=""/>
<input type="hidden" id="hiddenMngtLossArr" value=""/>
<input type="hidden" id="hiddenShutdnLossArr" value=""/>
<input type="hidden" id="hiddenTolCngLossArr" value=""/>
<input type="hidden" id="hiddenEFLossArr" value=""/>
<input type="hidden" id="hiddenChkVal" value=""/>
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
<input type="hidden" id="hiddenBack" value="Y"/>
<input type="hidden" id="hiddencircle" value="" />
<input type="hidden" id="hiddenLStfiRemoveBlank" value="" />
<input type="hidden" id="hdnParentId" value="" />
<input type="hidden" id="hdnPrevParentId" value="" />
<input type="hidden" id="hdnPrevParent" value="" />
<input type="hidden" id="hdnFnlnKeyid" />
<input type="hidden" id="hdnElementVal" />
</div> 
</form>
	