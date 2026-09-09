<script type="text/javascript">
jQuery(document).ready(function(){	
	setLoadFormCallBackFrmId('frmpcsreport');
	invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();
	
	var rowid =jQuery('#hiddenIds').val();
	
	/*var sectId =jQuery('#hiddenfact').val();
	var cellId =jQuery('#hiddenfact').val();
	var mchmId =jQuery('#hiddenfact').val();*/
	
	//window.setInterval(yourfunction, 10000);

	var datStr ="?q=1";
	if(rowid!=null && rowid!=""&&rowid!=" "){
		 
	if(rowid.substring(0,3)=='FCT')
		datStr+="&cmbFactid="+rowid;
	if(rowid.substring(0,3)=='LIN')
		datStr+="&cmbSectid="+rowid;
	if(rowid.substring(0,3)=='CEL')
		datStr+="&cmbCellid="+rowid;
	if(rowid.substring(0,3)=='MCH')
		datStr+="&cmbMchid="+rowid;
	}	
	viewGrid("ProductionLoss_input.prdloss",datStr);
	
	jQuery("#btnParetoGraph").attr("disabled", true);
	jQuery("#btnParetoGraph").removeClass("easyui-button");
	jQuery("#btnParetoGraph").addClass("disabledButton");
	
	jQuery("#chkboxvai").attr("checked",false);
	
	//toggleCommonFilter(false);
	jQuery("#hiddenOcc").val("Y");
	jQuery("#hiddenTim").val("Y");
	setTimeout(function() { hideAddInfo();},1250);
	jQuery('#btnParetoGraph').click(function(){
		var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');
		var selectmonth = getFieldValue('hiddenselmonth');
		var dataString ="";
		dataString += "PARAMCODE="+rowid+"&selectmonth="+selectmonth;		
		var url = "chartPareto.prdloss?"+dataString;		
		showGraphData(url);
		
		/*var month=jQuery("#hiddenkey").val();
		if(jQuery('#chkboxoccurence').is(':checked') == true){
			occ=1;
		}else{
			occ=0;
		}
		
		if(month.substring(0,3) != "AVG" && month.substring(0,3) != "YTD")
			{
		if(month.trim() != "")
		{			
			var dataString = "";
			dataString += "month="+month+"&occ="+occ;
			var url = "chartMonthWise.prdloss?"+dataString;
			showGraphData(url);
		}
		else
		{
			var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');			
			var rowData = jQuery("#pcs").jqGrid('getRowData',rowid);
		
			jQuery("#pcs").jqgrid({				
				onCellSelect: function(rowid, index, contents, event) {
				
				}
			});
			
			var dataString ="";
			dataString += "?PARAMCODE="+rowid;		
			var url = "chartPareto.prdloss"+dataString;		
			
			//showGraphData(url);
	
		}
			}
		jQuery("#hiddenkey").val("");	*/
		});
	
	jQuery('#btnGraph').click(function()
	{
		
		var month=jQuery("#hiddenkey").val();
		var occ=jQuery("#hiddenOcc").val();
		var tim=jQuery("#hiddenTim").val();		
		
		/*if(month.trim() != "")
		{			
			var dataString = "";
			dataString += "month="+month;
		//	var url = "chartMonth.prdloss?"+dataString;
			//showGraphData(url);
		}*/
		
		{			
			var val = jQuery("#hiddenStr").val();	
			var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');	
		    var subLosColor = jQuery("#pcs tr[id="+rowid+"]").find("td").css("color");	
			/*if(subLosColor == "rgb(0, 128, 0)" ){
				alert('Graph Cannot be generated');
				return false;
			}*/
			if(val=="1" || rowid==null)	
			{	jQuery("#hiddenStr").val("");
				alert("Click On Data Row To Generate Graph");
				return false;
			}			
			else
			{						
				var rowid = jQuery("#pcs").jqGrid('getGridParam','selrow');	
				
				var rowData = jQuery("#pcs").jqGrid('getRowData',rowid);	
				
				if(rowData["2"]=="S")
					{
					var dataString ="";
					dataString += "PARAMCODE1="+rowid;			
					var url = "SubLossChart.prdloss?"+dataString;// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);				
					}
				else{
					var dataString ="";
					dataString += "PARAMCODE1="+rowid;			
					var url = "chart.prdloss?"+dataString;// + (rowid != null && rowid != 'undefined' ?  getParamName(rowid)+"="+rowid:"chType=p");
					showGraphData(url);
				}				
			}
			jQuery("#hiddenStr").val("");
		}		
	});	
	jQuery('#chkboxvwSubloss').click(function(){
		if(jQuery('#chkboxvwSubloss').is(':checked')==true){
			jQuery('#pcs td:contains("(+)")').trigger('click');
		}
		else{
			jQuery('#pcs td:contains("(-)")').trigger('click');
		}
	});
	jQuery('#chkboxvai').click(function(){
		if(jQuery('#chkboxvai').is(':checked')==true)
		{	
			viewAddInfo();	
		}
		else 
			{
			hideAddInfo();		
			}
	});
});

function viewGrid(url,filterString)
{
	if(filterString == "q=1" )
		return true;
	if( validateFilterSelection(filterString))
	{
		
		filterString +="&chkRemoveBlank=Y";
		
		jQuery('#chkboxvai').attr('checked', false);
		var tableCaption = "PCS Report";	
		//void [CommonFunctions.js] processGridnew(any url, any filterString, any tableId, any pagerId, any 
				// tableCaption, any doubleClickFunction, any tableHeaderSpanCallback, any ongridcompletecallback, 
				// any selectRowFunction, any filterNeed)
		processGridnew(url,filterString,"pcs","pager",tableCaption,"","","pcs_LoadComplete");
		return true;
	}	
	return false;
}

function addSubLoss(rowid)
{
	
	var rowData = jQuery("#pcs").jqGrid('getRowData',rowid);
	var keyId = rowData.LOSSID1;	
	//var keyId = rowData[1];
	var sectid = getFieldValue('section');
	var fctid = getFieldValue('factory');	

		if(sectid.trim()=="" || sectid=="undefined" ){
			//alert("Sub Loss can be Viewed From Section Level Only");
			return false;
		}
		
		if(sectid != null ){
			var filterString = "keyID="+keyId;
				filterString +="&sectid="+sectid;
				filterString +="&fctid="+fctid;
				filterString +="&rowId="+rowid;
			
			var url = "productionLossSubGrid_getData.prdloss";
			jQuery("#hiddenChkVal").val("1");
			//jQuery("#"+rowid+1).addClass("tpm-loading");tpm-loadingGridRow
			
			//jQuery("#pcs").jqGrid('addRowData',rowid+1,"<tr> </tr>");	
			//$('#myTable').append('<tr><td>my data</td><td>more data</td></tr>');
			jQuery("#"+rowid+"_load").remove();
			jQuery("#pcs tr[id="+rowid+"]").after('<tr id="'+rowid+'_load" class="loadingGridRow"><td></td></tr>');
			
			processAjaxCalls(url,filterString,"subGrid_successCallBack","subGrid_errCallBack","",keyId);
			return false;
		}
}


function pcs_LoadComplete_afterLoad()
{		
	
	hideAddInfo();

	//fillFunLocHeader();
	fillColor();
	jQuery('#pcs td:contains("(-)")').bind("click",function(){
		var id = jQuery(this).parent().attr("id");
		var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');		
		for(var i=0; i<allRowsId.length;i++)
		{				
			if(allRowsId[i] == id )
			{						
				for(var j=i+1; i<allRowsId.length;j++){
					var rowData = jQuery("#pcs").jqGrid('getRowData',allRowsId[j]);							
					if(rowData["2"] == "S"){//s--Sub Loss
						hideJqGridRow("pcs", allRowsId[j]);	
						jQuery("#pcs").jqGrid('setCell',id,"Loss/Month4",jQuery("#pcs").jqGrid('getCell',id,4).replace('-','+'));
						
					}
					else
						return;
				}	
			}
		}	
	});
	
	jQuery("#pcs").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) { 
		
			var celno = cellidx-4 ;
			
			var dateval = jQuery("#CH0-"+celno).html();
				
			setFieldValue('hiddenselmonth',dateval);
		}
			
		});
	
	jQuery('#pcs td:contains("(+)")').bind("click",function(){
		
		var hideVal = this.title;
	
		 if(hideVal.substring(1,2)=="+"){			
			 var rowid = jQuery(this).parent().attr("id");			
			var nextTr = jQuery("#pcs tr[id="+rowid+"]").next('tr').attr("id");
			var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);
		
			if(nextRowData[2]=="S")	{
			while(nextRowData[2]=="S")
			{					
				jQuery("#pcs tr[id="+nextTr +"]").show();				
				var nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				
				var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);					
			}			
			jQuery("#pcs").jqGrid('setCell',rowid,"Loss/Month4",jQuery("#pcs").jqGrid('getCell',rowid,4).replace('+','-'));
			}			
			else{
			
				addSubLoss(rowid);		
		}}
		else if(hideVal.substring(1,2)=="-")
		{	
			var id = jQuery(this).parent().attr("id");
			var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');		
			for(var i=0; i<allRowsId.length;i++)
			{				
				if(allRowsId[i] == id )
				{						
					for(var j=i+1; i<allRowsId.length;j++){
						var rowData = jQuery("#pcs").jqGrid('getRowData',allRowsId[j]);							
						if(rowData["2"] == "S"){//s--Sub Loss
							hideJqGridRow("pcs", allRowsId[j]);	
							jQuery("#pcs").jqGrid('setCell',id,"Loss/Month4",jQuery("#pcs").jqGrid('getCell',id,4).replace('-','+'));
							
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
	
	
function pcs_LoadComplete()
{	


	jQuery("#pcs").jqGrid( 'setGridParam',{onCellSelect:function(rowid,iCol,cellcontent,e){
		
		 jQuery("#hiddeniCol").val(cellcontent);
		
		  breakdonView(rowid,iCol,cellcontent);

		}	
	});
	
	
	
	jQuery("#gbox_pcs").children().removeClass("ui-jqgrid-sortable");
	
	
	jQuery("#pcs").jqgrid({				
		onCellSelect: function(rowid, index, contents, event) {
			
		}
	});

jQuery("tr.jqgridheaderrow1").children().bind("click", function()
{
	var month=jQuery("#hiddenkey").val();
	var occ=jQuery("#hiddenOcc").val();
	var tim=jQuery("#hiddenTim").val();	
	jQuery("#hiddenStr").val("");
	
	if(occ=="Y" && tim=="Y")	
		jQuery("#hiddenStr").val("1");
	else if(occ=="Y" && tim=="N" || occ=="N" && tim=="Y")
	{	
		var cell = jQuery(this);
		var index = cell.parent('tr').children().index(cell);
		index = parseInt(index) +4;	
		
		var colm = jQuery("#pcs").jqGrid ('getGridParam', 'colModel');	
		selId = colm[index].name;		
		
		jQuery("#hiddenkey").val(selId);
		
	}	
	else
		alert("select row data");
});	


}

function breakdonView(rowid,iCol,cellcontent){

 	var colm = jQuery("#pcs").jqGrid ('getGridParam', 'colModel');
	selId = colm[iCol].name;	
	
 	jQuery("#hiddenkey").val(selId);
	 
}




function subGrid_errCallBack(result)
{
	
	var id = result.gridRowId;
	// alert("No sub Loss Found");
	 jQuery("#"+gridRowId+"_load").remove();
	 jQuery("#pcs").jqGrid('setCell',id,"Loss/Month4",jQuery("#pcs").jqGrid('getCell',id,5).replace('(+)','*'));
	 
}

function subGrid_successCallBack(result)
{	

	var gridRowId = result.gridRowId;
	
	jQuery("#"+gridRowId+"_load").remove();

	var chkVal = jQuery("#hiddenChkVal").val();

	 if(result.exception == true || result.records=="0" || result.rows.length=="0")
	 {	
	 var id = result.gridRowId;
	// alert("No sub Loss Found");
	
	 }
		
	
	else{
		
		jQuery("#hiddenChkVal").val("0");
		
		var val = result.rows[0].cell;
	
		var rowId = result.rowId;		
		var addRowId = new Array();	
		
		var cm = jQuery("#pcs").jqGrid("getGridParam", "colModel");
		
		var toaddRow;
		for(var i=0; i<result.rows.length; i++)
		{			
			var rowObject = jQuery("#pcs").getRowData(gridRowId);		
			
			for(var j=0;j<val.length;j++)
			{				
				rowObject[ cm[j].name ]= result.rows[i].cell[j];				
			}		
			
			addRowId.push(result.rows[i].cell[4]);			
			
			jQuery("#pcs").jqGrid('addRowData',result.rows[i].cell[0],rowObject,'after',gridRowId);
			jQuery("#" + result.rows[i].cell[0]).find("td").css("color", "green");
			jQuery("#" + result.rows[i].cell[0]).find("td").css("background-color", "#BACEDB");
		}		
		
			hiddenMngtLossArr = new Array();
			hiddenMngtLossArr.push(addRowId);	
			
			
			jQuery("#pcs").jqGrid('setCell',gridRowId,"Loss/Month4",jQuery("#pcs").jqGrid('getCell',gridRowId,4).replace('+','-'));
			
	}
	
	
}

function validateFilterSelection(filterString)
{
	
	
	if(getFilterValue(filterString, "chkboxoccurence")=="Y" && getFilterValue(filterString, "chkboxtime")=="N")
	{	jQuery("#btnParetoGraph").attr("disabled", false);
		jQuery("#btnParetoGraph").addClass("easyui-button");
		jQuery("#hiddenOcc").val("Y");
		jQuery("#hiddenTim").val("N");
	}
	else if(getFilterValue(filterString, "chkboxoccurence")=="N" && getFilterValue(filterString, "chkboxtime")=="Y")
	{	jQuery("#btnParetoGraph").attr("disabled", false);
		jQuery("#btnParetoGraph").addClass("easyui-button");
		jQuery("#hiddenOcc").val("N");
		jQuery("#hiddenTim").val("Y");
	}
	
	else if(getFilterValue(filterString, "chkboxoccurence")=="Y" && getFilterValue(filterString, "chkboxtime")=="Y")
	{
		jQuery("#btnParetoGraph").attr("disabled", true);
		jQuery("#btnParetoGraph").removeClass("easyui-button");
		jQuery("#btnParetoGraph").addClass("disabledButton");
		jQuery("#hiddenOcc").val("Y");
		jQuery("#hiddenTim").val("Y");
		
	}	
	
	else if(filterString == "?q=1")
		return false;
	else
		{
		jQuery("#btnParetoGraph").attr("disabled", true);
		jQuery("#btnParetoGraph").removeClass("easyui-button");
		jQuery("#btnParetoGraph").addClass("disabledButton");
		jQuery("#hiddenOcc").val("N");
		jQuery("#hiddenTim").val("N");
		}		
	return true;
}	

function viewAddInfo()
{
	var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');
	
	for(var i=0;i<allRowsId.length;i++)
		{
		var ID=allRowsId[i];
		var nextTr = jQuery("#pcs tr[id="+ID+"]").next('tr').attr("id");
		var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);
		
		var color=parseInt(nextRowData[3]).toString(16);
		 
		if(nextRowData[2] == "A"){
			while(nextRowData[2]=="A")
			{		
				
				jQuery("#pcs tr[id="+nextTr +"]").show();	
				
				 jQuery("#" +nextTr).find("td").css("background-color", "#"+color);
				 nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);	
				
			}	
		}
	}
}

function hideAddInfo()
{
	var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');
	
	for(var i=0;i<allRowsId.length;i++)
		{
		var ID=allRowsId[i];
		
		var nextTr = jQuery("#pcs tr[id="+ID+"]").next('tr').attr("id");
		var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);
	
		if(nextRowData[2] == "A"){
			while(nextRowData[2]=="A")
			{			
				
				hideJqGridRow("pcs", nextTr);
				 nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				 
				 nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);					
			}	}
		}
}

function frmpcsreport_afterLoadCallBack(){ 
	var frmlossStrati =jQuery('#hiddenIds').val();
	//alert("! "+frmlossStrati.trim.length());
//	alert("!!!  "+frmlossStrati.trim().length);
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf('filter')<0)
	{
		if(frmlossStrati.trim().length<=0)
			toggleCommonFilter();
		else{
			var dataStr=actionPart.substring(29);
			viewGrid("ProductionLoss_input.prdloss",dataStr);
		}
	}
	else
	{
		jQuery('#hdnSetFilterValues').val('Y');
		var compId=jQuery("#hiddenCompId").val();
		var datStr="?q=2";
		datStr+="&cmbCompid="+compId;
		datStr+="&dtFromMonth="+jQuery("#hdnfromMonth").val();
		datStr+="&dtToMonth="+jQuery("#hdntoMonth").val();
		viewGrid("ProductionLoss_input.prdloss",datStr);
	}			
	
	}
function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentDate("dtefromDate");
	monthDiff(5,"dtefromMonth");
	readOnlyFields("dtefromDate");
	readOnlyFields("dtetoDate");

	var rowid =jQuery('#hiddenIds').val();
	 
	if(rowid!=null || rowid.trim()!=""){
		 
		var datStr ="";
		if(rowid.substring(0,3)=='FCT')
			datStr+="&factId="+rowid;
		if(rowid.substring(0,3)=='LIN')
			datStr+="&sectId="+rowid;
		if(rowid.substring(0,3)=='CEL')
			datStr+="&cellId="+rowid;
		if(rowid.substring(0,3)=='MCH')
			datStr+="&machId="+rowid;
	
	}
		loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter",datStr);
}

function fillFunLocHeader()
{	
	

	var factId = jQuery('#dispFunctionalLoc   a').attr('title');
	var factId1 = jQuery('#dispFunctionalLoc   a').next('title');
	

}
function fillColor()
{
	

	var allRowsId = jQuery('#pcs').jqGrid('getDataIDs');

	for(var i=0;i<allRowsId.length;i++)
		{
		var ID=allRowsId[i];
		
		var nextTr = jQuery("#pcs tr[id="+ID+"]").next('tr').attr("id");
		var nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);
		var color=parseInt(nextRowData[3]).toString(16);
		if(i==0){
			jQuery("#pcs").jqGrid('setCell',"DEFECTNREWORK","Loss/Month4","",{'background-color':'#D9D9FF'});
			}
		if(nextRowData[2] == "M" ){
			while(nextRowData[2]=="M")
			{				
				 jQuery("#pcs").jqGrid('setCell',nextTr,"Loss/Month4","",{'background-color':'#'+color});
				 nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);					
			}
		  }
		else{
			while(nextRowData[2]=="N")
			{				
				 jQuery("#pcs").jqGrid('setCell',nextTr,"Loss/Month4","",{'background-color':'#D9D9FF'});
				 nextTr = jQuery("#pcs tr[id="+nextTr+"]").next('tr').attr("id");				
				 nextRowData = jQuery("#pcs").jqGrid('getRowData',nextTr);					
			}
			}
			
		}

			
			
	function frmFilter_enableDisableSuccessCallBack()
	{
		fillWithCurrentDate("dtefromDate");			
		
		jQuery("#chkMonthwise").attr('checked',true);					
	}
	
}

</script>

<form name="frmpcsreport" id="frmpcsreport" >

<table style="margin-left:40px;margin-top:10px;" width="1024px">
<tr>
<td  width="645px" >

<div style="border:solid 1px #c1c1c1;padding:5px;padding:0px\9;">
	<label class="notes" style="font-weight: bold; padding-left:0px;"> 	 
	${requestScope.DoubleClickGraph}</label>
	<!--  <label class="notes" style="font-weight: bold; padding-right:20px;"> 	 
	${requestScope.ProdLoss}</label> -->
	</div>
	</td>
	<td width="199px">
	<div style=" display: none;" >
	<span >  <input id="chkboxvai" name="chkboxvai" type="checkbox"/> <label>View Results</label></span>
	<span>
	<input id="chkboxvwSubloss" name="chkboxvwSubloss" type="checkbox" /> <label>View Subloss</label>
	</span>
	</div>
	</td>
	<td width="171px">
	<div >
		<input id="btnGraph" class ="easyui-button"  type="button" value="Graph" style="height:22px;"/>   
		<input id="btnParetoGraph"  class ="easyui-button"  type="button" value="Pareto Graph" style="height:22px;width:90px;" / >   
	</div>
	</td>
</div>
</td>
</tr>
 </table>
<div id="wrapperRpt" style="margin-top:5px;">
	<div class="clear"></div>
<table id="pcs" ></table>
<div id="pager"></div>
<div id="divGraphContainer" ></div>	
<input type="hidden" id="hiddenStr"  />
<input type="hidden" id="hiddenkey"/>
<input type="hidden" id="hiddenOcc" value=""/>
<input type="hidden" id="hiddenTim" value=""/>
<input type="hidden" id="hiddenMngtLossArr" value=""/>
<input type="hidden" id="hiddenShutdnLossArr" value=""/>
<input type="hidden" id="hiddenTolCngLossArr" value=""/>
<input type="hidden" id="hiddenEFLossArr" value=""/>
<input type="hidden" id="hiddenChkVal" value=""/>
<input type="hidden" id="hiddenLoadVal" value=""/>
<input type="hidden" id="hiddenCompId" value="${requestScope.cmbCompid}"  />
<input type="hidden" id="hdnfromMonth" value="${requestScope.fromMonth}"  />
<input type="hidden" id="hdntoMonth" value="${requestScope.toMonth}"  />
<input type="hidden" id="hiddenIds" name="hiddenIds" value="${requestScope.hiddenIds}" />
<input type="hidden" id="hiddenselmonth" value=""/>

</div>

	
	
</form>
