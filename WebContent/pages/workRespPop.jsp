<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
			 fillComboBox("frmwrkordrResponsibility","cmbPwrdtradeid","combo_plnconfigTrade.plnconfig");
			 fillComboBox("frmwrkordrResponsibility","cmbPwrdempid","combo_plnconfigDesignation.plnconfig");
			
			jQuery('#popHead').css('display','none');
			var filterstr = jQuery('#hdnfilterstr').val();
			//alert("SS  "+filterstr );
			 processGridnew("wrkOdrResp_input.plnconfig","?"+filterstr,"workOResp","workOrderResp_pager","","doubleClick","","wrkOdrResp_onloadComplete");
		});	
		function doubleClick(id){
			var rowData = jQuery("#workOResp").jqGrid('getRowData',id);	
			var tradeId = rowData.tradeId;
			var empId   = rowData.empId;	
			var masterkeyId=rowData.txtPwrdmasterid;
			var dtlkeyId=rowData.txtPwrdDetailid;
			jQuery('#txtPwrdmasterid').val(masterkeyId);
			jQuery('#txtPwrdkeyid').val(dtlkeyId);
			alert(masterkeyId +" -- "+ dtlkeyId);
			//alert(tradeId +" -- "+ empId);
			setFieldValue("cmbPwrdtradeid", tradeId,"frmwrkordrResponsibility");
			setFieldValue("cmbPwrdempid", empId,"frmwrkordrResponsibility");
			if(tradeId.trim().length<=0){
				jQuery('input:checkbox[name=chkoverresp]').attr('checked',true);
				disableField('frmwrkordrResponsibility','cmbPwrdtradeid');
				//jQuery("#cmbPwrdtradeid").combobox("setValue","{}");
				jQuery('#lbltrade').removeClass('mandatory-lbl');
			}
			else
				enableFields('cmbPwrdtradeid');
		}

		
		function wrkOdrResp_onloadComplete(){
			
			var workORespId = jQuery("#workOResp").jqGrid('getDataIDs');
			 for(i=1;i<=workORespId.length;i++)	
			 {			
				if(jQuery("#workOResp").getCell(i, 'cmbPwrdtradeid')=="OVERALL"){
					jQuery('input:checkbox[name=chkoverresp]').attr('checked',true);
					disableField('frmwrkordrResponsibility','cmbPwrdtradeid');
					//jQuery("#cmbPwrdtradeid").combobox("setValue","{}");
					//alert('d');
					jQuery('#txtPwrmTradewise').val("OVERALL");
					jQuery('#lbltrade').removeClass('mandatory-lbl');
				}
				else{
					jQuery('input:checkbox[name=chkoverresp]').attr('checked',false);
					enableFields('cmbPwrdtradeid');
					jQuery('#lbltrade').addClass('mandatory-lbl');
				}
			 }
		}
		/**/
		jQuery('#btnClr').click(function(){
			clearField('cmbPwrdtradeid');
			clearField('cmbPwrdempid');
		});
		/**/
		/*Delete Functionality*/
		jQuery('#btnDelete').click(function(){
			var rowid = jQuery("#workOResp").jqGrid('getGridParam','selrow');
			var rowData = jQuery("#workOResp").jqGrid('getRowData',rowid);																								
			var worespKeyId = rowData.txtPwrdDetailid;
			if(rowid != "undefined" && rowid != undefined && rowid != " " && rowid != ""){
				//alert("delete  "+rowData.txtPwrdmasterid);
				//jQuery("#workOResp").delRowData(rowid);
				processAjaxCalls('workorderRespon_del.plnconfig?','&worespKeyId='+worespKeyId,'wORespdel_sucess','wORespdel_Err');
			}
			else
			alert("select row from Grid");
		});
		function wORespdel_sucess(result){
		alert('Data Deleted Successfully');
		jQuery("#workOResp").trigger("reloadGrid");
		clearField('cmbPwrdtradeid');
		clearField('cmbPwrdempid');
		}
		function wORespdel_Err(result){}
		
		/*End Delete */
		jQuery('#btnAddtoGrid').click(function(){
				var row = jQuery("#workOResp").jqGrid('getDataIDs');
				//var keyId=jQuery("#workOResp").jqGrid('getCell',row.length,'keyid');
				var trade = jQuery('#cmbPwrdtradeid').combobox("getText"); 
				var responsibility =jQuery('#cmbPwrdempid').combobox("getText") ;	
				//alert(keyId);
				/*var emptyItem =[{keyid:row,cmbPwrdtradeid:trade,cmbPwrdempid:responsibility}];
				var rowId = 1;
				if ( row == null || row == '' || parseInt(row) <= 0) 
					 jQuery("#workOResp").jqGrid('addRowData',rowId, emptyItem[0]);
				else
					jQuery("#workOResp").jqGrid('addRowData',row.length+1, emptyItem[0]);

				/*For saving*/
				
				if(jQuery("#cmbPwrdtradeid").combobox('getValue') != ' ' && jQuery("#cmbPwrdtradeid").combobox('getValue') != '' &&  jQuery("#cmbPwrdtradeid").combobox('getValue') != null)
				{
					clearValidationErrorMsg('cmbPwrdtradeid');
					
					if(jQuery("#cmbPwrdempid").combobox('getValue') != ' ' && jQuery("#cmbPwrdempid").combobox('getValue') != '' &&  jQuery("#cmbPwrdempid").combobox('getValue') != null){
						
						clearValidationErrorMsg('cmbPwrdtradeid');
						
						clearValidationErrorMsg('cmbPwrdempid');
						
						//var dataString = '?q=2&txtPwrmkeyid='+keyId+'&cmbPwrdtradeid='+jQuery("#cmbPwrdtradeid").combobox('getValue')+'&cmbPwrdempid='+jQuery("#cmbPwrdempid").combobox('getValue');
						
						saveForm('frmwrkordrResponsibility','workorderRespon_save.plnconfig');
					}
					else
						
						showValidationErrorMsg('cmbPwrdempid','Select Responsibility ');
					
					}
				else
				{  
					if(jQuery('#chkoverresp').is(':checked') == false)
					{
						showValidationErrorMsg('cmbPwrdtradeid','Select Trade ');	
						
					 }
					else if(jQuery('#chkoverresp').is(':checked') == true){
						if(jQuery("#cmbPwrdempid").combobox('getValue') != ' ' && jQuery("#cmbPwrdempid").combobox('getValue') != '' &&  jQuery("#cmbPwrdempid").combobox('getValue') != null){
							clearValidationErrorMsg('cmbPwrdempid');
							var chkOverall = jQuery('#txtPwrmTradewise').val();
							
							saveForm('frmwrkordrResponsibility','workorderRespon_save.plnconfig');
							
						  }
						else
							showValidationErrorMsg('cmbPwrdempid','Select Responsibility ');
					}
								
					    //processAjaxCalls('workorderRespon_save.plnconfig',dataString,'wOResp_sucess ','wOResp_Err');
				 }	
			});	
		function frmwrkordrResponsibility_beforeSubmit(){
			var row = jQuery("#workOResp").jqGrid('getDataIDs');
			var masterkeyId=jQuery("#workOResp").jqGrid('getCell',row.length,'txtPwrdmasterid');
			var dtlkeyId=jQuery("#workOResp").jqGrid('getCell',row.length,'txtPwrdDetailid');
			var overallResp=jQuery('#chkoverresp').val();
			//alert(row.length);
			//alert("BFS  "+overallResp);
			//var txtPwrdmasterid = jQuery('hdnPwrdmasterid').val();
			
			
			
			var filtStr = '';
			
			filterStr ='&overallResp='+overallResp;
			if(keyId == false )
				keyId = " ";
			//else

			filterStr +='&txtPwrdkeyid='+jQuery('#txtPwrdkeyid').val();
			filterStr +='txtPwrdmasterid ='+jQuery('#txtPwrdmasterid').val();
			//alert(filterStr);
			return filterStr ;
		}
		
		/*function frmwrkordrResponsibility_deleteSuccessCallback(result)
		{
			alert("asdfdf");
			var workMstKeyId =result.successData.keyId;
			processGridnew("wrkOdrResp_input.plnconfig","?q=2&workMstKeyId="+workMstKeyId,"workOResp","workOrderResp_pager","","doubleClick","","wrkOdrResp_onloadComplete");
			jQuery('#planconfig').trigger("reloadGrid");
		}*/
		
		function frmwrkordrResponsibility_successsCallback(result){
			
			var workMstKeyId =result.successData.keyId;
			alert(workMstKeyId);
			//jQuery('#workOResp').trigger("reloadGrid");
			processGridnew("wrkOdrResp_input.plnconfig","?q=2&workMstKeyId="+workMstKeyId,"workOResp","workOrderResp_pager","","doubleClick","","wrkOdrResp_onloadComplete");
			jQuery('#planconfig').trigger("reloadGrid");
			
			// clear dropdowns after successful save
		    clearField('cmbPwrdtradeid');
		    clearField('cmbPwrdempid');
		    clearValidationErrorMsg('cmbPwrdtradeid');
		    clearValidationErrorMsg('cmbPwrdempid');

		    // also reset the overall-responsibility checkbox and hidden master/detail keys
		    jQuery('#chkoverresp').attr('checked', false);
		    jQuery('#chkoverresp').val(' ');
		    enableFields('cmbPwrdtradeid');
		    jQuery('#lbltrade').addClass('mandatory-lbl');

		    jQuery('#txtPwrdmasterid').val('');
		    jQuery('#txtPwrdkeyid').val('');
		
			} 
		function wOResp_sucess(result)
		{	
			//alert(result.successData.msg);				
			jQuery("#workOResp").trigger("reloadGrid");
			}
		function wOResp_Err(){
				alert('error');
			}
		jQuery('#chkoverresp').click(function(){
		if(jQuery('#chkoverresp').is(':checked') == true)
		{
			disableField('frmwrkordrResponsibility','cmbPwrdtradeid');
			clearField('cmbPwrdtradeid');
			clearField('cmbPwrdempid');
			clearValidationErrorMsg('cmbPwrdtradeid');
			//jQuery("#cmbPwrdtradeid").combobox("setValue","{}");
			jQuery('#lbltrade').removeClass('mandatory-lbl');
			jQuery('#chkoverresp').val('W');
		}
		else{
			jQuery('#chkoverresp').val('');
			enableFields('cmbPwrdtradeid');
			jQuery('#lbltrade').addClass('mandatory-lbl');
			}
		});
</script>
<form id="frmwrkordrResponsibility" name="frmwrkordrResponsibility">
	<table>
	<tr>
		<td>
		<input id="chkoverresp" name="chkoverresp" value=" " type="checkbox" /> <label>Overall Responsibility</label>
		</td>
	</tr>
		<tr>
			<td>
        
		        	<div><label  class="mandatory-lbl" id="lbltrade">Trade</label>
		            <span style="padding-left:225px;"><label class="mandatory-lbl">Responsibility</label></span>
		            </div>
		            <div class="easyui-paddingbfpx" >
		            <input id="cmbPwrdtradeid" name="cmbPwrdtradeid" class="easyui-combobox" style="width:240px;"value="" />
		            <span style="padding-left:5px;">
		             <input id="cmbPwrdempid" name="cmbPwrdempid" class="easyui-combobox" style="width:255px;" >
		          	</span>
		          	<span style="padding-right:20px;float:right;margin-top:10px;">
		          	
		             <input type="button" id="btnAddtoGrid" name="btnAddtoGrid" class="easyui-button" value="Add" >
		             <input type="button" id="btnClr" name="btnClr" class="easyui-button" value="Clear" >
		             <!-- <input type="button" id="btnDelete" name="btnDelete" class="easyui-button" value="Delete" > -->
		          	</span>
					</div>
					<span class="" >
		           
		            <label style="padding-left:5px;" id="err_cmbPwrdtradeid" class="tpm-errormsg" >
		          	</label>
		          	<label style="margin-left:46%; margin-top:-2%;" id="err_cmbPwrdempid" class="tpm-errormsg">
		          	</label>
					</span>
					<!--<span id="err_cmbPwrdtradeid" class="tpm-errormsg" style="" ></span>
					<span id="err_cmbPwrdempid" class="tpm-errormsg" style="" ></span>
			
			--></td>
		</tr>
		<tr>
			<td>
			<table id="workOResp"  ><tr><td/></tr></table>
			<div id="workOrderResp_pager"></div>
			<div class="clearfix"></div>
<!--			<input type="hidden" id="hdnPwrdmasterid" name ="hdnPwrdmasterid" value="${requestScope.planKeyId}"/>-->
			<input type="hidden" id="hdnfilterstr" name ="hdnfilterstr" value="${requestScope.controls} "/>
			
			</td>
		</tr>
		</table>
			<input type="hidden" id="txtPwrdmasterid" name="txtPwrdmasterid" value=""/>
			<input type="hidden" id="txtPwrdkeyid" name="txtPwrdkeyid" value=""/>
			<input type="hidden" id="woResrowmachId" name="woResrowmachId" value="${ requestScope.machId}"/>
</form>
<input type="hidden" id="txtPwrmGenral" name="txtPwrmGeneral" value=""/>
			<input type="hidden" id="txtPwrmTradewise" name="txtPwrmTradewise" value=""/>
		
<!--			<input type="hidden" id="hdnControl" name="hdnControl" value="${ requestScope.datastr}"/>-->