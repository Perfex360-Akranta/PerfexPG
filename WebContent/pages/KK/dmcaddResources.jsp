<script type="text/javascript">
jQuery(document).ready(function(){
	
	var flid = jQuery("#frmProject input[id='flid']").val();
	var dataStr = "&flid="+flid+"&addEmpResource=Y&disable=N&enable=LCN";
	loadFunctionalLocation("AddbachEmployeefun","resource_functionalLoc.prpo","DivAddbachEmployeefun","frmAddbachEmployee",dataStr);
	fillComboBox("frmProject","cmbaddresurceRole","Rolecombo.mom");
	
	//alert(jQuery("#hdnTemp").val());
    jQuery('#btnResourcesAdd').click(function(){
    	//saveForm("frmAddbachEmployee","entAddEmployee_save.entbatch");
    	
    	addResourcesEmp();
    });

    jQuery('#btnsave').click(function(){
    	
    	addjhmemberResourcesEmp();
    });

    jQuery('#btndelete').click(function(){
    	
    	removeRecord();
    	
    });		 


     //var role=getFieldValue('cmbaddresurceRole');
	 //processGridnew("dmcaddjhmemberResourcesList_input.prpo","?q=2&role="+role+"&flid="+flid,"gridaddjhResourecesEmp","gridaddjhResourecesEmpPager");
	 
});

function frmAddbachEmployee_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmAddbachEmployee","440px");

    setFieldValue("hdnflid",result.flId);
    //alert("location="+result.locn);	
	var upID = jQuery('#hdnUniquePositionId').val( );
	var kzpmKeyid = jQuery("#hdnKzpmKeyid").val();

    //processGridnew("addResourcesList_input.prpo","?q=2&kzpmKeyid="+kzpmKeyid+"&flid="+result.flId,"gridResourecesEmp","gridResourecesEmpPager","","","","emploadComplete"," ");	
	processGridnew("dmcaddResourcesList_input.prpo","?q=2&kzpmKeyid="+kzpmKeyid+"&flid="+result.flId,"gridResourecesEmp","gridResourecesEmpPager");

	processGridnew("dmcaddjhmemberResourcesList_input.prpo","?q=2&jhmembers=jhmembers&kzpmKeyid="+kzpmKeyid+"&flid="+result.flId+"&allepmloyes=No","gridaddjhResourecesEmp","gridaddjhResourecesEmpPager");
}
function frmProjectcmbaddresurceRole_onSelect(record)
{
	 var flid = jQuery("#frmAddbachEmployee input[id='flid']").val();
	 var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
	 
	 processGridnew("dmcaddjhmemberResourcesList_input.prpo","?q=2&role="+record.id+"&flid="+flid+"&saveKzpmKeyid="+hdnKzpmKeyid+"&allepmloyes=No","gridaddjhResourecesEmp","gridaddjhResourecesEmpPager");
}
function addResourcesEmp(row,check)
{
	
	var jqGridIdResource="resourcesgrid";
	var jqGridId;
	if(check.trim().length>0)
		 jqGridId="gridaddjhResourecesEmp";
	else
	     jqGridId="gridResourecesEmp";
    
	var allRowsEmp = jQuery("#"+jqGridId).jqGrid('getRowData');
	var lastRow="";
	var jsonArrO='[';
	var flg=false;	
	if(allRowsEmp.length>0){
		for( var i = 0; i < allRowsEmp.length;i++){
			var rowId=parseInt(i)+1;	
			var empName = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'empName');	
			var empKeyId = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKprlEmpmKeyid');
			if(check.trim().length=0)
			     var keyId = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKprlKeyid');
		     
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true && (keyId.trim().length<=0)){	
				flg=true;
				jsonArrO+= '{';
				jsonArrO += '"txtKprlKeyid":"",';
				jsonArrO += '"hdnIsDelete":"N",';
				jsonArrO += '"txtKprlEmpmKeyid":"'+empKeyId+'",';
				jsonArrO += '"txtKprlLeadMemb":"M",';
				jsonArrO += '"txtKprlRoleKeyid":"Member",';
				jsonArrO += '"txtKprlHrsestimate":"1"';
				jsonArrO+= '},';
				/*var allRowsResource = jQuery("#"+jqGridIdResource).jqGrid('getRowData');			
				if(allRowsResource.length==0){
					lastRow=1;
					var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:empKeyId,cmbEmpName:empName,txtKprlLeadMemb:"M",cmbLeadMemb:"Member",txtKprlRoleKeyid:" ",txtKprlHrsestimate:"1"}];
					jQuery("#"+jqGridIdResource).jqGrid('addRowData',lastRow, emptyItem[0]);
					//jQuery("#"+jqGridIdResource).setSelection(allRowsResource[lastRow], true);
					//jQuery("#jqg_"+jqGridIdResource+"_"+lastRow).attr('checked',true);
				}
				else{
					
					lastRow=allRowsResource.length;
					var emptyItem =[{txtKprlKeyid:" ",hdnIsDelete:"",txtKprlEmpmKeyid:empKeyId,cmbEmpName:empName,txtKprlLeadMemb:"M",cmbLeadMemb:"Member",txtKprlRoleKeyid:" ",txtKprlHrsestimate:"1"}];
					jQuery("#"+jqGridIdResource).jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
					//jQuery("#"+jqGridIdResource).setSelection(allRowsResource[lastRow], true);
					//jQuery("#jqg_"+jqGridIdResource+"_"+parseInt(lastRow)+1).attr('checked',true);
				    //alert(1);
				}	*/
				//var projectResourcesc=saveResources();
				//alert(projectadmaic);
				//alert("projectResourcesc"+projectResourcesc);
				
			}		
		}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		var gridData  = '&resourcedtls='+jsonArrO;
		if(flg==true){
			var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
			processAjaxCalls("resource_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+gridData,"resourcesSaveSuccess");
		}
	}
	closePopUpDialoge("divAddResource");
}
function resourcesSaveSuccess(result) {
    alert(" Data Saved Successfully ");
	ReloadJhMemberGrid();
	jQuery("#gridaddjhResourecesEmp").trigger("reloadGrid");
	jQuery("#gridResourecesEmp").trigger("reloadGrid");
}

function ReloadJhMemberGrid(){

	var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
	var flid = jQuery("#frmProject input[id='flid']").val();
	var role=getFieldValue('cmbaddresurceRole');
	var url = "addjhmemberResourcesList_getData.prpo";
	url += "?&role="+role;
	url =url+"&flid="+flid+"&saveKzpmKeyid="+hdnKzpmKeyid;
  	jQuery("#gridaddjhResourecesEmp").setGridParam({url:url}).trigger('reloadGrid');
}

function remove_successCallBack(result) {
	//alert(result.successData);
	jQuery("#gridResourecesEmp").trigger("reloadGrid");
	jQuery("#gridaddjhResourecesEmp").trigger("reloadGrid");
}

function divAddResource_onClose(){
	jQuery("#resourcesgrid").trigger("reloadGrid");
	return true;
}

function removeRecord(keyid) {
	var r = confirm("Do You Want To Delete?");
	   if(r==true)
		   {
			var jhmemrow = jQuery("#gridResourecesEmp").jqGrid('getDataIDs');//	row get data

			 for (i = 0; i < jhmemrow.length; i++) 
				 {
				 if(jQuery('#jqg_gridResourecesEmp_'+jhmemrow[i]).is(':checked') == true) {
						 var rowId = jhmemrow[i];
				  		 var keyid = jQuery("#gridResourecesEmp").jqGrid('getCell', rowId,"txtKprlKeyid");
				         processAjaxCalls("resource_remove.prpo", "keyid="+ keyid, 'remove_successCallBack','remove_errorCallBack');
					   
			          }
				  }
		   }
	   
	   
   }
       
function addjhmemberResourcesEmp(row)
{
	var jqGridId="gridaddjhResourecesEmp";
	var allRowsEmp = jQuery("#"+jqGridId).jqGrid('getRowData');
	var lastRow="";
	var jsonArrO='[';
	var flg=false;	
	if(allRowsEmp.length>0){
		for( var i = 0; i < allRowsEmp.length;i++){
			var rowId=parseInt(i)+1;	
			var empName = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'empName');	
			var empKeyId = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKprlEmpmKeyid');
		    //var keyId = jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'txtKprlKeyid');		     
			if(jQuery("#jqg_"+jqGridId+"_"+rowId).is(":checked") == true){	
				flg=true;
				jsonArrO+= '{';
				//jsonArrO += '"txtKprlKeyid":"",';
				jsonArrO += '"hdnIsDelete":"N",';
				jsonArrO += '"txtKprlEmpmKeyid":"'+empKeyId+'",';
				jsonArrO += '"txtKprlLeadMemb":"M",';
				jsonArrO += '"txtKprlRoleKeyid":"Member",';
				jsonArrO += '"txtKprlHrsestimate":"1"';
				jsonArrO+= '},';
			}	
		}
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		var gridData  = '&resourcedtls='+jsonArrO;
		if(flg==true){
			var hdnKzpmKeyid = jQuery("#hdnKzpmKeyid").val();
			processAjaxCalls("resource_save.prpo","hdnKzpmKeyid="+hdnKzpmKeyid+gridData,"resourcesSaveSuccess");
		}
	}
	//closePopUpDialoge("divAddResource");
}

/*function emploadComplete(){
	//Bstdkeyid
	var row = jQuery("#gridResourecesEmp").jqGrid('getDataIDs');
	
	 var cm = jQuery("#gridResourecesEmp").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		   var keyId = jQuery("#gridResourecesEmp").jqGrid('getCell',row[i],"txtKprlKeyid");
		   if(keyId.trim().length>0){ 
			  // jQuery('#jqg_gridResourecesEmp_'+row[i]).attr('checked',true);
			    jQuery('#gridResourecesEmp').setSelection(row[i], true);
			    jQuery('input:checkbox[id=jqg_gridResourecesEmp_'+row[i]+']').attr('checked',true);
			    //jQuery("#gridResourecesEmp").jqGrid('setCell',row[i],'selctVal','1');
		   }
	 }
}*/
function bachChkBoxChk(rowId){
	var bachId = jQuery('#hdnKzpmKeyid').val();
	jQuery("#gridResourecesEmp").jqGrid('setCell',rowId,'txtKprlKzpmKeyid',bachId);
	jQuery("#gridResourecesEmp").jqGrid('setCell',rowId,'selctVal','1');
}
function bachChkBoxUnChk(rowId){
	jQuery("#gridResourecesEmp").jqGrid('setCell',rowId,'txtKprlKzpmKeyid'," ");
	jQuery("#gridResourecesEmp").jqGrid('setCell',rowId,'selctVal','0');
}
/**Function for  selecting/Unselecting all Rows**/
function gridResourecesEmp_selectAll(id,status){
	
	for(var i=0; i<id.length; i++){
		if(status)
			bachChkBoxChk(id[i]);
		else
			bachChkBoxUnChk(id[i]);
	}
}
/**End**/
/**Function for  selecting/Unselecting  Row**/
function gridResourecesEmp_selectRow(id){

	if(jQuery('#jqg_gridResourecesEmp_'+id).is(':checked'))
		bachChkBoxChk(id);
	else
		bachChkBoxUnChk(id);
}
function frmAddbachEmployee_beforeSubmit()
{
	var selArray =  jQuery('#gridResourecesEmp').jqGrid('getGridParam', 'selarrrow');

	if( selArray.length == 0 ) {
		alert("Select Employee ");		
		return "";
	}
	else{ 
	  var gridData = '&employee='+JqGridToJsonSelectdRows('gridResourecesEmp','jqg_gridResourecesEmp_','selctVal');
 	  //alert(gridData);
	   return gridData ;
	}
}

function frmAddbachEmployee_successsCallback(result)
{	
	jQuery("#gridResourecesEmp").trigger("reloadGrid");
	closePopUpDialoge("divAddEmp");	

}
jQuery('#chkAllEmployees').click(function() {

	var kzpmKeyid = jQuery("#hdnKzpmKeyid").val();
    var flid = jQuery("#hdnflid").val();
    //processGridnew("addResourcesList_input.prpo","?q=2&kzpmKeyid="+kzpmKeyid+"&flid="+result.flId,"gridResourecesEmp","gridResourecesEmpPager","","","","emploadComplete"," ");	
	processGridnew("dmcaddResourcesList_input.prpo","?q=2&kzpmKeyid="+kzpmKeyid+"&flid="+flid,"gridResourecesEmp","gridResourecesEmpPager");

	processGridnew("dmcaddjhmemberResourcesList_input.prpo","?q=2&jhmembers=jhmembers&kzpmKeyid="+kzpmKeyid+"&flid="+flid+"&allepmloyes=Yes","gridaddjhResourecesEmp","gridaddjhResourecesEmpPager");
	
});

</script>
<form id="frmAddbachEmployee">    
	<div id="frmAddbachEmployeeFuntKeyIds" >						
		<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
		<input type="hidden" id="section" name="cmbsection"  value=""></input>
		<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
		<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
		<input type="hidden" id= "flid"  name= "" value=""/>
		<input type="hidden" id= "elementType"  />
		<div id="AddbachEmployeefun" style=" ">
		</div>
	</div>
	<div style="padding-top:0px;" class="grdTrainareaLinkDiv" >	
	<table>		
	<tr>
	<td valign="top">
	<div>
	<label>Role</label>
	</div>
	<div>
	   <input class="easyui-combobox" id="cmbaddresurceRole" name="cmbaddresurceRole"  style=" width : 260px;"  value=" " />
	   <span style="margin-left:5%;">
	   <input type="checkbox" name="chkAllEmployees" id="chkAllEmployees" value="Y"/>
       <span >
	   	   <label>All Employees</label>
	   </span>
	   </span>
	</div>	
	<div>
       <table id="gridaddjhResourecesEmp" ></table>
	   <div id="gridaddjhResourecesEmpPager" ></div>
	</div>
	</td>		
	<td>
	<div style="padding-left:10px;padding-right:10px;">
		<input type="button" class="easyui-button" id="btndelete" name="btndelete" value="<<" title="Remove" style="height:23px;width:40px;"/>
	</div>
	<div style="padding-left:10px;padding-right:10px;padding-top:10px;">
	<input type="button" class="easyui-button" id="btnsave" name="btnsave" value=">>" title="Add" style="height:23px;width:40px;"/>
	</div>
	</td>		
	<td>
	<div style="padding-top:36px;">
		<table id="gridResourecesEmp" ></table>
			<div id="gridResourecesEmpPager" >	</div>
	</div>
	</td>		
	</tr>		
	</table> 			
	</div>
	<div style="padding-top:10px;float:right;margin-right:47%;"><span style="">
		<input type="button" id="btnResourcesAdd" name="btnResourcesAdd" class="easyui-button" value="Save" style="width: 50px;display:none;"></span>	    
	</div>		
	<input type="hidden" id="txtKprlEmpmKeyid" name="txtKprlEmpmKeyid" value="${requestScope.keyid}" />
	<input type="hidden" id="hdnKzpmKeyid" name="hdnKzpmKeyid" value="${requestScope.kzpmKeyid }"/>
	<input type="hidden" id="hdnflid" name="hdnflid" />
</form>