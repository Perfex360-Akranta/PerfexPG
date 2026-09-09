<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Group Creation</title>
</head>
<script>

jQuery(document).ready(function() {
	initialiseForm('frmEmpgroupCreation');
	jQuery('#submitForm').val('frmEmpgroupCreation');
	var url = jQuery('#hiddenUrl').val();
	var mode = "VIEW";
	var type=getFieldValue("txtFormType");
	var pillarId=getFieldValue("cmbMgrmPillarid");
	fillComboBox("frmEmpgroupCreation", "cmbMgrmPillarid", "pillar.commonFilter","",false);
    var flid = jQuery("#frmEmpgroupCreation input[id='flid']").val();
     
    var dataStr = "&flid="+flid;
    loadFunctionalLocation("MgrmBrdfunLocation", "functionalLoc.dashboard", "MgrmBrdfunLocation", "frmEmpgroupCreation",dataStr);
    viewMasterGrid(url,"");
    viewDetailGrid(url,"?q=");
  

});

jQuery("#btnAdd").click(function(){

	saveForm("frmEmpgroupCreation","EmpgroupCreation_save.emp","");
	
})

function viewMasterGrid(url,filterString)
{	
	     var keyid='';
	     var flid = jQuery("#frmEmpgroupCreation input[id='flid']").val();
         filterString += "&active=Y";
    	 filterString=filterString+"&flid="+flid+"&keyid="+keyid;
    	 processGridnew(url,filterString,"viewMasterGrid","viewMasterpager");			

}

function viewDetailGrid(url,filterString)
{	
		var flid = jQuery("#frmEmpgroupCreation input[id='flid']").val();
  		var keyid=jQuery('#txtMgrmKeyid').val();
   		filterString += "&active=Y";
		filterString=filterString+"&flid="+flid+"&keyid="+keyid;
	   	processGridnew("EmpgroupDetailCreation_input.emp",filterString,"viewDetailGrid","viewDetailpager","","doubleClickGrid");			
}


   
function frmEmpgroupCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	setFunctionalLocWidth("frmEmpgroupCreation","550px");
	ReloadMasterGrid();
	//ReloadDetailGrid();
	
}

function frmEmpgroupCreation_successsCallback(result)
{
	 var keyid=result.KeyId;
	 jQuery('#txtMgrmKeyid').val(keyid);
	 jQuery("#hdnPillCode").val(result.successData.pillCode);
	 ReloadMasterGrid();
	 ReloadDetailGrid();
}


function ReloadMasterGrid(){

	var keyid=jQuery('#txtMgrmKeyid').val();
	var flid = jQuery("#frmEmpgroupCreation input[id='flid']").val();
	var url = "EmpgroupCreation_getData.emp";
	url += "?&active=Y";
	url =url+"&flid="+flid+"&keyid="+keyid;
  	jQuery("#viewMasterGrid").setGridParam({url:url}).trigger('reloadGrid');
}
function ReloadDetailGrid(){

	var keyid=jQuery('#txtMgrmKeyid').val();
	var flid = jQuery("#frmEmpgroupCreation input[id='flid']").val();
	var url = "EmpgroupDetailCreation_getData.emp";
	url += "?&active=Y";
	url =url+"&flid="+flid+"&keyid="+keyid;
  	jQuery("#viewDetailGrid").setGridParam({url:url}).trigger('reloadGrid');
}



jQuery("#btnDelete").click(function() {

	removeEmployee();

});

function removeEmployee() {

	   var r = confirm("Do You wanna remove these from Group...!!!");
	   if(r==true)
		   {
	         var employeerow = jQuery("#viewDetailGrid").jqGrid('getDataIDs');//	row get data
			 for (i = 0; i < employeerow.length; i++) 
				 {
				 if(jQuery('#chkSelect_viewDetailGrid_'+employeerow[i]).is(':checked') == true) {
						 var rowId = employeerow[i];
				  		 var keyid = jQuery("#viewDetailGrid").jqGrid('getCell', rowId,"KEYID");
				         processAjaxCalls("EmpgroupCreationMember_remove.emp", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
					   
			          }
				  }
		   }
	   else 
		   {
		   alert("Thanks for keeping them in Group...!!!");
	       return flase;
		   }
}

function remove_successCallBack(result) {
	//alert(result.successData);
	jQuery("#viewDetailGrid").trigger("reloadGrid");
	jQuery("#viewMasterGrid").trigger("reloadGrid");
}
function remove_errorCallBack() {
	alert("row not removed");
}


function frmEmpgroupCreation_beforeSubmit() {

	  var paramrow = jQuery("#viewMasterGrid").jqGrid('getDataIDs');//	row get data
		var jSONArro="[";
	    var jSONArr="";
		for (i = 0; i < paramrow.length; i++) 
			{
			var rowId = paramrow[i];
	      	if (jQuery('#chkSelect_viewMasterGrid_' + paramrow[i]).is(':checked') == true) {
	     		jSONArr +="{";
	     		var dtlkeyid=jQuery("#dtlkeyid").val();
	       	    var keyid = jQuery("#viewMasterGrid").jqGrid('getCell', rowId,"KEYID");
	       	       	jSONArr +='"txtMgrdKeyid":"'+dtlkeyid+'",';
	     	    	jSONArr +='"txtMgrdEmpmKeyid":"'+keyid+'"';
	     	    	jSONArr +="},";
	     	   	}
		}
		jSONArro += jSONArr.slice(0, -1)+"]";
	//	alert(jSONArro);
	 return "&jSONArro="+jSONArro;	
}

function checkEmployeeFormatter(cellValue, options, rowObject) {
	var rowId = options.rowId;
	var colId = options.pos;
	var gridId = options.gid;

	return '<input id="chkSelect_'+gridId+'_'+rowId+'" type="checkbox"  value="">';

}


</script>
<body>
<form name="frmEmpgroupCreation" id="frmEmpgroupCreation">
<div id="" style="padding: 0px;width: 1-">
			<!-- ******Tabs******* -->
			<div class="" style="padding-bottom: px;">

				<div title="Basic" style="padding: 0px;">
					<!-- Basic Div -->
					<div class="easyui-paddingbfpx"
						style="padding-top: 20px; padding-left: 50px">
						<table>
							<tr>
								<td>
									<div>
									<label class="mandatory-lbl">Group Name</label>
									<label class="mandatory-lbl" style="margin-left:555px ">Pillar</label>
									</div>
									<div>
										<input id="txtMgrmName" name="txtMgrmName" class="easyui-text" style="width:560px;" value="${requestScope.GenTlMomGroupmst.mgrmName}" />
										<span style="margin-left:73px ">
										<input id="cmbMgrmPillarid" name="cmbMgrmPillarid" type="text" class="easyui-combo" style="width: 300px;" value="${requestScope.GenTlMomGroupmst.mgrmPillarid}" />
										</span>
									</div>
								</td>
							</tr>
						</table>
						<table>
                       	   <tr>
    						    <td colspan="2" >
    								    <div id="frmEmpGroupFuntKeyIds" style="margin-top:20px;width:550px" >
					
												<input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.factId}"  ></input>
												<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
												<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
												<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.sectId}"  ></input>
												<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
												<input type="hidden" id="machine" name="cmbMachineid" value="${requestScope.mchId}"></input>
<!--												<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.mgrmFlid}"  ></input>-->
				                             	<input type="hidden" id="flid" name="cmbMgrmFlid" value="${requestScope.GenTlMomGroupmst.mgrmFlid}"  ></input>
					
									</div>
                                     	 	  <div  class="easyui-paddingbfpx" id="MgrmBrdfunLocation" style="margin-top:-12px;width:550px; width:550px;"></div>
    											
    										 </td>
    										 <td><div>
									            <label style="margin-left:-95px">Email Id</label></div>
									            <div style="margin-left:-95px">
									            	<input id="txtMgrmEmailid" name="txtMgrmEmailid" class="easyui-text" style="width:260px;" value="${requestScope.GenTlMomGroupmst.mgrmEmailid}" />
									            </div> </td>
   										  </tr>
								
                          
                                    <tr style=" width : 716px;"><td><div colspan="2"  >
                                   				<label>List of All Employee</label>
    											<span Style="margin-left:530px"><label>Group Members</label></span>
    											</div>
                                    </td></tr>
                                    	</table>
                            <table class="easyui-paddingbfpx">
                                 <tr>	
                                    <td>
                                     		<table id="viewMasterGrid" ></table>
		                           			 <div style= "width:400px" id=viewMasterpager></div>
		                           			 </td>
		                           		 <td>
		                            		  <div style="margin-left:10px; margin-right:10px"><input type="button" class="easyui-button" id="btnAdd" value="     >>     " style="height: 23px;" /> </div>
		                            		  <div style="margin-left:10px;margin-top:5px; margin-right:10px"><input type="button" class="easyui-button" id="btnDelete" value="     <<     " style= "height:23px;" /> </div>
		                            	 </td>
		                           		 <td>
		                          			  <table id="viewDetailGrid" style="margin-left:20px"></table>
		                          			  <div style="margin-left:20px" id=viewDetailpager></div>
		                           		 </td>
		                            </tr>
		                            </table>
						</div>
				</div>
			</div>
	</div>	
		 <input type="hidden" id="dtlkeyid" name="dtlkeyid" value="${requestScope.NewGenTlGroupdtl.mgrdKeyid}" />
		 <input type="hidden" id="hiddenurl1" value="EmpgroupDetailCreation_input.emp" name="hiddenurl1" />  	
	     <input type="hidden" id="txtMgrmKeyid" name="txtMgrmKeyid" value="${requestScope.keyid}" />
         <input type="hidden" id="hdnMgrmKeyid" name="hdnMgrmKeyid" value="${requestScope.GenTlMomGroupmst.mgrmKeyid}"  ></input>
	     <input type="hidden" id="hdnPillCode" value="${requestScope.pillarcode }"/>
	     <input type="hidden" id="mode" value="create" name="mode" /> 
</form>
</body>
</html>