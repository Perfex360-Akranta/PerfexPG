<!-- Author:Dhanalakshmi.R
Date:21-9-2012-->
<script type="text/javascript">

jQuery(document).ready(function(){
	//alert('f');
	viewGrid("entskillGapAnalysis_input.skilGap","?q=2");

	jQuery('#btnGraph').click(function(){
	 	var rowid = jQuery("#grdSkillGapAnalysis").jqGrid('getGridParam','selrow');
	 	
	 	if(rowid !=null){
	 		var rowData = jQuery("#grdSkillGapAnalysis").jqGrid('getRowData',rowid);
	 		var keyid=rowData.KEYID;
	 		//alert(keyid);
	 		
	 		var url = "entskillGapRadarChartPage_input.skilGap?assessmentId=" + (rowData.ASMMKEYID != null && rowData.ASMMKEYID != 'undefined' ? rowData.ASMMKEYID :'' )+"&keyid="+keyid;
	 		$('#chkspoke').click(function () {
		 		//alert("spoke");
	 			url+="&isSpoke=true";
	 		});
	 		
	 		$("#chktopic").click(function () {
	 			//alert("topic");
	 		url+="&isSpoke=false";
	 		
	 		});
	 		showGraphData(url,true);		
	}
	
});});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "SkillGapAnalysis";
		
		processGridnew(url,filterString,"grdSkillGapAnalysis","grdSkillGapAnalysispager1",tableCaption,"","","loadComplete","","");
	    return true;
		
	}
	
} 
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
</script>
<form name="frmSkillGapAnalysis" id="frmSkillGapAnalysis" >
<div class="" style="padding-right:20px;">
<span><label>SpokeWise</label><input id="chkspoke" type="checkbox" checked="checked"/></span>
<span><label>TopicWise</label><input id="chktopic" type="checkbox"/></span>
<input id="btnGraph" class="easyui-button"  type="button" value="Graph"/>  </div>
<div id="divGraphContainer" ></div>	
<div id="wrapperRpt">

<table>
	<tr>
	<td id="alertDiv">
		<table id="grdSkillGapAnalysis" ></table>
		<div id="grdSkillGapAnalysispager1"></div></td></tr></table>
	
</div>
<input type="hidden" id="hdnDesc" name="hdnDesc" value=""/>
<input type="hidden" id="hdnStatus" name="hdnStatus" value=""/>
</form> 