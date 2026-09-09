<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmsaftytalk');
	jQuery('#submitForm').val('frmsaftytalk');	
	
	formatDateBox('dtesftlDate','dd-mmm-yyyy');
	fillComboBox("frmsaftytalk","cmbsftlPrepardby","employee.commonFilter" );
	fillComboBox("frmsaftytalk", "cmbfstlkEquipmentid", "machineCombo.commonFilter");
	fillComboBox("frmsaftytalk", "cmbfstlkPreparedby", "employee.commonFilter");
	fillComboBox("frmsaftytalk", "cmbfstlkApprovedby", "employee.commonFilter");
	fillComboBox("frmsaftytalk", "cmbfstlkIssuedby", "employee.commonFilter");
	fillComboBox("frmsaftytalk", "cmbfstlkProductid", "product.commonFilter");	
	
	var datee = getFieldValue('dtesftlDate','frmsaftytalk');
	if (datee=='' || datee==' '){
	  fillWithCurrentDate('dtesftlDate');
	  setFieldValue('cmbsftlPrepardby',jQuery('#hdnuserId').val());
	}else
	{
		//setFieldValue('cmbsftlPrepardby',jQuery('#hdnPreparadby').val());
		setFieldValue('cmbsftlPrepardby',jQuery('#hdnPreparadId').val());
		
	}
	 var keyid = getFieldValue('hdnkeyid','frmsaftytalk');

	 if(keyid.length>0)
	    readOnlyFields("dtesftlDate");
	   	
	 
	
	var factId = jQuery("#frmsaftytalk input[id='factory']").val();
    var sectionId = jQuery("#frmsaftytalk input[id='section']").val();
    var cellId = jQuery("#frmsaftytalk input[id='cell']").val();
    var machId = jQuery("#frmsaftytalk input[id='machine']").val();
   //var flid = jQuery("#frmsaftytalk input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+keyid;  					
	
   loadFunctionalLocation("saftytalkfunLocation","functionalLoc.sftlk","frmsftlkFuntKeyIds","frmsaftytalk",dataStr);
    
    //setFieldValue('flid',keyid,'frmsaftytalk');
     
    //processGridnew("SafetyTalk_input.sftlk","&q=2","saftytalkGrid", "pager", "SafetyTalk", "fndoubleClick","","");
   
	jQuery("#btnInset").click(function(){//alert(" Insert :: ");
		var keyid = jQuery("#hdnsftlKeyid").val();		
		saveForm('frmsaftytalk','SafetyTalk_input.sftlk?&keyid='+keyid,"");
	});

	jQuery("#btnDelete").click(function() {//alert(" Delete :: ");
			
		var keyid = getFieldValue('hdnsftlKeyid','frmsaftytalk');
		
		if(keyid != "")
		{
			var r = confirm("Do You Want To Delete?");
			if (r == true) {
				deleteRecord("frmsaftytalk","SafetyTalk_delete.sftlk?&keyid="+keyid);
			}
		}else
		{
			alert(" Select a row to delete ");
			return false;
		}

	});
	
	jQuery("#btnClear").click(function(){		
		fnclear();
	});	
	
	fileManagerPopUp("","STY","frmsaftytalk","btnfilemgr","STYTFilemgr");
});


function btnfilemgr_click(){
    
	var keyid = jQuery('#hdnsftlKeyid').val();
  	
	if(keyid.trim().length>0){
  		fileManagerPopUp(keyid,"STY","","","");
  		
  	}else{
  		saveForm('frmsaftytalk','SafetyTalk_save.sftlk?filemanger=filemanger');	
	 }
  	
}

function dtesftlDate_onSelect(record) {
	completedDateEvt();
} 


function completedDateEvt()
{
	var currentDate = getServerDateTime();
	var qpointDate = jQuery('#dtesftlDate').datebox("getValue");
	
	if(convertStringToDate(qpointDate)> currentDate)
	{
		alert('Safety Talk date should not exceed current date');
		fillWithCurrentDate('dtesftlDate');
	}
}


function frmsaftytalk_deleteSuccessCallback(result){
	alert(result.successData.msg);
	fnclear();
	jQuery("#saftytalkGrid").trigger("reloadGrid"); 
}
function fnviewgrid()
{
	  var flid =  jQuery("#frmsaftytalk input[id='flid']").val();
	  var datee = getFieldValue("dtesftlDate");
	  var Preparedby = getFieldValue("cmbsftlPrepardby", "frmsaftytalk");
	  //alert(" Preparedby :: Grid "+Preparedby);
	  
	  processGridnew("SafetyTalk_input.sftlk","&q=2&keyid="+flid+"&Date="+datee+"&Preparedby="+Preparedby,"saftytalkGrid", "pager", "SafetyTalk", "fndoubleClick","","");
	  //void processGridnew(any url, any filterString, any tableId, any pagerId, any tableCaption, any   doubleClickFunction, any tableHeaderSpanCallback, any ongridcompletecallback, any selectRowFunction, any filterNeed)
}



function frmsaftytalk_successsCallback(result)
{
	fnclear();
	var safetykeyid=result.safetykeyid;
	//alert(" safetykeyid :: "+safetykeyid);
	//jQuery('#hdnsftlKeyid').val(safetykeyid);
	if(safetykeyid.trim().length>0)
	  readOnlyFields("dtesftlDate");
	
	var filemanger =result.filemanger;
	if(filemanger==true){
    	if(safetykeyid.trim().length>0){
   			
    		fileManagerPopUp(safetykeyid,"STY","","","");
    		
			 }
		}
	
	
	fnviewgrid();
	
	
	//jQuery("#saftytalkGrid").trigger("reloadGrid"); 
}
function fndoubleClick(id)
{
	var rowData = jQuery("#saftytalkGrid").jqGrid('getRowData',id);
	var preparedby = rowData.EMPID;
	//var Date = rowData.Date;
	var SafetyTalk = rowData.SafetyTalk;
	var Keyid = rowData.Keyid;
		
	setFieldValue('hdnsftlKeyid',Keyid,'frmsaftytalk');
	setFieldValue('cmbsftlPrepardby',preparedby,'frmsaftytalk');
	//setFieldValue('dtesftlDate',Date,'frmsaftytalk');
	//setFieldValue('txtsftlTalk',SafetyTalk,'frmsaftytalk');	
	jQuery("#txtsftlTalk").val(SafetyTalk);
	
}
function fnclear(){	
	setFieldValue('hdnsftlKeyid','','frmsaftytalk');
	//setFieldValue('cmbsftlPrepardby','','frmsaftytalk');
	//setFieldValue('dtesftlDate','','frmsaftytalk');
	setFieldValue('txtsftlTalk','');
	
}
function frmsaftytalk_FuntLocHierarchy_SuccessCallBack(keys){
	  var flid=keys.flId;	 
	  //setFieldValue('flid',flid,'frmsaftytalk');	 
	  fnviewgrid();
}
</script>
<form id="frmsaftytalk" name="frmsaftytalk">
<div style="margin-left:40px;margin-top:60px;">
<div id='wrapper' style="padding-left:90px;">
<table>
		<tr>						
			<td colspan="3">
				 <div id="frmsftlkFuntKeyIds"  >							
					<input type="hidden" id="factory" name="cmbfstlkFactoryid"  value="" ></input>
					<input type="hidden" id="section" name="cmbfstlkSectionid"  value=""></input>
					<input type="hidden" id="cell"    name="cmbfstlkCellid"     value=""></input>
					<input type="hidden" id="machine" name="cmbfstlkMachineid"  value=""></input>
					<input type="hidden" id="flid" name="cmbsftlFlid"  value="${requestScope.SheTlSaftytalk.sftlFlid}"></input>
										
				</div>						
			    <div id="saftytalkfunLocation" style="width:84.3%;width:82%\9;"></div>	
		    </td>
		    
		    <td>
		        <div style="margin-left:10px;">
				    <div>
					  <label class="mandatory-lbl">Date</label>
				    </div>
				    <div>
				      <input class="easyui-datebox"  style="width:100px;height:21px;" id="dtesftlDate" name="dtesftlDate" value= "${requestScope.Date}"  />
			        </div>
		        </div>
		    </td>
		
		</tr>
</table>
<table>
	<tr>
		<td colspan="2" style="padding-left:0px;">
			<div >
				<label class="mandatory-lbl">Safety Talk</label>
			</div>
			<div>
				<textarea rows="4" cols="200" id="txtsftlTalk" name="txtsftlTalk"  style="width:400px;height:55px;">${requestScope.SheTlSaftytalk.sftlDate}</textarea>
			</div>
		</td>
		
		<td valign="top">
		<div style="padding-left:10px;">
			<div>
				<label class="mandatory-lbl">Prepared by</label>
				
			</div>
			<div>
				<input  style="width:250px;height:21px;" class="easyui-combobox" id="cmbsftlPrepardby" name="cmbsftlPrepardby" value= "${requestScope.SheTlSaftytalk.sftlPrepardby}" />
				
			</div>
		</div>
		</td>		
		
		<td>
			<div style="margin-left:20px;position:relative;">
			
	            <span id="STYTFilemgr" style="position:absolute;top:14px;" >
       		    </span> 
	
			</div>
			<div style="padding-left:16px;padding-top:50px;">
					<input type="button" class="easyui-button" id="btnInset"  name="btnInset" style="width:80px;height:21px;" value="Insert"/>			
					<input type="button" class="easyui-button" id="btnDelete"  name="btnDelete" style="width:80px;height:21px;" value="Delete"/>			
					<input type="button" class="easyui-button" id="btnClear"  name="btnClear" style="width:80px;height:21px;" value="Clear"/>
			</div>
	</td>
	</tr>
	</table>
	
			
			
	

	<div style="width:79%; padding-top:0px;">
			<table id='saftytalkGrid'>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
</div>
</div>
	<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.keyid}" />
	<input type="hidden" id="hdnsftlKeyid" name="hdnsftlKeyid" value="${requestScope.SheTlSaftytalk.sftlKeyid}" />
	<input type="hidden" id="mode" name="mode"/>
	<input type="hidden" id="hdnuserId" name="hdnuserId" value="${requestScope.userId}"/>
	<input type="hidden" id="hdnPreparadby" name="hdnPreparadby" value="${requestScope.Preparadby}"/>
	<input type="hidden" id="hdnPreparadId" name="hdnPreparadId" value="${requestScope.PreparadId}"/>
	
</form>