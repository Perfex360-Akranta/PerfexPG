

<script>
jQuery(document).ready(function(){
		initialiseForm('frmTeamPerform');
		jQuery('#submitForm').val('frmTeamPerform');
		fillComboBox("frmTeamPerform","cmbAet","sectionCombo.commonFilter");
		fillComboBox("frmTeamPerform","cmbPact","cellCombo.commonFilter");
		setLoadFormCallBackFrmId("frmTeamPerform");
		
		var url =jQuery('#hiddenUrl').val();
		jQuery("#functionallocn").hide();
	    var factId = jQuery("#frmTeamPerform input[id='factory']").val();
		var sectionId = jQuery("#frmTeamPerform input[id='section']").val();
		var cellId = jQuery("#frmTeamPerform input[id='cell']").val();
		var machId = jQuery("#frmTeamPerform input[id='machine']").val();
		var flid = jQuery("#frmTeamPerform input[id='flid']").val();
	
	 if(flid !=null)
	    {	 
	//////////////////////////alert("Loadfn");
	    	loadFunctionalLocation("NewMocfunLocation","functionalLoc.apdb","NewMOCfunLocationValues","frmTeamPerform","&flid="+flid);
	    	reloadCombo("frmTeamPerform","cmbpact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
	    	//reloadCombo("frprocessAjaxmMocProject","cmbaet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
	 	   
	    }
	    else
	 	   {
	    	////////////////////////////alert("ELSELoadfn");
	 	   loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMocfunLocationValues","frmTeamPerform","");
	 	   }
	    viewGrid(url,"&q=2");	
       
	
});


function viewGrid(url,filterString)
{ 
	if( validateFilterSelection(filterString))
	{ 
		// processGridnew("TeamMember_input.apdb","?q=2flid="+flid+"&cellId="+cellId, "attandanceGrid", "pageratt","","","","loadCompleteAttendanceGrid");
	     processGridnew("TeamMember_input.apdb",filterString,"TeamPerformGrid","pager","","MOCDoubleClick","loadCompleteAttendanceGrid");
        return true;
}
 return false;	
}

function loadCompleteAttendanceGrid(){
	
    var row = jQuery("#TeamPerformGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#TeamPerformGrid").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++)
	 {
	
			 jQuery("#TeamPerformGrid").setRowData(row[i], false, { height : 50 + (i * 2) });
				//disableGridSort("TeamGrid");
				
				  // jQuery("#TeamPerformGrid").jqGrid('setCell',row[i],'height','500');
				   
			
	

	 }	 
}

function frmTeamPerformcmbPact_onSelect(record) 	{
	
	var dataStr="&cellId="+record.id;

	loadFunctionalLocation("NewMocfunLocation","functionalLoc.nmoc","NewMOCfunLocationValues","frmTeamPerform",dataStr);
	var cellId = record.id;
	//alert(cellId);
/* 	var flid = jQuery("#frmTeamPerform input[id='flid']").val();
	alert(flid);
    processGridnew("TeamMember_input.apdb","?q=2flid="+flid+"&flid="+flid, "TeamPerformGrid", "pager","","","","loadCompleteAttendanceGrid");
 */
}

 jQuery("#cmbAet").combobox({
				onSelect : function(recordid) {
			    //alert("click");
					var dataStr="&sectId="+recordid.id;	
					////alert("click"+dataStr);
					loadFunctionalLocation("NewMocfunLocation","functionalLoc.apdb","TeamCounFunctionalLocn","frmTeamPerform",dataStr);
				}
			});
  
 function frmTeamPerform_FuntLocHierarchy_SuccessCallBack(keyIds)
 {
 	var factId = "";
 var pbuId=keyIds.pbuId;
 //////////////////////////////////alert("pbuid"+pbuId);
 	var sectId = keyIds.sectId;	
 	//alert("sectId"+sectId);
 	var cellId=keyIds.cellId;
 
 	var sbuId=keyIds.sbuId;
 	//alert(sbuId);
 	var LocnId=keyIds.locnId;
 	//alert(LocnId)
 	var locnId = jQuery("#frmTeamPerform input[id='location']").val();
 	var flid = jQuery("#frmTeamPerform input[id='flid']").val();
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
 	readOnlyFields("cmbLocnid");
    processGridnew("TeamMember_input.apdb","?q=2flid="+flid+"&flid="+flid, "TeamPerformGrid", "pager","","","","loadCompleteAttendanceGrid");
 	reloadCombo("frmTeamPerform","cmbPact","cellCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&sectionid="+sectId);
 	reloadCombo("frmTeamPerform","cmbAet","sectionCombo.commonFilter?compId="+keyIds.compId+"&locnId="+keyIds.locnId+"&pbuId="+pbuId);
 	setFunctionalLocWidth('frmTeamPerform','625px');
 }

function validateFilterSelection(filterString){
return  true;
}
function AbnormalityBtn(id, options, rowObject){
	//alert(options.rowId+'_'+options.pos);
	return '<input type="button" id="btnAbnormality_'+options.rowId+'_'+options.pos+'" onclick=ViewAbnormality("'+options.rowId+'"); name="btnAbnormality_'+options.rowId+'_'+options.pos+'"    style="width:80px;  height:23px;"   class="easyui-button" value="Abnormality"/>';
}
function SuggestionBtn(id, options, rowObject){
	//alert(options.rowId+'_'+options.pos);
	return '<input type="button" id="btnSuggestion_'+options.rowId+'_'+options.pos+'" onclick=ViewSuggestion("'+options.rowId+'"); name="btnSuggestion_'+options.rowId+'_'+options.pos+'"    style="width:80px;  height:23px;"   class="easyui-button" value="Suggestion"/>';
}

function ViewAbnormality(id){
	
	var empKeyId = jQuery("#TeamPerformGrid").jqGrid('getCell',id,1);
	
	var EmployeeName=escape(jQuery("#TeamPerformGrid").jqGrid('getCell',id,5));
	
	var EmployeeLogin=jQuery("#TeamPerformGrid").jqGrid('getCell',id,6);
	
	var JH=jQuery("#cmbPact").combobox("getValue");
	
	var DMT=jQuery("#cmbAet").combobox("getValue");
	
	var Flid=jQuery("#frmTeamPerform input[id='flid']").val();
	
	// navigateToNextForm("AbnIndividual dView_input.abnForm?q=2&empKeyId="+empKeyId+"&EmployeeName="+EmployeeName+"&filterButton=false","AbnormalityView");
	 
	LoadPopUp("divAbnormality","AbnormalityTeamPer_input.apdb?q=2&empKeyId="+empKeyId+"&EmployeeName="+EmployeeName+"&EmployeeLogin="+EmployeeLogin+"&JH="+JH+"&DMT="+DMT+"&Flid="+Flid,true,"1170px","590px","6px","5%","","AbnormalityView","",false);
	

}

function ViewSuggestion(id){
	
	var empKeyId = jQuery("#TeamPerformGrid").jqGrid('getCell',id,1);
	
	var EmployeeName=escape(jQuery("#TeamPerformGrid").jqGrid('getCell',id,5));
	var EmployeeLogin=jQuery("#TeamPerformGrid").jqGrid('getCell',id,6);
	//alert("EmployeeName"+EmployeeName);
	var JH=jQuery("#cmbPact").combobox("getValue");
	var DMT=jQuery("#cmbAet").combobox("getValue");
	
	var Flid=jQuery("#frmTeamPerform input[id='flid']").val();
	
	// navigateToNextForm("AbnIndividual dView_input.abnForm?q=2&empKeyId="+empKeyId+"&EmployeeName="+EmployeeName+"&filterButton=false","AbnormalityView");
	 
	LoadPopUp("divSuggestion","SuggestionTeamPer_input.apdb?q=2&empKeyId="+empKeyId+"&EmployeeName="+EmployeeName+"&EmployeeLogin="+EmployeeLogin+"&JH="+JH+"&DMT="+DMT+"&Flid="+Flid,true,"1170px","590px","6px","5%","multiSelectOk_Callback","SuggestionView","",false);
	

}
function txtFormatterimg(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	var gid=options.gid;
	var imageUrl=rowObject[3];
	
	//alert(imageUrl);
	if(imageUrl.length<=1){
		
		imageUrl="tmp/images/PENGUINS_1576505108.JPG";
	}
	
//alert("colname::colno"+columnName+"::::"+columnNo);if(imageUrl.trim().length)
	idval='imghand_';
	var prorpertyHover="";
	var prorpertyleave="";
	if(gid=="TeamPerformGrid"){
		 prorpertyHover='gotMousehover(this.id,'+id+')';
		 prorpertyleave='gotMouseleave(this.id)';
	}
	if(columnNo==4){ 
		
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'"   src="'+imageUrl+'" align="left" width="200px" height="100px" style="cursor: pointer;" />';
		}else{
		return '<img id='+idval+columnNo + '_'+id +' name='+idval+columnNo + '_'+id +' onmouseenter="'+prorpertyHover+'" onmouseleave="'+prorpertyleave+'" src="'+imageUrl+'"   width="200px" height="100px" align="left"  style="cursor: pointer"/>';
	}
}


</script>
<form name="frmTeamPerform" id='frmTeamPerform'>
<table>
 <tr>
 <td>
       </td>
       </tr> 
      </table>
<div id="wrapperRpt"style= margin-top:2px;>
<div id="functionallocn">
  <div  id="frmTeamPerformFuntKeyIds">
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu} "  ></input>	
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>		
					<input type="hidden" id="section" name="cmbaet" value="${requestScope.dmt}"></input>
					<input type="hidden" id="cell" name="cmbpact" value="${requestScope.jh}"  ></input>
					<input type="hidden" id="machine" name="machine" value="${requestScope.mchId} "  ></input>
					<input type="hidden" id="flid" name="flid" value="${requestScope.flid}"></input>	
				</div>
				
			 	<div id="NewMocfunLocation" style="width:100%;margin-top:0px;margin-left:-35px"></div>
		</div>	
	<div id="completedBlock" Style="margin-top:-2px">
<div style="margin-left:0px;margin-top:0px;">
	<span style="margin-left:0px;"><label class="mandatory-lbl">DMT</label></span>
	<span style="margin-left:250px;"><label class="mandatory-lbl">JH</label></span>
</div>
    
     
<div style="margin-left:0px;margin-top:5px;">
		<span><input class="easyui-combo" id="cmbAet" name="cmbAet" style="width: 260px;"	value="" /></span>
    <span style="margin-left:20px;"><input class="easyui-combo" id="cmbPact" name="cmbPact" style="width: 260px;"	value="" /></span>
  
</div>
	<input type="hidden" id="hdnFrmMode" value="${requestScope.mode}"/> 
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnAbnType" name="hdnAbnType" value="${requestScope.AbnType}" >
	<input type="hidden" id="hdnLoginId" name="hdnLoginId" value="${requestScope.loginUser}" >
	<input type="hidden" id="hdnatnplnsts" name="hdnatnplnsts" value="" >
	<input type="hidden" id="hdnFlid" name="hdnFlid" value="${requestScope.Flid}" >
	 </div>	
<table>
      <tr>
           <td style="float:left;">
                     </td>
           
      </tr>
      
      <tr>
           <td colspan="2">
               <div class="clear"></div>
	             <table id="impVscomp" ></table>
	                <div id="pager"></div>
	                <div id="divGraphContainer" ></div>	
           </td>
      </tr>
</table>
	
	
</div>	

    <div style="margin-left:10px; margin-left:65px\9;margin-top:5px;">
       <table  id='TeamPerformGrid'>
			<tr>
			<td>
			<div style="margin-top: -26px"></div>
			</td>
			</tr>
		</table>
<div id="pager"></div>
</div>



<input type="hidden" id="mode" name="mode"  value="${requestScope.mode}"/> 
</form>
