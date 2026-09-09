
	jQuery(document).ready(function(){
		
		
		initialiseForm("frmEqpGroup"); 
		jQuery('#submitForm').val('frmEqpGroup'); // set the id of form to submit
		//for getting values in combobox call the action
		jQuery('.main-cntborder').css('height','825px'); 
		jQuery('#mainEqpGroup').css('display','none');
		var url = jQuery('#hiddenUrl').val();
		toolgrid();//for tool Grid show
		chk_select();//for tool check box 
		pm_spares();//for spares grid
			
		jQuery('#cmbPeqsSupplierid').combobox('disable');
		
		//for getting values in combobox call the action
		 jQuery('#frmEqpGroup .easyui-text').css('text-transform', 'uppercase');
         jQuery('#frmEqpGroup textarea').css('text-transform', 'uppercase');	
         fillComboBox("frmEqpGroup","cmbPeqsEqpgroupid","equipmentgroup.commonFilter");
		 fillComboBox("frmEqpGroup","cmbPeqsAssemblyid","assembly.commonFilter");
		 setFieldValue('cmbPeqsEqpgroupid',jQuery('#hdnEqpGrpKeyId').val(),'frmEqpGroup');	
		 	
		//fillComboBox("frmEqpGroup","cmbPeqsSubassemblyid","Peqs_SubassemblyId.eqpStd");
		
		//fillComboBox("frmEqpGroup","cmbPeqsSubassemblyid","Puns_supplierId.unschedacty");
		
		
	/*---------*/
		numericTextBox('txtPeqsDuration');
		//function for tool  grid
		
	});
	
	function viewGrid(url,filterString,gridName)
	{
			processGridnew(url,"?row=0","sparesGrid","sparesPager");								
	}	
	
	
	function  frmEqpGroupcmbPeqsAssemblyid_onLoadSuccess(){
		
		fillComboBox("frmEqpGroup","cmbPeqsTradeid","combo_PeqsTrade.eqpStd");
		var hdnVal= jQuery('#hdnEqpGrpassId').val();
		if(hdnVal != ' ' && hdnVal != '' && hdnVal != null)
		setFieldValue('cmbPeqsAssemblyid',hdnVal,'frmEqpGroup');
	}
	function  frmEqpGroupcmbPeqsTradeid_onLoadSuccess(){
		
		fillComboBox("frmEqpGroup","cmbPeqsActivitytype","Peqs_Jobtype.eqpStd");
	}
	function  frmEqpGroupcmbPeqsActivitytype_onLoadSuccess(){
		
		fillComboBox("frmEqpGroup","cmbPeqsMachinecond","combo_PeqsMScondition.eqpStd");
	}
	function  frmEqpGroupcmbPeqsMachinecond_onLoadSuccess(){
		
		fillComboBox("frmEqpGroup","cmbPeqsFrequnit","combo_Peqswhtfreq.eqpStd");
	}
	function  frmEqpGroupcmbPeqsFrequnit_onLoadSuccess(){
		fillComboBox("frmEqpGroup","cmbPeqsPreparedbyid","combo_Peqspreparedby.eqpStd");
	}
	function  frmEqpGroupcmbPeqsPreparedbyid_onLoadSuccess(){
	var toolPeqsId = jQuery('#hdnPeqskeyid').val();
	if(toolPeqsId != null && toolPeqsId != " ")
		toolgrid(toolPeqsId);
	else{
		
		toolgrid("");
	}
	}
	function chkplnexist(machineId){
		
		processAjaxCalls("chkPlanExists_input.eqpStd?",'&machineId='+machineId,'chkPlanExists_OnSuccess','chkPlanExists_OnError');
	};
	function chkPlanExists_OnSuccess(result){
		//alert(result.retChkpln);
		//alert("success");
		if(result.retChkpln == '0'){
			alert("No Plan Exists for this Machine");
			jQuery('#hdnPeqsPlanconfigstatus').val('N');
		}
		else
			jQuery('#hdnPeqsPlanconfigstatus').val('Y');
	}
	function chkPlanExists_OnError(result){alert("error");}
	function  frmEqpGroupcmbPeqsFrequnit_onSelect(record)
	{
		var machineId = getFieldValue('cmbPeqsMachineid','frmEqpGroup');
		chkplnexist(machineId);
		
		if(record.id == 'Y'){
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Year(s)");
			}
			else if(record.id == 'M'){
			
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').attr("class","mandatory-lbl");
				jQuery('#lblVal').html("No of Months(s)");
			} 
			else{
				jQuery('#txtPeqsFrequency').attr('disabled','disable');
				jQuery('#lblVal').html(" ");
				jQuery('#lblVal').removeAttr("class","mandatory-lbl");
				jQuery('#lblVal').html("Frequency Unit");
			
	 			}
			}
	
		
	function  frmEqpGroupcmbPeqsAssemblyid_onSelect(record)
	{
		jQuery('#hdnAssmid').val(record.id);
		
	}
	
	/*function  combo_onSelect(){
		
	      jQuery("#cmbPeqsFrequnit").combobox({
	 		onSelect:function(recordid){
	 			
	     });*/
	    
	
	//click functions
	jQuery( "#additional_info" ).click(function() 
	{	
	  /*jQuery("#additional_info_div").load('preventive_addinfo_input.eqpStd', function(response, status, xhr) {
				  if (status == "error") {
				    var msg = "Sorry but there was an error: ";
				    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
				  }
				});*/
				processGridnew("addInfo_input.eqpStd","&q=0","addInfoGrid","pager22","","","","addInfoGrid_loadComplete");	
				fillComboBox("frmEqpGroup","cmbPeqsPhenomenaid","combo_PeqsPhenomenaid.eqpStd");
				fillComboBox("frmEqpGroup","cmbPeqsCauseid","combo_PeqsCauseid.eqpStd");
				 var assmId = jQuery('#hdnAssmid').val();
					//jQuery('#cmbPeqsAssemblyid').combobox("getValue");
				var filassmid = jQuery('#hdnPeqsAssemblyId').val();
				if(assmId != "" && assmId != "undefined" && assmId != undefined && assmId != null && assmId.trim().length>0)
				{
	 	 			//LoadForm('additional_info_div','preloadDIVid1','preventive_addinfo_input.eqpStd','dispErr','','addInfo_errorCallBack');
					jQuery( "#additional_info_frm" ).css('display','block');
//					jQuery( "#additional_info_frm" ).dialog( {autoOpen: false,
//						modal: true,
//						height: 450,
//						width: 900
//						});
					}
				else if(filassmid != "" && filassmid != "undefined" && filassmid != undefined && filassmid != null && filassmid.trim().length>0)
				{
	 	 		//	LoadForm('additional_info_div','preloadDIVid1','preventive_addinfo_input.eqpStd','dispErr','','addInfo_errorCallBack');
					jQuery( "#additional_info_frm" ).css('display','block');
//					jQuery( "#additional_info_frm" ).dialog( {autoOpen: false,
//						modal: true,
//						height: 400,
//						width: 900
//						});
					}
				else{
					alert('Select Assembly');
					}
						return false;
					});
	jQuery('#btnClose').click(function(){
		jQuery( "#additional_info_frm" ).css('display','none');
	});
				
	jQuery( "#rsrc_pln" ).click(function() 
	{
		LoadForm('rsrc_pln_div','preloadDIVid2','eqpStdnt-mntnc-rsrc_input.eqpStd','dispErr','','resplan_errorCallBack');
		jQuery( "#rsrc_pln_frm" ).dialog( {autoOpen: false,
				modal: true,
				height: 450,
				width: 900});
		return false;
	});	
	
	//function for check one select and deselect the other check box						
		jQuery('#chkPeqsSource').click(function(){
			jQuery('input:checkbox[name=ch1PeqsSource]').attr('checked',false);
			jQuery('input:checkbox[name=chkPeqsSource]').attr('checked',true);
			jQuery('#cmbPeqsSupplierid').combobox('disable');
			});
		jQuery('input:checkbox[name=ch1PeqsSource]').click(function(){		
			 jQuery('input:checkbox[name=chkPeqsSource]').attr('checked',false);
			 jQuery('input:checkbox[name=ch1PeqsSource]').attr('checked',true);
			 jQuery('#cmbPeqsSupplierid').combobox('enable');
		});
	//function for combobox getvalue
	jQuery('#filemanager').click(function(){
		var vale = jQuery('#cmbPeqsFrequnit').find('option:selected').text();
		alert("jjj- "+vale);
	     var val=jQuery('#cmbPeqsFrequnit').combobox('getValue');
	     alert("val   -"+ val);
		
	});
	jQuery('#subtypebtn').click(function(){
		multiSelectPop("subType_input.eqpStd","","","","","true","","subTypePopOk_Callback");	
		
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
	//function for subtype button
	function subTypePopOk_Callback(rowIds){
		//alert(rowIds.length);
		var selRowIdArr =rowIds.split(",");
		//var rowObject = jQuery("#multiSelectGrid").getRowData(rowIds);
		//alert(rowObject.txtPeqsActsubtypetypetype);
		var subType = "";
		var subTypeKeyId = "";
		var seprator = ",";
		for(var i=0;i<selRowIdArr.length-1;i++){
			//alert(selRowIdArr[i]);
			var rowObject = jQuery("#multiSelectGrid").getRowData(selRowIdArr[i]);
			jQuery("#multiSelectGrid").getCell(selRowIdArr[i],"my_checkbox","False");
			subType +=rowObject.txtPeqsActsubtype;
			subType	+=seprator;		
			subTypeKeyId+=rowObject.txtPeqsActsubtypetype;
			subTypeKeyId+=seprator;
			
			//alert("type  :"+subType+"----key "+subTypeKeyId);
		}
		
		subType = subType.slice(0,-1);
		jQuery('#txtPeqsActsubtype').val(subType);
		subTypeKeyId =subTypeKeyId.slice(0,-1);
		jQuery('#txtPeqsActsubtypetype').val(subTypeKeyId);
		
		  	//alert(row_Ids.txtPeqsActsubtypetypetype);
	}
			

	//functions for tools pickup
	
	//alert(toolPeqsId);
	function toolgrid(toolPeqsId){
		//alert("tools");
		
		if(toolPeqsId == null && toolPeqsId == " ")
			processGridnew("tool_input.eqpStd","&q=0","tools","toolspager","","");
		else
			processGridnew("toolmod_input.eqpStd","?q=0&toolPeqsId="+toolPeqsId,"tools","toolspager","","");	
	}
	//function tools pop up  
	function toolpop(){
		
		//multiSelectPop("tool_pop.mselect","","","","","")
		var tableDatas = jQuery("#tools").jqGrid('getRowData');
		var rowIds = "";
		if(tableDatas!=null && tableDatas[0] != null)
			// alert("tableDatas -"+tableDatas[0].elementid);
		 for(var i=0;i<tableDatas.length;i++){
			 rowIds += tableDatas[i].txtPeqtToolid+',';
		 }

		 var colNames = 'txtPeqtToolid,displaycode';
		 ToolsTree('tool_tree.eqpStd','?rowIds='+rowIds,'tools','1',colNames,'true');
	}
	//function for spares
	function pm_spares(){
		
		processGridnew("spare_input.eqpStd","?row=0","sparesGrid","pager1","","");	
		
	}
	//fumction for how method button click
	function grdHowmethod_gridComplete(result){
		//alert('s');
		jQuery('#list').setGridParam({cellEdit:false});	
	}
	function howmethodEqp()
	{
		//alert("howmthd");
		var peqskeyID =	jQuery('#txtPeqsKeyid').val();
		var peqskeyID =	jQuery('#pmsdkeyhidn').val();
		if(peqskeyID != "" && peqskeyID != "undefined" && peqskeyID != undefined && peqskeyID != null && peqskeyID.trim().length>0)
			var dataStr = "peqskeyID="+peqskeyID;
		else
			dataStr = "peqskeyID= ' '";
		processGridnew("howmthd_input.eqpStd?peqskeyID="+peqskeyID,"&q=0","list","pager","","","","grdHowmethod_gridComplete");
		//jQuery('#howmethodgrid').css('display','block');
		jQuery( "#howmethodEqpgrid" ).show();
		jQuery( "#howmethodEqpgrid" ).dialog({
			autoOpen: false,
			modal: true,
			height: 380,
			width: 640,	
			top:180	
		});	 
		
	  }
//for close grid
	jQuery("#closebtn").click(function(){
		var tableDatas = jQuery("#list").jqGrid('getRowData');
		var rowIds = "";
		jQuery( "#txtPeqsHowmethod").val(tableDatas[0].txtPeqmActivity);
		if(tableDatas==null && tableDatas[0] == null)
			 alert("No Rows Available - ");
		
		jQuery( "#howmethodEqpgrid").dialog('close');
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
		var howmethodEqp = jQuery('#txtPeqsHowmethod').val();
		 // alert("adDDhowMethod  :"+howMethod);
	 addRow("add");
	 });
	 
//function for clear/delete row

	 jQuery('#clrbtn').click(function (){
		var rowid = jQuery("#list").jqGrid('getGridParam', 'selrow');
		//alert("rowid="+rowid);
		jQuery("#list").delRowData(rowid);
		});
	 function howmethodEqpRemove_onSuccess(result)
	 {
	 	
	 	if( result.tpmException != null )
	 	{
	 		showCommonErrorMsg(result.tpmException);
	 	}
	 	else
	 		jQuery("#list").delRowData(howmethodEqpId);	
	 	
	 }
	 function howmethodEqpRemove_onError(status)
	 {
	 	alert(Object.keys(status));
	 	alert(status.responseText);
	 	showCommonErrorMsg(status.error);
	 }
//FUNCTION FOR ADD ROW	 
	function addRow(para,id){
		
	  var val=null;
	  var txthowmethodEqp ;	
	  var txtDuration ;
	  var peqskeyID =jQuery('#txtPeqskeyhidn').val();
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
			  
			   txthowmethodEqp = jQuery("#txtPeqmActivity").val();	
			   txtDurationEqp     = jQuery("#txtPeqmDuration").val();	
	   		   if((txthowmethodEqp==null||txthowmethodEqp==""))
				 msg = [false,"Enter Method"];
	   		   else if((txtDurationEqp==null||txtDurationEqp==""))
				 msg = [false,"Enter Duration"];
		 	   else
	 			 msg = [true,null];
	 	         //jQuery("#list").jqGrid('addRowData',0,[{"hmkeyid":"0","txthowmethodEqp":txthowmethodEqp,"txtduration":txtDuration }]);
		 	   return  msg; 
			}
		
//	afterSubmit:
	});
//getting rowCount
	//alert(id);
  	 var rowCount = jQuery("#list").getGridParam("reccount");
	 var peqmKeyid = jQuery("#list").jqGrid('getRowData',id);
	// alert("peqsKeyid -"+peqsKeyid.txtmethodKeyid+"--"+peqsKeyid);
	 //alert(Object.keys(peqsKeyid));
	 var tmpPemKeyid =( peqmKeyid.txtmethodKeyid ? peqmKeyid.txtmethodKeyid:null);//alert("tmpPemKeyid  -"+tmpPemKeyid);

		 jQuery("#list").jqGrid().setGridParam({editurl :'howmthd_input.eqpStd?peqskeyID='+peqskeyID +'&txtPeqmKeyid='+tmpPemKeyid},
		 function(response, status,result, xhr)
		  {
			   if (status == "error") 
			  {
			       var msg = "Sorry but there was an error: ";
			       jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
		      }
			   else 
				 	alert(Object.keys(result));
			   		alert("response="+response);
		   			alert("response="+responseText);
		});	
	
	 
  }
		//function for checkbox selection
		//jQuery(":checkbox").click(chk_select);
function chk_select()
  {
 jQuery("#tooltree").removeAttr('class', 'easyui-button');
    jQuery("#tooltree").attr('disabled','disabled');
   // var toolreq = jQuery('#chkPeqsIstoolsreqchecked').val();		
	  jQuery("#chkPeqsIstoolsreq").click(function(){
		if (jQuery("#chkPeqsIstoolsreq").is(":checked"))
		{
			//show the hidden div 
			
			jQuery("#whttools").css('color','#39138A');
			jQuery("#whttools").css('font-weight','bold');
			jQuery("#tooltree").attr('class', 'easyui-button');
			jQuery("#tooltree").removeAttr('disabled');
		}
		else{
			jQuery("#whttools").css('color','#000');
			jQuery("#whttools").css('font-weight','normal');
			 jQuery("#tooltree").removeAttr('class', 'easyui-button');
		     jQuery("#tooltree").attr('disabled','disabled');
		     jQuery("#tools").clearGridData();
			}
	
	  });
  }
//for Spares
//function for spares div show spares
   jQuery("#spares").removeAttr('class', 'easyui-button');
   jQuery("#spares").attr('disabled','disabled');
   jQuery("#chkPeqsIssparesreq").click(function(){
	if (jQuery("#chkPeqsIssparesreq").is(":checked"))
	{
		var keyId = jQuery('#hdnPeqskeyId').val();
		//show the hidden div 
		if(keyId != '' && keyId != ' ' && keyId != null){
		jQuery("#whtspares").attr('class','mandatory-lbl');
		
		}
		else
			saveForm( 'frmEqpGroup',"eqpStdnt_mntncform_input.eqpStd?&spares=SpaRes");
			//alert("can be viewed only after saving pmstandard");
		  // jQuery('input:checkbox[name=chkPeqsIssparesreq]').attr('checked',false);
		//jQuery("#spares").attr('class', 'easyui-button');
		//jQuery("#spares").removeAttr('disabled');
	}
	/*else{
		jQuery("#whtspares").removeAttr('class', 'mandatory-lbl');
		navigateToNextForm("preventive_input.eqpStd");
		 //jQuery("#spares").removeAttr('class', 'mandatory-lbl');
	    // jQuery("#spares").attr('disabled','disabled');
		}*/
  });
				
   function frmEqpGroup_successsCallback(result){
		//alert(Object.keys(result));
		//alert('dfgffd'+result.keyData.redirectSpare);
		 //alert('sdsds'+result.successData.keyData);
	   
		 var PeqsKey = result.keyData.afterSveKey ; 
		 var ifSpares = result.keyData.redirectSpare;
		 var persistentData = result.successData.persistentData;
		 var forwardData = result.successData.forwardData;
		if(ifSpares != ' ' && ifSpares != '' && ifSpares != null){
			jQuery('#frmPmstdbdr').removeClass('main-cntborder');
			jQuery('#frmPmstdbdr').css('height','0px');
			jQuery('#frmSparesPickup div').removeAttr('id');
			//navigateToNextForm("Sparepickup_input.sprpckup","Spares Pickup",forwardData,persistentData);
			navigateToNextForm("Sparepickup_input.sprpckup?q=2loadContentDivId=LoadSparesfrm&preLoadContentDivId=preloadDIVid5","Spares Pickup",forwardData,persistentData);
		}
		/*To return to why why form*/
		if(result.successData.successData == "bdmmode")
		{
			popFormNavigation();
			navigateToPrevForm();
		}
   }	
   function frmEqpGroup_exceptionCallback(result){
	   jQuery('input:checkbox[name=chkPeqsIssparesreq]').attr('checked',false);
	   }
//for save details
   function frmEqpGroup_beforeSubmit(){//alert('bfr submit');
	//if(jQuery("#chkClisIstoolsreq").is(":checked")&&)
	
	var tableDatas = jQuery("#tools").jqGrid('getRowData');
	var grdDatas   = jQuery("#addInfoGrid").jqGrid('getRowData');
	var gridData  = "";
	if(jQuery("#chkPeqsIstoolsreq").is(":checked")&&tableDatas.length==0)
		{
			alert("Select TOOLS");
			return false;
		}
		
		/*if(jQuery("#chkSecurityCheck").is(":checked")&&grdDatas.length!=0)
		{
		gridData += '&typeOfWork='+JqGridToJsonSelectdRows('addInfoGrid','select','txtPsplcheckVal','additional_info_frm');
		var cmbPeqsPhenomenaid = jQuery('#cmbPeqsPhenomenaid').combobox("getValue");
		var cmbPeqsCauseid  = jQuery('#cmbPeqsCauseid').combobox("getValue");
		var txtPmmsWorkpermitrequired = jQuery('#txtPmmsWorkpermitrequired').val();
		var txtPeqsCorrectiveaction = jQuery('#txtPeqsCorrectiveaction').val();
		var txtPeqsResultifnotdone = jQuery('#txtPeqsResultifnotdone').val();
		if(cmbPeqsPhenomenaid != ' ' && cmbPeqsPhenomenaid != '' && cmbPeqsPhenomenaid != null)
		gridData += '&cmbPeqsPhenomenaid='+cmbPeqsPhenomenaid;
		if(cmbPeqsCauseid != ' ' && cmbPeqsCauseid!= '' && cmbPeqsCauseid!= null)
		gridData += '&cmbPeqsCauseid='+cmbPeqsCauseid;
		if(txtPmmsWorkpermitrequired != ' ' && txtPmmsWorkpermitrequired!= '' && txtPmmsWorkpermitrequired!= null)
		gridData += '&txtPmmsWorkpermitrequired='+txtPmmsWorkpermitrequired;
		if(txtPeqsCorrectiveaction != ' ' && txtPeqsCorrectiveaction!= '' && txtPeqsCorrectiveaction!= null)
		gridData += '&txtPeqsCorrectiveaction='+txtPeqsCorrectiveaction;
		if(txtPeqsResultifnotdone != ' ' && txtPeqsResultifnotdone!= '' && txtPeqsResultifnotdone!= null)
		gridData += '&txtPeqsResultifnotdone='+txtPeqsResultifnotdone;
		}*/
		else{
		gridData  = '&multiplemethods='+convertJqGridToJSONObjectArr('list');
		gridData +='&toolsGrid='+convertJqGridToJSONObjectArr('tools');
		}
		//alert(gridData);
		return gridData; 
}

   
   function frmEqpGroup_exceptionCallback(result){
	   jQuery(".main-cntborder").css('height','945');   
   }
/***End**/
	function frmEqpGroup_FuntLocHierarchy_SuccessCallBack(keyIds)
	{
		
		setFieldValue('cmbPeqsCellid',keyIds.cellId,'frmEqpGroup');
		setFieldValue('cmbPeqsMachineid',keyIds.machId,'frmEqpGroup');
		//reloadMachine("frmEqpGroup","cmbPeqsMachineid",keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	
	}
	function  frmEqpGroupcmbPeqsMachineid_onSelect(record)
	{
		fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmbCell","cmbSection","cmbFactory","", "","cmbPeqsCostCenter");
		reloadCombo("frmEqpGroup","cmbPeqsAssemblyid","assembly.commonFilter?q=2&machineId="+ record.id);
	 loadFunctionalLocation("PeqsMainfunLocation","functionalLoc.eqpStd","PeqsfunLocationValues","frmEqpGroup","&machId="+record.id);
	}
	
		function cboxSprFormatter(id, options, rowObject)
			{
				var id = options.rowId;
			  	//return '<input  type="checkbox" id="addinfo_checkbox" name="addinfo_checkbox" + (rowObject[1]=="True" ? 'checked':'') +'"' onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
			  	return '<input id="addinfo_checkbox_'+ id +'" name ="addinfo_checkbox_'+ id +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + ' onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+ id +'\')}"/>';
			}
		function selectData(rowId){
			jQuery("#addInfoGrid").setCell(rowId,"txtPsplcheckVal","1");
		}
		function unselectData(rowId){
			jQuery("#addInfoGrid").setCell(rowId,"txtPsplcheckVal","0");
		}
		/*before delete confirmation*/
		function frmEqpGroup_beforeDelete(){
			var inactive=confirm("Are you sure to Inactivate the standard!");
			if (inactive==true)
			  {
				  var delstd=confirm("Deleting standard will affect the related transactions in JH Schedule Do you want to proceed?");
					if (delstd==true)
					  {
						alert("Select Date for Inactivation");
						formatDateBox('dtePeqsInactivateddate','dd-MMM-yyyy');
						fillWithCurrentDate('dtePeqsInactivateddate');
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
		jQuery('#btnOk').click(function(){
			
			var currentDate = new Date();
			var chkDate = getFieldValue('dtePeqsInactivateddate');
			if(convertStringToDate(chkDate) <= currentDate)
			{   
			//alert(currentDate);
			alert("Inactivating Date should be Greater than Current Date");
				
			}
			else{
				var inactivedate =  getFieldValue('dtePeqsInactivateddate');
				processAjaxCalls("jhClitModification_delete.eqpStd?",'inactivedate='+inactivedate,'inactive_OnSuccess','inactive_OnError');
				jQuery('#selInactiveDate').css('display','none');
				}
			});
		function inactive_OnSuccess(){
			alert("Standard inactivated sucessfully");
			navigateToPrevForm();
			}
		jQuery('#btnBack').click(function(){
			
			navigateToPrevForm();
		});
		
		 /*
		  * For Additional Information
		  */ 
		function addInfoGrid_loadComplete(){addInfoGrid_chkdisable(0);}
		jQuery('#chkSecurityCheck').click(function(){
		
			if(jQuery('#chkSecurityCheck').is(':checked') == true)
			{
				jQuery('#txtPmmsWorkpermitrequired').attr('disabled',false);
				jQuery('#addifogrddiv').attr('disabled', 'disabled');
				addInfoGrid_chkdisable('1');
				//disableField('frmAddInfo','txtPeqsSafetyinstruction');
			}
			else{
				jQuery('#txtPmmsWorkpermitrequired').attr('disabled',true);
				jQuery('#addifogrddiv').attr('disabled', '');
				addInfoGrid_chkdisable(0);
			}
		});
		function addInfoGrid_chkdisable(mode){		
			/*jQuery("#jqgh_addInfoGrid_select").click(function()
					{*/	
								//alert("mode" +mode);
								var allRows = jQuery("#addInfoGrid").jqGrid('getRowData');
								if(mode == 0)
								{			
									for(i= 0;i<=allRows.length;i++)
									{
										jQuery('input:checkbox[name=addinfo_checkbox_'+i+']').attr('disabled',true);
									 }
								}
								else
								{
									for(i= 0;i<=allRows.length;i++)
									{
										jQuery('input:checkbox[name=addinfo_checkbox_'+i+']').attr('disabled',false);
										mode = 0;
									}						
								}				
							
						
					//});		
				}
jQuery('#btneqpmnt').click(function(){
	processGridnew("equipmentGridPop_input.eqpStd","&q=0","equipmentGridPop","pager_equip","","","","equipmentGridPop_loadComplete");
		
	jQuery( "#equipmnt_frm" ).css('display','block');
	
});
jQuery('#btnCloseeqp').click(function(){
	
	jQuery( "#equipmnt_frm" ).css('display','none');
});
jQuery('#clsebtn').click(function(){
	jQuery( "#equipmnt_frm" ).css('display','none');
});

function chkboxFormater(id, options, rowObject)
{	
	var rowId = options.rowId;	
	return '<input id="equipment_checkbox" name="equipment_checkbox" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}
function chkboxCheck(rowId)
{
	jQuery("#equipmentGridPop").jqGrid('setCell',rowId,'checkvalue','1');		
}
function chkboxUnCheck(rowId){
	jQuery("#equipmentGridPop").jqGrid('setCell',rowId,'checkvalue','0');	
}