

<script type="text/javascript">
jQuery(document).ready(function()
		{
			initialiseForm('frmtargetgroup');
			jQuery('#submitForm').val('frmtargetgroup');
			
			
			fillComboBox("frmtargetgroup","cmbTargetGroup","tgtGroup.commonFilter"); 
			var factId = jQuery("#frmtargetgroup input[id='factory']").val();
			var sectionId = jQuery("#frmtargetgroup input[id='section']").val();
			var cellId = jQuery("#frmtargetgroup input[id='cell']").val();
			var machId = jQuery("#frmtargetgroup input[id='machine']").val();
			var flid =jQuery("#frmtargetgroup input[id='flid']").val();
			
			 var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
			// alert(dataStr);
			 loadFunctionalLocation("targetfunLocation","functionalLoc.commonFilter","targetfunLocation","frmtargetgroup",dataStr);
			
			// alert(flid);
			

			 
			 var targetid = jQuery("#hdnTgtmKeyid").val();
				
			      if(targetid.trim().length<=0){
			    
			    	  disableField('frmtargetgroup','cmbTargetGroup');
			          }
			      jQuery("#btndelete").click(function(){
				      
						var datastring = "&datastring="+getGridSelectArray("TargetGpgrid");
						//alert(getGridSelectArray("TargetGpgrid"));
						
						processAjaxCalls("targetform_delete.topi?"+datastring, "",'remove_successCallBack','remove_errorCallBack');
					      
			      });
			});

function remove_successCallBack(result)
{
	alert(result.successData.msg);
	jQuery("#TargetGpgrid").trigger("reloadGrid");
}
function remove_errorCallBack()
{
}


function frmtargetgroup_FuntLocHierarchy_SuccessCallBack(result)
{

		var flId = result.flId;
		//alert(flId+"  "+result.flid);
		 processGridnew("TargetGroupform_input.topi","?q=2&flId="+flId,"TargetGpgrid","TargetGppager","","taregtdoubleclick","","loadcomplete");
}
function frmtargetgroup_beforeSubmit()
{	
      
      return "&employee="+getGridSelectArray("TargetGpgrid");
}

function frmtargetgroup_beforeDelete()
{
	var mstKeyid=jQuery("#hdnTgtmKeyid").val();
	 if(mstKeyid==null || mstKeyid=="" || mstKeyid==" ")
	 	return false;
	 else
		 var data=confirm("Are You Sure to Delete?");
	 if(data)
		 return gridData;
	 else
		 return false;
	 
}
function frmtargetgroup_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearForm('frmtargetgroup');
}



function loadcomplete(ids){
	var row = jQuery("#TargetGpgrid").jqGrid('getDataIDs');
	var comboid = getFieldValue("cmbTargetGroup");	//alert(comboid);	
	 for(var i=0;i<row.length;i++)
	 {
		var roleid = jQuery("#TargetGpgrid").jqGrid('getCell',row[i],"TGTD_TGTM_KEYID");
		if(comboid == roleid)
			{
			//alert("4");
			jQuery('#TargetGpgrid').setSelection(row[i], true);
			jQuery("#jqg_TargetGpgrid_"+row[i]).attr('checked','checked');
			
			}
	 }
}




</script>
<form id="frmtargetgroup" name="frmtargetgroup">
	<div id="wrapper">
	<div style="margin-left: 25%">
		<table>
			<tr>
				<td >
						<div id="frmtargetgroupFuntKeyIds"  >							
									<input type="hidden" id="factory" name="factory" value=" "  ></input>			
									<input type="hidden" id="section" name="section" value=" "  ></input>
									<input type="hidden" id="cell" name="cell" value=" "  ></input>
									<input type="hidden" id="machine" name="machine" value=" "  ></input>
									<input type="hidden" id="flid" name="cmbtgtmFlid" value="${requestScope.entTlTargetgroupmst.tgtmFlid}"></input>							
						</div>						
						<div id="targetfunLocation" style="width:600px;"></div>
					</td>
			</tr>
			<tr>
				<td valign="top" style=" width : 239px;" >
							<div class="easyui-paddingbfpx" >
								<label >Target Group</label>
							<span class="easyui-paddingbfpx" style="margin-left: 170px">
								<label class="mandatory-lbl">Target Group</label>
							</span>	</div>
							<div >
								<input class="easyui-combobox" id="cmbTargetGroup" name="cmbTargetGroup"  value="${requestScope.entTlTargetgroupmst.tgtmKeyid}" style=" width : 200px;"  />
							<span style="margin-left: 40px">
								<input class="easyui-text" id="txtTgtmTitle" name="txtTgtmTitle" maxlength="100" value="${requestScope.entTlTargetgroupmst.tgtmTitle}" style=" width : 200px;text-transform: uppercase"  />
							</span>
							<span>
								<input type="button" class="easyui-button" style="width:75px;" id="btndelete" name="btndelete" value="Delete"/>
							</span>
							<div id="err_txtTgtmTitle" class="tpm-errormsg" style="display: block; padding-left:245px"></div>
							</div>
				</td>
			</tr>
	</table>

<table id="TargetGpgrid">
	<tr>
		<td>
		</td>
	</tr>
</table>
<div id='TargetGppager'></div>
</div>
		</div>
		<input type="hidden" id="mode" value=""/>
<input type="hidden" id="hdnTgtmKeyid" name="hdnTgtmKeyid" value="${requestScope.entTlTargetgroupmst.tgtmKeyid}"/>
</form>