<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript"><!--
	jQuery(document).ready(function() {
		initialiseForm("frmMenuRightsAssign");
		jQuery("#submitForm").val("frmMenuRightsAssign");
		fillComboBox("frmMenuRightsAssign","cmbMenu","MenuList.eupl");
		fillComboBox("frmMenuRightsAssign","cmbtpmPillar","TPMMenuPillar.eupl");
		fillComboBox("frmMenuRightsAssign","cmbRole","employeeRole.commonFilter");
		var flid =jQuery("#frmMenuRightsAssign input[id='flid']").val();
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url,"q=2");
	});

	 function viewGrid(url,filterString){
	  processGridnew("NewMenuRightsAssign_input.eupl",filterString,"Gridempdelprv","pager","","","","","GridLoadComplete");

	 }
	function frmMenuRightsAssign_beforeSubmit(){
	    var RoleId=jQuery("#cmbRole").combobox('getValue');
	    //alert(RoleId);
	    var paramJsonArr =convertJsonArr();
		//alert("paramJsonArr"+paramJsonArr);
	    return "&paramJsonArr="+paramJsonArr+"&RoleId="+RoleId;         
	}
 

	function convertJsonArr(){
		var allrow = jQuery("#Gridempdelprv").jqGrid('getRowData');
		var jsonArrO = '';
		var val = "";
		for ( var i = 0; i < allrow.length; i++) {
			var row = allrow[i];
			var rowno = parseInt(i) + 1; 
			//alert("rowno"+rowno)
			if (jQuery('#jqg_Gridempdelprv_' + rowno).is(':checked')) {
				jsonArrO += '{';
				for ( var colName in row) {
					if (row[colName].substring(0, 6) != '<input') {
						jsonArrO += '"' + colName + '":"' + row[colName] + '",';
					} else {
						var x = row[colName].indexOf("id=") + 4;
						var y = row[colName].substring(x);
						var z = y.indexOf('"');
						var cellId = y.substring(0, z);
						if (jQuery("#" + cellId).attr("type") == "checkbox"){
							val = jQuery('#' + cellId).is(':checked') ? 'Y'
									:'N';					  
						} else {
							val = jQuery('#' + cellId).val();
						}
						jsonArrO += '"' + colName + '":"' + val + '",';
					}
				}
				jsonArrO=jsonArrO.replace('&',',');
				//jsonArrO=jsonArrO.replace('undefined','Y');
				jsonArrO = jsonArrO.slice(0, -1) + '},';
				//alert(jsonArrO);
			}
		}
		
		return '[' + jsonArrO.slice(0, -1) + ']';
	}
	
	function GridLoadComplete(){
		//alert("Inside");
		
	   /*  var row = jQuery("#Gridempdelprv").jqGrid('getDataIDs');
	    //////////////////////////////alert("row"+row);
		 var cm = jQuery("#Gridempdelprv").jqGrid("getGridParam", "colModel"); */
		  
		/*  var jqGridId="Gridempdelprv"; */
		 var row = jQuery("#Gridempdelprv").jqGrid('getDataIDs');
    //////////////////////////////alert("row"+row);
	 var cm = jQuery("#Gridempdelprv").jqGrid("getGridParam", "colModel");
	
		 for(var i=0;i<row.length;i++) 
		 {
			
		//	 var Assigned=jQuery("#Gridempdelprv").getCell(rowId, 1).trim();
			// var rowId=parseInt(i);
			 var Assigned = jQuery("#Gridempdelprv").jqGrid('getCell',row[i],"txtAedprole");
	//alert(Assigned);
			if(Assigned=="Y")	 
				for(var j=0;j<cm.length;j++)
		    	 {				
			   		//jQuery("#KZBankGrid").jqGrid('setCell',row[i],"STATUS","",{'color':'#000','font-size':'12px','background-color':'#C0FFC0'});
			   		jQuery("#Gridempdelprv").jqGrid('setCell',row[i],"txtAedprole","",{'color':'#000','font-size':'12px','background-color':'green'});
		    	 }
			// alert("insisde"+Assigned);
			//	alert("inside");
		//	  jQuery("#Gridempdelprv").jqGrid('setCell',rowId,"txtAedprole","",{'background-color':'green'});
				 
			   
		 }
		 
	}
	
	function frmMenuRightsAssign_successsCallback(result){
		
	jQuery("#Gridempdelprv").trigger("reloadGrid");

	}

	jQuery("#btnEmployeeRoleAdd").click(function(){
		 var RoleId= jQuery("#cmbRole").combobox('getValue');
		
		 var RootId=jQuery("#cmbtpmPillar").combobox('getValue');
		 //alert(RootId)
		 var MenuId=jQuery("#cmbMenu").combobox('getValue');
		//alert(MenuId)
		 if(RoleId.trim()=="")
		 {
		 	alert("Select Employe Role");
		 	return false;
		 }
		//  processGridnew("NewMenuRightsAssign_input.eupl",filterString,"Gridempdelprv","pager","","","","","");

		 processGridnew("NewMenuRightsAssign_input.eupl","q=2&RoleId="+RoleId+"&RootId="+RootId+"&MenuId="+MenuId,"Gridempdelprv","pager","","","","GridLoadComplete");
         
	});
	 
   
</script>
<form id="frmMenuRightsAssign" name="frmMenuRightsAssign">
          <table>
          <tr>
           <td>
	 			
	 			<div style="margin-left:50px;margin-top:22px;"><label class="mandatory-lbl">Employee Role</label></div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:50px;">
			<input id="cmbRole" name="cmbRole" class="easyui-combobox" style="width:245px;"  />
				
				
					
			   <!--   <div style="margin-left:258px;margin-top:-25px;">
				 <input type="button" class="easyui-button" value="View"  id="btnEmployeeRoleAdd"/>
				</div> 
				 -->
				</div> 
				
			
				
	 		</td>
	 	
	 		<td>
	 			<div style="margin-left:-50px;margin-top:22px;"><label>Root Menu</label></div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:-50px;">
			<input id="cmbtpmPillar" name="cmbtpmPillar" class="easyui-combobox" style="width:245px;"  />
			</div>
			
			<td>
	 			<div style="margin-left:-50px;margin-top:22px;"><label>Menu Name</label></div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:-50px;">
			<input id="cmbMenu" name="cmbMenu" class="easyui-combobox" style="width:245px;"  />
			  <div style="margin-left:258px;margin-top:-25px;">
				 <input type="button" class="easyui-button" value="View"  id="btnEmployeeRoleAdd"/>
				</div> 
				
			</div>
	 		</td>
   </table>
   
 <div style="width:100%;position:relative;">
     <div style="float:left; margin-left:50px;">
		<table id="Gridempdelprv">
		</table>
		<div id="pager"></div>
	</div>
	
	</div>
          
 
		  
		  
	<input type="hidden" id="mode" name="mode" value="create" />
	<input type="hidden" id="flid" name="flid" value="${requestScope.requestScope.flid}" ></input>
</form>