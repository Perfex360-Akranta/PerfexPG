
	jQuery(document).ready(function(){//alert(" Pm Standard ");
		initialiseForm('frmPmStandardform');
		jQuery('#frmPmstdbdr').addClass('main-cntborder');
		//"MoldDiv"
		var url = jQuery('#hiddenUrl').val();
		fillComboBox("frmPmStandardform", "cmbPmsdUom", "uomCombo.commonFilter");
		jQuery('#tradeName').css('display','none');
		jQuery('#btnChkDiv').css('display','none');
		//alert(url);
		//=&relatedTo=Mould&loadContentDivId
		jQuery('#cmbPmsdAssemblyid').combobox({onRequest:function( ){
			var machId = jQuery("#frmPmStandardform input[id='machine']").val();
			if(jQuery('#chkPmOtherAssm').is(':checked') == true)
				machId ="";
			return "&machineId="+machId ;
	  	}});      
		
		var tempplmSource=jQuery("#hdnplmSource").val();
		 if(tempplmSource == '' || tempplmSource == null || tempplmSource== "")
			 jQuery('input:checkbox[value=I]').attr('checked',true);
		 
			var gUrl = url.substring(url.indexOf('edTo='),url.indexOf('&loadContentDivId'));
			gUrl = gUrl.substring(5,gUrl.length);//alert(gUrl);
			/*if("Mould" == gUrl){
				jQuery('#MoldDiv').css('display','block');
				jQuery('#mouldtd').css('width','33%');
			}
			else
				jQuery('#mouldtd').css('width','0%');
			*/
		/*var titleBar ='<div id="titleAdditionalInfo" class="sub-header " style="width:102%;_height:10px;margin-left:-12px;margin-top:-10px;"><label id="lblAdditionalInfo" style="margin-left:1px;font-size:11px;">Additional Information</label><img id="imgAdditionalInfo" src="images/close-butt1.png" style="float:right;cursor:pointer; " onclick="closepopAddinfo();"/> </div>';
		jQuery('#mainlayout').after('<div class="custom-popup LoadPopUp" id="addInfPop" style="display:none;width:64%;left:12%;z-index:10;top:25%;height:63%;position:absolute;_position:absolute;">'+titleBar+jQuery('#additional_info_frm').html()+'</div>');
		jQuery("#dvAddInfoWrapper").html('');*/
		
		 
			
		//initialiseForm("frmPmStandardform");
		
		toolgrid();//for tool Grid show
		chk_select();//for tool check box 
		pm_spares();//for spares grid
		jQuery('#cmbPmsdSupplierid').combobox('disable');
		jQuery('#submitForm').val('frmPmStandardform'); // set the id of form to submit
		//for getting values in combobox call the action
		/*added on 22nd jun*/
		var machId ;
		machId = jQuery("#frmPmStandardform input[id='machine']").val();
		if(machId != " " && machId !="" && machId != null){
			
		}
		else
			machId = jQuery('#hdnMachId').val();
		
		//
		jQuery('#frmPmStandardform .easyui-text').css('text-transform', 'uppercase');
        jQuery('#frmPmStandardform textarea').css('text-transform', 'uppercase');	
          
        fillComboBox("frmPmStandardform","cmbPmsdMachineid","machineCombo.commonFilter");
 		fillComboBox("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter");
		jQuery("#frmPmstdbdr").removeClass('main-cntborder');	
		var assmId =   getFieldValue('cmbPmsdAssemblyid',"frmPmStandardform");
		fillComboBox("frmPmStandardform","cmbPmsdSubassemblyid","Pmsd_SubassemblyId.prv?assmId="+assmId);
		fillComboBox("frmPmStandardform","cmbPmsdTradeid","combo_pmsdTrade.prv");
		fillComboBox("frmPmStandardform","cmbPmsdActivitytype","Pmsd_Jobtype.prv"); 
		fillComboBox("frmPmStandardform","cmbPmsdMachinecondition","combo_pmsdMScondition.prv"," ",false);
		fillComboBox("frmPmStandardform","cmbpmsdCostCenter","costCenter.commonFilter");
	       
		fillComboBox("frmPmStandardform","cmbPmsdFrequencyunit","combo_pmsdwhtfreq.prv");
		fillComboBox("frmPmStandardform","cmbPmsdIdealconditiontype","combo_idealcondition.prv");
		fillComboBox("frmPmStandardform","cmbPmsdPreparedbyid","employee.commonFilter");//combo_pmsdpreparedby.prv
		//fillComboBox("frmPmStandardform","cmbPmsdSubassemblyid","Puns_supplierId.unschedacty");
		/* for functionalLocation*/
		var factId = jQuery("#frmPmStandardform input[id='factory']").val();
		//alert(factId);

		var sectionId = jQuery("#frmPmStandardform input[id='section']").val();
		var cellId = jQuery("#frmPmStandardform input[id='cell']").val();
		var machId = jQuery("#frmPmStandardform input[id='machine']").val();
		var flid = jQuery("#frmPmStandardform input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		//alert(dataStr);
		var whymachId = jQuery('#hdnMachine').val();
		var machId = '';
		if(whymachId != " " && whymachId !="" && whymachId != null)
			machId = whymachId ;
		else
			machId = jQuery('#hdnMachId').val();
		
		
		
		if(machId != " " && machId !="" && machId != null)
			setFieldValue('cmbPmsdMachineid',machId,'frmPmStandardform');
		else
			machId = jQuery("#cmbPmsdMachineid").combobox("getValue");
		
		if(machId != " " && machId !="" && machId != null){

			loadFunctionalLocation("pmsdMainfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandardform","&machId="+machId);
		}
		else
			loadFunctionalLocation("pmsdMainfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandardform",dataStr);
		
	/*---------*/
		//alert(dataStr);
		numericTextBox('txtPmsdDuration');
		//function for tool  grid
		jQuery('.main-cntborder').css('height','840px');
		var documentNo =jQuery('#hdnPmsdkeyid').val();
		fileManagerPopUp(documentNo,"PMS","frmPmStandardform","btnFilManagePM","filSpanFilemgr");
		/*for file manager*/ /*for file manager*/
		
/*		jQuery("#btnFilManagePM").click(function(){
			var documentNo =jQuery('#hdnPmsdkeyid').val();
			if(documentNo != null && documentNo != ''){

				fileManagerPopUp(documentNo,"PMS","","","", "");
				
			}
			else
	 			alert("Standard Key Id not Avaliable to view FileManager");
			
		}); */
		var pmsdkey  = jQuery('#hdnPmsdkeyid').val();
		var pmstdId = "" ;
			
		if(pmsdkey.trim().length <=0 ){
			jQuery("#btnFilManagePM").attr("disabled", true);
			jQuery("#btnFilManagePM").removeClass("easyui-button");
			jQuery("#btnFilManagePM").addClass("disabledButton");
			 pmstdId = "";
	     }
		else
			pmstdId =pmsdkey;
		processGridnew("addInfo_input.prv","&q=0&pmStandardId="+pmstdId,"addInfoGrid","pager22","","","","addInfoGrid_loadComplete");
		/*** For Adding Assembly ****/
		jQuery('#btnfrmaddAssembly').click(function(){
			var fact = jQuery('input:[name=cmbPmsdFactoryid]').val();
			var sect = jQuery('input:[name=cmbPmsdSectionid]').val();
			var cell = jQuery('input:[name=cmbPmsdCellid]').val();
			var mach = jQuery('input:[name=cmbMachineid]').val();
			var elemId = fact+"-"+sect+"-"+cell+"-"+mach+"&formField=A&pntId="+fact+"-"+sect+"-"+cell+"&masterSelId="+mach;
			if(mach != ' ' && mach != '' && mach != null)
				//LoadPopUp("funcnLocn_getCol.funlocn","?elemType="+fact+"-"+sect+"-"+mach+"-"+MCH000119+"-&formField=A","Functional Location");
			funcnLocnPopUp("funcnLocn_input.funlocn",elemId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack","Assembly","true");
			else
				alert('Select Machine');
			//navigateToNextForm(url,formheader,forwardData,persistentData,navigateToNext_SuccessCalBack,navigateToNext_ErrorCalBack)
		});
		//fileManagerPopUp(jQuery('#hdnPmsdkeyid').val(),"PMS","frmPmStandardform","btnFilManagePM","spnFilManagePM");
		var tableDatas = jQuery("#tools").jqGrid('getRowData');
		
		
		if(tableDatas.length>0)
		{
			jQuery('input:checkbox[name=chkPmsdIstoolsreq]').attr('checked',true);
		}
		if (!jQuery("#chkPmsdIssparesreq").is(":checked"))
		{
			jQuery("#spareBtn").removeAttr('class', 'easyui-button');
			jQuery("#spareBtn").attr('class', ' disabledButton');
			jQuery("#spareBtn").attr('disabled','disabled');
		}
		if (jQuery("#chkPmsdIstoolsreq").is(":checked")){
			jQuery("#tooltree").removeAttr('class', 'disabledButton');
			jQuery("#tooltree").attr('class', ' easyui-button');
			jQuery("#tooltree").attr('disabled','disabled');
			
		}
		jQuery(document).keyup(function(e){
				
		    if(e.keyCode === 27){
		    	 
		    	closeFlDialog('addInfPop');
		    }

		});
		jQuery( "#additional_info" ).click(function() 
				{	
				  /*jQuery("#additional_info_div").load('preventive_addinfo_input.prv', function(response, status, xhr) {
							  if (status == "error") {
							    var msg = "Sorry but there was an error: ";
							    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
							  }
							});*/
					
							 //var assmId = jQuery('#hdnAssmid').val();//jQuery('#hdnAssmid').val();
								//jQuery('#cmbPmsdAssemblyid').combobox("getValue");
							var filassmid = jQuery('#hdnPmsdAssemblyId').val();
							var assmId = jQuery('#cmbPmsdAssemblyid').combobox("getValue");
							//alert("Checking for Assembly"+filassmid);
							if(assmId != "" && assmId != "undefined" && assmId != undefined && assmId != null && assmId.trim().length>0)
							{
				 	 			//LoadForm('additional_info_div','preloadDIVid1','preventive_addinfo_input.prv','dispErr','','addInfo_errorCallBack');
								//jQuery( "#additional_info_frm" ).css('display','block');
								openAdditionalInfo();
//								jQuery( "#additional_info_frm" ).dialog( {autoOpen: false,
//									modal: true,
//									height: 450,
//									width: 900
//									});
								}
							else if(filassmid != "" && filassmid != "undefined" && filassmid != undefined && filassmid != null && filassmid.trim().length>0)
							{
				 	 		//	LoadForm('additional_info_div','preloadDIVid1','preventive_addinfo_input.prv','dispErr','','addInfo_errorCallBack');
								//jQuery( "#additional_info_frm" ).css('display','block');
								
								openAdditionalInfo();
//								jQuery( "#additional_info_frm" ).dialog( {autoOpen: false,
//									modal: true,
//									height: 400,
//									width: 900
//									});
								}
							else{
								alert('Select Assembly');
								}
									return false;
								});
			/*	jQuery('#imgAdditionalInfo').click(function(){
					//jQuery( "#additional_info_frm" ).css('display','none');
					jQuery('#addInfoDiv').val('true');
					closeFlDialog('addInfPop');
				});
					*/	
				jQuery( "#rsrc_pln" ).click(function() 
				{
					LoadForm('rsrc_pln_div','preloadDIVid2','prvnt-mntnc-rsrc_input.prv','dispErr','','resplan_errorCallBack');
					jQuery( "#rsrc_pln_frm" ).dialog( {autoOpen: false,
							modal: true,
							height: 450,
							width: 900});
					return false;
				});	
				
				//function for check one select and deselect the other check box						
					jQuery('#chkPmsdSource').click(function(){
						jQuery('input:checkbox[name=ch1PmsdSource]').attr('checked',false);
						jQuery('input:checkbox[name=chkPmsdSource]').attr('checked',true);
						jQuery('#cmbPmsdSupplierid').combobox('disable');
						});
					jQuery('input:checkbox[name=ch1PmsdSource]').click(function(){		
						 jQuery('input:checkbox[name=chkPmsdSource]').attr('checked',false);
						 jQuery('input:checkbox[name=ch1PmsdSource]').attr('checked',true);
						 jQuery('#cmbPmsdSupplierid').combobox('enable');
					});
				//function for combobox getvalue
				jQuery('#filemanager').click(function(){
					var vale = jQuery('#cmbPmsdFrequencyunit').find('option:selected').text();

				     var val=jQuery('#cmbPmsdFrequencyunit').combobox('getValue');

					
				});
				jQuery('#btnsubType').click(function(){
					//alert(jQuery('#txtPmsdActivitysubtype').val());
					//jQuery("#hdnColNames").val();
					 
					var colNames = jQuery('#txtPmsdActivitysubtype').val();
					multiSelectPop("subType_input.prv","","","",colNames,"true","","subTypePopOk_Callback");	
					
				});
				jQuery('#spares').click(function(){
					//var Ass
					//subFormPop("Sparepickup_input.sprpckup","480","1180","spares","");
					//subFormPop("PhenCause_input.pcl","Phenomena-Cause-Link","?q=2");
					/*jQuery( "#Spares_div" ).show();
					jQuery( "#Spares_div" ).dialog({
						autoOpen: false,
						modal: true,
						height: 380,
						width: 1180,	
						top:150	
					});	*/
					//panel-header panel-header-noborder window-header
					//navigateToNextForm("Sparepickup_input.sprpckup");
					
					//jQuery(".window-header").hide();
					//jQuery( ".window-shadow" ).hide(); 
					
					
				});
				jQuery('#close_spares').click(function(){
				jQuery( "#Spares_div" ).dialog("close");

				});
				var valActType = getFieldValue('cmbPmsdActivitytype','frmPmStandardform');
				if('SDM'==valActType){
					jQuery("#lblSource").removeClass("mandatory-lbl");
					jQuery("#whtFreqlbl").removeClass("mandatory-lbl");
					jQuery("#assmLbl").removeClass("mandatory-lbl");
					jQuery("#machcondLbl").removeClass("mandatory-lbl");
					jQuery("#howMuchLbl").removeClass("mandatory-lbl");
				}
				numericTextBox("txtPmsdMinValue");
				numericTextBox("txtPmsdMaxValue");
				numericTextBox("txtPmsdTarget");
				//alert(getFieldValue('cmbPmsdIdealconditiontype','frmPmStandardform'));
				var idealCond=getFieldValue('cmbPmsdIdealconditiontype','frmPmStandardform');
				//alert(idealCond);
				if (idealCond =='T')
				{
					jQuery("#txtPmsdStandard").attr("disabled", false);
					jQuery("#txtPmsdMinValue").attr("disabled", true);
					jQuery("#txtPmsdMaxValue").attr("disabled", true);
					jQuery("#txtPmsdTarget").attr("disabled", true);
					   
				}
				else if (idealCond =='M'){
					jQuery("#txtPmsdStandard").attr("disabled", true);
					jQuery("#txtPmsdMinValue").attr("disabled", false);
					jQuery("#txtPmsdMaxValue").attr("disabled", false);
					jQuery("#txtPmsdTarget").attr("disabled", false);
				}
				else if(idealCond=='O' || idealCond=='' || idealCond==null) {
					jQuery("#txtPmsdStandard").attr("disabled", true);
					jQuery("#txtPmsdMinValue").attr("disabled", true);
					jQuery("#txtPmsdMaxValue").attr("disabled", true);
					jQuery("#txtPmsdTarget").attr("disabled", true);
					
				}
				
				jQuery('#chkIdealCond').click (function ()
				{
						var thisCheck = jQuery(this);
						if (thisCheck.is (':checked'))
						{
							jQuery("#txtPmsdStandard").attr("disabled", true);
							jQuery("#txtPmsdMinValue").attr("disabled", false);
							jQuery("#txtPmsdMaxValue").attr("disabled", false);
							jQuery("#txtPmsdTarget").attr("disabled", false);
							   
						}
						else{
							jQuery("#txtPmsdStandard").attr("disabled", false);
							jQuery("#txtPmsdMinValue").attr("disabled", true);
							jQuery("#txtPmsdMaxValue").attr("disabled", true);
							jQuery("#txtPmsdTarget").attr("disabled", true);
						}
			});		
			 
			jQuery("#btnTaskList").click(function(){
				var machId = jQuery("#frmPmStandardform input[id='machine']").val();
				LoadPopUp("", "methodTasklist_input.prv?machineId="+machId, true,"70%","460px","10%","7%", "","Task List");
			});	
			
			jQuery("#chkPmOtherAssm").click(function(){
				if(jQuery(this).is("checked")){
					clearField("cmbPmsdAssemblyid");
					reloadCombo("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter" );			
				}
				else{
					clearField("cmbPmsdAssemblyid");
					var machId = jQuery("#frmPmStandardform input[id='machine']").val();
					reloadCombo("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter?machineId="+ machId );
				}
			});	
			
		   
	});
	function btnFilManagePM_click(){
		var documentNo =jQuery('#hdnPmsdkeyid').val();
		if(documentNo != null && documentNo != ''){
			fileManagerPopUp(documentNo,"PMS","","","");
		}
		else
 			alert("Please create standard to add Files");
		
	}
	function list_multiselectPopUpId_onClose()
	{
		var mach = jQuery('input:[name=cmbMachineid]').val();
		//reloadCombo("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter?q=2&machineId="+ mach);	
	}
	function MultiSelectCancel_CallBack(args)
	{
		jQuery('#multiselectPopUpId').dialog('close');
		return true;
	}
	
	var activityType = getFieldValue('cmbPmsdActivitytype','frmPmStandardform');
	if(activityType != 'CBM'){
		jQuery("#cbmBtn").attr("disabled", true);
		jQuery("#cbmBtn").removeClass("easyui-button");
		jQuery("#cbmBtn").addClass("disabledButton");
     }
	else{
		
	}
	jQuery("#cbmBtn").click(function(){
		var pmsdkey  = jQuery('#hdnPmsdkeyid').val();
		var assmId = jQuery('#cmbPmsdAssemblyid').combobox("getValue");
		
		var watActvity = jQuery('#txtPmsdActivity').val();
		var dataString="pmsdId="+pmsdkey+"&assmId="+assmId+"&watActvity="+escape(watActvity)+"&hdnSaveVal=hdnCBMData";
		if(assmId.trim().length>0){		
			var already = jQuery('#hdnAlreadyLoad').val();
			if (already == 'Y') 
				jQuery('#loadCBM').show();
			else {
				LoadPopUp("loadCBM", "openCBM_input.prv?"+dataString, true,"81%","575px","-1%","7%", "cbmResult_successCallBack","Condition Based Maintenance(CBM)Details");
				jQuery('#hdnAlreadyLoad').val('Y');
			}			
		}
		else
			alert("Select Assembly");
	});
	
function cbmResult_successCallBack(){
	
}
	function viewGrid(url,filterString,gridName)
	{
			processGridnew(url,"?row=0","sparesGrid","sparesPager");								
	}	
	function  frmPmStandardformcmbPmsdMachineid_onLoadSuccess(){
		 
		//fillComboBox("frmPmStandardform","cmbpmsdCostCenter","costCenter.commonFilter");
	}
   function  frmPmStandardformcmbpmsdCostCenter_onLoadSuccess(){
	   fillComboBox("frmPmStandardform","cmbPmsdMouldid","mould.commonFilter");
	}
	function  frmPmStandardformcmbPmsdAssemblyid_onLoadSuccess(){
		//fillComboBox("frmPmStandardform","cmbPmsdTradeid","combo_pmsdTrade.prv");
		var assmid = jQuery('#hdnfrmassmWeeklyId').val();
		if(assmid.trim().length>0){
			setFieldValue('cmbPmsdAssemblyid',assmid,'frmPmStandardform');
			jQuery('#cmbPmsdAssemblyid').combobox('disable');
			jQuery('#hdnPmsdAssemblyId').val(assmid);
		}
	}
	function  frmPmStandardformcmbPmsdTradeid_onLoadSuccess(){
		//fillComboBox("frmPmStandardform","cmbPmsdActivitytype","Pmsd_Jobtype.prv");
	}
	function  frmPmStandardformcmbPmsdActivitytype_onLoadSuccess(){
		//fillComboBox("frmPmStandardform","cmbPmsdMachinecondition","combo_pmsdMScondition.prv");
		jQuery('#cmbPmsdActivitytype').combobox('disable');
	}
	/*function  frmPmStandardformcmbPmsdMachinecondition_onLoadSuccess(){
		//fillComboBox("frmPmStandardform","cmbPmsdFrequencyunit","combo_pmsdwhtfreq.prv");
		var valActType = getFieldValue('cmbPmsdActivitytype','frmPmStandardform');
		if('SDM'==valActType){
		    setFieldValue('cmbPmsdMachinecondition','S','frmPmStandardform');
			disableField('frmPmStandardform','cmbPmsdMachinecondition');
			disableField('frmPmStandardform','cmbPmsdFrequencyunit');
		}
	}*/
	function  frmPmStandardformcmbPmsdFrequencyunit_onLoadSuccess(){
		//fillComboBox("frmPmStandardform","cmbPmsdPreparedbyid","combo_pmsdpreparedby.prv");
	}
	function  frmPmStandardformcmbPmsdPreparedbyid_onLoadSuccess(){
	var toolPmsdId = jQuery('#hdnPmsdkeyid').val();
	
	if(toolPmsdId != null && toolPmsdId != " ")
		toolgrid(toolPmsdId);
	else{
		
		toolgrid("");
	}
	jQuery('#cmbPmsdPreparedbyid').combobox('disable');
	var hdnpmcalStatus=jQuery('#hdnpmcalStatus').val();
	if(hdnpmcalStatus == 'A')
		disableForm("frmPmStandardform");
		
	var rptMode = jQuery('#hdnrptMode').val();
	if(rptMode == "view"){
		disableForm("frmPmStandardform");
		jQuery('#chkPmsdSource').attr('disabled',true);
		jQuery(' input[name=ch1PmsdSource]').attr('disabled',true);
		
	}
	}
	function chkplnexist(machineId){
		
		processAjaxCalls("chkPlanExists_input.prv?",'&machineId='+machineId,'chkPlanExists_OnSuccess','chkPlanExists_OnError');
	};
	function chkPlanExists_OnSuccess(result){
		//alert(result.retChkpln);
		//alert("success");
		if(result.retChkpln == '0'){
			alert("No Plan Exists for this Machine");
			jQuery('#hdnPmsdPlanconfigstatus').val('N');
		}
		else
			jQuery('#hdnPmsdPlanconfigstatus').val('Y');
	}
	function chkPlanExists_OnError(result){alert("error");}
	function  frmPmStandardformcmbPmsdFrequencyunit_onSelect(record)
	{
		var machineId = getFieldValue('cmbPmsdMachineid','frmPmStandardform');
		chkplnexist(machineId);
		
		if(record.id == 'Y'){
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Year(s)");
				jQuery('#txtPmsdFrequency').attr('disabled',false);
			}
			else if(record.id == 'M'){
				jQuery('#txtPmsdFrequency').attr('disabled',false);
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Months(s)");
			} 
			else{
				jQuery('#txtPmsdFrequency').val("");
				jQuery('#txtPmsdFrequency').attr('disabled','disable');
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').removeAttr("class","mandatory-lbl");
				jQuery('#lblVal').html("Frequency Unit");
			
	 			}
			}
	function frmPmStandardformcmbPmsdIdealconditiontype_onSelect(record){
		//alert(getFieldValue('cmbPmsdIdealconditiontype','frmPmStandardform'));
		if (record.id =='T')
		{
			jQuery("#txtPmsdStandard").attr("disabled", false);
			jQuery("#txtPmsdMinValue").attr("disabled", true);
			jQuery("#txtPmsdMaxValue").attr("disabled", true);
			jQuery("#txtPmsdTarget").attr("disabled", true);
			jQuery("#txtPmsdMinValue").val("0");
			jQuery("#txtPmsdMaxValue").val("0");
			jQuery("#txtPmsdTarget").val("0");
			jQuery("#txtPmsdStandard").val("");
			   
		}
		else if (record.id =='M'){
			jQuery("#txtPmsdStandard").attr("disabled", true);
			jQuery("#txtPmsdMinValue").attr("disabled", false);
			jQuery("#txtPmsdMaxValue").attr("disabled", false);
			jQuery("#txtPmsdTarget").attr("disabled", false);
			jQuery("#txtPmsdMinValue").val("0");
			jQuery("#txtPmsdMaxValue").val("0");
			jQuery("#txtPmsdTarget").val("0");
			jQuery("#txtPmsdStandard").val("");
		}
		else if(record.id=='O' || record.id=='' || record.id==null) {
			jQuery("#txtPmsdStandard").attr("disabled", true);
			jQuery("#txtPmsdMinValue").attr("disabled", true);
			jQuery("#txtPmsdMaxValue").attr("disabled", true);
			jQuery("#txtPmsdTarget").attr("disabled", true);
			jQuery("#txtPmsdMinValue").val("0");
			jQuery("#txtPmsdMaxValue").val("0");
			jQuery("#txtPmsdTarget").val("0");
			jQuery("#txtPmsdStandard").val("");
		}
	}
		
	function  frmPmStandardformcmbPmsdAssemblyid_onSelect(record)
	{
		var machinId = jQuery("#frmPmStandardform input[id='machine']").val();
		if(machinId.trim().length<=0)
		{
			clearField("cmbPmsdAssemblyid");
			alert('Select Machine');
			return false;
		
		}
		else
			jQuery('#hdnAssmid').val(record.id);
		clearField("cmbPmsdSubassemblyid");
		reloadCombo("frmPmStandardform","cmbPmsdSubassemblyid","Pmsd_SubassemblyId.prv?assmId="+record.id);
		
	}
	
	/*function  combo_onSelect(){
		
	      jQuery("#cmbPmsdFrequencyunit").combobox({
	 		onSelect:function(recordid){
	 			
	     });*/
	    
	
	//click functions
	/*
	function closepopAddinfo(){
		jQuery('#addInfoDiv').val('true');
		closeFlDialog('addInfPop');
	}	*/
	//function for additional information pop up
	function openAdditionalInfo(){
		//jQuery('body').scrollTop();
		 // window.scrollTo(0, window.pageYOffset);
		/* var pmstdId = jQuery('#hdnPmsdkeyid').val();
		 alert("openAdditionalInfo");
		 
		//jQuery('#additional_info_frm').addClass('custom-popup');	
		 //alert('dd');
		 jQuery('#addInfPop').show();
		 //jQuery('#addInfPop').a(jQuery('#additional_info_frm').html());
	   // jQuery('#additional_info_frm').css('border','1px solid #2364CA');
	    jQuery('#additional_info_frm').css('z-index',100);
	    //jQuery('#additional_info_frm').css('top','5');
	    jQuery('#mstfrm_div').addClass('popup-mask');	
		jQuery('#mstfrm_div').show();
		//setTimeout(function() {jQuery('#addInfoPop').addClass('LoadPopUp');alert('loadpop');},1000);
	     jQuery('#additional_info_frm>div[id="mainBorder"]').css('height','55%');
	    jQuery('#additional_info_frm').css('left','200');
	    jQuery('.popup-mask').css('z-index',3); */
		/*var hdnPmsdPhenomenaid=jQuery('#hdnPmsdPhenomenaid').val();
		var hdnPmsdCauseid=jQuery('#hdnPmsdCauseid').val();
		var hdnPmsdResultifnotdone=jQuery('#hdnPmsdResultifnotdone').val();
		var hdnPmsdCorrectiveaction=jQuery('#hdnPmsdCorrectiveaction').val();
		var hdnPmsdSafetyinstruction=jQuery('#hdnPmsdSafetyinstruction').val();
		var addGridData=jQuery('#addGridData').val();*/
		
		LoadPopUp("addInfoDiv", "addInfo_input.prv?q=2&hdnPmsdPhenomenaid=hdnPmsdPhenomenaid&hdnPmsdCauseid=hdnPmsdCauseid&hdnPmsdResultifnotdone=hdnPmsdResultifnotdone&hdnPmsdCorrectiveaction=hdnPmsdCorrectiveaction&hdnPmsdSafetyinstruction=hdnPmsdSafetyinstruction&addGridData=addGridData", true,"64%","70%","10%","12%", "", "Additional Infromation");
		//processGridnew("addInfo_input.prv","&q=0","addInfoGrid","pager22","","","","addInfoGrid_loadComplete");
		
	    
	}
	function addInfoDiv_afterClose(){
		return true;
	}
	function closeFlDialog(dlgId)
	{
			 
		  jQuery( '#'+dlgId ).hide();
		 jQuery('#addInfPop').hide();
		 jQuery('#mstfrm_div').removeClass('popup-mask');	
		 jQuery( '#'+dlgId ).removeClass('custom-popup');
	}
	//end of pop up
	//function for subtype button
	function subTypePopOk_Callback(rowIds){
		//alert(rowIds.length);
		var selRowIdArr =rowIds.split(",");
		//var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds);
		//alert(rowObject.txtPmsdActivitysubtypetype);
		var subType = "";
		var subTypeKeyId = "";
		var seprator = ",";
		for(var i=0;i<selRowIdArr.length-1;i++){
			//alert(selRowIdArr[i]);
			var rowObject = jQuery("#multiSelectGrid").getRowData(selRowIdArr[i]);
			jQuery("#multiSelectGrid").getCell(selRowIdArr[i],"my_checkbox","False");
			subType +=rowObject.txtPmsdActivitysub;
			subType	+=seprator;		
			subTypeKeyId+=rowObject.txtPmsdActivitysubtype;
			subTypeKeyId+=seprator;
			
			//alert("type  :"+subType+"----key "+subTypeKeyId);
		}
		
		subType = subType.slice(0,-1);
		jQuery('#txtPmsdActivitysub').val(subType);
		subTypeKeyId =subTypeKeyId.slice(0,-1);
		jQuery('#txtPmsdActivitysubtype').val(subTypeKeyId);
		
		  	//alert(row_Ids.txtPmsdActivitysubtypetype);
	}
			

	//functions for tools pickup
	
	//alert(toolPmsdId);
	function toolgrid(toolPmsdId){
		//alert("tools");
		
		if(toolPmsdId == null && toolPmsdId == " ")
			processGridnew("tool_input.prv","&q=0","tools","toolspager","","","","toolLoadComplete");
		else
			processGridnew("toolmod_input.prv","?q=0&toolPmsdId="+toolPmsdId,"tools","toolspager","","","","toolLoadComplete");	
	}
	//function tools pop up  
	function toolpop(){
		
		//multiSelectPop("tool_pop.mselect","","","","","")
		var tableDatas = jQuery("#tools").jqGrid('getRowData');
		var rowIds = "";
		if(tableDatas!=null && tableDatas[0] != null)
			// alert("tableDatas -"+tableDatas[0].elementid);
		 for(var i=0;i<tableDatas.length;i++){
			 rowIds += tableDatas[i].txtPtldToolid+',';
		 }

		 var colNames = 'txtPtldToolid,displaycode';
		 ToolsTree('tool_tree.prv','?rowIds='+rowIds,'tools','1',colNames,'true');
	}
	//function for spares
	function pm_spares(){
		
		processGridnew("spare_input.prv","?row=0","sparesGrid","pager1","","","","sprLoadComplete");	
		
	}
	
	function toolLoadComplete(id){
		
		var rowid = jQuery("#tools").jqGrid('getDataIDs');
		var toolLength =rowid.length-1; 
		if(rowid.length >0 ){
			jQuery('input:checkbox[name=chkPmsdIstoolsreq]').attr('checked',true);
			jQuery("#tooltree").removeAttr('class', 'disabledButton');
			jQuery("#tooltree").attr('class', ' easyui-button');
			jQuery("#tooltree").attr('disabled',false);
		}
		
	}

	//fumction for spare LoadComplete
	function sprLoadComplete(id){
		
		var rowid = jQuery("#sparesGrid").jqGrid('getDataIDs');
		var sprLength =rowid.length-1; 
		
		if(rowid.length >0 ){
			jQuery('input:checkbox[name=chkPmsdIssparesreq]').attr('checked',true);
			jQuery("#spareBtn").removeAttr('class', 'disabledButton');
			jQuery("#spareBtn").attr('class', ' easyui-button');
			jQuery("#spareBtn").attr('disabled',false);
		}
		
	}
	//fumction for how method button click
	function grdHowmethod_gridComplete(result){
		//alert('s');
		jQuery('#list').setGridParam({cellEdit:false});	
	}
	//fumction for how method button click 
	jQuery("#closebtn").click(function(){
		jQuery( "#howmethodgrid").dialog('close');
		var tableDatas = jQuery("#list").jqGrid('getRowData');
		var rowIds = "";
		jQuery( "#txtPmsdHowmethod").val(tableDatas[0].txtPmmsActivity);
		if(tableDatas==null && tableDatas[0] == null)
			 alert("No Rows Available - ");
	});
	//function for edit row
	jQuery("#edtbtn").click(function(){
		var row_id = jQuery("#list").jqGrid('getGridParam','selrow');
	
		 if( row_id != null ){ 
			 addRow("edit",row_id);
		 }
		 else{
			 alert("Select Row");
		 }
	});
	//function for add row
		jQuery("#addmthd").click(function(id){
			var howMethod = jQuery('#txtPmsdHowmethod').val();
			  //alert("adDDhowMethod  :"+howMethod);
		 addRow("add");
		 });
	//function for clear/delete row

		 jQuery('#clrbtn').click(function (){
			var rowid = jQuery("#list").jqGrid('getGridParam', 'selrow');
				
			});
	function howmethod()
	{
		//alert("howmthd");
		var pmsdkeyID =	jQuery('#pmsdkeyhidn').val();
		if(pmsdkeyID != "" && pmsdkeyID != "undefined" && pmsdkeyID != undefined && pmsdkeyID != null && pmsdkeyID.trim().length>0)
			var dataStr = "pmsdkeyID="+pmsdkeyID;
		else
			dataStr = "pmsdkeyID= ' '";
		
		processGridnew("howmthd_input.prv?"+dataStr,"&q=0","list","pagerHow","","","","grdHowmethod_gridComplete");
		
		//jQuery('#howmethodgrid').css('display','block');
		jQuery( "#howmethodgrid" ).show();
		jQuery( "#howmethodgrid" ).dialog({
			autoOpen: false,
			modal: true,
			height: 380,
			width: 640,	
			top:180	
		});	 
	//jQuery(".window-header").hide()  ; 
	  }
//for close grid
	
	 function howmethodRemove_onSuccess(result)
	 {
	 	
	 	if( result.tpmException != null )
	 	{
	 		showCommonErrorMsg(result.tpmException);
	 	}
	 	else
	 		jQuery("#list").delRowData(howmethodId);	
	 	
	 }
	 function howmethodRemove_onError(status)
	 {
	 	
	 	showCommonErrorMsg(status.error);
	 }
//FUNCTION FOR ADD ROW	 
	function addRow(para,id){
		numericTextBox('txtPmmsDuration');
	  var val=null;
	  var txtHowMethod ;	
	  var txtDuration;
	  var txtInstructions;
	  var prvkeyID =jQuery('#stdhidn').val();
		 if(para=="edit")
			val=id;
		 else if(para=="add")
			val="new";
		jQuery("#list").jqGrid('editGridRow',val,{
			height:200,
			reloadAfterSubmit:true,
			top : 40,
			left:45,
			width:500,
			beforeSubmit:function()
			{
			  var  msg ;	
			  var val;
			  
			   txtHowMethod = jQuery("#txtPmmsActivity").val();	
			   txtDuration  = jQuery("#txtPmmsDuration").val();	
			   txtInstructions= jQuery("#txtPmmsInstructions").val();
	   		   if((txtHowMethod==null||txtHowMethod==""))
				 msg = [false,"Enter Method"];
	   		   else if((txtDuration==null||txtDuration==""))
					 msg = [false,"Enter Duration"];
	   		else if((txtInstructions==null||txtInstructions==""))
				 msg = [false,"Enter Instructions"];
	 		   else
	 			 msg = [true,null];
	 	//jQuery("#list").jqGrid('addRowData',0,[{"hmkeyid":"0","txthowmethod":txtHowMethod,"txtduration":txtDuration }]);
		 	   return  msg; 
			}
		
//	afterSubmit:
	});
//getting rowCount
	//alert(id);
	 var rowCount = jQuery("#list").getGridParam("reccount");
	 var pmmsKeyid = jQuery("#list").jqGrid('getRowData',id);
	 
	 //alert(Object.keys(mlmmKeyid));
	 var tmpMlmKeyid =( pmmsKeyid.txtmethodKeyid ? pmmsKeyid.txtmethodKeyid:null);//alert("tmpMlmKeyid  -"+tmpMlmKeyid);

		 jQuery("#list").jqGrid().setGridParam({editurl :'howmthd_input.prv?prvkeyID='+prvkeyID +'&txtMlmmKeyid='+tmpMlmKeyid},
		 function(response, status,result, xhr)
		  {
			   if (status == "error") 
			  {
			       var msg = "Sorry but there was an error: ";
			       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		      }
			   else{
				   
			   } 
				 
		});	
	
	 
}
	
	jQuery("#btnMultipleResp").click(function(){
		 var flid = jQuery("#frmPmStandardform input[id='flid']").val();	
		 //alert(flid);		
		var dataString='&emrNo=&flid='+flid;
		if (flid != "")		
			//subFormPop("BreakdownMultipleResp_input.brdn",40,80,530,550,"Multiple Responsibility",dataString);
		LoadPopUp("loadMultiple", "BreakdownMultipleResp_input.brdn?"+dataString, true,"550px","600px","-1%","7%", "bdMultiResult_successCallBack","Multiple Responsibilty");
		else
			alert("Select Functional Location");
		
		//jQuery('#hdnlinkMode').val('Cause');			
		//openMasterForm('CauseMst_link.gnms?q=2&&closeOnSave=true',frmMode.create,'frmBDMaster','Breakdown Analysis','mstFrm');
	});
		//function for checkbox selection
		//jQuery(":checkbox").click(chk_select);
function chk_select()
  {

 jQuery("#tooltree").removeAttr('class', 'easyui-button');
 jQuery("#tooltree").attr('class', ' disabledButton');
 jQuery("#tooltree").attr('disabled',false);
   // var toolreq = jQuery('#chkpmsdIstoolsreqchecked').val();		
	  jQuery("#chkPmsdIstoolsreq").click(function(){
 
		if (jQuery("#chkPmsdIstoolsreq").is(":checked"))
		{
			//show the hidden div 
			
			jQuery("#whttools").css('color','#E03D76');
			jQuery("#whttools").css('font-weight','bold');
			jQuery("#tooltree").removeAttr('class', 'disabledButton');
			 jQuery("#tooltree").attr('class', ' easyui-button');
			 jQuery("#tooltree").attr('disabled',false);
			
		}
		else{
			jQuery("#whttools").css('color','#000');
			jQuery("#whttools").css('font-weight','normal');
			jQuery("#tooltree").removeAttr('class', 'easyui-button');
			 jQuery("#tooltree").attr('class', ' disabledButton');
			 jQuery("#tooltree").attr('disabled','disabled');
		     var tooltableDatas = jQuery("#tools").jqGrid('getRowData');
				var pmsdkey  = jQuery('#hdnPmsdkeyid').val();
				if(tooltableDatas.length>0)
				{
					var delSpares = confirm("Already Tools have been Added. Do you want to Cancel them.");
					if (delSpares == true)
					  {
						 processAjaxCalls("tool_delete.prv?",'&pmstdId='+pmsdkey,'deltool_OnSuccess','deltool_OnError');
					  }
					else{
						
					}
					jQuery("#tools").clearGridData();
				}
		     }
	
	  });
  }
function deltool_OnSuccess(result){
	   if(result.successData != ' ' && result.successData != '' && result.successData != null)
		   alert(result.successData);
	   jQuery("#tools").trigger("reloadGrid");
}
//for Spares
jQuery('#spareBtn').click(function(){
	saveForm( 'frmPmStandardform',"prvnt_mntncform_save.prv?&spares=SpaRes");
	
});

//function for spares div show spares
   jQuery("#spares").removeAttr('class', 'easyui-button');
   jQuery("#spares").attr('disabled','disabled');
   
   jQuery("#chkPmsdIssparesreq").click(function(){
	if (jQuery("#chkPmsdIssparesreq").is(":checked"))
	{
		
			jQuery("#spareBtn").removeAttr('class', 'disabledButton');
			jQuery("#spareBtn").attr('class', ' easyui-button');
			jQuery("#spareBtn").attr('disabled',false);
		
		jQuery("#whtspares").css('color','#E03D76');
		var keyId = jQuery('#hdnPmsdkeyId').val();
		//show the hidden div 
		if(keyId != '' && keyId != ' ' && keyId != null){
		jQuery("#whtspares").attr('class','mandatory-lbl');
		}
		  // jQuery('input:checkbox[name=chkPmsdIssparesreq]').attr('checked',false);
		//jQuery("#spares").attr('class', 'easyui-button');
		//jQuery("#spares").removeAttr('disabled');
	}
	else{
		jQuery("#whtspares").css('color','#000');
		var sparetableDatas = jQuery("#sparesGrid").jqGrid('getRowData');
		//alert(sparetableDatas.length);
		var pmsdkey  = jQuery('#hdnPmsdkeyid').val();
		//alert(pmsdkey);
		if(sparetableDatas.length>0)
		{
			var delSpares = confirm("Already Spares have been Added. Do you want to Cancel them.");
			if (delSpares == true)
			  {
				 processAjaxCalls("spare_delete.prv?",'&pmstdId='+pmsdkey,'delSpare_OnSuccess','delSpare_OnError');
			  }
			else{
				jQuery('input:checkbox[name=chkPmsdIssparesreq]').attr('checked',true);
			}
		}
		else{
			jQuery("#spareBtn").removeAttr('class', ' easyui-button');
			jQuery("#spareBtn").attr('class', ' disabledButton');
			jQuery("#spareBtn").attr('disabled','disabled');
		}
	}
	/*else{
		jQuery("#whtspares").removeAttr('class', 'mandatory-lbl');
		navigateToNextForm("preventive_input.prv");
		 //jQuery("#spares").removeAttr('class', 'mandatory-lbl');
	    // jQuery("#spares").attr('disabled','disabled');successData
		}*/
  });
   function delSpare_OnSuccess(result){
	   if(result.successData != ' ' && result.successData != '' && result.successData != null)
		   alert(result.successData);
	   jQuery("#sparesGrid").trigger("reloadGrid");
   }
   function frmPmStandardform_exceptionCallback(result){
	   
   }	
   function frmPmStandardform_successsCallback(result){
	   
	  var frmActType = getFieldValue('cmbPmsdActivitytype','frmPmStandardform');
	  var frmPrepBy = getFieldValue('cmbPmsdPreparedbyid','frmPmStandardform');
	  var machId = jQuery('#hdnMachId').val();
	 /* var factId = jQuery("#frmPmStandardform input[id='factory']").val();
	  var sectionId = jQuery("#frmPmStandardform input[id='section']").val();
	  var cellId = jQuery("#frmPmStandardform input[id='cell']").val();
	  var machineId = jQuery("#frmPmStandardform input[id='machine']").val();*/
	  //editted by Prabhu
	  var factId = jQuery("#frmPmStandardform input[id='factory']").val();
		var sectionId = jQuery("#frmPmStandardform input[id='section']").val();
		var cellId = jQuery("#frmPmStandardform input[id='cell']").val();
		var machineId = jQuery("#frmPmStandardform input[id='machine']").val();
		var flid = jQuery("#frmPmStandardform input[id='flid']").val();
	  setTimeout(function() {
		  clearForm('frmPmStandardform');
		  setFieldValue('cmbPmsdActivitytype',frmActType,'frmPmStandardform');
		  setFieldValue('cmbPmsdPreparedbyid',frmPrepBy,'frmPmStandardform');
		  setFieldValue('cmbPmsdMachineid',machineId,'frmPmStandardform');
		// alert(factId+"   "+sectionId+"   "+cellId+"   "+machineId);
		  jQuery("#frmPmStandardform input[id='factory']").val(factId);
		  jQuery("#frmPmStandardform input[id='section']").val(sectionId);
		  jQuery("#frmPmStandardform input[id='cell']").val(cellId);
		  jQuery("#frmPmStandardform input[id='machine']").val(machineId);
		  jQuery("#frmPmStandardform input[id='flid']").val(flid);
	  },800);
		//alert(Object.keys(result));
		//alert('dfgffd'+result.keyData.redirectSpare);
		 //alert('sdsds'+result.successData.keyData);
	 //alert(result.successData.planData);
	  var ke=jQuery("#hdnPmsdkeyid").val();
	   alert(ke);
		 var pmsdKey = result.keyData.afterSveKey ; 
		 var ifSpares = result.keyData.redirectSpare;
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;
		if(ifSpares != ' ' && ifSpares != '' && ifSpares != null){
			jQuery('#frmPmstdbdr').removeClass('main-cntborder');
			jQuery('#frmPmstdbdr').css('height','0px');
			jQuery('#frmSparesPickup div').removeAttr('id');
			var whymachId = jQuery('#hdnMachine').val();
			//if(whymachId != " " && whymachId !="" && whymachId != null)
				//jQuery('#frmPmstdbdr').css('display','none');
			//navigateToNextForm("Sparepickup_input.sprpckup","Spares Pickup",forwardData,persistentData);
			//alert(persistentData );
			//alert(forwardData);
			navigateToNextForm("Sparepickup_input.sprpckup?q=2&loadContentDivId=LoadSparesfrm&preLoadContentDivId=preloadDIVid5","Spares Pickup",forwardData,persistentData);
		}
		
		else{
			if(result.successData.msg == "No Plan Exists")
				 alert("Data Saved Sucessfully");
			/*To return to why why form*/
			if(result.successData.successData == "bdmmode")
			{
				popFormNavigation();
				navigateToPrevForm();
			}
		}
   }	
   function frmPmStandardform_exceptionCallback(result){
	   jQuery('input:checkbox[name=chkPmsdIssparesreq]').attr('checked',false);
	   }
   
//for save details
function frmPmStandardform_beforeSubmit(){//alert('bfr submit');
	//if(jQuery("#chkClisIstoolsreq").is(":checked")&&)
	var machineId = getFieldValue('cmbPmsdMachineid','frmPmStandardform');
	var locationId = jQuery("#frmPmStandardform input[id='location']").val();
	/*var idealmin=jQuery("#txtPmsdMinValue").val();
	var idealmax=jQuery("#txtPmsdMaxValue").val();
	var target=jQuery("#txtPmsdTarget").val();
	if(target>idealmax){
		
	}*/
	if(locationId.trim().length<=0){
		alert("Select Location");
		return false;
	}
	var url = jQuery('#hiddenUrl').val();
	var hdnpmcalStatus=jQuery('#hdnpmcalStatus').val();
	if(hdnpmcalStatus == 'A')
		{
			if(url.substring(0,url.indexOf('?')) == 'prvnt_mntncform_modify.prv')
				return false;
		}
	//chkplnexist(machineId);
	var activityType = getFieldValue('cmbPmsdActivitytype','frmPmStandardform');
	var tableDatas = jQuery("#tools").jqGrid('getRowData');
	var sparetableDatas = jQuery("#sparesGrid").jqGrid('getRowData');
	var grdDatas   = jQuery("#addInfoGrid").jqGrid('getRowData');
	var assmId = jQuery('#cmbPmsdAssemblyid').combobox("getValue");
	
	var isAddInfoOpen = jQuery('#addInfoDiv').val();
	//alert(activityType +" -- "+assmId.trim().length);
/*	*/
	
	var gridData  = "";
	if(jQuery("#chkPmsdIstoolsreq").is(":checked")&&tableDatas.length==0)
		{
			alert("Select Tools");
			return false;
		}
	else if(jQuery("#chkPmsdIssparesreq").is(":checked")&&sparetableDatas.length==0)
	{
		jQuery('input:checkbox[name=chkPmsdIssparesreq]').attr('checked',false);
		//alert("Select Spares");
		//return false;chkSecurityCheck
	}
	else if(activityType == 'CBM' && assmId.trim().length>0){
		//alert("  else  "+jQuery('#hdnCBMData').val().trim().length );
			if(jQuery('#hdnCBMData').val().trim().length>0)
				gridData +="&cbmData="+jQuery('#hdnCBMData').val();	
		}

	else if(isAddInfoOpen.trim().length>0)
	{
		var cmbPmsdPhenomenaid = jQuery('#cmbPmsdPhenomenaid').combobox("getValue");
		var cmbPmsdCauseid  = jQuery('#cmbPmsdCauseid').combobox("getValue");
		var txtPmsdSafetyinstruction = null;	
		
		var txtPmsdCorrectiveaction = jQuery('#txtPmsdCorrectiveaction').val();
		var txtPmsdResultifnotdone = jQuery('#txtPmsdResultifnotdone').val();
	if(jQuery("#chkSecurityCheck").is(":checked")&&grdDatas.length!=0)
	{
	gridData += '&typeOfWork='+JqGridToJsonSelectdRows('addInfoGrid','select','txtPsplcheckVal','additional_info_frm');
	txtPmsdSafetyinstruction = jQuery('#txtPmsdSafetyinstruction').val();
	if(txtPmsdSafetyinstruction.trim().length>0)
		gridData += '&txtPmsdSafetyinstruction='+txtPmsdSafetyinstruction;
	}
	//alert('sd');
	 if(cmbPmsdPhenomenaid.trim().length>0)
			gridData += '&cmbPmsdPhenomenaid='+cmbPmsdPhenomenaid;
	 if(cmbPmsdCauseid.trim().length>0){//alert('d');
			gridData += '&cmbPmsdCauseid='+cmbPmsdCauseid;
			
	}
	 if(txtPmsdCorrectiveaction.trim().length>0){
			gridData += '&txtPmsdCorrectiveaction='+txtPmsdCorrectiveaction;
	}
	 if(txtPmsdResultifnotdone.trim().length>0){
			gridData += '&txtPmsdResultifnotdone='+txtPmsdResultifnotdone;
	}
	 	
	}
	gridData  += '&multiplemethods='+convertJqGridToJSONObjectArr('list');
	gridData +='&toolsGrid='+convertJqGridToJSONObjectArr('tools');
	
	
		
		 //alert("gridData  "+gridData);
		 return gridData; 
}

 
/***End**/
function frmPmStandardform_FuntLocHierarchy_SuccessCallBack(keyIds)
	{

	//alert(keyIds.machId);
		if( keyIds.cellId != undefined)
			setFieldValue('cmbPmsdCellid',keyIds.cellId,'frmPmStandardform');
		if( keyIds.machId != undefined)
			setFieldValue('cmbPmsdMachineid',keyIds.machId,'frmPmStandardform');
		//fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","cmbpmsdCostCenter");
		fillMachineHierarchy("machineHierarchy.commonFilter",keyIds.machId,"","","","", "","cmbpmsdCostCenter");
		//reloadMachine("frmPmStandardform","cmbPmsdMachineid",keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	}
	function  frmPmStandardformcmbPmsdMachineid_onSelect(record)
	{
		fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbCell","cmbSection","cmbFactory","", "","cmbpmsdCostCenter");
		//reloadCombo("frmPmStandardform","cmbPmsdAssemblyid","assembly.commonFilter?q=2&machineId="+ record.id);
	 loadFunctionalLocation("pmsdMainfunLocation","functionalLoc.prv","pmsdfunLocationValues","frmPmStandardform","&machId="+record.id);
	}
	

function selectData(rowId){
	jQuery("#addInfoGrid").setCell(rowId,"txtPsplcheckVal","1");
}
function unselectData(rowId){
	jQuery("#addInfoGrid").setCell(rowId,"txtPsplcheckVal","0");
}
/*before delete confirmation*/
function frmPmStandardform_beforeDelete(){
	
	var url = jQuery('#hiddenUrl').val();
	var hdnpmcalStatus=jQuery('#hdnpmcalStatus').val();
	if(hdnpmcalStatus == 'A')
		{
			if(url.substring(0,url.indexOf('?')) == 'prvnt_mntncform_modify.prv')
				return false;
		}
	var inactive=confirm("Are you sure to Inactivate the standard!");
	if (inactive==true)
	  {
		  var delstd=confirm("Deleting standard will affect the related transactions in JH Schedule Do you want to proceed?");
			if (delstd==true)
			  {
				alert("Select Date for Inactivation");
				formatDateBox('dtePmsdInactivateddate','dd-MMM-yyyy');
				fillWithCurrentDate('dtePmsdInactivateddate');
				jQuery('#selInactiveDate').css('display','block');
				return false;
			  }
			else{}
	  }
	else
	  {
	  //alert("You pressed Cancel!");
	  }
	  return false;
}
jQuery('#btnCancel').click(function(){
	jQuery('#selInactiveDate').css('display','none');
});
jQuery('#btnOk').click(function(){
	
	var currentDate = new Date();
	var chkDate = getFieldValue('dtePmsdInactivateddate');
	if(convertStringToDate(chkDate) <= currentDate)
	{   
	//alert(currentDate);
	alert("Inactivating Date should be Greater than Current Date");
		
	}
	else{
		var inactivedate =  getFieldValue('dtePmsdInactivateddate');
		processAjaxCalls("jhClitModification_delete.jhclit?",'inactivedate='+inactivedate,'inactive_OnSuccess','inactive_OnError');
		jQuery('#selInactiveDate').css('display','none');
		}
	});
function inactive_OnSuccess(result){
	
	alert("Standard inactivated sucessfully");
	navigateToPrevForm();
	}
jQuery('#btnBack').click(function(){
	
	navigateToPrevForm();
});

 /*
  * For Additional Information
  */ 
