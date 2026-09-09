<script type="text/javascript">
var glbtargetdate="";
var glbrevisedate="";
jQuery(document).ready(function(){
	initialiseForm('frmKaizensugg');
	var empid=jQuery('#hdnEmpid').val();
	fillComboBox("frmAuditClosure","cmbSdadResponsibilty","employee.commonFilter?");
	formatDateBox('dteKzbnDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteKzbnDate');
	fillComboBox("frmKaizensugg", "cmbKzbnSuggestedby", "employee.commonFilter");
	fillComboBox("frmKaizensugg", "cmbKzbnResponsibility", "employee.commonFilter");
	fillComboBox("frmKaizensugg", "cmbMocItem", "mocitem.commonFilter");
	
    var keyid=jQuery("#hdnKzbnsugKeyid").val();
    numericTextBox("txtKzbnImplementcost");
    processAjaxCalls("kaizenFillkzn_modify.kznbnk","keyid="+keyid,"updatekznSuccess","");

    setFieldValue("cmbKzbnSuggestedby",jQuery('#hdnLoginUserid').val());
    
    jQuery("#cmbMocItem").combobox("clear");
    jQuery("#cmbMocItem").combobox("disable");
    
 // On checkbox change
    jQuery("#chkKzbnmocrequired").on("change", function() {
        if (jQuery(this).is(':checked')) {
            jQuery("#cmbMocItem").combobox("enable");
            jQuery('#cmbMocItem').addClass('mandatory-lbl'); 
            mocrequired = "Y";
        } else {
            jQuery("#cmbMocItem").combobox("clear");
            jQuery("#cmbMocItem").combobox("disable");  // clear value when unchecked
            mocrequired = "";
        }
    });
    
    jQuery('#txtKzbnImplementcost').keydown(function(event) {
        //alert(" KeyCode :: "+event.keyCode);
       if(event .numeric()==true)
    	   {
    	    return true;
    	   }
       else {
    	   return false;
       }
    	   
	    /*if (event.keyCode == 17 || event.keyCode >= 48 && event.keyCode < 58){
	        event.preventDefault(); 
	        return false;
	    }
	    if (event.keyCode >= 96 && event.keyCode < 106){
	        return ;
	    }*/
	});
   
});



jQuery('#cmbSdamAudittype').combobox({
    onSelect: frmKaizensuggcmbSdamAudittype_onSelect
});

jQuery("#btnSubmit").click(function(){
	
		 var keyid=jQuery("#hdnKzbnsugKeyid").val();
		 var kznkaizen=getFieldValue("txtKzbnKaizen"); 
		 var kznstatus=getFieldValue("cmbSdamAudittype");
		// alert(kznstatus);
		 var kznApprvid=getFieldValue("cmbKzbnSuggestedby");
		 var kznimpcost=getFieldValue("txtKzbnImplementcost");
		 var kzndate=getFieldValue("dteKzbnDate");
		 var kznremarks=getFieldValue("txtKzbnVerifyremarks");
		 var respons=getFieldValue("cmbKzbnResponsibility");
		 var mocitem=getFieldValue("cmbMocItem");
		 
			var mocrequired;
			if(jQuery("#chkKzbnmocrequired").is(':checked')==true){
				////alert("inside")
				
			mocrequired="Y";	
			}//var mocrequired=getFieldValue("chkKzbnmocrequired");
		else{
		
			mocrequired="N";	
			}
		
		//alert("Moc Required"+mocrequired)
	 	if (kznstatus.trim().length==0) { 
	 		alert(" Select Accept/Reject");
			return false;
		}
	
		if (kzndate.trim().length==0) { 
			alert(" Select Target Date");
			return false;
		}
		
		if (kznApprvid.trim().length==0) { 
			alert(" Select Approved By");
			return false;
		}
		
		if(respons.trim().length==0 && kznstatus=="V"){ 
			alert(" Select Responsibility");
			return false;
		}
		
		if(mocitem.trim().length==0 && mocrequired=="Y"){ //change here 
			alert(" Select MOC Item");
			return false;
		}

		
		var dataString="?&frmsave=popup&keyid="+keyid+"&kznkaizen="+escape(kznkaizen)+"&kznstatus="+kznstatus+"&kznApprvid="+kznApprvid;
	    dataString+="&kznimpcost="+kznimpcost+"&kzndate="+kzndate+"&kznremarks="+escape(kznremarks)+"&respons="+respons+"&mocrequired="+mocrequired;  
	
	    if(mocitem){
	    	dataString+="&mocitem="+mocitem;
	    }
	
	    //processAjaxCalls("KaizenBankSuggestion_save.kznbnk",dataString,"updateKaizenDatapopup","");
	    processAjaxCalls("kaizenBankAcceptVerifypopup_save.kznbnk",dataString,"updateKaizenDatapopup","");
	 //closePopUpDialoge("divKaizenBankPop");     
});



function updateKaizenDatapopup(result){
	alert("Data Updated Successfully");
	closePopUpDialoge("loadkaizenbank");
	setFieldValue("txtKzbnKaizen","");
	setFieldValue("txtKzbnVerifyremarks","");
	setFieldValue("cmbSdamAudittype","");
	setFieldValue("txtKzbnImplementcost","");
	setFieldValue("cmbKzbnSuggestedby","");
	setFieldValue("cmbKzbnResponsibility","");
	
}

function loadkaizenbank_onClose(){
	jQuery("#KZBankGrid").trigger("reloadGrid");
	return true;
}

function updatekznSuccess(result){
	jQuery('#txtKzbnKaizen').val(result[0][0]);
}


function frmKaizensuggcmbSdamAudittype_onSelect(record)
{
	
	
    var auditType = record.id;
    
  
    if (auditType == "R" || auditType == "E")
    {
        jQuery('#chkKzbnmocrequired').prop('checked', false);
        jQuery('#chkKzbnmocrequired').prop('disabled', true);

        jQuery("#cmbMocItem").combobox("clear");
        jQuery("#cmbMocItem").combobox("disable");

        mocrequired = "";
    }
    else
    {
        jQuery('#chkKzbnmocrequired').prop('disabled', false);
    }
}


</script>


<form id="frmKaizensugg" name="frmKaizensugg">
<table align="center">
     <tr>
        <td>
           	<div id="Status"  style="margin-left:30px;">
				<label><b>Suggestion</b></label>
			<div>
         		<textarea style="width: 250px; height : 80px;" id="txtKzbnKaizen" maxlength="490" name="txtKzbnKaizen" >${requestScope.kaizenbank.kzbnKaizen }</textarea>
		    </div>
		    </div>
        </td>
 <td>
	          <div id="Status"  style="margin-left:30px;">
					<label><b>Remarks</b></label>
				<div>
	         		<textarea style="width: 250px; height : 80px;" id="txtKzbnVerifyremarks" maxlength="490" name="txtKzbnVerifyremarks" style="width: 200px; height: 21px;" value="" ></textarea>
			    </div>
			    </div>
	      </td>          
        <td valign="top">
            <div id="Status"  style="margin-left:20px;">
				<label class="mandatory-lbl"><b>Accept/Reject</b></label>
			<div>
			<select id="cmbSdamAudittype" class="easyui-combobox" name="cmbSdamAudittype"   style="width:120px; ">  		
				    <option value='V'><b>Accepted</b></option>
					<option value='R'><b>Rejected</b></option>
					<option value='E'><b>Rework</b></option> 
			</select>
		    </div>
		    </div>
        </td> 
        <td>
        <div style="margin-left:-120px;margin-top:40px;">
 		<label style="color:green;"><b>MOC Required</b></label>
	<span><input id="chkKzbnmocrequired" name="chkKzbnmocrequired" type="checkbox" /></span>
	<br>
	<label><b>MOC Item</b></label>
			<div>
			<input id="cmbMocItem" class="easyui-combobox" name="cmbMocItem"  style="width: 120px;/*  height: 21px; */" value="">  		
				  
			</div>
	</div>
        </td>
          
     </tr>
     </table>
     <table>
     <tr>
        <td>
        <br>
            <div id="Status"  style="margin-left:30px;">
				<label><b>Implementation Cost</b></label>
			<div>
			<div style="padding-left: 0px;">
				<input type="text" class="easyui-text"  id="txtKzbnImplementcost" name="txtKzbnImplementcost" maxlength="10"   style=" width : 120px; text-align:left;" value=""/>
			</div>
			</div>
		    </div>
        </td>
        <td>
        <br>
            <div id="Status"  style="margin-left:30px;">
				<label class="mandatory-lbl"><b>Target Date</b></label>
			<div>
			<div style="padding-left: 10px;">
				<input id="dteKzbnDate"  name="dteKzbnDate" clear="false" class="easyui-datebox" value=""  style="width:80px;/* height:21px; */"  />
			</div>
			</div>
		    </div>
        </td> 
         <td>
         <br>
           	<div id="Status"  style="margin-left:30px;">
				<label><b>Approved by</b></label>
			<div>
         		<input class="easyui-combobox" id="cmbKzbnSuggestedby" name="cmbKzbnSuggestedby" style="width: 200px; /* height: 21px; */" value="${requestScope.kaizenbank.kzbnSuggestedby }"/>
		    </div>
		    </div>
        </td>  
         <td>
         <br>
           	<div id="Status"  style="margin-left:30px;">
				<label><b>Responsibility</b></label>
			<div>
         		<input class="easyui-combobox" id="cmbKzbnResponsibility" name="cmbKzbnResponsibility" style="width: 200px;/*  height: 21px; */" value=""/>
		    </div>
		    </div>
        </td>  
        </tr>
        </table>
        <table>
        <tr>
	   <td colspan="3">
	   <div style="margin-left: 300px;margin-top:14px;">
	   	 <input type="button" id="btnSubmit" name="btnSubmit" class="easyui-button" value="Submit"/>
	   </div>
	   </td> 
     </tr>
   </table>           
<input type="hidden"id="hdnApmode" name="hdnApmode" value="${requestScope.apMode}" />
<input type="hidden" id="hdnKzbnsugKeyid" name="hdnKzbnsugKeyid" value="${requestScope.suggKeyid}"></input>
</form>
