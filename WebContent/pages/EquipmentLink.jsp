
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmEquipmentLinkGrid');
	jQuery('#submitForm').val('frmEquipmentLinkGrid');
	var keyid= jQuery("#hdneqpkeyid").val();
	processGridnew("pmtasklist_input.task","?q=1&tasktype=EQP&keyid="+keyid,"TaskGridEqp","TaskEqpPager"," ", "doubleclick","","onloadcomplete");
	var factId = jQuery("#frmEquipmentLinkGrid input[id='factory']").val();
    var sectionId = jQuery("#frmEquipmentLinkGrid input[id='section']").val();
    var cellId = jQuery("#frmEquipmentLinkGrid input[id='cell']").val();
    var machId = jQuery("#frmEquipmentLinkGrid input[id='machine']").val();
    var flId = jQuery("#frmEquipmentLinkGrid input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flId;
    loadFunctionalLocation("equipment","functionalLoc.task","frmEquipmentLinkGridLocationfunLocationValues","frmEquipmentLinkGrid",dataStr);
    processGridnew("pmtasklistequipment_input.task","?q=1&cellId"+cellId+"&Taskkeyid="+keyid,"EquipmentGrid","EquipmentPager"," ", "doubleclick","","eqpLoadComplete");

   
    jQuery("#btnPaste").click(function(){
		///var tskGrdVal=ConvrtJsonArrTaskGrid();
		var eqpGrdVal= ConvrtJsonArrEqupGrid();
		var saveVal=0;
		if( eqpGrdVal!=""){
			//saveForm("frmEquipmentLinkGrid","pmtasklistcreate_save.task?q=2&TSKKEYID="+taskkeyid+"&EQPKEYID="+eqpkeyid);
		//alert("in  "+eqpGrdVal);
			saveVal++;
		}else if (eqpGrdVal==""){
			alert("Select Equipment and Task");
			return false;
		}/*else if(tskGrdVal=="")
		{
			alert("Select Task");
			return false;
		}*/else if(eqpGrdVal=="")
		{
			alert("Select Equipment");
			return false;
		}
		if (saveVal>0){
			var datastring ="?q=2&EQPGRID="+eqpGrdVal;
			//alert("in  "+eqpGrdVal);
			saveForm("frmEquipmentLinkGrid","pmtasklistcreate_save.task"+datastring);
		}
    	//SaveRecord();
	});
    
	});
function ConvrtJsonArrTaskGrid(){
	var allRows = jQuery("#TaskGridEqp").jqGrid('getRowData');
	var jsonArrO="[";
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		for(var colName in row) {
			var TaskCheck=jQuery("#TaskGridEqp").jqGrid('getCell',(i+1),'TARGET');
			if(TaskCheck=='1'){
				if(colName=='KEYID'){
					jsonArrO += '{';
					var tskKeyid= jQuery("#TaskGridEqp").jqGrid('getCell',(i+1),colName);
					jsonArrO += '"txtEqplPmtmKeyid":"'+tskKeyid+'"},';
				}
			}
		}
	}
	if (jsonArrO=="["){
		jsonArrO = jsonArrO.slice(0,-2);
	}
	else
		jsonArrO = jsonArrO.slice(0,-1)+ "]";
	return jsonArrO;
}
function ConvrtJsonArrEqupGrid(){
	/* var allRows = jQuery("#EquipmentGrid").jqGrid('getRowData');
	var jsonArrO='[';
	//alert('allRows.length:' + allRows.length);
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		//for(var colName in row) {
		alert("row" + i);
		var EqupCheck=jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'SelectVal');
		var Eqplinkkeyid = jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'Equipmentlinkkeyid');
		alert("EqupCheck"+EqupCheck);			 
		if(EqupCheck=="1"){
			alert("EqupCheck1:Eqplinkkeyid");
            jsonArrO+='txtkeyid'+jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'KEYID');                   
            jsonArrO+='equipmentkeyid'+jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'Equipmentlinkkeyid');           
		}
		else if(EqupCheck =="0"  && Eqplinkkeyid != null   && Eqplinkkeyid.trim() != ""){
			alert("EqupCheck0:Eqplinkkeyid"+Eqplinkkeyid);
		    jsonArrO+='txtkeyid'+jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'KEYID');
     		jsonArrO+='equipmentkeyid'+jQuery("#EquipmentGrid").jqGrid('getCell',parseInt(i+1),'Equipmentlinkkeyid');              
		}
	 	alert("row finish"+i);
	}
	
	
	if (jsonArrO=="[")
		jsonArrO = jsonArrO.slice(0,-2);
	else
		jsonArrO =jsonArrO.slice(0,-1)+ "]";
	return jsonArrO;
*/
	var allRows = jQuery("#EquipmentGrid").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row["SelectVal"];	
		var equipkeyid = parseJqGridCellValue(row["KEYID"]);
		var eqplinkkeyid = parseJqGridCellValue(row["Equipmentlinkkeyid"]);
		var taskkeyid= jQuery("#hdneqpkeyid").val();
		//alert(taskkeyid);
		if( value != null  &&  value.trim()  != ""){					
			if(value == '1' && (eqplinkkeyid==null || eqplinkkeyid.trim()==""))
			{
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtEqplEqpkeyid":"' + equipkeyid+'"';
				jsonArrO += ',"txtEqplKeyid":"' + eqplinkkeyid+'"';
				jsonArrO += ',"txtEqplPmtmKeyid":"' + taskkeyid+'"';
				jsonArrO += ',"Flg":"I"';				
				jsonArrO +=  "},";
			}
			else if(value == '1' && eqplinkkeyid!=null && eqplinkkeyid.trim()!="")
			{
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtEqplEqpkeyid":"' + equipkeyid+'"';
				jsonArrO += ',"txtEqplKeyid":"' + eqplinkkeyid+'"';
				jsonArrO += ',"txtEqplPmtmKeyid":"' + taskkeyid+'"';
				jsonArrO += ',"Flg":"U"';
				jsonArrO +=  "},";
			}
			else if(value == '0' && eqplinkkeyid!=null && eqplinkkeyid.trim()!="")
			{
				flg=true;
				jsonArrO += '{';
				jsonArrO += '"txtEqplEqpkeyid":"' + equipkeyid+'"';
				jsonArrO += ',"txtEqplKeyid":"' + eqplinkkeyid+'"';
				jsonArrO += ',"txtEqplPmtmKeyid":"' + taskkeyid+'"';
				jsonArrO += ',"Flg":"D"';
				jsonArrO +=  "},";
			}			
		} 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	//alert('jsonArrO:'+jsonArrO);
	return jsonArrO; 
}

function onloadcomplete(){
	
	var rowTask=jQuery("#TaskGridEqp").jqGrid('getDataIDs');//	row get data
	var tlgroupid= jQuery("#hdneqpkeyid").val();
		 	for(var i=0;i<rowTask.length;i++)
			{
				var TaskgpId= jQuery("#TaskGridEqp").jqGrid('getCell',rowTask[i],"KEYID");
               
				
				if(tlgroupid == TaskgpId)
					jQuery("#TaskGridEqp").jqGrid('setCell',rowTask[i],'TARGET','1');
			}
		
}
function eqpLoadComplete(){
	var roweqp=jQuery("#EquipmentGrid").jqGrid('getDataIDs');
	
	
	for(var i=0;i<roweqp.length;i++)
	{
		var SelectVal= jQuery("#EquipmentGrid").jqGrid('getCell',roweqp[i],"SelectVal");
		
	if(SelectVal=="1"){
		
		jQuery("#EquipmentGrid").jqGrid('setCell',roweqp[i],'SelectVal','1');
		}
	}
}
function frmEquipmentLinkGrid_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	if(keyIds.cellId.trim().length>0)
	var keyid=jQuery("#hdneqpkeyid").val();
	processGridnew("pmtasklistequipment_input.task","?q=1&cellId="+keyIds.cellId+"&Taskkeyid="+keyid,"EquipmentGrid","EquipmentPager"," ", "doubleclick","","eqpLoadComplete");
	}
	
function checkBox(id, options, rowObject) {
	var tlgroupid= jQuery("#hdneqpkeyid").val();
	var id = options.rowId;
	return '<input id="equipment_checkbox_'+ id +'" '+ (rowObject[1]==tlgroupid ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+id + '\');}else{checkBoxUnChecked(\''+ id +'\')}"/>';
}

function chkboxCheck(rowId)
{
	jQuery("#TaskGridEqp").jqGrid('setCell',rowId,'TARGET','1');		
}

function checkBoxUnChecked(rowId){
	
	jQuery("#TaskGridEqp").jqGrid('setCell',rowId,'TARGET','0');	
}

function form_lfladf(){		
	SaveRecord();
}
function ChkFormatter(id, options, rowObject) {
	var taskkeyid= jQuery("#hdneqpkeyid").val();
	   var id = options.rowId;
		return '<input id="task_checkbox_'+ id +'" '+ (rowObject[2]==taskkeyid ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){taskchkboxCheck(\''+id + '\');}else{taskcheckBoxUnChecked(\''+ id +'\')}"/>';
}
function taskchkboxCheck(rowId)
{
	jQuery("#EquipmentGrid").jqGrid('setCell',rowId,'SelectVal','1');		
}

function taskcheckBoxUnChecked(rowId){
	jQuery("#EquipmentGrid").jqGrid('setCell',rowId,'SelectVal','0');	
}



function convertGridToJSONArr(jqGridId){
	
	var allRows = jQuery("#TaskGridEqp").jqGrid('getRowData');
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		jsonArrO += '{';
		
		for(var colName in row) {
			
			if(row[colName].substring(0,6)!='<input')
			{
				jsonArrO += '"'+colName +'":"' + row[colName] +'",'; 
			}
			else
			{
				var x=row[colName].indexOf("id=")+4;
				var y=row[colName].substring(x);
				var z = y.indexOf('"');					
				jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val() +'",'; 
			}
		}
		jsonArrO = jsonArrO.slice(0, -1) + "},"; 
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
	return jsonArrO; 
}


function SaveRecord()
	{	
    
	var rowTask=jQuery("#TaskGridEqp").jqGrid('getDataIDs');//	row get data
	var rowEqp=jQuery("#EquipmentGrid").jqGrid('getDataIDs');//	row get data
	
		for(var i=0;i<rowTask.length;i++)
		{
			var Task= jQuery("#TaskGridEqp").jqGrid('getCell',i+1,"TARGET");
			if(Task=='1'){
				
				var taskkeyid    = jQuery("#TaskGridEqp").jqGrid('getCell',i+1,"KEYID");
				if(taskkeyid!=null && taskkeyid!='undefined'&& taskkeyid!=undefined && taskkeyid!="")
				{	
					for(var j=0;j<rowEqp.length;j++){
						var Equipmnt= jQuery("#EquipmentGrid").jqGrid('getCell',i+1,"SelectVal");
						if(Equipmnt=='1'){
							var eqpkeyid    = jQuery("#EquipmentGrid").jqGrid('getCell',j+1,"KEYID");
							if(eqpkeyid!=null && eqpkeyid!='undefined'&& eqpkeyid!=undefined && eqpkeyid!="")
							{	//alert("j "+j+"    i     "+i);
								//saveForm("frmEquipmentLinkGrid","pmtasklistcreate_save.task?q=2&TSKKEYID="+taskkeyid+"&EQPKEYID="+eqpkeyid);
								
							}
						}
					} 
				}
			}
		}
}

</script>


<form id="frmEquipmentLinkGrid" name="frmEquipmentLinkGrid">
<div id="wrapper"  style="width:100%;">
<table cellspacing="15px" style="margin-left: 100px">
   <tr>
      <td colspan="2" >
     
         <div id="frmEquipmentLinkGridFuntKeyIds" >							
									<input type="hidden" id="factory" name="cmbFactoryid"  value="" ></input>
									<input type="hidden" id="section" name="cmbSectionid"  value=""></input>
									<input type="hidden" id="cell"    name="cmbCellid"     value=""></input>
									<input type="hidden" id="machine" name="cmbMachineid"  value=""></input>	
									<input type="hidden" id="flid"    name="cmbflid"        value=""></input>	
									
				<div id="equipment" style="width:112%;width:100%\9;"></div>
			</div>

</td>



<tr>
     <td>
         <div  >
		   <div>
				<table id="TaskGridEqp" ></table> 
			</div>
			<div id="TaskEqpPager"></div>
		  </div>
      </td>

<td style="padding-left: 8%;" >
		<div>
		   <input type="button" class="easyui-button" value ="Paste" name="btnPaste" id="btnPaste" style="height: 23px;margin-left: 5px; "/>
		</div>
        <div  style=" ">
			<div>
				<table id="EquipmentGrid" ></table> 
			</div>
			<div id="EquipmentPager"></div>
		</div>

<input id="hdneqpkeyid" type="hidden" name="hdneqpkeyid"  value="${requestScope.tlgroupkeyid}">

</td>

</tr>
</table>
</div>
</form>