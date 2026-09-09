<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
	jQuery(document).ready(
			function() {
			
				initialiseForm('frmVisualSop');
				jQuery('#submitForm').val('frmVisualSop');
				jQuery('#frmVisualSop .easyui-text').css('text-transform', 'uppercase');
				jQuery('#frmVisualSop textarea').css('text-transform', 'uppercase');
				
				var factId = jQuery("#frmVisualSop input[id='factory']").val();
			    var sectionId = jQuery("#frmVisualSop input[id='section']").val();
			    var cellId = jQuery("#frmVisualSop input[id='cell']").val();
			    
			    var machId = jQuery("#frmVisualSop input[id='machine']").val();
			    var flid = jQuery("#frmVisualSop input[id='flid']").val();
			    //alert("flid"+flid);
				//alert("FACT: " + factId + "\nSECTION: " + sectionId + "\nCELL: " + cellId + "\nMACHINE: " + machId + "\nFLID: " + flid);
			    
				fillComboBox("frmVisualSop", "cmbVsomEquipmentid", "machineCombo.commonFilter?&cellId="+cellId);
				fillComboBox("frmVisualSop", "cmbVsomPreparedby", "employee.commonFilter");
				fillComboBox("frmVisualSop", "cmbVsomApprovedby", "employee.commonFilter");
				fillComboBox("frmVisualSop", "cmbVsomIssuedby", "employee.commonFilter");
				fillComboBox("frmVisualSop","cmbVsomProductid","comboGradeSpec.commonFilter?q&flid="+flid);
				  //var flid = jQuery("#frmVisualSop input[id='flid']").val();
					
				//fillComboBox("frmVisualSop", "cmbVsomProductid", "combo_product.pcs?&machId="+machId);
				//fillComboBox("frmVisualSop", "cmbVsomProductid", "combo_product.pcs?q&flid="+flid);				
				fillComboBox("frmVisualSop", "cmbVsomMaintsection", "trademst.VisualSop");
		
			    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;	  					
			    loadFunctionalLocation("vsopfunLocation","functionalLoc.VisualSop","momfunLocationValues","frmVisualSop",dataStr);

			    var keyId=jQuery('#txtVsomKeyid').val();
				processGridnew("VisualSopDetail_input.VisualSop","&q=2&keyId="+keyId,"VisualSop", "pager", "", "docDoubleClick","","VisualSopcompletecallback");

				var frmtype = getFieldValue('hdnformtype','frmVisualSop');
		
				
			
				if( frmtype == 'Approval')
				{
					Setworkflow (flid);
				} 
				
				fileManagerPopUp("","VSOP","frmVisualSop","btnfilemgr","vsopFilemgr");
				jQuery('#btnInsert').click(function(){			
					saveForm("frmVisualSop","VisualSopDetailUpdate_input.VisualSop?&Type=dtlPop","");
						
					});
				
				
			});

	function docDoubleClick(id) {
		var rowData = jQuery("#VisualSop").jqGrid('getRowData',id);
		//sriram 27-Oct-2025 chnage uppercase to lowercase vsod_keyid 
		var detKeyId= rowData.vsod_keyid;
		
		var keyId=jQuery('#txtVsomKeyid').val();
		var frmtype = getFieldValue('hdnformtype','frmVisualSop');
		if( frmtype == 'Entry')
		{
			LoadPopUp("divvisualsopdetpop","visualSOPDetailAdd_view.VisualSop?mode=MODIFY&detKeyId="+detKeyId+"&keyId="+keyId, true,"90%", "86%", "5%", "5%","multiSelectOk_Callback","Detail Of Visual SOP");
		}
		// void LoadPopUp(any divId,      any url,                       any isModel,                                         any width, any height, any top, any left, any  loadpopUpSuccessCallBack, any title, any isInside, any toolBar, any needClose, any classname)
	}

	jQuery('#btnExcelview').click( function()
			{
				var vsopId=jQuery("#txtVsomKeyid").val();
				window.open("vsopReport_Excelview.VisualSop?vsopId="+vsopId,"Excel View");
			});	
	function Setworkflow(flid)
	{    var vsopkeyid = getFieldValue('txtVsomKeyid');		 
		 workFlow('divvsopworkflow',false,'VSOPAPPR', vsopkeyid, 'VSOP', flid);
	}
	
	function VSOPAPPR_successCallback(result)
	{
	    //alert(" cccck :: "+result.nextRoleName);
		updateApprovedLevel(result.wfStatus, result.lastLevel, result.nextRoleName);
	}
	function updateApprovedLevel(wfStatus,lastLevel, nextLevel)
	{
		//alert(wfStatus);
		if((wfStatus == "A" ||wfStatus == "E") ){//&& lastLevel == "Y"){
			var status="";
		     if(wfStatus == "A" && lastLevel == "Y")
		    	 status="C";
		     else if(wfStatus == "A")
		    	 status="A";
		     else if(wfStatus == "E") {
		    	 status="E";
		    	 nextLevel="REWORK";
		     }
		     if(nextLevel.trim().length==0)
		    	 nextLevel="-";
		     
		     var vsopkeyid = jQuery("#txtVsomKeyid").val();
		     var ds = "?&status="+status+"&nextLevel="+nextLevel+"&keyid="+vsopkeyid;
			//saveForm('frmOplCreation','updateApprovedStatusLevel.opl'+ds);
			//alert(ds);
		     processAjaxCalls("updateApprovedStatusLevel.VisualSop",ds,"updateSuccess","");
		}	
		
	}


	
	function btnfilemgr_click()
	{
		var formtype = getFieldValue('hdnformtype');  
		if(formtype == 'Report')
		{   
			var vsopkeyid = getFieldValue('txtVsomKeyid');		
			fileManagerPopUp(vsopkeyid,"VSOP","","","","view");			
			
		}
		else
		{
			
			saveForm("frmVisualSop","VisualSopDetailUpdate_input.VisualSop?&Type=fileMng","");
			
		}		
		
		
	}
	function gotMousehover(id,rowId){
		
		var imgurl=jQuery("#"+id).attr("src");
		var spanid='spn'+'_'+id;
		var html='<span id= '+spanid+' style="position: absolute; z-index:210;  top:4; padding:5px; background-color:#fff; right:-286;_right:-120;"><img src='+imgurl+' style="width:133px;_width:116px;height:83px;"/></span>';
		//jQuery("tr[id='"+rowId+"']").after(arrow);
		jQuery('#gbox_VisualSop').after(html);
		}
	function gotMouseleave(id) {
		var spanid='spn'+'_'+id;
		jQuery("#"+spanid).remove();
	}
	
	function txtFormatter(id, options, rowObject) {
		var id = options.rowId;
		var columnName = options.colModel.name;
		var columnNo = options.pos;
		var gid=options.gid;
		var imageUrl=rowObject[5];
		//alert(gid);
	//	alert("imageUrl::"+imageUrl.trim().length);
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
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'" src="images/toolsused.GIF"   width="100px" height="20px" align="middle"  style="cursor: pointer"/>';
	}
}		

/* 	function frmVisualSop_FuntLocHierarchy_SuccessCallBack(flIds){
		  var flid=flIds.flid;
		  setFieldValues('cmbVsomFlnid');
	}
 */
 function divvisualsopdetpop_onClose()//reload grid
	{
		 //jQuery("#VisualSop").trigger("reloadGrid");
		 var keyId=jQuery('#txtVsomKeyid').val();		 
		 processGridnew("VisualSopDetail_input.VisualSop","q=2&keyId="+keyId,"VisualSop", "pager", "", "docDoubleClick","","VisualSopcompletecallback");
				
		  return true;
	}
	 function frmVisualSop_successsCallback(result)
		{
		jQuery("#txtVsomKeyid").val(result.successData.vsomKeyid);
		if(result.successData.type=="dtlPop")
			LoadPopUp("divvisualsopdetpop","visualSOPDetailAdd_view.VisualSop?mode=create&keyId="+result.successData.vsomKeyid, true,"90%", "86%", "5%", "5%","multiSelectOk_Callback","Detail Of Visual SOP");
		else if(result.successData.type=="fileMng")
			fileManagerPopUp(result.successData.vsomKeyid,"VSOP","","","");		
		else{
			
			clearForm('frmVisualSop');
			//setTimeout(function(){ 
		 		navigateToPrevForm();
		 		//},10000);
	 	}
			
		}
		


function frmVisualSop_beforeDelete(){
	var keyid= jQuery("#txtVsomKeyid").val();
	var rowData = jQuery("#VisualSop").jqGrid('getDataIDs');
	var formtype = getFieldValue('hdnformtype');

	if (formtype == 'Report' || formtype == 'Approval')
	{
		return false;
	}
	
	if(keyid==null || keyid=="" || keyid==" ")
		return false;
	else{
		var r ;
		if(rowData.length>0)
			r =confirm("Data have been referred, Do you want to Delete?");
		else
			r =confirm("Are you sure to Delete?");
		if(r)
			return true;
		else
			return false;
	}
		/*if (r == true)	
		{	
			//deleteRecord("frmVisualSop","VisualSopDetailUpdate_delete.VisualSop");
		}
		*/
}	
function frmVisualSop_beforeRefreshCallback(){
	var keyid= jQuery("#txtVsomKeyid").val();
	var r;
	if(keyid==null || keyid=="" || keyid==" "){
		r =confirm("Are you sure to Clear?");
		if(r)
			return true;
		else
			return false;
	}
}
function frmVisualSop_beforeSubmit(){
	
	var cellId = getFieldValue('cell','frmVisualSop');
	if (cellId =='' || cellId ==' ') {
		popupCommonErrorMsg("Select JH");
		return false;
	}
	var keyid= jQuery("#txtVsomKeyid").val();
	var formtype = getFieldValue('hdnformtype');
	
	//if (formtype == 'Report')
		if (formtype == 'Report'||(formtype == 'Approval'))
	{
		return false;
	}
	
	if(keyid!=null && keyid!="" && keyid!=" "){
		var r =confirm("Data have been changed, Do you want to proceed?");
		if(r)
			return true;
		else
			return false;
	}
}
function frmVisualSop_FuntLocHierarchy_SuccessCallBack(keyIds)
{   
	//alert(keyIds.cellId);
	if (keyIds.cellId!=null) {
		reloadCombo("frmVisualSop", "cmbVsomEquipmentid", "machineCombo.commonFilter?&cellId="+keyIds.cellId);
		
		reloadCombo("frmVisualSop", "cmbVsomProductid", "combo_product.pcs?&cellId="+keyIds.cellId);
		
		setFieldValue('cmbVsomEquipmentid',keyIds.machId);
	}
}
function frmVisualSopcmbVsomEquipmentid_onSelect(record) { 
	var dataStr = "&machId="+record.id;
	loadFunctionalLocation("vsopfunLocation","functionalLoc.VisualSop","momfunLocationValues","frmVisualSop",dataStr);
}
	
 function frmVisualSop_deleteSuccessCallback(result){
	
		alert(result.successData.msg);
		jQuery("#VisualSop").trigger("reloadGrid"); 
}
 
</script>


	<form action="" method="post" id="frmVisualSop">
		<div id='wrapper' style="width:100%">

  	<div style="padding-left: 0%;">
					<table>
						<tr>
						
							<td colspan="3">
							 <div id="frmmomFuntKeyIds"  >							
								<input type="hidden" id="factory" name="cmbVsomFactoryid"  value="" ></input>
								<input type="hidden" id="section" name="cmbVsomSectionid"  value=""></input>
								<input type="hidden" id="cell"    name="cmbVsomCellid"     value=""></input>
								<input type="hidden" id="machine" name="cmbVsomMachineid"  value=""></input>
								<input type="hidden" id="flid" name="cmbVsomFlnid"  value="${requestScope.newJhaTlVisualsopmst.vsomFlnid}"></input>
								<input type="hidden" id="elementId" name="cmbelementid"  value="${requestScope.newJhaTlVisualsopmst.elementid}"></input>							
							</div>						
						<div id="vsopfunLocation" style="width:84.3%;width:82%\9;"></div>	</td>
						</tr>
						<tr >
							<td ><div class="easyui-paddingbfpx"><label>Equipment</label></div>
							<div class="easyui-paddingbfpx">
									 <input class="easyui-combo"  
										id="cmbVsomEquipmentid" name="cmbVsomEquipmentid" 
										style="width: 300px;" ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										value="${requestScope.newJhaTlVisualsopmst.vsomEquipmentid}" />
								</div></td>
								<td style="padding-left:50px;padding-left:20px\9; "><div class="easyui-paddingbfpx"><label>Product</label></div>
							<div class="easyui-paddingbfpx">
									<input class="easyui-combo"
										id="cmbVsomProductid" name="cmbVsomProductid"
										 style="width: 300px;" ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										value="${requestScope.newJhaTlVisualsopmst.vsomProductid}" />
								</div></td>
							<td style="padding-top:0px;">
		 				<div style="position:relative; margin-top: 10px;margin-left: 45px;">
					 <span  id="vsopFilemgr" style="position:absolute;l%;left:28%;right:5%\9;top:-1px;top:0px\9;" ></span>
					 <input type="button" id="btnExcelview" name="btnExcelview" class="easyui-button" style="width:80px;height: 23px;" value="Excel View" /> 
             </div>
		</td>
							
						</tr>
						<tr>
						<td >
							
							<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Operation</label></div>
							<div class="easyui-paddingbfpx">
								

									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" type="textarea" maxlength="495"  rows="2" cols="80" style=" width : 300px;" id="txtVsomOperation"
									 ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										name="txtVsomOperation">${requestScope.newJhaTlVisualsopmst.vsomOperation}</textarea>
								</div></td>
						
									<td  style="padding-left:50px; padding-left:20px\9; " ><div class="easyui-paddingbfpx"><label class="mandatory-lbl">Safety Instruction</label></div>
							
							<div class="easyui-paddingbfpx">
									

									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="495"  rows="2" cols="80" style="width: 300px;"
									 ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										id="txtVsomSafetyinstruction" name="txtVsomSafetyinstruction">${requestScope.newJhaTlVisualsopmst.vsomSafetyinstruction}</textarea>
								</div></td>
								<td style="padding-left:45px;padding-left:20px\9;"><div class="easyui-paddingbfpx" ><label>Effect of Non Compliance</label></div>
							
							<div class="easyui-paddingbfpx">
									
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" maxlength="495"  style="width: 300px;" 
									${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										id="txtVsomEffectofnoncompliance" name="txtVsomEffectofnoncompliance">${requestScope.newJhaTlVisualsopmst.vsomEffectofnoncompliance}</textarea>
								</div></td>
						</tr>
						<tr>
						<td ><div class="easyui-paddingbfpx"><label class="mandatory-lbl">Created By</label></div>
							
							
							<div class="easyui-paddingbfpx">
									 <input class="easyui-text"
										id="cmbVsomPreparedby" name="cmbVsomPreparedby"
										style="width: 300px;" readonly="readonly" 
										value="${requestScope.newJhaTlVisualsopmst.vsomPreparedby}" />
								</div></td>
								<td style="padding-left:50px;padding-left:20px\9; ">
								
								
								<div class="easyui-paddingbfpx" >
								<label>Maintenance Section</label></div>
								<div class="easyui-paddingbfpx" >
									<input class="easyui-text"
										id="cmbVsomMaintsection" name="cmbVsomMaintsection"
										 style="width: 300px;" ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										value="${requestScope.newJhaTlVisualsopmst.vsomMaintsection}" />
								</div>
								
								
								
								
								
								<div class="easyui-paddingbfpx" style="display: none;"><label>Approved By</label></div>
							<div class="easyui-paddingbfpx" style="display: none;">
									<input class="easyui-text"
										id="cmbVsomApprovedby" name="cmbVsomApprovedby"
										 style="width: 300px;" ${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										value="${requestScope.newJhaTlVisualsopmst.vsomApprovedby}" />
								</div>
								</td>
								<td style="padding-left:45px;padding-left:20px\9; ">
								<div class="easyui-paddingbfpx" style="display: none;" ><label class="mandatory-lbl">Issued By</label></div>
							<div class="easyui-paddingbfpx" style="display: none;">
									 <input class="easyui-text"
										id="cmbVsomIssuedby" name="cmbVsomIssuedby"
										style="width: 300px;"${ requestScope.VsopFormBean.disableForRpt == true ? ' disabled':''}
										value="${requestScope.newJhaTlVisualsopmst.vsomIssuedby}"/>
								</div></td>
								
						</tr>
						<tr>
						
									</tr>
	</table>
						
				
		
			<div style=" position:relative;  width:1000px; width:954px\9;  height:20px; height:25px\9;" class="sub-header">
			<span style="position: absolute;">Visual SOP Detail</span>
								<span style="position:absolute; right:-1px;" id="skilbtn">
								<c:if test="${false  == requestScope.VsopFormBean.disableForRpt }">
										<img id="btnInsert" alt="" title="Add Details"  src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-2;" >
										</c:if>
								</span>
			</div>
		<div style="width: 79%;position:relative;">
			<table id='VisualSop'>
				<tr>
					<td></td>
				</tr>
				
			</table>
			<div id='pager'></div>

		</div>
		</div>
		<div id='divvsopworkflow'>
		</div>
		</div>
		<input type="hidden" id="mode" name="mode" value=""/>
		<input type="hidden" id="hdnformtype" name="hdnformtype" value="${requestScope.formtype}" />
		<input type="hidden" id="txtVsomKeyid" name="txtVsomKeyid" value="${requestScope.newJhaTlVisualsopmst.vsomKeyid}" />

	</form>
