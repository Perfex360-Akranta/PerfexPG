
<script type="text/javascript">	
jQuery(document).ready(function(){
	initialiseForm('frmPMTaskListGrid');
	//alert("ddfdf");
	jQuery('#submitForm').val('frmPMTaskListGrid');
	var keyid=jQuery('#hdnPmtmKeyid').val();
	fillComboBox("frmPMTaskListGrid", "cmbPmtdJobtypeid", "Pmsd_Jobtype.prv");
	processGridnew("pmtasklistalter_input.task","?q=1&keyid="+keyid,"PMTaskGrid","PMTasktPager"," ", "doubleclick","","onloadcompletecallback");
	jQuery("#btnEquipmentLink").click(function(){
		var keyid=jQuery("#hdnPmtmKeyid").val();
		//alert(" keyid" +keyid);
		navigateToNextForm("pmtasklistcreate_input.task?q=1&keyid="+keyid);
		
	});
	jQuery("#btnInsert").click(function() {
		saveForm("frmPMTaskListGrid","pmtasklistalter_save.task");
		//alert("Data Saved Successfully");
	});
	jQuery("#btnViewinactive").click(function() {
		//saveForm("frmPMTaskListGrid","pmtasklistinactive_input.task");
		LoadPopUp("TaskInactive","pmtasklistinactive_input.task?q=2", true,"50%","75%","10%","20%","","Inactive Task");


		});
	});




function doubleclick(id)
{	

    //alert("sgh");
	 var rowData = jQuery("#PMTaskGrid").jqGrid('getRowData',id );
	//var keyid = rowData.KEYID;
	
	 setFieldValue("txtPmtdTask",rowData.Task);
	 setFieldValue("txtPmtdStandard",rowData.Standard);
	 setFieldValue("txtPmtdMethod",rowData.Method);
	 setFieldValue("cmbPmtdJobtypeid",rowData.ActivityType);
	 //alert("job"+rowData.ActivityType);
	 setFieldValue("cmbPmtdFrequency",rowData.FREQ);
	 setFieldValue("hdnPmtdKeyid",rowData.Keyid);
    
}

function onloadcompletecallback()
{
	var rowData = jQuery("#PMTaskGrid").jqGrid('getRowData',1 );
	}

function Taskdelete_onClose(){
	jQuery("#PMTaskGrid").trigger("reloadGrid");
	return true;
}

function frmPMTaskListGrid_successsCallback(result){
		 	jQuery("#PMTaskGrid").trigger("reloadGrid");
		 	setFieldValue("txtPmtmTaskgroup",result.TaskGroup);
		 	setFieldValue("hdnPmtmKeyid",result.TaskGroupKeyid);
			//alert(result.TaskGroup);
		
}

function ButtonFormatter(id, options, rowObject) {
   
	var keyid = rowObject[1];
	return '<input id="btnDelete" class="grdButton" name="btnDelete" type="button" keyid="'+keyid+'" onclick=removeRecord("'+keyid+'"); style="text-align:left"/>';
 	

}

function removeRecord(keyid){
	if(keyid!=null && keyid!='undefined' && keyid!=""){
		LoadPopUp("Taskdelete","pmtasklistdelete_input.task?q=2&Keyid="+keyid, true,"30%","40%","20%","33%","","Inactive"); 
	}
		
}
function remove_successCallBack(result)
{
	jQuery("#PMTaskGrid").trigger("reloadGrid");
}
function remove_errorCallBack()
{
}



</script>


<form id="frmPMTaskListGrid" name="frmPMTaskListGrid">
<div id="wrapper"   >
<div  align="center">
 <table cellspacing="5px">
<tr>
 <td colspan="3">
       <div class="easyui-paddingbfpx" >
		   <label  class="mandatory-lbl">Task List Group</label>  </div>
	          <div class="easyui-paddingbfpx" style="" >
	           <input id="txtPmtmTaskgroup" name="txtPmtmTaskgroup" type="text" value="${requestScope.TaskGroup}" style=" width :800px; width : 148px\9;"class="easyui-text" />
	    </div>
  </td>
  </tr>
  
  <tr>
    <td colspan="3">
       <div class="sub-header" style="text-align: left;float:left;width:790px; width:800px\9;height:18px\9;position:relative;margin-right:4%;margin-left:0%\9;">
				<span style="position:absolute;">Details</span>
      	</div>
    </td>
 </tr>

<tr>
  <td>
    <div class="easyui-paddingbfpx"> <label class="mandatory-lbl" > Task</label></div>
       <div class="easyui-paddingbfpx">
        <textarea  id="txtPmtdTask" name="txtPmtdTask" style="resize:none;width: 200px  ;text-transform:uppercase; width: 200px\9 ; height:40px;" maxlength="100" ></textarea>
       </div>
  </td>


<td>
          <div class="easyui-paddingbfpx"> <label > Standard  </label></div>
     <div class="easyui-paddingbfpx">
        <textarea  id="txtPmtdStandard" name="txtPmtdStandard" style="resize:none;width: 200px  ;text-transform:uppercase; width: 200px\9 ; height:40px;" maxlength="100" ></textarea>
        </div>
</td>



 <td>
    <div class="easyui-paddingbfpx"> <label > Method</label></div>
      <div class="easyui-paddingbfpx">
        <textarea  id="txtPmtdMethod" name="txtPmtdMethod" style="resize:none;width: 222px  ;text-transform:uppercase; width: 200px\9 ; height:40px;" maxlength="100" ></textarea>
      </div>
  </td>
</tr>


<tr>
   <td style="margin-top: 20px">
      <div class="easyui-paddingbfpx"> <label > Activity Type</label></div>
        <div class="easyui-paddingbfpx">
         <input class="easyui-combobox" style="width:200px; width:200px\9;" id="cmbPmtdJobtypeid" name="cmbPmtdJobtypeid" value="" />
      </div>
   </td>


 <td style="margin-top: 20px">
    <div class="easyui-paddingbfpx"> <label > Frequency</label></div>
      <div class="easyui-paddingbfpx" >
        <select  id="cmbPmtdFrequency" name="cmbPmtdFrequency"  class="easyui-combo" style="width:120px;width:100px\9; height: 21px;">
				    <option > </option>
					<option value="W"> Weekly</option>
					<option value="F">Fort-Nightly</option>
					<option value="M">Monthly</option>
					<option value="Q">Quarterly</option>
					<option value="H">Half-Yearly</option>
					<option value="Y">Yearly</option>
        </select>
        <span style="">
         <input type="button" class="easyui-button" value ="Insert" name="btnInsert" id="btnInsert" style="height: 24px;width:80px; text-align:center "/>
      </span>
    </div>
</td>

    <td style="margin-top: 20px;margin-top: 19px\9;">
       <div class="easyui-paddingbfpx" style="margin-top: 20px" >
         <input type="button" class="easyui-button" value ="View Inactive" name="btnViewinactive" id="btnViewinactive" style="height: 24px; text-align:center "/>
		  <span style="">
		    <input type="button" class="easyui-button" value ="Equipment Link" name="btnEquipmentLink" id="btnEquipmentLink" style="height: 23px;margin-left:10px;width:100px\9;text-align:center "/>
		  </span>
	  </div>
	</td>
	
</tr>
</table>
	<div style="margin-right: 30px">
		<table id="PMTaskGrid" ></table> 
	</div>
	<div id="PMTasktPager"></div>
	  <input type="hidden" id="mode" />
	  <input id="hdnPmtmKeyid" type="hidden" name="hdnPmtmKeyid" value="${requestScope.keyid}">
	  <input id="hdnPmtdKeyid" type="hidden" name="hdnPmtdKeyid">
	</div>
 </div>
</form>