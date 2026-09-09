<script>
jQuery(document).ready(function(){	
	initialiseForm('frmskillGapReport');
	
	var url = jQuery('#hiddenUrl').val();	
	var flid = "";
	fillComboBox("frmskillGapReport","cmbSkillEmployee","roleBasedEmployeeCombo.commonFilter?&childFlids=Y&flid="+flid);
	fillComboBox("frmskillGapReport","cmbSkillUniquePosition","roleMst.commonFilter?&flid="+flid);	
	
	//setComboDefaultValue("frmskillGapReport", "cmbSkillUniquePosition");
	
	//viewGrid(url,"&firstClick=Y");
	
	jQuery("#btnview").click(function( ){
		
        var empid=getFieldValue('cmbSkillEmployee');
        var unqid=getFieldValue('cmbSkillUniquePosition');
        
        dataString = '&empmid='+empid+'&unqid='+unqid;
        processGridnew("SkillGapReport_input.tl",dataString,"skillgapreportlist","skillgapreportpager");

	});

	
	jQuery("#btnClear").click(function( ){

		clearField('cmbSkillEmployee');
		clearField('cmbSkillUniquePosition');

	});
	setLoadFormCallBackFrmId("frmskillGapReport");
	invokeAfterLoadFormCallBack();
	
});

	function frmskillGapReportcmbSkillUniquePosition_onLoadSuccess() {
		setComboDefaultValue("frmskillGapReport", "cmbSkillUniquePosition");
	}
	function frmskillGapReport_afterLoadCallBack(){
		
		toggleCommonFilter();	
		
	}
	function viewGrid(url,dataString)
	{
		if(validateFilterSelection(dataString))
		{   
			var flid = getFilterValue(dataString, 'flid');
			//alert(flid);
			reloadCombo("frmskillGapReport","cmbSkillEmployee","roleBasedEmployeeCombo.commonFilter?&childFlids=Y&flid="+flid);
			reloadCombo("frmskillGapReport","cmbSkillUniquePosition","roleMst.commonFilter?&flid="+flid);
			
			dataString += '&flid='+flid;
			
			processGridnew("SkillGapReport_input.tl",dataString,"skillgapreportlist","skillgapreportpager");
		    return true;
		}
	}
	 function frmskillGapReportcmbSkillEmployee_onSelect(record)
	 {
	    
		 // alert(" record.id :: "+record.id);
	     processAjaxCalls("SkillGapReport_recall.tl?&type=SkillGap&KEYID="+record.id,"","recallsuccessCallBack","errorCallBack");
		 
	 }
	 
	 function recallsuccessCallBack(result){
		 //alert(" result :: "+result[0][0]);
		 
		 reloadCombo("frmskillGapReport","cmbSkillUniquePosition","roleMst.commonFilter?&keyId="+result[0][0]);
		 
	 }

	 
	function validateFilterSelection(filterString){
		    return true;
	}
	
</script>
<form id="frmskillGapReport" name="frmskillGapReport" >
  <div id="WrapperRpt" style="width:100%">
  <table style="margin-top:-10px;">
  <tr>
  <td>
  <div style="padding-left:10px;">
  <div>
  <label>Employee</label>
  </div>
  <div>
  <input class="easyui-combobox" id="cmbSkillEmployee" name="cmbSkillEmployee"  style=" width :260px;"  value="" />
  </div>
  </div>
  </td>
  <td>
  <div style="padding-left:10px;">
	  <div>
		  <label>Unique Position</label>
	  </div>
	  <div>
		  <input class="easyui-combobox" id="cmbSkillUniquePosition" name="cmbSkillUniquePosition"  style="width :260px;"  value="" />
	  </div>
  </div>
  </td>
  <td>
  <div style="padding-left:10px;">
	  <div>
		  <input class="easyui-button" type="button" id="btnview" name="btnview"  style="width:50px;height:30px;"  value="View" />
		  <span>
		  <input class="easyui-button" type="button" id="btnClear" name="btnclear"  style="width:50px;height:30px;"  value="Clear" />
		  </span>
	  </div>
  </div>
  </td>
  </tr>
  </table>

<div style="padding-left:10px;">
    <table id="skillgapreportlist" ><tr><td></td></tr></table>
	<div id="skillgapreportpager"></div>
	
</div>

</div>    
</form>