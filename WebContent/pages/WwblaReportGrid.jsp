<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<script>
 jQuery(document).ready(function ()
			{
				var url = jQuery('#hiddenUrl').val();
				//alert('url'+url);
				var dataStr="?q=2";			
				viewGrid(url,dataStr);													
			});

			function viewGrid(url,filterString)
			{						
				if( validateFilterSelection(filterString))
				{
					filterString += '&drillFlag=f&firstClick=Y';
					//processGridnew(url ,filterString,"AbnActionPlandrillGrid","AbnActionPlanpagerid","","doubleClickGrid");
					processGridnew(url, filterString,"WwblaMainGrid", "WwblaMainPager", "", "docDoubleClick", "");
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
	var formtype = getFieldValue("hdnformtype","frmWwblaGrid");
	//alert('formtype'+formtype);
	if (formtype !="rpt")
		{
			var rowData = jQuery("#WwblaMainGrid").jqGrid('getRowData',id);	
			var keyId = rowData.KEYID;
			//alert('keyId'+keyId);
			//var  refDoctype = jQuery('#hdnRefdoctype').val();
			//var refDocid = jQuery('#hdnRefdocid').val();
			var dataStr = "?&grid=true&keyId="+keyId+"&refDocid="+refDocid+"&refDoctype="+refDoctype;
			//alert(dataStr);
			//navigateToNextForm("WwblarptGrid_input.wwbla"+dataStr,"Wwbla");
			navigateToNextForm("WwblarptGrid_input.Wwbla?keyid="+keyid);
		}
	else
		{  
			var rowData = jQuery("#WwblaMainGrid").jqGrid('getRowData',id);	
			var keyid = rowData.KEYID;
			var title = rowData.TITLE;
			//navigateToNextForm("WwblaRpt_input.wwbla?&grid=true&keyid="+keyid+"&title="+title,"Wwbla Report");
			navigateToNextForm("WwblarptGrid_input.Wwbla?keyid="+keyid);
		}
	
}
</script>
<form name="frmWwblaGrid" id="frmWwblaGrid"  method="post">
	<div id="WrapperRpt">
	<c:if test="${true  == requestScope.disableForRpt}">
		<div style="margin-left:0px;margin-top:-20px;">
	      <input id="btnNewBooking" name="btnNewBooking" class="easyui-button"  type="button" value="New Booking" style="width:100px;"/>
	    </div>
	</c:if>
   <div style="margin-top: 0px">
		<table id="WwblaMainGrid">
			<tr>
				<td>
				</td>
			</tr>
		</table>
		<div id='WwblaMainPager'></div></div>
	</div>
	<input type="hidden" id="hdnformtype" value="${requestScope.formtype}"/>
	<input type="hidden" id="hdnRefdoctype" value="${requestScope.refDoctype}"/>
	<input type="hidden" id="hdnRefdocid" value="${requestScope.refDocid}"/>
</form>