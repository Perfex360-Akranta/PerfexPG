 
<script type="text/javascript">
  
 jQuery.noConflict();
 jQuery(document).ready(function(){	
	 var url=jQuery('#hdnPopupUrl').val();
	 var dataStr =jQuery('#hdnCondition').val();
	 processGridnew("subType_input.prv","?&q=0","subtype","pagersub","","");	
 });
 function subtypecbox(id)
 {
 	return '<input id="tick"  type="checkbox" />';
 	 // 	return '<input  type="checkbox" id="pillar_checkbox" onlick=\''/>';
 }
 function pillarLinkSelect(id)
 {
 	var pillarId = jQuery("#subtype").getCell(id,"Keyid");
 	multiSelectPop("subType_input.prv","?&q=0Keyid"+Keyid,"subtype","pagersub","","",false);
 }

 function checkBoxClick(id)
 {
	 var tableDatas = jQuery("#subtype").jqGrid('getRowData');
	 var rowIds = "";
	 if(tableDatas!=null && tableDatas[0] != null)
	 alert("tableDatas -"+tableDatas[0].Keyid);
 	jQuery('#multiselectPopUpId').dialog('close');
 }
  
//for close grid
	jQuery('#closebtn').click(function(){
		jQuery("#multiselectPopUpId").dialog('close');
	});
 </script>
<form>
<div style="margin-top:5px;">
<input type="button" class ="easyui-button" value="Export To Excel" id="" style="float:right;margin-right:20px;o"/>
<input type="button" class ="easyui-button" value="Find" id="btnok" style="float:left;"/>
<input type="button" class ="easyui-button" value="Filter" id="" style="float:left;margin-right:20px;"/>
</div><br><br>

<div>
<table id="subtype">
</table>
<div id="pagersub"></div>
<div>
</div>
<div style="margin-top:5px;">
<input type="button" class ="easyui-button" value="CANCEL" id="closebtn" style="float:right;margin-right:20px;"/>
<input type="button" class ="easyui-button" value="OK" id="btnok" style="float:right;"/>
</div>
</div>
<input type="hidden" id="hdnPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsMultiselect" value="${requestScope.isMultiselect}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnCondition" value="${requestScope.condition}" >
</form>