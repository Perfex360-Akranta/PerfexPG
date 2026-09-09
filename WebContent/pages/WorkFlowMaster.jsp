<script type="text/javascript">

	jQuery(document).ready(function(){
		 initialiseForm("frmworkflow");
		 jQuery('#submitForm').val('frmworkflow');
		 numericTextBox("txtWrkmNoofstage"); 	
		 var keyid=jQuery("#txtWrkmKeyid").val();
		 viewGrid("WorkFlowEntry_input.workflow", 'q=2&keyid='+keyid);		
	});
	
	function viewGrid(url,filterString)
	{		
		var tableCaption = "WORK FLOW DETAILS";		
		processGridnew(url,filterString,"workflowGrid","pager",tableCaption,"","","loadComplete","","");		
	    return true;		
	}
	jQuery("#btnAddRow").click(function(){
		var row = jQuery("#workflowGrid").jqGrid("getDataIDs");
		addRow(row);
	});
	
	function addRow(row)
	{
		 if ( row == null || row == '' || parseInt(row) <= 0) {
			var emptyItem =[{Keyid:" ",check:" ",select:"0",role:" ",rolename:" ",type:" ",typename:" "}];
		 	//var emptyItem =[{txtMrkpKeyid:" ",txtMrkpReviewPoint:" ",txtMrkpCriteriaForScoring:" ",txtMrkpMaxScore:" "}];
			jQuery("#workflowGrid").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++)
					lastRow = row[i];
			var emptyItem =[{Keyid:" ",check:" ",select:"0",role:" ",rolename:" ",type:" ",typename:" "}];
			jQuery("#workflowGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
		 }
	}
	
	jQuery('#btndlgDeleteRp').click( function()
	{	
        removeRecord();	
		jQuery("#workflowGrid").trigger("reloadGrid");	
	});				  
		
	function formatterChkWorkFlow(id, options, rowObject)
	{		
		var rowId = options.rowId;	
		return '<input id="workFlow_checkbox_'+rowId+'" name="workFlow_checkbox_" '+ (rowObject[0]&&id=="2" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){workFlowCheck(\''+rowId + '\');}else{workFlowUnCheck(\''+ rowId +'\')}"/>';
	}

	function workFlowCheck(rowId)
	{
		//alert('check');	
		var wrkRow = jQuery("#workflowGrid").jqGrid('getDataIDs');//	row get data
		var keyIds="";
	 	var rowid = "";
	 	//var r = confirm("Do You Want To Delete?");
		var keyidList="";
	 	for (i = 0; i < wrkRow.length; i++) {
	 		rowid = wrkRow[i];
	 		
	 		var CHEKVal = jQuery("#workflowGrid").jqGrid('getCell', rowid,"select");
	 		//alert("checkval::"+CHEKVal);
	 		if (CHEKVal == '1') {
	 			keyid = jQuery("#workflowGrid").jqGrid('getCell', rowid, "Keyid");
	 			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "") {
		 			if(keyidList!=null)
		 				keyidList=keyidList + ",";
	 				
	 				keyidList=keyidList + keyid ;
	 			}
	 		}
	 	}
		setFormater("workflowGrid","frmworkflow","RoleCombo.workflow?",rowId,"rolename","role","200px",false);	
		setFormater("workflowGrid","frmworkflow","typeCombo.workflow?",rowId,"typename","type","90px",false);			
		jQuery("#workflowGrid").jqGrid('setCell', rowId, 'select', '1'); 
	}

	function workFlowUnCheck(rowId){
		//alert('uncheck');		
		var roleval = jQuery("#workflowGrid").jqGrid('getCell', rowId,"role");
		var typeval = jQuery("#workflowGrid").jqGrid('getCell', rowId,"type");
		//alert('roleval:'+roleval+'typeval:'+typeval);
		removeFormater("workflowGrid","combo",rowId,"","rolename");
		removeFormater("workflowGrid","combo",rowId,"","typename");
		//alert('uncheck');	
		jQuery("#workflowGrid").jqGrid('setCell', rowId, 'select', '0');			 		
	}	

	function removeRecord(keyid) {
	 	var wrkRow = jQuery("#workflowGrid").jqGrid('getDataIDs');//	row get data
	 	var rowid = "";
	 	var r = confirm("Do You Want To Delete?");
		var keyidList="";
	 	for (i = 0; i < wrkRow.length; i++) {
	 		rowid = wrkRow[i];
	 		var CHEKVal = jQuery("#workflowGrid").jqGrid('getCell', rowid,"select");
	 		//alert("checkval::"+CHEKVal);
	 		if (CHEKVal == '1') {
	 			keyid = jQuery("#workflowGrid").jqGrid('getCell', rowid, "Keyid");
	 			//alert(keyid);
	 			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "") {
		 			if(keyidList!=null)
		 				keyidList=keyidList + ",";
	 				
	 				keyidList=keyidList + keyid ;
	 				//alert('keyidList:'+keyidList);
	 			}/*else {
	 				var r = confirm("Do You Want To Remove Row?");
	 				if (r == true)
	 					jQuery("#workflowGrid").trigger("reloadGrid");
	 				else
	 					return false;
	 			}*/
	 		}
	 	}
	 	
	 	if (r == true) {	 					
			processAjaxCalls("detaildelete_remove.workflow", "keyid="+ keyidList, 'remove_successCallBack','remove_errorCallBack');
		} 
		else
			return false;

	 }

	 function remove_successCallBack(result) 
	 {
	 	jQuery("#workflowGrid").trigger("reloadGrid");
	 }	

	 function frmworkflow_successsCallback(result) {	
		 //alert("after save");
		 jQuery("#txtWrkmKeyid").val(result.keyid);
		 //jQuery("#workflowGrid").trigger("reloadGrid");	   
		 viewGrid("WorkFlowEntry_input.workflow", 'q=2&keyid='+result.keyid);
	 }
	 function frmworkflow_deleteSuccessCallback(result){
		 alert(result.successData.msg);	
		 clearForm("frmworkflow");	 
		 jQuery("#workflowGrid").clearGridData();
	}
	 
	 function frmworkflow_beforeSubmit()
	 {	
	 	var errText="";
	 	var errFlg=false;
	     var gridVal=getSelectdRows('workflowGrid');
	     var gridData  = '&wrkdtl='+gridVal;	
	     //alert('gridVal:'+gridVal);     
	     var errText = getFilterValue(gridVal+'&', 'errText');
	  	if(errText==null || errText.trim()==""){		
	  		
	  	}
	  	else{
	  		//alert('errText'+errText);
	  		errFlg=true;	 		
	  	}
	  	
	  	//alert("errFlg"+errFlg);	
	  	if(errFlg==true){
	  		setTimeout(function() {
	  			showCommonErrorMsg(errText);
	  		}, 200);
	  		div_err();
	  		return false;
	  	}
	  	else{
	  		return gridData;
	  	}
	 }			
	 
	 function getSelectdRows(jqGridId) {
	 	//alert('getSelectdRows');
	 	var flg=false;	
	 	var isSelect=false;	
	 	var errMsg="";	
	 	var datarow = jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
	 	var datacol = jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	 	var rowid = "";	 	
	 	var jsonArrO = '[';
	 	for (i = 0; i < datarow.length; i++) {
	 		rowid = datarow[i];
	 		var errFlgRow=false;
	 		var errMsgRow="";
	 		//alert('rowid:'+rowid);
	 		var keyId = jQuery("#"+jqGridId).jqGrid('getCell', rowid,"Keyid"); // Call detail Key Id	
	 		var CHEKVal = jQuery("#"+jqGridId).jqGrid('getCell',rowid, "select");
	 		
	 		if (CHEKVal == "1") {
	 			//alert('rowid:'+rowid);
	 			isSelect=true;										
	 			var roleId = jQuery("#"+jqGridId).jqGrid('getCell', rowid,"role");				
	 			var type = jQuery("#"+jqGridId).jqGrid('getCell', rowid,"type");
	 			
	 			//alert('type:'+type);
	 			if(type == null  ||  type.trim()  == ""){				
	 				if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
	 				errMsgRow=errMsgRow + " Type";
	 				errFlgRow=true;
	 			}
	 			if (errFlgRow==true){
	 				errMsg=errMsg + " Select " + errMsgRow + " in Row " + parseInt(i+1) + "  " ;
	 				flg=true;
	 				for ( var colName in rowid) {
	 					jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#ff8040'});  //#94E031				
	 				}
	 			}	
	 			jsonArrO += '{';
	 			jsonArrO += '"txtWrkdKeyid":"' + keyId + '",';	 			
	 			jsonArrO += '"txtWrkdStage":"' + roleId + '",';
	 			jsonArrO += '"cmbWrkdType":"' + type + '",';
	 			jsonArrO += '},';
	 		}
	 	}

	 	if (jsonArrO != "[")
	 		jsonArrO = jsonArrO.slice(0, -1) + "]";
	 	else
	 		jsonArrO = "";
	 	
	 	//alert('jsonArrO:'+jsonArrO);
	 	if(isSelect==false){
	 		errMsg=errMsg + " Check Atleast One Role " ;
	 		flg=true;
	 	}
	 	if(flg==true){				
	 		return jsonArrO+"&errText="+errMsg; 
	 	}
	 	else{
	 		return jsonArrO+"&errText="; 
	 	}		
	 }
	 	 
		
</script>

<form id="frmworkflow" name="frmworkflow">
      <div id="wrapper" style="width: 70%;margin-left:32%;" align="center" >
           <table>
           	<tr>
              	<td colspan="2">
                    <div class="easyui-paddingbfpx" >
	             		<label  class ="mandatory-lbl">Name</label> 
	               	</div>
		           	<div  class="easyui-paddingbfpx" >
			       		<input type="text" name = "txtWrkmName" id ="txtWrkmName" class="easyui-text"  value="${requestScope.workflowmst.wrkmName}" style="width:320px;"/>			
			        </div>
		    	</td>
		    	</tr>
		    	<tr>
		         <td style="padding-left:0px; "   >
			  		<div class="easyui-paddingbfpx" >
	             		<label  class ="mandatory-lbl">No .of. Stage</label> 
	               </div>
		           <div  class="easyui-paddingbfpx" >
						<input type="text" name = "txtWrkmNoofstage" id ="txtWrkmNoofstage"  class="easyui-text" maxlength="3" value="${requestScope.workflowmst.wrkmNoofstage}" style="width:120px;"/>			
			        </div>
			  	</td>
			  	<td align="right" style="padding-left: 10px;">
					<div>
						<span style="padding-left: 5px;">
							<input type="button" class="easyui-button" id="btnAddRow" value="Add">
						</span>
						<span style="padding-left: 5px;">
							<input type="button" class="easyui-button" id="btndlgDeleteRp" value="Delete">
						</span>
					</div>			  		  
			  	</td>	 					
			  </tr>
 		</table>
 		<div>
			<table id='workflowGrid'>	
				<tr><td></td></tr>
			</table>
			<div id="pager"></div>
		</div>
	</div>          
	<input type="hidden" id="mode" name="mode" value=""/>
	<input id="txtWrkmKeyid" type='hidden' name="txtWrkmKeyid" value="${requestScope.workflowmst.wrkmKeyid}" />
</form>	


