<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

 	
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){
	
	initialiseForm("frmAddInfo");
	processGridnew("addInfo_input.prv","&q=0","addInfoGrid","pager22","","","","addInfoGrid_loadComplete");	
	fillComboBox("frmAddInfo","cmbPmsdPhenomenaid","combo_pmsdPhenomenaid.prv");
	fillComboBox("frmAddInfo","cmbPmsdCauseid","combo_pmsdCauseid.prv");
	jQuery('#addifogrddiv').attr('disabled', 'disabled');
	 jQuery('#frmAddInfo .easyui-text').css('text-transform', 'uppercase');
     jQuery('#frmAddInfo textarea').css('text-transform', 'uppercase');	
	});
	function addInfoGrid_loadComplete(){addInfoGrid_chkdisable(0);}
	jQuery('#chkSecurityCheck').click(function(){

		if(jQuery('#chkSecurityCheck').is(':checked') == true)
		{
			jQuery('#txtPmmsWorkpermitrequired').attr('disabled',false);
			jQuery('#addifogrddiv').attr('disabled', 'disabled');
			addInfoGrid_chkdisable('1');
			//disableField('frmAddInfo','txtPmsdSafetyinstruction');
		}
		else{
			jQuery('#txtPmmsWorkpermitrequired').attr('disabled',true);
			jQuery('#addifogrddiv').attr('disabled', '');
			addInfoGrid_chkdisable(0);
		}
	});
	/**get Value from grid**/
	 
/*function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#addInfoGrid").jqGrid('getRowData');
	alert(jqGridId+"-----------"+checkBoxColName+"-----------"+ckeckForSelColName);
	var jsonArrO='[';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		alert("value"+value);		
		if( value != null  &&  value.trim()  != ""){			
			if(value == '1')				
			{
				jsonArrO += '{';
			for(var colName in row) {
				var cellValue = parseJqGridCellValue(row[colName]);
				alert("cellValue"+cellValue);			
					if(colName == 'txtPsplSftpermitid')
						{
						//alert("colName   " +colName);
						jsonArrO += '"'+colName +'":"' + cellValue+'"';
						}
			}
			jsonArrO +=  "},";
			
		}
	} 
	}
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	alert("jsonArrO--mch   "+jsonArrO);
	return jsonArrO; 
}  
	/**END**/
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
									jQuery('input:checkbox[name=addinfo_checkbox_'+i+']').attr('disabled',false);
									mode = 0;
								}						
							}				
						
					
				//});		
			}
</script>
<form id="frmAddInfo">
<div class="main-cntborder" style="margin-top: 1%;">
<table class="tablealign-center" style="padding-left:1%;">
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
                    <input id="cmbPmsdPhenomenaid" name="cmbPmsdPhenomenaid" class="easyui-combobox"  style="width:300px;" value=""  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Causes</label>                    
                </div> 
                <div class="easyui-paddingbfpx"> 
                    <input id="cmbPmsdCauseid" name="cmbPmsdCauseid" class="easyui-combobox"  style="width:300px;" value=""  >                    
                </div>
                <div  class="easyui-paddingbfpx">
                    <label>Why(If not done)</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="3" cols="34" id="txtPmsdResultifnotdone" name="txtPmsdResultifnotdone"></textarea>
                </div>    
                <div  class="easyui-paddingbfpx">
                    <label>Corrective Action</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                    <textarea rows="3" cols="34" id="txtPmsdCorrectiveaction" name="txtPmsdCorrectiveaction"></textarea>
                </div>   
                
  </td>
  <td width="50%" class="valigncnt" style="padding-left: 10px;">
<div  class="easyui-paddingbfpx">
                    <label>Safety Instruction</label>                    
                </div> 
                <div class="easyui-paddingbfpx">
                <input type="checkbox" id="chkSecurityCheck"><label style="font-size:12px;">Safety Work Permit Required</label><br/>            
                    <textarea rows="3" cols="40" id="txtPmmsWorkpermitrequired" name="txtPmmsWorkpermitrequired" disabled="disabled"></textarea>
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

</div>
</form>