<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
	jQuery(document).ready(function() {

				initialiseForm('frmSlaRep');
				jQuery('#submitForm').val('frmSlaRep');	
				var fromPillar = jQuery("#hdnfrom").val();
		     	var rolelevel=jQuery("#hdnloginlevel").val();
		     	//alert("domt>>>>"+jQuery("#hdntodmtid").val());
		    	//alert("rolelevel>>>"+rolelevel);
		     	var mode=jQuery("#mode").val();	
		     	
		     	//alert("123");
		     	var frmmode = getFieldValue('hdnmode','frmSlaRep');
		     	var hidebutton=jQuery("#hdnhidebutton").val();	
		     	
		     	if(hidebutton=="Y"){
		     		//disableUIButton("btnSave");
		     		jQuery('#ImgSave').unbind('click');
		     	}
		     //	disableUIButton("btnDelete");	
		     	/* if(rolelevel!=900){
		     		alert(" Change Role to QM pillar ");
			 
			} */
				var Keyid=jQuery("#txtSlamKeyid").val();				

				if(fromPillar=="OTM"){
						jQuery("#divproduct").hide();
				}else {
					jQuery("#divproduct").show();
					//jQuery('#divAndLbl').css('margin-top','-35px');	
				}
				
				setFieldValue('cmbsladFrequency',jQuery("#hdnsladfrequency").val());
				fillComboBox("frmSlaRep","cmbSlamStatus","Departmentfillcombo.SerLevAgr");
				jQuery("#frmSlaRep :input[type=textarea] ").css('text-transform', 'uppercase');
				jQuery("#frmSlaRep :input[type=text] ").css('text-transform', 'uppercase');
				formatDateBox('dteslamApproveddate', 'dd-MMM-yyyy');
				formatDateBox('dteSlamDate', 'dd-MMM-yyyy');
				formatDateBox('dtesladEffectivedate', 'dd-MMM-yyyy');				
				//formatDateBox('dteSlaApprovedDate', 'dd-MMM-yyyy');

				numericTextBox("txtsladAdherenceperc");
				numericTextBox("txtSladMutualmin");
				numericTextBox("txtSladMutualmax");
				numericTextBox("txtSladMutualtarget");

				fillComboBox("frmSlaRep", "cmbslamApprovedby", "employee.commonFilter");			
				fillComboBox("frmSlaRep", "cmbslaval", "getcmbslaval_input.SerLevAgr","",false);
				fillComboBox("frmSlaRep", "cmbSlamFromdptid", "sectionCombo.commonFilter");
				fillComboBox("frmSlaRep", "cmbSlamTodptid", "sectionCombo.commonFilter");
				//fillComboBox("frmSlaRep", "cmbSladProductid", "productAll.commonFilter");
				fillComboBox("frmSlaRep", "cmbSladProductid", "pramater.SerLevAgr");
				fillComboBox("frmSlaRep", "cmbSladUomid", "uomCombo.commonFilter");
			
				var hurl = "DepartmentReportdetails_input.SerLevAgr";				
				var txtSlamKeyid = jQuery('#txtSlamKeyid').val();
				
				//processGridnew(hurl, "?q=2&SlamKeyid="+txtSlamKeyid+"&from="+fromPillar, "SLADetGrid", "pager2", "", "doubleclickdtl", "");
				
				var fromdmtid=getFieldValue("cmbSlamFromdptid");
		   	    var todmtid=getFieldValue("cmbSlamTodptid");
                var fromPillar = jQuery("#hdnfrom").val();
                var frmmode = getFieldValue('hdnmode','frmSlaRep');
                var mstid=jQuery("#hdnmstid").val();
                var dtlid=jQuery("#hdndtlid").val();
                //alert(" frmmode :: "+frmmode);
                if(frmmode=="Entry"){
					jQuery("#divproduct").css('display','block');
                    jQuery("#divslagrid").css('display','none');
                    setFieldValue('cmbSlamTodptid',jQuery("#hdntodmtid").val());
                }else{
					jQuery("#divslagrid").css('display','block');
					jQuery("#divproduct").css('display','none');
					setFieldValue('cmbSlamStatus','A');
                }	
                
         		processGridnew("DepartmentReportdetails_input.SerLevAgr","?q=2&mstid="+mstid+"&dtlid="+dtlid+"&from="+fromPillar+"&frmmode="+frmmode, "SLADetGrid", "pager2", "", "doubleclickdtl","","sla_LoadComplete");
	     		
				jQuery("input:radio[name=chkSlamSource][value ='I']").prop('checked', true);
				
				//jQuery('input:checkbox[name=rdInternal]').attr('value','I');

				jQuery("input:radio[name=chkSlamSource][value ='"+getFieldValue('hdnSlamSource')+"']").prop('checked', true);
				var clearfrom = jQuery("#formClrVal").val();
				/*if (clearfrom == "true") {

					clearForm("frmSlaRep");
				}*/
				var slamkeyid = jQuery('#txtSlamKeyid').val();
				
				fileManagerPopUp(slamkeyid,"SLA","frmReportNew","btnfilemgr","SLAFilemgr");

				jQuery('#chkslaApprove').click(function(){
		    		jQuery('#chkslaReject').not('#chkslaApprove').removeAttr('checked');
		    	 	});
		     	jQuery('#chkslaReject').click(function(){
		    		jQuery('#chkslaApprove').not('#chkslaReject').removeAttr('checked');
		     	});

		     	SetStatus();


		     	jQuery("#btnDtlsave").click(
		    			function() { 	

		    				var empmname=jQuery('#hdnempname').val();
		    				var type=jQuery('#hdntype').val();
		    				var mstkeyid=jQuery('#txtSlamKeyid').val();
		    				var dtlkeyid=jQuery('#txtSladKeyid').val();
		    				var rolelevel=jQuery("#hdnloginlevel").val();	
		    		     	var mode=jQuery("#mode").val();	
		    		     	
		    		     	var inboxmode=jQuery("#hdninboxmode").val();
		    		   
		    		     	if(inboxmode==null||inboxmode.length<1)
		    		     	{
		    		     		//alert(rolelevel);
		    		     		inboxmode="NotInbox";
		    		     	
		    		     	}	
		    		     	//alert("inbox>>>"+inboxmode);
		    		     	if(inboxmode=="inbox")
		    		     	{
		    		     		
		    		     		jQuery("#hdnslamStatus").val("-");
		    		     	}
		    		     	
		    		     	
		    		     	var frmmode = getFieldValue('hdnmode','frmSlaRep');
		    		     	
		    		     	
		    		     	if(rolelevel!=900 && inboxmode!="inbox"){
		    		     		alert(" Change Role to QM pillar ");
	    				    return false;
	    				}
		    				if(empmname.trim().length==0 && type.trim().length>0){
		    				 	popupCommonErrorMsg(" ur not QM-Pillar Member :: ");
		    				    return false;
		    				}

		    				if (!confirm(" SLA Submitted for DMT Leader "))
		    					return false;
		    				else{
			    				
		    					var dataString=" ";
			    				if(dtlkeyid.trim().length==0)
				    				dataString+="&newdtlrecrd=true";

			    				saveForm("frmSlaRep","Department_save.SerLevAgr?isdtlsave=true&mstkeyid="+mstkeyid+dataString,"");
			    				
		    				}
		    			});
		     	
		     	jQuery("#btnDtlexcel").click(
		    			function() { 	
		    				//alert(123);
		    				/*String frmdmt = request.getParameter("frmdmt");
		    				String todmt = request.getParameter("todmt");
		    				String frmmonth = request.getParameter("month");
		    				String tomonth = request.getParameter("Source");
		    				String SlamKeyid = request.getParameter("SlamKeyid");*/
		    				
		    				var frmdmt = getFieldValue('cmbSlamFromdptid');
		    				var todmt = getFieldValue('cmbSlamTodptid');
		    				var month = getFieldValue('dteSlamDate');
		    				var Source = getFieldValue('hdnSlamSource');
		    				var SlamKeyid = getFieldValue('txtSlamKeyid');
		    				var mstid=jQuery("#hdnmstid").val();
		                    var dtlid=jQuery("#hdndtlid").val();
		                   
		    				//alert( 'frmdmt' +frmdmt + 'todmt' + todmt + 'month' + month + 'Source' + Source + 'SlamKeyid' + SlamKeyid);
		    				window.open("DepartmentReportdetails_getExcel.SerLevAgr?&frmdmt="+frmdmt+"&todmt="+todmt+"&month="+month+"&Source="+Source+"&SlamKeyid="+SlamKeyid+"&mstid="+mstid+"&dtlid="+dtlid,"Excel View");
		    				
		    			});
		     	
		    	
		    	jQuery("#btnDtlclear").click(
		    			function() {
		    				setFieldValue('txtSladMutualtarget','');
		    				setFieldValue('txtSladMutualmin','');
		    				setFieldValue('txtSladMutualmax','');
		    				setFieldValue('cmbSladUomid','');
		    				setFieldValue('cmbsladFrequency','');
		    				setFieldValue('txtSladQualitycharacter','');

		    				setFieldValue('txtSladRemarks','');
		    				setFieldValue('txtsladAdherenceperc','');
		    				setFieldValue('dtesladEffectivedate','');
		    				setFieldValue('cmbSladProductid','');
		    				
		    	});

		    	jQuery("#btnDeletedtl").click(
		    			function() {	
		    				var SladKeyid = getFieldValue("hdnSladKeyid");		
		    				var active=jQuery("#hdnactive").val();

		    				if(active=="N"){
								alert(" This Record is already made as Inactive");
								return false;
			    			}
			    			
		    				if(SladKeyid != null && SladKeyid != '')
			    			{			    								
			    				deleteRecord("frmSlaRep","Departmentdtl_delete.SerLevAgr");
			    			}
		    				else
			    			{
				    			alert("No Data To Delete");
			    			}
		    			});
		    	
		    	jQuery("#rdExternal").click(function(){
		    			jQuery("#lblAnd").html("And (Customer)");
		    			//fillComboBox("frmSlaRep", "cmbSlamFromdptid", "customer.commonFilter");
		    			//fillComboBox("frmSlaRep", "cmbSlamTodptid", "customer.commonFilter");
		    			reloadCombo("frmSlaRep","cmbSlamTodptid","partnorMst.commonFilter");
		    			setFieldValue('hdnSlamSource',"E");
		    			//clearField('cmbSlamFromdptid');
		    			clearField('cmbSlamTodptid');
		    		});
		    	
		    	jQuery("#rdInternal").click(function(){
		    		jQuery("#lblAnd").html("And ");
		    		reloadCombo("frmSlaRep", "cmbSlamFromdptid", "sectionCombo.commonFilter");
		    		reloadCombo("frmSlaRep", "cmbSlamTodptid", "sectionCombo.commonFilter");		    		
		    		setFieldValue('hdnSlamSource',"I");
		    		//clearField('cmbSlamFromdptid');
		    		clearField('cmbSlamTodptid');
		    	});			     

		    	if(Keyid.trim().length == 0)
				{   					 
					 fillWithCurrentDate("dteSlamDate");
					 fillWithCurrentDate("dtesladEffectivedate");
					 fillWithCurrentDate("dteslamApproveddate");
					//fillWithCurrentDate("dteSlaApprovedDate");
					
				}else
				{
					 fillWithCurrentDate("dteslamApproveddate");
				}

		    	
		    	setFieldValue ('hdnSlamSource','I');
				jQuery("input:radio[name=chkSource][value ='I']").prop('checked', true);
				var empid = getFieldValue("hdnApprovedby");  
				 
				setFieldValue("cmbslamApprovedby",empid);
				lockdmt();

				var authrolelevel=jQuery('#hdnslarolelevel').val();
				
				processAjaxCalls("Qmpillarval.SerLevAgr","","updateRoleSuccess","");
				processAjaxCalls("otherpillarval.SerLevAgr","?&authrolelevel="+authrolelevel,"updatevalRoleSuccess","");
				
				var slatype=jQuery("#hdnslatype").val();
				
				if(slatype=="FROMDMTAPPROVAL")
				   jQuery("#authorization").html("Authorization - From DMT APPROVAL");
				else if(slatype=="TODMTQMAPPROVAL")
					jQuery("#authorization").html("Authorization - To QM PILLAR APPROVAL");
				else if(slatype=="TODMTAPPROVAL")
					jQuery("#authorization").html("Authorization - To DMT APPROVAL");


				setTimeout(function() {
					var otherspillar=jQuery('#hdnotherpillar').val();
				    var hdnslatype=jQuery('#hdnslatype').val();
    
					if(otherspillar.length==0 && hdnslatype.trim().length>0){
						readOnlyFields('cmbSlamStatus');
						readOnlyFields('txtslamRemarks');
						readOnlyFields('cmbslamApprovedby');
						readOnlyFields('dteslamApproveddate');
					}
	 		    },350);
	 		    
			});


	/*function frmSlaRepcmbSlamTodptid_onSelect(record){
         var fromdmtid=getFieldValue("cmbSlamFromdptid");
         var fromPillar = jQuery("#hdnfrom").val();
         //alert(fromdmtid);		
	     processAjaxCalls("DepartmenntfillMasterid_modify.SerLevAgr","&fromdmtid="+fromdmtid+"&tosectid="+record.id,"updateSuccessMasterid","");
	     processGridnew("DepartmentReportdetails_input.SerLevAgr","?q=2&fromdmtid="+fromdmtid+"&tosectid="+record.id+"&from="+fromPillar, "SLADetGrid", "pager2", "", "doubleclickdtl","","sla_LoadComplete");
	}*/

	function chkFormatter(id, options, rowObject)
	{	
	    var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="checkbox" id="AbnCompcheckbox_'+rowId+'_'+colId+'" name="AbnCompcheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}

	function chkboxCheck(rowId) {
		
	}
	
	function sla_LoadComplete()
	{
		 
		 var row = jQuery("#SLADetGrid").jqGrid('getDataIDs');
		 var cm = jQuery("#SLADetGrid").jqGrid("getGridParam", "colModel");
		 
		 for(var i=0;i<row.length;i++){
			var active = jQuery("#SLADetGrid").jqGrid('getCell',row[i],"ACTIVE");
			if(active=="N"){
				//jQuery("#SLADetGrid").setCell(row[i], "ACTIVE", "",{'background-color':'#EC1708'}).addClass("totalRow");
				jQuery("#SLADetGrid").setCell(row[i], "REMARKS", "",{'background-color':'#EC1708'});
			}
		}
	}
	
	function updateSuccessMasterid(result){//alert(" result[0][0] :: "+result[0][0]);
        setFieldValue("txtSlamKeyid",result[0][0]);
    }
	
	 function updateRoleSuccess(result){
		var empmname=result.qmpillar;
		jQuery('#hdnempname').val(empmname);
		var type=jQuery('#hdntype').val();
		//alert("empmname>>"+empmname);
		///alert("type>>"+type);
		
		if(empmname.trim().length==0 && type.trim().length>0){
			popupCommonErrorMsg(" ur not QM-Pillar Member :: ");
		  return false;
		}
	 }
	 function updatevalRoleSuccess(result){
		var otherpillar=result.otherpillar;
		jQuery('#hdnotherpillar').val(otherpillar);
	 }

function lockdmt()
{
	var islockdmt = getFieldValue('hdnlockdmt');
	var dmtid = getFieldValue('hdndmtid');
	//alert(dmtid);
	//alert(islockdmt);
	if(islockdmt == "Y")
	{   
		
		//readOnlyFields('cmbSlamFromdptid');
		setFieldValue('cmbSlamFromdptid',dmtid);
		
	}
	
}

function frmSlaRepcmbSladUomid_onSelect(record){

      var value=getFieldValue('cmbslaval');

      if(record.id=="UOM001" && value=="GRT"){
    	  disableField('frmSlaRep','txtSladMutualmax');
    	  setFieldValue("txtSladMutualmax",' ');
    	  setFieldValue("txtSladMutualmax",'100');
    	  var minVal=parseInt(getFieldValue('txtSladMutualmin'));
    	  var maxVal=parseInt(getFieldValue('txtSladMutualmax'));
    	  var targetVal=parseInt(getFieldValue('txtSladMutualtarget'));
    	  if(maxVal>0){
    			if(minVal>maxVal){
    				alert("Minimum Value Should be Less than Maximum Value");
    				setFieldValue('txtSladMutualmin','');
    			}
    			if(targetVal> maxVal){
    				alert("Target Value Should be Less than Maximum Value");
    				setFieldValue('txtSladMutualtarget','');
    			}
    		 }
      }

      
	 
}

function frmSlaRepcmbslaval_onSelect(record)
{
	
	var slvalue = record.id;
	
	if(slvalue == "LST")
	{	disableField('frmSlaRep','txtSladMutualmin');	
		enableFields('txtSladMutualmax');
		//jQuery("#mindiv").hide();
		//jQuery("#maxdiv").show();
		setFieldValue("txtSladMutualmax",'');		
		setFieldValue("txtSladMutualmin",'0');
		setFieldValue("txtSladMutualtarget",'');
		
	}else if(slvalue == "GRT")
	{   
		disableField('frmSlaRep','txtSladMutualmax');	
		enableFields('txtSladMutualmin');
		//jQuery("#maxdiv").hide();
		//jQuery("#mindiv").show();
		setFieldValue("txtSladMutualmin",'');
		setFieldValue("txtSladMutualmax",'99999');
		setFieldValue("txtSladMutualtarget",'');
	}else if(slvalue == "BWT")
	{
		enableFields('txtSladMutualmax');
		enableFields('txtSladMutualmin');
		//jQuery("#maxdiv").show();
		//jQuery("#mindiv").show();
		setFieldValue("txtSladMutualmin",'');
		setFieldValue("txtSladMutualmax",'');
		setFieldValue("txtSladMutualtarget",'');
	}
	}

	
	
	
	function doubleclickdtl(id){
		 var rowData = jQuery("#SLADetGrid").jqGrid('getRowData',id);
		 //var frmdmtstrd =  rowData.FROMDMTAPP;
		 var dtlapprvl =  rowData.APPROVAL;

		 if(dtlapprvl=="I"){
	          alert(" SLA is under Approval ");
	          return false;
          }else if(dtlapprvl=="A"){
	          alert(" SLA is already Approved ");
	          return false;
          }
		 
         /*if(frmdmtstrd=="A"){
           alert(" SLA is under Approval ");
           return false;
         }*/
		 
		 setFieldValue("cmbSladProductid",rowData.PRODUCTID,"frmSlaRep");
		 setFieldValue("txtSladQualitycharacter",rowData.QUALITYCHARACTERISTICS);
		 setFieldValue("cmbSladUomid",rowData.UOMID,"frmSlaRep");
		 setFieldValue("txtSladRemarks",rowData.REMARKS);
		 setFieldValue("txtSladMutualtarget",rowData.TARGET);
		 setFieldValue("txtSladMutualmin",rowData.MINIMUM);
		 setFieldValue("txtSladMutualmax",rowData.MAXIMUM);
		 setFieldValue("hdnSladKeyid",rowData.KEYID);
		 setFieldValue("hdnSladCreatedon",rowData.CREATEDON);
		 setFieldValue("txtsladAdherenceperc",rowData.ADHERENCEPERCENTAGE);
		 setFieldValue("dtesladEffectivedate",rowData.EFFECTIVEDATE);
		 setFieldValue("cmbsladFrequency",rowData.FREQUENCIES);
		 setFieldValue("hdnactive",rowData.ACTIVE);
		 var minval = rowData.MINIMUM;
		 var maxval = rowData.MAXIMUM;
		 
		 if(parseInt(minval) == '0' )
		 {
			 setFieldValue('cmbslaval','LST');
			 disableField('frmSlaRep','txtSladMutualmin');	
			 enableFields('txtSladMutualmax');
			 
		 }else if(parseInt(maxval) == '99999')
		 {   
			 setFieldValue('cmbslaval','GRT');
			 disableField('frmSlaRep','txtSladMutualmax');	
			 enableFields('txtSladMutualmin');
		 }else
		{
			 enableFields('txtSladMutualmax');
			 enableFields('txtSladMutualmin');
		}
		 
	}
	function SetStatus()
	{ 	
	
		if(jQuery("#hdnslamStatus").val() =="A" )
	     {
    		jQuery("#chkslaApprove").prop("checked",true);
    		jQuery("#chkslaReject").prop("checked",false);
	     }
    	else if(jQuery("#hdnslamStatus").val() =="R" )
	     {
    		jQuery("#chkslaReject").prop("checked",true);
    		jQuery("#chkslaApprove").prop("checked",false);
	     }
    	else
	     {
	     	jQuery("#chkslaReject").prop("checked",false);
    		jQuery("#chkslaApprove").prop("checked",false);
    		//fillWithCurrentDate("dteslamApproveddate");
    		//setFieldValue("txtslamRemarks","");
    		//setFieldValue("cmbslamApprovedby","");
	     }
	}
	
	function frmSlaRep_successsCallback(result){
		
		 setFieldValue("cmbSladProductid","");
		 setFieldValue("cmbsladFrequency","");		 
		 setFieldValue("txtSladQualitycharacter","");
		 setFieldValue("cmbSladUomid","");
		 setFieldValue("txtSladRemarks","");
		 setFieldValue("txtSladMutualtarget","");
		 setFieldValue("txtSladMutualmin","");
		 setFieldValue("txtSladMutualmax","");
		 setFieldValue("hdnSladKeyid","");
		 setFieldValue("txtsladAdherenceperc","");	
		 setFieldValue("dtesladEffectivedate","");
		 
		 enableFields('txtSladMutualmax');
		 enableFields('txtSladMutualmin');
			
		 var fromPillar = jQuery("#hdnfrom").val();
		 
		 var Keyid = result.successData.keyId;
		 setFieldValue("txtSlamKeyid",Keyid);
		
		//jQuery("#SLADetGrid").jqGrid().trigger("reloadGrid");
		//processGridnew("DepartmentReportdetails_input.SerLevAgr", "?q=2&SlamKeyid="+Keyid+"&from="+fromPillar, "SLADetGrid", "pager2", "", "doubleclickdtl", "");
		
		 var fromdmtid=getFieldValue("cmbSlamFromdptid");
		 var todmtid=getFieldValue("cmbSlamTodptid");
         var fromPillar = jQuery("#hdnfrom").val();
         //alert(fromdmtid);		
	     processGridnew("DepartmentReportdetails_input.SerLevAgr","?q=2&fromdmtid="+fromdmtid+"&tosectid="+todmtid+"&from="+fromPillar, "SLADetGrid", "pager2", "", "doubleclickdtl","","sla_LoadComplete");
		
		jQuery("input:radio[name=chkSlamSource][value ='"+getFieldValue('hdnSlamSource')+"']").prop('checked', true);
		SetStatus();
		navigateToPrevForm();
	}
	
	function btnfilemgr_click()
	{
		//alert("11");
	    var documentNo =jQuery('#txtSlamKeyid').val();
	   
		if(documentNo != null && documentNo != '')
		{
			fileManagerPopUp(documentNo,"SLA","","","");
		}
		
	}
	/*function frmSlaRepcmbSlamTodptid_onLoadSuccess(){
		
		fillComboBox("frmSlaRep", "cmbSladProductid", "product.commonFilter");
	}
	function frmSlaRepcmbSladProductid_onLoadSuccess(){
		
		fillComboBox("frmSlaRep", "cmbSladUomid", "uomCombo.commonFilter");
	}*/
	function getFocus(id) {
		numericTextBox(id);
		}
	/*function frmSlaRep_beforeSubmit(){
		
				
		//setFieldValue('hdnSlamSource',jQuery('input[name=chkSlamSource]').val());
	}*/
	function frmSlaRep_deleteSuccessCallback(result){

		if( result.msg == "SLADtl deleted succesfully")
		{    alert(" Data Made as Inactive Successfully");
			 setFieldValue("cmbSladProductid","");
			 setFieldValue("cmbsladFrequency","");		 
			 setFieldValue("txtSladQualitycharacter","");
			 setFieldValue("cmbSladUomid","");
			 setFieldValue("txtSladRemarks","");
			 setFieldValue("txtSladMutualtarget","");
			 setFieldValue("txtSladMutualmin","");
			 setFieldValue("txtSladMutualmax","");
			 setFieldValue("hdnSladKeyid","");
			 setFieldValue("txtsladAdherenceperc","");	
			 setFieldValue("dtesladEffectivedate","");
			jQuery("#SLADetGrid").jqGrid().trigger("reloadGrid");
		}
		else
		{	alert("Deleted Successfully");
			navigateToNextForm('ServiceLevelAgreement_input.SerLevAgr?q=1',"SLA Definition");
		}
	}
	function formattercheckbox(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;	
		return '<input type="checkbox" id="chkSlaDtl_'+rowId+'_'+colId+'" name="chkSlaDtl_'+rowId+'_'+colId+'" style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}
	function chkboxCheck(rowId)
	 {
		jQuery("#SLADetGrid").jqGrid('setCell',rowId,'CHECKVAL','1');
	 }

	function chkboxUnCheck(rowId)
	 {
		jQuery("#SLADetGrid").jqGrid('setCell',rowId,'CHECKVAL','0');
	 }
	 
	function frmSlaRep_beforeDelete()
	{   
		var status =  getFieldValue('hdnstatus');
		if(status == 'ACCEPTED')
		{
			return false;
		}
			 //var delMsg = "Do You Want To Delete ?";
			 var delMsg = "Do You Want To Make This Record as Inactive ?";
			 	if(confirm(delMsg) == false)
				{
						return false;
				}
		
	}
	

	
	function ApproveChk()
	{   
		if (jQuery("#chkslaApprove").is(":checked"))
		{
			
			setFieldValue('hdnslamStatus','A');	
		}
		else
		{
			
			setFieldValue('hdnslamStatus','R');	
		}
		
	}
	function RejectChk()
	{   
		if (jQuery("#chkslaReject").is(":checked"))
		{
			
			setFieldValue('hdnslamStatus','R');	
		}
		
	}
	function remove_successCallBack(){
		
		jQuery("#SLADetGrid").jqGrid().trigger("reloadGrid");
	}
	function maxgotchange(){		
		var minVal=parseInt(getFieldValue('txtSladMutualmin'));
		var maxVal=parseInt(getFieldValue('txtSladMutualmax'));
		var targetVal=parseInt(getFieldValue('txtSladMutualtarget'));
		if(maxVal>0){
			if(minVal>maxVal){
				alert("Minimum Value Should be Less than Maximum Value");
				setFieldValue('txtSladMutualmin','');
			}
			else if(targetVal> maxVal){
				alert("Target Value Should be Less than Maximum Value");
				setFieldValue('txtSladMutualtarget','');
			}
		}
	}
	function mingotchange(){		
		var minVal=parseInt(getFieldValue('txtSladMutualmin'));
		var targetVal=parseInt(getFieldValue('txtSladMutualtarget'));
		if (targetVal< minVal){
			alert("Target Value Should be greater than Minimum Value");
			setFieldValue('txtSladMutualtarget','');
			}
	}
	
	function targetgotchange(){		
		var minVal=parseInt(getFieldValue('txtSladMutualmin'));
		var maxVal=parseInt(getFieldValue('txtSladMutualmax'));
		var targetVal=parseInt(getFieldValue('txtSladMutualtarget'));
		if (targetVal>0){
			if(maxVal>0){
				if(targetVal> maxVal){
					alert("Target Value Should be Less than Maximum Value");
					setFieldValue('txtSladMutualtarget','');
				}
			}
			if(minVal>0)
			{
			if (targetVal< minVal){
				alert("Target Value Should be greater than Minimum Value");
				setFieldValue('txtSladMutualtarget','');
				}
			}
		}	
	}
function frmSlaRep_beforeSubmit()
{   
	var empmname=jQuery('#hdnempname').val();
	var inboxfrmmode=jQuery('#hdninboxmode').val();
	var type=jQuery('#hdntype').val();
	var otherspillar=jQuery('#hdnotherpillar').val();
    var hdnslatype=jQuery('#hdnslatype').val();
    var keyid=jQuery("#txtSlamKeyid").val();
    
   	
    
	if(otherspillar.length==0 && hdnslatype.trim().length>0){
		return false;
	}
	
    if(empmname.trim().length==0 && type.trim().length>0){
	 	popupCommonErrorMsg(" ur not QM-Pillar Member :: ");
	    return false;
	}
		var cmode = getFieldValue('hdnmode','frmSlaRep');
		var slastatus = getFieldValue("cmbSlamStatus","frmSlaRep");
		var arg ="";

		if (cmode != "Authentication"){
			arg = "&isdtlsave=true";
		}
		else{
			arg = "&isdtlsave=false";
		}

		if (slastatus.trim().length>0 && slastatus!="undefined"){
		      arg+="&slastatus="+slastatus;
		}
		
		 if(inboxfrmmode=="inbox")
		    {
			  arg+="&inboxmode=inbox";
		    }
		
		return arg;
	
}
	
</script>

	<form action="" method="post" id="frmSlaRep" name="frmSlaRep">
		<div id="wrapper" style="width:100%;margin-top:60px;">
		<div style="padding-left: 7%;">
				<table>
						<tr>
							<td>
								<div class="easyui-paddingbfpx">
									<label class="mandatory-lbl">Source</label>
									<span style="margin-left:85px"><label class="mandatory-lbl" style="padding-left: 22px;">Date</label> </span>
								</div>
								<div class="easyui-paddingbfpx">
									<span style="border: solid; border-width: 1px"><input type="radio" name="chkSlamSource" id="rdInternal" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="I" /><label>Internal</label>
										<input type="radio" name="chkSlamSource"  id="rdExternal"  <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="E"/><label>External</label>
									</span> 
									<span style="margin-left:30px">
										<input class="easyui-text" id="dteSlamDate" name="dteSlamDate" maxlength="30" style="width: 90px; height: 21px;" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="${requestScope.jhnTlSlamst.slamDate}"/>
									</span>
								</div>
								</td>
								<td style="padding-left: 60px;">
								<div class="easyui-paddingbfpx" >
									<label class="mandatory-lbl">Between From (Source)</label>
								</div>
								<div class="easyui-paddingbfpx">
										<input class="easyui-combo" id="cmbSlamFromdptid" disabled="disabled" name="cmbSlamFromdptid" style="width: 277px;" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="${requestScope.jhnTlSlamst.slamFromdptid}"/>
									</div>
								</td>
								<td style="padding-left: 78px">
								<div class="easyui-paddingbfpx" id="divAndLbl" >
									<label class="mandatory-lbl" id="lblAnd">And (Destination)</label>
								</div>
									<div class="easyui-paddingbfpx">
										<input class="easyui-combo" id="cmbSlamTodptid" name="cmbSlamTodptid" style="width: 265px;" value="${requestScope.jhnTlSlamst.slamTodptid}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>  />
									</div>
									</td>
								</tr>
								</table>								
								<div style=" position:relative;  width:974px; width:974px\9;  height:20px; height:25px\9;" class="sub-header">
								<span style="position: absolute;">SLA Detail</span>
								</div>

							<table id="divproduct">
								<tr>
							<td style="vertical-align: top;">
							
								<div class="easyui-paddingbfpx" >
									<label>Parameter/Specification</label>
								</div>
								<div class="easyui-paddingbfpx"> 
									<input class="easyui-combo" id="cmbSladProductid" name="cmbSladProductid"  style="width: 264px;" value="${requestScope.JhnTlSladtl.SladProductid}"  <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> />
								</div>
								
							<td style="padding-left: 30px;;vertical-align: top;" >
							
							<div style="padding-left:20px; padding-top:0px;">
								<div class="easyui-paddingbfpx" >
								
									<label class="mandatory-lbl">UOM</label>									
									<span style="margin-left: 134px;"><label class="mandatory-lbl">Frequency</label></span>
								</div>

								<div class="easyui-paddingbfpx">
									<span>
									<input class="easyui-combo" id="cmbSladUomid" name="cmbSladUomid"   style="width: 160px;" value="${requestScope.jhnTlSladtl.sladUomid}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> />
									
									</span>
									
									<span style="margin-left:18px; position:absolute;">
									<select class="easyui-combo" id="cmbsladFrequency" name="cmbsladFrequency" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>  style="width: 115px; height: 21px;" onchange ="getfrequency()">
											<option value=''></option>											
											<option value='M'>MONTHLY</option>
											<option value='W'>WEEKLY</option>
											<option value='D'>DAILY</option>
											<option value='S'>SHIFT</option>
											<option value='H'>HOURLY</option>											
									</select>	
									<label  id="err_cmbsladFrequency" class="tpm-errormsg"></label>								
									</span>
									<label id="err_cmbSladUomid" class="tpm-errormsg"></label>  
									<span style="margin-left:38px; position:absolute;">									
									</span>   
								</div>
								</div>
							
								
							</td>
							<td style="vertical-align: top; padding-left: 70px; ">

								<div class="easyui-paddingbfpx" >
									<label class="mandatory-lbl">Adherence Percentage</label>
									<span style="padding-left: 14px;"><label class="mandatory-lbl" >Effective Date</label></span>
								</div>
								
								<div class="easyui-paddingbfpx">
									<input class="easyui-text" id="txtsladAdherenceperc" name="txtsladAdherenceperc"  maxlength="3" value="${requestScope.jhnTlSladtl.sladAdherenceperc}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>  style="width: 130px;"/>
								<span style="padding-left: 20px;">
									<input id="dtesladEffectivedate" name="dtesladEffectivedate" type="text" class="easyui-text" maxlength="95" value="${requestScope.jhnTlSladtl.sladEffectivedate}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> style="width: 116px;" value=""/></span>
								
								</div>	
							<div class="tpm-errormsg" id="err_txtsladAdherenceperc" style="position:absolute;right:160;"></div>
								</td>
						</tr>
						<tr>
						<td >
							<div class="easyui-paddingbfpx" >
								<label class="mandatory-lbl">Quality Characteristics (Product and Service)</label>
							</div>
							<div class="easyui-paddingbfpx">
								<textarea rows="2" cols="80" style="width: 265px; height : 80px;" id="txtSladQualitycharacter" name="txtSladQualitycharacter" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> >${requestScope.jhnTlSladtl.sladQualitycharacter}</textarea>
							</div>
						</td>
							<td valign="top">
							 <div class="easyui-paddingbfpx" style="padding-top: 2px;padding-left:50px;">
									<label>Mutually Agreed Upon Specifications</label>
							 </div>
							 <div class="easyui-paddingbfpx" style="width : 291px;padding-left:50px;">
									<label class="mandatory-lbl">Values</label>
									<input class="easyui-combo" id="cmbslaval" name="cmbslaval" style="width: 243px;" />
									<!-- 
									<select id="cmbslaval" class="" name="cmbslaval"  onchange="alert(34);getslaval();"  style="height:22px;width:95px;">
										<option value="LST">Lesser Then </option>
										<option value="GRT">Greater Then</option>	
										<option value="BWT">Between</option>							
									</select> 
									-->
							</div>
							<div id="" style="position:absolute; left : 444px; width : 429px; padding-top:10px;" >								
								<span id="mindiv"  >
									 <span style="top:30px;">
										<label class="mandatory-lbl" >Min</label> 
										<span style="padding-left:17px;">
											<input class="easyui-text" id="txtSladMutualmin" name="txtSladMutualmin" onblur="maxgotchange();"
											maxlength="6" style="width: 50px;text-align:right; padding-left: 10px;"   
											value="${requestScope.jhnTlSladtl.sladMutualmin}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> />
										</span>
									</span>
						        </span>
								<span id="maxdiv"  >
										 <span style="padding-left:5px;">
											 <label class="mandatory-lbl" >Max</label> 
											 <input class="easyui-text" id="txtSladMutualmax" name="txtSladMutualmax" maxlength="6" onblur="maxgotchange();" style="width: 50px;text-align:right;"   value="${requestScope.jhnTlSladtl.sladMutualmax}" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> />
										 </span>
								</span>
								<span id="maxtarget"  >
							        <span style="padding-left:5px;">
									<label class="mandatory-lbl" >target</label> 
									<span style="position:absolute;padding-left:5px;" >
									<input class="easyui-text" id="txtSladMutualtarget" name="txtSladMutualtarget" maxlength="6"  onblur="targetgotchange();" style="width: 50px;text-align:right;"  value="${requestScope.jhnTlSladtl.sladMutualtarget}"  <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>  />
									</span>
									</span>
								</span>
							    <span style="position:absolute;left:110px;padding-top: 20px">
									  	<label id="err_txtSladMutualmin" class="tpm-errormsg"></label>
								</span>   
								  <span style="position:absolute;left:210px;padding-top: 20px">
								  	<label id="err_txtSladMutualmax" class="tpm-errormsg"></label>   
							 	 </span>	
							 	 <label id="err_txtSladMutualtarget" class="tpm-errormsg"></label>								 
								</div>
							</td>
							<td valign="top" style="padding-left:70px;">
							<div class="easyui-paddingbfpx" >
									<label>Remarks</label>
							</div>
							<div class="easyui-paddingbfpx">
								<textarea rows="2" cols="80" style="width: 273px; height : 40px;"  id="txtSladRemarks" name="txtSladRemarks" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> >${requestScope.jhnTlSladtl.sladRemarks}</textarea>
							</div>
							</td>
						</tr>
						<tr>
						<td>
						</td>
						<td  style="padding-left: 33px;" valign="top">
						</td>
							<td  style="padding-left: 70px; " valign="bottom">
														
							<div style ="width:330px; position:relative;margin-top:-20px;">
								<input type="button" class="easyui-button"   id="btnDtlsave" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>
										name="btnDtlsave" style="width:53px;height:21px;"value="Save" />								
								<input type="button" class="easyui-button" id="btnDeletedtl" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/>
										name="btnDeletedtl"  style="width:53px;height:21px;" value="Delete" />									
								<input type="button" class="easyui-button" id="btnDtlclear" name="btnDtlclear"style="width:53px;height:21px;" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="Clear" />
								<input type="button" class="easyui-button" id="btnDtlexcel" name="btnDtlexcel"style="width:65px;height:21px;" <c:out value = "${ requestScope.SlaFormBean.disableForAuth == true ? ' disabled':''}"/> value="Excel View" />
								<span  id="SLAFilemgr" style="position:absolute;margin-left:10px;right:-30px;right:100px\9;"></span>
							</div>
							</td>
					</tr>
			</table>
		
	<div id="divslagrid" style="width: 79%;">
			<table id='SLADetGrid'>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager2'></div>
		</div>
		
		<c:if test="${true  == requestScope.SlaFormBean.disableForAuth }">
   
     
	<div style=" position:relative;  width:97%; width:954px\9;  height:20px; height:25px\9;" class="sub-header">
			<span id="authorization" style="position: absolute;">Authorization</span>
			</div>
	<table>
	<tr>
	<td width="293px" valign="top">
			
		<div class="easyui-paddingbfpx" style="padding-top: 20px;display:none;">
			<input id="chkslaApprove" name="chkslaApprove" onclick="ApproveChk()" type="checkbox"  value="A" /><span Style="padding-left: 3px;"><label>Approve </label></span>
		</div>
		<div class="easyui-paddingbfpx" style="padding-top: 3px;dispaly:none;">
			<input id="chkslaReject" name="chkslaReject" onclick="RejectChk()" type="checkbox"  value="R" style="display:none;"/><span Style="padding-left:0px;display:none;"><label> Reject</label> </span>
	    </div>
	    <div class="easyui-paddingbfpx" style="padding-top: 3px;dispaly:none;">
			<span Style="padding-left:0px;"><label>Approval</label> </span>
	    </div>
	    <div class="easyui-paddingbfpx" style="padding-top: 3px;dispaly:none;">
	    	<input class="easyui-combobox" id="cmbSlamStatus" name="cmbSlamStatus" style="width:120px;"  value="${requestScope.jhnTlSlamst.slamStatus}"/>
		</div>
	</td>
			<td valign="top">
			<div class="easyui-paddingbfpx">
								<label >Approved By</label>
								<span style="padding-left: 128px;"><label >Approved Date</label></span>
							</div>
							<div>
								<input id="cmbslamApprovedby" name="cmbslamApprovedby"
									type="text" class="easyui-combo" maxlength="95" 
									style="width: 132px\9; width : 174px;" 
									value="${requestScope.jhnTlSlamst.slamApprovedby}" />
									<span style="padding-left: 23px;">
									<input id="dteslamApproveddate" name="dteslamApproveddate"
									type="text" class="easyui-text" maxlength="95"
									style="width: 100px;" 
									value="${requestScope.jhnTlSlamst.slamApproveddate}"/></span>
							</div>
			</td>
			<td style="padding-left: 40px;">
			<div class="easyui-paddingbfpx"><label>Remarks</label></div>
			<div>
		<textarea  rows="3" cols="80"style="width: 237px; height : 60px;" id="txtslamRemarks"  name="txtslamRemarks" >${requestScope.jhnTlSlamst.slamRemarks}</textarea>
			</div>
			
			</td>
			</tr>
			</table>
			
		 </c:if>
		
		</div>
		</div>
	
	<input type="hidden" id="formClrVal" name="formClrVal" value="${requestScope.clearfrom}" />
	<input type="hidden" id="hdnSlamSource" name="hdnSlamSource" value="${requestScope.jhnTlSlamst.slamSource}"/>
	<input type="hidden" id="txtSlamKeyid" name="txtSlamKeyid" value="${requestScope.jhnTlSlamst.slamKeyid}"/>
	<input type="hidden" id="hdnslamStatus" name="hdnslamStatus" value="${requestScope.jhnTlSlamst.slamStatus}"/>
	<input type="hidden" id="txtSladKeyid" name="txtSladKeyid" value="${requestScope.jhnTlSladtl.sladKeyid}"/>
	<input type="hidden" id="hdnApprovedby" name="hdnApprovedby" value="${requestScope.Approvedby}"/>
	<input type="hidden" id="hdnSladCreatedon" name="hdnSladCreatedon" value=""/>
	<input type="hidden" id="hdnSlamCreatedon" name="hdnSlamCreatedon" value="${requestScope.jhnTlSlamst.slamCreatedon}"/>
	<input type="hidden" id="hdnSlamPreparedby" name="hdnSlamPreparedby" value="${requestScope.jhnTlSlamst.slamPreparedby}"/>
	<input type="hidden" id="hdnSlamPrepareddate" name="hdnSlamPrepareddate" value="${requestScope.jhnTlSlamst.slamPrepareddate}"/>
	<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}" />
	<input type="hidden" id="hdnstatus" name="hdnstatus" value="${requestScope.status}" />
	<input type="hidden" id="hdndmtid" name="hdndmtid" value="${requestScope.dmtid}" />
	<input type="hidden" id="hdnlockdmt" name="hdnlockdmt" value="${requestScope.lockdmt}" />
	 <input type="hidden" id="mode" name="mode"  /> 
	<input type="hidden" id="hdnfrom" name="hdnfrom"  value="${requestScope.from}" />
	<input type="hidden" id="hdnqmplardata" name="hdnqmplardata" value=""/>
	<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>
	<input type="hidden" id="hdnslatype" name="hdnslatype" value="${requestScope.slatype}"/>
	<input type="hidden" id="hdnslarolelevel" name="hdnslarolelevel" value="${requestScope.slarolelevel}"/>
	<input type="hidden" id="hdnsladfrequency" name="hdnsladfrequency" value="${requestScope.jhnTlSladtl.sladFrequency}"/>
	<input type="hidden" id="hdnotherpillar" name="hdnotherpillar" value=""/>
	<input type="hidden" id="hdnempname" name="hdnempname" value=""/>
	<input type="hidden" id="hdnactive" name="hdnactive" value=""/>
	<input type="hidden" id="hdnmstid" name="hdnmstid" value="${requestScope.mstid}"/>
	<input type="hidden" id="hdndtlid" name="hdndtlid" value="${requestScope.dtlid}"/>
	<input type="hidden" id="hdntodmtid" name="hdntodmtid" value="${requestScope.todmtid}"/>
	<input type="hidden" id="hdnloginlevel" name="hdnloginlevel" value="${requestScope.loginlevel}"/>
	<input type="hidden" id="hdnhidebutton" name="hdnhidebutton" value="${requestScope.hidebutton}" />
	<input type="hidden" id="hdninboxmode" name="hdninboxmode" value="${requestScope.inboxxmode}" />
</form>
