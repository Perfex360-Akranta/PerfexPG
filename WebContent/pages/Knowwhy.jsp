<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<script>
jQuery(document).ready(function()
		
			
		{
	var roleId = jQuery("#hdnUserRole").val();
	
	if(roleId != 'AROL0055' && roleId != 'AROL0003')
	{
		 console.log("Access DENIED - Role mismatch");
	        console.log("Expected: 'QM PILLAR MEMBER' (AROL0055)");
	       
	        
	        // Hide all form content
	        jQuery('#frmKnow').hide();
	        
	        // Show error message
	        if(typeof popupCommonErrorMsg === 'function') {
	            popupCommonErrorMsg("Please select the QM PILLAR MEMBER OR DMT LEADER");
	        } else {
	            alert("Please select the QM PILLAR MEMBER OR DMT LEADER");
	        }
	        
	        // Redirect back after showing message
	        setTimeout(function() {
	            navigateToPrevForm();
	        }, 5000);
	        
	        return false;
		
	}
	
	 
		initialiseForm('frmKnow');
		
		
		jQuery('#submitForm').val('frmKnow');
		formatDateBox('dteKnwmPrepareddate','dd-MMM-yyyy');
		
		fillComboBox('frmKnow','cmbKnwmPillarid', 'pillar.commonFilter');
		//fillComboBox('frmKnow','cmbKnwmApprovedby', 'employeeFilter.commonFilter');
		//fillComboBox("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
		disableField("frmKnow", "cmbKnwmPhenomena");
		jQuery("#phenomena").removeClass('mandatory-lbl');
		fillComboBox("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
		jQuery('.sub-header').css('height','24px\0/');
		jQuery('#know').css('width','65%');
		var btnName = jQuery("#hdnBtnName").val();
		
		

		/**Version Control**/
		var LatestVersionNo=jQuery("#txtKnwmVersionno").val();
		var CurrVersionNo=jQuery("#txtCurrVersionno").val();
		if(LatestVersionNo=="0"){
			jQuery("#txtKnwmVersionno").val('');
			jQuery("#txtKnwmVersiondate").val('');	
		}


		
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function()
		{
			var Knwid = getFieldValue("hdnKnwmKeyid");
			var flid = jQuery("#frmKnow input[id='flid']").val();
			if(Knwid != null && Knwid.length > 0)
			window.open("KnwwhyRpt_Excelview.KnowWhy?Knwid="+Knwid+"&flid="+flid);
			else
				alert(" Save Transaction Before View Excel ");
	});
				
        imageUpload(jQuery("#dlgImg"),'ImageUpload.commonFilter','dlgImg',"imgKnowwhyImage","imgknowImgFilename","415","264");

		var imgpath= getFieldValue("hdnKnwmImagepath");
	  	//alert(imgpath);
	  	jQuery('#imgKnowwhyImage').attr('src', imgpath);
	  	
	  	fileManagerPopUp("","KNW","frmKnow","btnfilemgr","abnFilemgr");
	  	
	  	var keyid = jQuery("#hdnKnwmKeyid").val();
	  	processGridnew("KnowWhygridpop_input.KnowWhy","q=2&detailgrid="+false+"&keyid="+keyid,"knowwhygrid","","","GridPopupdoubleclick","","load_complete");

    	var factId = jQuery("#frmKnow input[id='factory']").val();
        var sectionId = jQuery("#frmKnow input[id='section']").val();
        var cellId = jQuery("#frmKnow input[id='cell']").val();
        var machId = jQuery("#frmKnow input[id='machine']").val();
        var flid = jQuery("#frmKnow input[id='flid']").val();
        var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
        if(dataStr.trim().length<=0)
        	dataStr = "";	  					
        loadFunctionalLocation("knowLocation","functionalLoc.KnowWhy","frmKnowLocationfunLocationValues","frmKnow",dataStr);
        fillComboBox('frmKnow','cmbKnwmDevelopedby', 'employee.commonFilter?flid='+flid);
        
        jQuery("#btnUpd").click(function() {
        	///var knowwhykeyid = jQuery("#hdnKnwmKeyid").val();
        	//alert("The KnowWhy KeyId"+knowwhykeyid);
        	var Possible= jQuery("#txtKnwdPossiblecauses").val();
        	///alert(Possible);
        	var knowwhy =jQuery("#txtKnwdKnowwhy").val();
        	var solution =jQuery("#txtKnwdSolution").val();
        	var normal = jQuery("#txtKnwdNormalcondition").val();
        	var sustenance = jQuery("#txtKnwdSustenanceaction").val();
        //	var LatestVersionNo=jQuery("#txtKnwmVersionno").val();
    	//	var CurrVersionNo=jQuery("#txtCurrVersionno").val();
    		//processAjaxCalls("knowwhyappcountlist_save.Knowwhy?q=2","knowwhykeyid="+knowwhykeyid);
        	if(Possible.trim().length<=0 && knowwhy.trim().length<=0  && normal.trim().length<=0 && solution.trim().length<=0 && sustenance.trim().length<=0  ) {

                alert("Enter Possible , Knowwhy ,Solution and Normal Condition and Sustenance Action");
        	}
        	else{
        		saveForm("frmKnow","Knowwhyform_save.KnowWhy?&type=type&detailSave=Y");
        	}
        });
       
        jQuery('#cmbKnwmType').val(jQuery('#hdntype').val());

        if(jQuery('#chkCheckPhenomena').is(':checked') == true){
        	enableFields("cmbKnwmPhenomena");
        	jQuery("#phenomena").addClass('mandatory-lbl');
        	jQuery("#Quality").removeClass('mandatory-lbl');
        	
    			//reloadCombo("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
        	reloadCombo("frmKnow", "cmbKnwmPhenomena", "defphen.commonFilter");
    		
            }
        else{
	        	disableField("frmKnow","cmbKnwmPhenomena");
	        	jQuery("#Quality").addClass('mandatory-lbl');
	    		jQuery("#phenomena").removeClass('mandatory-lbl');
            }
        var value= getFieldValue("cmbKnwmType");
        gotSelectType(value);
        
        jQuery("#btndel").click(function() {
        	var dtlkeyid =jQuery('#hdnKnwdKeyid').val();
        	 if(dtlkeyid==null || dtlkeyid=="" || dtlkeyid==" ")
        		 	return false;
        	 else{
        			var r=confirm("Are You Sure to Delete?");
        			if(r)
            			{
	        				processAjaxCalls("Knowwhyform_delete.KnowWhy?q=2","&dtlkeyid="+dtlkeyid,"remove_successCallBack","remove_errorCallBack");
	        				return true;
        				}
        			else
        				return false;
        	
        		 }
        });

	   
		jQuery("#imgKnowwhyImage").load(function() {
			if((jQuery(this).width()>415)||(jQuery(this).height()>264))
			{	jQuery('#imgKnowwhyImage').attr('src', "");
				alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
				return false;
			}


			jQuery("#btnImgClear").click(function() {
				jQuery('#imgKnowwhyImage').attr('src', "");
				jQuery('#imgknowImgFilename').val("");
				});
	    });
	
		if(screen.width <= 1024){
			 jQuery('.table').css('margin-left','-204');
			 
	         if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0")
	             jQuery('.table').css('margin-left','0');
		}    
	    	else{
	    		
	    	}

	
		jQuery('#chkCheckPhenomena').click(function() {
			//alert("check");
        	if(jQuery('#chkCheckPhenomena').is(':checked') == true){
            	//alert(jQuery('#chkCheckPhenomena').is(':checked') );
        		enableFields("cmbKnwmPhenomena");
        		jQuery("#Quality").removeClass('mandatory-lbl');
        		jQuery("#phenomena").addClass('mandatory-lbl');
        		//fillComboBox("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
        		//reloadCombo("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
        		reloadCombo("frmKnow", "cmbKnwmPhenomena", "defphen.commonFilter");
        		
            	}
        	else{
            	disableField("frmKnow","cmbKnwmPhenomena");
        		jQuery("#Quality").addClass('mandatory-lbl');
        		jQuery("#phenomena").removeClass('mandatory-lbl');
            	}
        });

        
		 fillWithCurrentDate('dteKnwmPrepareddate');
		    var mode= jQuery('#mode').val();
			//alert("The Mode Is"+mode);
			
			if(mode=="approval"){
			//	alert("Inside the Approval");
		 		var Knwid = getFieldValue("hdnKnwmKeyid");
				 var flid=jQuery("#flid").val();
				 workFlow("knowwhyapproval",false,"KNWWHYAPPR", Knwid, "KNW",flid);	
				 //return false;
			}
			else if(mode=="View"){
				    var Knwid = getFieldValue("hdnKnwmKeyid");
					 var flid=jQuery("#flid").val();
				    disableUIButton('btnUpd');
				    disableUIButton('btndel');
				    disableUIButton('btnfilemgr');
				    disabledUIButton('dlgImg');
				    disabledUIButton('btnImgClear');
					workFlow("knowwhyapproval",false,"KNWWHYAPPR", Knwid, "KNW",flid,"","","N");	
				  // return false;	
				}
		
			
			var mode=jQuery('#mode').val();
			if(mode=="approval"){
				disableForm("frmKnow");
				enableUIButton('btnViewTemplate');
				jQuery('#knowLocation').append('<div id="divhide1" style="position:absolute;top:0;left:0;width:60%;z-index:2;opacity:0.4;height:100%;"> </div>');
			}
});



/* function gotSelectType(value)
{

	if(value =='Q')
		{ 
		    //alert("2" + value);
		     
		   
			reloadCombo("frmKnow","cmbKnwmPhenomena","defphen.commonFilter");
			
		}
	
	else {
		// alert("4" + value);
		
			reloadCombo("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
			//fillComboBox("frmKnow","cmbKnwmPhenomena","phenomena.commonFilter");
		 }
	
	

} */

function gotSelectType(value) {
    // Update the label text based on selected value
    if(value == 'Q') {
        jQuery("#Quality").text('Quality Defect');
        // Update phenomena combo
        reloadCombo("frmKnow", "cmbKnwmPhenomena", "defphen.commonFilter");
    }
    else if(value == 'E') {
        jQuery("#Quality").text('Engineering Defect');
        // Update phenomena combo
        reloadCombo("frmKnow", "cmbKnwmPhenomena", "phenomena.commonFilter");
    }
    else if(value == 'S') {
        jQuery("#Quality").text('Service Defect');
        // Update phenomena combo
        reloadCombo("frmKnow", "cmbKnwmPhenomena", "phenomena.commonFilter");
    }
}
function isDtl(id){
	//alert(id);
	 if(jQuery("#"+id).val().trim().length>0)
		jQuery('#hdnIsDtlTrue').val("true");	
		var data=jQuery("hdnIsDtlTrue").val();
	//	alert(data);
}
function GridPopupdoubleclick(id)
{
	
	 var rowData = jQuery("#knowwhygrid").jqGrid('getRowData',id );
	 setFieldValue("txtKnwdPossiblecauses",rowData.POSSIBLECAUSES);
	 setFieldValue("txtKnwdKnowwhy",rowData.KNOWWHY);
	 setFieldValue("txtKnwdSolution",rowData.SOLUTION);
	 setFieldValue("txtKnwdNormalcondition",rowData.NORMALCONDITION);
	 setFieldValue("txtKnwdSustenanceaction",rowData.SUSTENANCEACTION);
	 var dtlkeyid = rowData.DTLKEYID;
	 jQuery('#hdnKnwdKeyid').val(dtlkeyid); 
}



function frmKnow_beforeSubmit()
{
	var sectionId = jQuery("#frmKnow input[id='section']").val();
	
	if(sectionId.trim().length<=0){
		alert("Select DMT");
		return false;
	}
	
	var mode=jQuery('#mode').val();
	if(mode=="approval"){
		popupCommonErrorMsg("Know Why Can not be modify data in " + mode + " Mode. ");
		return false;
	}
	if(mode=="View"){
		popupCommonErrorMsg("Know Why Can not be Add data in " + mode + " Mode. ");
		return false;
	}
	
	//var imagename =jQuery("#imgknowImgFilename").val();// jQuery("#imgKnowwhyImage").attr('src');
	var imagename =jQuery("#imgKnowwhyImage").attr('src');
	//var image = src.replace(/^.*\/|\.png$/g, '');
	var image=imagename.lastIndexOf("/");
	var imageName= imagename.substring(image+1,imagename.length);
	var imagefullname = imageName.toUpperCase();
	jQuery("#hdnKnwmImage").val(imagefullname);

}


/* function btnfilemgr_click()
{
	var keyid = jQuery('#hdnKnwmKeyid').val();
  	
  	if(keyid.trim().length<=0){
  		saveForm('frmKnow','KnowWhygrid_save.KnowWhy?filemanger=filemanger');
  	}else if(keyid != null && keyid != ''){
  		
  		var mode= jQuery('#mode').val();
		
  		apMode = "create";
		if(mode=="approval")
			apMode = "view";
		
			fileManagerPopUp(keyid,"KNW","","","",apMode);
	    }
}
 */

function btnfilemgr_click(){
    
		var keyid = jQuery('#hdnKnwmKeyid').val();
    //alert(" documentNo:::1234::: "+documentNo);
	if(keyid != null && keyid != ''){
		
		var mode= jQuery('#mode').val();
		
		apMode = "create";
		if(mode=="View")
		   apMode = "view";
	
		fileManagerPopUp(keyid,"KNW","","","",apMode);
			
	} else
    {
		saveForm('frmKnow','KnowWhygrid_save.KnowWhy?filemanger=filemanger');    
		}	
} 



function remove_successCallBack(result)
{
	alert(result.successData.msg);
  	jQuery("#knowwhygrid").trigger("reloadGrid");
  	setFieldValue('txtKnwdPossiblecauses','');
	setFieldValue('txtKnwdKnowwhy','');
	setFieldValue('txtKnwdSolution','');
	setFieldValue('txtKnwdNormalcondition','');
	setFieldValue('txtKnwdSustenanceaction','');
}
function frmKnow_FuntLocHierarchy_SuccessCallBack(result){
		setFunctionalLocWidth('frmKnow','799px');
	
}



function frmKnow_successsCallback(result)
{
	//var keyid =result.keyid;
	var keyid =result.successData.keyIdValue;//Added This Line
	var detailSave = result.successData.detailSave;//Added This Line
	
	var LatestVersion=result.KnowLatVerNo;
	jQuery('#txtKnwmVersionno').val(LatestVersion);
	var latVerData=result.knowLatestVerDate;
	jQuery("#txtKnwmVersiondate").val(latVerData);
	var CurrVersion=result.KnowCurrentVerNo;
	jQuery("#txtCurrVersionno").val(CurrVersion);
	var Type=result.type;
	var imgpath=result.image;
	if(detailSave == "Y")//Added This Line
	{
		processGridnew("KnowWhygridpop_input.KnowWhy","q=2&detailgrid="+false+"&keyid="+keyid,"knowwhygrid","","","GridPopupdoubleclick","","load_complete");
	}
	
	if(detailSave != "Y")//Added This Line
	{
	navigateToPrevForm();
	}
	var filemanger =result.filemanger;
	if(filemanger==true){
    if(keyid.trim().length>0){
   	 fileManagerPopUp(keyid,"KNW","","","");
			 }
		}	
/* 	if(Type!="type"){
	setTimeout(function(){
	  navigateToPrevForm();
	},350);
	} */
	jQuery("#hdnKnwmKeyid").val(keyid);
	//processGridnew("KnowWhygridpop_input.KnowWhy","q=2&detailgrid="+false+"&keyid="+keyid,"knowwhygrid","","","GridPopupdoubleclick","","load_complete");
	setFieldValue('txtKnwdPossiblecauses','');
	setFieldValue('hdnKnwdKeyid' ,'');
	setFieldValue('txtKnwdKnowwhy','');
	setFieldValue('txtKnwdSolution','');
	setFieldValue('txtKnwdNormalcondition','');
	setFieldValue('txtKnwdSustenanceaction',''); 
}


function frmKnow_beforeDelete()
{
	var mode=jQuery('#mode').val();
	//alert(" mode :: "+mode);
	if(mode=="approval"){
		popupCommonErrorMsg("Know Why Can not be delete data in " + mode + " Mode. ");
		return false;
	}
	
	var mstKeyid=jQuery("#hdnKnwmKeyid").val();
	 if(mstKeyid==null || mstKeyid=="" || mstKeyid==" ")
	 	return false;
	 else
		 var r=confirm("Are You Sure to Delete?");
		if(r)
			return true;
		else
			return false;
	}



function frmKnow_deleteSuccessCallback(result){
	alert(result.successData.msg);
	clearForm("frmKnow");
	jQuery('#imgKnowwhyImage').attr('src', "");
	jQuery("#knowwhygrid").trigger("reloadGrid");
}


function remove_errorCallBack()
{
}



/*function gotSelectType(value){
	if(value=='Q'){ 
		jQuery("#lbchange").text('Quality');
		}
	else if(value=='E') {  
		jQuery("#lbchange").text('Engineering');
		}
	else{  
		jQuery("#lbchange").text('Service');
		}
}*/



</script>
<form name="frmKnow" id='frmKnow' >
 <div id="wrapperRpt" style="margin-top: -2px">
	<table cellspacing="10px" style="">
		<tr style=" ">
			<td colspan="4" style=" width : 700px;">
				<div id="frmKnowFuntKeyIds"  >							
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>	
								<input type="hidden" id="flid" name="cmbKnwmFlid"  value="${requestScope.qtmTlKnowwhymst.knwmFlid}"></input>
								<input type="hidden" id="elementId" name="cmbKnwmElementid"  value="${requestScope.qtmTlKnowwhymst.knwmElementid}"></input>							
							</div>	
											
						<div id="knowLocation" style="width:775px;"> 	</div>
			</td>
			<td  style="" rowspan="4">
				<div style="padding-left:20px;" >
				<div >
					<img id="imgKnowwhyImage" name="imgKnowwhyImage" src="images/box_1.png" width="200px" height="210px" />
				</div>
				<div style="position:relative;">
			 		<span style="position: absolute; bottom: 40px; left: 0%; top: 2px;">
			 			<input type="button" class="easyui-button" id="dlgImg" name="dlgImg" value="+" style="/* width:12px; */height:18px;"/> 
			 		</span> 
			 		<span style="position: absolute; bottom: 8px; right: 0%; top: 2px;">
			 			<input type="button" class="easyui-button" id="btnImgClear" name="ImgClear" value="-" style="/* width:12px; */height:18px;"/> 
			 		</span>			  
				 </div>
				 </div>
			</td>
		</tr>
	<tr >
	  <td>
			<div  style="">
				<label class="mandatory-lbl" >Pillar</label>		
			</div>		
			<div style="width : 152px;">
				<input class="easyui-combobox" id="cmbKnwmPillarid" name="cmbKnwmPillarid" maxlength="30" style="width: 185px; /* height: 21px; */" value="${requestScope.qtmTlKnowwhymst.knwmPillarid}"/>						
			</div>
	</td>
	<td>
		<label >Type</label>
		<span style="margin-left: 100px"> <label> Date</label></span>
	<div>
		<select id="cmbKnwmType" name="cmbKnwmType" style="width:90px;/*  height:21px; */"  onchange='gotSelectType(this.value)' >
			<option value='Q'>Quality</option>
			<option value='E'>Engineering</option>
			<option value='S'>Service</option>
		</select>
		<span style="margin-left: 5px">
			<input class="easyui-datebox"  id="dteKnwmPrepareddate" name="dteKnwmPrepareddate"  style=" width:90px;/* height:25px; */ " value="${requestScope.qtmTlKnowwhymst.knwmPrepareddate}"/> 
		</span>
		</div>
	</td>

<td>
	<div style= "padding-left: 20px">
		<label class="mandatory-lbl" id="phenomena">Phenomena</label>	
	</div>
	<div style="position:relative;">
	<!--<input type="checkbox"  id="chkknw" name="chkknw"  />-->
	<span  style="margin-left:20px;">
		<input id="cmbKnwmPhenomena" name="cmbKnwmPhenomena"   class="easyui-combobox" style="width:358px;/*  height: 21px; */" value="${requestScope.qtmTlKnowwhymst.knwmPhenomena}"  />
	</span>
	<span style="position:absolute;left:0;top:4;"><input id="chkCheckPhenomena"  name="chkCheckPhenomena" type="checkbox"  value = "${ requestScope.qtmTlKnowwhymst.checkPhenomena == 'Y' ? ' checked':''}"/></span>
	</div>		
</td>

</tr>

<tr>
<td  colspan="2">
		<div style=" ">
			<label id="Quality" class="mandatory-lbl" >Quality Defect</label>
		</div>
		<div class="" style=" ">
			<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 385px; height : 52px;text-transform: uppercase;" class="limit-length" title="Maximum Length is 250" maxlength="250" id="txtKnwmQuality" name="txtKnwmQuality" >${requestScope.qtmTlKnowwhymst.knwmQuality}</textarea>
		</div>
					
</td>

<td colspan="">
	<div style="">
	<div>
		<label class="mandatory-lbl" >Description</label></div>
	</div>
	<div class="" style="">
		<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="3" style="width: 375px; height : 52px;text-transform: uppercase;" class="limit-length" title="Maximum Length is 250" maxlength="250" id="txtKnwmDescription" name="txtKnwmDescription" >${requestScope.qtmTlKnowwhymst.knwmDescription}</textarea>
	</div>
							
	</td>

</tr>
<tr>
	<td valign="top" style=" " colspan="2">
		<label class="mandatory-lbl">Developed By</label>					
	<div >
		<input class="easyui-combobox" id="cmbKnwmDevelopedby" name="cmbKnwmDevelopedby"  style="width: 385px;/*  height: 21px; */" value="${requestScope.qtmTlKnowwhymst.knwmDevelopedby}"/>
	</div>
	</td>
	<!--<td valign="top">
		<label class="mandatory-lbl">Approved By</label>
		<div>
			<input class="easyui-combobox" id="cmbKnwmApprovedby" name="cmbKnwmApprovedby" maxlength="30"style="width: 373px; height: 21px;" value="${requestScope.qtmTlKnowwhymst.knwmApprovedby}"/>
		</div>
	</td>
-->
</tr>
<tr>
	<td colspan="4" style="">
   <div style=" width:805px; margin-top: 0px;height:15px; position:relative;" class="sub-header">
			<span style="" >Know Why Details</span>
			<span style="position:absolute;right:0px;top:-4px;">
			<input class="easyui-button" type="button" value="ViewFormat" id="btnViewTemplate"name="btnViewTemplate" style="height: 25px;width:75px" />
					<!--<img id="btnInsert" alt="" title="Add Details" src="images/addbtsub.png" style="cursor: pointer;z-index:210;margin-top:-4;height:24px;" class="">
			--></span>
	</div>
	</td>
</tr>
<tr>
	<td>
		 <div style="margin-top: -10px">
			<label >Possible Causes</label>		
			<div class="" >
					<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80"  id="txtKnwdPossiblecauses" name="txtKnwdPossiblecauses" class="limit-length" style="width: 179px;text-transform: uppercase; height : 52px;" onblur="isDtl('txtKnwdPossiblecauses');"></textarea>	
			</div>
		</div>				
	</td>
	<td>
		<div style="margin-top: -10px">
			<label >Know Why</label>
	        <div class="">
	         	<textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80" id="txtKnwdKnowwhy" name="txtKnwdKnowwhy" class="limit-length" style="width: 240px;height : 52px; text-transform: uppercase;" onblur="isDtl('txtKnwdKnowwhy');"></textarea>
	        </div>
	    </div>
	</td>
	<td>
	<div style="margin-top: -10px">
		<label >Solution</label>
		<span style="margin-left: 150px"> <label> Normal Condition</label></span>
		<!--  <span style="margin-left: 150px"> <label> Sustenance Action</label></span>-->
		</div>
		
		<div class="" style=" ;">
		  <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80" id="txtKnwdSolution" name="txtKnwdSolution"  class=" limit-length"  style="width: 174px; height : 52px;text-transform: uppercase;" onblur="isDtl('txtKnwdSolution');"></textarea>
		<span style="margin-left: 18px"> <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80" id="txtKnwdNormalcondition" name="txtKnwdNormalcondition" class="limit-length" style="width: 172px; height : 52px;text-transform: uppercase;" onblur="isDtl('txtKnwdNormalcondition');"></textarea></span>
	<!--<span style="margin-left: 18px"> <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80" id="txtKnwdSustenancecondition" name="txtKnwdSustenancecondition" class="limit-length" style="width: 172px; height : 52px;text-transform: uppercase;" onblur="isDtl('txtKnwdSustenancecondition');"></textarea></span>  -->
		</div>
		
	</td>
	
  <td colspan="5">
  <div style="margin-top: -10px">
  <label >Sustenance Action</label>
  
   
   <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" maxlength="400" rows="2" cols="80" id="txtKnwdSustenanceaction" name="txtKnwdSustenanceaction"  class=" limit-length"  style="width: 174px; height : 52px;text-transform: uppercase;" onblur="isDtl('txtKnwdSustenanceaction');"></textarea>
   </div>
   <td colspan="6">
   <div style="margin-top: -10px;margin-left: -30px">
  	 <input class="easyui-button" type="button" value="Insert" id="btnUpd"name="btnUpd" style="height: 25px;width:45px" />
	<span style="margin-top: 10px;">
		<input class="easyui-button" type="button" value="Delete" id="btndel" name="btndel" style="height: 25px;width:45px" />
	 </span>
	 <span style="position: relative; margin-top: 10px;margin-left: 5px">
	 <span  id="abnFilemgr" style="  position: absolute;" >	
     </span></span>
  </div> 
             
             
					
</td>

</tr>
</table>
<table style="position:absolute;top:20px;left:1134px;border:1px solid -moz-buttondefault;">
<tr>
<td>
	    <div>
			<label>
			      Latest Version.No.
			</label>
			 </div>
			 <div>
				<input class="easyui-text" id="txtKnwmVersionno" name="txtKnwmVersionno" disabled="disabled" style="width: 106px; height: 21px;" value="${requestScope.qtmTlKnowwhymst.knwmVersionno}"/>			       
	   		</div>

</td>
</tr>
<tr>
<td>
        <div>
			<label>
			      Latest Version.Date
			</label>
		</div>
		<div>
				<input class="easyui-text" id="txtKnwmVersiondate" name="txtKnwmVersiondate" disabled="disabled" style="width: 106px; height: 21px;" value="${requestScope.qtmTlKnowwhymst.knwmVersiondate}"/>			       
	    </div>
</td>
</tr>
<tr>
<td>
        <div>
			<label>
			      Current Version.No.
			</label>
		</div>
		<div>
				<input class="easyui-text" id="txtCurrVersionno" name="txtCurrVersionno" disabled="disabled" style="width: 106px; height: 21px;" value="${requestScope.qtmTlKnowwhymst.knwmTempfield5}"/>			       
	    </div>
</td>       

</tr>
</table>
<table  id='knowwhygrid' >
			<tr>
				<td>
				<div style="margin-top: -26px"></div>
				</td>
			</tr>
		</table>
		
		<div id ="knowwhyapproval"></div>
		
</div>


<input type="hidden" id="mode" name="mode"  value="${requestScope.mode}"/> 
<input type="hidden" id="hdnKnwmKeyid" name="hdnKnwmKeyid"  value="${requestScope.qtmTlKnowwhymst.knwmKeyid}"/> 
<input type="hidden" id="txtclearformval" name="txtclearformval" value="${requestScope.form}" />
<input type="hidden" id="imgknowImgFilename" name="imgknowImgFilename" value="" />
<input type="hidden" id="hdnKnwmImage" name="hdnKnwmImage" value="" />
<input type="hidden" id="hdnKnwmImagepath" name="hdnKnwmImagepath" value="${requestScope.qtmTlKnowwhymst.knwmImage}" />
<input type="hidden" id="hdnKnwdKeyid" name="hdnKnwdKeyid" value="" />
<input type="hidden" id="hdnIsDtlTrue" name="hdnIsDtlTrue" value="" />
<input type="hidden" id="hdntype" name="hdntype" value="${requestScope.qtmTlKnowwhymst.knwmType}"/>
</form>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Excel View" />