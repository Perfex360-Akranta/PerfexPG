<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script>
 jQuery(document).ready(function ()
			{
				jQuery("#btnNewBooking").hide();
	 			var url = jQuery('#hiddenUrl').val();
				var dataStr="?q=2";			
				viewGrid(url,dataStr);													
			});

			function viewGrid(url,filterString)
			{						
				if( validateFilterSelection(filterString))
				{
					filterString += '&drillFlag=f&firstClick=Y';
					//processGridnew(url ,filterString,"AbnActionPlandrillGrid","AbnActionPlanpagerid","","doubleClickGrid");
					processGridnew(url, filterString,"FishBoneMainGrid", "FishBoneMainPager", "", "docDoubleClick", "");
					return true;
				}
				return false;
			}
			
			function validateFilterSelection(filterString){
					return true;
			}
	

	jQuery ("#btnNewBooking").click(function(){
		var keyId = "";
		var  refDoctype = jQuery('#hdnRefdoctype').val();
		var refDocid = jQuery('#hdnRefdocid').val();
		var dataStr = "?&grid=true&keyId="+keyId+"&refDocid="+refDocid+"&refDoctype="+refDoctype;
		//alert(dataStr);
		navigateToNextForm("FishBoneTree_input.fishbone"+dataStr,"Fish Bone");
		
	});
	


/*if( validateFilterSelection(filterString))
{
	
	processGridnew(url, "?q=2","FishBoneMainGrid", "FishBoneMainPager", "", "docDoubleClick", "");
   processGridnew(url,filterString,"list","pager",tableCaption,"doubleClickGrid","","kaizenGridOncompleteLoad");
	return true;
}	
jQuery(document).ready(function() {
	
	var url = jQuery("#hiddenUrl").val();
	processGridnew(url, "?q=2","FishBoneMainGrid", "FishBoneMainPager", "", "docDoubleClick", "");

function validateFilterSelection(filterString){
	return  true;
}*/	

function docDoubleClick(id){
	var formtype = getFieldValue("hdnformtype","frmFishBoneGrid");
	if (formtype !="rpt")
		{
			var rowData = jQuery("#FishBoneMainGrid").jqGrid('getRowData',id);	
			var keyId = rowData.KEYID;
			var  refDoctype = jQuery('#hdnRefdoctype').val();
			var refDocid = jQuery('#hdnRefdocid').val();
			var dataStr = "?&grid=true&keyId="+keyId+"&refDocid="+refDocid+"&refDoctype="+refDoctype;
			//alert(dataStr);
			navigateToNextForm("FishBoneTree_modify.fishbone"+dataStr,"Fish Bone");
		}
	else
		{  
			var rowData = jQuery("#FishBoneMainGrid").jqGrid('getRowData',id);	
			var keyid = rowData.KEYID;
			var title = rowData.TITLE;
			navigateToNextForm("FishBonerptGrid_input.fishbone?&grid=true&keyid="+keyid+"&title="+title,"Fish Bone Report");
		}
	
}
</script>
<form name="frmFishBoneGrid" id="frmFishBoneGrid"  method="post">
	<div id="WrapperRpt">
	<c:if test="${true  == requestScope.disableForRpt}">
		<div style="margin-left:0px;margin-top:-20px;">
	      <input id="btnNewBooking" name="btnNewBooking" class="easyui-button"  type="button" value="New Booking" style="width:100px;"/>
	    </div>
	</c:if>
   <div style="margin-top: 0px">
		<table id="FishBoneMainGrid">
			<tr>
				<td>
				</td>
			</tr>
		</table>
		<div id='FishBoneMainPager'></div></div>
	</div>
	<input type="hidden" id="hdnformtype" value="${requestScope.formtype}"/>
	<input type="hidden" id="hdnRefdoctype" value="${requestScope.refDoctype}"/>
	<input type="hidden" id="hdnRefdocid" value="${requestScope.refDocid}"/>
</form>