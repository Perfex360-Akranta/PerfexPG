
 <script type="text/javascript" >
jQuery(document).ready(function(){
	fillComboBox("frmEqpGroup","cmbEqpgroupid","equipmentgroup.commonFilter");
	setFieldValue('cmbEqpgroupid',jQuery('#hdnEqpGrpKeyId').val(),'frmEqpGroup');
	 setLoadFormCallBackFrmId('frmEqpGroup');	
	
});
function frmEqpGroup_afterLoadCallBack(){
	navigateToNextForm("assmWeekly_input.eqpStd?q=2&filterString=''&loadContentDivId=Loadassemblygridfrm&preLoadContentDivId=preloadDIVid&formId=frmEqpGroup");
}
function frmEqpGroup_beforeCloseCurrentForm(){
	/*if( ! jQuery(".layout-split-west").is(":visible"))
		jQuery('#mainlayout').layout('expand','west');
	setFormMainHeader("Home");*/
	popFormNavigation();
	return true;
}
</script>
<form id="frmMainEqpGroup">
<div id="mainEqpGroup">
<div id="wrapper" style="width:100%;">
<div  class="main-cntborder easyui-paddingbtpx" style="" >
	
<table border="0" class="tablealign-center" style="margin-left:3.5%;">
	 <tr>  
        <td style="width:33%" >
            <div style="float:left;margin-top:8px; ">
               
               <div  class="easyui-paddingbfpx mandatory-lbl">
                    <label>Equipment Group</label>
                </div> 
                <div class="easyui-paddingbfpx" > 
                    <input id="cmbEqpgroupid" name="cmbEqpgroupid" class="easyui-combobox"  style="width:330px;" value=""  >                    
                </div>  
            </div>
        </td>
          
    </tr>
    
    <tr>
    	<td style="float:left">
			 <div class="easyui-paddingbfpx" style="float:left; ">
			 
			<div id="preloadDIVid"></div>
			<div id="preloadDIVid3"></div>
			<div id="preloadDIVid5"></div>
			<div id="Loadassemblygridfrm"></div>
			<div id="Loadactivitygridfrm"></div>
			<div id="LoadeqpStdfrm"></div>
			 </div>
			<!-- <input type="hidden" id="fltrStr" value="${requestScope.filterString_assmweek}"/>-->
		</td>
    </tr>
</table>
</div>
</div>
</div>
<input type="hidden" id="hdnEqpGrpKeyId" name="hdnEqpGrpKeyId" value="${requestScope.keyId}"/>
<input type="hidden" id="chkVal" name="chkVal" />
<input type="hidden" id="getiCol" name="getiCol"/>
<input type="hidden" id="getcellContent" name="getcellContent"/>


</form>