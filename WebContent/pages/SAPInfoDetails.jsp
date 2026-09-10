<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> --%>
<script type="text/javascript">
jQuery(document).ready(function(){
	
	initialiseForm("frmSapDtls");
	//jQuery('#cmbOredertype').combobox('setValue','SMT001');	
	// setTimeout(function() {readOnlyFields('cmbOredertype');},1250);         
		
	//if(jQuery('#cmbOredertype').combobox('getValue')!=null || jQuery('#cmbOredertype').combobox('getValue')!=""){	
	//alert('Test');
	// readOnlyFields('cmbOredertype');
	//}
	var bdId =jQuery('#hdnbdkeyId').val();
	
	processGridnew("sapInfo_input.brdn","&bdId="+bdId,"sapInfoGrid","sapinfopager","","dbl","","loadSuccess");
	//alert(2);

	
	
	var docType=jQuery('#hdnrefDocType').val();
	
	fillComboBox("frmSapDtls","cmbOredertype","sapOrderType.sapinfo?docType="+docType);
	 if(docType=="PM"){	
	      jQuery('#cmbOredertype').combobox('setValue','SMT002');
	      jQuery('#snpgrid').width('80%');	
	  }
	   else{
		  jQuery('#cmbOredertype').combobox('setValue','SMT001');	
	   }
	     setTimeout(function() {readOnlyFields('cmbOredertype');},1250);  
	     var fact=null;
	     var plantName=null;
	
	      jQuery("#btnSpareshReplaced").click(function(){
	     	 if(docType=="PM"){		
        	 	plantName = jQuery('#linfrmWOGenFactory > u > b').html();        	 	
        		fact = jQuery("#frmWOGen input[id='factory']").val();
        		
        	}
         else{       		
            	 plantName = jQuery('#linfrmBDMasterFactory > u > b').html();
                 fact = jQuery("#frmBDMaster input[id='factory']").val();
                
	 		}
         //alert(fact);
		  if( plantName == null){
			  alert("Select Factory");			  
			  return ;
		  }	  	
		  var str = "plantName=" + escape(plantName.trim()) +"&factId="+fact;

   	  LoadPopUp("divSAPSpares","Sapstackinformation_input.sapinfo?"+str, true,"92%","95%","-10px","2%", "","Spares");
      }); 

/*
	      function chkFormatter(id, options, rowObject)
	      {
	      	var id = options.rowId;
	        	return '<input id="chkIndividual" name="chkIndividual" type="checkbox"  onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	      } */
	   
	 //jQuery('#cmbOredertype option:contains("BREAKDOWN MAINTENANCE ORDER-PM01")').prop('selected',true);

		if(jQuery('#txtSparse').val()!='Y'){
		// && jQuery('#hdnbdkeyId').val()!=null ||jQuery('#txtRefDocId').val()!=null){
			//alert(jQuery('#hdnbdkeyId').val()+'----------bdkeyid');
			jQuery('#btnSpareshReplaced').prop('disabled',true);//show();
		}
		else
			jQuery('#btnSpareshReplaced').prop('disabled',false);//hide(); 			


				 jQuery('#btnSaveInfo').click(function(){
				 var spareSAPData= getSelecteddata();
				 var qty=null;
				 var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');							
					for( var i = 1; i <= allRows.length;i++){
						
					 qty=jQuery('#txtSspmQuantity_'+i).val();
					
					}
					if(qty==0){
						alert('Enter The Quantity');
						}
					else 
					 saveForm("frmSapDtls","updateSparesQty.sapinfo?spareSAPData="+spareSAPData);
					 
				 
				 //alert(spareSAPData);
				// if(jQuery('#cmbOredertype').combobox('getValue')==""){
						
						
					// }
				// else
			 });
				 jQuery('#btnDeleteInfo').click(function(){
				//	 var spareSAPData= getSelecteddata();
				
				var cehckval=jQuery('#hdnCheckSel').val();  
					if(cehckval=="1"){					
					 var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');	
					for( var i = 1; i <= allRows.length;i++){
						var rowData = jQuery("#sapInfoGrid").jqGrid('getRowData',i);
						var keyId = rowData.KeyId;
						var cehckval=jQuery('#hdnCheckSel').val();						
						if(cehckval=="1" && keyId!="" || keyId != null){ 
						    saveForm("frmSapDtls","SparesInfo_delete.sapinfo?keyId="+keyId);
							 }	
						       
							}
					
				}
					else {
							alert("Select The Row To delete");
						 }
					jQuery("#sapInfoGrid").trigger("reloadGrid");
					
				/*					
				alert(cehckval+"val");
				var rowData = jQuery("#sapInfoGrid").jqGrid('getRowData');
									//alert(allRows.length);						
					//var keyId = allRows.KeyId; 
						alert(rowData+"  kk"+ rowData.KeyId);
						for( var i = 1; i <= allRows.length;i++){

						// var row=jQuery("#sapInfoGrid").jqGrid('getDataIDs');							
						//var col =jQuery("#sapInfoGrid").jqGrid('getGridParam','colModel');  
						var rowData = jQuery("#sapInfoGrid").jqGrid('getRowData',i);
						var keyId = rowData.KeyId;
						var kd=jQuery('#KeyId').val(); 
						alert(keyId+"kk  "+kd); 
							}
						
					 var qty=null;
					 var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');							
						for( var i = 1; i <= allRows.length;i++){
							
						 qty=jQuery('#txtSspmQuantity_'+i).val();
						
						}
						alert("24");
						if(qty==0){
							alert('Enter The Quantity');
							}
						else 
						 saveForm("frmSapDtls","updateSparesQty.sapinfo?spareSAPData="+spareSAPData);
						*/
				 });

			    jQuery('#btnRefreshSapInfo').click(function(){					
			    	jQuery("#sapInfoGrid").trigger("reloadGrid");
				} );

			    function delSelecteddata(){

			    	var cehckval=jQuery('#hdnCheckSel').val();
						if(cehckval=="1"){ 
					var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');
					//alert(allRows.length);						
					var jsonArrO ='[';
						for( var i = 1; i <= allRows.length;i++){

							jsonArrO +='{';
						  // var row=jQuery("#sapInfoGrid").jqGrid('getDataIDs');							
						//var col =jQuery("#sapInfoGrid").jqGrid('getGridParam','colModel');  
						var rowData = jQuery("#sapInfoGrid").jqGrid('getRowData',i);
						var keyId = rowData.KeyId; 
						
						//var quantity = jQuery('#txtSspmQuantity_'+i).val();//rowData.Qty;
						//alert("quantity..."+quantity);

						jsonArrO += '"txtSspmKeyId" :"'+keyId+'",';
					//	jsonArrO += '"txtSspmQuantity" :"'+quantity+'"';
						jsonArrO +=   "},";
			
						}
						jsonArrO = jsonArrO.slice(0, -1) + "]"; 
					    jsonArrO = (jsonArrO!= ']'?jsonArrO:"");
							
						return jsonArrO;
				}
			    }

			    function getSelecteddata(){
					var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');
					//alert(allRows.length);						
					var jsonArrO ='[';
						for( var i = 1; i <= allRows.length;i++){

							jsonArrO +='{';
						  // var row=jQuery("#sapInfoGrid").jqGrid('getDataIDs');							
						//var col =jQuery("#sapInfoGrid").jqGrid('getGridParam','colModel');  
						var rowData = jQuery("#sapInfoGrid").jqGrid('getRowData',i);
						var keyId = rowData.KeyId; 
						
						var quantity = jQuery('#txtSspmQuantity_'+i).val();//rowData.Qty;
						//alert("quantity..."+quantity);

						jsonArrO += '"txtSspmKeyId" :"'+keyId+'",';
						jsonArrO += '"txtSspmQuantity" :"'+quantity+'"';
						jsonArrO +=   "},";
			
						}
						jsonArrO = jsonArrO.slice(0, -1) + "]"; 
					    jsonArrO = (jsonArrO!= ']'?jsonArrO:"");
							
						return jsonArrO;
				}
				   var mchId = jQuery('#cmbOredertype').combobox('getValue');
				   
				   
			    function setSelectedIndex(s, v) {
					for ( var i = 0; i < s.options.length; i++ ) {
					if ( s.options[i].text == v ) {
					s.options[i].selected = true;
					return;
					}
					}
					}
					setSelectedIndex(document.getElementById('#cmbOredertype'),"BREAKDOWN MAINTENANCE ORDER");    
				
			  //  var gridData = $('#sapInfoGrid').jqGrid('data');
			 //   for (var i = 0; i < rowCount; i++) {
				  ///       var keyid = gridData[i].KeyId;
			   //      var quantity = gridData[i].Qty;	         
			      //   }
			    });
			    
	function loadSuccess() {
		//alert(1);
    	Numericonly();
	}	
	

	function divSAPSpares_onClose() {
		jQuery("#sapInfoGrid").trigger("reloadGrid");
		return true;
	}
    function quantityFormatter(cellvalue, options, rowObject) {	
		var rowId = options.rowId;
		var formatStr  = '<input id="txtSspmQuantity_'+rowId+'"' ;
		formatStr  += ' type="text" class="easyui-text"   value="'+rowObject[6]+'" style="width:80px;text-align:right; " > ';
		return formatStr;
	}
	
 	function Numericonly(){
	 var allRows = jQuery("#sapInfoGrid").jqGrid('getRowData');							
		for( var i = 1; i <= allRows.length;i++){
			numericTextBox('txtSspmQuantity_'+i);
		}
 	}

			/*function isNumber(evt) {
				alert('Asd');
			    evt = (evt) ? evt : window.event;
			    var charCode = (evt.which) ? evt.which : evt.keyCode;
			    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
			        return false;
			    }
			    return true;
			}
				*/	
				
	
	
    function frmsapstackinformation_successsCallback(result) {
    	jQuery("#sapInfoGrid").trigger("reloadGrid");

       }
		
		function frmSapDtls_beforeSubmit(){
		
			var spareSAPData= getSelecteddata();
 		
			return spareSAPData+"&existDocNumber="+jQuery("#txtExistwoid").val()+"&refDocId="+jQuery("#txtRefDocId").val();
			 }
		
 	function loadSAPSpares_onClose(){

	 return true;
 	}
		
 	
    function Select(id, options, rowObject){
	     // alert("12");
		var rowId = options.rowId;
		
		return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value=""    onclick="if(this.checked){SapchkboxCheck(\''+rowId + '\') ;}else{SapchkboxUnCheck(\''+ rowId +'\')}"/>';
			
		}
function SapchkboxCheck(rowId){

		jQuery('#hdnCheckSel').val("1");
      	jQuery("#sapInfoGrid").setCell(rowId, "hdnchkSel","1");
    }
    function SapchkboxUnCheck(rowId){
		jQuery('#hdnCheckSel').val("0");    	
      	jQuery("#sapInfoGrid").setCell(rowId, "hdnchkSel"," ");
    }
		
</script>
<form Name="frmSapDtls" id="frmSapDtls">
	<div id="Header">
		<span Style="padding-left:230px;margin-top:10px;">
			<label style="padding-right:20px;font-weight:bold;color:red"> SAP Information For</label><span style="color:#3A3226;"><b>${requestScope.machine}</b></span><!--  <Input type="text" size="50" value="${requestScope.machine}" Style="font-weight:normal;color:red;padding-left:20px;"/>-->
		</span>
	<!--  	<div style="padding-left:750px;">
			<svg height="20" width="20" align="right" padding-left="150px;">
   				<circle cx="10" cy="10" r="8" stroke="black" stroke-width="1" fill="#FCF9B0" style="align:right;"/>
 			</svg>
 			<label style="font-weight:bold;">SAP Related Fields</label>
 		</div>-->
		<table > 
			<tr><td><label class="mandatory-lbl">Order Type</label></td><td>
				<input  class="easyui-combobox"  id="cmbOredertype" name="cmbOredertype" style="width:200px;padding-left:5px;" autocomplet="On"/> </Td><Td><label style="padding-left:15px;"> SAP Cost Center</label></Td><td><input type="text" class="easyui-text" name="txtSapCostcenter" id="txtSapCostCenter"  size="30" style="background-color:#FCF9B0;padding-left:5px;" value="${requestScope.BDFormBean.bphmSectionid}"></td><Td><label style="padding-left:15px;">No.Of Capacities Required</label></Td><Td><input type="text" class="easyui-text" id="txtNoCapacities" size="30" class="easyui-text" style="background-color:#FCF9B0;padding-left:5px;"/>
			</td></tr>
			<tr></tr>	
			<tr><td><label >Work Center</label></td><td><input type="text" class="easyui-text" id="txtWorkCenter" name="txtWorkCenter" size="30" style="background-color:#FCF9B0;padding-left:5px;"/></Td><Td><label style="padding-left:15px;"> SAP Functional Location</label></Td><Td><input type="text" id="txtSapFunLocation" name="txtSapFunLocation" size="30" style="background-color:#FCF9B0;padding-left:5px;" class="easyui-text" value="${requestScope.funLoc}"></Td><Td><label style="padding-left:15px;">Normal Duration Of the Activity</label></Td><Td><input type="text"  id="txtNormalDtActivity" size="30" class="easyui-text"style="background-color:#FCF9B0;padding-left:5px;"/></td></tr>
		</table>

	<div style="margin-top:20px">
		<label> No.Of Spares Count:          No.Of Quantities</label>
		<div id="buttons" style="padding-left:500px;margin-top:-20px">
		<input type="button" id="btnDeleteInfo" class="easyui-button" name="btnDeleteInfo" value="Delete"  >
		
		<input type="button" id="btnSaveInfo" class="easyui-button" name="btnSaveInfo" value="Save Info"  >

		<input type="button" id="btnRefreshSapInfo" class="easyui-button" name="btnRefreshSapInfo" value="Refresh SAP Info" style="pading-left:10px;" >
		<input type="button" id="btnSpareshReplaced" class="easyui-button" name="btnSpareshReplaced" value="Spares Information" style="pading-left:10px;">
	</div>
</div>
</div>
	<div id="snpgrid" style="width:100%">  
		<table id="sapInfoGrid" ><tr><td></td></tr></table>
	</div>
		<div id="sapinfopager"></div>

			<input type="hidden" id="txtExistwoid" name="txtExistwoid" value="${requestScope.existWoId }"/>
			<input type="hidden" id="txtRefDocId" name="txtRefDocId" value="${requestScope.refdocId }"/>	 
 			<input type="hidden" id="txtSparse" name="txtSparse" value="${requestScope.spares}"/>
 			<input type="hidden" id="hdnbdkeyId" name="hdnbdkeyId" value="${requestScope.bdkeyid}"/>
 			<input type="hidden" id="hdnCheckSel" name="hdnCheckSel" value=""/>
 		 <input type="hidden" id="hdnrefDocType" name="hdnrefDocType" value="${requestScope.refDocType}"/>
 	
</form>