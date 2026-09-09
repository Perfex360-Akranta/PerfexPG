<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	//var FromDate="01-Nov-2008";
	//var ToDate="30-Nov-2011";
var url = jQuery('#hiddenUrl').val();


var dataString ="";
//dataString += "?dtFromDate="+FromDate;
	//dataString += "&dtToDate="+ToDate;
	var url = jQuery('#hiddenUrl').val();
	viewGrid(url,"&s=1");
	
});

function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{	
		
		processGridnew(url,filterString,"list","pager","","","","");
		return true;
	}
	return false;
}
function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentMonth('dtetoMonth');
	monthDiff(5,"dtefromMonth");
	enableFields('chkMonthwise');
	enableFields('chkDatewise');
	enableFields('dtetoDate');
	enableFields('dtefromDate');
	//disableField('frmFilter','dtetoMonth');
	
	/*disableField('frmAbnormalityRelated', 'chkdectbychkbox');
	disableField('frmAbnormalityRelated', 'chkdectdtchkbox');
	disableField('frmAbnormalityRelated', 'chkcauschkbox');
	disableField('frmAbnormalityRelated', 'chkabncatchkbox');
	disableField('frmAbnormalityRelated', 'chkabnimpchkbox');
	disableField('frmAbnormalityRelated', 'chkallchkbox');
	
	jQuery("#chkDatewise").attr('checked',false);
	jQuery("#chkMonthwise").attr('checked',true);
	clearField('dtefromDate');
	clearField('dtetoDate');
	disableField('frmFilter', 'dtefromDate');
	disableField('frmFilter', 'dtetoDate');
	
	disableField('frmAbnormalityRelated', 'cboabnstatus');*/
			
	
}

function abnStrComplete()
{
	 var row = jQuery("#list").jqGrid('getDataIDs');
	var k = 0;
	var trs = document.getElementsByTagName("tr");
	for(var i=0;i<trs.length;i++)
	{
	 if(trs[i].id.substring(0,4) == 'list')
		   k++;
	}
	for(var j=0;j<row.length;j++)
	{
		if(row[j] == '' || row[j] == ' '|| row[j] == null)
			jQuery("#list").jqGrid('setCell',row[j],"MachineNo_1","Total",{'color':'#d9151e','font-weight':'bold'});
	}
	
	jQuery('#listghead_'+(k-1)).css('display','none');
		

	setTotalRowCss('list');
}
function validateFilterSelection(filterString){
		return  true;
}


</script>
<form>
 <div id="wrapperRpt">
<div style="margin-top: -28px">

	<table id="list" style="width:100%">
	<tr><td/></tr></table>
	<div id="pager"></div></div>
</div>
</form>
<!--<script>-->
<!--jQuery(document).ready(function(){	-->
<!--	-->
<!--	-->
<!---->
<!--	-->
<!--var FromDate="01-Nov-2008";-->
<!--var ToDate="30-Nov-2011";-->
<!--var url = jQuery('#hiddenUrl').val();-->
<!--alert(url);-->
<!--var dataString ="";-->
<!--dataString += "?dtFromDate="+FromDate;-->
<!--dataString += "&dtToDate="+ToDate;-->
<!---->
<!--	viewGrid(url,dataString);-->
<!--});-->
<!---->
<!---->
<!--function viewGrid(url,filterString)-->
<!--{-->
<!--	if( validateFilterSelection(filterString))-->
<!--	{-->
<!--		var tableCaption = "Customer Complaint Statement";-->
<!--		processGridnew(url,filterString,"list","pager",tableCaption);-->
<!--		//jQuery("#list").setGridParam({url:"EqpReport_view.eqm"+dataString,dataType: "json" }).trigger('reloadGrid');-->
<!--		-->
<!--	}	-->
<!--}-->
<!---->
<!--function validateFilterSelection(filterString){-->
<!--	return  true;-->
<!--}-->
<!--</script>-->
<!---->
<!--<table id="list" style="width:100%">-->
<!--	<tr><td/></tr></table>-->
<!---->
<!--			<div id="pager"></div>-->