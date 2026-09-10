
<script type="text/javascript" src="js/prvntv_mntnc_js.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		fillComboBox("frmPmStandard","cmbpmsdFactoryid","factroyCombo.commonFilter" );
		fillComboBox("frmPmStandard","cmbPmsdSectionid","sectionCombo.commonFilter" );
		fillComboBox("frmPmStandard","cmbPmsdCellid","cellCombo.commonFilter");
		fillComboBox("frmPmStandard","cmbPmsdMachineid","machineCombo.commonFilter");
		fillComboBox("frmPmStandard","cmbCostCenter","costCenter.commonFilter");

		/* for functionalLocation*/
		var factId = jQuery("#frmPmStandard input[id='factory']").val();
		var sectionId = jQuery("#frmPmStandard input[id='section']").val();
		var cellId = jQuery("#frmPmStandard input[id='cell']").val();
		var machId = jQuery("#frmPmStandard input[id='machine']").val();
		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId;
		//alert(dataStr);
		loadFunctionalLocation("pmsdfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandard",dataStr);
		
	/*---------*/
	});
	/***Function for Auto Fil ComBo**/
  function  frmPmStandardcmbPmsdFactoryid_onSelect(record)
	{
	
		jQuery("#cmbPmsdSectionid").combobox('clear');
		jQuery("#cmbPmsdCellid").combobox('clear');
		jQuery("#cmbPmsdMachineid").combobox('clear');
		reloadCombo("frmPmStandard","cmbPmsdSectionid","sectionCombo.commonFilter?factId="+record.id);
		reloadCombo("frmPmStandard","cmbPmsdCellid","cellCombo.commonFilter?factId="+record.id  );
		reloadCombo("frmPmStandard","cmbPmsdMachineid","machineCombo.commonFilter?factId="+ record.id );
	}
  function  frmPmStandardcmbPmsdSectionid_onSelect(record){
	  
	jQuery("#cmbPmsdFactoryid").combobox('clear');
	jQuery("#cmbPmsdCellid").combobox('clear');
	jQuery("#cmbPmsdMachineid").combobox('clear');

	fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmbPmsdFactoryid");
	/*reloadCombo("frmPmStandard","cmbPmsdSectionid","factroyCombo.commonFilter?sectId="+record.id);
	reloadCombo("frmPmStandard","cmbPmsdCellid","cellCombo.commonFilter?sectId="+record.id  );
	reloadCombo("frmPmStandard","cmbPmsdMachineid","machineCombo.commonFilter?sectId="+ record.id );*/
	
  }

  function  frmPmStandardcmbPmsdCellid_onSelect(record){
	jQuery("#cmbPmsdSectionid").combobox('clear');
	jQuery("#cmbPmsdFactoryid").combobox('clear');
	jQuery("#cmbPmsdMachineid").combobox('clear');
	fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbPmsdSectionid","cmbPmsdFactoryid");
	/*	reloadCombo("frmPmStandard","cmbPmsdSectionid","factroyCombo.commonFilter?cellId="+record.id);
	reloadCombo("frmPmStandard","cmbPmsdCellid","sectionCombo.commonFilter?cellId="+record.id  );
	reloadCombo("frmPmStandard","cmbPmsdMachineid","machineCombo.commonFilter?cellId="+ record.id );*/
	
  }
  function  frmPmStandardcmbPmsdMachineid_onSelect(record)
	{
	 filcmbbox(record.id);
	}
	
	function filcmbbox(machineId)
	{
		processAjaxCalls('pmsMachine_fillcombo.prv','q=2&eqpId='+machineId,'frmSuccess','frmError');
	}
	function frmSuccess(result)
	{
		jQuery("#cmbpmsdFactoryid").combobox('setValue',result.machineHirerachy.factory);
		jQuery("#cmbPmsdSectionid").combobox('setValue',result.machineHirerachy.section);
		jQuery("#cmbPmsdCellid").combobox('setValue',result.machineHirerachy.cell);
		jQuery("#cmbCostCenter").combobox('setValue',result.machineHirerachy.costcenterid);
	}
	function frmError(result)
	{
		alert('Err');
	}
/***End**/
  function frmPmStandard_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		setFieldValue('cmbPmsdcellid',keyIds.cellId);
		setFieldValue('cmbPmsdMachineid',keyIds.machId);
		//alert('reload'+keyIds.machId);
		//jQuery("#cmbAbnmEquipmentid").combobox("disable");
		//var cellId = jQuery("#cell").val();
		reloadMachine("frmPmStandard","cmbPmsdMachineid",keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
		//reloadCombo("frmJhClitStd","cmbCostcenter","costCenter.commonFilter?cellId="+keyIds.cellId  );
		//reloadCombo("frmAbnormality","cmbAbnmEquipmentid","machineCombo.commonFilter?compId="+keyIds.compId +'&factId='+keyIds.factId+'&sectId='+keyIds.sectId+"&cellId="+keyIds.cellId);
		
	}
</script>
<form id="frmPmStandard" name="frmPmStandard" action="" method="post">
<div id="wrapper">
<div class="main-cntborder easyui-paddingbtpx" >

<table border="0" class="tablealign-center" style="margin-left:7.5%;">
    <tr>        
    <td colspan='3'>
			 	<div  id="frmPmStandardFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbPmsdFactoryid" value="${requestScope.cliTlStandards.clisFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbPmsdSectionid" value="${requestScope.cliTlStandards.clisSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbPmsdCellid" value="${requestScope.cliTlStandards.clisCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbPmsdMachineid" value="${requestScope.cliTlStandards.clisMachineid}"  ></input>
				</div>
			 	<div id="pmsdfunLocation" style=""></div>
	 </td>   
	 </tr>
	 <tr>  
        <td style="width:33%" >
            <div style="float:left;">
               
               <div  class="easyui-paddingbfpx">
                    <label>Equipment</label>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdMachineid" name="cmbPmsdMachineid" class="easyui-combobox"  style="width:300px;" value=""  >                    
                </div>  
            </div>
        </td>
        
        <td valign='top' style="width:33%;">
            <div id="tt" style="padding-left:0px;">
                <div  class="easyui-paddingbfpx"><label>Cost Center</label></div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbCostCenter" name="cmbCostCenter" class="easyui-combobox"  style="width:300px;" value=""  >
                </div>
               
            </div>	
        </td>
        
        <td style="width:33%;vertical-align: top" >
            <div style="float:left;">
                              
                <div class="easyui-paddingbfpx" style="margin-top: 20px;"> 
                    <input type="checkbox"><label>File Manager</label>                    
                </div>
            </div>
        </td>
    </tr>
</table>

<!-- Main page content div -->

<div style="clear:both"></div>

<div id="bind_source_div_fnd" ></div>
<div id="bind_source_div_actws" ></div>
<div id="preLoadFilter">
<div id="bind_source_div_ginfo" ></div>
<div class="clearfix"></div>
</div>
</div>
</div>
</form>