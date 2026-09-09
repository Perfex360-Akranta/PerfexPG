
<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){	
			var url = jQuery('#hiddenUrl').val();
			
			viewGrid(url,"?q=2");	
			//setLoadFormCallBackFrmId("frmActWiseRpt");
		});
	/*	function frmActWiseRpt_afterLoadCallBack(){
			toggleCommonFilter();	
		}	*/					
		
		function viewGrid(url,filterString)
		{			
			if( validateFilterSelection(filterString))
			{				
				processGridnew(url,filterString,"list","pager");
				return true;
			}	
			return false;
		}
		
		function validateFilterSelection(filterString){

			/* if( ! checkFilterValueExist(filterString,"cmbSectid"))
				{
					alert("Select Section");
					return false;
				}	*/
			 return true;
		}

		function frmFilter_enableDisableSuccessCallBack()
		{
			jQuery("#chkDatewise").attr('checked',true);
			jQuery("#chkMonthwise").attr('checked',false);		
			enableFields('chkDatewise');
			readOnlyFields('dtefromMonth');
			//readOnlyFields('dtetoDate');
			var url = jQuery('#hiddenUrl').val();
			if(url == "ElapsedActivityReport_input.map")
			{
				jQuery('#cboPmstatus').children('option[value="X"]').hide();
				jQuery('#cboPmstatus').children('option[value="Y"]').show();
				jQuery('#cboPmstatus').children('option[value="A"]').hide();
				jQuery('#cboPmstatus').children('option[value=""]').hide();
				jQuery('#cboPmstatus option[value=Y]').attr('selected', 'selected');
			}
			
		}
</script>
<form id="frmActWiseRpt">
	<div id="wrapperRpt">
	<div style="margin-top: -28px"> 
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div></div>
	</div>
</form>