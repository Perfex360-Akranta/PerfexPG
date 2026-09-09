<script type="text/javascript">

jQuery(document).ready(function(){

	initialiseForm("frmKpivConfig");
        jQuery('#submitForm').val('KpivConfig');
        var btnName = jQuery("#hdnBtnName").val();
		jQuery("#btnViewTemplate").val(btnName);
		jQuery("#btnViewTemplate").click(function()
				{
			//alert("Read From File");
			
			processAjaxCalls("openFile.file?fileName=KPIV.xlsx", "", "", "", "", "new");					
		});
		
       var url = jQuery('#hiddenUrl').val();
       viewGrid("Kpivconfig_input.kpov","q=2");
   	fillComboBox("frmKpivConfig","cmbNmrtEmployeeid","emplo.commonFilter");
    //formatDateBox('dteNmrtOccurrencedatetime','MMM-yyyy');
    jQuery('#dteNmrtOccurrencedatetime').datebox({  
		 formatter: function(date)
		 { 
			 return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); }
	 });  

   	var factId = jQuery("#frmKpivConfig input[id='factory']").val();
    var sectionId = jQuery("frmKpivConfig input[id='section']").val();
    var cellId = jQuery("#frmKpivConfig input[id='cell']").val();
    var machId = jQuery("#frmKpivConfig input[id='machine']").val();
    var flid = jQuery("#frmKpivConfig input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid+"";
  //  alert("tamil");
    loadFunctionalLocation("KpivfunLoc","functionalLoc.kpov","frmkpivLocationfunLocationValues","frmKpivConfig",dataStr);
       
	jQuery("#btnnew").click(function(){
		navigateToNextForm("");
	});
});

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "KpivConfig";
		
		processGridnew(url,filterString,"KpivconGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	    return true;
		
	
} 
	function loadComplete(){
		
		var row = jQuery("#KpivconGrid").jqGrid('getDataIDs');
		//var color="#c1c1c1";
		 var cm = jQuery("#KpivconGrid").jqGrid("getGridParam", "colModel");
		 
		// jQuery("#KpivconGrid").jqGrid('setCell',1,cm[31].name, zeroVal,{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#00FFFF'});
		 for(var i=0;i<row.length;i++)
		 {
			 for(var j=1;j<=cm.length;j++)
	     	 { //alert(cm[32].name+"  "+cm.length);
		     	//  alert(cm[j].name+" -"+j+"-  ");
		     	 var zeroVal = jQuery("#KpivconGrid").jqGrid('getCell',row[i],cm[j].name);
		     	 // alert("zeroVal"+zeroVal);
		     	// alert(cm[j].name+" cm[ "+j+"].name " );
			 	  		jQuery("#KpivconGrid").jqGrid('setCell',1,cm[j].name, zeroVal,{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#00FFFF'});
				  		
	     	 }
		 }
	}
	
</script>


<form name="Kpiv" id="Kpiv" >
<div id="wrapperRpt">

<div id="frmskpivFuntKeyIds"  >							
	<input type="hidden" id="factory" name="factory"  value="" ></input>
	<input type="hidden" id="section" name="section"  value=""></input>
	<input type="hidden" id="cell"    name="cell"     value=""></input>
	<input type="hidden" id="machine" name="machine"  value=""></input>
   <input type="hidden" id="flid" name="cmbSopmFlnid"  value="${requestScope.newQtmTlSopmst.sopmFlnid}"></input>

</div>
	<div id="KpivfunLoc" style="width:728px; width:700px\9">
	</div>	

<table>
		<td >
			<div class="easyui-paddingbfpx" >
			<label>KPOV</label>
			</div>
			<div class="easyui-paddingbfpx">
				<input type="text" class="easyui-text" id="cmbNmrtEmployeeid" name="cmbNmrtEmployeeid" maxlength="30" style="width: 350px; height: 20px" 
				value="" />
			</div>
			</td>
			<td style="padding-left:50px"> 
				<div  class="easyui-paddingbfpx" style="padding-top:7px ">
			<label>Month</label>
						<div  class="easyui-paddingbfpx">
				<span  class="easyui-paddingbfpx" >
						<input id="dteNmrtOccurrencedatetime"  name="dteNmrtOccurrencedatetime" clear="false" class="easyui-datebox" value="${requestScope.genTlNearmissreportmst.nmrtOccurrencedatetime}"  style="width:150px;height:21px;"  />
				</span>
											<input type="button" class="easyui-button" style="width:45px;" id="btnView" name="btnView" value="View"/>
											<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/>						
				     						</div>
				     						</div>
				     						
				     					</td>
				     					</table>	
				     						
			
			
			
			
		


<div class="easyui-paddingbfpx" style="padding-left:-2px">
<table id="KpivconGrid"  ></table>
		<div id="pager"></div>
		</div>
</div>

 <input type="hidden" id="mode"/>
 <input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Report" />
</form>