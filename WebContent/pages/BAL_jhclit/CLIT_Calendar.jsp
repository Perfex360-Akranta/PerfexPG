 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

 
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	
	
	 initialiseForm('frmclitCal');	    	  
       jQuery('#submitForm').val('frmclitCal');
  jQuery("#btnSave").hide();//attr("disabled", true);

  jQuery('#abnmTagclassid').hide();
  readOnlyFields("txtObservation");
 readOnlyFields("chkNotOk");
 jQuery('#btnSendMail').hide();
  
	var url = jQuery('#hiddenUrl').val();
	if(url == "jhcalendar_input.jhcal")
	jQuery('#actualImg').css('display','none');
  //  numericTextBox('txtactual');//for number only validation
	
    fillComboBox("frmclitCal","cmbClitCellid","cellCombo.commonFilter" );
	fillComboBox("frmclitCal","cmbClitEquipment","machineCombo.commonFilter" );
	fillComboBox("frmclitCal","cmbClitShift","shift.commonFilter" );
	//fillComboBox("frmclitCal","cmbAbnmTagclassid","Combo_TagClass.abnForm?frmType=JH");

	loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmclitCal","&machId="+clismchId);
	
	var machineHirerachyId = null;
	/*for opening from Breakdownmst*/
	var clismchId = jQuery("#cmbClisMachineid").combobox("getValue");//getFieldValue('cmbClisMachineid');
	//alert('clismchId :'+clismchId );
	if(clismchId != null && clismchId != "")
	{
		jQuery('#hien').val(clismchId);
		//viewbtncall(clismchId);
		loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmJhClitStd","&machId="+clismchId);
		readOnlyFields('cmbClisMachineid');
		var frmName = jQuery('#hdnFrmName').val();
		navigateToNextForm("jhClit_mcharea.jhclit?q=2&loadContentDivId=jhclitgrid2&preLoadContentDivId=preloadDIVid2&mchId="+clismchId,frmName);
		//LoadForm("jhclitgrid1","preloadDIVid2","jhClit_mcharea.jhclit","dispErr","","jhclitmcharea_errorCallBack");
		popFormNavigation();
	}
	else{
		//enableFields('cmbClisMachineid');	
	//	LoadForm("jhclitgrid1","preloadDIVid","grid_jhclitcount.jhclit","dispErr","","jhclitcountgrid_errorCallBack");
		/* for functionalLocation*/
		var factId = jQuery("#frmJhClitStd input[id='factory']").val();
		var sectionId = jQuery("#frmJhClitStd input[id='section']").val();
		var cellId = jQuery("#frmJhClitStd input[id='cell']").val();
		var machId = jQuery("#frmJhClitStd input[id='machine']").val();
		var flid = jQuery("#frmJhClitStd input[id='flid']").val();
		
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		//alert("flid "+dataStr);
		loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmJhClitStd",dataStr);
		
	/*---------*/
	}
	
	
	/*for jh scheduled and standard report **/
	var mchId= jQuery('#jhsshdnKeyid').val();
	var frmMOnth =jQuery('#jhsshdnfltrstring').val();
	var filterStr = jQuery('#hdnFromStandard').val();
 
	 
	//alert('calendar'+filterStr);
	//alert('len'+filterStr.length);
	//alert('s');    
    if (filterStr.length > 0)
    { 
        if(frmMOnth.length > 0 )
        	viewGrid(url,frmMOnth);
        else
	 	   viewGrid(url,filterStr);
    }
    else
    {
    	viewGrid(url,"");
    	
    	if(mchId ==  null || mchId == "" ){
    	//alert("no mch value");
    	
       // processGridnew(url,filterStr,"list","pager","");
       setLoadFormCallBackFrmId('frmjhclitCal');	
    	//toggleCommonFilter();
		//loadCommonFilter();
    	}
    	else{alert('mch value present');}
    }				
    
   
});

/*function chkFormatter(id, options, rowObject)
{
	 
		var id = options.rowId;
		var columnName = options.colModel.name;	
		var checked ="";

					
	    	     return '<input id="selectRow" name="selectRow " type="checkbox"  onclick="if(this.checked){dataSelect(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
					//alert(checked);
			}

*/
///

function dataSelect(id){

	//alert(id);
		jQuery('#hdnCheckSel').val("1");
     	jQuery("#listGrid").setCell(rowId, "hdnchkSel","1");
   }
   function unselectData(id){
	//	alert(02);
		   
		jQuery('#hdnCheckSel').val("0");    	
     	jQuery("#listGrid").setCell(rowId, "hdnchkSel"," ");
   }
///

function chk_box(id,cellvalue){
	  
	if(id=='3'&& cellvalue!="" ){
	//formatStr =' <input  type="checkbox" id="chksel" checked="checked" onclick="if(this.checked){selectData('+ position +');}else{unselectData('+position + ');}" />';
	}
  }
function jhCalLoadComplete(){

	jQuery("#btnSave").show();//attr("disabled", false);
	jQuery("#chkNotOk").attr("disabled", false);
	 var ids = jQuery("#listGrid").getDataIDs();
	 
	 jQuery("#listGrid").setGridParam({
			onCellSelect:function(id,cellidx,cellvalue) {
				jQuery('#hdncelvalue').val(cellvalue);
				if(id > 3)
					if(cellvalue.trim().length == 1)
					{
						var rowData = jQuery("#listGrid").jqGrid('getRowData',id);
						var refdocno = rowData.RefDocno;
						var refno = refdocno.split("$");						
						jQuery('#selRefDocId').val(refno[0]);
						;
					}
					else{
						 
						btnEnableDisable(false);/*function for enable / disable link buttons*/
						
						}	
			}
	 });
	// alert(12);
}

function frmclitCal_FuntLocHierarchy_SuccessCallBack(keyIds)
{	
	var dataString="";
	var factId=keyIds.factId;
	var sectId = keyIds.sectId;	
	var cellId=keyIds.cellId;
	var flid=keyIds.flId;
	//
		reloadCombo("frmclitCal","cmbClitCellid","cellCombo.commonFilter?sectionid="+sectId );
	     if (keyIds.cellId!="null")
		setFieldValue('cmbClitCellid',keyIds.cellId);			
	reloadCombo("frmclitCal","cmbClitShift","shift.commonFilter?frmRfilter=yes&factId="+factId);
	reloadCombo("frmclitCal","cmbClitEquipment","machineCombo.commonFilter?cellId="+cellId);//+"&flid="+flId);
	aler(factId +"factId ");
	
	
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if(hours < 10)
		{
		 hours = "0" + hours;
		}
	if (minutes < 10){
		minutes = "0" + minutes;
	}	
	dataString +='?&flid='+flid+'&fromTime='+hours + ':' + minutes;
	
	
}

function frmclitCalcmbClitCellid_onSelect(record) 	{

	jQuery("#cmbClitEquipment").combobox('clear');

  var cellId=jQuery("#cmbClitCellid").combobox('getValue');
	reloadCombo("frmclitCal","cmbClitEquipment","machineCombo.commonFilter?cellId="+cellId);//+"&flid="+flId);
}
function frmclitCalcmbClitEquipment_onSelect(record) 	{
	var clismchId=jQuery("#cmbClitEquipment").combobox('getValue');
	
loadFunctionalLocation("clisfunLocation","functionalLoc.jhclit","clisfunLocationValues","frmclitCal","&machId="+clismchId);
//reloadCombo("frmclitCal","cmbClitShift","shift.commonFilter?factId="+factId);

var serverTime = srvTime();
var currentTime = new Date(serverTime);
var hours = currentTime.getHours();
var minutes = currentTime.getMinutes();
if(hours < 10)
	{
	 hours = "0" + hours;
	}
if (minutes < 10){
	minutes = "0" + minutes;
}	
var fromTime=hours + ':' + minutes;	
var factId = jQuery('#factory').val();
var sectId = jQuery('#section').val();
var cellId = jQuery('#cell').val();
var flid = jQuery("#frmclitCal input[id='flid']").val();
var dataString = '?q=2&factId='+factId+'&sectId='+sectId;
    dataString += '&cellId='+cellId+'&fromTime='+fromTime+"&flid="+flid;
//alert('setShift : '+dataString); 
processAjaxCalls('cmb_shift.clitcal',dataString,'getShift','getShiftErr');


}


function  getShift(record)
	{  
  	reloadCombo("frmclitCal","cmbClitShift","combo_shift.brdn" );
	//jQuery('#cmbClitShift').combobox('setValue',record.shift); 
	//readOnlyFields("cmbClitShift");
	//	if( record.shift == undefined || record.shift == "" )  
	//		alert("Unallocated Shift time!");
	           	
}

jQuery("#btnExcel").click(function(){
	window.open("jhClitcalendar_getExportExcel.jhcal","Excel View");
});

jQuery("#view_Data").click(function(){


	var fact = jQuery("#frmclitCal input[id='factory']").val();
	var sect = jQuery("#frmclitCal input[id='section']").val();
	var cellId = jQuery("#frmclitCal input[id='cell']").val();
	var machId = jQuery("#frmclitCal input[id='machine']").val();
	var flid = jQuery("#frmclitCal input[id='flid']").val();
	
//	alert(machId+" machId "+fact +" fact " +sect +" cell "+cellId);

	var factId = jQuery("#frmclitCal input[id='factory']").val();
	var sectionId = jQuery("#frmclitCal input[id='section']").val();
	//var cellId = jQuery("#frmclitCal input[id='cell']").val();
	var machId = jQuery("#frmclitCal input[id='machine']").val();
	
	
	var date =new Date();
	var currentDate=date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
	
	//alert( "date" + currentDate);
	

	
	var cellId=jQuery('#cmbClitCellid').combobox('getValue');
	var mchineId=jQuery('#cmbClitEquipment').combobox('getValue');
	var shift = jQuery('#cmbClitShift').combobox('getValue');
	
	if(cellId== null || cellId=='' || cellId =='undefined'){
          alert(' Select Cell ');
		}
	else if(mchineId== null || mchineId=='' || mchineId =='undefined'){
        alert(' Select Equipment ');
		}
  	else if(shift== null || shift=='' || shift =='undefined'){
        alert(' Select Shift ');
		}
	else

		processGridnew('clitCalendar_input.clitcal','?q=2&factId='+factId+'&sectionId='+sectionId+'&cellId='+cellId +'&machId='+machId+'&flid='+flid+'&currentDate='+currentDate+'&shiftId='+shift+'',"listGrid","pager_calender","","","","jhCalLoadComplete");//,"","","","jhCalLoadComplete");
	
});


function clrFormatter(cellvalue, options, rowObject)
{		
	 if(cellvalue=="-1")	{
		formatStr = '<span  style="background-color:#E1F043;font-weight:bold;font-size:14;padding-bottom:11px;"  class="cellWithoutBackground"></span>' ;//formatStr = "<span></span>  " ;
	}
	 else if(cellvalue=="1")	{
			formatStr = '<span  style="background-color:#57EB07;font-weight:bold;font-size:14;padding-bottom:11px;"  class="cellWithoutBackground"></span>' ;//formatStr = "<span></span>  " ;
		}
	else if(cellvalue=="0")	
		formatStr = '<span  style="background-color:#C0C0C0;font-weight:bold;font-size:14;padding-bottom:11px;"  class="cellWithoutBackground"></span>' ;//formatStr = "<span></span>  " ;
    else
		formatStr =cellvalue;	
 return  formatStr;

}





jQuery("#btnSave").click(function(){
	var date =new Date();
	//var dateTime = getTime();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	
	var currentDate=date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
	var sysTime =hours+":"+minutes;
	var status=null;
	var observation=null;
	var cellId=jQuery('#cmbClitCellid').combobox('getValue');
	var mchineId=jQuery('#cmbClitEquipment').combobox('getValue');
	var shiftId=jQuery('#cmbClitShift').combobox('getValue');
	if(jQuery("#chkNotOk").is(":checked") == true){
	 status='Not OK';
	 observation =jQuery('#txtObservation').val();
		}
	else {
		 status='OK';
		}
	
	//alert(shiftId);
	var selArray ;
	selArray= jQuery("#listGrid").jqGrid('getGridParam', 'selarrrow');
	var selrowid="";
	 var jsonArr='';
  if(selArray !=null && selArray!=" " && selArray!=""){
	
	for(var i=0;i<selArray.length;i++)
	{
      //alert("INSIDE THE LOOP");
		selrowid=selArray[i];
       //jsonArr+='[';
	   var refId =jQuery("#listGrid").jqGrid('getCell', selrowid,"REFID");
	  var Keyid=refId.slice(0,-2);
	//  alert(Keyid +" Keyid");
	   var Criteriasplit= Keyid.split(',');
	 	for(var k=0;k<Criteriasplit.length;k++){
		 	var Keyidval=Criteriasplit[k]; 
		 	var keyvalSplit=Keyidval.split(";");
		 	var keyvalu=keyvalSplit[0];
		 	//jsonArr += '"'+keyvalu + '",';	
		 //	alert("keyvalu"+keyvalu);
		 	jsonArr += '"'+keyvalu + '",';	
 	    }
	 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += ',';
	    //alert("jsonArr "+jsonArr);
	}	
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
    var activityList=jsonArr;
   
        if(activityList!=null)
    	{
        //    alert(2569 +"  " +activityList);
        	
        processAjaxCalls("clitCalendar_save.clitcal","q=2?&actvity="+activityList+"&cellId="+cellId+"&mchineId="+mchineId+"&shiftId="+shiftId+"&status="+status+"&observation="+observation,'getCopyEquipment','getCopyEquipmentError');	
      
    	}
		
  }
  else{
	  
	  alert('Pls. Select Activity To Complete');
  }

});

function getCopyEquipment(result)
{
	var rslt=result.stnd.result;
	//alert(rslt);
	if(rslt=="success")
		{
		  alert("Data Saved Successfully !!");
			jQuery('input:checkbox[name=chkNotOk]').attr('checked',false);
			jQuery('#txtObservation').val("");
			readOnlyFields('txtObservation');
			jQuery('#btnSendMail').hide();
	    	jQuery("#listGrid").trigger("reloadGrid");
	    	
			
		}
	else if(rslt=="error")
		{
		alert("Data Not Saved !!");
		}
	 
}
jQuery('#btnSendMail').click(function(){
	
	var date =new Date();
	//var dateTime = getTime();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	
	var currentDate=date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
	var sysTime =hours+":"+minutes;
	var status=null;
	var observation=null;
	var cellId=jQuery('#cmbClitCellid').combobox('getValue');
	var mchineId=jQuery('#cmbClitEquipment').combobox('getValue');
	var shiftId=jQuery('#cmbClitShift').combobox('getValue');
	var tagClass =null;
	
	if(jQuery("#chkNotOk").is(":checked") == true){
	 status='Not OK';
	 observation =jQuery('#txtObservation').val();
	 tagClass = jQuery('#cmbAbnmTagclassid').combobox('getValue');
		}
	
	
	//alert(shiftId);
	var selArray ;
	selArray= jQuery("#listGrid").jqGrid('getGridParam', 'selarrrow');
	var selrowid="";
	 var jsonArr='';
  if(selArray !=null && selArray!=" " && selArray!=""){
	
	for(var i=0;i<selArray.length;i++)
	{
      //alert("INSIDE THE LOOP");
		selrowid=selArray[i];
       //jsonArr+='[';
	   var refId =jQuery("#listGrid").jqGrid('getCell', selrowid,"REFID");
	  var Keyid=refId.slice(0,-2);
	//  alert(Keyid +" Keyid");
	   var Criteriasplit= Keyid.split(',');
	 	for(var k=0;k<Criteriasplit.length;k++){
		 	var Keyidval=Criteriasplit[k]; 
		 	var keyvalSplit=Keyidval.split(";");
		 	var keyvalu=keyvalSplit[0];
		 	//jsonArr += '"'+keyvalu + '",';	
		 //	alert("keyvalu"+keyvalu);
		 	jsonArr += '"'+keyvalu + '",';	
 	    }
	 	jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += ',';
	    //alert("jsonArr "+jsonArr);
	}	
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
    var activityList=jsonArr;
   
        if(activityList!=null)
    	{
        //    alert(2569 +"  " +activityList);
        	
        processAjaxCalls("clitCalendar_save.clitcal","q=2?&actvity="+activityList+"&cellId="+cellId+"&mchineId="+mchineId+"&shiftId="+shiftId+"&status="+status+"&observation="+observation+"&tagClass="+tagClass,'dataSaved','dataSavedError');	
      
    	}
		
  }

 
	 });
function dataSavedError(result){
	var rslt=result.stnd.result;
	alert(rslt +"Error");
	
}

function dataSaved(result)
{
	var rslt=result.stnd.result;
	
	if(rslt=="success")
		{
		
		var section = jQuery("#frmclitCal input[id='section']").val();
		
		var cellName = jQuery('#cmbClitCellid').combobox('getText');
		var machineName=jQuery('#cmbClitEquipment').combobox('getText');
		var observation=jQuery('#txtObservation').val();
       
	//	alert(cellName+" sectiontsectiont  " + mchineName +" observation "+ observation);
		
		var selRow= jQuery("#listGrid").jqGrid('getGridParam', 'selrow');

		   var refid =jQuery("#listGrid").jqGrid('getCell', selRow,"KEYID");
		   
		   var assembly =jQuery("#listGrid").jqGrid('getCell', selRow,"WHATACTIVITY");

			  var activity =jQuery("#listGrid").jqGrid('getCell', selRow,"ASSEMBLY");

		//alert(section+" 0005 "+machineName+"  mchineName  "+cellName+"  ,, sectiont  "+observation+ ' assembly '+assembly +" activity "+activity);
		LoadPopUp("divMailList","employee_mail_input.clitcal?sectionId="+section+"&refid="+refid, true,"40%","300px","200px","50px", null, 'Send Email',false, false,true);

			jQuery('input:checkbox[name=chkNotOk]').attr('checked',false);
			//jQuery('#txtObservation').val("");
			//readOnlyFields('txtObservation');
			//jQuery('#btnSendMail').hide();
	    //	jQuery("#listGrid").trigger("reloadGrid");
	    	
		}
	else if(rslt=="error")
		{
		alert("Data Not Saved !!");
		}
	 
}

 function chkNotOkClick(){
	
	 if(jQuery('#chkNotOk').is(":checked")) 
		 {
		 var rowid = jQuery('#listGrid').jqGrid('getGridParam', 'selrow');
		 if(rowid == null || rowid == 'undefined' ){
			 
			 			
			 alert("First Select Actitity.");
			    readOnlyFields('txtObservation');
				jQuery('input:checkbox[name=chkNotOk]').attr('checked',false);
				jQuery('#btnSendMail').hide();
				jQuery('#abnmTagclassid').hide();
		 }
		 else{
		 enableFields('txtObservation');
		 jQuery('#btnSendMail').show();
		 jQuery('#abnmTagclassid').show();
		 jQuery('#btnSave').hide();
		 
		 }
		 }
	 else{
	    	 jQuery('#btnSendMail').hide();
	         jQuery('#abnmTagclassid').hide();
	         jQuery('#txtObservation').val('');
	         readOnlyFields('txtObservation');
	         jQuery('#btnSave').show();
 }
}


 </script>

<div id= "wrapperRpt" >

<!--  <input type="hidden" name="hdnFromStandard" id="hdnFromStandard"  value="${requestScope.keyId}" />   -->     
        <form name="frmclitCal" id="frmclitCal" method="post">
         <input type="hidden" name="exporthtml" id="exporthtml" />
        <div id="JHCLIT" class="divbrdr"  style="margin-top:-10px;margin-top:-4px\9;width:109%"> 
	<table  >  
			 <tr >
			 	<td colspan='3' style =  "display:none; ">
			 	<div  id="frmclitCalFuntKeyIds"  >
				<input type="hidden" id="factory" name="cmbClisFactoryid" value="${requestScope.cliTlStandards.clisFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbClisSectionid" value="${requestScope.cliTlStandards.clisSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbClisCellid" value="${requestScope.cliTlStandards.clisCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbClisMachineid" value="${requestScope.cliTlStandards.clisMachineid}"  ></input>
				<input type="hidden" id="flid" name="cmbClisFlid" value="${requestScope.cliTlStandards.clisFlid}" ></input>
				</div>
			 			<div id="clisfunLocation" style="margin-left: 60px;   "></div>
			 	</td> 
			 </tr>
			 	</table>
			 	</div>      
        
          
        <div style="margin-left:0px;color:blue;font-weight:bold;vertical-align:top;">
        <table>       
          <tr><td> 
            <label  class="mandatory-lbl" id="lblCell">Cell</label>
            </td>
            <td> 
            <label  class="mandatory-lbl" id="lblEquipment" style="margin-left: 25px;">Equipment</label>
            </td>
           
            </tr>
            <tr>
          <td style="font-size: 12px;  margin-left:20px;">

		   			<input type="text" class="easyui-combobox"  id="cmbClitCellid" name="cmbClitCellid" value="${requestScope.cellId}" style="width: 200px;"/>
          </td>
          <td style="font-size: 12px;  padding-left:20px;">

		   			<input type="text" class="easyui-combobox"  id="cmbClitEquipment" name="cmbClitEquipment" value="${requestScope.MachineId}" style="width: 250px;"/>
          </td>
           <td style="Padding-left:10px;">
          <input type="button" value="View" class="easyui-button" id="view_Data"/>  
          </td>
         
          <!--       
         <td style="font-size: 12px;  padding-left:20px;">

				<input type="button" value="View" class="easyui-button" id="view_Data"/>   
				       </td>
           <td style="font-size: 12px;  padding-left:20px;">
          	<input type="button" id="btnExcel" class="easyui-button" value="Export Excel" /> 
          	  </td> -->
          
          </tr></table>
          
        </div>
          <table >
          <tr>
           <td> 
             <label  class="mandatory-lbl" id="lblShift" style="margin-left: 0px;">Shift</label>
            </td>
            <td style=" width : 78px;"> 
             <label   id="lblnotOk" style="margin-left: 10px; font-weight: bold;">Not OK</label>
            </td>
            <td style=" width : 193px;"> 
             <label   id="lblRemark" style="margin-left: 0px; font-weight: bold;">Observations</label>
            </td> 
            <td style=" width : 193px;"> 
             <label   id="lblTag" style="margin-left: 0px; font-weight: bold;">Tag</label>
            </td>            
            </tr>
            <tr>
            
          <td style=" padding-left:0px;">
		   			<input type="text" class="easyui-combobox"  id="cmbClitShift" name="cmbClitShift" value="${requestScope.shift}" style="width: 40px;"/>
          </td> 
          <td style=" padding-left:10px;">
		   			<input type='checkbox' class="easyui-checkbox" style="width:20px" id="chkNotOk" name="chkNotOk" value="1" onchange="chkNotOkClick()" />
          </td> 
          <td style=" padding-left:0px;">
		   			<textarea class="easyui-text" style=" text-transform: uppercase; width : 300px; height : 40px; font-size: 12px" rows="2"  maxlength="500px" id="txtObservation" name="txtObservation"></textarea>
          </td>
          
          <td style="Padding-left:10px;">
          
                    	<input type="button" id="btnSave" class="easyui-button" value="Save" />  </td>
                       <td> 
                       <div style="display:none" id="abnmTagclassid" >
                        <select   id="cmbAbnmTagclassid" name="cmbAbnmTagclassid" class="easyui-combobox"  style="width:60px;font-size: 12px;height:30px;"   >   
                        	<option value=""> </option>
                        	<option title="RED" value="RED">RED</option>
                        	<option title="RED" value="WHITE">WHITE</option>
                        	</select>
                        	</div>
                        	 </td>
                    	 <td style="Padding-left:10px;">
             	<input  class="easyui-button" type="button"   value ="E-Mail" id="btnSendMail" name="btnSendMail"   />
          
          </td>
          
          
          </tr>
</table>
<table>
          <tr>
          <td style="font-size: 12px; Padding-left:0px;">
		  <span class="jh_Calen_planed"  style="padding-top:2px;">
          </span>
          </td>
          <td>Plan Not Available</td>          
		  <td  >
		  <span class="jh_Calen_planned" > </span>
          </td><td>Planned</td>
           <td  style="font-size: 12px; ">
           <span class="jh_Calen_completed"  >
          </span>
          </td><td>Completed</td>
         </tr>
          </table>
        
   		<div class="table-responsive">
   			<table id="listGrid" border="1" style="width:105%" ><tr><td></td></tr></table>
						
			<div id="pager_calender"></div>
			
	</div>
		</form>
		</div>
		