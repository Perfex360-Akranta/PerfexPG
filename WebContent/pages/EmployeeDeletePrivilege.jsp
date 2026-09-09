<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript"><!--
	jQuery(document).ready(function() {
		initialiseForm("frmempdeleteprv");
		jQuery("#submitForm").val("frmempdeleteprv");
		fillComboBox("frmempdeleteprv","cmbAedpEmpmKeyid","employee.commonFilter");
		fillComboBox("frmempdeleteprv","cmbRole","employeeRole.commonFilter");
		var flid =jQuery("#frmempdeleteprv input[id='flid']").val();
		var url = jQuery("#hiddenUrl").val();
		viewGrid(url,"q=2");
	});
	 function viewGrid(url,filterString){
	   	  processGridnew("EmpRole_input.edp",filterString,"Gridemprole","pager1","","");
	   	  processGridnew("EmpList_input.edp",filterString,"Gridemplist","pager2","","");
		  processGridnew("EmployeeDelPri_input.edp",filterString,"Gridempdelprv","pager","","","","","");
	}
	function frmempdeleteprvcmbRole_onSelect(){
		  var url = jQuery('#hiddenUrl').val();
		  var  filterString="";
		  var roleKeyId =getFieldValue("cmbRole","frmempdeleteprv");
		  filterString=filterString+"&roleId="+roleKeyId;
	    processGridnew("EmpRole_input.edp",filterString,"Gridemprole","pager1","","");
	}

	jQuery("#btnView").click(function(){
			var empid=getFieldValue("cmbAedpEmpmKeyid","frmempdeleteprv");
			if(empid.trim().length>0){
				processAjaxCalls("chkEmpMenuList.edp","&q=2&empid="+empid,"chkEmpMenuList_onsuccessCallBack","chkEmpMenuList_onerrorCallBack","","");
			}
			else{
				var selArray =  jQuery("#Gridempdelprv").jqGrid('getGridParam', 'selarrrow');
				 var selrowid="";
				 var jsonArr='';
				 var jsonArr1='';
			  if(selArray !=null && selArray!=" " && selArray!=""){		
				for(var i=0;i<selArray.length;i++)
				{
					   selrowid=selArray[i];
					   var Menuid =jQuery("#Gridempdelprv").jqGrid('getCell',selrowid,"txtAedpMenunumber");
				   var Criteriasplit= Menuid.split(',');
				 	for(var k=0;k<Criteriasplit.length;k++){
					 	var Keyidval=Criteriasplit[k]; 
					 	var keyvalSplit=Keyidval.split(";");
					 	var keyvalu=keyvalSplit[0];
					 	jsonArr += '"'+keyvalu + '" ,';	
			 	    }
				 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
					jsonArr += ',';
				}
				jsonArr = jsonArr.substring(0,jsonArr.length-1);
			    var menuno=jsonArr;
			    if(menuno!=null)
			    	{	   
			    	 processGridnew("EmpList_input.edp","&q=2&menuno="+menuno,"Gridemplist","pager2","","");
			    	}
			  }
			}
	});
	
	 jQuery("#btnDelete").click(function(){
			var selArray =  jQuery("#Gridemplist").jqGrid('getGridParam', 'selarrrow');
            if(selArray.length==0){
                alert("Select Checkbox To Delete");
                return false;
            }
	var selrowid="";
	 var jsonArr='';
	 var jsonArr1='';
       if(selArray !=null && selArray!=" " && selArray!=""){		
	for(var i=0;i<selArray.length;i++)
	{
		selrowid=selArray[i];
	   var Keyid =jQuery("#Gridemplist").jqGrid('getCell', selrowid,"txtaedpEmpmKeyid");
	  // alert(Keyid);
	   var Criteriasplit= Keyid.split(',');
	 	for(var k=0;k<Criteriasplit.length;k++){
		 	var Keyidval=Criteriasplit[k]; 
		 	var keyvalSplit=Keyidval.split(";");
		 	var keyvalu=keyvalSplit[0];
		 	jsonArr += '"'+keyvalu + '" ,';	
	    }
	 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += ',';
	}	
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
   var DeleteList=jsonArr;
   if(DeleteList!=null)
   	{	   
        processAjaxCalls("AddEmployee_delete.edp" ,"&DeleteList="+DeleteList, " "," ");
         setTimeout(function() {
	    	alert("Data Deleted SuccessFully");
	    	jQuery('#Gridemplist').trigger("reloadGrid");
	       },1000); 
   	}
 } 
		 
	 });

	function chkEmpMenuList_onsuccessCallBack(result){
		   var menu=result.empList;
		   var row=jQuery("#Gridempdelprv").jqGrid('getDataIDs');
			for(var i=0;i<row.length;i++){
			   var menuno=jQuery("#Gridempdelprv").jqGrid('getCell',row[i],"txtAedpMenunumber");
			for(var j=0;j<menu.length;j++){
			var menunumber=menu[j];
			  if(menunumber==menuno){
				   jQuery('input:checkbox[id=jqg_Gridempdelprv_'+row[i]+']').attr('checked',true);
			  }
			  else{ 
			 jQuery('input:checkbox[id=jqg_Gridempdelprv_'+row[i]+']').attr('disabled',false);
			  }
			}
	}
	}
	
	 jQuery("#btnEmployeeAdd").click(function(){
		 	empId=getFieldValue("cmbAedpEmpmKeyid","frmempdeleteprv");
		 	//alert(empId);
		 if(empId.length!=0 && empId!=null)
			 {
		     var paramconvertArr =convertJsonArr();
	  	     saveForm("frmempdeleteprv","AddEmployeeMenu_save.edp?&paramconvertArr="+paramconvertArr+"&empId="+empId);
			 }
	  });	 

		function convertJsonArr(){
			//var allrow =  jQuery("#Gridempdelprv").jqGrid('getGridParam', 'selarrrow');
			var allrow = jQuery("#Gridempdelprv").jqGrid('getRowData');
			//alert("The allrow::"+allrow); 
			/*if(allrow.length==0){
				alert("Select CheckBox");
				return false;
			}*/
			
			var jsonArrO = '';
			var val = "";
			for ( var i = 0; i < allrow.length; i++) {
				var row = allrow[i];
				var rowno = parseInt(i) + 1;
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
					jsonArrO = jsonArrO.slice(0, -1) + '},';
				}
			}
			return '[' + jsonArrO.slice(0, -1) + ']';
		}
	
	function frmempdeleteprv_beforeSubmit(){
		 var gridlist="";
		 var Menucount=0;
		 var empcount=0;
		 var row=jQuery("#Gridempdelprv").jqGrid('getDataIDs');
		
		/* var selArray=jQuery("#Gridempdelprv").jqGrid('getGridParam', 'selarrrow');
		 var selemprolegrd=jQuery("#Gridemprole").jqGrid('getGridParam', 'selarrrow');
	     if(selemprolegrd.length==0){
			 alert("Select Data To Save");
			 return false;
		 }*/
		 for(var i=0;i<row.length;i++){
			  // selrowid=row[i];
		var menuno=jQuery("#Gridempdelprv").jqGrid('getCell',row[i],"txtAedpMenunumber");
	  //  alert(menuno); 
		if(jQuery('input:checkbox[id=jqg_Gridempdelprv_'+row[i]+']').is(':checked')==true){
	                Menucount=Menucount+1;
			       // alert(Menucount);
			}
		 }
		 var emprow=jQuery("#Gridemprole").jqGrid('getDataIDs');
		// alert("EmpRow"+emprow);
		 for(var j=0;j<emprow.length;j++){
			  // selrowid=emprow[j];
		   var empKeyid=jQuery("#Gridemprole").jqGrid('getCell',emprow[j],"txtaedpEmpmKeyid");
		   //alert("The empKeyid::"+empKeyid);
		 if(jQuery('input:checkbox[id=jqg_Gridemprole_'+emprow[j]+']').is(':checked')==true){
			 empcount=empcount+1;
			// alert(empcount);
		 }  
		 }
	     var gridval=getGridSelectArray('Gridempdelprv'); 
		// alert(gridval);
		 var gridval1=getGridSelectArray("Gridemprole"); 
		// alert(gridval1);
	     gridlist="&Modification="+gridval+"&Emplist="+gridval1+"&Menucount="+Menucount+"&employeecount="+empcount;	
		 //alert("Total Grid:"+gridlist);
	     return gridlist;
	}
	
	function frmempdeleteprv_successsCallback(result){
		jQuery("#Gridempdelprv").trigger("reloadGrid");
		jQuery("#Gridemplist").trigger("reloadGrid");
		jQuery("#Gridemprole").trigger("reloadGrid");
	}
   
</script>
<form id="frmempdeleteprv" name="frmempdeleteprv">
          <table>
          <tr>
           <td>
	 			
	 			<div style="margin-left:50px;margin-top:22px;"><label>Employee</label></div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:50px;">
				<input id="cmbAedpEmpmKeyid" name="cmbAedpEmpmKeyid" class="easyui-combobox" style="width:245px;"  />
				
			     <div style="margin-left:258px;margin-top:-25px;">
				 <input type="button" class="easyui-button" value="Add"  id="btnEmployeeAdd"/>
				</div> 
				
				</div> 
				
				<div style="margin-left:550px;margin-top:-44px;"><label>Role</label></div>
				<div class="easyui-paddingbfpx" style="width :311px;margin-left:550px;">
				<input id="cmbRole" name="cmbRole" class="easyui-combobox" style="width:245px;"  />
				 <div style="margin-left:260px;margin-top:-24px;">
				 <input type="button" class="easyui-button" value="View"  id="btnView"/>
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
	 <div style="float:right; margin-left:800px;margin-top:-396px;">
		<table id="Gridemprole">
		</table>
		<div id="pager1"></div>
	
		<div style="margin-left:2%;">
		<table id="Gridemplist">
		</table>
		<div id="pager2"></div>
	    </div>	
	    <div style="margin-left:500px;margin-top:-128px;">
	  	<input type="button" class="easyui-button" value="Delete"  id="btnDelete"/>
	    </div>
	    
	</div>
	</div>
          
          <%-- <div style="margin-left:2%;margin-top:120;right:48%;">
          <span style="margin-left:2%;margin-top:120;right:48%;">
		  <input type="button" class="easyui-button" value="<<"  id='btnshowSaveEmp' style="font-weight:normal;height:21px;"/>
		  </span>
		  </div>
		  
		  <span style="margin-left:2%;top:155;right:48%;">
		  <input type="button" class="easyui-button" value=">>"  id='btnDelSaveEmp' style="font-weight:normal;height:21px;"/>
		  </span> --%>
		  
		  
	<input type="hidden" id="mode" name="mode" value="create" />
	<input type="hidden" id="flid" name="flid" value="${requestScope.requestScope.flid}" ></input>
</form>