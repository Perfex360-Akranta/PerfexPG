<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<script type="text/javascript">
	 jQuery(document).ready(function(){
			var url = jQuery('#hiddenUrl').val();	
			initialiseForm('frmRoleTopicLink');	
				
			fillComboBox("frmRoleTopicLink","cmbRole"," roleMst.commonFilter" );	
			fillComboBox("frmRoleTopicLink","cmbCriteria"," Spoke.commonFilter" );	
			//
			//enableUIButton("btnAdd");
			/*-----for functionalLocation-----*/
			var factId = jQuery("#frmRoleTopicLink input[id='factory']").val();
			var sectionId = jQuery("#frmRoleTopicLink input[id='section']").val();
			var cellId = jQuery("#frmRoleTopicLink input[id='cell']").val();
			var machId = jQuery("#frmRoleTopicLink input[id='machine']").val();
			var flid = jQuery("#frmRoleTopicLink input[id='flid']").val();
			var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
			
			loadFunctionalLocation("ctfxfunLocation","ConvMatx_functionalLoc.ConvMatx","ctfxfunLocationValues","frmRoleTopicLink",dataStr);
			/*----------*/		
			jQuery('#submitForm').val('frmRoleTopicLink'); // set the id of form to submit	
			processGridnew("topicList_view.tatnd","q=2","topicgrid","topicpager","","");
			processGridnew(url,"q=2","roletopicgrid","roletopicpager","","");					
		  	jQuery('#btnAdd').click(function(){		
		  		//processGridnew(url,"q=2","roletopicgrid","roletopicpager","","");
			});	
		  	jQuery('#btnClear').click(function(){		
			});	
			var mode =jQuery('#txtmode').val();
			if(mode=="MODIFY"){  
				jQuery("#txtTask").val('Saftty Measures');	
				setFieldValue('cmbRole','ASSOCIATE - WORKER');
				setFieldValue('cmbCriteria','BEHAVIOUR');

				}	
	 });
	 function txtFormatter(id, options, rowObject)
	 {	
		 var columnKey="";
			var color='';
			var rowId = options.rowId;
			var columnid = options.pos;	
			var columnName = options.colModel.name;	
			var columnNo=columnName.substring(columnName.indexOf("_")+1);
			var idval;
			idval='txtKkp_';
			return '<input id='+idval+rowId + '_'+columnid +' onfocus=gotFocuse(this.id);  type="text" value="'+rowObject[columnid]+'" maxlength="5" style="width: 75px;text-align:right;background-color:'+color+'" / >';
					
			
	 }
	 function cboxFormatter(id, options, rowObject)
	 {
	 	var id = options.rowId;
	   	return '<input  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	 }
	 	 
	 function gotFocuse(id,colId,rowId){
			
			numericTextBox(id);
	}
	 
	 	
	function frmRoleTopicLink_successsCallback(result)
	{
		jQuery('#txtCnmmKeyid').val(result.successData.CnmdCnmmkeyid); 
	  	var url="ConvMatxDetails_input.ConvMatx";
	  	LoadPopUp("divConvMatxPopup", url+"?keyId=" + result.successData.CnmdCnmmkeyid, true,"80%","388px","100px","8%", "conversionMatrixOk_Callback","Conversion Matrix Details Entry",false);	
	}
	
	function frmRoleTopicLink_deleteSuccessCallback(result)
	{
		alert(result.successData.msg);		  
	}
	
	function viewGrid(url)
	{  
		//var keyId=jQuery('#txtCnmmKeyid').val();
		processGridnew(url,"&q=2","listdetail","detailpager","","");			
		return true;	
	}
	
	function divConvMatxPopup_onClose()//reload grid
	{
		 jQuery("#listdetail").trigger("reloadGrid");
		 return true;
	}
	
	function btnfilemgr_click()
	{
		if(1 != null && 1 != ''){
			fileManagerPopUp(1,"ABN","","","");
		}
	}
	function frmRoleTopicLink_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth('frmRoleTopicLink','714px');
	}
 </script>

<form name="frmRoleTopicLink" id="frmRoleTopicLink" action="" method="post">
	<div id="wrapper" style="padding-left:8% ;" >
		<table>
			<tr>
				<td >				
					<div>
						<div  id="frmRoleTopicLinkFuntKeyIds"  >
							<div style="float: left;">
								<input type="hidden" id="factId" name="factId" value=""  ></input>
								<input type="hidden" id="sectId" name="sectId" value=""  ></input>
								<input type="hidden" id="cellId" name="cellId" value=""  ></input>
								<input type="hidden" id="machId" name="machId" value=""  ></input>
								<input type="hidden" id="flid" name="cmbRollFlnid" value="${requestScope.flid}"  />
							</div>
							
							<div class="" style="width:92%;">
								<!--<div id="ctfxfunLocation" style="width: 835px; "></div>-->
								<div id="ctfxfunLocation" style="width: 87%; display: block;" tabindex="0">
									<div id="frmRoleTopicLinkctfxfunLocationValues">
									<ul>
									<li class="dispFunctionalLoccap">
									<b> Functional Location :</b>
									
									</li>
									</ul>
									<div id="dispFunctionalLoc" class="easyui-paddingbfpx">
									<a id="linfrmRoleTopicLinkCompany" title="ITC-BCM" style="cursor: pointer;">
									<u>
									<b> 2000 </b>
									</u>
									</a>
									/  
									<a id="linfrmRoleTopicLinkLocation" title="SBU 2" style="cursor: pointer;">
									<u>
									<b> SBU 2 </b>
									</u>
									</a>
									/  
									<a id="linfrmRoleTopicLinkFactory" title="PBU BOARD" style="cursor: pointer;">
									<u>
									<b> PBU BOARD </b>
									</u>
									</a>
									/  
									<a id="linfrmRoleTopicLinkSection" title="FH - 123" style="cursor: pointer;">
									<u>
									<b> FH - 123 </b>
									</u>
									</a>
									/  
									<a id="linfrmRoleTopicLinkCell" title="FH 123 - SHEETER 1, 2 & 6 (CP)" style="cursor: pointer;">
									<u>
									<b> FH 123 - SHEETER 1, </b>
									</u>
									</a>
									/  
									<a id="linfrmRoleTopicLinkMachine" title="SHEET CUTTER # 1" style="cursor: pointer;">
									<u>
									<b> 10001593 </b>
									</u>
									</a>
									/  
									</div>
									</div>
								</div>
								<div id="txtFct" class="tpm-errormsg" style=""/></div>
								<div id="err_err_cell" class="tpm-errormsg" style=""/></div>
							</div>
							<div class="clear"></div>
						</div>	
					</div>				
				</td>
				<td style="padding-left: 25px;">
					
							<div ><label  class="mandatory-lbl">Role</label></div>				
							<div class="easyui-paddingbfpx">
								<input id="cmbRole" name="cmbRole" class="easyui-combobox" 
								tabindex="19"  style="width:180px;" value="" >							
							</div>
				</td>
			</tr>
			</table>
				<div style=" position:relative;  width:77.5%;_width:84%; height:20px; height:25px\9;" class="sub-header">
			<span style="position: absolute;">Detail</span>
							
			</div>
			<table  style="width:77%;">
				<tr>
					<td style="width:20%;" valign="top">
						
							
								<div class="easyui-paddingbfpx"><label  class="mandatory-lbl">Criteria</label></div>				
								<div >
									<input id="cmbCriteria" name="cmbCriteria" class="easyui-combobox" 
									tabindex="19"  style="width:250px;" value="">							
								</div>	
							
							<div ><label  class="mandatory-lbl">Task</label></div>			
						<div>
							<div class="easyui-paddingbfpx">
			                    <textarea  id="txtTask" name="txtTask" style="resize:none;width:250px;height:66px;" ></textarea>                   
			                </div>								
						</div>							
					</td>
					
					<td style="padding-left: 43px;width:20%;">
						<div ><label  class="mandatory-lbl">Topic</label></div>			
						<div class="easyui-paddingbfpx">
							<div class="easyui-paddingbfpx">
			                    <table id="topicgrid" ></table>
								<div id="topicpager"></div>                   
			                </div>								
						</div>
										
					</td>
					
				</tr>		
			</table>
				<div style="position: relative;">
							<span style="position: absolute;right: 262px;_right: 180px;">
								<input type="button" class="easyui-button" id="btnAdd" value="Add" />
								<input type="reset" class="easyui-button" id="btnClear" value="Clear"/>																
							</span>											
					</div>
			<div style="float: left; padding-top: 27px;">

				<table id="roletopicgrid" ></table>
				<div id="roletopicpager"></div>

			</div>
	</div>
	<input type="hidden" id="mode" name="mode" value=""/>
	<input type="hidden" id="txtmode" name="txtmode" value="${requestScope.mode}"/>
</form>