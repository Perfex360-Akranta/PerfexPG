
 <script type="text/javascript">
 
	jQuery(document).ready(function(){//alert("JHCLIT");
		jQuery('#frmJhClitStd .easyui-combobox').css('text-transform', 'uppercase');
			//for disabling factory section cell and machine
		
				var url = jQuery('#hiddenUrl').val();
				
				if(url == 'jhClit_input.jhclit')
					jQuery('#hdnFrmName').val("CLTI Standard Creation");
				else if(url == 'jhClitModification_input.jhclit')
					 jQuery('#hdnFrmName').val("CLTI Standard Modification");
				else if(url == 'jhClitview_input.jhclit')
					jQuery('#hdnFrmName').val("CLTI Standard View");
					
				jQuery('#hdnUrl').val(url);
				jQuery("#newstndard").css('display','none');
				jQuery('#addInfo').css('display','none');
				jQuery('#filemgr').css('display','none');
				jQuery('#addMachine').css('display','none');

			
				fillComboBox("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter");
				
				var machineHirerachyId = null;
				/*for opening from Breakdownmst*/
				var clismchId = jQuery("#cmbClisMachineid").combobox("getValue");//getFieldValue('cmbClisMachineid');
				//alert('clismchId :'+clismchId );
				if(clismchId != null && clismchId != "")
				{
					jQuery('#hien').val(clismchId);
					//viewbtncall(clismchId);
					loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmJhClitStd","&machId="+clismchId);
					readOnlyFields('cmbClisMachineid');
					var frmName = jQuery('#hdnFrmName').val();
					navigateToNextForm("jhClit_mcharea.jhclit?q=2&loadContentDivId=jhclitgrid2&preLoadContentDivId=preloadDIVid2&mchId="+clismchId,frmName);
					//LoadForm("jhclitgrid1","preloadDIVid2","jhClit_mcharea.jhclit","dispErr","","jhclitmcharea_errorCallBack");
					popFormNavigation();
				}
				else{
					enableFields('cmbClisMachineid');	
					LoadForm("jhclitgrid1","preloadDIVid","grid_jhclitcount.jhclit","dispErr","","jhclitcountgrid_errorCallBack");
					/* for functionalLocation*/
					var factId = jQuery("#frmJhClitStd input[id='factory']").val();
					var sectionId = jQuery("#frmJhClitStd input[id='section']").val();
					var cellId = jQuery("#frmJhClitStd input[id='cell']").val();
					var machId = jQuery("#frmJhClitStd input[id='machine']").val();
					var flid = jQuery("#frmJhClitStd input[id='flid']").val();
					
					var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
					//alert(dataStr);
					loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmJhClitStd",dataStr);
					
				/*---------*/
				}
				
				jQuery('#btnAddMachine').click(function(){

				multiSelectSavePop("addmachine_input.admch","","addmach","","tick,txtmcamname,txtmcamcode","true","","multiSelectSaveOk_Callback");
					
				});

		 	
		var chkMachineId = getFieldValue('cmbClisMachineid');
			//if(chkMachineId != " " && chkMachineId !="" && chkMachineId != null )
			//	{}
			//else
				//alert("Select Equipment");
			
	});
	
	function dispErr(){}
	function jhclitcountgrid_errorCallBack(){}

	function  frmJhClitStdcmbClisFactoryid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisSectionid","sectionCombo.commonFilter" );
	}
	function  frmJhClitStdcmbClisSectionid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter");
	}
	function  frmJhClitStdcmbClisCellid_onLoadSuccess()
	{
		fillComboBox("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter");
	}
	function  frmJhClitStdcmbClisMachineid_onLoadSuccess()
	{
		//fillComboBox("frmJhClitStd","cmbClisFactoryid","factroyCombo.commonFilter" );
		
	}
	function  frmJhClitStdcmbClisFactoryid_onSelect(record)
	{
	
		jQuery("#cmbClisSectionid").combobox('clear');
		jQuery("#cmbClisCellid").combobox('clear');
		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisSectionid","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?factId="+ record.id );

	}
	   function  frmJhClitStdcmbClisSectionid_onSelect(record){
	    	  
	    	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbClisFactoryid");
		 }

	    function  frmJhClitStdcmbClisCellid_onSelect(record){
	    		
	    	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbClisSectionid","cmbClisFactoryid");
		 }
	/*function  ffrmJhClitStdcmbClisSectionid_onSelect(record)
	{
		jQuery("#cmbClisCellid").combobox('clear');
		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisCellid","cellCombo.commonFilter?sectId="+record.id  );
		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?sectId="+ record.id );
	}
	
	function  frmJhClitStdcmbClisCellid_onSelect(record)
	{
		jQuery("#cmbClisMachineid").combobox('clear');
		reloadCombo("frmJhClitStd","cmbClisMachineid","machineCombo.commonFilter?cellId="+ record.id );
	}*/
	
	function  frmJhClitStdcmbClisMachineid_onSelect(record)
	{
		filcmbbox(record.id);
		
		loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmJhClitStd","&machId="+record.id);
	}
	
 	function filcmbbox(machineId)
 	{
		processAjaxCalls('jhClit_fillcombo.jhclit','q=2&eqpId='+machineId,'frmSuccess','frmError');
	 
	}
    function frmJhClitStd_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('cmbCliscellid',keyIds.cellId);
		setFieldValue('cmbClisMachineid',keyIds.machId);
		jQuery('#clisFactID').val(keyIds.factId);
		//alert('reload'+keyIds.machId);
		//jQuery("#cmbAbnmEquipmentid").combobox("disable");
		//var cellId = jQuery("#cell").val();
		reloadMachine("frmJhClitStd",'cmbClisMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		reloadCombo("frmJhClitStd","cmbClisShiftid","combo_clitShift.jhclit?factId ="+ keyIds.factId );
		//reloadCombo("frmJhClitStd","cmbCostcenter","costCenter.commonFilter?cellId="+keyIds.cellId  );
		//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?compId="+keyIds.compId +'&factId='+keyIds.factId+'&sectId='+keyIds.sectId+"&cellId="+keyIds.cellId);
		
	}
    //for click view button
    function viewbtncall()
    {
		
		//alert(mchId);
		var selmchId = jQuery("#cmbClisMachineid").combobox("getValue");
		var machineID =  jQuery('#hien').val();//alert("j:  "+machineID);
		/*if(machineID == "" || machineID == null){
			 machineID = bdmMachineId;	
			}*/
		 if( selmchId  == null ||selmchId == "") 
        	{
			 jQuery('#hien').val(" ");
        	}
		 
    	jQuery("#jhclitgrid1").load('jhClit_clickview.jhclit','?&machineID='+selmchId, function(response, status, xhr) {
			   if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    alert(msg);
			   jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
			  }
			   if (status == "success") {
					//alert("sucess   view");
				   }
			});	
    }
	function frmSuccess(result)
	{
	 	//alert("dfd"+Object.keys(result));
		jQuery("#cmbClisFactoryid").combobox('setValue',result.machineHirerachy.factory);
		jQuery("#cmbClisSectionid").combobox('setValue',result.machineHirerachy.section);
		jQuery("#cmbClisCellid").combobox('setValue',result.machineHirerachy.cell);
	}
	function frmError(result)
	{
		alert('Err');
	}
function back_div()
{
	var chkfp = jQuery('#fstGrd').val();
	  if(chkfp != "1a")
		navigateToPrevForm();
	  else
		  alert('No Previous Pages');
}

/***/
 jQuery("#cmbClisFactoryid").keydown(function() {
	
     var newVal = $("#cmbClisFactoryid").val();
     var quantityRegexp = /^(0|[1-9]+[0-9]*)$/;

     // success
     if (quantityRegexp.test(newVal)) {
         oldVal = newVal;
         // hide error
         jQuery("#cmbClisFactoryid_error").hide();
     }

     // else failure
     else {
         jQuery("#cmbClisFactoryid").val(oldVal);
         // display error message
         jQuery("#cmbClisFactoryid_error").show();
     }
 });

 

/***/

</script>
<form name="frmJhClitStd" id="frmJhClitStd" action="" method="post">
<div id="wrapper" >


	<div id="JHCLIT" class="divbrdr"  style="margin-top:-10px;margin-top:-4px\9;margin-left:-3%;width:80%"> 
	<table width="100%"  >  
			 <tr >
			 	<td colspan='3'>
			 	<div  id="frmJhClitStdFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbClisFactoryid" value="${requestScope.cliTlStandards.clisFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbClisSectionid" value="${requestScope.cliTlStandards.clisSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbClisCellid" value="${requestScope.cliTlStandards.clisCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbClisMachineid" value="${requestScope.cliTlStandards.clisMachineid}"  ></input>
				<input type="hidden" id="flid" name="cmbClisFlid" value="${requestScope.cliTlStandards.clisFlid}" ></input>
				</div>
			 			<div id="clisfunLocation" style="margin-left: 60px "></div>
			 	</td>
			 </tr>
			 <tr>
			 	<td style="width:30%">
			 		<div style="margin-top:10px;width:50%"> 
			 		<div class="mandatory-lbl" style="margin-left: 60px;" ><label>Equipment</label></div>
		               	<div class="easyui-paddingbfpx" style="margin-left: 60px;width:160px;">
		               	<input id="cmbClisMachineid" name="cmbClisMachineid" class="easyui-combobox" onkeydown="" style="width:255px;" value="${requestScope.BdmMachineId}"  >
					</div>
					</div>
			 	</td>
				<td colspan="2" > 
				<div style="padding-left:280px;width:100%;position:relative;">
				<table>
					<tr>
						<td valign="bottom">
							<div id="addMachine" style="float:right">
								<input type="button" class="easyui-button" id="btnAddMachine" value="ADD MACHINE AREA" style="width:145px;height:23px;height:28px\9;"/>
							</div>
						</td>
						<td valign="bottom">
							<div id="addInfo" style="float:right;">
							<input type="button" class="easyui-button" id="addInfo" value="ADDITIONAL INFORMATION" style='height:23px; height:28px\9; width:170px;' />
							</div>
						</td>
						<td valign="top" >
							<div style="position:relative;width:110px;">
								<div id="filemgr" style="float: right;position:absolute;top:0;">
	<!--							  <input class="easyui-button" id="btnClitFilManage" type="button" value="File Manager" style=" ">-->
								  </div>
							  </div>
						 </td>
						<td valign="bottom">
							<div id ='newstndard' style="float:right;">
							<input type="button" class ="easyui-button" value="New Standard" id="newstd" style="height:23px;height:28px\9;"/>
							</div>
						</td>
						<td valign="bottom">
						<input type="button" class ="easyui-button" value="VIEW" id="vwebtn"  onclick='viewbtncall();' style='height:23px;height:28px\9;'/>
						</td>
						<td valign="bottom">
						<input type="button" class ="easyui-button" value="BACK" id="bckbtn"  onclick='back_div();' style='height:23px;height:28px\9;margin-left:15px;'/>
						</td>
						
					</tr>
				</table>
			</div>
		</td>
	</tr>
</table>
	<input type="hidden" id="hien" name="hien" class="easyui-text"  style="width:155px;" value=""  >
   	<input type="hidden" id="txtClisAssemblyid" name="txtClisAssemblyid" class="easyui-text" style="width:155px;" />
   	<input type="hidden" id="txtClisKeyid" name="txtClisKeyid" class="easyui-text" style="width:155px;" />
   	<input type="hidden" id="rcdid" class="easyui-text" style="width:155px;" />
</div>
<div  style="margin-left:25px;margin-right:20px;width: 965px; "><hr></div>
<div style="padding-left:30px;">
<div id="firstGrid"   style="display:none">Double Click on the data row to view Equipment Area</div>
<div id="secondGrid"  style="display:none">Double Click on the Machine Area name to view the Activities</div>
<div id="thridGrid"   style="display:none;margin-right:50px; margin-left:-6px;">Double Click on the Activity to edit/view the standard</div>
<!--<div id="fourthfrm"  class = "" style="display:none;color:lightskyblue;margin-left:580px;"></div>-->

</div>
<div id="preloadDIVid1"></div>
<div id="preloadDIVid2"></div>
<div id="preloadDIVid3"></div>
<div id="preloadDIVid4"></div>
<div id="Loadjhclitgrid1" ></div>
<div>
<div id="jhclitgrid1" class="divbrdr" style="width:80%;float:left;margin-left:2%;"></div>
<div id="jhclitgrid2" class="divbrdr" style="width:80%;margin-left:-3%;"></div>
<div id="jhclitgrid3" class="divbrdr" style="width:80%;margin-left:2%;"></div>
<div id="Loadjhclitfrm" class="divbrdr" style="width:100%;margin-left:-3%;"></div>
</div>

<input type="hidden" id ="hdnUrl"/>

<input type="hidden" id="mode" value="${ requestScope.generalMaintainanceBean.formMode }">
</div>

</form>
<div id="selInactiveDate" class="divdelContainer " style="display:none;">
	<center>
	<div style="margin-top:15%;" style="">
	<label style="padding-right: 10px;">Inactivated Date </label>
<!--	<input class="easyui-datebox " id="dteClisInactivateddate" name="dteClisInactivateddate" style="width:150px;" ></input>-->
	<input class="easyui-datebox easyui-text" id="dteClisInactivateddate" name="dteClisInactivateddate" style="width:150px;" ></input>
	<input type="button" class="easyui-button" id="btnOk" value="OK"/>
	</div>
</center>
</div>
<input type="hidden" id="modeHdn" name="modeHdn" value="${requestScope.formMode}"/>
<input type="hidden" id="fmodeHdn" name="fmodeHdn" value="${requestScope.fMode}"/>
<input type="hidden" id="bdmHdnM"  name="bdmHdnM" value="${requestScope.bdmmode}"/>
<input type="hidden" id="clisFactID"  name="clisFactID" value=""/>
<input type="hidden" id="hdnFrmName"  name="hdnFrmName" />

