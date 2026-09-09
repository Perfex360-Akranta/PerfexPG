<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    jQuery(document).ready(function(){
	initialiseForm("frmmenuviewReport");
	var url =jQuery("#hiddenUrl").val();
	jQuery("#submitForm").val("frmmenuviewReport");
	var flid =jQuery("#frmmenuviewReport input[id='flid']").val();
	loadFunctionalLocation("menuvwFunLocation","functionalLoc.menuTree","menuvwFunLocationnValues","frmmenuviewReport","&flid="+flid);
	fillComboBox("frmmenuviewReport","cmbrole","employeeRole.commonFilter?");
	fillComboBox("frmmenuviewReport","cmbmenu","menuComboValue.menuTree");
    viewGrid(url,"q=2");
 });
function viewGrid(url,filterString){
      processGridnew("menuViewsdataReport_input.menuTree",filterString,"Gridviewinfo","pagergridviewreport","","");
    }  
        jQuery("#btnviewGridreport").click(function(){
	    		var url = jQuery('#hiddenUrl').val();
				var  filterString="";
				var flid =jQuery("#frmmenuviewReport input[id='flid']").val();
				var roleKeyId =getFieldValue("cmbrole","frmmenuviewReport");
				var menuId=getFieldValue("cmbmenu","frmmenuviewReport");
				filterString=filterString+"&flid="+flid+"&roleKeyId="+roleKeyId+"&menuid="+menuId;
				viewGrid(url,filterString);				
		       });	

    function frmmenuviewReport_FuntLocHierarchy_SuccessCallBack(keyIds){
	 var flid=keyIds.flid;
	 ReloadMasterGrid();
}
   function ReloadMasterGrid(){
      var flid =jQuery("#frmmenuviewReport input[id='flid']").val();
	  var url = jQuery('#hiddenUrl').val();	
      var url="menuViewsdataReport_getData.menuTree";
	  url += "?&active=Y";
	  url =url+"&flid="+flid;
	  jQuery("#Gridviewinfo").setGridParam({url:url}).trigger('reloadGrid');
}

   function frmmenuviewReportcmbrole_onSelect()
   {
	   funviewGrid();
  }
  function frmmenuviewReportcmbrole_onClear()
  {
	  funviewGrid();
} 

  function funviewGrid(){
	    var url = jQuery('#hiddenUrl').val();
		var  filterString="";
		var flid =jQuery("#frmmenuviewReport input[id='flid']").val();
		var roleKeyId =getFieldValue("cmbrole","frmmenuviewReport");
		var menuId=getFieldValue("cmbmenu","frmmenuviewReport");
		filterString=filterString+"&flid="+flid+"&roleKeyId="+roleKeyId;
		viewGrid(url,filterString);		
}
 
  function frmmenuviewReportcmbmenu_onSelect()
   {
		var url = jQuery('#hiddenUrl').val();
		var  filterString="";
		var flid =jQuery("#frmmenuviewReport input[id='flid']").val();
		var menuId=getFieldValue("cmbmenu","frmmenuviewReport");
		filterString=filterString+"&flid="+flid+"&menuid="+menuId;
		viewGrid(url,filterString);
  }
function frmmenuviewReportcmbmenu_onClear(){
	funviewGrid();
}
    
</script>
<form id="frmmenuviewReport" name="frmmenuviewReport">
<div style="margin-top:40px;margin-left:65px;">
			<div  class="easyui-paddingbfpx" id="frmmenuviewReportFuntKeyIds">
					<input type="hidden" id="factory" name="cmbFactoryid" value="${requestScope.factId}"  ></input>
					<input type="hidden" id="sbu" name="sbu" value="${requestScope.sbu}"  ></input>
					<input type="hidden" id="pbu" name="pbu" value="${requestScope.pbu}"  ></input>
					<input type="hidden" id="section" name="cmbSectionid" value="${requestScope.sectId}"  ></input>
					<input type="hidden" id="cell" name="cmbCellid" value="${requestScope.cellId}" ></input>
					<input type="hidden" id="machine" name="cmbMachineid" value="${requestScope.mchId}"></input>
					<input type="hidden" id="flid" name="cmbFlid" value="${requestScope.requestScope.flid}"  ></input>
				</div>
				<div id="menuvwFunLocation" style="width:450px;"></div>
		</div>
      <div style="margin-top:30px;margin-left:55px;">
        <div class="easyui-paddingbfpx">
        <div class="easyui-paddingbfpx" style="padding-top: 3px;">
				<span style="padding-left: 4px;vertical-align: top;padding-top: 1px;">
					<label style="padding-left:8px;">Role</label>
				</span>
				<span style="padding-left: 370px;vertical-align: top;padding-top: 1px;">
					<label style="padding-left:32px;">Menu</label>
				</span>	
			   </div>
                   <div>
		          	<span style="vertical-align: top; padding-left:10px;"> 
				     <input id="cmbrole" name="cmbrole" class="easyui-combobox" style="width: 400px; height: 20px;" />
				</span> 
				<span style="vertical-align: top; padding-left: 30px;"> 
				     <input id="cmbmenu" name="cmbmenu" class="easyui-combobox" style="width: 350px; height: 20px;" />
				</span> 
	<div style="margin-top:-30px;margin-left:60px;">
	<span style="margin-left:750px; margin-top:-50px;">
	<input class="easyui-button" id="btnviewGridreport" name="btnviewGridreport" style="width:100px;" style="margin-top:-10px;" value="View List" style="height:20px;"/>
	</span>			
     </div> 
</div>  
</div>
</div>
 <div style="margin-top:10px; margin-left:65px;">	    
<table id="Gridviewinfo">
</table>
<div id="pagergridviewreport"></div>
</div>	 	
</form>
