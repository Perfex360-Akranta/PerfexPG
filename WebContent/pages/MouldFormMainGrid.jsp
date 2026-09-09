<script type="text/javascript">

		jQuery.noConflict();
		jQuery(document).ready(function(){
			
			var url = jQuery('#hiddenUrl').val();
			var mode = "VIEW";
			var dataString ="";
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");
			viewGrid(url,"?q=");										
		});

		function viewGrid(url,filterString)
		{		filterString += "&active=Y";
		
				processGridnew(url,filterString+'&closeOnSave=true',"list","pager","","eqpDoubleClick");			
			return true;	
		}

		
		function eqpDoubleClick(id)
		{	
			var rowData = jQuery("#list").jqGrid('getRowData',id);
			var keyid = rowData.MLDM_MOULDID;
			
			
			navigateToNextForm('mould_input.mld?mldKeyId='+keyid+'&closeOnSave=true&eqpMode=update',"Mould Master");
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
			var keyId = rowData.MLDM_MOULDID;
			
			//processAjaxCalls("equipment_viewInactive.eqp" ,"keyId="+keyId+"&checked=Y", "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
		}
		function chkboxProdUnCheck(rowId)
		{
			jQuery("#list").jqGrid('setCell',rowId,'chkVal','0');	
			var rowData = jQuery("#list").jqGrid('getRowData',rowId);	
			var keyId = rowData.MLDM_MOULDID;
			
		}
		jQuery("#btnInact").click(function(){
			
			var url = "mouldMainGrid_view.mld?";
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
			
			return true;
		});
		
		jQuery("#btnMackAct").click(function(){
			
			var url = "mouldMainGrid_makeActive.mld?";
			var addRowId = new Array();
			//var data =  JqGridToJsonSelectdRows("list","tick","chkVal");
			var allRows = jQuery("#list").jqGrid('getDataIDs');
		
			for(var i=0;i<allRows.length;i++)
			{
			
				var rowData = jQuery("#list").jqGrid('getRowData',allRows[i]);				
				if(rowData.chkVal == "1")
				{	
					var keyId = rowData.MLDM_MOULDID;
							
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
			var url = "mouldMainGrid_view.mld?";				
			processAjaxCalls('mouldMainGrid_makeActive.mld' ,'keyIds='+keyIds,"equipMakeActive_successsCallback","listInactive_errorCallback");
			return true;
		  }
			
			
		});
		jQuery("#btnAct").click(function(){
			//processAjaxCalls('equipment_getAll.eqp' ,'', "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");
			
			var filterString = "active=Y";
			var url = "mouldMainGrid_view.mld?";
			
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
		
		function equipMakeActive_successsCallback(result)
		{
			
			alert(result.successData.msg);
			//if(result == null){
				/*var r=confirm("Are you sure to activate the record(s).");
		
				if (r==true)
			  {*/
					//alert(result.successData.Data);
				var filterString = "active=N";
				var url = "mouldMainGrid_view.mld?";				
				processGridnew(url,filterString,"list","pager","","","","Inactive_SuccessCallBack");
				return true;
			  //}}
			/*else{*/
				
				 // }
			
		}
		
</script>
<form id="equipmentMainGrid">
<div id="wrapper">

	<div class="clear"></div>
	<div style="width: 100%;">
	<label class="notes"   style="font-weight: bold; padding-left:20px; " > ${requestScope.DoubleClick}</label>
	<span style="padding-left: 660px;">
	<input type="button" class="easyui-button" style="height: 20px;" id="btnAct" name="btnAct" value="View Active"></span>	
	<span><input type="button" id="btnInact"  style="height: 20px;" name="btnInact" class="easyui-button" value="View Inactive"></input></span>
	<span><input type="button" style="height: 20px;" class="easyui-button" id="btnMackAct" name="btnMackAct" value="Make Active"></span>
	</div>
<table id="list" ></table>
<div id="pager"></div>
</div>
</form>
<input type="hidden" id="hiddenArr" name ="hiddenArr"/>
