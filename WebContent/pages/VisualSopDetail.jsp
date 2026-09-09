

<script type="text/javascript"><!--
jQuery(document).ready(	function() {
	
			initialiseForm('frmVisualSoppop');
			/*jQuery('#frmVisualSoppop .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmVisualSoppop textarea').css('text-transform', 'uppercase');	*/
			
			btnDefaults();
			var keyId=jQuery('#txtVsodVsomKeyid').val();
			var mode =jQuery("#txtmode").val();
			var imgUrl =jQuery("#imgVsodImgtoolusedFilename").val();
			
			if(mode=="modify"){
				jQuery('#imgVsodImgtoolused').attr('src',imgUrl);
			btnUpdate();
			}	
						viewGrid("VisualSopDetail_input.VisualSop","q=2&keyId="+keyId);	
						processGridnew("PPEDetail_input.VisualSop","q=2&keyId="+keyId,"PPEgrid","test");	
				 imageUpload(jQuery( "#dlgImg" ),'ImageUpload.commonFilter?','dlgImg',"imgVsodImgtoolused","imgVsodImgtoolusedFilename","300","200");


				 
				 
				/*jQuery("#imgVsodImgtoolused").load(function() {
					if((jQuery(this).width()>415)||(jQuery(this).height()>264))
					{	jQuery('#imgVsodImgtoolused').attr('src', "");
						alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
						return false;
					}
			    });*/
			
						
				});


function txtFormatterToolimg(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	var gid=options.gid;
	var imageUrl=rowObject[4];
	//alert(imageUrl);
	idval='imghand_';	
	
	
	var prorpertyHover='gotMousehover(this.id,'+id+')';
	var prorpertyleave='gotMouseleave(this.id)';
	
	if(columnNo==4){ 
		
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'"   src="'+imageUrl+'" align="middle" width="100px" height="20px" style="cursor: pointer;" />';
		}else{
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'" src="'+imageUrl+'"   width="100px" height="20px" align="middle"  style="cursor: pointer"/>';
	}
}
function txtFormatterimg(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	var gid=options.gid;
	var imageUrl=rowObject[4];
	//alert(gid);
	//alert("imageUrl::"+imageUrl.trim().length);
	//alert("Img Url:::"+imageUrl);
	//alert("options:::::"+Object.keys(options));
//alert("colname::colno"+columnName+"::::"+columnNo);if(imageUrl.trim().length)
	idval='imghand_';
	var prorpertyHover="";
	var prorpertyleave="";
	if(gid=="VisualSop"){
		 prorpertyHover='gotMousehover(this.id,'+id+')';
		 prorpertyleave='gotMouseleave(this.id)';
	}
	if(columnNo==6){ 
		
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'"   src="'+imageUrl+'" align="middle" width="100px" height="20px" style="cursor: pointer;" />';
		}else{
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'" src="'+imageUrl+'"   width="100px" height="20px" align="middle"  style="cursor: pointer"/>';
	}
}		
function viewGrid(url,dataString)
{
	processGridnew(url,dataString,"VisualSopgrid", "pagergrid","test","DuubleclickPopup");
	//processGridnew("PPEDetail_input.VisualSop","q=2","PPEgrid","test");
}
function formattercheckbox(id, options, rowObject){

	var rowId = options.rowId;
	var colId = options.pos;	
	
	//return '<input type="checkbox" id="chkppeDtl_'+rowId+'_'+colId+'" name="chkppeDtl_'+rowId+'_'+colId+'" style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	return '<input id="list_checkbox_'+rowId+'" name="list_checkbox" '+ (rowObject[0]=="0" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
 {
	
	jQuery("#PPEgrid").jqGrid('setCell',rowId,'CHECKVAL','1');
 }

function chkboxUnCheck(rowId)
 {  
	
	jQuery("#PPEgrid").jqGrid('setCell',rowId,'CHECKVAL','0');
 }

function frmKnowClear()
{
	 jQuery('#imgVsodImgtoolused').attr('src', "");
}
function DuubleclickPopup(id)
{	
	btnUpdate();
	ClearPPEGrid();
	var rowData = jQuery("#VisualSopgrid").jqGrid('getRowData',id);
	var keyId = rowData.vsod_keyid;
	
	//alert("keyId"+keyId);

	var instruction = rowData.Instruction;
	
	//alert("instruction"+instruction);
	var keyPoint = rowData.KeyPoint;
	//alert("keyPoint"+keyPoint);
	var importanceOfKeyPoint = rowData.ImportanceOfKeyPoint;
	//alert("importanceOfKeyPoint"+importanceOfKeyPoint);
	var toolUsed = rowData.ToolUsed;
	//alert("toolUsed"+toolUsed);
	var toolid = rowData.PPE;
	//alert("toolid"+toolid);
	var imgId ="imghand_"+6+'_'+id;	
	var imgurl=jQuery("#"+imgId).attr("src");	
	setFieldValue('txtVsodKeyid',keyId);
	//setFieldValue('txtvsodImgppe',toolid);
	setFieldValue('txtVsodInstruction',instruction);
	setFieldValue('txtVsodKeypoint',keyPoint);
	setFieldValue('txtVsodImportanceofkeypoint',importanceOfKeyPoint);
	setFieldValue('txtVsodToolused',toolUsed);
	setFieldValue('imgVsodImgtoolusedFilename',imgurl);
	jQuery('#imgVsodImgtoolused').attr('src',imgurl);	
	FillPPEgrid(toolid);
}

jQuery( "#btnImgClear" ).click(function() {
	jQuery('#imgVsodImgtoolused').attr('src', "images/tools.jpg");
	jQuery('#imgVsodImgtoolusedFilename').val("");
	});

function FillPPEgrid(toolid)
{   
	
	var ppearr= toolid.split(",");
	
	var row=jQuery("#PPEgrid").getRowData();
	

	for(var i=1;i<row.length+1;i++)
	{   
		var ppeVal= jQuery("#PPEgrid").jqGrid('getCell',i,"KEYID");
		
		for (var a = 0;a<ppearr.length;a++)
		{
						
			   
			if (ppeVal == ppearr[a])
			{	
				jQuery("#PPEgrid").jqGrid('setCell',i,'CHECKVAL','1');				
				jQuery("#list_checkbox_"+i).attr('checked',true);
				
            }
            
        }
		
		
	}
	
}
function ClearPPEGrid()
{
	var row=jQuery("#PPEgrid").getRowData();
	
	
	var rowdata = "";
	var chkval=0;
	for(var i=1;i<row.length+1;i++)
	{		
		
		jQuery("#PPEgrid").jqGrid('setCell',i,'CHECKVAL','0');				
		jQuery("#list_checkbox_"+i).attr('checked',false);
		
	}
}
function setppetexttoGrid()
{
	var row=jQuery("#PPEgrid").getRowData();
	
	
	var rowdata = "";
	var chkval=0;
	for(var i=1;i<row.length+1;i++)
	{		
		if(jQuery("#PPEgrid").jqGrid('getCell',i,"CHECKVAL") == 1)
		{  
			
			rowdata =  rowdata +","+ jQuery("#PPEgrid").jqGrid('getCell',i,"KEYID");
		}
	}
	
	
	setFieldValue('txtvsodImgppe',rowdata);
}
jQuery("#btnvisualsave").click(
		function() {
		
		setppetexttoGrid();
		saveForm("frmVisualSoppop","VisualSoppopDetail_save.VisualSop","");
		});
jQuery("#btnVisualDelete").click(
		function() {
			var r=confirm("Are you sure to Delete?");
			if(r)
				deleteRecord("frmVisualSoppop","VisualSoppopDetail_delete.VisualSop");
		});
jQuery("#btnclear").click(
		function() {
			setFieldValue('txtVsodKeyid','');
			setFieldValue('txtVsodInstruction','');
			setFieldValue('txtVsodKeypoint','');
			setFieldValue('txtVsodImportanceofkeypoint','');
			setFieldValue('txtVsodToolused','');
			setFieldValue('imgVsodImgtoolusedFilename','');
			jQuery('#imgVsodImgtoolused').attr('src','images/tools.jpg');
			
		});

function frmVisualSoppop_successsCallback(result){
	
	setFieldValue('txtVsodKeyid','');
	setFieldValue('txtVsodInstruction','');
	setFieldValue('txtVsodKeypoint','');
	setFieldValue('txtVsodImportanceofkeypoint','');
	setFieldValue('txtVsodToolused','');
	setFieldValue('imgVsodImgtoolusedFilename','');
	jQuery('#imgVsodImgtoolused').attr('src','images/tools.jpg');
	jQuery("#VisualSop").trigger("reloadGrid"); 
	jQuery("#VisualSopgrid").trigger("reloadGrid");
	ClearPPEGrid();
	//closePopUpDialoge("divvisualsopdetpop",true);
 }

function frmVisualSoppop_deleteSuccessCallback(result){
	
	alert(result.successData.msg);
	setFieldValue('txtVsodKeyid','');
	setFieldValue('txtVsodInstruction','');
	setFieldValue('txtVsodKeypoint','');
	setFieldValue('txtVsodImportanceofkeypoint','');
	setFieldValue('txtVsodToolused','');
	setFieldValue('imgVsodImgtoolusedFilename','');
	jQuery('#imgVsodImgtoolused').attr('src','images/tools.jpg');
	jQuery("#VisualSop").trigger("reloadGrid"); 
	jQuery("#VisualSopgrid").trigger("reloadGrid");
}
function btnDefaults(){
	enableUIButton("btnvisualsave");
	enableUIButton("btnclear");
	disableUIButton("btnVisualDelete");
}
function btnUpdate(){
	enableUIButton("btnvisualsave");
	enableUIButton("btnclear");
	enableUIButton("btnVisualDelete");
}


</script>

	<form id="frmVisualSoppop" name="frmVisualSoppop" action="" method="post" >
		

			
				<div style="padding-top: 3px; padding-left: 15px; padding-left: 70px\9;">
					<table>
						<tr  >
							<td valign="top" >
								<div >
									<label class="mandatory-lbl">Instruction</label>
								</div>
								<div  >
								<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  maxlength="495" tabindex="1" rows="2" cols="80" style="height : 60px; width : 250px;" id="txtVsodInstruction"
										name="txtVsodInstruction">${requestScope.newJhaTlVisualsopdtl.vsodInstruction}</textarea>
								</div>
								<div >
									<label>Tool Used</label>
								</div>
								<div  >


									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" maxLength="495" tabindex="3"  rows="2" cols="80" style="width: 250px; height : 60px;"
										id="txtVsodToolused" name="txtVsodToolused">${requestScope.newJhaTlVisualsopdtl.vsodToolused}</textarea>
								</div>
								</td>
							
							<td  valign="top" style="padding-left: 40px">
							<div  class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Key Point</label>
								</div>
								<div >
								<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" maxlength="495" tabindex="2" rows="2" cols="80" style="width: 250px; height : 60px;" id="txtVsodKeypoint" name="txtVsodKeypoint">${requestScope.newJhaTlVisualsopdtl.vsodKeypoint}</textarea>

								</div>
								<div >
									<label>Importance Of Key Point</label>
								</div>
								<div  >


									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" class="txtarea" maxlength="495" tabindex="4" rows="2" cols="80" style="width: 250px; height : 60px;"
										id="txtVsodImportanceofkeypoint" name="txtVsodImportanceofkeypoint">${requestScope.newJhaTlVisualsopdtl.vsodImportanceofkeypoint}</textarea>
								</div>
							</td>
							<td valign="top" style="padding-left: 40px;padding-top:20px" > 
							
							<img src="images/tools.jpg" id="imgVsodImgtoolused" name="imgVsodImgtoolused"  width="100px" height="100px" style="width : 160px; height : 126px;"/>
							<div  style="padding-top: 6px;">
							<span style="padding-right: 99px;"><input	type="button" class="easyui-button" id="dlgImg" 	name="dlgImg" value="+" style=" height : 20px;"/> </span>
							<input	type="button" class="easyui-button" id="btnImgClear"name="btnImgClear" value="-"  style="width : 29px; height : 20px;"/></div>
							
							</td>							
							<td style="padding-left: 40px;">
							<div style="padding-left: 20px ">
								<table id=PPEgrid>
									<tr>
										<td></td>
									</tr>
								</table>	
								</div>							
							</td>
						</tr>
						
						<tr > 
						<td style="width:20" colspan="2" >
						<div style ="width:330px;">
							<input type="button" class="easyui-button" id="btnvisualsave"
									name="btnvisualsave" style="width:100px;height:21px;"value="Insert/Update" />								
							<input type="button" class="easyui-button" id="btnVisualDelete"
									name="btnVisualDelete" style="width:100px;height:21px;" value="Delete" />									
							<input type="button" class="easyui-button" id="btnclear" name="btnclear"style="width:100px;height:21px;" value="Clear" />
						</div>
						</td>
						</tr>
					 </table>
				</div>		
		
				<div style="padding-top: 20px; float:left; padding-left: 3%\9;">
				<table id=VisualSopgrid>
				
				</table>
			<div id='pagergrid'></div>
			
			</div>
		 <input type="hidden" id="txtVsodVsomKeyid" name="txtVsodVsomKeyid"value="${requestScope.mstKeyId}" />
		 <input type="hidden" id="txtvsodImgppe" name="txtvsodImgppe"value="${requestScope.vsodImgppe}" />
		 <input type="hidden" id="txtVsodKeyid" name="txtVsodKeyid"value="${requestScope.newJhaTlVisualsopdtl.vsodKeyid}" />
		 <input type="hidden" id="txtmode" name="txtmode"value="${requestScope.mode}" />
		 <input type="hidden" id="imgVsodImgtoolusedFilename" name="imgVsodImgtoolusedFilename" value="${requestScope.newJhaTlVisualsopdtl.vsodImgtoolused}" />
	</form>

