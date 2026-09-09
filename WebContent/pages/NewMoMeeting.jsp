<script>
var glbType = "";
/*Created By Sathish Kumar .v */
 
    jQuery(document).ready(function(){ 
           	initialiseForm('frmNewMom');				
			jQuery('#submitForm').val('frmNewMom');
						
			var mode = jQuery("#frmNewMom input[id=mode]").val();
			if(screen.width <= 1024)
			{
				jQuery('#meetinghppnd').css('padding-left','174px');
				jQuery('#meetinghppnd').css('width','180px');
				jQuery('#processsmeetinghppnd').css('padding-top','14px');
				jQuery('#MessageBoard').css('padding-right','70px');
				
             }
			
			//reloadCombo("frmNewMom","cmbMomsEmployee","rolebasedemployee.mom?&roleId="+roleId+"&flid="+flid);
            var loginFlid =jQuery("#hdnloginFlid").val();
           // alert(loginFlid);
      	  //  var roleId =getFieldValue('cmbMomsRole');
      	    //alert(roleId);
      	    formatDateBox('dteMomsDate', 'dd-MMM-yyyy');
			formatDateBox('dteMomaDate', 'dd-MMM-yyyy');
			fillComboBox("frmNewMom","cmbMomsShiftid","shift.commonFilter");
			fillComboBox("frmNewMom","cmbdEmployee","employee.commonFilter");
			fillComboBox("frmNewMom","cmbdprtmntname","department.rsrsk");	
			fillComboBox("frmNewMom","cmbMomsMeetingtype","MeetingType.mom","",false);
			fillComboBox("frmNewMom","cmbMomsPillarid","pillar.commonFilter");
			fillComboBox("frmNewMom","cmbMomsPillargroup","pillargroup.mom");
			fillComboBox("frmNewMom","cmbMomsRole","Rolecombo.nmom");
			fillComboBox("frmNewMom","cmbMomsEmployee","rolebasedemployee.mom");
			var masterrefid=jQuery("#txtAplmMasterrefid").val();
		    var refdoctype=jQuery("#txtAplmRefdoctype").val();
			glbType=jQuery('#hdntype').val();
			var Type=jQuery('#hdntype').val();
			if(Type=="JH"){
				  setFieldValue('cmbMomsMeetingtype',"J");
				  disableField("frmNewMom", "cmbMomsMeetingtype");
				  readOnlyFields("cmbMomsPillarid");
				  jQuery('#SafetyTalk').addClass("mandatory-lbl");
				  jQuery('#Agenda').addClass("mandatory-lbl");    // Added By Swetha on 25Oct2025
				  jQuery('#pillargrouplblid').hide();
				  jQuery('#spnPillargroup').hide();
			}else if(Type=="Dmt"){
				   setFieldValue('cmbMomsMeetingtype',"D");
				   disableField("frmNewMom", "cmbMomsMeetingtype");
				   readOnlyFields("cmbMomsPillarid");
				   jQuery("#cmbMomsShiftid").hide();
				   jQuery("#spnShift").hide();
                   jQuery("#lblShift").hide();
                   readOnlyFields("cmbMomsShiftid");
                   jQuery('#Agenda').addClass("mandatory-lbl");    // Added By Swetha on 25Oct2025
                   jQuery('#pillargrouplblid').hide();
                   jQuery('#spnPillargroup').hide();
			}else if(Type=="Pillar"){
				   setFieldValue('cmbMomsMeetingtype',"P");
				   disableField("frmNewMom", "cmbMomsMeetingtype");
				   jQuery('#pillarlblid').addClass("mandatory-lbl");
                   enableFields("cmbMomsPillarid");
                   jQuery("#spnShift").hide();
                   jQuery("#lblShift").hide();
                   readOnlyFields("cmbMomsShiftid");
                   jQuery('#Agenda').addClass("mandatory-lbl");    // Added By Swetha on 25Oct2025
                   
			}else if(Type=="Production" || Type=="Others"){
				  if(Type=="Others"){
				   	setFieldValue('cmbMomsMeetingtype',"O");
				  	jQuery('#Agenda').addClass("mandatory-lbl");    // Added By Swetha on 25Oct2025
				  }
				  else{
					setFieldValue('cmbMomsMeetingtype',"PD");
				    disableField("frmNewMom", "cmbMomsMeetingtype");
				    readOnlyFields("cmbMomsPillarid");
				    readOnlyFields("cmbMomsShiftid");
				  }
        		    hideProductionNotRelatedFields();
				   
			}else if(Type=="CEC" && glbType=="CEC"){
				setFieldValue('cmbMomsMeetingtype',"CEC");
				setFieldValue('cmbMomsPillarid',"TGT007");
				readOnlyFields("cmbMomsPillarid");
				readOnlyFields("cmbMomsMeetingtype");
				//frmNewMomcmbMomsPillarid_onSelect();
				frmNewMomcmbMomsPillargroup_onSelect();
				jQuery('#Agenda').addClass("mandatory-lbl");
			    jQuery("#cmbMomsShiftid").hide();
 			    jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                jQuery("#btnjhActivity").hide();
                readOnlyFields("cmbMomsShiftid");
                fnFillAttendanceGrid();
            	setFieldValue('cmbMomsPillarid',"TGT007");
			}else if(Type=="DEC" && glbType=="DEC"){
				setFieldValue('cmbMomsMeetingtype',"DEC");
				setFieldValue('cmbMomsPillarid',"TGT007");
				readOnlyFields("cmbMomsPillarid");
				readOnlyFields("cmbMomsMeetingtype");
				jQuery("#btnjhActivity").hide();
				fnFillAttendanceGrid();
				jQuery('#Agenda').addClass("mandatory-lbl");
			    jQuery("#cmbMomsShiftid").hide();
 			    jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                readOnlyFields("cmbMomsShiftid");
				frmNewMomcmbMomsPillargroup_onSelect();
				setFieldValue('cmbMomsPillarid',"TGT007");
				//frmNewMomcmbMomsPillarid_onSelect();
			}else if(Type=="FIP"){
				disableField("frmNewMom", "cmbMomsPillarid");
			}
			else if(Type=="UMC"){
				//alert("isnide the if");
			    setFieldValue('cmbMomsMeetingtype',"UMC");
			    disableField("frmNewMom", "cmbMomsMeetingtype");
			    readOnlyFields("cmbMomsPillarid");
               // enableFields("cmbMomsPillarid");
                jQuery("#spnShift").hide();
                jQuery("#lblShift").hide();
                readOnlyFields("cmbMomsShiftid");
               // fnFillAttendanceGrid();
				jQuery('#Agenda').addClass("mandatory-lbl");
                
			}
			
			else if(Type=="OGM"){
				   setFieldValue('cmbMomsMeetingtype',"OGM");
				   disableField("frmNewMom", "cmbMomsMeetingtype");
				   readOnlyFields("cmbMomsPillarid");
             jQuery("#spnShift").hide();
             jQuery("#lblShift").hide();
             readOnlyFields("cmbMomsShiftid");
             
			}
			
			var MstKeyid=jQuery('#txtMomsKeyid').val();	
			var recall=jQuery('#hdnrecall').val();
			var momdate = jQuery("#dteMomsDate").datebox("getValue");
			var flid = jQuery("#frmNewMom input[id='flid']").val();
			var shift= getFieldValue("cmbMomsShiftid");
			if(mode.trim().length>0 && mode=="view")
				processGridnew("NewMoMeetingMom_input.nmom","?q=2&keyid="+MstKeyid+"&type="+glbType+"&mode=view", "momGrid", "pagermom","","","","loadCompleteAction");
		    else if (recall.trim().length<=0 )
				processGridnew("NewMoMeetingMom_input.nmom","?q=2&keyid="+MstKeyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");			
			setFunctionalLocWidth('frmNewMom','600px');
			
			jQuery('#chkMomsIsmeetinghappen').prop('checked', true);

			readOnlyFields("txtMomsMeetingno");

               var meetingtitle=getFieldValue("txtMomsMeetingtitle", "frmNewMom"); 
               var meethppnd=jQuery('#hdnmeethappnd').val();

			if(meethppnd=="No"){
				jQuery('#chkMomsIsmeetinghappen').prop('checked', false);
				chkboxmeetUnCheck();
			}

			var keyid = jQuery('#txtMomsKeyid').val();   // get key id 	
			if(keyid.length==0){
               fillWithCurrentDate('dteMomsDate');
               }else
                {
               	var val = getFieldValue("cmbMomsMeetingtype", "frmNewMom");
               	
               	var pasdate= getFieldValue("dteMomsDate", "frmNewMom");
               	var currentDate = getServerDateTime();
               	
                   if(val=="P"){
                    jQuery('#pillarlblid').addClass("mandatory-lbl");
                    enableFields("cmbMomsPillarid");
                   }else{
                    jQuery('#pillarlblid').removeClass("mandatory-lbl");
                    readOnlyFields("cmbMomsPillarid");
                   }
                   
                   jQuery('#btnExcelVw').show();
                   jQuery('#btnMomMail').show();
               }
			
			var locationId = jQuery("#frmNewMom input[id='location']").val();
			var factId = jQuery("#frmNewMom input[id='factory']").val();
		    var sectionId = jQuery("#frmNewMom input[id='section']").val();
		    var cellId = jQuery("#frmNewMom input[id='cell']").val();
		    
			var machId = jQuery("#frmNewMom input[id='machine']").val();
            var flid = jQuery("#frmNewMom input[id='flid']").val(); 
        
		    var dataStr = "&factId=" + factId
							+ "&sectionId=" + sectionId
							+ "&cellId=" + cellId + "&machId="
							+ machId+"&flid="+ flid;
              
		    
		    var Type=jQuery('#hdntype').val();
			if (Type =='Pillar' || Type =='UMC' || Type =='OGM')
				dataStr += "&disable=N";
		    var funclocn="";
		    funclocn= "functionalLoc.nmom?&Type="+Type ;
		    dataStr += "&Type="+Type;
		    if(MstKeyid==null || MstKeyid.length < 5)
		    {	
		       fillcurrecntshift();
		    }
		    
		    loadFunctionalLocation("MomAttfunLocation", funclocn, "MomAttfunLocation", "frmNewMom",dataStr);

			jQuery("#btnAttView").click(function()
					 {
				       var Employee =getFieldValue('cmbdEmployee');
				       var Department =getFieldValue('cmbdprtmntname');	  
				       processGridnew("NewMoMeetingAtt_input.nmom","?q=2&keyid="+keyid+"&dept="+Department+"&emp="+Employee, "attandanceGrid", "pageratt");
				       	
					 }); 
		
		if( mode == "view"){
			disableForm();
			jQuery('#chkMomsOthers').prop("readonly",true);
		}	
		else {
				
			jQuery("#btnjhActivity").click(function(){
					LoadPopUp("divjhActivity","jhActivityMom_input.dashboard?", true, "91%", "66%", "12%", "3%", " ", "Jh Activity","",false);
				});
			jQuery("#btnAttsave").click(function()
			 {
				 
				jQuery('#hdnsavebtn').val("Y");
				var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox');
				saveForm('frmNewMom',"MoMeetingFormATT_save.mom?MomAtt="+MomAtt);										
					
			 }); 
			 jQuery("#btnaddOthers").click(function()
			    {
			        var keyid = jQuery('#txtMomsKeyid').val();
			        var Checkvist=jQuery('#hdnCheckvist').val("Y");
			        var shift= getFieldValue("cmbMomsShiftid");
					var flid =jQuery("#frmNewMom input[id='flid']").val();
					var momdate = getFieldValue("dteMomsDate", "frmNewMom");
					var glbType=jQuery('#hdntype').val();
					var recall=jQuery('#hdnrecall').val();
					var pillarid =getFieldValue('cmbMomsPillarid');
					
					var ds = "?&keyid="+keyid+"&shift="+shift+"&date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid+"&recall="+recall;
			        if(keyid.trim().length>0||(keyid.trim().length>0 && recall.trim().length>0))
				    {
			          LoadPopUp("AddVisitors","Newvisitor_input.nmom"+ds, true, "46%", "72%", "7%", "27%", " ", "Add Visitors","",false);
                            
					}else{

						     alert(" First Save MoM ");
                             return false;

						}			     
				       
			    });
			 
			 jQuery("#btnAddNew").click(function()
					 {
						 var masterKeyId=getFieldValue('txtMomsKeyid');
						// alert(masterKeyId);
						 var row  = jQuery("#momGrid").jqGrid('getDataIDs');
						// alert(row);
						 var rowId= jQuery("#momGrid").jqGrid('getRowData',row);
						// alert(rowId);
						 addRow(row);
					
						 
			     });

				   jQuery("#btnDelete").click(function()
				   	{ 
						 removeRecord();
					});
				   
				   
				   jQuery("#btnAddEmployee").click(function()
							{
				        	   jQuery('#hdnaddemp').val('Y');  
						       var Employee =getFieldValue('cmbMomsEmployee');
						       var Employeetext =jQuery("#cmbMomsEmployee").combobox("getText");
						       var empArr = Employeetext.split('-');
						       var row  = jQuery("#attandanceGrid").jqGrid('getDataIDs');
							   var masterKeyId=getFieldValue('txtMomsKeyid');
							   
							   processAjaxCalls("MoMeetingFillRole_modify.mom","emplyid="+Employee,"updateRoleSuccess","");
							  
							   var role=getFieldValue('cmbMomsRole');
							   var roletext =jQuery("#cmbMomsRole").combobox("getText");
							   var roleArr = roletext.split('-');
								 if(masterKeyId.length==0){ 
									 if(row.length==0)
										row=row+1; 
										 
								        addRowEmployee(row,Employee,empArr[1],empArr[0],roleArr[0]);
								      
									}
								 else{ 
									  if(row.length==0)
											row=row+1;
									 
									   addRowEmployee(row,Employee,empArr[1],empArr[0],roleArr[0]);
								     }
							   //} 
						  
							}); 
				          
				          jQuery("#btnAddAllEmployee").click(function()
				  				{
				  			       var Employee =getFieldValue('cmbMomsEmployee');
				  			       var Employeetext =jQuery("#cmbMomsEmployee").combobox("getText");
				  			       var row  = jQuery("#attandanceGrid").jqGrid('getDataIDs');
				  			       var empArr = Employeetext.split('-');
				  				   var masterKeyId=getFieldValue('txtMomsKeyid');
				  				   
				  				   var role=getFieldValue('cmbMomsRole');
				  				   var roletext =jQuery("#cmbMomsRole").combobox("getText");
				  				   var roleArr = roletext.split('-');
				  					 if(masterKeyId.length==0){ 
				  					       addRowEmployeeMultiple(row,Employee,empArr[1],empArr[0],roleArr[0]);
				  						}
				  					 else{   
				  						   addRowEmployeeMultiple(row,Employee,empArr[1],empArr[0],roleArr[0]);
				  					     }
				  				   //} 
				  			  
				  				}); 

                   	}
			   
			 if(jQuery("#meeting").val() != null)
				   MomeetinChechappen(jQuery("#meeting").val());
			 if(jQuery("#meetingatt").val() != null)
				   AttandancesCheck(jQuery("#meetingatt").val());
					
			 jQuery("#tabMom").tabs(
					    {
						onSelect : function(title)
						 {	 
							var Meetingparm = jQuery("#momGrid").getGridParam();
							var Mettingtype=getFieldValue('cmbMomsMeetingtype');
							glbType=jQuery('#hdntype').val();
							var Type=jQuery('#hdntype').val();
							if( title == "Mom"  && (Meetingparm == undefined || Meetingparm  <=0)) {
                                processGridnew("NewMoMeetingAtt_input.nmom","?q=2&keyid="+keyid, "attandanceGrid", "pageratt");
						    }else if( title == "Attendance" && Mettingtype.trim().length>0 ){
		                       	 var tabslect=jQuery('#hdntabselect').val();
					        	 var flid =jQuery("#frmNewMom input[id='flid']").val();
					     		 var cellId = jQuery("#frmNewMom input[id='cell']").val();
				     		     var momdate = getFieldValue("dteMomsDate", "frmNewMom");
					     		 var keyid=jQuery('#txtMomsKeyid').val();
					     		 var row = jQuery("#attandanceGrid").jqGrid('getDataIDs');					     		 
					     		 if(row.length==0){
					      			fnFillAttendanceGrid();
					      		 }
					     		  else{
					     			 
					     			 if(Mettingtype!="P")
					     				 {
					     			      fnFillAttendanceGrid();
					     				 }
					     			 
					     		 } 
					      		
		                   }
			           }
				  });

		   fileManagerPopUp("","MOM","frmNewMom","btnfilemgr","MomFilemgr",mode); 

		   
		   	var MeetingNo =jQuery('#txtMomsMeetingno').val();
			var MeetingTitle =jQuery('#txtMomsMeetingtitle').val();//txtMomsMeetingtitle
			var Meetingtype = getFieldValue("cmbMomsMeetingtype", "frmNewMom");
			var MeetingSafety =jQuery('#txtMomsSafetytalk').val();
			//alert("before");  
			jQuery('#hdnMetngtitle').val(MeetingTitle);
			jQuery('#hdnMomsMtntyp').val(Meetingtype);
			jQuery('#hdnMomsMtngsafety').val(MeetingSafety);
			jQuery('#hdnMomsMtngno').val(MeetingNo); 
			//alert("after");       
		        jQuery('#chkMomsIsmeetinghappen').click(function(){
				if(jQuery('#chkMomsIsmeetinghappen').is(':checked') == true){
					    //alert(jQuery('#chkMomsIsmeetinghappen').is(':checked'));
					    chkboxmeetCheck();
					
				}else if(jQuery('#chkMomsIsmeetinghappen').is(':checked') == false){ 

					chkboxmeetUnCheck();

					}
				});


		        //alert(" Inside :: "+jQuery('#hdnDMT').val());

				var DMT=jQuery('#hdnDMT').val();
				var DMTDBLE=jQuery('#hdnDMTDBLE').val();
				if(DMT.trim().length>0 && DMTDBLE.trim().length>0){//alert(1);
				readOnlyFields("cmbMomsMeetingtype");
				jQuery('#cmbMomsMeetingtype').combobox('setValue','D');

	         }
				
				
			jQuery('#btnExcelVw').click(function()
					 {
					 	var momKeyId = jQuery('#txtMomsKeyid').val();
					 	var flid = jQuery("#frmNewMom input[id='flid']").val();
					 	
					 	if(momKeyId != null && momKeyId.length >0){
					 		if((glbType=="Production" && glbType.length>0)||(glbType=="Others" && glbType.length>0 ) )
					 		window.open("NewMoMeetingMom_view.nmom?&momKeyId="+momKeyId+"&flid="+flid+"&glbType="+glbType);
					 	else 
					 		window.open("NewMoMeetingMom_view.nmom?&momKeyId="+momKeyId+"&flid="+flid);
					 	}
					 });

		
		jQuery('#chkSelectAll').click(function() {
		    show_winMask(1);

	         var row = jQuery("#attandanceGrid").jqGrid('getDataIDs');		     
			if(jQuery("#chkSelectAll").is(':checked')== true){
		       	 for(var i=0;i<row.length;i++)
		       	 {
		       		jQuery('#MomAttcheckbox_'+row[i]+'_1').prop('checked', true);
		       		chkboxAttCheck(row[i]);
		       	 }
		     }
			else { 
				for(var i=0;i<row.length;i++)
		       	 {
		       		jQuery('#MomAttcheckbox_'+row[i]+'_1').prop('checked', false);
					chkboxAttUnCheck(row[i]);
		       	 }
			}
				
			show_winMask(0);
		 });


		jQuery('#btnMomMail').click(function(){
			var type = getComboBoxText("cmbMomsMeetingtype");
			var gmomType ="";
			if((glbType=="Production" && glbType.length>0)||(glbType=="Others" && glbType.length>0 ) )
				gmomType =glbType;
			var momKeyId = jQuery('#txtMomsKeyid').val();
		 	var flid =  jQuery("#frmNewMom input[id='flid']").val();
		 	var pasdate = getFieldValue("dteMomsDate", "frmNewMom");
		 	var plrtype = getComboBoxText("cmbMomsPillarid");
            var text=plrtype.substring(0,plrtype.indexOf("-"));
		 	
	 		var ds="momKeyId="+momKeyId+"&flid="+flid+"&glbType="+gmomType+"&date="+pasdate;

	 		if(type=="PILLAR")
		        ds+="&mtype="+escape(text);
	 		else
		 		ds+="&mtype="+type;

			processAjaxCalls("NewMoMeetingMom_sendMail.nmom",ds,"sendMailSuccessCalBk","");
			});		 
			
		var type=jQuery('#hdntype').val();
		var MstKeyid=jQuery('#txtMomsKeyid').val();
	    var meetingtype=getFieldValue('cmbMomsMeetingtype');
	    
		if(type=="FIP")
			setFieldValue("cmbMomsMeetingtype",type);

		if( MstKeyid.trim().length>0 && meetingtype=="P"){
			 disableField("frmNewMom", "cmbMomsPillargroup");
		 }		
});

	function sendMailSuccessCalBk(result){
		alert(result.msg);		
	}
    
    
    jQuery('#chkMomsOthers').click(function() {
	     
	     //alert(" Others "+jQuery('#chkkzbnOthers').val());
	     
	     if(jQuery("#chkMomsOthers").is(':checked')== true){
	    	 jQuery('#chkMomsOthers').val('Y');
	    	 var sat = jQuery('#chkMomsOthers:checked').val();
	    	 jQuery("#cmbMomsEmployee").combobox('setValue',"");
			 othersClickAction(sat);
	     }else if(jQuery("#chkMomsOthers").is(':checked')== false){
	         jQuery('#chkMomsOthers').val('N');
	         var sat = jQuery('#chkMomsOthers').val();
	         jQuery("#cmbMomsEmployee").combobox('setValue',"");
			 othersClickAction(sat);
		 }
	     
	     
	     var MstKeyid=jQuery('#txtMomsKeyid').val();
	     var meetingtype=getFieldValue('cmbMomsMeetingtype');
		 
	     if(MstKeyid.trim().length>0){
		 if(meetingtype=="D"||meetingtype=="PD"||meetingtype=="P"||meetingtype=="O"||meetingtype=="CEC"||meetingtype=="DEC"||meetingtype=="FIP"){
		 }else if(meetingtype=="J"){
			enableFields("cmbMomsShiftid");
		 }
	     }
	     
	      
	     
	});
	
    
    function fnFillAttendanceGrid() {
    	
    	var flid =jQuery("#frmNewMom input[id='flid']").val();
    	//alert(" flid :: "+flid);
    	var locationId = jQuery("#frmNewMom input[id='location']").val();
	    var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var mstkeyid=jQuery('#txtMomsKeyid').val();
		var keyid=jQuery('#txtMomsKeyid').val();
    	var meetingType=getFieldValue("cmbMomsMeetingtype", "frmNewMom");
    	var Mettingtype=getFieldValue('cmbMomsMeetingtype');
    	var Type=jQuery('#hdntype').val();
		var Pillarid=getFieldValue("cmbMomsPillarid", "frmNewMom");
		var pillargroup=getFieldValue("cmbMomsPillargroup", "frmNewMom");
		var shift= getFieldValue("cmbMomsShiftid");
		var recall=jQuery('#hdnrecall').val();
		if(Mettingtype=="J"||Type=="JH")
 			{
 			   processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 				
 			}else if(Mettingtype=="D"||Type=="Dmt")
 			{
 				//alert("locationId"+locationId);
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 				
 			}else if(Mettingtype=="FIP")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+jQuery('#hdnMomsRefdocid').val()+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
 			else if(Mettingtype=="PD"||Type=="Production")
 			{
 	 		 	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
 			else if(Mettingtype=="O"||Type=="Others")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
 			else if(Mettingtype=="DEC"||Mettingtype=="CEC")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&PILLAR="+Mettingtype+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 				setFieldValue('cmbMomsPillarid',"TGT007");
 			}
 			else if(meetingType=="P")
 			{
 				var dsPillar = "?q=2&roleid="+Pillarid+"&PILLAR=PILLARS"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall;
 				if(pillargroup && pillargroup.trim() !== "" )
 				  {
 					  dsPillar += "&pillargroup=" + pillargroup;
 					  
 				  }
 				
 				 setTimeout(function(){
    	    	processGridnew("NewMoMeetingAtt_input.nmom",dsPillar, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");	
 				},1000); 
 				 }
 			else if(Mettingtype=="UMC")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=UMC"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
 			else if(Mettingtype=="OGM")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=OGM"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid+"&shift="+shift+"&recall="+recall, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
 	}

    

    function hideProductionNotRelatedFields() {
    	jQuery('#pillarlblid').hide();
    	jQuery('#btnjhActivity').hide();
    	jQuery('#spnPillar').hide();
    	jQuery('#spnPillargroup').hide();
    	jQuery('#pillargrouplblid').hide();
        jQuery('#MeetingTitle').hide();
    	jQuery('#lblMeetingNumber').css('padding-left',0);
    	jQuery('#txtMomsMeetingtitle').hide();
    	jQuery('#lblShift').hide();
    	jQuery('#spnShift').hide();
    	jQuery('#SafetyTalk').hide();
    	jQuery('#txtMomsSafetytalk').hide();
    	jQuery('#lblRemarks').hide();
    	jQuery('#spnMomsRemarks').hide();
    } 
    
	function othersClickAction(sat)
	{
	 var cellId = jQuery("#frmNewMom input[id='cell']").val();
	 var flid = jQuery("#frmNewMom input[id='flid']").val();
	 var locationId = jQuery("#frmNewMom input[id='location']").val();
	 var roleId =getFieldValue('cmbMomsRole');
	 
	 if(sat=="Y")
	 {
	
		 reloadCombo("frmNewMom","cmbMomsEmployee","rolebasedemployee.mom?&Others=Y&roleId="+roleId+"&locnId="+locationId);
		 
	 }
	 else
	 {  
		 
		 reloadCombo("frmNewMom","cmbMomsEmployee","rolebasedemployee.mom?&roleId="+roleId+"&flid="+flid);
	}	
}



 function disableForm(){
    disableField("frmNewMom", "dteMomsDate");
	disableField("frmNewMom","cmbMomsShiftid");
	disableField("frmNewMom","cmbMomsMeetingtype");
	disableField("frmNewMom","cmbdEmployee");
	disableField("frmNewMom","btnAttsave");
	disableField("frmNewMom","btnjhActivity");
	disableField("frmNewMom","btnaddOthers");
	disableField("frmNewMom","btnDelete");
	
	disableField("frmNewMom","btnAddNew");
	disableField("frmNewMom","btnAddEmployee");
	disableField("frmNewMom","cmbMomsRole");
	disableField("frmNewMom","cmbMomsEmployee");
	readOnlyFields("txtMomsAgenda");

	readOnlyFields("txtMomsSafetytalk");
	readOnlyFields("txtMomsRemarks");
	disableField("frmNewMom","txtMomsMeetingtitle");
	disableField("frmNewMom","chkMomsIsmessageboard");
	disableField("frmNewMom","chkMomsIsmeetinghappen");
	
 }
    
 function addRowEmployee(row,Employee,empCode,Employeetext, roleCode){
 	
 	//alert(1);
     if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) { 
               
		if ( row == null || row == '' || parseInt(row) <= 0) {
			var j=0;
			var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];
			
		       jQuery("#attandanceGrid").jqGrid('addRowData',j, emptyItem[0]);
	           var k =j+1;
	           jQuery("#hdnVal").val(k);
		}
	    else {	 
		        var empExists = false;
		        for(var i=0;i<row.length;i++){
		        	var rowData = jQuery("#attandanceGrid").jqGrid('getRowData',3+i);
	        		var empid=rowData.EmployeeId;
	        		if(Employee==empid){
	        			popupCommonErrorMsg(" This Record is already exist in grid .. ");
	        			return false;
	        		}
		        }
		        
		        if (empExists == false) {
		        	
				    lastRow = row[row.length-1];
	  				var rolecodeemp=jQuery('#hdnroledata').val();
	  			
	  				if(rolecodeemp.trim().length>0)
	  					var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];
	  				else
	  					var emptyItem =[{EmployeeId:Employee,EmployeeCode:empCode,Employee:Employeetext,Attendance:'',AttendanceID:' ',Role:rolecodeemp}];
	  					
                    if(Employeetext.trim().length>0){
	  		           jQuery("#attandanceGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
	  		           jQuery('#hdnaddemp').val('C');
                       chkboxAttCheck((parseInt(lastRow))+1);
                    }
					 
	        	}
	        }
	   }
   }

    function addRowEmployeeMultiple(row,Employee,Employeetext, empCode,roleCode){
  	  
        if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) {//alert(" Inside Validation "); 
                  
  		if ( row == null || row == '' || parseInt(row) <= 0) { 
  			   var emptyItem =[{EmployeeId:"",Employee:" ",EmployeeCode:"",Role:" ",Attendance:"",AttendanceID:" "}];
  		       jQuery("#attandanceGrid").jqGrid('addRowData',j, emptyItem[0]);
  	           var k =j+1;
  	           jQuery("#hdnVal").val(k);
  		    }
  	        
  	   else {	 
  		        var empExists = false;
  		        for(var i=0;i<row.length;i++){
		        	var rowData = jQuery("#attandanceGrid").jqGrid('getRowData',3+i);
	        		var empid=rowData.EmployeeId;
	        		//jQuery('#hdnnewaddemp').val(empid);
	        		if(Employee==empid){
	        			popupCommonErrorMsg(" This Record is already exist in grid .. ");
	        			return false;
	        		}
  		        }
  		      if (empExists == false) {
	  				lastRow = row[row.length-1];
	                var emptyItem =[{EmployeeId:Employee,Employee:Employeetext,EmployeeCode:empCode,Role:roleCode,Attendance:' ',AttendanceID:''}];
	  		        jQuery("#attandanceGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
	        	}
  	        }
  	   }
    }
        
    function fillcurrecntshift(){
    	
    	var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var hours = currentTime.getHours();
		var minutes = currentTime.getMinutes();
		
		if (minutes < 10){
			minutes = "0" + minutes;
		}	
		
		if (hours < 10){
			hours = "0" + hours;
		}	

		var factId = "";
		var dataString = '?q=2&sectId='+jQuery("#frmNewMom input[id='section']").val();
		    dataString += '&cellId='+jQuery("#frmNewMom input[id='cell']").val()+'&fromTime='+hours + ':' + minutes;
		processAjaxCalls('txt_shift.brdn',dataString,'getMOMShift','getMOMShiftErr');
		
    }
		
    function recallsuccessCallBack(result){//alert(" Checking Now :: "+result[0][0]);
		setFieldValue('hdnAttRole',result[0][0]);
	}
    
	
	function  getMOMShift(record)
	{
		//alert("record.shift"+record.shift);
		var mstKeyid=jQuery('#txtMomsKeyid').val();
		if(mstKeyid.trim().length<=0)
		   jQuery('#cmbMomsShiftid').combobox('setValue',record.shift);
	}
   	
/*     
    function momGrid_selectRow(id){
    			
    	jQuery('#hdnRowCoId').val(id);
		     var MomPillarId =  getFieldValue("cmbMomsPillarid"); 
		     var MomPillarIdGrid =  getFieldValue("cmbMomdPillar_momGrid_"+id);
		     alert("pillar"+MomPillarId+" pillar gird"+MomPillarIdGrid);
		     var MomPillarid = (MomPillarId && MomPillarId.trim() !== "") ? MomPillarId : MomPillarIdGrid;
		      
		
		setFieldValue("cmbMomdPillar_momGrid_"+id,MomPillarid);
		jQuery("#Actplnbutton_momGrid_"+id).prop('maxlength','0');
		jQuery("#Actplnbutton_momGrid_"+id).val("");
		jQuery("#kpibtn_momGrid_"+id).prop('maxlength','0');
		jQuery("#kpibtn_momGrid_"+id).val("");
		return "MomPillarId="+MomPillarId;
    } */

    function chkboxmeetCheck()
	 {
    	var MetingNo =jQuery('#hdnMomsMtngno').val();
	    jQuery('#txtMomsMeetingno').val(MetingNo);

	    var MetingTitle =jQuery('#hdnMetngtitle').val();
	    jQuery('#txtMomsMeetingtitle').val(MetingTitle);

	    var MetingType =jQuery('#hdnMomsMtntyp').val();
	    jQuery('#cmbMomsMeetingtype').combobox('setValue',MetingType);

	    var MetingSafety =jQuery('#hdnMomsMtngsafety').val();
	    jQuery('#txtMomsSafetytalk').val(MetingSafety);

	    jQuery('#err_txtMomsMeetingno').show();
	    jQuery('#err_txtMomsMeetingtitle').show();
	    jQuery('#err_cmbMomsMeetingtype').show();
	    jQuery('#err_txtMomsSafetytalk').show();
	    
		enableFields("dteMomsDate");
		enableFields("cmbMomsShiftid");
		enableFields("txtMomsRemarks");
		enableFields("txtMomsMeetingtitle");
		enableFields("txtMomsSafetytalk");
		enableFields("txtMomsRemarks");
		jQuery('#MeetingType').addClass("mandatory-lbl");
	 }
    function chkboxmeetUnCheck()
	 {
    	jQuery('#txtMomsMeetingno').val(" ");
	    jQuery('#txtMomsMeetingtitle').val(" ");
	    jQuery('#cmbMomsMeetingtype').val(" ");
	    jQuery('#txtMomsSafetytalk').val(" "); 
	    setFieldValue("cmbMomsMeetingtype"," ","frmNewMom");
	    jQuery('#MeetingType').removeClass("mandatory-lbl");
	    jQuery('#MeetingNumber').removeClass("mandatory-lbl");
	    var errmsgfun =jQuery('#err_MomAttfunLocation').html();
	    var errmsgshift = jQuery('#err_cmbMomsShiftid').html();
	    var errmsgmetno = jQuery('#err_txtMomsMeetingno').html();
	    var errmsgmettitle = jQuery('#err_txtMomsMeetingtitle').html();
	    var errmsgmettype = jQuery('#err_cmbMomsMeetingtype').html();
	    var errmsgmettalk = jQuery('#err_txtMomsSafetytalk').html();
	    
        clearValidationErrorMessages("frmNewMom","err_txtMomsMeetingno");

        jQuery('#err_MomAttfunLocation').html(errmsgfun);
	    jQuery('#err_cmbMomsShiftid').html(errmsgshift);
	    
	    jQuery('#err_txtMomsMeetingno').html(errmsgmetno);
	    jQuery('#err_txtMomsMeetingtitle').html(errmsgmettitle);
	    jQuery('#err_cmbMomsMeetingtype').html(errmsgmettype);
	    jQuery('#err_txtMomsSafetytalk').html(errmsgmettalk);
	    
	    jQuery('#err_MomAttfunLocation').show();
	    jQuery('#err_cmbMomsShiftid').show();

	    
	   
	    enableFields("dteMomsDate");
		enableFields("cmbMomsShiftid");
		enableFields("txtMomsRemarks");
		readOnlyFields("txtMomsMeetingno");
		readOnlyFields("txtMomsMeetingtitle");
		readOnlyFields("cmbMomsMeetingtype");
		readOnlyFields("txtMomsSafetytalk");
		readOnlyFields("txtMomsRemarks");
	 }
	function frmNewMom_deleteSuccessCallback(result)
	{
        alert(result.successData.msg);
		clearForm('frmNewMom');
		jQuery('#momGrid').trigger("reloadGrid");
		jQuery('#attandanceGrid').trigger("reloadGrid");
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
   /* function dteMomsDate_onSelect(date)
	{
	   	//var momdate = getFieldValue("dteMomsDate", "frmNewMom");
	   	var momdate = formatDate(date);
	   	var flid = jQuery("#frmNewMom input[id='flid']").val();
	   	jQuery("#hdnmomdate").val(momdate);
	   	setFieldValue("dteMomsDate", momdate, "frmNewMom");
		var currentDate = getServerDateTime();
		if( date > currentDate){					
			jQuery('#dteMomsDate').datebox('clear');
			jQuery('#txtMomsAgenda').val(' ');
			showValidationErrorMsg('dteMomsDate','Should Not Exceed Current Date');	
			return false;	
		}
		else
			clearValidationErrorMsg('dteMomsDate');

		fnsetvalues();
		processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
		fnRecall();
	} */
   function dteMomsDate_onSelect(date)
   {
     //var momdate = getFieldValue("dteMomsDate", "frmMom");
     var momdate = formatDate(date);
     var checkDate = new Date(date);
     var today = new Date(getServerDateTime());

     var thirtyDays = new Date(today);
     thirtyDays.setDate(thirtyDays.getDate() - 30);

     console.log(checkDate, "momdate");
     console.log(thirtyDays, "thirtyDays");
     if (checkDate < thirtyDays)
     {
         alert('Cannot Create or Update - View in view menu');
         //popupCommonErrorMsg("Enter Discussion Details");
         var dateval=jQuery("#dteMomsDate").datebox('getValue');
         setTimeout(function() {
        	 jQuery("#dteMomsDate").datebox('setValue', dateval);
        },100);
         
         //setFieldValue("dteMomsDate", dateval, "frmMom");
         console.log(dateval+"dateval");
      
         return false;
     }
     //var dateval=jQuery("#dteMomsDate").datebox('getValue');
    var flid = jQuery("#frmMom input[id='flid']").val();
     jQuery("#hdnmomdate").val(momdate);
     setFieldValue("dteMomsDate", momdate, "frmMom");
   var currentDate = getServerDateTime();
   if( date > currentDate){
   jQuery('#dteMomsDate').datebox('clear');
   jQuery('#txtMomsAgenda').val(' ');
   showValidationErrorMsg('dteMomsDate','Should Not Exceed Current Date');
   return false;
   }
   else
   clearValidationErrorMsg('dteMomsDate');

   fnsetvalues();
   processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
   fnRecall();


   }


   function frmNewMomcmbMomsShiftid_onSelect(date)
	{
	  // alert(123);
	  console.log(date);
	  setFieldValue("cmbMomsShiftid",date.id);
	   jQuery("#hdnmomdate").val("date");
	   fnRecall();
	}

   
 function frmNewMomcmbMomsRole_onSelect(record)
 {
	 
     var flid =jQuery("#frmNewMom input[id='flid']").val();
	 var role =getFieldValue('cmbMomsRole');
	
	 reloadCombo("frmNewMom","cmbMomsEmployee","rolebasedemployee.mom?&roleId="+record.id+"&flid="+flid);
	 
	 jQuery('input:checkbox[name=chkMomsOthers]').prop('checked',false);
	 jQuery('#cmbMomsEmployee').combobox('clear');
 }

 function frmNewMomcmbMomsPillargroup_onSelect(record)
 {
	 var flid =jQuery("#frmNewMom input[id='flid']").val();
	 var pillarid =getFieldValue('cmbMomsPillarid');
	 var type = getFieldValue("cmbMomsMeetingtype", "frmNewMom");
	// alert("type>>>"+type);
	 var pillargroup =getFieldValue('cmbMomsPillargroup');
	 var momdate = getFieldValue("dteMomsDate", "frmNewMom");
	 var locationId = jQuery("#frmNewMom input[id='location']").val();
     var cellId = jQuery("#frmNewMom input[id='cell']").val();
     var keyid=jQuery('#txtMomsKeyid').val();
     var dataString;
     
     if(type=="UMC")
    {
    	 dataString= '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=UMC"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
     
    }
     else  if(type=="OGM")
    {
          dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=OGM"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
      
    }
     else if(type=="P"){
    	 dataString = '?q=2&flid='+flid+"&roleid="+pillarid+"&pillargroup="+pillargroup;
         dataString += "&PILLAR=PILLAR"+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&mkeyid="+keyid;
     
     }
     processGridnew("NewMoMeetingAtt_input.nmom",dataString,"attandanceGrid","pageratt","","","","loadCompleteAttendanceGrid1");
    	
} 
 
 function frmNewMomcmbMomsEmployee_onSelect(record)
 {     
	 processAjaxCalls("MoMeetingFillRole_modify.mom","emplyid="+record.id,"updateRoleSuccess","");
 
 }

 function updateRoleSuccess(result){
		
		jQuery('#hdnroledata').val(result[0][1]);
	}
 
 
 function frmNewMomcmbMomsPillarid_onSelect(record)
	{	
		var Pillarid=record.id;
	    var meetingType=getFieldValue("cmbMomsMeetingtype", "frmNewMom");
	    var flid =jQuery("#frmNewMom input[id='flid']").val();
	    var locationId = jQuery("#frmNewMom input[id='location']").val();
	    var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var dateval=jQuery("#dteMomsDate").datebox('getValue');
		var mstkeyid=jQuery('#txtMomsKeyid').val();
		
		var recall=jQuery('#hdnrecall').val();
		processAjaxCalls("MoMeetingPillaridRecall.mom","&flid="+flid+"&date="+momdate+"&shift="+Pillarid,"PillaridRecallSuccess","");
		 //fillComboBox("frmNewMom","cmbMomsPillargroup","pillargroup.nmom?pillarid="+Pillarid);
		 fillComboBox("frmNewMom","cmbMomsPillargroup","pillargroup.nmom?pillarid="+Pillarid+"&locationId="+locationId);
	}
 
 function PillaridRecallSuccess(result)
 {
	 
	 var keyid=result.momkeyid;
	// alert("keyid"+keyid);
	 var Pillarid=result.pillarid;
	 var meetingType=getFieldValue("cmbMomsMeetingtype", "frmNewMom");
	 var flid =jQuery("#frmNewMom input[id='flid']").val();
	 //  alert("flid"+flid);
	    var locationId = jQuery("#frmNewMom input[id='location']").val();
	   // alert("locationId"+locationId);
	    var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		//var mstkeyid=jQuery('#txtMomsKeyid').val();
		var mstkeyid=jQuery('#txtMomsKeyid').val();
			//alert(mstkeyid);
			var shift= getFieldValue("cmbMomsShiftid"); 
		
	//	alert("mstkeyid"+mstkeyid);
		var recall=jQuery('#hdnrecall').val();
	 if(keyid!=null)
	{	 
		 var mstkeyid=jQuery('#txtMomsKeyid').val(keyid);
			jQuery("#txtMomsMeetingno").val(keyid); 
			
			var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
		     processAjaxCalls("NewMoMeetingRecalling_input.nmom",ds,"RecallingSuccessData",""); //Added this line - Swetha
		     processGridnew("NewMoMeetingMom_input.nmom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha
	  //processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+mstkeyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
	}
	 else{
		 jQuery('#txtMomsKeyid').val('');
			jQuery("#txtMomsMeetingno").val(''); 
			jQuery("#txtMomsAgenda").val('');
			jQuery("#txtMomsSafetytalk").val('');
			jQuery("#txtMomsRemarks").val('');
			jQuery("#txtMomsMeetingtitle").val('');//added this
			
			processGridnew("NewMoMeetingMom_input.nmom","?q=2&keyid="+keyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction"); //Added this line -Swetha

			 var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+Pillarid;
			      processAjaxCalls("NewMoMeetingRecalling_input.nmom",ds,"RecallingSuccessData",""); //Added this line - Swetha
		    if(Pillarid=="TGT001"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    }else if(Pillarid=="TGT002"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT003"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT003"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT004"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT005"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT006"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT007"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    } else if(Pillarid=="TGT008"&& meetingType=="P"){
		    	processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt");	
		    }
	 }
 }
 
 
 function meetingemployeetype(id){
		var Mettingtype=id;
		var flid =jQuery("#frmNewMom input[id='flid']").val();
		var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var keyid=jQuery('#txtMomsKeyid').val();
	    if(Mettingtype=="P"){
	    	jQuery('#pillarlblid').addClass("mandatory-lbl");
	    	enableFields("cmbMomsPillarid");
		 }else{
		     jQuery('#pillarlblid').removeClass("mandatory-lbl");
			 readOnlyFields("cmbMomsPillarid");
			 clearField("cmbMomsPillarid"); 
		 }
	    
		if(Mettingtype=="J")
		{
		  jQuery('#SafetyTalk').addClass("mandatory-lbl");
		}else
		  jQuery('#SafetyTalk').removeClass("mandatory-lbl");
		  if(Mettingtype=="D"){
		     meetingtype();
		  }
			
		if(keyid.trim().length<=0){
		  if(Mettingtype=="J")
			{
			   processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				
			}else if(Mettingtype=="D")
			{
				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				
			}else if(Mettingtype=="FIP")
			{
				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
			else if(Mettingtype=="PD")
			{
				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}
			else if(Mettingtype=="O")
			{
				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
			}else if(Mettingtype=="DEC"||Mettingtype=="CEC"||Mettingtype=="O")
 			{
 				processGridnew("NewMoMeetingAtt_input.nmom","?q=2&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
 			}
		  }else
			  {
			  
			  if(Mettingtype=="J")
				{
				   processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=JH"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
					
				}else if(Mettingtype=="D")
				{
					processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=DMT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
					
				}else if(Mettingtype=="FIP")
				{
					processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=FI PROJECT"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				}
				else if(Mettingtype=="PD")
				{
					processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=PRODUCTION"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				}
				else if(Mettingtype=="O")
				{
					processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid=-"+"&PILLAR=OTHERS"+"&flid="+flid+"&momdate="+momdate+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
				}

			  }
 }              
 function frmNewMomcmbMomsMeetingtype_onSelect(record)
	{
		
	 meetingemployeetype(record.id);
	   
	 var meetingtype=record.id;
	   
	 if(meetingtype=="D"||meetingtype=="PD"||meetingtype=="P"||meetingtype=="O"||meetingtype=="CEC"||meetingtype=="DEC"||meetingtype=="FIP"){
		 disableField("frmNewMom", "cmbMomsShiftid");
	 }else if(meetingtype=="J"){
		enableFields("cmbMomsShiftid");
	 }

	}
 
 function meetingtype(){
	        var rowIds = jQuery("#momGrid").jqGrid('getDataIDs');

	        if(rowIds==''||rowIds==0){
               rowIds=rowIds+1;
	        }
            for (var i=0;i<rowIds.length;i++){

                 var tdcol = jQuery("#momGrid" + ' tr[id='+ rowIds[i] +"]").find('td[aria-describedby="momGrid_cmbMomdDiscussionType"]');  
				 tdcol.prop('Mandatory',true); 
		    }   
	}
	
	
	function btnfilemgr_click()
	{
      	var keyid = jQuery('#txtMomsKeyid').val();
      	if(keyid.trim().length<=0){
      		saveForm('frmNewMom','NewMoMeetingForm_save.nmom?filemanger=filemanger');
      	}else if(keyid != null && keyid != ''){
      		var mode = jQuery("#frmNewMom input[id=mode]").val();
      		fileManagerPopUp(keyid,"MOM","","","",mode);
		 }		
	}

	function frmNewMom_FuntLocHierarchy_SuccessCallBack(result)
	{
		console.log(result);
		
		var mode = jQuery('#hdnMenuMode').val();
		jQuery('#hdnrecall').val("Y");
		 if(mode == "modify")
		{
			jQuery('#hdnrecall').val("N");
			
		}
		var Type=jQuery('#hdntype').val();
		var recall = jQuery('#hdnrecall').val();
		var sbuId = result.sbuId;
		var pbuId = result.pbuId;
		var sectId = result.sectId;
		var cellId = result.cellId;
		if(mode != "modify"){
		if (Type =='Pillar' && sbuId && sbuId.length > 0 && pbuId && pbuId.length > 0)  {
			readOnlyFields("cmbFunlocLocation");
			readOnlyFields("cmbFunlocCompany");
			processAjaxCalls("MoMeetingFlid.nmom","originalId="+sbuId,"recallingFlidSuccess","");
			return;
		}
		if (Type =='Dmt'&& sectId && sectId.length > 0 && cellId && cellId.length > 0) 
		{

		processAjaxCalls("MoMeetingFlid.mom","originalId="+sectId,"recallingFlidSuccess","");
		return;
		}
		}
		if(Type =='Pillar')
		{
			jQuery('#hdnrecall').val("N");
			
		}
			
		setFunctionalLocWidth('frmNewMom','600px');
		var mkeyid = jQuery("#txtMomsKeyid").val();
		var cellId = jQuery("#frmNewMom input[id='location']").val();
		var flid =jQuery("#frmNewMom input[id='flid']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var locationId = jQuery("#frmNewMom input[id='location']").val();
		processAjaxCalls("MoMeetingAgendafill_modify.mom","flid="+flid+"&momdate="+momdate,"updateSuccessAgendaData","");
		if(result.flId != undefined && result.flId.trim() != "" && result.flId.trim() !=null)
		{ 
		    reloadCombo("frmNewMom","cmbMomsEmployee","employee.commonFilter?&flid="+result.flId);
		}
		
		var Mettingtype =getFieldValue('cmbMomsMeetingtype');
		var mode = jQuery("#frmNewMom input[id=mode]").val();
		var shift= getFieldValue("cmbMomsShiftid");

        if(mode=="view" && mode.trim().length>0){
		    jQuery('#frmNewMom').append('<div style="position:absolute;top:0;left:0;width:98%;z-index:2;opacity:0.4;height:10%;"> </div>');
            jQuery("#chkSelectAll").prop("readonly",true);
        }    
            fnRecall();
	    	//fnFillAttendanceGrid();
        if((Mettingtype=="CEC" && mkeyid.trim().length>0)||(Mettingtype=="DEC" && mkeyid.trim().length>0)||(Mettingtype=="P" && mkeyid.trim().length>0)){
	    	frmNewMomcmbMomsPillargroup_onSelect();
        }  
    }
	function recallingFlidSuccess(result)
	{
		console.log(result);
		var flid = result.flid;
		var dataStrFlid ="&flid=" + flid;
		funclocnStr= "functionalLoc.nmom?Type=Pillar";
		loadFunctionalLocation("MomAttfunLocation", funclocnStr, "MomAttfunLocation", "frmNewMom",dataStrFlid);
		
		
		}
	
	function fnRecall(){
        var recall=jQuery('#hdnrecall').val();
        glbType=jQuery('#hdntype').val();
		var Type=jQuery('#hdntype').val();
		var Mettingtype =getFieldValue('cmbMomsMeetingtype');
		var mode = jQuery("#frmNewMom input[id=mode]").val();
		var shift= getFieldValue("cmbMomsShiftid");
		var flid =jQuery("#frmNewMom input[id='flid']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var hdndte=jQuery("#hdnmomdate").val();
		var pillarid =getFieldValue('cmbMomsPillarid');
		if (recall.trim().length>0 && recall=="Y"){
            processGridnew("NewMoMeetingMom_input.nmom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType+"&pillarid="+pillarid, "momGrid", "pagermom","","","","loadCompleteAction");
             setTimeout(function() {
 	        	fnFillAttendanceGrid();
 		    },300);
             
             fnsetvalues();
         } 
		
		if(hdndte.trim().length>0 && recall.trim().length<=0 && recall!="Y"){
			processGridnew("NewMoMeetingMom_input.nmom","?q=2&flid="+flid+"&momdate="+momdate+"&shift="+shift+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");
	        var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType;
	        processAjaxCalls("NewMoMeetingRecalling_input.nmom",ds,"RecallingSuccessData","");
	        setTimeout(function() {
	        	fnFillAttendanceGrid();
		    },300);
	        
	        fnsetvalues();
       }else{
    	   var ds = "?&shift="+shift + "&Date="+momdate+"&flid="+flid+"&type="+glbType+"&pillarid="+pillarid;
           processAjaxCalls("NewMoMeetingRecalling_input.nmom",ds,"RecallingSuccessData","");
           fnsetvalues();
       }    
	
	} 
	
	function fnsetvalues(){
		
		setFieldValue("txtMomsKeyid","");
		setFieldValue("txtMomsMeetingno","");
		setFieldValue("txtMomsSafetytalk","");
		setFieldValue("txtMomsRemarks","");
		setFieldValue("txtMomsMeetingtitle","");
		setFieldValue("txtMomsAgenda","");
		setFieldValue("cmbMomsPillarid","");		
		setFieldValue("txtMomsKeyid",jQuery("#hdnMno").val());
		setFieldValue("txtMomsMeetingno",jQuery("#hdnMno").val());
		setFieldValue("txtMomsSafetytalk",jQuery("#hdnsfty").val());
		setFieldValue("txtMomsRemarks",jQuery("#hdnrmrk").val());
		setFieldValue("txtMomsMeetingtitle",jQuery("#hdntitle").val());
		setFieldValue("txtMomsAgenda",jQuery("#hdnagnda").val());
		 if(jQuery("#hdnpillarid").val().trim().length>0)
		  setFieldValue("cmbMomsPillarid",jQuery("#hdnpillarid").val()); 
		if(jQuery("#hdnpillarid").val().trim().length>0){
		  setFieldValue("cmbMomsPillargroup",jQuery("#hdnpillargrpid").val());
		  disableField("frmNewMom", "cmbMomsPillargroup");
		}
		
	}
	function RecallingSuccessData(result){
		setFieldValue("txtMomsKeyid","");
        setFieldValue("txtMomsMeetingno","");
        setFieldValue("txtMomsSafetytalk","");
        setFieldValue("txtMomsRemarks","");
        setFieldValue("txtMomsMeetingtitle","");
        setFieldValue("txtMomsAgenda","");
        //setFieldValue("cmbMomsPillarid","");
       	setFieldValue("txtMomsKeyid",result[0][0]);
        setFieldValue("txtMomsMeetingno",result[0][0]);
        setFieldValue("txtMomsSafetytalk",result[0][2]);
        setFieldValue("txtMomsRemarks",result[0][3]);
        setFieldValue("txtMomsMeetingtitle",result[0][4]);
        setFieldValue("txtMomsAgenda",result[0][6]);
        if(result[0][7].trim().length>0)
            setFieldValue("cmbMomsPillarid",result[0][7]);
        if(result[0][8].trim().length>0){
            setFieldValue("cmbMomsPillargroup",result[0][8]);
            disableField("frmNewMom", "cmbMomsPillargroup");
        }        
        fnFillAttendanceGrid();
    }
	
	function viewGrid(url,filterString)
	{
		if (dataString=="?")
			processGridnew("mom_input.mom",dataString,"list","pager","","docDoubleClick");		
		else if(validateFilterSelection(dataString))
		{   	
	   processGridnew(url,filterString,"attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");//,"","","","loadCompleteAttendanceGrid"
    }
	}
   
	   function FiptoMom_afterClose()
	    {
	    
	    	jQuery("#list").trigger("reloadGrid");
	    	
	    }

	   
	   function loadCompleteAttendanceGrid1(id){ 
		   
		   var row = jQuery("#"+id).jqGrid('getDataIDs');
		     
		     var meetingType=getFieldValue("cmbMomsMeetingtype", "frmNewMom");
		   //  alert("type:"+meetingType);
		     var flid =jQuery("#frmNewMom input[id='flid']").val();
			 var locationId = jQuery("#frmNewMom input[id='location']").val();
			   // alert("locationId"+locationId);
			 var cellId = jQuery("#frmNewMom input[id='cell']").val();
			 
			 var pillargrp=getFieldValue("cmbMomsPillargroup", "frmNewMom");
		     var momdate = getFieldValue("dteMomsDate", "frmNewMom");
			 var keyid=jQuery('#txtMomsMeetingno').val();
		     var recall=jQuery('#hdnrecall').val();
			var Pillarid=getFieldValue("cmbMomsPillarid", "frmNewMom");
		     if(meetingType=="P")
		    	 {
			     processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");	
		    	 }
		     else  if(meetingType=="UMC")
		        {
		    	 processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=UMC"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");	
			    	 
		        }
		     else  if(meetingType=="OGM")
		        {
		    	 processGridnew("NewMoMeetingAtt_input.nmom","?q=2&roleid="+Pillarid+"&PILLAR=OGM"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId+"&pillargroup="+pillargrp, "attandanceGrid", "pageratt");	
			    	 
		        }
		     
		   
		   
		   
	   }
	   
	   
  function loadCompleteAttendanceGrid(id){
     var row = jQuery("#"+id).jqGrid('getDataIDs');
     var meetingType=getFieldValue("cmbMomsMeetingtype", "frmNewMom");
     if(meetingType=="P")
    	 {
	    var flid =jQuery("#frmNewMom input[id='flid']").val();
	    var locationId = jQuery("#frmNewMom input[id='location']").val();
	   // alert("locationId"+locationId);
	    var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");
		var keyid=jQuery('#txtMomsMeetingno').val();
		var recall=jQuery('#hdnrecall').val();
		var Pillarid=getFieldValue("cmbMomsPillarid", "frmNewMom");
		var pillargroup = getFieldValue("cmbMomsPillargroup", "frmNewMom");
		var callBackString = "?q=2&roleid="+Pillarid+"&PILLAR=PILLAR"+"&mkeyid="+keyid+"&flid="+flid+"&momdate="+momdate+"&locationId="+locationId+"&cellId="+cellId;
		if(pillargroup && pillargroup.trim() !== "")
		{
			callBackString += "&pillargroup=" + encodeURIComponent(pillargroup);
		}
      processGridnew("NewMoMeetingAtt_input.nmom",callBackString, "attandanceGrid", "pageratt");	
	    }
   	jQuery('#hdnaddemp').val('');
}
  
  function momGrid_selectRow(id){
	  //alert("entered in action plan select row"+id);
	 
	  var jqGridId="momGrid";
	  var dateCtrl="dteApldTargetdate_momGrid_"+id;
	     jQuery("#"+dateCtrl).datebox({  	   
				onSelect:function(recordid)
					{ 
					isValidDate(dateCtrl,id);
					    	
					} 
				});
	    	jQuery('#hdnRowCoId').val(id);
			     var MomPillarId =  getFieldValue("cmbMomsPillarid"); 
			     var MomPillarIdGrid =  getFieldValue("cmbMomdPillar_momGrid_"+id);
			    // alert("pillar"+MomPillarId+" pillar gird"+MomPillarIdGrid);
			     var MomPillarid = (MomPillarId && MomPillarId.trim() !== "") ? MomPillarId : MomPillarIdGrid;
			      
			
			setFieldValue("cmbMomdPillar_momGrid_"+id,MomPillarid);
			jQuery("#Actplnbutton_momGrid_"+id).prop('maxlength','0');
			jQuery("#Actplnbutton_momGrid_"+id).val("");
			jQuery("#kpibtn_momGrid_"+id).prop('maxlength','0');
			jQuery("#kpibtn_momGrid_"+id).val("");
			return "MomPillarId="+MomPillarid;
			

  }
 function isValidDate(dateCtrl,ctrlRowId){
	   var currdate=jQuery("#hdnCurrentDate").val();
	   //alert(currdate); 
	   var approvalDate = getFieldValue(dateCtrl);
		var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
			{
		var stringdate=convertStringToDate(approvalDate);
		
	if(convertStringToDate(approvalDate)== currentDate)
			{
			}
		if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
		{  
			if(stringdate==convertStringToDate(currdate))
				{
				  clearValidationErrorMsg(dateCtrl);
			    	return false;
				}
			else{
			 popupCommonErrorMsg('Should Not Enter Past Date');
			fillWithCurrentDate(dateCtrl);
			return false;
			}
		}
			}
	}
function addRow(row){
	var Date=getFieldValue('dteMomsDate');
	var shift =getComboBoxText('cmbMomsShiftid'); 
	var saftytalk=getFieldValue('txtMomsSafetytalk');
	var Pillar=getComboBoxText('cmbMomsPillarid');
	var Meetingno=getFieldValue('txtMomsMeetingno');
	var Meetingtitle=getFieldValue('txtMomsMeetingtitle');
	var Meetintype=getComboBoxText('cmbMomsMeetingtype');
	var val =  jQuery("#hdnVal").val();
	var j = parseInt(val);
	var checkval=jQuery('#chkMomsIsmeetinghappen').is(':checked');
	if(checkval==true){
	  if(Date==null || Date==''){
		   alert("Select Date");
		}
	  if(shift==null ||shift==' '||shift==''){ 
		   alert("Select Shift");
		   return false ;
	  }	    
	  if(Meetintype==null || Meetintype==''){
		   alert("Select Meeting Type");
		   return false ;
		}
	  
	}else if(shift==null ||shift==' '||shift==''){ 
		   alert("Select Shift");
		   return false ;
	  }
	  
      if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) {
    	     //  alert("Row !");
                var Type=jQuery("#momGrid").jqGrid('getCell',row.length,"cmbMomdDiscussionType");
                var Pillar = jQuery("#momGrid").jqGrid('getCell',row.length,"cmbMomdPillar");
                var DisCussion=jQuery("#momGrid").jqGrid('getCell',row.length,"txtMomdDiscussionDetails");
                var Remarks = jQuery("#momGrid").jqGrid('getCell',row.length,"txtMomdRemarks");
			    if (jQuery('#jqg_momGrid_'+row.length).is(':checked') == true){
                var Type = jQuery("#cmbMomdDiscussionType_momGrid_"+row.length).val();
                var Pillar = jQuery("#cmbMomdPillar_momGrid_"+row.length).combobox("getValue");
                var dis=jQuery("#txtMomdDiscussionDetails_momGrid_"+row.length).val();
                /* var Actionplanstatus = jQuery("#cmbApldStatus_momGrid_"+row.length).combobox("getValue");
                var Actionplandesc=jQuery("#txtApldActionplan_momGrid_"+row.length).val();
                var Actionplandate = jQuery("#dteApldTargetdate_momGrid_"+row.length).combobox("getValue");
                var Actionplanstatus = jQuery("#cmbApldResponsibility_momGrid_"+row.length).combobox("getValue");*/   
              var Remarks=jQuery("#txtMomdRemarks_momGrid_"+row.length).val();
			  
 		    }
		  }
        
		if ( row == null || row == '' || parseInt(row) <= 0) { 
			 //alert("Row null");
              var emptyItem =[{txtMomdKeyid:" ",txtMomdMomsKeyid:" ",dteMomsDate:" ",cmbMomsShiftid:" ",cmbMomdDiscussionType:" ",cmbMomdPillar:"",txtMomdDiscussionDetails:" ",txtApldActionplan:" ",cmbApldStatus:" ",dteApldTargetdate:" ",cmbApldResponsibility:" ",txtMomsSafetytalk:" ",txtMomdRemarks:"  "}];
               jQuery("#momGrid").jqGrid('addRowData',j, emptyItem[0]);
	           var k =j+1;
	           jQuery("#hdnVal").val(k);
		    }
	        
	   else {	   
		       // alert("else LAstRow"); 
		        for(var i=0;i<row.length;i++)
				lastRow = row[i];
		        var emptyItem =[{txtMomdKeyid:" ",txtMomdMomsKeyid:" ",dteMomsDate:" ",cmbMomsShiftid:" ",cmbMomdDiscussionType:" ",cmbMomdPillar:"",txtMomdDiscussionDetails:" ",txtApldActionplan:" ",cmbApldStatus:" ",dteApldTargetdate:" ",cmbApldResponsibility:" ",txtMomsSafetytalk:"  ",txtMomdRemarks:"  "}];
		        jQuery("#momGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);				
	        }

		if(Meetintype=='DMT'){
        	meetingtype();
 		}
	}
	

	

	function formatterCheckBox(id, options, rowObject)
	{	
		var rowId = options.rowId;
		var colId = options.pos;
		return '<input type="checkbox" id="Momcheckbox_'+rowId+'_'+colId+'" name="Momcheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxDiscuss(\''+rowId + '\');}else{chkboxUnCheckDiscuss(\''+ rowId +'\');}" />';
	}

	function getCurrentTime(){
		var serverTime = srvTime();
		var currentTime = new Date(serverTime);
		var hours = currentTime.getHours();
			var minutes = currentTime.getMinutes();
			if (minutes < 10){
				minutes = "0" + minutes;
			}
			return hours + ":" + minutes;
	}
	
	function chkMomAttformatter(id, options, rowObject)
	{	

		var keyid = jQuery('#txtMomsKeyid').val();    	
		if(keyid.length==0){
			disable=' ';
        }       
		var rowId = options.rowId;
		var colId = options.pos;

		var mode = jQuery("#frmNewMom input[id=mode]").val(); 
		if(mode=="view" && mode.trim().length>0){	
          disable='readonly="readonly"';   //
		  return '<input '+disable+' type="checkbox" id="MomAttcheckbox_'+rowId+'_'+colId+'" name="MomAttcheckbox_'+rowId+'_'+colId+'" '+ ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxAttCheck(\''+rowId + '\');}else{chkboxAttUnCheck(\''+ rowId +'\');}" />'; 
		}else
		  return '<input type="checkbox" id="MomAttcheckbox_'+rowId+'_'+colId+'" name="MomAttcheckbox_'+rowId+'_'+colId+'" '+ ' style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxAttCheck(\''+rowId + '\');}else{chkboxAttUnCheck(\''+ rowId +'\');}" />';
	}
	function txtMomAttformatter(id, options, rowObject)
	{	
		var rowId = options.rowId;
		var colId = options.pos;
		return '<input class="easyui-combobox" id="cmbAttcombo_'+rowId+'_'+colId+'" name="cmbAttcombo_'+rowId+'" value="'+rowObject[7]+'"  style=" width :100px;"/> ';          	           	                      
	}	
	function chkboxCheck(rowId)
	 {
		jQuery("#attandanceGrid").jqGrid('setCell',rowId,'CHKBOX','1');	
	 }
	function chkboxUnCheck(rowId)
	 {
		jQuery("#attandanceGrid").jqGrid('setCell',rowId,'CHKBOX','0');
	 }
	 	
	function chkboxDiscuss(rowId) {

		makeRowEditable("momGrid",rowId);
		jQuery("#momGrid").jqGrid('setCell', rowId, 'check', '1');
		setTimeout(function() {
			setFocusOnField("txtMomdDiscussionDetails_momGrid_"+rowId);
		},100); 
	}

	function chkboxUnCheckDiscuss(rowId) {		
		jQuery("#momGrid").jqGrid('setCell', rowId, 'check', '0');
	}
	
function chkboxAttCheck(rowId) {
	    jQuery("#attandanceGrid").jqGrid('setCell', rowId, 'chkbox', '1');
     	setFormater("attandanceGrid","frmNewMom","MeetingAttendance.mom",rowId,"AttendanceID","Attendance","96px",false);
     	if(getFieldValue("attandanceGridcmbMomaAttandance_"+rowId).trim().length<=0) 
   		{ 
   				setFieldValue("attandanceGridcmbMomaAttandance_"+rowId,"P");
   				jQuery("#attandanceGrid").jqGrid('setCell',rowId,'Attendance','P');
   			    jQuery('#MomAttcheckbox_'+rowId+'_1').prop('checked', true);

   			    if(jQuery('#hdnaddemp').val()=='C'){
   			         setFieldValue("cmbMomsEmployee"," ");
   			    }
   		}
     	
	}
	
	function chkboxAttUnCheck(rowId) {
		
		jQuery("#attandanceGrid").jqGrid('setCell', rowId, 'chkbox', '0');
		removeFormater("attandanceGrid","",rowId,"","AttendanceID");
		
	}

   function getSelectdRowsAtt(jqGridId, checkBoxColName, ckeckForSelColName) {//alert(1234567890);
		var momrow = jQuery("#attandanceGrid").jqGrid('getDataIDs');//	row get data
        var momcol = jQuery("#attandanceGrid").jqGrid("getGridParam","colModel");// col get data
		var rowid = "";
		var acontrolId = "";
		var aKeyId; 
		var colValue = "";
		var acontrolId1 = "";
		var colValue1 = "";
		var newcombovalue= "";
		var jsonArrO = '[';
		for (i = 0; i < momrow.length; i++) {
			rowid = momrow[i];
			var isChecked = jQuery('#MomAttcheckbox_'+ rowid +'_1').is(':checked');
			if(isChecked == true  ){
				AttendanceKeyid = jQuery("#attandanceGrid").jqGrid('getCell', rowid,"KeyId");
				var colkeyid = jQuery('#txtMomsKeyid').val(); //call master key id   
				var EmpID = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"EmployeeId");
	            var FLID= jQuery("#frmNewMom input[id='flid']").val();
	            var Role = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"Role");
	            var AttendanceId = jQuery("#attandanceGrid").jqGrid('getCell',rowid,"Attendance");

            if ((FLID != null && FLID != "")|| (FLID != null && FLID != "")) {
				jsonArrO += '{';
				jsonArrO += '"txtMomaKeyid":"' + AttendanceKeyid + '",';
				jsonArrO += '"txtMomaMomsKeyid":"' + colkeyid + '",';
				jsonArrO += '"txtMomaFlid":"' + FLID + '",';
				jsonArrO += '"txtMomaEmployeeid":"' + EmpID + '",';
				jsonArrO += '"txtMomaAttandance":"' + AttendanceId + '"';
				jsonArrO += '},';
			}
          }
	    }
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		return jsonArrO;			
	}
   
   function Actionplane_onClose(){

         var keyid = jQuery('#txtMomsKeyid').val();  
         var APLKeyid = jQuery('#txtAplmKeyid').val();
         var row =  jQuery('#hdnRowCoId').val();
         jQuery('#hdnactnmode').val('');
         jQuery("#momGrid").jqGrid('setCell',row,'txtMomdActionplanId',APLKeyid);
         var dtlKeyid=jQuery("#momGrid").jqGrid('getCell',row,'txtMomdKeyid');
         processAjaxCalls("MoMeetingForm_update.mom","dtlKeyid="+dtlKeyid+"&aplKeyid="+APLKeyid,"updateSuccess","");        
         return true;
        }

    function updateSuccess(){
    	jQuery("#momGrid").trigger("reloadGrid");
   	}

    function updateSuccessAgendaData(result){
        var keyid=jQuery('#txtMomsKeyid').val();
        var type=jQuery('#hdntype').val();
        
        if(keyid.trim().length=="0" && (type=='JH' || type=='Dmt')){
        	setTimeout(function() {
        		setFieldValue("txtMomsAgenda",result[0][0]);
    		},500);
            
        }
        setFieldValue("txtMomsAgenda",result[0][0]);
    }
			
	function momGridActplnbutton_onClick(result){
		//alert("actionplanpopup");
		var rowid=result.rowId;
	//	alert(rowid);
		var btnid=result.btnId;
		//alert(btnid);
		var refDocId = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomdKeyid");
	//	alert("RefID::::"+refDocId);
		var Type=jQuery('#hdntype').val();
		var Pillarid=getFieldValue("cmbMomsPillarid", "frmNewMom");

        if(Pillarid.trim().length==0)
        	Pillarid='-';
        
		var mode = jQuery("#frmNewMom input[id=mode]").val();
		var mainTask;

        if(mode=="view")
        	mainTask = jQuery("#momGrid").jqGrid('getCell',rowid,"txtMomdDiscussionDetails");
        else
        	mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
   	       // alert("The mainTask:::"+mainTask);	
		if (mainTask.trim().length==0) {
			popupCommonErrorMsg("Enter Discussion Details");
			return false;
		}
		
		var mom = "MOM";
		var flid = jQuery("#frmNewMom input[id='flid']").val(); 
		//alert("flid"+flid);
		var Mstkeyid = jQuery('#txtMomsKeyid').val();
		
		ActionPlan = jQuery("#momGrid").jqGrid('getCell',rowid,"txtApldActionplan");
		//alert("Mstkeyid"+Mstkeyid);
		if(Mstkeyid.trim().length>0){
			if(refDocId.trim().length>0){
			 var flid = jQuery("#frmNewMom input[id='flid']").val();
			// alert("The flid"+flid);
			 var pasdate= getFieldValue("dteMomsDate", "frmNewMom");
			 var keyid=jQuery('#txtMomsKeyid').val();
			 
			 if(mode=="view"){
			     apMode = "view";
			   //  alert("OPENActionPLan");
				 openActionPlan("Actionplane",keyid,"MOM",flid,mainTask,refDocId,pasdate,apMode);
			 }else
			    {
				//    alert("Else first");
           	        saveForm('frmNewMom','NewMoMeetingForm_save.nmom?momactnpln=momactnpln&rowid='+rowid);
		         }	
			}
			else
				//alert("Else second");
				 saveForm('frmNewMom','NewMoMeetingForm_save.nmom?momactnpln=momactnpln&rowid='+rowid);
		}else
			//alert("Else third");
			 saveForm('frmNewMom','NewMoMeetingForm_save.nmom?momactnpln=momactnpln&rowid='+rowid);
		}
	  

	function momGridkpibtn_onClick(result){
		//alert(" click");
		var rowid=result.rowId;
		var btnid=result.btnId; 
		var Pillar ;
		var mode = jQuery("#frmNewMom input[id=mode]").val();
        if(mode=="view")
        	Pillar = jQuery("#momGrid").jqGrid('getCell',rowid,"cmbMomdPillar");
        else
        	Pillar = jQuery("#cmbMomdPillar_momGrid_"+rowid).combobox("getValue");
			
		if(Pillar.length > 0 ||Pillar!=null || Pillar!= ''){
			dtlKeyid = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomsKeyid");
		
		var mom = "MOM";     
		var pillarCode =jQuery("#momGrid").jqGrid('getCell', rowid, "PillarID");
		var kpikeyid =jQuery("#momGrid").jqGrid('getCell',rowid,"txtMokpKeyid");
		
		if(kpikeyid .trim().length<=0)
		   kpikeyid ="";
			
		jQuery('#hdnRowCoId').val(rowid);
		var flid =jQuery("#frmNewMom input[id='flid']").val();
		var ds = "?from=MOM"+"&flid="+flid+"&kpikeyid="+kpikeyid+"&pillar="+Pillar;

		LoadPopUp("divIndicatorPop", "kpiIndicatorList_view.kpiActKk"+ds,true, "38%", "75%", "0px", "40%", "multiSelectOk_Callback","KPI Indicator", false);
   }
}

function divIndicatorPop_onClose(){
	   var indicators = jQuery('#selePillarId').val();
	   var KpiKeyid = jQuery('#seleKeyid').val();
	   var row =  jQuery('#hdnRowCoId').val();
	   jQuery("#momGrid").jqGrid('setCell',row,'kpi',indicators);
	   jQuery("#momGrid").jqGrid('setCell',row,'txtMokpKeyid',KpiKeyid);
	   return true;
}
		
		
	function frmNewMom_beforeDelete() {
		if (confirm("Are you sure want to delete ?")==false)
			return false;	   	
	}

	function frmNewMom_beforeSubmit(){
		var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var sectId = jQuery("#frmNewMom input[id='section']").val();
		var Type=jQuery('#hdntype').val();
		
		
		//Special characters
		var agenda=jQuery('#txtMomsAgenda').val();
		var safetyTalk = jQuery('#txtMomsSafetytalk').val();
		var remarks = jQuery('#txtMomsRemarks').val();

		if (hasSpecialCharacters(agenda)) {
		    alert("Special Characters Not Allowed in Agenda");
		    return false; // stop further execution if needed
		}

		if (hasSpecialCharacters(safetyTalk)) {
		    alert("Special Characters Not Allowed in Safety Talk");
		    return false;
		}

		if (hasSpecialCharacters(remarks)) {
		    alert("Special Characters Not Allowed in Remarks");
		    return false;
		}	

		
		
		var flid = jQuery("#frmNewMom input[id='flid']").val();
		var mom="MOM";
	    if((Type=="CEC" && agenda.length==0)||(Type=="DEC" && agenda.length==0)){
			popupCommonErrorMsg("Enter Agenda");
			return false;
		}		
		if(Type=="JH" && cellId.length==0){
			popupCommonErrorMsg("Select JH");
			return false;
		}
		if(Type=="DMT" && sectId.length==0){
			popupCommonErrorMsg("Select DMT");
			return false;
		}	 
   		var save=jQuery('#hdnsavebtn').val();
		if((save.trim().length== "0" || save!= "Y")){
			var gridvalue=getGridSelectArray("momGrid");
			var mstkeyid=jQuery('#txtMomsKeyid').val();			
			if(gridvalue.trim().length<=0){
				var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox');
	        	var val = getFieldValue("cmbMomsMeetingtype", "frmNewMom");
			    if(MomAtt.trim().length<=0 && mstkeyid.trim().length<=0)
				{
					alert(" Select Atleast One Employee For Attendance ");
					return false;
				}else
				 {   
				    gridData +='&MomAtt=' +MomAtt;
				    return gridData;
				 }
     	        	return true;
	        }else{
	        	 var row=jQuery("#momGrid").jqGrid('getGridParam', 'selrow');
	      	    var Discussiondetail=getFieldValue("txtMomdDiscussionDetails_momGrid_"+row); 
	    	    if(Discussiondetail.trim().length==0){
	       	    	 popupCommonErrorMsg("Enter the Discussion Details");
	       	    	 return false;
	       	     }
	    	    var Responsiblity=jQuery("#cmbApldResponsibility_momGrid_"+row).combobox("getValue");
	    	    var targetDate=jQuery("#dteApldTargetdate_momGrid_"+row).datebox('getValue');
	       		var ActionPlanDesc=getFieldValue("txtApldActionplan_momGrid_"+row);
	       		var ActionPlanStatus=getFieldValue("cboApldStatus_momGrid_"+row);
	       		//alert('['+ActionPlanDesc+']');
	       		//alert('['+ActionPlanDesc+']');
	       	    if(ActionPlanDesc.trim().length>0){
	     			 if(targetDate.trim().length==0 || targetDate==null || targetDate=='' || targetDate==' '){
	        				popupCommonErrorMsg("Select the Target Date");
	        				return false;
	        			 } 
	       			if(Responsiblity.trim().length==0 || Responsiblity==null || Responsiblity=='' || Responsiblity==' '){
	       				popupCommonErrorMsg("Select the Responsiblity");
	       				return false;
	       			 }
	       			
	       		 }
	       	 if(targetDate.trim().length>0){
       			 if(ActionPlanDesc.trim().length==0 || ActionPlanDesc==null || ActionPlanDesc==""){
       			    //    popupCommonErrorMsg("Enter the ActionPlan");
       			     //   return false;
       			 }
       		 }
       		 if(Responsiblity.trim().length>0){
       			 if(ActionPlanDesc.trim().length==0 || ActionPlanDesc==null || ActionPlanDesc==""){
       				 popupCommonErrorMsg("Enter the ActionPlan");
       				 return false;
       			 }
	       		 
	       		 
	       		 }
	       		var ActionPlanId=jQuery("#momGrid").jqGrid('getCell', row, "txtMomdActionplanId");
	       		var ActionplanDetailId=jQuery("#momGrid").jqGrid('getCell',row,"txtapldkeyid");
	        	var gridData = '&momDetails=' + gridvalue+'&flid='+flid+'&mom='+mom+'&ActionPlanDesc='+ActionPlanDesc+'&Discussiondetail='+Discussiondetail+'&Responsiblity='+Responsiblity+'&targetDate='+targetDate+'&ActionPlanId='+ActionPlanId+'&ActionplanDetailId='+ActionplanDetailId+'&row='+row+'&ActionPlanStatus='+ActionPlanStatus;
			    gridData +='&KpiIndicator=' + getselectKPI();
			    var MomAtt= getSelectdRowsAtt('attandanceGrid', 'MomAttcheckbox_', 'chkbox'); 
			   // alert("else momatt"+MomAtt);
			    if(MomAtt.trim().length<=0 && mstkeyid.trim().length<=0)
				{
					alert(" Select Atleast One Employee For Attendance ");
					return false;
				}
			    else
				 {   
				    gridData +='&MomAtt=' +MomAtt;
				    return gridData;
				 }
	         }
	    }	    
	}
	
	function convertJHFSAuditToJSONString() {
		var momrow = jQuery("#momGrid").jqGrid('getDataIDs');//	row get data
		var momcol = jQuery("#momGrid").jqGrid("getGridParam", "colModel");// col get data
		var rowid = "";
		var controlId = "";
		var cKeyId; //det keyid
		var colValue = "";
		var remarksVal = "";
		var colValue1 = "";
		var colValue2 = "";
		var controlId2 = "";
		var jsonArrO = '[';
		for (i = 0; i < momrow.length; i++) { 
			rowid = momrow[i];
			cKeyId = jQuery("#momGrid").jqGrid('getCell', rowid, "Keyid"); // propertie index id of value detailkeyid 
			var colkeyid = jQuery('#txtMomsKeyid').val(); // call master key id 
	        var actionplan = jQuery("#momGrid").jqGrid('getCell', momrow[i],"actPlan");
		if (actionplan!= null || actionplan != ""){
			actionplan ="";
		}
		    var checkval=jQuery("#momGrid").jqGrid('getCell',rowid,"check");
	    if(checkval=="1"||checkval==""||checkval!=0){
			var type = getComboBoxText("momGridcmbMomdDiscussionType_"+momrow[i]);
			var Pillar = jQuery("#momGrid").jqGrid('getCell',rowid,"PillarID");
			controlId ="momGridtxtMomdDiscussionDetails_"+ momrow[i];
            colValue = jQuery('#' + controlId).val();
			remarksVal = jQuery("#momGridtxtMomdRemarks_"+ momrow[i]).val();
        if ((colValue != null && colValue != "")|| (colValue1 != null && colValue1 != "")) {
			jsonArrO += '{';
			jsonArrO += '"txtMomdKeyid":"' + cKeyId + '",';
			jsonArrO += '"txtMomdMomsKeyid":"' + colkeyid + '",';
			jsonArrO += '"txtMomdDiscussionDetails":"' + colValue + '",';
			jsonArrO += '"txtMomdRemarks":"' + remarksVal + '",'; 
			jsonArrO += '"txtMomdactPlan":"' + actionplan + '",';
			jsonArrO += '"cmbMomdDiscussionType":"' + type + '",';
			jsonArrO += '"cmbMomdPillar":"' + Pillar + '"';
			jsonArrO += '},';
		}
	  }
	}	
	if (jsonArrO != "[")
			jsonArrO = jsonArrO.slice(0, -1) + "]";
		else
			jsonArrO = "";
		return jsonArrO;
	}
	
	function getselectKPI(){
		var momrow = jQuery("#momGrid").jqGrid('getDataIDs');//	row get data
		var momcol = jQuery("#momGrid").jqGrid("getGridParam", "colModel");// col get data
		var rowid = "";
		var jsonArrO = '[';
		for (i = 0; i < momrow.length; i++) {
			
			if(jQuery('#jqg_momGrid_'+momrow[i]).is(':checked') == true){ 
			
			var Kpikeyid = jQuery("#momGrid").jqGrid('getCell', momrow[i],"txtMokpKeyid"); // Kpi Indicator Keyid
			var KpiInKeyid = Kpikeyid.split(',');
			//alert("KpiInKeyid "+KpiInKeyid);
			for (j = 0; j < KpiInKeyid.length; j++) {//alert(2);
                
				var Maskeyid = jQuery('#txtMomsKeyid').val(); // Master Keyid  
				var DtlKeyId = jQuery("#momGrid").jqGrid('getCell', momrow[i],"txtMomdKeyid"); // Details keyid
				var kpi = jQuery("#momGrid").jqGrid('getCell', momrow[i],"kpi"); // Details keyid
				if(kpi.length>0){
					if (((Maskeyid != null || Maskeyid != "") || (DtlKeyId != null || DtlKeyId != ""))
							|| ((Maskeyid == null || Maskeyid == "") || (DtlKeyId == null || DtlKeyId == ""))) 
						{
						jsonArrO += '{';
						jsonArrO += '"txtmokpKinkKeyid":"' + KpiInKeyid[j] + "\",";
						jsonArrO += '"txtmokpMomdKeyid":"' + DtlKeyId + "\",";
						jsonArrO += '"txtmokpMomsKeyid":"' + Maskeyid + "\"";
						jsonArrO += '},';   
						//alert("jsonArrO "+jsonArrO) ;
					}	
				}		
			}
		
		}
		}
		if (jsonArrO != "[")
			jsonArrO = jsonArrO.slice(0, -1) + "]";
		else
			jsonArrO = "";
		//alert(" Inside jsonArrO "+jsonArrO);
		return jsonArrO;
		
	}
	function MomeetinChechappen(meeting) {//alert(" meeting:::: "+meeting);
		if (meeting == 'Y') {
			jQuery('#chkMomsIsmeetinghappen').prop('checked', true);
		} else if (meeting == 'N') {
			jQuery('#chkMomsIsmeetinghappen').prop('checked', false);
		}
	}

	function AttandancesCheck(meetingatt) {
		if (meetingatt == 'Y') {
			jQuery('#Att_checkbox_').prop('checked', true);
		} else if (meetingatt == 'N') {
			jQuery('#Att_checkbox_').prop('checked', false);
		}
	}

	function MommeetigSortable_loadComplete() {
		jQuery("#momGrid").children().removeClass("ui-jqgrid-sortable");
	}
	function MommeetigSortable1_loadComplete() {
		jQuery("#attandanceGrid").children().removeClass("ui-jqgrid-sortable");
	}

	function remove_successCallBack(result){
		alert(result.successData);
		jQuery("#momGrid").trigger("reloadGrid");
	}
	function remove_errorCallBack() {
	}
	function removeRecord(keyid) {
		var momrow = jQuery("#momGrid").jqGrid('getDataIDs');//	row get data
		for (i = 0; i < momrow.length; i++) {
        var rowid = momrow[i];
        if (jQuery('#jqg_momGrid_'+momrow[i]).is(':checked') == true){//alert(1);
		    
			var keyid = jQuery("#momGrid").jqGrid('getCell', rowid, "txtMomdKeyid");
            //alert("The Keyid::"+keyid);
            var ActionPMasterId=jQuery("#momGrid").jqGrid('getCell',rowid,"txtMomdActionplanId");
           // alert(ActionPMasterId);
             if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) {
				    var r = confirm("Do You Want To Delete?");
					if (r == true) {
						processAjaxCalls("NewMoMeeting_remove.nmom", "keyid="+ keyid+"&ActionPMasterId="+ActionPMasterId, 'remove_successCallBack','remove_errorCallBack');
					} else{
						return false;
					} 
               	}else {
                    var r = confirm("Do You Want To Remove Row?");
					if (r == true)
						jQuery("#momGrid").trigger("reloadGrid");
					else
						return false;
				}
             }
           }	}


    function frmNewMom_successsCallback(result) { //alert(" SuccessCallBack :: "+result.MomMstkeyid);	  

        var rowid=result.RowId;
	    jQuery('#hdnrowid').val(rowid);
      
        var momactnpln =result.momactnpln;
        jQuery('#hdnactnmode').val(momactnpln);
        
        //alert(" momactnpln :: "+momactnpln);//MomMstkeyid
        
        var filemanger =result.filemanger;
        var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
       // alert("success mainTask"+mainTask);
        var checkvis=jQuery('#hdnCheckvist').val();
        
        var MstNo=result.MomMstNo;
        jQuery('#txtMomsMeetingno').val(MstNo);
      //  var ActionPlanKeyid=result.ActionKeyid;
       // alert("The ActionPlanKeyid"+ActionPlanKeyid);
        var MstKeyid=result.MomMstkeyid;
        jQuery('#txtMomsKeyid').val(MstKeyid);
        var flid =jQuery("#frmNewMom input[id='flid']").val();
        var cellId = jQuery("#frmNewMom input[id='cell']").val();
		var momdate = getFieldValue("dteMomsDate", "frmNewMom");//
		// jQuery("#momGrid").jqGrid('setCell',rowid,'txtMomdActionplanId',ActionPlanKeyid);
		if(filemanger==true){
        	if(MstKeyid.trim().length>0){
	   			 var keyid=result.MomMstkeyid;
	   			 fileManagerPopUp(keyid,"MOM","","","");
   			 }
   		}
		
		processGridnew("NewMoMeetingMom_input.nmom","?q=2&keyid="+MstKeyid+"&type="+glbType, "momGrid", "pagermom","","","","loadCompleteAction");		
		jQuery('#btnExcelVw').show();
		jQuery('#btnMomMail').show();
		jQuery('#chkSelectAll').prop('checked', false);
         var Mettingtype =getFieldValue('cmbMomsMeetingtype');
         var mkeyid = jQuery("#txtMomsKeyid").val();
   
		setTimeout(function() {
			fnFillAttendanceGrid();
		},500);
		if((Mettingtype=="CEC"||Mettingtype=="DEC")||(Mettingtype=="P" && mkeyid.trim().length>0)){
    	    setTimeout(function() {
        	    frmNewMomcmbMomsPillargroup_onSelect();
    		},500);
        }
		
        
	}
    
    
    function loadCompleteAction()
	{
	        var momactnpln=jQuery('#hdnactnmode').val();
			var rowid=jQuery('#hdnrowid').val();
			var MstKeyid=jQuery('#txtMomsKeyid').val();
			if(momactnpln==true || momactnpln=='true'   ){
	        	if(MstKeyid.trim().length>0){
					 var flid = jQuery("#frmNewMom input[id='flid']").val();
					 var refDocId = jQuery("#momGrid").jqGrid('getCell',rowid, "txtMomdKeyid");  
					 var mainTask = jQuery("#momGrid").jqGrid('getCell',rowid, "txtMomdDiscussionDetails");
					 //var mainTask = getFieldValue("txtMomdDiscussionDetails_momGrid_"+rowid);
					 var pasdate= getFieldValue("dteMomsDate", "frmNewMom");
					 var keyid=jQuery('#txtMomsKeyid').val();
					 var mode = jQuery("#frmNewMom input[id=mode]").val();
					 var type=jQuery('#hdntype').val();
					 var pillarid=getFieldValue("cmbMomsPillarid", "frmNewMom");

				        if(pillarid.trim().length==0)
				        	pillarid='-';
			        	 
					 if(mode.trim().length>0){
						 apMode = "view";
      					 openActionPlan("Actionplane",MstKeyid,"MOM",flid,mainTask,refDocId,pasdate,apMode);
					 }
					 else{
						 //openActionPlan("Actionplane",MstKeyid,"MOM",flid,mainTask,refDocId,pasdate,"create");

						 var dataStr ="actPlanRefMasId="+MstKeyid+"&actPlanRefDocType=MOM"+"&flid="+flid+"&actPlanMainTask="+escape(mainTask) +"&actPlanRefDtlId="+refDocId;
							dataStr+="&actPlanRefDate="+pasdate+"&apMode=create"+"&type="+type+"&pillarid="+pillarid;
							LoadPopUp("Actionplane","ActionPlan_input.api?"+dataStr,true,"83%","90%","3%","7%","","Action Plan","",false);
					 }
				}
			}
	        else
				 { 
					 return false;
				 }
}
</script>

<form name="frmNewMom" id="frmNewMom">
        <div id="wrapperRpt" style="width :100%">
        <table>
        <tr>
        <td colspan="2">
        <div id="frmmomFuntKeyIds">
					<input type="hidden" id="factory" name="cmbMomdFactoryid" value=""></input>
					<input type="hidden" id="section" name="cmbMomdSectionid" value=""></input>
					<input type="hidden" id="location" name="cmbMomdLocationId" value=""></input> 
					<input type="hidden" id="sbu" name="cmbMomdSbu" value=""></input> 
					<input type="hidden" id="pbu"    name="cmbMomdPbu" value=""></input>
					<input type="hidden" id="dmt"    name="cmbMomdDmt" value=""></input>
					<input type="hidden" id="jh"    name="cmbMomdJh" value=""></input>
					<input type="hidden" id="cell"    name="cmbMomdCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbMomdMachineid" value=""></input>
					<input type="hidden" id="flid"    name="cmbMomsFlid" value="${requestScope.mom.momsFlid} "></input>
					<input type="hidden" id="elementId"    name="cmbelementid" value="${requestScope.mom.elementid} "></input>        
		</div>

     <div  class="easyui-paddingbfpx" id="MomAttfunLocation" style="width: 104%;margin-top:-12px;width:108%\9;"></div>
     </td>
     <td valign="top" style="position:relative;">
     <div style="margin-top:-10px;margin-left:10px;position:absolute;width:200px;">
		<label class="mandatory-lbl">Date</label>
		<span style="padding-left:80px;">
		<label id="lblShift" class="mandatory-lbl" style="padding-left:0px;">Shift</label>
		</span>
		<div style="padding-left:5px;">
		<input  id="dteMomsDate" name="dteMomsDate" class="easyui-datebox" style="width:90px;" value="${requestScope.mstDate}"/>
		<span id="spnShift">
		<input class="easyui-combobox" id="cmbMomsShiftid" name="cmbMomsShiftid"  style=" width : 80px;" value="${requestScope.mom.momsShiftid}" />
		</span>
		<div>
		<table>
			<tr>
				<td>
				</td>
				<td>
				<span id="err_dteMomsDate" class="tpm-errormsg"></span>
				</td>
			</tr>
		</table>
		</div>
		</div>
	</div>	
		<table>
			<tr>
				<td style="padding-left:0px;padding-top:-10px;"><span id="err_dteMomsDate" class="tpm-errormsg"></span></td>
				<td style="padding-left:32px;"><span id="err_cmbMomsShiftid" class="tpm-errormsg"></span></td>
			</tr>
		</table>
		
		</td>
      
      <td valign="top" style="margin-top:-10px;">
      <div id="meetinghppnd" style="padding-top:14px;padding-left:170px;width:160px;">
<!--      <span style="padding-left:4px; display:none;">-->
<!--       <input type="checkbox" id="chkMomsIsmeetinghappen" name="chkMomsIsmeetinghappen" value="Y"> <label style="vertical-align: top;" m> Meeting Happened?</label>-->
<!--      </span>-->
       <span style="padding-left:6px;">
			  <input type="checkbox" id="chkMomsIsmeetinghappen" name="chkMomsIsmeetinghappen" value="Y"> <label style="vertical-align: top" class="mandatory-lbl"> Meeting Happened?</label>
	   </span>
       </div>
       
       </td>
       
       <td>
       <div style="margin-left:6px;margin-top:-6px;margin-bottom:2px;">
      		<input type="button" class="easyui-button" id="btnMomMail" name="btnMomMail" value="Mail" style="height:23px;width:70px;display:none;"/>
		</div>
	   	<span style="padding-left:6px;">
			<input type="button" class="easyui-button" id="btnjhActivity" name="btnjhActivity" value="Jh Activity" style="height:23px;width:70px;"/>
	    </span>
		
    </td>
	
     </tr>
    <tr>
    
    <td colspan="3">
    
     <div id="MessageBoard" style="margin-right:-26px;width:180px;float:right;display: none;">
	      <input type="checkbox" id="chkMomsIsmessageboard" name="chkMomsIsmessageboard" <c:out value = "${requestScope.mom.momsIsmessageboard == 'Y' ? ' checked':' '}"/> value="Y" > <label style="vertical-align: top"> Show In Message Board</label>
	 </div>
       
   </td>
   <td>
   <div style="margin-left:6px;">
      <input type="button" class="easyui-button" id="btnExcelVw" name="btnExcelVw" value="Excel View" style="height:23px;width:70px;display:none;"/>
	</div>
	
   </td>	    
    </tr>
    
     </table>
 <div id="tabMom" class="easyui-tabs" style="height:auto; width: 1150px; width:1150px\9; margin-top:-0px; float: left;">

 <div title="Mom">
 <table style="margin-left:8px;margin-left:2px\9;"> 
 <tr>
 
 <td valign="top">
         <div style="padding-top:12px;">
         <span style="margin-left:5px;">
		<label id="MeetingType" class="mandatory-lbl">Meeting Type</label>
		</span>
		<span style="padding-left:45px;"><label id="pillarlblid">Pillar</label></span>
		<div style="margin-left:5px;">
		<input class="easyui-combobox" id="cmbMomsMeetingtype" name="cmbMomsMeetingtype" readonly="readonly" style=" width : 120px;"  value="${requestScope.mom.momsMeetingtype}" />
		<span id="spnPillar" style="padding-left:0px;">
		<input class="easyui-combobox" id="cmbMomsPillarid" name="cmbMomsPillarid"  style=" width : 120px;"  value="${requestScope.mom.momsPillarid}" />
		</span>
		</div>
		</div>
		
		<div style="padding-left:4px;">
		 <label id="MeetingTitle" >Meeting Title</label>
		 
		<span style="padding-left:90px;" id="lblMeetingNumber"> 
			<label id="MeetingNumber" >Meeting Number</label>
		</span>
		<div>
        
        </div>
        <div><input type="text" class="easyui-text" id="txtMomsMeetingtitle" name="txtMomsMeetingtitle" maxlength="98"   style=" width : 160px;" value="${requestScope.mom.momsMeetingtitle}"/>
        <span >
        <input type="text" class="easyui-text"  id="txtMomsMeetingno" name="txtMomsMeetingno" maxlength="10"   style=" width : 120px; text-align:left;" value="${requestScope.mom.momsKeyid}"/>
        </span>
        </div>
		</div>
		
		<table>
			<tr>
			<td><span id="err_cmbMomsMeetingtype" class="tpm-errormsg"></span></td>
			<td style="padding-left:126px;"><span id="err_cmbMomsPillarid" class="tpm-errormsg"></span></td>
			</tr>
		</table>
		</td>
		
	<td valign="top" style="padding-top:16px;padding-left:6px;">
       
    </td>	
	
  <td valign="top" style="padding-left:6px;padding-top:16px;">
       <div style="margin-left:5px;"><label id="Agenda">Agenda </label></div>
            <textarea  rows="3"  cols="17" id="txtMomsAgenda" maxlength="500" name="txtMomsAgenda"  style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"   >${requestScope.mom.momsAgenda}</textarea>
  </td>
 
	<td valign="top" style="padding-top:16px;padding-left:0%; width : 220px;">
         
         <div style="margin-left:5px;"><label id="SafetyTalk" >Safety Talk </label></div>
               <textarea  rows="3"  cols="17" id="txtMomsSafetytalk" maxlength="500" name="txtMomsSafetytalk"  style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"   >${requestScope.mom.momsSafetytalk}</textarea>
               <span id="err_txtMomsSafetytalk" class="tpm-errormsg" style="padding-left:10px;"></span>
        
         </td>
  <td valign="top" style="padding-top:16px;width:220px;"> 
         <div style="margin-left:5px;"><label id="lblRemarks">Remarks </label></div>
         <span id="spnMomsRemarks">   
         <textarea  rows="3"  cols="17" id="txtMomsRemarks" maxlength="500" name="txtMomsRemarks" style="height : 50px; margin-left: 5px;text-transform: ; width : 220px;"  >${requestScope.mom.momsRemarks}</textarea>
         </span>
         <span id="err_txtMomsRemarks" class="tpm-errormsg"></span>
         
         <div  style="margin-top: 10px;margin-left:-18px;">
	       <input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px"/>
	       <span style="padding-left:8px;">
	          <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/>
	       </span>
	        <span  id="MomFilemgr" style="position:absolute;margin-left:10px;margin-left:85px\9;margin-top:-1px;margin-top:-3px\9;" >
	     	</span>
     	</div>
  </td> 

        
 </tr>
    <tr>
        
    </tr>
    <tr>
       
       <td valign="middle" >
       <div  style="margin-top: 0px;margin-left:10%;display:none;">
       <input type="button" class="easyui-button" value ="Add" id="btnAddNew" style="height:23px"/>
       <span style="padding-left:8px;">
       <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/>
       </span>
       
		<span  id="MomFilemgr" style="position:absolute;margin-left:10px;margin-left:85px\9;margin-top:-1px;margin-top:-3px\9;" >
     	</span>
     	</div>
       </td>
     </tr>
    
</table>
     <div style="margin-left:0%;margin-left:39px\9;margin-top:-8px;\0\margin-top:5px;" >
     <div style="margin-left:75%;margin-left:60%\9;">
      
     </div>
     <div style="margin-left:14px;margin-top:10px;">
		<table id="momGrid" style=" "> <tr> <td> </td></tr> </table>
	 </div>
	<div style="height:10px;"></div>
	</div>
</div>

<div  title="Attendance">
<table style="padding-left:20px;padding-top:10px;padding-left:50px\9">
	<tr>
	
	<td valign="bottom">
		<input type="checkbox" id="chkSelectAll" name="chkSelectAll" style="margin-left:10px;" />
		<input type="text" value="Select All" readonly="readonly" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
	</td>
	
	<td valign="top" style="padding-left: 20px;">
	<div>
	<label>Role</label>
	</div>
	<div>
	   <input class="easyui-combobox" id="cmbMomsRole" name="cmbMomsRole"  style=" width : 260px;"  value=" " />
	</div>
    </td>
	
	<td valign="bottom">
	<div style="padding-left:20px;">
	   <input type="button" class="easyui-button" id="btnAddAllEmployee" name="btnAddAllEmployee" value="Add All Employee" style="height:23px;display:none;"/>
	</div>
	</td>
	
	<td valign="bottom">
	<div style="margin-top:-10px;">
	<div style="padding-left:0px;">
	   <span style="padding-left:0px;"><label id="pillargrouplblid">Pillar Group</label></span>
	</div>
	<div>
	<span id="spnPillargroup" style="padding-left:0px;">
		<input class="easyui-combobox" id="cmbMomsPillargroup" name="cmbMomsPillargroup"  style=" width : 100px;"  value="${requestScope.mom.momsPillargroup}"/>
	</span>
	</div>
	</div>
	</td>
	<td valign="top" >
	<div style="padding-left:20px;margin-top:-4px;">
		<div>
		<label>Employee</label>
		<input type="checkbox" id="chkMomsOthers" name="chkMomsOthers" style="margin-left:10px;" />
	    <span>
		    <label>	Others</label>
	    </span>
		</div>
		<div>
		   <input class="easyui-combobox" id="cmbMomsEmployee" name="cmbMomsEmployee"  style=" width : 220px;"  value="" />
		</div>
		</div>
    </td>
    
    <td valign="top">
      <div style="padding-left:20px;margin-top:10px;">
       <input type="button" class="easyui-button" id="btnAddEmployee" name="btnAddEmployee" value="Add Employee" style="height:23px;"/>
      </div>
    </td>
    
		 
	<td valign="top" >
	<div style="margin-left:0%">
	 <input type="button" class="easyui-button" id="btnAttsave" name="btnAttsave" value="Save" style="height:23px;display:none;"/>
	 <span style="padding-left: 14px;">
	<label>External Members</label>
	</span>
    <span style="padding-left: 0px;">
    <input type="button" id="btnaddOthers" class="easyui-button" value="..." style="cursor: default;"/>
    </span>
    </div>
    </td>
	</tr>
</table>
	
<!-- 	<div class="easyui-paddingbfpx" style="margin-left:475px;">
<select class="easyui-text" id="cboAPlanStatus" name="cboAPlanStatus" panelHeight=80px;  style="width:  100px; height: 21px;" >
							<option value='P'>Pending</option>
							<option value='C'>Completed</option>
						</select>
						</div> -->
  <div style="margin-left:24px; margin-left:65px\9;">
  
	<table id="attandanceGrid">
			<tr><td></td></tr>
	</table>
	        <div id="pageratt"></div>
   </div>
   <div style="height:20px;"></div>
	
  </div>	
  </div>

   </div>
      
      <input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
      <input type="hidden" id="hdndate" name="hdndate"/>
      <input type="hidden" id="hdnsavebtn" name="hdnsavebtn"/>
      <input type="hidden" id="hdnCheckvist" name="hdnCheckvist"/>
      <input type="hidden" id="hdnMetngtitle" name="hdnMetngtitle"/>
      <input type="hidden" id="hdnMomsMtntyp" name="hdnMomsMtntyp" />
      <input type="hidden" id="hdnMomsMtngno" name="hdnMomsMtngno"/>
      <input type="hidden" id="hdnMomsMtngsafety"/>
      <input type="hidden" id="txtMomdMomsKeyid" name="txtMomdMomsKeyid" value="${requestScope.key}"/>
      <input type="hidden" id="txtMomaMomsKeyid" name="txtMomaMomsKeyid" value="${requestScope.key}"/>    <%--  att master Key Id --%>  
      <input type="hidden" id="txtMomsKeyid" name="txtMomsKeyid" value="${requestScope.mstkeyid}"/>
      <input type="hidden" id="meeting" name="meeting" value="${requestScope.mom.momsIsmeetinghappen}"/>
      <input type="hidden" id="meetingatt" name="meetingatt" value="${requestScope.moma. momaAttandance}"/>
      <input type="hidden" id="checkempvalue" name="checkempvalue" value="${requestScope.CHKBOX}"/> 
      <input type="hidden" id="hdnVal" name="hdnVal" value="1"/>
      <input type="hidden" id="hdnValcheck" name="hdnValcheck" value="Y"/>
      <input type="hidden" id="hdnRowCoId" name="hdnRowCoId" value=""/>
      <input type="hidden" id="hdnPillarIds" name="hdnPillarIds" />
      <input type="hidden" id="hdnmeethappnd" name="hdnmeethappnd" value="${requestScope.MEETHAPPEN}"/>
      <input type="hidden" id="hdnDMT" name="hdnDMT" value="${requestScope.DMT}"/>
      <input type="hidden" id="hdnDMTDBLE" name="hdnDMTDBLE" value="${requestScope.DMTDBLE}"/>
      <input type="hidden" id="hdnnewaddemp" name="hdnnewaddemp" />
      <input type="hidden" id="hdnactnmode" name="hdnactnmode" value=""/>
      <input type="hidden" id="hdnrowid" name="hdnrowid" value=""/>
      <input type="hidden" id="hdnMomsRefdocid" name="hdnMomsRefdocid" value="${requestScope.momRefDocId}"/>
	  <input type="hidden" id="hdnMomsRefdoctype" name="hdnMomsRefdoctype" value="${requestScope.momRefDocType}"/>
	  <input type="hidden"id="txtAplmDetailrefid" name="txtAplmDetailrefid" value="${requestScope.newGenTlActionplanmst.aplmDetailrefid}" />
	 <input type="hidden"id="txtAplmRefdoctype" name="txtAplmRefdoctype" value="${requestScope.newGenTlActionplanmst.aplmRefdoctype}" />
	 <input type="hidden" id="txtAplmMasterrefid" name="txtAplmMasterrefid" value="${requestScope.newGenTlActionplanmst.aplmMasterrefid}" />     
      <input type="hidden" id="hdnAttRole" name="hdnAttRole" value=""/>  
      <input type="hidden" id="hdnaddemp" name="hdnaddemp" value=""/>
      <input type="hidden" id="hdntype" name="hdntype" value="${requestScope.type}"/>
      <input type="hidden" id="hdntabselect" name="hdntabselect" value="N"/>
      <input type="hidden" id="hdnroledata" name="hdnroledata" value=""/>
      <input type="hidden" id="hdnrecall" name="hdnrecall" value="${requestScope.recall}"/>
      <input type="hidden" id="hdnMno" name="hdnMno" value="${requestScope.Mno}"/>
      <input type="hidden" id="hdnIsmthpn" name="hdnIsmthpn" value="${requestScope.Ismthpn}"/>
      <input type="hidden" id="hdnsfty" name="hdnsfty" value="${requestScope.sfty}"/>
      <input type="hidden" id="hdnrmrk" name="hdnrmrk" value="${requestScope.rmrk}"/>
      <input type="hidden" id="hdntitle" name="hdntitle" value="${requestScope.title}"/>
      <input type="hidden" id="hdnmtype" name="hdnmtype" value="${requestScope.mtype}"/>
      <input type="hidden" id="hdnagnda" name="hdnagnda" value="${requestScope.agnda}"/>
      <input type="hidden" id="hdnpillarid" name="hdnpillarid" value="${requestScope.pillarid}"/>
      <input type="hidden" id="hdnpillargrpid" name="hdnpillargrpid" value="${requestScope.pillargrpid}"/>
      <input type="hidden" id="hdnmomdate" name="hdnmomdate" value=" "/>
      <input type="hidden" id="hdnelementid" name="hdnelementid" value=" "/>
       <input type="hidden" id="hdnMenuMode" name="hdnMenuMode" value="${requestScope.menumode}"/>
      <input type="hidden" id="hdnCurrentDate" name="hdnCurrentDate" value="${requestScope.CurrentDate}">
      <input type="hidden" id="hdnloginFlid" name="hdnloginFlid" value="${requestScope.loginFlid}">
     </form>