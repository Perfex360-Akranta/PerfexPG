<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function()
	{
		jQuery('#popHead').hide();
		var selectdmchId = jQuery('#hien').val();
		
		//processGridnew("addmachine_input.jhclit","?&selectdmchId"+selectdmchId,"addmach","pagera","","dblclick");
		processGridnew("addmachine_input.admch",'?q=2&selectdmchId='+selectdmchId,"addmach","pagera","ADD Machine","");
		jQuery("#addmach").setGridParam({multiselect:true});
		
		jQuery('#hdnmultiSel').val("true");	
	});

	function cboxFormatter(id, options, rowObject)
	{
		var id = options.rowId;
		return '<input  type="checkbox" id="selchk" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	}

	
	function frmaddMachine_beforeSubmit(){
		alert("inside save");
		//var tableDatas = jQuery("#addmach").jqGrid('getRowData');
		//alert(tableDatas.txtmcamname);
			var rowData = jQuery("#addmach").jqGrid('getRowData',id);																								
		var availId = rowData.txtmcamname;
		alert(availId);	
		var seletedData  = JqGridToJsonSelectdRows("addmach","tick","availId");
		alert(seletedData);
		return seletedData; 
		
	}

	function addmachinePopOk_Callback(rowIds){
		alert("adjhf");
		//var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds);
		//alert(rowObject.txtPmsdActsubtype);
		var subType = "";
		var subTypeKeyId = "";
		var seprator = ',';
		for(var i=0;i<rowIds.length;i++){
			var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds[i]);
			jQuery("#multiSelectGrid").getCell(rowIds[i],"my_checkbox","False");
			
			subType +=rowObject.txtmcamkeyId;
			subType	+=seprator;		
			subTypeKeyId+=rowObject.txtmcamnamee;
			subTypeKeyId+=seprator;
			alert(subType+"----"+subTypeKeyId);
			
		}
		subType = subType.slice(0,-1);
		jQuery('#txtPmsdActsub').val(subType);
		subTypeKeyId =subTypeKeyId.slice(0,-1);
		jQuery('#txtPmsdActsubtype').val(subTypeKeyId);
	  	//alert(row_Ids.txtPmsdActsubtype);
		
		
	}
</script>
<form id="frmaddMachine" name="frmaddMachine">
<div id="saveIcon" style="width:100%">
	<span style="margin-left:10px;cursor:pointer;">
		<img src="images/2_save.png"/>
	</span>
	<div style="margin-left:10px;font-weight:bold;">Save</div>
</div>
<table id="addmach"></table>
<div id="pagera"></div>
</form>
<input type="hidden" id="hdnSel" type="text" />
<input type="hidden" id="hdnmultiSel" />
<input type="hidden" id="hdnokCallback" value="${requestScope.multiSelectOk_Callback}"/>