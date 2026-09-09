
<script type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	//setLoadFormCallBackFrmId("frmSafeactStratification");
	var type=jQuery('#hdnviewtype').val();
	jQuery('#chkVew15').attr('checked',true);
	 processGridnew("reminder_input.alerts","&q=2&type=VL","reminderGrd","reminderpager","","remDoubleClick","","remiderGrid_loadComplete");
});

jQuery('#chkVewAll').click(function(){
       if(jQuery("#chkVewAll").is(":checked")==true){
			  jQuery('#chkVew15').attr('checked',false); 
		  }
	});
	
jQuery('#chkVew15').click(function(){
       	if(jQuery("#chkVew15").is(":checked")==true){
			jQuery('#chkVewAll').attr('checked',false);	
		} 
	});
	
jQuery('#btnRemainderView').click(function(){
	var types;
    var vl15=jQuery("#chkVew15").is(":checked");
    var vwall=jQuery("#chkVewAll").is(":checked");    
    if(vl15==true)
    	{
    	   types="VL";
    	}
    else if(vwall==true)
	{
	   types="VW";
	}
		processGridnew("reminder_input.alerts","&q=2&type="+types,"reminderGrd","reminderpager","","remDoubleClick","","remiderGrid_loadComplete");
});	

function remDoubleClick(id){
	var rowData = jQuery("#reminderGrd").jqGrid('getRowData',id);
	var document = rowData.DOCUMENT;
	var docNo    = rowData.DOCUMENTNO;
	var docDate     = rowData.DOCDATE;
	var item =rowData.ITEM;
	var allotedBy   = rowData.CREATEDBY;
	var checkYNo="Y";
	//alert(item);
	var formurl     = "";
	var formCaption = "";
	if("Action Plan"==document)
	{	
		var itemsplit=  item.indexOf("(") + 1;
		var sub=item.substring(itemsplit, item.length-1);
	 	var type=sub.substring(1,4);
	 	//
		if(type=="AUD"){
	    var split=sub.substring(7,11);
	    if(split=="DIVD"){
	    	  formurl     = "AuditDetailsgrid_input.ehsaudit?mode=Closure&type=divisional&";
	    	  formCaption = "Divisional Audit";
	    	  }else if(split="CORD"){
	    	  formurl     = "AuditDetailsgrid_input.ehsaudit?mode=Closure&type=corporate&";
	    	  formCaption = "Corporate Audit";
	    	  }else{
	    	  formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy+"&checkYNo="+checkYNo;
	  		formCaption = "Action Plan";
	      }
		 }
	 else{
   	  formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy+"&checkYNo="+checkYNo;
		//formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy;  
		formCaption = "Action Plan";
	 }
	 	
		
		if(type=="PSI"){
		    var split=sub.substring(7,10);
		    if(split=="PSD"){
		    	  formurl     = "safetyinspectiondetails_input.safetyinspect?mode=Closure&";
		    	  }else{
			    	  formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy+"&checkYNo="+checkYNo;
		    	 // formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy;
		  		  formCaption = "Action Plan";
		      }
		}else{
	    	  formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy+"&checkYNo="+checkYNo;
			//formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy;
			formCaption = "Action Plan";
		 }

		if(type=="NM "){
		
		           if(type=="NM ")   
                        {
		        	   
		        	   formurl     = "Nearmissgrid_input.nmrnew?&fromMode=reminder&formType=Closure&";
		    	  }
		           else{
				    	  formurl     = "ActionPlan_input.api?&fromMode=reminder&actPlanKeyId="+docNo+"&allotedBy="+allotedBy+"&checkYNo="+checkYNo;
		        	   formCaption = "Action Plan";
		   		 }

		     
		}	
		}
	/*if("Audit"==document)
	{	
		formurl     = "AuditDetailsgrid_input.ehsaudit?mode=Closure&type=daily&";
		formCaption = "Audit";
	}*/
	else if("MOCApproval"==document)
	{	
		formurl     = "ChangeRequest_input.nmoc?&fromMode=reminder&MocKeyid="+docNo;
		 // navigateToNextForm("ChangeRequest_input.nmoc?q=2&SuggestionFlid="+SuggestionFlid+"&Suggestion="+Suggestion+"&SuggestionId="+SuggestionId+"&MocKeyid="+MocKeyid+"&Responsibility="+Responsibility+"&filterButton=false","MOCCreation");
		formCaption = "MOC Approval";
	}
	else if("Abnormality"==document)
	{	
		//filterString += "&modeType=complete";
		 
		//formurl     = "Abnormality_input.abnForm?&fromMode=reminder&modeType=complete&AbnId="+docNo;
		formCaption = "Abnormality Completion";
		formurl = "Abnormality_input.abnForm?q=2&AbnId="+docNo+"&abnStatus=PENDING&mode=complete&modeType=complete&filterButton=false";
	}else if("Near Miss"==document)
	{	
		formCaption = "Near Miss";
		formurl = "Nearmiss_input.nmr?q=2&grid=true&clearfrom=false&keyId="+docNo;
	}else if("Kaizen"==document)
	{	
		
		formCaption = "Suggestion Implementation";
		formurl = "KaizenBankImplement_input.kznbnk?&fromMode=reminder&KzbnKeyid="+docNo;
		//alert("FormURLS"+formurl);
	}
	else if("Safety Suggestion"==document)
	{	
		formurl     = "SafetySuggestionGrid_input.kznbnk?&fromMode=reminder&KzbnKeyid="+docNo;
		
		formCaption = "Safety Suggestion";
	}	
	else if("Direct-Hazop"==document){
		//alert(document);
		//alert(docNo);
		formCaption = "Hazop Creation";
		formurl = "HazopCreate_input.hzop?&mode=modify&keyid="+docNo;
	}
	
	//navigateToNextForm("Abnormality_input.abnForm?q=2&refDocId="+refDocId+"&filterButton=false"+"&flid="+selFlid,"Abnormality Identification");
	if( formurl != ""){
		if( jQuery(".layout-split-west").is(":visible")){
			 jQuery('#mainlayout').layout('collapse','west');
		}
		//alert("navigation");
		jQuery(".reminder_content_div").slideUp("fast");
		navigateToNextForm(formurl,formCaption);
	}
	
}
</script>
<input type="hidden" id="hdnviewtype" name="hdnviewtype" value="${requestScope.type}" />
<div id="wrapperRpt">
	<div style="margin-left:10px;margin-top:50px;">
	
	<label>View ALL </label>
	<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkVewAll" name="chkVewAll" type="checkbox" /></span>
	<label>View Last 15 days</label>
	<span style="border: solid 2px #c1c1c1; padding: 3px;"><input id="chkVew15" name="chkVew15" type="checkbox" /></span>
	<span style="position:relative;top:5px\9;">
	<input id="btnRemainderView" class="easyui-button" style="padding-top:0;" type="button" value="Submit"/> </span>
		<table id='reminderGrd'><tr><td></td></tr></table>
		<div id='reminderpager'></div>
	</div>
</div>