  <script>
  jQuery(document).ready(function(){
	jQuery(function(){
		jQuery('#tools').datagrid({			
			width:250,
			height:100,
			nowrap: false,
			striped: false,
			collapsible:true,
			url:'datagrid_data.json',
			sortName: 'code',
			sortOrder: 'desc',
			remoteSort: false,
			
			frozenColumns:[[
              {title:'Tool Name-Code',field:'Employee Code',width:230}
			]],
			
			pagination:false,
			rownumbers:true
			
		});
		
	});

	jQuery("#btnBack1").click(function (){
		
		jQuery("#JHCCLITS_machineArea").show();
		jQuery("#JHCCLITS").hide();
		jQuery("#MachineAreaGrid").hide();
		jQuery("#EquipmentArea").hide();
		jQuery("#page2").show();
	});

/*jQuery("#btnBack1").click(function (){
		
		jQuery("#JHCCLITS_machineArea").hide();
		jQuery("#JHCCLITS").hide();
		jQuery("#MachineAreaGrid").hide();
		jQuery("#page2").show();
		
	});*/

  jQuery('#cmbMaintSect').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 
	
	jQuery('#cmbClassif').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	jQuery('#cmbFreq').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	

	jQuery('#cmbShift').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	jQuery('#Desig').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	jQuery('#Respon').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	jQuery('#prepared').combobox({
		mode:'remote',
		url:'combobox_data.comp',
		valueField:'id',
		textField:'text'
	}); 

	/*jQuery("#AddInfo").click(function (){

		jQuery("#JHCCLITS_machineArea").hide();
		jQuery("#JHCCLITS").hide();
		jQuery("#MachineAreaGrid").hide();
		jQuery("#AddInfoDiv").show();
		
		/*jQuery("#JHCCLITS_machineArea").hide();
		jQuery("#JHCCLITS").hide();
		jQuery("#AddInfoDiv").show();
			
	});*/

	jQuery( "#AddInfo" ).click(function() 
			{
				jQuery( "#AddInfoDiv" ).show( );
				jQuery( "#AddInfoDiv" ).dialog({
					autoOpen: false,
					modal: true,
					width:"400",
					height:"300"
				});
			});


	
  });
</script>


<!--<div style="padding-top:5px;padding-bottom:5px; padding-left: 100px;" class="sub-header" ><b>Equipment Area: C Hook</b> 
<span style="padding-left:420px;"> 
<input type="button" value="File Manager" class="easyui-button" style=" height : 21px;">
<input type="button" value="Additional Information" class="easyui-button" style=" height : 21px;" id="AddInfo">

<input type="button" value="Back" class="easyui-button" style=" height : 21px;" id="btnBack1">
</span> </div> --><br><br>
<form name="frmJhClitStd" id="frmJhClitStd" action="" method="post">
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="normaltxt">  
       <tr width="50%" >
       
          <td valign="top" style="padding-left: 100px;"> 
          
            <div   ><label>Maint.Section</label> </div>
            <div class="easyui-paddingbfpx" >
            <input id="cmbMaintSect" name="cmbMaintSect" class="easyui-combobox"style="width:255px;"value=""></div>
            
           <div   ><label class="mandatory-lbl">Main Part</label></div>
           <div class="easyui-paddingbfpx" >
            <textarea rows="2" style="width: 255px;" cols="" id="MainPart" name="MainPart"></textarea></div>
            
            <div  ><label  class="mandatory-lbl">Item</label></div>
            <div class="easyui-paddingbfpx" >
            <textarea rows="2" style="width: 255px;" cols="" id="Item" name="Item"></textarea></div>
            
            <div  ><label  class="mandatory-lbl">Classification</label>
            <span style="padding-left:50px;"><label>What (Freq)</label></span></div>
            <div class="easyui-paddingbfpx" >
            <input id="cmbClassif" name="cmbClassif" class="easyui-combobox"style="width:125px;"value="">
            <span style="padding-left:5px;"><input id="cmbFreq" name="cmbFreq" class="easyui-combobox" style="width:125px;" value=""></input></span></div>
            
            <div  ><label  class="mandatory-lbl">Shift</label></div>
            <div class="easyui-paddingbfpx" >
            <input id="cmbShift" name="cmbShift" class="easyui-combobox" style= "width:255px;" value=""></input></div>
            
            <div  ><label  class="mandatory-lbl">Method</label></div>
            <div class="easyui-paddingbfpx" >
            <textarea rows="2" style="width: 255px;" cols="" id="Method" name="Method"></textarea></div>
            
          </td>
          <td valign="top">
          <div  class="easyui-paddingbfpx" ><label>What (Tools)</label> 
          <input type="checkbox" /><label>Tools Information</label></div>
          <div  class="easyui-paddingbfpx" ><table id="tools"></table></div>
          
          <div   ><label>Block Diagram Reference</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="BlkDigRef" name="BlkDigRef"></textarea></div>
          
          <div   ><label>Standard</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="Std" name="Std"></textarea></div>
          
          <div   ><label class="mandatory-lbl">Time</label></div>
          <div class="easyui-paddingbfpx" >          
          <input type="text" id=time name=time  style=" height : 21px;" class="easyui-text"/>(in minutes)</div>
          
          <div ><label class="mandatory-lbl" >Next Due</label>
          <span style="padding-left:70px;"><label>JH Standard Effective Form</label></span></div>
          <div class="easyui-paddingbfpx" >
          <input id="NextDue" class="easyui-datebox easyui-text" ></input>
          <span style="padding-left:5px;"><input id="JHEffect" class="easyui-datebox easyui-text"></input></span></div>   
                 
          </td>
          <td valign="top">
          
          <div   ><label class="mandatory-lbl">Shift Time</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="ShiftTime" name="ShiftTime"></textarea></div>
          
          <div   ><label>If Not Done</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="notdone" name="notdone"></textarea></div>
          
          <div  ><label>Corrective Action</label></div>
          <div class="easyui-paddingbfpx" >
          <textarea rows="2" style="width: 255px;" cols="" id="CorrectAct" name="CorrectAct"></textarea></div>
          
          <div  ><label>Designation</label></div>
          <div class="easyui-paddingbfpx" >
          <input id="Desig" name="Desig" class="easyui-combobox"style="width:255px;"value=""></div>
          
          <div   ><label class="mandatory-lbl">Responsibility</label> </div>
          <div class="easyui-paddingbfpx">
          <input id="Respon" name="Respon" class="easyui-combobox"style="width:255px;"value=""></div>
          
          <div  ><label class="mandatory-lbl">Prepared By</label> </div>
          <div class="easyui-paddingbfpx">
          <input id="prepared" name="prepared" class="easyui-combobox"style="width:255px;"value=""></div>  
                  
          </td>
       </tr>

</table>



<div id="AddInfoDiv" title="Additional Information" style="display: none;">



<div><label>Machine Area</label></div>
<div class="easyui-paddingbfpx" >
<input id="MachArea" name="MachArea" class="easyui-combobox" style="width:255px;" value="">
</div> 

<div  ><label>Group No</label></div>
<div class="easyui-paddingbfpx" >
<input type="text" class="easyui-text" id=time name=time  style=" height : 21px;"/></div>

<div><label>Dept.Mgr</label></div>
<div class="easyui-paddingbfpx" >
<input id="DeptMgr" name="DeptMgr" class="easyui-combobox" style="width:255px;" value=""></div> 

<div ><label>Section.Mgr</label></div>
<div class="easyui-paddingbfpx" >
<input id="SectMgr" name="SectMgr" class="easyui-combobox" style="width:255px;" value=""></div>

<div ><label>Group Lead</label></div>
<div class="easyui-paddingbfpx" >
<input id="GroupLead" name="GroupLead" class="easyui-combobox" style="width:255px;" value=""></div> 

<div class="easyui-paddingbfpx" >
<input type="button" align="middle" value="Close" style=" height : 21px;" class="easyui-button"/> 
<input type="reset" align="middle" value="Clear" style=" height : 21px;" class="easyui-button"/> </div>
</div>
</form>