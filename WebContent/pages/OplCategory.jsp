<!-- Created By:Siddharth.A -->
<script type="text/javascript">
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
		//jQuery("#ImgSave").attr({"disabled":true});
		//jQuery('#ImgSave').unbind('click');
		//jQuery("#ImgDelete").attr("disabled","disabled");

		
	viewGrid("oplPillar_input.oplcat","?row=0","pillar");

	viewGrid("oplCategory_input.oplcat","?row=0","category");
	//jQuery('.ui-widget ui-widget-content ui-corner-all ui-jqdialog').css("opacity", 1);

	
	
});

function viewGrid(url,filterString,oplcategory)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "";

		if(oplcategory=="pillar")
		{
			processGridnew(url,filterString,"pillarGrid","pillarPager",tableCaption,"","","loadCompletePillarGrid");
				
		}
		else if(oplcategory=="category")
		{
			processGridnew(url,filterString,"oplCatgryGrid","oplCatgryPager",tableCaption,"","","CategoryGridOnLoad");
		}

			jQuery('#pillarGrid').jqGrid
		({
		    rowList: [],        // disable page size dropdown
		    pgbuttons: false,     // disable page control like next, back button
		    pgtext: null,         // disable pager text like 'Page 0 of 10'
		    viewrecords: false    // disable current view record text like 'View 1-10 of 100' 
		});
	}	
}

function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
  //return '<input type="checkbox"' + (cellvalue ? ' checked="checked"' : '') + 
   //   'onclick="alert(' + options.rowId + ')"/>';
	return '<input id="my_checkbox"  type="checkbox" ' + ' onclick="if(this.checked){checkBoxClick(\''+id + '\');}else{uncheckBoxClick(\''+id + '\')}"/>';
}

function checkBoxClick(id)
{
		var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();

		for(var i=0;i<rowIds.length;i++){
			
			if( rowIds[i] != id )
			 	jQuery("#pillarGrid").setCell(rowIds[i],"my_checkbox","False");
		}
		jQuery("#txtOpltpmpillarid").val(id);
			
		processGridnew("oplCategory_input.oplcat","?pillarRowid="+ id,"oplCatgryGrid","oplCatgryPager","","","","CategoryGridOnLoad");

		jQuery("#oplCatgryGrid").jqGrid().setGridParam({editurl :'createCategory_input.oplcat'});

}

function uncheckBoxClick(id)
{
	var rowIds = jQuery('#oplCatgryGrid').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++){
		
		if( rowIds[i] != id )
		{
		 	jQuery("#oplCatgryGrid").setCell(rowIds[i],"txtOplcName"," ");
			jQuery("#oplCatgryGrid").setCell(rowIds[i],"txtOplcCode"," ");
		}
	}
}

function onloadCallback(status){}
function loadCompletePillarGrid()
{
	jQuery(":input[type=checkbox]").removeAttr('disabled');
}

function CategoryGridOnLoad()
{
	jQuery('#oplCatgryGrid').jqGrid('setGridParam', {cellEdit: false});	 
}

function onSelectPillarRow(categoryRowid)
{
	//alert("selected row"+categoryRowid);
}


function validateFilterSelection(filterString){
	return  true;
}



jQuery('#btnRemove').click(function ()
{
	var CatgryGridCnt=jQuery('#oplCatgryGrid').getGridParam('reccount');
	if((CatgryGridCnt>0)&&(jQuery(":input[type=checkbox]").is(':checked')))
	{
		var rowid = jQuery("#oplCatgryGrid").jqGrid('getGridParam', 'selrow');
		if(rowid!=null)
			show_confirm(rowid);
	}
});


function show_confirm(rowid)
{
var r=confirm("Are you Sure you want to Delete this Record..");
	if (r==true)
  	{
		var pillarId =jQuery('#txtOpltpmpillarid').val();
		processAjaxCalls("oplCategory_deleteData.oplcat","txtOplcKeyid="+ rowid +"&txtOplcTpmpillarid="+pillarId,"oplccategoryRemove_onSuccess","oplccategoryRemove_onError");
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
		jQuery("#oplCatgryGrid").delRowData(result.oplcCategoryid);	
	
}

function oplccategoryRemove_onError(status)
{
	alert(Object.keys(status));
	alert(status.responseText);
	showCommonErrorMsg(status.error);
}

function checkPara(para,id)
{
		var val=null;
	
		if(para=="edit")
		val=id;
		else if(para=="add")
		val="new";
	
		var tpmPillarid=jQuery('#txtOpltpmpillarid').val();

		 jQuery("#oplCatgryGrid").jqGrid('editGridRow',val,{
			height:150,
			width:300,
			reloadAfterSubmit:true,
			top : 80,
			left:30,
			modal: false,
			drag: true,

			beforeSubmit:function()
			{
			  var  msg ;	
			  var val;
			  var txtOplcName = jQuery("#txtOplcName").val();	
			  var txtOplcCode = jQuery("#txtOplcCode").val();
			
			  if((txtOplcName==null||txtOplcName==""))
				 msg = [false,"Enter Name"];
	 		  else if( (txtOplcCode==null||txtOplcCode==""))
	 			 msg = [false,"Enter Code"];
	 		  else
	 			 msg = [true,null];
	 			  	
		 	   return  msg; 
			},
			onInitializeForm:function()
			{
				jQuery('#txtOplcName').attr('maxlength','75');
				jQuery('#txtOplcCode').attr('maxlength','25');	
				jQuery('.ui-widget ui-widget-content ui-corner-all ui-jqdialog').css("opacity", 1);
				jQuery('.ui-jqdialog-content .CaptionTD').css("width","18px");
				jQuery('.ui-jqdialog-content .DataTD').css("padding","0.6px");
				jQuery('#txtOplcName').css("width","160px");
				jQuery('#txtOplcCode').css("width","160px");
				
			}
		//	afterSubmit:
					
		});
																						 							
	 jQuery("#oplCatgryGrid").jqGrid().setGridParam({editurl :'createCategory_save.oplcat?txtOplcTpmpillarid='+tpmPillarid},
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
	if(jQuery(":input[type=checkbox]").is(':checked'))
		checkPara("add");
	else
		alert('Select Pillar');
});

jQuery("#btnEdit").click(function()
{
	var row_id = jQuery("#oplCatgryGrid").jqGrid('getGridParam','selrow');
	
	 if( row_id != null )
	 { 
		 checkPara("edit",row_id);
	 }
	 else 
	 {
		 if(jQuery('#oplCatgryGrid').getGridParam('reccount')>0)
		 	alert("Select Row");
	 }
});

 
jQuery("#ImgSave").click(function()
{
	return false;
	saveForm("frmoplCategory","createCategory_input.oplcat");
	
});
</script>

<form id="frmoplCategory" name="frmoplCategory" action="oplCategory_create.opl" method="post"> 

<table class="main-cntborder" style="border: 0px solid #a4a4a4;">
	<tr>
		<td style="width:50%;padding-left:50px;margin-top: 12px;" valign='top'  class="easyui-paddingbfpx cntborder">
			<div class="floatleft" style="width: 523px;height: 300px;padding-right: 15px;margin-top:14px;text-align:left;padding-left:30px;">
					<label class="notes">Select Target Pillar to View OPL Category</label>
					
					<div id="griddivPillar" style="float: left;margin:1px;"> 
						 <table id="pillarGrid" style="width:100%"><tr><td/></tr></table>
					 	 <div id="pillarPager"></div> 
					</div>
			</div>
			<div style="clear: left;"></div>
		</td>	
		<td><div class="floatleft" style="padding-right: 20px;"></div></td>
		<td  style="width:50%;padding-left:50px" valign='top'  class="easyui-paddingbfpx cntborder">
			<div class="floatright" style="width:500px;height: 200px;" align="right">
					<div style="width: 400px;">
					<!-- <label class="notes">Double Click data row and Click Remove for Deletion</label> -->
					<input type="button" id="btnRemove" name="btnRemove" class="easyui-button"  value="Remove" />
					<input type="button" id="btnAdd" name="btnAdd" class="easyui-button" value="Add New Row"/>
 					<input type="button" id="btnEdit" name="btnEdit" class="easyui-button" value="Edit Row" />
 			
 					</div>
					
					<div id="griddivCategory" style="float: left;margin:1px;"> 
						 <table id="oplCatgryGrid" style="width:100%"><tr><td/></tr></table>
					  <div id="oplCatgryPager"></div> 	
					</div>

			</div>
		</td>
	</tr>	
</table>
<input type="hidden" id="txtSelectRowid" value="k" >
<input type="hidden" id="txtOpltpmpillarid" value=""/>
</form>

