<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript">

jQuery(document).ready(function(){
	 
	if(screen.width <= 1024){
		 
		 jQuery('#Filter').css('height','418px');
	 }	
var filterString ="?q=2&data=1";
	//processGridnew("fillJobtype_input.prv",filterString,"tblJobType","","","doubleClickGrid","","Load_Complete","frmPMRelated");
	processGridnew("fillJobtype_input.prv",filterString,"tblJobType","","","doubleClickGrid","","Load_Complete");
 
fillComboBox("frmPMRelated","cmbJhStep","Combo_JhStep.eqp");	
numericTextBox('txtnoofdays');
fillComboBox("frmPMRelated","cmbprodcngroup","productiongrpp.commonFilter" );
fillComboBox("frmPMRelated","cmbActivity","activity.commonFilter");
fillComboBox("frmPMRelated","cmbsupplier","supplier.commonFilter");
//fillComboBox("frmPMRelated","cmbsupplier","supplier.commonFilter");
//var url = jQuery('#hiddenUrl').val();
//if(url=="monplanconf_input.mpc")
	//disableField();
});
function disableField()
{
	setTimeout(function(){
		jQuery('#cmbActivity').combobox('disable');
		jQuery('#cmbsupplier').combobox('disable');
		jQuery('#cmbprodcngroup').combobox('disable');
		jQuery('#cmbJhStep').combobox('disable');
		},200);
}
function tblJobType_selectRow(id){
		if(jQuery('#jqg_tblJobType_'+id).is(':checked'))
			chkboxCheck(id);
		else
			chkboxUnCheck(id);
	}
	
function tblJobType_selectAll(id,status){
		for(var i=0; i<id.length; i++){
			if(status)
				chkboxCheck(id[i]);
			else
				chkboxUnCheck(id[i]);
		}
	}
	function chkboxCheck(rowId)
 {
 	jQuery("#tblJobType").jqGrid('setCell',rowId,'selectval','1');		
 }
 function chkboxUnCheck(rowId){
	jQuery("#tblJobType").jqGrid('setCell',rowId,'selectval','0');	
 }
 function convertJsonArr(gridId){
		var allrow = jQuery("#"+gridId).jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1;
			if (jQuery('#jqg_tblJobType_' + rowno).is(':checked')) {
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						if( colName == 'code')
							{
								jsonArrO +=  row[colName] + ',';
							} 
					} 
				}
			}
		}
		return jsonArrO;
	}
 function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
 { 
	
 	var allRows = jQuery("#tblJobType").jqGrid('getRowData');
 	var cm = jQuery("#tblJobType").jqGrid("getGridParam", "colModel"); 	
 	
 	var jsonArrO = '';
 	for( var i = 0; i < allRows.length;i++)
 	{ 		
 			var row = allRows[i]; 
 			var value = row[ckeckForSelColName];
 			if( value != null  &&  value.trim()  != "")
 			{	
 	 			if(value == '1')				
 				{
 	 			//jsonArrO += '{';
 					for(var colName in row) 
 					{
 	 					if(colName == 'code'  )
 						{	 	 	
 	 						var cellValue = parseJqGridCellValue(row[colName]);
								jsonArrO += cellValue+",";
						}		
 					} 					
 					//jsonArrO += "},"; 						
 				}
 			}  		
 	} 	
 	jsonArrO = jsonArrO.slice(0, -1) + ",";
 	jsonArrO = (jsonArrO != ','?jsonArrO:"");
 	return jsonArrO; 
 } 
 
	function getRelatedFilterValues()
	{
	
		var filterStr='';
		
		/*var cmbPmjobtype = jQuery("#cmbPmjobtype").val();
		filterStr += "&cmbjobtype="+cmbPmjobtype;
		*/
		var cmbMachCond = jQuery("#cmbMachCond").val();
		filterStr += "&cmbMachCond="+cmbMachCond;
		var cmbActivity = jQuery("#cmbActivity").combobox("getValue");
		filterStr += "&cmbactivities="+cmbActivity;
		var cmbsupplier = jQuery("#cmbsupplier").combobox("getValue");
		filterStr += "&cmbsupplier="+cmbsupplier;
		/*
		var cmbtools = jQuery("#cmbtools").combobox("getValue");
		filterStr += "&cmbtools="+cmbtools;*/
	
		var cmbSources =jQuery("#cmbSources").val();
		filterStr += "&cmbSource="+cmbSources;

		var cmbfreq = jQuery("#cmbFreq").val();
		filterStr += "&cmbfreq="+cmbfreq;		
		
		var cmbeqpCondn = jQuery("#cmbEqpCondn").val();
		filterStr += "&cmbeqpCondn="+cmbeqpCondn;
		
		
		filterStr += "&chkincludestatuschkbox="+getChkBoxVal('chkincludestatus');
		
		
		var cmbpmstatus = jQuery("#cmbPmstatus").val();
		filterStr += "&cmbpmstatus="+cmbpmstatus;
		
		var cmbdrillfor =  jQuery("#cmbdrillfor").val();
		filterStr += "&cmbdrillfor="+cmbdrillfor;

		filterStr += "&chkremblankchkbox="+getChkBoxVal('chkRemblank');
		filterStr += "&chkcompchkbox="+getChkBoxVal('chkComp');
		filterStr += "&chkpendchkbox="+getChkBoxVal('chkPend');
		filterStr += "&chkempchkbox="+getChkBoxVal('chkEmp');
		var chkRemoveBlank = getChkBoxVal('chkRemoveBlank');
		filterStr += "&chkRemoveBlank="+(chkRemoveBlank=="1" || chkRemoveBlank==1? 'Y':'N');
		
		filterStr += "&chkcontractorchkbox="+getChkBoxVal('chkContractor');
		filterStr += "&chksparechkbox="+getChkBoxVal('chkSpare');
		filterStr += "&chkservicechkbox="+getChkBoxVal('chkService');
		filterStr += "&chkutilchkbox="+getChkBoxVal('chkUtil');
		
		
		filterStr += "&chkotherchkbox="+getChkBoxVal('chkOther');
		filterStr += "&chktotalchkbox="+getChkBoxVal('chkTotal');
		filterStr += "&chkapchkbox="+getChkBoxVal('chkAp');		
		filterStr += "&chktwchkbox="+getChkBoxVal('chkTw');
		filterStr += "&chkjtwchkbox="+getChkBoxVal('chkJtw');	
		
		
		 var cmbpmjobtypeGrdData = convertJsonArr("tblJobType");// getSelectdRows("tblJobType",'','selectval');
		 filterStr += "&cmbjobtype="+escape(cmbpmjobtypeGrdData.slice(0,-1)); 

		var cmbparam = jQuery("#cmbParam").val();
		filterStr += "&cmbparam="+cmbparam;
		
		filterStr += "&chkactwisechkbox="+getChkBoxVal('chkActwise');		
		filterStr += "&chkmonwisechkbox="+getChkBoxVal('chkMonwise');
		filterStr += "&chkawisechkbox="+getChkBoxVal('chkAwise');
		filterStr += "&chkatypechkbox="+getChkBoxVal('chkAtype');
		filterStr += "&chkmwisechkbox="+getChkBoxVal('chkMwise');		
		filterStr += "&chksummarychkbox="+getChkBoxVal('chkSummary');		
		filterStr += "&chksectchkbox="+getChkBoxVal('chkSect');		
		filterStr += "&chkcellchkbox="+getChkBoxVal('chkCell');		
		//filterStr += "&chkeqrmntchkbox="+getChkBoxVal('chkEqpmnt');		
		filterStr += "&chkMachineWise="+getChkBoxVal('chkMachineWise');
		filterStr += "&chkEquipmentWise="+getChkBoxVal('chkEquipmentWise');
		
		//filterStr += "&chkMonth="+getChkBoxVal('chkMonth');
		//filterStr += "&chkWeek="+getChkBoxVal('chkWeek');
		var chkMonth = getChkBoxVal("chkMonth");
		var chkWeek = getChkBoxVal("chkWeek");
		filterStr += "&chkMonth="+ (chkMonth == "1" || chkMonth == 1 ? 'Y':'N');
		filterStr += "&chkWeek="+ (chkWeek == "1" || chkWeek == 1 ? 'Y':'N');
		
		var cmbprodcngroup = jQuery("#cmbprodcngroup").combobox("getValue");
		filterStr += "&cmbprodcngroup="+cmbprodcngroup;
		
		var cmbJhStep = jQuery("#cmbJhStep").combobox("getValue");
		filterStr += "&cmbJhStep="+cmbJhStep;
		
		var txtDuration = jQuery("#txtnoofdays").val();
		filterStr += "&txtDuration="+txtDuration;
		
		return filterStr;
	}
	
	
	function getChkBoxVal(Id) {		
		if(jQuery('#'+Id).is(':checked') == true)  		
			return 1;
		else
			return 0;
	}
	</script>
	<form id="frmPMRelated" name="frmFilter">
						
	<!--   PM Related Tab	-->
						<div title="PM Related" style="padding:10px;">
								<div class="sub-header">Regular Filter</div>
								<div align="CENTER">
								<table><tr><td>										                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>Activities</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbActivity" name="cmbActivity" class="easyui-combobox"  style="width:250px" value=""  >                       
			                   </div>
			                   
			                   
			                 <!-- <div  class="easyui-paddingbfpx">
                        			<label>ProductionId</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbProduction" name="cmbProduction" class="easyui-combobox"  style="width:250px" value=""  >                       
			                   </div>
			                    -->  
			                    <div  class="easyui-paddingbfpx">
								<label>Production Group</label>                       
								</div> 
								<div class="easyui-paddingbfpx"> 
									<input id="cmbprodcngroup" name="cmbprodcngroup" class="easyui-combobox" clear="false"  style="width:250px" value=""  >                       
								</div>
			                   </td>
			                   <td style="padding-left:20px;">
			                    <div  class="easyui-paddingbfpx">
                        			<label>Supplier</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbsupplier" name="cmbsupplier" class="easyui-combobox"  style="width:250px" value=""  >                       
			                   </div>
			                   
			                   
			                   
			                   <div  class="easyui-paddingbfpx">
                        			<label>JH Step</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx"> 
			                        <input id="cmbJhStep" name="cmbJhStep" class="easyui-combobox"  style="width:250px" value=""  >                       
			                   </div>
			                    <div  class="easyui-paddingbfpx" style="display: none;">
                        			 <label>Tools</label>                       
                    		   </div> 
			                    <div class="easyui-paddingbfpx" style="display: none;"> 
			                         <input id="cmbtools" name="cmbtools" class="easyui-combobox"  style="width:250px" value=""  >                        
			                   </div>
			                   </td>
			                   <td style="padding-left:23px;padding-top:10px;">
			                   		<div>
			                   		<table id="tblJobType">
			                   		
			                   		</table>
			                   		</div>
			                   		
			                   </td>
			                   </tr></table></div>
			                   <div class="sub-header">Advanced Filter Criteria</div>
			                   	<div align="center">
								<table><tr><td>
			                    <div  class="easyui-paddingbfpx">
                  					 <label>Sources</label>
                   					 <span  style="margin-left: 137px;"><label>Frequency</label></span>
                    			</div> 
			                    <div class="easyui-paddingbtpx"> 
			                         <select id="cmbSources" name="cmbSources" style="width:160px;" >
			                        			 <option value=""> Both</option>
												<option value="I"> Internal</option>
												<option value="E"> External</option>
												
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cmbFreq" name="cmbFreq" style="width:160px;" >
													<option value="">All</option>
													<option value="A"> Above Monthly</option>
													<option value="B"> Monthly and Below</option>													
									   </select> 
			                        </span> 
			                    </div>
			                    
			                     <div  class="easyui-paddingbfpx">
                        			<input id="txtnoofdays" type="text" name="txtnoofdays" class="easyui-text" maxlength="3" style="width: 50px" value=""/> <label>Modified Records No. of Days</label>
                        			<span><input id="chkRemoveBlank" name="chkRemoveBlank" type="checkbox" /><label>Remove Blanks</label></span>                     
                    		   </div> 
                    		   
                    		   <div  class="easyui-paddingbfpx">                  					 
				                       <select id="cmbEqpCondn" name="cmbEqpCondn" style="width:160px;" >
													<option value="RUNNING"> Running</option>
													<option value="SHUT"> Shutdown</option>
													<option value="BOTH">Both</option>
									   </select> 			                      
			                    </div>
			                    
			                    <div  class="easyui-paddingbfpx">
                        			<input id="chkincludestatus" type="checkbox"/> <label>Include Status</label> 
                        			
                        			 <span  style="margin-left: 25px;"> 
				                        <input id="chkMonth" type="checkbox"/> <label>Monthly</label>
				                         <input id="chkWeek" type="checkbox"/> <label>Weekly</label>
			                        </span>               
                    		   </div> 
                    		   
                    		     <div  class="easyui-paddingbfpx">
                  					 <label>Status</label>
                   					 <span  style="margin-left: 144px;"><label>Drill For</label></span>
                    			</div> 
                    		     <div class="easyui-paddingbtpx"> 
			                         <select id="cmbPmstatus" name="cmbPmstatus" style="width:160px;" >
												<option value=""> All</option>
												<option value="X"> Pending</option>
												<option value="Y"> Completed</option>
												<option value="A"> Allotted</option>
									 </select> 
			                        <span  style="margin-left: 25px;"> 
				                       <select id="cmbdrillfor" name="cmbdrillfor" style="width:160px;" >
													<option value="M"> Monthly</option>
													<option value="W"> Weekly</option>
									   </select> 
			                        </span> 
			                    </div>
			                    
			                   
                    		   
                    		    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="chkComp" type="checkbox"/> <label>%Completed</label>
                  					  <span  style="margin-left: 2px;"> 
                  					   <input id="chkPend" type="checkbox"/> <label>%Pending</label></span>
							   </div> 
							  
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="chkEmp" type="checkbox"/> <label>Employee</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkContractor" type="checkbox"/> <label>Contractor</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkSpare" type="checkbox"/> <label>Spare</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkService" type="checkbox"/> <label>Service</label></span><br/>
                  					  <span>  <input id="chkUtil" type="checkbox"/> <label>Utility</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkOther" type="checkbox"/> <label>Other</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkTotal" type="checkbox"/> <label>Total</label></span>
                  					  <span  style="margin-left: 2px;">  <input id="chkAp" type="checkbox"/> <label>All Parameter</label></span>
							   </div> 
							    </td>
							   <td style="padding-left:20px;">
							    <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="chkTw" type="checkbox"/> <label>Trade Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkJtw" type="checkbox"/> <label>Job Type Wise</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx" >
                  					 <!-- <label >Job Type</label> -->
                  					 <span style="margin-left: 1px;">  <label>Machine Condition</label> </span>
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                       <!--   <select id="cmbPmjobtype" name="cmbPmjobtype" style="width:160px; " >
												<option value=""> All</option>
												<option value="CBM"> Condition Based Maintenance</option>
												<option value="TBM"> Time Based Maintenance</option>
												<option value="PRM"> Preventive Maintenance</option>
												<option value="MBM"> Meter Based Maintenance</option>
												<option value="RBM"> Run Based Maintenance</option>
												<option value="CAL"> Calibration</option>
												<option value="SDM"> Shutdown Maintenance</option>
												
									 </select>  -->
									 <span>
									 	<select id="cmbMachCond" name="cmbMachCond" style="width:160px;" >
									 		<option value=""> All</option>
									 		<option value="ONLINE">On Line</option>
									 		<option value="OFFLINE">Off Line</option>
									 	</select>
									 </span>
							  </div>
							  
							  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px; display:none;">
                  					  <input id="chkActwise" type="checkbox"/> <label>Activity Wise</label>
                  					  <span  style="margin-left: 2px;">  <input id="chkMonwise" type="checkbox"/> <label>Month Wise</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                  					 <label>Parameter1</label>                  					 
							   </div> 
                    		   <div class="easyui-paddingbfpx"> 
			                         <select id="cmbParam" name="cmbParam" style="width:160px;" >
												<option value="BDN"> Breakdown</option>
												<option value="PMT"> PM Time</option>
												<option value="K"> Kaizen</option>
												<option value="CLIT"> CLIT Time</option>
												<option value="ABN">Abnormality</option>
									 </select> 
									 <span>
									 	<input id="chkMachineWise" type="checkbox"/> <label>Machine Wise</label>
									 	<input id="chkEquipmentWise" type="checkbox"/> <label>WorkOrder Wise</label>
									 </span>
							  </div>
							  
							  <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;width:365px\9;">
                  					  <input id="chkAwise" onclick="chekActi_deselect(this.id)" type="checkbox"/> <label>Activity Wise</label>
                  					  <span style="margin-left: 2px;">  <input id="chkAtype" onclick="chekActi_deselect(this.id)" type="checkbox"/> <label>Activity Type</label></span>
                  					  <span style="margin-left: 2px;">  <input id="chkMwise" onclick="chekActi_deselect(this.id)" type="checkbox"/> <label>Month Wise</label></span>
                  					  <span>  <input id="chkSummary" onclick="chekActi_deselect(this.id)" type="checkbox"/> <label>Summary</label></span>
							   </div> 
							   
							   <div  class="easyui-paddingbfpx">
                  					 <label>Display in Column</label>
							   </div> 
							     <div  class="easyui-paddingbtpx easyui-chkbxgroup" style="width:350px;">
                  					  <input id="chkSect" type="checkbox"/> <label>Section</label>
                  					   <span  style="margin-left: 2px;">  <input id="chkCell" type="checkbox"/> <label>Cell</label></span>
                  					 <!--  <span  style="margin-left: 2px;">  <input id="chkEqpmnt" type="checkbox"/> <label>Equipment</label></span>--> 
							  </div>
			                  </td></tr></table></div> 
						</div>
</form>