<script>
	jQuery(document).ready(function(){
			initialiseForm('frmBestKZNGrid');
			jQuery('#submitForm').val('frmBestKZNGrid'); 
			var url = jQuery("#hiddenUrl").val();
			var level=jQuery("#hdnKzbmLevel").val();
			setLoadFormCallBackFrmId("frmBestKZNGrid");
			invokeAfterLoadFormCallBack();
			jQuery("#btnNew").click(function(){	
				navigateToNextForm("bestkaizenlevel_input.bzlv?q=2&btnnew=btnnew&level="+level);				
			});
		//	viewGrid(url,"&q=2&level="+level);
	});
	
	function viewGrid(url,filterString){  
		if( validateFilterSelection(filterString))
		{
			var level=jQuery("#hdnKzbmLevel").val();
			var FromDte = getFilterValue(filterString, "dtFromDate");
	   		var ToDte = getFilterValue(filterString, "dtToDate");
	   		var Frommonth = getFilterValue(filterString, "dtFromMonth");
	   		var Tomonth = getFilterValue(filterString, "dtToMonth");
	   		filterString += '&level='+level;
			processGridnew(url,filterString,"BestKZNgrid","BestKZNpager","","doubleclick","","loadcomplete");
			//processGridnew(url,filterString,"BestKZNgrid","BestKZNpager","","doubleclick","","loadcomplete");
		   return true;
		}
		return false;
	}
	function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
		 
		    return true;
	}
	/*function viewgrid(filter,url) {
		
		processGridnew(url,filter,"BestKZNgrid","BestKZNpager","","doubleclick","","loadcomplete");
	}*/
	
	
	function doubleclick(id){

		var KZNKeyid=jQuery("#BestKZNgrid").jqGrid('getCell', id,"KEYID");
		var level=jQuery("#hdnKzbmLevel").val();
		navigateToNextForm("bestkaizenlevel_input.bzlv?q=2&Keyid="+KZNKeyid+"&level="+level);
	}
	function frmBestKZNGrid_afterLoadCallBack(){
		toggleCommonFilter();	
	}

	
</script>
<form name="frmBestKZNGrid" id="frmBestKZNGrid">
	<div id="WrapperRpt">
		<table>
			<tr>
				<td>
					<div style="margin-top: -28px">
						<input type="button" class="easyui-button" id="btnNew" name="btnNew" value="New" style="height: 25px; width : 75px;margin-left:10px;"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
				<div style="margin-top: -7px">
					<table id='BestKZNgrid'>
					
				
						<tr>
							<td>
							</div>
							</td>
							
						</tr>
					</table>
					<div id='BestKZNpager'></div>
				</td>
			</tr>
		</table>				
	</div>
	<input type="hidden"  id="hdnKzbmLevel" name="hdnKzbmLevel"  value="${requestScope.kznTlBestmst.kzbmLevel}"/>
</form>

