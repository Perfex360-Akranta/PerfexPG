
<script type="text/javascript">
jQuery(document).ready(function(){
	 
	var progID = jQuery('#hdnprogKeyid').val();
	var bachId = jQuery('#hdnBatchkeyId').val();
	var flId = jQuery('#hdnBatchFlid').val();
	var type = jQuery('#hdnBatchType').val();
	//alert("type :1" +type);
	
	
	var factId = jQuery("#frmAddbachEmployee input[id='factory']").val();
	var sectionId = jQuery("#frmAddbachEmployee input[id='section']").val();
	var cellId = jQuery("#frmAddbachEmployee input[id='cell']").val();
	var machId = jQuery("#frmAddbachEmployee input[id='machine']").val();
  //  var flid = jQuery("#frmAddbachEmployee input[id='flid']").val();
	/*	
    jQuery("#cmbAddEmpRoleId").combobox({onRequest:function( ){
		//var flidMain = jQuery("#frmCustomerMapping input[id='flid']").val();
		var flidMain=""; 
		return "&flId="+flidMain;
	}});*/
    fillComboBox('frmAddbachEmployee','cmbAddEmpRoleId','RoleCombo.roleteam?&flid='); //+flidMain
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flId+ "&disable=N" ;//+"&frmType=false";
	loadFunctionalLocation("addEmployeefunLocation","functionalLoc.commonFilter","addEmployeefunLocationValues","frmAddbachEmployee",dataStr);

	
 	//alert(progID+" -- "+bachId);
	//processGridnew("AddBachEmployee_input.tcl","?q=2&progKeyid=" + progID + "&bachId=" + bachId + "&flid=" + flId + "&type=" + type,"grdbachEmployee","grdbachEmployeePager","","","","BachEmploadComplete"," ");
	//viewGrid();
	//alert(jQuery("#hdnTemp").val());
    jQuery('#btnBachSaveEmp').click(function(){
    

  	   
  		var selArray =  jQuery("#grdbachEmployee").jqGrid('getGridParam', 'selarrrow');
  		var selrowid="";
  		 var jsonArr='';
  	  if(selArray !=null && selArray!=" " && selArray!=""){
  		//var r = confirm("Do You Want To Update?");
  	//	var statusvalue= jQuery("#cmbStatus").combobox("getValue");  
//  	alert("statusvalue:::"+statusvalue);
  	
  	
  		for(var i=0;i<selArray.length;i++)
  		{
  	      // alert("INSIDE THE LOOP");
  			selrowid=selArray[i];
  	       //jsonArr+='[';
  		   var Keyid =jQuery("#grdbachEmployee").jqGrid('getCell', selrowid,"txtBstdkeyid");
  		  // alert("Keyid"+Keyid);
  		   var Criteriasplit= Keyid.split(',');
  		   //alert("Criteriasplit"+Criteriasplit);
  		 	for(var k=0;k<Criteriasplit.length;k++){
  			 	var Keyidval=Criteriasplit[k]; 
  			 	var keyvalSplit=Keyidval.split(";");
  			 	var keyvalu=keyvalSplit[0];
  			 	//jsonArr += '"'+keyvalu + '",';	
  			 	//alert("keyvalu"+keyvalu);
  			 	jsonArr += '"'+keyvalu + '",';	
  	 	    }
  		 	jsonArr = jsonArr.substring(0,jsonArr.length);
  			jsonArr += ',';
  		  //  alert("jsonArr "+jsonArr);
  		}	
  		jsonArr = jsonArr.substring(0,jsonArr.length);
  	    var UpdateList=jsonArr;
  	    //alert("UpdateList::"+UpdateList);
  	  //  if(statusvalue.equals("TECHO"))
  	    //	{
  	    //	alert("INSIDE THE TECHO");
  	        if(UpdateList!=null)
  	    	{  
  	        	saveForm("frmAddbachEmployee","entAddEmployee_save.entbatch")
  	        		//saveForm("frmAddbachEmployee","entAddEmployee_save.entbatch&UpdateList"+UpdateList)
  	      
  	    	}
  	  }
    	;
    });	 
    
    
	jQuery('#chkSelectAll').click(function() {
		//LoadingFormWaiter("preLoadContent","LoadContent");
	    show_winMask(1);
        fnSelectAllEmp();
		show_winMask(0);
		
	 });
	if(type != "gt"){
		//readOnlyFields('cmbAddEmpRoleId');
		//readOnlyFields();
		 jQuery("#frmAddbachEmployeeFuntKeyIds").hide();
		 jQuery("#addemployeediv").hide();
		 
			
	}
    
});

function BtnFormatterDelete(id, options, rowObject)
{					
	var rowId = options.rowId;
	var gridId = options.gid;

	return '<input type="button" id="remov" class="grdButton" value="" onclick="deleterec(\''+rowId + '\',\''+gridId + '\');"/>';
}

function deleterec(rowid,gridId){
	
	var row = jQuery("#grdbachEmployee").jqGrid('getDataIDs');
	var bstdkeyid = jQuery("#grdbachEmployee").jqGrid('getCell',row[rowid-1],"txtBstdkeyid");
	//alert(bstdkeyid);
	 var r = confirm("Are You Sure To Delete?");	   
	  if(r){
		 // alert(result.msg);
		  if(bstdkeyid == null ||bstdkeyid == '' || bstdkeyid == ' ' ){
				alert("Only Selected record can be delete ")
				return false;
			}
			processAjaxCalls('deleteBatchEmployee.entbatch','&bstdkeyid='+bstdkeyid+'&gridId='+gridId ,'deleteBatchEmployee_onsuccessCallBack','deleteBatchEmployee_onerrorCallBack');
	      
	   
	     }
	  else{
		  return false;
	} 
	
}
function deleteBatchEmployee_onsuccessCallBack(result){
	alert(result.successData.msg);
	jQuery("#grdbachEmployee").trigger("reloadGrid");
	
	  
}

function frmAddbachEmployeecmbAddEmpRoleId_onSelect(id){
	viewGrid();
}

function viewGrid() {
	var progID = jQuery('#hdnprogKeyid').val();
	var bachId = jQuery('#hdnBatchkeyId').val();	
	var roleId = getFieldValue("cmbAddEmpRoleId");	
	var newFlid =  jQuery("#frmAddbachEmployee input[id='flid']").val();
	var flId=newFlid;
	var type = jQuery('#hdnBatchType').val();
	var bachDate = jQuery('#hdnbachDate').val();
	var ds ="?q=2&progKeyid=" + progID + "&bachId=" + bachId + "&flid=" + flId + "&type=" + type+ "&roleId=" + roleId+ "&newFlid=" + newFlid;
	processGridnew("AddBachEmployee_input.tcl",ds,"grdbachEmployee","grdbachEmployeePager","","","","BachEmploadComplete"," ");

}

function  fnSelectAllEmp() {
	var row = jQuery("#grdbachEmployee").jqGrid('getDataIDs');
	for(var i=0;i<row.length;i++)
	 {
		if(jQuery("#chkSelectAll").is(':checked')== true){
		    jQuery('#grdbachEmployee').setSelection(row[i], true);
		    jQuery('input:checkbox[id=jqg_grdbachEmployee_'+row[i]+']').attr('checked',true);
		    jQuery("#grdbachEmployee").jqGrid('setCell',row[i],'selctVal','1');
		 }
		 else {
			    jQuery('#grdbachEmployee').setSelection(row[i], false);
			    jQuery('input:checkbox[id=jqg_grdbachEmployee_'+row[i]+']').attr('checked',false);
			    jQuery("#grdbachEmployee").jqGrid('setCell',row[i],'selctVal','0');

		 }
	 }	
}
function BachEmploadComplete(){
	//Bstdkeyid
	var row = jQuery("#grdbachEmployee").jqGrid('getDataIDs');
	
	 var cm = jQuery("#grdbachEmployee").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var Bstdkeyid = jQuery("#grdbachEmployee").jqGrid('getCell',row[i],"txtBstdkeyid");	
		   
		 
		   //jQuery('#jqg_grdbachEmployee_'+row[i])
		   if(Bstdkeyid.trim().length>0){ 
			  // jQuery('#jqg_grdbachEmployee_'+row[i]).attr('checked',true);
			    jQuery('#grdbachEmployee').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_grdbachEmployee_'+row[i]+']').attr('checked',true);
			    jQuery("#grdbachEmployee").jqGrid('setCell',row[i],'selctVal','1');
		   }
	 }
}
function bachChkBoxChk(rowId){
	var bachId = jQuery('#hdnBatchkeyId').val();
	jQuery("#grdbachEmployee").jqGrid('setCell',rowId,'txtBstdbachkeyid',bachId);
	jQuery("#grdbachEmployee").jqGrid('setCell',rowId,'selctVal','1');
}
function bachChkBoxUnChk(rowId){
	jQuery("#grdbachEmployee").jqGrid('setCell',rowId,'txtBstdbachkeyid'," ");
	jQuery("#grdbachEmployee").jqGrid('setCell',rowId,'selctVal','0');
}
/**Function for  selecting/Unselecting all Rows**/
function grdbachEmployee_selectAll(id,status){
	
	for(var i=0; i<id.length; i++){
		if(status)
			bachChkBoxChk(id[i]);
		else
			bachChkBoxUnChk(id[i]);
	}
}
/**End**/
/**Function for  selecting/Unselecting  Row**/
function grdbachEmployee_selectRow(id){

	if(jQuery('#jqg_grdbachEmployee_'+id).is(':checked'))
		bachChkBoxChk(id);
	else
		bachChkBoxUnChk(id);
}
function frmAddbachEmployee_beforeSubmit()
{
	var selArray =  jQuery('#grdbachEmployee').jqGrid('getGridParam', 'selarrrow');

	/*	if( selArray.length <= 0 ) {
		alert("Select Employee ");		
		return "";
	}
	else{ 
 */	

	var gridDataStr = JqGridToJsonSelectdRows('grdbachEmployee','jqg_grdbachEmployee_','selctVal');
	if( ! checkEmployeeSelected() && gridDataStr.length == 0 ){
		alert("Select Employee ");
		return false;	
	}	
	var gridData = '&employee='+escape(gridDataStr);
 	  // alert(gridData);
	   return gridData ;
//	}
}
function checkEmployeeSelected(){
	var allRows = jQuery("#grdbachEmployee").jqGrid('getRowData');
	for( var i = 0; i < allRows.length;i++)
	{
		var row = allRows[i];
		var value = row["txtBstdkeyid"];
		if(value.trim().length > 0 )
			return true;		
	}
	return false;
	
}
function frmAddbachEmployee_successsCallback(result)
{
	
	jQuery("#grdbachEmployee").trigger("reloadGrid");
	closePopUpDialoge("divAddEmp");	

}

function frmAddbachEmployee_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	viewGrid();	
} 
</script>
<form id="frmAddbachEmployee">
 <div id='wrapper' style="width:99%;">
	<div id="addEmpFnlnLoc" class="easyui-tabs" style="height: 380px;height: 340px\9;width: 1000px; width:1000px\9;  float: left">
	  <div style="padding-left:10px;padding-top:10px;">
		<table style="width: 100%">	
		<tr>
			<td  width="20%">
						<div style="padding-left:0px;">
						   
							<input type="checkbox" id="chkSelectAll" name="chkSelectAll" style="margin-left:0px;" />
							
							<input type="text" value="Select All" disabled="disabled" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
						</div>
			</td>	
			<td width="30%">		
	 			<div  id="frmAddbachEmployeeFuntKeyIds">
					<div style="float: left;padding-right: 30px;">
					<input type="hidden" id="factory" name="cmbFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbSectionid" value=" " ></input>
					<input type="hidden" id="cell" name="cmbCellid" value=" " ></input>
					<input type="hidden" id="elementId" name="elementId" value=" " ></input>
					<input type="hidden" id="elementType" name="elementType" value=" " ></input>
					<input type="hidden" id="machine" name="machine" value=" "></input>
					<input type="hidden" id="flid" name="flid" value=" "  ></input>
					<input type="hidden" id="elementId" name="" value=""  ></input>
					</div>
				<div id="addEmployeefunLocation" style="width:20%;"></div>
				</div>
	
		</td>
		<td width="20%">
				<div id="addemployeediv">
					<div style="padding-left:0px;">
							<label id="lblAddEmpRole" class="mandatory-lbl" >Role</label>
					</div>
					<span style="padding-left:0px;">
							<input class="easyui-combobox" id="cmbAddEmpRoleId" name="cmbAddEmpRoleId" style="width:180px;" value=""/>
					</span>
			 </div>
		</td>
						
		
	</tr>
	</table>
	</div>
		<div style="padding-top:0px;" class="grdTrainareaLinkDiv" >			
		<table id="grdbachEmployee" ></table>
			<div id="grdbachEmployeePager" >	</div> 
			
	</div>
	
	<div style="padding-top:0px;float:right;margin-right:50%;">
		<span style="padding-left: 30px;">
			<input type="button" id="btnBachSaveEmp" name="btnBachSaveEmp" class="easyui-button" value="Save" style="width: 50px;">
		</span>	    
	</div>	
	
	</div>
	
	
	
</div>
<input type="hidden" id="hdnprogKeyid" name="hdnprogKeyid" value="${requestScope.progKeyid}" />
<input type="hidden" id="hdnBatchkeyId" name="hdnBatchkeyId" value="${requestScope.batchId}" />
<input type="hidden" id="hdnbachDate" name="hdnbachDate" value="${requestScope.bachDate}" />
<input type="hidden" id="hdnBatchFlid" name="hdnBatchFlid" value="${requestScope.flid}" />
<input type="hidden" id="hdnBatchType" name="hdnBatchType" value="${requestScope.type}" />
<input type="hidden" id="hdnSelectAll" name="hdnSelectAll" value="true" />

</form>