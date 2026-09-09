<script type="text/javascript">
 jQuery(document).ready(function(){
	 //alert("EnergyConsumptionFrm");
	 initialiseForm('frmEnergyCons');

	 fillComboBox("frmEnergyCons","cmbAbnmEquipmentid1","machineCombo.commonFilter");
	 jQuery('#frmEnergyCons .easyui-text').css('text-transform', 'uppercase');
	 jQuery('#frmEnergyCons textarea').css('text-transform', 'uppercase');
	 jQuery(".txtarea").attr('maxlength','499');		
	 formatDateBox('dteEncmDate','dd-MMM-yyyy');	 
	 
	 var factId = jQuery("#frmEnergyCons input[id='factory']").val();
	 var sectionId = jQuery("#frmEnergyCons input[id='section']").val();
	 var cellId = jQuery("#frmEnergyCons input[id='cell']").val();
	 var machId = jQuery("#frmEnergyCons input[id='machine']").val();
	 var flid = jQuery("#frmEnergyCons input[id='flid']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;	
	
	 loadFunctionalLocation("energyfunLocation","functionalLoc.energy","energyfunLocationValues","frmEnergyCons",dataStr);

	 jQuery('#submitForm').val('frmEnergyCons'); 

	 var compId = getFieldValue('company','frmEnergyCons');
	 var locnId = getFieldValue('location','frmEnergyCons');
	 var factId = getFieldValue('factory','frmEnergyCons');
	 var sectId = getFieldValue('section','frmEnergyCons');
	 var cellId = getFieldValue('cell','frmEnergyCons');
     var machId = getFieldValue('machine','frmEnergyCons');

	 var actionPart = jQuery('#hiddenUrl').val();		
	 viewGrid(actionPart,"?q=1&firstClick=Y");
 });


    //below code no problem
    
    function viewGrid(url,filterString)
 {
 	if( validateFilterSelection(filterString))
 	{
 	
 		filterString +='&drillFlag=f';
 		filterString += '&firstClick=Y';
 		
 		var tableCaption = "Energy Consumption";
 		processGridnew(url,filterString,"EnergyConsp","pager",tableCaption,"EnergyCons_doubleClickGrid","","load_Complete");
 	
 		return true;
 	}
 	return false;	
 }
 function load_Complete(id)
 {
	var rowId = jQuery("#EnergyConsp").jqGrid('getDataIDs');	
	var cm = jQuery("#EnergyConsp").jqGrid("getGridParam", "colModel");
	
	for(i=1;i<=rowId.length;i++)	
	{	
		for(j=1;j<=cm.length-1;j++)	
		{		
			var controlId="exceptPwr_"+(j-1);			
			var actualId = "actualPwr_"+(j-1);
			
			numericTextBox(controlId);
			numericTextBox(actualId);	
		}
	}
}
 function validateFilterSelection(filterString){ 
	 
	 if(filterString == "?q=1&firstClick=Y")
		 return true;
	 
	 else if(getFilterValue(filterString, "dtFromDate") == "" ){
			alert("Select Date");
			return false;
			}
	 else if(getFilterValue(filterString, "cmbSectid") == "" ){
			alert("Select Section");
			return false;
			}
	 else
 		return  true;
 }
 jQuery("#btnView").click(function(){
	 var date = jQuery('#dteEncmDate').datebox('getValue');	
	 var url = jQuery('#hiddenUrl').val();		


	/* var compId = getFieldValue('company','frmEnergyCons');
		var locnId = getFieldValue('location','frmEnergyCons');
		var factId = getFieldValue('factory','frmEnergyCons');
		var sectId = getFieldValue('section','frmEnergyCons');
		var cellId = getFieldValue('cell','frmEnergyCons');
		var machId = getFieldValue('machine','frmEnergyCons');*/
	 
		 var factId = jQuery("#frmEnergyCons input[id='factory']").val();
		 var sectId = jQuery("#frmEnergyCons input[id='section']").val();
		 var cellId = jQuery("#frmEnergyCons input[id='cell']").val();
		 var machId = jQuery("#frmEnergyCons input[id='machine']").val();
	
	 filterString = "dtFromDate="+date;
	 filterString +="&cmbFactid="+factId+"&cmbSectid="+sectId+"&cmbCellid="+cellId+"&cmbMchid="+machId;
	 
	 viewGrid(url,filterString);

 });



 function  frmEnergyConscmbAbnmEquipmentid1_onSelect(record){
		 
	 loadFunctionalLocation("energyfunLocation","functionalLoc.energy","energyfunLocationValues","frmEnergyCons","&machId="+record.id);
	}
	
 function Formatter_chkBox(id, options, rowObject)
 {	
 	var rowId = options.rowId;	
 	return '<input id="EnergyConsp_checkbox" name="EnergyConsp_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}" />';
 }
 function chkboxCheck(rowId)
 {
 	jQuery("#EnergyConsp").jqGrid('setCell',rowId,'chkValue','1');		
 }
 function chkboxUnCheck(rowId){
 	jQuery("#EnergyConsp").jqGrid('setCell',rowId,'chkValue','0');	
 }
function Formatter_ExpPwr_cellEdit(id, options, rowObject)
{	
	var rowId = options.rowId;	
 	return '<input type="text" style="text-align:right;width:150px;"  maxlength="7" id=exceptPwr_'+rowId +' value='+id +' >'; 	
}
function Formatter_ActPwr_cellEdit(id, options, rowObject)
{	
	var rowId = options.rowId;	
 	return '<input type="text" style="text-align: right;width:150px;"  maxlength="7" id=actualPwr_'+rowId +' value='+id +' >'; 	
}

 function frmEnergyCons_beforeSubmit()
 {	
	var gridData = "&gridData="+getSelectdRows("EnergyConsp","Select","chkValue");	
	gridData += "&date="+jQuery('#dteEncmDate').datebox('getValue');
	
	var chkVal = jQuery("#chkboxValue").val();
	if(chkVal == "1")
		return gridData;
	else{
		alert("Select a Row For Save!");
		return false;
	}
	
}

 function frmEnergyCons_beforeDelete()
 {	
	var gridData = "&gridData="+getSelectdRows("EnergyConsp","Select","chkValue");	
	gridData += "&date="+jQuery('#dteEncmDate').datebox('getValue');	
	
	return gridData;
}

 function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName)
 { 	
	
 	var allRows = jQuery("#EnergyConsp").jqGrid('getRowData');
 	var cm = jQuery("#EnergyConsp").jqGrid("getGridParam", "colModel"); 	
 	var jsonArrO='[';
 	for( var i = 0; i < allRows.length;i++)
 	{ 		
 			var row = allRows[i]; 	 			
 			var value = row[ckeckForSelColName]; 
 			 				
 			if( value != null  &&  value.trim()  != "")
 			{			
 				if(value == '1')				
 				{
 	 				jQuery("#chkboxValue").val(value);
 	 				
 					jsonArrO += '{';
 					for(var colName in row) 
 					{ 
 						if(colName != 'Select' && colName != 'chkValue')
 						{
 	 						
 							var cellValue = parseJqGridCellValue(row[colName]);
 							
 							//jsonArrO += '"'+colName +'":"' + cellValue+'",'; 	
 							if(colName.trim()=="MachineId")						
 								jsonArrO += "txtEncdMachineid :" + cellValue+",";
 							else if(colName.trim()=="PRODUCTID")				
 								jsonArrO += "txtEncdProductid :" + cellValue+","; 							
 							else if(colName.trim()=="Standardweightpercomponent(Gms)")				
 								jsonArrO += "txtEncdStandardwt :" + cellValue+",";	
 							else if(colName.trim()=="Exp.PwrConsumption/day")				
 							{		
 								if(cellValue.trim()!="/" && cellValue.trim().length>0)
 								{ 
 	 								cellValue=jQuery('#exceptPwr_'+(i+1)).val();
 	 								if(!isNaN(cellValue))
	 						        {
	 							       jsonArrO += "txtEncdExppowercons :" + cellValue+",";	
	 						        }	
	 								else			
	 									alert("Enter Numeric value");
 								}	 									
 							}
 							else if(colName.trim()=="Act.PwrConsumption/day")	
 							{
 								if(cellValue.trim()!="/" && cellValue.trim().length>0)
 								{
 									cellValue=jQuery('#actualPwr_'+(i+1)).val();
 	 								if(!isNaN(cellValue))
	 						        {	 							        
 	 									jsonArrO += "txtEncdActpowercons :" + cellValue+",";
	 						        }	
	 								else			
	 									alert("Enter Numeric value");
 								} 									
 							}
 							else if(colName.trim()=="ExpectedProductionperday(Nos)")				
 								jsonArrO += "txtEncdExpproduction :" + cellValue+",";		
 							else if(colName.trim()=="NoofpiecesrejectedincludingQC(Nos)")	
 							{ 	 							
 								if(!isNaN(cellValue) && cellValue.trim() != "") 						      
 									jsonArrO += "txtEncdNoofpiecesrejected :" + cellValue+",";	 						      
 								else 																
 									jsonArrO += "txtEncdNoofpiecesrejected :" + 0+","; 									
 							}			
 								
 							else if(colName.trim()=="EnergyConspId")
 							{
 								if(cellValue.trim().length>0)
									jsonArrO += "txtEncdEncmKeyid :" + cellValue+",";								
 							}
 							else if(colName.trim()=="EnergyDtlId")	
 							{
 								if(cellValue.trim().length>0)
 									jsonArrO += "txtEncdKeyid :" + cellValue+",";								
 							}			
 																					
 						}		
 					} 					
 					jsonArrO += "},"; 						
 				}
 			}  		
 	} 	
 	jsonArrO = jsonArrO.slice(0, -1) + "]";
 	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
 	
 	return jsonArrO; 
 } 
	 function frmEnergyCons_successsCallback(result)
	{
		 //alert(Object.keys(result));
		 jQuery("#chkboxValue").val("0");
		 jQuery("#EnergyConsp").trigger("reloadGrid");
		 
	}
	function dteEncmDate_onSelect(date)
	{
		clearValidationErrorMessages("frmEnergyCons","err_dteEncmDate");
	}
    
  	
   </script> 
  <form name="frmEnergyCons" id="frmEnergyCons" action="" method="post">
  <div id="wrapperRpt" style="width:100%">


  <div id="frmEnergyConsFuntKeyIds"  >
	<div>
		<input type="hidden" id="factory" name="cmbJhkzFactoryid" value="${requestScope.abnormalityBean.factory}"  ></input>
		<input type="hidden" id="section" name="cmbJhkzSectionid" value="${requestScope.abnTlAbnormality.abnmSectionid}"></input>
		<input type="hidden" id="cell" name="cmbJhkzCellid" value="${requestScope.abnTlAbnormality.abnmCellid}"  ></input>
		<input type="hidden" id="machine" name="cmbJhkzMachineid" value="${requestScope.abnTlAbnormality.abnmEquipmentid}"></input>
		<input type="hidden" id="flid" name="cmbJhkzFlid" value="${requestScope.abnTlAbnormality.abnmFlid}"  ></input>
		
		<div class="" style="padding-right: 395px;width:320px;">
		<div id="energyfunLocation" style="padding-left: 10px; width: 950px; ">			
		</div>			
<!--		<div id="txtFct" class="tpm-errormsg" style="padding-left:30px;"/></div>-->
<!--		<div id="err_err_cell" class="tpm-errormsg" style="padding-left:30px;"/></div>		-->
	</div>
	<div style="padding-left:10px;">
		<div>
		<span ><label>Equipment</label></span>
		<span style="padding-left:195px;"><label class="mandatory-lbl">Date</label></span></div>
		<div>
		<input id="cmbAbnmEquipmentid1" name="cmbAbnmEquipmentid1" class="easyui-combobox"  style="width:255px;" value=""></input>
		<input id="dteEncmDate"  name="dteEncmDate" clear="false" class="easyui-datebox" value=""  style="width: 125px;" /></input>
		<input type="button" name="btnView" id ="btnView" value="View" class="easyui-button"/></input>
		
		<span id="err_dteEncmDate" class="tpm-errormsg"></span>		
		</div>
	</div>
	<div class="clear"></div>	
	</div>
		

</div>
<div style="padding-left:10px;">
<table id="EnergyConsp"></table>
<div id = "pager"></div>
</div>
<input type="hidden" id="mode"  /></input>
<input type="hidden" id="chkboxValue" name="chkboxValue" value="0" /></input>

</div>
</form>
