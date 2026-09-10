<script type="text/javascript" >
jQuery(document).ready(function(){
	initialiseForm('frmMethodTask');
	jQuery('#submitForm').val('frmMethodTask');
	var machineId = jQuery("#hdnTaskListMachineid").val();
	var fromWo = jQuery("#hdnFromWoCom").val();
	processGridnew("methodTasklist_input.prv","machineId="+machineId+"&FromWO="+fromWo,"grdMethodTaskList","pgrMethodTaskList");
	jQuery('#btnAddRow').click(function(){
		var row  = jQuery("#grdMethodTaskList").jqGrid('getDataIDs');
		addNewRow(row);
	});
});
function addNewRow(row){
	var val =  jQuery("#hdnRowVal").val();
	var j = parseInt(val);
	if ( row == null || row == '' || parseInt(row) <= 0) { 
		var emptyItem =[{txtMtskKeyid:" ",txtMtskOperation:" ",txtMtskCheckingtool:" ",txtIdMinVal:" ",txtIdMaxVal:" ",txtIddirectVal:" ",txtIdCriteria:" ",txtMtskTypeofcheck:" ",txtMtskActualcondition:" "}];
	    jQuery("#grdMethodTaskList").jqGrid('addRowData',j, emptyItem[0]);
        var k =j+1;
        jQuery("#hdnRowVal").val(k);
	  }
   else{	              
       for(var i=0;i<row.length;i++)
	   lastRow = row[i];
       var emptyItem =[{txtMtskKeyid:" ",txtMtskOperation:" ",txtMtskCheckingtool:" ",txtIdMinVal:" ",txtIdMaxVal:" ",txtIddirectVal:" ",txtIdCriteria:" ",txtMtskTypeofcheck:" ",txtMtskActualcondition:" "}];
       jQuery("#grdMethodTaskList").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
     }
}
function frmMethodTask_beforeSubmit(){ 
	var gridData = "";
	if(  getGridSelectArray("grdMethodTaskList").trim().length>1  )
	  	  gridData ="&getMethodTaskList="+getGridSelectArray("grdMethodTaskList")+"&MachineId="+jQuery('#hdnTaskListMachineid').val();
	else
		alert("Select Task To Save");
	 
	return gridData;
}
function grdMethodTaskList_onBlur(result){
	 var gridId = result.gridId;
	 var textId = result.txtId;
	 var rowId  = result.rowId;
	 var txtPrefix = "_grdMethodTaskList_";
	 //alert(gridId +"  "+textId +"  "+rowId);
	 enableFields(txtPrefix+textId+rowId);
	 if("txtIdMinVal" == textId.trim()){
		 readOnlyFields("txtIddirectVal"+txtPrefix+rowId);
		 readOnlyFields("txtIdCriteria"+txtPrefix+rowId);
	 }else if("txtIdMaxVal" == textId.trim()){
		 readOnlyFields("txtIddirectVal"+txtPrefix+rowId);
		 readOnlyFields("txtIdCriteria"+txtPrefix+rowId);
	 }else if("txtIddirectVal" == textId.trim()){ 
		 readOnlyFields("txtIdMinVal"+txtPrefix+rowId);
		 readOnlyFields("txtIdMaxVal"+txtPrefix+rowId);
		 readOnlyFields("txtIdCriteria"+txtPrefix+rowId);
	 }else if("txtIdCriteria" == textId.trim()){
		 readOnlyFields("txtIdMinVal"+txtPrefix+rowId);
		 readOnlyFields("txtIdMaxVal"+txtPrefix+rowId);
		 readOnlyFields("txtIddirectVal"+txtPrefix+rowId);
	 }
}
</script>

<form id="frmMethodTask">
<div>
	<input type="button" class="easyui-button" value="Add Row" id="btnAddRow"/>
</div>
 <div class="easyui-paddingbfpx" style="margin-top:-5;">
     <table id="grdMethodTaskList" style="width:100%"><tr><td/></tr></table>
     <div id="pgrMethodTaskList">
     </div>
 </div>
 <input type="hidden" id="hdnTaskListMachineid" value="${requestScope.machineId}"/>
 <input type="hidden" id="hdnFromWoCom" value="${requestScope.fromWO}"/>
 <input type="hidden" id="hdnRowVal" value="1"/>
 <input type="hidden" id="hdnMstkMachineId" />
 <input type="hidden" id="mode"/>
</form>