 <script>
 jQuery(document).ready(function(){
	 
	 initialiseForm('frmAssesmentLevelMain');	  
	 
	   viewGrid("","");
	  // alert(34);
	  /* for functionalLocation*/
		var factId = jQuery("#frmAssesmentLevel input[id='factory']").val();
		var sectionId = jQuery("#frmAssesmentLevel input[id='section']").val();
		var cellId = jQuery("#frmAssesmentLevel input[id='cell']").val();
		var machId = jQuery("#frmAssesmentLevel input[id='machine']").val();
		var flid = jQuery("#frmAssesmentLevel input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		loadFunctionalLocation("AssmfunLocation","functionalLoc.commonFilter","AssmfunLocationValues","frmAssesmentLevel",dataStr);

		jQuery("#filter_tab").css('display','block');
 });
 
 function viewGrid(url,filterString){
	  url = jQuery('#hiddenUrl').val();
	 
	 var level="";
	  if(url.substring(0,6)=='level3')
		  level ='level3';
	  else if(url.substring(0,6)=='level4')
		  level ='level4';
	  
	  filterString += "&level="+level+"&mode=grid";
	  
	 processGridnew("assesmentLevel_input.tatnd",filterString,"MainassemLevelGrd","mainPager","","assemLevelDBLClick","","assemLevelGridComplete");
	 return true;
 }

 function assemLevelDBLClick(id){
	
	 var rowData = jQuery("#MainassemLevelGrd").jqGrid('getRowData',id);
	 var keyid = rowData.txtEmraKeyid;
	 var topicId = rowData.txtProgkeyid;
	 var flid = rowData.txtFlid;
	 var lastDt = rowData.dteEmraDate;
	 lastDt =convertStringToDate(lastDt);

	var serverTime = srvTime();
	var currentDate = new Date(serverTime);

	var today = currentDate;
	var month,day,year;
	year=today.getFullYear();
	month=today.getMonth();
	date=today.getDate();
	if((month-1)<=0)
		year=today.getFullYear();
	var backdate = new Date(year, month, date-30);
	
	if(backdate <= lastDt) {	
			alert(' Minimum required days for Assement has not Elapsed!');
			return false;
	 }
	 
	 var levelurl="";
	 var caption="";
	 var url = jQuery('#hiddenUrl').val();
	  if(url.substring(0,6)=='level3'){ 
		  levelurl='level3frm_input.tatnd';
		  caption ="Quadrant 3 Assessment";
	  }
	  else if(url.substring(0,6)=='level4'){ 
		  levelurl ='level4frm_input.tatnd';
		  caption ="Quadrant 4 Assessment";
	  }
	  
	 navigateToNextForm(levelurl+'?&keyid='+keyid+"&topicId="+topicId+"&flid="+flid+"&filterButton=false",caption);
 }
 </script>
 <form id="frmAssesmentLevelMain">
 
	 <div id='wrapperRpt'>
	 	 
	 	<table id='MainassemLevelGrd'><tr><td></td></tr></table>
	 	<div id="mainPager"></div>
	 </div>
	 <input type="hidden" id="mode"/>
 </form>