<script>
jQuery(document).ready(function(){	
	
	
	var mainForm = jQuery("#mainFormValClti").val();
	var momdocid=jQuery("#hdnMomRefDocId").val();
	//alert(momdocid);
	var momrefType=jQuery("#hdnMomRefDocType").val();
	//alert(momrefType);
	
					if (mainForm !=true) 
					{
					  jQuery(".clitdiv").attr('id', 'wrapperRpt');
					}
					setLoadFormCallBackFrmId("frmMomMaingrid");
					invokeAfterLoadFormCallBack();
	//viewGrid("mom_input.mom","q=2"+'&momRefDocId='+jQuery("#hdnMomRefDocId").val()+'&momRefDocType='+jQuery("#hdnMomRefDocType").val());
	var url = jQuery('#hiddenUrl').val();
	
	var mode = "VIEW";
	var frmMode = jQuery('#hdnMomMode').val();
	
	var frmType = jQuery('#hdnMomType').val();
	 // alert("frmtype>>"+frmType);
	var stage = jQuery('#hdnmomstage').val();
	jQuery('#btnNew').css("display:none"); 
	fillComboBox("frmMomMaingrid","cmbMomasShiftid","shift.commonFilter");
	
	fillWithCurrentDate('hdnmomdate');
	
	fillcurrecntshift();

	//alert(" frmMode :: "+frmMode+" frmType :: "+frmType+" stage :: "+stage);

    if(frmMode == "view" && frmType =="FIP" || (stage.trim().length==0 && frmType !="FIP" && frmType !="JH" && frmType !="Dmt" && frmType !="Pillar" && frmType !="Production" && frmType !="Others")){
    	
    	//jQuery('#btnNew').show();
    	jQuery('#btnNew').hide();
	}else if(frmMode == "view" && frmType=="FIP"|| ( stage.trim().length==0 && frmType !="FIP" && frmType =="JH"  )){
		jQuery('#btnNew').hide();
		
	}else if (frmType=="Dmt"){
		jQuery('#btnNew').hide();
	}else if (frmType=="Pillar"){
		jQuery('#btnNew').hide();
	}else if (frmType=="Production"){
		jQuery('#btnNew').hide();
	}else if (frmType=="Others"){
			jQuery('#btnNew').hide();
    }
	else if (frmType=="UMC"){
		jQuery('#btnNew').hide();
}
	else if (frmType=="OGM"){
		jQuery('#btnNew').hide();
}
    if (frmType=="FIP"){
  
	jQuery('#btnNew').show();
	toggleCommonFilter(false);
	//var ds='&hdnMomRefDocId'
	//alert(viewGrid(url,"?"));
	//var dataString1 ="";
	//dataString1+="&momdocid="+momdocid;
	//alert(dataString1);
	processGridnew(url,dataString+'&momRefDocId='+jQuery("#hdnMomRefDocId").val()+'&momRefDocType='+jQuery("#hdnMomRefDocType").val(),"list","pager",tableCaption," ");
	//viewGrid(url,dataString1); 
    }
	
    var dataString ="";
	dataString += "&FORM_MODE="+mode + "&ViewClicked=Y";
	var filterStr = jQuery("#hdnFilterStr").val();
	if( filterStr != null && filterStr.length > 0 )
	{
		var dataString = jQuery("#hdnFilterStr").val();
		var tableCaption = "Equipment Query";
		processGridnew(url,dataString+'&momRefDocId='+jQuery("#hdnMomRefDocId").val()+'&momRefDocType='+jQuery("#hdnMomRefDocType").val(),"list","pager",tableCaption," ");
	}
	else{
		//viewGrid("mom_input.mom","q=2"+'&momRefDocId='+jQuery("#hdnMomRefDocId").val()+'&momRefDocType='+jQuery("#hdnMomRefDocType").val());
		}

 //alert(" Inside :: "+jQuery('#hdnDMT').val());

	 var DMT=jQuery('#hdnDMT').val();
	 
	 if(frmMode != "view" || frmType =="FIP"){
		
		 jQuery ("#btnNew").click(function()
					{	
						var momType = jQuery('#hdnMomType').val();
						//var momType = jQuery('#type').val();
				        var flid = getFieldValue("hdnLoginFlid");
				        var momdate = getFieldValue("hdnmomdate");
				        var shift =getFieldValue("cmbMomasShiftid");
				       // alert(" hdnflid :: "+flid+" hdnmomdate "+hdnmomdate+" shift :: "+shift);
						 if(momType=="DEPEHS")
							momType="DEC";
						 else if(momType=="CENEHS")
							 momType="CEC"; 
						 
						 //alert(" momType :: "+momType);
					//navigateToNextForm("MoMeetingForm_input.mom?&filterButton=false&DMT="+DMT+'&DMTDBLE=DMTDBLE'+'&momRefDocId='+jQuery("#hdnMomRefDocId").val()+'&momRefDocType='+jQuery("#hdnMomRefDocType").val(),"Minutes of Meeting");
						var ds = "?&recall=Y&filterButton=false&type="+momType +"&DMT="+DMT+'&DMTDBLE=DMTDBLE';
						    ds +="&momRefDocId="+ jQuery('#hdnMomRefDocId').val() + '&momRefDocType='+jQuery('#hdnMomRefDocType').val();
						    ds +="&shift="+shift + "&Date="+momdate+"&flid="+flid;
						   // ds+="menumode=modify"
				//	 closePopUpDialoge("pager");
						   LoadPopUp("FiptoMom","MoMeetingForm_input.mom"+ds, true,"85%","80%","3%","1%", "popup_callback()",momType+" Minutes of Meeting"," ",true,true);
						//   navigateToNextForm("MoMeetingForm_input.mom"+ds ,momType+" Minutes of Meeting");	
					});
		
	}
    var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnView").val(btnName);
	jQuery("#btnView").click(function()
			{		
		processAjaxCalls("openFile.file?fileName=QMMOMAttendance.xls", "", "", "", "", "new");					
	});
	
});
function frmMomMaingrid_afterLoadCallBack(){
	toggleCommonFilter();  
	}  
function fillcurrecntshift(){
	
	var serverTime = srvTime();
	var currentTime = new Date(serverTime);
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	
	if (minutes < 10){
		minutes = "0" + minutes;
	}		
	
	var factId = "";
	var dataString = '?q=2&fromTime='+hours + ':' + minutes;
	
	processAjaxCalls('txt_shift.brdn',dataString,'getMOMNwShift','getMOMShiftErr');
	
}

function getMOMNwShift(record)
{
	jQuery('#cmbMomasShiftid').combobox('setValue',record.shift);
}

	function viewGrid(url,dataString)
	{
		if(dataString=="?")
			
			processGridnew("mom_input.mom",dataString,"list","pager","","docDoubleClick");
	   else if(validateFilterSelection(dataString))
		{   
			var flid = getFilterValue(dataString, 'flid');
			var momType = jQuery('#hdnMomType').val(); 
			var modeForFunction = jQuery('#hdnMomMode').val();
			//alert("mode value"+mode);
			if (flid.length<3)
				flid=jQuery('#hdnLoginFlid').val();
			dataString += "&momRefDocId="+jQuery("#hdnMomRefDocId").val();
			dataString += "&momRefDocType="+jQuery("#hdnMomRefDocType").val();
			dataString += '&flid='+flid
			dataString += "&mode="+modeForFunction;
			
			if(momType.trim().length>0)
				dataString += '&Type='+momType;
				
			processGridnew("mom_input.mom",dataString,"list","pager","","docDoubleClick");
		    return true;
		}
		
	}

	
	function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
		
		/*if(filterString=="?q=2")
			return true;

		if(getFilterValue(filterString, "flid") == "" ){
			alert(" Select JH ");
			return false;
		}
		if(getFilterValue(filterString, "dtFromDate") == "" ){
			alert(" Select From Date ");
			return false;
		}
		if(getFilterValue(filterString, "dtToDate") == "" ){
			alert(" Select To Date ");
			return false;
		}*/
		    return true;
	}



function docDoubleClick(id)
{	
	
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var keyid = rowData.KEYID;
	//alert("keyid "+keyid);
	var Flid =  rowData.FLN;
	var Date =  rowData.MDATE;
	
	//alert(" Date :: "+Date);
	var MEETHAPPEN =  rowData.MEETHAPPEN;
	//alert(" MEETHAPPEN :: "+MEETHAPPEN);

	//alert(" Inside :: "+jQuery('#hdnDMT').val());

	var DMT=jQuery('#hdnDMT').val();

	var frmMode = jQuery('#mode').val(); 
	var momType = jQuery('#hdnMomType').val(); 
	var frmMode = jQuery('#hdnMomMode').val();
	 
	 if(momType=="DEPEHS")
		 momType="DEC";
	 else if(momType=="CENEHS")
		 momType="CEC"; 

	var ds ='?&filterButton=false&grid=true&mode='+frmMode + '&keyId='+keyid+'&Date='+Date;
	ds+= '&MEETHAPPEN='+MEETHAPPEN+'&DMT='+DMT+'&momRefDocId='+jQuery("#hdnMomRefDocId").val();
	ds+= '&momRefDocType='+jQuery("#hdnMomRefDocType").val();
	ds+= '&type='+momType;
	ds+= '&mode='+frmMode;
	ds+='&menumode=modify'
	//alert(" ds :: "+ds);
	navigateToNextForm('MoMeetingForm_input.mom'+ds,momType + " Minutes Of Meeting");
		//LoadPopUp("frmMom","MoMeetingForm_input.mom"+ds, true,"95%","90%","3%","1%", "popup_callback()",momType+" Minutes Of Meeting"," ",true,true );
}


</script>
<form id="frmMomMaingrid" name="frmMomMaingrid">
  <div id="WrapperRpt" style="width:100%">
<table style="margin-top:-20px;">
 <tr>

	<td>
      <div style="padding-left:4px;"><input class="easyui-button" type="button" id="btnNew" name="btnNEW" value="New Entry"  >
    <span style="padding-left:10px;"><input class="easyui-button" type="button" id="btnView" name="btnView" value="View Report" style="display:none;"></span>
    <span style="padding-left:10px;display:none;">
		<input class="easyui-combobox" id="cmbMomasShiftid" name="cmbMomasShiftid"  style=" width : 80px;display:none;" value=" " />
		</span>
    <span style="padding-left:0px;"><label class="notes"> Double Click the Data row to view the Details </label></span>
    
   </div></td>
    </tr> 
</table> 
</div> 
<div style="padding-left:45px;">
    <table id="list" ><tr><td></td></tr></table>
	<div id="pager"></div>
	<div id="paramDiv" style="display:none;" title="param">
	</div>
</div>

    <input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>
	<input type="hidden" id="mainFormValClti" value="${requestScope.mainForm}"/>
	<input type="hidden" id="hdnBtnName" value="View Report"/> 
	<input type="hidden" id="hdnDMT" name="hdnDMT" value="${requestScope.DMT}"/>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdnMomRefDocId" name="hdnMomRefDocId" value="${requestScope.momRefDocId}"/>
	<input type="hidden" id="hdnMomRefDocType" name="hdnMomRefDocType" value="${requestScope.momRefDocType}"/>
	<input type="hidden" id="hdnMomType" name="hdnMomType" value="${requestScope.type}"/>
	<input type="hidden" id="hdnMomMode" name="hdnMomMode" value="${requestScope.mode}"/>
	<input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value=""/>
	<input type="hidden" name="hdnmomstage" id="hdnmomstage" value="${requestScope.stage}"/>
	<input type="hidden" name="hdnmomdate" id="hdnmomdate" value=""/>
</form>
	
	