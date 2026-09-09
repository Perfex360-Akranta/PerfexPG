<script>
var qammKeyid = "";
	
	jQuery(document).ready(function(){

		initialiseForm('frmTenHome');
		jQuery('#submitForm').val('frmTenHome');
		var menu =jQuery('#hdnMenu').val();
		viewGrid("","?q&");
		//if("true" != jQuery('#hdnFormMode').val()){
		if (menu=="QAMATRIX") {
			jQuery('#grdDiv').css("margin-left","0%" );
			jQuery('#openQaMAtrix').css('visibility','visible');
		}
		else{
			jQuery('#openQaMAtrix').css('visibility','hidden');
			
		}
		
		jQuery('#openQaMAtrix').click(function(){
			navigateToNextForm("Qamatrixstep_input.tsdi?grid=false"+"&filterButton=false",'QA Matrix');
			
		});
		
		jQuery("#btnApprove").click(function() {
			menu = jQuery('#hdnMenu').val();
			var approvalData = selectedTenSteps();
			if (approvalData==false)
				return false;
		 	//processAjaxCalls("finalApproval_save.tsdi","&approvalData="+approvalData ,'approve_OnSuccess','approve_OnError');
		 	var formId = jQuery('#submitForm').val();
		 	if(formId.length > 0 )
				saveForm(formId,"finalApproval_save.tsdi?&approvalData="+approvalData);
		});

		jQuery('#hdnTenStepCurrentSep').val('');

		jQuery('#hdnSelectedFlid').val(' ');
		jQuery('#hdnSelectedGradeSpec').val(' ');
		jQuery('#hdnSelectedCtqid').val(' ');
		jQuery('#hdnSelectedProcessid').val(' ');
		jQuery('#hdnSelectedDate').val(' ');
		
		
	});
	
	function viewGrid(url, filterStr) {
		var menu =jQuery('#hdnMenu').val();
		var type="";
		if("true" != jQuery('#hdnFormMode').val())
			type="HOME";
		else
			type="FINAL";
		
		var ds ="";
		if(type=="FINAL") {
			jQuery('#btnApprove').css('visibility','visible');
			ds+="&qammKeyid="+glbQamatrixId ;
			ds+="&type="+type;
		}
		else {
			jQuery('#btnApprove').css('visibility','hidden');
			ds+="&type="+menu;
			ds+="&qammKeyid=" ;
		}
		filterStr =  filterStr + ds;
		processGridnew("tenstepsdesign_input.tsdi",filterStr,"hmGrd","hmpager","","hmDblClkFunction","","hmComplete","");

		return true;
	}

	function frmTenHome_successsCallback(result) {
		alert(result.msg);
		navigateToPrevForm("");
	}
	
	function approve_OnSuccess(result) {
		//alert(result.msg);
		//alert('10 Step Process Saved');
		navigateToPrevForm("");
	}

	function selectedTenSteps(){
		
		var grid = jQuery("#hmGrd");
		var colModels = grid.jqGrid("getGridParam", "colModel");
		var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	    
		if( selArray.length <= 0 ){
	    	  alert('Select Approval');
	    	  return false;
	     }
		
		var jsonArr='[';
        var row=grid.jqGrid('getDataIDs');		
		var rowid="";
	 	for(var i=0;i<row.length;i++)
		{
		 	rowid=row[i];
		 	var qammKeyid = jQuery("#hmGrd").jqGrid('getCell',rowid,"qammKeyid");
		 	var approvedBy =jQuery("#cmbQammApprovedby_"+rowid).combobox('getValue');
		 	var approvedDate =jQuery("#dteQammApproveddate_"+rowid).datebox('getValue');
		 	var approveFlag = 'Y'; //jQuery("#cmbApproved_"+rowid).combobox('getValue');
		 	var checkVal =jQuery("#jqg_hmGrd_"+(parseInt(i)+1)).is(':checked');
		 	
		 	if (approvedBy.length<3) {
		 		alert('Select Approved By');
		 		return false;
		 	}
		 	if (fnDateValiation(approvedDate, rowid) ==false)
		 		return false;
		 	
		 	if(true == checkVal){
		 		jsonArr+= '{';
		 		jsonArr += '"txtQammKeyid":"'+qammKeyid+'",';
		 		jsonArr += '"txtQammApprovedflag":"'+approveFlag+'",';
				jsonArr += '"txtQammApprovedby":"'+approvedBy+'",';
				jsonArr += '"txtQammApproveddate":"'+approvedDate+'" ';
				jsonArr+='},';					
			}
		}
	 	
		if (jsonArr=="["){
			jsonArr=="";
			return false;
		}
		else{ 
			jsonArr = jsonArr.slice(0,-1) ;
			jsonArr = jsonArr.slice(0,-2)+ "}]";
			//alert(jsonArr);
			return jsonArr;
		}
	}

	function fnDateValiation(approvedDate, rowid)
	{	
		detectionDate = glbTenStepDate.substr(0,12);
		var currentDate = getServerDateTime();
		var completedDate = approvedDate;
		if (detectionDate=="undefined" || detectionDate=='' || detectionDate==' ') {  
			if(convertStringToDate(completedDate) > currentDate)
			{
				alert('Should Not Exceed Current Date');
				fillWithCurrentDate('dteQammApproveddate_'+rowid);
				return false;
			}
			else
			    clearValidationErrorMsg('dteQammApproveddate_'+rowid);
		}
		else {
			if(convertStringToDate(completedDate) > currentDate)
			{
				alert('Should Not Exceed Current Date');
				fillWithCurrentDate('dteQammApproveddate_'+rowid);
				return false;
			}
			else if(convertStringToDate(completedDate) < convertStringToDate(detectionDate))
			{
				alert('Should Not Less than Detection Date');
				fillWithCurrentDate('dteQammApproveddate_'+rowid);
				return false;
			}
			else
			    clearValidationErrorMsg('dteQammApproveddate_'+rowid);	
		}
		
		return true;
}

	function hmDblClkFunction(id){
		//navigateToNextForm("tenstepspg2_input.tsdi" );
		qammKeyid = jQuery("#hmGrd").jqGrid('getCell',id,"qammKeyid");
		var date = jQuery("#hmGrd").jqGrid('getCell',id,"Date");
		var flid = jQuery("#hmGrd").jqGrid('getCell',id,"flid");
		var title = jQuery("#hmGrd").jqGrid('getCell',id,"Title");
		var ctqId = jQuery("#hmGrd").jqGrid('getCell',id,"ctqId");
		var gradeSpec = jQuery("#hmGrd").jqGrid('getCell',id,"gradeSpecId");
		var processId = jQuery("#hmGrd").jqGrid('getCell',id,"processId");
		var preparedbyId = jQuery("#hmGrd").jqGrid('getCell',id,"PrepreaedId");
		var dataStr = "?grid=false&qammKeyid="+qammKeyid+"&flid="+flid+"&ctqId="+ctqId;
		dataStr+="&date="+date+"&processId="+processId+"&gradeSpec="+gradeSpec;
		dataStr+="&preparedbyId="+preparedbyId+"&title="+title;
		//alert(dataStr);
		var filterString= "&qamKeyid="+qammKeyid;
		
		var menu =jQuery('#hdnMenu').val();
		//alert(menu);
		var currentStatus = jQuery("#hmGrd").jqGrid('getCell',id,"Current_Status");
		jQuery('#hdnTenStepCurrentSep').val(currentStatus);
		if (menu=="QAMATRIX") {
			var currentStatus = jQuery("#hmGrd").jqGrid('getCell',id,"Current_Status");
			if (currentStatus=='' || currentStatus==' ') 
				navigateToNextForm("Qamatrixstep_input.tsdi"+dataStr,'QA Matrix');
		}
		else if (menu=="SELECTION") {
			navigateToNextForm("tenstepApproval_input.tsdi?q=2&menu=SELECTION"+filterString,"Ten Step Proces Selection",null,{"filterString":filterString});
		}
		else if (menu=="APPROVAL") {
			navigateToNextForm("tenstepApproval_input.tsdi?q=2&menu=APPROVAL"+filterString,"Approval",null,{"filterString":filterString});
		}
		else if (menu=="ANALYSIS") {
			var processname = jQuery("#hmGrd").jqGrid('getCell',id,"Process");
			var currentStatus = jQuery("#hmGrd").jqGrid('getCell',id,"Current_Status");
			var preparedId = jQuery("#hmGrd").jqGrid('getCell',id,"PrepreaedId");
			var tenStepDate = jQuery("#hmGrd").jqGrid('getCell',id,"Date");;
			var finalApprovedby = jQuery("#hmGrd").jqGrid('getCell',id,"FinalApprovalBy");;
			if (currentStatus!= "") {
				var dataStr = "?&processid="+processId+"&flid="+flid+"&processname="+processname;
				dataStr+="&qamatrixId="+qammKeyid+"&preparedId="+preparedId+"&tenStepDate="+tenStepDate+"&finalApprovedby="+finalApprovedby;
				dataStr+="&filterButton=false";
				navigateToNextForm("tenstepsLink_input.tsdi"+dataStr,"10step",null,{"filterString":"tenstepsLink_input.tsdi"});
			}
		}
		
			
	}
	
	function chkApproveFormatter(cellval, options, rowObject)	{	
		var rowId = options.rowId;
		var comboBox = "<select id='cmbApproved_"+rowId+"' style='width:100px;' ";
		comboBox+= "name='cmbApproved_"+rowId+"' class='easyui-combobox' >";
		comboBox += "<option 'selected' value='Y'> YES   </option>"; 
		comboBox += "<option  value='N'> NO     </option>"; 
		comboBox += "</select>";		
		return comboBox;
	}	 	
	
	function approvedDateFormatter(cellVal, options, rowObject)
	{
		return '<input id="dteQammApproveddate_'+options.rowId+'" name="dteQammApproveddate_"'+options.rowId+'" style="width:100px;"  class="easyui-datebox" value="'+cellVal+'"/>';
	}
	
	function approvedByFormatter(cellVal, options, rowObject)
	{
		return '<input id="cmbQammApprovedby_'+options.rowId+'" name="cmbQammApprovedby_"'+options.rowId+'" style="width:180px;"  class="easyui-combo" value="'+cellVal+'"/>';
	}
	
	function hmComplete(){	
		formatDateBoxWithGrid("dteQammApproveddate_",'dd-MMM-yyyy');		
		fillComboBoxWithGrid("frmTenHome","cmbQammApprovedby_","employee.commonFilter?flid="+tnStpflid);

		 var row = jQuery("#hmGrd").jqGrid('getDataIDs');
			if(row.length<=0){ 
				//navigateToNextForm("tenstepQaMatrix_input.tsdi" );
				return false;
			}
		 var cm = jQuery("#hmGrd").jqGrid("getGridParam", "colModel");
		 
		 for(var i=0;i<row.length;i++)
		 {
			 
			 //for(var j=0;j<cm.length;j++)
	     	 //{
				 var approvedDate =jQuery("#dteQammApproveddate_"+row[i]).datebox('getValue');
				 if (approvedDate=='31-Dec-2100')
				 	fillWithCurrentDate('dteQammApproveddate_'+row[i]);
				 var approvedId = jQuery("#hmGrd").jqGrid('getCell',row[i],"ApprovedId");
				 var isDMTLeader = jQuery("#hmGrd").jqGrid('getCell',row[i],"IsDMTLeader");
				 if( isDMTLeader=="0" || (approvedId!='' && approvedId!= ' ')) {
				 	jQuery("#jqg_hmGrd_1").attr('disabled','disabled');
				 	jQuery("#cmbApproved_1").attr('disabled','disabled');
				 	jQuery("#cmbApproved_1").attr('disabled','disabled');
				 	setTimeout(function() {
				 		jQuery("#cmbQammApprovedby_1").attr('disabled','disabled');
				 		jQuery("#dteQammApproveddate_1").attr('disabled','disabled');
				 		jQuery("#btnApprove").attr('disabled','disabled');
				 		readOnlyFields("#cmbQammApprovedby_1");
						readOnlyFields("#dteQammApproveddate_1");
					},2000);
				 	return false;
				 }
				 else {
					setTimeout(function() {
					 var userId = getFieldValue("userLoginid");
						 setFieldValue("cmbQammApprovedby_1",userId);
				 	},500);
				 }

				 /*var okVal = jQuery("#hmGrd").jqGrid('getCell',row[i],cm[j].name); 
				  if( 'Pending' == okVal){
				  		jQuery("#hmGrd").jqGrid('setCell',row[i],cm[j].name,okVal,{'color':'#000','font-weight':'bold','font-size':'12px','background-color':'yellow'});
				  }
				  else if('Completed' == okVal){
				  		jQuery("#hmGrd").jqGrid('setCell',row[i],cm[j].name,okVal,{'color':'#000','font-weight':'bold','font-size':'12px','background-color':'green'});
				  		
				 }
	     	 }*/
		 }	
	}
	
</script>
<form id="frmTenHome" name="frmTenHome">
<div id='' style="padding: 10px;">
<span style='margin-left:0px;'>
<input type='button' class='easyui-button' value='New' id='openQaMAtrix' style='visibility:hidden;height:21px;'/>
</span>
	
<span style='margin-left:0px;'>
   		<input type='button' class='easyui-button' value='Save' id='btnApprove' style='visibility:hidden;height:21px;'/>
   </span>

	<div id='grdDiv' style="">
		<table id="hmGrd">
			<tr><td></td></tr>
		</table>
		<div id="hmpager"></div>
	</div>
</div>

<input type='hidden' id='hdnFormMode' value='${requestScope.frmMode}'/>
<input type='hidden' id='hdnMenu' value='${requestScope.menu}'/>
</form>