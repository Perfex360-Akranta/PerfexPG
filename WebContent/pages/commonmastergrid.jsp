<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
			var formUrl = jQuery("#menuName").val();
			var masterForm = jQuery("#masterForm").val();
			formUrl += (formUrl.indexOf('?')>0 ? '':'?')+ 'masterForm='+masterForm;

			jQuery("#hiddenUrl").val(formUrl);
			var gridURL = jQuery("#gridURL").val();
			//alert(gridURL);
			var doubleClick="fillForm";
			var clickEnable=jQuery("#clickEnable").val();
			if("N".contains(clickEnable))
				doubleClick="";
			processGridnew(gridURL,"?q=2&rowStart=1&rowEnd=100&active=Y&active1=Y&chkHide=Y","list","pager","",doubleClick);
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");
						
		});
		function getMstSuccess(result)
		{
			//alert("Success");
		}
		function getMstError(result)
		{
			//alert("Success");
		}

		function fillForm(id)
		{
		
			var rowData = jQuery("#list").jqGrid('getRowData',id);
			var keyId = rowData.KEYID;
			var keyId2 = rowData.keyid;
			var formUrl = jQuery("#hiddenUrl").val();
			formUrl = unescape(formUrl);			
			var formHeader = jQuery("#menuCaption").val();
			
			if( formUrl.indexOf("loadFormArg") >= 0 )
			{
				formUrl = formUrl.substring(formUrl.indexOf("loadFormArg")+ "loadFormArg".length+1);
				jQuery("#hiddenUrl").val(formUrl);
				
			}	
			formUrl += formUrl.indexOf('?') < 0 ? '?':'&';
			
			var masterForm = jQuery("#masterForm").val();
		//	navigateToNextForm(formUrl+'keyId='+keyId +'&closeOnSave=true',formHeader,null,{'masterForm':masterForm});
			navigateToNextForm(formUrl+'keyId='+keyId2 ,formHeader,null,{'masterForm':masterForm});
		}

		function BtnFormatterTick(id, options, rowObject)
		{
			
			var rowId = options.rowId;
			
		  	//return '<input id="chkActive" name="chkActive" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxProdCheck(\''+rowId + '\');}else{chkboxProdUnCheck(\''+ rowId +'\')}"/>';
		  	return '<input id="chkActive" name="chkActive"'+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxProdCheck(\''+rowId + '\');}else{chkboxProdUnCheck(\''+ rowId +'\')}"/>';
		  	//return '<input id="SubEquipment_checkbox" name="SubEquipment_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
		}
		function chkboxProdCheck(rowId)
		{			
			var allRows = jQuery("#list").jqGrid('getDataIDs');			
			jQuery("#list").jqGrid('setCell',rowId,'TICK','1');	
			var rowData = jQuery("#list").jqGrid('getRowData',rowId);	
			var keyId = rowData.KEYID;
			//alert(keyId);
			//processAjaxCalls("equipment_viewInactive.eqp" ,"keyId="+keyId+"&checked=Y", "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
		}
		function chkboxProdUnCheck(rowId)
		{			
			jQuery("#list").jqGrid('setCell',rowId,'TICK','0');	
			var rowData = jQuery("#list").jqGrid('getRowData',rowId);	
			var keyId = rowData.KEYID;	
		}
		
		jQuery("#btnAct").click(function(){
			//processAjaxCalls('equipment_getAll.eqp' ,'', "listInactiveSelect_successsCallback","listInactive_errorCallback");
			
			jQuery("#btnMackAct").attr("disabled",true);
			jQuery("#btnMackAct").removeClass("easyui-button");
			jQuery("#btnMackAct").addClass("disabledButton");
			
			var gridURL = jQuery("#gridURL").val();
			
			processGridnew(gridURL,"?q=2&rowStart=1&rowEnd=100&active=Y&active1=Y&chkHide=Y","list","pager","","fillForm");
			return true;
		});
		
	jQuery("#btnInact").click(function(){
			
			//var url = "equipment_input.eqp?";
			var lblVal = jQuery("#btnInact").val();
			
			/*if(lblVal == "View Inactive")
			{*/
				//jQuery("#btnInact").val("View Active");
				var gridURL = jQuery("#gridURL").val();
				processGridnew(gridURL,"?q=2&rowStart=1&rowEnd=100&active=N&active1=N&chkHide=N","list","pager","","");
				
				jQuery("#btnMackAct").attr("disabled",false);
				jQuery("#btnMackAct").removeClass("disabledButton");
				jQuery("#btnMackAct").addClass("easyui-button");
			//}
			/*else if(lblVal == "View Active")
			{
				jQuery("#btnInact").val("View Inactive");
				var gridURL = jQuery("#gridURL").val();
				processGridnew(gridURL,"?q=2&rowStart=1&rowEnd=100&active=Y&active1=Y&chkHide=Y","list","pager","","fillForm");	
				
				jQuery("#btnMackAct").attr("disabled",true);
				jQuery("#btnMackAct").removeClass("easyui-button");
				jQuery("#btnMackAct").addClass("disabledButton");
			}
				*/
			return true;
		});
		
jQuery("#btnMackAct").click(function(){
	
	var gridURL = jQuery("#gridURL").val();
	var addRowId = new Array();
	var allRows = jQuery("#list").jqGrid('getDataIDs');
	console.log("All row IDs:", allRows); 

	/* for(var i=0;i<allRows.length;i++)
	{
		var rowData = jQuery("#list").jqGrid('getRowData',allRows[i]);
		console.log("Row Data:", JSON.stringify(rowData));
		 console.log("Row " + i + " tick value:", rowData.tickval);
		 
		 
//		if(rowData.tickval == "1")
	//	{	
		//	var keyIdVal = rowData.keyid;
			//addRowId.push(keyIdVal);
		//}
		
		
		//sriram 17-05-2025
		var checkbox = jQuery("#" + i + " td[aria-describedby='list_tickval'] input[type='checkbox']");
		// Check if it is checked
    	if (checkbox.prop("checked")) {
        // Get the keyid from the hidden column
        var keyId = jQuery("#" + i + " td[aria-describedby='list_keyid']").text().trim();
        addRowId.push(keyId);
    }
	} */
	
	
	//sriram added for loop 20-oct-2025
	for(var i = 0; i < allRows.length; i++) {
	    var rowId = allRows[i];
	    var rowData = jQuery("#list").jqGrid('getRowData', rowId);
	    
	    // Get the actual checkbox from the DOM in the grid
	    var checkbox = jQuery("#list").find("tr[id='" + rowId + "'] td[aria-describedby='list_tickval'] input[type='checkbox']");
	    
	    console.log("Row " + rowId + " - Checkbox found:", checkbox.length, "Checked:", checkbox.prop("checked"));
	    
	    if (checkbox.length > 0 && checkbox.prop("checked")) {
	        var keyId = rowData.keyid;
	        if(keyId) {
	            addRowId.push(keyId);
	            console.log("Added KeyID:", keyId);
	        }
	    }
	}

	console.log("Final selected KeyIDs:", addRowId);
	
	//sriram ended
	
	var keyIds = addRowId;
	var dataString = "keyIds = "+keyIds;
	//alert("Key Ids"+keyIds);
	if (keyIds == null  || keyIds.length <= 0){
		alert("Select Row");
	}
	else{
	var r=confirm("Are you sure to activate the record(s).");
	if (r==true)
	  {			
		processAjaxCalls('getrelated_getMakeActive.gnms' ,'keyIds='+keyIds, "generalMakeActive_successsCallback","listInactive_errorCallback");
		return true;
	  }
	else
		return false;
	}
	
});
		
		
function generalMakeActive_successsCallback(result)
{
		alert(result.successData.msg);
		var gridURL = jQuery("#gridURL").val();
		processGridnew(gridURL,"?q=2&rowStart=1&rowEnd=100&active=N&active1=N&chkHide=N","list","pager","","","","");
		return true;
	
	
}

</script>
	<div id="wrapperRpt">
	<div style="position:absolute;top:19;margin-left:1%"><span >
	<input type="button" style="height: 20px;" class="easyui-button" id="btnAct" name="btnAct" value="View Active">	
	<input type="button" style="height: 20px;" id="btnInact" name="btnInact" class="easyui-button" value="View Inactive"></input>
	<input type="button" style="height: 20px;" class="easyui-button" id="btnMackAct" name="btnMackAct" value="Make Active"></span></div>
			<div style="margin-top:45px;\0\margin-top:12px;">
				<table id="list" style="width:100%"><tr><td/></tr></table>
				 <div id="pager"></div>
			 </div>		
			 <input type="hidden" id="gridURL" value="${requestScope.masterGridURL }" />
			 <input type ="hidden" id="menuCaption" value="${requestScope.menuCaption }" />
			 <input type ="hidden" id="menuName" value="${requestScope.menuName }" />
			 <input type ="hidden" id="masterForm" value="${requestScope.masterForm}" />
			 <input type ="hidden" id="clickEnable" value="${requestScope.clickEnable}" />
	</div>
