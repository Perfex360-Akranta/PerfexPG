<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 
<script type="text/javascript">

jQuery.noConflict();
jQuery(document).ready(function(){

	var url = jQuery('#hiddenUrl').val();
	
	//fillComboBox("frmAbnBulkTagRemove","cmbAbnmTradeid","Combo_Trade.abnForm");
	fillComboBox("frmAbnBulkTagRemove","cmbAbnmCompletedby","Combo_CompletedBy.abnForm");
	//formatDateBox('dteAbnmenddate','dd-MMM-yyyy');
	
	jQuery('#submitForm').val('frmAbnBulkTagRemove');
	var FromDate = "";
	var ToDate="";
	var url = jQuery('#hiddenUrl').val();
	if(url=="AbnTagRemove_input.abnForm")
		jQuery('#completedBlock').hide();
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;

	/*if(url=="AbnTagRemove_input.abnForm")
		dataString+="&abnStatus=removal";
	else if(url=="AbnModify_input.abnForm")
		dataString+="&abnStatus=modification";*/

		var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
		
		if( prevDataUrl == null || prevDataUrl.length <=0)	{	
		
			viewGrid(url,"?q=1&firstClick=Y");
		}
		else{
	
			viewGrid(unescape(prevDataUrl),"&q=1");
		}

		
		//viewGrid(url,dataString);
	});

	function viewGrid(url,filterString,tableCaption)
	{
		var abnType=jQuery('#hdnAbnType').val();
		filterString+="&abnType="+abnType;	
		if( validateFilterSelection(filterString))
		{
			processGridnew(url,filterString,"list","pager","","nxtgrid","","abnormalityGridOncompleteLoad");
			return true;
		}
	}
	function validateFilterSelection(filterString){
		return  true;
	}
	function abnormalityGridOncompleteLoad()
	{

		var rowId = jQuery("#list").jqGrid('getDataIDs');	
		var cm = jQuery("#list").jqGrid("getGridParam", "colModel");

		
		formatDateBoxWithGrid("dspAbnmenddate_",'dd-MMM-yyyy');
		formatDateBoxWithGrid("dspAbnmWostarttime_",'dd-MMM-yyyy');		
		fillComboBoxWithGrid("frmAbnBulkTagRemove","cmbAbnmTradeid","Combo_Trade.abnForm");
		initialiseSpinnerWithGrid("spnAbnmWostarttime_");
		initialiseSpinnerWithGrid("spnAbnmenddate_");
		spinnerKeyPress('spnAbnmWostarttime_');
		//spinnerChange('spnAbnmDetectedbytime','detectionDateEvt')
		//spinnerUp('spnAbnmDetectedbytime','detectionDateEvt');
	//spinnerDown('spnAbnmDetectedbytime','detectionDateEvt');
		/*for(i=1;i<=rowId.length;i++)	
		{	
			//var date="dteAbnmenddate_"+i;	
			//var date1="dteAbnmWostarttime_"+i;	
			for(j=1;j<=cm.length-1;j++)	
			{		
				//var controlId="dteAbnmenddate_"+(j-1);	
				var date="dteAbnmenddate_"+(j-1);	
				var date1="dteAbnmWostarttime_"+(j-1);	
				//var date="txtNewCycDate_"+(j-1);	
				//numericTextBox(controlId);
				
				//formatDateBox(date,'dd-MMM-yyyy');
				//formatDateBox(date1,'dd-MMM-yyyy');
			}
		}*/
		var rowIds = jQuery('#list').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			var cellVal =jQuery('#list').getCell(rowIds[i],"Status");
			if(cellVal=="COMPLETED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"DetectedDate","",{'color':'blue'});
			var tagClor = jQuery('#list').getCell(rowIds[i],"TagClass");
			if(tagClor == "RED")
				jQuery("#list").jqGrid('setCell',rowIds[i],"TagNo","",{'color':'red'});
			
			
		} 		
    }

	function spinner(id,rowId,cellVal)
	{	
		return '<input id="'+id+rowId+'" name="'+id+rowId+'" class="easyui-timespinner spinner-text validatebox-text" value="'+cellVal.substring(12,20)+'"  style="width: 80px;"/></span>';
	}
		function nxtgrid(id) 
		{
	  
	  	  	var rowData = jQuery("#list").jqGrid('getRowData',id);																								
			var selId = rowData.machineId;
			//var record = rowData.Equipmentname;
			var grid = jQuery('#list');
			var sel_id = grid.jqGrid('getGridParam', 'selrow');
			var TagNo = grid.jqGrid('getCell', sel_id, 'TagNo');
			var url = jQuery('#hiddenUrl').val();
			
			if(url=="AbnModify_input.abnForm")
				tableCaption="Abnormality Modification";
			else if(url=="AbnHTAView_input.abnForm")
				tableCaption="HTA Abnormality View";
			else if(url=="AbnSOCView_input.abnForm")
				tableCaption="SOC Abnormality View";
			else if(url=="AbnUNSView_input.abnForm")
				tableCaption="UNS Abnormality View";
			else if (url=="AbnTagRemove_input.abnForm")
				tableCaption="Abnormality Removal Tag";
			
			
			var tagClass=jQuery('#list').getCell(sel_id,"TagClass");
			var abndKeyid =grid.jqGrid('getCell', sel_id, 'abndKeyid');
			var abnStatus=jQuery('#list').getCell(sel_id,"Status");
			var mode  = jQuery("#hdnFrmMode").val();
			var woId= jQuery('#list').getCell(sel_id,"MWNo");
			var filterData = TagNo+"&tagClass="+tagClass+"&abnStatus="+abnStatus+"&mode="+mode+"&WOID="+woId;//alert("1stfrm"+filterData);
			var url = jQuery("#list").jqGrid('getGridParam', 'url');
			url = url.replace('getData','input');
			url = escape(url); 
			
			navigateToNextForm("Abnormality_input.abnForm?q=2&AbnId="+filterData+"&filterButton=false",tableCaption,null,{"filterString":url});
			
		 
	}
		 function formattor_chkBoxSelect(id, options, rowObject)
		 {	
		 	var rowId = options.rowId;	
		 	return '<input id="list_checkbox" name="list_checkbox" '+ (rowObject[0]=="0" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
		 }
		 function chkboxCheck(rowId)
		 {
		 	jQuery("#list").jqGrid('setCell',rowId,'chkVal','1');		
		 }
		 function chkboxUnCheck(rowId){
		 	jQuery("#list").jqGrid('setCell',rowId,'chkVal','0');	
		 }

	/*	 function formatter_dteAbnmWostarttime(cellVal, options, rowObject)
		 {			 
			 return '<input id="dspAbnmWostarttime_'+options.rowId+'" name="dteAbnmWostarttime_"'+options.rowId+'" style="width:90px;"  class="easyui-datebox" value="'+cellVal.substring(0,11)+'"/>'+spinner("spnAbnmWostarttime_",options.rowId,cellVal);				 
		 }
		 function formatter_dteAbnmenddate(cellVal, options, rowObject)
		 {
			 return '<input id="dspAbnmenddate_'+options.rowId+'" style="width:90px;"  class="easyui-datebox" value="'+cellVal.substring(0,11)+'" />'+spinner("spnAbnmenddate_",options.rowId,cellVal);			
		 }
		 function formatter_cmbAbnmTradeid(cellVal, options, rowObject)
		 {
			 return '<input id="cmbAbnmTradeid_'+options.rowId+'" name="cmbAbnmTradeid_'+options.rowId+'" tabindex="23" class="easyui-combobox"  style="width:305px;" value=""/> ';
		 }
		 
		 function formatter_txtAbnmCountermeasure(cellVal, options, rowObject)
		 {	
		 	var rowId = options.rowId;	
		 	return '<textarea class="txtarea" rows="1" tabindex="24"  maxlength="490" style="width:305px;resize:none; " cols="" id=abnCounter_'+rowId+'  >'+cellVal+'</textarea>';
		  	//return '<input type="text" style="text-align:right;width:150px;"  maxlength="490" id=abnCounter_'+rowId +' value='+cellVal +' >'; 	
		 }
		 function formatter_txtAbnmWhyabnhappened(cellVal, options, rowObject)
		 {	
		 	var rowId = options.rowId;	
		 	return '<textarea class="txtarea" rows="1" tabindex="24"  maxlength="490" style="width:305px;resize:none; " cols="" id=abnWhyHapp_'+rowId+'  >'+cellVal+'</textarea>';
			//return '<input type="text" style="text-align:right;width:150px;"   id=abnWhyHapp_'+rowId +' value='+cellVal +' >'; 	
		 }
		 function formatter_txtAbnmWhatcause(cellVal, options, rowObject)
		 {	
		 	var rowId = options.rowId;
		 	return '<textarea class="txtarea" rows="1" tabindex="24"  maxlength="490" style="width:305px;resize:none; " cols="" id=abnWhatCause_'+rowId+'  >'+cellVal+'</textarea>';	
		  	//return '<input type="text" style="text-align:right;width:150px;"  maxlength="7" id=abnWhatCause_'+rowId +' value='+cellVal +' >'; 	
		 }
		 function formatter_txtAbnmTargetremarks(cellVal, options, rowObject)
		 {	
		 	var rowId = options.rowId;	
		 	return '<textarea class="txtarea" rows="1" tabindex="24"  maxlength="490" style="width:305px;resize:none; " cols="" id=abnRemark_'+rowId+'  >'+cellVal+'</textarea>';
		  	//return '<input type="text" style="text-align:right;width:150px;"  maxlength="7" id=abnRemark_'+rowId +' value='+cellVal +' >'; 	
		 }

*/
		 function frmAbnBulkTagRemove_beforeSubmit()
		 {	

			//var gridData = "&gridData="+JqGridToJsonSelectdRows("list","checkbox","chkVal");	
			
			var gridData =  "gridData="+getGridSelectArray('list');
			///return "gridData="+gridData;
			
			if(gridData != null || gridData.length>0){
				gridData += "&cmbAbnmCompletedby="+jQuery('#cmbAbnmCompletedby').combobox('getValue');		
				return gridData;
			}
			else{
				alert("Select a Row For Save!");
				return false;
			}
			
		}
		function  frmAbnBulkTagRemove_successsCallback()
		{
			//alert("succes calback");
			jQuery("#list").trigger("reloadGrid");
		}
		function frmFilter_enableDisableSuccessCallBack(){
			
			if(jQuery('input:checkbox[name=chkMonthwise]').attr('checked') == 'checked')
			{
			jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
			jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
			jQuery("#dtefromDate").datebox('disable');
			jQuery("#dtetoDate").datebox('disable');
			jQuery("#dtefromDate").datebox('clear');
			jQuery("#dtetoDate").datebox('clear');
			}
			else
			{
				jQuery('input:checkbox[name=chkMonthwise]').attr('checked',false);
				jQuery("#dtefromMonth").datebox('disable');
				jQuery("#dtetoMonth").datebox('disable');
				jQuery("#dtefromMonth").datebox('clear');
				jQuery("#dtetoMonth").datebox('clear');
			}
			
		}
</script>
<form id="frmAbnBulkTagRemove">
<div id="wrapperRpt">
	<div id="completedBlock">
	 
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	
	<div>
	<label>Completed By</label>
<!--	<span style="padding-left:125px"><label>Maintenance Section</label></span>-->
<!--	<span style="padding-left:90px"><label>Work End Date</label></span>-->
	</div>
	<div>
	<input id="cmbAbnmCompletedby" name="cmbAbnmCompletedby" tabindex="21" class="easyui-combobox"  style="width:200px;" value="${requestScope.loginUser}" />
<!--	<input id="cmbAbnmTradeid" name="cmbAbnmTradeid" tabindex="23" class="easyui-combobox"  style="width:200px;" value=""  >-->
<!--	<input id="dteAbnmenddate"  name="dteAbnmenddate" clear="false" class="easyui-datebox" value=""  style="width: 125px;" />-->
	</div>
	 </div>	
	 <div style="" id="Abnmodify"> 
		<table id="list" ></table>
	<div id="pager"></div>
	</div>
</div>
<input type="hidden" id="mode"  />
</form>