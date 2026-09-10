<script type="text/javascript" src="js/costinfo_js.js"></script>

<script type="text/javascript">

jQuery(document).ready(function(){
		initialiseForm('frmCostInfo');
//		jQuery('#submitForm').val('frmCostInfo');
		
		//jQuery('#hdnGlbType').val("Estimate");
		//jQuery('#hdnGlbType').val("Actual");
		
		fillComboBox("frmCostInfo","cmbspcnFactoryid","factroyCombo.commonFilter" );
		/*fillComboBox("frmCostInfo","cmbspcnSectionid","sectionCombo.commonFilter" );						
		fillComboBox("frmCostInfo","cmbspcnCellid","cellCombo.commonFilter" );						
		fillComboBox("frmCostInfo","cmbspcnMachineid","machineCombo.commonFilter" );
		fillComboBox("frmCostInfo","cmbspcnAssemblyid","assembly.commonFilter");
		fillComboBox("frmCostInfo","cmbspcnTrade","trade.commonFilter" );
		fillComboBox("frmCostInfo","cmbspcnRefdocno","combo_RefDocNo.crt");						
		fillComboBox("frmCostInfo","cmbPhenomena","combo_phenomena.crt");
		fillComboBox("frmCostInfo","cmbCause","combo_cause.crt");*/		

		formatDateBox('dteplanDate','dd-MMM-yyyy');
		formatDateBox('dteworkStart','dd-MMM-yyyy');
		formatDateBox('dteworkEnd','dd-MMM-yyyy');

		var woId=jQuery('#hdnwoId').val();

		processGridnew('costSummary_view.crt',"?q=2&woId="+woId,"costSumryGrid","costSumryPager","","","","costGridLoad","costGridError");  

		fnFillRefName();
		
	/* for functionalLocation*/
		var factId = jQuery("#frmCostInfo input[id='factory']").val();
		var sectionId = jQuery("#frmCostInfo input[id='seciton']").val();
		var cellId = jQuery("#frmCostInfo input[id='cell']").val();
		var machId = jQuery("#frmCostInfo input[id='machine']").val();
		var flid = jQuery("#frmCostInfo input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;	
		//alert(dataStr);
		loadFunctionalLocation("spcnfunLocation","functionalLoc.crt","spcnfunLocationValues","frmCostInfo",dataStr);
	/*---------*/	
			
		jQuery('#tabcostInfo').tabs({  
	        border:true,  
	        onSelect:function(title){
	        	//alert(title+' is selected');
	        	if (title=="Summary and Activity")    {		        	
	        		jQuery("#costSumryGrid").jqGrid().trigger("reloadGrid");	        		
	        	}
	        	else if (title=="Employee Cost")    {  
		        	 
		        	     	
	            	if(jQuery('#empCostId').html().length<= 4 )
	            		LoadForm("empCostId", "ecpreLoadContent","empCost_input.crt","dispErr");
	            	if (jQuery('#hdnGlbType').val() == "Actual"){	
	            		jQuery('#submitForm').val('frmEmpCostAct');}
	            	else{
	            		//jQuery('#submitForm').val('frmEmpCostEst');
	            		LoadForm("empCostId", "ecpreLoadContent","empCost_input.crt","dispErr");
	            	}
	            }		            
	            else if (title=="Contractor Cost"){		            		          
		            if(jQuery('#contractorCostId').html().length<= 4 )
	            		LoadForm("contractorCostId", "ccpreLoadContent","contractorCost_input.crt","dispErr");
		            
	            	if (jQuery('#hdnGlbType').val() == "Actual")	
	            		jQuery('#submitForm').val('frmContractorCostAct');
	            	else
	            		jQuery('#submitForm').val('frmContractorCostEst');
		            
		          }
	            else if (title=="Spare Cost"){		          		            
	            	if(jQuery('#spareCostId').html().length<= 4 )
	            		LoadForm("spareCostId", "scpreLoadContent","spareCost_input.crt","dispErr");
	            	
	            	if (jQuery('#hdnGlbType').val() == "Actual")	
	            		jQuery('#submitForm').val('frmSpareCostAct');
	            	else
	            		LoadForm("spareCostId", "scpreLoadContent","spareCost_input.crt","dispErr");
	            		//jQuery('#submitForm').val('frmSpareCostEst');	            	
		          }
	            else if (title=="Service Cost"){		          		            
	            	if(jQuery('#serviceCostId').html().length<= 4 )
	            		LoadForm("serviceCostId", "srpreLoadContent","serviceCost_input.crt","dispErr");
	            	
	            	if (jQuery('#hdnGlbType').val() == "Actual")	
	            		jQuery('#submitForm').val('frmServiceCostAct');
	            	else
	            		LoadForm("serviceCostId", "srpreLoadContent","serviceCost_input.crt","dispErr");
	            		//jQuery('#submitForm').val('frmServiceCostEst');	            	
		          }
	            else if (title=="Utilities Cost"){		          		            
	            	if(jQuery('#utilCostId').html().length<= 4 )
	            		LoadForm("utilCostId", "ucpreLoadContent","utilityCost_input.crt","dispErr");
	            	
	            	if (jQuery('#hdnGlbType').val() == "Actual")	
	            		jQuery('#submitForm').val('frmUtilityCostAct');
	            	else
	            		LoadForm("utilCostId", "ucpreLoadContent","utilityCost_input.crt","dispErr");
	            		//jQuery('#submitForm').val('frmUtilityCostEst');	            	
		          }
	            else if (title=="Other Cost"){		          		            
	            	if(jQuery('#otherCostId').html().length<= 4 )
	            		LoadForm("otherCostId", "ocpreLoadContent","otherCost_input.crt","dispErr");
	            	
	            	if (jQuery('#hdnGlbType').val() == "Actual")	
	            		jQuery('#submitForm').val('frmOtherCostAct');
	            	else
	            		LoadForm("otherCostId", "ocpreLoadContent","otherCost_input.crt","dispErr");
	            		//jQuery('#submitForm').val('frmOtherCostEst');
		          }
	        }  
	    });

	});

function fnFillRefName() {

	if (jQuery('#hdnDocType').val() == "BDM")
		jQuery('#txtRefName').val('BREAK DOWN');
	else if (jQuery('#hdnDocType').val() == "PMD")
		jQuery('#txtRefName').val('PLANNED MAINTENANCE');
	else if (jQuery('#hdnDocType').val() == "WOM")
		jQuery('#txtRefName').val('PLANNED MAINTENANCE');
	else if (jQuery('#hdnDocType').val() == "ABN")
		jQuery('#txtRefName').val('Abnormality');
	else if (jQuery('#hdnDocType').val() == "KZN")
		jQuery('#txtRefName').val('KAIZEN');
	else if (jQuery('#hdnDocType').val() == "CLI")
		jQuery('#txtRefName').val('CLEAN LUBE INSPECT');
	else if (jQuery('#hdnDocType').val() == "OPL")
		jQuery('#txtRefName').val('ONE POINT LESSON');
	else if (jQuery('#hdnDocType').val() == "MCA")
		jQuery('#txtRefName').val('MAINTENANCE LOG');
	else if (jQuery('#hdnDocType').val() == "ACP")
		jQuery('#txtRefName').val('ACTION PLAN');
	else if (jQuery('#hdnDocType').val() == "GEN")
		jQuery('#txtRefName').val('General Maintenance');
	else if (jQuery('#hdnDocType').val() == "MW")
		jQuery('#txtRefName').val('Maintenance Workorder');
	
	
}

function frmCostInfocmbspcnFactoryid_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnSectionid","sectionCombo.commonFilter" );
}
function frmCostInfocmbspcnSectionid_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnCellid","cellCombo.commonFilter" );
}
function frmCostInfocmbspcnCellid_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnMachineid","machineCombo.commonFilter" );
}						
function frmCostInfocmbspcnMachineid_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnAssemblyid","assembly.commonFilter");
}						
function frmCostInfocmbspcnAssemblyid_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnTrade","trade.commonFilter" );
}
function frmCostInfocmbspcnTrade_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbspcnRefdocno","combo_RefDocNo.crt");
}
function frmCostInfocmbspcnRefdocno_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbPhenomena","combo_phenomena.crt");
}
function frmCostInfocmbPhenomena_onLoadSuccess() 	{
	fillComboBox("frmCostInfo","cmbCause","combo_cause.crt");
	costGridLoad();
		
}
	

function costGridLoad()
{
	var fromWo = jQuery('#fromWo').val();
	 alert("fromWo: " + fromWo);
	var machineId = jQuery('#machineId').val();
	alert("machineId: " + machineId);
	var assmId = jQuery('#AssmId').val();
	alert("assmId: " + assmId);
	var tradeId = jQuery('#tradeId').val();
	alert("tradeId: " + tradeId);
	if(fromWo.trim().length>0){
		loadFunctionalLocation("spcnfunLocation","functionalLoc.crt","spcnfunLocationValues","frmCostInfo","&machId="+machineId);
		setFieldValue('cmbspcnAssemblyid',assmId);
		/* disableField('frmCostInfo','cmbspcnAssemblyid');
		disableField('frmCostInfo','cmbPhenomena');
		disableField('frmCostInfo','cmbCause');
		disableField('frmCostInfo','cmbspcnTrade');
		disableField('frmCostInfo','cmbspcnRefdocno'); */
		
		//mano
		 readOnlyFields('frmCostInfo','cmbspcnAssemblyid');
		 readOnlyFields('frmCostInfo','cmbPhenomena');
		 readOnlyFields('frmCostInfo','cmbCause');
		 readOnlyFields('frmCostInfo','cmbspcnTrade');
		 readOnlyFields('frmCostInfo','cmbspcnRefdocno');
		/* jQuery('#txaProblem').attr('disabled','disable');
		jQuery('#txaMeasure').attr('disabled','disable'); */
		jQuery('#txaProblem').attr('readOnly','readOnly');
		jQuery('#txaMeasure').attr('readOnly','readOnly');
		 
		setFieldValue('cmbspcnTrade',tradeId);
		 fillWithCurrentDate('dteplanDate');//filling Current Time
		 fillWithCurrentDate('dteworkStart');//filling Current Time
		 fillWithCurrentDate('spnworkStarttime');//filling Current Time
		 fillWithCurrentDate('dteworkEnd');//filling Current Time
		 fillWithCurrentDate('spnworkEndtime');//filling Current Time
	}
	if(fromWo.trim().length<=0)
	disableForm("frmCostInfo");
}

</script>
		
<div> <input type="hidden" id="hdnGlbType" value="${requestScope.formType}" ></div>
<div> <input type="hidden" id="hdnDocType" value="${requestScope.DocType}" ></div>
<div> <input type="hidden" id="hdnwoId" value="${requestScope.DocNo}" ></div>

<form id="frmCostInfo" name="frmCostInfo" >
<div id="wrapper" style="width:100%">
<div align="left" class="main-cntborder" style="display: block;border:none">

<table  class="tablealign-left" width=100%>
    <tr>   
        <td valign='top' style="width:33%;">
            <div style="float:left;">
                <div  class="easyui-paddingbfpx">
                    <label>Doc No</label>
                    <span  style="margin-left: 160px"><label>Actual Time</label></span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbspcnRefdocno" name="cmbspcnRefdocno" class="easyui-combobox" style="width:200px;" value="${requestScope.DocNo}"  >
                    <input id="txtActualTime" name="txtActualTime" type="text" class="easyui-text"  style="width:80px;te" value="${requestScope.DownTime}"  >
                </div>            
            </div>
        </td>
        <td>
        	<div class="easyui-paddingbfpx" style="margin-top: 15px;"><input type="text" id="txtRefName" name="txtRefName" " class="easyui-text noFocus"  readonly="readonly" value="" style="width:285px;height:30px;background-color:#D1E2FD;font-weight:bold;text-align:center; "/></div>
        </td>
    </tr>
        
	<tr><td colspan="2">
				<div  id="frmCostInfoFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbspcnFactoryid" value="${requestScope.FactoryId}"  ></input>
				<input type="hidden" id="section" name="cmbspcnSectionid" value="${requestScope.SectionId}"  ></input>
				<input type="hidden" id="cell" name="cmbspcnCellid" value="${requestScope.CellId}"  ></input>
				<input type="hidden" id="machine" name="cmbspcnMachineid" value="${requestScope.MachineId}"  ></input>
			    <input type="hidden" id="flid" name="cmbspcnFlid" value="${requestScope.Flid}"  ></input>
				</div>			
				<div id="spcnfunLocation" style="width:1000px" ></div>
	</td>
	</tr>    
</table>

	<div class="floatleft" id="costInformationTab" style="padding-left:5px;width : 1100px; ; height : 550px;margin: 0x;">
	<div id="tabcostInfo" class="easyui-tabs" fit="true" plain="true" style="width:100%;padding-left:0px;">	    
	    <div id="costSumTab" title="Summary and Activity" style="padding:0px;">
	        <table border="0" style="width:100%;">
	        <tr>
	        <td class="valigncnt" colspan="2">
	        <div class="sub-header">Work Summary</div>
	        </td>
	        <td>
	        	 <div class="sub-header" >Cost Summary</div>
	        </td>
	        </tr>
	            <tr>
	                <!--top left content -->
	                <td class="valigncnt" style="width:50%" >
	                    <div style="float:left;margin-left: 20px;">
	                         <div  class="easyui-paddingbfpx"><label>Assembly</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <input id="cmbspcnAssemblyid" name="cmbspcnAssemblyid" class="easyui-combobox"  style="width:285px;" value="${requestScope.AssemblyId}"  >                            
	                        </div>
	                        <div  class="easyui-paddingbfpx"><label>Phenomena</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <input id="cmbPhenomena" name="cmbPhenomena" class="easyui-combobox"  style="width:285px;" value="${requestScope.PhenomenaId}"  >                            
	                        </div>
	                        <div  class="easyui-paddingbfpx"><label>Cause</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <input id="cmbCause" name="cmbCause" class="easyui-combobox" style="width:285px;" value="${requestScope.CauseId}"  >                            
	                        </div>
	                        <div  class="easyui-paddingbfpx"><label>Trade</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <input id="cmbspcnTrade" name="cmbspcnTrade" class="easyui-combobox"  style="width:285px;" value="${requestScope.TradeId}"  >                            
	                        </div>
	                        <div  class="easyui-paddingbfpx"><label>Date</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <input id="dteplanDate" name="dteplanDate" class="easyui-datebox" style="width:190px;" value="${requestScope.EntryDate}" />                             
	                        </div>
							<div  class="easyui-paddingbfpx">
	                        	<label>Work Start</label>	                        	
	                        </div> 
	                        <div class="easyui-paddingbfpx">
								<span style="padding-right: 12px"><input id="dteworkStart" name="dteworkStart" class="easyui-datebox" value="${requestScope.StartDate}" style="width: 110px;" /></span>
								<span class="spinner"><input  id="spnworkStarttime" name="spnworkStarttime" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.StartTime}" min="00:00" showseconds="false" style="width: 60px;"></span>
	                        </div>                     
	                    </div>
	                </td>            
	                <td class="valigncnt" valign='top' style="width:30%;">
	                    <div  style="padding-left:0px;">
	                        <div  class="easyui-paddingbfpx"><label>Problem</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <textarea style="width: 250px;" rows="4" cols="20" id="txaProblem" name="txaProblem" > ${requestScope.Problem} </textarea>                            
	                        </div> 
	                        <div  class="easyui-paddingbfpx"><label>Counter measure</label></div> 
	                        <div class="easyui-paddingbfpx"> 
	                            <textarea style="width: 250px;" rows="4" cols="6" id="txaMeasure" name="txaMeasure" > ${requestScope.Measure} </textarea>                   
	                        </div>  
	                        </div> 
	                        <div  class="easyui-paddingbfpx">
	                        	<label>Work End</label>	                        	
	                        </div> 
                    		<div class="easyui-paddingbfpx">
								<span style="padding-right: 12px"><input id="dteworkEnd" name="dteworkEnd" class="easyui-datebox" value="${requestScope.EndDate}" style="width: 120px;" /></span>
								<span class="spinner"><input  id="spnworkEndtime" name="spnworkEndtime" class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.EndTime}" min="00:00" showseconds="false" style="width: 60px;"></span>	                          	                           
	                        </div>  	                        
	                 </td>
			        <td class="valigncnt" valign="top" style="width:30%;">
			        <div style="height:18px"></div> 
			        	<div style="padding-left:20px;">
			          		<div style="float: center;padding-right: 40px;margin-top:5px;">
			          		<table id="costSumryGrid" width="400px" style="float: center;"></table> </div>
							<div id="costSumryPager" style="float: center;"></div>
						</div>
			        </td>
	        </tr>
	        </table>  
	 	</div> 	
	<div id="empCostTab" title="Employee Cost" style="padding:0px;">
		<div id="empCostId" style="padding: 20px; width: 1050px; " title=""  > 
		</div>
		<div id ="ecpreLoadContent" class="tpm-loading"> </div>
	</div>
	
	<div id="contractorTab" title="Contractor Cost" style="padding:0px;">
		<div id="contractorCostId" style="padding: 20px; width: 1050px;" title=""  > 
		</div>
		<div id ="ccpreLoadContent" class="tpm-loading"> </div>
	</div>
	
	<div id="spareTab" title="Spare Cost" style="padding:0px;">
		<div id="spareCostId" style="padding: 20px; width: 1050px; " title=""  > 
		</div>
		<div id ="scpreLoadContent" class="tpm-loading"> </div>	
	</div>
	
	<div id="serviceTab" title="Service Cost" style="padding:0px;">
		<div id="serviceCostId" style="padding: 20px; width: 1050px;" title=""  > 
		</div>	
		<div id ="srpreLoadContent" class="tpm-loading"> </div>
	</div>
	
	<div id="utilTab" title="Utilities Cost" style="padding:0px;">
		<div id="utilCostId" style="padding: 20px; width: 1050px; " title=""  > 
		</div>
		<div id ="ucpreLoadContent" class="tpm-loading"> </div>	
	</div>
	
	<div id="otherTab" title="Other Cost" style="padding:0px;">
		<div id="otherCostId" style="padding: 20px; width: 1050px; " title=""  > 
		</div>
		<div id ="ocpreLoadContent" class="tpm-loading"> </div>	
	</div>
	</div>
</div>
</div>
</div>
<input type="hidden" id="fromWo" value="${requestScope.fromWorkOrder}"/>
<input type="hidden" id="machineId" value="${requestScope.mchid}"/>
<input type="hidden" id="AssmId" value="${requestScope.assmId}"/>
<input type="hidden" id="tradeId" value="${requestScope.tradeId}"/>

</form>
 