<script type="text/javascript">
		jQuery(document).ready(function()
			{
			
			// ========== ROLE-BASED ACCESS CONTROL ==========
		   /*  var roleid = jQuery("#hdnroleId").val();
		    var roleName = jQuery("#hdnroleName").val();		    		   
		    
		    // Trim values to remove any whitespace
		    if(roleid) roleid = roleid.trim();
		    if(roleName) roleName = roleName.trim();
		    
		    // Check if user has QM PILLAR MEMBER role
		    if(roleName !== "QM PILLAR MEMBER" || roleid !== "AROL0055"){		        
		        // Hide all form content
		        jQuery('#frmUPS').hide();
		        
		        // Show error message
		        if(typeof popupCommonErrorMsg === 'function') {
		           // popupCommonErrorMsg("Access Denied: Please select the QM PILLAR MEMBER role to access Upstream Defect form");
		            popupCommonErrorMsg("Please select the QM PILLAR MEMBER ");
		        } else {
		            alert("Please select the QM PILLAR MEMBER ");
		        }
		        
		        // Redirect back after showing message
		         setTimeout(function() {
		            navigateToPrevForm();
		        }, 3000); 
		        
		        return false;
		    } */
		    		   
		    // ========== END ROLE-BASED ACCESS CONTROL ==========
		    	
		    	var roleId = jQuery("#hdnUserRole").val();
		    			if(roleId != 'AROL0055') {
		    			    jQuery('#frmUPS').hide();
		    			    if(typeof popupCommonErrorMsg === 'function') {
		    			        popupCommonErrorMsg("Please select the QM PILLAR MEMBER");
		    			    } else {
		    			        alert("Please select the QM PILLAR MEMBER");
		    			    }
		    			    setTimeout(function() {
		    			        navigateToPrevForm();
		    			    }, 5000);
		    			    return false;
		    			}
		    			
		    	
            initialiseForm('frmUPS');
			jQuery('#submitForm').val('frmUPS');
			formatDateBox('dteUpsmDate','dd-MMM-yyyy');   
			
			//fillComboBox("frmUPS", "cmbUpsdArea", "employee.commonFilter");
			fillComboBox("frmUPS", "cmbUpsdDefect", "Upstream_DefectForm.upd");
			fillComboBox("frmUPS", "cmbRawMaterial", "rawMaterial.commonFilter");
			fillComboBox("frmUPS", "cmbdefect", "");
			//processGridnew("UpstreamDefectForm_input.upd","q=2","UpstremGrid","pager","","docDoubleClick");
			
			 
		    var sectionId = jQuery("#frmUPS input[id='section']").val();
		    var cellId = jQuery("#frmUPS input[id='cell']").val();
		    
			var machId = jQuery("#frmUPS input[id='machine']").val();
            var flid = jQuery("#frmUPS input[id='flid']").val(); 
            //alert(" In Jsp ::   "+flid);
        
		    var dataStr = " &sectionId=" + sectionId
							+ "&cellId=" + cellId + "&machId="
							+ machId+"&flid="+ flid;

			loadFunctionalLocation("UpStrmDftsfunLocation", "functionalLoc.mom", "UpStrmDftsfunLocation", "frmUPS",dataStr);

			fileManagerPopUp("","UPS","frmUPS","btnfilemgr","UPSFilemgr");
			
			 var url = jQuery('#hiddenUrl').val();
			 //alert(url);
			 var tableCaption = "UpStream Defect";
			 viewGrid(url,"?q=2",tableCaption);


			
             var KeyId=jQuery('#txtUpsmKeyid').val();
             

			 //alert(" FnlnId :: "+jQuery('#hdnfnlnid').val());
		     //alert(" New :: "+jQuery('#hdnnewFrm').val());
				
              var NewFrm=jQuery('#hdnnewFrm').val();
              //alert(" NewFrm :: "+NewFrm.trim().length);
			  if(NewFrm.trim().length>0){

					var date=getFieldValue("dteUpsmDate");
	                var PreparedDate  = date.substring(0, 12);
				    setFieldValue("dteUpsmDate",PreparedDate,"frmUPS");

				}
				else{
					
					fillWithCurrentDate('dteUpsmDate');
					
				}

			  
			      jQuery("#btnInsert").click(function()
				 {
                   
			    	  jQuery("#hdninsrt").val("insert");
					  saveForm('frmUPS','UpstreamDefectForm_save.upd?&type=type');
				 
				 });

					  
					  
					  
					  jQuery("#btnDelete").click(function() {
		         
						  //var Mstkeyid = jQuery("#txtUpsmKeyid").val();
						  var dtlKeyid=jQuery('#txtUpsdKeyid').val();
						  //alert(Mstkeyid+" Mstkeyid :: "+dtlKeyid +" dtlKeyid :: ");
					        
					        if (dtlKeyid != null && dtlKeyid != 'undefined' && dtlKeyid != undefined && dtlKeyid != "" && dtlKeyid.trim().length>0) {
								    var r = confirm("Do You Want To Delete?");
									if (r == true) {
										processAjaxCalls("UpstreamDefectForm_remove.upd", "dtlKeyid="+ dtlKeyid, 'remove_successCallBack','remove_errorCallBack');
									} else{
										return false;
									} 
					          	}else{
					                   jQuery("#UpstremGrid").trigger("reloadGrid");
								}

						 });

					  


			       jQuery("#btnActionPlan").click(function(){
					 var Keyid=jQuery("#txtUpsmKeyid").val();
					 var dtlKeyid=jQuery('#txtUpsdKeyid').val();
					 //alert(" dtlKeyid :: "+dtlKeyid);
					 if(Keyid.trim().length>0)
				    { 
						 var flid=jQuery("#frmUPS input[id='flid']").val();
						 var mainTask= getFieldValue("txtUpsmTitle");//escape(mainTask)
						 openActionPlan("UsdActionPlan",Keyid,"UPD",flid,mainTask,Keyid);
				    }
					 else
				    {
						 saveForm('frmUPS','UpstreamDefectForm_save.upd?upsactnpln=upsactnpln');
				    }		 

				});	
			  
			  
			  
			
			});
		
		
		function dteUpsmDate_onSelect(record) {
			completedDateEvent();
		} 


		function completedDateEvent(){
			
			var currentDate = getServerDateTime();
			var dteUpsmDate=jQuery('#dteUpsmDate').datebox("getValue");
			if(convertStringToDate(dteUpsmDate )> currentDate)
			{
				alert('Upstream Date should not exceed current date');
				fillWithCurrentDate('dteUpsmDate');
			}	
		}

		
		function btnfilemgr_click()
		{//12121211211
	      	var keyid = jQuery('#txtUpsmKeyid').val();    
			if(keyid != null && keyid != ''){
			   fileManagerPopUp(keyid,"UPS","","","");
			}
			else
			{
				saveForm('frmUPS','UpstreamDefectForm_save.upd?filemanger=filemanger');
			}
			
		}


		
		function frmUPS_successsCallback(result)
		{
			 var Keyid=result.successData.keyId;
			    var filemanger =result.filemanger;
			    var upsactnpln =result.upsactnpln;
			    var Type=result.type;
			    //alert("Type"+Type);
			   
			    //alert(Keyid+" :: filemanger :: "+filemanger);
			    jQuery("#txtUpsmKeyid").val(Keyid);
			    

			    if(Type!="type"){
			    setTimeout(function(){
				 //alert("Chk navi");    
				 navigateToPrevForm();
				 },10000);
			    }
			   
			
			    
			    if(filemanger==true){
		        	if(Keyid.trim().length>0){
			   			fileManagerPopUp(Keyid,"UPS","","","");
		   			 }
		   		}

			   
					
				
				
			    if(upsactnpln==true || upsactnpln=='true'){
					 //alert(" 1234 ");
		        	if(Keyid.trim().length>0){
					 var flid=jQuery("#flid").val();
					 var mainTask = jQuery("#txtUpsmTitle").val();
					 openActionPlan("Actionplane",Keyid,"UPD",flid,mainTask,Keyid);
		    		}
			    }
			    
			    //alert(Keyid);
			    viewGrid("UpstreamDefectForm_input.upd","","");
				//processGridnew("UpstreamDefectForm_input.upd?&q=2&Keyid="+Keyid,"","UpstremGrid","pager",tableCaption,"docDoubleClick");
				//jQuery('#UpstremGrid').trigger("reloadGrid");
				setFieldValue("txtUpsdRawmaterial"," ","frmUPS");
				jQuery('#txtUpsdKeyid').val("");
				jQuery('#txtUpsdCorrectionaction').val("");
				jQuery('#txtUpsdPreventiveaction').val("");
				jQuery('#txtUpsdInformto').val("");
				jQuery("#cmbUpsdDefect").combobox("setValue"," ");
				//jQuery("#txtUpsmKeyid").val(" ");
				
			    //jQuery("#txtUpsmKeyid").val(Keyid);
				
				//alert(2);
		}
		
		function frmUPS_deleteSuccessCallback(result)
		{
			//alert(1);
			alert(result.successData.msg);
			navigateToPrevForm();
		}
		
		function remove_successCallBack(result){
			
		    alert(result.successData.msg);
			jQuery('#UpstremGrid').trigger("reloadGrid");
			
			//setFieldValue("txtUpsmArea"," ","frmUPS");
			//setFieldValue("txtUpsmInspectionlotno"," ","frmUPS");
			//setFieldValue("dteUpsmDate"," ","frmUPS");
			setFieldValue("txtUpsdRawmaterial"," ","frmUPS");
			jQuery("#cmbUpsdDefect").combobox("setValue"," ");
			jQuery('#txtUpsdCorrectionaction').val("");
			jQuery('#txtUpsdPreventiveaction').val("");
			jQuery('#txtUpsdInformto').val("");
			jQuery('#txtUpsdKeyid').val("");
			//jQuery('#txtUpsmTitle').val("");
			//jQuery('#txtUpsmRemakrs').val("");
			
			
		}

		  
		function viewGrid(url,dataString,tableCaption)
		  {
			 // var flId=jQuery('#flid').val();
			  var flId = jQuery("#frmUPS input[id='flid']").val(); 
			  var Date=jQuery('#dteUpsmDate').val();
			  var Mstkeyid = jQuery("#txtUpsmKeyid").val();
			  //alert(Mstkeyid);
			  dataString+="&keyId="+Mstkeyid;
			  //alert(" dataString "+dataString);
			  processGridnew("UpstreamDefectForm_input.upd?&q=2&flId="+flId+"&Date="+Date,dataString,"UpstremGrid","pager",tableCaption,"docDoubleClick");
			  //processGridnew("UpstreamDefectForm_input.upd?&q=2&keyId="+Mstkeyid,"UpstremGrid","pager",tableCaption,"docDoubleClick");
		  }		
					
	
		function docDoubleClick(id)
		{	
			var rowData = jQuery("#UpstremGrid").jqGrid('getRowData',id);
			var KEYID=rowData.KEYID;
            jQuery('#txtUpsdKeyid').val(KEYID);
			processAjaxCalls("UpstreamDefectForm_recall.upd?&KEYID="+KEYID,"","recallsuccessCallBack","errorCallBack");
		}
		
		function recallsuccessCallBack(result){  

			//alert(" "+result[0][3]);
			setFieldValue('txtUpsdRawmaterial',result[0][0]);
			setFieldValue('cmbUpsdDefect',result[0][1]);
			setFieldValue('txtUpsdInformto',result[0][2]);
			setFieldValue('txtUpsdCorrectionaction',result[0][3]);
			setFieldValue('txtUpsdPreventiveaction',result[0][4]);
			setFieldValue('txtUpsdKeyid',result[0][6]);
			var Date = result[0][5];
			var PreparedDate  = Date.substring(0, 12);
			setFieldValue('dteUpsmDate',PreparedDate);

	
		}
		
		function toggleUpstreamDefect(checkbox) {
		    var flid      = jQuery("#frmUPS input[id='flid']").val();
		    var sectionId = jQuery("#frmUPS input[id='section']").val();
		    var defectMode = jQuery(checkbox).is(':checked') ? "OTHERS" : "";

		    clearField('cmbUpsdDefect');
		    reloadCombo("frmUPS", "cmbUpsdDefect",
		        "Upstream_DefectForm.upd?flid=" + flid
		        + "&sectionId=" + sectionId
		        + "&defectMode=" + defectMode);
		}
function frmUPS_FuntLocHierarchy_SuccessCallBack(keyIds) {
		    
		    console.log("keyIds full object: ", JSON.stringify(keyIds));
		    console.log("keyIds.sectId: " + keyIds.sectId);
		    console.log("keyIds.flId: " + keyIds.flId);

		   
		    var sectionId = keyIds.sectId;
		    var flid      = keyIds.flId;

		    
		    jQuery("#chkUpsdDefectOther").prop("checked", false);

		    reloadCombo("frmUPS", "cmbUpsdDefect",
		        "Upstream_DefectForm.upd?flid=" + flid
		        + "&sectionId=" + sectionId);
		}
		

		
</script>

<form id="frmUPS" name="frmUPS">

<div id="wrapper">

<table>
        <tr>
        <td colspan="2">
        <div id="frmUPSFuntKeyIds">
					<input type="hidden" id="factory" name="cmbUpsdFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbSUpsdectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbUpsdCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbUpsdMachineid" value=""></input>
					<input type="hidden" id="flid"    name="cmbUpsmFlid" value="${requestScope.newGenTlUpstreamdefectMst.upsmFlid}"></input>        
		</div>

     <div  class="easyui-paddingbfpx" id="UpStrmDftsfunLocation" style="width: 104%;margin-top:-12px;width:108%\9;"></div>
     </td>
     <td style="padding-left:15px;margin-top:10px;">
 <div>
		<span id="UPSFilemgr" >
		
		</span>
		</div>
		

  	</td>
  	<td>
     <div class="easyui-paddingbfpx" style="margin-top: 4px; padding-left:10px;">
         
	      <input id="btnActionPlan" class="easyui-button" type="button"  style="height:20px;" name="btnActionPlan" value=" Action Plan "/>
	
	</div>
  	</td>
     
</tr>
</table>
<table>
	<tr>
		<td valign="top">
		   <div style="width:230px;">
			<div class="easyui-paddingbfpx">
				<label >Area</label>
			</div>
	
			<div class="easyui-paddingbfpx">
				<input class="easyui-text" id="txtUpsmArea" name="txtUpsmArea" style="width: 220px; height: 21px;text-transform: uppercase;" value="${requestScope.newGenTlUpstreamdefectMst.upsmArea}"/>
			</div>
						
			</div>		
			
			<div class="easyui-paddingbfpx">
			<label >Inspection Lot No. </label>
			</div>
			<div class="easyui-paddingbfpx">
		
				<input id="txtUpsmInspectionlotno" name="txtUpsmInspectionlotno" type="text" class="easyui-text"  maxlength="95"  style="width: 220px; height: 21px;text-transform: uppercase;" value="${requestScope.newGenTlUpstreamdefectMst.upsmInspectionlotno}" />
			</div>
					
         </td>

<td style="padding-left: 0px;" valign="top">
    <div class="easyui-paddingbfpx">
			<label class="mandatory-lbl">Title </label>
	</div>
	<div class="easyui-paddingbfpx">
                                 
       <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="2" style=" width : 220px; height : 65px;" id="txtUpsmTitle" name="txtUpsmTitle" >${requestScope.newGenTlUpstreamdefectMst.upsmTitle}</textarea>                                                                                                      
	
    </div>

	
</td>

<td colspan="2" style="padding-left: 15px;" valign="top">

	<div class="easyui-paddingbfpx">
		<label>Remarks</label>
	</div>
	<div class="easyui-paddingbfpx">
		<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style=" width : 460px; height : 65px;" id="txtUpsmRemakrs" name="txtUpsmRemakrs" >${requestScope.newGenTlUpstreamdefectMst.upsmRemakrs}</textarea>
	</div>
   
</td>		

					



<td style="padding-left: 15px;" valign="top">

	<div class="easyui-paddingbfpx">
			<label class="mandatory-lbl">Date </label>
	</div>
	<div class="easyui-paddingbfpx">
                                                                                                                                        
	<input id="dteUpsmDate"  name="dteUpsmDate" clear="false" class="easyui-datebox" style="width:120px;height:21px;" value="${requestScope.newGenTlUpstreamdefectMst.upsmDate}"  />
	
	</div>


</td>		
	
	
</tr>
<tr>
</table>

<table width="100%"
       cellpadding="8"
       style="
           border-collapse:separate;
           border-spacing:10px;
           border:1px solid #5B9BD5;         
           border-radius:12px;
       ">    
<td>
      <div style="width:230px;">
       <div class="easyui-paddingbfpx">
		   <label class="mandatory-lbl">Raw Material </label>
		</div>
		<div class="easyui-paddingbfpx">
	       <input class="easyui-text" id="txtUpsdRawmaterial" name="txtUpsdRawmaterial" style="width: 220px; height: 21px;text-transform: uppercase;" value=""/>
	       
		</div>
		
		<!-- <div>
		    <span style="padding-top:-20px;"><label class="mandatory-lbl" >Defect</label></span>
		</div>
 -->		
 
 <div>
    <span style="padding-top:-20px;">
        <label class="mandatory-lbl">Defect</label>
        <span style="margin-left:10px;">
            <input type="checkbox" id="chkUpsdDefectOther" 
                   name="chkUpsdDefectOther" value="N" 
                   onchange="toggleUpstreamDefect(this)"/>
            <label>Others</label>
        </span>
    </span>
</div>
	    <div>
		    <input class="easyui-combobox" id="cmbUpsdDefect" name="cmbUpsdDefect" style="width: 220px; /* height: 21px; */" value=""/>
		</div>
		
		<div class="easyui-paddingbfpx">
		
	</div>
	<div class="easyui-paddingbfpx">

		
	</div>
		
								</div>
</td>
<td >
 <div class="easyui-paddingbfpx">
							<label class="mandatory-lbl" >Corrective Action</label>
							</div>
								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="2" style=" width : 220px; height : 65px;" id="txtUpsdCorrectionaction" name="txtUpsdCorrectionaction" ></textarea>
								</div>

 
</td>
<td style="padding-left:17px; ">
<div class="easyui-paddingbfpx">
							<label >Preventive Action</label>
							</div>
								<div class="easyui-paddingbfpx">
									<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="1" cols="34" style=" width :220px; height : 65px;" id="txtUpsdPreventiveaction" name="txtUpsdPreventiveaction" ></textarea>
								</div>


</td>

<td style="padding-left:17px; ">
	<div class="easyui-paddingbfpx">
			<label class="mandatory-lbl">Inform To</label>
	</div>
	<div class="easyui-paddingbfpx">
		<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="1" cols="34" style=" width :220px; height : 65px;" id="txtUpsdInformto" name="txtUpsdInformto" ></textarea>
	</div>
</td>

<td style="padding-left:17px;padding-top:40px;">
	<input type="button" class="easyui-button" value ="Insert" id="btnInsert" style="height:23px"/>
    
    <span style="padding-left:8px;">
        <input type="button" class="easyui-button" id="btnDelete" value="Delete" style="height:23px;"/>
    </span>
       
</td>
</tr>

<tr>

	<!-- <div style="float:left;"> -->
        <td colspan="4" style="padding:10px;">
            <table id="UpstremGrid" width="100%"></table>
            <div id="pager"></div>
        </td>
    </tr>
</table>



		
		

	  
	 
</div>

	 <input type="hidden" id="mode" name="mode" />   
	 <input type="hidden" id="txtUpsmKeyid" name="txtUpsmKeyid" value="${requestScope.newGenTlUpstreamdefectMst.upsmKeyid}"/>
	 <input type="hidden" id="txtUpsdKeyid" name="txtUpsdKeyid" value=""/>
	 <input type="hidden" id="hdnfnlnid" name="hdnfnlnid" value="${requestScope.FnlnId}"/>
	 <input type="hidden" id="hdnnewFrm" name="hdnnewFrm" value="${requestScope.New}"/>
	 
	 <input type="hidden" id='hdnroleName' value="${requestScope.rolename}"/>
    <input type="hidden" id='hdnroleId' value="${requestScope.rolekeyid}"/>

</form>
