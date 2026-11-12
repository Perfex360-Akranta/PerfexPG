<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script type="text/javascript">	
	jQuery(document).ready(function(){	
		initialiseForm('frmAccidentIncident');	    	  
	    jQuery('#submitForm').val('frmAccidentIncident'); 
	 
	    var Keyid =jQuery("#hdnkeyid").val();
	   // alert("hi"+jQuery("#txtsinmExactDesc").val());
	    btndefaulthid();
		formatDateBox('dtesinmAnalysedDate','dd-MMM-yyyy');
		fillComboBox("frmAccidentIncident","cmbsinmAccidentType","IncidentTypeCombo.accIncRpt");
		fillComboBox("frmAccidentIncident","cmbsinmAnalysedShift","combo_shift.brdn");
		fillComboBox("frmAccidentIncident","cmbsinmAnalysedBy","employee.commonFilter");
		fillComboBox("frmAccidentIncide	nt","cmbsinmExactType","IncidentTypeCombo.accIncRpt");
		formatDateBox('dtesinmAnalysedDate','dd-MMM-yyyy');	
		spinnerKeyPress('spnsinmAnalysedtime');			
		spinnerChange('spnsinmAnalysedtime','analysedTimeSpnEvt');
		
	
		spinnerUp('spnsinmAnalysedtime','analysedTimeSpnEvt');
		spinnerDown('spnsinmAnalysedtime','analysedTimeSpnEvt');
		//fillWithCurrentDate('dtesinmAnalysedDate');
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		
		var imgUrl =jQuery("#imgVsodImgtoolusedFilename").val();
		var Imgtype=jQuery("#hdnimageType").val();
		//alert("The imgUrl::::"+imgUrl);
		
		console.log("Before imageUpload initialization");

console.log("Button exists:", jQuery("#dlgImg").length);



		 
		    imageUpload(jQuery( "#dlgImg" ),'ImageUpload.commonFilter?','dlgImg',"imgVsodImgtoolused","imgVsodImgtoolusedFilename","300","200",false);
		    
		    console.log("After imageUpload initialization");

		    console.log("Generated input:", jQuery("#dlgImg_file").length);
		    
		    // viewGrid("IvestigationRpt_input.ehsaccIncRpt","?&keyId="+jQuery("#hdnkeyid").val());
	        processGridnew("IvestigationRpt_input.ehsaccIncRpt","?&keyId="+jQuery("#hdnkeyid").val(),"AccGrid","pager1","","doubleClickGrid","");
	        var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
			fillAnalysisShift();
			if(analysedDate != null && analysedDate != '' && analysedDate != ' ')
			{
				//setDatenTimeFlag('dtesinmAnalysedDate','spnsinmAnalysedtime');
				
			}
			
	 
	  		 disableUIButton('btnYYAccident');
	  		
	  	   var rootcause = jQuery('#hdnrootcause').val();
		   if(rootcause=="A")
			   {
			   jQuery('#chbUnsafeAct').attr('checked',true);
			   }
		   
		   if(rootcause=="C")
		   {
		   jQuery('#chbUnsafeCondn').attr('checked',true);
		   }


				
	});	
	
	
	jQuery("#btnRiskAsses").click(function(){
		  var keyid =jQuery("#hdnkeyid").val();
		if(keyid==null)
		{
			popupCommonErrorMsg("Please Save Incident Recording");
			return false;
		}
		else{
			navigateToNextForm("RiskAssesment_view.risk");
		}
		
		
	}
    );

	jQuery("#btnvisualsave").click(
			function() {
				
		var imgpath= getFieldValue("imgVsodImgtoolused");
	  	var imagename =jQuery("#imgVsodImgtoolusedFilename").val();
		var image=imagename.lastIndexOf("/");
	 	var imageName= imagename.substring(image+1,imagename.length);
		var imagefullname = imageName.toUpperCase();
		var keyid=jQuery("#hdnkeyid").val();
	//	alert("imagefullname:::"+imagefullname);
		var type=jQuery("#hdnimageType").val();
		//alert("type"+type);
		if(type.length==0)
			{
		
		saveForm("frmAccidentIncident","IvestigationRpt_save.ehsaccIncRpt?&q=2&imgname="+imagefullname+"&keyid="+keyid,"");
			}
		else
			{
			
			saveForm("frmAccidentIncident","IvestigationRpt_save.ehsaccIncRpt?&q=2&imgname="+imagefullname+"&keyid="+keyid+"&type="+type,"");
			}
			});
	function analysedTimeSpnEvt()
	{
		var analysedDate = jQuery('#dtesinmAnalysedDate').datebox('getValue');
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		var analysedTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
		
		compareDates('dtesincReportedDate','spnsincReportedtime','dtesinmAnalysedDate','spnsincAnalysedtime','Analysed Date should not be lesser than Reported Date');
		if(analysedTime.indexOf(':') == 1)
			analysedTime = '0'+analysedTime;
		var dataString = '?q=2&';
	    	dataString += 'fromTime='+analysedTime;
	    	//alert(dataString);
		processAjaxCalls('txt_shift.brdn',dataString,'getAnalysedShift','getAnalysedShiftErr');	
	}
	function fillDefaultShift()
	{
		var fromTime = jQuery('#spnsinmOccurredtime').spinner('getValue');
		jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&fromTime='+fromTime;
		processAjaxCalls('txt_shift.brdn',dataString,'fillShift','fillShiftErr');
	}
	function fillAnalysisShift()
	{   jQuery('#spnsinmAnalysedtime').spinner('setValue',jQuery("#hdntime").val());
		var fromTime = jQuery('#spnsinmAnalysedtime').spinner('getValue');
	//	alert("fromTime"+fromTime);
		if(fromTime.indexOf(':') == 1)
			fromTime = '0'+fromTime;
		var dataString = '?q=2&fromTime='+fromTime;
		processAjaxCalls('txt_shift.brdn',dataString,'getAnalysedShift','getAnalysedShiftErr');
	}
	function compareDates(fromDateId,fromTimeId,toDateId,toTimeId,errMsg)
	{
		var fromDate = jQuery('#'+fromDateId).datebox('getValue') + jQuery('#'+fromTimeId).spinner('getValue');
	    var toDate = jQuery('#'+toDateId).datebox('getValue') + jQuery('#'+toTimeId).spinner('getValue');
		var currentDate = getServerDateTime();
		if(convertStringToDate(toDate) > currentDate)
		{
			showValidationErrorMsg(toDateId,'Should Not Exceed Current Date/Time');
			jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
			jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
		}
		else if( compareDateTime(toDate,fromDate) > 0 )
	    {
	    	showValidationErrorMsg(toDateId,errMsg);
	    	jQuery('#'+toDateId).datebox('setValue',jQuery('#'+fromDateId).datebox('getValue'));
			jQuery('#'+toTimeId).spinner('setValue',jQuery('#'+fromTimeId).spinner('getValue'));
	    }
	    else
	    	clearValidationErrorMsg(toDateId);	
	}
	function setDatenTimeFlag(dateId,timeId)
	{
		var dateFlag = jQuery('#'+dateId).datebox('getValue');
		///alert("dateFlag"+dateFlag);
		
		if(dateFlag != null && dateFlag != '' && dateFlag != ' ')
		{ 
			
			if(dateFlag == '01-Jan-1801 00:00:00')
			{
				if(dateId == 'dtesinmAnalysedDate')
				{
					fillWithCurrentDate('dtesinmAnalysedDate');
					fillWithCurrentDate('spnsinmAnalysedtime');
					fillAnalysisShift();
				}
				else
					jQuery('#'+dateId).datebox('clear');
			}
			else if(dateFlag == '31-Dec-2100 00:00:00')
				jQuery('#'+dateId).datebox('clear');			
			else
			{
				jQuery('#'+timeId).spinner('setValue',dateFlag.substring(dateFlag.indexOf(' ')+1,dateFlag.indexOf(' ')+6));
				jQuery('#'+dateId).datebox('setValue',dateFlag.substring(0,dateFlag.indexOf(' ')));
			}
		}
		
	}

	jQuery("#btnInvReport").click(function(){
			var documentNo =jQuery("#txtsinmKeyid").val();	 		
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"INC","","","");
				
			}
			else
	 			alert("Incident No. should be selected to view FileManager");
			
		});
	
	function txtFormatterimg(id, options, rowObject) {
		   // alert("image Formateer");
			var id = options.rowId;
			//alert("The id"+id);
			var columnName = options.colModel.name;
			//alert("The columnName::"+columnName);
			var columnNo = 3; 
			//alert("The columnNo:::"+columnNo);
			var gid=options.gid;
			//alert("gid"+gid);
			//alert("The gid:::"+rowObject[2]);
			if(id==1)
				{
			var imageUrl=rowObject[2];
				}
			else{
				var imageUrl=rowObject[2];
			}
			
			var modifyurl=imageUrl.search("tmp/images/");
			if(modifyurl=="-1")
				{
				imageUrl="tmp/images/"+imageUrl;
				}
			//alert("modifyurl"+modifyurl);
			//alert("imageURL"+imageUrl);
			idval='imghand_';
		//	alert("idval:::"+idval);
			var prorpertyHover="";
			var prorpertyleave="";
			if(gid=="AccGrid"){
				 prorpertyHover='gotMousehover(this.id,'+id+')';
				 prorpertyleave='gotMouseleave(this.id)';
			}
			if(columnNo==3){ 
				//alert("Inside the columnNo"+columnNo);
				return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'"   src="'+imageUrl+'" align="middle" width="100px" height="20px" style="cursor: pointer;" />';
				}else{
				return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'" src="'+imageUrl+'"   width="100px" height="20px" align="middle"  style="cursor: pointer"/>';
			}
			imageUrl=" ";
		}
	
	 function viewGrid(url,dataString)
	{
		processGridnew("IvestigationRpt_input.ehsaccIncRpt","?&keyId="+jQuery("#hdnkeyid").val(),"AccGrid","pager1","","","doubleClickGrid");
	} 
	
	 function doubleClickGrid(id){
			btnUpdate();
		    var rowData = jQuery("#AccGrid").jqGrid('getRowData',id);
			//alert("The RowData::::"+rowData);
			var AccKeyid =jQuery("#hdnkeyid").val();
			var keyId = rowData.AccKeyid;
			var imagetype=rowData.IMAGETYPE;
			jQuery("#hdnimageType").val(imagetype);
		//	alert("The image type"+imagetype);
			//var imgId1 =rowData.IMAGE;
			//alert("imgId1:::"+imgId1);
			var imgId ="imghand_"+3+'_'+id;	
			//alert("imgId::Double click:"+imgId);
			//var newurl="tmp/images/"+imgId;
			var imgurl=jQuery("#"+imgId).attr("src");	
			//alert("The imgId::Double click::"+imgurl);
			setFieldValue('imgVsodImgtoolusedFilename',imgurl);
			jQuery('#imgVsodImgtoolused').attr('src',imgurl);	
			//var imgId =rowData.IMAGE;
			//alert("imgId:::"+imgId);
			//var imgurl=jQuery("#"+imgId).attr("src");
		//	var newurl="tmp/images/"+imgId;
		//	alert(newurl);
			//setFieldValue('imgVsodImgtoolusedFilename',newurl);
		//alert("jQuery('#imgVsodImgtoolused')"+jQuery('#imgVsodImgtoolused').attr('src'));
			
		/* 	if(jQuery('#imgVsodImgtoolused').attr('src')=="images/tools.jpg")
				{
				alert(456);
				//jQuery('#imgVsodImgtoolused').attr('src','');
				jQuery('#imgVsodImgtoolused').attr('src',newurl);
				}
			else{
				alert(567)
				jQuery('#imgVsodImgtoolused').attr('src',' ');
				jQuery('#imgVsodImgtoolused').attr('src',newurl);
			} */
			//jQuery('#imgVsodImgtoolused').attr('src',newurl);	
			
		}


	jQuery("#txtsinmExactDesc").keypress(function(){
		 var tval = jQuery('#txtsinmExactDesc').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=1000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmIdea").keypress(function(){
		 var tval = jQuery('#txtsinmIdea').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	
	jQuery("#txtsinmRootcause").keypress(function(){
		 var tval = jQuery('#txtsinmRootcause').val();
	        tlength = tval.length;
		  var acclength=parseInt(tlength);
		  if(acclength>=3000)
			  {
			     alert("Maximum Limit is over");
			     return false;
			  }
	
	});
	

	
	jQuery("#btnYYAccident").click(function(){
		var refDocDate = jQuery('#hdnreportdate').val();
 		var refDocId = jQuery('#hdnkeyid').val();
 		var problem = jQuery('#hdnAccdesc').val();
 		var yyArea = jQuery("#hdnarea").val();
 		var flId= jQuery("#hdnflid").val();
 		var attendby = jQuery('#hdnanalysedby').val();
 		var pillar = "EHS";
 		 openWhyWhy("",false,refDocId,"IRE",flId, refDocDate, problem, "create",null,null,attendby,yyArea,pillar);
	});
	jQuery('#chbUnsafeAct').click(function() {
		jQuery('#chbUnsafeAct').attr('checked',true);
		jQuery('#chbUnsafeCondn').attr('checked',false);
		jQuery('#hdnsinmRootcausedueto').val('A');		
	});
	jQuery('#chbUnsafeCondn').click(function() {
		jQuery('#chbUnsafeCondn').attr('checked',true);
		jQuery('#chbUnsafeAct').attr('checked',false);
		jQuery('#hdnsinmRootcausedueto').val('C');	
	});


	
	jQuery("#btnexcelview").click(function(){
        var incKeyid= jQuery('#hdnkeyid').val();
        var flid = jQuery("#hdnflid").val();
         if(incKeyid != null && incKeyid.length >0){
             window.open("Accincident_Excelview.ehsaccIncRpt?incKeyid="+incKeyid+"&flid="+flid);
         }
         else
        	 {
        	 return false;
        	 }
    });

	jQuery("#btncrtView").click(function(){
	    var keyid=jQuery("#hdnkeyid").val();
	 	var flid = jQuery("#hdnflid").val();
LoadPopUp("loadAccidentModify","safteyincident_input.ehsaccIncRpt?keyId="+keyid+"&mode="+"Modify", true,"90%","80%","3%","5%", "popup_callback()","Accident Modify"," ",true,true );
		
 });
	

	
	jQuery("#btnaccDelete").click(function(){
		var keyid=jQuery("#hdnkeyid").val();
		//alert("The Keyid"+keyid);
		var type=jQuery("#hdnimageType").val();
		//alert("type"+type);
		var d=confirm("Are You Sure To Delete?");
		if(d){
			deleteRecord("frmAccidentIncident","IvestigationRpt_delete.ehsaccIncRpt?&q=2&keyid="+keyid+"&type="+type);
			//alert("Data Deleted Successfully");
		}
	});
	jQuery( "#btnImgClear" ).click(function() {
		jQuery('#imgVsodImgtoolused').attr('src', "images/tools.jpg");
		jQuery('#imgVsodImgtoolusedFilename').val("");
		});
	
	function frmAccidentIncident_successsCallback(result){
		//setFieldValue('txtVsodToolused','');
		  var Keyid =jQuery("#hdnkeyid").val();
		 // alert("Keyid"+Keyid);
		  processGridnew("IvestigationRpt_input.ehsaccIncRpt","?&keyId="+jQuery("#hdnkeyid").val(),"AccGrid","pager1","","","doubleClickGrid");
		setFieldValue('imgVsodImgtoolusedFilename','');
		jQuery('#imgVsodImgtoolused').attr('src','images/tools.jpg');
		//jQuery("#AccGrid").trigger("reloadGrid");

	 }
	function frmAccidentIncident_deleteSuccessCallback(result){
		alert(result.successData.msg);
		alert("delete Successcallback");
		setFieldValue('imgVsodImgtoolusedFilename','');
		jQuery('#imgVsodImgtoolused').attr('src','images/tools.jpg');
		clearForm('frmAccidentIncident');
		jQuery("#AccGrid").trigger("reloadGrid"); 
	}
	jQuery("#btnsave").click(function(){
		//navigateToNextForm('safteyincident_save.ehsaccIncRpt?q=2&bookingMode=Booking','Incident Reporting');
		//saveForm('frmAccidentIncident','safteyincident_save.ehsaccIncRpt?incId='+result.successData.keyId);
		var keyid=jQuery("#hdnkeyid").val();
		//alert("keyid"+keyid);
		//var chko=jQuery("#chbUnsafeAct").val();
		var chkact = ( jQuery("#chbUnsafeAct").is(':checked') ) ? "Y": "N";
		
		var chkcon = ( jQuery("#chbUnsafeCondn").is(':checked') ) ? "Y": "N";
		//var chks=jQuery("#chbUnsafeCondn").val();
		
		var analysedtime=jQuery("#dtesinmAnalysedDate").datebox('getValue');
		var analysedTimeVal = jQuery('#spnsinmAnalysedtime').timespinner('getValue');
		alert("TIme value"+analysedTimeVal);
		
		var shift=jQuery("#cmbsinmAnalysedShift").combobox('getValue');
		var analyseby=jQuery("#cmbsinmAnalysedBy").combobox('getValue');
		var exacttype=jQuery("#cmbsinmExactType").combobox('getValue');
		var exactdesc=jQuery("#txtsinmExactDesc").val();
		var invest=jQuery("#txtsinmIdea").val();
		var recom=jQuery("#txtsinmRootcause").val();
		
		if(exacttype.length==0)
		{
		   popupCommonErrorMsg("Select Accident type");
		   return false;
		}
	
	if(exacttype.length==0)
	{
	   popupCommonErrorMsg("Select Invesigated By ");
	   return false;
	}
	
	if(analysedtime.length==0&&time.length==0)
		{
		 popupCommonErrorMsg("Select Analaysed Date & Time ");
		   return false;
		}
	
	if(shift.length==0)
		{
		 popupCommonErrorMsg("Select Shift");
		   return false;
		}
		//alert("outside");
		navigateToNextForm('safteyincidentanalysis_save.ehsaccIncRpt?q=2&keyid='+keyid+"&time="+analysedtime
				+"&shift="+shift+"&analyseby="+analyseby+"&extype="+exacttype+"&exdesc="+exactdesc+"&inv="+invest+"&rec="+recom
				+"&chkact="+chkact+"&chkcon="+chkcon+"&t1="+analysedTimeVal
				);
		closePopUpDialoge("IncidentCA");
		
		setTimeout(function() {
	    	jQuery("#incidentRptModifcnGrid").trigger("reloadGrid");
	    	},1000);	
		
		
	});	
		
	function btndefaulthid(){
		enableUIButton("btnvisualsave");
		disableUIButton("btnaccDelete");
	}
	
	function btnUpdate(){
		enableUIButton("btnvisualsave");
		enableUIButton("btnaccDelete");
	}

			
</script>

<form id="frmAccidentIncident" >
	<div id="wrapper">

			<table>
			
			<tr>
			  <td valign="top" style="width:40px;margin-left:0px; height:0px;" class="easyui-paddingbfx">
			  			<label class="lblaccIncidentAnalysisdate mandatory-lbl" >Analysis Date Time</label>	
			  			<input id="dtesinmAnalysedDate"  name="dtesinmAnalysedDate" clear="false" class="easyui-datebox" value="${requestScope.analysedate}"  style="width: 202px;"  value = "${requestScope.sheAccIncBean.disableAnalysedDate == true ? ' disabled':''}"/>
			  		<span class="spinner" style=margin-left:210px;margin-top:-30px;>
			  		<input id="spnsinmAnalysedtime" name="spnsinmAnalysedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.sheAccIncBean.sinmAnalysedtime}"  style="width: 60px;"  value = "${requestScope.sheAccIncBean.disableAnalysedtime == true ? ' disabled':''}">
			  		</span>			      
			  	    </td>		
	
			  			<td valign="top" style="width:40px;margin-left:0px; height:0px;" class="easyui-paddingbfx">
			  			 <div style="width : 61px; margin-left:55px;margin-top:-5px; align : top ;padding-left:0px; height: 0px">			
						<label class=" mandatory-lbl" style="padding-left:5px;" >Shift</label>
						<input id="cmbsinmAnalysedShift" name="cmbsinmAnalysedShift" class="easyui-combobox"  style="width:270px;"  value="${requestScope.analyseshift}" value = "${requestScope.sheAccIncBean.disableAnalysedShift == true ? ' disabled':''}"/>	
						</div>	
						</td>
						 
				       <td valign="top" style="width: 200px; margin-left:200px;">
						<div  style="margin-left:130px; margin-top:-5px;" class=""><label class=" mandatory-lbl" style="padding-left:90px;">Investigated By</label></div>
     			  	   	<div  style="margin-left:220px;"class=""><input id="cmbsinmAnalysedBy" name="cmbsinmAnalysedBy" class="easyui-combobox"  style="width:270px;"  value="${requestScope.analyseby}"  value = "${requestScope.sheAccIncBean.disableAnalysedBy == true ? ' disabled':''}"/></div>
			
			  		    </td>
			<td>
 <div style="width : 61px; margin-left:50px;margin-top:-8px; align : top ;padding-left:0px; height: 0px">			
<input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:22px;display:;" value="Excel View" />
</div>  
 </td>  				
	
   </tr>                    
</table>

	<table >		  	
			  	
		
		 <tr>
         <td style="width: 229px">
         <div style="margin-left:0px;margin-top:0px;padding-left:px; width: 200px"></div>
          <div style="margin-left:0px;"><label class="mandatory-lbl">Accident Type</label></div>	 
		<input id="cmbsinmExactType" name="cmbsinmExactType" class="easyui-combobox"  style="width:270px;"  value="${requestScope.exctype}"  value = "${requestScope.sheAccIncBean.disableExactType == true ? ' disabled':''}"/>			 
	    </td>
	  
	 <td> 
	<div style="margin-left:-145px;"><label class="lblaccRootcausedueto">Root Cause Due To</label></div>
	<div style="margin-left:70px; margin-top:13px;"><label>Unsafe Act</label></div>	
	<div style="margin-left:55px; margin-top:-13px;"><input type="checkbox" id="chbUnsafeAct" name="chbUnsafeAct"  value = "${requestScope.sheAccIncBean.disableUnsafeAct == true ? ' disabled':''}"/> 
	</div>
	<div style="margin-left:160px; margin-top:-13px;"><label>Unsafe Condition</label></div>
	<div style="margin-left:140px; margin-top:-13px;"><input type="checkbox" id="chbUnsafeCondn" name="chbUnsafeCondn"  value = "${requestScope.sheAccIncBean.disableUnsafeCondn == true ? ' disabled':''}"/> 
	</div>
  
  </td>
  	  
  <td>
  <div style="margin-left:80px;"><label>Description of Accident/Incident</label></div>
  <div style="margin-left:80px; margin-top:-1px;">
  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmExactDesc" name="txtsinmExactDesc" maxlength="500" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableExactDesc == true ? ' disabled':''}">${requestScope.accddesc}</textarea> 
  </div> 	
  </td>
  
  
  <td>
  <div style="margin-left:52px; margin-top:-1px;">
 <input type="button" id="btncrtView" name="btncrtView" class="easyui-button" style="width:80px;height:22px;display:;" value="View" />
</div>
</td>  
</tr>
	</table>	
	
	<table>
	<tr>
	
	<td>
		
   <div style="margin-left:1px;"><label>Investigation</label></div>			
    <div style="margin-left:1px; margin-top:4px;">
   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmIdea" name="txtsinmIdea"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableIdea == true ? ' disabled':''}">${requestScope.invest}</textarea>
   </div>		
</td> 
		
<td>	
<div style="margin-left:51px;"><label >Recommended Corrective Action</label></div>		
 <div style="margin-left:50px; margin-top:-1px;">
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmRootcause" name="txtsinmRootcause"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableRootcause == true ? ' disabled':''}">${requestScope.recom}</textarea>	
</div>
</td>
<td>
<div style="margin-left:15px;"><label>Remarks</label></div>
<div style="margin-left:15px; margin-top:-1px;">
<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtsinmRemarks" name="txtsinmRemarks"  maxlength="3000" style="resize:none;width:270px;height:75px;"  value = "${requestScope.sheAccIncBean.disableRootcause == true ? ' disabled':''}">${requestScope.recom}</textarea>	
</div>
</td>	


<!-- <td>
<div style="margin-left:30px;"></div>
<div style="margin-left:30px; margin-top:-1px;">
  <input type="button" class="easyui-button" id="btnRiskAsses" name="btnRiskAsses" value="Risk Assessment" style="height:20px;width:110px;"/>
</div>
</td>	 -->
		
<td>
<div style="margin-left:45px; margin-top:-1px;">		
<input type="button" class="easyui-button" value ="Submit" id="btnsave" style="height:20px; width:55px;"/>
</div>		
</td>		
</tr>		
</table>	

<table>			  	
<tr>
<td>
<img src="images/tools.jpg" id="imgVsodImgtoolused" name="imgVsodImgtoolused"  width="100px" height="100px" style="width : 160px; height : 126px;"/>
<div  style="padding-top: 6px;">
<span style="padding-right: 99px;"><input	type="button" class="easyui-button" id="dlgImg" 	name="dlgImg" value="+" style=" height : 20px;"/> </span>
<input	type="button" class="easyui-button" id="btnImgClear"name="btnImgClear" value="-"  style="width : 29px; height : 20px;"/></div>

							
</td>

<td>
 <div style="padding-top: -5px; margin-left: 160px;">
										<table  id="AccGrid">
											<tr><td><td/></tr>
										</table>
										<div id="pager1"></div>
									    </div>
</td>


<!-- <td>
<div style="margin-left:52px; margin-top:-1px;">		
<input type="button" class="easyui-button" value ="Submit" id="btnsave" style="height:20px; width:55px;"/>
</div>		
</td> -->	

</tr>
</table>	

<table>
<tr>




<td>
<div style="margin-left:13px; margin-top:14px;">
<input type="button" class="easyui-button" id="btnvisualsave" name="btnvisualsave" style="width:137px;height:30px;"value="Image Insert/Update" />
</div>
</td>
<td>
<div style="margin-left:13px; margin-top:14px;">
<input type="button" class="easyui-button" id="btnaccDelete" name="btnaccDelete" style="width:137px;height:30px;"value="Image Delete" />
</div>
</td>
</tr>
</table>

		  		
     </div>	  		
			  		
			  		<div style="padding-left:30px;">
			  			<span id="err_dtesinmAnalysedDate" class="tpm-errormsg"></span>
			  			<span id="err_cmbsinmAnalysedShift" class="tpm-errormsg analysedShiftaccErrMsg"></span>
			  			<span id="err_cmbsinmAnalysedBy" class="tpm-errormsg analysedByaccErrMsg"></span>
			  		</div>
		
<table id=ACCNTgrid>
	<tr>
	<td></td>
		</tr>
	</table>
<div>		                                                                   
<input type="hidden" id="imgPresentImgFilename" name="imgPresentImgFilename" value="" />
<input type="hidden" id="imgVsodImgtoolusedFilename" name="imgVsodImgtoolusedFilename" value="${requestScope.GenTlAllmoduleimgfile.imagetype}" />
<input type="hidden" id="hdnstatus" name="hdnstatus" value="${requestScope.sheTlIncidentMst.sinmStatus}" />
<input type="hidden" id="hdnkeyid" name="hdnkeyid" value="${requestScope.KEYID}" />
<input type="hidden" id="hdnrootcause" name="hdnrootcause" value="${requestScope.rootcause}" />
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"/>
<input type="hidden" id="hdnimageType" name="hdnimageType" value="${requestScope.ImgType}">
<input type="hidden" id="hdnsinmRootcausedueto" name="hdnsinmRootcausedueto" value="${requestScope.sheTlIncidentMst.sinmRootcausedueto}" />
<input type="hidden" id="hdntime" name="hdntime" value="${requestScope.time}"/>
</div>
<input type="hidden" id="hdnCheckFlag" value="N" />
</form>
<input type="hidden" id="hdnYYArea" name="hdnYYArea" value="" />