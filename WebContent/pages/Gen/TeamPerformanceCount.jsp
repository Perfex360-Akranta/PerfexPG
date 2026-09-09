<script  type="text/javascript">
jQuery.noConflict();
jQuery(document).ready(function(){	
	var url = jQuery('#hiddenUrl').val();
	
//	initialiseForm('frmTeamCountCount');
	formatDateBox('dteFromdate','dd-MMM-yyyy');
	formatDateBox('dteTodate','dd-MMM-yyyy');
//	fillComboBox("frmTeamCountCount","cmbAet","sectionCombo.commonFilter");
//	fillComboBox("frmTeamCountCount","cmbPact","cellCombo.commonFilter");
//	fillComboBox("frmAdminPanel", "cmbLocnid", "location.commonFilter");
//	fillComboBox("frmAdminPanel", "cmbEmployeetype", "Combo_UserType.apdb");
	
	initialiseForm('frmTeamCount');

fillComboBox("frmTeamCount", "cmbAet", "sectionCombo.commonFilter");
fillComboBox("frmTeamCount", "cmbPact", "cellCombo.commonFilter");

fillComboBox("frmTeamCount", "cmbLocnid", "location.commonFilter");
fillComboBox("frmTeamCount", "cmbEmployeetype", "Combo_UserType.apdb");

	
	viewGrid(url,"");
	var url =jQuery('#hiddenUrl').val();
	jQuery("#functionallocn").hide();
    var factId = jQuery("#frmTeamCount input[id='factory']").val();
	var sectionId = jQuery("#frmTeamCount input[id='section']").val();
	var cellId = jQuery("#frmTeamCount input[id='cell']").val();
	var machId = jQuery("#frmTeamCount input[id='machine']").val();
	var flid = jQuery("#frmTeamCount input[id='flid']").val();

 if(flid !=null )
    {	 
 //  alert("Loadfn");
    	loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount","&flid="+flid);
   // 	reloadCombo("frmTeamCount","cmbpact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
    	//reloadCombo("frprocessAjaxmMocProject","cmbaet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
 	   
    }
    else
 	   {
    	//////////////////////////////alert("ELSELoadfn");
 	   loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.nmoc","TeamCounFunctionalLocnValues","frmTeamCount","");
 	   }

 
 jQuery("#btnview").click(function(){

		var url = jQuery('#hiddenUrl').val();
		//alert(url);
		var fromdate=jQuery("#dteFromdate").datebox('getValue');
		if(fromdate.length<=1) {
			popupCommonErrorMsg("Please Select From Date")
			return false;
		}
		//if(fromdate.length<1)
			
	    var todate=jQuery("#dteTodate").datebox('getValue');
	    if(todate.length<=1) {
			popupCommonErrorMsg("Please Select ToDate Date");
			return false;
		}
	    var Functon = jQuery("#frmTeamCount input[id='flid']").val();
	    
		
	  //  alert("flid"+Functon);
	   
	    var Click="Y";
	    var EmployeeType=getFieldValue("cmbEmployeetype");
	  //  alert("EmployeeType"+EmployeeType);
		processGridnew(url,"&fromdate="+fromdate+"&todate="+todate+"&Functon="+Functon+"&Click="+Click+"&EmployeeType="+EmployeeType,"TeamPerCount","pager","","","","");
	//processGridnew(url,"&fromdate="+fromdate+"&todate="+todate+"&Functon="+Functon+"&Click="+Click+"&EmployeeType="+EmployeeType,"TeamPerCount","pager","","","","");

	});

	
});



function viewGrid(url,filterString){	
 if(validateFilterSelection(filterString)){		 
		var url = jQuery('#hiddenUrl').val();
	//	alert(url)
		filterString+="&q=2";
	//	alert(filterString);
	//	alert("url:"+url+",filterString:"+filterString);
		processGridnew(url,filterString,"TeamPerCount","pager","","","","");
		return true;
	}
	return false;
}
function validateFilterSelection(filterString){
	return  true;
}



 function  frmTeamCountcmbPact_onSelect(record){	
 
	// alert("recordid "  +  record.id);
		var dataStr="&cellId="+record.id;
		//alert("click"+dataStr);
		loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
	  
		var cellId = jQuery("#frmTeamCount input[id='cell']").val();
		var flid = jQuery("#frmTeamCount input[id='flid']").val();

		reloadCombo("frmTeamCount","cmbAet","sectionCombo.commonFilter?cellId="+cellId+"&flid="+flid);

		
 }
 
 function  frmTeamCountcmbAet_onSelect(record){	
	 
	// alert("recordid "  +  record.id);
		var dataStr="&sectId="+record.id;	
	
		loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
	
	 
  }
 function  frmTeamCountcmbLocnid_onSelect(record){	
	 
	// alert("recordid "  +  record.id);
		var dataStr="&locnId="+record.id;	
	
 loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
		
	 
  }
 
 /*
 
jQuery("#cmbPact").combobox({
	onSelect : function(recordid) {
       
		alert("recordid.id   +  1 "  );
		
		var dataStr="&cellId="+recordid.id;
		//alert("click"+dataStr);
		loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
	
	}
});

 jQuery("#cmbAet").combobox({
				onSelect : function(recordid) {
			    ////alert("click");
			    alert("recordid.id  +  2"  );
					var dataStr="&sectId="+recordid.id;	
					//alert("click"+dataStr);
					loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
				
				}
			});
 
 
 jQuery("#cmbLocnid").combobox({
		onSelect : function(recordid) {
	    ////alert("click");
			var dataStr="&locnId="+recordid.id;	
		//alert("click"+dataStr);
			loadFunctionalLocation("TeamCounFunctionalLocn","functionalLoc.apdb","TeamCounFunctionalLocnValues","frmTeamCount",dataStr);
		
		}
	});

*/

function frmTeamCount_FuntLocHierarchy_SuccessCallBack(keyIds){
 var factId = "";
 var pbuId=keyIds.pbuId;
////////////////////////////////////alert("pbuid"+pbuId);

	var sectId = keyIds.sectId;	
   // alert("section id  " + sectId);
	var cellId=keyIds.cellId;
//	 alert("cellId id  " + cellId);
	var sbuId=keyIds.sbuId;
	////alert(sbuId);
	var LocnId=keyIds.locnId;
	//alert(LocnId)
	var locnId = jQuery("#frmTeamCount input[id='location']").val();
	//alert(locnId)
	var flid = jQuery("#frmTeamCount input[id='flid']").val();
	//alert(flid);
	setFieldValue('hdnflid',flid);
	setFieldValue('cmbLocnid',locnId);
	setFieldValue('cmbPact',keyIds.cellId);
	setFieldValue('cmbAet',keyIds.sectId);
	setFieldValue('hdncellId',keyIds.cellId);
	setFieldValue('hdnsectionId',keyIds.sectId);
	setFieldValue('hdnlocnid',keyIds.sectId);
	setFieldValue('hdnsbuId',keyIds.sbuId);
	setFieldValue('hdnpbuId',keyIds.pbuId);
	//readOnlyFields("cmbLocnid");
   //processGridnew("TeamMember_input.apdb","?q=2flid="+flid+"&flid="+flid, "TeamPerformGrid", "pager","","","","loadCompleteAttendanceGrid");
	reloadCombo("frmAdminPanel","cmbLocnid","location.commonFilter?locnId="+locnId+"&flid="+flid);
    reloadCombo("frmTeamCount","cmbPact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
    reloadCombo("frmTeamCount","cmbAet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
	setFunctionalLocWidth('frmTeamCount','625px');
}


</script>

<form id=frmTeamCount>
<div id="wrapperRpt">
<div id="functionallocn">
  <div  id="frmTeamCountFuntKeyIds">
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbaet" value="${requestScope.dmt}"></input>
					<input type="hidden" id="cell" name="cmbpact" value="${requestScope.jh}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="flid" value="${requestScope.flid}"></input>	
				</div>
				
			 	<div id="TeamCounFunctionalLocn" style="width:100%;margin-top:0px;margin-left:-35px"></div>
		</div>
	 	<table style="margin-top:-16px;">
		<tbody>
		<tr>
		<td>

<div id="completedBlock" Style="margin-top:-12px">
<div style="margin-left:0px;margin-top:-12px;">
	<span style="margin-left:0px;"><label class="">DMT</label></span>
	<span style="margin-left:250px;"><label class="">JH</label></span>
</div>
    
     
<div style="margin-left:0px;margin-top:5px;">
		<span><input class="easyui-combo" id="cmbAet" name="cmbAet" style="width: 260px;"	value="" /></span>
    <span style="margin-left:20px;"><input class="easyui-combo" id="cmbPact" name="cmbPact" style="width: 260px;"	value="" /></span>
  
</div>
</div>
			</td>
			 
	    <td>
		<div>
	  	<label class="mandatory-lbl"style="margin-left:15px;margin-top:5px;">From Date</label>                       
	     </div> 
			    <div style="margin-left:15px;margin-bottom: 5px;">
			        <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteFromdate" name="dteFromdate" value=""/>
			        </div>
			 </td>
			 
			 <td>
			    <label class="mandatory-lbl" style="margin-left:15px;margin-bottom:1px;">To Date</label>  
			    <div style="margin-left:15px;margin-bottom: 5px;">
			         <input class="easyui-text" style=" margin-top: 0px; width : 87px; height: 24px" id="dteTodate" name="dteTodate" value=""/>
			          </div>
			  </td>
			 
			
			   <td>
			    <div style="margin-left:0px;margin-top:-23px;">
			       <!--  <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;"> -->
			       <label class="" style="margin-left:25px;margin-bottom:1px;">User Type</label>  
			       </div>
			     
		  
			  </td>
			  
			   
		  </tr>
		 </table>
		 
		   <div style="margin-left:780px;margin-top:-28px;">
		   
	<span style="margin-left:-5px;"><input class="easyui-combo" id="cmbEmployeetype" name="cmbEmployeetype" style="width:150px; height:20px;" value="" /></span>
		   
	   <!-- <select id="cmbEmployeetype" class="easyui-combo" name="cmbEmployeetype" style="width: 150px; height: 20px;">
										<option value=" "> </option>
										<option value="M"> MANAGER</option>
									<option value="R"> EMPLOYEE</option>
									<option value="A"> ASSOCIATE</option>
									<option value="C"> CONTRACT</option>
									<option value="B"> BADLI</option>
									<option value="T"> TRAINEE</option>
									<option value="E"> EXECUTIVE</option>
									<option value="O"> OTHERS</option>
							</select> -->
							
							</div>
							
							
							<div>
							
							
							</div>
							
							  <div class="easyui-paddingbfpx" style="margin-left:950px;margin-top:-37px;" >
							    <span style="padding-left:-0px;"><label class="mandatory-lbl"><b>Location</b></label></span>
					         <span style="position:relative;padding-left: -100px">	
						    <input class="easyui-combobox" id="cmbLocnid" name="cmbLocnid"  style="width:150px;"  value="${requestScope.mocRfcmst.rfcmdmtid}" />
				
						    </span>
					       </div>
			    <div style="margin-left:1130px;margin-top:-27px;">
			       <input type="button"  class="easyui-button" value="View" id="btnview" style="height: 24px; width : 87px;"> 
			      
			       </div>
			     
		  
			  
		 
<table id="TeamPerCount"style="margin-top:20px;"><tr><td/></tr></table>
<div id="pager"></div>
</form>