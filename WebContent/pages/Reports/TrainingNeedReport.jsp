<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();	
	setLoadFormCallBackFrmId("frmTrneedReport");
	invokeAfterLoadFormCallBack();
	if(!(getFilterValue(filterString, "flid")==null || getFilterValue(filterString, "flid")==" " || getFilterValue(filterString, "flid")=="" ))
	{
	viewGrid(url,"?q=2");
	}
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{	
		 
		filterString += '&firstClick=Y';
		var tableCaption = "TrainingNeed Report";		
		processGridnew(url,filterString,"TrNeedReport","pager",tableCaption," "," ","load_Complete");
		return true;
	}
	return false;	
}
function validateFilterSelection(filterString){ 
	if(getFilterValue(filterString, "flid")==null || getFilterValue(filterString, "flid")==" " || getFilterValue(filterString, "flid")=="" ){
		alert("Select DMT");
		return false;
	}
     var actionPart = jQuery('#hiddenUrl').val();
	 if(actionPart.indexOf('filter')<0){
         if(jQuery('#chkDatewise').is(':checked') == false && jQuery('#chkMonthwise').is(':checked') == false ){
             alert("Select Date or Month ");
             return false;
           }
      }
	return  true;
}
function frmTrneedReport_afterLoadCallBack(){	
	var rowid =jQuery('#hiddenIds').val();
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart.indexOf("?")<=0){
			toggleCommonFilter();	
		}
}
function load_Complete(){
	/*setTotalRowCss('internal');
	var url = jQuery('#hiddenUrl').val();
	var rowIds = jQuery("#yield").getDataIDs();
	var parentId = jQuery("#yield").jqGrid('getCell', rowIds[4], 'KEYID');*/
}
function frmFilter_enableDisableSuccessCallBack(){
	enableDisableDatenMonthFilter();	
}
function load_Complete(){
	var row = jQuery("#TrNeedReport").jqGrid('getDataIDs');
	var cm = jQuery("#TrNeedReport").jqGrid("getGridParam", "colModel");	 
	 for(var i=0;i<row.length;i++){
		 for(var j=8;j<cm.length;j++){
			 var zeroVal = jQuery("#TrNeedReport").jqGrid('getCell',row[i],cm[j].name);	
			 if(zeroVal =='1'){
			  		jQuery("#TrNeedReport").jqGrid('setCell',row[i],cm[j].name,"&#10003;",{'color':'#4D27C5','font-weight':'bold','font-size':'18px'});
				  }
			 else{
				    jQuery("#TrNeedReport").jqGrid('setCell',row[i],cm[j].name," ",{'color':'#ff8040','font-weight':'bold','font-size':'15px'});
				 }
     	 }
	 }
}
</script>
<form id="frmTrneedReport" name="frmTrneedReport">
<div id="wrapperRpt" style="margin-top: 10px;">
	<div class="clear"></div>
	<div id="divGraphContainer" ></div><br>
	<table id="TrNeedReport" ></table>
	<div id="pager"></div>
	
</div>
</form>