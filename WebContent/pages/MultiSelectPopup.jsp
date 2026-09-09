<script type="text/javascript">
jQuery(document).ready(function(){	
	
	var url=jQuery('#hdnPopupUrl').val();
	var dataStr =jQuery('#hdnCondition').val();
	   jQuery('#rowFlag').val("Y");
	   var flag =jQuery('#rowFlag').val();	
		
	if(dataStr.indexOf("teamId")>=0){
		
		 //loadTeamEmp("loadMultiSelectPopUp","8","13","","onLoad");
		 jQuery('#multiselectPopUpId').css("width","488");
		 //jQuery('#multiselectPopUpId').css("height","370");
	}
	processGridnew(url,"?"+dataStr,"multiSelectGrid","multiSelectPager","","","","multiSelectGrid_onComplete");
	 

	if(jQuery("#loadpopTeamEmp").is(":visible") ){
		
		var divId = jQuery('#prevDiv').html();
		jQuery("#loadpopTeamEmp").slideUp(200);	
		jQuery("#loadMultiSelectPopUp_openTeamFilter").remove();
		//loadTeamEmp("loadMultiSelectPopUp","8","13","","onLoad");
	}
});

jQuery('#loadMultiSelectPopUp_openTeamFilter').click(function(event){
	event.stopPropagation();
	//var filterString =jQuery('#hdnCondition').val();
	clearField("cmbFactory");
	clearField("cmbDeparttKeyid");
	clearField("cmbDesignation");	
	
	//loadTeamEmp("loadMultiSelectPopUp","25","13","loadSuccesCallBack","");
	
});	

function loadSuccesCallBack() {
}

function multiSelectGrid_onComplete(){
	 
	 var flag =  jQuery("#rowFlag").val();	
	var filterString="onLoad";
	var toRowId = jQuery("#hdnRowId").val();
	var toGridId = jQuery("#hdnGridId").val();
	var isMultiSelect = jQuery("#hdnIsMultiselect").val();
	var colNames =  jQuery("#hdnColNames").val();
	var colNamesArr =colNames; // colNames.trim().split(","); 
	colNamesArr=colNamesArr.substring(0,colNamesArr.indexOf(','));
	
	if(isMultiSelect == false || isMultiSelect == "false")
	{
		var keyid =jQuery('#'+toGridId).getCell(toRowId,colNamesArr);
		if(keyid != null && keyid.trim().length > 0)
		{
			var rowIds = jQuery('#multiSelectGrid').jqGrid().getDataIDs();
			for(var i=0;i<rowIds.length;i++)
			{
				var val=jQuery('#multiSelectGrid').getCell(rowIds[i],colNamesArr);
				if(val===keyid)
				{
					jQuery("#multiselectcheckbox_"+rowIds[i]).attr('checked','checked');
				}
			}
		}
	}
	else if( isMultiSelect == true || isMultiSelect == "true")
	{
		//var keyid =jQuery('#'+toGridId).getCell(toRowId,colNamesArr);
		//keyid=keyid.split(',');
		toRowId=toRowId.split(',');
		var rowIds = jQuery('#multiSelectGrid').jqGrid().getDataIDs();
	
		for(var i=0;i<rowIds.length;i++)
		{
			for(var j=0;j<toRowId.length;j++)
			{
				var val=jQuery('#multiSelectGrid').getCell(rowIds[i],colNamesArr);
				//alert(toRowId[j] );
				if(val==toRowId[j] && val != ' ' && val != null && val != undefined )
				{	
					jQuery("#multiselectcheckbox_"+rowIds[i]).attr('checked','checked');
				}
			}
		}
	}
	 
	jQuery("#multiSelectGrid").jqGrid( 'setGridParam',{onSortCol: function (index, columnIndex, sortOrder) {
	   		// alert(index);
	   		jQuery("#hdnSltedRowIds").val('');
	   		// return 'stop';
			}
		});
 

	
	
} 
function loadMultiSelectPopUp_DptRoleSuccessCallBack(FactoryId,deptId,Designationid){
	 
	var url=jQuery('#hdnPopupUrl').val();
	var teamId = jQuery('#hdnIdNode').val();
	processGridnew(url,"?q=2&teamId="+teamId+"&FactoryId="+FactoryId+"&deptId="+deptId+"&Designationid="+Designationid,"multiSelectGrid","multiSelectPager","","","","multiSelectGrid_onComplete","");
		
	 }
function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
 	return '<input  id="multiselectcheckbox_'+ id +'"'+ (rowObject[1]=="True" ? 'checked':'') + '  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
}

function selectData(rowId){
	var isMultiSelect = jQuery("#hdnIsMultiselect").val();
	var toGridId = jQuery("#hdnGridId").val();
	
	if( isMultiSelect == "false" || isMultiSelect == false){
		
		if( toGridId != null ){
			var colNames =  jQuery("#hdnColNames").val();
			var colNamesArr =  colNames.split(","); 
			var toRowId = jQuery("#hdnRowId").val();
			for(var i = 0 ; i<colNamesArr.length;i++){
				var value = jQuery("#multiSelectGrid").getCell(rowId, colNamesArr[i]);
				jQuery("#"+toGridId).setCell(toRowId,colNamesArr[i], value);		
			}
		}
		else{
			try{
				var okCallback = jQuery('#hdnokCallback').val();
				if( okCallback != null && okCallback.length > 0){
					var okCallbackFun = eval( okCallbackFun );
					if( jQuery.isFunction(okCallbackFun)){
						retVal = window[okCallback].apply(this,rowId);
					}
				}	
			}catch(Exception ){
			}
		}
			
		jQuery('#multiselectPopUpId').dialog('close');
	}
	else if( isMultiSelect == "true" || isMultiSelect == true){
		var toRowId = jQuery("#hdnRowId").val();
		var selRowIds = jQuery("#hdnSltedRowIds").val();
		
		if( selRowIds == " ")
			selRowIds = rowId +',';
		else
			selRowIds += rowId +',';
		if(toRowId != null && toRowId.length > 0)
		{
			var gridIds =getIds(toRowId);
			if(selRowIds.indexOf(gridIds)==-1)
			{	selRowIds=gridIds+selRowIds;}
		}

		jQuery("#hdnSltedRowIds").val(selRowIds);
	}
}

function unselectData(id){
	var selRowIds = jQuery("#hdnSltedRowIds").val();
	var toRowId = jQuery("#hdnRowId").val();
	
	if(toRowId != null && toRowId.trim().length > 0)
	{	
		var gridIds=getIds(toRowId);
		if(id.indexOf(gridIds)>0)
			gridIds=gridIds.replace(id+',','');

	if(jQuery('#hdnVar').val().trim()!=null && jQuery('#hdnVar').val().trim()!="")
		selRowIds=jQuery('#hdnVar').val();
	else
		selRowIds = gridIds;
	}
	
	selRowIds = selRowIds.replace(id+',','');
	
	jQuery('#hdnVar').val(selRowIds);
		
	jQuery("#hdnSltedRowIds").val(selRowIds);
}


function getIds(toRowId)//Added By Siddharth.A
{
	var colNames =  jQuery("#hdnColNames").val();
	colNames=colNames.split(',');
	var rowIds = jQuery('#multiSelectGrid').jqGrid().getDataIDs();
	var gridIds="";
	if(toRowId != null && toRowId.trim().length > 0)
	{
		toRowId=toRowId.split(',');
		for(var i=0;i<rowIds.length;i++)
		{
			var val=jQuery('#multiSelectGrid').getCell(rowIds[i],colNames[0]);
			for(var j=0;j<toRowId.length;j++)
			{
				if(val == toRowId[j])
				{	
					gridIds.trim() == "" ? gridIds = rowIds[i]+',': gridIds += rowIds[i]+',';
				}
			}
		}
	}
	return gridIds;
}
function loadCompletePillarGrid()
{
	jQuery(":input[type=checkbox]").removeAttr('disabled');
	
}


jQuery('#btndialogOk').click( function()
{
	var isMultiSelect = jQuery("#hdnIsMultiselect").val();
	if( isMultiSelect == "true")
	{
		
		var toGridId = jQuery("#hdnGridId").val();
		var selRowIds = jQuery("#hdnSltedRowIds").val();
	
		if( toGridId != null && toGridId.length > 0 ){

			var colNames =  jQuery("#hdnColNames").val();
			var colNamesArr =  colNames.split(",");
			var selRowIdArr =selRowIds.split(",");
			for(var i=0;i<selRowIdArr.length-1;i++){ 
				
				
				//for(var j=0;j<colNamesArr.length;j++)
				//{
					var rowObject = jQuery("#multiSelectGrid").getRowData(selRowIdArr[i]);
					
					var rowDataExist = jQuery("#"+toGridId).getRowData(selRowIdArr[i]);
					
					if( ! isEmpty(rowDataExist)){
						
						jQuery("#"+toGridId).setRowData(selRowIdArr[i],rowObject,true);
					}	
					else{
						jQuery("#"+toGridId).addRowData(selRowIdArr[i],rowObject,'last');
					}	
							
				//}
			}
		}	
		
	
			
	}
	try{
		var okCallback = jQuery('#hdnokCallback').val();
		
		if( okCallback != null && okCallback.length > 0){
			var args = [selRowIds];
			
		//	var okCallbackFun = eval( okCallbackFun );
		//	if( jQuery.isFunction(okCallbackFun)){
				window[okCallback].apply(this,args);
		//	}
		}	
	}catch(Exception ){
	}
	jQuery('#multiselectPopUpId').dialog('close');

});
jQuery('#btndialogClose').click( function()
{ 
	var cancelCallback = jQuery('#hdnCancelCallBack').val(); ;
	var id=jQuery('#hdnRowId').val(); 
	if( typeof eval('(' + cancelCallback +')') == 'function')
		eval('(' + cancelCallback +'(id))');
	
	jQuery('#multiselectPopUpId').dialog('close');
	
});	

</script>

<form id="frmMultiselect" name="frmMultiselect">
<div id="multiselectPopUpId" title="" style="height:300px;" >

<table >
	<tr>
		<td >

		<!-- 	<div id="Fnddiv" class="cntborder floatleft" style="display:;margin-top: 1%;width: 42.5%;"> --><!-- Filter Div -->
				<!-- 			<table class="div-border">
								<tr style="width:10%" class="valigncnt">
								 <td>
								<div>
								   	<div >
										<span class="sub-header" style="padding-right: 4%;">&nbsp;Find</span>
										<span style="padding-right: 5%;"><label>Search Column:</label></span>
										<span style="padding-right: 2%;"><label>CODE</label></span>
									</div>
									<div style="margin-top: 1%;">
										<span >&nbsp;Options</span>
										<span ><input type="text" class="easyui-combobox"/></span>
										<span><input type="text" style="height: 2%"/></span>
										<span><input type="button" value="Find" id="btnFnd" class="easyui-button" style="height: 2%" /></span>
										<span><input type="button" value="Close" id="btnClse" class="easyui-button" onclick="clse()" style="height: 2%" /></span>
									</div>
						 	  </td>
								</tr>	
							</table> 
							</div> -->
						<!-- End of Filter Div -->
					 
				
			
			<div id="">
			 		<!--<span><input type="button" class="easyui-button" value="Find"/></span>
					<span><input type="button" class="easyui-button" value="Filter"/></span> 
					
					<span><input type="button" class="easyui-button" style=" height : 21px;" value="Export To Excel" id="exprtToExl"/ ></span>	-->
			</div>
			

		<div class="floatleft" style="width: 400px;height: 300px;padding-right: 30px;text-align:left;">
	
					<div id="griddivImprCat" style="float: left;margin-left: 6%; "> 
						 <table id="multiSelectGrid" style="width:100%"><tr><td/></tr></table>
					 	 <div id="multiSelectGrid" ></div> 
					</div>
			</div>
			<div style="clear: both;"></div>
			<div class="clear"></div><br><br><br><br>
			<div style="padding-left:180px;margin-top: 30%;margin-bottom: 10%;" >
					<span><input type="button" id="btndialogOk" class="easyui-button" value="Ok" style=" height : 21px;" /> </span>
					<span><input type="button" id="btndialogClose" class="easyui-button" value="Cancel" style=" height : 21px;width:60px;" onclick="javascript:jQuery('#multiselectPopUpId').dialog('close')"/> </span>
			</div>	
		</td>
	</tr>	
</table>
</div>
<input type="hidden" id="rowFlag">
<input type="hidden" id="hdnSltedRowIds" value=" " />
<input type="hidden" id="hdnPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsMultiselect" value="${requestScope.isMultiselect}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnCondition" value="${requestScope.condition}" >
<input type="hidden" id="hdnCancelCallBack" value="${requestScope.multiSelectCancel_Callback}" >
<input type="hidden" id="hdnokCallback" value="${requestScope.multiSelectOk_Callback}"/>
<input type="hidden"  id="hdnIdNode" name="hdnIdNode"/>
<input type="hidden" id="hdnVar" />
<input type="hidden" id="hdnFact" value="${requestScope.Factory}"/>
<input type="hidden" id="hdnDept" value="${requestScope.Dept}"/>
<input type="hidden" id="hdnDesg" value="${requestScope.Designation}"/>

</form>