<script>
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	//viewGrid(url,"?q=2");
	setLoadFormCallBackFrmId("frmabnstratification");
	invokeAfterLoadFormCallBack();
	jQuery('#btnGraph').click(function(){
		var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
		var url = "chartAbnStratificationRptImpact.abnStrRpt";
	  showGraphData(url);	
	});
	
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType+"&firstClick=N";	
		processGridnew(url,filterString,"list","pager","","","","abnStrComplete");
		return true;
	}
	return false;
}
function abnStrComplete()
{
	var gridId = "list";
	
	 var row = jQuery("#list").jqGrid('getDataIDs');
	var k = 0;
	var trs = document.getElementsByTagName("tr");
	//hideJqGridRow('list', 'jqgridheaderrow1');
	var gridId = 'list';
	var rowId= 'jqgridheaderrow1';
	jQuery('#list  tr[class=jqgridheaderrow1]').css({display:"none"});
	for(var i=0;i<trs.length;i++)
	{
	 if(trs[i].id.substring(0,4) == 'list')
		   k++;
	}
	for(var j=0;j<row.length;j++)
	{
		if(row[j] == '' || row[j] == ' '|| row[j] == null)
			jQuery("#list").jqGrid('setCell',row[j],"MachineNo","Total",{'color':'#d9151e','font-weight':'bold'});
	}
	
	jQuery('#listghead_'+(k-1)).css('display','none');
		

	
}
function validateFilterSelection(filterString){
		return  true;
}

function frmFilter_enableDisableSuccessCallBack()
{
	var url = jQuery('#hiddenUrl').val();	
	enableDisableDatenMonthFilter();
	if(url=="AbnStratificationRptType_input.abnStrRpt")
	{
		jQuery("#cmbAbnmTypeid").combobox("setValue","ABT0007");
		setTimeout(function() {readOnlyFields('cmbAbnmTypeid');},100);						
		
		reloadCombo("frmAbnormalityRelated","cmbAbnmTagclassid","Combo_TagClass.abnForm?q=2&frmType=SHE");
		setTimeout(function() {reloadCombo("frmAbnormalityRelated","cmbAbnmCategoryid","Combo_Category.abnForm?q=2&frmType=SHE");},1200);
		reloadCombo("frmAbnormalityRelated","cmbAbnmImpactid","Combo_Impact.abnForm?q=2&frmType=SHE");
	}
	jQuery('#cboRelatedTo option[value=MCH]').attr('selected', 'selected');
}

function frmabnstratification_afterLoadCallBack(){
	
 toggleCommonFilter();
}

</script>
<form>

<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
 <div id="wrapperRpt" style="max-width: 1210px;">
<div  style="padding-right:20px;margin-top:0px; width:500px;height:50px">
	<label>Percentage Wise</label>
	<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkPercentage" name="chkPercentage" type="checkbox" /><label> for Graph</label></span>
	<label>Include Zero</label>
	<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkZero" name="chkZero" type="checkbox" /><label> for Graph</label></span>
	<span style="position:relative;top:5px\9;"><input id="btnGraph" class="easyui-button" style="padding-top:0;" type="button" value="Pie-Graph"/> </span>
</div><br><br>
<div id="divGraphContainer" ></div>	
<div style="margin-top:-54px;margin-top:-50px\9;">
	<table id="list" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div>
</div>
</div>
</form>

