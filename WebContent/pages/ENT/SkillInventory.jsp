<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   --%>
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
				
			  var dataString = " ";
			  setLoadFormCallBackFrmId("frmSkillInventory");
			  invokeAfterLoadFormCallBack();
			 /* var url = jQuery('#hiddenUrl').val();
				 viewGrid(url,"&q=1");	*/	
		});
		function frmSkillInventory_afterLoadCallBack(){	
			toggleCommonFilter();		
		}
		function viewGrid(url,filterString)
		{			
			if( validateFilterSelection(filterString))
			{					
				var urlStr = jQuery('#hiddenUrl').val();				
				processGridnew(urlStr,filterString,"list","pager","Skill Level Inventory","","","fillForm");
				return true;	
			}
			return false;	
		}
		
		function validateFilterSelection(filterString){
			if(filterString=="?q=2")
				return true;
			//alert(filterString);
			if(getFilterValue(filterString, "cmbCellid") == "" ){
				alert(" Select JH ");
				return false;
			}
			/*if(getFilterValue(filterString, "flid") == "" ){
				alert(" Select Functional Location ");
				return false;
			}*/
			else{
				return true;
			}
		}
		function fillForm(id)
		{
			/*
			jQuery('.ui-jqgrid .ui-jqgrid-htable th ').css('white-space', 'normal');
			jQuery(".ui-state-default ui-th-ltr").css('white-space', 'normal'); */


			var ids =  jQuery("#list").getDataIDs();
			
			
			  for (var i = 0; i<ids.length; i++) 
			  {	    
				  if((jQuery("#list").jqGrid('getCell',ids[i],'RATING')).trim()== 'A' )    	    
		  		      jQuery("#list").setCell(ids[i], 'RATING', 'ACTUAL', {'color':'red'});
				  else if((jQuery("#list").jqGrid('getCell',ids[i],'RATING')).trim()== 'B')
					  jQuery("#list").setCell(ids[i], 'RATING', 'DESIRED', {'color':'green'});
			  }	 
			    
		}	

		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery('#disableFuncLoc').val('disable');
			enableFields('dteyear');
			enableFormFields("frmFilter", "dteyear");
			jQuery("#frmFilter input[id=dteyear]").datebox("enable");
			var disableFuncLoc = jQuery('#disableFuncLoc').val();
			loadFunctionalLocation("frmFilterfunLocation","functionalLoc.commonFilter","frmFilterfunLocationValues","frmFilter","&disableFuncLoc="+disableFuncLoc+"&machId="+record.id);
		}

		
</script>
<form name = 'frmSkillInventory'>
<div id="wrapperRpt"> 
<c:forEach var="listItems" items="${requestScope.Heaerlabel}">          
        <label class="notes"   style="font-weight: bold; padding-left:20px; " ><c:out value=" ${listItems[0]}" /> </label>
    </c:forEach> 

	<div style=""> 
			 <table id="list" style="width:50%"><tr><td/></tr></table>
			 <div id="pager"></div>
	</div>
</div>
<!--<input type="hidden" id="hdnHeaderLbl" name="hdnHeaderLbl" value="${requestScope.Heaerlabel}">-->


</form>