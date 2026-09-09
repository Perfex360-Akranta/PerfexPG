<script type="text/javascript">	
jQuery(document).ready(function() {
		initialiseForm('frmMilestone');
		jQuery('#frmMilestone div[id=dispFunctionalLoc]').html(jQuery("#frmProject div[id=dispFunctionalLoc]").html());		
		
		jQuery('#submitForm').val('frmMilestone');
		jQuery('#hdnKmmmFlid').val(jQuery("#frmProject input[id='flid']").val());
		jQuery('#hdnKmmmKzpmKeyid').val(jQuery("#hdnKzpmKeyid").val());
		jQuery('#txtSubCategory').val(jQuery("#txtKzpmProjectno").val());
		jQuery('#txtCategory').val(jQuery("#txtKzpmProjectname").val());
		jQuery('#dteKmmmFromdate').val(jQuery("#dteKzpmStartdate").val());
		jQuery('#dteKmmmTodate').val(jQuery("#dteKzpmEnddate").val());
		
		jQuery('#frmMilestone .easyui-text').css('text-transform', 'uppercase');
		formatDateBox('dteKmmmFromdate','dd-MMM-yyyy');
		formatDateBox('dteKmmmTodate','dd-MMM-yyyy');
		fillComboBox("frmMilestone","cmbKmmmEmpmKeyid","employee.commonFilter");
		
		jQuery("#btnMultiEmp").click(function(){
			LoadPopUp("MultiSelectEmployee","multiEmpSelect_input.api?q=2",true,"40%","88%","1%","14%","","Employee Selection","","",true,"setFileManagerdimension");				
		});
		
		if(jQuery("#hdnKmmmKeyid").val().trim().length<=0){
			loadStagesAdd();			
		}
		else{			
			setFieldValue("cmbKmmmStages",getFieldValue("txtMstStage"));
		}
		var stage=getFieldValue("cmbKmmmStages");
		//alert("stage:"+stage);
		processGridnew('milestone_input.prpo','Keyid='+jQuery("#hdnKmmmKeyid").val()+"&stage="+stage,"milestoneGrid","","","","","milestoneLoad","milestoneError");
		readOnlyFields("cmbKmmmStages");	
		if(jQuery("#hdnMode").val()=="view"){
			disableForm("frmMilestone");
			jQuery('.loadpopuptoolbar').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:90%;"> </div>');
		}	
						
});

function loadStagesAdd(){
	var mStatus=jQuery("#hdnKzpmMeasurestage").val();
	var aStatus=jQuery("#hdnKzpmAnalysestage").val();
	var iStatus=jQuery("#hdnKzpmImprovestage").val();
	var cStatus=jQuery("#hdnKzpmControlstage").val();
	//alert("stage"+stage);
	//alert("mStatus"+mStatus+"aStatus"+aStatus+"iStatus"+iStatus+"cStatus"+cStatus);
	if(mStatus=="-" || mStatus=="P" || mStatus=="I" || mStatus=="R" || mStatus=="E" || mStatus.trim().length<=0 ){
		//alert("mStatus");
		setFieldValue("cmbKmmmStages","M");
	}
	else if(mStatus=="C" && ( aStatus=="-" || aStatus=="P" || aStatus=="I" || aStatus=="R" || aStatus=="E" || aStatus.trim().length<=0)  ){	
		//alert("aStatus");
		setFieldValue("cmbKmmmStages","A");	
	}			
	else if(aStatus=="C" && ( iStatus=="-" || iStatus=="P" || iStatus=="I" || iStatus=="R" || iStatus=="E" || iStatus.trim().length<=0 ) ){
		//alert("iStatus");
		setFieldValue("cmbKmmmStages","I");
	}
	else if(iStatus=="C" && ( cStatus=="-" || cStatus=="P" || cStatus=="I" || cStatus=="R" || cStatus=="E" || cStatus.trim().length<=0 ) ){
		//alert("cStatus");			
		setFieldValue("cmbKmmmStages","C");
	}
}

function frmMilestone_deleteSuccessCallback(result)
{
	alert(result.successData.msg);	
	jQuery("#milestoneGrid").trigger("reloadGrid");
	jQuery("#hdnKmmmKeyid").val("");
	setFieldValue("cmbKmmmEmpmKeyid","");
	setFieldValue("cmbKmmmStatus","");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();		
	//processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");		
	processGridnew("projectsmile_input.prpo","q=2&keyid="+jQuery("#hdnKzpmKeyid").val(),"fourgrid","pagergrid","","doubleClickMile","","load_complete");
}
	
jQuery("#btnAddRow").click(function(){
	var row = jQuery("#milestoneGrid").jqGrid('getDataIDs');
	addRow(row);
});

function openHist(id,key)
{
	 //var key = jQuery("#milestoneGrid").getCell(id, 'Keyid');
	 var dataStr = "?q=2&dtlId="+key;
 	 LoadPopUp("divHistory","history_view.prpo"+dataStr, true,"98%","110%","0%","2px", "History_Callback","History",true);
}
function frmMilestone_beforeSubmit(){
	var errText="";
	var errLevel="";
	var errTemplate="";
	var errFlg=false;
	var gridData = "?q=2&Milestone="+convertToJsonArrForMilestone("milestoneGrid");
	
	//alert("auditTemplate:"+auditTemplate);	
	errTemplate = getFilterValue(gridData+'&', 'errText');
	if(errTemplate==null || errTemplate.trim()==""){
		
	}
	else{	
		errFlg=true;
		if(errTemplate!=null && errTemplate.trim()!=""){
			errText+=",";
		}
		errText+=errTemplate;
	}
	
	
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

function frmMilestone_successsCallback(result){
	//jQuery("#milestoneGrid").trigger("reloadGrid");
	var keyId=result.successData.keyid;
	var stage=getFieldValue("cmbKmmmStages");
	jQuery("#hdnKmmmKeyid").val(keyId);
	//alert("hdnKmmmKeyid:"+jQuery("#hdnKmmmKeyid").val());
	//alert("stage:"+stage);
	processGridnew('milestone_input.prpo','q=2&Keyid='+jQuery("#hdnKmmmKeyid").val()+"&stage="+stage,"milestoneGrid","","","","","milestoneLoad","milestoneError");
	var masterkeyid = jQuery("#hdnKzpmKeyid").val();	
	processAjaxCalls("getWorkFlowStatus.prpo","kznKeyId="+masterkeyid,"workFlowStatusSuccess","");		
	processGridnew("projectsmile_input.prpo","q=2&keyid="+jQuery("#hdnKzpmKeyid").val(),"fourgrid","pagergrid","","doubleClickMile","","load_complete");
}

function convertToJsonArrForMilestone(jqGridId){
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');//	row get data
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var jsonArrO='[';
	var flg=false;	
	var selectFlg=false;	
	var errMsg="";	
	for(var i=0;i<row.length;i++)
		{
		var errFlgRow=false;
		var errMsgRow="";
	 var rowid=row[i];
     var detailKeyid= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"keyid");
     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"selectval");
     if(select=="1"){
    	 selectFlg=true;
        var assignedto = jQuery("#cmbAssigendto_"+rowid).combobox('getValue');
        var date = jQuery("#dteTargetdate_"+rowid).datebox('getValue');
        var milestone = jQuery("#txtMilestone_"+rowid).val();
        var description = jQuery("#txtDescription_"+rowid).val();
        var remarks = jQuery("#txtRemarks_"+rowid).val();
        var status = jQuery("#cmbStatus_"+rowid).val();
        var milestonemstid = jQuery("#"+jqGridId).jqGrid('getCell',rowid,"milestonemstid");
        var keyid;
        if (milestonemstid.trim().length<=0 || milestonemstid.trim()=="undefined" || milestonemstid.trim()==undefined){
            //alert("milestonemstid");
            milestonemstid="-";
        }
        if(detailKeyid.trim().length >=0 && detailKeyid != "undefined" ){
            keyid = detailKeyid;
        }
        else{
        	keyid = "";
        }
        if(milestone.trim().length<=0){				
			if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
			errMsgRow=errMsgRow + " MileStone";
			errFlgRow=true;
		}
		
		if(assignedto.trim().length<=0){				
			errMsgRow=errMsgRow + " Assigned To";
			errFlgRow=true;			
		}
		if( date.trim().length<=0 ){				
			if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}
			errMsgRow=errMsgRow + " Target Date";
			errFlgRow=true;
		}
		if(status.trim().length<=0 ){				
			if (errFlgRow==true){ errMsgRow=errMsgRow + ",";}	
			errMsgRow=errMsgRow + " Status";		
			errFlgRow=true;			
		}
		if (errFlgRow==true){
			errMsg=errMsg + " Enter " + errMsgRow + " in Row " + parseInt(i+1) + "  " ;
			flg=true;
			for ( var colName in row) {
				jQuery("#" + jqGridId).jqGrid('setCell',i+1,colName,'',{'background-color':'#ff8040'});  //#94E031				
			}
		}
       if((milestone.trim().length>0) && (status.trim().length>0) && (assignedto.trim().length>0) && (date.trim().length>0) ) {
			jsonArrO+= '{';
			jsonArrO += '"txtKmmdKeyid":"'+keyid+'",';
			jsonArrO += '"txtKmmdEmpmKeyid":"'+assignedto+'",';
			jsonArrO += '"txtKmmdTargetdate":"'+date+'",';
			jsonArrO += '"txtKmmdDescription":"'+description+'",';
			jsonArrO += '"txtKmmdRemarks":"'+remarks+'",';
			jsonArrO += '"txtKmmdMilestone":"'+milestone+'",';
			jsonArrO += '"txtKmmdStatus":"'+status+'",';
			jsonArrO += '"txtKmmdTempfield1":"'+milestonemstid+'"';
			jsonArrO+= '},';			
		}
     }
    }
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	if(selectFlg==false){
		errMsg="Select Any MileStone Details To Save";
		return jsonArrO+"&errText="+errMsg; 
	}
	if(flg==true){	
		
		return jsonArrO+"&errText="+errMsg; 
	}
	else{
		return jsonArrO+"&errText="; 
	}
}
	function addRow(row)
	{	
		 if ( row == null || row == '' || parseInt(row) <= 0) {	
		 	var emptyItem =[{txtMspdKeyid:" ",txtMspdMilestone:" ",txtMspdTargetdate:" ",chkRevised:" ",dteRevisedTargetDate:" ",dteRevisedTargetDate:" ",txtMspdAssignedto:" ",txtMspdStatus:" ",txtMspdRemarks:" ",btnDeleteMilestone:" ",btnHistoryMilestone:" "}];
			jQuery("#milestoneGrid").jqGrid('addRowData',1, emptyItem[0]);
		 }	
		 else
		 {
			for(var i=0;i<row.length;i++)
					lastRow = row[i];
			
			var emptyItem =[{txtMspdKeyid:" ",txtMspdMilestone:" ",txtMspdTargetdate:" ",chkRevised:" ",dteRevisedTargetDate:" ",dteRevisedTargetDate:" ",txtMspdAssignedto:" ",txtMspdStatus:" ",txtMspdRemarks:" ",btnDeleteMilestone:" ",btnHistoryMilestone:" "}];
			jQuery("#milestoneGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
		 }
	}
	
	function milestoneLoad()
	{		
		var row = jQuery("#milestoneGrid").jqGrid('getDataIDs');
		for(var id = 1; id<=row.length; id++) {	
			var status= jQuery("#milestoneGrid").jqGrid('getCell',id,"Status");
			formatDateBox('dteTargetdate_'+id,'dd-MMM-yyyy');
			formatDateBox('dtedate_'+id,'dd-MMM-yyyy');
			fillComboBoxWithGrid("frmMilestone","cmbAssigendto_"+id,"employee.commonFilter");
		}
			
				
	}
	function txtFormatterMilestone(id,options,rowObject){
		var id = options.rowId;
		var columnid = options.pos;
		var columnName = options.colModel.name;	
		var columnNo=columnName.substring(columnName.indexOf("_")+1);
		if(rowObject[columnid - 1]== undefined || rowObject[1] ==undefined || rowObject[14] ==undefined )
		{ 
			rowObject[columnid - 1] ="";
			rowObject[1] ="";
			rowObject[14] ="";
			//rowObject[columnid + 3] ="";
			
		}
		if(columnName == "Select"){
			return '<input id="chkKmmdcheckbx_'+id+'" name="chkKmmdcheckbx_'+id+'" type="checkbox" ' + 'onclick="if(this.checked){chkboxmstCheck(\''+id + '\',\''+rowObject+ '\');}else{chkboxmstUnCheck(\''+id +'\',\''+rowObject+ '\')}" />';
		}
		else if(columnName == "Milestones"){
			return '<span><span id="txtTextA_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextK_'+ id+'" style="display:none;"> <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtMilestone_'+id +'" name="txtMilestone'+id +'" maxlength="90" style="width:135px;">'+rowObject[columnid-1]+'</textarea></span></span>';
			}
		else if(columnName == "Description"){
			return '<span><span id="txtTextB_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextL_'+ id+'" style="display:none;"> <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtDescription_'+id +'" name="txtDescription'+id +'" maxlength="90" style="width:135px;">'+rowObject[columnid-1]+'</textarea></span></span>';
			}
		else if(columnName == "TargetDate"){
			return '<span><span id="txtTextC_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextM_'+ id+'" style="display:none;"> <input id="dteTargetdate_'+id+'" name="dteTargetdate_'+id+'" style="width:100px;" class="easyui-datebox" value="'+rowObject[columnid-1]+'"/></span></span>';
			}
		else if(columnName == ""){
			return '<span><span id="txtTextD_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextN_'+ id+'" style="display:none;"> <input id="chkKmmcheckbx_'+id+'" name="chkKmmcheckbx_'+id+'" type="checkbox" ' + 'onclick="if(this.checked){chkKmmmboxCheck(\''+id + '\',\''+rowObject[0]+ '\');}else{chkKmmmboxUnCheck(\''+id +'\',\''+rowObject[0]+ '\')}" /></span></span>';
		}
		else if(columnName == "Reviseddate"){
			return '<span><span id="txtTextE_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextO_'+ id+'" style="display:none;"> <input id="dtedate_'+id+'" name="dteRevisedTargetdate'+id+'" style="width:100px;" disabled class="easyui-datebox" value=""/></span></span>';
			}
		else if(columnName == "AssignedTo"){
			return '<span><span id="txtTextF_'+ id+'">'+rowObject[1]+' </span><span id="txtTextP_'+ id+'" style="display:none;"><input type="combobox" class="easyui-combobox" id="cmbAssigendto_'+id +'"  style="width: 135px;text-align:left;"  value="'+rowObject[columnid - 1]+'"  ></span></span>';
		}
		else if(columnName == "Status"){
			
			return '<span><span id="txtTextG_'+id+'">'+rowObject[14]+' </span><span id="txtTextQ_'+ id+'" style="display:none;"> <select   id="cmbStatus_'+id+'" style=width:100px; name="cmbStatus_'+id+'" class="easyui-combobox"><option  value=" ">  </option><option  value="P">Pending</option><option  value="C">Completed</option><option  value="W" >Work In Progress</option><option  value="S" >Short Close</option></select></span></span>';
			
			}
		else if(columnName == "Remarks"){
			return '<span><span id="txtTextH_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextR_'+ id+'" style="display:none;"> <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)"  id="txtRemarks_'+id +'" name="txtRemarks_'+id +'" maxlength="90" style="width:135px;">'+rowObject[columnid-1]+'</textarea></span></span>';
			}
		else if(columnName == "Delete"){
			return '<span><span id="txtTextI_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextS_'+ id+'" style="display:none;"><img id="del_'+id +'" name="del_'+id +'"   src="images/wrong.png"  onclick="delMilestone(\''+id + '\',\''+rowObject[0]+ '\');"/></span></span>';
			}
		else if(columnName == "History"){
			return '<span><span id="txtTextJ_'+id+'">'+rowObject[columnid - 1]+' </span><span id="txtTextT_'+ id+'" style="display:none;"><input id="hist_'+id +'" name="hist_'+id +'" type="button" value="History" class="easyui-button"  onclick="openHist(\''+id + '\',\''+rowObject[0]+ '\');"/></span></span>';
			}
	}
	function chkKmmmboxCheck(id,value){
		
			if(value!="" && value.trim().length>=0 && value !="undefined"){
				
				var date = jQuery("#dteTargetdate_"+id).datebox('getValue');
				jQuery("#dtedate_"+id).datebox('setValue',date);
				jQuery("#dtedate_"+id).datebox("enable");
				
				}
			else{
				jQuery("#dtedate_"+id).datebox('setValue',"");
				jQuery("#dtedate_"+id).datebox("disable");
				}
			
		}
	function chkKmmmboxUnCheck(id,value){
		jQuery("#dtedate_"+id).datebox('setValue',"");
				jQuery("#dtedate_"+id).datebox("disable");
			
		
		}
	function delMilestone(id,keyid){
		var r=confirm("Do You Want To Delete Row?");
		if(keyid!=null && keyid!='undefined' && keyid!=""){
			
			if (r==true)
			{
				processAjaxCalls("milestoneDetail_delete.prpo", "keyid="+keyid, 'remove_successCallBack','remove_errorCallBack');
				jQuery("#milestoneGrid").delRowData(id);
				return true;
			}
			else 
				return false;
		}
		else{
			
			if (r==true){
				jQuery("#milestoneGrid").delRowData(id);
			}
			else
				 return false;
		} 
	}
	function chkboxmstCheck(rowid,values){ //alert("Inside1 ::::: "+rowid);
		var dateCtrl="dteTargetdate_"+rowid;
		//alert("dateCtrl:"+dateCtrl);
		jQuery("#"+dateCtrl).datebox({  	   
		onSelect:function(recordid)
			{
				//alert("dateCtrl:"+dateCtrl);
				isValidMileStoneDate(dateCtrl,rowid);
			} 
		});
	    var arr=[];
	    var array=[];
	    array.push("txtTextA_","txtTextB_","txtTextC_","txtTextD_","txtTextE_","txtTextF_","txtTextG_","txtTextH_","txtTextI_","txtTextJ_");
		arr.push("txtTextK_","txtTextL_","txtTextM_","txtTextN_","txtTextO_","txtTextP_","txtTextQ_","txtTextR_","txtTextS_","txtTextT_");
		var value = values.split(",");
		for(var i=0;i<value.length;i++){
			jQuery("#cmbStatus_"+rowid).val(value[10]);
			}
		showCol(rowid,arr);
		hideCol(rowid,array);
		
		jQuery("#milestoneGrid").jqGrid('setCell',rowid,'selectval','1');
		
	}
	function chkboxmstUnCheck(rowid,value){ //alert("Inside2 ::::: "+rowid);
		
		var arr=[];
		var array=[];
		array.push("txtTextA_","txtTextB_","txtTextC_","txtTextD_","txtTextE_","txtTextF_","txtTextG_","txtTextH_","txtTextI_","txtTextJ_");
		arr.push("txtTextK_","txtTextL_","txtTextM_","txtTextN_","txtTextO_","txtTextP_","txtTextQ_","txtTextR_","txtTextS_","txtTextT_");	
		showCol(rowid,array);
		hideCol(rowid,arr);
		jQuery("#milestoneGrid").jqGrid('setCell',rowid,'selectval','0');
			 	
	}

	function showCol(rowid,arr){
		 for(var i=0;i<=arr.length;i++){
			jQuery('#'+arr[i]+rowid).show();
			jQuery('#'+arr[i]+rowid).on(' click keydown', function(e){
			    e.stopPropagation();
			});
		 }
	}
	function hideCol(rowid,array){		 
	    var id=parseInt(rowid);		
		 for(var i=0;i<=array.length;i++){//alert("Inside hideCol show ::::for "+array[i]+id);
			 jQuery('#'+array[i]+id).hide();
		}
	}
	
	function milestoneGrid_selectRow(rowId)
	{	
		var dateCtrl="dteTargetdate_"+rowId;
		jQuery("#"+dateCtrl).datebox({  	   
		onSelect:function(recordid)
			{				
				isValidMileStoneDate(dateCtrl,rowId);
			} 
		});
	}
	
	function isValidMileStoneDate(dateCtrl,ctrlRowId){
		var targetDate = getFieldValue(dateCtrl);	
		var currentDate = getServerDateTime();	
		var fromDate = jQuery("#dteKmmmFromdate").datebox("getValue");
		var toDate = jQuery("#dteKmmmTodate").datebox("getValue");
		if (targetDate=="undefined" || targetDate=="" || targetDate==" ") {  
			if(convertStringToDate(targetDate) > currentDate)
			{
				alert('Should Not Exceed Current Date');
				fillWithCurrentDate(dateCtrl);
				return false;
			}
			else{
			    clearValidationErrorMsg(dateCtrl);
		    	return false;
			}
		}
		else{
			if(convertStringToDate(targetDate) < currentDate)
			{
				alert('Target Date Should be greater than Current Date');
				//fillWithCurrentDate(dateCtrl);
				setFieldValue(dateCtrl,toDate);
				return false;
			}
			if(convertStringToDate(targetDate) <convertStringToDate(fromDate))
			{
				alert('Target Date Should Not Less Than Project Start Date');
				//fillWithCurrentDate(dateCtrl);
				setFieldValue(dateCtrl,toDate);
				return false;
			}
			if(convertStringToDate(targetDate) >convertStringToDate(toDate))
			{
				alert('Target Date Should Not Exceed Project End Date');
				//fillWithCurrentDate(dateCtrl);
				setFieldValue(dateCtrl,toDate);
				return false;
			}
			
		}
		
		var jqGridId="milestoneGrid";
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		if(allRows.length>0){
			for( var i = 0; i < allRows.length;i++){
				var rowId=parseInt(i)+1;				
				var prevTargetDate = getFieldValue("dteTargetdate_"+rowId);//jQuery("#"+jqGridId).jqGrid('getCell', rowId, 'TargetDate');	
				//alert("prevTargetDate:"+prevTargetDate);				
				prevTargetDate=prevTargetDate.replace(" 00:00:00","").trim();	
				if(parseInt(ctrlRowId)>parseInt(rowId)){
					if(convertStringToDate(targetDate) < convertStringToDate(prevTargetDate))
					{
						alert('Target Date Should Not Less than Previous Targret Date');
						fillWithCurrentDate(dateCtrl);
						return false;
					}
				}
							
			}
		}
		
	}
		
</script>
<form id="frmMilestone" name="frmMilestone">
	<div style="padding-left:5px;margin-top: -5px;">
		<label class="mandatory-lbl" > Function Location </label>
		<div id="dispFunctionalLoc" style="width:90%;"  class="easyui-paddingbfpx"> 	</div>
	</div>
	<div style="padding-left:5px;padding-top: 5px;">	
		<span style="padding-left:1px;"><label class="mandatory-lbl">Project Name </label></span>
		<span style="padding-left:121px;"><label class="mandatory-lbl">Project No. </label></span>
		<span style="padding-left:20px;"><label class="mandatory-lbl">Stages</label></span>
		<span style="padding-left:63px;"><label class="mandatory-lbl">From Date </label></span>
		<span style="padding-left:42px;"><label class="mandatory-lbl">To Date </label></span>		
		<span style="padding-left:62px;"><label class="mandatory-lbl">Responsibility </label></span>	
		<span style="padding-left:112px;"><label>Status </label></span>					
	</div>
	<div class="easyui-paddingbfpx" style="padding-left:5px;">
	   	<input type="text" class="easyui-text" id = "txtCategory" name="txtCategory"  disabled value="" style="width:200px;background-color:#ece9d8;font-weight:bold;"/>
	   	
	    <span  style="padding-left:1px;">	    
	    	<input type="text" class="easyui-text" id = "txtSubCategory" name="txtSubCategory"  disabled value="" style="width:70px;background-color:#ece9d8;font-weight:bold;"/>
	    </span>

	    <span  style="padding-left:1px;">
	    	<select id="cmbKmmmStages" class="easyui-combobox" name="cmbKmmmStages"   style="/* height: 22px; */width:110px;" value="${requestScope.mile.kmmmStages}" >
						<!--<option value="D">Define </option> -->
						<option value="M">Measure</option>	
						<option value="A">Analyse</option>							
						<option value="I">Improve</option>
						<option value="C">Control</option>
				</select>
	    </span>
	    <span  style="padding-left:1px;">
			<input id="dteKmmmFromdate" name="dteKmmmFromdate" class="easyui-datebox"  style="width:100px;" value="${requestScope.mile.kmmmFromdate}"/>
		</span>
		<span  style="padding-left:1px;">
			<input id="dteKmmmTodate" name="dteKmmmTodate" class="easyui-datebox"  style="width:100px;" value="${requestScope.mile.kmmmTodate}"/>
		</span>
	    <span style="padding-left:1px;">
	    	<input  id="cmbKmmmEmpmKeyid" name="cmbKmmmEmpmKeyid" class="easyui-combobox" style="width:156px;" value="${requestScope.mile.kmmmEmpmKeyid}"/ >	
	    </span>
	    <span style="padding-left:1px;" >
	    	<input type="button" id="btnMultiEmp" class="easyui-button" name="btnMultiEmp" value="..."/>
	    </span>
 		<span style="padding-left:1px;">	
 				<select id="cmbKmmmStatus" class="easyui-combobox" name="cmbKmmmStatus"  style="/* height: 22px; */width:110px;">
						<option value="P">Pending </option>
						<option value="W">Work In Progress</option>	
						<option value="C">Completed</option>							
						<option value="S">Short Close</option>
				</select>       	
	    	
	    </span>
	    <span>
	    <input type="button" id="btnAddRow" class="easyui-button" name="btnMultiEmp" value="Add Milestone"/>
	    </span>
	    
	  
	</div>	
	
	<div class="easyui-paddingbfpx" style="padding-left:5px;padding-top: -5px;">
	<span id="err_dteKmmmFromdate" class="tpm-errormsg" style="padding-left:468px;"></span>
	<span id="err_dteKmmmTodate" class="tpm-errormsg" style="float:left;padding-left:497px; display: block;"></span>    		
		<span id="err_cmbKmmmEmpmKeyid" class="tpm-errormsg" style="float:right;padding-right:253px; "></span>
	</div>		
	<div  style="padding-left:5px;float:left;" id="jqGridMilestone" class="prodPlanDiv">			
		<table id="milestoneGrid" style="float: left;"></table>
		<!--	<div id="mstPlanActualPager"></div>-->
	</div>
	<input type="hidden"  id = "txtMstStage" name="txtMstStage"  value="${requestScope.mile.kmmmStages}" />
	<input type="hidden"  id = "txtMstStatus" name="txtMstStatus"  value="${requestScope.mile.kmmmStatus}" />
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
	<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}">
	<input type="hidden" id="hdnKmmmKeyid" name="hdnKmmmKeyid" value="${requestScope.mile.kmmmKeyid}">
	<input type="hidden" id="hdnKmmmKzpmKeyid" name="hdnKmmmKzpmKeyid" value="">
	<input type="hidden" id="hdnKmmmFlid" name="hdnKmmmFlid" value="">	
</form>   