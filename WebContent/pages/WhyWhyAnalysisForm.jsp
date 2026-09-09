<script type="text/javascript">	
jQuery(document).ready(function(){
	//alert(12365);
	initialiseForm('frmWhywhyAnalysis1');
	jQuery('#submitForm').val('frmWhywhyAnalysis1');
	setTimeout(function() {fillComboBox("frmWhywhyAnalysis1","cmbWwmsMachineid","machineCombo.commonFilter" );},1250);	
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsKeyid","combo_yy.why");
	formatDateBox('dteWwmsDate','dd-MMM-yyyy');
	formatDateBox('dteWwmsPrevdate','dd-MMM-yyyy');
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsPrevperson","employee.commonFilter");
	jQuery('#frmWhywhyAnalysis1 .easyui-text').css('text-transform', 'uppercase');
	jQuery('#frmWhywhyAnalysis1 textarea').css('text-transform', 'uppercase');

	 var factId = jQuery("#frmWhywhyAnalysis1 input[id='factory']").val();
	 var sectionId = jQuery("#frmWhywhyAnalysis1 input[id='section']").val();
	 var cellId = jQuery("#frmWhywhyAnalysis1 input[id='cell']").val();
	 var machId = jQuery("#frmWhywhyAnalysis1 input[id='machine']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	
	 setTimeout(function() {loadFunctionalLocation("wwmsfunLocation","functionalLoc.why","wwmsfunLocationValues","frmWhywhyAnalysis1",dataStr);},1250);  					


		
	 if(jQuery('#hdnformType').val() != null && jQuery('#hdnformType').val() != '')
	 {	
		 if(jQuery('#hdnformType').val() == "BD")
		 {
			 jQuery('#sheYYFields').css('display','none');
			 jQuery('#sheprevDetails').css('display','none');			 
			 jQuery('#YYGridForKaizen').css('display','none');
			 jQuery('#rootCauseinKaizen').css('display','none');
			 jQuery('#CounterMeasureInKaizen').css('display','none');
			 jQuery('#lbl4mType').css('display','none');
			 jQuery('#fourMSpn').css('display','none');
			 jQuery('#ActionTakenInShe').css('display','none');
			 fillComboBox("frmWhywhyAnalysis1","cmbWwmsAssemblyid","assembly.commonFilter");

			/* if(jQuery('#hdnformType').val() == "CC")
			 {
				 jQuery('#lblHD').css('display','none');
				 jQuery('#chbWwmsIshdpossible').css('display','none');
				 jQuery('#txtWwmsPreveffectiveness').css('display','none');	
				 jQuery('#chkWwmsSparesreplaced').css('display','none');
				 jQuery('#lblsprReplaced').css('display','none');
				 jQuery('#lblsprNotReplaced').css('display','none');
				 jQuery('#sparesReplaced').css('display','none');
				 jQuery('#dateCol').css('display','none');
				 jQuery('#lblChecksMade').css('display','none');
				 jQuery('#lblYYNotOk').css('display','none');
				// jQuery('#lblYY').css('display','none');
				// jQuery('#txaWwmsFinalaction').css('display','none');
				 jQuery('#lblCustCompFields').css('display','block');
				 jQuery('#custCompFields').css('display','block');
				 jQuery('#customerComplaint').css('display','block');
				 jQuery('#kaizenDetailsInBD').css('display','none');
				 jQuery('.yy-completeYYBtn').css('margin-left','500px');
				 
			 }*/
			
				
			 setTimeout(function() {checkBoxSel();},1250);
			 setTimeout(function() {disableFieldsInYY();},1250);
			 var dataString = '?q=2&wwmsKey='+jQuery("#cmbWwmsKeyid").combobox('getValue')+'&finalAction='+jQuery("#txaWwmsFinalaction").val();
		
			 processGridnew('yy_view.why',dataString,"yyGrid","","","","","yyOnLoad","");
			 processGridnew('rootcause_view.why','?q=2',"rootPillarGrid","","","","","rootCauseOnLoad","");
			 processGridnew('pillar_view.why',dataString,"PillarGrid","","","","","pillarOnLoad","");
		 }
		 else if(jQuery('#hdnformType').val() == "CC" || jQuery('#hdnformType').val() == "DOCK" || jQuery('#hdnformType').val() == "IMT")
		 {

			 
			 jQuery('#sheYYFields').css('display','none');
			 jQuery('#sheprevDetails').css('display','none');	
			 jQuery('#YYGridForKaizen').css('display','none');
			 jQuery('#rootCauseinKaizen').css('display','none');
			 jQuery('#CounterMeasureInKaizen').css('display','none');
		
			 fillComboBox("frmWhywhyAnalysis1","cmbWwmsAssemblyid","assembly.commonFilter");
			 jQuery('#lblHD').css('display','none');
			 jQuery('#chbWwmsIshdpossible').css('display','none');
				 jQuery('#txtWwmsPreveffectiveness').css('display','none');	
				 jQuery('#chkWwmsSparesreplaced').css('display','none');
				 jQuery('#lblsprReplaced').css('display','none');
				 jQuery('#lblsprNotReplaced').css('display','none');
				 jQuery('#sparesReplaced').css('display','none');
				 jQuery('#dateCol').css('display','none');
				 jQuery('#lblChecksMade').css('display','none');
				 jQuery('#lblYYNotOk').css('display','none');
				 jQuery('#ActionTakenInShe').css('display','none');
				// jQuery('#lblYY').css('display','none');
				// jQuery('#txaWwmsFinalaction').css('display','none');
				 jQuery('#lblCustCompFields').css('display','block');
				 jQuery('#custCompFields').css('display','block');
				 jQuery('#customerComplaint').css('display','block');
				 jQuery('#kaizenDetailsInBD').css('display','none');
				 jQuery('.yy-completeYYBtn').css('margin-left','500px');
			 setTimeout(function() {checkBoxSelIsKzn();},1250);
			 setTimeout(function() {checkBoxSelIsPY();},1250);
			 setTimeout(function() {disableFieldsInCC();},1250);
			
			 if(jQuery('#hdnformType').val() == "IMT")
				 {
				 setTimeout(function() {readOnlyFields('cmbfourMType');},1250);
				 setTimeout(function() {readOnlyFields('cmbWwmsAssemblyid');},1250);
				 }
			 var dataString = '?q=2&wwmsKey='+jQuery("#cmbWwmsKeyid").combobox('getValue')+'&finalAction='+jQuery("#txaWwmsFinalaction").val()+'&yyOpenFrom='+jQuery('#hdnformType').val();
		
			 processGridnew('yy_view.why',dataString,"yyGrid","","","","","yyOnLoad","");
			 processGridnew('rootcause_view.why','?q=2',"rootPillarGrid","","","","","rootCauseOnLoad","");
			 processGridnew('pillar_view.why',dataString,"PillarGrid","","","","","pillarOnLoad","");
			 jQuery("#txaWwmsFinalaction").change(function(){
				  jQuery("#yyGrid").jqGrid("setCell", 1, 'txtWwdtWhy',  jQuery("#txaWwmsFinalaction").val().toUpperCase());
			 });
		 }
		 else if(jQuery('#hdnformType').val() == "SHE")
		 {
			 jQuery('#wrapper').css('padding-left','70px');
			 formatDateBox('dteWwmsPrevdat','dd-MMM-yyyy'); 
			// fillComboBox("frmWhywhyAnalysis1","cmbWwmsPrevperson","employee.commonFilter");
			 jQuery('#chkWwmsSparesreplaced').css('display','none');
			 jQuery('#lblsprReplaced').css('display','none');
			 jQuery('#lblsprNotReplaced').css('display','none');
			 jQuery('#sparesReplaced').css('display','none');
			 jQuery('#yyDate').css('display','none'); 
			 /*
		 	 jQuery('#lblEqp').css('display','none');
		 	 jQuery('#lblAsm').css('display','none');
			 */
		 	 jQuery('#eqpStatPhenCause').css('display','none');
			 jQuery('#faPrevHD').css('display','none');
			 jQuery('#kaizenDetailsInBD').css('display','none');
			 jQuery('#CounterMeasureInKaizen').css('display','none');
			 if(screen.width >= 1250)		
			 {	 
			 	jQuery('.yy-rootCauseTD').css('width','40%');
			 	jQuery('#counterMsrGridHdr').css('width','425');
			 	jQuery('#ActionTakenInShe').css('vertical-align','-25');
			 }
			 else
			 {
				 jQuery('.yy-rootCauseTD').css('padding-left','0');
				 jQuery('.yy-rootCauseTD').css('width','50%');
				 jQuery('#counterMsrGridHdr').css('width','390');	
				 jQuery('#ActionTakenInShe').css('vertical-align','-25');		 		 
			 }
			 setTimeout(function() {disableFieldsInSheYY();},1250);
			 checkBoxSelIsEffective();
			 
			 var dataString = '?q=2&wwmsKey='+jQuery("#cmbWwmsKeyid").combobox('getValue')+'&phenomena='+jQuery("#txtWwmsAccidentphen").val();
			 processGridnew('yyShe_view.why',dataString,"yyGrid","","","","","yySheOnLoad","");
			 processGridnew('rootcause_view.why','?q=2&openMode=SFT',"rootPillarGrid","","","","","rootCauseOnLoad","");
			 processGridnew('pillar_view.why',dataString+'&yyOpenFrom=SHE',"PillarGrid","","","","","pillarOnLoad","");
		 }
		 else if(jQuery('#hdnformType').val() == "WO")
		 {
			 jQuery('#sheYYFields').css('display','none');
			 jQuery('#sheprevDetails').css('display','none');	
			 jQuery('#YYGridForKaizen').css('display','none');
			 jQuery('#rootCauseinKaizen').css('display','none');
			 jQuery('#CounterMeasureInKaizen').css('display','none');
			 jQuery('#ActionTakenInShe').css('display','none');
			 fillComboBox("frmWhywhyAnalysis1","cmbWwmsAssemblyid","assembly.commonFilter");	
			 setTimeout(function() {checkBoxSel();},1250);
			 setTimeout(function() {disableFieldsInYY();},1250);
			 var dataString = '?q=2&wwmsKey='+jQuery("#cmbWwmsKeyid").combobox('getValue')+'&finalAction='+jQuery("#txaWwmsFinalaction").val();
			
			 processGridnew('yy_view.why',dataString,"yyGrid","yyPager","","","","yyOnLoad","");
			 processGridnew('rootcause_view.why','?q=2',"rootPillarGrid","rootPillarPager","","","","rootCauseOnLoad","");
		 }
		 else if(jQuery('#hdnformType').val() == "KAIZEN" || jQuery('#hdnformType').val() == "ABN" || jQuery('#hdnformType').val() == "GM")
		 {
			 jQuery('#sheYYFields').css('display','none');
			 jQuery('#sheprevDetails').css('display','none');	
			 setTimeout(function() {disableFieldsInKaizen();},1250);
			 jQuery('#chkWwmsSparesreplaced').css('display','none');
			 jQuery('#lblsprReplaced').css('display','none');
			 jQuery('#lblsprNotReplaced').css('display','none');
			 jQuery('#sparesReplaced').css('display','none');
			 jQuery('#lblAsm').css('display','none');
			 jQuery('#lblPhn').css('display','none');
			 jQuery('#lblCause').css('display','none');
			 jQuery('#asmPhnCause').css('display','none');			 
			 jQuery('#lblChecksMade').css('display','none');
			 jQuery('#lblYouDidNot').css('display','none');	
			 jQuery('#lblSustAction').css('display','none');
			 jQuery('#txtWwmsChecksmade').css('display','none');
			 jQuery('#txtWwmsYoudidnot').css('display','none');
			 jQuery('#lblYYNotOk').css('display','none');
			 jQuery('#lblHD').css('display','none');
			 jQuery('#chbWwmsIshdpossible').css('display','none');
			 jQuery('#lbl4mType').css('display','none');
			 jQuery('#fourMSpn').css('display','none');
			 jQuery('#ActionTakenInShe').css('display','none');
			 
			 jQuery('#dateCol').css('display','none');
			 jQuery('#txtWwmsPreveffectiveness').css('display','none');	
			 jQuery('#lblYY').html('First Why');
			 jQuery('#YYGridForBD').css('display','none');
			 jQuery('#rootCauseGridinBD').css('display','none');
			 jQuery('#kaizenDetailsInBD').css('display','none');
			 jQuery('#pillarGridinBD').css('display','none');
			 jQuery('.yy-completeYYBtn').css('margin-left','300px');
			 if(jQuery('#hdnformType').val() == "ABN")
			 {
				 jQuery('#rootCauseinKaizen').css('display','none');
				 jQuery('#tempforAbn').html( jQuery('#CounterMeasureInKaizen').html());
				 jQuery('#CounterMeasureInKaizen').css('display','none');
			 }
			 var dataString = '?q=2&wwmsKey='+jQuery("#cmbWwmsKeyid").combobox('getValue')+'&finalAction='+jQuery("#txaWwmsFinalaction").val();
			 processGridnew('yykaizen_view.why',dataString,"yyKaizenGrid","","","","","yyKaizenOnLoad","");
			 jQuery("#txaWwmsFinalaction").change(function(){
				  jQuery("#yyKaizenGrid").jqGrid("setCell", 1, 'txtWwdtWhy',  jQuery("#txaWwmsFinalaction").val().toUpperCase());
			 });
			
			 if(jQuery('#hdnformType').val() == "KAIZEN")
				 jQuery('.yy-pillarTD').css('width','6%');
			 
		 }
			 
	 }
	 
		jQuery('#chkWwmsSparesreplaced').click(function() {
			jQuery('#chkWwmsSparesreplaced').attr('checked',true);
			jQuery('#sparesReplaced').attr('checked',false);
		});
		jQuery('#sparesReplaced').click(function() {
			jQuery('#sparesReplaced').attr('checked',true);
			jQuery('#chkWwmsSparesreplaced').attr('checked',false);
		});
		
//	 jQuery("#dispFunctionalLoc").attr('disabled', 'disabled');
	 

});


jQuery( "#imgCloseOJTSOP" ).click(function() {
	closePopUp();	
});
jQuery( "#btnOJTSOP" ).click(function() {	
		
	saveForm('frmWhywhyAnalysis1','whywhy_save.why');
});
jQuery('#btnEndYY').click(function() {
	
	var rowId = jQuery("#yyGrid").jqGrid('getDataIDs');
    var lastRow;
	for(var i=0;i<rowId.length;i++)
		lastRow = rowId[i];
	var Action = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAction');
	var Answer = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAnswer');
	
	if(Action != null && Action !='' && Action != ' '&& Answer != null && Answer !='' && Answer != ' ')
		jQuery("#hdnAddRowFlag").val("Yes");
	
	if(Action.indexOf('input'))
	{
		Action = jQuery('#'+lastRow+'_txtWwdtAction').val();
	}
	jQuery("#txtWwmsCountermeasure").val(Action);
	addYYRow();
});
jQuery('#btnEndSheYY').click(function() {
	var rowId = jQuery("#yyGrid").jqGrid('getDataIDs');
    var lastRow;
	for(var i=0;i<rowId.length;i++)
		lastRow = rowId[i];	
	var why = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtWhy');	
	if(why != null && why !='' && why != ' ')
		jQuery("#hdnAddRowFlag").val("Yes");
	if(why.indexOf('input'))
	{
		why = jQuery('#'+lastRow+'_txtWwdtWhy').val();
	}	
	jQuery("#txtWwmsRootcause").val(why);
	addYYSheRow();
});
jQuery('#btnDelYY').click(function() {
	//txtWwdtKeyid
});
jQuery('#chbisPYYes').click(function() {
	jQuery('#chbisPYYes').attr('checked',true);
	jQuery('#chbisPYNo').attr('checked',false);
	jQuery('#hdnWwmsIspokayoke').val('Y');
});
jQuery('#chbisPYNo').click(function() {
	jQuery('#chbisPYNo').attr('checked',true);
	jQuery('#chbisPYYes').attr('checked',false);
	jQuery('#hdnWwmsIspokayoke').val('N');
});
jQuery('#chbisKZNYes').click(function() {
	jQuery('#chbisKZNYes').attr('checked',true);
	jQuery('#chbisKZNNo').attr('checked',false);
	jQuery('#hdnWwmsIskzn').val('Y');
});
jQuery('#chbisKZNNo').click(function() {
	jQuery('#chbisKZNNo').attr('checked',true);
	jQuery('#chbisKZNYes').attr('checked',false);
	jQuery('#hdnWwmsIskzn').val('N');
});
jQuery('#chb4mType').click(function() {	
	jQuery('#hdnWwmsFormtype').val('Y');
});
jQuery('#chkNotEffective').click(function() {
	jQuery('#chkNotEffective').attr('checked',true);
	jQuery('#chkEffective').attr('checked',false);
	jQuery('#hdnWwmsIseffective').val('N');		
});
jQuery('#chkEffective').click(function() {
	jQuery('#chkEffective').attr('checked',true);
	jQuery('#chkNotEffective').attr('checked',false);
	jQuery('#hdnWwmsIseffective').val('Y');	
});
jQuery("#cmbfourMType").combobox({
	onSelect:function(record){
		jQuery("#hdnWwmsFormtype").val(record.id);
	}
});

/*function yygridDbClick_callback(id){
	
	
	var colModel = jQuery("#yyGrid").jqGrid("getGridParam","colModel" );

	for(var j= colStart;j<colModel.length;j++)	{
		 if(  colModel[j].editable == true  ){
			 
		 }
	}
}
*/
function frmWhywhyAnalysis1cmbWwmsMachineid_onLoadSuccess()
{
	var machineId = jQuery('#cmbWwmsMachineid').combobox('getValue');
	
	if(machineId == null || machineId == '' || machineId == ' ')
		setTimeout(function() { jQuery('#cmbWwmsMachineid').combobox('setValue','');},1250);
}
function frmWhywhyAnalysis1cmbWwmsAssemblyid_onLoadSuccess()
{
	if(jQuery('#hdnformType').val() == "IMT")
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsPhenomenaid","combo_Phenomena.ire?q=2");	//fillComboBox("frmWhywhyAnalysis1","cmbWwmsSectionid","sectionCombo.commonFilter");
	else
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsPhenomenaid","combo_phenomena.brdn?q=2");
}
function frmWhywhyAnalysis1cmbWwmsPrevperson_onLoadSuccess()
{
	
}
/*function frmWhywhyAnalysis1cmbWwmsSectionid_onLoadSuccess()
{
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsCellid","cellCombo.commonFilter");	
}
function frmWhywhyAnalysis1cmbWwmsCellid_onLoadSuccess()
{
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsPhenomenaid","combo_phenomena.brdn");	
}*/
function frmWhywhyAnalysis1cmbWwmsPhenomenaid_onLoadSuccess()
{
	if(jQuery('#hdnformType').val() == "IMT")
		fillComboBox("frmWhywhyAnalysis1","cmbWwmsCauseid","combo_cause.ire");	
	else
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsCauseid","combo_cause.brdn");	
}
function frmWhywhyAnalysis1cmbWwmsCauseid_onLoadSuccess()
{
	
	//jQuery('#cmbWwmsKeyid').combobox('disable');//fillComboBox("frmWhywhyAnalysis1","cmbWwmsKeyid","combo_yy.why");	
}
function frmWhywhyAnalysis1cmbWwmsKeyid_onLoadSuccess()
{
	readOnlyFields('cmbWwmsKeyid');	
	fillComboBox("frmWhywhyAnalysis1","cmbWwmsMaintinchargeid","employee.commonFilter");
	//jQuery('#cmbWwmsKeyid').combobox('disable');	
}

function checkBoxSel()
{
	
	if(jQuery('#hdnSparesVal').val() == 'Y')
	{
		jQuery('#chkWwmsSparesreplaced').attr('checked',true);
		jQuery('#sparesReplaced').attr('checked',false);
	}
	else if(jQuery('#hdnSparesVal').val() == 'N')
	{
		jQuery('#chkWwmsSparesreplaced').attr('checked',false);
		jQuery('#sparesReplaced').attr('checked',true);
	}
	else
	{
		jQuery('#chkWwmsSparesreplaced').attr('checked',false);
		jQuery('#sparesReplaced').attr('checked',false);
	}
	
	if(jQuery('#hdnWwmsishd').val() == 'Y')
	{
		jQuery('#chbWwmsIshdpossible').attr('checked',true);
	}
		
}
function checkBoxSelIsKzn()
{
	if(jQuery('#hdnWwmsIskzn').val() == 'Y')
	{
		jQuery('#chbisKZNYes').attr('checked',true);
		jQuery('#chbisKZNNo').attr('checked',false);
	}
	else if(jQuery('#hdnWwmsIskzn').val() == 'N')
	{
		jQuery('#chbisKZNYes').attr('checked',false);
		jQuery('#chbisKZNNo').attr('checked',true);
	}
	else
	{
		jQuery('#chbisKZNYes').attr('checked',false);
		jQuery('#chbisKZNNo').attr('checked',false);
	}

	
	
		var fmt = jQuery('#hdnWwmsFormtype').val();
		jQuery("#cmbfourMType").combobox('setValue',fmt);
	
	
}
function checkBoxSelIsPY()
{
	if(jQuery('#hdnWwmsIspokayoke').val() == 'Y')
	{
		jQuery('#chbisPYYes').attr('checked',true);
		jQuery('#chbisPYNo').attr('checked',false);
	}
	else if(jQuery('#hdnWwmsIspokayoke').val() == 'N')
	{
		jQuery('#chbisPYYes').attr('checked',false);
		jQuery('#chbisPYNo').attr('checked',true);
	}
	else
	{
		jQuery('#chbisPYYes').attr('checked',false);
		jQuery('#chbisPYNo').attr('checked',false);
	}
}
function checkBoxSelIsEffective()
{
	if(jQuery('#hdnWwmsIseffective').val() == 'Y')
	{
		jQuery('#chkEffective').attr('checked',true);
		jQuery('#chkNotEffective').attr('checked',false);
	}
	else if(jQuery('#hdnWwmsIseffective').val() == 'N')
	{
		jQuery('#chbisPYYes').attr('checked',false);
		jQuery('#chkNotEffective').attr('checked',true);
	}
	else
	{
		jQuery('#chkEffective').attr('checked',false);
		jQuery('#chkNotEffective').attr('checked',false);
	}
}
function addYYRow()
{
	if(jQuery("#hdnAddRowFlag").val() == 'No' || jQuery("#hdnAddRowFlag").val() == '' || jQuery("#hdnAddRowFlag").val() == null)
		{
		
			//var row = jQuery("#yyGrid").jqGrid('getDataIDs');
			 var row = jQuery("#yyGrid").jqGrid('getRowData');
			 var rowId = jQuery("#yyGrid").jqGrid('getDataIDs');
			 var lastRow;
			for(var i=0;i<rowId.length;i++)
				lastRow = rowId[i];

			 var Action = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAction');
			 var Answer = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAnswer');
			
			 if(Action != null && Action != '' && Action != ' ' && Answer !=null && Answer != '' && Answer != ' ')
			 {
				 if(confirm("Do You Want to Close Why Why?") == true)
				 {
					 jQuery("#hdnAddRowFlag").val("Yes");
					 jQuery("#txtWwmsCountermeasure").val(Action);
				 }
				 else
				 {
					jQuery("#hdnAddRowFlag").val("No");			 
					var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:Answer,txtWwdtAnswer:" ",txtWwdtAction:" "}];
				 	jQuery("#yyGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
				 }
			 }
			 else
			 {
				/*if(confirm("Do You Want to Close Why Why?") == true)
				{
					 var Action = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAction');
					 if(Action == null || Action == ' ' || Action == '')
						 {
						  alert("Enter Action For Why Why");							
						  jQuery("#yyGrid").jqGrid('editCell',parseInt(lastRow),2,true);				 
						 }
					 else
						  jQuery("#hdnAddRowFlag").val("Yes");
				}
				else
				{*/
						
					// var Answer = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAnswer');
				 			
					 if(Answer !=null && Answer != '' && Answer != ' ')	
					 {			
					   jQuery("#hdnAddRowFlag").val("No");			 
						var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:Answer,txtWwdtAnswer:" ",txtWwdtAction:" "}];
					 	jQuery("#yyGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
					 }
					 else
						 alert('Insert Answer');			
				//}
			 }
		}
}
function actionFormatterC(cellvalue, options, rowObject)   
{
	 var rowId = options.rowId;
	 var formatStr  = '<span id="rc_'+rowId+'"' ;
		 formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}
function actionFormatterSave(cellvalue, options, rowObject)     ////Tom
{
	 var rowId = options.rowId;	
	 var formatStr  = '<input id="pillar_'+rowId+'"' ;
		 formatStr  += 'type="button" class="easyui-button" style="height:20px;height:16px\9;" value="..." onclick="selectPillar(\''+rowId + '\');">';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}
function actionFormatterDel(cellvalue, options, rowObject) 
{
	
	
	 var rowId = options.rowId;
	 	 
	 	var formatStr  = '<input id="del_'+rowId+'"' ;
		 formatStr  += 'type="button" class="easyui-button" style="height:20px;height:16px\9;" value="..." onclick="deleteYY(\''+rowId + '\');">';// tick 
		 formatStr  +=  '</span>';
		return formatStr;
		 
	 
}
function actionFormatterDelKaizen(cellvalue, options, rowObject) 
{
	
	
	 var rowId = options.rowId;		 
	 var formatStr  = '<input id="del_'+rowId+'"' ;
		 formatStr  += 'type="button" class="easyui-button" style="height:20px;height:16px\9;" value="..." onclick="deleteYYKaizen(\''+rowId + '\');">';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}
function actionFormatterDelShe(cellvalue, options, rowObject) 
{
	 var rowId = options.rowId;		 
	 var formatStr  = '<input id="del_'+rowId+'"' ;
		 formatStr  += 'type="button" class="easyui-button" style="height:20px;height:16px\9;" value="..." onclick="deleteYY(\''+rowId + '\');">';// tick 
		 formatStr  +=  '</span>';
	return formatStr;
}
function selectPillar(id)
{
	var flag = jQuery('#hdnCounterMsrFlag').val();
	var jh = "am";
	if(flag != null && flag != ' ' && flag != '')
	{
		if(flag == "YES")
			jh = "jh";
	}

	if(jQuery('#hdnformType').val() == "CC" || jQuery('#hdnformType').val() == "IMT")
	{
		if(id == '1')
			jQuery("#hdnwwmsPillarmode").val("am");
		else if(id == '2')
			jQuery("#hdnwwmsPillarmode").val(jh);
		else if(id == '3')
			jQuery("#hdnwwmsPillarmode").val("ci");
		else if(id == '4')
			jQuery("#hdnwwmsPillarmode").val("et");
		else if(id == '5')
			jQuery("#hdnwwmsPillarmode").val("am");
		
	}
	else if(jQuery('#hdnformType').val() == "SHE" || jQuery('#hdnformType').val() == "DOCK")
	{
		if(id == '1')
			jQuery("#hdnwwmsPillarmode").val("ci");
		else if(id == '2')
			jQuery("#hdnwwmsPillarmode").val("oj");
		else if(id == '3')
			jQuery("#hdnwwmsPillarmode").val("et");
		else if(id == '4')
		{
		  if(jQuery('#hdnformType').val() == "DOCK")
			jQuery("#hdnwwmsPillarmode").val("so");
		  else
			jQuery("#hdnwwmsPillarmode").val("am");
		}		
	}
	else
	{
		if(id == '1')
			jQuery("#hdnwwmsPillarmode").val(jh);
		else if(id == '2')
			jQuery("#hdnwwmsPillarmode").val("ci");
		else if(id == '3')
			jQuery("#hdnwwmsPillarmode").val("et");
		else if(id == '4')
			jQuery("#hdnwwmsPillarmode").val("pm");
		else if(id == '5')
			jQuery("#hdnwwmsPillarmode").val("trn");
	}
	//if(id != '1')
	if(jQuery("#hdnwwmsPillarmode").val() != "am" && jQuery("#hdnwwmsPillarmode").val() != "oj" && jQuery("#hdnwwmsPillarmode").val() != "so")
		saveForm('frmWhywhyAnalysis1','whywhy_save.why');
	else
	{
		if(jQuery("#hdnwwmsPillarmode").val() == "oj")
			 openPopUp('OJT');	
		else if(jQuery("#hdnwwmsPillarmode").val() == "so")
			 openPopUp('SOP');	
	}
	//var rowData = jQuery("#list").jqGrid('getRowData',id);
	//jQuery("#hdnwwmsPillarmode").val("am");
}
function openPopUp(title)
{
	jQuery('#mstfrm_div').addClass('popup-mask');
	//jQuery('#mstfrm_div').css('z-index',100);
	jQuery('#mstfrm_div').show();
	jQuery('#dlgOJTSOP').addClass('custom-popup');			
    jQuery( '#dlgOJTSOP' ).show();    
    jQuery( '#dlgOJTSOP' ).css('border','1px solid #2364CA');
   jQuery( '#dlgOJTSOP' ).css('margin-top',400);     
    jQuery( '#dlgOJTSOP' ).css('width',350);
	jQuery( '#dlgOJTSOP' ).css('height',300);	
	jQuery( '#lblOJTSOP' ).html(title);	
	if(title == 'OJT')
		 jQuery( '#sopRemarks' ).css('display','none');
	else if(title == 'SOP')
		 jQuery( '#ojtRemarks' ).css('display','none');
		
}
function closePopUp()
{	
	 jQuery('#dlgOJTSOP').hide();
	 jQuery('#mstfrm_div').removeClass('popup-mask');	
	 jQuery('#dlgOJTSOP').removeClass('custom-popup');	
}
function deleteYY(id)
{
	if(id != '1')
	{
		if(confirm('Do You Want to Delete?') == true)
		{
			var rowData = jQuery("#yyGrid").jqGrid('getRowData',id);
			var keyId = rowData.txtWwdtKeyid;
			//alert(keyId);
			jQuery('#yyGrid').restoreCell(id,2);
			jQuery('#yyGrid').restoreCell(id,3);
			if(keyId != null && keyId != '' && keyId!=' ')
				processAjaxCalls('WhyWhy_delete.why','?q=2&yyKey='+keyId+'&rowId='+id,'afterDeleteYY');
			else{
				  jQuery('#yyGrid').delRowData(id);
				  var g = jQuery('#yyGrid');		
				  var gridData= jQuery("#jqgrid_id").jqGrid('getRowData');
				  g.setGridParam({ data: gridData });
				  g[0].refreshIndex();
				  //g.trigger("reloadGrid");
			}	
		}
	}
	
}
function afterDeleteYY(result)
{
	alert(result.msg);
	jQuery('#yyGrid').jqGrid('delRowData',result.rowId);
	var g = jQuery('#yyGrid');		
	g[0].refreshIndex();
}
function deleteYYKaizen(id)
{
	 var rowData = jQuery("#yyKaizenGrid").jqGrid('getRowData',id);
	 var keyId = rowData.txtWwdtKeyid;
		//alert(keyId);
		jQuery('#yyKaizenGrid').restoreCell(id,2);
		jQuery('#yyKaizenGrid').restoreCell(id,3);
	if(keyId != null && keyId != '' && keyId!=' ')
		processAjaxCalls('WhyWhy_delete.why','?q=2&yyKey='+keyId+'&rowId='+id,'afterDeleteYYKaizen');
	else{
		
		jQuery('#yyKaizenGrid').delRowData(id);
		var g = jQuery('#yyKaizenGrid');		
		var gridData= jQuery("#jqgrid_id").jqGrid('getRowData');
		g.setGridParam({ data: gridData });
		g[0].refreshIndex();
		//g.trigger("reloadGrid");
				
			
	}	
	
}
function afterDeleteYYKaizen(result)
{
	alert(result.msg);
	jQuery('#yyKaizenGrid').jqGrid('delRowData',result.rowId);
	var g = jQuery('#yyKaizenGrid');		
	g[0].refreshIndex();
}
/*function actionFormatterAM(cellvalue, options, rowObject) 
{
	var rowId = options.rowId;
	var formatStr  = '<span id="am_'+rowId+'"' ;
		formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		formatStr  +=  '</span>';
	return formatStr;	
}
function actionFormatterPM(cellvalue, options, rowObject) 
{
	var rowId = options.rowId;
	var formatStr  = '<span id="pm_'+rowId+'"' ;
		formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		formatStr  +=  '</span>';
	return formatStr;
}
function actionFormatterCI(cellvalue, options, rowObject) 
{
	var rowId = options.rowId;
	var formatStr  = '<span id="ci_'+rowId+'"' ;
		formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
	    formatStr  +=  '</span>';
	return formatStr;
}
function actionFormatterET(cellvalue, options, rowObject) 
{
	var rowId = options.rowId;
	var formatStr  = '<span id="et_'+rowId+'"' ;
		formatStr  += 'class="tick" style=\"color:blue;font-size:20px;\" >';// tick 
		formatStr  +=  '</span>';
	return formatStr;
}
*/
function yyOnLoad()
{
	if(screen.width >=1250)
	{
		//jQuery( "#yyGrid" ).setGridWidth(1140);
		//jQuery( "#yyGrid" ).jqGrid('setColProp','amount',{width:new_width});
	}
	
	 var row = jQuery("#yyGrid").jqGrid('getDataIDs');
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var answer = jQuery('#txaWwmsFinalaction').val(); 
	 	var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:answer,txtWwdtAnswer:" ",txtWwdtAction:" "}];
	 	jQuery("#yyGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
	
	jQuery("#yyGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {
			
			var keyID = jQuery("#yyGrid").jqGrid('getCell',id,name="txtWwdtKeyid");
			//alert(keyID);
			//if(jQuery('#txtWwdtKeyid')hdnDelYYId
		},
		afterEditCell: function(rowid, name, value, iRow, iCol) {			
			 var inputControl = jQuery('#' + (iRow) + '_' + name);	
			
			 inputControl.css('text-transform', 'uppercase');		 	
			 inputControl.keydown(function(e) {		
				 		
				  if (e.keyCode === 13) { 		
					  
					  var ans = jQuery("#yyGrid").jqGrid('getCell',rowid,name=='txtWwdtAnswer'?iCol:iCol-1);
								  			 
					  if(ans == null || ans == ' ' || ans == '')
						  alert("Enter Answer For Why Why");
					  else
						 addYYRow();
				  }
			 });

			 
		 },
		afterSaveCell : function(rowid,name,val,iRow,iCol) 
    	{
			  var ans = jQuery("#yyGrid").jqGrid('getCell',rowid,'txtWwdtAnswer');
			  var action = jQuery("#yyGrid").jqGrid('getCell',rowid,'txtWwdtAction');
			  jQuery("#yyGrid").jqGrid("setCell", rowid, 'txtWwdtAnswer', ans.toUpperCase());
			  jQuery("#yyGrid").jqGrid("setCell", rowid, 'txtWwdtAction', action.toUpperCase());			  
			 
		}
	
	});

}
function yyKaizenOnLoad()
{
	 var row = jQuery("#yyKaizenGrid").jqGrid('getDataIDs');
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var answer = jQuery('#txaWwmsFinalaction').val(); 
	 	 var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:" ",txtWwdtAnswer:" "}];
	 	jQuery("#yyKaizenGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
		jQuery("#yyKaizenGrid").setGridParam({
			afterEditCell: function(rowid, name, value, iRow, iCol) {			
				 var inputControl = jQuery('#' + (iRow) + '_' + name);	
				 inputControl.css('text-transform', 'uppercase');		 	
				 inputControl.keydown(function(e) {				
					  if (e.keyCode === 13) { 					
						  var ans = jQuery("#yyKaizenGrid").jqGrid('getCell',rowid,name=='txtWwdtAnswer'?iCol:iCol-1);					  			 
						  if(ans == null || ans == ' ' || ans == '')
							  alert("Enter Answer For Why Why");
						  else
							 addYYKaizenRow();
					  }
				 });

				 
			 },
			afterSaveCell : function(rowid,name,val,iRow,iCol) 
	    	{
				  var ans = jQuery("#yyKaizenGrid").jqGrid('getCell',rowid,'txtWwdtAnswer');
				 // var action = jQuery("#yyKaizenGrid").jqGrid('getCell',rowid,'txtWwdtAction');
				  jQuery("#yyKaizenGrid").jqGrid("setCell", rowid, 'txtWwdtAnswer', ans.toUpperCase());
				  //jQuery("#yyKaizenGrid").jqGrid("setCell", rowid, 'txtWwdtAction', action.toUpperCase());			  
				 
			}
		
		});
}
function yySheOnLoad()
{
	if(screen.width >=1250)
	{
		jQuery( "#yyGrid" ).setGridWidth(900);
		//jQuery( "#yyGrid" ).jqGrid('setColProp','txtWwdtWhy',{"width":"1500"});
	}
	 var row = jQuery("#yyGrid").jqGrid('getDataIDs');
	 if ( row == null || row == '' || parseInt(row) <= 0) {
		 var why = jQuery('#txtWwmsAccidentphen').val(); 
	 	 var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:" ",txtWwdtAnswer:" "}];
	 	jQuery("#yyGrid").jqGrid('addRowData',1, emptyItem[0]);
	 }	
		jQuery("#yyGrid").setGridParam({
			afterEditCell: function(rowid, name, value, iRow, iCol) {			
				 var inputControl = jQuery('#' + (iRow) + '_' + name);	
				 inputControl.css('text-transform', 'uppercase');		 	
				 inputControl.keydown(function(e) {				
					  if (e.keyCode === 13) { 							 					  			 
						
							 addYYSheRow();
					  }
				 });

				 
			 },
			afterSaveCell : function(rowid,name,val,iRow,iCol) 
	    	{
		    	
				 var why = jQuery("#yyGrid").jqGrid('getCell',rowid,'txtWwdtWhy');
				 jQuery("#yyGrid").jqGrid("setCell", rowid, 'txtWwdtWhy', why.toUpperCase());					 
			}
		
		});
}
function addYYKaizenRow()
{
	if(jQuery("#hdnAddRowFlag").val() == 'No' || jQuery("#hdnAddRowFlag").val() == '' || jQuery("#hdnAddRowFlag").val() == null)
		{
			var row = jQuery("#yyKaizenGrid").jqGrid('getDataIDs');
			 var rowId = jQuery("#yyKaizenGrid").jqGrid('getDataIDs');
			 var lastRow;
			for(var i=0;i<rowId.length;i++)
				lastRow = rowId[i];
			
		/*	if(confirm("Do You Want to Close Why Why?") == true)
			{
				 var Answer = jQuery("#yyKaizenGrid").jqGrid('getCell',row.length,'txtWwdtAnswer');
				
				 if(Answer == null || Answer == ' ' || Answer == '')
					  alert("Enter Answer For Why Why");
				 else
				 {
					 jQuery('#txtWwmsRootcause').val(Answer);
					  jQuery("#hdnAddRowFlag").val("Yes");
				 }
			}
			else
			{*/
				 jQuery("#hdnAddRowFlag").val("No");	
				 var Answer = jQuery("#yyKaizenGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtAnswer');
				// alert(Answer);					 
				 var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:Answer,txtWwdtAnswer:" "}];
				 jQuery("#yyKaizenGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
			//}
		}
}
function addYYSheRow()
{
	if(jQuery("#hdnAddRowFlag").val() == 'No' || jQuery("#hdnAddRowFlag").val() == '' || jQuery("#hdnAddRowFlag").val() == null)
	{
		var row = jQuery("#yyGrid").jqGrid('getDataIDs');
		 var rowId = jQuery("#yyGrid").jqGrid('getDataIDs');
		 var lastRow;
		for(var i=0;i<rowId.length;i++)
			lastRow = rowId[i];
		 jQuery("#hdnAddRowFlag").val("No");
		// var Why = jQuery("#yyGrid").jqGrid('getCell',parseInt(lastRow),'txtWwdtWhy');	
		// jQuery("#txtWwmsRootcause").val(Why);			 
		 var emptyItem =[{txtyyKeyid:" ",txtWwdtWhy:" "}];
		 jQuery("#yyGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);	
	}
}
function rootCauseOnLoad()
{
	var formType = jQuery('#hdnformType').val();
	if(screen.width >=1250)
	{
		
		if(formType != null && formType != ' ' && formType != '')
		{
			if(formType == 'SHE')
			{
				jQuery( "#rootPillarGrid" ).setGridWidth(428);
				jQuery( "#PillarGrid" ).setGridWidth(428);
				jQuery( "#PillarGrid" ).setGridHeight(85);
			}
			else
			{	
				jQuery( "#rootPillarGrid" ).setGridWidth(270);
				jQuery( "#PillarGrid" ).setGridWidth(388);
			}
		}
		else
		{	
			jQuery( "#rootPillarGrid" ).setGridWidth(270);
			jQuery( "#PillarGrid" ).setGridWidth(388);
		}	
	}
	else
	{
		if(formType == 'SHE')
		{
			jQuery( "#rootPillarGrid" ).setGridWidth(428);
			jQuery( "#PillarGrid" ).setGridWidth(400);
			jQuery( "#PillarGrid" ).setGridHeight(85);
		}
		
	}
	

	
	selectedRC();
	jQuery("#rootPillarGrid").setGridParam({
		onCellSelect:function(id,cellidx,cellvalue) {  
		
				if(cellidx == 1)
					{						
						if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
						{
							var rcId= jQuery("#rootPillarGrid").jqGrid('getCell',id,cellidx-1);	
							var rc= jQuery("#rootPillarGrid").jqGrid('getCell',id,cellidx+1);	
							
							jQuery("#hdnWwmsRootcauseid").val(rcId);
							if(formType != 'SHE')
								jQuery("#hdnWwmsRootcause").val(rc);
							jQuery("#rc_"+id).html("&#10003");	
							jQuery("#hdnRCID").val(rcId);
							disableRootCause(id);									
						}
						else
						{
							jQuery("#rc_"+id).html("");
							jQuery("#hdnRCID").val();
							jQuery("#hdnWwmsRootcauseid").val("");
							if(formType != 'SHE')
								jQuery("#hdnWwmsRootcause").val("");
						}
						
					}
			/*	else if(cellidx == 3)
					{
						if(jQuery("#am_"+id).html() == '' || jQuery("#am_"+id).html() == null)
						{
							if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
								alert("Select Root Cause");
							else
							{
								jQuery("#am_"+id).html("&#10003");
								jQuery("#pm_"+id).html("");
								jQuery("#et_"+id).html("");
								jQuery("#ci_"+id).html("");	
								jQuery("#hdnwwmsPillarmode").val("am");					
							}					
								
						}
						else
						{
							jQuery("#am_"+id).html("");
							jQuery("#hdnwwmsPillarmode").val("");
						}
					}
				else if(cellidx == 4)
					{
						if(jQuery("#pm_"+id).html() == '' || jQuery("#pm_"+id).html() == null)
						{
							if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
								alert("Select Root Cause");
							else
							{
								jQuery("#am_"+id).html("");							
								jQuery("#et_"+id).html("");
								jQuery("#ci_"+id).html("");	
								jQuery("#pm_"+id).html("&#10003");
								jQuery("#hdnwwmsPillarmode").val("pm");	
							}
						}
						else
						{
							jQuery("#pm_"+id).html("");
							jQuery("#hdnwwmsPillarmode").val("");
						}
					}
				else if(cellidx == 5)
					{
						if(jQuery("#ci_"+id).html() == '' || jQuery("#ci_"+id).html() == null)
						{
							if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
								alert("Select Root Cause");
							else
							{
								if(jQuery("#hdnWwmsRootcause").val() == 'POOR BASIC CONDITION')
								{}
								else
								{
									jQuery("#am_"+id).html("");
									jQuery("#pm_"+id).html("");
									jQuery("#et_"+id).html("");							
									jQuery("#ci_"+id).html("&#10003");
									jQuery("#hdnwwmsPillarmode").val("ci");
								}	
							}
						}
						else
						{
							jQuery("#ci_"+id).html("");
							jQuery("#hdnwwmsPillarmode").val("");
						}
					}
				else if(cellidx == 6)
					{
						if(jQuery("#et_"+id).html() == '' || jQuery("#et_"+id).html() == null)
						{
							if(jQuery("#rc_"+id).html() == '' || jQuery("#rc_"+id).html() == null)
								alert("Select Root Cause");
							else
							{
								if(jQuery("#hdnWwmsRootcause").val() == 'POOR BASIC CONDITION')
								{}
								else
								{
									jQuery("#am_"+id).html("");
									jQuery("#pm_"+id).html("");													
									jQuery("#ci_"+id).html("");
									jQuery("#et_"+id).html("&#10003");
									jQuery("#hdnwwmsPillarmode").val("et");	
								}
							}
						}
						else
						{
							jQuery("#et_"+id).html("");
							jQuery("#hdnwwmsPillarmode").val("");
						}
					}*/
		}
	});
}
function disableRootCause(id)
{
	for(var i=1;i<=5;i++)
	{
		if(i != id)
		{
		  jQuery("#rc_"+i).html("");
		  jQuery("#am_"+i).html("");
		  jQuery("#pm_"+i).html("");
		  jQuery("#ci_"+i).html("");
		  jQuery("#et_"+i).html("");
		}
	}
}
function selectedRC()
{
	var rowID =  jQuery("#rootPillarGrid").jqGrid('getRowData');
	var selectedRC = null;
	
	for(var i=0;i<=rowID.length;i++)
	{
		//alert(2);
	//   alert("cause =="+txtWwmsrootcauseid);
		if(jQuery("#rootPillarGrid").jqGrid('getCell',i,'txtWwmsrootcauseid') == jQuery("#hdnRCID").val())
		{
			selectedRC = i;
			jQuery("#rc_"+selectedRC).html("&#10003");	
		}
	}
	
	/*if(jQuery("#hdnIsJh").val() == 'Y')
	{
		jQuery("#am_"+selectedRC).html("&#10003");	
		jQuery("#hdnwwmsPillarmode").val("am");
	}
	if(jQuery("#hdnIsPM").val() == 'Y')
	{
		jQuery("#pm_"+selectedRC).html("&#10003");
		jQuery("#hdnwwmsPillarmode").val("am");
	}
	if(jQuery("#hdnIsCI").val() == 'Y')
	{
		jQuery("#ci_"+selectedRC).html("&#10003");
		jQuery("#hdnwwmsPillarmode").val("ci");
	}
	if(jQuery("#hdnIsET").val() == 'Y')
	{
		jQuery("#et_"+selectedRC).html("&#10003");
		jQuery("#hdnwwmsPillarmode").val("et");
	}*/
}
/*function convertToJSONArr(jqGridId){
	
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		var jsonArrO='[';
		for( var i = 0; i < allRows.length;i++){
			var row = allRows[i];
			jsonArrO += '{';
			
			for(var colName in row) {
				
				if(row[colName].substring(0,6)!='<input')
				{
					jsonArrO += '"'+colName +'":"' + row[colName].replace(/(\r\n|\n|\r)/gm,"") +'",'; 
				}
				else
				{
					var x=row[colName].indexOf("id=")+4;
					var y=row[colName].substring(x);
					var z = y.indexOf('"');					
					jsonArrO += '"'+colName +'":"' + jQuery('#'+y.substring(0,z)).val().replace(/(\r\n|\n|\r)/gm,"") +'",'; 
				}
			}
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}
		
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");		
		return jsonArrO; 
	}*/

function disableFieldsInYY()
{
	setTimeout(function() {
							readOnlyFields('cmbWwmsMaintinchargeid');	
							jQuery("#chkWwmsSparesreplaced").attr("disabled",true);
							jQuery("#sparesReplaced").attr("disabled",true);},1250);	
	readOnlyFields('txtWwmsRefdocno');
	readOnlyFields('dteWwmsDate');
	readOnlyFields('txaWwmsFinalaction');
	readOnlyFields('cmbWwmsKeyid');	
	var mchId = jQuery("#cmbWwmsMachineid").combobox('getValue');
	var assmId = jQuery("#cmbWwmsAssemblyid").combobox('getValue');
	var phenId = jQuery("#cmbWwmsPhenomenaid").combobox('getValue');
	var causeId = jQuery("#cmbWwmsCauseid").combobox('getValue');
	
	if(mchId != null && mchId != '' && mchId != ' ')
		readOnlyFields('cmbWwmsMachineid');
	if(assmId != null && assmId != '' && assmId != ' ')
		readOnlyFields('cmbWwmsAssemblyid');
	if(phenId != null && phenId != '' && phenId != ' ')
		readOnlyFields('cmbWwmsPhenomenaid');
	if(causeId != null && causeId != '' && causeId != ' ')
		readOnlyFields('cmbWwmsCauseid');
	
}
function disableFieldsInSheYY()
{
	readOnlyFields('txtWwmsRefdocno');
	readOnlyFields('cmbWwmsKeyid');	
	readOnlyFields('txtWwmsRootcause');	
}
function disableFieldsInCC()
{
	setTimeout(function() {
		readOnlyFields('cmbWwmsMaintinchargeid');},1250);	
		readOnlyFields('txtWwmsRefdocno');
		readOnlyFields('dteWwmsDate');
		
		readOnlyFields('cmbWwmsKeyid');	
		var mchId = jQuery("#cmbWwmsMachineid").combobox('getValue');
		var assmId = jQuery("#cmbWwmsAssemblyid").combobox('getValue');
		var phenId = jQuery("#cmbWwmsPhenomenaid").combobox('getValue');
		var causeId = jQuery("#cmbWwmsCauseid").combobox('getValue');
		
		if(mchId != null && mchId != '' && mchId != ' ')
		readOnlyFields('cmbWwmsMachineid');
		if(assmId != null && assmId != '' && assmId != ' ')
		readOnlyFields('cmbWwmsAssemblyid');
		if(phenId != null && phenId != '' && phenId != ' ')
		readOnlyFields('cmbWwmsPhenomenaid');
		if(causeId != null && causeId != '' && causeId != ' ')
		readOnlyFields('cmbWwmsCauseid');
}
function disableFieldsInKaizen()
{
	readOnlyFields('txtWwmsRefdocno');
	readOnlyFields('dteWwmsDate');
	readOnlyFields('cmbWwmsKeyid');	
	readOnlyFields('cmbWwmsMachineid');
	readOnlyFields('txtWwmsRootcause');
	readOnlyFields('txtWwmsCountermeasure');
}
function frmWhywhyAnalysis1_beforeSubmit()
{

	if(jQuery('#hdnformType').val() == "KAIZEN" || jQuery('#hdnformType').val() == "ABN" || jQuery('#hdnformType').val() == "GM")
	{
		 var rowid = jQuery("#yyKaizenGrid").jqGrid('getDataIDs');
		 var ans = jQuery("#yyKaizenGrid").jqGrid('getCell',rowid.length,'txtWwdtAnswer');
		
		 if(ans == null || ans == ' ' || ans == '') {
			  alert("Enter Answer For Why Why");
			  jQuery("#yyKaizenGrid").jqGrid('editCell',rowid.length,1,true);
			  return false;
		  }
		 if(ans.substring(0,1) == '<')
		 {
			 
	 		 if(jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != '' && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != null && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != ' ') 
					return 'YYAnalysis='+convertGridToJSONArr('yyKaizenGrid');
	 		 else
		 		 {
	 			  	 alert("Enter Answer For Why Why");
					 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
					  jQuery("#yyGrid").jqGrid('editCell',rowid.length,1,true);	
					  return false;
		 		 }
		 }
		 else
		   return 'YYAnalysis='+convertGridToJSONArr('yyKaizenGrid');
	}
	else if(jQuery('#hdnformType').val() == "SHE")
	{

		var rowid = jQuery("#yyGrid").jqGrid('getDataIDs');
		var why = jQuery("#yyGrid").jqGrid('getCell',rowid.length,'txtWwdtWhy');
		var rootCauseVal = jQuery("#hdnWwmsRootcauseid").val();
		var pillar = jQuery("#hdnwwmsPillarmode").val();

		 if(jQuery("#hdnRCID").val() != null && jQuery("#hdnRCID").val() != '')
			 rootCauseVal = jQuery("#hdnRCID").val();		

		
		 if(rootCauseVal == null || rootCauseVal == ' ' || rootCauseVal =='')	
		  {
			  alert("Select RootCause");			 
			  return false;
		  }	 
		  /*if(pillar == null || pillar == ' ' || pillar =='')	
		  {
			  alert("Select Counter Measure");
			  return false;
		  }*/
		  
 		  if(why == null || why == ' ' || why == '') {
			  
			  alert("Enter Why");
			  jQuery("#yyGrid").jqGrid('editCell',rowid.length,0,true);	
			  return false;
		  }
 		  else
 	 	  {
 			 if(why.substring(0,1) == '<')
			 {
		 		 if(jQuery('#'+rowid.length+'_txtWwdtWhy').val() != '' && jQuery('#'+rowid.length+'_txtWwdtWhy').val() != null && jQuery('#'+rowid.length+'_txtWwdtWhy').val() != ' ')
			 	 { 
		 				jQuery("#txtWwmsRootcause").val(jQuery('#'+rowid.length+'_txtWwdtWhy').val());
						return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
			 	 }
		 		 else
			 	 {
		 			 alert("Enter Why");
					 jQuery("#yyGrid").jqGrid('editCell',rowid.length,0,true);	
					 return false;
			 	}
			 }
 			 else
 	 		 {
 				 jQuery("#txtWwmsRootcause").val(why);
 				 return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
 	 		 }
 	 	  }
		  
		
	}
	else
	{
		
		var rowid = jQuery("#yyGrid").jqGrid('getDataIDs');
	    var ans = jQuery("#yyGrid").jqGrid('getCell',rowid.length,'txtWwdtAction');
	    var ansCol = jQuery("#yyGrid").jqGrid('getCell',rowid.length,'txtWwdtAnswer');
		var rootCauseVal = jQuery("#hdnWwmsRootcauseid").val();

		 if(jQuery("#hdnRCID").val() != null && jQuery("#hdnRCID").val() != '')
			 rootCauseVal = jQuery("#hdnRCID").val();			

		var pillar = jQuery("#hdnwwmsPillarmode").val();

		 /*if(jQuery("#hdnIsJh").val() != null && jQuery("#hdnIsJh").val() != '')
			 pillar = jQuery("#hdnIsJh").val();
		 if(jQuery("#hdnIsPM").val() != null && jQuery("#hdnIsPM").val() != '')
			 pillar = jQuery("#hdnIsPM").val();
	     if(jQuery("#hdnIsCI").val() != null && jQuery("#hdnIsCI").val() != '')
	    	 pillar = jQuery("#hdnIsCI").val();
		 if(jQuery("#hdnIsET").val() != null && jQuery("#hdnIsET").val() != '')
			 pillar = jQuery("#hdnIsET").val();*/
			
	
	
		  if(ans == null || ans == ' ' || ans == '') {
			  
			  alert("Enter Action For Why Why");
			 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
			  jQuery("#yyGrid").jqGrid('editCell',rowid.length,2,true);	
			  return false;
		  }
 		  if(ansCol == null || ansCol == ' ' || ansCol == '') {
			  
			  alert("Enter Answer For Why Why");
			 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
			  jQuery("#yyGrid").jqGrid('editCell',rowid.length,1,true);	
			  return false;
		  }
		  if(rootCauseVal == null || rootCauseVal == ' ' || rootCauseVal =='')	
		  {
			  alert("Select RootCause");
			  return false;
		  }	 
		  if(pillar == null || pillar == ' ' || pillar =='')	
		  {
			  alert("Select Counter Measure");
			  return false;
		  }
		  else
			  {
			  if(jQuery('#'+rowid.length+'_txtWwdtAction').val() != '' && jQuery('#'+rowid.length+'_txtWwdtAction').val() != null && jQuery('#'+rowid.length+'_txtWwdtAction').val() != ' ') 
					return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
			  else if(jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != '' && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != null && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != ' ') 
				return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
			  else
			   {
				  if(ansCol == null || ansCol == ' ' || ansCol == '') {
					  
					  alert("Enter Answer For Why Why");
					 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
					  jQuery("#yyGrid").jqGrid('editCell',rowid.length,1,true);	
					  return false;
				  }
			  	   else if(ans == null || ans == ' ' || ans == '') {
					  
					  alert("Enter Action For Why Why");
					 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
					  jQuery("#yyGrid").jqGrid('editCell',rowid.length,2,true);	
					  return false;
				  }
				  else
					  {
					 	 if(ansCol.substring(0,1) == '<')
						 {
					 		 if(jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != '' && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != null && jQuery('#'+rowid.length+'_txtWwdtAnswer').val() != ' ') 
									return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
					 		 else
						 		 {
					 			  	 alert("Enter Answer For Why Why");
									 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
									  jQuery("#yyGrid").jqGrid('editCell',rowid.length,1,true);	
									  return false;
						 		 }
						 }
						 else if(ans.substring(0,1) == '<')
						 {
					 		 if(jQuery('#'+rowid.length+'_txtWwdtAction').val() != '' && jQuery('#'+rowid.length+'_txtWwdtAction').val() != null && jQuery('#'+rowid.length+'_txtWwdtAction').val() != ' ') 
									return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
					 		 else
						 		 {
					 			  	 alert("Enter Action For Why Why");
									 // var row = jQuery("#yyGrid").jqGrid('getDataIDs');
									  jQuery("#yyGrid").jqGrid('editCell',rowid.length,2,true);	
									  return false;
						 		 }
						 }
					 	 else
					  		return 'YYAnalysis='+convertGridToJSONArr('yyGrid');
					  }
					 
					 
				  }
			  }
	}
}
function frmWhywhyAnalysis1_successsCallback(result)
{
	var openForm = result.OpenForm;	
	var msg = "Do You Want to open the Counter Measure?";
	//alert("openForm:"+openForm);
	 if( openForm != null && openForm == "Design")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;	
		 if(confirm(msg) == true)
		 {						  
			navigateToNextForm('kaizen_input.kaizen?closeOnSave=true&','Kaizen',forwardData,persistentData );
		 }
		 else
			 navigateToPrevForm();
	 }
	 else if( openForm != null && openForm == "TRN")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;	
		 if(confirm(msg) == true)
		 {	
			 navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );				
		 }	
		 else
			 navigateToPrevForm();
		 
	 }
	 else if( openForm != null && openForm == "ET")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;	
		 if(confirm(msg) == true)
		 {	
			 navigateToNextForm('WhyWhyTrainingList_input.why','One Point Lesson',forwardData,persistentData );
			// navigateToNextForm('modify_input.opl','One Point Lesson',forwardData,persistentData );	
		 }	
		 else
			 navigateToPrevForm();
	 }
	 else if( openForm != null && openForm == "JH")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;	
		 if(confirm(msg) == true)
		 {				 
			navigateToNextForm('jhClit_input.jhclit','JH Clit Standard',forwardData,persistentData );
		 }
		 else
			 navigateToPrevForm();
	 }
	 else if( openForm != null && openForm == "PM")
	 {
		 var persistentData = result.persistentData;
		 var forwardData = result.forwardData;	
		 if(confirm(msg) == true)
		 {	
			navigateToNextForm('preventive_input.prv','PM Standards',forwardData,persistentData );
		 }
		 else
			 navigateToPrevForm();		 
	 }
	 else if( openForm != null && openForm == "OJ")
	 {
		 closePopUp();
		 navigateToPrevForm();
	 }
	 else if( openForm != null && openForm == "SO")
	 {
		 closePopUp();
		 navigateToPrevForm();
	 }	 
	 else{
		 //navigateToNextForm('WhyWhyTrainingList_input.why','Training Program List',forwardData,persistentData );
		 navigateToPrevForm();
		 
	 }
	
}
function frmWhywhyAnalysis1_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	
	if(keyIds.machId != null && keyIds.machId != '' && keyIds.machId != ' ')
		setTimeout(function() {setFieldValue('cmbWwmsMachineid',keyIds.machId);},1250);
	reloadMachine("frmWhywhyAnalysis1",'cmbWwmsMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	var formType = jQuery('#hdnformType').val();
	if(screen.width >=1250)
	{
		if(formType == 'SHE')
			jQuery('#dispFunctionalLoc').css('width','74%');
		else
			jQuery('#dispFunctionalLoc').css('width','88%');
	}
	else
		jQuery('#dispFunctionalLoc').css('width','88%');
}
		

</script>
<form id="frmWhywhyAnalysis1" name="frmWhywhyAnalysis1" >
<div id="wrapper" style="width:100%">
<table border="0" align="left" id="whwhyTbl" style="margin-left:5%;" width="100%">
 <tr>
	<td colspan="4"><!--style="width:11%;" valign='top'>-->
<!--		<div style="padding-left:40px;">-->
		<div class="yy-body">
			<label >Ref Doc No.</label>
			<label style="padding-left:92px;padding-left:80px\9;">YY Master No</label>
		</div>
<!--		<div class="easyui-paddingbfpx" style="padding-left:40px;">-->
		<div class="easyui-paddingbfpx yy-body">
			<input id="txtWwmsRefdocno" type="text" class="easyui-text" name="txtWwmsRefdocno" value="${requestScope.kznWhyWhyMst.wwmsRefdocno}" style="width: 15%; height : 21px;" />
			<input id="cmbWwmsKeyid" class="easyui-combobox" name="cmbWwmsKeyid" value="${requestScope.kznWhyWhyMst.wwmsKeyid}" style="width: 130px; height : 21px;" >
			<span id="yyDate">
				<input id="dteWwmsDate" name="dteWwmsDate" value="${requestScope.kznWhyWhyMst.wwmsDate}" class="easyui-datebox" />
			</span>
			<input id="chkWwmsSparesreplaced" name="chkWwmsSparesreplaced" value="Y" type="checkbox"/>  <label id="lblsprReplaced">Spares Replaced</label>
            <span  style="margin-left: 2px;"> <input id="sparesReplaced"  type="checkbox" /> <label id="lblsprNotReplaced" >Spares Not Replaced</label></span>
            <input id="hdnSparesVal" type="text" name="hdnSparesVal" value="${requestScope.kznWhyWhyMst.wwmsSparesreplaced}" class="easyui-text" style="display:none;"/> 
		</div>
	</td>
<!--	<td style="width:15%;" valign='top'>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>YY Master No</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="cmbWwmsKeyid" class="easyui-combobox" name="cmbWwmsKeyid" value="${requestScope.kznWhyWhyMst.wwmsKeyid}" style="width: 130px; height : 21px;" >-->
<!--			<input id="dteWwmsDate" name="dteWwmsDate" value="${requestScope.kznWhyWhyMst.wwmsDate}" class="easyui-datebox" />-->
<!--		</div>-->
<!--	</td>-->
<!--	<td  style="width:15%;">-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="chkWwmsSparesreplaced" name="chkWwmsSparesreplaced" value="Y" type="checkbox"/>  <label>Spares Replaced</label>-->
<!--            <span  style="margin-left: 2px;"> <input id="sparesReplaced"  type="checkbox" /> <label>Spares Not Replaced</label></span>-->
<!--            <input id="hdnSparesVal" type="text" name="hdnSparesVal" value="${requestScope.kznWhyWhyMst.wwmsSparesreplaced}" class="easyui-text" style="display:none;"/>-->
<!--		</div>-->
<!--	</td>-->
<!--	<td style="width:15%"></td>-->
 </tr>
 <tr>
 	<td colspan="4"> 	
 		<div  class="easyui-paddingbfpx yy-body">
 		<div id="frmWhywhyAnalysis1FuntKeyIds">
						<input type="hidden" id="factory" name="cmbWwmsFactoryid" value="${requestScope.kznWhyWhyMst.wwmsFactoryid}"  ></input>
						<input type="hidden" id="section" name="cmbWwmsSectionid" value="${requestScope.kznWhyWhyMst.wwmsSectionid}" ></input>
						<input type="hidden" id="cell" name="cmbWwmsCellid" value="${requestScope.kznWhyWhyMst.wwmsCellid}"></input>
						<input type="hidden" id="machine" name="cmbWwmsMachineidhdn" value="${requestScope.kznWhyWhyMst.wwmsMachineid}"   ></input>
		</div>
		<div id="wwmsfunLocation" style=""></div>
		</div>
  	</td>
 </tr>
  <tr id="sheYYFields">
 	<td colspan="4"> 
 		<div class="yy-body">
			<label class="yyLbl-descOfAccid">Description of Accident/Incident</label>		
			<label class="yyLbl-AccidentPhen">Accident Phenomena</label>
			<label class="yyLbl-EffectivenessChb">Effectiveness</label>
			<label class="yyLbl-Phm">Effectiveness Details</label>
		</div>

		<div class="easyui-paddingbfpx yy-body">
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;height:75px;resize:none;" cols="" id="txtWwmsAccidentdesc" name="txtWwmsAccidentdesc">${requestScope.kznWhyWhyMst.wwmsAccidentdesc}</textarea>
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;height:75px;resize:none;" cols="" id="txtWwmsAccidentphen" name="txtWwmsAccidentphen">${requestScope.kznWhyWhyMst.wwmsAccidentphen}</textarea>
			<span class="yy-effectivenessSpan">
				<input id="chkEffective" name="chkEffective" value="Y" type="checkbox"/>  <label id="lblchkEffective">Effective</label>
           	    <span  style="margin-left: 2px;"> <input id="chkNotEffective" name="chkNotEffective" type="checkbox" /> <label id="lblchkNotEffective" >Not Effective</label></span>
           	</span>
            <input id="hdnWwmsIseffective" type="hidden" name="hdnWwmsIseffective"  class="easyui-text"  value="${requestScope.kznWhyWhyMst.wwmsIseffective}"/>
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;height:75px;resize:none;" cols="" id="txtWwmsPreveffective" name="txtWwmsPreveffective">${requestScope.kznWhyWhyMst.wwmsPreveffectiveness}</textarea>
		</div>
 	</td>
 </tr>
 <tr id="sheprevDetails">
 	<td colspan="4">
 		<div style=" border: 1px solid #92959A;width:402px;">
	 		<label class="yyLbl-descOfAccid">Previous Occurence</label>
	 		<div class="yy-body">
				<label class="yyLbl-shePrevDate">Date</label>		
				<label class="yyLbl-shePrevNo">No.</label>
				<label>Person</label>			
			</div>
			<div class="easyui-paddingbfpx yy-body">
				<input id="dteWwmsPrevdat" name="dteWwmsPrevdat" style="width: 120px;"  value="${requestScope.kznWhyWhyMst.wwmsPrevdate}" class="easyui-datebox" />
				<input id="txtWwmsPrevno" type="text" class="easyui-text" name="txtWwmsPrevno"  style="width: 73px; height : 21px;" value="${requestScope.kznWhyWhyMst.wwmsPrevno}"/>
				<input id="cmbWwmsPrevperson" type="text" class="easyui-combobox" name="cmbWwmsPrevperson" style="width:195px;" value="${requestScope.kznWhyWhyMst.wwmsPrevperson}">
			</div>
		</div>
		<span class="yy-completeSheYYBtn">
				<input type="button" class="easyui-button"  style="width:190px; margin-top:30px\9;margin-left:-8px\9;" value="Complete Why-Why Analysis" id="btnEndSheYY"/>
		</span>
 	</td>
 </tr>
 <tr id="eqpStatPhenCause">
<!-- 	<td style="width:20%;">-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:30px;">-->
<!--			<label>Equipment</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:30px;">-->
<!--			<input id="cmbWwmsMachineid" class="easyui-combobox" name="cmbWwmsMachineid" value="${requestScope.kznWhyWhyMst.wwmsMachineid}"  style="width: 255px; height : 21px;" >-->
<!--		</div>-->
<!--	</td>-->
<!--	<td style="width:20%;">-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Assembly</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="cmbWwmsAssemblyid" class="easyui-combobox" name="cmbWwmsAssemblyid" value="${requestScope.kznWhyWhyMst.wwmsAssemblyid}"  style="width: 255px; height : 21px;" >-->
<!--		</div>-->
<!--	</td>-->
	<td colspan="4" >
		<div class="yy-body">
			<label class="yyLbl-Eqp" id="lblEqp">Equipment</label>			
			<label class="yyLbl-station" id="lblAsm">Assembly/Station</label>
			<label class="yyLbl-Phm" id="lblPhn" style="margin-left:3px\9;">Phenomena</label>
			<label id="lblCause" style="margin-left:3px\9;">Cause</label>
		</div>
		<div class="easyui-paddingbfpx yy-body">
			<input id="cmbWwmsMachineid" class="easyui-combobox" name="cmbWwmsMachineid" value="${requestScope.kznWhyWhyMst.wwmsMachineid}"  style="width: 200px; height : 21px;" >
			
			<span id="asmPhnCause">
			<input id="cmbWwmsAssemblyid" class="easyui-combobox" name="cmbWwmsAssemblyid" value="${requestScope.kznWhyWhyMst.wwmsAssemblyid}"  style="width: 200px; height : 21px;" >
			<input id="cmbWwmsPhenomenaid" class="easyui-combobox" name="cmbWwmsPhenomenaid" value="${requestScope.kznWhyWhyMst.wwmsPhenomenaid}"  style="width: 200px; height : 21px;" >
			<input id="cmbWwmsCauseid" class="easyui-combobox" name="cmbWwmsCauseid" value="${requestScope.causeId}"  style="width: 200px; height : 21px;" >
			</span>
		</div>
	</td>
<!--	<td style="width:20%">-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Cause</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="cmbWwmsCauseid" class="easyui-combobox" name="cmbWwmsCauseid" value="${requestScope.causeId}"  style="width: 200px; height : 21px;" >-->
<!--		</div>-->
<!--	</td>-->
</tr>
<tr id="faPrevHD">
<!--	<td>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:30px;">-->
<!--			<label>Final Action</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:30px;">-->
<!--			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;" cols="" id="txaWwmsFinalaction" name="txaWwmsFinalaction">${requestScope.kznWhyWhyMst.wwmsFinalaction}</textarea>-->
<!--		</div>-->
<!--	</td>-->
<!--	<td>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Checks Made</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;" cols="" id="txtWwmsChecksmade" name="txtWwmsChecksmade">${requestScope.kznWhyWhyMst.wwmsChecksmade}</textarea>-->
<!--		</div>-->
<!--	</td>-->
	<td colspan="4">
	<span id="faInBD">
		<div class="yy-body">
			<label  class="yyLbl-PhmFa" id="lblYY">Final Action</label>
			<label  class="yyLbl-POeff" id="lblChecksMade">Previous Occurence Date</label>
			<label  class=yyLbl-Effective" id="lblYYNotOk" style="padding-left:2px\9;">Effectiveness(why not OK)</label>
			<label class="yy-LblIsKznReq" id="lbl4mType" >4m Type </label>
<!--			<input id="chb4mType"  type="checkbox" /></label>-->
<!--			<input type="hidden" id="hdnWwmsFormtype" name="hdnWwmsFormtype" value="${requestScope.kznWhyWhyMst.wwmsFormtype}"/>-->
<!--			<label  class="yyLbl-PO" id="lblYouDidNot">Counter Measure Details</label>-->
<!--			<label id="lblSustAction">Previous Occurence</label>-->
<!--			<label  class="yyLbl-FA" id="lblChecksMade">Checks Made</label>-->
<!--			<label  class="yyLbl-FA" id="lblYouDidNot">You Did Not</label>-->
<!--			<label id="lblSustAction">Sustenance Action</label>-->
		</div>
		<div class="easyui-paddingbfpx yy-body">
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;margin-top:-80px\9;resize:none;" cols="" id="txaWwmsFinalaction" name="txaWwmsFinalaction">${requestScope.kznWhyWhyMst.wwmsFinalaction}</textarea>
			
			<span style="vertical-align:59px;" id="dateCol">
				<input id="dteWwmsPrevdate" name="dteWwmsPrevdate" style="width: 200px;" " value="${requestScope.kznWhyWhyMst.wwmsPrevdate}" class="easyui-datebox" />
			</span>
			<span>
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;margin-right:8px;margin-top:-80px\9;" cols="" id="txtWwmsPreveffectiveness" name="txtWwmsPreveffectiveness">${requestScope.kznWhyWhyMst.wwmsPreveffectiveness}</textarea>
			</span>
			<span id="fourMSpn" style="vertical-align:59px;">
				<select id="cmbfourMType" class="easyui-combobox" name="cmbfourMType"   style="height: 22px;width:95px;">
								<option value=" "> </option>
								<option value="MEN">MEN</option>
								<option value="MET">METHOD</option>	
								<option value="MAT">MATERIAL</option>	
								<option value="MAC">MACHINE</option>								
				</select>
				<input type="hidden" id="hdnWwmsFormtype" name="hdnWwmsFormtype" value="${requestScope.kznWhyWhyMst.wwmsFormtype}"/> 
			</span>
			<span style="vertical-align:59px;">
<!--			<div class="yy-hd" style=" ">-->
				<label style="" id="lblHD">Horizontal Deployment</label>
				<span style="padding-left:1px;">
					<input id="chbWwmsIshdpossible" name="chbWwmsIshdpossible" type="checkbox" value="Y"/>
					<input type="hidden" id="hdnWwmsishd" name="hdnWwmsishd" value="${requestScope.kznWhyWhyMst.wwmsIshdpossible}"/>
				</span>
<!--		</div>-->
		   </span>
		   </span>
<!--		  <span id="custCompFields" style="display:none;margin-top:-70px;">-->
<!--		  <div id="lblCustCompFields" class="yy-body">-->
<!--	 		-->
<!--			-->
<!--		 </div>-->
<!--		 </span>-->
			<span class="yy-completeYYBtn">
				<input type="button" class="easyui-button"  style="width:190px;margin-top:30px\9;margin-left:-8px\9;"  value="Complete Why-Why Analysis" id="btnEndYY"/>
			</span>
<!--			<input id="dteWwmsDate" name="dteWwmsDate" value="${requestScope.kznWhyWhyMst.wwmsDate}" class="easyui-datebox" style="width:200px;"/>-->
<!--			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;" cols="" id="txtWwmsChecksmade" name="txtWwmsChecksmade">${requestScope.kznWhyWhyMst.wwmsChecksmade}</textarea>-->
<!--				<span style="border: 1px solid #a4a4a4;;height:18%;position:absolute;padding:3px;">-->
<!--				<div>-->
<!--						-->
<!--						<label  class="yyLbl-Eff" id="lblChecksMade">Date</label>-->
<!--				</div>-->
				
<!--					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="1" style="width: 200px;resize:none;margin-top:0px;" cols="" id="txtWwmsPreveffectiveness" name="txtWwmsPreveffectiveness">${requestScope.kznWhyWhyMst.wwmsPreveffectiveness}</textarea>-->
<!--				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;" cols="" id="txtWwmsCountermeasure" name="txtwwmsCountermeasure">${requestScope.kznWhyWhyMst.wwmsCountermeasure}</textarea>-->
					
				
<!--			</span>-->
			
			
<!--			<input type="button" class="easyui-button" id="btnDelYY" value="Delete" style="float:right;position:fixed;margin-left:5px;">-->
		</div>
	</td>
	<td colspan="4">
	
	</td>
</tr>
<!--	<td>-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Sustenance Action</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;resize:none;" cols="" id="txtWwmsCountermeasure" name="txtwwmsCountermeasure">${requestScope.kznWhyWhyMst.wwmsCountermeasure}</textarea>-->
<!--		</div>-->
<!--	</td>-->
		
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Sub Unit</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="cmbWwmsSectionid" class="easyui-combobox" name="cmbWwmsSectionid" value="${requestScope.kznWhyWhyMst.wwmsSectionid}"  style="width: 200px; height : 21px;" >-->
<!--		</div>-->
<!--		-->
<!--		<div  class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<label>Section</label>-->
<!--		</div>-->
<!--		<div class="easyui-paddingbfpx" style="padding-left:20px;">-->
<!--			<input id="cmbWwmsCellid" class="easyui-combobox" name="cmbWwmsCellid" value="${requestScope.kznWhyWhyMst.wwmsCellid}"  style="width: 200px; height : 21px;" >-->
<!--		</div>-->
<tr>
 	<td colspan="4">
 		<div  class="floatleft easyui-paddingbfpx yy-body">
 		<span id="YYGridForBD">
			<table id="yyGrid" style="width:100%"><tr><td/></tr></table>
			<div id="yyPager"></div>
		</span>
		<span id="YYGridForKaizen">
			<table id="yyKaizenGrid" style="width:100%"><tr><td/></tr></table>
			<div id="yyKaizenPager"></div>
		</span>
		</div>
	</td>
</tr>
<tr>
	<td  valign='top' class="yy-rootCauseTD">
	<div  class="floatleft easyui-paddingbfpx yy-body">
	   <span id="tempforAbn">
	   </span>
	<span id="rootCauseGridinBD">
		<div class="sub-header">Root Cause</div>
		<table id="rootPillarGrid" width="400px">
        </table>
         <div id="rootPillarPager"></div>   
     </span>     	
     <span id="rootCauseinKaizen">
     	<label class="mandatory-lbl">Root Cause</label>
     	<div class="easyui-paddingbfpx">
     		<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 435px;height:75px;resize:none;" cols="" id="txtWwmsRootcause" name="txtWwmsRootcause">${requestScope.kznWhyWhyMst.wwmsRootcause}</textarea>
     	</div>
     </span>
         </div>
     
	</td>
	<td  valign='top' class="yy-pillarTD">
		<div  class="easyui-paddingbfpx yy-pillarDisp" style="float:left; width : 391px;">
			<span id="pillarGridinBD">
				<div class="sub-header" id="counterMsrGridHdr" style="width:385px;">Counter Measure</div>
				<table id="PillarGrid" width="" style="float: left;"></table>
		        <div id="PillarPager"></div>   
    	    </span>
    	    <span id="ActionTakenInShe" style="margin-left:3px;">
				<label>Action Taken</label>
				<div class="easyui-paddingbfpx">
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" style="width:435px;height:75px;resize:none" name="txtWwmsCountermeasure" id="txtWwmsCountermeasure">${requestScope.kznWhyWhyMst.wwmsCountermeasure}</textarea>
				</div>		
			</span>
    	    
		</div>
	</td>
	<td  valign='top'  class="yy-cmTD"  style="margin-left:3.3%;margin-left:-1%\9;">
	     <span id="kaizenDetailsInBD">
				<div class="sub-header yy-cmdHeader">Counter Measure Details</div>
				<div class="easyui-paddingbfpx" style="padding-top:0px;">
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" style="width:200px;height:110px;resize:none" name="txtWwmsCountermeasure" id="txtWwmsCountermeasure">${requestScope.kznWhyWhyMst.wwmsCountermeasure}</textarea>
				</div>
		<!--		<div>-->
		<!--			<label>Incharge</label>-->
		<!--		</div>-->
		<!--		<div class="easyui-paddingbfpx" >-->
		<!--			<input id="cmbWwmsMaintinchargeid" class="easyui-combobox" name="cmbWwmsMaintinchargeid" value="${requestScope.kznWhyWhyMst.wwmsMaintinchargeid}"  style="width: 200px; height : 21px;" >-->
		<!--		</div>-->
				
			</span>
	
	<span id="CounterMeasureInKaizen">
		<label id="lblCMKaizen">Counter Measure</label>
		<div class="easyui-paddingbfpx">
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" style="width:435px;height:75px;resize:none" name="txtWwmsCountermeasure" id="txtWwmsCountermeasure"></textarea>
		</div>		
	</span>
<!--	<span id="customerComplaint" style="display:none;">-->
<!--		<label  id="lblisKZN" >Is Kaizen Required</label> <input id="chbisKZNYes"  type="checkbox" /><label> Yes</label><input id="chbisKZNNo" type="checkbox" /><label > No</label>-->
<!--		 <div  class="easyui-paddingbfpx">-->
<!--			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;height:45px;resize:none;" cols="" id="txtWwmsCountermeasure" name="txtWwmsCountermeasure">${requestScope.kznWhyWhyMst.wwmsCountermeasure}</textarea>-->
<!--			<input type="hidden" id="hdnWwmsIskzn" name="hdnWwmsIskzn" value="${requestScope.kznWhyWhyMst.wwmsIskzn}"/>-->
<!--		 </div>-->
<!--		  <div  class="easyui-paddingbfpx">-->
<!--		  	<label class="lblCause" id="lblisPY" >Is Poka Yoke Reqd.</label> <input id="chbisPYYes"  type="checkbox" /><label> Yes</label><input id="chbisPYNo"  type="checkbox" /><label> No</label>-->
<!--		  </div>-->
<!--		  <div  class="easyui-paddingbfpx">-->
<!--				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 200px;height:45px;resize:none;" cols="" id="txtWwmsPokayoke" name="txtWwmsPokayoke">${requestScope.kznWhyWhyMst.wwmsPokayoke}</textarea>-->
<!--				<input type="hidden" id="hdnWwmsIspokayoke" name="hdnWwmsIspokayoke" value="${requestScope.kznWhyWhyMst.wwmsIspokayoke}"/>-->
<!--		 </div>-->
<!--	</span>-->
	
	</td>
</tr>

</table>
<input id="hdnWwmsRootcauseid" name="hdnWwmsRootcauseid" style="display:none" value="${requestScope.rcId}"/>
<input id="hdnWwmsRootcause" name="hdnWwmsRootcause" style="display:none"/>
<input id="hdnwwmsPillarmode" name="hdnwwmsPillarmode" style="display:none"/>
<input id="hdnAddRowFlag" name="hdnAddRowFlag" style="display:none"/>
<input id="hdnWwmsFactoryid" name="hdnWwmsFactoryid"  style="display:none" value="${requestScope.kznWhyWhyMst.wwmsFactoryid}"/>
<input type="hidden" id="hdnRCID" name="hdnRCID" value="${requestScope.rcId}">
<input type="hidden" id="hdnIsJh" name="hdnIsJh" value="${requestScope.isJH}">
<input type="hidden" id="hdnIsPM" name="hdnIsPM" value="${requestScope.isPM}">
<input type="hidden" id="hdnIsCI" name="hdnIsCI" value="${requestScope.isCI}">
<input type="hidden" id="hdnIsET" name="hdnIsET" value="${requestScope.isET}">
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnformType" name="hdnformType" value="${requestScope.yyFormBean.formType}"/>
<input type="hidden" id="hdnCounterMsrFlag" name="hdnCounterMsrFlag" value="${requestScope.counterMeasureFlag}"/>
<input type="hidden" id="hdnWwmsOpenfrom" name="hdnWwmsOpenfrom" value="${requestScope.yyFormBean.formType}"/>
<input type="hidden" id="hdnDelYYId" name="hdnDelYYId"/>


</div>
	<div id="dlgOJTSOP" class="flPopUpBox">
		<div id="titleOJTSOP" class="fl-popUpHeader" style="width:366px;margin-left:-10px;">
			 <label id="lblOJTSOP" style="margin-left:1px;font-size:11px;"></label> 
			 <img id="imgCloseOJTSOP" src="images/cancel.png" style="float:right;"/>
		 </div>
		 <div  class="easyui-paddingbfpx" style="padding-left:10px;padding-top:10px;">
				<label>Remarks</label>                       
		</div> 
		<div class="easyui-paddingbfpx" id="ojtRemarks" style="padding-left:10px;"> 
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtWwmsOjtdesc" name="txtWwmsOjtdesc" style="width: 250px;height:100px;resize:none;"> </textarea>
		</div>
		<div class="easyui-paddingbfpx" id="sopRemarks" style="padding-left:10px;"> 
				<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" id="txtWwmsSopdesc" name="txtWwmsSopdesc" style="width: 250px;height:100px;resize:none;"> </textarea>
		</div>
		<div class="easyui-paddingbfpx" style="padding-left:10px;">
		 <input type="button" class="easyui-button" style=" width : 50px;" id="btnOJTSOP" name="btnOJTSOP" value="OK"/>
		</div>
		
	</div>
</form>
		