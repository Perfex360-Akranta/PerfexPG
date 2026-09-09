 <script>
 jQuery(document).ready(function(){
	 
	 initialiseForm('frmAssesmentLevel');
	 jQuery('#submitForm').val('frmAssesmentLevel');
	 jQuery('#hdnlevel').val("");
	 
	 // alert(level );
	 
      fillComboBox("frmAssesmentLevel","cmbAssmUniquepos","roleMst.commonFilter");
      
	  fillComboBox("frmAssesmentLevel","cmbTopicId","progKey_fillcombo.prgEnt");
	 // fillComboBox("frmtrngattendnce", "cmbProgKeyid", "progKey_fillcombo.empatt"); 
	  /* for functionalLocation*/
		var factId = jQuery("#frmAssesmentLevel input[id='factory']").val();
		var sectionId = jQuery("#frmAssesmentLevel input[id='section']").val();
		var cellId = jQuery("#frmAssesmentLevel input[id='cell']").val();
		var machId = jQuery("#frmAssesmentLevel input[id='machine']").val();
		var flid = jQuery("#frmAssesmentLevel input[id='flid']").val();
		var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		
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
	  if(url.substring(0,6)=='level3'){ 
		  level ='level3'; 
		  jQuery('#hdnLevel').val("3");
	  }
	  else if(url.substring(0,6)=='level4'){ 
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
	 var gridData ="";
	    if(getGridSelectArray("assemLevelGrd").trim().length==0)
	    {
	    	/*
	    	if("save"==mode) 
	    		alert("No Data Selected to Save ");
	    	else if("delete"==mode) 
	    		alert("No Data Selected to Delete");
	    	*/
	    		return false;
	     }else{ 
		    if(getGridSelectArray("assemLevelGrd").trim()!="]" || getGridSelectArray("assemLevelGrd").trim().length>1  )
		    {
			     gridData ="&asslevel="+getGridSelectArray("assemLevelGrd")+"&curLevel="+jQuery('#hdnLevel').val()+"&topicId="+getFieldValue("cmbTopicId");
			  //   alert("&topicId="+getFieldValue("cmbTopicId")); 
		    }
		    else
		  	  return false;
	     }
	    gridData = gridData.replace('undefined','');
	    
	   // alert(gridData);
      return gridData ;
 }

 /**Function for  selecting/Unselecting  Row**/
 function assemLevelGrd_selectRow(id){
	 var  loggeduser = jQuery('#hdnLoggeduser').val();
		// alert(id+"   "+loggeduser );
		setTimeout(function(){
		setFieldValue("assessdBY_assemLevelGrd_"+id, loggeduser);
		jQuery("#assemLevelGrd").jqGrid('setCell',id,'txtEmraRattingby',loggeduser);
		},1500);
 	/*if(jQuery('#jqg_assemLevelGrd_'+id).is(':checked'))
 		assmchkboxCheck(id);
 	else
 		assmchkboxUnCheck(id);*/
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
	 // alert(filterString);
	 
	 processGridnew(url,filterString,"assemLevelGrd","assemLevelpager",""," ","","assemLevelGridComplete");
 }
 function frmAssesmentLevel_FuntLocHierarchy_SuccessCallBack(keyIds)
 { 
	  var selFlid = keyIds.flId;
	  
	   reloadCombo("frmAssesmentLevel","cmbAssmUniquepos","roleMst.commonFilter?&flid="+selFlid);
	  var roleId  = getFieldValue("cmbAssmUniquepos");
	  var topicId  = getFieldValue("cmbTopicId");
	  var dataStr = "?q=2&level="+getLevel()+"&mode=trans"+"&selFlid="+selFlid+"&topicId="+topicId;
	  var level =  jQuery('#hdnLevel').val();
	  if (level == '3')
	  {
	  	reloadCombo("frmAssesmentLevel","cmbTopicId","progKeylevel3skill_fillcombo.prgEnt?&flid="+selFlid);
	  }
	  
	  if (level == '4')
	  {
	  	reloadCombo("frmAssesmentLevel","cmbTopicId","progKeylevel4skill_fillcombo.prgEnt?&flid="+selFlid);
	  }
	  
	  
	  
	  if(roleId.trim().length>0)
		  dataStr +="&roleId="+roleId;
	  viewGrid("assesmentLevel_input.tatnd",dataStr);
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
	 viewGrid("assesmentLevel_input.tatnd",dataStr);
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
	 <input type="hidden" id="hdnLoggeduser" value="${requestScope.loggedUser}"/>
	  
 </form>