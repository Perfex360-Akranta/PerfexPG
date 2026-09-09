
  <%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>

  <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  	  			var factId =null;
  	  		initialiseForm('frmSection');	
  	  	jQuery("#savingdata").css('display','none');
  			//jQuery('#submitForm').val('frmSection'); // set the id of form to submit

  			var sectaddupdate=jQuery("#txtsectadd").val();
  			//alert("sectaddupdate:::::"+sectaddupdate);


  			fillComboBox("frmSection","cmbSectKeyid"," sectionCombo.commonFilter " );
  			fillComboBox("frmSection","cmbSectCompanyid","companyCombo.commonFilter" );
  			/*fillComboBox("frmSection","cmbSectKeyid"," sectionCombo.commonFilter " );
  			fillComboBox("frmSection","cmbSectCompanyid","companyCombo.commonFilter" );*/
  			fillComboBox("frmSection","cmbSectFactoryid","pbuCombo.commonFilter" );
  			
  			//alert("hihihih");
  			jQuery('#frmSection .easyui-combobox').css('text-transform', 'uppercase');
  			jQuery('#frmSection .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmSection textarea').css('text-transform', 'uppercase');

  			var factId = jQuery("#frmSection input[id='factory']").val();
  		    var sectionId = jQuery("#frmSection input[id='section']").val();
  		   //alert("sectionId ID"+sectionId);
  		    var cellId = jQuery("#frmSection input[id='cell']").val();
  		    //alert("cell ID"+cellId);
  		    
  			var machId = jQuery("#frmSection input[id='machine']").val();


  			var compId = jQuery("#frmSection input[id='company']").val();
  			 

  			var locId = jQuery("#frmSection input[id='location']").val(); 
  			//alert("locId ID"+locId);
  			
  			 var pbuId = jQuery("#frmSection input[id='pbu']").val();    
  			
  	        var flid = jQuery("#frmSection input[id='flid']").val(); 
  	        
  	        var dataStr = "&factId=" + factId
  							+ "&sectionId=" + sectionId
  							+ "&cellId=" + cellId + "&machId="+ machId
  							+ "&compId=" + compId
  							
  							+ "&locId=" + locId
  							+ "&pbuId=" + pbuId
  							+"&flid="+ flid;
				if(sectaddupdate=="add")
					{
					
					 loadFunctionalLocation("DmtfunLocation", "functionalLocadd.sect", "frmDmtfunLocationValues", "frmSection",dataStr);
					}
				else{
					
  			        loadFunctionalLocation("DmtfunLocation", "functionalLoc.sect", "frmDmtfunLocationValues", "frmSection",dataStr);
  			
				}
  	});

  		
  		   function frmSection_deleteSuccessCallback(result){

  		       alert(result.successData.msg);
  		       clearForm("frmSection");
  		      loadFunctionalLocation("DmtfunLocation", "functionalLoc.sect", "frmDmtfunLocationValues", "frmSection"," ");
  		       navigateToPrevForm();
  		       
  			   }
  			function frmSection_exceptionCallback(msg) {
  				//alert("msg in sucess:::"+msg);	
  				jQuery("#savingdata").css('display','none');
  				jQuery('#mstfrm_div').hide();
  			}
  			


  		 function  frmSection_beforeSubmit(){
  			jQuery('#mstfrm_div').addClass('popup-mask');
  			jQuery('#mstfrm_div').show();
  			jQuery("#savingdata").css('display','block');
  			var sectaddupdate=jQuery("#txtsectadd").val();
  			var pbuId = jQuery("#frmSection input[id='pbu']").val();    
  	   	    // alert(pbuId);
  	     	var sectionId = jQuery("#frmSection input[id='section']").val(); 
			//alert("sectionId ID"+sectionId);
			
			if(sectaddupdate=="add"){

			   	if(pbuId == '' || pbuId == ' ' || pbuId == undefined)
	  			 {
	  	    		
	  	   		 alert("Select PBU");
	  	   		 return false;
	  			 }
			   	
	  	}
        else{

        	if(sectionId == '' || sectionId == ' ' || sectionId == undefined)
 			 {
 	    		
 	   		 alert("Select SECTION");
 	   		 return false;
 			 }
            }
		  	
  		 }	


  			function frmSection_FuntLocHierarchy_SuccessCallBack(result)
  			{   

  				//alert(" result :: "+Object.keys(result));
  				// compId,flid,machId,cellId,locnId,sbuId,sectId,pbuId
  				
  				var PBU = result.sectId;
  				//alert(PBU);
  				 //alert(" SBU :: "+SBU);
  				 //var keyId=jQuery("#txtSectKeyid").val();
  				 //alert(keyId);
  				 
  				//processAjaxCalls("DMT_recall.sect","keyId="+keyId, "frmSection_recallsuccessCallback","frmSection_recallerrorCallback");
  				processAjaxCalls("DMT_recall.sect","keyId="+result.sectId, "frmSection_recallsuccessCallback","frmSection_recallerrorCallback");
  		        
  		    }


  			 function frmSection_recallsuccessCallback(record)
  		   {
  		       //alert(" Successcallback :: "+record.sbumst.SbutActive);
  		       //alert(" Successcallback :: "+record.sbumst.SbutName);
  			  jQuery("#txtSectKeyid").val(record.dmtmst.SectKeyid);
  			   jQuery("#txtSectName").val(record.dmtmst.SectName);
  		  	   jQuery("#txtSectCode").val(record.dmtmst.SectCode);
  		  	   //jQuery("#txtSectFlid").val(record.dmtmst.SectFlid);
  		  		
  		   }

  		   function frmSection_recallerrorCallback(record)
  		   {

  		   }
  	  		   
  			
  			function frmSectioncmbSectKeyid_onLoadSuccess()
  			{
  				
  	  		}
  			function frmSectioncmbSectCompanyid_onLoadSuccess()
  			{
  				fillComboBox("frmSection","cmbSectFactoryid","factroyCombo.commonFilter" );
  	  		}
  			function frmSectioncmbSectFactoryid_onLoadSuccess()
  			{
  	  		}

  			function  frmSectioncmbSectCompanyid_onSelect(record)
  			{
  				jQuery("#cmbSectFactoryid").combobox('clear');
  				reloadCombo("frmSection","cmbSectFactoryid","pbuCombo.commonFilter?compId="+record.id);
  				
  	  		}
  			function  frmSectioncmbSectFactoryid_onSelect(record)
  			{
  	  			//alert("record.id" +record.id);
  				//reloadCombo("frmSection","cmbSectCompanyid","companyCombo.commonFilter?factId="+record.id);
  				fillfactoryHierarchy("pbuCombo.commonFilter",record.id,"cmbSectCompanyid");
  	  		}
  			
	  		function  frmSectioncmbSectKeyid_onSelect(record)
	  		{
	  	  		//alert(record.id);
	  			//processAjaxCalls("section_recall.sect","keyId="+record.id,"frmSection_recallsuccessCallback","frmSection_recallerrorCallback");
	  		}
	  		function frmSection_successsCallback(result)
				{
	  			//alert("frmFactory");
	  			
	  			jQuery("#savingdata").css('display','none');
	  			jQuery('#mstfrm_div').hide();
	  			
	  				/*jQuery("#cmbSectKeyid").combobox('clear');
					reloadCombo("frmSection","cmbSectKeyid","sectionCombo.commonFilter");*/
					
					//refreshForm();
		  			//navigateToPrevForm();
	  		   loadFunctionalLocation("SectionfunLocation", "functionalLoc.sect?&DMTMASTER=DMTMASTER&frmType=SBU", "frmSectionfunLocation", "frmSection"," ");
				refreshForm();
				}	
			function frmSection_deleteSuccessCallback()
			{
	  			//alert("frmFactory");
  				//jQuery("#cmbSectKeyid").combobox('clear');
				reloadCombo("frmSection","cmbSectKeyid","sectionCombo.commonFilter");
				var t=confirm("Record is refered, can not delete\! Do you want to make inactive? ");
				if(t)
				{			
					checkConfirm();
				}	
			}
			function checkConfirm()
			{
				jQuery('#hdnInactive').val("Inactive");				
				saveForm("frmSection","section_delete.sect","");
			}

								
		
		
	 	/*	function frmSection_recallsuccessCallback(result)
	 		 { 
	 	  		//alert("sucess ");
	 	  		jQuery("#cmbSectKeyid").combobox("setValue",result.section.SectKeyid);
	 	  		jQuery("#cmbSectCompanyid").combobox("setValue",result.section.SectCompanyid);
	 	  	    jQuery("#cmbSectFactoryid").combobox("setValue",result.section.SectFactoryid);
	 	  		jQuery("#txtSectName").val(result.section.SectName);
	 	  		jQuery("#txtSectCode").val(result.section.SectCode);
	 	  	
	 		 }*/
	 	
	  		 function frmSection_recallerrorCallback(result)
	  		 {
	  			//alert("Error in callback");
	  		 }

	  		
	  		/* function frmSection_deleteSuccessCallback(result)
	  		 {  
	  			alert(result.successData.msg);
	  		 }*/
</script>
	<form name="frmSection" id="frmSection" >
 <input type="hidden" id="hdnInactive" name="hdnInactive"></input>
          			
	<div style="margin-left:03%;margin-top:20px;">
<!--	<div class="easyui-paddingbfpx" style="height: 414px;padding-top: 10px;padding-left:250px">-->
	<div id="frmSectFuntKeyIds">
					<input type="hidden" id="factory" name="cmbSectFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbSectSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbSectCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbSectMachineid" value=""></input>
					<input type="hidden" id="company" name="cmbSectCompanyid" value=""></input>
					<input type="hidden" id="location" name="cmbSectLocationid" value=""></input>
                   <input type="hidden" id="pbu" name=cmbsectFactoryid  value="${requestScope.genTlSectionmst.sectFactoryid}"></input> 
					<input type="hidden" id="flid" name="cmbSectFlid" value="${requestScope.genTlSectionmst.sectFlid}"></input>   
		</div>

     <div  class="easyui-paddingbfpx" id="DmtfunLocation" style="width: 70%;margin-top:40px;"></div>

	           
			   <div  class="easyui-paddingbfpx" style="margin-top: 10px;"><label class="mandatory-lbl">Name</label> </div>                      
                 <div style="margin-top: 5px;"><input type="text" id="txtSectName" name="txtSectName" class="easyui-text" maxlength="100"  style="width:500px;" value="${requestScope.genTlSectionmst.sectName}"  "${requestScope.genTlSectionmstBean.disableSectName == true ? ' disabled':''}"/></div>
			 
			   
			   <div  class="easyui-paddingbfpx" style="margin-top: 10px;"> <label class="mandatory-lbl">Code</label> </div>                      
                <div style="margin-top: 5px;"> <input type="text" id="txtSectCode" name="txtSectCode" class="easyui-text" maxlength="12" style="width:90px;" value="${requestScope.genTlSectionmst.sectCode}"  "${requestScope.genTlSectionmstBean.disableSectCode == true ? ' disabled':''}"/></div>
                 <div  class="easyui-paddingbfpx" style="margin-top: 10px;">
  			
	    </div>
	    <div style="margin-left:80%;margin-top:-25%;width:110%;">

	<div id="savingdata" style="right:0;position:fixed;display:none;width:55%;"><img id="treeSearchLoading" src="images/FnLocn/SavingData.gif"/></div>
<!--		<div id="flTreeComponent" class="demo" style="width: 50%;"></div>-->
</div>
	    </div>
<!--	    </div>-->
	    <input type="hidden" id="txtsectadd" name="txtsectadd" value="${requestScope.sectadd}"/>
	    <input type="hidden" id="mode" value="${requestScope.genTlSectionmstBean.formMode}" />
	     <input id="txtSectKeyid" type='hidden' name="txtSectKeyid" value="${requestScope.genTlSectionmst.sectKeyid}" />
	    
	</form> 