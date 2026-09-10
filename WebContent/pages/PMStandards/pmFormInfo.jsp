<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
 <!-- <script type="text/javascript" src="js/pmStandards.js">
 /*jQuery(document).ready(function(){
	 //initialiseForm('frmPmStandardform');
	 //jQuery('#submitForm').val('frmPmStandardform');
	 
 }); */

 /*if(jQuery('#chkPmsdSource').is(':checked') == true)
	 {
	 
	 }
 else
	 {
	 
	 }*/
	 
	 function frmPmStandardform_FuntLocHierarchy_SuccessCallBack(keyIds)
	 {
	     var sbuVal = jQuery("#frmPmStandardform input[name='hdnsbu']").val();
	     if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
	         sbuVal = jQuery("#hdnsbu").val();

	     if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
	         jQuery("#frmPmStandardform input[id='factory']").val(sbuVal);
	     }
	 }

	 function frmPmStandardform_beforeSubmit()
	 {
	     console.log("beforeSubmit CALLED, hdnsbu name-scoped:", jQuery("#frmPmStandardform input[name='hdnsbu']").val());
	     console.log("beforeSubmit CALLED, hdnsbu id-scoped:", jQuery("#hdnsbu").val());
	     console.log("factory field before:", jQuery("#frmPmStandardform input[id='factory']").val());

	     var sbuVal = jQuery("#frmPmStandardform input[name='hdnsbu']").val();
	     if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
	         sbuVal = jQuery("#hdnsbu").val();

	     if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
	         jQuery("#frmPmStandardform input[id='factory']").val(sbuVal);
	     }

	     console.log("factory field after:", jQuery("#frmPmStandardform input[id='factory']").val());
	     return true;
	 }
 </script> -->
 
 <script type="text/javascript" src="js/pmStandards.js">
</script>

<script type="text/javascript">
jQuery(document).ready(function(){
    setLoadFormCallBackFrmId('frmPmStandardform');
    initialiseForm("frmPmStandardform");
    jQuery('#submitForm').val('frmPmStandardform');
});

function frmPmStandardform_FuntLocHierarchy_SuccessCallBack(keyIds)
{
    var sbuVal = jQuery("#frmPmStandardform input[name='hdnsbu']").val();
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmPmStandardform input[id='factory']").val(sbuVal);
    }
}

function frmPmStandardform_beforeSubmit()
{
    console.log("frmPmStandardform_beforeSubmit CALLED");

    var sbuVal = jQuery("#frmPmStandardform input[name='hdnsbu']").val();
    if(sbuVal == null || sbuVal == '' || sbuVal == undefined)
        sbuVal = jQuery("#hdnsbu").val();

    if(sbuVal != null && sbuVal != '' && sbuVal != undefined){
        jQuery("#frmPmStandardform input[id='factory']").val(sbuVal);
    }

    return true;
}
</script>
<div id="wrapper">
<form id="frmPmStandardform">
<div id="frmdata" style="margin-left:8%;" >
<div id="" >
<table border="0" class="tablealign-center" align="center" style="margin-left:%;" width=100%>
    <tr>        
    <td colspan='3'>
			 	<div  id="frmPmStandardformFuntKeyIds">
			 	<input type="hidden" id="location" name="cmbPmsdLocationid" value="${requestScope.plmTlStandards.pmsdLocationid}"  ></input>
				<input type="hidden" id="factory" name="cmbPmsdFactoryid" value="${requestScope.plmTlStandards.pmsdFactoryid}"  ></input>
				<input type="hidden" id="section" name="cmbPmsdSectionid" value="${requestScope.plmTlStandards.pmsdSectionid}"  ></input>
				<input type="hidden" id="cell" name="cmbPmsdCellid" value="${requestScope.plmTlStandards.pmsdCellid}"  ></input>
				<input type="hidden" id="machine" name="cmbPmsdMachineid" value="${requestScope.plmTlStandards.pmsdMachineid}"  ></input>
				<input type="hidden" id="flid" name="cmbPmsdFlid" value="${requestScope.plmTlStandards.pmsdFlid}"  ></input>
				<input type="hidden" id="elementId" name="cmbPmsdElementid" value="${requestScope.plmTlStandards.pmsdElementid}"  ></input>
				
				</div>
			 	<div id="pmsdMainfunLocation" style="width:152%;"></div>
	 </td>   
	 </tr>
	 <tr>  
	 <td style="width:33%"  >
            <div id="tt" style="padding-left:0px;">
                <div><label>Cost Center</label></div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbpmsdCostCenter" name="cmbpmsdCostCenter" class="easyui-combobox"  style="width:260px;" value="${requestScope.costcenter}"  >
                </div>
               
            </div>	
        </td>
        <td valign='top'>
            <div style="float:left;">
               
               <div>
                    <label>Equipment</label>
                </div> 
                <div class="easyui-paddingbfpx "> 
                    <input id="cmbPmsdMachineid" name="cmbPmsdMachineid" class="easyui-combobox"  style="width:260px;"  value="${requestScope.plmTlStandards.pmsdMachineid}"  >                    
                </div>  
            </div>
        </td>
        <td id="mouldtd" style="">
       		 <div id="MoldDiv" style="display:none;">
            	<div class="easyui-paddingbfpx">
           			<label>Mould</label>                       
       		   </div> 
                <div class="easyui-paddingbfpx"> 
                     <input id="cmbPmsdMouldid" name="cmbPmsdMouldid" class="easyui-combobox"  style="width:260px"  value="${requestScope.plmTlStandards.pmsdMouldid}"  >     
               </div>
            </div>
        </td>
        
        
        <td style="width:33%;vertical-align: top" >
            <div style="float:left;">
                              
                
            </div>
        </td>
    </tr>
</table>
<div class="sub-header" style="text-align: left;width:82%;float:left; height : 20px;">
	<span>General Information</span>
    <span style="margin-left:50%">
    <input type="button" style="display:none;"value="Additional Information" id="additional_info" class="easyui-button" style="height:21px;"/>
<!--    <input type="button" value="File Manager"  id="filemanager" class="easyui-button" style="height:21px;" disabled="disable">-->
<!--    <input type="button" value="Resource Planning" id="rsrc_pln" class="easyui-button" style="height:21px;">-->
<!--    <input type="button" value="Back" id="btnBack" class="easyui-button" style="height:21px;">-->
   		<!-- <span id="filSpanFilemgr"> </span> -->
   		<!--  <input class="easyui-button" id="btnFilManagePM" type="button" value="File Manager" style="height:21px;"/> -->
    </span>
     <div style="position:relative;">
			 <span  id="filSpanFilemgr" style="right:-22px;top:-24px;position:absolute;right:140px\9;top: -28px\9;" >

     		
             </span> 
            </div> 
    </div>

</div>
<table class="tablealign-center" width="100%" align="center">
  <tr>
  <td width="40%" valign="top">
  <div >
                    <label class="mandatory-lbl">Assembly</label>
                    <input type="checkbox" id="chkPmOtherAssm" style="margin-left:95px" value="Y"/>  <label style="margin-left:3px">Others</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdAssemblyid" name="cmbPmsdAssemblyid" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlStandards.pmsdAssemblyid}"  >                    
                </div>
                <div>
                    <label>Sub Assembly</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdSubassemblyid" name="cmbPmsdSubassemblyid" class="easyui-combobox"  style="width:330px;"  >                    
                </div>
                <div  class="mandatory-lbl">
                    <label>Source</label>                    
                </div> 
                <div class="easyui-paddingbfpx " style="border-style:solid;border-width:thin;width:317px;padding:5px 5px 5px 5px;"> 
<!--                    <input type="checkbox" id="chkPmsdSource" name="chkPmsdSource" value="I" <c:out value = "${ requestScope.plmTlStandards.pmsdSource == 'I' ? ' checked':''}"/> /><label>Internal(Activity Done in House)</label><br/>-->
                    <input type="checkbox" id="chkPmsdSource" name="chkPmsdSource" value="I" <c:out value = "${ requestScope.plmTlStandards.pmsdSource == 'I' ? ' checked':''}"/>/><label>Internal(Activity Done in House)</label><br/>
                    <input type="checkbox" id="chkPmsdSource" name="ch1PmsdSource" value="E" <c:out value = "${ requestScope.plmTlStandards.pmsdSource == 'E' ? ' checked':''}"/>><label>External(Activity Done with the help of supplier)</label>
                <span id="err_chkPmsdSource" class="tpm-errormsg" style="margin-bottom:5px;" ></span>
                </div>
                
                <!--<div class="easyui-paddingbfpx" style="padding-top:10px;"> 
                    <input id="cmbPmsd" name="cmbPmsd" class="easyui-combobox"  style="width:333px;" value=""  >                    
                </div> -->
                <div>
                    <label>Supplier</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdSupplierid" name="cmbPmsdSupplierid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.plmTlStandards.pmsdSupplierid}">                    
                </div>
                <div  class="mandatory-lbl">
                    <label>Maint Section</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdTradeid" name="cmbPmsdTradeid" class="easyui-combobox"  style="width:330px;"  value="${requestScope.plmTlStandards.pmsdTradeid}" >                    
                </div>
                <div  class="mandatory-lbl">
                    <label>Activity Type</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdActivitytype" name="cmbPmsdActivitytype" class="easyui-combobox"  style="width:275px;" value="${requestScope.plmTlStandards.pmsdActivitytype}">
                     <span><input type="button"  value="CBM" id="cbmBtn" class= "easyui-button" style="margin-left:1%;height:20px;"  /></span>
                                        
                </div>                
                <div>
                    <label class="mandatory-lbl fntSize">Equipment Condition</label>
                    <span  style="margin-left: 47px;_margin-left: 45px;" ><label>Sub Type</label></span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdMachinecondition" name="cmbPmsdMachinecondition"   class="easyui-combobox" style="width: 135px; width: 132px\9;"	value="${requestScope.plmTlStandards.pmsdMachinecondition}" />
                    <input id="txtPmsdActivitysub" name="txtPmsdActivitysub"  style="width:120px;margin-left:34px;"  class="easyui-text"  value="${requestScope.activitySub }"/>
                    <input id="txtPmsdActivitysubtype" name="txtPmsdActivitysubtype"   type="hidden" value="${requestScope.plmTlStandards.pmsdActivitysubtype}"/>
                    <input type="button" value="..." class="easyui-button" id="btnsubType" style="height:20px;"/>
<%--                     <input id="cmbPmsdMachinecondition" name="cmbPmsdMachinecondition" class="easyui-combobox"  style="width:153px;"   value="${requestScope.plmTlStandards.pmsdMachinecondition}" /> --%>
                    
                    <span id="err_cmbPmsdMachinecondition" class="tpm-errormsg" style="margin-left:150px;" ></span>
	                
	            </div>                
                <div >
                    <label class="mandatory-lbl">Frequency</label>
                    <span  style="margin-left: 108px;_margin-left: 106px;" >
                    <label id="lblVal" >Frequency Unit</label>
                   
                    </span>
                </div> 
                <div class="easyui-paddingbfpx">
                <input id="cmbPmsdFrequencyunit" name="cmbPmsdFrequencyunit"  	class="easyui-combo" style="width: 135px; width: 132px\9;" value="${requestScope.plmTlStandards.pmsdFrequencyunit}" /> 
<!--                <input id="cmbPmsdFrequencyunit" name="cmbPmsdFrequencyunit" class="easyui-combobox"  style="width:140px;"   value="${requestScope.plmTlStandards.pmsdFrequencyunit}" />                
                   <input id="txtPmsdFrequencyunit" name="txtPmsdFrequencyunit" class="easyui-combobox"  style="width:172px;" value=""  >-->
                <!--<select id="cmbPmsdFrequencyunit" name="cmbPmsdFrequencyunit" class="easyui-combobox" style="width:172px;" required="true"  >
				<option value=""> </option>
				<option value="W"> WEEKLY</option>
				<option value="F"> FORTNIGHTLY</option>
				<option value="M"> MONTHLY</option>
				<option value="Q"> QUARTELY</option>
				<option value="H"> HALF YEARLY</option>
				<option value="Y"> YEARLY</option>
			</select>-->
			<input id="txtPmsdFrequency" name="txtPmsdFrequency" type="text" class="easyui-text" style="width:153px;margin-left:33px;_margin-left:31px" value="${requestScope.plmTlStandards.pmsdFrequency}">                    
                </div>                
                <div>
                    <label>Duration </label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <input id="txtPmsdDuration" name="txtPmsdDuration" type="text" class="easyui-text" style="width:140px;text-align: right;" value="${requestScope.plmTlStandards.pmsdDuration}" ><label >(Min)</label>
                </div>
                
                <div>
                    <label>Location</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPmsdLocation" name="txtPmsdLocation" maxlength= "450">${requestScope.plmTlStandards.pmsdLocation}</textarea>
                </div>
                <div  class="mandatory-lbl">
                    <label>Activity</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPmsdActivity" name="txtPmsdActivity" maxlength= "450">${requestScope.plmTlStandards.pmsdActivity}</textarea>
                </div>
                  
  </td>
  
  	<td width="50%" valign="top">
  
  
  				<div  class="mandatory-lbl" >
                    <span>
  	                 <label>Method</label>
     					<!-- <input type="button" class ="easyui-button" value="Task List" id="btnTaskList" style="margin-left:250px; vertical-align:top;height:20px;" />  -->               	  
					</span>                                      
                </div> 
                 
                <div >
                    <textarea rows="4" cols="37" id="txtPmsdHowmethod" name="txtPmsdHowmethod" maxlength= "450">${requestScope.plmTlStandards.pmsdHowmethod}</textarea>
                    <input type="button" class ="easyui-button" value="..." id="howmtd" style="vertical-align:top;height:20px;margin-left:0%;" onclick='howmethod();'/>
                </div> 
				<div  >
                    <span>
                    <label>Standard / Ideal Condition</label>
                    	
                    </span>
                                        
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" maxlength= "450" id="txtPmsdStandard" name="txtPmsdStandard">${requestScope.plmTlStandards.pmsdStandard}</textarea>
                    
                    
                </div>
                <!-- 
                	<div>
                	
                	<div>
                		<label  style="margin-left: 25px">Min</label>
                		<label style="margin-left: 60px"> Max</label>
                		<label  style="margin-left: 50px"> Target</label>
                    </div>
					<span>
                		<input type="checkbox" id="chkIdealCond" name="chkIdealCond" value="Y" />
                	</span>
                    <input type="text" id="txtPmsdMinValue" name="txtPmsdMinValue" class="easyui-text" disabled="disabled" style="margin-left: 2px;width:70px;" value = "${ requestScope.plmTlStandards.pmsdMinValue}"/>
                    <input type="text" id="txtPmsdMaxValue" name="txtPmsdMaxValue"  class="easyui-text"  disabled="disabled"  style="margin-left: 5px;width:70px;" value = "${ requestScope.plmTlStandards.pmsdMaxValue}"/>
                    <input type="text" id="txtPmsdTarget" name="txtPmsdTarget" class="easyui-text"  disabled="disabled" style="margin-left: 5px;width:70px;" value = "${ requestScope.plmTlStandards.pmsdTarget}"/>
                     
                    </div>
                   -->              
                <div  class="easyui-paddingbfpx" style=" margin-top:10px;">
                <div class="sub-header" style=" margin-bottom: 10px;width:65%;">Spares Information</div>
                    <label id="whtspares">Spares Required?</label>       
                    <input type="checkbox" id="chkPmsdIssparesreq" name="chkPmsdIssparesreq" value="Y" <c:out value = "${ requestScope.plmTlStandards.pmsdIssparesreq == 'Y' ? ' checked':''}"/>>             
                </div> 
              
                <div class="easyui-paddingbfpx" style="float: left;">
                <table id="sparesGrid" style="width:100%"><tr><td/></tr></table>
                <div id="pager1">
                </div>
                </div>
                <div style="position: relative;">
                <span style="right: 170;top: 6;">
                <input type="button"  value="..." id="spareBtn" class= "easyui-button" style="height:20px;"  />
                </span>
                </div>
                <div>
                <div  class="easyui-paddingbfpx" style="margin-top:120px">
                <div class="sub-header" style=" margin-bottom: 10px;width:65%;">Tools Information</div>
                    <label id="whttools">Tools Required?</label>       
                    <input type="checkbox" id="chkPmsdIstoolsreq" name="chkPmsdIstoolsreq" value="Y" <c:out value = "${ requestScope.plmTlStandards.pmsdIstoolsreq == 'Y' ? ' checked':''}"/>>  
                 </div>              
                 
                <div class="easyui-paddingbfpx" style="float: left;">                
                    <table id="tools" style="width:100%"><tr><td/></tr></table>
                <div id="pager">
                </div>
                </div>
                </div>
              	<div style="position: relative;">
                <span style="right: 170px;top: 6;">
                <input type="button"  value="..." id="tooltree" class= "easyui-button"  style=height:20px;" onclick='toolpop();' />
                </span>
                </div>
                <div class="clearfix" ></div>
                <div style="">
                <div  class="easyui-paddingbfpx mandatory-lbl" style="width:60%;_position:relative;top:90px;right:335px;">
                    <label>Prepared By</label>                    
                </div> 
                <div class="easyui-paddingbfpx" style="_position:relative;top:25px;">                
                    <input id="cmbPmsdPreparedbyid" name="cmbPmsdPreparedbyid" class="easyui-combobox"  style="width:330px; " value="${requestScope.plmTlStandards.pmsdPreparedbyid}"  >
                   <!--  <input type="button" class="easyui-button" id="btnMultipleResp" value="..." style="height: 22px;" /> -->
                </div>
                </div>
		</td>
  	</tr>
</table>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnplmSource" name="hdnplmSource" value="${requestScope.plmTlStandards.pmsdSource}"/>
<input type="hidden" id="hdnAssmid" name="hdnAssmid" />
<input type="hidden" id="hdnMachId" name="hdnMachId" value="${requestScope.machineId}"/>
<input type="hidden" id="hdnPmsdPlanconfigstatus" name="hdnPmsdPlanconfigstatus" />
<input type="hidden" id="hdnfrmassmWeeklyId"  value="${requestScope.assmId}"/>

<input type="hidden" id="addGridData" />
<input id="hdnPmsdPhenomenaid" name="hdnPmsdPhenomenaid" type="hidden" >
<input id="hdnPmsdCauseid" name="hdnPmsdCauseid" type="hidden" > 
<input id="hdnPmsdResultifnotdone" name="hdnPmsdResultifnotdone" type="hidden" > 
<input id="hdnPmsdCorrectiveaction" name="hdnPmsdCorrectiveaction" type="hidden" > 
<input id="hdnSecurityCheck" name="hdnSecurityCheck" type="hidden" > 
<input id="hdnPmsdSafetyinstruction" name="hdnPmsdSafetyinstruction" type="hidden" >
<input id="addGridData" name="addGridData" type="hidden" >
 <input type="hidden" id="hdnMultiresp" name="hdnMultiresp" value=""/> 
</div>
 
</form>
<!--Spares Div-->
<div id="Spares_div" style="background-image: url('images/tools/-spares13.jpg');">
<span id="close_spares" style="cursor:pointer;border-style:solid;background-color:LightGrey;border-color:#000;border-width:thin;float:right;">

</span>
</div>
<!--  Open popup window -->

<div id="preloadDIVid2"></div>
<div id="rsrc_pln_frm"  title="Resource Plan" >
<div id="rsrc_pln_div"> </div>
</div>
<input type="hidden" value="${requestScope.plmTlStandards.pmsdAssemblyid}" id="hdnPmsdAssemblyId"/>
	<input type="hidden" value="${requestScope.activitytype}" id="hdnPmsdactivityType"/>
	<input type="hidden" value="${requestScope.tradeId}" id="hdnPmsdtradeId"/>
	<input type="hidden" value="${requestScope.rptMode}" id="hdnrptMode"/>
	<input type="hidden" value="${requestScope.pmcalStatus}" id="hdnpmcalStatus"/>
	<input type="hidden" value="${requestScope.plmTlStandards.pmsdKeyid}" id="hdnPmsdkeyid"/>
	<input type="hidden" id="hdnCBMData" name="hdnCBMData" />
	<input type="hidden" id="hdnAlreadyLoad" name="hdnAlreadyLoad" value=""/>
<!--how Method-->
<form id="frmHowMethod" name="frmHowMethod"  action="" method="post">
	<div id="howmethodgrid" style="display:none;" title="How Method">
		<table id="list" ></table>
		<div id="pagerHow"></div>
			<div style="margin-left: 2%;margin-top:1%;">
				<input type="button" class ="easyui-button" value="ADD METHOD" id="addmthd" style="margin-left:px;width:200px;_width:150px;"/>
				<input type="button" class ="easyui-button" value="EDIT" id="edtbtn" style="margin-left:225px;_margin-left:200px;"/>
				<input type="button" class ="easyui-button" value="CLEAR" id="clrbtn" style="margin-left:10px;" />
				<input type="button" class ="easyui-button" value="CLOSE" id="closebtn" />
			</div>
	</div>
	
</form>
<!--end-->


<div id="selInactiveDate" class="divdelContainer " style="display:none;">
	<center>
	<div style="margin-top:15%;" style="">
	<label style="padding-right: 10px;">Inactivated Date </label>
	<input class="easyui-datebox easyui-text" id="dtePmsdInactivateddate" name="dtePmsdInactivateddate" style="width:150px;" ></input>
	<input type="button" class="easyui-button" id="btnOk" value="OK"/>
	<input type="button" class="easyui-button" id="btnCancel" value="Cancel"/>
	</div>
</center>
</div>
</div>