<script type="text/javascript">
jQuery(document).ready(function(){
setLoadFormCallBackFrmId("frmabncumulative");
invokeAfterLoadFormCallBack();
	var actionPart = jQuery('#hiddenUrl').val();		
	//viewGrid(actionPart,"?q=1&firstClick=Y&dtFromMonth=Apr-2014&dtToMonth=Aug-2014");
	//viewGrid(actionPart,"&q=1&firstClick=Y");
	jQuery('#btnLineGraph').click(function(){
		var rowid = jQuery("#abnCumulativeGrid").jqGrid('getGridParam','selrow');
		
		/*if(rowid !=null){
			if(checkForZeroes("abnCumulativeGrid",rowid,3)){	*/	
				var url = "chart.abnCumulative?chType=spline";
				showGraphData(url);
		  /* }
			else
				alert("To Select Valid Row");*/
		//}
	});
	jQuery('#btnBarGraph').click(function(){
		var rowid = jQuery("#abnCumulativeGrid").jqGrid('getGridParam','selrow');
		//var url="chartbar.abnCumulative";
		var url="chart.abnCumulative?chType=column";
		showGraphData(url);
	});
	
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType;		
		filterString += '&drillFlag=f';
		//alert(filterString);
		var tableCaption = "DrillDown  Report";
		processGridnew(url,filterString,"abnCumulativeGrid","pager",tableCaption,"doubleClickGrid","","cumulativeGrid");		
		return true;
	}
	
	return false;	
}

function frmabncumulative_afterLoadCallBack(){
toggleCommonFilter();
}

function validateFilterSelection(filterString){
	
	if(filterString=="&q=1&firstClick=Y")
		return true;
	else{ 
	if(!filterMonthnDateDifference(filterString,40,24))
		return false;
	}
	if((filterString.length > 0   &&  jQuery('#chkDatewise').is(':checked') == false) && (filterString.length > 0   && jQuery('#chkMonthwise').is(':checked') == false)){
		alert("Select either Date or Month ");
		return false;
	}	
	return  true;
}


function abnCumulativeGrid_onProcessGridBack(){
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	setDrillProcesGridBack("abnCumulativeGrid","keyid",keyfieldData);
	jQuery("#hdnFnlnKeyid").val("");
}	

function doubleClickGrid(id){ 
	var keyfieldData=jQuery("#hdnFnlnKeyid").val();
	keyfieldData=setDrillDoubleClick("abnCumulativeGrid","keyid",id,keyfieldData);
	jQuery("#hdnFnlnKeyid").val(keyfieldData);
}
function cumulativeGrid()
{ 

	setDrillDownHeader("CH0-0","abnCumulativeGrid","keyid");
	//setTotalRowCss('abnCumulativeGrid');
	var row = jQuery("#abnCumulativeGrid").jqGrid('getDataIDs');
	//var cm = jQuery("#abnCumulativeGrid").jqGrid("getGridParam", "colModel");	
	
	
	//alert("cm-- :" +	cm[0].name +cm[1].name +cm[2].name);
	
	//alert(val);
	var rowno=row.length;
	//rowno=rowno+10;
//	alert(rowno % 100);
	if(rowno < 100 ){
		setTotalRowCss('abnCumulativeGrid');
	}else{
		if(rowno % 100 != 0){
			setTotalRowCss('abnCumulativeGrid');
		}
	}
	
/*	
	if(row.length>=0)
		{
			var parentId =  jQuery("#abnCumulativeGrid").jqGrid('getCell', row[0], 'keyid');
			if(parentId.substr(0,3) != 'CMP'){
				hideShowBack(true);
				
			}
		}
	for(var j=1;j<=row.length;j++)
	{
		var identifiedTotal = 0;
		var removedTotal = 0;
		for(var i=0;i<cm.length;i+=2)
		{
			if(i != 0  && i != cm.length-2)
			{
				//alert(identifiedTotal);
				var colMod =cm[i].name;
				var x = jQuery("#abnCumulativeGrid").getCell(j, cm[i].name);	
				if(j != row.length)						
				 identifiedTotal = parseInt(identifiedTotal) + parseInt(x);
				else
				 identifiedTotal = jQuery("#abnCumulativeGrid").getCell(j, cm[i].name); 
			}			
		}
		for(var i=1;i<cm.length;i+=2)
		{
			if(i != 1  && i != cm.length-1)
			{
				var y = jQuery("#abnCumulativeGrid").getCell(j, cm[i].name);
				//alert("Y" + y);
				if(j != row.length)						
				 removedTotal = parseInt(removedTotal) + parseInt(y);
				else
				 removedTotal = jQuery("#abnCumulativeGrid").getCell(j, cm[i].name);
			}			
		}
		jQuery("#abnCumulativeGrid").jqGrid('setRowData',j,{totalIden: identifiedTotal });
		jQuery("#abnCumulativeGrid").jqGrid('setRowData',j,{totalRem: removedTotal });
	}
	setTotalRowCss('abnCumulativeGrid');
	jQuery("#" + row[row.length-2]).find("td").addClass('cumulativeRow');	
	*/
}



function frmFilter_enableDisableSuccessCallBack()
{
	//fillWithCurrentDate("dtefromDate");			
	//enableFields('chkDatewise');
	//readOnlyFields('chkDatewise');
	var url = jQuery('#hiddenUrl').val();
	enableDisableDatenMonthFilter();
	//jQuery("#chkDatewise").attr('checked',false);
	//jQuery("#chkMonthwise").attr('checked',true);		
	if(url=="HSE_AbnCumulative_input.abnCumulative")
	{
		//jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
		setComboValueSilent("cmbAbnmTypeid", "ABT0007");
		readOnlyFields('cmbAbnmTypeid');				
	
		reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
		setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
		reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
	}
}


</script>

<form>
<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
<div id="wrapperRpt" style="max-width: 1210px;">
<div  style="padding-right:20px;">
<input id="btnLineGraph" class="easyui-button" style="width:78px; height:23px;"  type="button" value="Line Graph"/> 
<input id="btnBarGraph" class="easyui-button" style="width:78px;height:23px;" type="button" value="Bar Graph"/>
<input type="button" id="bdbtn" onclick="" class="easyui-button" value="View BD" style="display: none;"/>
</div>
<div id="divGraphContainer" ></div>	
<div class="clear"></div>
<table id="abnCumulativeGrid" ></table>
<div id="pager"></div>
<input type="hidden" id="hiddenStr" value="sdsd" />
<input type="hidden" id="hdnFnlnKeyid" />
</div>
<!-- <div  style=margin-left:133px;margin-top:-463px;"><input id="btnBarGraph" class="easyui-button" style="width:78px;height:23px;" type="button" value="Bar Graph"/> 
</div>  -->
</form>
	



