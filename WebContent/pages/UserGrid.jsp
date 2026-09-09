<script type="text/javascript">
		jQuery.noConflict();
		jQuery(document).ready(function(){
				
				
			var url = jQuery('#hiddenUrl').val();
			//alert(url);
			var dataString = "?q=1"; 
			viewGrid(url,dataString);
				}
		);
		
		function viewGrid(url,filterString)
		{
			//alert(filterString);
			if( validateFilterSelection(filterString))
			{
				var tableCaption = "";
				processGridnew(url,filterString,"grdUser","grdUserPager",tableCaption,"doubleClickGrid","","loadComplete");
				return true;
			}	
		}
		function loadComplete()
		{
			if(screen.width <= 1024)
			{
				jQuery("#grdUser").setGridWidth(800);
				jQuery("#wrapperRpt").css("margin-left","73px");	
			}	
			}
		function validateFilterSelection(filterString){
			return  true;
		}
		function doubleClickGrid(rowid) 
		{
			var rowData = jQuery("#grdUser").jqGrid('getRowData',rowid );
			
			
			navigateToNextForm("userform_input.creat?&mode=MODIFY&keyId="+rowData.USERKEYID+"&loginId="+rowData.LOGINID+"&employeename="+rowData.EMPLOYEENUMBER+"&remarks="+rowData.REMARKS+"&department="+rowData.DEPARTMENT);
		}
		
	</script>
	
	<div id="wrapperRpt" style="margin-top:10px;margin-left:90px" >
	<table id="grdUser" ></table>
	<div id="grdUserPager"></div></div>
	<input type="hidden" id="hdnFrmMode" value=""/> 	