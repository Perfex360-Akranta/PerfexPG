  
      <script>
  jQuery('#prepared').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

  jQuery('#DeptMgr').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

  jQuery('#SectMgr').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

  jQuery('#GroupLead').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
  </script>
  
<div id="AddInfoDiv" title="Additional Information">



<div  ><label>Machine Area</label></div>
<div class="easyui-paddingbfpx" >
  	<input id="MachArea" name="MachArea" class="easyui-text"  type="text" style="width:255px;" value=""  > </div>
  	
<div  ><label>Group No</label></div>
<div class="easyui-paddingbfpx" >
<input type="text" class="easyui-text"; id=time name=time  style=" height : 21px;"/></div>

<div   ><label>Dept.Mgr</label></div>
<div class="easyui-paddingbfpx" >
<input id="DeptMgr" name="DeptMgr" class="easyui-combobox" style="width:255px;"value=""></div> 

<div  ><label>Section.Mgr</label></div>
<div class="easyui-paddingbfpx" >
<input id="SectMgr" name="SectMgr" class="easyui-combobox" style="width:255px;"value=""></div>

<div   ><label>Group Lead</label></div>
<div class="easyui-paddingbfpx" >
<input id="GroupLead" name="GroupLead" class="easyui-combobox" style="width:255px;"value=""></div> 

<div class="easyui-paddingbfpx" >
<input type="button" align="middle" value="Close" style=" height : 21px;" class="easyui-button"/> 
<input type="reset" align="middle" value="Clear" style=" height : 21px;" class="easyui-button"/> </div>
</div>