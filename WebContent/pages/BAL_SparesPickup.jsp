<!-- Created By:Siddharth. A -->
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			
			var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
			if( prevDataUrl == null || prevDataUrl.length <=0)		
				viewGrid("Sparepickup_input.sprpckup","?row=0");
			else{
				viewGrid(unescape(prevDataUrl),"");
			}	
		
		jQuery('#submitForm').val('frmSparesPickup'); // set the id of form to submit
		initialiseForm('frmSparesPickup');		
		//viewGrid("Sparepickup_input.sprpckup","?row=0");
		
		fillComboBox("frmSparesPickup","cmbSprName","combo_partno.sprpckup" );
		   jQuery('#cmbSprName').combobox('resize', {width:250});
		numericTextBox("txtPspdQuantity");
		readOnlyFields('txtnoselectspr');
		readOnlyFields('txtDesc');
		
	});

	function viewGrid(url,filterString)
	{
		processGridnew(url,filterString,"sprspickupGrid","sprspickupPager","","selectSprGridRow","","","","");
	}
						
	function selectSprGridRow(id)
	{
		
		var lastSel =	jQuery('#txtSelectRowid').val();
 
	/*	jQuery('#txtnoselectspr').val(lastSel);
	   if(id && id!==lastSel)
	   { 
	   		jQuery('#sprspickupGrid').jqGrid('restoreRow',lastSel); 
	   		lastSel=id;
	  		jQuery('#txtSelectRowid').val(id); 
	   }
		*/	var ret = jQuery("#sprspickupGrid").getRowData(id);// alert(Object.keys(ret)); 
		
			var qty=ret.txtPspdQuantity;
			if(qty==''||qty==null)
				alert('Enter Quantity');
			
			if((qty.indexOf("<input")==-1)&& (qty!=''))
   	   		{
   	   		 	jQuery('#txtPspdQuantity').val(ret.txtPspdQuantity);
   	   			jQuery('#cmbSprName').combobox('setValue',ret.txtPspdSpareid);
				jQuery('#hdnPspdKeyid').val(ret.txtPspdKeyid);
				jQuery('#hdnPspdSpareid').val(ret.txtPspdSpareid);
				jQuery('#txtDesc').val(ret.txtDesc);
   	   		}  
	}
	function pmsdShowMessage(title, message, isError) {
	    alert((title ? title + ": " : "") + message);
	}
	function pmsdSave_errorCallBack(result) {
	    console.error("[pmsdSave_errorCallBack] FIRED. raw result:", result);
	    pmsdShowMessage("Error", "Data Not Saved", true);
	}
	function sprpkupcboxFormatter(id, options, rowObject)
	{
		var id = options.rowId;
		return '<input id="sprpkup_checkbox"  type="checkbox" ' + ' onclick="if(this.checked){checkBoxClick(\''+id + '\')}else{checkBoxUnchecked(\''+id+'\')}"/>';
	}

	function checkBoxClick(rowId)
	{
	
		var selRowIds = jQuery("#hdnSltedRowIds").val(rowId);
		
		if( selRowIds == " ")
			selRowIds = rowId +',';
		else
			selRowIds += rowId +',';
		
		jQuery("#hdnSltedRowIds").val(selRowIds);
		var value = jQuery('#txtpspdStandardid').val();
		jQuery("#sprspickupGrid").setCell(rowId,"txtPspdStandardid", value);	
	//	alert("selected rows="+(jQuery("#hdnSltedRowIds").val(selRowIds)).context);
		var selRowIdArr =selRowIds.split(",");//alert("selRowIdArr="+selRowIdArr);
		
		}


	function checkBoxUnchecked(id)
	{
		var selRowIds = jQuery("#hdnSltedRowIds").val();
		selRowIds = selRowIds.replace(id+',','');
		jQuery("#hdnSltedRowIds").val(selRowIds);
		clear();
	}

	function frmSparesPickupcmbSprName_onSelect(record)
	{
		jQuery('#hdnSpareName').val(record.text);
		processAjaxCalls("text_sprName.sprpckup?sprmKeyid="+record.id, "","SprNameOnSuccess","");
	}
	function frmSparesPickupcmbSprName_onClear()
	{
		jQuery('#txtDesc').val('');
	}

	//SPARES PICKUP FORM ONSUCCESS FUNCTION
	function frmSparesPickup_successsCallback(result)
	{
		if(result != null && result != "")
		{	
			//alert(result.successData.funcLocns);
			//jQuery('#sprspickupGrid').trigger("reloadGrid");
			
			processGridnew("Sparepickup_input.sprpckup","?row=0","sprspickupGrid","sprspickupPager","","selectSprGridRow","","","","");
			jQuery('#hdnfuncnSessionId').val(result.successData.funcLocns);
			processAjaxCalls("sprscheckcnt_input.sprpckup","","sprCount_OnSuccess","sprCount_OnError");
		}
	}
	
	/** DELETE ALL SUCCESS  **/
	function frmSparesPickup_deleteSuccessCallback(msg)
	{
		jQuery('#sprspickupGrid').trigger("reloadGrid");
	}
	//SPARES PICKUP FORM ONERROR FUNCTION
	function frmSparesPickup_errorCallback(status)
	{
		alert(Object.keys(status));
	}
	
	/**FUNCTION TO CHECK SPR COUNT IN FNLOCN **/
	function sprCount_OnSuccess(result)
	{
	//	alert(result.jsonObject);
		if(result.jsonObject==0)
		{
			var r=confirm("Selected Spares are not avaliable in Factory Layout,Do you want to add ?");
			if (r==true)
  			{
	  			var funcLocns= jQuery('#hdnfuncnSessionId').val();alert("after ok"+funcLocns);
				saveForm("frmGenFuncLocn","FunctionalLocn_input.fnlocn?funcLocns="+funcLocns);
			}
			else
			{
		  		//alert()	
			}
		
		}
	}

	//SPR COUNT ONERROR
	function sprCount_OnError()
	{
		alert("Error Occured while saving to Functional layout...");
	}

	//FUNCTION FOR SUCCESS CALLBACK FROM FNCN LOCATION
	function frmGenFuncLocn_successsCallback(result)
	{
		alert(result.successData.msg);
	}
	
	function SprmultiSelectCancel_CallBack(id)
	{
	

	}
	//SPR PICKUP BEFORE SUBMIT
	function frmSparesPickup_beforeSubmit()
	{
		var gridData  = '&SparesPickup='+JqGridToJsonSelectdRows('sprspickupGrid','sprpkup_checkbox','txtPspdSpareid');
		return gridData; 
	} 
	
	function beforeSubmitCellFunction(rowid, cellname, value, iRow, iCol)
	{
		if(value=="")
		{	
			alert('Enter Required Qty For Selected Spare');
		//	jQuery('#sprspickupGrid').setCell(rowid,cellname," ");
			return false;
		}
		else if(value=="0")
		{
			alert('Required Qty cannot be 0');
			return false;
		}
		else
		{		
			return true;
		}
	}
	
	
	function SprmultiSelectOk_Callback(id)
	{
		jQuery("#sprspickupGrid").setCell(id,"txtPspdQuantity", "1");	
	}
	
	jQuery('#btnAddNwSpr').click(function()
	{
		/*var url = jQuery("#sprspickupGrid").jqGrid('getGridParam', 'url');
		url = url.replace("Sparepickup_getData.sprpckup","Sparepickup_input.sprpckup");
		url = escape(url); 
		//navigateToNextForm("SparesMaster_input.sprmst","Spares Master");
		*/
		openMasterForm('SparesMaster_input.sprmst?q=2&closeOnSave=true',frmMode.create,'frmSparesMasterForm','Spares Master','mstFrm');		
		
		//navigateToNextForm("SparesMaster_input.sprmst","Spares Master",null,{"filterString":url});

	});
	function frmSparesMasterForm_beforeCloseCurrentForm()
	{	
		refreshForm();
		return false;
	}
	
	function clear()
	{
		jQuery('#cmbSprName').combobox('setValue','');
		jQuery('#txtPspdQuantity').val('');
		jQuery('#txtDesc').val('');
		jQuery('#hdnPspdSpareid').val('');
		jQuery('#hdnPspdKeyid').val('');
	}
	
	jQuery('#btnClear').click(function()
	{
		clear();
	});
							
	jQuery('#btnMultiple').click( function(){
		multiSelectPop("sprmultiselect_input.sprpckup","", "sprspickupGrid","","cmbSprName,txtDesc,txtPspdQuantity",true,"SprmultiSelectCancel_CallBack","SprmultiSelectOk_Callback","Spares Details");
	}); 
	
	/** FOR ADD BUTTON **/					
	jQuery('#btnAdd').click( function()
	{
		var sprName=jQuery('#hdnSpareName').val();
		var sprmId=jQuery('#cmbSprName').combobox('getValue');
		var sprCode=jQuery('#txtDesc').val();
		var sprMake='';
		var sprModel='';
		var txtPspdQuantity=jQuery('#txtPspdQuantity').val();
	
				if(jQuery('#txtPspdQuantity').val()=="" )
				{
					alert("Enter Quantity");
				} 
				else if(jQuery('#txtPspdQuantity').val()=='0')
				{
					alert("Required Qty For Spare Cannot be zero");
				}
				else if(jQuery('#cmbSprName').combobox('getValue')=="")
				{
					alert("Select Spares");
				}
				else
				{
					 var rowObject =[];
					  
					rowObject[0] = new Object();
					rowObject[0].key=sprmId;
					
					(rowObject[0])['txtPspdSpareid'] = sprmId;
					(rowObject[0])['cmbSprName'] = sprName;
					(rowObject[0])['txtDesc'] = sprCode;
					(rowObject[0])['sprMake'] = sprMake;
					(rowObject[0])['sprModel'] = sprModel;
					(rowObject[0])['txtPspdQuantity'] = txtPspdQuantity;
					
					 wrap = jQuery(rowObject);
		    		 var rowDataExist = jQuery("#sprspickupGrid").getRowData(sprmId);
					
					if( ! isEmpty(rowDataExist))
					{
						 	var option=confirm("Data Already Exists...  Do You Want To Update? ");
							if(option==true)
							{
								rowDataExist['txtPspdQuantity'] = txtPspdQuantity;
								jQuery("#sprspickupGrid").setRowData(sprmId,rowDataExist,true);
							}
							else
							{//do nothing
							}
					}	
					else
					{
						jQuery("#sprspickupGrid").addRowData(sprmId,rowObject,'last',sprmId);
						jQuery('tr[id=undefined]').attr('id',sprmId);
					}	
		       		 clear();
				}
							
	});



	/** FOR DELETE BUTTON **/		
	/** CHECKBOX CLICK - keeps a comma separated list of ticked row ids **/
function checkBoxClick(rowId)
{
	var selRowIds = jQuery("#hdnSltedRowIds").val();

	if (selRowIds == null || jQuery.trim(selRowIds) == "")
		selRowIds = rowId + ',';
	else
		selRowIds += rowId + ',';

	jQuery("#hdnSltedRowIds").val(selRowIds);

	var value = jQuery('#txtpspdStandardid').val();
	jQuery("#sprspickupGrid").setCell(rowId, "txtPspdStandardid", value);
}

function checkBoxUnchecked(id)
{
	var selRowIds = jQuery("#hdnSltedRowIds").val();
	selRowIds = selRowIds.replace(id + ',', '');
	jQuery("#hdnSltedRowIds").val(selRowIds);
	clear();
}

/** RETURNS ALL ROWS WHOSE "Select" CHECKBOX IS TICKED **/
function getCheckedSpareRows()
{
	var rows = [];
	jQuery('#sprspickupGrid tr.jqgrow').each(function()
	{
		// attribute selector is used because every row's checkbox has the same id
		if (jQuery(this).find('input[id="sprpkup_checkbox"]').is(':checked'))
		{
			var rid  = this.id;
			var data = jQuery('#sprspickupGrid').getRowData(rid);
			rows.push({ rowid: rid, keyid: data.txtPspdKeyid, spareid: data.txtPspdSpareid });
		}
	});
	return rows;
}

/** FOR DELETE BUTTON **/
jQuery('#btnDelete').click(function()
{
	var rowsToDelete = [];
	var spareId = jQuery('#hdnPspdSpareid').val();

	if (spareId != null && spareId != "")
	{
		// row was double-clicked and loaded into the form
		rowsToDelete.push({ rowid: spareId, keyid: jQuery('#hdnPspdKeyid').val() });
	}
	else
	{
		// otherwise use the ticked checkboxes
		rowsToDelete = getCheckedSpareRows();
	}

	if (rowsToDelete.length == 0)
		alert("Select Spare to delete");
	else
		show_confirm(rowsToDelete);
});

function show_confirm(rowsToDelete)
{
	if (!confirm("Are you Sure you want to Delete the Selected Spare"))
		return;

	jQuery.each(rowsToDelete, function(i, r)
	{
		if (r.keyid != null && r.keyid != "")
		{
			// saved row - delete from DB
			processAjaxCalls("sparespickup_delete.sprpckup", "pspdKeyid=" + r.keyid,
							 "Sprpickupdelete_onSuccess", "Sprpickupdelete_onError");
		}
		else
		{
			// newly added row (not saved yet) - remove from grid only
			jQuery("#sprspickupGrid").delRowData(r.rowid);
		}
	});

	jQuery("#hdnSltedRowIds").val('');
	clear();
}
	//DELETE SUCCESS
	function Sprpickupdelete_onSuccess(result)
	{
		var rowid= result.OnDelete.DeletedPspKeyid;
		alert(result.OnDelete.msg);
		jQuery("#sprspickupGrid").delRowData(rowid);
		clear();
		processGridnew("Sparepickup_input.sprpckup","?row=0","sprspickupGrid","sprspickupPager","","selectSprGridRow","","","","");
			
	}
	
	//DELETE ERROR
	function Sprpickupdelete_onError(result)
	{
		alert(Object.keys(result));
	}
		
	jQuery('#txtPspdQuantity').keyup(function(event) {
		    var message =  jQuery('#txtPspdQuantity').val();
		    if((message==0)&&(message!="")){
		    alert("Required Qty For Spare Cannot be zero");
		  } else {
		    return true;
		  }
	});	

	function SprNameOnSuccess(result)
  	{
  		jQuery('#txtDesc').val(result.jsonObject);
    }

/*function numericColumnFormatter(cellvalue, options, rowObject) {	
		alert("formaterer");
		alert(cellvalue);
		alert(Object.keys(rowObject));
		alert(Object.keys(options));
		alert(options.rowid);
		alert("gid="+options.gid);
		alert("pos="+options.pos);
		alert("colModel= "+options.colModel);
		alert("colModel= "+Object.keys(options.colModel));
		alert("rg="+options.colModel.index);
		var rowIds = jQuery('#sprspickupGrid').jqGrid().getDataIDs();alert("rowIds="+rowIds.length);
		alert(i+""+"_"+txtPspdQuantity);
		for(var i=1;i<=rowIds.length;i++)
		{
			alert(i+""+_txtPspdQuantity);
			if (jQuery("#"+i+"_"+"txtPspdQuantity").val()=="0")
			{
				alert('jjyj');
						
			}
		}
		var numericStr  = '<span ' ;
	//	if(jQuery('#1_txtPspdQuantity').val()=="0")
	//		alert("nez");
			
	//	if(txtPspdQuantity == "" || txtPspdQuantity == '0' )
	//		alert("Please Enter Qty");
		numericStr  +=  '</span>';
		return numericStr.trim() == '</span>'?" ": numericStr;
	}


function keyUpFn (){

	 	alert('key pressed');
	 }


/*		function numericColumnFormatter(id, options, rowObject)
	{
		var id = options.rowId;
		alert("id="+id);
		alert(Object.keys(rowObject));
	}*/
	jQuery('#btnAddFctLyt').click (function (){
		alert(8);
	});
</script>

<form name="frmSparesPickup" id="frmSparesPickup" action="Sparepickup_input.sprpckup" method="post">
	<div id=""> 
	<div class="" style="width: 93%;margin: 0 auto;">
	<table rules="none" border="0">
		<tr>

			<td class="valigncnt" style="width:40%;">
<!--				<div class="floatleft" style="padding: 2%;padding-right: 12%"></div>-->
					<div class="floatleft" style="padding-right: 12%">
						<div style="margin-top: 7%;"><label class="mandatory-lbl">Spare Name</label></div>
						<div style="margin-top: 1%;">
							<span><!-- <input type="text" id="cmbSprName" name="cmbSprName" class="easyui-combobox" style=" width : 250%;"/> -->  <input type="text" id="cmbSprName" name="cmbSprName" class="easyui-combobox" style="width: 250px;"/></span>
							<span>	<input type="button" id="btnMultiple" class="easyui-button" value="Multiple" style="height: 2%"/></span>
						</div>
						<div style="margin-top: 0%;"><label class="mandatory-lbl">Required Quantity</label></div>
						<div style="margin-top: 1%; width: 120%">
								<span><input type="text" class="easyui-text" id="txtPspdQuantity" value=""  style="width: 33%;"/></span>
								<span><input type="button" id="btnAdd" class="easyui-button" value="Add"style="height: 2%"/></span>
								<span><input type="button" id="btnDelete" class="easyui-button" value="Delete"style="height: 2%"/></span>
								<span><input type="button" id="btnClear" class="easyui-button" value="Clear"style="height: 2%"/></span>	
								<span><input type="hidden" id="hdnPspdKeyid" value="" /></span>
								<span><input type="hidden" id="hdnPspdSpareid" value=""/></span>
						</div>
						
						
					</div><!-- End of left pane -->
					
					<div class="floatleft">
								<div style="margin-top: 17%"><label>Description</label></div>
								<div style="margin-top: 2%"><input type="text" class="easyui-text" id="txtDesc" style="width: 200%;"/></div>
						</div>
					
					<div class="clear"></div>
					<div class="floatleft" style="padding-right: 87%">&nbsp;</div>
					<div class="floatleft">
							<span><input type="button" id="btnAddFctLyt"  class="easyui-button" value="Add to Factory Layout"style="height: 2%"/></span>
							<span><input type="button" id="btnAddNwSpr"  class="easyui-button" value="Add New Spare"style="height: 2%"/></span>
					</div>
					<div class="clear"></div>
					
					<table rules="none" border="0" class="cntborder" style="margin: 0%">
						<tr>

							<td class="valigncnt" style="width:40%;">
							<div class="notes">Double Click the data row to Edit/Delete</div>
										<div class="clear"></div>
										
								<div>	
										<!-- <div class="sub-header"><label>Spare Pickup List</label></div> -->
										<div style="margin-top: 0%;margin-bottom: 0%;">
									   <!-- <span><input type="checkbox" /></span>
											<span style="padding-right: 12%"><label>Select/Deselect All</label></span> -->
										
										</div>
										
										<div id="griddiv" style="float: left;margin-top:-1%"><!-- Grid -->
											<table id="sprspickupGrid" width="80%" style="float: left;"></table>
											<div id="sprspickupPager"></div> 
										
											<div class="floatleft" style="width:220%;margin-top: 0%">
										<!-- 	<span><input type="button" id="btnOk"  class="easyui-button" value="Ok" style="height: 2%"/></span>
												<span style="padding-right: 27%"><input type="button" id="btnCancel" class="easyui-button"  value="Cancel" style="height: 2%"/></span>
										 		<span ><label>No. of Selected Spares</label></span>
												<span><input type="text" class="easyui-text" id="txtnoselectspr" /></span> -->
												<input type="hidden" id="txtSelectRowid" value=""/>
												<input type="hidden" id="hdnSprkeyid" value=""/>
												<input type="hidden" id="hdnSltedRowIds" value=""/>
												<input type="hidden" id="txtpspdStandardid" value="${requestScope.plmTlSparedtl.pspdStandardid}"/>
												<input type="hidden" id="hdnfuncnSessionId" value=""/>
												<input type="hidden" id="hdnSpareName" value=""/>
												<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>	
 											</div>
											<div class="floatright" style="margin-top: 1%;width: 90%">
											</div>
										 
										</div>
								</div>
							</td>
						</tr>
					</table>	
			</td>
		</tr>
	</table>
</div>
</div>
<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
</form>

<form id="frmGenFuncLocn" name="frmGenFuncLocn">
	
</form>
		