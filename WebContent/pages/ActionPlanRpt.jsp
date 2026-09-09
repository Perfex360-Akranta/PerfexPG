<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> --%>
<script type="text/javascript">
var apMode = "";
jQuery(document).ready(function(){//alert("This actionplan jsp");
    initialiseForm('frmActionPlan');
	jQuery('#submitForm').val('frmActionPlan');
	//alert(" MainTask :: "+requestScope.newGenTlActionplanmst.aplmMaintask.length());
	var location = jQuery("#frmActionPlan input[id='location']").val();
	//alert("alert"+jQuery("#cmbActionplanAlby").val());
	var factId = jQuery("#frmActionPlan input[id='factory']").val();
	var sectionId = jQuery("#frmActionPlan input[id='section']").val();
	var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	var machId = jQuery("#frmActionPlan input[id='machine']").val();
	var flid = jQuery("#frmActionPlan input[id='flid']").val();
	readOnlyFields("cmbActionplanAlby");
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
  	loadFunctionalLocation("ApfunLocation", "functionalLoc.commonFilter","ApfunLocationValues", "frmActionPlan", dataStr);
  	
  	fillComboBox("frmActionPlan","cmbApldCompletedby","employee.commonFilter");
  	fillComboBox("frmActionPlan","cmbApldRole","Rolecombo.mom");
  	fileManagerPopUp("","ACT","frmActionPlan","btnActfilemgr","ActFilemgr");
  	var allot=jQuery("#cmbActionplanAlby").val();
  	var checkYN=jQuery("#hdnchkyno").val();
  	if(allot.length!=0 && checkYN=='Y')
  		{
  		fillComboBox("frmActionPlan","cmbActionplanAlby","employee.commonFilter");
  		}
  	else{
  		jQuery('div#allot').hide();
  	}
  	var pillarid=jQuery("#frmActionPlan input[id='hdnpillarid']").val(); //jQuery('#hdnpillarid').val();
    var type=jQuery('#hdntype').val();
    var masterrefid=jQuery("#txtAplmMasterrefid").val();
    var refdoctype=jQuery("#txtAplmRefdoctype").val();
    
    /*if(type.trim().length==0){
    	jQuery("#rmndrid").css('margin-top','-80px');
    	jQuery("#rmndrid").css('margin-left','6px');
    	jQuery("#stusid").css('margin-top','-80px');
    	jQuery("#stusid").css('margin-left','6px');
    }else{
    	jQuery("#rmndrid").css('margin-top','-0px');
    	jQuery("#rmndrid").css('margin-left','6px');
    	jQuery("#stusid").css('margin-top','-0px');
    	jQuery("#stusid").css('margin-left','6px');
    }
    */
    	
  	/*if(jQuery("#txtAplmRefdoctype").val()=="MOM") 
  	    fillComboBox("frmActionPlan","cmbApldResponsibility","momgroupbasedemployee.api?&refid="+jQuery("#txtAplmMasterrefid").val()+"&reftype="+jQuery("#txtAplmRefdoctype").val());
	else
	*/

	 if(jQuery("#txtAplmRefdoctype").val()=="MOM") 
	    fillComboBox("frmActionPlan","cmbApldResponsibility","mompillargroupbasedemployee.api?&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&flid="+flid+"&location="+location);
     else
        fillComboBox("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&loginEmpshow=false&cellId="+cellId+"&flid="+flid);

  	 //alert(" type :: "+type);
    
	 var datastr="?&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&flid="+flid;
	 //alert(" datastr :: "+datastr);
	 //processGridnew("ActionPlanresponsibility_input.api",datastr,"ActionGridResp");
	 processGridnew("ActionPlanresponsibility_input.api",datastr,"ActionGridResp","","","","respLoadcomplete"); 
	 
	//fillComboBox("frmActionPlan","cmbApldResponsibility","employee.commonFilter");	
  	fillComboBox("frmActionPlan","cmbApldTradeid","trade.commonFilter");
  	fillComboBox("frmActionPlan","cmbApldStatus","Combo_Status.api","",false);
	formatDateBox('dteApldTargetdate','dd-MMM-yyyy');
	formatDateBox('dteApldCompleatedon','dd-MMM-yyyy');
	//jQuery('#frmActionPlan .easyui-text').css('text-transform', 'uppercase');
	//jQuery('#frmActionPlan textarea').css('text-transform', 'uppercase');	
	disableUIButton("btnDeleteaction");
	disableUIButton("btnclear");
	setFieldValue('cmbApldStatus','P');
	setTimeout(function() {fillWithCurrentDate("dteApldTargetdate");},500);
	var url = "ActionPlanmodify_input.api";
  	var AplKeyId = jQuery("#hdnAplmKeyid").val();
  	var refDocId = jQuery("#txtAplmMasterrefid").val();
  	var refDtlId = jQuery("#txtAplmDetailrefid").val();
  	//alert(" refDocId :: "+refDocId+" refDtlId :: "+refDtlId);
  	//alert(" Inside Actionplan jsp:::::::  "+refDocId);
  	var refDocType = jQuery("#txtAplmRefdoctype").val();
  	//alert(" Inside Actionplan jsp:::::::  "+refDocType);
  	var flid = jQuery("#txtAplmFlid").val();	  	
  	var modetail = jQuery("#txtAplmModet").val();
  	//alert(" Inside Actionplan jsp:::::::  "+modetail);
   	if(refDocId!=null ||refDocId!='' || refDocId!="undefined"){
  			jQuery("toremovewrapper").removeClass('wrapper');
  			jQuery("maindivid").css('margin-left',' 3%');
  	}

   	//alert(refDtlId);
	if (refDtlId.length<3) {
		jQuery('#divMenu').css('padding-left:26%;relative;margin-top: -20px;');
		//<div style="padding-left: 26%;position: relative;margin-top: -20px;">
		//<span style="position: ; right: ;top: 1px;">
	}

	var KeyId=jQuery("#txtAplmKeyid").val();
	//alert(KeyId);
	var dataStr = "q=2&KeyId="+KeyId+"&aplmRefdocid="+refDocId+"&aplmRefDtlid="+refDtlId;
	//alert(dataStr);
	processGridnew("ActionPlanmodify_input.api", dataStr, "ActionGrid", "pager2", "", "doubleClick", "");				
	var status =jQuery("#cmbApldStatus").val(); 				
	if(status=='C'){					
		jQuery("#compltedDetails").css('display', 'none');
		jQuery("#compltedDetails1").css('display','none');
		jQuery("#compltedDetails2").css('display','none');
		jQuery("#compltedDetails3").css('display','none');
	}
	
	//jquery("#ApfunLocation").attr('readonly', true);
  	//disableFunctionalLocation("true","ApfunLocation");

  	jQuery('#chkSelAll').click(function() {
  	  //show_winMask(1);
      var row = jQuery("#ActionGridResp").jqGrid('getDataIDs');
      
      if(jQuery("#chkSelAll").is(':checked')== true){
     	 for(var i=0;i<row.length;i++)
     	 {
     		jQuery('#jqg_ActionGridResp_'+row[i]).attr('checked', true);
         }
     }
	 else { 
		 for(var i=0;i<row.length;i++)
     	  {
     		jQuery('#jqg_ActionGridResp_'+row[i]).attr('checked', false);
		  }
	}
		
	//show_winMask(0);
});
  		jQuery("#btnInsertActionPlan").click(function() {
		
		var actnpln=getSelectdRowsActplan('ActionGridResp','ActionGridResp_','chkbox');
		var respnsibility=getFieldValue("cmbApldResponsibility");
		var hdnrespn=jQuery('#hdnrspnlity').val();
		var othrsChck = jQuery('#chkApldRepOtheres').is(':checked');
		
		if(othrsChck == true)
			othrsChck="Y";
		else
			othrsChck="N";
		
		jQuery('#hdninsert').val('insert');
		if((actnpln.trim().length==0 && respnsibility.trim().length==0)){
			popupCommonErrorMsg(" Select Responsibility ");
			return false;
		}

		if(respnsibility.trim().length==0){
		    var gridData = '&actplnDetails='+encodeURIComponent(actnpln)+"&Actionplan=Actnplnidn&hdnrespn="+hdnrespn+"&othrsChck="+othrsChck;
		    saveForm("frmActionPlan","ActionPlan_save.api?&Type=mstaction"+gridData,"");
		}else{
			saveForm("frmActionPlan","ActionPlan_save.api?&Type=mstaction&hdnrespn="+hdnrespn+"&othrsChck="+othrsChck,"");
		}
		
	});

	jQuery("#btnDeleteaction").click(function() {
		//saveForm("frmActionPlan","ActionPlandetail_delete.api","");
		var dtlKeyid=jQuery('#txtApldKeyid').val();
		var AplKeyId = jQuery("#txtAplmKeyid").val();
		processAjaxCalls("ActionPlandetail_delete.api", "keyid="+dtlKeyid+"&hdnApldAplmKeyid="+AplKeyId, 'frmActionPlan_deleteSuccessCallback','')	;
	});	
	jQuery("#btnclear").click(function() {
		clearDtls();
	});
	
	jQuery('#chkApldRepOtheres').click(function() {
		 var tag = jQuery("#cmbApldTradeid").combobox('getText');
		 comboFillWithTrade(tag);
	});
	
	/* jQuery('#chkApldRepOtheres').click(function() {
	    
	    //alert(" Others "+jQuery('#chkkzbnOthers').val());
	    if(jQuery("#chkApldRepOtheres").is(':checked')== true){
	   	 jQuery('#chkApldRepOtheres').val('Y');
	   	 var sat = jQuery('#chkApldRepOtheres:checked').val();
		     jQuery("#cmbApldResponsibility").combobox('setValue',"");
			 othersClickAction(sat);
	    }else if(jQuery("#chkApldRepOtheres").is(':checked')== false){
	        jQuery('#chkApldRepOtheres').val('N');
	        var sat = jQuery('#chkApldRepOtheres').val();
	   	    jQuery("#cmbApldResponsibility").combobox('setValue',"");
	   	    //jQuery("#cmbApldTradeid").combobox('setValue',"");
	   	    //jQuery("#cmbApldRole").combobox('setValue',"");
			 othersClickAction(sat);
		 }
	}); */

	apMode = jQuery('#hdnApmode').val();
	
	if (apMode=="view") {
		disableUIButton("btnInsertActionPlan");
		disableUIButton("btnDeleteaction");
		disableUIButton("btnclear");		
		disableField("frmActionPlan","popupImgSave");
		disableForm("frmActionPlan");	
		jQuery('#divFlidTask').append('<div style="position:absolute;top:0;left:0;width:68%;z-index:2;opacity:0.4;height:50%;"> </div>');
	}
	else {
		
		jQuery('#divFlidTask').append('<div id="divhide" style="position:absolute;top:0;left:0;width:68%;z-index:2;opacity:0.4;height:15%;"> </div>');
		disableField("frmActionPlan","txtAplmMaintask");
		readOnlyFields("txtAplmMaintask");
	}
	
	jQuery('#dteApldTargetdate').datebox({  	   
	   	onSelect:function(date)
			{
	   			targetDateEvt(date);
			} 
	   });

	jQuery('#dteApldCompleatedon').datebox({  	   
	   	onSelect:function(date)
			{
	   			completedDateEvt(date);
			} 
	   });

});

function btnActfilemgr_click()
{
	var actnpln=getSelectdRowsActplan('ActionGridResp','ActionGridResp_','chkbox');
	var respnsibility=getFieldValue("cmbApldResponsibility");
	var hdnrespn=jQuery('#hdnrspnlity').val();
	var othrsChck = jQuery('#chkApldRepOtheres').is(':checked');
	var keyid = jQuery('#txtAplmKeyid').val();
		
	if(othrsChck == true)
		othrsChck="Y";
	else
		othrsChck="N";
	
	//jQuery('#hdninsert').val('insert');
	if((actnpln.trim().length==0 && respnsibility.trim().length==0)){
		popupCommonErrorMsg(" Select Responsibility ");
		return false;
	}

	if(keyid.trim().length<=0){
		if(respnsibility.trim().length==0){
		    var gridData = '&actplnDetails='+actnpln+"&Actionplan=Actnplnidn&hdnrespn="+hdnrespn+"&othrsChck="+othrsChck;
		    saveForm("frmActionPlan","ActionPlan_save.api?&filemanger=filemanger&Type=mstaction"+gridData,"");
		}else{
			saveForm("frmActionPlan","ActionPlan_save.api?&filemanger=filemanger&Type=mstaction&hdnrespn="+hdnrespn+"&othrsChck="+othrsChck,"");
		}
     }else if(keyid != null && keyid != ''){
  		//var mode = jQuery("#frmActionPlan input[id=mode]").val();
  		//alert(" mode :: "+mode+" keyid :: "+keyid);
  		fileManagerPopUp(keyid,"ACT","","","");
	 }		
}

function getSelectdRowsActplan(jqGridId, checkBoxColName, ckeckForSelColName) {//alert(1234567890);
	var actplnrow = jQuery("#ActionGridResp").jqGrid('getDataIDs');//	row get data
	var mdfy=jQuery('#hdnmodify').val();
    var jsonArrO = '[';
	for (i = 0; i < actplnrow.length; i++) {
		rowid = actplnrow[i];
		
		//var Checkval= jQuery("#attandanceGrid").jqGrid('getCell',rowid,"chkbox");
		var isChecked = jQuery('#jqg_ActionGridResp_'+ rowid).is(':checked');
        if(isChecked == true  ){ //Checkval=="1" && Checkval!=null && Checkval!=' '){  
		var	responlity = jQuery("#ActionGridResp").jqGrid('getCell', rowid,"cmbApldResponsibility"); // Call detail Key Id
	
		
		if(mdfy=="modify")
			jQuery('#hdnrspnlity').val(responlity);
		
		/*var actnpln=jQuery("#txtApldActionplan").val();
		var howtodo=jQuery("#txtApldHowtodo").val();
		var tradeid=jQuery("#cmbApldTradeid").val();
		var actnstatus=getFieldValue("#cmbApldStatus");
		var trgtdate=getFieldValue("#dteApldTargetdate");
		var role=getFieldValue("#cmbApldRole");
		*/
		
        if ((isChecked != null && isChecked != "")) {
			jsonArrO += '{';
			jsonArrO += '"cmbApldResponsibility":"' + responlity + '"';
			/*jsonArrO += '"txtApldActionplan":"' + actnpln + '",';
			jsonArrO += '"txtApldHowtodo":"' + howtodo + '",';
			jsonArrO += '"cmbApldTradeid":"' + tradeid + '",';
			jsonArrO += '"txtApldHowtodo":"' + howtodo + '",';
			jsonArrO += '"cmbApldStatus":"' + actnstatus + '",';
			jsonArrO += '"dteApldTargetdate":"' + trgtdate + '",';
			jsonArrO += '"cmbApldStatus":"' + actnstatus + '",';
			jsonArrO += '"cmbApldRole":"' + role + '",';*/
			jsonArrO += '},';
		}
      }
    }
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO;
}

function frmActionPlancmbApldRole_onSelect(record)
{
	 
	 var location = jQuery("#frmActionPlan input[id='location']").val();
     var flid =jQuery("#frmActionPlan input[id='flid']").val();
	 var tradeid =getFieldValue('cmbApldTradeid');
	 var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	 
	 //jQuery('#cmbApldTradeid').combobox('clear');
	 jQuery('#cmbApldResponsibility').combobox('clear');
	 addactionplnres(null,record.id);
	 
	 //reloadCombo("frmActionPlan","cmbApldResponsibility","rolebasedemployee.mom?&roleId="+record.id+"&flid="+flid+"&tradeid="+tradeid);
	 //processGridnew("ActionPlanresponsibility_input.api","?&role=role&roleId="+record.id+"&flid="+flid+"&location="+location+"&tradeid="+tradeid+"&cellId="+cellId,"ActionGridResp");
	 
	 //jQuery('input:checkbox[name=chkApldRepOtheres]').attr('checked',false);
	 //jQuery('#cmbApldResponsibility').combobox('clear');
	 
	 
}

/*function frmActionPlancmbApldRole_onSelect(record)
{
	 var location = jQuery("#frmActionPlan input[id='location']").val();
     var flid =jQuery("#frmActionPlan input[id='flid']").val();
	 var role =getFieldValue('cmbApldRole');
	 reloadCombo("frmActionPlan","cmbApldResponsibility","rolebasedemployee.mom?&roleId="+record.id+"&flid="+flid);
	 processGridnew("ActionPlanresponsibility_input.api","?&role=role&roleId="+record.id+"&flid="+flid+"&location="+location,"ActionGridResp");
	 //jQuery('input:checkbox[name=chkApldRepOtheres]').attr('checked',false);
	 //jQuery('#cmbApldResponsibility').combobox('clear');
	 
}
*/

function comboFillWithTrade(tag)
{
	addactionplnres(null,null);
	/*var trade = jQuery("#cmbApldTradeid").combobox("getValue");
	 var roleid = jQuery("#cmbApldRole").combobox("getValue");
	 var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	 var flid = jQuery("#frmActionPlan input[id='flid']").val();
	 var rep = jQuery('#chkApldRepOtheres:checked').val();
	 var location = jQuery("#frmActionPlan input[id='location']").val();
	 var insert=jQuery("#hdninsert").val();
	 var dataStr ;
	 //jQuery('#cmbApldResponsibility').combobox('clear');
	 //alert(" rep :: "+rep);
	 //if(trade==null || trade=='' || trade==' ')
		 //trade = "others";
		 //if(rep=="Y"){
			 
			
			 //alert(" insert :: "+insert);
		 if(jQuery("#chkApldRepOtheres").is(':checked')== true){
			 //jQuery("#lblResp").addClass('mandatory-lbl');
			 //if(trade.trim().length>0 && insert.trim().length==0)
				 //setFieldValue("hdninsert"," ");
			  //if(insert.trim().length==0)
			     dataStr =	"?&role=otherTrd&tradeid="+trade+"&location="+location+"&roleId="+roleid+"&cellId=";
			  
	 	 	  reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter?loginEmpshow=false&trade="+trade+"&cellId="+"&roleId="+roleid+"&locnId="+location);
	 	      processGridnew("ActionPlanresponsibility_input.api",dataStr,"ActionGridResp");
	     }
		 else{
			 //jQuery("#lblResp").removeClass('mandatory-lbl');
			 //setFieldValue("hdninsert"," ");
			 //if(insert.trim().length==0)
			 dataStr =	"?&role=otherTrd&tradeid="+trade+"&location="+location+"&cellId="+ cellId+"&flid="+flid+"&roleId="+roleid;
			 if(cellId == null || cellId == ''){
				reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter?loginEmpshow=false&cellId="+ cellId+"&roleId="+roleid+"&trade="+trade+"&locnId="+location+"&flid="+flid);
			 	processGridnew("ActionPlanresponsibility_input.api",dataStr,"ActionGridResp");
			 }
			 else{
				 reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter?loginEmpshow=false&trade="+trade+"&roleId="+roleid+"&cellId="+cellId+"&locnId="+location+"&flid="+flid);
				 processGridnew("ActionPlanresponsibility_input.api",dataStr,"ActionGridResp");
			 }
		 }
		 
		/* setTimeout(function(){alert("setTimeout");
			 jQuery("#hdninsert").val(' ');
		     setFieldValue("hdninsert"," ");
		     },600);*/

}

function frmActionPlancmbApldResponsibility_onSelect(record)
{	 
	var status =jQuery("#cmbApldStatus").val();
	 processAjaxCalls("getTrade.abnForm?abnmRespons="+record.id,"","getApldTradeValues");
	 if (status == 'C'){
	 setFieldValue("cmbApldCompletedby",record.id);
	 }
	 enableFields("cmbApldStatus");
}
function getApldTradeValues(result)
{	 
	 //jQuery('#cmbApldTradeid').combobox('setValue',result.abnTrade);
	 setComboValueSilent("cmbApldTradeid",result.abnTrade);
}

function frmActionPlancmbApldTradeid_onSelect(record)
{   //alert(record.id);
	jQuery('#cmbApldResponsibility').combobox('clear');
	
	//alert(record.text);
	addactionplnres(record.id,null);
	/*jQuery('input:checkbox[name=chkApldRepOtheres]').attr('checked',false);
	var location = jQuery("#frmActionPlan input[id='location']").val();
	var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	var roleId = getFieldValue("cmbApldRole");
	var flid =jQuery("#frmActionPlan input[id='flid']").val();
	//jQuery('#cmbApldRole').combobox('clear'); +"&cellId="+cellId
	jQuery('#cmbApldResponsibility').combobox('clear');
	reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter?&loginEmpshow=false&trade="+record.id+"&locnId="+location+"&flid="+flid+"&roleId="+roleId);
	processGridnew("ActionPlanresponsibility_input.api","?&role=Trade&tradeid="+record.id+"&cellId="+cellId+"&location="+location+"&roleId="+roleId+"&flid="+flid,"ActionGridResp");*/
}

function formatDate(date){
	let newDate = new Date(date);
		   

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = String(newDate.getDate()).padStart(2, '0') + '-' +
				            months[newDate.getMonth()] + '-' +
				            newDate.getFullYear();
	return formatted;
 }

function frmActionPlancmbApldTradeid_onClear(record)
{
	
	var location = jQuery("#frmActionPlan input[id='location']").val();
	var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	//jQuery('#cmbApldResponsibility').combobox('clear');
	reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter?&loginEmpshow=false&trade="+"&locnId="+location+"&cellId="+cellId);
	
}

	function targetDateEvt(date)
	{
		var detectionDate = jQuery('#hdnAplmPlandate').val();
		detectionDate = detectionDate.substr(0,12);
		var currentDate = getServerDateTime();
		var targetDate = formatDate(date);// jQuery('#dteApldTargetdate').datebox("getValue");
    	
		//alert(detectionDate);
		
		if (detectionDate=="undefined" || detectionDate=="" || detectionDate==" ") { 
			//alert(convertStringToDate(targetDate) < currentDate);
			targetDate=targetDate+"23:59";
			if(convertStringToDate(targetDate) < currentDate)
			{
				alert('Should Not Lessthan Current Date');
				fillWithCurrentDate('dteApldTargetdate');
			}
			else
			    clearValidationErrorMsg('dteApldTargetdate');
		}
		else {
			if(convertStringToDate(targetDate) < convertStringToDate(detectionDate))
			{	
				alert('Should Not Less than Detection Date');
				fillWithCurrentDate('dteApldTargetdate');
				
			}
			else {
			    clearValidationErrorMsg('dteApldTargetdate');
			}
		}
	}



	function completedDateEvt(date)
	{
		
		var detectionDate = jQuery('#hdnAplmPlandate').val();
		detectionDate = detectionDate.substr(0,12);
		var currentDate = getServerDateTime();
		var completedDate =  formatDate(date); //jQuery('#dteApldCompleatedon').datebox("getValue");
    	
		if (detectionDate=="undefined" || detectionDate=='' || detectionDate==' ') {  
			if(convertStringToDate(completedDate) > currentDate)
			{
				alert('Should Not Exceed Current Date');
				//fillWithCurrentDate('dteApldCompleatedon');
				setTimeout(function() { fillWithCurrentDate("dteApldCompleatedon");},250);
			}
			else
			    clearValidationErrorMsg('dteApldCompleatedon');
		}
		else {
			if(convertStringToDate(completedDate) > currentDate)
			{
				alert('Should Not Exceed Current Date');
				//fillWithCurrentDate('dteApldCompleatedon');
				setTimeout(function() { fillWithCurrentDate("dteApldCompleatedon");},250);
			}
			else if(convertStringToDate(completedDate) < convertStringToDate(detectionDate))
			{
				alert('Should Not Less than Detection Date');
				//fillWithCurrentDate('dteApldCompleatedon');
				setTimeout(function() { fillWithCurrentDate("dteApldCompleatedon");},250);
			}
			else
			    clearValidationErrorMsg('dteApldCompleatedon');	
		}
	}


	function clearDtls(){
		disableUIButton("btnDeleteaction");
		disableUIButton("btnclear");
		setFieldValue('txtApldKeyid','');
		setFieldValue('txtApldAplmKeyid','');
		//setFieldValue('txtApldActionplan','');
		setFieldValue('cmbApldTradeid','');
		setFieldValue('txtApldHowtodo','');
		setFieldValue('cmbApldResponsibility','');
// 		setFieldValue('dteApldTargetdate','');
// 		setFieldValue('cmbApldStatus','');
		setFieldValue('cmbApldCompletedby','');
		setFieldValue('dteApldCompleatedon','');
		setFieldValue('txtApldAplmKeyid','');
		setFieldValue('txtApldKeyid','');
		setFieldValue('cmbApldStatus','P');
		setFieldValue('hdnmodify',' ');
		setFieldValue('cmbApldRole',' ');
		
		enableFields("chkSelAll");
		
		var row = jQuery("#ActionGridResp").jqGrid('getDataIDs');
		 
    	 for(var i=0;i<row.length;i++)
    	 {  
    		jQuery('#jqg_ActionGridResp_'+row[i]).attr("readonly",false);
    	 }
    	 
		if(jQuery('#chkMultiple').is(':checked')== false)
			{ 
		fillWithCurrentDate("dteApldTargetdate");
		}
		else{
			}
		jQuery("#txtApldRemarks").val('');
		jQuery("#txtApldCountermeasure").val('');
		//jQuery("#txtAplmMaintask").val('');
		
		jQuery("#compltedDetails").hide();
		jQuery("#compltedDetails1").hide();
		jQuery("#compltedDetails2").hide();
		jQuery("#compltedDetails3").hide();
		setFieldValue('cmbApldCompletedby',' ');
		setFieldValue('dteApldCompleatedon',' ');
		jQuery("#txtApldRemarks").val(' ');
		jQuery("#txtApldCountermeasure").val(' ');
		enableFields("dteApldTargetdate");

		if(jQuery('#chkMultiple').is(':checked')== true) {
			readOnlyFields ("txtApldActionplan");
			readOnlyFields ("dteApldTargetdate");
			setFocusOnField('cmbApldStatus');
		}
		else {
			setFieldValue('txtApldActionplan','');
			setFocusOnField('txtApldActionplan');
		}
		jQuery('input:checkbox[name=chkApldRepOtheres]').attr('checked',false);

		var clrdta=jQuery("#hdnclrdta").val();
		if(clrdta.trim().length==0){
		    var tag = jQuery("#cmbApldTradeid").combobox('getText');
		    comboFillWithTrade(tag);
		}
		
	}
	/*function frmActionPlan_FuntLocHierarchy_SuccessCallBack(keyIds){  
		
		alert(1);
			  setFunctionalLocWidth('frmActionPlan','500px');
			  //setFunctionalLocWidth('frmImprovementPrj','720px'); 
			  alert(2);
			 var flid=flIds.flid;
			 setFieldValues('cmbAplmFlid');
			 // setFunctionalLocWidth('frmActionPlan','645px');      
	}*/

	function deatails()
	{
		var user=jQuery("#hdnUser").val();
		var date=jQuery("#hdnDate").val();
		
		if(jQuery("#hdnApprovedBy").val()==null||jQuery("#hdnApprovedBy").val()==" "||jQuery("#hdnApprovedBy").val()==''){
			
			//setComboValueSilent("cmbAplmApprovedby", user);
			//jQuery("#cmbAplmApprovedby").combobox("setValue",user);
		}
		
		if(jQuery("#hdnCompDate").val()==null||jQuery("#hdnCompDate").val()==" "||jQuery("#hdnCompDate").val()==''){
			if(jQuery("#hdnDate").val()!=null  && jQuery("#hdnDate").val()!=" " && jQuery("#hdnDate").val()!='' && jQuery("#hdnDate").val()!="undefined" ){
				jQuery("#dteApldCompleatedon").datebox('setValue', date);
			}else{
				//fillWithCurrentDate("dteApldCompleatedon");
				setTimeout(function() { fillWithCurrentDate("dteApldCompleatedon");},250);
			}
		}
		else if((jQuery("#hdnCompDate").val()=="01-Jan-1801")||(jQuery("#hdnCompDate").val()=="31-Dec-2100")){

			setTimeout(function() {fillWithCurrentDate("dteApldCompleatedon");},500);
			// jQuery("#dteAplmCompleteddate").datebox('setValue', '');
		}else{
			setTimeout(function() {fillWithCurrentDate("dteApldCompleatedon");},500);
		}
		
		if(jQuery("#hdnCompletedBy").val()==null||jQuery("#hdnCompletedBy").val()==" "||jQuery("#hdnCompletedBy").val()=='')
			{
			setComboValueSilent("cmbAplmCompletdby", user);
			//jQuery("#cmbAplmCompletdby").combobox("setValue",user);
			}
			
		}
	function frmActionPlancmbApldStatus_onSelect(record) {
		if (record.id == 'C') {
			jQuery("#compltedDetails").show();
			jQuery("#compltedDetails1").show();
			jQuery("#compltedDetails2").show();
			jQuery("#compltedDetails3").show();
			readOnlyFields("dteApldTargetdate");
			var respon = getFieldValue("cmbApldResponsibility");
			setFieldValue("cmbApldCompletedby",respon);
			
			deatails();
			jQuery("#cmbApldCompletedby").combobox("disable");
			

		} else {
		
			jQuery("#compltedDetails").hide();
			jQuery("#compltedDetails1").hide();
			jQuery("#compltedDetails2").hide();
			jQuery("#compltedDetails3").hide();
			setFieldValue('cmbApldCompletedby','');
			setFieldValue('dteApldCompleatedon','');
			jQuery("#txtApldRemarks").val(' ');
			jQuery("#txtApldCountermeasure").val(' ');
			enableFields("dteApldTargetdate");
			setFieldValue("cmbApldCompletedby","");
		}
	}

	function frmActionPlan_successsCallback(result){
		
		 enableFields("chkSelAll");
		
		 var refKeyId=result.detKeyId;
		 var flid=result.flid;
		 var filemanger =result.filemanger;
		 enableFields("dteApldTargetdate");
		 jQuery("#txtAplmKeyid").val(result.keyid);
		 var KeyId=jQuery("#txtAplmKeyid").val();

		 if(filemanger==true){
        	if(KeyId.trim().length>0){
	   			 var keyid=result.keyid;
	   			 fileManagerPopUp(keyid,"ACT","","","");
   			 }
	   	}
	   		
		 if(KeyId!=null||KeyId!=undefined)  //|| refKeyId!=undefined
		 {
			 if(result.Type=="multiEmp")
				loadMultipleEmployee(refKeyId,flid);
			 else{
				
				if( KeyId == null || KeyId == undefined || KeyId.trim().length == 0)
					KeyId = result.keyid;
				
				//clearDtls();
				//jQuery("#ActionGrid").trigger("reloadGrid");
			 	processGridnew("ActionPlanmodify_input.api", "q=2&KeyId="+KeyId, "ActionGrid", "pager2", "", "doubleClick", "");				
		    }
		}
		 jQuery('#chkSelAll').attr('checked', false);
		 //jQuery("#ActionGridResp").trigger("reloadGrid");
		 setTimeout(function(){jQuery("#ActionGrid").trigger("reloadGrid");},600);
		 
		 jQuery("#hdnclrdta").val("clear");
		 clearDtls();
		 
		 addactionplnres(null,null);
		 
 	}

	function frmActionPlan_deleteSuccessCallback(result)
	{		
		alert(result.successData.msg);	
		var cnt = jQuery("#ActionGrid").getGridParam("reccount");
		if( cnt <= 1 )	
			jQuery("#txtAplmKeyid").val("");
		
		jQuery("#ActionGrid").trigger("reloadGrid");
		clearDtls();
		  
	}

	 function doubleClick(id)
		{		
		 
				
		 	var rowData = jQuery("#ActionGrid").jqGrid('getRowData',id);
		 	var status = rowData.APLD_STATUS;
		 	if (status =='C') {
		 		return;
		 	}
		 	if (apMode!="view") {
		 		enableUIButton("btnDeleteaction");
			 	enableUIButton("btnclear");
		 	}
		 	
			var keyId = rowData.APLD_KEYID;
			var mstKeyId=rowData.APLD_APLM_KEYID;
			var ActionPlan = rowData.ACTIONPLAN;
			var trade = rowData.TRADEID;
			var howtodo=rowData.HOWTODO;
			var responsibility = rowData.RESPONSIBILITY;
			var targetDate = rowData.TARGETDATE;
			
			var responsibilityId = rowData.APLD_RESPONSIBILITY;
			var completedBy = rowData.APLD_COMPLETEDBY;
			var completedDate = rowData.COMPLETEDDATE;
			var counterMeasure = rowData.COUNTERMEASURE;
			var remarks = rowData.REMARKS;
			var maintask = rowData.MAINTASK;
			var others = rowData.APLD_OTHERS;
			
			enableFields("cmbApldStatus");
			
			//reloadCombo('frmActionPlan','cmbApldTradeid','trade.commonFilter');
			//reloadCombo('frmActionPlan','cmbApldResponsibility','employee.commonFilter');
			//reloadCombo('frmActionPlan','cmbApldCompletedby','employee.commonFilter');
			//reloadCombo('frmActionPlan','cmbApldStatus','Combo_Status.api');
			//alert(status);
			if (status == 'C') {
				setFieldValue('txtApldKeyid',keyId);
				setFieldValue('txtApldAplmKeyid',mstKeyId);
				setFieldValue('txtApldActionplan',ActionPlan);
				setFieldValue('cmbApldTradeid',trade,"frmActionPlan" );
				//jQuery('#cmbApldTradeid').combobox('setValue',trade);				
				setFieldValue('txtApldHowtodo',howtodo);
				//setFieldValue('cmbApldResponsibility',responsibilityId,"frmActionPlan" );
				//jQuery('#cmbApldResponsibility').combobox('setValue',responsibilityId);				
				setFieldValue('dteApldTargetdate',targetDate);
				setFieldValue('txtAplmMaintask',maintask);
				setFieldValue('cmbApldStatus',status,"frmActionPlan" );
				setFieldValue('cmbApldCompletedby',completedBy,"frmActionPlan");
				setFieldValue('dteApldCompleatedon',completedDate);
				jQuery("#txtApldRemarks").val(remarks);
				jQuery("#txtApldCountermeasure").val(counterMeasure);
				jQuery("#compltedDetails").show();
				jQuery("#compltedDetails1").show();
				jQuery("#compltedDetails2").show();
				jQuery("#compltedDetails3").show();
						
			} else{
				setFieldValue('txtApldKeyid',keyId);
				setFieldValue('txtApldAplmKeyid',mstKeyId);
				setFieldValue('txtApldActionplan',ActionPlan);
				//setFieldValue('cmbApldTradeid',trade,"frmActionPlan" );
				jQuery('#cmbApldTradeid').combobox('setValue',trade);
				setFieldValue('txtApldHowtodo',howtodo);
				//setFieldValue('cmbApldResponsibility',responsibilityId,"frmActionPlan" );
				//jQuery('#cmbApldResponsibility').combobox('setValue',responsibilityId);
				setFieldValue('txtAplmMaintask',maintask);
				setFieldValue('dteApldTargetdate',targetDate);
				setFieldValue('cmbApldStatus',status,"frmActionPlan" );
				setFieldValue('cmbApldCompletedby',' ',"frmActionPlan" );
				setFieldValue('dteApldCompleatedon','',"frmActionPlan" );
				jQuery("#txtApldRemarks").val(' ');
				jQuery("#txtApldCountermeasure").val(' ');
				jQuery("#compltedDetails").css('display', 'none');
				jQuery("#compltedDetails1").css('display','none');
				jQuery("#compltedDetails2").css('display','none');
			    jQuery("#compltedDetails3").css('display','none');
			}
			
			 disableField("frmActionPlan", "chkSelAll");
			 /*var row = jQuery("#ActionGridResp").jqGrid('getDataIDs');
			 
	     	 for(var i=0;i<row.length;i++)
	     	 {  
	     		jQuery('#jqg_ActionGridResp_'+row[i]).attr("readonly",true);
	     	 }
	     	 */
	     	 
	     	jQuery('#hdnmodify').val('modify');
			jQuery('#chkApldRepOtheres').attr('checked',false);
			
			if(others=="Y"){
	     	   jQuery('#chkApldRepOtheres').attr('checked',true);
	     	   //setTimeout(function(){
	     	//         addactionplnres();
	     	   //},800);
	     	  
	     	  //if(jQuery("#chkApldRepOtheres").is(':checked')== true){
	     	   	 /*jQuery('#chkApldRepOtheres').val('Y');
	     	   	 var sat = jQuery('#chkApldRepOtheres:checked').val();
	     		     jQuery("#cmbApldResponsibility").combobox('setValue',"");
	     			 othersClickAction(sat);*/
	     	    //}
	     	      
			}
			
			
			addactionplnres(null,null);
			
			setTimeout(function(){
			setFieldValue('cmbApldResponsibility',responsibilityId,"frmActionPlan" );
			},900);
	     	processAjaxCalls("actionPlanDetail_modify.api","?q=2&keyId="+keyId,"detailRecallSuccess","detailRecallError");
			
	 }
	 function detailRecallSuccess(result)
  	 {	
	  	 
  		var howToDo= result.detailData.aplmHowexplanation;
  		var saving=result.detailData.aplmSavings;
  		var reason=result.detailData.aplmReason;
  		var target=result.detailData.aplmTarget;
  		var cause=result.detailData.aplmCauseid;
  		var phenomena=result.detailData.aplmPhenomenaid;
  		setFieldValue('cmbAplmPhenomenaid',phenomena);
		setFieldValue('cmbAplmCauseid',cause);
		setFieldValue('txtAplmHowexplanation',howToDo);
		setFieldValue('txtAplmReason',reason);
		setFieldValue('txtAplmSavings',saving);
		setFieldValue('txtAplmTarget',target);
  	 }
	 

jQuery("#btnMultiEmp").click(function(){
	
	var refKeyId=jQuery("#txtApldKeyid").val();
	var flid=jQuery("#txtAplmFlid").val();
	
	if(refKeyId.trim().length>0){
		loadMultipleEmployee(refKeyId,flid);
	}else{ 
		saveForm("frmActionPlan","ActionPlan_save.api?&Type=multiEmp","");
		 }
				
	});

jQuery("#btnMultiClear").click(function(){
	//setFieldValue('txtApldActionplan','');
	enableFields('txtApldActionplan');
	enableFields('dteApldTargetdate');
	jQuery('input:checkbox[name=chkMultiple]').attr('checked',false);
});

function loadMultipleEmployee(refKeyId,flid)
{
	//LoadPopUp("divConvMatxPopup", url+"?keyId=" + result.successData.CnmdCnmmkeyid, true,"80%","408px","100px","8%", "conversionMatrixOk_Callback","Conversion Matrix Details Entry",false);
	LoadPopUp("MultiSelectEmployee","multiEmpSelect_input.api?q=2&refKeyId="+refKeyId+"&flid="+flid,true,"86%","75%","6%","4%","","Employee Selection",false);

}

function frmActionPlan_FuntLocHierarchy_SuccessCallBack(keyIds)
{ 
	 setFunctionalLocWidth('frmActionPlan','644px');
	 var flid=keyIds.flid;
	 //setFieldValues('cmbAplmFlid');
	 var flid = jQuery("#frmActionPlan input[id='flid']").val();
	 var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	 var sectionId= jQuery("#frmActionPlan input[id='section']").val();
	 //alert(cellId);
	 //reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&flid="+flid);
	
//	 reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&loginEmpshow=false&cellId="+cellId+"&flid="+flid);

        /*if(jQuery("#txtAplmRefdoctype").val()=="MOM")
          	fillComboBox("frmActionPlan","cmbApldResponsibility","momgroupbasedemployee.api?&refid="+jQuery("#txtAplmMasterrefid").val()+"&reftype="+jQuery("#txtAplmRefdoctype").val());
     	else
        */

        if(jQuery("#txtAplmRefdoctype").val()=="MOM") 
    	    fillComboBox("frmActionPlan","cmbApldResponsibility","mompillargroupbasedemployee.api?&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&flid="+flid);
         else
            fillComboBox("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&loginEmpshow=false&cellId="+cellId+"&flid="+flid);

		
	 //reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&sectionId="+sectionId);	
}




jQuery('#chkApldRepJhMembers').click(function() {
	var flid = jQuery("#frmActionPlan input[id='flid']").val();
    if(jQuery("#chkApldRepJhMembers").is(':checked')== true){
    	reloadCombo("frmActionPlan","cmbApldResponsibility","momjhmemberemployee.api?&flid="+flid);
    }else if(jQuery("#chkApldRepOtheres").is(':checked')== false){
    	jQuery('#chkApldRepOtheres').val('N');
        var sat = jQuery('#chkApldRepOtheres').val();
   	    jQuery("#cmbApldResponsibility").combobox('setValue',"");
		othersClickAction(sat);
	 }
});

/*function othersClickAction(sat)
{
//alert(sat);
var cellId = jQuery("#frmActionPlan input[id='cell']").val();
var flid = jQuery("#frmActionPlan input[id='flid']").val();
var sectionId=jQuery("#frmActionPlan input[id='section']").val();
var trade = jQuery("#cmbApldTradeid").combobox("getValue");
if(sat=="Y")
{
	  reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&flid="+flid+"&others=Y"+"&trade="+trade);
	  //reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&sectionId="+sectionId+"&others=Y");
}
else if(sat=="N")
{  
	  //alert(cellId);
	  reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&flid="+flid+"&trade="+trade);
	 // reloadCombo("frmActionPlan","cmbApldResponsibility","employee.commonFilter?&sectionId="+sectionId);
}	
}*/



function addactionplnres(tradeid,roleId)
{
	 //alert(1);
	// alert("t :"+tradeid);
	// alert("r :"+roleId);
	 var location = jQuery("#frmActionPlan input[id='location']").val();
     var flid =jQuery("#frmActionPlan input[id='flid']").val();
	/*  var tradeid =getFieldValue('cmbApldTradeid');
	 var roleId =getFieldValue('cmbApldRole'); */
	 tradeid = tradeid != null ? tradeid : getFieldValue('cmbApldTradeid');
	 roleId = roleId != null ? roleId : getFieldValue('cmbApldRole');
	 var type=jQuery('#hdntype').val();
	 var othrsChck = jQuery('#chkApldRepOtheres').is(':checked');
	 var pillarid=jQuery('#hdnpillarid').val();
	 //alert(2);
	 //var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	 //var masterrefid=jQuery("#txtAplmMasterrefid").val();

		if(othrsChck == true)
			othrsChck="Y";
		else
			othrsChck="N";
		
		//alert(othrsChck);
		
		var datastr="?&tradeid="+tradeid+"&roleId="+roleId+"&others="+othrsChck+"&type="+type+"&flid="+flid;
		  
		datastr +="&pillarid="+pillarid+"&location="+location;  
		   
		 
		 
	     //reloadCombo("frmActionPlan","cmbApldResponsibility","mompillargroupbasedemployee.api"+datastr);
		 
		 reloadCombo("frmActionPlan","cmbApldResponsibility","actionplnemployee.commonFilter"+datastr);
		 processGridnew("ActionPlanresponsibility_input.api",datastr,"ActionGridResp","","","","respLoadcomplete");
		 
		 
}
//function viewGrid(url,filterString)
//{
 //  processGridnew(url,filterString,"attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");//,"","","","loadCompleteAttendanceGrid"
//}

function respLoadcomplete() {
	var modify=jQuery("#hdnmodify").val();
	 //alert(" modify :: "+modify);
	 setTimeout(function(){
	 if(modify.trim().length>0){
		 var row = jQuery("#ActionGridResp").jqGrid('getDataIDs');
			 for(var i=0;i<row.length;i++)
		 	 {  
		 		jQuery('#jqg_ActionGridResp_'+row[i]).attr("readonly",true);
		 	 }
	}
 },100);

}

function othersClickAction(sat)
{
	 //jQuery('#cmbApldTradeid').combobox('clear');
	 //jQuery('#cmbApldRole').combobox('clear');
	 var cellId = jQuery("#frmActionPlan input[id='cell']").val();
	 var flid = jQuery("#frmActionPlan input[id='flid']").val();
	 var roleId =getFieldValue('cmbApldRole');
	// alert(" roleId :: "+roleId);
	 var location = jQuery("#frmActionPlan input[id='location']").val();
	 var pillarid=jQuery('#hdnpillarid').val();
	 var type=jQuery('#hdntype').val();
	 var masterrefid=jQuery("#txtAplmMasterrefid").val();
	 var tradeid=getFieldValue("cmbApldTradeid");
	 var modify=jQuery('#hdnmodify').val();
	 
 	 if(tradeid.trim().length==0){
	 
		 if(sat=="Y")
		 {   
			 reloadCombo("frmActionPlan","cmbApldResponsibility","rolebasedemployee.mom?&roleId="+roleId+"&locnId="+location);
			 processGridnew("ActionPlanresponsibility_input.api","?&role=role&others=Momothers&location="+location+"&roleId="+roleId,"ActionGridResp");
		 }
		 else
		 {   
		     if(type=="Dmt"||type=="Pillar"||type=="Production")
		    	 reloadCombo("frmActionPlan","cmbApldResponsibility","mompillargroupbasedemployee.api?&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&flid="+flid);
		     else
			     reloadCombo("frmActionPlan","cmbApldResponsibility","rolebasedemployee.mom?&roleId="+roleId+"&flid="+flid+"&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&locnId="+location);
		     
			 var datastr="?&untick=untick&refid="+masterrefid+"&pillarid="+pillarid+"&type="+type+"&flid="+flid+"&roleId="+roleId;
			 processGridnew("ActionPlanresponsibility_input.api",datastr,"ActionGridResp");
		 }
	 
	 }
 	
 	 var modify=jQuery("#hdnmodify").val();
	 
	 if(modify.trim().length>0){
		 setTimeout(function(){
	     var row = jQuery("#ActionGridResp").jqGrid('getDataIDs');
			 for(var i=0;i<row.length;i++)
		 	 {  
		 		jQuery('#jqg_ActionGridResp_'+row[i]).attr("readonly",true);
		 	 }
		 },1400);
	 }
}


</script>


<form name="frmActionPlan" id="frmActionPlan">
  <div class="wrapper" id="toremovewrapper" style="padding: 10px;">
  	<div id="maindivid" style="margin-left: 0%;">
  		<table id="divFlidTask">
  			<tr>
  				<td valign="top">
					<div style="padding-top: 7px; margin-top:-20px;" >
						<div id="frmActionPlanFuntKeyIds" style="width: 50%; margin-left:4px;">
							<input type="hidden" id="location" name="cmbAplmLocationid"	value="" /> 
							<input	type="hidden" id="factory" name="cmbAplmFactoryid"	value=""/>
							<input type="hidden" id="section" name="cmbAplmSectionid"	value=""/>
							<input type="hidden" id="cell" name="cmbAplmWherecellid"	value=""/>
							<input type="hidden" id="machine" name="cmbAplmWhichmachine" value=""/>
							<input type="hidden" id="flid" name="cmbAplmFlid" value="${requestScope.newGenTlActionplanmst.aplmFlid}"/>
							<input type="hidden" id="elementId" name="cmbAplmElementid" value="${requestScope.newGenTlActionplanmst.aplmElementid}"/>
							<div id="ApfunLocation" style="width: 90%; width: 90% \9; " ></div>
				
						</div>
					</div>
					<div style="margin-left:6px;margin-top:6px;">
<%--  						<c:choose>  --%>
<%--  						      <c:when test="${requestScope.newGenTlActionplanmst.aplmMaintask.length()>0}">  --%>
								<div style="margin-left:0px;">
									<label style="font-weight: bold;">Main Task</label>
									<span style="padding-left:264px;"><label>How To Do</label></span>
							    <div>
									<textarea rows="3" cols="34"  id="txtAplmMaintask" name="txtAplmMaintask" style="width: 300px; height : 60px;">${requestScope.newGenTlActionplanmst.aplmMaintask}</textarea>
									<span style="padding-left:20px;"><textarea rows="5" cols="34"  id="txtApldHowtodo" name="txtApldHowtodo" style="width: 300px; height : 60px;"></textarea></span>
								</div>
 								</div> 
<%--  						      </c:when>  --%>
<%-- 	                          <c:otherwise> --%>
						     
<%--  						      </c:otherwise>  --%>
<%--  						</c:choose>  --%>
					</div>
				</td>
				<td style="padding-left: 24px;">
                   <div>
						<label class="">Maintenance Section</label>
					</div>
					<div >
						<input id="cmbApldTradeid" name="cmbApldTradeid" class="easyui-combobox" maxlength="95" tabindex="1" style="width: 300px;" value="" />
					</div>
					<div>
						<label>Role</label>
					</div>
					<div style="position: relative;padding-top: 3px;" >
						<input id="cmbApldRole" name="cmbApldRole" tabindex="3"	class="easyui-combobox" maxlength="95"	style="width: 300px" value="" />
					</div>
                    <div>
						<label class="mandatory-lbl">Responsibility</label>
						<input type="checkbox" id="chkApldRepOtheres" name="chkApldRepOtheres" value="Y"  ${ requestScope.abnormalityBean.disableForm == true ? 'readonly':''} /><label>Others</label>
						<span style="padding-left:6px;">
				           <input type="checkbox" id="chkSelAll" name="chkSelAll" style="margin-left:10px;" />
		                   <input type="text" value="Select All" readonly="readonly" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
				        </span>
						<span style="padding-left:4px;display:none"><input type="checkbox" id="chkApldRepJhMembers" name="chkApldRepJhMembers" /></span><label style="padding-left:4px;display:none">Jh Members</label>
					</div>
					
					
					<div style="position: relative;padding-top: 3px;" >
						<input id="cmbApldResponsibility" name="cmbApldResponsibility" tabindex="2"	class="easyui-combobox" maxlength="95"	style="width: 300px" value="" />
						
						<span style="position: absolute;right: -31;top:-4;" >
							<input type="button" id="btnMultiEmp" class="easyui-button" style="display:none;" name="btnMultiEmp"  value="..."/>
						</span>
					</div>
                    <div id="allot">
					<span style="padding-left:0px;"><label>Alloted By</label></span>
					</div>
					
					<div id="allot">
				  <input type="text" class="easyui-combobox"  id="cmbActionplanAlby" name="cmbActionplanAlby" maxlength="10"  style=" width : 150px;height:35px; text-align:left;" readonly="readonly"  value="${requestScope.newGenTlActionplanmst.aplmCreatedby}"/>
					</div>						
				
				</td>
			</tr>
		</table>
		<table style="padding-top: -5px;">
			<tr>
				<td valign="top" >
                 <div id='rmndrid' style="margin-top:-6px;margin-left:6px;">
					<div >
						<label class="mandatory-lbl">Action Plan</label>
						<span style="padding-left:10px;">
							 <input type="checkbox" id="chkMultiple" name="chkMultiple" value="Y" > 
							<label>Multiple</label>
						</span>
						<span style="padding-left:10px;margin-top: -5px;">
							<input type="button" id="btnMultiClear" name="btnMultiClear" class="easyui-button" value="Clear" style="height:20px;"/> 
						</span>
						
					</div>
					<div style="padding-top: 2px;">
						<textarea rows="3" cols="34" id="txtApldActionplan" tabindex="0"name="txtApldActionplan" style="width: 300px; height: 60px;"></textarea>
					</div>
					<table>
						<tr>
							<td>
								<span id="err_cmbApldCompletedby" class="tpm-errormsg"></span>
							</td>
							<td style="padding-left: 36px">
								<span id="err_dteApldCompleatedon" class="tpm-errormsg"></span>
							</td>
						</tr>
					</table>
					</div>
					<div id="compltedDetails" style="padding-left:6px;display:none ;">
						<div style="padding-top: 5px;" >
							<label>Remarks</label>
						</div>
						<div >
							<textarea rows="1" cols="34" id="txtApldRemarks" tabindex="8"name="txtApldRemarks" style="width: 300px; height : 60px;"></textarea>
						</div>
					</div>
					
				</td>
				<td valign="top" style="padding-left: 22px;padding-top:0px;">
				<div id="stusid" style="margin-top:-0px;">
                <div>
					<span style="padding-left:0px;"><label>Status</label> <span style="padding-left: 100px;"><label>Target Date</label></span></span>
					</div>
					
									
					<div>
					<span style="padding-left:0px;">
						     <input id="cmbApldStatus" name="cmbApldStatus" type="text" tabindex="4" class="easyui-combobox" readonly="readonly" style="width: 120px; padding-left:20px;width: 132px\9;"	value="" />
			              <span style="padding-left: 16px;">
			                  <input id="dteApldTargetdate" name="dteApldTargetdate" type="text" class="easyui-combobox" style="width: 120px;" value="" />
			              </span>
					</span>
					</div>
					 <div id="compltedDetails2" style="margin-top:0px;display: none">
						<div  style="padding-top: 0px;">
							<label class="mandatory-lbl">Completed By</label><span style="padding-left: 60px;"><label class="mandatory-lbl">Completed Date</label></span>
						</div>
						<div>
							<input id="cmbApldCompletedby" name="cmbApldCompletedby" class="easyui-combobox" readonly="readonly" maxlength="95" tabindex="5" style="width: 135px; width: 132px\9;"	value="" />
								<span style="padding-left: 10px;">
								<input id="dteApldCompleatedon" name="dteApldCompleatedon" tabindex="6"	type="text" class="easyui-text" maxlength="95"	style="width: 120px;"value=""/>
								</span>
						</div>
				 </div>
				<div style="margin-top:0px;">
					<div id="compltedDetails1" style="display:none ">
						<div style="padding-top: 5px;" >
							<label>Countermeasure</label>
						</div>
						<div>
							<textarea rows="1" cols="34" id="txtApldCountermeasure" tabindex="9"name="txtApldCountermeasure"style="width: 300px; height : 60px;"></textarea>
						</div>
					</div>
				</div>
				</div>
				</td>
				<td valign="top" style="padding-left: 20px;">
				   	<div id='actnrespid' style="margin-top:4px;padding-left: 30px;" >	  
			<table id='ActionGridResp'></table>
<!--			<div id='pager2'></div>-->
		</div>	
				</td>
			</tr>
		</table>
		<div style="position: relative;" id="divMenu">
			<span style="position: absolute; right: 180px;top: 0px;">
				<input type="button" class="easyui-button" value ="Insert/Update" id="btnInsertActionPlan" name="btnInsertActionPlan" style="height:23px"/>
			     <span style="padding-left:5px;">
			     	<input type="button" class="easyui-button" id="btnDeleteaction" name="btnDeleteaction" value="Delete" style="height:23px;"/>
			     </span>
			     <span style="padding-left:5px;">
			     	<input type="button" class="easyui-button" id="btnclear" name="btnclear" value="clear" style="height:23px;"/>
			     </span>
     			<span  id="ActFilemgr" style="position:absolute;top: 0px;right:-130px;" ></span>
		     </span>
		</div>	
	   	<div style="margin-top:20px;float: left;" >	  
			<table id='ActionGrid'></table>
<!--			<div id='pager2'></div>-->
		</div>
	</div>
	 <input type="hidden"id="txtAplmKeyid" name="txtAplmKeyid" value="${requestScope.newGenTlActionplanmst.aplmKeyid}" />
	 <input type="hidden"id="txtApldKeyid" name="txtApldKeyid" value="" />
	 <input type="hidden"id="txtApldAplmKeyid" name="txtApldAplmKeyid" value="" />
	 <input type="hidden"id="txtAplmFlid" name="txtAplmFlid" value="${requestScope.newGenTlActionplanmst.aplmFlid}" />
	 <input type="hidden"id="txtAplmDetailrefid" name="txtAplmDetailrefid" value="${requestScope.newGenTlActionplanmst.aplmDetailrefid}" />
	 <input type="hidden"id="txtAplmRefdoctype" name="txtAplmRefdoctype" value="${requestScope.newGenTlActionplanmst.aplmRefdoctype}" />
	 <input type="hidden" id="txtAplmMasterrefid" name="txtAplmMasterrefid" value="${requestScope.newGenTlActionplanmst.aplmMasterrefid}" />
	 <input type="hidden" id="hdnpillarid" name="hdnpillarid" value="${requestScope.pillarid}" />
	 <input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}" />
	 <input type="hidden"id="hdntaskid" name="hdntaskid" value="${requestScope.taskid}" />
	 <input type="hidden"id="hdnApmode" name="hdnApmode" value="${requestScope.apMode}" />
	 <%-- <input type="hidden"id="hdnAplmPlandate" name="hdnAplmPlandate" value="${requestScope.actPlanRefDate}" /> --%>
	 <input type="hidden"id="hdnAplmPlandate" name="hdnAplmPlandate" value="${requestScope.newGenTlActionplanmst.aplmPlandate}" />
	 <input type="hidden"id="hdnmodify" name="hdnmodify" value="" />
	 <input type="hidden"id="hdnrspnlity" name="hdnrspnlity" value="" />
	 <input type="hidden"id="hdninsert" name="hdninsert" value="" />
	 <input type="hidden"id="hdnclrdta" name="hdnclrdta" value="" />
	 <input type="hidden" id="hdnchkyno" name="hdnchkyno" value="${requestScope.CheckYN}"/>
</div>
</form>