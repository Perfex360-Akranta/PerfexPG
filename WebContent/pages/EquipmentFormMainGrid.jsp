<script type="text/javascript">

		jQuery.noConflict();
		jQuery(document).ready(function(){
			
			var url = jQuery('#hiddenUrl').val();
			var mode = "VIEW";
			var dataString ="";

			loadFunctionalLocation("divEqpMainGridFL","functionalLocMaingrid.eqp","EqpMainGridFLValues","equipmentMainGrid",dataString);
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");

			
			
			//viewGrid(url,"?q=");										
		});

		function viewGrid(url,filterString)
		{		filterString += "&active=Y";
				processGridnew(url,filterString+'&closeOnSave=true',"list","pager","","eqpDoubleClick","list_onComplete");			
			return true;	
		}

		function equipmentMainGrid_FuntLocHierarchy_SuccessCallBack(keyIds){
			var url = jQuery('#hiddenUrl').val();
			var flid = "&flid="+jQuery("#equipmentMainGrid input[id=flid]").val();
			viewGrid(url,flid);
		}
	function list_onComplete(result){
		jQuery("[id^=btnParameters]").click(function (){
		var rowid = this.id.replace("btnParameters_"," ").trim(); 
		var eqpId = jQuery("#list").jqGrid().getCell(rowid,"EquipmentNO");
		LoadPopUp("", "equipmentDetails_input.eqp?eqpId="+eqpId, true,"950px","420px","40px","150px",null,eqpId+ " - Equipment Details");
	});	
	jQuery("[id^=btnOtherDetails]").click(function (){
		var rowid = this.id.replace("btnOtherDetails_"," ").trim(); 
		var eqpId = jQuery("#list").jqGrid().getCell(rowid,"EquipmentNO");
		LoadPopUp("", "equipmentOtherDetails_input.eqp?eqpId="+eqpId, true,"730px","480px","40px","200px",null,eqpId+ " - Equipment Details");
	});	
}	
/*function list_onComplete(result){
	jQuery("[id^=btnOtherDetails]").click(function (){
		var rowid = this.id.replace("btnOtherDetails_"," ").trim(); 
		var eqpId = jQuery("#list").jqGrid().getCell(rowid,"EquipmentNO");
		LoadPopUp("", "equipmentOtherDetails_input.eqp?eqpId="+eqpId, true,"730px","420px","40px","200px",null,eqpId+ " - Equipment Details");
	});	
}	*/
		function eqpDoubleClick(id)
		{	
			var rowData = jQuery("#list").jqGrid('getRowData',id);
			var keyid = rowData.MCHM_KEYID;
			var elementIdHtml = rowData.elementid;
		    console.log("element id ",elementIdHtml)
		    // Parse the HTML and extract the value
		    
			navigateToNextForm('equipment_input.eqp'+'?keyId='+keyid+'&eqpMode=update',"Equipment Master");
		}
		
		function BtnFormatterTick(id, options, rowObject)
		{
			var rowId = options.rowId;	
		  	return '<input id="chkActive" name="chkActive" '+ (rowObject[0]=="0" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxProdCheck(\''+rowId + '\');}else{chkboxProdUnCheck(\''+ rowId +'\')}"/>';
		}
		function chkboxProdCheck(rowId)
		{
			jQuery("#list").jqGrid('setCell',rowId,'chkVal','1');	
			var rowData = jQuery("#list").jqGrid('getRowData',rowId);	
			var keyId = rowData.MCHM_KEYID;
			
			//processAjaxCalls("equipment_viewInactive.eqp" ,"keyId="+keyId+"&checked=Y", "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
		}
		function chkboxProdUnCheck(rowId)
		{
			jQuery("#list").jqGrid('setCell',rowId,'chkVal','0');	
			var rowData = jQuery("#list").jqGrid('getRowData',rowId);	
			var keyId = rowData.MCHM_KEYID;
			
		}
		jQuery("#btnInact").click(function(){
			
			var url = "equipment_input.eqp?";
			var lblVal = jQuery("#btnInact").val();
			
			if(lblVal == "View Inactive")
			{
				//jQuery("#btnInact").val("View Active");
				var filterString = "active=N";
				processGridnew(url,filterString,"list","pager","","","","Inactive_LoadComplete");	
				
				jQuery("#btnMackAct").attr("disabled",false);
				jQuery("#btnMackAct").removeClass("disabledButton");
				jQuery("#btnMackAct").addClass("easyui-button");
			}
			/*else if(lblVal == "View Active")
			{
				jQuery("#btnInact").val("View Inactive");
				var filterString = "active=Y";
				processGridnew(url,filterString,"list","pager","","","","Inactive_LoadComplete");	
				
				jQuery("#btnMackAct").attr("disabled",true);
				jQuery("#btnMackAct").removeClass("easyui-button");
				jQuery("#btnMackAct").addClass("disabledButton");
			}*/
				
			return true;
		});
		
		jQuery("#btnMackAct").click(function(){
			
			var url = "equipment_makeActive.eqp?";
			var addRowId = new Array();
			//var data =  JqGridToJsonSelectdRows("list","tick","chkVal");
			var allRows = jQuery("#list").jqGrid('getDataIDs');
		
			for(var i=0;i<allRows.length;i++)
			{
				var rowData = jQuery("#list").jqGrid('getRowData',allRows[i]);				
				if(rowData.chkVal == "1")
				{	
					var keyId = rowData.KEYID;					
					addRowId.push(keyId);
				}
			}
			hiddenArr = new Array();
			hiddenArr.push(addRowId);
			var keyIds = hiddenArr;
			var dataString = "keyIds = "+keyIds;
			
			var r=confirm("Are you sure to activate the record(s).");
			
			if (r==true)
		  {
			var filterString = "active=N";
			var url = "equipment_input.eqp?";				
			processAjaxCalls('equipment_makeActive.eqp' ,'keyIds='+keyIds,"equipMakeActive_successsCallback","listInactive_errorCallback");
			return true;
		  }
			
			
		});
		jQuery("#btnAct").click(function(){
			//processAjaxCalls('equipment_getAll.eqp' ,'', "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");
			
			var filterString = "active=Y";
			var url = "equipment_input.eqp?";
			
			//processGridnew(url,filterString,"list","pager","","","","Inactive_SuccessCallBack");	
			processGridnew(url,filterString+'&closeOnSave=true',"list","pager","","eqpDoubleClick");
			return true;
		});
		
		function Inactive_LoadComplete()
		{			
			/*jQuery("#btnMackAct").attr("disabled",false);
			jQuery("#btnMackAct").removeClass("disabledButton");
			jQuery("#btnMackAct").addClass("easyui-button");	*/
		}

		
		function FormattarParameters(id, options, rowObject)
		{
			var rowId = options.rowId;
		 	  return '<input type="button" class="easyui-button" id="btnParameters_'+rowId+'" style="width:80px;height:15px;" value="Parameters"/>';
			}
		function FormattarOtherDetails(id, options, rowObject)
		{
			var rowId = options.rowId;
		 	  return '<input type="button" class="easyui-button" id="btnOtherDetails_'+rowId+'" style="width:80px;height:15px;" value="Other Details"/>';
		}
		
		function equipMakeActive_successsCallback(result)
		{
			
			alert(result.successData.msg);
			//if(result == null){
				/*var r=confirm("Are you sure to activate the record(s).");
		
				if (r==true)
			  {*/
					//alert(result.successData.Data);
				var filterString = "active=N";
				var url = "equipment_input.eqp?";				
				processGridnew(url,filterString,"list","pager","","","","Inactive_SuccessCallBack");
				return true;
			  //}}
			/*else{*/
				
				 // }
			
		}
		
</script>
<form id="equipmentMainGrid">
<div style="margin-top:20px; margin-left:3%">
<div  id="frmEquipmentMainGridFuntKeyIds" >
	<input type="hidden" id="factory" name="cmbMchmFlitfact" value=""  ></input>
	<input type="hidden" id="section" name="cmbFlitSection" value=""  ></input>
	<input type="hidden" id="cell" name="cmbMchmFlitCellid" value=""  ></input>
	<input type="hidden" id="flid" name="cmbMchFiltFlid" value=""  ></input>
	<input type="hidden" id="updElement" name="updElement" value="${requestScope.updElement}"  ></input>
	</div>
	<div id="divEqpMainGridFL"  style="width: 116%; padding-left:0%;margin-top:-20px" align="left"></div>

	<div class="clear"></div>
	
	<table>
	<tr>
	<td style="">
	<span style="">
	<input type="button" class="easyui-button" style="height: 20px;width:76px;" id="btnAct" name="btnAct" value="View Active"></span>	
	<span><input type="button" id="btnInact"  style="height: 20px;width:100px;" name="btnInact" class="easyui-button" value="View Inactive"></input></span>
	<span><input type="button" style="height: 20px;width:80px;" class="easyui-button" id="btnMackAct" name="btnMackAct" value="Make Active"></span>
	
	</td>
	<td >
	<div style="width:100% ;">
	<label class="notes"   style="font-weight: bold; padding-left:10px;" > ${requestScope.DoubleClick}</label>
	</div>
	</td>
	</tr>
	<tr>
		
	</tr>
	</table>
<table id="list" ></table>
<div id="pager"></div>
</div>
</form>
<input type="hidden" id="hiddenArr" name ="hiddenArr"/>
<input type="hidden" id="mode" name ="mode" value="${requestScope.mode}"/>
