<script>

jQuery(document).ready(function(){

	//jQuery('#fishBoneTree').css('height',window.innerHeight-180);
	var type=jQuery("#hdntype").val();
	processAjaxCalls("getQMReportData.qmre?type="+type,"","selectedReport_onsuccesscallback");
	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnReport").val(btnName);
	jQuery('#btnReport').click(function(){		
		processAjaxCalls("openFile.file?fileName="+type+"ReportCard.xls", "", "", "", "", "new");						
	});	
	
});
function Exl_onsuccesscallback(result){

}

function selectedReport_onsuccesscallback(result){
	var html="";
	
	
	var hdnresult = jQuery("#hdnresultlength").val(result.length);
	//alert('hdnresulst'+hdnresult);
	
	 for( var i = 0; i< result.length ; i++){
		
	 	html += "<div id='QtmRptDiv_"+i+"'>"+
		 "<div style=' width:120.5%;_width:1075px;  height:18px;' class='sub-header'>"+
		"<span >"+result[i][9]+"</span>"+
		"</div>"+
		"<table id='qmreportgrid_"+i+"' style='' rules='all'>"+
		"<tr>"+
		"<td></td>"+
		"</tr>"+
		"</table>"+
		"<div id='pager_"+i+"'></div> "+
		"<div style='padding-top:10px;'></div>"+
		"<input type='hidden' id='hdnfunction"+i+"' value='"+result[i][6]+"'/>"+
		"</div>";
										
	}
		
	jQuery('#grdContent').append(html);
	for(var g=0; g<result.length;g++){
		 
			viewgrid(g);
		 
		
	}
}

function viewgrid(grdcnt){
	var gridid = 'qmreportgrid_'+grdcnt;
	var pagerid = 'pager_'+grdcnt;
	//alert(gridid+"   pager :::"+pagerid);
	
	processGridnew("qmreportgrid_input.qmre","?q=2&function="+jQuery("#hdnfunction"+grdcnt).val(),gridid,pagerid,"","","","");	
}

</script>
<form>
<!--<div class="easyui-paddingbfpx" style="width:100%;;float:left;margin-left:100.8%;">-->
<!--		-->
<!--	</div>-->
	<div id='wrapperRpt' style="padding-top:20px;max-height:700px;">
		<div class="easyui-paddingbfpx" style="width:100%;;float:left;margin-left:114.8%;margin-left:95.3%\9;margin-top:-21px;">		
		<span style=""><input class="easyui-button" type="button" value="Report"
			id="btnReport" name="btnReport" style="height: 21px"  /> </span>	
		</div>					
		<div id='grdContent'>
		</div>
	</div>
<input type="hidden" id='hdnresultlength' value=''/>
<input type="hidden" id='hdntype' value='${requestScope.type }'/>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>