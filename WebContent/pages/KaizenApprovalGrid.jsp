
<script>
jQuery(document).ready(function(){
	
	var url = jQuery("#hiddenUrl").val();
	setLoadFormCallBackFrmId("frmApproval");
	invokeAfterLoadFormCallBack();

   // viewGrid(url,"q=2");

});
function frmApproval_afterLoadCallBack(){
	toggleCommonFilter();
	}

function viewGrid(url,filterString)
{	
	
	processGridnew(url,filterString,"kaizenApproval","pager","","doubleClickGrid");
	return true;
    
}
/*
function doubleclicKaizenApproval(id)
{	
	navigateToNextForm("KaizenApprovalmodify_input.kaizen?grid=true&clearfrom=false");
    
}*/

function doubleClickGrid(rowid)
{   
	var forwardData = jQuery('#hiddenUrl').val();
	var status=jQuery('#kaizenApproval').getCell(rowid,"STATUS");
	var cellId=jQuery('#kaizenApproval').getCell(rowid,'FLNID');//FLNID
	var keyId=jQuery('#kaizenApproval').getCell(rowid,'IMPRVNO');
	var ThemeCat=jQuery("#kaizenApproval").getCell(rowid,'THEMECATEGORYKEYID');
    var themeName=jQuery("#kaizenApproval").getCell(rowid,"THEME");
    var benFitArea=jQuery("#kaizenApproval").getCell(rowid,"RESULTAREA");
	//alert(keyId +"Keyid");
	var url = jQuery("#kaizenApproval").jqGrid('getGridParam', 'url');
	url = url.replace('getData','input');
	url = escape(url); 
	//alert(jQuery('#hdnbdmode').val());
	if(forwardData.indexOf("KaizenView_input.kaizen") >=0)
	{ 
		navigateToNextForm("kaizen_input.kaizen?kznKeyid="+keyId+"&benFitArea="+benFitArea+"&themeName="+themeName+"&mode=view&Mode=view&filterButton=false","",null,{"filterString":url});
	}else if(forwardData.indexOf("KaizenWorkFlow_input.kaizen") >=0)
	{  
		navigateToNextForm("kaizen_input.kaizen?kznKeyid="+keyId+"&benFitArea="+benFitArea+"&themeName="+themeName+"&mode=view&Mode=view&filterButton=false","",null,{"filterString":url});
	}
	else if(jQuery('#hdnbdmode').val()!=null && jQuery('#hdnbdmode').val()>0)
	{   
        navigateToNextForm("kaizen_input.kaizen?&bdmmode=bdmmode&kznKeyid="+keyId+"&kznStatus="+status+"&benFitArea="+benFitArea+"&themeName="+themeName+"&mode=modify&closeOnSave=true&filterButton=false","Kaizen Idea Sheet Approval",null,{"filterString":url});
	}	
	else 
	{	
		var isCloseOnSave="";
		if(status!="COMPLETED")
			//isCloseOnSave="&closeOnSave=true";
		LoadPopUp("KaizenApp","kaizen_input.kaizen?q=2&rowId="+rowid+"&kznKeyid="+keyId+"&kznStatus="+encodeURIComponent(status)+"&mode="+jQuery("#mode").val()+"&Mode=Approval"+isCloseOnSave+"&benFitArea="+benFitArea+"&themeName="+encodeURIComponent(themeName)+"&filterButton=false"+"&cellId="+cellId,true,"80%","90%","1%","5%","loadpopUpSuccessCallBack","Kaizen Idea Sheet Approval","",true,true);
 		    //navigateToNextForm("kaizen_input.kaizen?&kznKeyid="+keyId+"&kznStatus="+status+"&mode="+jQuery("#mode").val()+"&Mode=Approval"+isCloseOnSave+"&benFitArea="+benFitArea+"&themeName="+themeName+"&filterButton=false"+"&cellId="+cellId,"Kaizen Idea Sheet Approval",null,{"filterString":url});
	}
}
</script>

<form id="frmApproval">
<div id='wrapperRpt' >
<div style="margin-top: -18px">
<table  id='kaizenApproval' >
			<tr>
				<td ></td>
			</tr>
		</table>
		<div id='pager'></div>
		</div>
</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}">
</form>