<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
				setLoadFormCallBackFrmId("frmIncIdenCom");
				var url = jQuery('#hiddenUrl').val();
				//viewGrid(url,"q=2");			
			});
	function frmIncIdenCom_afterLoadCallBack(){
		toggleCommonFilter(); 
		}
	function viewGrid(url,filterString)
	{					
		if( validateFilterSelection(filterString))
		{
			jQuery("#hiddenFileter").val(filterString);
			jQuery("#hdnflid").val(getFilterValue(filterString, 'flid'));
			jQuery("#hdnfromdate").val(getFilterValue(filterString, 'dtFromDate'));
			jQuery("#hdntodate").val(getFilterValue(filterString, 'dtToDate'));
			jQuery("#hdnfrommonth").val(getFilterValue(filterString, 'dtFromMonth'));
			jQuery("#hdntomonth").val(getFilterValue(filterString, 'dtToMonth'));
			processGridnew(url,filterString,"frmIncIdenCom","pager","","","","frmIncIdenCom_loadComplete");
			return true;
		}
		return false;
	}
	function validateFilterSelection(filterString){
		
		if(filterString == "?q=")
			return true;
			
		if(getFilterValue(filterString, "cmbCellid") == "" ){
            alert(" Select JH ");
            return false;
        }
        if((filterString.length > 0   &&  jQuery('#chkMonthwise').is(':checked') == false))
        {
            alert("Select Monthwise Checkbox");
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
	function frmIncIdenCom_loadComplete()
	{
		setTotalRowCss('frmIncIdenCom');
	}
</script>
<form>	
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >		
	<table id='frmIncIdenCom'>
	<tr>
	<td></td>
	<tr>
	</table>
	<div id='pager'></div>	
</div>
<input type="hidden" name="hiddenFileter" id="hiddenFileter" value="" />
	<input type="hidden" name="hdnflid" id="hdnflid" value="" />
	<input type="hidden" name="hdnfrommonth" id="hdnfrommonth" value="" />
	<input type="hidden" name="hdntomonth" id="hdntomonth" value="" />
	<input type="hidden" name="hdnfromdate" id="hdnfromdate" value="" />
	<input type="hidden" name="hdntodate" id="hdntodate" value="" />
	</form>			
	
