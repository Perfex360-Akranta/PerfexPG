
 <!-- <script type="text/javascript" src="js/jquery.easyui.min.js"></script> -->
<%--   <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>


 <script type="text/javascript">	

   jQuery(document).ready(function(){
	   //jQuery("#frmBDpcs select[id='cmbbdmsRelatedto']").attr('disabled',"disabled");
       initialiseForm('frmBDpcs');	    	  
       jQuery('#submitForm').val('frmBDpcs'); // set the id of form to submit  txtbdmsActualworktime
       /*Added By Roopa---*/
       //jQuery( "#tabbrkDown > ul li:nth-child(4)" ).css("display","none");
       //jQuery('input:checkbox[name=chkIssparesN]').attr('checked',true);
 	   formatDateBox('dtebdmsReporteddate','dd-MMM-yyyy');
 	  formatDateBox('dtebdmsProdaccepdate','dd-MMM-yyyy');
 	 // fillWithCurrentDate('dtebdmsReporteddate');//filling Current Date
 	  fillWithCurrentDate('dtebdmsProdaccepdate');
	 // fillWithCurrentDate('spnbdmsReportedtime');
	  fillWithCurrentDate('spnbdmsProdacceptime');
	  
	 // fillComboBox("frmBDpcs","cmbbdmsMachineid","machineCombo.commonFilter" ); 
	   var flId = jQuery("#hdnFlid").val();
	  // var cellId = jQuery("#frmBDpcs input[id='cell']").val();
		//var flId=jQuery("#frmBDpcs input[id='flid']").val();
		//alert(flId+"........");
		fillComboBox("frmBDpcs","cmbbdmsMachineid","machineCombo.commonFilter?flid="+flId );	
		
		
	  // loadFunctionalLocation("pcsLossLocation","functionalLoc_lossCapture.pcs","pcsLossLocationnValues","frmBDpcs","");
			readOnlyFields("dtebdmsProdaccepdate");
			readOnlyFields("spnbdmsProdacceptime");
			readOnlyFields("txtbdmsActualworktime");
			readOnlyFields("dtebdmsReporteddate");
			//readOnlyFields("spnbdmsReportedtime");
			
			var factId = jQuery("#frmBDpcs input[id='factory']").val();
			   var sectionId = jQuery("#frmBDpcs input[id='section']").val();
			   var cellId = jQuery("#frmBDpcs input[id='cell']").val();
			   var machId = jQuery("#frmBDpcs input[id='machine']").val();
			   var flid = jQuery("#frmBDpcs input[id='flid']").val();
			   var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flId;	  					
			   var cellid = jQuery('#hdnCellId').val();
			  // alert("cell id        "+machId);
				 loadFunctionalLocation("bdmsfunLocation","functionalLoc.brdn","bdmsfunLocationValues","frmBDpcs",dataStr);
				 processGridnew("Breakdownpcs_input.brdn","&cellid="+cellid, "pcsBdGrid", "pcsBdPager","","bdPcsdbl","","loadSuccess");
					
				 function loadSuccess() {}	
				
   }); 
	  

   var wsDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');
	var weDate = jQuery("#dtebdmsProdaccepdate").datebox('getValue') + jQuery("#spnbdmsProdacceptime").spinner('getValue');
	var downTime = timeDifference(wsDate,weDate);
	
	displayText('txtbdmsActualworktime',downTime.minutes);
	jQuery("#txtbdmsActualworktime").trigger("change");
	
	

   jQuery('#chbbdmsWoprodaccepflag').click(function() {				 
				 
		 if(jQuery("#chbbdmsWoprodaccepflag").is(':checked') == true ){
		 	enableFields("dtebdmsProdaccepdate");
		 	enableFields("spnbdmsProdacceptime");
		 	
	    	setFieldValue('spnbdmsProdacceptime',jQuery('#spnbdmsReportedtime').spinner('getValue'));
	    	setFieldValue('dtebdmsProdaccepdate',jQuery('#dtebdmsReporteddate').datebox('getValue'));
		 	
	    	 var startDate = new Date(jQuery('#dtebdmsReporteddate').datebox('getValue'));
	    	   var endDate = new Date(jQuery('#dtebdmsProdaccepdate').datebox('getValue'));

	    	   if (startDate < endDate){
	    	
	    	   }
		 }
		 else{
				readOnlyFields("dtebdmsProdaccepdate");
				readOnlyFields("spnbdmsProdacceptime");
			 }
		
		 	  
   });
  
   jQuery('#btnPcsBdInsert').click(function(){
	  
	   saveForm("frmBDpcs","Breakdownpcs_save.brdn");
	   
   });
   jQuery('#btnPcsBdDelete').click(function(){
	    saveForm("frmBDpcs","Breakdown_delete.brdn?url=pcsDel");
	   
   });
   
   jQuery('#btnPcsBdClear').click(function(){	 		
	  jQuery('#cmbbdmsMachineid').combobox('clear');
	  jQuery('#txtbdmsRemarks').val('');
	  jQuery('#txtbdmsProblemdescription').val('');
   });
   
   function frmBDpcs_successsCallback(result) 
   {
	   jQuery("#txtbdmsWno").val( result.successData.womsKeyid);
 	   jQuery("#txtbdmsKeyid").val( result.successData.keyId);
	   jQuery("#pcsBdGrid").trigger("reloadGrid");
	   return true;
   }
   function bdPcsdbl(rowid) { 

		//var plosKeyid = jQuery("#pcslossGrid").jqGrid('getCell',id,2); 		
		
		var rowData = jQuery("#pcsBdGrid").jqGrid('getRowData',rowid);

		var prdAccept=rowData.PRDFLAG;
		//alert(prdAccept);
		if(prdAccept=='Y'){
			var strttm=rowData.ProductionStartDate;
			jQuery("#chbbdmsWoprodaccepflag").prop('checked', true);
			readOnlyFields('chbbdmsWoprodaccepflag');
			}
		else
			jQuery("#chbbdmsWoprodaccepflag").prop('checked',false);	
	       
	       
	    setFieldValue('cmbbdmsMachineid',rowData.MachinId);
	    setFieldValue('hdncmbbdmsMachineid',rowData.MachinId);
		setFieldValue('txtbdmsProblemdescription',rowData.Problem);
		setFieldValue('txtbdmsRemarks',rowData.Remark);		
		setFieldValue('dtebdmsProdaccepdate',rowData.ProductionStartDate);
		setFieldValue('spnbdmsProdacceptime',rowData.ProductionStartTime);
		setFieldValue('dtebdmsReporteddate',rowData.OccuredDate);
		setFieldValue('spnbdmsReportedtime',rowData.OccuredTime);
		setFieldValue('hdnbdmsKeyid',rowData.BDId);
		setFieldValue('txtbdmsActualworktime',rowData.WTIME);
		loadFunctionalLocation("bdmsfunLocation","functionalLoc.brdn","bdmsfunLocationValues","frmBDpcs","&machId="+rowData.MachinId);
		frmBDpcs_successsCallback(result);
   }
  
   if( jQuery("#chbbdmsWoprodaccepflag").is(":checked") == false ){
	   setFieldValue('txtbdmsActualworktime','0');
	   }
   function prodAcceptimeEvt()
	{			
		dtebdmsProdaccepdate_onSelect(null);				
	}
  
   
   function dtebdmsProdaccepdate_onChange(date)
 	{
     
		compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsProdaccepdate','spnbdmsProdacceptime','Production Start should not be lesser than Occured date');
		actualTimeCalculation();
		jQuery("#txtbdmsActualworktime").trigger("change");
   }
   function dtebdmsProdaccepdate_onSelect(date)
	{
    	
		compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsProdaccepdate','spnbdmsProdacceptime','Production Date should not be lesser than Occured Date');
		actualTimeCalculation();
						
  }
   function actualTimeCalculation()
	{		
		if(jQuery("#spnbdmsReportedtime").spinner('getValue') != null && jQuery("#spnbdmsReportedtime").spinner('getValue') != null)
		{					
			//alert(jQuery('#spnbdmsReportedtime').spinner('getValue')+'...nnn....nnnn');
			var repDate = jQuery("#dtebdmsReporteddate").datebox('getValue') + jQuery("#spnbdmsReportedtime").spinner('getValue');				
			var weDate = jQuery("#dtebdmsProdaccepdate").datebox('getValue') + jQuery("#spnbdmsProdacceptime").spinner('getValue');
			//var weDate = jQuery("#dtebdmsWoendtime").datebox('getValue') + jQuery("#spnbdmsWoend").spinner('getValue');
			
			actualMins = timeDifference(repDate,weDate);
			if(parseInt(actualMins.minutes,10)>=0)
			displayText('txtbdmsActualworktime',actualMins.minutes);														
			var downTime = 0;
			
			mins = timeDifference(repDate,weDate);	
			if(parseInt(mins.minutes,10)>=0)					
				 downTime = mins.minutes;//displayText('txtbdmsDowntime',mins.minutes);
				 if(parseInt(downTime,10)< 0)
				  {
					 // alert("Down time "+downTime);
					  jQuery("#txtbdmsBreaktime").val('');
					  jQuery("#txtbdmsDowntime").trigger("change");
				  }

			 if(downTime != ''  && downTime != null && downTime != 'NaN'){
			 	displayText('txtbdmsActualworktime',downTime);
			 	
			 }	

			//downTime = timeDifference(repDate,weDate);
			//if(parseInt(downTime.minutes)>=0)
				//displayText('txtbdmsDowntime',downTime.minutes);								
		}
	}
   function frmBDpcscmbbdmsMachineid_onSelect(record)
   {	
	 
		    loadFunctionalLocation("bdmsfunLocation","functionalLoc.brdn","bdmsfunLocationValues","frmBDpcs","&machId="+record.id);
			readOnlyFields('frmBDpcsFuntKeyIds');
  } 

   spinnerChange('spnbdmsProdacceptime','prodAcceptimeEvt');
   
   spinnerUp('spnbdmsProdacceptime','prodAcceptimeEvt');
   spinnerDown('spnbdmsProdacceptime','prodAcceptimeEvt');
   spinnerChange('spnbdmsReportedtime','setShiftEvt');
   spinnerUp('spnbdmsReportedtime','setShiftEvt');
   spinnerDown('spnbdmsReportedtime','setShiftEvt');
   spinnerKeyPress('spnbdmsProdacceptime');
   spinnerKeyPress('spnbdmsReportedtime');
   
   setFieldValue('dtebdmsReporteddate',jQuery('#hdnbdmslossdate').val());
    var hr=jQuery('#hdnbdmshourno').val();
    var shft=jQuery('#hdnbdmsshifttext').val();
    //alert(hr+"..........");
    if(hr==1&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"06:25");		
        }
    else if(hr==2&&shft==1){
    		setFieldValue('spnbdmsReportedtime',"07:25");	
    			
    }else if(hr==3&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"08:35");		
    }else if(hr==4&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"09:35");		
    }else if(hr==5&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"11:15");		
    }else if(hr==6&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"12:15");		
    }else if(hr==7&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"13:20");		
    }else if(hr==8&&shft==1){
    	setFieldValue('spnbdmsReportedtime',"14:25");		
    }else if(hr==1&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"15:45");		
    }else if(hr==2&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"16:45");		
    }else if(hr==3&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"17:55");		
    }else if(hr==4&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"18:55");   
    }else if(hr==5&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"20:35");		
    }else if(hr==6&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"21:35");		
    }else if(hr==7&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"22:45");		
    }else if(hr==8&&shft==2){
    	setFieldValue('spnbdmsReportedtime',"23:45");		
    }else if(hr==1&&shft==3){
    	setFieldValue('spnbdmsReportedtime',"02:00");		
    }else if(hr==2&&shft==3){
    	setFieldValue('spnbdmsReportedtime',"03:00");		
    }else if(hr==3&&shft==3){
    	setFieldValue('spnbdmsReportedtime',"04:10");		
    }else if(hr==4&&shft==3){
    	setFieldValue('spnbdmsReportedtime',"05:10");		
    }

    if(jQuery('#chbbdmsWoprodaccepflag').is(':checked'==true)){
    	
    	setFieldValue('spnbdmsProdacceptime',jQuery('#spnbdmsReportedtime').val());
    	setFieldValue('dtebdmsProdaccepdate',jQuery('#dtebdmsReporteddate').val());
        
        }


   // alert(jQuery('#hdnMchCalTime').val());
    
 	function spinnerChange(spinnerId,callBackFunc)
  	{              	
  	
  		   jQuery("#"+spinnerId).change(function(){	
  			 
  			if( typeof eval('('+callBackFunc +')') == 'function')
			{
				eval('( '+ callBackFunc +'())');
			}
  		});
    }
  	function spinnerUp(spinnerId,callBackFunc)
  	{
  		jQuery("#"+spinnerId).spinner({
			onSpinUp:function(){
				if( typeof eval('('+callBackFunc +')') == 'function')
    			{
    				eval('( '+ callBackFunc +'())');
    			}
			}
			});
    }
  	function spinnerDown(spinnerId,callBackFunc)
  	{
  		jQuery("#"+spinnerId).spinner({
			onSpinDown:function(){
				if( typeof eval('('+callBackFunc +')') == 'function')
    			{
    				eval('( '+ callBackFunc +'())');
    			}
			}
			});
    }

  	function setShiftEvt()
	{          
		if( jQuery("#chbbdmsWoprodaccepflag").is(":checked") == true )
			prodAcceptimeEvt();
	}
  	function showDowntime()
	{
		   var fromDate = jQuery('#dtebdmsReporteddate').datebox('getValue') + ' '+jQuery('#spnbdmsReportedtime').spinner('getValue');
		   var toDate = jQuery('#chbbdmsWoprodaccepflag').is(':checked') == true?jQuery('#dtebdmsProdaccepdate').datebox('getValue') + ' '+jQuery('#spnbdmsProdacceptime').spinner('getValue'):jQuery('#dtebdmsWoendtime').datebox('getValue') + ' '+jQuery('#spnbdmsWoend').spinner('getValue');
		   var downTimedata = '?q=2&factId='+jQuery('#factory').val()+'&fromTime='+fromDate+'&toTime='+toDate;								
		   processGridnew('downtime_view.brdn',downTimedata,"DwnTmeBrkupGrid","DwnTmeBrkupPager","","","","downTimeGrid","");
	}
  	 function downTimeGrid(){	

			fillDwnTimeFields();
  	 }
  	function woStartEvt()
	{        		
		dtebdmsWostarttime_onSelect(null);
	}

  	function dtebdmsWostarttime_onSelect(date)
  	{
		if(jQuery('#chbbdmsWoendflag').is(':checked') == true)
		{	
    		compareDates('dtebdmsReporteddate','spnbdmsReportedtime','dtebdmsWostarttime','spnbdmsWostart','Start Date should not be lesser than Occurred Date');

    		//compareDates('dtebdmsReporteddate','dtebdmsReporteddate','dtebdmsProdaccepdate','','Start Date should not be lesser than Occurred Date');

			actualTimeCalculation();
		}	
	}
  	
  	
 	   </script>
 	  
			
<form name="frmBDpcs" id="frmBDpcs" method="post">

<div>
<div id="wrapper">
	<input id="hdnRecTime" name="hdnRecTime" style="display:none;"/>
	<input id="hdnwsTime" name="hdnwsTime" style="display:none;"/>
	<input id="hdnweTime" name="hdnweTime" style="display:none;"/>
	<input id="hdnProdAccTime" name="hdnProdAccTime" style="display:none;"/>
	<input id="hdnbdanKeyid" name="hdnbdanKeyid" value="${requestScope.bdmTlDtl.bdanKeyid}" style="display:none;"/>
	<input type="hidden" id="hdnRepBDFlag" name="hdnRepBDFlag" value="${requestScope.bdmTlMst.bdmsRepeatedbdflag}">
	<input type="hidden" id="mode" name="mode" value="${requestScope.formMode}">
			<div class="easyui-paddingbfpx" style="position:relative;">
			<div style="margin-top:-21px;padding-left:478px;display:none"><input id="txtbdmsSapNo" type="text" style="width: 150px;"  class="easyui-text" value="${requestScope.bdmTlMst.bdmsKeyid}" disabled/></div>
			<div  style="padding-left: 504px;margin-to: -25px;">
<!--			<img id="" src="images/filemanager1.png"/>-->
<!--			<img id="" src="images/hist3.png"/>-->
			<span id="forNewpp" style="display:none;">
<!--				<img id="" src="images/newpropasal1.png"/>-->
					<input type="button" id="btnNewPP" class="easyui-button" value="New PP Proposal" style="width: 120px;height: 22px;"/>
			</span>			
		   </div>		  
<!--			<div id="forNewPP" style="display:none;padding-left: 800px;"><input type="button" id="btnNewPP" class="easyui-button" value="New PP Proposal" style="width: 120px;height: 25px;"/></div>-->
		   
	    </div>
		<div id="frmBDpcsFuntKeyIds">
						<input type="hidden" id="factory" name="cmbbdmsFactoryid" value="${requestScope.bdmTlMst.bdmsFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbbdmsSectionid" value="${requestScope.bdmTlMst.bdmsSectionid}"  ></input>
						<input type="hidden" id="cell" name="cmbbdmsCellid" value="${requestScope.bdmTlMst.bdmsCellid}" ></input>
						<input type="hidden" id="machine" name="cmbbdmsMachineidhdn" value="${requestScope.bdmTlMst.bdmsMachineid}"></input>
						<input type="hidden" id="flid" name="cmbbdmsFlid" value="${requestScope.bdmTlMst.bdmsFlid}"></input>
						<input type="hidden" id="elementId" name="cmbbdmsElementid" value="${requestScope.bdmTlMst.bdmsElementid}"></input>
		</div>
		<div>
		  <div id="bdmsfunLocation" style="padding-left:10px; width:150px; "></div>	
		<div  style="margin-top:10px;" >	
		    <label class="mandatory-lbl">Equipment</label> 
		    <span style="padding-left:280px;"><label class="mandatory-lbl">Problem Description</label></span>
		    <span style="padding-left:200px;"><label>Remark</label></span>
		    </div>
		    </div>
			<div  style="padding-left:10px; "> 
			<table><tr><td>	<input type="text" id="cmbbdmsMachineid" name="cmbbdmsMachineid" class="easyui-combobox" value="${requestScope.bdmTlMst.bdmsMachineid}" style="width: 300px;" <c:out value = "${requestScope.bdFormBean.disablebdmsMachineid == true ? ' disabled':''}"/>/>
			<input type="hidden" id="hdncmbbdmsMachineid" value="${requestScope.bdFormBean.disablebdmsMachineid}" /></td>
			<td><span style="padding-left:30px;"><textarea id="txtbdmsProblemdescription" name="txtbdmsProblemdescription"   style="resize:none;width:280px;height:40px;" <c:out value = "${requestScope.bdFormBean.disablebdmsProblemdescription == true ? ' disabled':''}"/>>${requestScope.bdmTlMst.bdmsProblemdescription}</textarea></span>
		  	</td><td><span style="padding-left:30px;"><textarea id="txtbdmsRemarks" name="txtbdmsRemarks"   style="resize:none;width:280px;height:40px" <c:out value = "${requestScope.bdFormBean.disablebdmsProblemdescription == true ? ' disabled':''}"/>>${requestScope.bdmTlMst.bdmsProblemdescription}</textarea></span>
		 	</td></tr>
		 	</table>
		 	</div>
			<!-- 	<input type="button" class="easyui-button"  id="btnEqpLink" value="..." style="height: 22px;"/> -->
				
			</div>			
			 
	   </div>	   
					  
	   	      <div style="padding-left:50px;">
	            	<label class="mandatory-lbl">Occurred On</label>
					<label style="padding-left:115px;">Prod. Started</label>
					<label style="padding-left:100px;">Down Time(Min.)</label>
			  </div>
			 
			  <div class="easyui-paddingbfpx">	
				<span style="padding-left: 55px;"><input id="dtebdmsReporteddate"  name="dtebdmsReporteddate" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsReporteddate}"  style="width: 85px;" <c:out value = "${requestScope.bdFormBean.disablebdmsReporteddate == true ? ' disabled':''}"/> /></span>
				<span class="spinner"><input  id="spnbdmsReportedtime" name="spnbdmsReportedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.bdFormBean.bdmsReportedtime}"  style="width: 60px;" <c:out value = "${requestScope.bdFormBean.disablebdmsReportedtime == true ? ' disabled':''}"/>></span>
				<!--  
				<span  class="spinner"><input  id="spnbdmsReportedtime" name="spnbdmsReportedtime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.bdFormBean.bdmsReportedtime}"  style="width: 55px;" <c:out value = "${requestScope.bdFormBean.disablebdmsReportedtime == true ? ' disabled':''}"/>></span>
				--><span style="padding-left:20px;"><input type="checkbox"  value="Y" id="chbbdmsWoprodaccepflag" name="chbbdmsWoprodaccepflag" <c:out value = "${requestScope.bdFormBean.disablebdmsProdaccepdate == true ? ' disabled':''}"/>/></span>
				<span style="padding-left: 5px;"><input id="dtebdmsProdaccepdate"  name="dtebdmsProdaccepdate" clear="false" class="easyui-datebox" value="${requestScope.bdmTlMst.bdmsReporteddate}"  style="width: 85px;" <c:out value = "${requestScope.bdFormBean.disablebdmsReporteddate == true ? ' disabled':''}"/> /></span>
				<span class="spinner"><input  id="spnbdmsProdacceptime" name="spnbdmsProdacceptime"  class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.bdFormBean.bdmsReportedtime}"  style="width: 55px;" <c:out value = "${requestScope.bdFormBean.disablebdmsReportedtime == true ? ' disabled':''}"/>/></span>
				<span style="padding-left:30px;"><input type="text"  class="easyui-text" name="txtbdmsActualworktime" id="txtbdmsActualworktime" size="15px;" style="text-align:center;"/></span>
				<span style="Padding-left:350px;">
				<input type="button" id="btnPcsBdInsert" name="btnPcsBdInsert" class="easyui-button" value="Insert"/>
				<input type="button" id="btnPcsBdClear" name="btnPcsBdClear" class="easyui-button" value="Clear"/>
				<input type="button" id="btnPcsBdDelete" name="btnPcsBdDelete" class="easyui-button" value="Delete"/>
				</span>
			</div>		
			 
		</div>
		<div style="padding-left:05px;">
 		 
 				<table id='pcsBdGrid'>
    	       		<tr><td></td></tr>
    	       		</table>
				<div id="pcsBdPager"></div>
	
 <input type="hidden" id="hdnbdnkeyID" name="hdnabnkeyID" value="${requestScope.keyid}"/> 
 <input type="hidden" id="hdnFlid" name="hdnFlid" value="${requestScope.flid}"/>
 <input type="hidden" display='none' id="hdnbdmsShiftid" name="hdnbdmsShiftid" value="${requestScope.shiftId}"/>
 <input type="hidden"  display='none' id="hdnCellId" name="hdnCellId" value="${requestScope.cellId}"/>
 <input type="hidden" id="hdncmbbdmsBookedby" name="hdncmbbdmsBookedby" value="${requestScope.empId}"/>
 <input type="hidden" id="hdnBdanWwno" name="hdnBdanWwno" value="${requestScope.bdmTlDtl.bdanWwno}"/>
 <input type="hidden" id="hdnbdmsKeyid" name="hdnbdmsKeyid" value="${requestScope.bdmTlmst.bdmsWno}"/>
 <input type="hidden" id="hdnbdmsshifttext" name="hdnbdmsshifttext" value="${requestScope.shifttext}"/>
 <input type="hidden" id="hdnbdmsTempfield7" name="hdnbdmsbdmsTempfield7" value="${requestScope.availTime}"/>
 <input type="hidden" id="hdnbdmshourno" name="hdnbdmshourno" value="${requestScope.hourno}"/>
 <input type="hidden" id="hdnbdmslossdate" name="hdnbdmslossdate" value="${requestScope.lossdate}"/>
 <input type="hidden" id="txtbdmsWno" name="txtbdmsWno" value="${requestScope.bdmTlMst.bdmsWno}" class="easyui-text noFocus" tabindex = "-1" disabled style="height: 22px;background-color:#D1E2FD;font-weight:bold;text-align:center;color:#245edc;" <c:out value = "${requestScope.bdFormBean.disablebdmsWno == true ? ' disabled':''}"/>/>
 <input type="hidden" style="display:hide;" id="txtbdmsKeyid" name="cmbbdmsKeyid" value="${requestScope.bdmTlMst.bdmsWno}" tabindex = "-1" disabled style="height: 22px;background-color:#D1E2FD;font-weight:bold;text-align:center;color:#245edc;" <c:out value = "${requestScope.bdFormBean.disablebdmsWno == true ? ' disabled':''}"/>/>
  
 </div>
 
</form>