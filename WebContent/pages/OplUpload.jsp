<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="js/fileuploader.js"></script>
<link href="css/fileuploader.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
jQuery(document).ready(function(){
	    initialiseForm('frmoplupld');
	    jQuery('#submitForm').val('frmoplupld');
		var factId = jQuery("#frmoplupld input[id='factory']").val();
		var sectionId = jQuery("#frmoplupld input[id='section']").val();
		var cellId = jQuery("#frmoplupld input[id='cell']").val();
		var machId = jQuery("#frmoplupld input[id='machine']").val();
		var flid = jQuery("#frmoplupld input[id='flid']").val();
		var keyid =jQuery("#hdnoplkeyid").val();
		var mode=jQuery("#hdnMode").val();	
		var hdnflid = jQuery("#hdnoplflid").val();
		if (hdnflid!=null && hdnflid.trim().length>0 )
			flid=hdnflid;

	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("oplfunLocation","functionalLoc.oplUpd","oplfunLocationValues","frmoplupld",dataStr);
	
	fileManagerPopUp("","OPL","frmoplupld","btnfilemgr","OplUpdFilemgr");
	
	formatDateBox('dteoplmDate','dd-MMM-yyyy');
	fillComboBox("frmoplupld","cmboplmPreparedid","employee.commonFilter");
	
	if(mode=="View"){
		disableForm("frmoplupld");
	}
});

 
	var ClassificationB=jQuery("#hdnclassificationB").val();
	var ClassificationI =jQuery("#hdnclassificationI").val();
	var ClassificationT =jQuery("#hdnclassificationT").val();
	if(ClassificationB!=null && ClassificationB!='undefined'){
		 jQuery('#chkClassificationB').attr('checked',true);
	}

	 if(ClassificationI!=null && ClassificationI!='undefined'){
		jQuery('#chkClassificationI').attr('checked',true);
	}

	if(ClassificationT!=null && ClassificationT!='undefined'){
		 jQuery('#chkClassificationT').attr('checked',true);
	}


function btnfilemgr_click(){
    var documentNo =jQuery("#cmboplmKeyid").combobox('getValue');
	if(documentNo != null && documentNo != ''){
		var frmMode=jQuery('#hdnMode').val();
		apMode = "create";
		if(frmMode=="View")
		   apMode = "view";
		fileManagerPopUp(documentNo,"OPL","","","",apMode);		
	} else
    {
		 saveForm('frmoplupld','OplUpload_save.oplUpd?filemanager=filemanager');
     }	
}

function frmoplupld_beforeSubmit() {
	var mode = jQuery("#frmMode").val();
	  var classificationB="";
	  var classificationT="";
	  var classificationI="";
		  if(jQuery('#chkClassificationB').is(':checked')==true){
			  classificationB=jQuery("#chkClassificationB").val();
		  }
		  if(jQuery('#chkClassificationT').is(':checked')==true){
			  classificationT=jQuery("#chkClassificationT").val();
		  }
		  if(jQuery('#chkClassificationI').is(':checked')==true){
			  classificationI=jQuery("#chkClassificationI").val();
		  }	  
    var oplId=jQuery("#cmboplmKeyid").val();
    var ds = "?&classificationB="+classificationB+"&classificationT="+classificationT+"&classificationI="+classificationI;  
    return ds;
}

function frmoplupld_successsCallback(result)
{
	 fillWithCurrentDate("dteoplmDate");
	 fillWithCurrentDate("dteoplmPrepareddate");
	 fillWithCurrentDate("dteoplmApproveddate");
	 jQuery('#cmboplmRelated').combobox('setValue','MCH');
	 var mode = result.formMode;
	 var hdnMode=jQuery('#hdnMode').val();
	 var frmMode=jQuery('#frmMode').val();
	 var oplkeyid = result.successData.oplKeyId;
	 var filemanager =result.successData.filemanager;
	 jQuery("#cmboplmKeyid").combobox('setValue',oplkeyid);
	 if(filemanager==true ){
		 var keyid = result.successData.oplKeyId;
		 fileManagerPopUp(keyid,"OPL","","","");

	 }

 if((frmMode=='Create')){
	  //navigateToPrevForm();
	  clearField("frmoplupld");
 }
 else if(mode=="modify"||mode=="view")//||mode=="view"||mode=="approval"
	 {
	   navigateToPrevForm(); 
	 }
	
	 if(result.successData.successData == "bdmmode")
	{
		popFormNavigation();
		popFormNavigation();
		//navigateToPrevForm();
	}
	else
	{
		if(result.mode.frmMode=="modify"&&mode != "category")
		{
		  //navigateToPrevForm("modify_view.opl","Modification");
		}
		else if( mode != null && mode == "category" )
		{
		  var persistentData = result.persistentData;
		  var forwardData = result.forwardData;
		  //alert(forwardData.oplKeyid);
		 navigateToNextForm("oplCategory_input.oplcat","OPL Category",forwardData,persistentData);	
		}
		else if(result.mode.frmMode!= null && result.mode.frmMode!= "View")
		{//alert(1);
			frmOplClear();
		}
		
		 jQuery('#imgOplmAfterimage').attr('src', " ");
		 jQuery('#imgOplmPresentimage').attr('src', " ");
	}
}



 </script>
<form name="frmoplupld" id="frmoplupld" action="" method="post">
<input type="hidden" id="hdnempty" name ="hdnempty" value="R"/>
<div id="wrapper" style="width:100%;">
<div class="main-cntborder" style="height: 500px;width:106%;">
<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;" >
<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
			<div  id="frmoplupldFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
		<div id="frmoplupldFuntKeyIds">
		 <input type="hidden" id="factory" name="cmboplmFactoryid" value="${requestScope.oplTlMst.oplmFactoryid}"></input>
		 <input type="hidden" id="section" name="cmboplmSectionid" value="${requestScope.oplTlMst.oplmSectionid}"></input> 
		 <input type="hidden" id="cell" name="cmboplmCellid" value="${requestScope.oplTlMst.oplmCellid}"></input> 
		 <input type="hidden" id="machine" name="cmboplmMachineid1" value="${requestScope.oplTlMst.oplmMachineid}"></input> 
		 <input type="hidden" id="flid" name="cmboplmFlid" value="${requestScope.oplTlMst.oplmFlid}"></input>
		 <input type="hidden" id="elementId" name="cmboplmElementid"  value="${requestScope.oplTlMst.oplmElementid}"></input>
		 </div>
			</div>
			<div id="oplfunLocation" style="width: 50%; margin-left:30px;"></div>
		
			</div>
			
			<div style="position:relative;">
			 <span  id="OplUpdFilemgr" style="right:100px;top:-24px;position:absolute;right:140px\9;top: -28px\9;" >
             </span> 
          </div> 
		
			
</td>
</tr>
<tr><td colspan="3" >
</td>
</tr>
<tr style=" top:80px" >		
<td valign="top" style="padding-left: 30px; width : 360px;">
			<div   style="padding-left: 0px;">
			<label>Document No</label>
			</div>
			<div class="easyui-paddingbfpx" >
			<input id="cmboplmKeyid" name="cmboplmKeyid" class="easyui-combobox"  style="width:120px;" value="${requestScope.oplkeyid}" disabled="${requestScope.oplFormBean.disableCmbDocumentNo}" />
	        </div>	
	        
   <div  style="margin-top:-43px;margin-left:152px;">
		<div><label class="mandatory-lbl">Date</label></div>
		<div class="easyui-paddingbfpx">
		<input id="dteoplmDate" name="dteoplmDate" class="easyui-datebox"  style="width:116px;" value="${requestScope.opldate}" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/> />  
		</div>
		</div>
		
		
		
		
<!-- 		<div style="padding-top:6px;margin-left:400px; margin-top:-35px"> -->
<!-- <label class="mandatory-lbl">OPL Prepared By</label> -->
<%-- <span><input class="easyui-combobox" id="cmboplmPreparedid"name="cmboplmPreparedid" style="width: 160px; height: 21px;"value="${requestScope.oplTlMst.oplmPreparedid}" --%>
<%-- <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>> --%>
<!-- </span> -->
<!-- </div> -->

	<div style="margin-top:-40px;margin-left:300px;" class="mandatory-lbl" ><label id="lblAbnDesc">Opl Theme</label></div>		
	<div class="easyui-paddingbfpx">
<textarea id="txtoplmTheme" name="txtoplmTheme" rows="5" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;margin-left:300px;resize: none; height: 68px; text-transform: lowercase" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.opltheme}</textarea>
	
	</div>
	
		
		<div style="margin-left:0px;; margin-top:-50px;">
			<label class="mandatory-lbl"> Prepared By </label> 
			</div>
	 		<div class="easyui-paddingbfpx">
         		<input id="cmboplmPreparedid" name="cmboplmPreparedid" class="easyui-combobox" style="width:270px;" value="${requestScope.oplpreparedId}" ></input>
</div>


<!-- 	 <div class="sub-header" style="width:1115px;margin-top:6px; margin-left:-30px;"> Cost Details</div> -->
					
	
	
	<%-- <div style="margin-top:10px;" class="mandatory-lbl" ><label id="lblAbnDesc">Opl Theme</label></div>		
	<div class="easyui-paddingbfpx">
<textarea id="txtoplmTheme" name="txtoplmTheme" rows="5" cols="28" maxlength="250"  title="Maximum Length is 250" style=" width : 350px;" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.oplTlMst.oplmTheme}</textarea>
	
	</div>
	 --%>
	 <div class="sub-header" style="margin-bottom: 10px; width: 810px;margin-top:15px;"><label
			class="mandatory-lbl">Classification</label></div>
	 
		<div style="margin-top: 8px;"><span style="">&nbsp;</span><input
			type="checkbox" id="chkClassificationB" name="chkClassificationB" value="B"
			<c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>
			<c:out value = "${ requestScope.oplFormBean.classificationB == 'B' ? ' checked':''}"/> /><span
			style="padding-left: 12px;"><label>Basic Knowledge</label></span></div>
		<!-- ${requestScope.oplFormBean.classificationB} -->
		<div style="margin-top: 8px;"><span style="">&nbsp;</span><input
			type="checkbox" id="chkClassificationI" name="chkClassificationI"
			value="I"
			<c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>
			<c:out value = "${ requestScope.oplFormBean.classificationI == 'I' ? ' checked':''}"/> /><span
			style="padding-left: 12px;"><label>Improvement Cases</label></span></div>
		<div style="margin-top: 8px;"><span style="">&nbsp;</span><input
			type="checkbox" id="chkClassificationT" name="chkClassificationT"
			value="T"
			<c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>
			<c:out value = "${ requestScope.oplFormBean.classificationT == 'T' ? ' checked':''}"/> /><span
			style="padding-left: 12px;"><label
			style="margin-bottom: 12px;">Trouble/Problem Cases</label></span></div>
		<span id="err_chkClassificationT" class="tpm-errormsg" style=""></span>
		<!-- <span id="err_chkClassificationT" class="tpm-errormsg" "="" ;="" style="padding-left:5px;"></span> -->

		</div>
		
<!-- 		<div class="sub-header " style="margin-bottom: 10px; width: 400px;"><label -->
<!-- 			style="position: absolute">Description of Classification</label><span -->
<!-- 			style="float: right;"></span></div> -->

		<%--   <div style="padding-bottom: 15px;padding-right:10px;"> 
		            <textarea class="txtarea" id="txtoplmoplmClassdescription" name="txtoplmClassdescription" rows="5" cols="6" style="width:355px; height : 68px;resize:none;" <c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/> >${requestScope.oplTlMst.oplmClassdescription}</textarea>
					</div> --%>
 <div  style="margin-top:-85px;margin-left:300px;">
		<div><label class="mandatory-lbl">Classification Description</label></div>
		</div>
		<div style="padding-bottom: 15px; padding-right: 10px;"><textarea
			class="txtarea" id="txtoplmClassdescription"
			name="txtoplmClassdescription" rows="5" cols="6"
			style="width: 355px; height: 68px; resize: none;margin-left:300px;"
			<c:out value = "${ requestScope.oplFormBean.disableForm == true ? ' disabled':''}"/>>${requestScope.opldesc}</textarea>
		</div>

	 
				
				
				   <!--  </div>				
 <div class="sub-header" style="width:1115px;margin-top:6px; margin-left:-30px;"> Work Flow</div> -->
</td>
</tr>
<tr>
<td colspan="3">
</td>
</tr>
<tr>
</tr>
</table>
</div>
</div>
<input type="hidden" id="hdnUploadedFile" name="hdnUploadedFile" value=""/>
<%-- <input type="hidden" id="hdnflid" value="${requestScope.flid}" />
<input type="hidden" id="hdnkeyid" value="${requestScope.keyid}" /> --%>
<input type="hidden" id="hdndate" value="${requestScope.date}" />
<input type="hidden" id="hdncreate" value="${requestScope.Formmode}" /> 
<input type="hidden" id="hdnMode" value="${requestScope.Mode}" /> 
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" /> 
<input type="hidden" id="frmMode" name="Oplformmode" value="${requestScope.oplFormBean.formMode}" />
<input type="hidden" id="hdnapprovalMode" value="${requestScope.approvalMode}" /> 
<input type="hidden" id="hdnOplmStatus" name="hdnOplmStatus" value="${requestScope.oplTlMst.oplmStatus}" /> 
<input type="hidden" id="hdnwhywhyMode" value="${requestScope.whywhymod}" />
<input type="hidden" id="hdncurdate" name="hdncurdate" value="" />
<input type="hidden" id="hdnempid" name="hdnempid" value="" />
<input type="hidden" id="hdnlocnid" name="hdnlocnid" value="" />
<input type="hidden" id="hdnempnamedata" name="hdnempnamedata" value="" />
<input type="hidden" id="hdnoplkeyid" name="hdnoplkeyid" value="${requestScope.oplkeyid}" />
<input type="hidden" id="hdnoplflid" name="hdnoplflid" value="${requestScope.oplflid}" />
<input type="hidden" id="hdnopldate" name="hdnopldate" value="${requestScope.opldate}" />
<input type="hidden" id="hdnoplpreparedId" name="hdnoplpreparedId" value="${requestScope.oplpreparedId}" />
<input type="hidden" id="hdnclassificationT" name="hdnclassificationT" value="${requestScope.classificationT}" />
<input type="hidden" id="hdnclassificationB" name="hdnclassificationB" value="${requestScope.classificationB}" />
<input type="hidden" id="hdnclassificationI" name="hdnclassificationI" value="${requestScope.classificationI}" />

</form>

 