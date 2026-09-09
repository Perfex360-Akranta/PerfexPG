<!--Author Manikandan-->


<script type="text/javascript">
jQuery(document).ready(function(){	

	viewGrid("TopNFailureReport_input.tnf","?q=1");
 
});

jQuery( "#topview" ).click(function(response) {
	
	var url ='TopNFailureReport_input.tnf';		
	var filterStr = jQuery("#hdnFilter").val();
	var filterString = '';
	filterString += filterStr;
	
	var b = jQuery("#rng").val();
	//viewGrid("TopNFailureReport_input.tnf?",filterString+"&range="+b);
	processGridnew(url,filterString+"&range="+b,"top","pager","Top And Failure Report","");
});


function viewGrid(url,filterString)
{
	
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "Top And Failure Report";
		jQuery.cookie("filterString",filterString);
		jQuery("#hdnFilter").val(filterString);
		if( filterString != null)
		{	
			filterString += "&range=" + jQuery("#rng").val();
			//filterString += "&ViewClicked=" + jQuery("#view").val();
			//alert(filterString);
		}
		processGridnew(url,filterString,"top","pager",tableCaption,"");
		return true;
	}
	return false;	
}
function frmFilter_enableDisableSuccessCallBack()
{
			
	//enableFields('dteyear');
	// alert(jQuery("#dtFromDate").val());
						
}
function validateFilterSelection(filterString){
	
	 if( filterString == "?q=1")
		return true;
	/* else{ 
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			alert("Select  FromDate");
			return false;
		}
		else
			return true;
		if( filterString.length > 0  &&  ! checkFilterValueExist(filterString, "dtToDate"))
		{
			alert("Select  ToDate");
			return false;
		}*/
		else	
			return  true;
	 
}

function frmFilter_enableDisableSuccessCallBack()
{
	fillWithCurrentDate("dtefromDate");			
	//enableFields('dteyear');
	jQuery("#chkDatewise").attr('checked',true);
	jQuery("#chkMonthwise").attr('checked',false);	
	//jQuery("#chkDatewise").attr('disabled',false);						
}
</script>
<div id="wrapperRpt">
<!--<div class="easyui-padding"  style="padding-top: 10">-->
<div style="margin-top: -28px";  class="mrgnleftsxt"  >

		<label>Enter the Range</label>
			<input type="text" id="rng" class="easyui-text easyui-paddingbfpx" value="15" style="width:30px;" >
			<span style="padding-left:4px;"><input type="button" id="topview"  class="easyui-button"  value="View" style="height:20px;"/></span>
		</div>

<div style=""> 
	 <table id="top" style="width:100%"><tr><td/></tr></table>
	 <div id="pager"></div>
</div>
</div>
<input type="hidden" id="hdnFilter" value=""/>
		
		