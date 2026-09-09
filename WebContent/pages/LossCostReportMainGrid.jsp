<script>
jQuery(document).ready(function ()
		{
			var url = jQuery('#hiddenUrl').val();
			var dataStr="?q=2";	
			initialiseForm('frmLossCostRptGrid');		
			viewGrid(url,dataStr);	
			jQuery("#btnGraph").click(function(){
				LoadPopUp("divGraphLossCost", "PcsLossCostReport_input.lossCstRpt?cellid="+jQuery("#frmLossCostRptGrid input[id='cell']").val(),true,"94%","90%","1%","2%","loadSuccPopup", "Loss Cost Graph",false,false);
			});	
			var factIdMain = jQuery("#frmLossCostRptGrid input[id='factory']").val();
			var sectionIdMain = jQuery("#frmLossCostRptGrid input[id='section']").val();
			var cellIdMain = jQuery("#frmLossCostRptGrid input[id='cell']").val();
			var machIdMain = jQuery("#frmLossCostRptGrid input[id='machine']").val();
			var flidMain = jQuery("#frmLossCostRptGrid input[id='flid']").val();
			var dataStr = "&factId="+factIdMain+"&sectionId="+sectionIdMain+"&cellId="+cellIdMain +"&machId="+machIdMain+"&flid="+flidMain;
			loadFunctionalLocation("lossCostfunLocation","functionalLoc.commonFilter","lossCostfunLocationnValues","frmLossCostRptGrid",dataStr);
			setLoadFormCallBackFrmId("frmLossCostRptGrid");										
			invokeAfterLoadFormCallBack();
		});
		
		function frmLossCostRptGrid_FuntLocHierarchy_SuccessCallBack(result)
		{
			var url = jQuery('#hiddenUrl').val();
			var cellId=result.cellId;
		    var flId=result.flId;
			var filterString="&cellid="+cellId+"&flid="+flId;
			viewGrid(url,filterString);
		}
		function viewGrid(url,filterString)
		{						
			if( validateFilterSelection(filterString))
			{
				var flid = getFilterValue(filterString, 'flid');
				jQuery("#flid").val(flid);
				//loadFunctionalLocation("lossCostfunLocation","functionalLoc.commonFilter","lossCostfunLocationnValues","frmLossCostRptGrid","&flid="+flid);
				filterString += '&drillFlag=f&firstClick=Y';
				processGridnew(url ,filterString,"LossCostReportGrid","LossCostReportGridpager","","doubleClickGrid");
				
				return true;
			}
			return false;
		}
		function validateFilterSelection(filterString){
			/*if(filterString=="?q=2")
				return true;

			if(getFilterValue(filterString, "flid") == "" ){
				alert(" Select JH ");
				return false;
			}
			if(getFilterValue(filterString, "dtFromMonth") == "" ){
				alert(" Select From Month ");
				return false;
			}
			if(getFilterValue(filterString, "dtToMonth") == "" ){
				alert(" Select To Month ");
				return false;
			}*/
				return true;
		}
		/*function frmLossCostRptGrid_afterLoadCallBack(){
			toggleCommonFilter();	
		}*/
		
		function doubleClickGrid(id){
			//navigateToNextForm("PcsLossCostReport_input.lossCstRpt","Loss Cost ");
		} 
</script>
<form id="frmLossCostRptGrid">
	<div id="wrapperRpt" style="width:100%;margin-top: 10px;">
 <table>
	<tr>
		<td width="100%">
			<div  class="easyui-paddingbfpx" style="padding-left:20px;">
				<div  class="easyui-paddingbfpx" id="frmlossCostFuntKeyIds">
					<%-- <input type="hidden" id="factory" name="factory" value="${requestScope.factId}"  ></input> --%>
					<input type="hidden" id="section" name="section"   ></input>
					<input type="hidden" id="cell" name="cell" ></input>
					<input type="hidden" id="machine" name="machine" ></input>
					<input type="hidden" id="flid" name="flid" value="${requestScope.flid}" ></input>
				</div>
				<div id="lossCostfunLocation" style="padding-left: px;" ></div>
			</div> 
		</td>
		<td width="20%">
			<input type="button" class="easyui-button" id="btnGraph" name="btnGraph" value="Graph" style="height:23px; width:50px;"/>
		</td>
	</tr>
</table>
	

		<table id ='LossCostReportGrid'><tr><td></td> </tr></table>
		<div id ='LossCostReportGridpager'></div>
	</div>
</form>