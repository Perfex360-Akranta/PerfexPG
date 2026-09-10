<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!--<script type="text/javascript" src="js/pmStandards.js"></script>-->
 <script>

	jQuery(document).ready(function(){
		var url = jQuery('#hiddenUrl').val();
		initialiseForm("frmadditionalInformation");
		jQuery('#submitForm').val('frmadditionalInformation'); 
		fillComboBox("frmadditionalInformation","cmbPmsdPhenomenaid","combo_pmsdPhenomenaid.prv");
		fillComboBox("frmadditionalInformation","cmbPmsdCauseid","combo_pmsdCauseid.prv");
		processGridnew("addInfo_input.prv","&q=0","addInfoGrid","pager22","","","","addInfoGrid_loadComplete");
	});
	function addInfoGrid_loadComplete(){
		 
		addInfoGrid_chkdisable(0);
		  
		 var row = jQuery("#addInfoGrid").jqGrid('getDataIDs');
		 var cm = jQuery("#addInfoGrid").jqGrid("getGridParam", "colModel");
		 
		 for(var i=0;i<row.length;i++)
		 {
			 var colVal = jQuery("#addInfoGrid").jqGrid('getCell',row[i],"txtPsplcheckVal");
			
			 if(colVal == '1'){
			 jQuery('#addinfo_checkbox_'+ row[i]).attr('checked',true);
			 addInfoGrid_chkdisable(1);
			 }
		}
		
	}
	jQuery('#chkSecurityCheck').click(function(){
		
		if(jQuery('#chkSecurityCheck').is(':checked') == true)
		{
			jQuery('#txtPmsdSafetyinstruction').attr('disabled',false);
			jQuery('#addifogrddiv').attr('disabled', 'disabled');
			addInfoGrid_chkdisable('1');
			//disableField('frmAddInfo','txtPmsdSafetyinstruction');
		}
		else{
			jQuery('#txtPmsdSafetyinstruction').attr('disabled',true);
			jQuery('#addifogrddiv').removeAttr('disabled');
			addInfoGrid_chkdisable(0);
		}
	});
	function addInfoGrid_chkdisable(mode){		
		/*jQuery("#jqgh_addInfoGrid_select").click(function()
				{*/	
							//alert("mode" +mode);
							var allRows = jQuery("#addInfoGrid").jqGrid('getRowData');
							if(mode == 0)
							{			
								for(i= 0;i<=allRows.length;i++)
								{
									jQuery('input:checkbox[name=addinfo_checkbox_'+i+']').attr('disabled',true);
								 }
							}
							else
							{
								for(i= 0;i<=allRows.length;i++)
								{
									
									jQuery('input:checkbox[id=chkSecurityCheck]').attr('checked',true);
									jQuery('#txtPmsdSafetyinstruction').removeAttr('disabled',false);
									jQuery('input:checkbox[name=addinfo_checkbox_'+i+']').removeAttr('disabled');
									mode = 0;
								}						
							}				
						
					
				//});		
			}
	function cboxSprFormatter(id, options, rowObject)
	{
		var id = options.rowId;
		//alert(rowObject[0]+" "+rowObject[1]+" "+rowObject[2]);
	  	//return '<input  type="checkbox" id="addinfo_checkbox" name="addinfo_checkbox" + (rowObject[1]=="True" ? 'checked':'') +'"' onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
	  	return '<input id="addinfo_checkbox_'+ id +'" name ="addinfo_checkbox_'+ id +'" '+ (rowObject[0]=="1" ? 'checked':'') + ' type="checkbox" ' + ' onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+ id +'\')}"/>';
	}

	function addInfoDiv_onClose(){
		var griddata = convertJqGridToJSONObjectArr('addInfoGrid');
		var phe =getFieldValue('cmbPmsdPhenomenaid');
		var cause =getFieldValue('cmbPmsdCauseid');
		var res =getFieldValue('txtPmsdResultifnotdone');
		var corr =getFieldValue('txtPmsdCorrectiveaction');
		var saf =getFieldValue('txtPmsdSafetyinstruction');
		setFieldValue('hdnPmsdPhenomenaid',phe);
		setFieldValue('hdnPmsdCauseid',cause);
		setFieldValue('hdnPmsdResultifnotdone',res);
		setFieldValue('hdnPmsdCorrectiveaction',corr);
		setFieldValue('hdnPmsdSafetyinstruction',saf);
		setFieldValue('addGridData',griddata);
		return true;
	}	
 </script>
<form id="frmadditionalInformation">
<!--<div id="additional_info_frm" title="Additional Information"  class="flPopUpBox" style="display: none;background: none repeat scroll 0 0 #E0F3FA;width:68%;height:95%;">-->

<!--<div id="preloadDIVid1"></div>-->

<div id="additionalinfo" >
<!--<span id="btnClose" style="float:right;margin: -21px -17px;"><img alt="close" src="images/window-close.png" style="cursor:pointer;vertical-align: top;"></span>-->
<div id="mainBorder" class="" style="margin-top:1%;">
<table class="tablealign-center" style="padding-left:1%;padding-top:2%;">
  <tr>
    <th ><div class="sub-header" style="margin-bottom: 10px;text-align: left"><span>Additional Information</span></div></th>
    <th><div class="sub-header" style="margin-bottom: 10px;text-align: left"><span>Safety Permit</span></div></th>
  </tr>
  <tr >
  <td width="50%" style="padding-right: 10px;">
  <div  class="easyui-paddingbfpx">
                    <label>Phenomena<input type="checkbox"></label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdPhenomenaid" name="cmbPmsdPhenomenaid" class="easyui-combobox"  style="width:300px;" value="${requestScope.plmTlStandards.pmsdPhenomenaid}"  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Causes</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdCauseid" name="cmbPmsdCauseid" class="easyui-combobox"  style="width:300px;" value="${requestScope.plmTlStandards.pmsdCauseid}"  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Why(If not done)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="3" cols="34" id="txtPmsdResultifnotdone" name="txtPmsdResultifnotdone">${requestScope.plmTlStandards.pmsdResultifnotdone}</textarea>
                </div>    
                <div  class="easyui-paddingbfpx">
                    <label>Corrective Action</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="3" cols="34" id="txtPmsdCorrectiveaction" name="txtPmsdCorrectiveaction">${requestScope.plmTlStandards.pmsdCorrectiveaction}</textarea>
                </div>   
                
  </td>
  <td width="50%" class="valigncnt" style="padding-left: 10px;">
<div  class="easyui-paddingbfpx">
                    <label>Safety Instruction</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                <input type="checkbox" id="chkSecurityCheck" value="Y" /><label style="font-size:12px;">Safety Work Permit Required</label><br/>            
                    
                     <textarea rows="3" cols="40" id="txtPmsdSafetyinstruction" name="txtPmsdSafetyinstruction" disabled="disabled">${requestScope.plmTlStandards.pmsdSafetyinstruction}</textarea>
                </div>          
                <div  class="easyui-paddingbfpx">                
                    <label>Type of work</label>
                </div> 
                <div class="easyui-paddingbfpx" id="addifogrddiv">
                <table id="addInfoGrid" style="width:100%"><tr><td/></tr></table>
                <div id="pager22">
                </div>
                </div>	
                
</td>
  </tr>
</table>
<div style="height: 2%;"></div>
</div>

</div>

<!--</div>-->
<!--<input type="hidden" id="addInfoDiv" name="addInfoDiv"  />-->
</form>