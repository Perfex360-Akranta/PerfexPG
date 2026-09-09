<script>
 jQuery(document).ready(function(){
	 initialiseForm('frmAssesmentLevelMain');	  
	   viewGrid("","");
	 //  alert("Check");
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
	  if(url.substring(3,12)=='Quadrant3')
		  level ='level3';
	  else if(url.substring(3,12)=='Quadrant4')
		  level ='level4';
	  
	  filterString += "&level="+level+"&mode=grid";
	  //filterString+="&masterid="+masterid;
	  //  ?q=2&masterid="+masterid
		//  processGridnew("NewQuadrant3Assessment_input.newentRpt",filterString+"&masterid="+masterid,"MainassemLevelGrd","mainPager","","assemLevelDBLClick","","assemLevelGridComplete");
	 processGridnew("NewQuadrant3Assessment_input.newentRpt",filterString,"MainassemLevelGrd","mainPager","","assemLevelDBLClick","","assemLevelGridComplete");
	 return true;
 }

 function assemLevelDBLClick(id){
	 var rowData = jQuery("#MainassemLevelGrd").jqGrid('getRowData',id);
	 var keyid = rowData.KEYID;
	 var topicId = rowData.PROGKEY;
	 var masterid=rowData.MASTERKEYID;
	 var flid = rowData.FLID;
	 var lastDt = rowData.LASTUPTDATE;	 
	 lastDt =convertStringToDate(lastDt);
//alert("lastDt"+lastDt)
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
        //alert("backdate:::"+backdate);
	 
	if(backdate <= lastDt) {	
			alert(' Minimum required days for Assement has not Elapsed!');
			return false;
	 }
	  
	 var levelurl="";
	 var caption="";
	 var url = jQuery('#hiddenUrl').val();
	  if(url.substring(3,12)=='Quadrant3'){ 
		  levelurl='NewQuadrant3Assessmentfrm_input.newentRpt';
		  caption ="Quadrant 3 Assessment";
	  }
	  else if(url.substring(3,12)=='Quadrant4'){ 
		  levelurl ='NewQuadrant4Assessmentfrm_input.newentRpt';
		  caption ="Quadrant 4 Assessment";
	  }
	  
	 navigateToNextForm(levelurl+'?&Keyid='+keyid+"&topicId="+topicId+"&flid="+flid+"&masterid="+masterid+"&filterButton=false",caption);
 }
 </script>
 <form id="frmAssesmentLevelMain">
 
	 <div id='wrapperRpt'>
	 	 
	 	<table id='MainassemLevelGrd'><tr><td></td></tr></table>
	 	<div id="mainPager"></div>
	 </div>
	 <input type="hidden" id="mode"/>
	 <input type="hidden" name="hidden" value="${requestScope.masterid}">
 </form>