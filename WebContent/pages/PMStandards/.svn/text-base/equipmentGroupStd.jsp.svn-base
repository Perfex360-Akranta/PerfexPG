<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <script type="text/javascript" src="js/equipmentGroup.js"></script>
<style>
.eqpstdDiv{
background: url("images/content-bg.png") no-repeat scroll 0 0 transparent;
margin:0;
height:355px;
border: 1px solid #a4a4a4;
text-align: center;
box-shadow: 3px 3px 5px #000000; 
width: 750px;
position:absolute;
z-index: 999;
}

</style>

<form id="frmEqpGroup" name="frmEqpGroup" action="" method="post">
<div id="wrapper" class="eqpFrm">
<div  class="main-cntborder easyui-paddingbtpx"  >
<table width="100%"  style="margin-left:7.5%;">
<tr>  
        <td style="width:33%" >
            <div style="float:left;margin-bottom:10px;margin-top:8px; ">
               
               <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Equipment Group</label>
                </div> 
                <div class="easyui-paddingbfpx" > 
                    <input id="cmbPeqsEqpgroupid" name="cmbPeqsEqpgroupid" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlEqpgrpstandard.peqsEqpgroupid}"  >                    
                </div>  
            </div>
        </td>
          
    </tr>
<tr>
        <td >
			<div class="sub-header" style="text-align: left;width:88%;"><span>General Information</span>
			    <span style="float: right;"><!--
			     <input type="button" value="Equipments" id="btneqpmnt" class="easyui-button" style="height:21px;">
			    <input type="button" value="Additional Information" id="additional_info" class="easyui-button" style="height:21px;">
			    <input type="button" value="File Manager"  id="filemanager" class="easyui-button" style="height:21px;" disabled="disable">
			    <input type="button" value="Resource Planning" id="rsrc_pln" class="easyui-button" style="height:21px;">
			    --><input type="button" value="Back" id="btnBack" class="easyui-button" style="height:21px;">
			    </span>
			    </div>
        </td>
    </tr>
</table>
<table class="tablealign-center" width="100%" align="center" style="margin-left:8%;">

  <tr>
  <td width="40%" valign="top">
  <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Assembly</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPeqsAssemblyid" name="cmbPeqsAssemblyid" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlEqpgrpstandard.peqsAssemblyid}"  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Sub Assembly1</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPeqsSubassemblyid" name="cmbPeqsSubassemblyid" class="easyui-combobox"  style="width:330px;"  >                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Source</label>                    
                </div> 
                <div class="easyui-paddingbfpx " style="border-style:solid;border-width:thin;width:317px;padding:5px 5px 5px 5px;"> 
<!--                    <input type="checkbox" id="chkPeqsSource" name="chkPeqsSource" value="I" <c:out value = "${ requestScope.plmTlEqpgrpstandard.peqsSource == 'I' ? ' checked':''}"/> /><label>Internal(Activity Done in House)</label><br/>-->
                    <input type="checkbox" id="chkPeqsSource" name="chkPeqsSource" value="I" <c:out value = "${ requestScope.plmTlEqpgrpstandard.peqsSource == 'I' ? ' checked':''}"/>/><label>Internal(Activity Done in House)</label><br/>
                    <input type="checkbox" id="chkPeqsSource" name="ch1PeqsSource" value="E" <c:out value = "${ requestScope.plmTlEqpgrpstandard.peqsSource == 'E' ? ' checked':''}"/>><label>External(Activity Done with the help of supplier)</label>
                <span id="err_chkPeqsSource" class="tpm-errormsg" style="margin-bottom:5px;" ></span>
                </div>
                
                <!--<div class="easyui-paddingbfpx" style="padding-top:10px;"> 
                    <input id="cmbPeqs" name="cmbPeqs" class="easyui-combobox"  style="width:333px;" value=""  >                    
                </div> -->
                <div  class="easyui-paddingbfpx">
                    <label>Supplier</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPeqsSupplierid" name="cmbPeqsSupplierid" class="easyui-combobox"  style="width:330px;"    value="${requestScope.plmTlEqpgrpstandard.peqsSupplierid}">                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Maint Section</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPeqsTradeid" name="cmbPeqsTradeid" class="easyui-combobox"  style="width:330px;"  value="${requestScope.plmTlEqpgrpstandard.peqsTradeid}" >                    
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Activity Type</label>                    
                </div>
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPeqsActivitytype" name="cmbPeqsActivitytype" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlEqpgrpstandard.peqsActivitytype}">                    
                </div>                
                <div  class="easyui-paddingbfpx">
                    <label>Sub Type</label>
                    <span  style="margin-left: 130px;font-size:12px;" class="mandatory-lbl">Machine Condition</span>
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="txtPeqsActivitysubtype" name="txtPeqsActivitysubtype"  style="width:140px;"  type="hidden" class="easyui-text"  value="${requestScope.plmTlEqpgrpstandard.peqsActivitysubtype}"/>
                    <input id="txtPeqsActivitysub" name="txtPeqsActivitysub"  style="width:140px;" type="text" class="easyui-text"  value="${requestScope.activitySub }"/>
                    <input type="button" value="..." class="easyui-button" id="subtypebtn" style="height:20px;"/>
                    <input id="cmbPeqsMachinecond" name="cmbPeqsMachinecond" class="easyui-combobox"  style="width:153px;"   value="${requestScope.plmTlEqpgrpstandard.peqsMachinecond}" />
                    <span id="err_cmbPeqsMachinecond" class="tpm-errormsg" style="margin-left:150px;" ></span>
	                
	            </div>                
                <div  class="easyui-paddingbfpx ">
                    <label>What (Freq)</label>
                    <span  style="margin-left: 108px;" >
                    <label id="lblVal" >Frequency Unit</label>
                   
                    </span>
                </div> 
                <div class="easyui-paddingbfpx">     
                <input id="cmbPeqsFrequnit" name="cmbPeqsFrequnit" class="easyui-combobox"  style="width:140px;"   value="${requestScope.plmTlEqpgrpstandard.peqsFrequnit}" >                
<!--                   <input id="txtPeqsFrequencyunit" name="txtPeqsFrequencyunit" class="easyui-combobox"  style="width:172px;" value=""  >-->
                <!--<select id="cmbPeqsFrequnit" name="cmbPeqsFrequnit" class="easyui-combobox" style="width:172px;" required="true"  >
				<option value=""> </option>
				<option value="W"> WEEKLY</option>
				<option value="F"> FORTNIGHTLY</option>
				<option value="M"> MONTHLY</option>
				<option value="Q"> QUARTELY</option>
				<option value="H"> HALF YEARLY</option>
				<option value="Y"> YEARLY</option>
			</select>-->
			<input id="txtPeqsFrequency" name="txtPeqsFrequency" type="text" style="width:155px;margin-left:30px" class="easyui-text" value="${requestScope.plmTlEqpgrpstandard.peqsFrequency}">                    
                </div>                
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>How Much</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <input id="txtPeqsDuration" name="txtPeqsDuration" type="text" class="easyui-text" style="width:140px;" value="${requestScope.plmTlEqpgrpstandard.peqsDuration}" ><label >(Min)</label>
                    <span id="err_txtPeqsHowmuchduration" class="tpm-errormsg" style="margin-left:10px;" ></span>
                </div>
                
                <div  class="easyui-paddingbfpx">
                    <label>Where(Location)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPeqsLocation" name="txtPeqsLocation">${requestScope.plmTlEqpgrpstandard.peqsLocation}</textarea>
                </div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>What(Activity)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPeqsActivity" name="txtPeqsActivity">${requestScope.plmTlEqpgrpstandard.peqsActivity}</textarea>
                </div>
                  
  </td>
  
  <td width="50%" valign="top">
  
  				<div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>How(Method)</label>  
                    <input type="button" class ="easyui-button" value="..." id="howmtd" style="vertical-align:top;height:20px;margin-left:34%;" onclick='howmethodEqp();'/>                  
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPeqsHowmethod" name="txtPeqsHowmethod">${requestScope.plmTlEqpgrpstandard.peqsHowmethod}</textarea>
                    
                </div> 
				<div  class="easyui-paddingbfpx">
                    <label>What(Standard)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="4" cols="37" id="txtPeqsStandard" name="txtPeqsStandard">${requestScope.plmTlEqpgrpstandard.peqsStandard}</textarea>
                </div>
                                
                <div  class="easyui-paddingbfpx">
                <div class="sub-header" style=" margin-bottom: 10px;width:65%;">Spares Information</div>
                    <label id="whtspares">What Spares</label>       
                    <input type="checkbox" id="chkPeqsIssparesreq" name="chkPeqsIssparesreq" value="Y" >             
                </div> 
              
                <div class="easyui-paddingbfpx" style="float: left;">
                <table id="sparesGrid" style="width:100%"><tr><td/></tr></table>
                <div id="pager1">
                </div>
                </div>
                <div class="clearfix"></div>
                <div  class="easyui-paddingbfpx">
                <div class="sub-header" style=" margin-bottom: 10px;width:66%;">Tools Information</div>
                    <label id="whttools">What(Tools)</label>       
                    <input type="checkbox" id="chkPeqsIstoolsreq" name="chkPeqsIstoolsreq" value="Y" <c:out value = "${ requestScope.plmTlEqpgrpstandard.peqsIstoolsreq == 'Y' ? ' checked':''}"/>>  
                    <input type="button"  value="..." id="tooltree" style="margin-left:32%;background-color:lightgray;width:41px;height:25px;" onclick='toolpop();' />           
                </div> 
                <div style="float:right;vertical-align:top;margin-top:20px;margin-right:120px">
          
           </div>
                <div class="easyui-paddingbfpx floatleft" >                
                    <table id="tools" style="width:100%"></table>
                <div id="pager">
                </div>
                </div>
                <div class="clearfix" ></div>
                <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Prepared By</label>       
                                 
                </div> 
                <div class="easyui-paddingbfpx">                
                    <input id="cmbPeqsPreparedbyid" name="cmbPeqsPreparedbyid" class="easyui-combobox"  style="width:330px;" value="${requestScope.plmTlEqpgrpstandard.peqsPreparedbyid}"  >
                </div>
		</td>
  	</tr>
</table>  

</div>
</div>
<div id="sparepopDiv" style="margin:10px;display:none;">
<table id="sprpopGrid" ><tr><td></td></tr></table>
<div id="pager-spr"></div>
<div style="float:right;margin-right:30px;margin-top:10px;"><input type="button" id="spr_close" name="spr_close" class="easyui-button" value="Cancel"/></div>
</div>
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hdnAssmid" name="hdnAssmid"/>

<!--  Open popup window -->
<div id="additional_info_frm" title="Additional Information" class="addInfoPm" style="display: none">
<div id="preloadDIVid1"></div>

</div>

<div id="equipmnt_frm" title="Equipment" class="eqpInfoPm " style="display: none">

	<div id="preloadDIVid1"></div>
	<div class="fl-header" style="width: 750px;">
	   <span id="FrmHeader" style="margin-left: 1%;">Equipments</span>
	   <span style="float:right;">
	  	<img src="images/window-close.png" id="btnCloseeqp">
	  </span>
  </div>
  
	<div id="equipment_div" class="eqpstdDiv">
<!--	<span id="btnCloseeqp" style="float:right;margin: -21px -17px;"><img alt="close" src="images/window-close.png" style="cursor:pointer;vertical-align: top;"></span>-->
	<div class="" style="">
	   <div style="margin-top: 45px;">
			<table id="equipmentGridPop" ></table>
			<div id="pager_equip"></div>
		</div>
		<input type="button" class ="easyui-button" value="OK" id="okbtn" style="margin-left:50px;" />
		<input type="button" class ="easyui-button" value="CLOSE" id="clsebtn" />
	</div>
	</div>
</div>
<input type="hidden" id="hdnEqpGrpKeyId" name="hdnEqpGrpKeyId" value="${requestScope.keyId}"/>
<input type="hidden" id="hdnEqpGrpassId" name="hdnEqpGrpassId" value="${requestScope.assId}"/>
</form>
<!--how Method-->
<form id="frmHowMethodeqpgrp" name="frmHowMethodeqpgrp"  action="" method="post">
<div id="howmethodEqpgrid" style="display:none;" title="How Method">
<table id="list" ></table>
<div id="pager"></div>

<input type="button" class ="easyui-button" value="ADD METHOD" id="addmthd" style="margin-left:30px;width:200px;_width:100px;margin-top:5px;"/>
<input type="button" class ="easyui-button" value="EDIT" id="edtbtn" style="margin-left:50px;"/>
<input type="button" class ="easyui-button" value="CLEAR" id="clrbtn" style="margin-left:50px;" />
<input type="button" class ="easyui-button" value="CLOSE" id="closebtn" />
</div>
</form>
