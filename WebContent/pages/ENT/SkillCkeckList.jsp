<script type="text/javascript">
		jQuery.noConflict();
		
		jQuery(document).ready(function(){
			 
			jQuery('#submitForm').val('frmSkillCheckList');
			initialiseForm('frmSkillCheckList'); 	
			formatDateBox('dteChkdEffectiveDate','dd-MMM-yyyy');					
			fillComboBox("frmSkillCheckList","cmbChkmTopiKeyid","topic.commonFilter" );
			fillComboBox("frmSkillCheckList","cmbChkmSkrmKeyid","skillRating.commonFilter" );
			fillWithCurrentDate('dteChkdEffectiveDate');
			
			numericTextBox('txtChkdOrderno');
			var dataString = "?q=2";
			var topicId = jQuery("#hdnSkill").val();
			
			if(topicId != null && topicId.trim() != '')
			{
				//alert("topicId::"+topicId);
			//alert("cmb val::::::"+jQuery("#cmbChkmTopiKeyid").combobox("setValue",topicId));
				jQuery("#cmbChkmTopiKeyid").combobox('setValue',topicId);
				jQuery("#cmbChkmTopiKeyid").combobox("disable");		
				var rattingId = jQuery('#cmbChkmSkrmKeyid').combobox('getValue');
				dataString += "&topicId="+topicId+"&rattingId="+rattingId;				
				processAjaxCalls("SkillCheckList_recallrank.checkList",dataString,"recallData_successCalBack","recall_errCalBack");
				
			}
			viewGrid("SkillCheckList_input.checkList",dataString);	
			
		});
		
	
		jQuery("#btnADD").click(function(){		
			var txtChkmKeyid = jQuery("#txtChkmKeyid").val();
			var txtChkdKeyid = jQuery("#txtChkdKeyid").val();
			jQuery("#hdnRating").val(jQuery("#cmbChkmSkrmKeyid").combobox("getValue"));
			
			if(txtChkmKeyid == '' && txtChkdKeyid == '')
			{
				jQuery("#txtChkmKeyid").val(jQuery("#hdnChkmKeyid").val());
				jQuery("#txtChkdKeyid").val(jQuery("#hdnChkdKeyid").val());
			}
			
				saveForm("frmSkillCheckList","SkillCheckList_save.checkList");
			
		});
		
	
		jQuery("#btnDelete").click(function(){
			var r=confirm("Do You Want To Delete?");
			
			if (r==true)
			  {						
				saveForm("frmSkillCheckList","SkillCheckList_delete.checkList");
			  }
			else  {	  }
			
			
		});
		
		
		function frmSkillCheckListcmbChkmSkrmKeyid_onSelect(record) {
			var topicId = jQuery("#hdnSkill").val();
			var rattingId = jQuery('#cmbChkmSkrmKeyid').combobox('getValue');
			var dataString = "?q=2";
			dataString += "&topicId="+topicId+"&rattingId="+rattingId;			
			viewGrid("SkillCheckList_input.checkList",dataString);
		} 
		
		 function viewGrid(url,filterString)
		 {	
		 		processGridnew(url,filterString,"skillCheckListGrd","pager","","skillCheckListGrd_doubleClickGrid","","Load_Complete");
		 		return true;
		 }
		 
		 function Load_Complete()
		 {
			 var rowIds = jQuery('#skillCheckListGrd').jqGrid().getDataIDs();
			 var length = rowIds.length;	
			 
			 var cellVal =jQuery('#skillCheckListGrd').getCell(rowIds[length-1],"txtChkdOrderno");
			cellVal = parseInt(cellVal)+1;
			 if(length >0)
			 {
				 jQuery("#txtChkdOrderno").val(cellVal);				
			 }
			 else if(length==0)
				 jQuery("#txtChkdOrderno").val("1");
	
		}
		 function skillCheckListGrd_doubleClickGrid(id)
		{			
			var rowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',id);			
			var dtlId = rowData.txtChkmKeyid;
			
			 var filterString = "keyId="+dtlId;
			 filterString += "&dtlId="+id;
			processAjaxCalls("SkillCheckList_recall.checkList",filterString,"recall_successCalBack","recall_errCalBack");
		}
		 
		 function recallData_successCalBack(result)
		 {
			if(result != null ){
				 jQuery("#cmbChkmSkrmKeyid").combobox("setValue",result.checkListData.ChkmSkrmKeyid);
				 jQuery("#txtChkmKeyid").val(result.checkListData.ChkmKeyid);
				 jQuery("#hdnChkmKeyid").val(result.checkListData.ChkmKeyid);
				 
				 if(result.checkListData.ChkmSkrmKeyid != null)
				 	jQuery("#cmbChkmSkrmKeyid").combobox("disable");
				 jQuery("#hdnRating").val(result.checkListData.ChkmSkrmKeyid);
				 var filterString = "keyId="+result.checkListData.ChkmKeyid;
				 processAjaxCalls("SkillCheckList_recallDtl.checkList",filterString,"recall_successCalBackDtl","recall_errCalBack");
			}
		 }
		 
		 function recall_successCalBackDtl(result)
		 {
			 if(result != null ){
			 jQuery("#hdnChkdKeyid").val(result.checkListDataDtl.ChkdChkmKeyid);
			 }
		 }
		function recall_successCalBack(result)
		{
		  if(result != null ){
			jQuery("#cmbChkmTopiKeyid").combobox("setValue",result.checkListMst.ChkmTopiKeyid);
			jQuery("#txtChkdOrderno").val(result.checkListDtl.ChkdOrderno);
			jQuery("#txtChkdName").val(result.checkListDtl.ChkdName);
			jQuery("#txtChkdRemarks").val(result.checkListDtl.ChkdRemarks);
			var date = result.checkListDtl.ChkdEffectiveDate;
			date = date.substring(0,11);			
			jQuery("#dteChkdEffectiveDate").datebox("setValue",date);
			jQuery("#txtChkmKeyid").val(result.checkListMst.ChkmKeyid);
			jQuery("#txtChkdKeyid").val(result.checkListDtl.ChkdKeyid);
			jQuery("#hdnChkmKeyid").val(result.checkListMst.ChkmKeyid);
			jQuery("#hdnChkdKeyid").val(result.checkListDtl.ChkdKeyid);
			
			jQuery("#cmbChkmSkrmKeyid").combobox("setValue",result.checkListMst.ChkmSkrmKeyid);
		  }
		}
		 
	function skillChkBox_Formatter(id, options, rowObject)
	{
		var rowId = options.rowId;	
		return '<input id="skillChkBox_checkbox" name="skillChkBox_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}
	
	function chkboxCheck(rowId)
	{
		
		//jQuery("#list").jqGrid('setCell',rowId,'chkVal','1');	
		jQuery("#skillCheckListGrd").setCell(rowId,'ChekActive','1');
		var rowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',rowId);	
		var keyId = rowData.MCHM_KEYID;
		
		//processAjaxCalls("equipment_viewInactive.eqp" ,"keyId="+keyId+"&checked=Y", "listInactiveSelect_successsCallback","listInactive_errorCallback");
		
	}
	function chkboxUnCheck(rowId)
	{
		
		jQuery("#skillCheckListGrd").jqGrid('setCell',rowId,'ChekActive','0');	
		var rowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',rowId);	
		var keyId = rowData.MCHM_KEYID;
		
	}
	jQuery("#btnInactive").click(function(){
		var url = "equipment_makeActive.eqp?";
		var addRowId = new Array();
		var allRows = jQuery("#skillCheckListGrd").jqGrid('getDataIDs');
	
		for(var i=0;i<allRows.length;i++)
		{
			var rowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',allRows[i]);	
			
			if(rowData.ChekActive == "1")
			{	
				var keyId = rowData.txtChkmKeyid;	
				
				addRowId.push(keyId);
			}
		}
	});
	
	jQuery("#btnUp").click(function(){
		var ids = jQuery("#skillCheckListGrd").getDataIDs();
		var rowid = jQuery("#skillCheckListGrd").jqGrid('getGridParam','selrow');	
		
		
		var ids=allRowsId[1];
		var nextTr = jQuery("#skillCheckListGrd tr[id="+ids+"]").next('tr').attr("id");
		var nextRowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',nextTr);
		
		var currRow = ids[ jQuery("#skillCheckListGrd").getGridParam('selrow') - 1 ];
		
	});
	function frmSkillCheckList_successsCallback(result)
	{	
		
		//var skillId = jQuery("#hdnSkill").val();		
		//jQuery("#cmbChkmTopiKeyid").combobox("setValue",skillId);
		//var rating = jQuery("#hdnRating").val();
		fillComboBox("frmSkillCheckList","cmbChkmTopiKeyid","topic.commonFilter" );
		fillComboBox("frmSkillCheckList","cmbChkmSkrmKeyid","skillRating.commonFilter" );
		//jQuery("#cmbChkmSkrmKeyid").combobox("setValue",rating);
		fillWithCurrentDate('dteChkdEffectiveDate');
		jQuery("#cmbChkmSkrmKeyid").combobox("setValue",result.skillId);
		jQuery("#cmbChkmTopiKeyid").combobox("setValue",result.topicId);
		jQuery("#txtChkmKeyid").val(result.successData.ChekKeyid);
		//jQuery("#txtChkdKeyid").val(result.succDetaildtlid);
		readOnlyFields('cmbChkmTopiKeyid');
		//processAjaxCalls("SkillCheckList_recallrank.checkList","&keyId="+rating,"recallData_successCalBack","recall_errCalBack");
		var dataString = "?q=2";
		dataString += "&topicId="+result.topicId+"&rattingId="+result.skillId;		
		viewGrid("SkillCheckList_input.checkList",dataString);
		
		
		/*var txtChkmKeyid = jQuery("#hdnChkmKeyid").val();
		
		jQuery("txtChkmKeyid").val(txtChkmKeyid);
		var txtChkdKeyid = jQuery("#hdnChkdKeyid").val();
		jQuery("txtChkdKeyid").val(txtChkdKeyid);*/
	
	}
	
	
	 function btnFormatter_delete(id, options, rowObject)
		{					
			var rowId = options.rowId;
			
			return '<input type="button" id="remov" class="grdButton" value="" onclick="removeCheckList(\''+rowId + '\');"/>';
		}	
		function removeCheckList(rowId){	
		    
			var rowData = jQuery("#skillCheckListGrd").jqGrid('getRowData',rowId);
			var keyId = rowData.txtChkdKeyid;
			
			var conFdelete = confirm("Do You Want To Delete");
			if(conFdelete){
				if( keyId != null && keyId.length > 0  ){	
					processAjaxCalls("checkListDelete_Delete.checkList","&keyId="+keyId+"&rowId="+rowId , 'checkList_successCallBack','checkList_errorCallBack');
					
				}
			}
		}
		function checkList_successCallBack(result){
			if(result.tpmException){
				var rowId=result.rowId;
				alert(result.tpmException);			
				jQuery("#skillCheckListGrd").delRowData(rowId);
			}
		}
		
		
</script>

<form id="frmSkillCheckList">
	<div id="wrapper">
<!--	<div class="main-cntborder easyui-paddingbtpx" style="height:430px;">-->
		<table >
			<tr valign="top" style="padding-top: 10px;">
			
				<td >
				    <div style="margin-left:-40px;">
					<div align="left" class="left120"><label class="mandatory-lbl">Topic</label> </div>	
					<div align="left" class="left120">
					<input id="cmbChkmTopiKeyid" name="cmbChkmTopiKeyid" class="easyui-combobox" style="width: 255px;width:280px\9;"	value="${requestScope.topicId}">		
					</div>
					</div>
				</td>
				<td>	
				<div style="margin-left:-40px;">
				<div>
						<span style="padding-left: 70px;"> <label class="mandatory-lbl">Rating </label></span>
					</div>
					<div style="padding-left: 70px;">
						<input id="cmbChkmSkrmKeyid"	name="cmbChkmSkrmKeyid" class="easyui-combobox" style="width: 255px;width:280px\9;" value="${requestScope.rattingId}">
					</div>
					</div>	
				</td>		
				
				<td>
					<div style="width:146px;margin-left:0px;">
						<span style="padding-left: 22px;"> <label class="mandatory-lbl" >Display Order</label></span>
<!--					<span style="padding-left: 17px;"> <label class="mandatory-lbl" >Effective Date</label></span> -->
                        <div style="padding-left:24px;">
                        <input type="text" class="easyui-text" id="txtChkdOrderno" name="txtChkdOrderno" maxlength="15" style="width: 120px; height: 21px;" value="1">
                        </div>
					</div>
				</td>
				<td style="padding-right:0px;">	
					<div style="padding-left: 0px;margin-top:2px;margin-left:0px;">
						<span style="padding-left: 0px;"> <label class="mandatory-lbl" >Effective Date</label></span>
						<div style="padding-left:">
						<input id="dteChkdEffectiveDate" name="dteChkdEffectiveDate"	class="easyui-datebox" clear="false" style="width: 130px;" value="" />
						</div>
						<div><span id="err_txtChkdOrderno" class="tpm-errormsg" style="float: left;" ></span>   
						<span id="err_dteChkdEffectiveDate" class="tpm-errormsg" style="float: right;"></span>  
						</div>
					</div>		
				</td>		
			</tr>
			
			<tr >
				<td colspan="1">					
					<div class="left120" style="margin-left:-40px;">					
						<div><label class="mandatory-lbl">Check Points </label></div>
						<div>
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" type="text" rows="3"  cols="28" maxlength="175" id="txtChkdName" name="txtChkdName"></textarea>
						</div>
					</div>	
				</td>
				
				<td colspan="1">					
					<div style="padding-left: 70px;margin-left:-40px;">
						<div><label>Remarks</label></div>
						<div>
							<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" type="text" rows="3"  cols="28" maxlength="175" id="txtChkdRemarks" name="txtChkdRemarks"></textarea>
						</div>
					</div>		
					
					
				</td>
				
				<td >
					<div  style="padding-left: 80px;">							
						<div>
							<input type="button" class="easyui-button"	id="btnADD" name="btnADD" value="Add/Update" style="width:90px;"/> 
<!--							<input type="button" class="easyui-button" id="btnInactive" name="btnInactive" value="InActive" />-->
<!--							<input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value="Delete" />-->
<!--							<input type="button" class="easyui-button" id="btnUp" name="btnUp" value="Up" />-->
<!--							<input type="button" class="easyui-button" id="btnDown" name="btnDown" value="Down" />-->
						</div>
					</div>
				</td>				
			</tr>		
		</table>
		
	<div id="grdCheckList" style="margin-top: 10px;margin-left:80px;float:left;">
	    <table id="skillCheckListGrd" style="width: 100%"><tr><td/></tr></table>
		<div id="pager"></div>
	</div>
	
	</div>
<!--</div>-->

<input type="hidden" id="txtChkmKeyid" name="txtChkmKeyid" value=""/>
<input type="hidden" id="txtChkdKeyid" name="txtChkdKeyid" value=""/>
</form>

<input type="hidden" id="hdnSkill" name="hdnSkill" value="${requestScope.topicId}"/>	
<input type="hidden" id="hdnRating" name="hdnRating" value="${requestScope.rattingId}"/>
<input type="hidden" id="hdnChkmKeyid" name="hdnChkmKeyid" value=""/>
<input type="hidden" id="hdnChkdKeyid" name="hdnChkdKeyid" value=""/>
<style>
.left120{
padding-left:120px;
}

</style>