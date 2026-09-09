<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	

			viewGrid("kznPillar_input.kcat","?row=0","pillar");
			viewGrid("kznCategory_input.kcat","?row=0","category");
			
	});

function viewGrid(url,filterString,kzncategory)
{
		var tableCaption = "";

		if(kzncategory=="pillar")
		{
			processGridnew(url,filterString,"categoryGrid","pillarPager",tableCaption,"","","loadCompletePillarGrid");
		}
		else if(kzncategory=="category")
		{
			processGridnew(url,filterString,"kznCatgryGrid","kznCatgryPager",tableCaption,"","","CategoryGridOnLoad");
		}

}
function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
	return '<input id="my_checkbox" name="my_checkbox"  type="checkbox" ' + ' onclick="if(this.checked){checkBoxClick(\''+id + '\');}else{uncheckBoxClick(\''+id + '\')}"/>';
}
function btnFormatter(id, options, rowObject)
{
	var id = options.rowId;
	return '<input type="button" id="my_button" name="my_button" class="easyui-button" value="..." onclick="subCatBtn(\''+ id + '\');" style="height:15px;"/>';
}

function subCatBtn(rowId)
{	
	jQuery('#txtKscmKctmKeyid').val(rowId);
	processGridnew("subCategory_input.kcat?kscmKctmKeyId="+rowId,"&q=0","subCatGrid","subCatPager","","","","SubGridOnLoad");
	jQuery( "#dialog" ).show();
	jQuery( "#dialog" ).dialog
	({
		autoOpen: false,
		modal: true,
		height: 450,
		width: 500		
	});	 
		
	//multiSelectPop("subCategory_input.kcat","ksmKeyId%3D"+rowId, "subCatGrid",rowId,"txtKzplKzncategoryid,kznCategory",false,"MultiSelectCancel_CallBack");
}

function checkBoxClick(id)
{
		var rowIds = jQuery('#categoryGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++){
			
			if( rowIds[i] != id )
			 	jQuery("#categoryGrid").setCell(rowIds[i],"my_checkbox","False");
		}
		jQuery("#txtKzntpmpillarid").val(id);
			
		processGridnew("kznCategory_input.kcat","?pillarRowid="+ id,"kznCatgryGrid","kznCatgryPager","","","","CategoryGridOnLoad");

		jQuery("#kznCatgryGrid").jqGrid().setGridParam({editurl :'createCategory_input.kcat'});
}

function uncheckBoxClick(id)
{
	jQuery("#kznCatgryGrid").clearGridData();
}

function onloadCallback(status){}

function CategoryGridOnLoad()
{
	jQuery('#kznCatgryGrid').jqGrid('setGridParam', {cellEdit: false});	//,editable:true	  	
}

function SubGridOnLoad()
{
	jQuery('#subCatGrid').jqGrid('setGridParam', {cellEdit: false});		  	
}


function onSelectPillarRow(categoryRowid)
{
	//alert("selected row"+categoryRowid);
}

jQuery('#btnRemove').click(function ()
{
	var CatgryGridCnt=jQuery('#kznCatgryGrid').getGridParam('reccount');
	if((CatgryGridCnt>0)&&(jQuery(":input[type=checkbox]").is(':checked')))
	{
		var rowid = jQuery("#kznCatgryGrid").jqGrid('getGridParam', 'selrow');
		if(rowid!=null)
			show_confirm(rowid);
	}
});

function show_confirm(rowid)
{
var r=confirm("Are you Sure you want to Delete this Record..");
	if (r==true)
  	{
		var pillarId =jQuery('#txtKzntpmpillarid').val();
		processAjaxCalls("kznCategory_deleteData.kcat","txtKctmKeyid="+ rowid +"&txtKctmTpmpillarid="+pillarId,"oplccategoryRemove_onSuccess","oplccategoryRemove_onError");
	}
	else
 	{
   	}
}
function oplccategoryRemove_onSuccess(result)
{
	if( result.tpmException != null )
	{
		showCommonErrorMsg(result.tpmException);
	}
	else
		jQuery("#kznCatgryGrid").delRowData(result.kctmKeyid);	
	
}

function oplccategoryRemove_onError(status)
{
	showCommonErrorMsg(status.error);
}

function checkPara(gridId,para,id)
{
		var val=null;
	
		if(para=="edit")
		val=id;
		else if(para=="add")
		val="new";
	
		var tpmPillarid=jQuery('#txtKzntpmpillarid').val();
		var txtKscmKctmKeyid =jQuery('#txtKscmKctmKeyid').val();
		var txtKscmKeyid = jQuery('#subCatGrid').getCell(id,'txtKscmKeyid');
	
		 jQuery("#"+gridId).jqGrid('editGridRow',val,{
		//	height:120,
			width:330,
			reloadAfterSubmit:true,
			//top : 80,
			//left:30,
			height:170,
			top : 70,
			left:20,
			drag: true,
			opacity:1,
			closeOnEscape:true,
			autoOpen: false,
			//modal: true,
			beforeSubmit:function()
			{
			  var  msg ;	
			  var val;
			  var txtKctmName = jQuery("#txtKctmName").val();	
			  var txtKctmCode = jQuery("#txtKctmCode").val();
			  var txtKscmSubcatname = jQuery("#txtKscmSubcatname").val();
			  var txtKscmSubcatcode = jQuery("#txtKscmSubcatcode").val();
			  
				if(gridId=="kznCatgryGrid")
				{
				  if((txtKctmName==null||txtKctmName==""))
					 msg = [false,"Enter Name"];
	 			  else if( (txtKctmCode==null||txtKctmCode==""))
	 				 msg = [false,"Enter Code"];
	 			  else
	 				 msg = [true,null];
	 			  	
		 		   return  msg; 
				}

				else if(gridId=="subCatGrid")
				{
				  if((txtKscmSubcatname==null||txtKscmSubcatname==""))
					 msg = [false,"Enter Subcategory Name"];
		 		  else if( (txtKscmSubcatcode==null||txtKscmSubcatcode==""))
		 			 msg = [false,"Enter Code"];
		 		  else
		 			 msg = [true,null];
		 		 	
			 	   return  msg; 
				}
			},
			onInitializeForm:function()
			{
				jQuery('#txtKctmName').attr('maxlength','75');
				jQuery('#txtKctmCode').attr('maxlength','25');	
				jQuery('#txtKscmSubcatname').attr('maxlength','100');
				jQuery('#txtKscmSubcatcode').attr('maxlength','30');

				jQuery('.ui-jqdialog-content .CaptionTD').css("width","18px");
				jQuery('.ui-jqdialog-content .DataTD').css("padding","0.6px");
				jQuery('#txtKctmName').css("width","150px");
				jQuery('#txtKctmCode').css("width","150px");
				jQuery('#txtKscmSubcatname').css("width","150px");
				jQuery('#txtKscmSubcatcode').css("width","150px");
			}
					
		});
												 
		// jQuery("#"+gridId).jqGrid().setGridParam({editurl :'createCategory_save.kcat?txtKctmTpmpillarid='+tpmPillarid},
		jQuery("#"+gridId).jqGrid().setGridParam({editurl :'createCategory_save.kcat?txtKctmKeyid='+id+'&txtKctmTpmpillarid='+tpmPillarid+'&gridId='+gridId+'&txtKscmKctmKeyid='+txtKscmKctmKeyid+'&txtKscmKeyid='+txtKscmKeyid},										 
		 function(response, status,result, xhr)
		  {
			   if (status == "error") 
			  {
			       var msg = "Sorry but there was an error: ";
			       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		      }
			   else 
		   			alert(result.responseText);
		});	
}

jQuery("#btnAdd").click(function()
{
	 var checked =jQuery("input:checked[name='my_checkbox']").length >0;
	if(checked)
		checkPara("kznCatgryGrid","add");
	else
		alert('Select Pillar');
});

jQuery("#btnEdit").click(function()
{
	var row_id = jQuery("#kznCatgryGrid").jqGrid('getGridParam','selrow');
	 if( row_id != null )
	 { 
		 checkPara("kznCatgryGrid","edit",row_id);
	 }
	 else 
	 {
		 if(jQuery('#kznCatgryGrid').getGridParam('reccount')>0)
		 	alert("Select Row");
	 }
});

jQuery("#ImgSave").click(function()
{
	return false;
		
});

/** CODE FOR SUBCATEGORY **/

jQuery("#btnAddsubCat").click(function()
{
	checkPara("subCatGrid","add");
	
});

jQuery("#btnEditsubCat").click(function()
{
	var row_id = jQuery("#subCatGrid").jqGrid('getGridParam','selrow');
	 if( row_id != null )
	 { 
		 checkPara("subCatGrid","edit",row_id);
	 }
	 else 
	 {
		 if(jQuery('#subCatGrid').getGridParam('reccount')>0)
		 	alert("Select Row");
	 }
});

jQuery('#btnRemovesubCat').click(function ()
{
	var subCatGridCnt=jQuery('#subCatGrid').getGridParam('reccount');
	var rowid = jQuery("#subCatGrid").jqGrid('getGridParam', 'selrow');
	
		if(rowid!=null && rowid.length>0)
			show_subCatconfirm(rowid);
});


function show_subCatconfirm(rowid)
{
	var r=confirm("Are you Sure you want to Delete this Record..");
	if (r==true)
	{
		var txtKscmKeyid =getGridCell("subCatGrid",rowid,"txtKscmKeyid");
		var kscmKctmKeyidId =jQuery('#txtKscmKctmKeyid').val();
		processAjaxCalls("subCategory_deleteData.kcat","txtKscmKeyid="+ txtKscmKeyid +"&txtKscmKctmKeyid="+kscmKctmKeyidId+'&gridRowid='+rowid,"KznsubcategoryRemove_onSuccess","KznSubcategoryRemove_onError");
	}
}
function KznsubcategoryRemove_onSuccess(result)
{
	if( result.tpmException != null )
	{
		showCommonErrorMsg(result.tpmException);
	}
	else
		jQuery("#subCatGrid").delRowData(result.oplcCategoryid);	

	 jQuery('#subCatGrid').trigger("reloadGrid"); 		
}

function KznSubcategoryRemove_onError(status)
{
	showCommonErrorMsg(status.error);
}

/** -----------------------------------  **/
</script>
<form id="kznCatgory">
<div id="dialog" title="Sub Category" style="display:none;">

<table border="0" width="80%" align="center">
		<tr>
			<td colspan="2" align="right">
				<input type="button" id="btnRemovesubCat" name="btnRemovesubCat" class="easyui-button"  value="Remove"/>
				<input type="button" id="btnAddsubCat" name="btnAddsubCat" class="easyui-button" value="Add New Row"/>
 				<input type="button" id="btnEditsubCat" name="btnEditsubCat" class="easyui-button" value="Edit Row"/>
 			
			</td>
		</tr>
		<tr>
			<td>
				 <table id="subCatGrid" style="width:100%"><tr><td/></tr></table>
				 <div id="subCatPager"></div>
			 </td>
		</tr>
</table>

			 
</div>


<div class="" style="padding-left:30px;">
	
	<table border="0" width="80%" align="center">
		<tr>
			<td colspan="2" align="right">
				<input type="button" id="btnRemove" name="btnRemove" class="easyui-button"  value="Remove"/>
				<input type="button" id="btnAdd" name="btnAdd" class="easyui-button" value="Add New Row"/>
 				<input type="button" id="btnEdit" name="btnEdit" class="easyui-button" value="Edit Row"/>
 			
			</td>
		</tr>
		<tr>
			<td>
				 <table id="categoryGrid" style="width:100%"><tr><td/></tr></table>
				 <div id="pillarPager"></div>
			 </td>
			 <td>
				  <table id="kznCatgryGrid" style="width:100%"><tr><td/></tr></table>
				  <div id="kznCatgryPager"></div>
			 </td>
		</tr>
	</table>


	<div class="clearfix"></div>
	<input type="hidden" id="txtKzntpmpillarid" value=""/>
	<input type="hidden" id="txtKscmKctmKeyid" value=""/>
</div>

</form>