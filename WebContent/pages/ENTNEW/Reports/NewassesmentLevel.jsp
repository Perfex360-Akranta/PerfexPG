 <script>
 jQuery(document).ready(function(){
	
	 initialiseForm('frmAssesmentLevel');
	 jQuery('#submitForm').val('frmAssesmentLevel');
	 jQuery('#hdnlevel').val("");
	 var keynew=jQuery('#hdnKeyid').val();
	 var masterid=jQuery("#hdnmasterid").val();
	 //alert("masterid"+masterid);
     fillComboBox("frmAssesmentLevel","cmbAssmUniquepos","roleMst.commonFilter"); 
	//  fillComboBox("frmAssesmentLevel","cmbTopicId","topic_fillcombo.tcl?&flId="+flid);
	  fillComboBox("frmAssesmentLevel","cmbTopicId","topic_fillcombo.tcl");
	 // fillComboBox("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.empatt"); 
	  /* for functionalLocation*/
		var factId = jQuery("#frmAssesmentLevel input[id='factory']").val();
		var sectionId = jQuery("#frmAssesmentLevel input[id='section']").val();
		var cellId = jQuery("#frmAssesmentLevel input[id='cell']").val();
		var machId = jQuery("#frmAssesmentLevel input[id='machine']").val();
		var flid = jQuery("#frmAssesmentLevel input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		viewGrid("","");
		loadFunctionalLocation("AssmfunLocation","functionalLoc.commonFilter","AssmfunLocationValues","frmAssesmentLevel",dataStr);
		 jQuery('#btndelLeveAss').click(function(){ 
			 var gridDat = getGridData("delete");
			  
			 deleteRecord("frmAssesmentLevel","deletelevel_input.tatnd?&delRec="+gridDat);
		 });
		 jQuery("#filter_tab").css('display','none');
		 
			 
 });
 function frmAssesmentLevel_deleteSuccessCallback(result){
	 alert(result.successData.msg);
	 jQuery('#assemLevelGrd').trigger("reloadGrid");
 }
 function frmAssesmentLevel_successsCallback(result)
	{
		jQuery("#assemLevelGrd").trigger("reloadGrid");
	}
 function getLevel(){
	 var url = jQuery('#hiddenUrl').val();
	 var level; 
	  if(url.substring(3,12)=='Quadrant3'){ 
		  level ='level3'; 
		  jQuery('#hdnLevel').val("3");
	  }
	  else if(url.substring(3,12)=='Quadrant4'){ 
		  level ='level4';
		  jQuery('#hdnLevel').val("4");
	  }
	  return level ;
 }
 function frmAssesmentLevel_beforeSubmit()
 {  
	
	 if(getFieldValue("cmbTopicId").trim().length>0 )
		
		return getGridData("save");
	 
	 else
		alert("Select Topic"); 
 }
 
 function getGridData(mode){
	   var selArray =  jQuery("#assemLevelGrd").jqGrid('getGridParam', 'selarrrow');
		var selrowid="";
		 var jsonArr='';
		 var jsonArr1='';
	  if(selArray !=null && selArray!=" " && selArray!=""){		
		for(var i=0;i<selArray.length;i++)
		{
			selrowid=selArray[i];
		   var Keyid =jQuery("#assemLevelGrd").jqGrid('getCell', selrowid,"KEYID");
		   //alert(Keyid);
		   var Criteriasplit= Keyid.split(',');
		 	for(var k=0;k<Criteriasplit.length;k++){
			 	var Keyidval=Criteriasplit[k]; 
			 	var keyvalSplit=Keyidval.split(";");
			 	var keyvalu=keyvalSplit[0];
			 	jsonArr += '"'+keyvalu + '" ,';	
			 	//alert("jsonArr "+jsonArr);
	 	    }
		jsonArr = jsonArr.substring(0,jsonArr.length-1);
		jsonArr += ',';
	}	
	jsonArr = jsonArr.substring(0,jsonArr.length-1);
	//alert("The jsonArr:::"+jsonArr);
    var UpdateList=jsonArr;
    //alert("The UpdateList:::"+UpdateList);
	 var gridData ="";
	 //alert("The gridData:::"+gridData);
	 var gridval=getGridSelectArray('assemLevelGrd');
	// console.log("GRIDVAL = ", gridval);
	 //alert("GRIDVAL = " + gridval);
	    if(gridval.trim().length==0)
	    {
	    		return false;
	     }
	    else{ 	    
		    if(gridval.trim().length>1)
		    {
			     gridData ="&asslevel="+getGridSelectArray("assemLevelGrd")+"&curLevel="+jQuery('#hdnLevel').val()+"&topicId="+getFieldValue("cmbTopicId")+"&UpdateList="+UpdateList;
		    }
		    else{
		    	
		  	  return false;
		    }	     }
	    gridData = gridData.replace('undefined','');
       return gridData;
 }
 }
 
 
 jQuery("#dteEmraDate").datebox({  	   
		onSelect:function(recordid)
			{
			var sysdate=jQuery("#hdncurrentdate").val();
			var currdate=jQuery("#LASTUPTDATE").datebox('getValue');
			var approvalDate =jQuery("#dteEtcmCompletedDate").datebox('getValue');
			var currentDate = getServerDateTime();
		if(convertStringToDate(approvalDate)!=convertStringToDate(currdate))
				{
			if(convertStringToDate(sysdate) < convertStringToDate(approvalDate))
			{  
			      popupCommonErrorMsg('Should Not Enter the Future Date');
			       fillWithCurrentDate(dateCtrl);
					return false;
			}
			else{
				if(convertStringToDate(currdate) > convertStringToDate(approvalDate))
					{
					popupCommonErrorMsg('Should Not Exceed the Calendar Date');
					fillWithCurrentDate(dateCtrl);
					return false
					}
				
			}
				}   	
			} 
		}); 


 /**Function for  selecting/Unselecting  Row**/
 function assemLevelGrd_selectRow(id){
	 var rowData = jQuery("#assemLevelGrd").jqGrid('getRowData',id);
    var lastDt = rowData.LASTUPTDATE;
	//alert(lastDt);
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
	  
	 var  loggeduser = jQuery('#hdnLoggeduser').val();
	
		setTimeout(function(){
		setFieldValue("assessdBY_assemLevelGrd_"+id, loggeduser);
		},1500); 
 }
 /**End**/
 
 
 
 
 function assmchkboxCheck(rowId)
{

	jQuery("#assemLevelGrd").jqGrid('setCell',rowId,'SELEVAL','1');	
}
function assmchkboxUnCheck(rowId)
{

	jQuery("#assemLevelGrd").jqGrid('setCell',rowId,'SELEVAL','0');
}
 function viewGrid(url,filterString){
	// alert(url);
	 var masterid=jQuery("#MASTERKEYID").val();
	 //processGridnew(url,"?q=2&masterid="+masterid,"assemLevelGrd","assemLevelpager",""," ","","assemLevelGridComplete");
	 processGridnew(url,filterString,"assemLevelGrd","assemLevelpager",""," ","","assemLevelGridComplete");
 }
 function frmAssesmentLevel_FuntLocHierarchy_SuccessCallBack(keyIds)
 { 
	  var selFlid = keyIds.flId;
	  var masterid=jQuery("#MASTERKEYID").val();
	  reloadCombo("frmAssesmentLevel","cmbAssmUniquepos","roleMst.commonFilter?&flid="+selFlid);
	  var roleId  = getFieldValue("cmbAssmUniquepos");
	  var topicId  = getFieldValue("cmbTopicId");
	  var dataStr = "?q=2&level="+getLevel()+"&mode=trans"+"&selFlid="+selFlid+"&topicId="+topicId+"&masterid="+masterid;
	  var level =  jQuery('#hdnLevel').val();
	/*  if (level == '3')
	  {
	  	  reloadCombo("frmAssesmentLevel","cmbTopicId","topic_fillcombo.tcl?&flId="+selFlid);
	  }
	  
	  if (level == '4')
	  {
	  	reloadCombo("frmAssesmentLevel","cmbTopicId","topic_fillcombo.tcl?&flId="+selFlid);
	  }	  */
	  if(roleId.trim().length>0)
		  dataStr +="&roleId="+roleId;
	  viewGrid("NewQuadrant3Assessment_input.newentRpt",dataStr);
	  /*if(getFieldValue("cmbTopicId").trim().length>0 ){
			 jQuery('#AssmfunLocation').before("<div id='hdnLayer' style='position:absolute;height:50%;opacity:0.4;width:100%;'></div>");
			 disableField("frmAssesmentLevel", "cmbTopicId");
		 }*/
 }
 
 function frmAssesmentLevelcmbAssmUniquepos_onSelect(record){
  
	 var selFlid = jQuery("#frmAssesmentLevel input[id='flid']").val();
	 reloadCombo("frmAssesmentLevel","cmbTopicId","topic_fillcombo.tcl?&roleId="+record.id); 
	 var dataStr = "?q=2&level="+getLevel()+"&mode=trans"+"&roleId="+record.id;
	 if(selFlid.trim().length>0)
		  dataStr +="&selFlid="+selFlid;
	 viewGrid("NewQuadrant3Assessment_input.newentRpt",dataStr);
 }
 function selectedFlid_onsuccesscallback(result)
 { 
	 //alert("result  "+result!=null && result!="" && result!=" " );
	 if(result!=null  && result[0][0].length>0) 
		 loadFunctionalLocation("AssmfunLocation","functionalLoc.commonFilter","AssmfunLocationValues","frmAssesmentLevel","&flid="+result[0][0]);
	 /*else{
		 loadFunctionalLocation("AssmfunLocation","functionalLoc.commonFilter","AssmfunLocationValues","frmAssesmentLevel","&flid=");
     }*/
	 
}
 function frmAssesmentLevelcmbTopicId_onSelect(record){ 
	 var selFlid = jQuery("#frmAssesmentLevel input[id='flid']").val();
	 var roleId  = getFieldValue("cmbAssmUniquepos"); 
	 processAjaxCalls("trngProgSelect_Select.tcl?progId="+record.id,"","selectedFlid_onsuccesscallback");
	 var dataStr = "?q=2&level="+getLevel()+"&mode=trans"+"&topicId="+record.id;
	  if(selFlid.trim().length>0)
		  dataStr +="&selFlid="+selFlid;
	  if(roleId.trim().length>0)
		  dataStr +="&roleId="+roleId ;
	 // viewGrid("assesmentLevel_input.tatnd",dataStr);
 }
jQuery("#chkSelectAll").click(function(){
		   show_winMask(1);
	       fnSelectAllEmp();
		   show_winMask(0);
	});


	function  fnSelectAllEmp(){
			var row = jQuery("#assemLevelGrd").jqGrid('getDataIDs');
			for(var i=0;i<row.length;i++)
			 {
				if(jQuery("#chkSelectAll").is(':checked')== true){
				    jQuery('#assemLevelGrd').setSelection(row[i], true);
				    jQuery('input:checkbox[id=jqg_assemLevelGrd_'+row[i]+']').attr('checked',true);
				    jQuery("#assemLevelGrd").jqGrid('setCell',row[i],'selctVal','1');
				 }
				 else {
					    jQuery('#assemLevelGrd').setSelection(row[i], false);
					    jQuery('input:checkbox[id=jqg_assemLevelGrd_'+row[i]+']').attr('checked',false);
					    jQuery("#assemLevelGrd").jqGrid('setCell',row[i],'selctVal','0');

				 }
			 }	
		}
 
 </script>
 <form id="frmAssesmentLevel">
	 <div id='wrapperRpt'>
	 	<div style="position:relative;">
			<div id="frmAssesmentLevelFuntKeyIds"  >							
				<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
				<input type="hidden" id="section" name="cmbsection"  value=""></input>
				<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
				<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
				<input type="hidden" id="flid"    name="cmbAsmmTrarKeyid"    value="${requestScope.flid}"></input>						
			</div>						
			<div id="AssmfunLocation" style="width:89.2%;width:84%\9;"></div>
			<table>
				<tr>
					<td>
						<div><label id=" "  class=" " >Unique Position</label></div>
						<div class="easyui-paddingbfpx" >
						<input id="cmbAssmUniquepos" name="cmbAssmUniquepos" class="easyui-combobox" style="width:175px;"  value=" "/>
						</div>
					</td>
					<td>
						<div>
				 		<label id=" "  class="mandatory-lbl" >Topic</label>
				 		</div>
						<div class="easyui-paddingbfpx"  >
						<input id="cmbTopicId" name="cmbTopicId" class="easyui-combobox" style="width:205px;" value="${requestScope.topicId}">
						 
						</div>
	 	    <div>			
         <td valign="bottom">
		<input type="checkbox" id="chkSelectAll" name="chkSelectAll" style="margin-left:10px;" />
		<input type="text" value="Select All" disabled="disabled" style="border:0px solid black;  font-size:11px ; width:60px;height:20px;color:black;background-color:#c9c9ec;font-weight:bold;text-align:left; " />
	     </td>
		</div>
					</td>
					<td>
						<div style="margin-top:2px; display: none;">
					 		<input type="button" class="easyui-button" value="Delete" id="btndelLeveAss"/>
					 	</div>
					</td>
				</tr>
			</table>
			
 
			
	 	</div>
	 	
	 	<table id='assemLevelGrd'><tr><td></td></tr></table>
	 	<div id="assemLevelpager"></div>
	 </div>
	 <input type="hidden" id="mode"/>
	 <input type="hidden" id="hdnLevel" name="hdnLevel"/>
	 <input type="hidden" id="hdnKeyid" value="${requestScope.keyid}"/>
	 <input type="hidden" id="hdnLoggeduser" value="${requestScope.loggedUser}"/>
	 <input type="hidden" id="hdnmasterid" value="${requestScope.masterid}"/>
	 <input type="hidden" id="hdncurrentdate" name="hdncurrentdate" value=""></input>  
 </form>