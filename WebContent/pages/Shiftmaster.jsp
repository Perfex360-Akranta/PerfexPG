
  <script type="text/javascript">	
  			jQuery(document).ready(function(){	
  			initialiseForm('frmShift');	
  			jQuery('#submitForm').val('frmShift'); // set the id of form to submit
  		/*	
  			fillComboBox("frmShift","cmbSftmKeyid","shift.commonFilter" );
  			fillComboBox("frmShift","cmbSftmCompanyid","companyCombo.commonFilter" );
  			fillComboBox("frmShift","cmbSftmFactoryid","factroyCombo.commonFilter" );
  			fillComboBox("frmShift","cmbSftmSectionid","sectionCombo.commonFilter" );
  			fillComboBox("frmShift","cmbSftmCellid","cellCombo.commonFilter" );
  	
  			fillComboBox("frmShift","cmbSftmShiftorder","combo_Shiftorder.sftm" );
  	*/	
  	   
  		    var factId = jQuery("#frmShift input[id='factory']").val();
		    var sectionId = jQuery("#frmShift input[id='section']").val();
		    var cellId = jQuery("#frmShift input[id='cell']").val();
		    var machId = jQuery("#frmShift input[id='machine']").val();
            var compId = jQuery("#frmShift input[id='company']").val(); 
            var locId = jQuery("#frmShift input[id='location']").val(); 
			var flid = jQuery("#frmShift input[id='flid']").val(); 
	        
	        var dataStr = "&factId=" + factId
							+ "&sectionId=" + sectionId
							+ "&cellId=" + cellId + "&machId="+ machId
							+ "&compId=" + compId
							
							+ "&locId=" + locId
							+"&flid="+ flid;
			  loadFunctionalLocation("ShiftfunLocation", "functionalLoc.sftm", "frmshiftfunLocationValues", "frmShift",dataStr);


			  fillComboBox("frmShift","cmbSftmShiftorder","combo_Shiftorder.sftm" );
		  		
				fillComboBox("frmShift","cmbSftmKeyid","shift.commonFilter?&flid="+flid );
		  		fillComboBox("frmShift","cmbSftmFactoryid","location.commonFilter" );
  		
  			Shift_successtime();
  			jQuery('#frmShift .easyui-text').css('text-transform', 'uppercase');
  			jQuery('#frmShift textarea').css('text-transform', 'uppercase');

			jQuery("#spnSftmDuration").change(function (){
				//alert("hh");
				fill_change();
			});  
			jQuery("#txtSftmCode").change(function (){
				//alert("hh");
				shift_order();
			});  
			numericTextBox('txtSftmBreaktime');
			
  		}); 

  			function frmShiftcmbSftmKeyid_onLoadSuccess()
				{ 

	  			}
  			function frmShiftcmbSftmCompanyid_onLoadSuccess()
			{ 
  				//fillComboBox("frmShift","cmbSftmFactoryid","factroyCombo.commonFilter" );
  			}
  			function frmShiftcmbSftmFactoryid_onLoadSuccess()
			{ 
  				fillComboBox("frmShift","cmbSftmSectionid","sectionCombo.commonFilter" );
  			}
  			function frmShiftcmbSftmSectionid_onLoadSuccess()
			{ 
  				fillComboBox("frmShift","cmbSftmCellid","cellCombo.commonFilter" );
  			}
  			function frmShiftcmbSftmCellid_onLoadSuccess()
			{ 
  				fillComboBox("frmShift","cmbSftmShiftorder","combo_Shiftorder.sftm" );
  			}
  			function frmShiftcmbSftmShiftorder_onLoadSuccess(){}
  			
  		function  frmShiftcmbSftmKeyid_onSelect(record)
  		{

  			
  			
  			processAjaxCalls("shift_recall.sftm" ,"Sftm="+record.id, "frmShift_successCallback","frmShift_errorCallback");
  		  
  		}
 		 function frmShift_successCallback(result)
 		 { 
 	 		// alert(45);
 	  		
 	  		jQuery("#cmbSftmKeyid").combobox("setValue",result.shift.SftmKeyid);
 	  		jQuery("#cmbSftmCompanyid").combobox("setValue",result.shift.SftmCompanyid);
 	  	    jQuery("#cmbSftmFactoryid").combobox("setValue",result.shift.SftmFactoryid);
 	  	    // jQuery("#cmbSftmFlid").combobox("setValue",result.shift.SftmFlid);
 	  	    jQuery("#txtSftmFlid").val(result.shift.SftmFlid);
 	  	    var dataStr = "&flid="+result.shift.SftmFlid;
 	  	 	loadFunctionalLocation("ShiftfunLocation", "functionalLoc.sftm", "frmshiftfunLocationValues", "frmShift",dataStr);
            jQuery("#txtSftmName").val(result.shift.SftmName);
            
 	  		jQuery("#txtSftmCode").val(result.shift.SftmCode);
 	  		jQuery("#txtSftmBreaktime").val(result.shift.SftmBreaktime);
 	  		jQuery("#spnSftmStarttime").val(result.shift.SftmStarttime);
 	  		jQuery("#spnSftmDuration").val(result.shift.SftmDuration);
 	  		jQuery("#spnSftmEndtime").val(result.shift.SftmEndtime);
 	  		jQuery("#cmbSftmShiftorder").combobox("setValue",result.shift.SftmShiftorder);
 	  		jQuery("#txtSftmDescription").val(result.shift.SftmDescription);
 	  		reloadCombo("frmShift","cmbSftmFlid","shift.commonFilter?&flid="+record.id);
 	  		Shift_successtime();
 	  	  
 	  		//jQuery("#txtSftmFlid").val(result.shift.SftmFlid);
 	  		/*var factId = jQuery("#frmShift input[id='factory']").val();
		    var sectionId = jQuery("#frmShift input[id='section']").val();
		    var cellId = jQuery("#frmShift input[id='cell']").val();
		    var machId = jQuery("#frmShift input[id='machine']").val();
            var compId = jQuery("#frmShift input[id='company']").val(); 
            var locId = jQuery("#frmShift input[id='location']").val(); 
			var flid = jQuery("#frmShift input[id='flid']").val(); 
	        
	       /* var dataStr = "&factId=" + factId
							+ "&sectionId=" + sectionId
							+ "&cellId=" + cellId + "&machId="+ machId
							+ "&compId=" + compId
							
							+ "&locId=" + locId
							+"&flid="+ flid;
			  loadFunctionalLocation("ShiftfunLocation", "functionalLoc.sftm", "frmshiftfunLocationValues", "frmShift",dataStr);*/
 	  		
 	  		
 		 }

 		function frmShift_FuntLocHierarchy_SuccessCallBack(result)
 		{
 			//alert(100);
 			var flid = result.flId;
 			jQuery("#cmbSftmFlid").val(flid);
 			reloadCombo("frmShift","cmbSftmFlid","shift.commonFilter?q&flid="+flid);
 		}
 	  	
  		 function frmShift_errorCallback(result)
  		 {
  			//alert("Error in callback");

  		 }
  		 function Shift_successtime()
  		 {
  	  	
  			var spnSftmDuration = jQuery('#spnSftmDuration').val();
  	  		
  			if(spnSftmDuration.length > 19) {
  				spnSftmDuration = spnSftmDuration.substring(11, 17);
  				jQuery('#spnSftmDuration').val(spnSftmDuration); 				
  				
  			}
  			var spnSftmStarttime = jQuery('#spnSftmStarttime').val();
  			if(spnSftmStarttime.length > 19) {
  				spnSftmStarttime = spnSftmStarttime.substring(11, 17);
  				jQuery('#spnSftmStarttime').val(spnSftmStarttime);
  				
  			}
  			var spnSftmEndtime = jQuery('#spnSftmEndtime').val();
  			if(spnSftmEndtime.length > 19) {
  				spnSftmEndtime = spnSftmEndtime.substring(11, 17);
  				jQuery('#spnSftmEndtime').val(spnSftmEndtime);
  				
  			}
  			
  		 }
  		function fill_change()
			{ 
			
	  			var Starttime= jQuery('#spnSftmStarttime').val();
	  			if(Starttime!=null && Starttime !=""){
				var Duration = jQuery('#spnSftmDuration').val();

				var startTimeArr =  Starttime.split(":");
				var durationArr = Duration.split(":");
				var totalMints = (parseInt(startTimeArr[ 1 ]) + parseInt(durationArr[ 1 ])) ;
				var totHours =   ((parseInt(startTimeArr[ 0 ]) + parseInt(durationArr[ 0 ])) % 24) + Math.floor(totalMints / 60 );
				totalMints = totalMints %60;
		 		var Endtime = totHours +':' + totalMints;
		 		
		 		jQuery('#spnSftmEndtime').val(Endtime);
		 		
	  			}
	  		
	  		}
  		function shift_order()
  		{
  			var Shiftorder= jQuery('#txtSftmCode').val();
  			var time = jQuery('#spnSftmStarttime').val();
  			//alert(Shiftorder);
  			
  			if (Shiftorder == 1)  	  			
  	  			jQuery("#spnSftmStarttime").setValue("8");
  	  			
  	  			else
  	  	  			{
  	  	  		//	alert("1st shift start ");
  	  	  			jQuery('#spnSftmStarttime').val(" ");
  	  	  			}
	  	 }
  		/*function  frmShiftcmbSftmFactoryid_onSelect(record)
  		{
  			jQuery("#cmbSftmSectionid").combobox('clear');
  			jQuery("#cmbSftmCellid").combobox('clear');
  		
  			reloadCombo("frmShift","cmbSftmSectionid","sectionCombo.commonFilter?factId="+record.id);
  			reloadCombo("frmShift","cmbSftmCellid","cellCombo.commonFilter?factId="+record.id  );
  			
  		}*/
  		  	 
  		function  frmShiftcmbSftmCellid_onSelect(record)
  		{
  			//alert("hi");
  			//jQuery("#cmbMachine").combobox('clear');
  			//reloadCombo("frmShift","cmbSftmSectionid","sectionCombo.commonFilter?cellId="+ record.id );
  			fillCellHierarchy("cellHierarchy.commonFilter",record.id,"cmbSftmSectionid","cmbSftmFactoryid");
  			//alert(cellHierarchy.commonFilter);
  			//alert(record.id);
  			//alert(cmbSect);
  			
  		}
  		
  		 function frmShift_deleteSuccessCallback(result)
  		 {  
  			clearForm('frmShift');
  			alert(result.successData.msg);
  		 }
  		function frmShift_FuntLocHierarchy_SuccessCallBack(result){
  			setFunctionalLocWidth('frmShift','600px');
  		
  	}
  		
  		 
</script>
<form name="frmShift" id="frmShift" > 	
<div id="wrapper">
<div align="center" >

	<table align="center" >
		<tr>
			<td>
			
		         <div  style="float:left;padding-left:20px;padding-right:50px; width : 450px;">
					
					<div class="easyui-paddingbfpx" ><label >Shift</label></div> 
					          	<div class="easyui-paddingbfpx"> 
					        	<input id="cmbSftmKeyid" name="cmbSftmKeyid" class="easyui-combobox"  style="width:300px;" value="${requestScope.genTlShiftmst.sftmKeyid}" / >
					</div>
		<div id="frmShiftFuntKeyIds">
					<input type="hidden" id="factory" name="cmbFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbMachineid" value=""></input>
					<input type="hidden" id="company" name="cmbCompanyid" value=""></input>
					<input type="hidden" id="location" name="cmbLocationid" value=""></input>
					<input type="hidden" id="flid" name="txtSftmFlid" value="${requestScope.genTlShiftmst.sftmFlid}"></input>        
		</div>

                 <div  class="easyui-paddingbfpx" id="ShiftfunLocation" style="width: 70%;"></div>
					
	           
					
<!--					 <div class="easyui-paddingbfpx "><label class="mandatory-lbl">Location</label></div> -->
<!--					             <div class="easyui-paddingbfpx"> -->
<!--					             <input id="cmbSftmFactoryid" name="cmbSftmFactoryid" class="easyui-combobox"  style="width:300px;" value="${requestScope.genTlShiftmst.sftmFactoryid}" / >-->
<!--					              </div>-->
					
<!--					<div class="easyui-paddingbfpx"><label>Section</label></div> -->
<!--					             <div class="easyui-paddingbfpx"> -->
<!--					           	 <input  class="easyui-text" id="cmbSftmSectionid" name="cmbSftmSectionid"  style="width:300px; height : 21px;" value="${requestScope.genTlShiftmst.sftmSectionid}"  / >-->
<!--					</div>-->
<!--					<div class="easyui-paddingbfpx"><label>Line</label></div> -->
<!--					             <div class="easyui-paddingbfpx"> -->
<!--					           	 <input id="cmbSftmCellid" name="cmbSftmCellid" class="easyui-combobox" style="width:300px;height : 21px;" value="${requestScope.genTlShiftmst.sftmCellid}"  / >-->
<!--					</div>-->
					
					<div class="easyui-paddingbfpx "><label class="mandatory-lbl">Name</label></div> 
					            	 <div class="easyui-paddingbfpx"> 
					           		<input  class="easyui-text"  id="txtSftmName" name="txtSftmName"  style="width:300px;width:303px\9;height : 21px;" value="${requestScope.genTlShiftmst.sftmName}" / >
					</div>
					
					
					<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Code</label><span  style="padding-left:135px;font-size:12px; " class="mandatory-lbl">BreakTime(in mins)</span></div> 
					<div class="easyui-paddingbfpx"> 
					<input   class="easyui-text"  id="txtSftmCode" name="txtSftmCode"  style="width:140px;" value="${requestScope.genTlShiftmst.sftmCode}"  / >
					<span class="floatR3 " style= "padding-left:19px" > 
					<input class="easyui-text" id="txtSftmBreaktime" name="txtSftmBreaktime"  style="width:137px;width:140px\9;" value="${requestScope.genTlShiftmst.sftmBreaktime}" />
<!--					<input class="easyui-button" type="button" value="..." />-->
					</span> 
					<span>
					<span id="err_txtSftmCode" class="tpm-errormsg" ></span>  
					<span id="err_txtSftmBreaktime" class="tpm-errormsg"  style= "padding-left:200px" ></span></span>
					</div>
					
					
					<div class="easyui-paddingbfpx"><label class="mandatory-lbl">Duration in(HH:MM)</label><span  style="padding-left:55px;"><label class="mandatory-lbl">ShiftOrder</label></span></div>
					<div>
					<input  id="spnSftmDuration" name="spnSftmDuration"  style="width:140px;" class="easyui-timespinner spinner-text validatebox-text" showseconds="false"onchange="fill_change();" value="${requestScope.genTlShiftmst.sftmDuration}"/ >	 
					<span class="floatR3  " style= "padding-left:17px" > 
					<input id="cmbSftmShiftorder" name="cmbSftmShiftorder"  style="width:140px;width:138px\9;" clear='false' class="easyui-combobox"  editable="true" style="width:120px;"  value="${requestScope.genTlShiftmst.sftmShiftorder}"/>
					 
					</span> 
					<span class="tpm-errormsg" >
					<span id="err_cmbSftmShiftorder" class="tpm-errormsg"  style="padding-left:200px;" ></span>
					<span id="err_spnSftmDuration" class="tpm-errormsg"  >
					
					</span>
					</span>
					</div> 
<!--					<span id="err_spnSftmDuration" class="tpm-errormsg" style="padding-left:200px;"></span>-->
					<div class="easyui-paddingbfpx "><label class="mandatory-lbl" style="padding-left:0px;margin-left:0px;">Start time </label><span  style="padding-left:105px; font-size:12px;" >End time</span></div> 
					<div class="easyui-paddingbfpx" style="padding-left: 0px" > 
					            			
					<input  id="spnSftmStarttime" name="spnSftmStarttime"  style="width:140px;" class="easyui-timespinner spinner-text validatebox-text"style="width: 120px;"showseconds="false" onchange="fill_change();" value="${requestScope.genTlShiftmst.sftmStarttime}" />
					
					<span class="floatR3" style= "padding-left:17px"> 
					
					<input  id="spnSftmEndtime" name="spnSftmEndtime"   style="width:140px;width:138px\9;" class="easyui-timespinner spinner-text validatebox-text" style="width: 120px;" showseconds="false" onchange="fill_change();" value="${requestScope.genTlShiftmst.sftmEndtime}" />
					   
					</span>
					<span id="err_spnSftmStarttime" class="tpm-errormsg"></span>
					 </div>
					<div class="easyui-paddingbfpx" style= "padding-left:0px"><label>Remarks</label></div>
					<div class="easyui-paddingbfpx" style= "padding-left:0px"><textarea id="txtSftmDescription" name="txtSftmDescription"  cols="34" rows="5" style="width:305px\9;">${requestScope.genTlShiftmst.sftmDescription}</textarea></div>
				</div>
			</td>
		</tr>
		<input type="hidden" id="mode" value="${requestScope.formMode}" />
	 </table>
   </div>
  </div>
</form>