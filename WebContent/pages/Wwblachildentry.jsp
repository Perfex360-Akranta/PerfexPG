<script>

	jQuery(document).ready(function(){

	     initialiseForm('frmwwblachildEntry');

		 var levelno=jQuery('#hdnlevelNo').val();
	     var OrderNo=jQuery('#hdnOrderNo').val();
	     var dispCode=jQuery('#hdndispCode').val();
	     var ParentId=jQuery('#hdnParentId').val();
	     var DtlId=jQuery('#hdnDtlid').val();
	     var Masterid=jQuery('#hdnMasterid').val();
	    // alert("Master id "+Masterid);
	 
	     jQuery( "#btnwwbledit" ).hide();
	     fillComboBox("frmwwblachildEntry","cmbWwbdVerification","wwblafillcombo.wwbla?type=verify");
		 fillComboBox("frmwwblachildEntry","cmbWwbdIslastfactor","wwblafillcombo.wwbla?type=factor");
		 fillComboBox("frmwwblachildEntry","cmbWwbdResponsibility","employee.commonFilter");
		 formatDateBox('dteWwbdTargetDate','dd-MMM-yyyy');
		// fillWithCurrentDate('dteWwbdTargetDate');
         jQuery('#btnAddassamelevel').hide();
		 readOnlyFields("txtWwbdCountermeasure");
		 readOnlyFields("cmbWwbdResponsibility");
		 readOnlyFields("txtWwbdReoccur");
		 readOnlyFields("dteWwbdTargetDate");
		
		 
		 setFieldValue("cmbWwbdIslastfactor","N");
		 setFieldValue("cmbWwbdVerification","G");
		
		 var Status=jQuery("#hdnStatus").val();
		
		
		 if(Status=="Completed"){
			
			 setFieldValue("cmbWwblStatus","C"); 
		 }
		
	     if(DtlId=='WWBLA001')  
		 {
			 disableField("frmwwblachildEntry","btnAddassamelevel");
			 disableField("frmwwblachildEntry","btnwwbledit"); 
			 disableField("frmwwblachildEntry","btnwwbldelete");
			 readOnlyFields("txtParent");
		 }
	     if(DtlId!='WWBLA001'){
			processAjaxCalls("Wwblachildentry_recall.wwbla","dtlId="+DtlId,"recallSuccess","");
	     }     
	     
		 jQuery("#btnAddasChild").click(function()
		 {
			 var verify=getFieldValue("cmbWwbdVerification");
			 var lastfcr=getFieldValue("cmbWwbdIslastfactor");
			 var phenomena=getFieldValue("txtWwbdPhenomenaFactor");
			 var typeval=jQuery('#hdntypeval').val();
			 var Skilltype=getFieldValue("cmbWwbdResponsibility");
			 var chldval=jQuery('#hdnchldval').val();
			 var countrmsr=getFieldValue("txtWwbdCountermeasure");
			 var Investigation=getFieldValue("cmbWwblStatus");
			 var actionplan =getFieldValue("txtWwbdReoccur");
			 var targetDate = getFieldValue("dteWwbdTargetDate");
			 if(phenomena.trim().length==0 && verify=="G" && countrmsr.trim().length==0){
				 popupCommonErrorMsg(" Enter RootCause ");
				 return false; 
			 }
			 if(verify.trim().length==0){
				 popupCommonErrorMsg(" Select Verification ");
				return false; 
			 }
			 if(lastfcr.trim().length==0){
				 popupCommonErrorMsg(" Select Last WHY ");
				 return false; 
			 }if(countrmsr.trim().length==0 && verify=="G" && lastfcr=="Y"){
				 popupCommonErrorMsg(" Enter Counter Measure ");
				 return false;
			 }
			 if(Skilltype.trim().length==0 && verify=="G" && lastfcr=="Y" ){
				 popupCommonErrorMsg("  Select Responsibility  ");
				 return false;
			 } 
			 if(actionplan.trim().length==0 && verify=="G" && lastfcr=="Y"){
			        popupCommonErrorMsg(" Enter Action Plan Point ");
			        return false;
			    }if(targetDate.trim().length==0 && verify=="G" && lastfcr=="Y"){
				        popupCommonErrorMsg(" Select Target Date ");
				        return false;
				    
			 }if(verify=="N" && chldval.trim().length>0){
				 popupCommonErrorMsg(" Child cannot be added as parent verification is not good ");
				 return false;
			 }if((verify=="N" && chldval.trim().length>0 && countrmsr.trim().length>0 && countrmsr.trim() != "-" )||(verify=="G" && chldval.trim().length>0 && countrmsr.trim().length> 0 && countrmsr.trim() != "-") ){ 
				// alert("chldval value  "+chldval);
				// alert("countrmsr   "+countrmsr);
				 popupCommonErrorMsg(" Child WHY cannot be added as counter measure or Root Cause has given ");
				 return false;
			 }
				 
			  /* saveForm('frmwwblachildEntry',"WWBLAChildEntry_save.wwbla?&levelno="+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&Investigation="+Investigation+"&ParentId="+ParentId+"&Masterid="+Masterid+"&DtlId="+DtlId+"&detlid=detlid");	//?&frstIdn=frstIdn */
					  
			  saveForm(
					    'frmwwblachildEntry',
					    "WWBLAChildEntry_save.wwbla?" +
					    "&levelno=" + encodeURIComponent(levelno) +
					    "&OrderNo=" + encodeURIComponent(OrderNo) +
					    "&dispCode=" + encodeURIComponent(dispCode) +
					    "&Investigation=" + encodeURIComponent(Investigation) +
					    "&ParentId=" + encodeURIComponent(ParentId) +
					    "&Masterid=" + encodeURIComponent(Masterid) +
					    "&DtlId=" + encodeURIComponent(DtlId) +
					    "&detlid=" + encodeURIComponent('detlid') +
					    "&Skilltype=" + encodeURIComponent(Skilltype)+
					    "&actionplan=" + encodeURIComponent(actionplan)+
					    "&targetDate=" + encodeURIComponent(targetDate) 
					);
			
		 });

		 jQuery("#btnAddassamelevel").click(function()
		 {
			 url = '&levelno='+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&ParentId="+ParentId+"&Masterid="+Masterid+"&detlid=Smelvl";

			 if(DtlId=='WWBLA001')  
			 {
				 url += "&level=level";	
			 }

			 saveForm('frmwwblachildEntry',"WWBLAChildEntry_save.wwbla?"+url);	//?&frstIdn=frstIdn

		 });

          jQuery("#btnwwbledit").click(function()
		 {
              var parenttxt=getFieldValue("txtParent");			 
		 	  enableFields("txtFismParent");
		 	  url = '&levelno='+levelno+"&OrderNo="+OrderNo+"&dispCode="+dispCode+"&ParentId="+ParentId+"&Masterid="+Masterid+"&DtlId="+DtlId+"&Editval=Editval"+"&parenttxt="+parenttxt;
        	  saveForm('frmwwblachildEntry',"WWBLAChildEntry_save.wwbla?"+url);
		 	 
		 });

          jQuery("#btnwwbldelete").click(function()
         {
        	  
       	  if(confirm("Do You Want To Delete All Child Record?") == true)
       	  {
           	 processAjaxCalls("WWBLAChildEntry_delete.wwbla",'&DtlId='+DtlId,'Remove_Sucesscallback','');
       	  }
        			 	
         });
          var Mode = jQuery("#hdnmode").val();
       //   alert("Mode value: " + Mode);

          if(Mode == "View"){
              // Increase timeout to 1000ms and add debugging
              setTimeout(function(){
                  console.log("Starting to disable fields...");
                  
                  // Disable all buttons
                  disableUIButton("btnAddasChild");  
                  disableUIButton("btnwwbldelete");
                  disableUIButton("btnAddassamelevel");
                  disableUIButton("btnwwbledit");
                  console.log("Buttons disabled");
                  
                  // Disable text inputs - try both methods
                  try {
                      jQuery("#txtParent").textbox('disable');
                  } catch(e) {
                      jQuery("#txtParent").attr('disabled', true);
                  }
                  jQuery("#txtWwbdPhenomenaFactor").attr('disabled', true);
                  jQuery("#txtWwbdCountermeasure").attr('disabled', true);
                  jQuery("#txtWwbdReoccur").attr('disabled', true);
                  console.log("Text fields disabled");
                  
                  // Disable comboboxes (EasyUI specific)
                  try {
                      jQuery("#cmbWwblStatus").combobox('disable');
                      jQuery("#cmbWwbdIslastfactor").combobox('disable');
                      jQuery("#cmbWwbdResponsibility").combobox('disable');
                      jQuery("#cmbWwbdVerification").combobox('disable');
                      console.log("Comboboxes disabled");
                  } catch(e) {
                      console.error("Error disabling comboboxes:", e);
                  }
                  
                  // Disable datebox - try both methods
                  try {
                      jQuery("#dteWwbdTargetDate").textbox('disable');
                  } catch(e) {
                      jQuery("#dteWwbdTargetDate").attr('disabled', true);
                  }
                  console.log("Date field disabled");
                  
                  //alert("All fields disabled in view mode");
              }, 0000); // Increased to 1000ms (1 second)
          }
		 
	});

	function recallSuccess(result){
		
		// alert(result+"Result");
		console.log("Full Result:", JSON.stringify(result, null, 2));
		
		/* setFieldValue("cmbWwbdVerification",result[0][0]);
		
 		setFieldValue("cmbWwbdIslastfactor",result[0][1]);
 		setFieldValue("txtWwbdCountermeasure",result[0][2]);
 		setFieldValue("cmbWwbdResponsibility",result[0][3]);
 		setFieldValue("txtWwbdPhenomenaFactor",result[0][4]);
 		setFieldValue("txtWwbdCountermeasure",result[0][5]);
 		setFieldValue("cmbWwbdResponsibility",result[0][6]);
 		setFieldValue("txtWwbdReoccur",result[0][7]);
 		setFieldValue("dteWwbdTargetDate",result[0][8]);
 		setFieldValue("cmbWwblStatus",result[0][9]);
 		 *//* setFieldValue("dteWwbdTargetDate",result[0][8]);
 		setFieldValue("cmbWwblStatus",result[0][9]); */
 		//enblecountertype();
 		
		setFieldValue("cmbWwbdVerification",result[0][0]);
 		setFieldValue("txtWwbdPhenomenaFactor",result[0][1]);
 		setFieldValue("cmbWwbdIslastfactor",result[0][2]);
 		setFieldValue("txtWwbdCountermeasure",result[0][3]);
 		setFieldValue("cmbWwbdResponsibility",result[0][4]);
 		
 		setFieldValue("txtWwbdReoccur",result[0][5]);
 		setFieldValue("cmbWwblStatus",result[0][6]);
 		setFieldValue("dteWwbdTargetDate",result[0][7]);
 		
 		
 		var cntrmsr=result[0][3];
 		var verify=result[0][0];
 		if(cntrmsr.trim().length>0 ||verify=="N"){
 			jQuery("#hdnchldval").val("childval");
 	 	}
 	 		
 		childvaltn();
 	}
	function frmwwblachildEntrycmbWwbdVerification_onSelect(record){
		enblecountertype(record.id,null);
	}
	function frmwwblachildEntrycmbWwbdIslastfactor_onSelect(record){
		enblecountertype(null,record.id);
	}

	function frmwwblachildEntry_successsCallback(result)
	{
		openChildNode(result.Parentid);
		clearField('txtFisdCause');
		var ParentText=jQuery('#txtFismParent').val().toUpperCase(); 
		renamingnodevalue(ParentText,result.Parentid);
	}

	function childvaltn(){
		var verify=getFieldValue("cmbWwbdVerification");
		var lastfcr=getFieldValue("cmbWwbdIslastfactor");
		var chldval=jQuery('#hdnchldval').val();
		
		var countrmsr=getFieldValue("txtWwbdCountermeasure");
		var chldval=jQuery('#hdnchldval').val();
		
		if((countrmsr.trim().length>0 && chldval.trim().length>0)||(verify=="N" && chldval.trim().length>0)||(verify=="G" && countrmsr.trim().length>0 && chldval.trim().length>0)){
			readOnlyFields("txtWwbdPhenomenaFactor");
			readOnlyFields("txtWwbdReoccur");
			readOnlyFields("dteWwbdTargetDate");
			jQuery('#Countrmsr').removeClass("mandatory-lbl");
			jQuery('#yyphebomena').removeClass("mandatory-lbl");
			jQuery('#ActionPlan').removeClass("mandatory-lbl");
		}/*if(verify=="N" && chldval.trim().length>0){

	    }if(verify=="G" && countrmsr.trim().length>0 && chldval.trim().length>0){

		}*/
			
	}
	function enblecountertype(verify,lastfcr){
	     verify = verify != null ? verify : getFieldValue("cmbWwbdVerification");
	     lastfcr = lastfcr != null ? lastfcr : getFieldValue("cmbWwbdIslastfactor");
	    var chldval = jQuery('#hdnchldval').val();
	    var countrmsr = getFieldValue("txtWwbdCountermeasure");

	    // Log all retrieved values
	    console.log("=== enblecountertype() called ===");
	    console.log("verify:", verify);
	    console.log("lastfcr:", lastfcr);
	    console.log("chldval:", chldval);
	    console.log("countrmsr:", countrmsr);

	    if(verify == "G" && lastfcr == "Y"){
	        console.log("Condition: verify='G' AND lastfcr='Y' - ENABLING fields");
	        enableFields("txtWwbdCountermeasure");
	        enableFields("cmbWwbdResponsibility");
	        enableFields("txtWwbdReoccur");
	        enableFields("dteWwbdTargetDate");
	        jQuery('#Skilltype').addClass("mandatory-lbl");
	        jQuery('#Countrmsr').addClass("mandatory-lbl");
	        jQuery('#ActionPlan').addClass("mandatory-lbl");
	        jQuery('#hdntypeval').val("mandtry");
	        console.log("Fields enabled and mandatory labels added");
	    } else if((verify == "G" && lastfcr == "N") || (verify == "N" && lastfcr == "Y")){
	        console.log("Condition: (verify='G' AND lastfcr='N') OR (verify='N' AND lastfcr='Y') - Making fields READ-ONLY");
	        readOnlyFields("txtWwbdCountermeasure");
	        readOnlyFields("txtWwbdReoccur");
	        readOnlyFields("dteWwbdTargetDate");
	        readOnlyFields("cmbWwbdResponsibility");
	        jQuery('#Skilltype').removeClass("mandatory-lbl");
	        jQuery('#Countrmsr').removeClass("mandatory-lbl");
	        jQuery('#ActionPlan').removeClass("mandatory-lbl");
	        console.log("Fields set to read-only and mandatory labels removed");
	    } else {
	        console.log("No condition met - verify:", verify, "lastfcr:", lastfcr);
	    }
	    
	    console.log("=== enblecountertype() completed ===\n");
	}
		/*if(verify=="N"){
			setFieldValue("cmbWwbdIslastfactor","N");
        	readOnlyFields("cmbWwbdIslastfactor");
        	readOnlyFields("txtWwbdCountermeasure");
			readOnlyFields("cmbWwbdSkilltype");
			jQuery('#Skilltype').removeClass("mandatory-lbl");
			jQuery('#Countrmsr').removeClass("mandatory-lbl");
		}
		else*/ /*else if(verify=="N"){
        	setFieldValue("cmbWwbdIslastfactor","N");
        	readOnlyFields("cmbWwbdIslastfactor");
        	setFieldValue("txtWwbdPhenomenaFactor"," ");
 		    readOnlyFields("txtWwbdPhenomenaFactor");
 		    readOnlyFields("txtWwbdCountermeasure");
 		    readOnlyFields("cmbWwbdSkilltype");
 		    jQuery('#yyphebomena').removeClass("mandatory-lbl");
  	    }*/
  	    /*else if(verify=="N" && lastfcr=="Y"){
    	   readOnlyFields("txtWwbdCountermeasure");
  		   readOnlyFields("cmbWwbdSkilltype");
  		   jQuery('#Skilltype').removeClass("mandatory-lbl");
  		   jQuery('#hdntypeval').val(" ");
  	    }else if(verify=="G" && lastfcr=="N"){
    	   readOnlyFields("txtWwbdCountermeasure");
  		   readOnlyFields("cmbWwbdSkilltype");
  		   jQuery('#Skilltype').removeClass("mandatory-lbl");
  		   jQuery('#hdntypeval').val(" ");
  	   }else if(verify=="N" && lastfcr=="N"){
    	   jQuery('#hdntypeval').val(" ");
  	    }if(verify=="N" && chldval.trim().length>0 || countrmsr.trim().length>0){
  		   setFieldValue("txtWwbdPhenomenaFactor"," ");
		   readOnlyFields("txtWwbdPhenomenaFactor");
		   jQuery('#yyphebomena').removeClass("mandatory-lbl");
  	   }if(verify=="G" && countrmsr.trim().length==0){
  		   enableFields("txtWwbdPhenomenaFactor");
		   jQuery('#yyphebomena').addClass("mandatory-lbl");
  	   }*/
    
    
	function Remove_Sucesscallback(result){

      alert(result.successData.msg);
      jQuery("#wwblaTreeComponent").jstree("refresh");
	     
	}
	/*function frmpcsEnableDisable_successsCallback(result)
	{
		if(result!=null){
		jQuery('#pcsEbleDbleGrid').clearGridData();
		jQuery('#cmbpelcCellid').combobox('clear');*/

	
	function frmwwblachildEntry_successsCallback(result)
	{
		openChildNode(result.Parentid);
		clearField('txtWwbdPhenomenaFactor');
		var ParentText=jQuery('#txtParent').val().toUpperCase(); 
		renamingnodevalue(ParentText,result.Parentid);
	}

	function renamingnodevalue(ParentText,id){
		
	    jQuery("#"+id).children("a").html(ParentText);
	    jQuery("#"+id).attr("displaycode",ParentText);

	}
	function openChildNode(parentId){
		 if(jQuery("#wwblaTreeComponent").jstree("is_open",  jQuery('#'+parentId)) == false)
		  {
			   var isLeaf = jQuery("#wwblaTreeComponent").jstree("is_leaf",  jQuery('#'+parentId));
				
				if(isLeaf == true)
				{
					jQuery("#wwblaTreeComponent").jstree("load_node",  jQuery('#'+parentId));
					setTimeout(function() {jQuery("#wwblaTreeComponent").jstree("open_node",  jQuery('#'+parentId));},1250);
				}
				else
		  			jQuery("#wwblaTreeComponent").jstree("open_node",  jQuery('#'+parentId));
		  }
		  else
		  	refreshNode("wwblaTreeComponent",parentId);
	}
	
	/* var Mode=jQuery("#hdnmode").val();

	if(Mode=="View"){
		
		disableUIButton("btnAddasChild");  
		disableUIButton("btnwwbldelete");
		disableField("frmwwblachildEntry","cmbWwbdIslastfactor");
		jQuery("#txtWwbdPhenomenaFactor").attr("disabled",true);
		jQuery("#txtWwbdPhenomenaFactor").attr("disabled",true);
		jQuery("#txtWwbdPhenomenaFactor").attr("disabled",true);
	}
	 */
</script>

	<form id="frmwwblachildEntry">
	    <table style="margin-top:4px;margin-left:10px;">
	    <tr>
		    <td>
				<div style="margin-left:%;">
					<div>
				    	<label>Parent</label>
					</div>
					<div>
					    <input class="easyui-text" id="txtParent" name="txtParent" style="width : 230px;text-transform:uppercase;"  value="${requestScope.dispCode}"></input>
					</div>
				</div>
			</td>
			   <td>
               	<div id="Status"  style="margin-left:-125px;;">
			<label style="color:green;" ><b>Investigation Status</b></label>
			<div>
		<select id="cmbWwblStatus" class="easyui-combobox" name="cmbWwblStatus"   style="width:120px; ">  		
				
				<option value='W'><b>Pending</b></option>
				<option value='C'><b>Completed</b></option>
				
				<!-- <option value='W'><b>Work In Progress</b></option> -->
		    <!-- <option value='C'><b>Completed</option> -->
				
			</select>
		    </div>
		    </div>
        
        
        </td>
		</tr>
		<tr>
			<td>
     			<div style="margin-left:%;">
					<div>
					    <label class="mandatory-lbl" id="yyphebomena">Why Why Factor for Phenomena</label>
					    <span style= "margin-left:80px; display:none;"><label class="mandatory-lbl">Verification</label></span>
						<span style= "margin-left:80px;"><label class="mandatory-lbl">Last WHY-WHY</label></span>
					</div>
					</div>
							<tr>
			<td>
				<div>
					<table>
						<tr>
							<td>	
								<textarea  id="txtWwbdPhenomenaFactor" name="txtWwbdPhenomenaFactor" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600"></textarea>
							</td>	
							<td valign="top">
								
								<span style="padding-left: 4px;"><input class="easyui-combobox" id="cmbWwbdIslastfactor" name="cmbWwbdIslastfactor" style="width:120px;"  value="" /></span>
							</td>
						</tr>
					</table>
				</div>
			    <div>
	                <label id="Countrmsr">RootCause</label>
	            </div>
	            <div>
	                 <textarea id="txtWwbdCountermeasure" name="txtWwbdCountermeasure" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600"></textarea>
	            </div>
	        
               <div>
              	<label id="Skilltype" >Responsibility</label>
               </div>			
               <div>
               	<input class="easyui-combobox" id="cmbWwbdResponsibility" name="cmbWwbdResponsibility" style="width:262px;"  value="" />
               </div>
               <%-- <div style="margin-left:30px;">
   <div><label class="mandatory-lbl">Date</label></div>
								<div>
									<span style="float:left;padding-right:0px;">					
								 		<input class="easyui-text" style=" width : 120px;" id="dteHzomDate" name="dteHzomDate" 
								 													value="${requestScope.hazopMst.hzomDate}"/>
							      	</span>
								</div></div>  --%>
               
               <div style="margin-left:270px;margin-top:-38px;">
               <div><label class="mandatory-lbl">Target Date</label></div>
               <div>
									<span style="float:left;padding-right:0px;">					
<input class="easyui-text" style=" width : 120px;" id="dteWwbdTargetDate" name="dteWwbdTargetDate" value=""/>
							      	</span>
								</div>
               </div>
               
               <div style="margin-top:30px;margin-left:-30px;">
	              <span><input type="button" id="btnAddasChild" name="btnAddasChild" class="easyui-button" style="width:110px;height:22px;margin-left:30px;" value="Add as Child" /></span>
				  <span style= "margin-left:5px;"><input type="button" id="btnAddassamelevel" name="btnAddassamelevel" class="easyui-button" style="width:130px;height:22px;" value="Add as Same Level" /></span>
				  <span style= "margin-left:5px;"><input type="button" id="btnwwbledit" name="btnwwbledit" class="easyui-button" style="width:80px;height:22px;" value="Edit" /></span>
				  <span style= "margin-left:5px;"><input type="button" id="btnwwbldelete" name="btnwwbldelete" class="easyui-button" style="width:110px;height:22px;" value="Delete" />
		      </span> </div>
			</td>
		</tr>
	</table>
	<table style="margin-left:70px;display:none;">
	<tr>
				<td>
				   <div style="margin-left:10%;padding-top:20px;" >
				  	<input type="button" class="easyui-button" id="btnaddaschild" name="btnaddaschild" value="Add WHY-WHY" style="height:23px"/>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btnAddassamelevel" name="btnAddassamelevel" value="Add As Same Level" style="height:23px"/>
				  	</span>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btnwwbledit" name="btnwwbledit" value="Edit" style="height:23px"/>
				  	</span>
				  	<span style="padding-left:10px;">
				  	<input type="button" class="easyui-button" id="btnwwbldelete" name="btnwwbldelete" value="Delete" style="height:23px"/>
				  	</span>
				  	</div>
				</td>
				
			</tr>
	</table>
	
	     <div style="margin-top:-140px;margin-left:280px;">
	                <label id="ActionPlan">Action Plan Point</label>
	           </div>
	           <div style="margin-left:280px;margin-top:9px;">
	                 <textarea id="txtWwbdReoccur" name="txtWwbdReoccur" style="resize:none;width:265px;text-transform:uppercase;" maxlength="600"></textarea>
	            </div>
	            
	            <div class="Verify" style="display:none"><input class="easyui-combobox" id="cmbWwbdVerification" name="cmbWwbdVerification" style="width:0px;"  value="" /></div>
	
	<input type="hidden" id="hdnlevelNo" name="hdnlevelNo" value="${requestScope.levelNo}"/> 
	<input type="hidden" id="hdnOrderNo" name="hdnOrderNo" value="${requestScope.OrderNo}"/> 
	<input type="hidden" id="hdndispCode" name="hdndispCode" value="${requestScope.dispCode}"/> 
	<input type="hidden" id="hdnParentId" name="hdnParentId" value="${requestScope.ParentId}"/> 
	<input type="hidden" id="hdnMasterid" name="hdnMasterid" value="${requestScope.Masterid}"/> 
	<input type="hidden" id="hdnDtlid" name="hdnDtlid" value="${requestScope.DtlId}"/> 
	<input type="hidden" id="hdntypeval" name="hdntypeval" value="${requestScope.DtlId}"/>
	<input type="hidden" id="hdnchldval" name="hdnchldval" value=""/>
	<input type="hidden" id="hdnmode" name="hdnmode"  value="${requestScope.mode}"/>
		<input type="hidden" id="hdnStatus" name="hdnStatus"  value="${requestScope.Status}"/>
		
	</form>