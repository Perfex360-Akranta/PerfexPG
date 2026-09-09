<!-- Author:Dhanalakshmi.R
Date:21-9-2012-->
<script type="text/javascript">

jQuery(document).ready(function(){
	//alert('f');
	viewGrid("entskillGapAnalysis_input.skilGap","?q=2");

	jQuery('#btnGraph').click(function(){
	 	var rowid = jQuery("#grdSkillGapAnalysis").jqGrid('getGridParam','selrow');
	 	if(rowid==null||rowid==""||rowid=='')
 		{
 		alert('Select Employee');
 		return false;
 		}
		
	 	else if(rowid !=null){
	 		var rowData = jQuery("#grdSkillGapAnalysis").jqGrid('getRowData',rowid);
	 		var keyid=rowData.KEYID;
	 		var evalDate=rowData.FROMDATE;
	 		var empId=rowData.EMPMKEYID;
	 		//alert(empId);
	 		//alert(evalDate);
	 		//alert(keyid);
	 		//alert(rowData.ASMMKEYID);
	 		
	 		var url = "entskillGapRadarChartPage_input.skilGap?assessmentId=" + (rowData.ASMMKEYID != null && rowData.ASMMKEYID != 'undefined' ? rowData.ASMMKEYID :'' )+"&keyid="+keyid+"&evalDate="+evalDate+"&empId="+empId;
	 		if(jQuery('#chkspoke').is(':checked') == true)
	 		 {
	 			
		 		//alert("Criteria");
	 			url+="&isSpoke=true";
	 		}
	 		
	 		else if(jQuery("#chktopic").is(':checked') == true) {
	 			//jQuery('#chkspoke').attr('checked', false);
	 			//alert("topic");
	 		url+="&isSpoke=false";
	 		
	 		}
	 		showGraphData(url,true);		
		}
	});
	
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "SkillGapAnalysis";
		
		processGridnew(url,filterString,"grdSkillGapAnalysis","grdSkillGapAnalysispager1",tableCaption,"","","loadComplete","","");
	    return true;
		
	}
	
} 
jQuery("#chkspoke").click(function(){
	if (jQuery("#chkspoke").is(':checked'))
    {
       // alert("Checked");
        jQuery('#chktopic').attr('checked', false);
    }
    else
    {
       // alert("Unchecked");
        jQuery('#chktopic').attr('checked', true);
    }

	
});
jQuery("#chktopic").click(function(){
	if (jQuery("#chktopic").is(':checked'))
    {
       // alert("Checked");
       jQuery('#chkspoke').attr('checked', false);
    }
    else
    {
        //alert("Unchecked");
        jQuery('#chkspoke').attr('checked', true);
    }	
});
function loadComplete()
{
	 var row = jQuery("#grdSkillGapAnalysis").jqGrid('getDataIDs');
	 var cm = jQuery("#grdSkillGapAnalysis").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {
		 var rowData = jQuery("#grdSkillGapAnalysis").jqGrid('getRowData',row[i]);
		 var setArea=rowData.PARENTNAMES;
		 //alert(setArea);
		 setArea=setArea.replace("/",'');
		 //alert(setArea);
		 jQuery("#grdSkillGapAnalysis").jqGrid('setCell',row[i],cm[5].name,setArea);
		 
	 }
	
	}
function validateFilterSelection(filterString){
	
	return true;
}

function frmFilter_enableDisableSuccessCallBack()
{
	jQuery('input:checkbox[name=chkDatewise]').attr('checked',false);
	jQuery('input:checkbox[name=chkMonthwise]').attr('checked',true);
	jQuery("#dtetoDate").datebox("setValue","");
	jQuery("#dtefromDate").datebox("setValue","");
	readOnlyFields('dtetoDate');
	readOnlyFields('dtefromDate');
	
	jQuery('#disableFuncLoc').val('disable');
	jQuery("#frmFilterfunLocation").click(function() { 		
		 jQuery("#functLocHierarPopupId").dialog('close'); });
}

</script>
<form name="frmSkillGapAnalysis" id="frmSkillGapAnalysis" >

<div style="margin-top:25px;margin-left:45px;">
<label><b>Select Employee and Click Graph To Generate Graph</b></label>
<!--<div style="float:right;margin-top:13px;">-->
<span style="padding-left: 30px;">
<label>CriteriaWise</label><input id="chkspoke" type="checkbox" />
<label>TopicWise</label><input id="chktopic" type="checkbox" checked="checked"/>
<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>
</span>
</div>


<div id="wrapperRpt" style="margin-top:0px;">

		<table id="grdSkillGapAnalysis" ></table>
		<div id="grdSkillGapAnalysispager1"></div>
		
		
		 </div> 
	
	

<input type="hidden" id="hdnDesc" name="hdnDesc" value=""/>
<input type="hidden" id="hdnStatus" name="hdnStatus" value=""/>
</form> 