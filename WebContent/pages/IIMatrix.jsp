<script>
var prevPriority ="";
jQuery(document).ready(function(){
	 
	/*processGridnew("IIMatrix1_input.impac","?q=2","tempgrid1","pagerid1");
	processGridnew("IIMatrix2_input.impac","?q=2","tempgrid2","pagerid2");
	processGridnew("IIMatrix3_input.impac","?q=2","tempgrid3","pagerid3");
	processGridnew("IIMatrix4_input.impac","?q=2","tempgrid4","pagerid4");
	*/
	var defctId = jQuery("#hdndefectid").val( );
	var qamatrixId= jQuery('#hdnQamatrixId').val();
    var dataStr = "&defectid="+defctId+"&procesid="+procesid+"&qamatrixId="+qamatrixId;
	processAjaxCalls("getResults.impac",dataStr ,"getResultSuccess","getResultdelte" );
	var btnName = jQuery("#hdnBtnName").val();
	//processAjaxCalls(url,data,onsuccessCallBack,onerrorCallBack,dataType,requestId,hideProcessing)
	
	
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
			{
		//alert("Read From File");
		processAjaxCalls("openFile.file?fileName=IIMatrix.xls", "", "", "", "", "new");					
	});
	
	jQuery("#btnNextStep").click(function() {
		jQuery('#hdnTenStepUrl').val("getWhyWHy_input.tsdi");
		jQuery('#nxtStepId').val("liwhywhy");
		jQuery("#val").text("Step 5 : Analysis of the problem");
		var procesid = jQuery('#hdnProcessid').val();
		// http://localhost:8080/perfexitc/getWhyWHy_input.tsdi?q=2&mainForm=true&processid=PRS0000002&flid=FNL000000075
		LoadForm("divSteps","","getWhyWHy_input.tsdi?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid);
		openTenSteps(); 
	});

	//fileManagerPopUp("","tenStep","frmPbm","btnFilManage","tenStepFilemgr");
});


function btnFilManage_click(){
    
    var documentNo = "1"; //for prtoType use Only

	//if(documentNo != null && documentNo != ''){
		//fileManagerPopUp("","TenStep","","","");
	//}
}

function getResultSuccess(result){
	 var gridData = result.resultData;
	 var tblData ="";
	 
	 var sno = 0;
	 for(var i =0;i<=gridData.length;i++){
		 
		 var priority = gridData[i][0];
		 var result =gridData[i][1];
		 
		 jQuery('#'+priority+"Result").css({'font-size':'12'});
		 
		 if (i==0 ) {
		 	tblData ="<table> ";
		 	prevPriority =priority;
		 }
		 
		 if (i!=0 && prevPriority != priority) {
				 tblData +="</table>";
				 jQuery("#"+prevPriority+"Result").html(tblData );
				 tblData =" <table> ";
			 	 prevPriority =priority;
			 	sno = 0;
		}
		 sno = parseInt(sno)+1;
		 tblData +="<tr>";
		 tblData +="<td style='padding-left:2px;font-size:12px;width:15px;'>  "+sno+" </td>";
		 tblData +="<td style='font-size:12px;text-transform:uppercase;'> "+result+"</td>";
		 tblData +="</tr>" ;
		 
		 if(gridData.length-1   == i) {
			 tblData +="</table>";
			 jQuery("#"+priority+"Result").html(tblData ); 
		 }
	 }
}

</script>


<form name="frmtemporary" id="frmtemporary" >
<div style="margin-top:5px;margin-left:10px;">
<!-- <input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/></div> -->
<div style="position:relative;">
			 <span  id="tenStepFilemgr" style="position:absolute;right:40px;right:70px\9;top:-30px;">
             </span> 
        </div>
<table style="margin-left:10px;" width='95%'>
	<tr> <td><span><input type="button" class="easyui-button" id="btnNextStep" value="Next"/></span></td>
	<tr>
		<td id="P1" valign='top'   width='50%' style="height:200px;border:solid 1px #c1c1c1;">
	          <div>
	          	<div class="sub-header">  P-1 Easy Implementation and High Impact  </div>
	          	<div id="P1Result"></div>
	          </div>
		</td>
		<td id="P2" valign='top' width='50%' style="height:200px;border:solid 1px #c1c1c1;"> 	 	
	         <div>
	          	<div class="sub-header">   P-2 Easy Implementation and Low Impact  </div>
	            <div id="P2Result"></div>
	          </div>
		</td>
	</tr>
	<tr >
		<td id="P3" valign='top' width='50%' style="height:200px;border:solid 1px #c1c1c1;">
			 <div>
	          	<div class="sub-header">   P-3 Difficult Implementation and High Impact  </div>
	             <div id="P3Result"></div>
	          </div>
		</td>
		<td id="P4" valign='top' width='50%' style="height:200px;border:solid 1px #c1c1c1;">
			  <div>
	          	<div class="sub-header">    P-4 Difficult Implementation and Low Impact  </div>
	            <div id="P4Result"></div>
	          </div>
		</td>
	</tr>
</table>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
<input type="hidden"  id="hdndefectid" value="${requestScope.defectid}"/>
</div>
</form>