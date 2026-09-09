<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%--  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    jQuery(document).ready(function(){
	initialiseForm("frmmenuview");
	var url = jQuery("#hiddenUrl").val();
	jQuery("#submitForm").val("frmmenuview");
	var flid = jQuery("#frmmenuview input[id='flid']").val();
	loadFunctionalLocation("menuvwFunLocation","functionalLoc.menuTree","menuvwFunLocationnValues","frmmenuview","&flid="+flid);
	fillComboBox("frmmenuview","cmbrole","employeeRole.commonFilter?");
	fillComboBox("frmmenuview","cmbuser","employee.commonFilter?");
    viewGrid(url,"q=2");
 });
      function viewGrid(url,filterString){          
   	  processGridnew("menuViewsdata_input.menuTree", filterString,"Gridviews","pagergridview","","");
}
	     
	 jQuery("#btnnewReport").click(function(){
	     navigateToNextForm("menu_ViewsdataReport.menuTree");
	      });	 

	       jQuery("#btnviewinfo").click(function(){
 		    var url = jQuery('#hiddenUrl').val();
			var  filterString="";
			var flid =jQuery("#frmmenuview input[id='flid']").val();
			var roleKeyId =getFieldValue("cmbrole","frmmenuview");
			var userKeyId=getFieldValue("cmbuser","frmmenuview");
			filterString=filterString+"&flid="+flid+"&roleId="+roleKeyId+"&userId="+userKeyId;
			viewGrid(url,filterString);				
	       });	
     
function frmmenuview_FuntLocHierarchy_SuccessCallBack(keyIds)
{  
     var flid=keyIds.flid;
     ReloadMasterGrid();               
}
   function ReloadMasterGrid(){
	    var flid = jQuery("#frmmenuview input[id='flid']").val();
		var url = "menuViewsdata_getData.menuTree";
		url += "?&active=Y";
		url =url+"&flid="+flid;
	  	jQuery("#Gridviews").setGridParam({url:url}).trigger('reloadGrid');
	   }         	

 function frmmenuviewcmbrole_onSelect()
 {  
	 fnvwGrid();	
}
 function frmmenuviewcmbrole_onClear() {
	 fnvwGrid();
 }
 
  function fnvwGrid(){
	var url = jQuery('#hiddenUrl').val();
	var  filterString="";
	var flid = jQuery("#frmmenuview input[id='flid']").val();
	var roleKeyId =getFieldValue("cmbrole","frmmenuview");
	filterString=filterString+"&flid="+flid+"&roleId="+roleKeyId;
	viewGrid(url,filterString);
 } 
  
 function frmmenuviewcmbuser_onSelect()
 { 
	    var url = jQuery('#hiddenUrl').val();
		var  filterString="";
		var flid = jQuery("#frmmenuview input[id='flid']").val();
		var userKeyId=getFieldValue("cmbuser","frmmenuview");
	    filterString=filterString+"&flid="+flid+"&userId="+userKeyId;
		viewGrid(url,filterString);   
}
 function frmmenuviewcmbuser_onClear() {
 fnvwGrid();
 }
 
</script>
<form id="frmmenuview" name="frmmenuview">
<div style="margin-top:20px;margin-left:70px;">
			<div  class="easyui-paddingbfpx" id="frmmenuviewFuntKeyIds">
					<input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.factId}"  ></input>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.requestScope.flid}"  ></input>
				</div>
				<div id="menuvwFunLocation" style="width:450px; "></div>
		</div>
      <div style="margin-top:30px;margin-left:60px;">
        <div class="easyui-paddingbfpx">
       <div>
             <div class="easyui-paddingbfpx" style="padding-top: 3px;">
				<span style="padding-left: 4px;vertical-align: top;padding-top: 1px;">
					<label style="padding-left:8px;">Role</label>
				</span>
				<span style="padding-left: 370px;vertical-align: top;padding-top:1px;">
					<label style="padding-left:32px;">User</label>
				</span>	
			   </div>
		          <div>
		          	<span style="vertical-align: top; padding-left: 10px;"> 
				     <input id="cmbrole" name="cmbrole" class="easyui-combobox" style="width:400px; height: 20px;" />
				</span> 
				<span style="vertical-align: top; padding-left: 30px;"> 
				     <input id="cmbuser" name="cmbuser" class="easyui-combobox" style="width:350px; height: 20px;" />
				</span>	
     <div style="margin-top:-30px;margin-left:80px;">
	<span style="margin-left:750px; margin-top:-50px;">
	<input class="easyui-button" id="btnviewinfo" name="btnviewinfo" style="width:100px;" style="margin-top:-10px;" value="View List" style="height:20px;"/>
	</span>			
     </div> 
 <div style="margin-top:-28px; margin-left:60px;">						
<span style="padding-left:900px; padding-left: 45px\9; vertical-align: top;"> 
<input class="easyui-button" id="btnnewReport" name="btnnewReport" style="width:150px;" value="View Menu Report" style="height: 20px;"/>
</span>
</div>
</div>
</div>
</div>
</div>
<div style="margin-top:-1px; margin-left:70px;">	    
<table id="Gridviews">
</table>
<div id="pagergridview"></div>
</div>
</form>
