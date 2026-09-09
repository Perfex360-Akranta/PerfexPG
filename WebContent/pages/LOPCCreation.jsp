<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script type="text/javascript">
    jQuery(document).ready(function(){
    initialiseForm('frmLOPCCreation');
    jQuery('#submitForm').val('frmLOPCCreation');
    var rolename=jQuery('#hdnrole').val();
    readOnlyFields("txtdocuno");
    jQuery("#btnInvestigation").hide();
    fillComboBox('frmLOPCCreation','cmbNmrtCompletedby', "employee.commonFilter");
    fillComboBox('frmLOPCCreation','cmbLoemEmployeeid', "employee.commonFilter");
    fillComboBox('frmLOPCCreation','cmbNmrnApprovedby', "employee.commonFilter");
    fillComboBox('frmLOPCCreation','cmbnmrnResponsibility', "employee.commonFilter");
    fillComboBox('frmLOPCCreation','cmbLoemIdentifiedby', 'employee.commonFilter');
    fillComboBox('frmLOPCCreation','cmbLoemLocacategoryid', 'LOPCCategory.lopc');
    fillComboBox('frmLOPCCreation','cmbNmrnDeptid', 'sectionCombo.commonFilter');
    fillComboBox('frmLOPCCreation','cmbNmrnProbablerecrate', 'comboProbable.commonFilter');
    fillComboBox("frmLOPCCreation","cmbNmrnStatus","Combo_Status.api");
    formatDateBox('dteLoemOccurencedatetime','dd-MMM-yyyy');  
    formatDateBox('dteLoemPrepareddatetime','dd-MMM-yyyy');
    
	fileManagerPopUp("","NearMiss","frmLOPCCreation","btnFilManage","NearMissFilemgr");
	
	 var Mode=jQuery("#hdnMode").val();
	 if(Mode=="View"){
		 jQuery("#btnInvestigation").show();
		    jQuery("#LopcCategory").attr("disabled",true).addClass("ui-state-disabled");
			jQuery("#txtLoemLopcdesc").attr("disabled",true);
			jQuery("#spnPreparedDatetime").attr("disabled",true);
			 setTimeout(function() {readOnlyFields('spnPreparedDatetime');},1250);
			
		
			 jQuery("#cmbLoemIdentifiedby").combobox('disable');
			 jQuery("#cmbLoemLocacategoryid").combobox('disable');
			  jQuery("#cmbLoemLocacategoryid").attr("disabled",true);
			 jQuery("#chkkzbnOthers").attr("disabled",true);
			
			 jQuery("#chkkzbnPrepareOthers").attr("disabled",true);
			 disableField("frmLOPCCreation","dteLoemPrepareddatetime");
			 disableField("frmLOPCCreation","dteLoemOccurencedatetime");
		   jQuery("#btnFilManage").hide();
		   jQuery('#btnexcelview').show();
	 }
	  var mode=jQuery('#hdnformtype').val();
	 
	  if(mode.length==0)
	  {  
		   fillWithCurrentDate('dteLoemOccurencedatetime');
           fillWithCurrentDate('dteLoemPrepareddatetime');
		   fillWithCurrentDate('spnOccurrencetime');	
		   fillWithCurrentDate('dteLoemPrepareddatetime');
		   fillWithCurrentDate('spnPreparedDatetime');	
			    spinnerKeyPress('spnPreparedDatetime');
			    spinnerChange('spnPreparedDatetime','setShiftEvt');
			    spinnerUp('spnPreparedDatetime','setShiftEvt');
			    spinnerDown('spnPreparedDatetime','setShiftEvt');   
			   
			   
			    spinnerKeyPress('spnOccurrencetime');
			    spinnerChange('spnOccurrencetime','setShiftEvt');
			    spinnerUp('spnOccurrencetime','setShiftEvt');
			    spinnerDown('spnOccurrencetime','setShiftEvt');  
		   jQuery("#btnexcelview").attr("disabled",true).addClass("ui-state-disabled");
	   }
	  if(mode=="delete")
	   	{
           
             jQuery("#btnExcelVw").hide();
             jQuery("#btnFilManage").hide();
	   	}
	  
	   
	   if(mode=="Modify"){
		   jQuery("#btnExcelVw").hide();
		   jQuery("#btnFilManage").attr("disabled",false).removeClass("ui-state-disabled");
		}
	   
	   if(mode=="View"){
		    jQuery("#LopcCategory").attr("disabled",true).addClass("ui-state-disabled");
			jQuery("#txtNmrnDescnearmiss").attr("disabled",true);
			jQuery("#spnPreparedDatetime").attr("disabled",true);
			jQuery("#spnOccurrencetime").attr("disabled",true);
			jQuery("#btnfrmLOPCCreationmainFunLoc").css('display','none');
			 jQuery("#cmbLoemEmployeeid").combobox('disable');
			 jQuery("#cmbLoemIdentifiedby").combobox('disable');
			 jQuery("#cmbLoemLocacategoryid").combobox('disable');
			  jQuery("#cmbLoemLocacategoryid").attr("disabled",true);
			 jQuery("#chkkzbnOthers").attr("disabled",true);
			
			 jQuery("#chkkzbnPrepareOthers").attr("disabled",true);
			 disableField("frmLOPCCreation","dteLoemPrepareddatetime");
			 disableField("frmLOPCCreation","dteLoemOccurencedatetime");
		   jQuery("#btnFilManage").hide();
		   jQuery('#btnexcelview').show();
		}

	   var flid=jQuery("#hdnFnlid").val();
	   var keyid=jQuery("#hdnLopcID").val();
	  
	   if(flid.length==0)
		   {
		      
	         loadFunctionalLocation("LopcFunLocation","functionalLoc.lopc","LopcFunLocationValues","frmLOPCCreation","");	
		   }
	   else{
		   loadFunctionalLocation("LopcFunLocation","functionalLoc.lopc","LopcFunLocationValues","frmLOPCCreation","&flid="+flid);	
			
	   }

  
   
       

            });
    
    jQuery("#cmbLoemLocacategoryid").combobox({ 
        onSelect: function (recordid) { 
            if(recordid.length!=0)
            	{
            	jQuery("#cmbLoemEmployeeid").combobox('enable');
            	jQuery("#spnOccurrencetime").prop("disabled", false);
            	jQuery("#dtenmrnTargetdate").prop("disabled", false);
            	jQuery("#cmbnmrnResponsibility").combobox('enable');
            	jQuery("#cmbNmrnProbablerecrate").combobox('enable');
            	jQuery("#txtNmrnDescnearmiss").prop("disabled", false);
            	jQuery("#txtNmrnActionrecommended").prop("disabled", false);
            	jQuery("#txtNmrnRemarks").prop("disabled", false);
            	jQuery("#txtNmrnInvestigation").prop("disabled", false);
            	var severity= jQuery("#cmbLoemLocacategoryid").combobox('getValue');
            	var comboid= jQuery("#cmbLoemLocacategoryid").combobox('getText');
            	var rolename=jQuery('#hdnrole').val();
        	  var nmrnkeyid=jQuery("#hdnLopcID").val();
            	if(comboid.length!=0)
            		{
            	
            	  if(comboid=="Minor")
            		{
            		  if(nmrnkeyid==null)
            		 {	  
            		 loadFunctionalLocation("LopcFunLocation","functionalLoc.lopc","LopcFunLocationValues","frmLOPCCreation","");	
            		 }

            		}
            	else{
            		 if(nmrnkeyid==null)
            		 {	
            		loadFunctionalLocation("LopcFunLocation","functionalLocdmt.nmrnew","LopcFunLocationValues","frmLOPCCreation","");	
            		 }
            	    }
            	
            		}
            }
        } 
  });
    jQuery("#btnexcelview").click(function(){
	    var keyid=jQuery("#hdnLopcID").val();
	 	var flid = jQuery("#frmLOPCCreation input[id='flid']").val();
	 	//alert(keyid+" keyid "+flid);
	 	if(keyid != null && keyid.length >0){
	 		window.open("NewNearMissExcelview_view.nmrnew?&keyid="+keyid+"&flid="+flid);
	 	}
 });
    
    jQuery("#btnInvestigation").click(function(){
    
	    var keyid=jQuery("#hdnWwblKey").val();
	    var frmMode = jQuery('#hdnMode').val();
	    
	    var Status=jQuery("#hdnStatus").val();
	 	//alert(keyid+" keyid "+frmMode);
	 	if(keyid != null && keyid.length >0){
	 		navigateToNextForm("WwblaView_input.wwbla?q=2&frmMode="+frmMode+"&keyid="+keyid+"&Status="+Status);
	 	}
 });
    jQuery('#chkkzbnOthers').click(function() {
        
        //alert(" Others "+jQuery('#chkkzbnOthers').val());
        
        if(jQuery("#chkkzbnOthers").is(':checked')== true){
       	 jQuery('#chkkzbnOthers').val('Y');
       	 var sat = jQuery('#chkkzbnOthers:checked').val();
    	     jQuery("#cmbLoemEmployeeid").combobox('setValue',"");
    		 othersClickAction(sat);
        }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
            jQuery('#chkkzbnOthers').val('N');
            var sat = jQuery('#chkkzbnOthers').val();
       	 jQuery("#cmbLoemEmployeeid").combobox('setValue',"");
    		 othersClickAction(sat);
    	 }
    });

    function frmLOPCCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
    { 
    	var filemanager =result.successData.filemanager;
    	
    	if(filemanager==true ){
			 var keyid = result.successData.keyId;
			 jQuery('#hdnLopcID').val(keyid);
			 //alert(keyid);
			 fileManagerPopUp(keyid,"","","","");
			
			return ;
		 } 
    }
    function othersClickAction(sat)
    {

    	var cellId = jQuery("#frmLOPCCreation input[id='cell']").val();
    	var flid = jQuery("#frmLOPCCreation input[id='flid']").val();
    	if(sat=="Y")
    	{
    	  reloadCombo("frmLOPCCreation","cmbLoemEmployeeid","employee.commonFilter?&cellId="+cellId+"&others=Y");
    	}
    	else
    	{  
    	  reloadCombo("frmLOPCCreation","cmbLoemEmployeeid","employee.commonFilter?&cellId="+cellId);
    	}	
    }

    jQuery('#chkkzbnPrepareOthers').click(function() {
        
        if(jQuery("#chkkzbnPrepareOthers").is(':checked')== true){
       	 jQuery('#chkkzbnPrepareOthers').val('Y');
       	 var sat = jQuery('#chkkzbnPrepareOthers:checked').val();
    	     jQuery("#cmbLoemIdentifiedby").combobox('setValue',"");
    	     othersPrepareClickAction(sat);
        }else if(jQuery("#chkkzbnPrepareOthers").is(':checked')== false){
            jQuery('#chkkzbnPrepareOthers').val('N');
            var sat = jQuery('#chkkzbnPrepareOthers').val();
       	 jQuery("#cmbLoemIdentifiedby").combobox('setValue',"");
    		 othersPrepareClickAction(sat);
    	 }
    });
    
    function othersPrepareClickAction(sat)
    {

    	var cellId = jQuery("#frmLOPCCreation input[id='cell']").val();
    	var flid = jQuery("#frmLOPCCreation input[id='flid']").val();
    	if(sat=="Y")
    	{
    	  reloadCombo("frmLOPCCreation","cmbLoemIdentifiedby","employee.commonFilter?&cellId="+cellId+"&others=Y");
    	}
    	else
    	{  
    	  reloadCombo("frmLOPCCreation","cmbLoemIdentifiedby","employee.commonFilter?&cellId="+cellId);
    	}	
    }

    function dteLoemOccurencedatetime_onSelect(record) {
    	var detectoinDate = jQuery('#dteLoemOccurencedatetime').datebox('getValue') ;
    	var currentDate = getServerDateTime();
    	  if((currentDate) < convertStringToDate(detectoinDate) )
    		 {
    	 		popupCommonErrorMsg('Should not greater than Current Date');
    	 		fillWithCurrentDate('dteLoemOccurencedatetime');
    	 		return false;
    		 }
    	 	else
    	 	    clearValidationErrorMsg('dteNmrtCompleteddate');
    }



  function dteLoemPrepareddatetime_onSelect(record){
    	var detectoinDate = jQuery('#dteLoemPrepareddatetime').datebox('getValue');  
    	var occurdate=jQuery('#dteLoemOccurencedatetime').datebox('getValue');     
       // alert("occurdate"+occurdate);
        var stringdatecon=convertStringToDate(occurdate);
        if(occurdate!=null||occurdate.length!=0){
             // alert("insid ethe if");
            if((stringdatecon)> convertStringToDate(detectoinDate) )
    		 {
    	 		popupCommonErrorMsg('Should not greater than Occur Date');
    	 		fillWithCurrentDate('dteLoemPrepareddatetime');
                return false;
    		 }
    	 	else
    	 	    clearValidationErrorMsg('dteLoemPrepareddatetime');
        } 
      else{
          popupCommonErrorMsg('Select Occcured Date');
          fillWithCurrentDate('dteNmrtOccurrencedatetime');
          fillWithCurrentDate('dteLoemPrepareddatetime');
       }
    	  var currentDate = getServerDateTime();
    	  if((currentDate) < convertStringToDate(detectoinDate) )
    		 {
    	 		popupCommonErrorMsg('Should not greater than Current Date');
    	 		fillWithCurrentDate('dteLoemPrepareddatetime');
    	 		
    		 }
    	 	else
    	 	    clearValidationErrorMsg('dteLoemPrepareddatetime');
    }
    
    function btnFilManage_click(){
	    var documentNo =jQuery("#hdnLopcID").val();
		if(documentNo != null && documentNo != ''){
			//alert(frmMode);
			var frmMode=jQuery('#frmMode').val();
			apMode = "create";
			if(frmMode=="View")
			   apMode = "view";
			fileManagerPopUp(documentNo,"LOPC","","","","create");
				
		} 
		else{
			var id=jQuery("#hdnFilemngr").val("Y");
			saveForm('frmLOPCCreation','LOPCCreation_save.lopc?&filemanager=filemanager');
		}	
}
    
     function frmLOPCCreation_FuntLocHierarchy_SuccessCallBack(result){
    	// setFunctionalLocWidth('frmLOPCCreation','600px');
	}
    
 	function frmLOPCCreation_beforeDelete(){
     var mode=jQuery('#hdnformtype').val();
    	if(mode=='View'){
    		alert('Data Cannot be Delete in View Mode');
    		return false;
    	}
	}
 	
/* function frmLOPCCreation_beforeSubmit(){    	
    	var mode=jQuery('#hdnformtype').val();
    	if(mode=='View'){
    		alert("Not Allowed to Save");
    		return false;
    	}
    	var rolename=jQuery('#hdnrole').val();
    	var flid = jQuery("#frmLOPCCreation input[id='flid']").val();
        var sectionId = jQuery("#frmLOPCCreation input[id='section']").val();
    	var cellId = jQuery("#frmLOPCCreation input[id='cell']").val();
    	var LopcId=jQuery("#hdnLopcID").val();
    	//alert(LopcId);
        if(cellId.length==0){
    			 popupCommonErrorMsg("Select JH");
    	    		return false;
    			}
        return "LopcId="+LopcId;
    }	 */
    
    function frmLOPCCreation_beforeSubmit(){    	
        var mode = jQuery('#hdnformtype').val();
        if(mode == 'View'){
            alert("Not Allowed to Save");
            return false;
        }
        
        // Validate LOPC Category
        var lopcCategory = jQuery("#cmbLoemLocacategoryid").combobox('getValue');
        if(lopcCategory == null || lopcCategory.length == 0){
            popupCommonErrorMsg("Please select LOPC Category");
            return false;
        }
        
        // Validate Occured Date
        var occurredDate = jQuery('#dteLoemPrepareddatetime').datebox('getValue');
        if(occurredDate == null || occurredDate.length == 0){
            popupCommonErrorMsg("Please select Occured Date");
            return false;
        }
        
        // Validate Occured Time
        var occurredTime = jQuery('#spnPreparedDatetime').timespinner('getValue');
        if(occurredTime == null || occurredTime.length == 0){
            popupCommonErrorMsg("Please select Occured Time");
            return false;
        }
        
        // Validate Prepared By
        var preparedBy = jQuery("#cmbLoemIdentifiedby").combobox('getValue');
        if(preparedBy == null || preparedBy.length == 0){
            popupCommonErrorMsg("Please select Prepared By");
            return false;
        }
        
        // Validate Description of LOPC
        var lopcDesc = jQuery.trim(jQuery("#txtLoemLopcdesc").val());
        if(lopcDesc.length == 0){
            popupCommonErrorMsg("Please enter Description of LOPC");
            jQuery("#txtLoemLopcdesc").focus();
            return false;
        }
        
        // Validate Functional Location (JH)
        var flid = jQuery("#frmLOPCCreation input[id='flid']").val();
        var cellId = jQuery("#frmLOPCCreation input[id='cell']").val();
        var LopcId = jQuery("#hdnLopcID").val();
        
        if(cellId == null || cellId.length == 0){
            popupCommonErrorMsg("Select JH");
            return false;
        }
        
        return "LopcId=" + LopcId;
    }
    
    function frmLOPCCreation_successsCallback(result){
        var msg=result.successData.glbmsg;
		var popup=result.successData.glbpopup;
		closePopUpDialoge("loadNearMissModify");
		closePopUpDialoge("loadNearModify");
		closePopUpDialoge("LOPCModify"); 
		if(popup==true)
		{

		}
		if(msg.length!=0)
		{
		}
	 
            clearForm();
            fillWithCurrentDate('dteLoemOccurencedatetime');
            fillWithCurrentDate('dteLoemPrepareddatetime');
			fillWithCurrentDate('spnOccurrencetime');	
			fillWithCurrentDate('dteLoemPrepareddatetime');
			fillWithCurrentDate('spnPreparedDatetime');	
		
	    if(result.successData.openfilemgr=="true"){
             var documentNo =result.successData.keyId;
	    	 if(documentNo!=undefined){
	 	    apMode = "create";
	 			fileManagerPopUp(documentNo,"NM","","","",apMode);
	    		 }
	    	}
	   navigateToprevForm();
	   closePopUpDialoge("frmLOPCCreation");
	   if(jQuery("#lopcModifyGrd").length > 0) {
	        jQuery("#lopcModifyGrd").trigger('reloadGrid');
	    }
	   
	}
    
    function frmLOPCCreation_deleteSuccessCallback(result){ 
    	alert(result.successData.msg);
    	navigateToprevForm();
    }

</script>

<form id="frmLOPCCreation" name="frmLOPCCreation" >
<div id=""  style="padding: 10px;">
<div style=" margin-left:3%;margin-top:-2px;">
<table cellspacing="15" >
<tr>
<td colspan="3">
                <div id="frmLOPCCreationFuntKeyIds"  >                           
                           
                                <input type="hidden" id="section" name="cmbloemSectionid"  value=""></input>
                                <input type="hidden" id="cell"    name="cmbloemCellid"     value=""></input>
                                <input type="hidden" id="machine" name="cmbloemMachineid"  value=""></input>       
                                <input type="hidden" id="flid" name="cmbloemFnlid" value="${requestScope.lopcEntryMst.loemFnlid}"/>
                               <%--  <input type="hidden" id="elementId" name="txtelementid" value="${requestScope.lopcEntryMst.elementid}"  />  --%>                  
                            </div>                      
                        <div id="LopcFunLocation" style="width:100%;"></div>  </td>
                        <td>
                         <div style="margin-left:0px;margin-top:0px;">
            <label><b> Document No</b></label>
            </div>
            <div style="margin-left:0px;;margin-top:0px;" >
                    <input id="txtdocuno" name="txtdocuno" type="text" class="easyui-text"  style="width:150px; height: 22px;text-align:center; font-weight:bold" value="${requestScope.lopcEntryMst.loemKeyid}"/>
       </div>
                        </td>
                        		<td>
			<div style="position: relative;margin-left:0px;margin-top:29px;"><span id="NearMissFilemgr"
			style="position: absolute;padding-right:30px; top: -20px;">
		</span></div></td>                          
</tr>
<tr>  
        <td>         
            <div>
            <label class="mandatory-lbl">LOPC Category</label>
            </div>
            <div id="LopcCategory">
                <input type="text" class="easyui-combobox" id="cmbLoemLocacategoryid"
                name="cmbLoemLocacategoryid"  style="width: 180px;/*  height: 20px */"
                value="${requestScope.lopcEntryMst.loemLocacategoryid}" />
                </div>
                </td>
                         <td>
            <div style="margin-left:-25%;">
            <label class="mandatory-lbl">Occured Date & Time</label>
                       
            </div>
            <div class="easyui-paddingbfpx" style="margin-left:-25%;">
            <span>
            <input id="dteLoemPrepareddatetime"  name="dteLoemPrepareddatetime"  clear="false" class="easyui-datebox" value="${requestScope.preparedate}"  style="width:118px;width:190px\9;/* height:21px; */"  />
            </span>
             <span class="spinner easyi-paddingbfpx">
             <input  id="spnPreparedDatetime"  name="spnPreparedDatetime"   class="easyui-timespinner spinner-text validatebox-text" value="${requestScope.prepared}"  style="width: 60px;" />
             </span>
             </div>
            </td>
                 <td>
            <div style="margin-left:-60%;margin-top:-5px;">
                    <label class="easyui-paddingbfpx mandatory-lbl">Prepared By</label>
                    <span style="padding-left:5px;">
                     <input type="checkbox" id="chkkzbnPrepareOthers" name="chkkzbnPrepareOthers" style="margin-left:10px;" />
                    </span>
                    <span style="padding-left:5px;"> <label>Others</label> </span>
                </div>
                <div style="margin-left:-60%;">
                    <input class="easyui-combobox" type="text"  id="cmbLoemIdentifiedby"    name="cmbLoemIdentifiedby" style="width: 180px;/*  height: 20px */"
                 value="${requestScope.lopcEntryMst.loemIdentifiedby}" />
                </div>
            </td>
                 <td>
                      <span>  <input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:25px;display:;" value="Excel View" />
 </span>
     <span>  <input type="button" id="btnInvestigation" name="btnInvestigation" class="easyui-button" style="width:90px;height:25px;" value="Investigation" />
 </span>
 
          
</td>

				
		
        </tr>

      </table>
      <table>         
    <tr>
    <td>
   
      <div><label class="mandatory-lbl"style="margin-left:10px;">Description of LOPC</label>
      </div>
            <div class="easyui-paddingbfpx"style="margin-left:10px;">
                <textarea  rows="2" cols="80"  style="width :820px; height : 66px;" id="txtLoemLopcdesc" name="txtLoemLopcdesc">${requestScope.lopcEntryMst.loemLopcdesc}</textarea>
            </div>
            </td>
       
 

 </tr>                    
</table>
</div>
</div>
<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.frmMode}" />
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
<input type="hidden" id="hdnWwblKey" name="hdnWwblKey" value="${requestScope.WWblaKeyid}"/>
<input type="hidden" id="hdnrole" name="hdnrole" value="${requestScope.rolename}" />
<input type="hidden" id="hdnformtype" name="hdnformtype" value="${requestScope.formType}" />
<input type="hidden" id="hdnLopcID" name="hdnLopcID" value="${requestScope.lopcEntryMst.loemKeyid}" />
<input type="hidden"  id="hdnFnlid" name="hdnFnlid" value="${requestScope.lopcEntryMst.loemFnlid}"/>
<input type="hidden"  id="hdnFilemngr" name="hdnFilemngr" value=""/>
<input type="hidden"  id="hdnStatus" name="hdnStatus" value="${requestScope.Status}"/>
</form>  