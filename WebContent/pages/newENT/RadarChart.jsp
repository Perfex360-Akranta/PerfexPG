<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
jQuery(document).ready(function(){
	//fillTable();
	 
	processAjaxCalls("entskillGapRadarChart_input.skilGap","","chartSuccesscallback","","","");
	var assmkeyId=jQuery("#hdnAssm").val();	
	processAjaxCalls("EmpTopicRate_chart.skilGap?assessmentId="+(assmkeyId != null && assmkeyId!= 'undefined' ? assmkeyId :'' ),"","topicRateSuccesscallback","","","");
});

jQuery("#imgExportRadarChart").click(function(){
	jQuery("#loadRadarExcFormat").slideToggle(200);
	var loadHtml = "<div style='padding-bottom:1px;'><a href='#' id='Excel3' name='Excel3'> Send To Excel 2003</a></div>";
		loadHtml += "<div><a href='#' id='Excel7' name='Excel7' style='padding-top:5px;'> Send To Excel 2007</a></div>";
		
	jQuery("#loadRadarExc").html(loadHtml);
	jQuery( '#Excel3').click(function (){		
		sendToExcel("3");		
	});
	jQuery( '#Excel7').click(function (){
		sendToExcel("7");
	});
	
});
function sendToExcel(format)
{

	var dataStr = "?q=2&location="+escape(jQuery("#loactions").html());
		dataStr += "&classifcn="+escape(jQuery("#classification").html());
		dataStr += "&unitHeading="+escape(jQuery("#unitTitle").html());
		dataStr += "&deptHeading="+escape(jQuery("#deptTitle").html());
		dataStr += "&unit="+escape(jQuery("#unit").html());
		dataStr += "&dept="+escape(jQuery("#Dept").html());
		dataStr += "&empName="+escape(jQuery("#name").html());
		dataStr += "&tokenNo="+escape(jQuery("#code").html());
		dataStr += "&role="+escape(jQuery("#role").html());
		dataStr += "&preparedBy="+escape(jQuery("#user").html());	
		dataStr += "&reviewby="+escape(jQuery("#reviewdby").html());
		dataStr += "&evaluationDate="+escape(jQuery("#date").html());
		dataStr += "&nxtEvaluationDate="+escape(jQuery("#nxtEvalDate").html());
		dataStr += "&dataGrid="+escape(jQuery("#Data").html());	
		dataStr += "&imgPath="+escape(jQuery("#imageIcon").attr('src'));
	
	  if(jQuery("#formxlexport").length <= 0 )
	  {	  
	        var xlFormHtml = '<form id="formxlexport" name="formxlexport" method="POST" > ' +
	        		   '<input type="hidden" id="fileName" name="fileName" /> '+
	        		   '<input type="hidden" id="f" name="f"  /> '+
	      	  	   '<input type="hidden" id="exporthtml" name="exporthtml"/> </form>';
	        jQuery("#LoadContent").prepend(xlFormHtml);
	        
	  }
	  jQuery("#formxlexport input[id=f]").val(format);
	 document.formxlexport.method='POST';
	 document.formxlexport.action="exportRadarChart.skilGap"+dataStr;
	 document.formxlexport.target='_blank';
	 document.formxlexport.submit();
	  jQuery("div[id^=loadRadarExcFormat]").hide(200); 
}
function chartSuccesscallback(result)
{
  // alert('in cal back :'+Object.keys(result));


	var parentNames=result.Data[0].parentNames;
	// alert(parentNames);
	parentNames=parentNames.replace("/"," ");
	// alert(parentNames);
	var names=parentNames.split("/");
	var location=names[0];
	// alert(location);
	var classification=names[1];
	var department=names[2];
	var funct=names[3];
	
	var empName=result.Data[0].empName;
	var date=result.Data[0].evalDate;
	var nxtEvalDate =result.Data[0].nxtevalDate;
	/*var mont = date.substring(date.indexOf('-'),6);
	    mont = mont.substring(1);
	var mnthNo = changeFormatStringtoNumber(mont);
	mnthNo = mnthNo+3;
	if(mnthNo == '12')
		mnthNo = '0';
	
	var nxtEvalmnth = getMonthStringFromInt(mnthNo);
	var nxtday =  date.substring(0,date.indexOf('-'));
	var nxtyr  = date.substring(date.length-5);*/
	
	var role=result.Data[0].role;
	var code=result.Data[0].empCode;
	var fileName=result.Data[0].imageName;
	//alert(fileName);
	var userName=result.Data[0].preparedBy;
	//alert(userName);
	//alert(fileName);
	
	
	jQuery("#imageIcon").attr('src',fileName);
	jQuery("#loactions").html(location);
	jQuery("#classification").html(classification);
	if(classification!=null || classification!=" " || classification!='')
		{ 
		//alert('inside');
		if(classification == "MANUFACTURING")
			{
			jQuery("#unit").html(funct);
			jQuery("#Dept").html(department);
			jQuery("#unitTitle").html("Unit");
			jQuery("#deptTitle").html("Process");
			}
		else if(classification == "NON MANUFACTURING")
			{
			jQuery("#Dept").html(funct);
			jQuery("#unitTitle").html("Department");
			jQuery("#deptTitle").html("Function");
			}
        }
    jQuery("#name").html(empName);
    jQuery("#code").html(code);
    jQuery("#role").html(role);
   	jQuery("#user").html(userName);
   	jQuery("#reviewdby").html(userName);
   	jQuery("#date").html(date);
   	jQuery('#nxtEvalDate').html(nxtEvalDate);
	}
 function topicRateSuccesscallback(result)
	{
	  
	  
		var fromSpoke= result.fromSpoke;
		if(fromSpoke=="true")
			jQuery("#titleTopicSpoke").html("<b>Criteria</b>");
		
		
		//alert(Object.keys(result));
		// alert(result.size);
		var rowSize=result.size;
		
		var tableContent="<table class='bord' >";
		
		tableContent+="<tr>";
		
		for (var j=0;j<rowSize;j++)
		{	
			var v=j+1;
			 tableContent+="<td  ALIGN=center class='bord' style='font-size:12px'>"+v+"</td>";
			 
	 for (var i=0;i<6;i++)
	  {
		  //alert(result.fillData[j][i]);
		
		if(i==0)
		 tableContent+="<TD ALIGN=left class='fontSize bord'>"+result.fillData[j][i]+"</TD>";
		 else
			 tableContent+="<TD ALIGN=center class='fontSize bord'>"+result.fillData[j][i]+"</TD>";
	  }
	 /*for (var i=0;i<6;i++)
	  {
		// alert(result.fillData[j][i]);
		
		 tableContent+="<TD ALIGN=center class='fontSize bord'>&nbsp;</TD>";
	     
	  }*/
	  
	 tableContent+="</tr>";  
			}
		
	tableContent+="</table>"; //alert(tableContent);
	   document.getElementById("Data").innerHTML =(tableContent);	
	 //alert(result.chartData.CurrentRating);//,fillData
		drawChart(result.chartData.chartData,"chartDiv","","","N");
		
	}

</script>
<style>

.padLeft
{
padding-left:5px;
}
.padRight
{
padding-right:5px;
}
.fontSize
{
font-size:11;
}
.bord{
border:solid 1px #D1D4DD;

padding:3px;
}
</style>
<form name="frmSkillGapAnalysis" id="frmSkillGapAnalysis" >

<table id="tblDetails" border="" cellspacing="0" class="bord"  >
<tr>

<td colspan="7" style="height:45px" class="bord">
	<img id="imgPrintRadarChart" alt=""  src="images/chart_print.gif" title="Print">
	<img id="imgExportRadarChart" alt=""  src="images/chart_datatable.png" title="Export To Excel" >
	<div class="loadExpToExcel" id="loadRadarExcFormat" style="margin-left:-140px;margin-top:-10px;"><div class='loadRadarExc' id='loadRadarExc'></div></div>
	<span style="margin-left:200px;">
		<label><b style="FONT-SIZE: x-large;">Radar Chart-Skill Gap Analysis</b></label>
	</span>
</td>
<td colspan="2" rowspan="6" width="200px" class="bord"><img id="imageIcon" src="images/EmpDefaultImg.jpg" width="100px" height="100px" style="margin-left:50px"></td>
</tr>

<tr >
<td width="100px" style="text-align:right" class="padRight fontSize bord"><b>Location:</b></td>
<td id="loactions" width="252px" class="padLeft fontSize bord">&nbsp;</td>
<td width="43px" class="fontSize bord" >&nbsp;</td>
<td width="150px" class="fontSize bord"><b>Name:</b></td>
<td  id="name" width="300px" colspan="3" class="padLeft fontSize bord"> </td>
</tr>
<tr >
<td width="100px" style="text-align:right"  class="padRight fontSize bord"  ><b>Classification</b></td>
<td  id="classification" width="252px"  class="padLeft fontSize bord">&nbsp;</td>
<td width="43px" class="fontSize bord">&nbsp;</td>
<td width="150px" class="fontSize bord"><b>Token No:</b></td>
<td id="code" width="350px" colspan="3" class="padLeft fontSize bord"> </td>
</tr>
<tr >
<td  id="unitTitle" width="100px" style="text-align:right" class="padRight fontSize bord"><b>Unit</b></td>
<td id="unit" width="252px"  class="padLeft fontSize bord">&nbsp;</td>
<td width="43px" class="fontSize bord">&nbsp;</td>

<td width="150px" class="fontSize bord">Role</td>
<td id="role" width="350px" colspan="3" class="padLeft fontSize bord"> </td>

</tr>
<tr >
<td  id="deptTitle" width="100px" style="text-align:right" class="padRight fontSize bord">Department</td>
<td  id="Dept"  width="252px"  class="padLeft fontSize bord" >&nbsp;</td>
<td width="43px" class="fontSize bord">&nbsp;</td>
<td width="150px" class="fontSize bord">Prepared By:</td>
<td id="user" width="350px" colspan="3" class="padLeft fontSize bord"> </td>
</tr>
<tr >
<td width="100px" style="text-align:right" class="padRight fontSize bord">Evaluation Date</td>
<td  id="date" width="252px"  class="padLeft fontSize bord ">&nbsp;</td>
<td width="43px" class="bord">&nbsp;</td>
<td width="150px" class="fontSize bord">Reviewed By:</td>
<td id="reviewdby" width="30px" class=" fontSize bord">&nbsp;</td>
<td width="100px"  class="fontSize bord">Next Evaluation Date</td>
<td id='nxtEvalDate' width="70px" class="fontSize bord">&nbsp;</td>

</tr>
<tr>
<td colspan="2" class="bord">
<div id="chartDiv" style="width:100%"></div>
</td>
<td valign="top" colspan="7">
<table id="bottomTable" border="" cellspacing="0" >
<thead class="bord" style="background-color:#F2F298">
<tr>
<td width="43px" align="center" class="fontSize bord" ><b>SI No</b></td>
<td  id="titleTopicSpoke" width="150px" align="center" class="fontSize bord"><b>Topic</b></td>
<td width="93px" align="center" class="fontSize bord"><b>Target Rating</b> </td>
<td width="94px" align="center" class="fontSize bord"><b>Current Rating</b></td>
<td width="147px" align="center" class="fontSize bord"><b>Rating Gap</b></td>
<td width="99px" align="center" class="fontSize bord"><b>Training Start Date</b></td>
<td width="99" align="center" class="fontSize bord"><b>Training Completion Date</b></td>
</tr>
</thead>
<tbody id="Data"></tbody>
</table>
</td>
</tr>
</table>
<input type="hidden" id="hdnAssm" value='${requestScope.assmntKeyid}'/>
<input type="hidden" id="hdnImg" value=""/>


</form>