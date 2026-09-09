<!-- Created By:Siddharth.A -->
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%> --%>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">	
<script type="text/javascript">
        var glbOplmStatus;
        var glbOplmApprovallevel="-";
        var glbOplmRoleName;
		var oplmStatus=jQuery('#hdnStatus').val();
		if(oplmStatus=="C" && oplmStatus!=null)
			 glbOplmStatus ="C";
		else
			 glbOplmStatus ="-";
		
		jQuery.noConflict();
		jQuery(document).ready(function(){
			//	 alert("OPL");
	        initialiseForm('frmOplCreation');		
		    jQuery("#pillar_checkbox").prop({'readonly':true});	
			viewGrid("Pillar_input.opl","q=2","pillar");
			viewGrid("oplStudent_input.opl","q=2","student");
			
			//alert(" Inside Checking :: "+jQuery('#frmMode').val());
			//alert(" Inside Checking :: 1 "+jQuery('#hdnapprovalMode').val());
			//setFieldValue("cmboplmPreparedid",jQuery('#hdnuser').val());
			
			jQuery('#submitForm').val('frmOplCreation'); // set the id of form to submit
			fillComboBox("frmOplCreation","cmboplmKeyid","combo_documentNo.opl" );
			fillComboBox("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter" );
			fillComboBox("frmOplCreation","cmboplmProcess","process.commonFilter" );
			//fillComboBox("frmOplCreation","cmboplmPreparedid","employee.commonFilter");
			fillComboBox("frmOplCreation","cmboplmApprovedid","employee.commonFilter");
			disableField("frmOplCreation", "chkOplmUtiliseforfuture");
			fillComboBox("frmOplCreation","cmboplmTradeid","Combo_Trade.abnForm");
			fillComboBox("frmOplCreation","cmboplmRole","Rolecombo.mom");
			fillComboBox("frmOplCreation","cmboplmEmployee","employee.commonFilter");
			fillComboBox("frmOplCreation","cmbgridEmployee","employee.commonFilter");
			fillComboBox("frmOplCreation","cmboplmPreparedid","employee.commonFilter");
			fillComboBox("frmOplCreation","cmboplunqepstn","roleMst.commonFilter?&type=opl");
			
           /* jQuery('#chkOplmMpworthy').click(function(){
        		if (jQuery('#chkOplmMpworthy').is(':checked')==false ){
        			jQuery('#chkOplmUtiliseforfuture').prop({'checked':false});
        			disableField("frmOplCreation", "chkOplmUtiliseforfuture");
        			//disableField("frmOplCreation", "chkoplmIsok");
        			//disableField("frmOplCreation", "chkoplmwhyhow");
        			//disableField("frmOplCreation", "chkoplmIspresent");
        			jQuery("#chkoplmIsok").prop("readonly","readonly");
        			jQuery("#chkoplmwhyhow").prop("readonly","readonly");
        			jQuery("#chkoplmIspresent").prop("readonly","readonly");
        		}
        	});*/
			
			  	
			var compId = getFieldValue('company','frmOplCreation');
			var locnId = getFieldValue('location','frmOplCreation');
			var factId = getFieldValue('factory','frmOplCreation');
			var sectId = getFieldValue('section','frmOplCreation');
			var cellId = getFieldValue('cell','frmOplCreation');
			var machId = getFieldValue('machine','frmOplCreation');
			
		   //fillComboBox("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter" );
			 
			imageUpload(jQuery( "#dlgImgAfter" ),'ImageUpload.commonFilter','dlgImgAfter',"imgOplmAfterimage","imgOplAfterImgFilename","415","264",false);
			imageUpload(jQuery( "#btnImfPresent" ),'ImageUpload.commonFilter','btnImfPresent',"imgOplmPresentimage","imgOplPresentImgFilename","415","264",false);	

			processAjaxCalls("ImageUpload.commonFilter","","updateRoleSuccess","");
			
			/** for functionalLocation **/
			var factId = jQuery("#frmOplCreation input[id='factory']").val();
			var sectionId = jQuery("#frmOplCreation input[id='section']").val();
			var cellId = jQuery("#frmOplCreation input[id='cell']").val();
			var machId = jQuery("#frmOplCreation input[id='machine']").val();
			var flid = jQuery("#frmOplCreation input[id='flid']").val();
			//alert(" cellId:::::::: "+jQuery('#cellId').val());
			var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+ "&flid=" +flid;
			//alert(1);
			loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation",dataStr);
			/** --------- **/	
			
			
			jQuery('#frmOplCreation .easyui-text').css('text-transform', 'uppercase');
			jQuery('#frmOplCreation textarea').css('text-transform', 'uppercase');
			jQuery(".txtarea").prop('maxlength','499');
			jQuery(".easyui-text").prop('maxlength','36');
			formatDateBox('dteoplmDate','dd-MMM-yyyy');
			formatDateBox('dtegridDate','dd-MMM-yyyy');
			formatDateBox('dteoplmPrepareddate','dd-MMM-yyyy');
			formatDateBox('dteoplmApproveddate','dd-MMM-yyyy');

			var frmMode=jQuery('#hdnMode').val();
				//alert("frmMode:"+frmMode);		
			//var frmMode=jQuery('#frmMode').val();
			if(frmMode="Update"){
				{
					jQuery('#tabOpl').tabs('select', "General");
					 
					
				}
				
				
			}
			if(frmMode=="Create"){
				
				jQuery('#chkSelectAll').prop("readonly","readonly");
		
				setFieldValue("cmboplmPreparedid",jQuery('#hdnuser').val());
				jQuery("#chkOplmMpworthy").prop("readonly","readonly");
				readOnlyFields("cmboplmEmployee");
				readOnlyFields("cmboplunqepstn");
				readOnlyFields("dtegridDate");
				jQuery('#grdchkone').prop("readonly","readonly");
				jQuery('#grdchktwo').prop("readonly","readonly");
				readOnlyFields("cmbgridEmployee");
				disableUIButton("btnAddEmployee");
				disableUIButton("btnApply");
				
			}
			else if(frmMode=="Approval"){
				enableUIButton("btnExcelview");
				disableUIButton("btnoplupload");	
			}
			else if(formMode=="View"){
				enableUIButton("btnExcelview");		
			}
			else if(frmMode=="Update"||frmMode=="Approval"){
				 readOnlyFields("cmboplmEmployee");
				 readOnlyFields("cmboplunqepstn");
				 disableUIButton("btnAddEmployee");
				 readOnlyFields("dtegridDate");
				 jQuery('#grdchkone').prop("readonly","readonly");
				 jQuery('#grdchktwo').prop("readonly","readonly");
				 readOnlyFields("cmbgridEmployee");
				 disableUIButton("btnAddEmployee");
				 disableUIButton("btnApply");
				 enableUIButton('btnExcelview');
				 jQuery('#chkSelectAll').prop("readonly","readonly");
				 jQuery( "#chkoplmwhyhow" ).click(function() {
                  if(frmMode=="Update"){
					  jQuery('#chkSelectAll').prop("readonly","readonly");
                	}
					   jQuery('#chkoplmwhyhow').prop('checked',true);
					   var r = confirm("Do You Want To Put OPL in Why/How?");
						if (r == true)
							jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
						else
							enableFields("chkoplmIsgeneral");
				
					       if(jQuery("#chkoplmIsok").is(':checked'))
					          jQuery('#chkoplmIsok').prop('checked',false);
					       
					       if(jQuery("#chkoplmIsgeneral").is(':checked'))
					          jQuery('#chkoplmIsgeneral').prop('checked',false);

					       if(jQuery("#chkoplmIspresent").is(':checked'))
						          jQuery('#chkoplmIspresent').prop('checked',false);
				});
					jQuery("#chkoplmIsgeneral").click(function() {
						// alert("general in update"); 
						//location.reload();
						
						
					   jQuery('#chkoplmIsgeneral').prop('checked',true);
					   var result = confirm("Do You Want To Put OPL in General?");
					  
						if (result == true){
							jQuery('#tabOpl').tabs('select', "General");
							jQuery("#chkoplmIspresent").prop("readonly","readonly");
							jQuery("#chkoplmIsok").prop("readonly","readonly");
							jQuery("#chkoplmwhyhow").prop("readonly","readonly");
						
						}	else{
							enableFields("chkoplmIspresent");
							enableFields("chkoplmIsok");
							enableFields("chkoplmwhyhow");
					       if(jQuery("#chkoplmIsok").is(':checked'))
					          jQuery('#chkoplmIsok').prop('checked',false);
					       
					       if(jQuery("#chkoplmwhyhow").is(':checked'))
					          jQuery('#chkoplmwhyhow').prop('checked',false);

					       if(jQuery("#chkoplmIspresent").is(':checked'))
						          jQuery('#chkoplmIspresent').prop('checked',false);
						}   
						});
                   
	            jQuery( "#chkoplmIspresent" ).click(function() {
	            	// alert("present in update");
	            
	            	 jQuery('#chkoplmIspresent').prop('checked',true);
	            	 var result = confirm("Do You Want To Put OPL in Present/After?");
						if (result == true)
							jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
						
						
							//jQuery("#frmmode").trigger("reloadGrid");
						else
							enableFields("chkoplmIsgeneral");
						
				       if(jQuery("#chkoplmIsok").is(':checked'))
				          jQuery('#chkoplmIsok').prop('checked',false);
				       
				       if(jQuery("#chkoplmwhyhow").is(':checked'))
				          jQuery('#chkoplmwhyhow').prop('checked',false);

				       if(jQuery("#chkoplmIsgeneral").is(':checked'))
					          jQuery('#chkoplmIsgeneral').prop('checked',false);
	       		});
	            
	            jQuery( "#chkoplmIsok" ).click(function() {
	            	// alert("ok in update"); 
	            	// alert("present in update");
	 	            
	            	 jQuery('#chkoplmIsok').prop('checked',true);
	            	 var result = confirm("Do You Want To Put OPL in Not Ok/Ok?");
						if (result == true)
							jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
						else
							enableFields("chkoplmIsgeneral");
		
				       if(jQuery("#chkoplmIspresent").is(':checked'))
				          jQuery('#chkoplmIspresent').prop('checked',false);
				       
				       if(jQuery("#chkoplmwhyhow").is(':checked'))
				          jQuery('#chkoplmwhyhow').prop('checked',false);

				       if(jQuery("#chkoplmIsgeneral").is(':checked'))
					          jQuery('#chkoplmIsgeneral').prop('checked',false);
	       		});
	            
	        }

			var formMode=jQuery('#frmMode').val();
			//alert("formMode:"+formMode);
			if(formMode=="Update"){

				jQuery('#Approvedby').removeClass("mandatory-lbl");
		        readOnlyFields("cmboplmApprovedid");
		        jQuery("#chkOplmMpworthy").prop("readonly","readonly");
		        
			}
				
			var oplmDate = getFieldValue("dteoplmDate");
			var oplmApprvDate = getFieldValue("dteoplmApproveddate");
			var oplmPrepareDate = getFieldValue("dteoplmPrepareddate");

			if( oplmDate == null || oplmDate.length <= 0)
			{
				fillWithCurrentDate("dteoplmDate");
			}
			if( oplmApprvDate == null || oplmApprvDate.length <= 0)
			{
				fillWithCurrentDate("dteoplmApproveddate");
			}	
			if( oplmPrepareDate == null || oplmPrepareDate.length <= 1)
			{
				fillWithCurrentDate("dteoplmPrepareddate");
			}
			
			if(jQuery("#relatedToCMB").val() != null && jQuery("#relatedToCMB").val().trim() !='')
			{		  
			   jQuery("#cmboplmRelated").combobox('setValue',jQuery("#relatedToCMB").val());
			   fillComboBox("frmOplCreation","cmboplmMouldid","mould.commonFilter");
				   		  
			}

			if(jQuery('#cmboplmRelated').combobox('getValue')=="MLD")
			   jQuery('#cmboplmMouldid').combobox('enable');
		    else 
			   jQuery('#cmboplmMouldid').combobox('disable');
		/*	jQuery(".tabs-title").bind("click", function(event){
				
				if(jQuery("#studGrid").getGridParam('reccount') <= 0 && jQuery(this).text() == "Student"  ){
					jQuery("#studGrid").setGridParam({url:'oplStudent_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}
				else if( jQuery("#pillarGrid").getGridParam('reccount') <= 0 && jQuery(this).text() == "Lesson" ){
					alert(jQuery("#pillarGrid").getGridParam('reccount') <= 0);
					alert(jQuery(this).text());
					jQuery("#pillarGrid").setGridParam({url:'Pillar_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}
			});
			*/
			var oplId = jQuery('#cmboplmKeyid').combobox('getValue');
			
			if (jQuery("#frmMode").val() == "Approval"){
			//jQuery('#tabOpl').tabs('select', "Lesson");
	// Vignesh Fixing Workflow on Doubleclick
			jQuery('#tabOpl').tabs('select', "Work Flow");
			}
			jQuery("#tabOpl").tabs({ onSelect:function(title){ 
				
				if(jQuery("#pillarGrid").getGridParam('reccount') <= 0 && title=="Lesson")
				{
					//jQuery("#pillarGrid").setGridParam({url:'Pillar_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
				}
				else if(title=="Student")
				{
					//jQuery("#studGrid").setGridParam({url:'oplStudent_getData.opl?oplId='+oplId,datatype:'json'}).trigger('reloadGrid');										
					
				}
			}
			});
			
			jQuery("#imgOplmPresentimage").load(function() {
				//alert(" imageSize :: "+result.imageSize);
				/*if((jQuery(this).width()>415)||(jQuery(this).height()>264))
				{	jQuery('#imgOplmPresentimage').prop('src', "");
					alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
					return false;
				}*
		        
		    });
					
			jQuery("#imgOplmAfterimage").load(function() {
			/*	if((jQuery(this).width()>415)||(jQuery(this).height()>264))
				{	jQuery('#imgOplmAfterimage').prop('src', "");
					alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
					return false;
				}*/
		    });
			
			
			//alert(1);
			//jQuery("#chkoplmwhyhow").prop("checked","checked");
			//alert(2);
			//alert(" frmMode "+jQuery('#frmMode').val());
			// ----------Vignesh for Excle view Adding form mode -- for approval -- //
			  if(jQuery('#frmMode').val()=="View" || jQuery('#frmMode').val()=="Approval"|| jQuery('#Formmode').val()=="APPROVAL" ||frmMode==='approval'|| (frmMode && frmMode.toLowerCase() === 'approval')||frmMode==='approval'){
			    enableUIButton("btnExcelview");
				jQuery("#chkOplmUtiliseforfuture").prop("readonly","readonly");
				jQuery("#chkOplmMpworthy").prop("readonly","readonly");
				jQuery("#chkoplmIsok").prop("readonly","readonly");
    			jQuery("#chkoplmwhyhow").prop("readonly","readonly");
    			jQuery("#chkoplmIspresent").prop("readonly","readonly");
    			jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
    			jQuery("#chkUndrstndOpl").prop("readonly","readonly");
    			jQuery('#txtoplmTheme').prop('readonly',true);
    			enableUIButton("btnExcelview");
    		 }
				  //changeFormInputBackGround('frmOplCreation');
			 
				if(jQuery('#hdnbdmode').val()=="bdmmode")
					//jQuery('#cmboplmMachineid').combobox('disable');

				
				if(jQuery('#hdnoplmIsok').val()=="Y")
					oknotokTabSelection();

				
				if(jQuery('#chkoplmwhyhow').val()=="Y")
					oknotokTabSelectionwhyhow();
			
				if(jQuery('#chkoplmIsgeneral').val()=="Y")
					general();
					//alert(" FormMode :: "+jQuery("#frmMode").val());
					
				if(jQuery("#frmMode").val()=="Create"){ 
					jQuery("#chkoplmwhyhow").prop("checked","checked");
					why();
					//jQuery("#chkoplmIspresent").prop("checked","checked"); 
				}
				
			
				else if(jQuery("#frmMode").val()=="Update"||jQuery("#frmMode").val()=="Approval"||jQuery("#frmMode").val()=="View")//View  
				{	
					// jQuery("#chkoplmwhyhow").prop("checked","checked");
				 //   why(); 
				    //if(jQuery('#chkoplmwhyhow').is(':checked') == true){
				    	//why();
				    //}
				    //jQuery("#chkoplmwhyhow").prop("readonly","readonly");
				   // jQuery("#chkoplmIspresent").prop("readonly","readonly");
					//jQuery("#chkoplmIsok").prop("readonly","readonly");
				    
					//jQuery("#chkoplmwhyhow").prop("readonly","readonly");
				}/*else if(jQuery("#frmMode").val()=="Approval"){
					enableFields("chkOplmUtiliseforfuture");
					jQuery("#chkoplmwhyhow").prop("readonly","readonly");
				    jQuery("#chkoplmIspresent").prop("readonly","readonly");
					jQuery("#chkoplmIsok").prop("readonly","readonly");
				}*/
				
				
				//alert(1234);
				jQuery('#btnExcelview').click( function(){   
	  				var flid = jQuery("#frmOplCreation input[id='flid']").val();
	  				var oplId=jQuery("#cmboplmKeyid").val();
	  				if (jQuery('#chkoplmIsgeneral').is(':checked')==true ){
		  				//var type=jQuery("#chkoplmgeneral").val();
	  				window.open("OplReport_Excelview.oplrpt?&oplId="+oplId+"&flid="+flid+"&type=general","Excel View");
	  				}else 
	  					window.open("OplReport_Excelview.oplrpt?&oplId="+oplId+"&flid="+flid+"&type=other","Excel View");
	  				//window.open("ImpprojSht_view.ipsrpt?&kaizenId="+kaizenId+"&benTypeVal="+benTypeVal+"&flid="+flid+"&workFlow="+Mode);
			  	});
					 
				//alert(5678);
			   fileManagerPopUp("","opl","frmOplCreation","btnFilManage","oplFilemgr");
			   jQuery('#rtdtomld').hide();
			   if( oplId != null && oplId.length > 0 && jQuery('#frmMode').val()!="Update"){	
				   
				   jQuery("#tabOpl").tabs('add',{
						id:"WorkFlow",
						title: "Work Flow",
						closable:false,	
						//height:"380px",
						content:"<div id='oplWorkFlow'  style='margin-left:30px;margin-top:50px;display:none;' ></div>",
						selected: jQuery("#frmMode").val() == "Approval" ? true : false

						
					
					});
				
				   //var flid=jQuery("#flid").val();
				   var flid =jQuery("#frmOplCreation input[id='flid']").val();
				   //alert(" Work Flid :: New :: "+flid);
				   var minDate=getFieldValue('dteoplmDate');
				   //workFlow(divId,isPopup,transCode, documentId, documentType, flId,minDate,maxDate){
			   		var mode=jQuery('#frmMode').val();
			   		
			   		if(mode=="View" && mode.trim().length>0)
				       workFlow("oplWorkFlow",false,"OPLAPPROVE", oplId, "OPL",flid,minDate,"","N");
				    else
					 workFlow("oplWorkFlow",false,"OPLAPPROVE", oplId, "OPL",flid,minDate);
			    	 setTimeout(function(){
		    		    jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		    	    },1000);
			   }	
				
			   /*
			   
			   if(jQuery("#chkoplmwhyhow").is(':checked')){
			   jQuery('#hdnoplmIsok').prop('checked',false);
			   jQuery('#chkoplmwhyhow').prop('checked',false);
		   
		   }
			   
			   */
			   
				   
			   jQuery("#chkoplmIspresent").click(function() {
			       
			   jQuery('#chkoplmIspresent').prop('checked',true);
			   var result = confirm("Do You Want To select Present/After?");
				if (result == true){
					jQuery("#chkoplmwhyhow").prop("readonly","readonly");
					jQuery("#chkoplmIsok").prop("readonly","readonly");
					jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
				
				}	else{
					enableFields("chkoplmwhyhow");
					enableFields("chkoplmIsok");
					enableFields("chkoplmIsgeneral");
			       if(jQuery("#chkoplmIsok").is(':checked'))
			          jQuery('#chkoplmIsok').prop('checked',false);
			       
			       if(jQuery("#chkoplmwhyhow").is(':checked'))
			          jQuery('#chkoplmwhyhow').prop('checked',false);

			       if(jQuery("#chkoplmIsgeneral").is(':checked'))
				          jQuery('#chkoplmIsgeneral').prop('checked',false);
				}
				});
			   
			   jQuery("#btnoplupload").click(function() {
				  var oplKeyid=jQuery("#hdnoplkeyid").val();
				  var oplflid = jQuery("#frmOplCreation input[id='flid']").val();
				  var opldate=jQuery("#dteoplmDate").datebox('getValue');
				  var oplpreparedId=jQuery("#cmboplmPreparedid").combobox('getValue');
				  var opltheme=unescape(encodeURIComponent(getFieldValue("txtoplmTheme")));
				  var opldesc=unescape(encodeURIComponent(getFieldValue("txtoplmClassdescription")));
				  var Mode=jQuery('#frmMode').val();
				  if(jQuery("#chkClassificationB").is(':checked')== true){
					   	 jQuery('#chkClassificationB').val('B');
					   	 var ClassificationB = jQuery('#chkClassificationB:checked').val();
					    }
				 if(jQuery("#chkClassificationI").is(':checked')==true){
					 jQuery('#chkClassificationI').val('I');
					 var ClassificationI=jQuery('#chkClassificationI:checked').val();
				 }
				 if(jQuery("#chkClassificationT").is(':checked')==true){
					 jQuery('#chkClassificationT').val('T');
					 var ClassificationT=jQuery('#chkClassificationT:checked').val();
				 }
				 if(jQuery("#chkClassificationS").is(':checked')==true){
				     jQuery('#chkClassificationS').val('S');
				     var ClassificationS=jQuery('#chkClassificationS:checked').val();
				 }
				 if(jQuery("#chkClassificationC").is(':checked')==true){
				     jQuery('#chkClassificationC').val('C');
				     var ClassificationC=jQuery('#chkClassificationC:checked').val();
				 }
				 if(jQuery("#chkClassificationP").is(':checked')==true){
				     jQuery('#chkClassificationP').val('P');
				     var ClassificationP=jQuery('#chkClassificationP:checked').val();
				 }

/* 				  var ds="?&filterButton=false&oplKeyid="+oplKeyid+"&oplflid="+oplflid+"&opldate="+opldate+"&oplpreparedId="+oplpreparedId;
				  ds=ds+"&op ltheme="+opltheme+"&opldesc="+opldesc+"&Mode="+Mode+"&ClassificationB="+ClassificationB+"&ClassificationI="+ClassificationI+"&ClassificationT="+ClassificationT; */
				  ds=ds+"&opltheme="+opltheme+"&opldesc="+opldesc+"&Mode="+Mode+"&ClassificationB="+ClassificationB+"&ClassificationI="+ClassificationI+"&ClassificationT="+ClassificationT+"&ClassificationS="+ClassificationS+"&ClassificationC="+ClassificationC+"&ClassificationP="+ClassificationP;
				  navigateToNextForm("OplUpload_input.oplUpd"+ds);
			   });
				
				jQuery("#chkoplmwhyhow").click(function() {
				    
				     jQuery('#chkoplmwhyhow').prop('checked',true);
				     var result = confirm("Do You Want To Put OPL in Why/How?");
						if (result == true){
							jQuery("#chkoplmIspresent").prop("readonly","readonly");
							jQuery("#chkoplmIsok").prop("readonly","readonly");
							jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
						
						}	else{
							enableFields("chkoplmIspresent");
							enableFields("chkoplmIsok");
							enableFields("chkoplmIsgeneral");
					  if(jQuery("#chkoplmIsok").is(':checked'))
						jQuery('#chkoplmIsok').prop('checked',false);
					  
					  if(jQuery("#chkoplmIspresent").is(':checked'))
					    jQuery('#chkoplmIspresent').prop('checked',false);

					
					  if(jQuery("#chkoplmIsgeneral").is(':checked'))
				          jQuery('#chkoplmIsgeneral').prop('checked',false);
						}
				});

				jQuery("#btnApply").click(function(){
					 var row = jQuery("#studGrid").jqGrid('getDataIDs');
					 var date=getFieldValue('dtegridDate');
					 var employ=getFieldValue('cmbgridEmployee');
					 var employtext=jQuery("#cmbgridEmployee").combobox("getText");
					 
					 /*for(var i=0;i<row.length;i++){
						 var checkval=jQuery("#studGrid").jqGrid('getCell',(i+1),"chkval4");
						 if(checkval=="1" && checkval.trim().length>0){
							var frstqdrt=jQuery('#chkoplcheckbx_'+(i+1)+'_6').is(':checked');
							 alert(" Checking New :: frstqdrt :: true :: adasd "+frstqdrt+" i+1 :: "+(i+1));
						     if(frstqdrt!=true && frstqdrt=="true"){
								 if(jQuery('#grdchkone').is(':checked') == false){
									    alert(" Select First Quadrant to Apply "+(i+1));
									    return false;
								 }
							 }
					     }
					  }*/
					  
					 var gridtick=false;
					 var frstsndtick=false;
					 for(var i=0;i<row.length;i++){
						 var checkval=jQuery("#studGrid").jqGrid('getCell',(i+1),"chkval4");
					
						 if(checkval=="1" && checkval.trim().length>0){
							 var griddate= jQuery("#studGrid").jqGrid('getCell',(i+1),"dteOpllDate");
							 var gridteacher= jQuery("#studGrid").jqGrid('getCell',(i+1),"cmbOpllTeacher");
							 var teacher= jQuery("#studGrid").jqGrid('getCell',(i+1),"cmbOpllTeachername");
							 
						     if(date.trim().length==0){
						    	 alert(" Select Date Executed ");
						    	 return false;
						     }
						     if(employ.trim().length==0){
						    	 alert(" Select Teacher ");
						    	 return false;
						     }
						    /* if(teacher!=employ && teacher!="EMP00001" && teacher.trim().length>0){
						    	 alert("Teacher Should not change for Each Quadrant at "+(i+1));
						    	 return false;
						     }*/
						     if(jQuery('#grdchkone').is(':checked') == true){
								 if(jQuery("#chkoplcheckbx_"+(i+1)+"_6").is(':checked') == false){
									jQuery("#chkoplcheckbx_"+(i+1)+"_6").prop('checked',true);
								 	jQuery("#studGrid").jqGrid('setCell',(i+1),'chk1','1');
								 }
							  }
							  if(jQuery('#grdchktwo').is(':checked') == true){
								 var frstqdrt=jQuery('#chkoplcheckbx_'+(i+1)+'_6').is(':checked');
								 if(frstqdrt!=true && frstqdrt!="true"){
									 if(jQuery('#grdchkone').is(':checked') == false){
										alert(" Select First Quadrant to Apply ");//+(i+1)
										return false;
									 }
									 frstsndtick=true;
							  }
							  if(jQuery("#chkoplcheckbx_"+(i+1)+"_9").is(':checked') == false){
								 	jQuery("#chkoplcheckbx_"+(i+1)+"_9").prop('checked',true);
								 	jQuery("#studGrid").jqGrid('setCell',(i+1),'chk2','2');	
								 	frstsndtick=true;
							     }
							  } 
							  if(griddate.trim().length==0)
							 	  jQuery("#studGrid").jqGrid('setCell',(i+1),'dteOpllDate',date);
							  else
								  jQuery("#studGrid").jqGrid('setCell',(i+1),'dteOpllDate',date);
							 
							  if(gridteacher.trim().length==0){
							 	jQuery("#studGrid").jqGrid('setCell',(i+1),'cmbOpllTeacher',employtext);
							 	jQuery("#studGrid").jqGrid('setCell',(i+1),'cmbOpllTeachername',employ);
							  }
							  gridtick=true;
						  }
					   }
						 if(gridtick==false){
							alert(" Select a Record to Apply ");
							return false;
						 }else if(gridtick==true && frstsndtick==true){
							alert(" Teacher Applied Successfully "); 
						 }else if(frstsndtick==false){
							alert(" Teacher is Already Applied "); 
						 }
				});
				jQuery("#btnAddEmployee").click(function(){
		        	   var Employee =getFieldValue('cmboplmEmployee');
				       var Employeetext =jQuery("#cmboplmEmployee").combobox("getText");
				       var row  = jQuery("#studGrid").jqGrid('getDataIDs');
					   var masterKeyId = jQuery('#cmboplmKeyid').combobox('getValue');
					   
					   if(Employee.trim().length==0){
						   alert(" Select Employee to add. ");
						   return false;
					   }else
						   processAjaxCalls("OplFillEmploy_modify.opl","emplyid="+Employee,"updateEmployeeSuccess","");
					  
					   var role=getFieldValue('cmboplmRole');
					   var roletext =jQuery("#cmboplmRole").combobox("getText");
					   var roleArr = roletext.split('-');
					   var curdate=jQuery('#hdncurdate').val();
					   var empid=jQuery('#hdnempid').val();
					   //var empname=jQuery('#hdnempnamedata').val();
						
					   if(masterKeyId.length==0){ 
							 if(row.length==0)
								row=row+1; 
						      
							 setTimeout(function() {
								 addRowEmployee(row,Employee,Employeetext,curdate);
							    },1250);
					    }else{
						     if(row.length==0)
								row=row+1;
						   
						     setTimeout(function() {
								 addRowEmployee(row,Employee,Employeetext,curdate);
							    },1250);
					    }
				  }); 
				
				
				var Emppillar=jQuery('#hdnEmppillar').val();
				//alert(" Emppillar :: "+Emppillar);
				//alert("filterString::::"+jQuery('#chkOplmMpworthy').is(':checked')+"-Emppillar-"+Emppillar);
	            if(Emppillar == "Y" && jQuery('#chkOplmMpworthy').is(':checked')==true)
				{
	            	//alert("Emppillar");
	         /*    jQuery('#frmOplCreationFuntKeyIds').append('<div id="divhide1" style="position:absolute;left:0;top:70px;width:20%;z-index:2;opacity:0.4;height:90%;"> </div>');
	            
	            	disableForm("frmOplCreation");
	            	enableUIButton('btnoplActionplan');
					enableUIButton('btnExcelview');
					//enableUIButton('btnnavigateHd');
					//jQuery("#chkOplmUtiliseforfuture").prop('readonly',false); 	
	            	enableFields("chkOplmUtiliseforfuture");
					disableField("frmOplCreation","chkOplmMpworthy");
					disableField("divMachine");
					readOnlyFields("cmboplmMachineid");
					jQuery("#chkoplmIsok").prop("readonly","readonly");
	    			jQuery("#chkoplmwhyhow").prop("readonly","readonly");
	    			jQuery("#chkoplmIspresent").prop("readonly","readonly");
	    			jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
	    			jQuery("#chkUndrstndOpl").prop("readonly","readonly");  */
	            	enableFields("chkOplmUtiliseforfuture");
				}
				
				});
	           
		function addRowEmployee(row,Employee,Employeetext,curdate){
		     if ( row!=undefined && row.length > 0 && parseInt(row) >= 0) { 
		 		if ( row == null || row == '' || parseInt(row) <= 0) {
					var j=0;
					var emptyItem =[{dteOpllDate:'',cmbOpllTeacher:'',txtOpllStudent:Employeetext,StudentId:Employee,txtOpllMtrxKeyid:' ',keyid1:'',chk1:'',txtOpllMtrxKeyid:'',keyid2:'',chk2:'',three:'',keyid3:'',chk3:'',dtevaldte3:'',Keyid4:'',four:'',keyid4:'',chk4:'',dtevaldte4:'',chkval4:'',cmbOpllTeachername:'',OldDateMatx:curdate}];//curdate
					jQuery("#studGrid").jqGrid('addRowData',j, emptyItem[0]);
			        var k =j+1;
			    }
			    else {	 
				        var empExists = false;
				        var empAdded = true;
				        for(var i=0;i<row.length;i++){
				        	var rowData = jQuery("#studGrid").jqGrid('getRowData',1+i);
				        	var Employeeid=rowData.StudentId;
				        	if(Employeeid==Employee){
				        		empAdded = false;
			        			popupCommonErrorMsg(" This Record is already exist in grid .. ");
			        			return false;
			        		}
				        }
				        
				        if (empExists == false) {
				        	
						    lastRow = row[row.length-1];
						    var curdate=jQuery('#hdncurdate').val();
						    
			  				if(curdate.trim().length>0)
			  					var emptyItem =[{dteOpllDate:'',cmbOpllTeacher:'',txtOpllStudent:Employeetext,StudentId:Employee,txtOpllMtrxKeyid:' ',keyid1:'',chk1:'',txtOpllMtrxKeyid:'',keyid2:'',chk2:'',three:'',keyid3:'',chk3:'',dtevaldte3:'',Keyid4:'',four:'',keyid4:'',chk4:'',dtevaldte4:'',chkval4:'',cmbOpllTeachername:'',OldDateMatx:curdate}];//curdate
			  					
		                    if(Employeetext.trim().length>0){
			  		           jQuery("#studGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
			  		        }
			        	}
				            if(empAdded!=false){
					        	alert(" Employee Added Successfully ");
					        }
				        }
			        }
			   }
		   
		jQuery("#chkoplmIsok").click(function() {
				
				    jQuery('#chkoplmIsok').prop('checked',true);
				    var result = confirm("Do You Want To Put OPL in Not Ok/Ok?");
					if (result == true){
						jQuery("#chkoplmIspresent").prop("readonly","readonly");
						jQuery("#chkoplmwhyhow").prop("readonly","readonly");
						jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
					
					}	else{
						enableFields("chkoplmIspresent");
						enableFields("chkoplmwhyhow");
						enableFields("chkoplmIsgeneral");
					if(jQuery("#chkoplmIspresent").is(':checked'))
					  jQuery('#chkoplmIspresent').prop('checked',false);

					  if(jQuery("#chkoplmwhyhow").is(':checked'))
				          jQuery('#chkoplmwhyhow').prop('checked',false);

					  if(jQuery("#chkoplmIsgeneral").is(':checked'))
				          jQuery('#chkoplmIsgeneral').prop('checked',false);
					}
				});
				jQuery("#chkoplmIsgeneral").click(function() {
				       
					   jQuery('#chkoplmIsgeneral').prop('checked',true);
					   var result = confirm("Do You Want To Put OPL in General?");
						if (result == true){
							jQuery("#chkoplmwhyhow").prop("readonly","readonly");
							jQuery("#chkoplmIsok").prop("readonly","readonly");
							jQuery("#chkoplmIspresent").prop("readonly","readonly");
						
						}	else{
							enableFields("chkoplmwhyhow");
							enableFields("chkoplmIsok");
							enableFields("chkoplmIspresent");
					       if(jQuery("#chkoplmIsok").is(':checked'))
					          jQuery('#chkoplmIsok').prop('checked',false);
					       
					       if(jQuery("#chkoplmwhyhow").is(':checked'))
					          jQuery('#chkoplmwhyhow').prop('checked',false);

					       if(jQuery("#chkoplmIspresent").is(':checked'))
						          jQuery('#chkoplmIspresent').prop('checked',false);
						}
						});
								
				var Emppillar=jQuery('#hdnEmppillar').val();
				//alert(" Emppillar :: "+Emppillar);
				//alert("filterString::::"+jQuery('#chkOplmMpworthy').is(':checked')+"-Emppillar-"+Emppillar);
	            if(Emppillar == "Y" && jQuery('#chkOplmMpworthy').is(':checked')==true)
				{
	            	//alert("Emppillar");
	         /*    jQuery('#frmOplCreationFuntKeyIds').append('<div id="divhide1" style="position:absolute;left:0;top:70px;width:20%;z-index:2;opacity:0.4;height:90%;"> </div>');
	            
	            	disableForm("frmOplCreation");
	            	enableUIButton('btnoplActionplan');
					enableUIButton('btnExcelview');
					//enableUIButton('btnnavigateHd');
					//jQuery("#chkOplmUtiliseforfuture").prop('readonly',false); 	
	            	enableFields("chkOplmUtiliseforfuture");
					disableField("frmOplCreation","chkOplmMpworthy");
					disableField("divMachine");
					readOnlyFields("cmboplmMachineid");
					jQuery("#chkoplmIsok").prop("readonly","readonly");
	    			jQuery("#chkoplmwhyhow").prop("readonly","readonly");
	    			jQuery("#chkoplmIspresent").prop("readonly","readonly");
	    			jQuery("#chkoplmIsgeneral").prop("readonly","readonly");
	    			jQuery("#chkUndrstndOpl").prop("readonly","readonly");  */
	            	enableFields("chkOplmUtiliseforfuture");
				}
	            /* else{
					disableField("chkOplmUtiliseforfuture");
				} */

		    	  jQuery('#chkSelectAll').click(function() {
	    			
	    			show_winMask(0);

	    	        var row = jQuery("#studGrid").jqGrid('getDataIDs');		     
	    			if(jQuery("#chkSelectAll").is(':checked')== true){
	    		       	 for(var i=0;i<row.length;i++)
	    		       	 {
	    		       		jQuery('#chkoplloadcheckbx_'+row[i]).prop('checked', true);
	    		       		checkboxCheck(row[i]);
	    		       	 }
	    		     }
	    			else { 
	    				for(var i=0;i<row.length;i++)
	    		       	 {
	    		       		jQuery('#chkoplloadcheckbx_'+row[i]).prop('checked', false);
	    		       		checkboxUnCheck(row[i]);
	    		       	 }
	    			}
	    				
	    			
	    		 });
    
			jQuery("#cmboplmMachineid").combobox({onRequest:function( ){

			 var compId = getFieldValue('company','frmOplCreation');
			 var locnId = getFieldValue('location','frmOplCreation');
		
			 var sectId = getFieldValue('section','frmOplCreation');
			 var cellId = getFieldValue('cell','frmOplCreation');
			 var flid = getFieldValue('flid','frmOplCreation');
			 var filterStr = "&cellId=" +cellId ;
			 return filterStr;
			 
			
	  	}}); 

		 function imageSize_successCallBack(result){
			   var imgSizeKB=result.imageSize;
			   jQuery('#imgOplmPresentimage').show();
			   jQuery("#lblResize").text("After Resizing:"+imgSizeKB+"KB");
			   if(imgSizeKB>500.0){
			       jQuery('#imgOplmPresentimage').prop('src', "");
			       jQuery('#imgOplPresentImgFilename').val("");
			       jQuery("#lblResize").text("");
			       jQuery('#hdnprtimgclr').val("prtimgclr");
			       popupCommonErrorMsg("Image size should be less than 0.5MB");
			   }
		  }
		  
		  function removePre_successCallBack(result) {
			 jQuery('#imgOplmPresentimage').prop('src', "");
		     jQuery('#imgOplPresentImgFilename').val("");
		   }
		   
		 function updateEmployeeSuccess(result){
			jQuery('#hdncurdate').val(result[0][0]);
			jQuery('#hdnempid').val(result[0][1]);
			jQuery('#hdnempnamedata').val(result[0][2]);
		 }

		function btnFilManage_click(){
	        
		    var documentNo =jQuery("#hdnoplkeyid").val();
		    //alert(" documentNo:::1234::: "+documentNo);
			if(documentNo != null && documentNo != ''){
				
				var frmMode=jQuery('#frmMode').val();
				
				apMode = "create";
				if(frmMode=="View")
				   apMode = "view";
			
				fileManagerPopUp(documentNo,"OPL","","","",apMode);
					
			} else
		    {
				 saveForm('frmOplCreation','create_save.opl?filemanager=filemanager');
		     }	
	 }

      
      jQuery("#btnoplActionplan").click(function(){
			 var hdnoplkeyid=jQuery("#hdnoplkeyid").val();
			 var keyid=jQuery("#hdnoplkeyid").val();
			 var flid=jQuery("#flid").val();
			 var mainTask= getFieldValue("txtoplmTheme");
			 var frmMode=jQuery('#frmMode').val();
			 //alert(" frmMode :: "+frmMode);
			 //alert(" hdnoplkeyid:::::::: "+hdnoplkeyid);
			 if(hdnoplkeyid.trim().length>0)
		    { 
				 //alert(" mainTask :: "+mainTask);
				 //openActionPlan("Actionplane",keyid,"MOM",flid,mainTask,refDocId);
				    apMode = "create";				 
					var Emppillar=jQuery('#hdnEmppillar').val();
					var frmMode=jQuery('#frmMode').val();
					if(frmMode=="View" ||Emppillar=="Y" ||Emppillar=="N"){
					   apMode = "view";
					   openActionPlan("oplActionPlan",keyid,"OPL",flid,mainTask,keyid,"",apMode);
					}
				 
		    }
			 else
		    {
				 
                 //alert(" Before Saving :: "+mainTask);		    
                 //jQuery('#hdntheme').val(mainTask);			
				 saveForm('frmOplCreation','create_save.opl?openactnpln=openactnpln');
		    }		 

		});	



		
/*function frmOplCreation_beforeSubmit(){

	//return "OplPillarLink="+JqGridToJsonSelectdRows('pillarGrid','pillar_checkbox','txtselectionFlag');
	//return "OplPillarLink="+getGridSelectArray("pillarGrid");

} */	

      function popup_OnSaveForm(){
			//alert("inside opl");
			saveForm('frmOplCreation','create_save.opl');
		}
				
function viewGrid(url,filterString,category)
{
	//alert("category:::"+category);
	if(category=="student")
	{ 
		//processGridnew(url,filterString,"studGrid","studPager","","","","loadCompleteStudentGrid");
	}
	else if(category=="pillar")
	{
		var tableCaption = "";
			processGridnew(url,filterString,"pillarGrid","pillarPager",tableCaption,"","","loadCompletePillarGrid","selectPillarGridRow");
			
	}
}
	
				/*	function doubleClickGrid(rowid)
					{
						alert("rowid="+rowid);
						
							jQuery( "#ImprvCategoryDialog" ).show();
							jQuery( "#ImprvCategoryDialog" ).dialog({
								autoOpen: false,
								show: "blind",
								hide: "explode",
								height: 550,
								width: 700,
								modal: true,
								 // buttons:{ "Close": function() { $(this).dialog("close"); } },
						         //   close: function(ev, ui) { $(this).remove(); },
						        buttons: { "OK":function() {alert('ok'); },
						                 "Cancel": function() { jQuery(this).dialog("close"); }
						                 }
			                 
						 	});
							processGridnew("imprvCategory_input.opl","?categoryRowid="+ rowid,"imprcatGrid","imprcatPager","Improvemnt Category");
								
				      // navigateToNextForm("imprvCategory_input.opl"+"?categoryRowid="+ rowid,"Improvement Category");
				
					}
			*/
function onerrorCallThis()
{
	//alert("error occured");
}
function chkcheckFormatter(id, options, rowObject){

	var Id = options.rowId;

	var disable='';
	 /*var Mode=jQuery('#hdncreate').val();

		if(Mode=="Create"){

      jQuery('#Approvedby').removeClass("mandatory-lbl");
      readOnlyFields("cmboplmApprovedid");
     }

     var formMode=jQuery('#frmMode').val();
		if(formMode=="Update"){

		jQuery('#Approvedby').removeClass("mandatory-lbl");
     readOnlyFields("cmboplmApprovedid");

		}*////
		
		//||jQuery('#hdnMode').val()=="Modify"
		
	   //alert(" Opl Matrix :: "+jQuery('#hdnMode').val()+" frmMode :: "+jQuery('#frmMode').val());
	
		var OplMatrix=jQuery('#hdnapprovalMode').val();
	 if((jQuery('#frmMode').val()=="View"||jQuery('#hdncreate').val()=="Create"
			 ||jQuery('#frmMode').val()=="Update" 
			 ||( jQuery('#frmMode').val()=="Approval" &&OplMatrix!="OPLMATRIX")) )
		{	
		  //alert(1);
		  disable='readonly="readonly"';   // 
		}
	
	return '<input '+disable+' id="chkoplloadcheckbx_'+Id+'" name="chkoplloadcheckbx_'+Id+'" ' + ' type="checkbox" ' + 'onclick="if(this.checked){checkboxCheck(\''+Id + '\');}else{checkboxUnCheck(\''+Id +'\')}" />';	
}
function checkboxCheck(rowid){ //alert(rowid);

	var rowData = jQuery("#studGrid").jqGrid('getRowData',rowid );
	
    jQuery("#studGrid").jqGrid('setCell',rowid,'chkval4','1');
    var cellId = getFieldValue('cell','frmOplCreation');
    var locnId =jQuery("#hdnlocnid").val();
    //var cmbUrl = "employee.commonFilter?locn=Y&cellId="+cellId+"&locnId="+locnId;
    var cmbUrl = "employee.commonFilter";
    
    //reloadCombo("frmOplCreation","cmboplmEmployee","employee.commonFilter?locn=Y&cellId="+keyIds.cellId+"&locnId="+locnId);
    
    //setFormater("studGrid","frmOplCreation",cmbUrl,rowid,"dteOpllDate","dteOpllDate","120px",false);
    
    //alert(" locnId :: "+locnId);
    setTimeout(function() {
    	//setFormater("studGrid","frmOplCreation",cmbUrl,rowid,"cmbOpllTeacher","cmbOpllTeachername","146px",false);
    },1050);
    var oplmDate = getFieldValue("dteoplmDate");
    var createdby = getFieldValue("cmboplmPreparedid");

    setTimeout(function() {
    	setFieldValue("studGriddteOpllDate_"+rowid,oplmDate);
    },1050);

    setFieldValue("studGridcmboplcombo_"+rowid," ");

    //setFieldValue("studGridcmboplcombo_"+rowid,createdby);
    
    setTimeout(function() {
    	setFieldValue("studGridcmboplcombo_"+rowid,createdby);
    },2050);
    
}

function checkboxUnCheck(rowid){

	jQuery("#studGrid").jqGrid('setCell',rowid,'chkval4','0');
	jQuery("#studGrid").jqGrid('setCell',rowid,'dteOpllDate',' ');
	jQuery("#studGrid").jqGrid('setCell',rowid,'cmbOpllTeacher',' ');
	jQuery("#chkoplcheckbx_"+rowid+"_6").prop('checked',false);
	jQuery("#chkoplcheckbx_"+rowid+"_9").prop('checked',false);
	jQuery("#studGrid").jqGrid('setCell',rowid,'chk1',' ');
	jQuery("#studGrid").jqGrid('setCell',rowid,'chk2',' ');
	
    removeFormater("studGrid","",rowid,"","cmbOpllTeacher");
	removeFormater("studGrid","",rowid,"","dteOpllDate");
	
	//removeFormater(gridId,formtType,rowId,value,colName)
}	


function chkFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	var colId = options.pos;
	var disable='';
	//alert(" ColId "+colId);
	if(colId==6||colId==9){
		 disable='readonly="readonly"';
	}
	if(colId==12){//alert(" Inside 11 ");
	    var keyid=rowObject[9];
	if(keyid!=null && keyid!=" "){

		processAjaxCalls("Datevalidate_input.opl?keyid="+keyid+"&rowId="+rowId,"","datevalidate_onsuccesscallback");
	    processAjaxCalls("Datevalidation_input.opl?keyid="+keyid,"","datevalidation_onsuccesscallback");

	}
	}if(colId==17){//alert(" Inside 11 ");
		var keyid=rowObject[12];
		
		if(keyid!=null && keyid!=" "){//alert(1);
			processAjaxCalls("Datevalidating_input.opl?keyid="+keyid+"&rowId="+rowId,"","datevalidating_onsuccesscallback");
		    processAjaxCalls("Datevalidation_input.opl?keyid="+keyid,"","datevalidation_onsuccesscallback");
		  }
		  
		  
		}
	
	 
	 var OplMatrix=jQuery('#hdnapprovalMode').val();
	 if(jQuery('#frmMode').val()=="View"||jQuery('#hdncreate').val()=="Create"
			 ||jQuery('#frmMode').val()=="Update" ||jQuery('#frmMode').val()=="Update"
			 ||(jQuery('#frmMode').val()=="Approval" && OplMatrix!="OPLMATRIX"))
		{	
		  disable='readonly="readonly"';    
		}
	
	    return '<input   '+disable+'  id="chkoplcheckbx_'+rowId+'_'+colId+'" name="chkoplcheckbx_'+rowId+'_'+colId+'" '+ (rowObject[colId-1]=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId +'\',\''+colId+'\');}else{chkboxUnCheck(\''+rowId +'\',\''+colId+'\')}"  />';
    
}

function datevalidate_onsuccesscallback(result){ //alert(result[0][0]);

	for(var i=0;i<result.length;i++){//alert(" Result "+result+" lenght "+result.length);
	    
		jQuery("#studGrid").jqGrid('setCell',result[i][1],'dtevaldte3',result[i][0]);
		
	}
	
}

function datevalidation_onsuccesscallback(result){


    jQuery("#hdnvalidate3").val(result[0][0]);
    jQuery("#hdnvalidate4").val(result[0][1]);
    
    
    for(var i=0;i<result.length;i++){

		
	}
	
}

function datevalidating_onsuccesscallback(result){


    for(var i=0;i<result.length;i++){

    	jQuery("#studGrid").jqGrid('setCell',result[i][1],'dtevaldte4',result[i][0]);	
	}
	
}

function chkboxCheck(rowId,colId) {//alert("colId:::::::  "+colId);
	if(colId==6){//alert("colId5::::::"+colId);
		jQuery("#studGrid").jqGrid('setCell', rowId, 'chk1', '1');
		
	}else if(colId==9){//alert(" Inside ");
		jQuery("#studGrid").jqGrid('setCell', rowId, 'chk2', '2');
		//var check=jQuery("#chkoplcheckbx_"+rowId+"_6").prop('checked',false);
         var check=jQuery("#chkoplcheckbx_"+rowId+"_6").is(':checked') == true;
         
			if(check == false){
				jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			    //alert(" Select Previous Quadrant Explain");
			    return false;
			}
		
	}else if(colId==12){//alert(1);
		var validateval= jQuery("#studGrid").jqGrid('getCell',rowId,"dtevaldte3");//chkval3
		var chkval3= jQuery("#studGrid").jqGrid('getCell',rowId,"chkval3");
		var keyid=jQuery("#studGrid").jqGrid('getCell',rowId,"keyid2");
		var hiddenval=jQuery("#hdnvalidate3").val();
		//var matxdate=jQuery("#studGriddteOpllDate_"+rowId).datebox('getValue');
		//var matxdate=jQuery("#studGrid").jqGrid('getCell',rowId,"dteOpllDate");
			var matxdate=jQuery("#dtegridDate").datebox('getValue');
		//alert(matxdate.length);
	     if(matxdate.length==0)
			{
			
			  alert("Select Executed Date!!!");
			  jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			  return false;
			} 
		var oldmatxdate=jQuery("#studGrid").jqGrid('getCell',rowId,"OldDateMatx");
		//alert(" matxdate :: "+matxdate+" :: oldmatxdate :: "+oldmatxdate);
		oldmatxdate = convertStringToDate(oldmatxdate);
		matxdate =  convertStringToDate(matxdate);
		var nTotalDiff = getTimeDifference (oldmatxdate,matxdate);
		var dateDiff = nTotalDiff.convtDays;
		//alert(" dateDiff :: "+dateDiff);
		//alert(" hiddenval :: "+hiddenval);	
		//alert();
		
		
			
				
				
				
		
				
		
		if(dateDiff>hiddenval){  //alert(" Inside ");
			
            jQuery("#studGrid").jqGrid('setCell', rowId, 'chk3', '3');
             
		}else if(keyid == null || keyid.trim().length==0){//|| keyid==''
			
			jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			alert(" Save Previous Quadrant(Demonstrate)");
			return false;
		}else if(dateDiff<=hiddenval){
			 var teacher=jQuery("#cmbgridEmployee").combobox('getValue');
			 var executedate=jQuery("#dtegridDate").datebox('getValue');
			 
			 if(executedate.length==0||teacher.length==0)
				 {
				    alert("Select Date ,Teacher and Click on Apply");
				    jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
				    return false;
				 }
		
			 jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			 alert(" Atleast "+hiddenval+" days is Required From Previous Level ");
		} 
		/*else{
			 jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			 alert(" Atleast "+hiddenval+" days is Required From Previous Level ");
			 
		}*/

		
	}else if(colId==17){ //alert("colId11::::::"+colId);
		var validateval= jQuery("#studGrid").jqGrid('getCell',rowId,"dtevaldte4");
		var keyid=jQuery("#studGrid").jqGrid('getCell',rowId,"keyid3");
		var hiddenval3=jQuery("#hdnvalidate4").val();

		//var matxdate=jQuery("#studGriddteOpllDate_"+rowId).datebox('getValue');
		//var matxdate=jQuery("#studGrid").jqGrid('getCell',rowId,"dteOpllDate");
		var matxdate=jQuery("#dtegridDate").datebox('getValue');
		//alert(matxdate.length);
	     if(matxdate.length==0)
			{
			
			  alert("Select Executed Date!!!");
			  jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			  return false;
			} 
		var oldmatxdate=jQuery("#studGrid").jqGrid('getCell',rowId,"OldDateMatx");
		//alert(validateval+" :: hiddenval :: "+hiddenval+" rowId :: "+rowId);
		oldmatxdate = convertStringToDate(oldmatxdate);
		matxdate =  convertStringToDate(matxdate);
		var nTotalDiff = getTimeDifference (oldmatxdate, matxdate);
		var dateDiffe = nTotalDiff.convtDays;

		//alert(" hiddenval3 :: "+hiddenval3 +" dateDiff "+dateDiffe);
		
		/*if(validateval >= hiddenval3 && validateval.length>0 && hiddenval3.length>0){ // alert(" Inside ");){
			
			jQuery("#studGrid").jqGrid('setCell', rowId, 'chk4', '4');
             
		}*/
		if(keyid == null || keyid == ' ' ||keyid.trim().length==0){
			
			jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			alert(" Save Previous Quadrant(Practice) ");
			return false;
			
		}else if(dateDiffe>hiddenval3){ 
		
		    jQuery("#studGrid").jqGrid('setCell', rowId, 'chk4', '4');
         
	     } else if(dateDiffe<=hiddenval3){
           
			
	    	 jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			
			alert(" Atleast "+hiddenval3+" days is Required From Previous Level ");
			
			

		}
			/*else{
			
			jQuery("#chkoplcheckbx_"+rowId+"_"+colId).prop('checked',false);
			alert(" Atleast "+hiddenval3+" days is Required From Previous Level ");
			
			}*/
	    
	}
}

function chkboxUnCheck(rowId,colId) { //alert("rowId ::: "+rowId+"colId::::::::  "+colId);
    
	if(colId==6){
		jQuery("#studGrid").jqGrid('setCell', rowId, 'chk1', '0');
	}else if(colId==9){
		jQuery("#studGrid").jqGrid('setCell', rowId, 'chk2', '0');
	}else if(colId==12){
		
		var validateval= jQuery("#studGrid").jqGrid('getCell',rowId,"dtevaldte3");
		var keyid=jQuery("#studGrid").jqGrid('getCell',rowId,"keyid2");
		var hiddenval=jQuery("#hdnvalidate3").val();
		if(validateval == hiddenval){
			jQuery("#studGrid").jqGrid('setCell', rowId, 'chk3', '0');
		}
	}else if(colId==17){
		var validateval= jQuery("#studGrid").jqGrid('getCell',rowId,"dtevaldte3");
		var keyid=jQuery("#studGrid").jqGrid('getCell',rowId,"keyid3");
		
		var hiddenval3=jQuery("#hdnvalidate4").val();
		
		if(validateval >=hiddenval3){
			
		 jQuery("#studGrid").jqGrid('setCell', rowId, 'chk4', '0');
             
		}
		
		
		
	}
}
function pillarcboxFormatter(id, options, rowObject)
{
	var rowId = options.rowId;
	
	return '<input name="pillar_checkbox" id="pillar_checkbox_'+ rowId +'" '+ (rowObject[1]=="True" ? 'checked':'') + ' type="checkbox" ' + ' onclick="if(this.checked){pillarLinkSelect(\''+rowId + '\');}else{pillarLinkUncheck(\''+ rowId +'\')}"/>';
}

function pillarLinkSelect(id)
{
	jQuery('#hdnpillarId').val(id);
	var pillarId = jQuery("#pillarGrid").getCell(id,"txtOpplTpmpillarid");
	jQuery('#hdnpillarId').val(pillarId);
	multiSelectPop("imprvCategory_input.opl","pillarId%3D"+pillarId, "pillarGrid",id,"txtOpplOplcategoryid,oplCategory",false,"MultiSelectCancel_CallBack","oplmultiSelectOk_Callback","OPL Category");
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if( mode == "INSERT")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else if(mode=="VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","UPDATE");
	
}

function pillarLinkUncheck(id)
{
	jQuery('#hdnpillarId').val('');
	var mode = jQuery("#pillarGrid").getCell(id,"txtDbMode");
	if(mode != "VIEW")
		mode =" ";
	else
		mode = "DELETE";

	jQuery("#pillarGrid").setCell(id,"oplCategory"," ");
	jQuery("#pillarGrid").setCell(id,"txtOpplOplcategoryid"," ");
	jQuery("#pillarGrid").setCell(id,"txtselectionFlag",mode);
	
}

function MultiSelectCancel_CallBack(id)
{
	if(jQuery("#pillarGrid").getCell(id,"txtDbMode") == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","DELETE");
	else 
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");

	jQuery("#pillarGrid").setCell(id,"pillar_checkbox","False");
	
}

function oplmultiSelectOk_Callback(id)
{
	/*var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++)
	{
		if(jQuery('#'+rowIds[i]).is(":checked")==true)
		{	("jQuery('#'+rowIds["+i+"]).is(:checked)"+jQuery('#'+rowIds[i]).is(":checked"));
			if(jQuery('#pillarGrid').getCell(rowIds[i],"oplCategory")=="")
				jQuery('#'+rowIds[i]).removeprop('checked');
		}	
	}*/
	id=jQuery('#hdnpillarId').val();
	if(jQuery("#pillarGrid").getCell(id,"txtDbMode") == "VIEW")
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag","INSERT");
	else 
		jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	jQuery("#pillarGrid").setCell(id,"pillar_checkbox","False");
}

function pillarGrid_multiselectPopUpId_onClose()
{
    var id=jQuery('#hdnpillarId').val();
	
	//jQuery("#pillarGrid").setCell(id,"txtselectionFlag"," ");
	//jQuery("#pillarGrid").setCell(id,"pillar_checkbox","true");
}


function selectPillarGridRow(id)
{
	/*var lastSel =	jQuery('#txtSelectRowid').val();
	alert("lastSel"+lastSel);
  	 if(id && id!==lastSel)
	  { 
   		jQuery('#oplCatgryGrid').jqGrid('restoreRow',lastSel); 
   		lastSel=id;
  		 jQuery('#txtSelectRowid').val(id); 
	  }
		*/
 		//		 jQuery('#pillarGrid').jqGrid('editRow',id, true);  
		/*		 jQuery(document).keypress(function(e) {
					if(e.keyCode == 13)
					{ 
						alert("id"+id);//down arrow
				
						
					}
								
				});*/
	/*			jQuery( "#ImprvCategoryDialog" ).show();
				jQuery( "#ImprvCategoryDialog" ).dialog({
						autoOpen: false,
						show: "blind",
						hide: "explode",
						height: 550,
						width: 500,
						modal: true
				});
 				processGridnew("imprvCategory_input.opl","?categoryRowid="+ id,"imprcatGrid","imprcatPager","Improvemnt Category");
	*/

	//multiSelectPop("imprvCategory_getData.opl","pillarGrid",id,"oplCatkey,OplCategoryId","oplPillarName,oplCategory");			
}

function loadCompleteStudentGrid(){

	
	
}


function loadCompletePillarGrid()
{
	
	var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
	for(var i=0;i<rowIds.length;i++)
	{
		var cellVal =jQuery('#pillarGrid').getCell(rowIds[i],"oplCategory");
		
		if(cellVal.trim().length!=" "){ 

			jQuery("#pillar_checkbox_"+rowIds[i]).prop('enabled','enabled');
			//jQuery("#pillar_checkbox_"+rowIds[i]).prop('checked','checked');
			
		}
		
		if(jQuery('#frmMode').val()=="View")
		{	
		//	alert(jQuery('#frmMode').val());
			//jQuery("#pillar_checkbox_"+rowIds[i]).prop('checked','checked');
			
			jQuery("#pillar_checkbox_"+rowIds[i]).prop('readonly','readonly');
			//jQuery("#chkoplcheckbx_"+rowIds[i]).prop('readonly','readonly');
		}
	}
	//jQuery(":input[type=checkbox]").removeAttr('readonly');
}

function dtegridDate_onSelect(date)
{
     fnStudentDateValidation();
}

function  frmOplCreationcmboplmFactoryid_onSelect(record)
{
	jQuery("#cmboplmSectionid").combobox('clear');
	jQuery("#cmboplmCellid").combobox('clear');
	jQuery("#cmboplmMachineid").combobox('clear');
	//alert(" Inside Jquery in Opl "+jQuery('#cmboplmFactoryid').val());
	//reloadCombo("frmOplCreation","cmboplmSectionid","sectionCombo.commonFilter?factId="+record.id);
	//reloadCombo("frmOplCreation","cmboplmCellid","cellCombo.commonFilter?factId="+record.id  );
	//reloadCombo("frmOplCreation","","machineCombo.commonFilter?factId="+ record.id );
}

function  frmOplCreationcmboplunqepstn_onSelect(record)
{
	setFieldValue("cmboplmEmployee","");
    reloadCombo("frmOplCreation","cmboplmEmployee","employee.commonFilter?&roleId="+record.id);
} 	
function frmOplCreationcmboplunqepstn_onClear()
{
	reloadCombo("frmOplCreation","cmboplmEmployee","employee.commonFilter");
}
function  frmOplCreationcmboplmSectionid_onSelect(record)
{
	jQuery("#cmboplmCellid").combobox('clear');
	jQuery("#cmboplmMachineid").combobox('clear');
	//reloadCombo("frmOplCreation","cmboplmCellid","cellCombo.commonFilter?sectId="+record.id  );
	//reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?sectId="+ record.id );
	//fillSectionHierarchy("sectionHierarchy.commonFilter",record.id,"cmboplmFactoryid");
}

function  frmOplCreationcmboplmCellid_onSelect(record)
{
	jQuery("#cmboplmMachineid").combobox('clear');
	//reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?cellId="+ record.id );
	//fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmboplmSectionid","cmboplmFactoryid");
}

function  frmOplCreationcmboplmMachineid_onSelect(record)
{
	loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","&machId="+record.id);
	//jQuery("#cmboplmSectionid").combobox('clear');
	//jQuery("#cmboplmCellid").combobox('clear');
	//fillMachineHierarchy("machineHierarchy.commonFilter",record.id,"cmboplmCellid","cmboplmSectionid","cmboplmFactoryid");
 	//reloadCombo("frmOplCreation","cmboplmMouldid","mould.commonFilter?q=2&mchId="+record.id );
}
function frmOplCreationcmboplmMachineid_onClear()
{
	//loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","&cellId="+jQuery("#frmOplCreation input[id='cell']").val());
	jQuery("#frmOplCreation input[id='machine']").val('');
	// setTimeout(function() {jQuery('#cmboplmMachineid').combobox('clear');},1250);
}

function frmOplCreationcmboplmMouldid_onSelect(record)
{
	//reloadCombo("frmOplCreation","cmboplmMachineid","machineCombo.commonFilter?mouldId="+ record.id ); 
}

function frmOplCreationcmboplmFactoryid_onLoadSuccess(){
	//fillComboBox("frmOplCreation","cmboplmSectionid","sectionCombo.commonFilter" );
}

function frmOplCreationcmboplmSectionid_onLoadSuccess(){
	//fillComboBox("frmOplCreation","cmboplmCellid","cellCombo.commonFilter" );
}

function frmOplCreationcmboplmCellid_onLoadSuccess(){
	
}

function frmOplCreationcmboplmMachineid_onLoadSuccess(){
	//fillComboBox("frmOplCreation","cmboplmPreparedid","employee.commonFilter");
	var frmMode=jQuery('#frmMode').val();
	if(frmMode=="Update" || frmMode=="Approval" || frmMode=="Create"){
		enableFields("cmboplmMachineid");
		//jQuery('#oplWorkFlow').append('<div id="divhide" style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:20%;"> </div>');
    }

}

function workFlow_Load_callbackOnSuccess() {
	var frmMode=jQuery('#frmMode').val();
	
	if(frmMode=="Update" || frmMode=="Approval" || frmMode=="Create"){
	    
		//jQuery('#WorkFlow').css('display','none');
	    //jQuery('#oplWorkFlow').css('display','none');
	    
	    enableFields("cmboplmMachineid");
		enableUIButton('btnoplActionplan');
		enableUIButton("btnExcelview");
		if(frmMode=="Approval"){
			
			enableFields("chkOplmMpworthy");
			//enableFields("chkOplmUtiliseforfuture");
			}
		
        
		//jQuery('#frmWorkFlowApp input[id^="workflow_OPL_"]').attr("checked",false);
		//jQuery('#frmOplCreation input[id^="workflow_OPL_"]').attr("checked",false);
		//jQuery('#WorkFlow').append('<div id="divhide" style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:20%;"> </div>');
		
	}
}

function frmOplCreationcmboplmPreparedid_onLoadSuccess(){
//	fillComboBox("frmOplCreation","cmboplmApprovedid","employee.commonFilter");
}
function frmOplCreationcmboplmApprovedid_onLoadSuccess(){
	//fillComboBox("frmOplCreation","cmboplmTradeid","Combo_Trade.abnForm");
}

function frmOplCreation_FuntLocHierarchy_SuccessCallBack(keyIds)
{
	//alert(3);
    //alert(" Inside Fun Success :: "+Object.keys(keyIds));
    //elementId,compId,flid,machId,cellId,locnId,sbuId,type,sectId,pbuId
	var cellId = keyIds.cellId;//
	var locnId = keyIds.locnId;
	jQuery('#hdnlocnid').val(locnId);
	//var flid =keyIds.flid;
	var flid =jQuery("#frmOplCreation input[id='flid']").val();
	//alert(" Inside Hirerachy 2222 :::: "+flid);
	
	var hdnoplkeyid=jQuery("#hdnoplkeyid").val();
	//alert(" hdnoplkeyid FuntLocHierarchy :::: "+hdnoplkeyid);  //CEL0000061 FNLN00000234,,CEL0000060,,FNLN00000310
	
	viewGrid("oplStudent_input.opl",'q=2&cellId=' + flid+'&hdnoplkeyid='+hdnoplkeyid,"student");
	//alert(" cellId::::::::::: "+cellId);
	//alert(keyIds.machId);
	//reloadMachine("frmOplCreation",'cmboplmMachineid',keyIds.cellId,keyIds.sectId,keyIds.factId,keyIds.locnId,keyIds.compId);
	
	//fillComboBox("frmOplCreation","cmboplmPreparedid","employee.commonFilter");
	reloadCombo("frmOplCreation","cmboplmPreparedid","employee.commonFilter?cellId="+keyIds.cellId);
	reloadCombo("frmOplCreation","cmboplmEmployee","employee.commonFilter?locn=Y&cellId="+keyIds.cellId+"&locnId="+locnId);
	
	if(keyIds.machId != undefined && keyIds.machId.trim() != "" && keyIds.machId.trim() !=null)
	{	
		//reloadCombo("frmAbnormality","cmboplmMachineid","machineCombo.commonFilter?cellId="+ keyIds.cellId);
	
	}
	
	var frmMode=jQuery('#frmMode').val();
	var frmwhywhymode=jQuery('#hdnwhywhyMode').val();
	setFunctionalLocWidth('frmOplCreation','700px');
	
/*	if(frmMode=="Approval"){
    	//disableForm("frmOplCreation");
    	//disableField("divMachine");
		//readOnlyFields("cmboplmMachineid");
		jQuery('#frmOplCreation').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
		disableForm("frmOplCreation");
		enableFields("chkOplmMpworthy");
//		enableUIButton('btnExcelview');
    }else */
    
    // --------- Editing for Excel View in Approval  --- //
    
    if(frmMode=="Approval"||formMode=="View"||frmwhywhymode=="View"||frmwhywhymode=="APPROVAL"||hdncreate=="APPROVAL"||frmMode=="approval") {
        
        setTimeout(function(){
        	jQuery("#pillarGrid").prop('readonly',"readonly");
    	},1150);
        

        
        jQuery('#frmOplCreation').append('<div id="divhide1" style="position:absolute;top:70;left:0;width:80%;z-index:2;opacity:0.4;height:10%;"> </div>');
        jQuery('#divhide1').css('pointer-events', 'none'); // <-- add this line Vignesh 22Oct2025
        disableForm("frmOplCreation");
        enableUIButton("btnExcelview");
     

        
     //   if (inPopup && (hdnMode === 'modify' || (frmMode && frmMode.toLowerCase() === 'modify') || modeFromResult === 'modify'))
    	if(frmMode=="Approval"||frmwhywhymode=="APPROVAL"||hdncreate=="APPROVAL"||frmMode==='approval'|| (frmMode && frmMode.toLowerCase() === 'approval')){
    		jQuery("#btnExcelview").show();    	
    	    enableFields("chkOplmMpworthy");
    	    enableUIButton("btnExcelview");
    	 // Call your existing freeze
    	//   disableForm("frmOplCreation");
    	   enableUIButton("btnExcelview");
    	}
    	
    	enableUIButton("btnExcelview");
    }
}



	var OplMatrix=jQuery('#hdnapprovalMode').val();
	if(OplMatrix=="OPLMATRIX"){
		disableField("frmOplCreation", "chkOplmMpworthy");
		jQuery('#tabOpl').tabs('select', "Student");
		enableUIButton('chkSelectAll');
		enableUIButton('btnAddEmployee');
		enableUIButton('btnApply');
		enableFields("cmboplmEmployee");
		enableFields("cmboplmRole");
		enableFields("cmboplunqepstn");
		enableFields("dtegridDate");
		enableFields("cmbgridEmployee");
		enableUIButton('grdchkone');
		enableUIButton('grdchktwo');
	}
	else {
		//jQuery('#studentTab').hide();
	}
	
   jQuery("#cmboplmRelated").combobox({
	onSelect:function(recordid){
		jQuery('#relatedToCMB').val(recordid.id);			
		if(recordid.id == 'MLD')
		{		
			jQuery("#lblMld").addClass('mandatory-lbl');	
			jQuery('#lblMachine').removeClass('mandatory-lbl');
			enableFields('cmboplmMouldid');
			var mchId = jQuery("#cmboplmMachineid").combobox('getValue');
			//fillComboBox("frmOplCreation","cmboplmMouldid","mould.commonFilter");	
			if(mchId != null && mchId != ' ' && mchId !='')
			{
				jQuery('#cmboplmMouldid').combobox('clear');
				//reloadCombo("frmOplCreation","cmboplmMouldid","mould.commonFilter?q=2&mchId="+mchId );
			}
			
		}
		if(recordid.id == 'MCH')
		{				
			jQuery("#lblMld").removeClass('mandatory-lbl');
			jQuery('#lblMachine').addClass('mandatory-lbl');
			jQuery("#cmboplmMouldid").combobox('clear');
			readOnlyFields('cmboplmMouldid'); 		
		}
			
	}

});
   
  function frmOplCreationcmboplmRole_onSelect(record)
  {
 	 var flid =jQuery("#frmOplCreation input[id='flid']").val();
 	 var role =getFieldValue('cmboplmRole');
 	
 	 reloadCombo("frmOplCreation","cmboplmEmployee","rolebasedemployee.mom?&roleId="+record.id+"&flid="+flid);
 	 
 	 //jQuery('input:checkbox[name=chkMomsOthers]').prop('checked',false);
 	 //jQuery('#cmbMomsEmployee').combobox('clear');
 	 
  }

function frmOplCreationcmbcmboplmMouldid_onClear()
{
	
	jQuery('#cmboplmRelated').combobox('setValue','MLD');
}



/** Success CallBack  with grid refresh **/
function frmOplCreation_successsCallback(result) {
  // Keep your current post-save “housekeeping”
  fillWithCurrentDate("dteoplmDate");
  fillWithCurrentDate("dteoplmPrepareddate");
  fillWithCurrentDate("dteoplmApproveddate");
  jQuery('#cmboplmRelated').combobox('setValue','MCH');

  // Values we already use elsewhere
  var modeFromResult = (result && result.formMode) ? ('' + result.formMode).toLowerCase() : '';
  var hdnMode  = (jQuery('#hdnMode').val() || '').toLowerCase();
  var frmMode  = (jQuery('#frmMode').val() || '');
  frmOplClear();
  var inPopup  = (window.parent && window.parent.jQuery && window.parent.jQuery('#oplModify').length > 0);

  // Optional flows you already had
  var openactnpln = result && result.successData && result.successData.openactnpln === true;
  var filemanager = result && result.successData && result.successData.filemanager === true;

  if (openactnpln) {
    var keyid = result.successData.oplKeyId;
    var flid  = result.successData.flid;
    var mainTask = getFieldValue("txtoplmTheme");
    openActionPlan("oplActionPlan", keyid, "OPL", flid, mainTask, keyid);
  }

  if (filemanager) {
    var keyid2 = result.successData.oplKeyId;
    fileManagerPopUp(keyid2, "OPL", "", "", "");
  }
  
  closePopUpDialoge("oplModify");
  jQuery('#oplGrid').trigger('reloadGrid');
  // --- Behavior control ---

  // Create → same as before
  if (frmMode === 'Create') {
	  frmOplClear();
	  clearField("frmOplCreation");
    setFocusOnField('txtoplmTheme');
    return false; // stop default navigation/closeOnSave
  }

  // Modify in popup → DO NOT navigate, DO NOT clear.
  // NEW: also refresh the grid behind the popup.
  if (inPopup && (hdnMode === 'modify' || (frmMode && frmMode.toLowerCase() === 'modify') || modeFromResult === 'modify')) {
    try {
      var $p = window.parent && window.parent.jQuery ? window.parent.jQuery : null;
      if ($p && $p('#oplGrid')[0]) { $p('#oplGrid').trigger('reloadGrid'); }
    } catch (e) { /* no-op */ }
    closePopUpDialoge("oplModify");
    frmOplClear();
    closePopUpDialoge("oplModify");
    // Keep the popup open with modified data visible (your current behavior)
    return false; // stop framework’s default flow
  }

  // View (or non-popup modify) → keep original behavior
  if (modeFromResult === 'view' || frmMode === 'View' || hdnMode === 'view') {
    navigateToPrevForm();
    return false;
  }
  if (formMode === 'Update'){
	  closePopUpDialoge("oplApproval");
	  closePopUpDialoge("oplModify");
  }

  // Default: do nothing special and stop any fallback navigation.
  return false;
}

 
 function oplActionPlan_onClose(){
        
	    //var mode = result.formMode;
	    //var hdnMode=jQuery('#hdnMode').val();   Update
	    
	    var frmMode=jQuery('#frmMode').val();
	    
	    //alert(" frmMode :: New :: "+frmMode);
	    if(frmMode=='Create'){
		    jQuery('#txtoplmTheme').val(' ');
		    jQuery("#chkClassificationB").prop({'checked':false});
		    jQuery("#chkClassificationI").prop({'checked':false});
		    jQuery("#chkClassificationT").prop({'checked':false});
		    jQuery("#chkClassificationS").prop({'checked':false});
            jQuery("#chkClassificationC").prop({'checked':false});
            jQuery("#chkClassificationP").prop({'checked':false});
		    jQuery("#chkoplmwhyhow").prop({'checked':false});
		    jQuery("#chkoplmIsok").prop({'checked':false});
		    jQuery("#chkoplmIspresent").prop({'checked':false});
		    jQuery("#chkoplmIsgeneral").prop({'checked':false});
	    }
	    
		return true;
   
    }


 function frmOplCreation_errorCallback(result)
 {
	  //alert(" Error Message :: "+result.errMsg.msg);
 }

 function frmOplClear()
 {
	 
	 jQuery('#imgOplmAfterimage').prop('src', " ");
	 jQuery('#imgOplAfterImgFilename').val(" ");
	 jQuery('#imgOplmPresentimage').prop('src', " ");
	 jQuery('#imgOplPresentImgFilename').val(" ");
	 fillWithCurrentDate("dteoplmDate");
	 fillWithCurrentDate("dteoplmPrepareddate");
	 fillWithCurrentDate("dteoplmApproveddate");
	 jQuery('#cmboplmRelated').combobox('setValue','MCH');
	 jQuery('#cmboplmMouldid').combobox('disable');
	 jQuery('#chkoplmIspresent').prop({"checked":true});
	 jQuery("#chkClassificationB").prop("checked",false);
	 jQuery("#chkClassificationI").prop("checked",false);
	 jQuery("#chkClassificationT").prop("checked",false);	
	 jQuery("#chkClassificationS").prop("checked",false);
     jQuery("#chkClassificationC").prop("checked",false);
     jQuery("#chkClassificationP").prop("checked",false);
	 loadFunctionalLocation("oplmfunLocation","functionalLoc.opl","oplmfunLocationValues","frmOplCreation","");			
		
	 var rowIds = jQuery('#pillarGrid').jqGrid().getDataIDs();
		for(var i=0;i<rowIds.length;i++)
		{
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"oplCategory"," ");
			jQuery("#pillarGrid").jqGrid('setCell',rowIds[i],"txtselectionFlag"," ");
		} 
 }

	jQuery('#btnOplCatgry').click(function ()
	{
		 saveForm('frmOplCreation','opl_category.opl?&mod=category');
	});
	
	jQuery('#chkUndrstndOpl').click( function () 
	{
		if(jQuery('#hdnChkUnderstoodOpl').val()== 'true')
		{
			alert('Record Already Exists');
			return false;
		}
		else
		{
			 var val = "true";
			 var opllessnDate=jQuery('#dteoplmDate').val();
			 var oplOplid=jQuery('#cmboplmKeyid').val();
			 var para="isOplCheck=" + val + "&txtOpllOplid=" + oplOplid + "&txtOpllDate=" + opllessnDate;
			 processAjaxCalls("excel_input.opl?",para);
		}
	});

function frmOplCreation_exceptionCallback()
{
/*	if(jQuery('#txtoplmLesson').val()=="")
		alert('Select Lesson');*/
}
/** Delete Success CallBack **/
function frmOplCreation_deleteSuccessCallback(result)
{
     //alert(result.successData.msg);
	//if(result.successData.frmMode!="View")
	  // navigateToPrevForm("modify_view.opl","Modification");	
}
/** Delete Error Callback **/
function frmOplCreation_deleteErrorCallback(result)
{
	//alert(result.errMsg.msg);
}
function frmOplCreation_beforeDelete()
{
	var mode = jQuery("#frmMode").val();
	if(mode=="View"){//mode=="Approval" ||
		popupCommonErrorMsg("OPL Can not be delete data in " + mode + " Mode. ");
		return false;
	}
	
	var hdnEmppillar= jQuery('#hdnEmppillar').val();
	if (hdnEmppillar.length>0 ){
		return false;
    }else{
     
    	var delMsg = "Do You Want To Delete This OPL ("+jQuery('#cmboplmKeyid').combobox('getValue')+") ?";
	 
		if(confirm(delMsg) == false)
		{
				return false;
		}
    }
}



function btnImfPresentOnComplete(response){
    
    processAjaxCalls("getUploadedImgSize.commonFilter","?q=2","imageSize_successCallBack");

 }
function dlgImgAfterOnComplete(response){
    processAjaxCalls("getUploadedImgSize.commonFilter","?q=2","afterImageSize_successCallBack");
}
function imageSize_successCallBack(result){
   var imgSizeKB=result.imageSize;
   jQuery("#lblResize").text("After Resizing:"+imgSizeKB+"KB");
   if(imgSizeKB>500.0){
       jQuery('#imgOplmPresentimage').prop('src', "");
       jQuery('#imgOplPresentImgFilename').val("");
       jQuery("#lblResize").text("");
       jQuery('#hdnprtimgclr').val("prtimgclr");
       popupCommonErrorMsg("Image size should be less than 0.5MB");
   }
}
function afterImageSize_successCallBack(result){
	   var imgSizeKB=result.imageSize;
	   jQuery('#imgOplmAfterimage').show();
	   jQuery("#lblAftrResize").text("After Resizing:"+imgSizeKB+"KB");
	  
	   if(imgSizeKB>500.0){
	       jQuery('#imgOplmAfterimage').prop('src', " ");
	       jQuery('#imgOplAfterImgFilename').val(" ");
	       jQuery("#lblAftrResize").text("");
	       jQuery('#hdnaftrimgclr').val("prtimgclr");
	       popupCommonErrorMsg("Image size should be less than 0.5MB");
	   }
}
 function removePre_successCallBack(result) {
    jQuery('#imgOplmPresentimage').prop('src', "");
    jQuery('#imgOplPresentImgFilename').val("");
  }
 jQuery( "#btnImgPresentClear" ).click(function() {
	 //alert('present');
      jQuery('#imgOplmPresentimage').hide();
    // jQuery('#imgOplmPresentimage').prop('src', "");
    // jQuery("#lblResize").text("");
   //  jQuery('#imgOplPresentImgFilename').val("");
   //  jQuery('#hdnprtimgclr').val("prtimgclr");
      var keyid=jQuery('#hdnoplkeyid').val();
     // alert("keyid::"+keyid);
     processAjaxCalls("imageclear_delete.opl","IMAGETYPE=PRE"+"&keyid="+keyid,'removePre_successCallBack');

 });

 function removeAft_successCallBack(result) {
   jQuery('#imgOplmAfterimage').prop('src', " ");
   jQuery('#imgOplAfterImgFilename').val(" ");
 }
 jQuery( "#btnImgAfterClear" ).click(function() {
	 // alert('after');
	  jQuery('#imgOplmAfterimage').hide();
	   //jQuery('#imgOplmAfterimage').prop('src', " ");
	  // jQuery("#lblAftrResize").text("");
	   //jQuery('#imgOplAfterImgFilename').val(" ");
	   //jQuery('#hdnaftrimgclr').val("prtimgclr");
	  
	   var keyid=jQuery('#hdnoplkeyid').val();
	   processAjaxCalls("imageclear_delete.opl","IMAGETYPE=AFT"+"&keyid="+keyid,'removeAft_successCallBack');
	  
});

jQuery('#chkoplmIsok').click(function(){

	 jQuery("#chkoplmIspresent").prop({'checked':false});
	 jQuery("#chkoplmwhyhow").prop({'checked':false});
	 jQuery("#chkoplmIsgeneral").prop({'checked':false});
	 jQuery("#chkoplmIsok").prop({'checked':true});
	 oknotokTabSelection();
});
jQuery('#chkoplmIsgeneral').click(function(){
	
   //alert("satrting");
     
	 jQuery("#chkoplmIspresent").prop({'checked':false});
	 jQuery("#chkoplmwhyhow").prop({'checked':false});
	 jQuery("#chkoplmIsok").prop({'checked':false});
	 jQuery("#chkoplmIsgeneral").prop({'checked':true});
	 
	 jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		    var elTitle = jQuery('.tabs-title', v);
		    var title = elTitle.html();
		    if(i==0)
		    	elTitle.html('Why');
		    else if(i==1){
		    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
		 
		    	setTimeout(function() {
		    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		        },1000); 
		    	elTitle.html('How');
		    	//alert("2");
		    	jQuery(jQuery("#tabOpl").find("li")[1]).hide();
		    	general();
		    	return false;
		    }})
});
jQuery('#chkoplmwhyhow').click(function(){
	//alert("why1");
	why();
	//jQuery( "#prstDescsubHeader" ).text( "Photo by Kelly Clark" );
});

	//jQuery( "#prstDescsubHeader" ).text( "Photo by Kelly Clark" );

jQuery('#chkoplmIspresent').click(function(){
	jQuery("#prstDescsubHeader").html("Present Description");
	jQuery("#prstCondsubHeader").html("Present Condition");
	jQuery("#aftDescsubHeader").html("After Description");
	jQuery("#aftCondsubHeader").html("After Condition");
	jQuery("#chkoplmIsok").prop({'checked':false});
	jQuery("#chkoplmIspresent").prop({'checked':true});
	jQuery("#chkoplmwhyhow").prop({'checked':false});
	jQuery("#chkoplmIsgeneral").prop({'checked':false});


	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
    },1000);   
	
	 jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		    var elTitle = jQuery('.tabs-title', v);
		    //alert("elTitle::"+elTitle);
		    var title = elTitle.html();
		   // alert("elTitle::"+elTitle);
		  //  alert("elTitle::"+elTitle);
		    if(i==0)
		    	elTitle.html('Present Condition');
		    else if(i==1){
		    	
		    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
		    	setTimeout(function() {
		    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		        },1000); 
		    	elTitle.html('After Condition');
		    	return  false;
		    }
		     
		});
  
});

/*jQuery("#chkoplmIsgeneral").click(function() {
	
    jQuery('#chkoplmIsgeneral').prop('checked',true);

	if(jQuery("#chkoplmIspresent").is(':checked'))
	  jQuery('#chkoplmIspresent').prop('checked',false);
	  
	  if(jQuery("#chkoplmwhyhow").is(':checked'))
          jQuery('#chkoplmwhyhow').prop('checked',false);
      
	  if(jQuery("#chkoplmIsok").is(':checked'))
			jQuery('#chkoplmIsok').prop('checked',false);

	
});*/

function general(){
	
	//alert("inside");
	
	setTimeout(function() {
		//alert("inside time out function");

		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
    },1000);   
    var frmMode=jQuery('#frmMode').val();
    //alert("geeneral1");
   // alert("frmMode::"+frmMode);
	jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		//jQuery(jQuery("#tabOpl").find("li")[1]).hide();

	    var elTitle = jQuery('.tabs-title', v);
	    var title = elTitle.html();
	    if(i==0)
	    	{
	    	elTitle.html('General');
	    	//alert("INSIDE THE IF");
	    	jQuery(jQuery("#tabOpl").find("li")[0]).hide();
	    	}
	    else if(i==1)
	    	{
	    	//alert(1);
	    	
	    	elTitle.html('General');

	    	jQuery(jQuery("#tabOpl").find("li")[1]).hide();
	    	}
	    else if(i==2){
	    	elTitle.html('Lesson');
	    }
	    else if(i==3){
	    	elTitle.html('Student');
	    	
	    	return false;
	    }
	    
	    });

//jQuery('#chkoplmIsgeneral').click(function() 
if(jQuery("#chkoplmIsgeneral").is(':checked'))
	jQuery('#chkoplmIsgeneral').prop('checked',true);

	if(jQuery("#chkoplmIspresent").is(':checked'))
	  jQuery('#chkoplmIspresent').prop('checked',false);
	  
	  if(jQuery("#chkoplmwhyhow").is(':checked'))
          jQuery('#chkoplmwhyhow').prop('checked',false);
      
	  if(jQuery("#chkoplmIsok").is(':checked'))
			jQuery('#chkoplmIsok').prop('checked',false);
		
	jQuery("#prstDescsubHeader").html("General Description");
	jQuery("#prstCondsubHeader").html("General Condition");
	jQuery("#aftDescsubHeader").html(" General Description").show();
	jQuery("#aftCondsubHeader").html(" General Description").show();
	jQuery("#chkoplmIsok").prop({'checked':false});
	jQuery("#chkoplmIspresent").prop({'checked':false});
	jQuery("#chkoplmwhyhow").prop({'checked':false});
	jQuery("#chkoplmIsgeneral").prop({'checked':true});
/*	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','none');
    },1000); */
     
	

}



function why(){
	

	jQuery("#prstDescsubHeader").html("Why Description");
	jQuery("#prstCondsubHeader").html("Why Condition");
	jQuery("#aftDescsubHeader").html("How Description").show();
	jQuery("#aftCondsubHeader").html("How Condition").show();
	
	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
    },1000);   
	
		 jQuery("#chkoplmIspresent").prop({'checked':false});
		 jQuery("#chkoplmIsok").prop({'checked':false});
		 if(jQuery("#chkoplmwhyhow").is(':checked')){
		 jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		    var elTitle = jQuery('.tabs-title', v);
		    var title = elTitle.html();
		    if(i==0)
		    	elTitle.html('Why');
		    else if(i==1){
		    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
		 
		    	setTimeout(function() {
		    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		        },1000); 
		    	elTitle.html('How');
		    //	alert("2");
		    	//jQuery(jQuery("#tabOpl").find("li")[1]).hide();
		    	return false;
		    }});
		 }
		 
		 if(jQuery("#chkoplmIsok").is(':checked')){
			    
			    oknotokTabSelection();
		 }
		 
		// ---------------- chnaging present after 07 Nov 2025  Vignesh ---- // 07 Nov 2025 
// 		 if(jQuery("#chkoplmIspresent").is(':checked')){
// 			 oknotokTabSelectionPresent();
//      	 }
		 
		 //

		 if(jQuery("#chkoplmIsgeneral").is(':checked')){
			 
		/* //	 alert("123455");
			 jQuery('#tabOpl ul.tabs li').each(function(i, v) {
				    var elTitle = jQuery('.tabs-title', v);
				    var title = elTitle.html();
				    if(i==0)
				    	elTitle.html('Why');
				    else if(i==1){
				    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
				 
				    	setTimeout(function() {
				    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
				        },1000); 
				    	elTitle.html('How');
				    	jQuery(jQuery("#tabOpl").find("li")[1]).hide();
				    	return false;
				    }}); */
			
		    general2();
		 }
		 
}
function general2()
{
    jQuery("#prstDescsubHeader").html("General Description");
	jQuery("#prstCondsubHeader").html(" General Condition");
	jQuery("#aftDescsubHeader").html(" General Description");
	jQuery("#aftCondsubHeader").html(" General Condition");

	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
    },1000);   
	
	jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		    var elTitle = jQuery('.tabs-title', v);
		    var title = elTitle.html();
		    if(i==0)
		    	elTitle.html(' General  ');
		    else if(i==1){
		    	var frmMode=jQuery('#frmMode').val();
				if(frmMode=="Approval"||frmMode=="Update"||frmMode=="View")
					{
					jQuery(jQuery("#tabOpl").find("li")[1]).hide();
					}
				else
		    	{
		    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
		    	}
		    	setTimeout(function() {
		    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		        },1000); 
		    	elTitle.html('General ');
		    	return false;
		    }
	});


//jQuery('#chkoplmIsgeneral').click(function() 
if(jQuery("#chkoplmIsgeneral").is(':checked'))
	jQuery('#chkoplmIsgeneral').prop('checked',true);

	if(jQuery("#chkoplmIspresent").is(':checked'))
	  jQuery('#chkoplmIspresent').prop('checked',false);
	  
	  if(jQuery("#chkoplmwhyhow").is(':checked'))
          jQuery('#chkoplmwhyhow').prop('checked',false);
      
	  if(jQuery("#chkoplmIsok").is(':checked'))
			jQuery('#chkoplmIsok').prop('checked',false);
		
	jQuery("#prstDescsubHeader").html("General Description");
	jQuery("#prstCondsubHeader").html("General Condition");
	jQuery("#aftDescsubHeader").html(" General Description").show();
	jQuery("#aftCondsubHeader").html(" General Description").show();
	jQuery("#chkoplmIsok").prop({'checked':false});
	jQuery("#chkoplmIspresent").prop({'checked':false});
	jQuery("#chkoplmwhyhow").prop({'checked':false});
	jQuery("#chkoplmIsgeneral").prop({'checked':true});
/*	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','none');
    },1000); */
     
}
function oknotokTabSelection()
{
    jQuery("#prstDescsubHeader").html("Not Ok Description");
	jQuery("#prstCondsubHeader").html(" Not Ok Condition");
	jQuery("#aftDescsubHeader").html(" Ok Description");
	jQuery("#aftCondsubHeader").html(" Ok Condition");

	setTimeout(function() {
		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
    },1000);   
	
	jQuery('#tabOpl ul.tabs li').each(function(i, v) {
		    var elTitle = jQuery('.tabs-title', v);
		    var title = elTitle.html();
		    if(i==0)
		    	elTitle.html(' Not Ok  ');
		    else if(i==1){
		    	
		    	jQuery(jQuery("#tabOpl").find("li")[1]).show();
		    	setTimeout(function() {
		    		jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
		        },1000); 
		    	elTitle.html('  Ok  ');
		    	return false;
		    }
	});
	/*function general(){alert("hhh");
		
	    jQuery("#prstDescsubHeader").html("General Description");
		jQuery("#prstCondsubHeader").html("General Condition");
		jQuery("#aftDescsubHeader").html(" ");
		jQuery("#aftCondsubHeader").html(" ");
	 
		jQuery('#tabOpl ul.tabs li').each(function(i, v) {
			    var elTitle = jQuery('.tabs-title', v);
			    var title = elTitle.html();
			    if(i==0)
			    	elTitle.html(' General  ');
			    else if(i==1){
			    	elTitle.html('   ');
			    	return false;
			    }
		});*/

}

function oknotokTabSelectionwhyhow()
{//alert("not ok y ?");
	jQuery("#prstDescsubHeader").html("Why Description");
	jQuery("#prstCondsubHeader").html("Why Condition");
	jQuery("#aftDescsubHeader").html("How Description");
	jQuery("#aftCondsubHeader").html("How Condition");
	}
/*function generalTabSelectionwhyhow()
{
	jQuery("#prstDescsubHeader").html("Why Description");
	jQuery("#prstCondsubHeader").html("Why Condition");
	jQuery("#aftDescsubHeader").html("How Description");
	jQuery("#aftCondsubHeader").html("How Condition");
	}*/

function oknotokTabSelectionPresent()
{//alert("not ok pre");
	jQuery("#prstDescsubHeader").html("Present Description");
	jQuery("#prstCondsubHeader").html("Present Condition");
	jQuery("#aftDescsubHeader").html("After Description");
	jQuery("#aftCondsubHeader").html("After Condition");
	}
/*function generalTabSelectionPresent()
{
	jQuery("#prstDescsubHeader").html("Present Description");
	jQuery("#prstCondsubHeader").html("Present Condition");
	jQuery("#aftDescsubHeader").html("After Description");
	jQuery("#aftCondsubHeader").html("After Condition");
	}*/


function frmOplCreation_beforeSubmit() {
		var whyhow=jQuery('#chkoplmwhyhow').is(':checked');
		var IsOk=jQuery('#chkoplmIsok').is(':checked');
		var IsPresent=jQuery('#chkoplmIspresent').is(':checked');
		var Isgeneral=jQuery('#chkoplmIsgeneral').is(':checked');
	    var Emppillar=jQuery('#hdnEmppillar').val();
	    //
	    
	  //alert(" emppillar123 :: "+Emppillar);
	    if(whyhow==true){
         jQuery('#chkoplmwhyhow').val('W');
         //alert(" After Why How :: "+jQuery('#chkoplmwhyhow').val());
		}else if(IsOk==true){
		   jQuery('#chkoplmwhyhow').val('Y');	
		}else if(IsPresent==true){
		   jQuery('#chkoplmIspresent').val('Y');	
		}
		else if(Isgeneral==true){
			   jQuery('#chkoplmIsgeneral').val('Y');	
			}
		/*
		if(jQuery("#frmMode").val()=="Create"){
		
			if(whyhow==true){
		    jQuery('#chkoplmwhyhow').val('W');
		    jQuery('#chkoplmwhyhow').val('N');
		    jQuery('#chkoplmIspresent').val('N');
			}else if(IsOk==true){
		    jQuery('#chkoplmwhyhow').val('W');
			  jQuery('#chkoplmwhyhow').val('Y');
			  jQuery('#chkoplmIspresent').val('N');
			}else if(IsPresent==true){ 
			  jQuery('#chkoplmwhyhow').val('N');
			  jQuery('#chkoplmwhyhow').val('N');
			  jQuery('#chkoplmIspresent').val('Y');	
			}
			
	}*/
		var mode = jQuery("#frmMode").val();
	    var oplId=jQuery("#cmboplmKeyid").val();
	    var ds = "?&type=Emppillar"+"&keyid="+oplId;  
	    if(mode=="Update"){
			if(whyhow==true){
	          jQuery('#chkoplmwhyhow').val('W');
	          jQuery('#chkoplmIsok').val('N');
	          jQuery('#chkoplmIspresent').val('N');
	          jQuery('#chkoplmIsgeneral').val('N');
			}else if(IsOk==true){
		      jQuery('#chkoplmwhyhow').val('N');
			  jQuery('#chkoplmIsok').val('Y');
			  jQuery('#chkoplmIspresent').val('N');
			  jQuery('#chkoplmIsgeneral').val('N');
			}else if(IsPresent==true){ 
			  jQuery('#chkoplmwhyhow').val('N');
			  jQuery('#chkoplmIsok').val('N');
			  jQuery('#chkoplmIspresent').val('Y');
			  jQuery('#chkoplmIsgeneral').val('N');
			  //alert(" Ispresent "+jQuery('#chkoplmIspresent').val());
			}
			else if(Isgeneral==true){ 
			  jQuery('#chkoplmwhyhow').val('N');
			  jQuery('#chkoplmIsok').val('N');
			  jQuery('#chkoplmIspresent').val('N');
			  jQuery('#chkoplmIsgeneral').val('Y');
 			  //alert(" Ispresent "+jQuery('#chkoplmIspresent').val());
			}
		}		
		else if(mode=="View" && (Emppillar != "Y")){  //emppillar!=null ||
			popupCommonErrorMsg("OPL Can not be modified data in " + mode + " Mode. ");
			return false;
		}else if(Emppillar=="Y"){
		
			if (jQuery('#chkOplmUtiliseforfuture').is(':checked')==true ){
				 ds += "&value=Y";
			}else if (jQuery('#chkOplmUtiliseforfuture').is(':checked')==false ){
				 ds += "&value=N";
			}
			
            processAjaxCalls("updateApprovedStatusLevel.opl",ds,"updateUtiliseSuccess","");
			return false;
		}else if(Emppillar=="N"){
			return false;
		}
	//var whyhow=jQuery('#chkoplmwhyhow').val(); 
	var PillarId=jQuery('#hdnpillarId').val();
	//alert(" PillarId :: "+PillarId);
	
	if(PillarId.trim().length>0)
		return "OplPillarLink="+JqGridToJsonSelectdRows('pillarGrid','pillar_checkbox','txtselectionFlag');
	
	var studGridData = converToJsonObject("studGrid");
	
	var OplMatrix=jQuery('#hdnapprovalMode').val();
	if (OplMatrix=="OPLMATRIX" && studGridData==false)
		return false;

	//alert(" hdnaftrimgclr :: "+jQuery('#hdnaftrimgclr').val());
    //alert(" hdnprtimgclr :: "+jQuery('#hdnprtimgclr').val());
    
    /*var prtimgclr=jQuery('#hdnprtimgclr').val();
    var aftrimgclr=jQuery('#hdnaftrimgclr').val();
    var keyid=jQuery('#hdnoplkeyid').val();
    var imgclrdata;

    if((prtimgclr.trim().length>0 && aftrimgclr.trim().length>0))
    	imgclrdata="IMAGETYPEAFT=AFT"+"&IMAGETYPEPRE=PRE"+"&keyid="+keyid;
    else if((prtimgclr.trim().length>0 && aftrimgclr.trim().length<=0))
    	imgclrdata="IMAGETYPEPRE=PRE"+"&keyid="+keyid;
    else if(aftrimgclr.trim().length>0 && prtimgclr.trim().length<=0)
    	imgclrdata="IMAGETYPEAFT=AFT"+"&keyid="+keyid;
 
    return imgclrdata;*/
	
    var griddata = "&gridData="+studGridData;
    var hiddenvalue=jQuery('#hdnApprovallevel').val();
    var prtimgclr=jQuery('#hdnprtimgclr').val();
    var aftrimgclr=jQuery('#hdnaftrimgclr').val();
    var keyid=jQuery('#hdnoplkeyid').val();
    
    if((glbOplmStatus=="A" && glbOplmApprovallevel=="P")||(glbOplmStatus=="R" && glbOplmApprovallevel=="DMT LEADER")||(glbOplmStatus=="E" && glbOplmApprovallevel=="REWORK"))
    	griddata = griddata + "&status="+glbOplmStatus+"&Approvallevel="+glbOplmApprovallevel;
    else if(glbOplmStatus=="A" && glbOplmApprovallevel=="DMT LEADER")
    	griddata = griddata + "&status="+glbOplmStatus+"&Approvallevel="+glbOplmApprovallevel;
    else if(glbOplmStatus=="C" && glbOplmApprovallevel=="-")
    	griddata = griddata + "&status="+glbOplmStatus;
    else if(glbOplmStatus=="-" && glbOplmApprovallevel=="-" && hiddenvalue=="DMT LEADER")
    	griddata = griddata + "&status=A"+"&Approvallevel=DMT LEADER";
    else if(glbOplmStatus=="C" && hiddenvalue=="DMT LEADER")
    	griddata = griddata + "&status="+glbOplmStatus;
    else
       {
            if((prtimgclr.trim().length>0 && aftrimgclr.trim().length>0))
            	griddata="IMAGETYPEAFT=AFT"+"&IMAGETYPEPRE=PRE"+"&keyid="+keyid;
    	    else if((prtimgclr.trim().length>0 && aftrimgclr.trim().length<=0))
    	    	griddata="IMAGETYPEPRE=PRE"+"&keyid="+keyid;
    	    else if(aftrimgclr.trim().length>0 && prtimgclr.trim().length<=0)
    	    	griddata="IMAGETYPEAFT=AFT"+"&keyid="+keyid;
       }
    
	return griddata;
	
}

function converToJsonObject(jqGridId)
{
	var row = jQuery("#"+jqGridId).jqGrid('getDataIDs');
    var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");// col get data
	var jsonArrO='[';
	for(var i=0;i<row.length;i++)
	{

	 var colIndexName = col[i];
	 var rowid=row[i];
	 var sel = [];

	 var checkval=jQuery("#"+jqGridId).jqGrid('getCell',rowid,"chkval4");
     if(checkval=="1"||checkval==""||checkval!=0){
	 
     //var Studentdate= getFieldValue("studGriddteOpllDate_"+rowid); -- 11-Dec-2015
	 //var teacher= getFieldValue("studGridcmboplcombo_"+rowid);  --11-Dec-2015	 
	 // if (fnStudentDateValidation(rowid)==false )
		 //return false;

	 var Studentdate=jQuery("#"+jqGridId).jqGrid('getCell',rowid,"dteOpllDate");
	 var teacher= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"cmbOpllTeachername");//cmbOpllTeachername,cmbOpllTeacher
	 var Studentname= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"StudentId");
     var select= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"chk1");  
     var select1= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"chk2");
     var select2= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"chk3");  
     var select3= jQuery("#"+jqGridId).jqGrid('getCell',rowid,"chk4");  
     	 
     sel[0] = select;
     sel[1] = select1;
     sel[2] = select2;
     sel[3] = select3;
 	
	    for(var j=4;j<col.length;j++){
			if(sel[j-parseInt(4)]!=undefined && sel[j-parseInt(4)]!=null && sel[j-parseInt(4)]!=" "){//alert(1);
				jsonArrO+= '{';
				jsonArrO += '"txtOpllKeyid":"",';
				jsonArrO += '"dteOpllDate":"'+Studentdate+'",';
				jsonArrO += '"txtOpllTeacher":"'+teacher+'",';
				jsonArrO += '"txtOpllStudent":"'+Studentname+'",';
				jsonArrO += '"txtOpllMtrxKeyid":"'+sel[j-parseInt(4)]+'"';
				jsonArrO+= '},';	
			}
	    }
	}
}	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	
	return jsonArrO;

}

function fnStudentDateValidation() {
		
		var targetDate= getFieldValue("dtegridDate");
		
		var detectionDate = getFieldValue("dteoplmDate");
		detectionDate = detectionDate.substr(0,12);
		var currentDate = getServerDateTime();
		//targetDate=targetDate+"23:59";
		if(convertStringToDate(targetDate) > currentDate)
		{
			alert('Executed Date Should Not Greate than Current Date');
			fillWithCurrentDate("dtegridDate");
			return false;
		}
		else if(convertStringToDate(targetDate) < convertStringToDate(detectionDate))
		{	
			alert('Executed Date Should Not Less than OPL Date');
			fillWithCurrentDate("dtegridDate");
			return false;
		}
		else {
		    clearValidationErrorMsg("dtegridDate");
		}
}

function updateApprovedLevel(wfStatus,lastLevel, nextLevel)
{
	glbOplmApprovallevel=nextLevel;
	if((wfStatus == "A" ||wfStatus == "E"||wfStatus == "R") ){//&& lastLevel == "Y"){
		var status="";
	     if(wfStatus == "A" && lastLevel == "Y")
	    	 status="C";
	     else if(wfStatus == "A")
	    	 status="A";
	     else if(wfStatus == "E") {
	    	 status="E";
	    	 nextLevel="REWORK";
	     }else if(wfStatus == "R") {
	    	 status="R";
	     }
 
	     glbOplmStatus= status;
	     glbOplmApprovallevel=nextLevel;
	     
	     if(nextLevel.trim().length==0)
	    	 nextLevel="-";
	     
	     
	     var oplId=jQuery("#cmboplmKeyid").val();
	     var ds = "?&status="+status+"&nextLevel="+nextLevel+"&keyid="+oplId;
	     if (jQuery('#chkOplmMpworthy').is(':checked')==true ){
			 ds += "&mpvalue=Y";
		}else if (jQuery('#chkOplmMpworthy').is(':checked')==false ){
			 ds += "&mpvalue=N";
		}
		//saveForm('frmOplCreation','updateApprovedStatusLevel.opl'+ds);
		//alert(ds);
	     processAjaxCalls("updateApprovedStatusLevel.opl",ds,"updateSuccess","");
	}		
}
	
//function updateSuccess

function updateUtiliseSuccess(result){
    alert(result.msg);
    navigateToPrevForm();
}

function OPLAPPROVE_successCallback(result)
{
    //alert(" cccck :: "+result.nextRoleName);
	updateApprovedLevel(result.wfStatus, result.lastLevel, result.nextRoleName);
	closePopUpDialoge("OPL");
}



(function () {
  // The framework stores the active form id in a hidden #submitForm input.
  var el = document.getElementById('submitForm');
  var formId = el && el.value ? el.value : null;

  if (formId && !window[formId + '_successsCallback']) {
    window[formId + '_successsCallback'] = function (result) {
      // Close the popup
      if (window.parent && window.parent.jQuery) {
        // Dialog id is the same "oplModify" you passed to LoadPopUp
        window.parent.jQuery('#oplModify').dialog('close');

        // Optional: refresh the grid behind the popup without navigating
        if (window.parent.jQuery('#oplGrid')[0]) {
          window.parent.jQuery('#oplGrid').trigger('reloadGrid');
        }
      }
      return result; // keep the framework happy
    };
  }
})();




 // ------------ VIgnesh for tabs  06Nov2025 --------------------------------//
//=== Re-apply tab captions from saved flags on load (Update/View/Approval) ===
(function () {
function getFormMode() {
 // frmMode can be empty in some paths; fall back gracefully
 var m = (jQuery('#frmMode').val() || '').trim();
 if (!m) m = (jQuery('#hdnMode').val() || '').trim();       // ${requestScope.Mode}
 if (!m) m = (jQuery('#hdncreate').val() || '').trim();     // ${requestScope.Formmode}
 if (!m && typeof window.formMode !== 'undefined') m = (window.formMode || '').trim();
 return (m || '').toUpperCase();
}

// Present/After redo without firing your confirm() click handler
function _presentAfterTabs() {
 jQuery("#prstDescsubHeader").html("Present Description");
 jQuery("#prstCondsubHeader").html("Present Condition");
 jQuery("#aftDescsubHeader").html("After Description");
 jQuery("#aftCondsubHeader").html("After Condition");
 // Ensure EasyUI tabs are visible after parser
 setTimeout(function () {
   jQuery('#tabOpl ul.tabs li.tabs-selected').css('display','block');
 }, 300);
 // Make sure tab titles match the mode
 jQuery('#tabOpl ul.tabs li').each(function (i, v) {
   var elTitle = jQuery('.tabs-title', v);
   if (i === 0) elTitle.html('Present Condition');
   else if (i === 1) elTitle.html('After Condition');
 });
}

function _syncTabsToChecked() {
 var mode = getFormMode();

 // In Create we intentionally keep your default "Present/After"
 if (mode === 'CREATE') return;

 // Decide which view we should show, based on which box is actually checked
 var isWhyHow  = jQuery('#chkoplmwhyhow').is(':checked');     // 'W' path
 var isOkNotOk = jQuery('#chkoplmIsok').is(':checked');       // 'Y' path
 var isGeneral = jQuery('#chkoplmIsgeneral').is(':checked');  // general
 var isPresent = jQuery('#chkoplmIspresent').is(':checked');  // present/after

 if (isWhyHow) {
   // Your existing function that sets "Why / How" captions (no confirm)
   if (typeof why === 'function') why();
   return;
 }
 if (isOkNotOk) {
   // Your existing function that sets "Not Ok / Ok" captions (no confirm)
   if (typeof oknotokTabSelection === 'function') oknotokTabSelection();
   return;
 }
 if (isGeneral) {
   // Your existing function that sets "General" captions (no confirm)
   if (typeof general === 'function') general();
   return;
 }
 // Fallback to present/after (safe default)
 _presentAfterTabs();
}

function _waitForTabsAndSync() {
 // Wait until EasyUI has built the <ul class="tabs"> structure
 if (jQuery('#tabOpl ul.tabs li').length < 1) {
   setTimeout(_waitForTabsAndSync, 50);
   return;
 }
 _syncTabsToChecked();
}

if (document.readyState === 'loading') {
 document.addEventListener('DOMContentLoaded', function () {
   // Run after your other initializers (including the line that checks Present)
   setTimeout(_waitForTabsAndSync, 0);
 });
} else {
 setTimeout(_waitForTabsAndSync, 0);
}
})();
//------------ VIgnesh for tabs  06Nov2025 --------------------------------//


</script>
<!--end of grid-->
<form name="frmOplCreation" id="frmOplCreation" action="opl_create.opl"
	method="post"><input type="hidden" id="hdnOplmRefdocno"
	name="hdnOplmRefdocno" value="${requestScope.oplTlMst.oplmRefdocno}" />
<input type="hidden" id="hdnOplmRefdo`ctype" name="hdnOplmRefdoctype"
	value="${requestScope.oplTlMst.oplmRefdoctype}" />

<div style="margin-left: 4%; width: 800px; margin-top: 20px;">
<div class="main-cntborder" style="width: 1100px; height: 860px;">
<table id="OplCreation" rules="none" border="0" style="width: 100%;">
	<tr>
		<td colspan="3" style="width: 30%">
		<div id="frmOplCreationFuntKeyIds">
		 <input type="hidden" id="factory" name="cmboplmFactoryid" value="${requestScope.oplTlMst.oplmFactoryid}"></input>
		 <input type="hidden" id="section" name="cmboplmSectionid" value="${requestScope.oplTlMst.oplmSectionid}"></input> 
		 <input type="hidden" id="cell" name="cmboplmCellid" value="${requestScope.oplTlMst.oplmCellid}"></input> 
		 <input type="hidden" id="machine" name="cmboplmMachineid1" value="${requestScope.oplTlMst.oplmMachineid}"></input> 
		 <input type="hidden" id="flid" name="cmboplmFlid" value="${requestScope.oplTlMst.oplmFlid}"></input>
		 <input type="hidden" id="elementId" name="cmboplmElementid"  value="${requestScope.oplTlMst.oplmElementid}"></input>
		 </div>
		<div class="easyui-paddingbfpx"
			style="padding-left: 20px; width: 250px;"><label>Document
		No</label></div>

		<div style="padding-bottom: 09px; padding-left: 20px;"><span
			style="padding-right: 20px;"><input id="cmboplmKeyid"
			name="cmboplmKeyid" class="easyui-combobox" style="width: 130px;"
			value="${requestScope.oplTlMst.oplmKeyid}"
			readonly="${requestScope.oplFormBean.disableCmbDocumentNo}"></span>
		<span> <input type="checkbox" id="chkoplmentry"
			name="chkoplmentry" style="display: none;" /> <label
			style="padding-right: 4px; display: none;">Entry</label> <input
			type="checkbox" id="chkoplmupload" name="chkoplmupload"
			style="display: none;" /> <label
			style="padding-right: 4px; display: none;">Upload</label> <input
			type="checkbox" id="chkOplmMpworthy" name="chkOplmMpworthy"
			${requestScope.oplTlMst.oplmMpworthy == 'Y' ? 'checked':''} />
		<label style="padding-right: 4px;">MP Worthy</label> <input
			type="checkbox" id="chkOplmUtiliseforfuture"
			name="chkOplmUtiliseforfuture"
			${ requestScope.oplTlMst.oplmUtiliseforfuture == 'Y' ? 'checked':''} />
		<label style="padding-right: 4px;">Utilize For Future Projects</label>


		<input type="checkbox" id="chkoplmwhyhow" name="chkoplmwhyhow"
			value=" "
			${ requestScope.oplTlMst.oplmIsok == 'W' ? 'checked':''} >
		<label style="padding-right: 4px;">Why/How</label> <input
			type="checkbox" id="chkoplmIsok" name="chkoplmIsok"
			${ requestScope.oplTlMst.oplmIsok == 'Y' ? 'checked':''} >
		<label style="padding-right: 4px;">Not Ok/Ok</label> <input
			type="checkbox" id="chkoplmIspresent" name="chkoplmIspresent"
			${ requestScope.oplTlMst.oplmIspresent == 'Y' ? 'checked':''} />
		<label style="padding-right: 4px;">Present/After</label>
			<input type="checkbox" id="chkoplmIsgeneral" name="chkoplmIsgeneral"
			value=" "
			${ requestScope.oplTlMst.oplmIsgeneral == 'Y' ? 'checked':''} />
		<label style="padding-right: 4px;">General</label>
		 </span> <span
			id="spnExcelView"> 
			<span><input type="checkbox" id="chkUndrstndOpl"
				name="chkUndrstndOpl"
				${ requestScope.oplFormBean.chkUnderstoodOpl == true ? 'checked':''} /></span>
			<span style="padding-right: 40px;"> <label>Have You
			Understood the OPL</label>
			 <input type="button" id="btnExcelview" name="btnExcelview" data-keep-enabled="true" class="easyui-button" style="margin-left: 40px; width: 80px;" value="Excel View" /> 
			
			</span>

		</span> <%-- <input type="hidden" id="hdnoplmIsok" name="hdnoplmIsok" value = "${ requestScope.oplTlMst.oplmIsok}"/>
					<input type="hidden" id="hdnoplmIspresent" name="hdnoplmIspresent" value = "${ requestScope.oplTlMst.oplmIspresent}"/> --%>
		</div>

		<div id="oplmfunLocation" style="padding-left: 20px; width: 74%;">

		</div>

		<div id="oplactnplan"
			style="float: right; margin-top: -26px; margin-top: -23px\9; width: 80px; margin-right: 170px; margin-right: 110px\9;">

		<input type="button" id="btnoplActionplan" name="btnoplActionplan"
			class="easyui-button"
			style="width: 80px; height: 22px; display: none;" value="Action Plan" />
		</div>

		<div style="position: relative;">
			<span id="oplFilemgr" style="position: absolute; right: 230px; right: 70px\9; top: -30px;">
		</span>
		</div>
		  <div>
			<input style="margin-left:920px;margin-top:-41px;" id="btnoplupload" class="easyui-button" name="btnoplupload"  type="button" value="Switch to OPL Upload" style="height:30px;"/>
</div>
		
		<div id="err_cell" class="tpm-errormsg"
			style="display: block; padding-left: 20px;"></div>

		</td>
	</tr>


	<tr>
		<!--left  pane -->
		<td valign='top'>
		<div style="padding-left: 20px;">
		<div class="sub-header " style="margin-bottom: 10px; width: 400px;"><label>OPL</label></div>
		<!-- 		<div style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Factory</label></span></div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
		                		 <input id="cmboplmFactoryid" name="cmboplmFactoryid" class="easyui-combobox"  style="width:355px;"  value="${requestScope.oplTlMst.oplmFactoryid}" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/>  / >
			
							</div>
			
					 		
							<div  style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Document No</label></span></div> 
		                  	<div style="padding-bottom: 8px;padding-left:px;"> 
		                		<input id="cmboplmKeyid" name="cmboplmKeyid" class="easyui-combobox"  style="width:255px;" value="${requestScope.oplTlMst.oplmKeyid}" readonly="${requestScope.oplFormBean.disableCmbDocumentNo}" >
							</div>
						-->
		<div style="padding-bottom: 5px; padding-left: 10px;">
<span
			style="padding-right: 120px">
<label class="mandatory-lbl">Date</label>
</span> 
<span style="padding-right: 30px"><label class="mandatory-lbl">Prepared
		By</label></span>
</div>
		
<span>
		<input id="dteoplmDate" name="dteoplmDate" class="easyui-datebox"
			style="width: 100px;" value="${requestScope.oplTlMst.oplmDate}"
			"${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/>
</span>

		<span style="margin-left: 70px;margin-top:10px"> <input class="easyui-combobox" id="cmboplmPreparedid"
			name="cmboplmPreparedid" style="width: 160px;/*  height: 21px; */"
			value="${requestScope.oplTlMst.oplmPreparedid}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>
			
</span>


		<table>
			<tr>
				<td><span id="err_dteoplmDate" class="tpm-errormsg"></span></td>
				<td><span id="err_cmboplmPreparedid" class="tpm-errormsg"
			style="padding-left: 10px;"></span></td>

			</tr>
		</table>
		<div class="sub-header"
			style="height: 17px; margin-bottom: 10px; width: 400px;"><label>Details</label></div>

		<!-- 		<div  style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Section</label></span></div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
		                		<input id=cmboplmSectionid name="cmboplmSectionid" class="easyui-combobox"  style="width:355px;" value="${requestScope.oplTlMst.oplmSectionid}" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/> / >
							</div>
							
							<div  style="padding-bottom: 5px; "><span><label class="mandatory-lbl"> Cell</label></span></div> 
							<div style="padding-bottom: 8px;padding-right:10px;"> 
		                		<input id="cmboplmCellid" name="cmboplmCellid" class="easyui-combobox"  style="width:355px;" value="${requestScope.oplTlMst.oplmCellid}" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/> />
							</div>
				 -->
		<div id="rtdtomld">
		<div style="padding-bottom: 5px; width: 260px;"><span> <label
			class="mandatory-lbl"> Related to</label></span> <span> <label
			id="lblMld" style="padding-left: 52px;">Mould</label></span></div>
		<div style="padding-bottom: 8px; padding-right: 10px; width: 400px;">
		<select id="cmboplmRelated" class="easyui-combobox"
			name="cmboplmRelated" style="height: 22px; width: 105px;"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} >
			<option value="MCH">MACHINE</option>
			<option value="MLD">MOULD</option>
		</select> <input id="relatedToCMB" name="relatedToCMB" type="hidden"
			value="${requestScope.oplTlMst.oplmRelated}" /> <input type="text"
			id="cmboplmMouldid" name="cmboplmMouldid" class="easyui-combobox"
			value="${requestScope.oplTlMst.oplmMouldid}"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
			style="width: 223px;" /></div>
		</div>

		<div id="err_cmboplmMouldid" class="tpm-errormsg"
			style="display: block; padding-left: 110px; margin-top: 5px;"></div>

		<div style="padding-bottom: 5px; width: 356px;"><span><label
			id="lblMachine"> Equipment</label></span> <span style="margin-left: 114px"><label>Process</label></span></div>
		<div style="padding-bottom: 8px; padding-right: 10px; width: 356px;"
			id="divMachine"><span style="padding-left: 0px;"> <input
			id="cmboplmMachineid" name="cmboplmMachineid" readonly="readonly"
			class="easyui-combobox" style="width: 175px;"
			value="${requestScope.oplTlMst.oplmMachineid}"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>
		</span> <span style="padding-left: 0px;"> <input id="cmboplmProcess"
			name="cmboplmProcess" class="easyui-combobox" style="width: 175px;"
			value="${requestScope.oplTlMst.oplmProcess}"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} >
		</span></div>


		<div style="padding-bottom: 5px; width: 356px;"><span><label
			class="mandatory-lbl"> Theme</label></span></div>
		<div style="padding-bottom: 3px; padding-right: 10px; width: 356px;">
		<textarea class="txtarea" id="txtoplmTheme" name="txtoplmTheme"
			rows="5" cols="6"
			style="width: 355px; resize: none; height: 68px; text-transform: lowercase"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}">${requestScope.oplTlMst.oplmTheme}</textarea>
		</div>


<div style="width: 408px;">
<div class="sub-header" style="margin-bottom: 10px; width: 400px;">
    <label class="mandatory-lbl">Classification</label>
</div>

<table style="width: 400px; margin-top: 8px;">
    <tr>
        <td style="width: 50%; vertical-align: top;">
            <!-- Left Column -->
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationB" name="chkClassificationB"
                    value="B"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationB == 'B' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Basic Knowledge</label></span>
            </div>
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationI" name="chkClassificationI"
                    value="I"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationI == 'I' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Improvement Cases</label></span>
            </div>
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationT" name="chkClassificationT"
                    value="T"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationT == 'T' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Trouble/Problem Cases</label></span>
            </div>
        </td>
        <td style="width: 50%; vertical-align: top;">
            <!-- Right Column -->
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationS" name="chkClassificationS"
                    value="S"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationS == 'S' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Safety</label></span>
            </div>
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationC" name="chkClassificationC"
                    value="C"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationC == 'C' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Cost</label></span>
            </div>
            <div style="margin-top: 8px;">
                <span>&nbsp;</span>
                <input type="checkbox" id="chkClassificationP" name="chkClassificationP"
                    value="P"
                    ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
                    ${ requestScope.oplFormBean.classificationP == 'P' ? ' checked':''} />
                <span style="padding-left: 6px;"><label>Production</label></span>
            </div>
        </td>
    </tr>
</table>



<span id="err_chkClassificationT" class="tpm-errormsg" style=""></span>
</div>

	

		<div
			style="padding-bottom: 3px; padding-right: 10px; margin-top: 12px; width: 400px;">

		<div class="sub-header " style="margin-bottom: 10px; width: 400px;"><label
			style="position: absolute">Description of Classification</label><span
			style="float: right;"></span></div>

		<%--   <div style="padding-bottom: 15px;padding-right:10px;"> 
		            <textarea class="txtarea" id="txtoplmoplmClassdescription" name="txtoplmClassdescription" rows="5" cols="6" style="width:355px; height : 68px;resize:none;" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/> >${requestScope.oplTlMst.oplmClassdescription}</textarea>
					</div> --%>

		<div style="padding-bottom: 15px; padding-right: 10px;"><textarea
			class="txtarea" id="txtoplmClassdescription"
			name="txtoplmClassdescription" rows="5" cols="6"
			style="width: 355px; height: 68px; resize: none;"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>${requestScope.oplTlMst.oplmClassdescription}</textarea>
		</div>
       
		</div>
		</td>
		<!--Right  pane -->

		<td class="valigncnt">

		<div id="OplTab"
			style="padding-left: 0px; width: 652px; height: 750px;">
		<div id="tabOpl" class="easyui-tabs" fit="true" plain="true"
			style="width: 200px; height: 900px;">
		<div id="presentTab" title="Present Condition" style="padding: 10px;">
		<div class="sub-header" style="width: 100%; margin-bottom: 10px;"><label
			id="prstDescsubHeader"> Present Description </label><span
			style="float: right;"> <!--<input type="button" class="button" value="Export to Excel" id="exprtexcel" />-->
		</span></div>
		
<div style="padding-bottom: 15px; padding-right: 10px;"><textarea
			class="txtarea" id="txtoplmPresentcondition"
			name="txtoplmPresentcondition" rows="5" cols="6"
			style="width: 614px; height: 130px; resize: none;"
			 ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>${requestScope.oplTlMst.oplmPresentcondition}</textarea>
		</div>
		<div class="sub-header "
			style="margin-bottom: 0px; position: relative;"><label
			id="prstCondsubHeader"> Present Condition </label><span
			style="padding-left: 35px; font-size: 8px;">width:11.01 cm
		height:7.01 cm</span>
		<span>
            <label id="lblResize"style="font-size:9px;"></label>
        </span>
		 <span
			style="float: right; padding-bottom: 5px; height: 20px;"></span> <input
			class="easyui-button" type="button" value="Image" id="btnImfPresent"
			style="height: 20px" 
			 
			  ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} />
			<!--   onclick="presentImage();"  -->
		<span style="padding-left: 10px;"> <input type="button"
			class="easyui-button" value="Clear" id="btnImgPresentClear"
			name="btnImgPresentClear" style="height: 20px"
		  ${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} />
		</span> </span></div>

		<div id="divImgPresent" style="padding-top: 20px;"><img alt=""
			id="imgOplmPresentimage" name="imgOplmPresentimage"
			src="${requestScope.oplTlMst.oplmPresentimage}"></div>

		</div>

		<div id="afterTab" title="After Condition" style="padding: 10px;">
		<div class="sub-header " style="width: 100%; margin-bottom: 10px;"><label
			id="aftDescsubHeader" style="position: absolute">After
		Description</label><span style="float: right;"></span></div>

		<div style="padding-bottom: 15px; padding-right: 10px;"><textarea
			id="txtoplmAftercondition" name="txtoplmAftercondition"
			class="txtarea" rows="5" cols="6"
			style="width: 614px; height: 130px; resize: none;"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}> ${requestScope.oplTlMst.oplmAftercondition}</textarea>
		</div>
		<div class="sub-header " style="margin-bottom: 0px;"><label
			id="aftCondsubHeader"> After Condition </label> <span
			style="padding-left: 35px; font-size: 8px;">width:11.01 cm
		height:7.01 cm</span>
		<span>
       		 <label id="lblAftrResize"style="font-size:9px;"></label>
        </span>
		<span
			style="float: right; padding-bottom: 5px; height: 20px;"> <input
			type="button" class="easyui-button" style="height: 20px"
			value="Image" id="dlgImgAfter" name="dlgImgAfter"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} />
		<span style="padding-left: 10px;"> <input type="button"
			class="easyui-button" value="Clear" style="height: 20px"
			id="btnImgAfterClear" name="btnImgAfterClear"
		${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} />
		</span> </span></div>

		<div style="padding-top: 20px;"><img alt=""
			id="imgOplmAfterimage" name="imgImflFilename"
			src="${requestScope.oplTlMst.oplmAfterimage}" width="" height=""
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} /></div>
		</div>

		<div title="Lesson" style="padding: 10px; height: 100%;">
		<div style="width: 100%;">
		<div style="padding-top: 5px;"><span
			style="padding-right: 7px; margin-top: 8px;">&nbsp;</span> <span
			class="sub-header" style="height: 17px; margin-bottom: 10px;"><label>Lesson</label></span>
		</div>
		<div style="padding-left: 10px; padding-bottom: 15px;"><textarea
			id="txtoplmLesson" name="txtoplmLesson" class="txtarea" rows="5"
			cols="6" style="width: 590px; height: 110px; resize: none"
		${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>${requestScope.oplTlMst.oplmLesson}</textarea>
		</div>



		<div class="sub-header" style="margin-bottom: 0px;">Pillar <span
			style="padding-left: 35px; font-size: 8px;"></span> <span
			style="float: right; padding-bottom: 5px; height: 20px;"> <input
			type="button" id="btnOplCatgry" name="btnOplCatgry"
			style="height: 20px;" class="easyui-button" value="OPL Category"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''} />
		<span style="padding-left: 10px;"> </span> </span></div>
		<br>
		<div style="margin-left: 14px; margin-left: 0px\9;">
		<table id="pillarGrid" style="/* width:500px */">
			<tr>
				<td />
			</tr>
		</table>
		<div id="pillarPager"></div>
		</div>

		</div>

		<div class="sub-header" style="height: 17px; margin-bottom: 10px;">Other
		Details</div>

		<div style="padding-bottom: 5px; padding-left: 10px;"><span style="padding-left: 20px">Date</span></div>
		<div style="padding-bottom: 8px; padding-left: 10px;"><span>
		
		<span style="padding-left: 20px"> <input type="text"
			class="easyui-datebox" id="dteoplmPrepareddate"
			name="dteoplmPrepareddate"
			value="${requestScope.oplTlMst.oplmPrepareddate} "
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}
			style="width: 120px; height: 21px;"></span></div>
		
		<div style="display: none;">
		<div style="padding-bottom: 5px;"><span
			style="padding-left: 10px; padding-right: 190px"><label
			id="Approvedby" class="mandatory-lbl">Approved By</label></span> <span
			style="padding-left: 20px">Date</span></div>
		<div style="padding-bottom: 8px; padding-left: 10px;"><!-- <span><input type="checkbox" id="chkApprvdBy" name="chkApprvdBy" /> -->
		<span> <!--		                			<input    class="easyui-combobox"  id="cmboplmApprovedid" name="cmboplmApprovedid" style="width:260px;height:21px;" value="${requestScope.oplTlMst.oplmApprovedid}" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}"/> >-->
		<input class="easyui-combobox" id="cmboplmApprovedid"
			name="cmboplmApprovedid" style="width: 260px; height: 21px;"
			value="${requestScope.oplTlMst.oplmApprovedid}"
		${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}>
		</span> <span style="padding-left: 20px"><input type="text"
			class="easyui-datebox" id="dteoplmApproveddate"
			name="dteoplmApproveddate" style="width: 120px; height: 21px;"
			value="${requestScope.oplTlMst.oplmApproveddate}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}></span>

		</div>
		<table>
			<tr>
				<td><span id="err_cmboplmApprovedid" class="tpm-errormsg"></span>
				</td>
				<td><span id=""></span></td>
			</tr>
		</table>
		</div>

		<div style="display: none;">
		<div style="padding-bottom: 5px;"><span
			style="padding-left: 10px; padding-right: 145px;">Maintenance
		Section</span> <span style="padding-left: 10px; padding-left: 20px;">Functional
		Manager</span></div>
		<div style="padding-bottom: 8px; padding-left: 10px;"><span><input
			type="text" class="easyui-combobox" id="cmboplmTradeid"
			name="cmboplmTradeid" style="width: 260px; height: 21px;"
			value="${requestScope.oplTlMst.oplmTradeid}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}></span>
		<span style="padding-left: 20px"><input type="text"
			class="easyui-text" id="txtoplmDepartmentmanager"
			name="txtoplmDepartmentmanager" style="width: 260px; height: 21px;"
			value="${requestScope.oplTlMst.oplmDepartmentmanager}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}></span>
		</div>

		<div style="padding-bottom: 5px;"><span
			style="padding-right: 167px; padding-left: 10px;">JH Leader</span> <span
			style="padding-left: 58px;">DMT Leader</span></div>
		<div style="padding-bottom: 8px; padding-left: 10px;"><span><input
			type="text" class="easyui-text" id="txtoplmSectionmanager"
			name="txtoplmSectionmanager" style="width: 260px; height: 21px;"
			value="${requestScope.oplTlMst.oplmSectionmanager}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}></span>
		<span style="padding-left: 20px"><input type="text"
			class="easyui-text" id="txtoplmGroupleader" name="txtoplmGroupleader"
			style="width: 260px; height: 21px;"
			value="${requestScope.oplTlMst.oplmGroupleader}"
			${ requestScope.oplFormBean.disableForm == true ? ' readonly':''}></span>

		</div>

		</div>
		<div style="padding-bottom: 5px;"></div>
		<div style="padding-bottom: 8px; padding-left: px;"></div>
		<!-- Other Details tab --></div>
		<div title="Student" id="studentTab" style="padding: 10px;">
		<div id="griddiv" style="float: left; margin: 0px;">
		<div>
		<table cellpadding="2" style="font-size: 12;">
		<tr>
			<td valign="top" style="padding-left: 16px;">
				<div>
				<label style="display:none;">Role</label>
				<span style="padding-left:12px;"><label>Unique Position</label> </span>
				<span style="padding-left:140px;"><label>Employee</label> </span>
				</div>
				<div>
					<span style="display:none;">
						<input class="easyui-combobox" id="cmboplmRole" name="cmboplmRole"  style=" width : 220px;display:none;"  value=" " />
					</span>
					 <span style="padding-left:10px;">
     			       	 <input class="easyui-combobox" id="cmboplunqepstn" name="cmboplunqepstn"  style=" width : 220px;"  value="" />
     			      </span>
				      <span style="padding-left:10px;">
     			       	 <input class="easyui-combobox" id="cmboplmEmployee" name="cmboplmEmployee"  style=" width : 220px;"  value="" />
     			      </span>
     			      <span style="padding-left:10px;">
     			       	 <input type="button" class="easyui-button" id="btnAddEmployee" name="btnAddEmployee"  style="width:100px;height:20px;"  value="Add Employee" />
     			      </span>
				</div>
			</td>
		 </tr>
		</table>
		<table>
		 <tr>
			<td valign="top">
			<div>
				<label style="padding-left: 30px;">Date Executed</label>
				<span style="padding-left:76px;"><label>1</label> </span>
				<span style="padding-left:20px;"><label>2</label> </span>
				<span style="padding-left:30px;"><label>Teacher</label> </span>
			</div>
			<div>
		          <span style="padding-left:30px;">
	     			   <input id="dtegridDate" name="dtegridDate" class="easyui-datebox" style="width: 140px;" value="" />
	     		  </span>
	     		  <span style="padding-left:4px;">
     			       <input type="checkbox" id="grdchkone"  name="grdchkone" style="margin-left:10px;" />
     			       <span style="padding-left:4px;"><input type="checkbox" id="grdchktwo"  name="grdchktwo" style="margin-left:10px;" /></span>
     			  </span>
	     		  <span style="padding-left:26px;">
     			       <input class="easyui-combobox" id="cmbgridEmployee" name="cmbgridEmployee"  style=" width : 220px;"  value="" />
     			  </span>
			      <span style="padding-left:10px;">
			       	   <input type="button" class="easyui-button" id="btnApply" name="btnApply"  style="width:60px;height:20px;"  value="Apply" />
			      </span>			
			</div>
			</td>
		 </tr>
		</table>
		</div>
		<div>
		<table cellpadding="2" style="font-size: 12;">
			<tr>
				<td valign="bottom">
					<input type="checkbox" id="chkSelectAll"  name="chkSelectAll" enabled="enabled" style="margin-left:10px;" />
					<input type="text" value="Select All" readonly="readonly" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left;" />
	            </td>
				<td
					style="padding: 2px; border: inset 2px #fff; background-color: #FBE999;">1</td>
				<td style="padding: 2px; border: ridge 2px #fff; font-weight: bold;">
				Explain</td>
				<td
					style="padding: 2px; border: inset 2px #fff; background-color: #F9D50A;">2</td>
				<td style="padding: 2px; border: ridge 2px #fff; font-weight: bold;">
				Demonstrate</td>
				<td
					style="padding: 2px; border: inset 2px #fff; background-color: #F8A500;">3</td>
				<td style="padding: 2px; border: ridge 2px #fff; font-weight: bold;">
				Practice</td>
				<td
					style="padding: 2px; border: inset 2px #fff; background-color: #05F901;">4</td>
				<td style="padding: 2px; border: ridge 2px #fff; font-weight: bold;">
				Competent</td>
			</tr>
		</table>
		</div>
		<div>
		<table id="studGrid"  style="float: left; width= 400px "></table>
		</div>
		<div id="studPager"></div>

		</div>
		<!-- End of Student tab --></div>

		</div>
		</div>

		</td>
	</tr>
</table>
<c:if test="${requestScope.oplFormBean.viewMode == true }">
	<script type="text/javascript">
   	/*
    	//jQuery("form :input").prop("readonly","readonly");
    //	jQuery(':input','#frmOplCreation')
    //	.not('#divExcelView')
    //	 .prop('readonly','readonly');
    */	
    	</script>
</c:if></div>


<input type="hidden" id="txtSelectRowid" value="">
<input type="hidden" id="hdnaftrimgclr" value="" />
<input type="hidden" id="hdnprtimgclr" value="" />
<input type="hidden" id="hdncreate" value="${requestScope.Formmode}" /> 
<input type="hidden" id="hdnMode" value="${requestScope.Mode}" /> 
<input type="hidden" id="frmMode" name="Oplformmode" value="${requestScope.oplFormBean.formMode}" />
<input type="hidden" id="imgOplPresentImgFilename" name="imgOplPresentImgFilename" value="" /> 
<input type="hidden" id="imgOplAfterImgFilename" name="imgOplAfterImgFilename" value="" />
<input type="hidden" id="hdnChkUnderstoodOpl" value="${requestScope.oplFormBean.chkUnderstoodOpl}" /> 
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" /> 
<input type="hidden" id="hdnbdmode" value="${requestScope.bdmmode}" /> 
<input type="hidden" id="hdnpillarId" /> 
<input type="hidden" id="hdnoplkeyid" value="${requestScope.oplKeyid}" /> 
<input type="hidden" id="hdnEmppillar" value="${requestScope.Emppillar}" /> 
<input type="hidden" id="hdntheme" value=" " /> 
<input type="hidden" id="hdnformName" value="${requestScope.formName}"  />
<input type="hidden" id="hdnvalidate3" value="${requestScope.oplTlMst.oplmMpworthy}" /> 
<input type="hidden" id="hdnvalidate4" value="" /> <input type="hidden" id="hdnuser" value="${requestScope.User}" /> 
<input type="hidden" id="hdnapprovalMode" value="${requestScope.approvalMode}" /> 
<input type="hidden" id="hdnOplmStatus" name="hdnOplmStatus" value="${requestScope.oplTlMst.oplmStatus}" /> 
<input type="hidden" id="hdnwhywhyMode" value="${requestScope.whywhymod}" /></div>
<input type="hidden" id="hdnOplmAprovLevel" name="hdnOplmAprovLevel" value="${requestScope.oplTlMst.oplmAprovLevel}" />
<input type="hidden" id="hdncurdate" name="hdncurdate" value="" />
<input type="hidden" id="hdnempid" name="hdnempid" value="" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="" />
<input type="hidden" id="hdnempnamedata" name="hdnempnamedata" value="" />
</form>