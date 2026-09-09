
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>


	<script>	 
	jQuery('#cmbTagNo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbFact').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbIssueNo').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbSect').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	  
	jQuery('#cmbTagCls').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
		 
	jQuery('#cmbCatg').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
		 
	jQuery('#cmbCostCen').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbImpact').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbMainType').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'		
	}); 

	jQuery('#cmbMainType').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'		
	}); 
	 	 
	jQuery('#cmbCell').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbEquip').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbStatus').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbTarget').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbBy').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbMaintSect').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 	 
	jQuery('#cmbAssembly').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 

	jQuery('#cmbLoc').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 

	jQuery('#cmbRespon').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
		
	}); 
	 </script>


<div align="center" class="main-cntborder" style="height: 495px;" >

<table  width="95%"  border="0" align="center" >
<tr>
<td width="33%" valign="top" style="padding-left: 40px">
<div ><label>Tag No</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbTagNo" name="cmbTagNo" class="easyui-combobox"  style="width:255px;" value=""  > 
	
	</div>
	<div class="sub-header"><b><label >When and Where</label></b></div>	
	<div  ><label class="mandatory-lbl" >Factory</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbFact" name="cmbFact" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div  ><label class="mandatory-lbl">Section</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbSect" name="cmbSect" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	
	
	<div   ><label class="mandatory-lbl">Cell</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbCell" name="cmbCell" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div   ><label class="mandatory-lbl">Equipment</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbEquip" name="cmbEquip" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	
	<div ><label>Detected Date</label><span style="padding-left:30px; ">By</span></div>
	<div class="easyui-paddingbfpx">
	<input id="Install" class="easyui-datebox" />
	<input id="cmbBy" name="cmbBy" class="easyui-combobox"  style="width:130px;" value=""  >
	</div>
	
	
	
	<div ><label>Assembly</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbAssembly" name="cmbAssembly" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div ><label>Location</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbLoc" name="cmbLoc" class="easyui-combobox"  style="width:255px;" value=""  >
	</div>
	
	<div class="easyui-paddingbfpx sub-header"><b><label >UnSafe Findings</label></b></div>
	<div  class="mandatory-lbl" >Description</div>		
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="Desc" name="Desc"></textarea>
	</div>
	
	</td>
	<td width="33%"   valign="top" style="padding-left: 20px;">
		
	
	<div  ><label class="mandatory-lbl" > Main Type</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbMainType" name=""cmbMainType"" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div   ><label class="mandatory-lbl">Sub Type</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbSubType" name=""cmbSubType"" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div><label>Why(Reason)</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="why" name="why"></textarea>
	
	</div>
	
	<div><label>What Cause</label></div>
	<div>
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="WatCause" name="WatCause"></textarea>
	</div>
		
		
<div class="sub-header" ><b>Causes</b></div>
		
<div   ><label class="mandatory-lbl">Classification</label> 
<span style="padding-left:40px; "><label>Improvement Team</label></span></div>
<div class="easyui-paddingbfpx">
<input id="cmbCls" name="cmbCls" class="easyui-combobox"  style="width:125px;" value=""  > 
<input id="cmbImpTeam" name="cmbImpTeam" class="easyui-combobox"  style="width:125px;" value=""  >	</div>

<div > <label>Category</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbCatg" name="cmbCatg" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
<div ><label>Impact</label></div>
<div class="easyui-paddingbfpx">
<input id="cmbIMpact" name="cmbIMpact" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div  > <label class="mandatory-lbl">  HIRA Ref No</label></div>
    <div class="easyui-paddingbfpx">
    <input id="HIRA" type="text" class="easyui-text"; name="HIRA" value="" style="width: 255px; height : 21px;" ></div>
           
           <div  > <label class="mandatory-lbl">PokeYoke Provided</label></div>
           <div style="padding-top:px;padding-bottom:5px;" >
           <input type="checkbox" />
           <input id="HIRA" type="text" class="easyui-text"; name="HIRA" value="" disabled="disabled" style="width: 235px; height : 21px;"  >
           </div>
				
</td>

<td  width="33%"  valign="top" style="padding-left: 30px">
	
	<div ><label>Responsiblity</label></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbRespon" name="cmbRespon" class="easyui-combobox"  style="width:255px;" value=""  > 
	</div>
	
	<div><label>Immediate Action</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="ImdiAct" name="ImdiAct"></textarea>
	</div>
	<div><label>Effect Leads To</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="EftLead" name="EftLead"></textarea>
	</div>
	
	<div><label>Avoid Recurrence</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="AvdRecur" name="AvdRecur"></textarea>
	</div>
	
<div  class="sub-header"><b><label >Status and Target Details</label></b></div>
<div   ><label class="mandatory-lbl">Status</label><span style="padding-left:85px; " class="mandatory-lbl" ><label class="mandatory-lbl">Target</label></span></div>
	<div class="easyui-paddingbfpx">
	<input id="cmbStatus" name="cmbStatus" class="easyui-combobox"  style="width:125px;" value=""  >
	<span style="padding-left:1px;"><input id="cmbTarget" name="cmbTarget" class="easyui-combobox"  style="width:125px;" value=""  ></span>
	</div>
	
	<div ><label>Counter Measure</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="CountMeas" name="CountMeas" disabled="disabled"></textarea>
	</div>	
	
	<div ><label>Remarks</label></div>
	<div class="easyui-paddingbfpx">
	<textarea rows="1" style="width:255px;resize:none;" cols="" id="Remarks" name="Remarks" ></textarea>
	</div>	
	</td>
<td  width="25%" style="padding-bottom: 40px;" valign="top">


</td>
</tr>
</table>
</div>