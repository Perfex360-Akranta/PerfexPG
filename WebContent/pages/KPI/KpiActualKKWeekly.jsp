<!-- Created By: Roopa -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script> 	 
jQuery.noConflict();
jQuery(document).ready(function()
{			
	jQuery('#submitForm').val('frmKpiTlActualKkWeekly'); // set the id of form to submit
	initialiseForm('frmKpiTlActualKkWeekly');		
	jQuery('#frmKpiTlActualKkWeekly .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmKpiTlActualKkWeekly textarea').css('text-transform', 'uppercase');
	fillComboBox("frmKpiTlActualKkWeekly","cmbKPIwUomid","uomCombo.commonFilter");
	/*----- for functionalLocation -----*/
	var factId = jQuery("#frmKpiTlActualKkWeekly input[id='factory']").val();
	var sectionId = jQuery("#frmKpiTlActualKkWeekly input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKkWeekly input[id='cell']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId ;	
	loadFunctionalLocation("kpiActKkWeeklyfunLocation","functionalLoc.kpiActKk","kpiActKkWeeklyfunLocationValues","frmKpiTlActualKkWeekly",dataStr);
	/*----------------------------------*/	
	//fillComboBox("frmKpiTlActualKkWeekly","txwKaukCalendaryear","comboYear.progcal");
	var monthYear=jQuery('#txwKaukMonthyearwekly').val();
	var indicatorid=jQuery('#txwKaukIndicatorid').val();
	var pillarId=jQuery('#txwKaukPillarid').val();
	var year=jQuery('#txwKaukCalendaryear').val();
	var isActual=jQuery('#txwKaukIsactual').val();
	//convertJsonListToControl();
	var params="";
	params="&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&pillarId="+pillarId;
	params=params+"&year="+year+"&monthYear="+monthYear+"&indicatorid="+indicatorid+"&isActual="+isActual;
	processAjaxCalls("kpiActualKkWeekly_list.kpiActKk?q=2"+params,"","WmonthCalendarDoubleClickSuccess","monthCalendarError");
	var frmMode=jQuery('#frmMode').val();
	var isActual=jQuery('#txwKaukIsactual').val();
	
	if (frmMode=="view"){
		//alert("frmMode:" +frmMode);
		disableForm('frmKpiTlActualKkWeekly');
	}
	else if (frmMode=="actual"){
		if(isActual=="N" ){
			
			disableForm('frmKpiTlActualKkWeekly');
		}
		else{
			//alert("frmMode:" +frmMode + ",isActual:" +isActual);
			enableForm('frmKpiTlActualKkWeekly');	
		}
	}	
	else if (frmMode=="target"){		
		if(isActual=="N"){
			enableForm('frmKpiTlActualKkWeekly');
		}
		else{
			disableForm('frmKpiTlActualKkWeekly');
		}		
	}			
});
function convertJsonListToControl(result){

	var totalCol =result.monthList.length;
	//var month=result.monthList[i][0];		
	
	if (totalCol==0){return false;}
	//var totalCol = 4;
	var controlHeader='';
	var controlsHtml='';
	jQuery("#txwNoOfCols").val(4);
	var monthYear=jQuery('#txwKaukMonthyearwekly').val();
	controlHeader='<div class="ui-state-default ui-jqgrid-hdiv" style="width: 95%;width: 85%\9;"><div class="ui-jqgrid-hbox">';
	controlHeader=controlHeader+'<table class="ui-jqgrid-htable" cellspacing="0" cellpadding="0" width=" " border="0" aria-labelledby="gbox_list" role="grid"  align=" ">';	
	//controlHeader=controlHeader + createHeader(result);
	controlsHtml=controlsHtml+'<tbody>';	
		
	//controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
	/*<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="right" >';		
	controlsHtml=controlsHtml + CreateHead("head3","Value.");
	controlsHtml=controlsHtml +'</td>';
	*/
	controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
	for(var k=1;k<5;k++){
		controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="center" valign="center" style="width:210px;background-color:#AFD6FE;padding-top:5px;font-size:12px;">';			
		controlsHtml=controlsHtml + monthYear+"[ W - "+k+"]";		
		controlsHtml=controlsHtml + '</td>';
		
	}	
	controlsHtml=controlsHtml +'</tr>';	
	controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
	var i = 1;
	var valArrO=[];
	var keyArrO=[];
	for(var m=0;m<10;m++){
		if(m >=2 ){
			//alert("result.monthList[0]["+m+"] ~~~~  "+result.monthList[0][m]);
			if(m%2)
			valArrO.push(result.monthList[0][m]);
			else
			keyArrO.push(result.monthList[0][m]);
		}
		
			
		
	}
	
	
	
	
	/*alert("valArrO["+1+"] -- "+keyArrO[0]);
	alert("valArrO["+2+"] -- "+keyArrO[1]);
	alert("valArrO["+3+"] -- "+keyArrO[2]);
	alert("valArrO["+4+"] -- "+keyArrO[3]);*/
	var j =0;
	for(var k=1;k<5;k++){
		
	//alert(" 1- "+result.monthList.split(','));
	
	
	var data =valArrO[j]; 
	if(data=='0' || data==null || data=='null')
	{data="";}
	var keyId=keyArrO[j];
	if(keyId==']' || keyId==undefined || keyId.trim()=='undefined')
	{keyId="";}
	controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr" role="columnheader" align="center" valign="bottom" style="width:80px;background-color:white;padding-top:5px;">';			
	controlsHtml=controlsHtml + CreateValue(k,data,i+"-"+monthYear,keyId);
	controlsHtml=controlsHtml + '</td>';
	i= i+7;
	 j++;
	//alert(i);
	}
	controlsHtml=controlsHtml +'</tr>';
	controlsHtml=controlsHtml +'</tbody></table>';
	controlsHtml=controlsHtml + '</div></div>';
	controlsHtml= controlHeader+controlsHtml;
	
	//alert(controlsHtml);
	jQuery('#divKaukWeekly').html(controlsHtml);
	
}
function CreateValue(col,name,monthYear,keyId){
	//alert('kjhk');
	//alert(col+name+monthYear+keyId);
	return '<div class="easyui-paddingbfpx" ><input id="txtIndW_'+col+'" name="txtIndW_'+col+'" value="'+name+'" style="width:40px;text-align:right;" maxlength="4" class="easyui-text" monthYear="'+monthYear+'" keyId="'+keyId+'" /></div>';	 
}
function createContents(result,i)
{
	var controlsHtml='';
	var month=result.monthList[i][0];		
	var data=result.monthList[i][2];
	var keyId=result.monthList[i][1];
	
	if(data=='0' || data==null || data=='null')
	{data="";}
	
	controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="center" valign="center" style="width:80px;background-color:#AFD6FE;padding-top:5px;">';			
	controlsHtml=controlsHtml + CreateMonth(i+1,month);		
	controlsHtml=controlsHtml + '</td>';
	
	controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr" role="columnheader" align="center" valign="bottom" style="width:80px;background-color:white;padding-top:5px;">';			
	controlsHtml=controlsHtml + CreateValue(i+1,data,month,keyId);
	controlsHtml=controlsHtml + '</td>';

	return controlsHtml;
}

function monthCalendarError(result){
	//alert("fail");
}
function CreateHead(col,name){		
	return '<div  class="floatcenter easyui-paddingbfpx" ><label class="lblProgCalHead" id="month'+col+'">'+ name+ '</label> </div> ';	 
}
function CreateHeadMonth(col,name){		
	return '<div  class="floatcenter easyui-paddingbfpx" ><label class="lblProgCalMonth " id="month'+col+'">'+ name+ '</label> </div> ';	 
}

function CreateMonth(col,name){	
	return '<div  class="ui-jqgrid-sortable "><label class="lblProgCalMonth" id="month'+col+'">'+ name+ '</label> </div> ';	 
}

function CreateValue(col,name,monthYear,keyId){	
	return '<div class="easyui-paddingbfpx" ><input id="txtIndW_'+col+'" name="txtIndW_'+col+'" value="'+name+'" style="width:40px;" maxlength="4" class="easyui-text" monthYear="'+monthYear+'" keyId="'+keyId+'" /></div>';	 
}

function CreateKeyId(col,name){	
	return '<div  ><input type="hidden" id="keyId'+col+'" name="keyId'+col+'" value="'+name+'" /></div>';	 
}

function frmKpiTlActualKkWeeklytxwKaukCalendaryear_onLoadSuccess(){}
function frmKpiTlActualKkWeekly_beforeRefreshCallback(){}
function frmKpiTlActualKkWeekly_beforeSubmit(){
	//alert("beforeSubmit");	
	var gridData  = '&multiplemethods='+convertControlsToJSONString();	
	
	return gridData; 			
}

function convertControlsToJSONString(){
	
	var year=getFieldValue('txwKaukCalendaryear','frmKpiTlActualKkWeekly');	
	var factId = jQuery("#frmKpiTlActualKkWeekly input[id='factory']").val();	
	var sectionId = jQuery("#frmKpiTlActualKkWeekly input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKkWeekly input[id='cell']").val();
	var pillarId=jQuery("#txwKaukPillarid").val();
	var indicatorid=jQuery("#txwKaukIndicatorid").val();
	var isActual=jQuery("#txwKaukIsactual").val();	
	var excellencevalue=jQuery("#txwKaukExcellencevalue").val();
	var benchmarkvalue=jQuery("#txwKaukBenchmarkvalue").val();	
	var cm = jQuery("#txwNoOfCols").val();
	var controlId="";
	var cValue="";
	var totValue=0;
	var cKeyId="";
	var cMonthYear="";
	var excellenceVal="";
	var benchMarkVal="";	
	var jsonArrO='[';
	for(i=1;i<=cm;i++)	
	{
		controlId="txtIndW_"+i;
		cValue=jQuery('#'+controlId).val();
		cKeyId=jQuery('#'+ controlId).attr('keyId');
		cMonthYear=jQuery('#' + controlId).attr('monthyear');
		if(cValue!=null && cValue!=""){
			totValue=parseInt(totValue)+parseInt(cValue);
			jsonArrO += '{';
			jsonArrO += '"txwKaukKeyid":"' + cKeyId +'",'; 
			jsonArrO += '"txwKaukIndicatorid":"' + indicatorid +'",';
			jsonArrO += '"txwKaukFactoryid":"' + factId +'",'; 
			jsonArrO += '"txwKaukSectionid":"' + sectionId +'",'; 
			jsonArrO += '"txwKaukCellid":"' + cellId +'",'; 
			jsonArrO += '"txwKaukPillarid":"' + pillarId +'",'; 
			jsonArrO += '"txwKaukCalendaryear":"' + year +'",'; 
			jsonArrO += '"txwKaukMonthyear":"' + cMonthYear +'",'; 
			jsonArrO += '"txwKaukValue":"' + cValue +'",'; 
			jsonArrO += '"txwKaukFreqtype":"W",'; 
			jsonArrO += '"txwKaukStatus":"P",'; 				
			jsonArrO += '"txwKaukIsactual":"'+isActual+'",';				
			jsonArrO += '"txwKaukExcellencevalue":"'+excellencevalue+'",'; 
			jsonArrO += '"txwKaukBenchmarkvalue":"'+benchmarkvalue+'",'; 					
			jsonArrO += '"txwKaukActive":"Y",'; 	
					
			jsonArrO = jsonArrO.slice(0, -1) + "},";
			
	}
}
if(parseInt(totValue)>0){
	jsonArrO += '{';	
	var monthKeyId=jQuery("#txwKaukMonthKeyId").val();		
	if(monthKeyId==null ||  monthKeyId==undefined || monthKeyId=="" )
		monthKeyId="";
	
	jsonArrO += '"txwKaukKeyid":"' + monthKeyId +'",'; 
	jsonArrO += '"txwKaukIndicatorid":"' + indicatorid +'",';
	jsonArrO += '"txwKaukFactoryid":"' + factId +'",'; 
	jsonArrO += '"txwKaukSectionid":"' + sectionId +'",'; 
	jsonArrO += '"txwKaukCellid":"' + cellId +'",'; 
	jsonArrO += '"txwKaukPillarid":"' + pillarId +'",'; 
	jsonArrO += '"txwKaukCalendaryear":"' + year +'",'; 
	jsonArrO += '"txwKaukMonthyear":"' + cMonthYear +'",'; 
	jsonArrO += '"txwKaukValue":"' + totValue +'",'; 
	jsonArrO += '"txwKaukFreqtype":"M",'; 
	jsonArrO += '"txwKaukStatus":"P",'; 				
	jsonArrO += '"txwKaukIsactual":"'+isActual+'",';				
	jsonArrO += '"txwKaukExcellencevalue":"'+excellencevalue+'",'; 
	jsonArrO += '"txwKaukBenchmarkvalue":"'+benchmarkvalue+'",'; 				
	jsonArrO += '"txwKaukActive":"Y",'; 	
				
	jsonArrO = jsonArrO.slice(0, -1) + "},";
}
jsonArrO = jsonArrO.slice(0, -1) + "]";jQuery('#testField').html(jsonArrO);


return jsonArrO; 	
}

function  frmKpiTlActualKkWeeklytxwKaukCalendaryear_onSelect(record)
{//alert(record.id);
}

function WmonthCalendarDoubleClickSuccess(result){
	//alert("success");
	setFieldValue('cmbKPIwUomid',"UOM006");
	convertJsonListToControl(result);
}

function frmKpiTlActualKkWeekly_exceptionCallback(){
	//alert("exception");
}

function frmKpiTlActualKkWeekly_successsCallback(result){
	closePopUpDialoge("divKpiTlActualKkWeekly");	
	jQuery('#grdKpiActualkk').trigger("reloadGrid") ;
}

function frmKpiTlActualKkWeekly_FuntLocHierarchy_SuccessCallBack(keyIds){
	/*setFieldValue('cmbKaukCellid',keyIds.cellId);*/
}
function  frmFunctLocHierarchcmbFunctLocMachine_onLoadSuccess(){}

jQuery('#btndlgCloseW').click( function()
{	
	closePopUpDialoge("divKpiTlActualKkWeekly");	
	jQuery('#grdKpiActualkk').trigger("reloadGrid") ;
});

jQuery('#btndlgWeeklySave').click( function()
{
	
	saveForm("frmKpiTlActualKkWeekly","kpiActualKkActual_save.kpiActKk?q=2");
});
</script>

<div style="width:80%;max-width:78%\9;width:78%\9;">		
	<form name="frmKpiTlActualKkWeekly" id="frmKpiTlActualKkWeekly">
	   	<div style="width:80%;">
	    	<table width="100%" align="center" >
	    		<tr>
	    			<td valign="top"   >
             			
		             	<div  class="easyui-paddingbfpx">
		                     <label>Indicator</label>                  
		                </div> 
		                <div class="easyui-paddingbfpx"> 
		                     <input id="txwKaukIndicatorName" name="txwKaukIndicatorName" value="${requestScope.kaukIndicatorName}" style="width:360px" maxlength="0" class="easyui-text"  <c:out value = "${ requestScope.kpiTlActualKkBean.disableForm == true ? ' disabled':''}"/>/>                       
		                </div>	
                		
                	</td>    
	    		<td>
                		 <div  class="">
                    		<label class="mandatory-lbl">UoM</label>                    
		                </div>
		                <div class=""> 
		                    <input id="cmbKPIwUomid" name="cmbKPIwUomid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.plmTlCbmstdcadtl.cmdtUomid}">                    
		                </div>
                	</td> 
                	<td valign="top" width="50%" >
             			<div style="padding-left:5%;">
		             	<div  class="easyui-paddingbfpx">
		                     <label>Year</label>                  
		                </div> 
		                <div class="easyui-paddingbfpx"> 
		                     <input id="txtKaukCalendaryear" name="txtKaukCalendaryear" value="2013" style="width:60px" maxlength="0" class="easyui-text"  <c:out value = "${ requestScope.kpiTlActualKkBean.disableForm == true ? ' disabled':''}"/>/>                       
		                </div>	
                		</div>
                	</td>     
	    		</tr>
      			<tr>      			
	                <td valign="top" width=" "  colspan='2' >		
					<div  id="frmKpiTlActualKkWeeklyFuntKeyIds">
						<div style="  ">
							<input type="hidden" id="factory" name="cmbKaukFactoryid" value=""  ></input>
							<input type="hidden" id="section" name="cmbKaukSectionid" value=""  ></input>
							<input type="hidden" id="cell" name="cmbKaukCellid" value="${requestScope.kpiTlActualKk.kaukDeptid}"  ></input>
						</div>
						<div id="kpiActKkWeeklyfunLocation" style="width: 835px;"></div>									
						<div class="" style="  ">
							<div id="txtFct" class="tpm-errormsg" style="padding-left:30px;"/></div>
							<div id="err_err_cell" class="tpm-errormsg" style="padding-left:30px;"/></div>
						</div>
						<div class="clear"></div>
					</div>			
				 	</td>
                	               
    			</tr>			    		
	    	</table>
    	</div>  
	    <div id="hiddenValues">        		 
		    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
		    <input type="hidden" id="frmMode" name="frmMode" value="${requestScope.frmMode}"/>
		    <input type="hidden" id="txwNoOfCols" name="txwNoOfCols" value="0"/>
		    <input type="hidden" id="txwKaukPillarid" name="txwKaukPillarid"  value="${requestScope.kpiTlActualKk.kaukPillarid}" />
		    <input type="hidden" id="txwKaukExcellencevalue" name="txwKaukExcellencevalue"  value="${requestScope.kpiTlActualKk.kaukExcellencevalue}" />
		    <input type="hidden" id="txwKaukBenchmarkvalue" name="txwKaukBenchmarkvalue"  value="${requestScope.kpiTlActualKk.kaukBenchmarkvalue}" />
		    <input type="hidden" id="txwKaukIndicatorid" name="txwKaukIndicatorid"  value="${requestScope.kpiTlActualKk.kaukIndicatorid}" />
		    <input type="hidden" id="txwKaukMonthyearwekly" name="txwKaukMonthyearwekly"  value="${requestScope.monthYear}" />
		    <input type="hidden" id="txwKaukIsactual" name="txwKaukIsactual"  value="${requestScope.kpiTlActualKk.kaukIsactual}" />
		    <input type="hidden" id="txwKaukMonthKeyId" name="txwKaukMonthKeyId"  value="${requestScope.kaukMonthKeyId}" />
		      <div id="testField" style="display:none;"></div>
		</div>	
		
		<div id="divKaukWeekly" style="width:75%\9;"></div>	
			<div style="margin-top:1%;">
			<div><label>Remarks</label></div>
			<div><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows='3' cols='10' style="width:800px;"> </textarea></div>
		</div>
		<div style=" padding-top:10px; padding-left:5%\9;">	
			<input type="button" class="easyui-button" id="btndlgWeeklySave" value="Save" <c:out value = "${ requestScope.entTlTrainingareaBean.disableForm == true ? ' disabled':''}"/>/>									
			<input type="button" class="easyui-button" id="btndlgCloseW" value="Close"/>
			
		</div>                                       
       
    </form>
</div>