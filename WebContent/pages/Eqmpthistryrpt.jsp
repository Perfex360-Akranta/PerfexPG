
<script>
jQuery(document).ready(function(){	

	jQuery("#chkDatewise").attr('checked',true);
	
	var url = jQuery('#hiddenUrl').val();
	var FromDate = null;
	var ToDate = null;
	var groupBy = "0";
	var dataString ="";
	dataString += "?dtFromDate="+FromDate;
	dataString += "&dtToDate="+ToDate;
	dataString += "&gropByEqp="+groupBy;

	var filterStr = jQuery("#hdnFilterStr").val();
	if( filterStr != null && filterStr.length > 0 )
	{
		var dataString = jQuery("#hdnFilterStr").val();
		var tableCaption = "Equipment History Report";
		processGridnew(url,dataString,"list","pager",tableCaption,"eqpDoubleClick");
	}
	else
		viewGrid(url,dataString);	

});

function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		jQuery("#hdnFilterStr").val(filterString);
		var tableCaption = "Equipment History Report";
		processGridnew(url,filterString,"list","pager",tableCaption,"eqpDoubleClick");
		return true;
	}	
	return false;
}

function eqpDoubleClick(id)
{	
	
	var rowData = jQuery("#list").jqGrid('getRowData',id);
	var formName = rowData.MAINTTYPE;
	var keyid = rowData.KEYID;
	var filter = jQuery("#hdnFilterStr").val();
	var jsonstr = '{"filter":"'+ filter+'"}';
	var perstData = jQuery.parseJSON(jsonstr);	

	if(formName=="BREAKDOWN")
		navigateToNextForm('brkdown_input.brdn'+'?BDKeyid='+keyid+'&mode=view&activity=B&filterButton=false',"Breakdown",null,perstData);	
	
	if(formName=="UNPLANNED")
		navigateToNextForm('brkdown_input.brdn'+'?BDKeyid='+keyid+'&mode=view&activity=U&filterButton=false',"UnPlanned Maintaince",null,perstData);
	
	 if(formName=="ABNORMALITY")
		navigateToNextForm('Abnormality_input.abnForm'+'?AbnId='+keyid+'&mode=view',"Abnormality",null,perstData);

	 if(formName=="OPL")
		navigateToNextForm('create_input.opl'+'?oplKeyId='+keyid+'&mod=VIEW&filterButton=false',"Opl",null,perstData);

	 if(formName=="PREVENTIVE")
		navigateToNextForm('pmActivity_input.prv'+'?keyid='+keyid+'&mode=VIEW',"Preventive",null,perstData);

	 if(formName=="KAIZEN")
		 navigateToNextForm('kaizen_input.kaizen'+'?kznKeyid='+keyid+'&mode=VIEW&filterButton=false',"Kaizen",null,perstData);
		

	 if(formName=="MACHINE ACTIVITY")
		navigateToNextForm('generalMaintcreat_input.genmain'+'?docno='+keyid+'&mode=VIEW&filterButton=false',"General",null,perstData);

	 if(formName=="IMPROVEMENT")
		navigateToNextForm('kaizen_input.kaizen'+'?keyId='+keyid+'&mode=VIEW&filterButton=false',"Kaizen",null,perstData);
	 
	 if(formName=="IMPROVEMENT")
			navigateToNextForm('kaizen_input.kaizen'+'?keyId='+keyid+'&mode=VIEW&filterButton=false',"Kaizen",null,perstData);
	

	 if(formName=="REFURBISHMENT")
			navigateToNextForm('ConditionalAppraisal_modify.condapp'+'?grid=true&keyId=CDAP000032'+'&mode=VIEW&filterButton=false',"Conditional Appraisal",null,perstData);

	 
	//navigateToNextForm('equipment_input.eqp'+'?keyId='+keyid+'&mode=VIEW',"Equipment Master");
}

function validateFilterSelection(filterString){

	/*if( filterString.length != 0)
	{
		 if( ! checkFilterValueExist(filterString, "dtFromDate"))
		{
			 showValidationErrorMsg('dtetoDate','Enter From date');	
			 jQuery('#dispErr').html('<h5> Enter From date</h5>');
			 //jQuery("#dispErr").html(showValidationErrorMsg);
         	//div_err();
			return false;
		}
		else if( ! checkFilterValueExist(filterString, "dtToDate"))
		{
			 showValidationErrorMsg('dtetoDate','Enter To date');//alert("Enter To date");
			 jQuery('#dispErr').html('<h5> Enter To date </h5>');
			return false;
		}					
	}	
	jQuery('#dispErr').html('');*/
	return  true;	
	
}
function frmFilter_enableDisableSuccessCallBack()
{
	jQuery("#chkDatewise").attr('checked',true);
	jQuery("#chkMonthwise").attr('checked',false);		
	enableFields('chkDatewise');
	enableFields('chkDatewise');
}

</script>
<div id="wrapperRpt" style="margin-top: 10px;">
<label class="notes"   style="font-weight: bold; " > ${requestScope.clickLable}</label>
<table id="list" style="width: 100%">
	<tr>
		<td />
	</tr>
</table>
<div id="pager"></div>
</div>
<input type="hidden" id="hdnFilterStr" value="${requestScope.filter}"/>