

<script> 
jQuery.noConflict();
jQuery(document).ready(function()
{	
	var actionPart = jQuery('#hiddenUrl').val();
	jQuery('#submitForm').val('frmKMIRpt'); 
	initialiseForm('frmKMIRpt');		
	fillComboBox("frmKMIRpt","cmbKaukCalendaryear","comboYearTest.progcal");
	var dataStr = getFnLocnValues();
	loadFunctionalLocation("kpiActKkfunLocation","functionalLoc.kpiActKk","kpiActKkfunLocationValues","frmKMIRpt",dataStr);
});
function viewGrid(url,filterString)
{	
	if( validateFilterSelection(filterString))
	{
		processGridnew(url,filterString,"grdKpiActualkk","pager","","","","");
		return true;
	}
}
function validateFilterSelection(filterString){
	if( ! checkFilterValueExist(filterString,"fromLink"))
	{
		filterString+="&fromLink="+jQuery("#hdnIndicLevel").val();
	}	
	return true;
}
function  frmKMIRptcmbKaukCalendaryear_onSelect(record)
{
	var dataStr=getFnLocnValues();
	var cellId = jQuery("#frmKMIRpt input[id='cell']").val();
	if(cellId=='' || cellId==' ' || cellId==null){
alert('Select Functional Location');
jQuery("#cmbKaukCalendaryear").combobox('clear');
		}
	else{
		fnClear(dataStr);	
	}
}
function getFnLocnValues()
{
	var companyId =getCompany("frmKMIRpt");
	var locnId=getLocation("frmKMIRpt");
	var factId = getFactory("frmKMIRpt");
	var sectionId = getSection("frmKMIRpt");
	var cellId = getCell("frmKMIRpt");
	var machineId=getMachine("frmKMIRpt");	
	var dataStr = "&compId="+companyId+"&locnId="+locnId+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId;
	if(machineId.trim().length>0)
	{
		dataStr+="&machId="+machineId;
	} 
	return dataStr;   
}
function getCompany(frmId)
{
	var companyId = jQuery("#"+frmId+ "input[id='company']").val();
	return companyId;
}
function getLocation(frmId)
{
	var locnId=jQuery("#"+frmId+" input[id='location']").val();
	return locnId;
}
function getFactory(frmId)
{
	var factId=jQuery("#"+frmId+" input[id='factory']").val();
	return factId;
}
function getSection(frmId)
{
	var sectionId = jQuery("#"+frmId+" input[id='section']").val();
	return sectionId;
}
function getCell(frmId)
{
	var cellId = jQuery("#"+frmId+" input[id='cell']").val();
	return cellId;
}
function getMachine(frmId)
{
	var machineId=jQuery("#"+frmId+" input[id='machine']").val();
	return machineId;
}
function getCalYear()
{
	return getFieldValue("cmbKaukCalendaryear","frmKMIRpt");
}
function getPillarId()
{
	return getFieldValue("txtKaukPillarid","frmKMIRpt");
}
function fnClear(dataStr)
{	
	var cellId = jQuery("#frmKMIRpt input[id='cell']").val();
	var mnthyear=jQuery("#cmbKaukCalendaryear").combobox('getValue');
	var pillarId=jQuery("#txtKaukPillarid").val();
	var filterString;
	filterString += '&cellId='+cellId;
	filterString += '&mnthyear='+mnthyear;
	filterString += '&pillarId='+pillarId;
	var actionPart = jQuery('#hiddenUrl').val();
	if(actionPart=="KmiReport_input.kpiActKk"){
		if(cellId=='' || cellId==' ' || cellId==null){
			
			viewGrid("KmiReport_input.kpiActKk","" );
		}
		else if(mnthyear!='' && mnthyear!=' ' && mnthyear!=null){
			
			viewGrid("KmiReport_input.kpiActKk" ,filterString);
		}
		}
	
}
function frmKMIRpt_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	var year = jQuery('#hdnYear').val();
	jQuery('#cmbKaukCalendaryear').combobox('setValue',year);	
	setFieldValue('cmbKaukCompanyid',keyIds.compId);
	setFieldValue('cmbKaukLocationid',keyIds.locnId);
	setFieldValue('cmbKaukFactoryid',keyIds.factId);
	setFieldValue('cmbKaukSectionid',keyIds.sectId);
	setFieldValue('cmbKaukCellid',keyIds.cellId);
	setFieldValue('cmbKaukMachineid',keyIds.machId);
	var dataStr=getFnLocnValues();
	fnClear(dataStr);	
}
</script>
<div id="wrapperRpt">		
	<form name="frmKMIRpt" id="frmKMIRpt">
	
	   	<table  align="center" >
	   		<tr>
			    <td >
			    	<table  align="center" >
	        			<tr>
            				 <td valign="top" width="50%" >	
								<div  id="frmKMIRptFuntKeyIds">
									<div >
										<input type="hidden" id="company" name="cmbKaukCompanyid" value="${requestScope.compId}"  >
										<input type="hidden" id="location" name="cmbKaukLocationid" value="${requestScope.locnId}"  >
										<input type="hidden" id="factory" name="cmbKaukFactoryid" value="${requestScope.factId}"  >
										<input type="hidden" id="section" name="cmbKaukSectionid" value="${requestScope.sectId}"  >
										<input type="hidden" id="cell" name="cmbKaukCellid" value="${requestScope.cellId}"  >
										<input type="hidden" id="machine" name="cmbKaukMachineid" value="${requestScope.machId}">
									</div>									
									<div class="" style="padding-right: 395px;width:320px;">
										<div id="kpiActKkfunLocation" style=" width: 835px; "></div>
										<div id="txtFct" class="tpm-errormsg" style="padding-left:30px;"></div>
										<div id="err_err_cell" class="tpm-errormsg" style="padding-left:30px;"></div>
									</div>
									<div class="clear"></div>
								</div>			
						 </td>
		                 <td valign="top" width="10%" >
			             	<div style="padding-left:5%;">
				             	<div  class="easyui-paddingbfpx">
				                     <label class="mandatory-lbl">Year</label>                  
				                </div> 
				                <div class="easyui-paddingbfpx"> 
				                     <input id="cmbKaukCalendaryear" name="cmbKaukCalendaryear" class="easyui-combobox"  style="width:60px"  value="${requestScope.kpiTlActualKk.kaukCalendaryear}" / >                       
				                </div>	
			                </div>
		                 </td > 
		               </tr>
			    	</table>
			    </td>
		    </tr>	
		      		   
		</table> 
	
	    <div>        		 
		    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
		    <input type="hidden" id="txtKaukPillarid" name="txtKaukPillarid" value="${requestScope.kpiTlActualKk.kaukPillarid}"/>
		    <input type="hidden" id="txtKaukPillar" name="txtKaukPillar" value="${requestScope.kaukPillar}"/>
		    <input type="hidden" id="txtFrmMode" name="txtFrmMode" value="${requestScope.frmMode}"/>
		    <input type="hidden" id="dblColIndex" name="dblColIndex" value=""/>  
		</div>	
	    
	    <div id="divKpiActual" style="width:85%;">
	    	<table id="grdKpiActualkk"></table>
			<div id="pager"></div>		
		</div>
		<input type="hidden" id="hdnCompId" name="hdnCompId" value="${requestScope.compId}"/>
		<input type="hidden" id="hdnLocnId" name="hdnLocnId" value="${requestScope.locnId}"/>
	 	<input type="hidden" id="hdnFactId" name="hdnFactId" value="${requestScope.factId}"/>
	    <input type="hidden" id="hdnSectId" name="hdnSectId" value="${requestScope.sectId}"/>
	    <input type="hidden" id="hdnCellId" name="hdnCellId" value="${requestScope.cellId}"/>
	    <input type="hidden" id="hdnMachineId" name="hdnMachineId" value="${requestScope.machId}"/>
	    <input type="hidden" id="hdnDeptId" name="hdnDeptId" value="${requestScope.deptId}"/>
	    <input type="hidden" id="hdnPillarId" name="hdnPillarId" value="${requestScope.kaukPillar}"/>
	    <input type="hidden" id="hdnYear" name="hdnYear" value="${requestScope.year}"/>  
	    <input type="hidden" id="hdnIsActual" name="hdnIsActual" value="${requestScope.isActual}"/> 
	    <input type="hidden" id="hdnFilter" name="hdnFilter" value="${requestScope.filter}"/> 
	    <input type="hidden" id="hdnIndicLevel" name="hdnIndicLevel" value="${requestScope.IndicLevel}"/>
	    <input type="hidden" id="hdnPrevUrl" name="hdnPrevUrl" value="${requestScope.prevUrl}"/>
	    <input type="hidden" id="hdntype" value="${requestScope.type}"/>
		<input type="hidden" id="hdnpillCode" value="${requestScope.pillCode}"/>
		<input type="hidden" id="hdnfrmTgtOrActual" value="${requestScope.frmTgtOrActual}"/>
	        </form>
</div>
