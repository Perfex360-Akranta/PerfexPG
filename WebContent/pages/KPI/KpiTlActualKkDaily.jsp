<!-- Created By: Roopa -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script><!--	 
jQuery.noConflict();
jQuery(document).ready(function()
{			
	
	jQuery('#submitForm').val('frmKpiTlActualKkDaily'); // set the id of form to submit
	initialiseForm('frmKpiTlActualKkDaily');		
//	jQuery('#frmKpiTlActualKkDaily .easyui-text').css('text-transform', 'uppercase');
//	jQuery('#frmKpiTlActualKkDaily textarea').css('text-transform', 'uppercase');
	/*----- for functionalLocation -----*/
	var factId = jQuery("#frmKpiTlActualKkDaily input[id='factory']").val();
	var flid = jQuery("#frmKpiTlActualKkDaily input[id='flid']").val();
	var sectionId = jQuery("#frmKpiTlActualKkDaily input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKkDaily input[id='cell']").val();
	var dataStr = "&flid="+flid+"&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId ;
	//alert(dataStr);
	loadFunctionalLocation("kpiActKkDailyfunLocation","functionalLoc.kpiActKk","kpiActKkDailyfunLocationValues","frmKpiTlActualKkDaily",dataStr);
	/*----------------------------------*/	
	//fillComboBox("frmKpiTlActualKkDaily","txtKaukCalendaryear","comboYear.progcal");
		
	fillComboBox("frmKpiTlActualKkDaily","cmbKPIUomid","uomCombo.commonFilter");

	LoadDateArea();
	
	var calcType = jQuery('#txtManualCalctype').val();
	if (calcType=='S')
		jQuery('#txtCalctype').val('Sum');
	else
		jQuery('#txtCalctype').val('Average');

	jQuery('#hdnClickedDate').val("1");

	jQuery("#btnActionplan").click(function(){
		
		var clickedDate = jQuery('#hdnClickedDate').val();	
		var controlId="txtInd_"+clickedDate;
		var cValue=jQuery('#' + controlId).val();
		var cKeyId=jQuery('#' + controlId).attr('keyId');
		//var MonthYear=jQuery('#' + controlId).attr('monthYear');
		//alert("cKeyId=="+cKeyId);
		if(clickedDate.trim().length!=0){
				saveForm("frmKpiTlActualKkDaily","kpiActualKkActual_save.kpiActKk?openactnpln=Y");
		}
		else{
			popupCommonErrorMsg("Select Date");
		}
		 
	});

});

function LoadDateArea() { 
	var factId = jQuery("#frmKpiTlActualKkDaily input[id='factory']").val();
	var flid = jQuery("#frmKpiTlActualKkDaily input[id='flid']").val();
	var sectionId = jQuery("#frmKpiTlActualKkDaily input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKkDaily input[id='cell']").val();
	var monthYear=jQuery('#txtKaukMonthyear').val();
	var month = monthYear.substring(monthYear.indexOf("-")+1,monthYear.lastIndexOf("-"));
	var monthIndex = getIndex(month);
	var year = monthYear.substring(monthYear.lastIndexOf("-")+1,monthYear.length);
	if(monthIndex<3)
		year = year -1;
	jQuery("#txtKaukCalendaryear").val(year);
	var indicatorid=jQuery('#txtKaukIndicatorid').val();
	var pillarId=jQuery('#txtKaukPillarid').val();
	var year=jQuery('#txtKaukCalendaryear').val();
	var isActual=jQuery('#txtKaukIsactual').val();
	var params="";
	params="&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&pillarId="+pillarId;
	params=params+"&year="+year+"&monthYear="+monthYear+"&indicatorid="+indicatorid+"&isActual="+isActual;
	processAjaxCalls("kpiActualKkDaily_list.kpiActKk?q=2"+params,"","monthCalendarDoubleClickSuccess","monthCalendarError");

}
function convertDailyJsonListToControl(result){
	var totalCol =result.monthList.length;		
	if (totalCol==0){return false;}
	var controlHeader='';
	var controlsHtml='';
	jQuery("#txtNoOfCols").val(totalCol);
	
	controlHeader='<div class="ui-state-default ui-jqgrid-hdiv" style="width: 97%;"><div class="ui-jqgrid-hbox">';
	controlHeader=controlHeader+'<table class="ui-jqgrid-htable" cellspacing="0" cellpadding="0" border="0" aria-labelledby="gbox_list" role="grid"  align="center">';	
	//controlHeader=controlHeader + createHeader(result);
	controlsHtml=controlsHtml+'<tbody>';	
		
	//controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
	/*<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="right" >';		
	controlsHtml=controlsHtml + CreateHead("head3","Value.");
	controlsHtml=controlsHtml +'</td>';
	*/
	controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
	for(var k=1;k<=5;k++){
		controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="center" valign="center" style="width:80px;background-color:#AFD6FE;padding-top:5px;">';			
		controlsHtml=controlsHtml + CreateMonth(i+1,"Date");		
		controlsHtml=controlsHtml + '</td>';
		controlsHtml=controlsHtml +'<td class="ui-state-default ui-th-ltr progCalHeadTd" role="columnheader" align="center" valign="center" style="width:80px;background-color:#AFD6FE;padding-top:5px;">';			
		controlsHtml=controlsHtml + CreateMonth(i+1,"Value");		
		controlsHtml=controlsHtml + '</td>';
	}	
	controlsHtml=controlsHtml +'</tr>';	
	for( var i = 0; i <result.monthList.length;i++){
		
		controlsHtml=controlsHtml +'<tr class="ui-jqgrid-labels" role="rowheader">';
		if(i<result.monthList.length){		
			controlsHtml=controlsHtml+createContents(result,i);
		}
		else
			controlsHtml=controlsHtml+'<td></td>';
		for(var j=1;j<=4;j++){
			if(i<result.monthList.length-1){
				i=i+1;
				controlsHtml=controlsHtml+createContents(result,i);
			}
			else
				controlsHtml=controlsHtml+'<td></td>';
		}		
		
		controlsHtml=controlsHtml +'</tr>';
		
	
	}		
	//controlsHtml=controlsHtml +'</tr>';
	
	controlsHtml=controlsHtml +'</tbody></table>';
	controlsHtml=controlsHtml + '</div></div>';
	controlsHtml= controlHeader+controlsHtml;
	
	//alert(controlsHtml);
	jQuery('#divKaukDaily').html(controlsHtml);
	//setFieldValue('cmbKPIUomid',"UOM006");
	
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var month = currentTime.getMonth();	
	var day = currentTime.getDate();	
	var frmMode=jQuery('#frmMode').val();
	var isActual=jQuery('#txtKaukIsactual').val();
	var currentDate =  getServerDateTime();
	
	for( var i = 0; i <result.monthList.length;i++){
		var txtNo = parseInt(i)+1;
		numericTextBox("txtInd_"+txtNo,"Y");
		var month=jQuery("#txtInd_"+txtNo).attr('monthyear');
		
		if(frmMode=="actual" && currentDate < convertStringToDate(month)){		
			//if(frmMode=="actual" && parseInt(txtNo) > parseInt(day) ) {
			readOnlyFields("txtInd_"+txtNo);
			jQuery("#txtInd_"+txtNo).attr('maxlength', '0');
		}
	}
	
	if (frmMode=="view"){
		//alert("frmMode:" +frmMode);
		disableForm('frmKpiTlActualKkDaily');
		enableUIButton("btndlgClose");
	}
	else if (frmMode=="actual"){
		
		if(isActual=="N" ){
			disableForm('frmKpiTlActualKkDaily');
			enableUIButton("btndlgClose");
		}
		else{
			//alert("frmMode:" +frmMode + ",isActual:" +isActual);
			enableForm('frmKpiTlActualKkDaily');	
		}
	}	
	else if (frmMode=="target"){		
		if(isActual=="N"){
			enableForm('frmKpiTlActualKkDaily');
		}
		else{
			disableForm('frmKpiTlActualKkDaily');
			enableUIButton("btndlgClose");
		}		
	}
	
	setFocusOnField("txtInd_1");
	
}
function createContents(result,i)
{
	var controlsHtml='';
	var month=result.monthList[i][0];		
	var data=result.monthList[i][2];
	var keyId=result.monthList[i][1];
	
	if( data==null || data=='null')
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
	return '<div class="easyui-paddingbfpx" ><input id="txtInd_'+col+'" name="txtInd_'+col+'" onblur="clickedDate('+col+')" value="'+name+'" style="width:70px;background-color: rgb(256, 256, 256);" maxlength="10" class="easyui-text" monthYear="'+monthYear+'" keyId="'+keyId+'" /></div>';	 
}


function clickedDate(col) {
	jQuery('#hdnClickedDate').val(col);
}

function CreateKeyId(col,name){	
	return '<div  ><input type="hidden" id="keyId'+col+'" name="keyId'+col+'" value="'+name+'" /></div>';	 
}

function frmKpiTlActualKkDailytxtKaukCalendaryear_onLoadSuccess(){ 
}
function frmKpiTlActualKkDaily_beforeRefreshCallback(){
	
}

function frmKpiTlActualKkDaily_beforeSubmit(){
	//alert("beforeSubmit");	
	var gridData  = '&multiplemethods='+convertDailyControlsToJSONString();	
	//alert(gridData);		
	return gridData; 			
}
function calTotal(){	
	var totValue=getFieldValue("txtTotal");
	if(totValue.trim().length>0){
		var colid=getFieldValue("dblColIndex");
		var rowid=getFieldValue("dblRowIndex");
		var controlId="txtInd_"+(colid-1) + "_"+rowid;
		//alert("totValue"+totValue+"rowid"+rowid+"colid"+colid);		
		jQuery("#"+controlId).val(totValue);
		setFieldValue("txtTotal","0");
	}
}
function convertDailyControlsToJSONString(){
	
	var year=getFieldValue('txtKaukCalendaryear','frmKpiTlActualKkDaily');	
	var factId = '{}';  //jQuery("#frmKpiTlActualKkDaily input[id='factory']").val();	
	var sectionId = jQuery("#frmKpiTlActualKkDaily input[id='section']").val();
	var cellId = jQuery("#frmKpiTlActualKkDaily input[id='cell']").val();
	var flId = jQuery("#frmKpiTlActualKkDaily input[id='flid']").val();
	var pillarId=jQuery("#txtKaukPillarid").val();
	var indicatorid=jQuery("#txtKaukIndicatorid").val();
	var isActual=jQuery("#txtKaukIsactual").val();	
	var excellencevalue=jQuery("#txtKaukExcellencevalue").val();
	var benchmarkvalue=jQuery("#txtKaukBenchmarkvalue").val();	
	var cm = jQuery("#txtNoOfCols").val();
	var controlId="";
	var cValue="";
	var totValue=0;
	var cKeyId="";
	var cMonthYear="";
	var excellenceVal="";
	var benchMarkVal="";	
	var jsonArrO='[';
	var count = 0 ;
	var rowId ="";
	var rowNumber="0";
	
	for(var i=1;i<=cm;i++)	
	{
		controlId="txtInd_"+i;
		cValue=jQuery('#' + controlId).val();
		cKeyId=jQuery('#' + controlId).attr('keyId');
		cMonthYear=jQuery('#' + controlId).attr('monthYear');
		
		if((cValue!= null && cValue!="") || (cKeyId!=null && cKeyId != '' && cKeyId != ' ')){
			
			if(cValue==null || cValue=='')
				cValue="0";
			else
				count++;
			
			if(totValue==null || totValue=='') 
				totValue="0";
			
			totValue=parseFloat(totValue)+parseFloat(cValue);
			jsonArrO += '{';
			jsonArrO += '"txtKaukKeyid":"' + cKeyId +'",'; 
			jsonArrO += '"txtKaukIndicatorid":"' + indicatorid +'",';
			jsonArrO += '"txtKaukDeptid":"' + flId +'",';
			jsonArrO += '"txtKaukFactoryid":"' + factId +'",'; 
			jsonArrO += '"txtKaukSectionid":"' + sectionId +'",'; 
			jsonArrO += '"txtKaukCellid":"' + cellId +'",'; 
			jsonArrO += '"txtKaukPillarid":"' + pillarId +'",'; 
			jsonArrO += '"txtKaukCalendaryear":"' + year +'",'; 
			jsonArrO += '"txtKaukMonthyear":"' + cMonthYear +'",'; 
			jsonArrO += '"txtKaukValue":"' + cValue +'",'; 
			jsonArrO += '"txtKaukFreqtype":"D",'; 
			jsonArrO += '"txtKaukStatus":"P",'; 				
			jsonArrO += '"txtKaukIsactual":"'+isActual+'",';				
			jsonArrO += '"txtKaukExcellencevalue":"'+excellencevalue+'",'; 
			jsonArrO += '"txtKaukBenchmarkvalue":"'+benchmarkvalue+'",'; 					
			jsonArrO += '"txtKaukActive":"Y",'; 	
						
			jsonArrO = jsonArrO.slice(0, -1) + "},";
			
			rowNumber = parseInt(rowNumber)+1;
			var selDate = jQuery('#hdnClickedDate').val();
			if (parseInt(i) == parseInt(selDate))
				rowId = rowNumber;
		}
	}
	
	if(parseInt(totValue)>0){
		jsonArrO += '{';	
		var monthKeyId=jQuery("#txtKaukMonthKeyId").val();		
		if(monthKeyId==null ||  monthKeyId==undefined || monthKeyId=="" )
			monthKeyId="";
		
		var calcType = jQuery('#txtManualCalctype').val();
			
		if (calcType=='S')
			totValue = parseFloat(parseFloat(totValue));
		else
			totValue = parseFloat(parseFloat(totValue)/parseFloat(count));
		
		totValue = (totValue * 100) /100 ;
		totValue = totValue.toFixed(2);
		setFieldValue("txtTotal",totValue);
		jsonArrO += '"txtKaukKeyid":"' + monthKeyId +'",'; 
		jsonArrO += '"txtKaukIndicatorid":"' + indicatorid +'",';
		jsonArrO += '"txtKaukDeptid":"' + flId +'",';
		jsonArrO += '"txtKaukFactoryid":"' + factId +'",'; 
		jsonArrO += '"txtKaukSectionid":"' + sectionId +'",'; 
		jsonArrO += '"txtKaukCellid":"' + cellId +'",'; 
		jsonArrO += '"txtKaukPillarid":"' + pillarId +'",'; 
		jsonArrO += '"txtKaukCalendaryear":"' + year +'",'; 
		jsonArrO += '"txtKaukMonthyear":"' + cMonthYear +'",'; 
		jsonArrO += '"txtKaukValue":"' + totValue +'",'; 
		jsonArrO += '"txtKaukFreqtype":"M",'; 
		jsonArrO += '"txtKaukStatus":"P",'; 				
		jsonArrO += '"txtKaukIsactual":"'+isActual+'",';				
		jsonArrO += '"txtKaukExcellencevalue":"'+excellencevalue+'",'; 
		jsonArrO += '"txtKaukBenchmarkvalue":"'+benchmarkvalue+'",'; 				
		jsonArrO += '"txtKaukActive":"Y",'; 	
					
		jsonArrO = jsonArrO.slice(0, -1) + "},";
	}
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	//alert("jsonArrO:"+jsonArrO);
	//alert(rowId);
	if(rowId==null || rowId=='' || rowId==' ')
		rowId="1";
	//alert(rowId);
	return jsonArrO+"&rowId="+rowId; 	
}

function  frmKpiTlActualKkDailytxtKaukCalendaryear_onSelect(record)
{//alert(record.id);
}

function monthCalendarDoubleClickSuccess(result){
	//alert("success");
	convertDailyJsonListToControl(result);
}

function frmKpiTlActualKkDaily_exceptionCallback(){
	//alert("exception");
}

function frmKpiTlActualKkDaily_successsCallback(result){
	calTotal();
	LoadDateArea();
	var openactnpln =result.successData.openactnpln;
	if(openactnpln==true )
	 {
		 var keyid = result.successData.keyid;		 
		 var flid = jQuery("#frmKpiTlActualKkDaily input[id='flid']").val();
		 var maintask = jQuery("#hdnIndicatiorName").val();
		 cMonthYear=jQuery('#txtKaukMonthyear').val();
		 var clickedDate = jQuery("#hdnClickedDate").val();
		 var abnDetectDate = clickedDate + cMonthYear.substr(2,10);
		 //alert(abnDetectDate);
		 if(keyid=="" || keyid==" ")
			 keyid = jQuery("#hdnKPIKeyid").val();

		 maintask = maintask +" " +  abnDetectDate;
		 var mstKeyid=jQuery("#hdnKPIKeyid").val();
		 //alert(keyid);
		 openActionPlan("actionPlan",mstKeyid,"KPI",flid,maintask,keyid,abnDetectDate,"create");
	 }
	else 	
		closePopUpDialoge("divKpiTlActualKkDaily");	
	//jQuery('#grdKpiActualkk').trigger("reloadGrid") ;
}

function frmKpiTlActualKkDaily_FuntLocHierarchy_SuccessCallBack(keyIds){
	/*setFieldValue('cmbKaukCellid',keyIds.cellId);*/
	
	readOnlyFields ( "cmbKPIUomid");
	readOnlyFields ("txtKaukIndicatorName");
	readOnlyFields ("txtKaukCalendaryear");
	
	jQuery('#divDisabledFields').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:20%;"> </div>');
				
}
function  frmFunctLocHierarchcmbFunctLocMachine_onLoadSuccess(){}

jQuery('#btndlgClose').click( function()
{	
	closePopUpDialoge("divKpiTlActualKkDaily");	
	//jQuery('#grdKpiActualkk').trigger("reloadGrid") ;
});

jQuery('#btndlgSave').click( function()
{	
	saveForm("frmKpiTlActualKkDaily","kpiActualKkActual_save.kpiActKk?q=2");
});
</script>

<div>		
	<form name="frmKpiTlActualKkDaily" id="frmKpiTlActualKkDaily">
	   	<div id="divDisabledFields">
	    	<table width="100%" align="center" >
	    		<tr>
	    			<td valign="top" width=" " colspan=" " >
             			
		             	<div  class="easyui-paddingbfpx">
		                     <label>Indicator</label>                  
		                </div> 
		                <div class="easyui-paddingbfpx"> 
		                     <input id="txtKaukIndicatorName" name="txtKaukIndicatorName" value="${requestScope.kaukIndicatorName}" style="width:360px" maxlength="0" class="easyui-text"  <c:out value = "${ requestScope.kpiTlActualKkBean.disableForm == true ? ' disabled':''}"/>/>                       
		                </div>	
                		
                	</td>   
                	<td>
                		 <div  class="">
                    		<label class="mandatory-lbl">UoM</label>                    
		                </div>
		                <div class=""> 
		                    <input id="cmbKPIUomid" name="cmbKPIUomid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.Uom}">                    
		                </div>
                	</td> 
                	<td valign="top" width="50%" >
             			<div style="padding-left:5%;">
		             	<div  class="easyui-paddingbfpx">
		                     <label>Year</label>                  
		                </div> 
		                <div class="easyui-paddingbfpx"> 
		                     <input id="txtKaukCalendaryear" name="txtKaukCalendaryear" value="" style="width:60px" maxlength="0" class="easyui-text"  <c:out value = "${ requestScope.kpiTlActualKkBean.disableForm == true ? ' disabled':''}"/>/>                       
		                </div>	
                		</div>
                	</td>
                	
                	<td>
                		 <div  class="">
                    		<label class="">Calculation Type</label>                    
		                </div>
		                <div class=""> 
		                    <input id="txtCalctype" name="txtCalctype" class="easyui-text"  style="width:120px;" disabled="disabled" value="${requestScope.manualCalctype}">                    
		                </div>
                	</td> 
                	     
	    		</tr>
      			<tr>      			
	                <td valign="top" width="50%"  colspan='3' >	
					<div  id="frmKpiTlActualKkDailyFuntKeyIds">
						<div style="float: left;padding-right: 20px;">
							<input type="hidden" id="flid" name="cmbKaukDeptid" value="${requestScope.kaukDeptid}"  ></input>
							<input type="hidden" id="factory" name="cmbKaukFactoryid" value=" "  ></input>
							<input type="hidden" id="section" name="cmbKaukSectionid" value=" "  ></input>
							<input type="hidden" id="cell" name="cmbKaukCellid" value=" "  ></input>
						</div>									
						<div class="" style="padding-right: 395px;width:320px;">
							<div id="kpiActKkDailyfunLocation" style="padding-left: 0px; width: 835px; "></div>
							<div id="txtFct" class="tpm-errormsg" style="padding-left:30px;"/></div>
							<div id="err_err_cell" class="tpm-errormsg" style="padding-left:30px;"/></div>
						</div>
						<div class="clear"></div>
					</div>			
				 	</td>
                	
	    	</table>
    	</div>  
	    <div>        		 
		    <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
		    <input type="hidden" id="frmMode" name="frmMode" value="${requestScope.frmMode}"/>
		    <input type="hidden" id="txtNoOfCols" name="txtNoOfCols" value="0"/>
		    
		    <input type="hidden" id="txtKaukPillarid" name="txtKaukPillarid"  value="${requestScope.kpiTlActualKk.kaukPillarid}" />
		    <input type="hidden" id="txtKaukExcellencevalue" name="txtKaukExcellencevalue"  value="${requestScope.kpiTlActualKk.kaukExcellencevalue}" />
		    <input type="hidden" id="txtKaukBenchmarkvalue" name="txtKaukBenchmarkvalue"  value="${requestScope.kpiTlActualKk.kaukBenchmarkvalue}" />
		    <input type="hidden" id="txtKaukIndicatorid" name="txtKaukIndicatorid"  value="${requestScope.kpiTlActualKk.kaukIndicatorid}" />
		    <input type="hidden" id="txtKaukMonthyear" name="txtKaukMonthyear"  value="${requestScope.kpiTlActualKk.kaukMonthyear}" />
		    <input type="hidden" id="txtKaukIsactual" name="txtKaukIsactual"  value="${requestScope.kpiTlActualKk.kaukIsactual}" />
		    <input type="hidden" id="txtKaukMonthKeyId" name="txtKaukMonthKeyId"  value="${requestScope.kaukMonthKeyId}" />
		    <input type="hidden" id="txtManualCalctype" name="txtManualCalctype"  value="${requestScope.manualCalctype}" />
		    <input type="hidden" id="hdnClickedDate" name="hdnClickedDate"  value="" />
		    
		</div>	
		
		<div id="divKaukDaily"></div>	
		<!-- <div style="margin-top:1%;">
			<div><label>Remarks</label></div>
			<div><textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows='3' cols='10' style="width:800px;"> </textarea></div>
		</div>
		 -->
		<div style="float:center;padding-top:10px;padding-left:5%;">
			
			<input type="button" id="btnActionplan" name="btnActionplan" class="easyui-button" style="width:70px;height:22px;" value="Action Plan" />
			<span style="padding-left: 40%;">
				<input type="button" class="easyui-button" id="btndlgSave" value="Save" <c:out value = "${ requestScope.entTlTrainingareaBean.disableForm == true ? ' disabled':''}"/>/>
			</span>									
			<input type="button" class="easyui-button" id="btndlgClose" value="Close"/>
		</div>                                       
       
    </form>
</div>