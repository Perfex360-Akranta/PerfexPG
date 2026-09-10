<script type="text/javascript">
jQuery(document).ready(function(){
	var url = jQuery('#hiddenUrl').val();
	var filterstring = url.substring(url.indexOf('&filterString'));
	//var filterstring   =getFilterValue(filterString ,"filterstring") ;
	//processGridnew("updatecancelgrid_getCol.mpc",'q=2&filterString='+filterstring,"grdUpdtWo","upcanWoPager","","updtcancelGrid_dblclick");
processGridnew("updatecancelgrid_input.mpc",filterstring.substring(filterstring.indexOf("?")+1),"grdUpdtWo","upcanWoPager","","updtcancelGrid_dblclick");
	jQuery('#btnNewFrm').click(function(){
		navigateToNextForm("updateWogrid_input.mpc?q=2","");
	});
});
function updtcancelGrid_dblclick(id){
	var url = jQuery('#hiddenUrl').val();
	var filterstring = url.substring(url.indexOf('&filterString'));
	//var filterstring = getFilterValue(url ,"filterstring") ;
	var rowData = jQuery("#grdUpdtWo").jqGrid('getRowData',id);
	var workorderno = rowData.workorderno;
	var prepByid = rowData.preparedbyId;
	filterstring += "&allotedto="+rowData.keyid;
	filterstring += "&workorderno="+workorderno;
	filterstring += "&prepByid="+prepByid ;
	navigateToNextForm("updtCancelGridsel_input.mpc?q=2"+filterstring,"");
}
</script>
<div id="wrapperRpt" style="">
		<div>
			<label class="notes">Double click on WorkOrders to Cancel</label>
		</div>
		<div>
		<table id="grdUpdtWo"></table>
		<div id="upcanWoPager"></div>
	</div>
</div>