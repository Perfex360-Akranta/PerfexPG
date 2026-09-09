
<script>
jQuery(document).ready(function(){
	initialiseForm('frmInspection');
	jQuery('#submitForm').val('frmInspection');	

	var keyid=jQuery("#hdnInspeKeyid").val();
	
	var url = jQuery('#hiddenUrl').val();
	fillComboBox("frmInspection","cmbResponsibility","employee.commonFilter" );
	formatDateBox('dteDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteDate'); 


	var factId = jQuery("#frmInspection input[id='factory']").val();
	var sectionId = jQuery("#frmInspection input[id='section']").val();
	var cellId = jQuery("#frmInspection input[id='cell']").val();
	var machId = jQuery("#frmInspection input[id='machine']").val();
	var flId = jQuery("#frmInspection input[id='flid']").val();	 

	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flId;
 	//alert("fild length="+flId.length);
	if(flId.length<=0){
		
		loadFunctionalLocation("InspecFunctionalLoc","functionalLoc.hseinsp","InspecFunctionalLocValues","frmInspection",dataStr);	
  	}else{
  	    loadFunctionalLocation("InspecFunctionalLoc","functionalLoc.hseinsp","InspecFunctionalLocValues","frmInspection","&flid="+flId);	
  	}
  	
	processGridnew(url,"&q=1","inspectiongrid","inspectionpager","Statutory Inspection","","","gridLoadComplete");

	setTimeout(function() {
		
	var mode = jQuery("#mode").val();
	//alert("mode"+mode);
	if(mode == "view" || nearmiss == "view"){
		disableForm("frmInspection");
	}
	 } ,650); 
		
});

function gridLoadComplete(){	
		
}

	jQuery("#btnAddNew").click(function(){
		var row = jQuery("#inspectiongrid").jqGrid("getDataIDs");
		addRow(row);
	});
function addRow(row)
{
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnInspecKeyid:" ",cmbInspecActs:" ",txtInspecNonCompilance:" ",txtInspecActionPlan:" ",cmbInspecResponsibility:" ",dteInspecTarget:" ",
			 	cmbInspecStatus:" ",cmbInspecCompletedby:" ",dteInspecCompleteddate:" "}];
		jQuery("#inspectiongrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnInspecKeyid:" ",cmbInspecActs:" ",txtInspecNonCompilance:" ",txtInspecActionPlan:" ",cmbInspecResponsibility:" ",dteInspecTarget:" ",
		 	cmbInspecStatus:" ",cmbInspecCompletedby:" ",dteInspecCompleteddate:" "}];
		jQuery("#inspectiongrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	 }
}

function inspectiongrid_selectRow(rowId)
{
	
	var jqGridId="inspectiongrid";
	jQuery("#cmbInspecStatus"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){
	}});	
	
}

function cmbInspecStatus_inspectiongrid_onSelect(record,rowId){
	callStatus(rowId);
}

function cmbInspecCompletedby_inspectiongrid_onSelect(record,rowId){
	
}
function dteInspecCompleteddate_inspectiongrid_onSelect(record,rowId){
	
}
function dteInspecTarget_inspectiongrid_onSelect(date){
	
}

 function callStatus(rowId){
	 var jqGridId="inspectiongrid";
	 var status=getFieldValue("cmbInspecStatus_inspectiongrid_"+rowId);
		//alert("status1="+status);
		processAjaxCalls('getStatusCheck.hseinsp','rowId='+rowId+'statusVal='+status,'statusVal_OnSuccess','statusVal_OnError');
		var status=getFieldValue("cmbInspecStatus_inspectiongrid_"+rowId);

		if(status != null && status != '' && status != ' ')
		{
			if(status == 'P')
			{
				disableField("frmInspection","cmbInspecCompletedby_"+jqGridId+"_"+rowId);
				disableField("frmInspection","dteInspecCompleteddate_"+jqGridId+"_"+rowId);		
			}
			else
			{
				enableFields("cmbInspecCompletedby_"+jqGridId+"_"+rowId);
				enableFields("dteInspecCompleteddate_"+jqGridId+"_"+rowId);
				
			}
		}
				
		
	 }


function statusVal_OnSuccess(result){	
	var rowId=result.rowId;
	var status=result.statusVal;
}


function frmInspection_beforeSubmit()
{	
	var keyid=jQuery("#hdnInspeKeyid").val();
	//alert(keyid);

	var rowid=  jQuery("#inspectiongrid").jqGrid('getGridParam', 'selrow');

	var cellId = getFieldValue('cell','frmInspection');
	if (cellId =='' || cellId ==' ') {
		popupCommonErrorMsg("Select JH");
		return false;
	}
	
	if(keyid.trim().length>0){
		var r =confirm("Data have been changed, Do you want to proceed?");
		if(r){			
		}
		else
			return false;
	}	
	var formId = jQuery('#submitForm').val();
    if(formId.trim().length>0){
       	var gridData1 = getSelectRows();
      // 	alert("grid true or false "+gridData);
       	if(gridData1==true){
       		var rowid=  jQuery("#inspectiongrid").jqGrid('getGridParam', 'selrow');

       		var gridval=getGridSelectArray('inspectiongrid');  
       	    var gridData  = '&riskdetails='+gridval+"&rowid="+rowid;

       	 return gridData;
        }
       	else{
			return false;
        }
    	   		
     }

  /*  if(gridval.trim().length>0){
       	var gridData = getSelectRows();
       	alert("grid true or false "+gridData);
    	if(gridData==false)
    		return false;	
    alert("grid data "+gridData);
   		return gridData;
     }*/
     else 
        return false;
 
	
}

function getSelectRows(){

	var allRows = jQuery("#inspectiongrid").jqGrid('getRowData');	
	for( var i = 1; i <= allRows.length;i++){
		var isChecked = jQuery("#jqg_inspectiongrid_"+i).is(':checked');
		if(isChecked == true)
		{
			var dteInspecTarget = getFieldValue("dteInspecTarget_inspectiongrid_"+i);
			var dteInspecCompleteddate = getFieldValue("dteInspecCompleteddate_inspectiongrid_"+i);
		}

		if ((dteInspecTarget==null || dteInspecTarget =='' || dteInspecTarget==' ')) { 
			popupCommonErrorMsg(" Select Target Date");
			return false;
		}
		if (dteInspecTarget!=null||dteInspecCompleteddate!=null) { 
			if (targetDateEvt(i)==false) {
				return false;
			}
		}
		
	}
	return true;
}

function targetDateEvt(rowId)
{
	var detectionDate = jQuery('#dteDate').datebox('getValue');
	detectionDate = detectionDate.substr(0,12);
	var currentDate = getServerDateTime();
	var targetDate = getFieldValue("dteInspecTarget_inspectiongrid_"+rowId);
	var Completeddate = getFieldValue("dteInspecCompleteddate_inspectiongrid_"+rowId);
	 var status=getFieldValue("cmbInspecStatus_inspectiongrid_"+rowId);

	 	
	if (detectionDate=="undefined" || detectionDate=="" || detectionDate==" ") { 
		//alert(convertStringToDate(targetDate) < currentDate);
		targetDate=targetDate+"23:59";
		if(convertStringToDate(targetDate) < currentDate)
		{
			alert('Target Date Should Not Lessthan Current Date');
			fillWithCurrentDate("dteInspecTarget_inspectiongrid_"+rowId);
			return false;
		}
		else
		    clearValidationErrorMsg("dteInspecTarget_inspectiongrid_"+rowId);
	}
	else {
		if(convertStringToDate(targetDate) < convertStringToDate(detectionDate))
		{	
			alert('Target Date Should Not Less than Inspection Date');
			fillWithCurrentDate("dteInspecTarget_inspectiongrid_"+rowId);
			return false;
		}
		else if(status=='C'){
			if(convertStringToDate(Completeddate) < convertStringToDate(detectionDate)){

				alert('Completed Date Should Not Less than Inspection Date');
				fillWithCurrentDate("dteInspecCompleteddate_inspectiongrid_"+rowId);
				return false;

				}

			}
			else {
		    clearValidationErrorMsg("dteInspecTarget_inspectiongrid_"+rowId);
		    clearValidationErrorMsg("dteInspecCompleteddate_inspectiongrid_"+rowId);
		}
	}
	return true;
}

function frmInspection_beforeDelete()
{
	
	var keyid=jQuery('#hdnInspeKeyid').val();
	//alert(keyid);
	if(keyid.length>0){
		var r=confirm("Are you sure to Delete?");
		if(r){
			return true;
			}
		else{
				return false;
			}
			return false;		
		}
		
}
function frmInspection_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearFields();
		
}
function clearFields(){
	setFieldValue("txtActivity","");
	setFieldValue("cmbResponsibility","");
	jQuery("#inspectiongrid").clearGridData();
 }



function frmInspection_successsCallback(result)
{ 
	var msg=result.successData.msg;
	
	if(msg=="Data Saved Successfully"){
		var keyid=result.successData.keyId;	
		var status=result.successData.status;
		setFieldValue("hdnInspeKeyid",keyid);
		processGridnew("hseinspecEntry_input.hseinsp?keyid="+keyid+"&status="+status,"&q=1","inspectiongrid","inspectionpager","Statutory Inspection", "","","gridLoadComplete");
		jQuery("#inspectiongrid").trigger("reloadGrid");
			}
	else{
		if (jQuery("#hdnInspeKeyid").val().trim().length>0){
				jQuery("#inspectiongrid").trigger("reloadGrid");
		}  
		else{		
			clearFields();
		}
	}	
}
</script>

<form id="frmInspection">
	<div id="wrapper" style="width:100%;">
		<table>
			<tr>
				<td colspan="4">
					<div id="frmInspectionFuntKeyIds">
						<input type="hidden" id="company" name="cmbInspecccompany" value=""></input>
						<input type="hidden" id="factory" name="cmbInspecFactoryid" value=""></input>
						<input type="hidden" id="section" name="cmbInspecSectionid" value=""></input>
						<input type="hidden" id="cell" name="cmbInspecCellid" value=""></input>
						<input type="hidden" id="machine" name="cmbInspecMachineid" value=""></input>
						<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.sheTlInspectionmst.flid}"></input>
						<input type="hidden" id="elementId" name="cmbelementid" value="${requestScope.sheTlInspectionmst.elementid}"></input>
			  		</div>
			 		<div id="InspecFunctionalLoc"  align="left"></div>	
				</td>
			</tr>
			<tr>
				<td>
				<div><label>Title</label></div>
					<div>
						<textarea id="txtTitle" name="txtTitle" style="resize:none;width:250px;" maxlength="100"  
																		value="${requestScope.sheTlInspectionmst.title}">INSPECTION-</textarea>
					</div>
				</td>
				<td valign="top">
				<div style="padding-top:2px;"><label class="mandatory-lbl">Activity</label></div>
					<div>
						<textarea id="txtActivity" name="txtActivity" style="resize:none;width:250px;" maxlength="100"  
																		value="${requestScope.sheTlInspectionmst.activity}">${requestScope.sheTlInspectionmst.activity}</textarea>
					</div>
				</td>
				<td valign="top">
						<div style="padding-top:2px;"><label class="mandatory-lbl">Members</label></div>
					<div>
						<textarea id="txtMembers" name="txtMembers" style="resize:none;width:200px;" maxlength="100"  
																		value="${requestScope.sheTlInspectionmst.members}">${requestScope.sheTlInspectionmst.members}</textarea>
					</div>
				
				</td>
				<td>
					<table>
					<tr>
						<td>
							<div><label class="mandatory-lbl">Done By</label></div>
							<div>
								<input class="easyui-combobox" id="cmbResponsibility" name="cmbResponsibility" style="width:200px;height:20px;" value="${requestScope.sheTlInspectionmst.responsibility}"/>
							</div>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<div><label class="mandatory-lbl">Date</label></div>
							<div>
							<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 87px;" id="dteDate" name="dteDate" 
								 													value="${requestScope.sheTlInspectionmst.date}"/>
							      	</span>
							</div>
						</td>
						<td align="right" style="padding-top:20px;padding-left:20px;">
							<input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px;"/>	
						</td>
					</tr>
				</table>
				</td>
				<td width="60%">
					<table>
						<tr>
							<td>
							
							</td>
						</tr>
							
					</table>
				</td>
			</tr>			
			<tr>
				<td colspan="4">
					<div>
						<table id="inspectiongrid" ></table> 
						<div id="inspectionpager"></div>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="hdnInspeKeyid" name="hdnInspeKeyid" value="${requestScope.sheTlInspectionmst.inspeKeyid}"/> 		
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" />
</form>
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />
<input type="hidden" id="hdnReportType" name="hdnReportType" value="${requestScope.formType}" />























<!-- 
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


<script>
//jQuery.noConflict();
jQuery(document).ready(function(){
	 var mode = jQuery('#hdnmode').val();
	 var inspcId=jQuery("#hdnInspckeyId").val();
	 
	formatDateBox('dteDate','dd-MMM-yyyy');
	if (jQuery('#dteDate').datebox('getValue').length<3)
		fillWithCurrentDate('dteDate');
	
	fillComboBox("frmInspection","cmbResponsibility","employee.commonFilter");
	fillComboBox("frmInspection","cmbFrequency","frequency.commonFilter");
//	fillComboBox("frmInspection","cmbActivity","activityType_getCol.commonFilter");

	loadFunctionalLocation("frminspecfunloc","functionalLoc.hseinsp","frminspecfunloc","frmInspection");
	
	processGridnew("hseinspec_input.hseinsp","?q=2"+"&gridType=colModelInspectionDtlsPop","list1");
	/*jQuery("#list1").jqGrid({
						datatype:"local",
						colNames:['Frequency','Activity','Responsibility'],
						colModel:[{name:'Frequency',index:'Frequency',editable:false,width:100,sortable:true},
						          {name:'Activity',index:'Activity',editable:false,width:100,sortable:true},
						          {name:'Responsibility',index:'Responsibility',editable:false,width:100,sortable:true},
						          ],
					   rowNum:10,
					   rowList:[5,10,15],
					   rownumbers:true,
					   pager:"#pager1",
					   viewrecords:true,
					   height:150,
					   width:750,
					   loadonce:true,
					   caption:'',
					   
		
	});
	*/
	jQuery("#btnadd").click(function(){
		 var mode = jQuery('#hdnmode').val();
		// alert(mode);
		var inspcId=jQuery("#hdnInspckeyId").val();
		saveForm('frmInspection','hseinspec_Save.hseinsp?q=2&saveMode=ADDINSPC&inspcId='+inspcId+'&mode='+mode);
		
	});
	
	
});
/*jQuery("#btnadd").click(function(){
	var inspcId=jQuery("#hdnInspckeyId").val();
	saveForm('frmInspection','hseinspec_save.hseinsp?q=2&saveMode=ADDINSPC');
	
});*/
function frmInspection_successsCallback(result)
{
	reloadCombo("frmInspection","cmbResponsibility","employee.commonFilter");
	

	processGridnew("hseinspec_input.hseinsp","?q=2&incidentKey="+result.keyId+"&gridType=colModelInspectionDtlsPop","list1","pager1"); //,"","empdblClk","","EmpGridComplete");
	/*if(result.afterSave != null)
	{
		if( result.afterSave == "openPop")
		{
			var dataString = '?q=2';
			if(result.successData.keyId != null)
				dataString += '&inspeckeyid='+result.successData.keyId;
			
		//	saveForm('frmInspection','hseinspec_save.hseinsp?incId='+result.successData.keyId);
			//LoadPopUp("divAddEmployee","SheEmployeeAdd_input.accIncRpt"+dataString, true,"900px","500px","0px","10%", "multiSelectOk_Callback","Incident Employee");
		}

	}*/

}



</script>

<form action="" id="frmInspection" name="frmInspection" method="post">
<div class="main-cntborder" id="hseInspection" style="margin-top:3%;margin-left:15%;width:80%">
	 <table style="margin-left:70px">
		<tr>
			<td valign="top" style="width:50%;">
				<div id="frmInspectionKeyIds">
						<input type="hidden" id="section" name="cmbFactid" value=""></input>
						<input type="hidden" id="section" name="cmbSectionid" value=""></input> 
						<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
						<input type="hidden" id="machine" name="cmbMachineid" value=""></input>
						<input type="hidden" id="section" name="cmbFlid" value=""></input>
							  
				</div>
				<div id="frminspecfunloc" style="margin-top:30px;" "style="width:550px;"></div>
			</td>
		</tr>
		</table>
		<table style="margin-left:70px">
		<tr>
			<td valign="top">
				<div class="easyui-paddingbfpx" style="margin-top:20px">
					<label class="mandatory-lbl">Activity</label>
				</div>
				<div class="easyui-paddingbfpx">
				<textarea id="txtActivity" name="txtActivity" maxlength="500" style="width:200px;height:75px;"></textarea>
				</div>
			</td>
			<td valign="top" style="padding-left:50px">
				<div class="easyui-paddingbfpx" style="margin-top:20px">
					<label class="mandatory-lbl">Frequency</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input class="easyui-combobox" id="cmbFrequency" name="cmbFrequency" style="width:100px;height:20px;"/>
				</div>
			</td>
			<td valign="top" style="padding-left:50px">
				<div class="easyui-paddingbfpx" style="margin-top:20px">
					<label class="mandatory-lbl">Responsibility</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input class="easyui-combobox" id="cmbResponsibility" name="cmbResponsibility" style="width:170px;height:20px;"/>
				</div>
			</td>
			<td valign="top" style="padding-left:50px">
				<div class="easyui-paddingbfpx" style="margin-top:20px">
						<label class="mandatory-lbl">Date</label>
				</div>
				<div class="easyui-paddingbfpx">
					<input id="dteDate" name="dteDate" />
				</div>
			</td>
		<td valign="top" >
				<div style="padding-left:40px;margin-top:90px">
						<input type="button" id="btnadd" name="btnadd" class="easyui-button" value="Add"/>
				</div>
				
			</td>
		
		
		</tr>
						
	</table>
	
	<div style="margin-left:70px;margin-top:20px;">
	<table id="list1"> 
									<tr>
										<td></td>
									</tr>
								</table>
		</div>	
						<div id="pager1"></div>
				 	
</div>
<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.formMode}"/>
<input type="hidden" id="hdnInspckeyId" name="hdnInspckeyId" value=""/>
<input type="hidden" id="hdnFrequnit" name="hdnFrequnit" value=""/>
<input type="hidden" id="hdnDuration" name="hdnDuration" value=""/>
<input type="hidden" id="hdnActive" name="hdnActive" value=""/>
<input type="hidden" id="hdnCreatedby" name="hdnCreatedby" value=""/>
<input type="hidden" id="hdnCreatedon" name="hdnCreatedon" value=""/>
<input type="hidden" id="hdnModifiedon" name="hdnModifiedon" value=""/>
</form>

 -->


<!-- <script type="text/javascript" src="js/jquery.easyui.min.js"></script> -->
<!-- <script>
		/*	jQuery.noConflict();
						jQuery(document).ready(function(){	
							//alert('hi');
					
						jQuery("#list1").jqGrid({
								datatype: "local",
								colNames:[ 'Trade','Job Type','Frequency','Activity','Designation','Responsibility'],
								colModel:[ {name:'aumTrade',index:'Trade',editable:false, width:100},
										   {name:'aumJobType',index:'JobType',editable:false, width:100},
										   {name:'aumFrequency',index:'Frequency',editable:false, width:100},
										   {name:'aumActivity',index:'Activity',editable:false, width:300},
										   {name:'aumDesignation',index:'Designation',editable:false, width:100},
										   {name:'aumResponsibility',index:'Responsibility',editable:false, width:150},
										  ],
   							    data:[
									  {id:"1", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
									  {id:"2", invdate:"2007-10-02",name:"test2", note:"note2",closed:false},
									  {id:"3", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
									  {id:"4", invdate:"2007-10-04",name:"test4", note:"note4",closed:true },
									  {id:"5", invdate:"2007-10-31",name:"test5", note:"note5",closed:false},
									  {id:"6", invdate:"2007-10-01",name:"test1", note:"note", closed:true},
									  {id:"7", invdate:"2007-10-02",name:"test2", note:"note2", closed:false},
									  {id:"8", invdate:"2007-09-01",name:"test3", note:"note3",closed:false},
									  {id:"9", invdate:"2007-10-04",name:"test4", note:"note4", closed:true},
									  {id:"10",invdate:"2007-10-31",name:"test5",note:"note5",closed:false}
									
								  ],		  
								rowNum:50,
								rowList:[5,10,20],
								rownumbers: true,
								shrinkToFit:false,
								//multiselect: true,
								//multikey: "ctrlKey",
								pager: '#pager1', 
								sortname: 'id',
								viewrecords: true,
								sortorder: "asc", 
								caption:'',
								width:950,
								height:250,
								loadonce: true
								
								//ondblclick: function(id){alert("view clicked");
									var dataString = 'f_date_from='+ f_date_from +'&f_date_to='+ f_date_to;
									
									jQuery("#list2").setGridParam({url:'FiveW2HRpt_view.fwh?'+dataString,datatype:'xml'}).trigger('reloadGrid');
									alert(setGridParam());
									 }
						//
								 
					
					}); 
							
				});


		function replace()
		 {
			document.getElementById("griddiv").style.display="none";
			document.getElementById("contentdiv").style.display="block";
		}
		function back() 
		{
			document.getElementById("contentdiv").style.display="none";
			document.getElementById("griddiv").style.display="block";
			
		}
*/
</script>
 -->






<!-- 

<div style="margin: 20px">
		<div style="float: left;padding-right: 180px;">&nbsp</div>
	<div style="float: left;padding-right: 160px">	
		<div style="margin-top: 12px;"><label class="mandatory-lbl">Factory</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Section</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Cell</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
	</div>	
	
	<div style="float: left;">	
		<div style="margin-top: 12px;"><label>Equipment</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label>Assembly</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
		<div style="margin-top: 8px;"><label class="mandatory-lbl">Week</label></div>
		<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 150px;"/></div>
	</div>	
	
	
	
</div>		 -->		<!-- End of Fixed div -->

<!-- 
<div style="clear: both;"></div>

	<div style="float: left;padding-right: 22px;">&nbsp;</div>
	 	<div id="griddiv" style="float: left;margin:0px;">             -->  <!-- Start of Grid -->
			
<!-- 				<div  style=" width : 1100px;padding-top:5px;">
					<span style="padding-right: 5%;">&nbsp;</span>
					<span class="notes" style="padding-right: 392px;margin-top: 2px;padding-top:5px;">	Double Click on the Activity to edit the Standard</span>
					<span>	<input type="button" value="New Standard" id=newstand" class="easyui-button" onclick="replace()" /></span>
					<span>	<input type="button" value="View" id="view" class="easyui-button" /></span>
					<span>	<input type="button" value="Export to Excel" class="easyui-button" id="exportexcel" /></span>
					</div>
					
					<div><table id="list1" width="400px" style="float: left;"></table> </div>
							<div id="pager1"></div>  
			
				</div> <!-- End Of Grid -->
									
			
	<!-- 		
				

		<div class="" id="contentdiv" style="margin-top: 12px;margin: 12px;display:none;">
				<table class="sub-cntborder">
					<tr>
						<td class="valigncnt" style="width:50%">
				
					<div class="floatright">
							<div style="margin-top: 8px;">
									<input type="button" id="back" value="Back" class="easyui-button" onclick="back()" style="height: 22px; width : 66px;" />
							</div>
					</div>
				
				<div style="float: left;padding-left: 95px;">&nbsp;</div>
				<div class="div-border" style="float: left;margin-top: 12px;padding-right: 13%">
				  <div style="margin:15px;padding-left: 15%">
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Job Type</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 180px;"/></div>
						<div style="margin-top: 8px;"><label>Equip. Condition</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 180px;"/></div>
						<div style="margin-top: 8px;"><label>No of Year</label><label class="notes">(s)</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-text"  style="width: 120px;height: 21px;"/></div>
						<div style="margin-top: 8px;"><label>How Much</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-text"  style="width: 120px;height: 21px;"/><label class="notes">(Duration in minutes)</label></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Next Due</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-datebox" style="width: 120px;"/></div>
						<div style="margin-top: 8px;"><label>Effective from</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-datebox" style="width: 120px;"/></div>
						<div style="margin-top: 8px;"><label>Maintenance Section</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Designation</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label>Responsibility</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">What</label><label class="mandatory-lbl notes">(Activity)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
				  </div>
				</div><!-- left side -->
				
		<!-- 		<div class="div-border" style="float: left;margin-top: 12px;">
				   <div style="margin:12px;">
						
						<div style="margin-top: 8px;"><label class="mandatory-lbl">How Method</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea>
							 <input type="button" class="easyui-button" value="..." style="height: 21px; width : 42px;"/>
						</div>
						<div style="margin-top: 8px;"><label>Where</label><label class="notes">(Location)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label>What</label><label class="notes">(Standard)</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label>Observation</label></div>
						<div style="margin-top: 5px;"><textarea style="width : 300px; height : 90px;resize:none" name="whatactvty" id="whatactvty" cols="6" rows="5"></textarea></div>
						<div style="margin-top: 8px;"><label class="mandatory-lbl">Prepared By</label></div>
						<div style="margin-top: 5px;"><input type="text" class="easyui-combobox" style="width: 300px;"/></div>
                   </div>				
				</div><!-- right side -->
					
<!-- 				</td>
			</tr>
		</table>
		</div>
	 -->
	
<!--




</div> Outermost div  

</div>-->