<script>
jQuery(document).ready(function(){
	initialiseForm('frmHazopCreate');
	jQuery('#submitForm').val('frmHazopCreate');
	readOnlyFields("txtHazopKeyid");
	var url = jQuery('#hiddenUrl').val();
	var factId = jQuery("#frmHazopCreate input[id='factory']").val();
	var sectionId = jQuery("#frmHazopCreate input[id='section']").val();
	var cellId = jQuery("#frmHazopCreate input[id='cell']").val();
	var machId = jQuery("#frmHazopCreate input[id='machine']").val();
	var flid = jQuery("#frmHazopCreate input[id='flid']").val();
	formatDateBox('dteHzomDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteHzomDate');
    readOnlyFields("txthzomKeyid");
	var filterString="";
    var KeyId=jQuery("#hdnkeyId").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
 	loadFunctionalLocation("FieldAuditfunLocation","functionalLoc.fass","FieldAuditfunLocationValues","frmHazopCreate",dataStr);
 	fileManagerPopUp("","FAS","frmHazopCreate","btnFilManage","newHazop");
 	processGridnew(url,"&q=1","HazopGrid","pager","","","","");	
	
});

var mode=jQuery("#mode").val();

function frmHazopCreate_deleteSuccessCallbacks(reslut){
	alert(result.successData.msg);
	jQuery("#HazopGrid").trigger("reloadGrid");	
}

function viewGrid(filterString){
	
	processGridnew("HazopCreate_input.hzop",filterString,"HazopGrid","pager","","","","");
	
}


jQuery("#btnAddwhatif").click(function(){
	var row = jQuery("#HazopGrid").jqGrid("getDataIDs");
	addRowHazop(row);
});

function addRowHazop(row){

	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			 txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
			 cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",cmbMohdResponsibility:" ",Responsibiltyby:" ",dteMohdTarget:" ",cmbMohdStatus:" ",StatusDetail:" ",txtMohdRemarks:" "}];
		jQuery("#HazopGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	 else
	 {
		for(var i=0;i<row.length;i++)
				lastRow = row[i];
		var emptyItem =[{hdnMohdKeyid:" ",txtMohdGuideword:" ",cmbMohdGuideword:" ",txtMohdParameter:" ",txtMohdDeviation:" ",txtMohdCauses:" ",
			txtMohdCosequeces:" ",txtMohdLikeHood1:" ",cmbMohdLikeHood1:" ",txtMohdSeverity1:" ",
		 	cmbMohdSeverity1:" ",txtMohdRisk1:" ",txtMohdWithoutSafeGuards:" ",txtMohdRecommentations:" ",txtMohdLikeHood2:" " ,cmbMohdLikeHood2:" ",txtMohdSeverity2:" ",cmbMohdSeverity2:" ",txtMohdRisk2:" ",cmbMohdResponsibility:" ",Responsibiltyby:" ",dteMohdTarget:" ",cmbMohdStatus:" ",StatusDetail:" ",txtMohdRemarks:" "}];
		jQuery("#HazopGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
		//NewHazopGridLoadCom();
	 }
}

function HazopGrid_selectRow(rowId){
	var jqGridId="HazopGrid";
	jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('setText',txt);
	jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox({onLoadSuccess:function( ){		
		var prob=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
		var sev=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	
		var calps=parseInt(prob)*parseInt(sev);
		
	}});		
}

function cmbMohdLikeHood1_HazopGrid_onSelect(record,rowId){		 
	callHazopPS(rowId);
}
function cmbMohdSeverity1_HazopGrid_onSelect(record,rowId){
	callHazopPS(rowId);
}

function callHazopPS(rowId){
	var prob=getFieldValue("cmbMohdLikeHood1_HazopGrid_"+rowId);
	var sev=getFieldValue("cmbMohdSeverity1_HazopGrid_"+rowId);
	processAjaxCalls('getRiskLevel2.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal2_OnSuccess','riskVal2_OnError');
}

function riskVal2_OnSuccess(result){	
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	var jqGridId="HazopGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId,'txtMohdRisk1',riskVal);
    setFieldValue("#txtMohdRisk1_"+jqGridId+"_"+rowId,riskVal);	
}

function cmbMohdLikeHood2_HazopGrid_onSelect(record,rowId){		 
	callHazop1PS(rowId);
}
function cmbMohdSeverity2_HazopGrid_onSelect(record,rowId){
	callHazop1PS(rowId);
}
function callHazop1PS(rowId){
	var jqGridId="HazopGrid";
	var HazopLikelyhood1=jQuery("#cmbMohdLikeHood1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopLikelyhood2=jQuery("#cmbMohdLikeHood2_"+jqGridId+"_"+rowId).combobox('getText');
	if(HazopLikelyhood2>=0){
	if((HazopLikelyhood1>=HazopLikelyhood2)){
		////////////alert("IF");
		var prob=getFieldValue("cmbMohdLikeHood2_HazopGrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_HazopGrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');
}
	else
	{
	////////////alert("else");
	popupCommonErrorMsg("Severity & Likelyhood should be Less than or Equal to Severity & Likelyhood of Without Safeguards  ");
	return false;	
	}
	}
	var HazopSeverity1=jQuery("#cmbMohdSeverity1_"+jqGridId+"_"+rowId).combobox('getText');
	var HazopSeverity2=jQuery("#cmbMohdSeverity2_"+jqGridId+"_"+rowId).combobox('getText');
	if(HazopSeverity2>=0){
	if(HazopSeverity1>=HazopSeverity2){
		////////////alert(" SevirityIF");
	var prob=getFieldValue("cmbMohdLikeHood2_HazopGrid_"+rowId);
		
		var sev=getFieldValue("cmbMohdSeverity2_HazopGrid_"+rowId);
		
		processAjaxCalls('getRiskLevel3.nmoc','rowid='+rowId+'&riskval=&prob='+prob+'&sev='+sev,'riskVal3_OnSuccess','riskVal3_OnError');	
	} 
		 else
			{
			////////////alert("else");
			popupCommonErrorMsg("Severity & LikelyHood should be Less than or Equal to severity  & Likelyhood of Without Safeguards ");
			return false;	
			}
	}
 
 }
function riskVal3_OnSuccess(result){	
	var rowId=result.rowId;
	var riskVal=result.riskVal;
	////////////////////////alert(riskVal);
	var jqGridId="HazopGrid";
	jQuery("#"+jqGridId).jqGrid('setCell', rowId, 'txtMohdRisk2',riskVal);
    setFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId,riskVal);	
	//var calps=parseInt(prob)*parseInt(sev);
	//var Risk1=getFieldValue("#txtMohdRisk2_"+jqGridId+"_"+rowId);
	var HazopRisk1=jQuery("#HazopGrid").jqGrid('getCell',rowId,"txtMohdRisk1");
	//////////////////////alert("Risk1Risk1"+Risk1);
	var HazopRisk2=jQuery("#HazopGrid").jqGrid('getCell',rowId,"txtMohdRisk2");	
	//////////////////////alert("Risk2"+Risk2);
    if(HazopRisk1==HazopRisk2){
    //////////alert("Risk value Should not greater that Risk Value Without Safeguard");

    }
    
}


function frmHazopCreate_beforeSubmit(){
	 var cellId = jQuery("#frmHazopCreate input[id='cell']").val();
	if(cellId.length==0)
		  {
		     popupCommonErrorMsg("Select JH");
		     return false;
		  } 	 
	var gridval=getGridSelectArray('HazopGrid');   
    var gridData ='&Hazopdetail='+gridval;
    if(gridval.trim().length>0)	
		return gridData;
    

	var Facility=jQuery("#txtHzomFacility").val();
	  if(Facility==null ||Facility.length==0){
		  popupCommonErrorMsg("Enter the Facility");
			return false;
	  }
	  
    var Team=jQuery("#txtHzomTeam").val();
    if(Team==null ||Team.length==0){
  	  popupCommonErrorMsg("Enter the Team");
  		return false;
    }
    
	var Node=jQuery("#txtHzomNode").val();
	if(Node==null ||Node.length==0){
		popupCommonErrorMsg("Enter the Node");
		return false;
	}
    
	var Design=jQuery("#txtHzomDesignintent").val();
	if(Design==null || Design.length==0){
		    popupCommonErrorMsg("Enter the Indent Design");
			return false;
	  }
	
}

function frmHazopCreate_successsCallback(result){
	//alert(KeyId);
	var keyid=result.KeyId;
//	alert(keyid);
	jQuery("#txthzomKeyid").val(keyid);
	processGridnew("HazopCreate_input.hzop?keyid="+keyid,"&q=1","HazopGrid","pager","","","","");		 
	//  jQuery("#HazopGrid").trigger("reloadGrid"); 
}

function frmHazopCreate_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmHazopCreate","620px");	
}


function btnFilManage_click(){
    var documentNo =jQuery("#txthzomKeyid").val();	      
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#frmMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"HZP","","","","create");	
	} else  
    {   
	  return false;
     }	
}

function frmHazopCreate_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth("frmHazopCreate","700px");	
}


</script>
<form id="frmHazopCreate" name="frmHazopCreate">
	<div id="wrapper" > 
		<table>
			<tr>
				<td colspan="3" >
					  <div  id="frmHazopCreateFuntKeyIds">
					<input type="hidden" id="factory" name="cmbsusnFactoryid" value=" "  ></input>			
					<input type="hidden" id="section" name="cmbsusnSectionid" value=" "  ></input>
					<input type="hidden" id="cell" name="cmbsusnCellid" value=" "  ></input>
					<input type="hidden" id="machine" name="cmbsusnEquipmentid" value=" "  ></input>
					<input type="hidden" id="flid" name="cmbHzomFlid" value="${requestScope.hazopMst.hzomFlid}"></input>	
				</div>
				
			 	<div id="FieldAuditfunLocation" style="width:123%;margin-top:-5px;"></div>
			</td>
			
			<td>	  
		    <div class="easyui-paddingbfpx" style="margin-top:-4px;margin-left:-290px;"><label class="mandatory-lbl">Date</label></div>
			 <div style="margin-left:-290px;margin-top:-5px;" >
			     
			<input type="text" class="easyui-text"  id="dteHzomDate" name="dteHzomDate" maxlength="10"   style=" width : 100px;height:25px; text-align:left;" value="${requestScope.hazopMst.hzomDate}"/>
				 
				  </div> 
			</td>
			
			<td>	  
				  <div class="easyui-paddingbfpx" style="margin-top:-4px;margin-left:-180px;"><label>Hazop No.</label></div>
			     <div style="margin-left:90px;margin-top:-54px;" >
			     
				  <input type="text" class="easyui-text"  id="txthzomKeyid" name="txthzomKeyid" maxlength="10"  style=" width :130px;height:27px; text-align:left;margin-left:-270px;margin-top:51px;" value="${requestScope.hazopMst.hzomKeyid}"/> 
				 
				  </div> 
			</td>
			
			
			  <td>
			<div style="position: relative;margin-left:-130px;margin-top:11px;"><span id="newHazop"
			style="position: absolute;padding-right:30px; top: -20px;">
		</span></div>  
	    </td>
	    
	    
	  		  
		<tr>
			<td valign="top" style=" width : 796px;">
			           <div class="easyui-paddingbfpx" style="margin-top:-1px;"><label class="mandatory-lbl">Facility/Operation/Process</label>
			         <span style="padding-left:210px;" ><label class="mandatory-lbl" id="lblTeam">Team</label></span>	      
			</div>

		<div style="width:250px;position:relative;margin-left:5px;margin-top:-5px;">
	    <textarea maxlength="3000" rows="2" cols="80"  id="txtHzomFacility" name="txtHzomFacility" class="limit-length" style="width:350px; height :50px;" value="">${requestScope.hazopMst.hzomFacility}</textarea>	
		</div>  
		      					      
		<div class="easyui-paddingbfpx" style="margin-left:370px;margin-top:-50px;">
  	    <textarea maxlength="3000" rows="2" cols="80"  id="txtHzomTeam" name="txtHzomTeam" class="limit-length" style="width:340px; height :50px;" value="">${requestScope.hazopMst.hzomTeam}</textarea>	
		</div>  
						    
 		</td>
   </tr>
     <tr>
     
     
	<td>
	<div class="mandatory-lbl"><label>Node</label></div> 
	<div style="margin-left:3px;">
			<span style="float:left;padding-right:0px;">					
			<textarea maxlength="500"  style="width:350px; height :50px;" id="txtHzomNode" name="txtHzomNode" value="">${requestScope.hazopMst.hzomNode}</textarea>
			</span>
			</div>
		   </td> 
		   
		<td>
	<div class="mandatory-lbl" style="margin-left:-430px;margin-top:0px;"><label>Indent Design</label></div> 
	<div class="easyui-paddingbfpx" style="margin-left:-430px;margin-top:0px;">
	<textarea maxlength="500"  style="width:340px; height :50px;" id="txtHzomDesignintent" name="txtHzomDesignintent" value="">${requestScope.hazopMst.hzomDesignintent}</textarea>
	
	</div>
   </td>
   	   
   <td>
		 
	 <div style="margin-left:-15px;margin-top:35px;">
	<input id="btnAddwhatif" class="easyui-button" style="padding-top:0;" type="button" value="Add New"/>
	</div>
		   </td>
		   </tr>
   <tr>
	<td colspan="3">
	<div class="sub-header" style="text-align: left;float:left;width:1000px; width:1400px\9;height:18px\9;position:relative;margin-right:4%">
     <span style="position:absolute;">Hazop Details</span>
   </div>
   </td>
    <td>
	</td>
   </tr>  
   <tr>
						
			<tr>
		      <td>
			  <div style="margin-left:720px;margin-top:-180px;"><label>P & ID No</label></div>
			<div style="margin-left:720px;">
			<span style="float:left;padding-right:0px;">	
			<input class="easyui-text" style="width :80px; height:25px;" id="txtHzomPidno" name="txtHzomPidno" type="text" value="${requestScope.hazopMst.hzomPidno}"/>												
		   </span>

			</div>
			</td>
		    </tr>
		    
</table>
</div>   
	<div style="margin-left:43px;margin-top:-42px;">
	<table id="HazopGrid" style=" "> <tr> <td> </td></tr>
	 </table>
	  <div id="pager"></div>
	 </div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
<input type="hidden" id="hdnkeyId" name="hdnkeyId" value="${requestScope.HazopMst.hzomKeyid}"/>
<input type="hidden" id="txtHazopKeyid" name="txtHazopKeyid" value="${requestScope.HazopMst.hzomKeyid}"/>
</form>
 
 
 