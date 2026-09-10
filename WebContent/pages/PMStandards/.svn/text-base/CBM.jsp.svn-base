<script type="text/javascript">	
	
jQuery(document).ready(function(){	
 		initialiseForm('frmCBM');
 		//alert(jQuery('#hiddenUrl').val());
 		var pmstdId = jQuery('#hdnPmstdId').val();
 		
 		var filterString = "?q=2";   
 			if(pmstdId.trim().length>0)
 				filterString ="&pmstdId="+pmstdId ;	
 		processGridnew('cbm_input.prv',filterString ,"cbmGrid","","","cbmDBLClick","","cbmLoadComplete"); 		
 		//processGridnew(grdUrl,fltrStr,"assmGrid","pager_assm","","pmAssembly_dblclick","","loadComFunction");
 		jQuery('#submitForm').val('frmCBM'); 		
 		jQuery('#frmCBM .easyui-text').css('text-transform', 'uppercase');
 	    jQuery('#frmCBM textarea').css('text-transform', 'uppercase');	
 	    jQuery('#frmCBM .easyui-datebox').css('text-transform', 'lowercase');	
 	    var mchId = jQuery("#cmbPmsdMachineid").combobox("getValue");
 		fillComboBox("frmCBM","cmbcmAssemblyid","assembly.commonFilter?machineId="+mchId);
 		var assmId =   getFieldValue('cmbcmAssemblyid',"frmCBM");
 		
 		//fillComboBox("frmCBM","cmbcmSubassemblyid","Pmsd_SubassemblyId.prv");
 		//Sub Assembly changed by Team
 		//fillComboBox("frmCBM","cmbcmSubassemblyid","combo_subassmbly.brdn?assmId="+assmId+"&machineId="+mchId);
 		fillComboBox("frmCBM","cmbcmSubassemblyid","Pmsd_SubassemblyId.prv?assmId="+assmId+"&machineId="+mchId);
 		fillComboBox("frmCBM","cmbCmdtInspectionid","inspectionCombo.prv");
 		fillComboBox("frmCBM","cmbCmdtUomid","uomCombo.commonFilter");
 		numericTextBox('txtCmdtLowerlimit');
 		numericTextBox('txtCmdtUpperlimit');
/*For disabling Controls*/
 		numericTextBox('txtCmdtDesirablereading');
 		disableField("frmCBM",'txtCmdtLowerlimit');
 		disableField("frmCBM",'txtCmdtUpperlimit');
 		disableField("frmCBM",'txtCmdtDesirablereading');
 		jQuery("#btnCbmInsert").attr("disabled", true);
		jQuery("#btnCbmInsert").removeClass("easyui-button");
		jQuery("#btnCbmInsert").addClass("disabledButton");
 		//disableField("frmCBM",'txtCmdtCorrectiveaction');
 		//disableField("frmCBM",'txtCmdtMeasuringmethod');
 		jQuery("#txtCmdtCorrectiveaction").attr('disabled','disabled');
 		jQuery("#txtCmdtMeasuringmethod").attr('disabled','disabled');
 		jQuery('#btndefineZone').click(function(){
 			var htmlDiv ="<div > <table border='1' rules='all'> <tr>";
 			htmlDiv +="<th style='padding:5px;border:solid 1px #c1c1c1;background-color:lightskyblue; ' ><label>Zone</label></th>";
 				htmlDiv +="<th style='padding:5px;border:solid 1px #c1c1c1;background-color:lightskyblue; ' ><label>Zone Name</label></th></tr><tr>";
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >";
				htmlDiv +='<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #94E031;cursor:default;" value="" maxlength="0" />';
				htmlDiv +='</td>';
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >";
				htmlDiv +='<input id="txtbtsno" name="txtbtsno" class="easyui-text"  style="width:180px; height : 20px; " value="GREEN"  />';
				htmlDiv +="</td></tr><tr>";
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >"; 
				htmlDiv +='<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #EDED6F;cursor:default;" value="" maxlength="0" />';
				htmlDiv +="</td>";
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >";
				htmlDiv +='<input id="txtbtsno" name="txtbtsno" class="easyui-text"   style="width:180px; height : 20px; " value="YELLOW"  />';
				htmlDiv +="</td></tr><tr>";
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >";
				htmlDiv +='<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #E52222;cursor:default;" value="" maxlength="0" />';
				htmlDiv +="</td>";
				htmlDiv +="<td style='padding:5px;border:solid 1px #c1c1c1; ' >";
				htmlDiv +='<input id="txtbtsno" name="txtbtsno" class="easyui-text"   style="width:180px; height : 20px;  " value="RED"  />';
				htmlDiv +="</td></tr></table><div align='center' style='width:100%'><input type='button' class='easyui-button' value='Ok'/></div></div>";
 			LoadPopUp("divDefineZone","keyId=", true,"24%","208px","100px","58%", "zoneOk_Callback","Define Zone" );
       	 jQuery('#loadPopUpdivDefineZone').append(htmlDiv);
 		});
/***CLICKING BUTTON OK**/
 		jQuery('#btnCbmOk').click(function(){
 			var Uomval =  jQuery("#cmbCmdtUomid").combobox("getValue");//getFieldValue('cmbCmdtUomid',"frmCBM");
 			
 	 		var inspectionid =jQuery("#cmbCmdtInspectionid").combobox("getValue");//getFieldValue('cmbCmdtInspectionid',"frmCBM");
 	 	
 	 		if(inspectionid.trim() == '' && inspectionid.length<=0){
				alert('Select Inspection');
				return false;
 	 	 	} 
 	 		else if(Uomval.trim() == '' && Uomval.length<=0){
				alert('Select UoM');
				return false;
 	 	 	} 
 	 		var griddata = convertJqGridToJSONObjectArr_zone('cbmGrid');
			var hdnID = jQuery('#saveval').val();
			//alert(hdnID);
		//	alert(griddata);
	 
		//	alert(jQuery('#'+hdnID).val());	 
	     var row = jQuery("#cbmGrid").jqGrid('getDataIDs');
    	 var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
    	 var tableDatas = jQuery("#cbmGrid").jqGrid('getRowData');
		 
		 /*alert(tableDatas[0].txtCmdtZoneid+" -- "+tableDatas[0].txtCmdtUpperlimit);
		 alert(tableDatas[0].txtCmdtZoneid+" -- "+tableDatas[1].txtCmdtUpperlimit);
		 alert(tableDatas[0].txtCmdtZoneid+" -- "+tableDatas[2].txtCmdtUpperlimit);*/
		 var grnUpLimit = tableDatas[0].txtCmdtUpperlimit ;
		 var ylowUpLimit = tableDatas[1].txtCmdtUpperlimit ;
		 var redUpLimit = tableDatas[2].txtCmdtUpperlimit ;
		 if(grnUpLimit.trim().length<=0){
				alert("Enter Upper Limit of Green Zone");
				return false;
			 }
		 else if(ylowUpLimit.trim().length<=0){
				alert("Enter Upper Limit of Yellow Zone");
				return false;
			 }
		 else if(redUpLimit.trim().length<=0){
				alert("Enter Upper Limit of Red Zone");
				return false;
			 }
		 jQuery('#'+hdnID).val(griddata);
		 closePopUpDialoge('loadCBM');
		// jQuery('#loadCBM').hide();		
 	 	});
/***CLICKING BUTTON CLOSE**/
 		jQuery('#btnCbmClose').click(function(){
 			closePopUpDialoge('loadCBM');
 	 	});
/***CLICKING BUTTON INSERT**/
		jQuery('#btnCbmInsert').click(function(){
			
			if(checklimitValue()){
				alert('checklimitvalue');
				return true;
			}
			else if(betweenCheck()){
								
			var row_id = jQuery("#cbmGrid").jqGrid('getGridParam','selrow');
			var row = jQuery("#cbmGrid").jqGrid('getDataIDs');
			var colm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
			var zonid= jQuery('#hdnZoneId').val();
			var pmstdId = jQuery('#hdnPmstdId').val();
			var CmdtLowerlimit       = jQuery("#txtCmdtLowerlimit").val();		
			var CmdtUpperlimit       = jQuery("#txtCmdtUpperlimit").val();	
			var CmdtDesirablereading = jQuery("#txtCmdtDesirablereading").val();
			var CmdtMeasuringmethod = jQuery("#txtCmdtMeasuringmethod").val();
			var CmdtCorrectiveaction = jQuery("#txtCmdtCorrectiveaction").val();
			var greenid   = jQuery('#hdngreen').val();
			var yellowid  = jQuery('#hdnyellow').val();
			var redid     = 	 jQuery('#hdnred').val();
			var Uomval =  jQuery("#cmbCmdtUomid").combobox("getValue");//getFieldValue('cmbCmdtUomid',"frmCBM");
			var inspectionid =jQuery("#cmbCmdtInspectionid").combobox("getValue");//getFieldValue('cmbCmdtInspectionid',"frmCBM");
			//alert(parseInt(CmdtUpperlimit) >= parseInt(CmdtLowerlimit));
			//If Val(LowerRead) <= Val(CBMMinVal) And Val(UpperRead) >= Val(CBMMaxval)
			  if(zonid == yellowid  ){
					 var grenUpperlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[0],"txtCmdtUpperlimit");
					 var grenLowerlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[0],"txtCmdtLowerlimit");
					// alert(parseInt(grenUpperlmt) +" >= "+ parseInt(CmdtLowerlimit));
					 
					 if((parseInt(grenLowerlmt) <= parseInt(CmdtLowerlimit))&&( parseInt(grenUpperlmt) >= parseInt(CmdtLowerlimit))){
						alert("Yellow Zone Lower Value are Overlapping Green Zone");	
						 return false;
					}
					 else  if((parseInt(grenLowerlmt) <= parseInt(CmdtUpperlimit))&&( parseInt(grenUpperlmt) >= parseInt(CmdtUpperlimit))){
							alert("Yellow Zone Lower Value are Overlapping Green Zone");	
							 return false;
						}
			  }
			  else  if(zonid == redid  ){
				  var yellowUpperlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[1],"txtCmdtUpperlimit");
				  var yellowLowerlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[1],"txtCmdtLowerlimit");
				  var grenUpperlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[0],"txtCmdtUpperlimit");
				  var grenLowerlmt =  jQuery("#cbmGrid").jqGrid('getCell',row[0],"txtCmdtLowerlimit");
				  if((parseInt(grenLowerlmt) <= parseInt(CmdtLowerlimit))&&( parseInt(grenUpperlmt) >= parseInt(CmdtLowerlimit))){
						alert("Red Zone Lower Value are Overlapping Green Zone");	
						 return false;
					}
				  else if((parseInt(grenLowerlmt) <= parseInt(CmdtUpperlimit))&&( parseInt(grenUpperlmt) >= parseInt(CmdtUpperlimit))){
						alert("Red Zone Lower Value are Overlapping Green Zone");	
						 return false;
					}
				  if((parseInt(yellowLowerlmt) <= parseInt(CmdtLowerlimit))&&( parseInt(yellowUpperlmt) >= parseInt(CmdtLowerlimit))){
						alert(" Red Zone Lower Value are Overlapping Yellow Zone");	
						 return false;
					}
				 else if((parseInt(yellowLowerlmt) <= parseInt(CmdtUpperlimit))&&( parseInt(yellowUpperlmt) >= parseInt(CmdtUpperlimit))){
						alert(" Red Zone Lower Value are Overlapping Yellow Zone");	
						 return false;
					}
			  }
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtLowerlimit",CmdtLowerlimit);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtUpperlimit",CmdtUpperlimit);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtDesirablereading",CmdtDesirablereading);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtMeasuringmethod",CmdtMeasuringmethod);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtCorrectiveaction",CmdtCorrectiveaction);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtInspectionid",inspectionid);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtUomid",Uomval);
			   jQuery("#cbmGrid").jqGrid('setCell',row_id,"txtCmdtPmstandardid",pmstdId);
			  // jQuery("#cbmGrid").trigger("reloadGrid");
			//saveForm("frmCBM","openCBM_save.prv?&pmstdId="+pmstdId+"&zoneid="+zonid );	
			   jQuery("#txtCmdtLowerlimit").val(' ' );		
				jQuery("#txtCmdtUpperlimit").val(' ' );	
				jQuery("#txtCmdtDesirablereading").val(' ');	
			}
			
		}); 	
		
});
function cmbActionTaken(id, options, rowObject){
	return "<select id='cmbActiontaken' style='width:120px;'><option></option><option>ActionRecommend1</option><option>ActionRecommend2</option><option>ActionRecommend3</option><option>ActionRecommend4</option></select>";
}
function checklimitValue(){
	var lowerLimit = jQuery('#txtCmdtLowerlimit').val();
	var upperLimit = jQuery('#txtCmdtUpperlimit').val();
	
	if(parseInt(lowerLimit)> parseInt(upperLimit)){//alert(lowerLimit +" > "+ upperLimit);
		alert("Lower reading should not be greater than Upper reading");
		jQuery("#txtCmdtLowerlimit").val(' ' );		
		setTimeout(function() {setFocusOnField('txtCmdtLowerlimit');},550);
		return false;
	}
	
}
function betweenCheck(){
	var lowerLimit = jQuery('#txtCmdtLowerlimit').val();
	var upperLimit = jQuery('#txtCmdtUpperlimit').val();
	var desirableReading = jQuery('#txtCmdtDesirablereading').val();
	if(lowerLimit.trim().length<=0){
		alert("Enter Lower Limit");
		jQuery("#txtCmdtLowerlimit").val(' ' );		
		setTimeout(function() {setFocusOnField('txtCmdtLowerlimit');},550);
		return false;
	}
	else if(upperLimit.trim().length<=0){
		alert("Enter Upper Limit");
		jQuery("#txtCmdtUpperlimit").val(' ' );	
		setTimeout(function() {setFocusOnField('txtCmdtUpperlimit');},550);
		return false;
	}
	else if(desirableReading.trim().length<=0){
		alert("Enter Desirablereading ");
		setTimeout(function() {setFocusOnField('txtCmdtDesirablereading');},550);
		return false;
	}
	else if(parseInt(lowerLimit) > parseInt(desirableReading) ){
		alert("Desirable Reading should not be lesser than lower reading");
		jQuery("#txtCmdtDesirablereading").val(' ');	
		setTimeout(function() {setFocusOnField('txtCmdtDesirablereading');},550);
		return false ;
	}
	else if( parseInt(upperLimit) < parseInt(desirableReading)  ){
		alert("Desirable Reading should not be greater than upper reading");
		jQuery("#txtCmdtDesirablereading").val(' ');
		setTimeout(function() {setFocusOnField('txtCmdtDesirablereading');},550);	
		return false;
	}
	else
	return true;
}

function cbmActRecFormatter(cellvalue, options, rowObject) {	
	var rowId = options.rowId;
	var formatStr  = '<select id=actiontk_'+ rowId +' style="width:180px;height:50px;"> <option value="Action Recommended1"> Action Recommended1</option>  <option value="Action Recommended1"> Action Recommended2</option>  <option value="Action Recommended1"> Action Recommended3</option>  </select>' ;
 
	return formatStr;
}

function cbmLoadComplete(){
	
	jQuery("#cbmGrid tr[id='1']").css('height','50');
	jQuery("#cbmGrid tr[id='2']").css('height','50');
	jQuery("#cbmGrid tr[id='3']").css('height','50');
	
	var row = jQuery("#cbmGrid").jqGrid('getDataIDs');
	
	 var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
	 var green = jQuery("#cbmGrid").jqGrid('getCell',row[0],cm[0].name);
	 var yellow = jQuery("#cbmGrid").jqGrid('getCell',row[1],cm[0].name);
	 var red = jQuery("#cbmGrid").jqGrid('getCell',row[2],cm[0].name);
	 jQuery('#hdngreen').val(green);
	 jQuery('#hdnyellow').val(yellow);
	 jQuery('#hdnred').val(red);
	 //alert(green +" -- "+ yellow+" -- "+ red);	
	  jQuery("#cbmGrid").jqGrid('setCell',row[0],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#94E031'});
	  jQuery("#cbmGrid").jqGrid('setCell',row[1],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#EDED6F'});
	  jQuery("#cbmGrid").jqGrid('setCell',row[2],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#E52222'});
	
}
function cbmDBLClick(rowid){
	 var rowData = jQuery("#cbmGrid").jqGrid('getRowData',rowid);	
		 enableFields('txtCmdtLowerlimit');
		 enableFields('txtCmdtUpperlimit');
		enableFields('txtCmdtDesirablereading');
		jQuery("#txtCmdtCorrectiveaction").removeAttr('disabled');
 		jQuery("#txtCmdtMeasuringmethod").removeAttr('disabled');
 		jQuery("#btnCbmInsert").attr("disabled", false);
		jQuery("#btnCbmInsert").addClass("easyui-button");
		jQuery("#btnCbmInsert").removeClass("disabledButton");
		jQuery("#txtCmdtLowerlimit").val(rowData.txtCmdtLowerlimit);		
		jQuery("#txtCmdtUpperlimit").val(rowData.txtCmdtUpperlimit);	
		jQuery("#txtCmdtDesirablereading").val(rowData.txtCmdtDesirablereading);
		jQuery("#txtCmdtCorrectiveaction").val(rowData.txtCmdtCorrectiveaction);
 		jQuery("#txtCmdtMeasuringmethod").val(rowData.txtCmdtMeasuringmethod);
	jQuery('#hdnZoneId').val(rowData.txtCmdtZoneid);
}

/*function getSelectdRows(jqGridId){
	
	var allRows = jQuery("#cbmGrid").jqGrid('getRowData');
	
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
	
		//alert("value"+value);		
		
				jsonArrO += '{';
			for(var colName in row) {
				var cellValue = parseJqGridCellValue(row[colName]);
				//alert("cellValue"+cellValue);			
					
						{
						//alert("colName   " +colName);
						jsonArrO += '"'+colName +'":"' + cellValue+'"';
						}
			}
			jsonArrO +=  "},";
			
		}
	
	
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert("jsonArrO--mch   "+jsonArrO);
	return jsonArrO; 
}  */
function convertJqGridToJSONObjectArr_zone(jqGridId){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '{';
		
		for(var colName in row) {
			jsonArrO += '"'+colName +'":"' + parseJqGridCellValue( row[colName]) +'",'; 
		}
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}
</script>
<form id="frmCBM" style="width:900px">
<table width="100%" align="center" >
  <tr>
  <td width="33.3%" valign="top">
  <div  class=" ">
                    <label>Assembly</label>                    
                </div> 
                <div class=""> 
                    <input id="cmbcmAssemblyid" name="cmbcmAssemblyid" class="easyui-combobox"  style="width:330px;" value="${requestScope.assmId}" disabled="disabled" >                    
                </div>
                <div  class="">
                    <label>Sub Assembly</label>                    
                </div> 
                <div class=""> 
                    <input id="cmbcmSubassemblyid" name="cmbcmSubassemblyid" class="easyui-combobox"  style="width:330px;"  >                    
                </div>
                  <div  class="">
                    <label class="mandatory-lbl">Measuring Point</label>                    
                </div>
                <div class=""> 
                    <input id="cmbCmdtInspectionid" name="cmbCmdtInspectionid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.plmTlCbmstdcadtl.cmdtInspectionid}">                    
                </div>        
                <div  class="">
                    <label class="mandatory-lbl">UoM</label>                    
                </div>
                <div class=""> 
                    <input id="cmbCmdtUomid" name="cmbCmdtUomid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.plmTlCbmstdcadtl.cmdtUomid}">                    
                </div>
                <div  class="">
                    <label class="mandatory-lbl">Lower Limit</label>
                    <span  style="margin-left: 124px;" class=" fntSize mandatory-lbl">Upper Limit</span>
                </div> 
                <div class=""> 
                    
                    <input id="txtCmdtLowerlimit" name="txtCmdtLowerlimit" maxlength="4" style="width:140px;text-align:right;" type="text" class="easyui-text"  value="${requestScope.plmTlCbmstdcadtl.cmdtLowerlimit }"/>
                    <span style="margin-left:13%;">
                    <input id="txtCmdtUpperlimit" name="txtCmdtUpperlimit" maxlength="4" style="width:140px;text-align:right;"  type="text" class="easyui-text"  value="${requestScope.plmTlCbmstdcadtl.cmdtUpperlimit }"/>
                    </span>
                    <span id="err_cmbPmsdMachinecondition" class="tpm-errormsg" style="margin-left:150px;" ></span>
	                
	            </div>                
                <div  class=" ">
                    <label class="mandatory-lbl">Desirable</label>
                    <span  style="margin-left: 108px;" >
                    <label id="lblVal" ></label>
                   
                    </span>
                </div> 
                <div class="">     
                <input id="txtCmdtDesirablereading" name="txtCmdtDesirablereading" maxlength="4"  class="easyui-text"  style="width:140px;text-align:right;"   value="${requestScope.plmTlCbmstdcadtl.cmdtDesirablereading}" >                
<!--                   <input id="txtPmsdFrequencyunit" name="txtPmsdFrequencyunit" class="easyui-combobox"  style="width:172px;" value=""  >-->
                <!--<select id="cmbPmsdFrequencyunit" name="cmbPmsdFrequencyunit" class="easyui-combobox" style="width:172px;" required="true"  >
				<option value=""> </option>
				<option value="W"> WEEKLY</option>
				<option value="F"> FORTNIGHTLY</option>
				<option value="M"> MONTHLY</option>
				<option value="Q"> QUARTELY</option>
				<option value="H"> HALF YEARLY</option>
				<option value="Y"> YEARLY</option>
			</select>-->
			                    
                </div>                
  </td>
  
  <td width="33.3%" valign="top">
 				 <div  style="margin-left:15px;">
                    <label>Corrective Action</label>                    
                </div> 
                <div style="margin-left:15px;">
                    <textarea rows="4" cols="37" id="txtCmdtCorrectiveaction" name="txtCmdtCorrectiveaction">${requestScope.plmTlCbmstdcadtl.cmdtCorrectiveaction}</textarea>
                </div>
  				<div  style="margin-left:15px;">
                    <label>What(Activity)</label>  
                                      
                </div> 
                <div style="margin-left:15px;">
                    <textarea rows="4" cols="37" id="txtPmsdHowmethod" name="txtPmsdHowmethod">${requestScope.watActvity}</textarea>
                    
                </div> 
                                
                
		</td>
		<td valign="top" width="33.3%">
				<div style="margin-left:15px;">
                    <label>Measurement Method</label>                    
                </div> 
                <div style="margin-left:15px;">
                    <textarea rows="4" cols="37" id="txtCmdtMeasuringmethod" name="txtCmdtMeasuringmethod">${requestScope.plmTlCbmstdcadtl.cmdtMeasuringmethod}</textarea>
                </div>
		</td>
  	</tr>
</table>
<div id="cbmTable" align="left">
	<div class="notes"><label>Double Click on zone to edit/insert the reading</label>
	<span style="float:right;margin-top:-8;"><input type="button" value="Insert" id="btnCbmInsert" class="easyui-button"/>
	<!-- <span><input type="button" value="Define Zone" id="btndefineZone" class="easyui-button" /></span> -->
	</span>
	</div>
	
	<div align="center" style="background-color: #F7E496;font-weight:bold;font-size:16px;width:111%;box-shadow:0 3px 1px rgba(0, 0, 0, 0.2);border-radius: 3px 3px 3px 3px;  ">Condition Based Maintenance</div>
	<table id="cbmGrid"><tr><td></td></tr></table>
	<div id="cbmPager"></div>
	<div align="center" style="width:111%;margin-top:5px;">
		<input type="button" value="Ok" id="btnCbmOk" class="easyui-button"/>
		
		<input type="button" value="Close" id="btnCbmClose" class="easyui-button"/>
	</div>
</div>
<input type="hidden" id="hdnPmstdId" name="hdnPmstdId" value='${requestScope.pmsdId}'/>
<input type="hidden" id="hdnZoneId" name="hdnZoneId" value=''/>
<input type="hidden" id="hdngreen" name="hdngreen" value=''/>
<input type="hidden" id="hdnyellow" name="hdnyellow" value=''/>
<input type="hidden" id="hdnred" name="hdnred" value=''/>
<input type="hidden" id="saveval" name="saveval" value='${requestScope.hdnSaveVal }'/>

</form>