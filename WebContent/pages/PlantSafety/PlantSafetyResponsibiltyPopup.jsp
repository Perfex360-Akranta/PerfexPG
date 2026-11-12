<script type="text/javascript">
var glbdate="";
jQuery(document).ready(function(){
	initialiseForm('frmResponseAudit');
	
	jQuery('#submitForm').val('frmResponseAudit');	
	fillComboBox("frmResponseAudit","cmbSdadResponsibilty","employee.commonFilter" );
	formatDateBox('dteSdadTargetDate','dd-MMM-yyyy');
	
	fillWithCurrentDate('dteSdadTargetDate');
	glbdate=jQuery("#dteSdadTargetDate").datebox('getValue');
});

jQuery("#dteSdadTargetDate").datebox({  	   
	onSelect:function(recordid){ 
		var currentDate = getServerDateTime();
		var datevalue=jQuery("#dteSdadTargetDate").datebox('getValue');
		var stringdate=convertStringToDate(datevalue);
		if(stringdate < currentDate){  
			popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate('dteSdadTargetDate');
			return false;
		}
	} 
});

jQuery('#chkkzbnOthers').click(function() {
    if(jQuery("#chkkzbnOthers").is(':checked')== true){
   	 jQuery('#chkkzbnOthers').val('Y');
   	 var sat = jQuery('#chkkzbnOthers:checked').val();
	 jQuery("#cmbSdadResponsibilty").combobox('setValue',"");
	 othersClickAction(sat);
    }else if(jQuery("#chkkzbnOthers").is(':checked')== false){
        jQuery('#chkkzbnOthers').val('N');
        var sat = jQuery('#chkkzbnOthers').val();
   	 	jQuery("#cmbSdadResponsibilty").combobox('setValue',"");
		othersClickAction(sat);
	}
});

function othersClickAction(sat){
	if(sat=="Y"){
	  reloadCombo("frmResponseNear","cmbSdadResponsibilty","employee.commonFilter?&others=Y");
	}else{  
	  reloadCombo("frmResponseNear","cmbSdadResponsibilty","employee.commonFilter?&others=Y");
	}	
}

jQuery("#btnexcelview").click(function(){
    var plansafKeyid=jQuery("#hdnpalnkey").val();
	var flid = jQuery("#hdnflid").val();
   if(plansafKeyid != null && plansafKeyid.length >0){
       window.open("Plantsafety_Excelview.safetyinspect?plansafetyKeyid="+plansafKeyid+"&flid="+flid);
   }else{
  	 return false;
   }
});

jQuery("#btnview").click(function(){
	var Keyid=jQuery("#hdnpalnkey").val();
	var role=jQuery("#hdnrolename").val();
	var flid=jQuery("#hdnflid").val();
	LoadPopUp("Plantsafety","safetyinspection_input.safetyinspect?keyid="+Keyid+"&mode="+"modify"+"&audittype="+role+"&auditflid="+flid+"&popup=Y", true,"90%","80%","10%","5%", "","Investigation"," ",true,true );
});

jQuery("#btnsaveres").click(function(){
	   var keyid=jQuery("#hdndtlkeyid").val();
	   var observe=jQuery("#hdnobserve").val();
	   var aplmkeyid=jQuery("#hdnaplmid").val();
	   var apldkeyid=jQuery("#hdnapldid").val();
	   var remarks=jQuery("#txtSdadResponseRemarks").val();
	   var responsevalue=jQuery("#cmbSdadResponsibilty").combobox('getValue');
	   var targetdate=jQuery("#dteSdadTargetDate").datebox("getValue");

	   if(targetdate.length==0){
	      jQuery("#dteSdadTargetDate").datebox("setValue",glbdate);
	      targetdate=glbdate;
	   }

	   if(responsevalue.length==0){
	      popupCommonErrorMsg("Select Responsibilty");
	      return false;
	   }

	   if(responsevalue.length!=0 && targetdate.length!=0){
	      processAjaxCalls("plantsafetypopup_save.safetyinspect?q=2&responsevalue="+responsevalue+"&targetdate="+targetdate+"&dtlkeyid="+keyid+"&observe="+observe+"&aplmkeyid="+aplmkeyid+"&apldkeyid="+apldkeyid+"&rmrks="+encodeURIComponent(remarks),"","","");

	      setTimeout(function() {
	         closePopUpDialoge("loadAuditverifyResponse");
	         jQuery("#safetyinspectgrid").trigger("reloadGrid");
	         alert("Response Assigned Successfully");
	      },1500);
	   }
	});</script>

<form id="frmResponseAudit">
<div id="loadresponse">
<table align="center" style="width:100%;">
     <tr>
          <td>
               <div style="margin-left:10%;">
                   <label class="mandatory-lbl"><b>Responsibily</b></label>
                   <span style="padding-left:10px;">
                       <input type="checkbox" id="chkkzbnOthers" name="chkkzbnOthers" style="margin-left:10px;" />
                   </span>
                   <span style="padding-left:05px;">
                       <label>Others</label>
                   </span>
               </div>
               <div style="margin-left:10%;">
                   <input id="cmbSdadResponsibilty" name="cmbSdadResponsibilty" class="easyui-combobox" style="width: 230px;" value="${requestScope.Response}"/>
               </div>
          </td>
          
          <td>
               <div style="margin-left:07%;"><label class="mandatory-lbl"><b>Target Date</b></label></div>
               <div style="margin-left:07%;">
                   <span style="float:left;padding-right:0px;">					
                       <input class="easyui-datebox" style="width: 100px;" id="dteSdadTargetDate" name="dteSdadTargetDate" value=""/>
                   </span>
               </div>
          </td>
          
          <td>
               <div style="margin-left:10%;margin-top:15%;">
                   <input type="button" id="btnexcelview" name="btnexcelview" class="easyui-button" style="width:80px;height:25px;display:;" value="Excel View" />
               </div>
          </td>
     </tr>
     
     <tr>
          <td>
               <div style="margin-left:10%;"><label><b>Remarks</b></label></div>
               <div style="margin-left:10%;">
                   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtSdadResponseRemarks" name="txtSdadResponseRemarks" style="resize:none;width: 230px;" maxlength="1000" value="${requestScope.remarks}">${requestScope.Remarks}</textarea>
               </div>
          </td>
          
          <td>
               <div style="margin-left:07%;margin-top:5%;">
                   <input type="button" id="btnview" name="btnview" class="easyui-button" style="width:80px;height:25px;display:;" value="View" />
               </div>
          </td>
     </tr>
     
     <tr>
          <td></td>
          <td>
               <div style="margin-left:30%; margin-top:4%;">
                   <input type="button" class="easyui-button" value="submit" id="btnsaveres" style="height:23px;"/>	
               </div>
          </td>
     </tr>
</table>

<input type="hidden" id="hdndtlkeyid" name="hdndtlkeyid" value="${requestScope.Detailkeyid}"></input>
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid}"></input>
<input type="hidden" id="hdnaplmid" name="hdnaplmid" value="${requestScope.aplmaster}"></input>
<input type="hidden" id="hdnapldid" name="hdnapldid" value="${requestScope.apldetail}"></input>
<input type="hidden" id="hdnrspn" name="hdnrspn" value="${requestScope.Response}"></input>
<input type="hidden" id="hdnobserve" name="hdnobserve" value="${requestScope.observe}"></input>
<input type="hidden" id="hdnpalnkey" name="hdnpalnkey" value="${requestScope.modiefiedid}"></input>
<input type="hidden" id="hdnrolename" name="hdnrolename" value="${requestScope.modiefiedid}"></input>
</div>
</form>