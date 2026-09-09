<script>
jQuery(document).ready(function(){
	initialiseForm('frmCalenderSafety');
	processGridnew("calenderaudit_input.saau","q=2","Calendergrid","CalendergridPager","","doubleclick","","load_complete");
	var factId = jQuery("#frmCalenderSafety input[id='factory']").val();
	 var sectionId = jQuery("#frmCalenderSafety input[id='section']").val();
	 var cellId = jQuery("#frmCalenderSafety input[id='cell']").val();
	 var machId = jQuery("#frmCalenderSafety input[id='machine']").val();
	 var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
	 loadFunctionalLocation("Calenderfunloc","functionalLoc.commonFilter","calenderfunLocationValues","frmCalenderSafety",dataStr);
});
function load_complete(ids){
	//alert(1);
	 var cm = jQuery("#Calendergrid").jqGrid("getGridParam", "colModel");
	 var row = jQuery("#Calendergrid").jqGrid('getDataIDs');
	 var nul = jQuery("#hdnNul").val();
	 for(var i=0;i<=row.length;i++){
		 for(var j=1;j<cm.length;j++){
			 
			 jQuery("#Calendergrid").jqGrid('setCell',i+1,'CheckPoint','',{'color':'black','background-color':'white', 'font-size': '13','font-weight' : 'bold', 'text- align':'right'} );
			 
			 var zeroVal = jQuery("#Calendergrid").jqGrid('getCell',row[i],cm[j].name);
			 if(zeroVal=='1'){
					tick(row[i],cm[j].name,zeroVal);
			 }
			 if(zeroVal=='0'){
				 tick(row[i],cm[j].name,zeroVal);
				 }
		 }
	 }
	 jQuery("#Calendergrid").setGridParam({onCellSelect:function(id,cellidx,cellvalue) {
				jQuery(cellidx).css('border','solid 1px #000');
				 if(cellvalue == String.fromCharCode(10003))
				{ 
					 jQuery("#hdnCol").val(cellidx);
					 jQuery("#hdnVal").val(cellvalue);
				}
				else if(cellvalue == String.fromCharCode(10005))
				{ 
					 jQuery("#hdnCol").val(cellidx);
					 jQuery("#hdnVal").val(cellvalue);
				}
				else if(cellvalue == String.fromCharCode(x2713))
				{ 
					 jQuery("#hdnCol").val(cellidx);
					 jQuery("#hdnVal").val(cellvalue);
				}
				else if(cellvalue == null)
				{
					 jQuery("#hdnCol").val(nul);
					 jQuery("#hdnVal").val(nul);
				}
			 }
	 
	});
}
function doubleclick(id,options,rowObject){
	var rowData = jQuery("#Calendergrid").jqGrid('getRowData',id);
	var checkpoint = rowData.CheckPoint;
	var tickVal = jQuery("#hdnVal").val();
	var cellid = jQuery("#hdnCol").val();
	jQuery("#hdnRow").val(id);
	  if( tickVal == String.fromCharCode(10003) || tickVal == String.fromCharCode(10005)|| tickVal == String.fromCharCode(x2713)){
		  LoadPopUp("checkpoint","safetyauditcalender_input.saau?checkpoint="+escape(checkpoint) ,true,"25%","40%","10%","20%","","Check Point ");
		  
	  }else{
		  
		  }
}
function tick(ids,columnNumber,zeroVal){
	
	var tickVal ;
	if(zeroVal =="1"){	
	   if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0") 
	   { 
	       tickVal = "&#x2713;";
	   }
	   else
	   { 
	        tickVal = "&#10003;";
	   }
	   jQuery("#Calendergrid").setCell(ids, columnNumber, tickVal,{'color':'green','font-size':'15px','text-align':'center'});
	}
	   if(zeroVal =="0"){
		   jQuery("#Calendergrid").setCell(ids, columnNumber, "&#10005;",{'color':'red','font-size':'15px','text-align':'center'});
	   }
}
function checkpoint_afterClose(arg){
	var tickVal;
	var columnNumber = jQuery("#hdnCol").val();
	var chkStatus = jQuery('#chkStaus').val();
	var nul = jQuery("#hdnNul").val();
	var id = jQuery("#hdnRow").val();
	if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0") 
	   { 
	       tickVal = "&#x2713;";
	   }
	   else
	   { 
	        tickVal = "&#10003;";
	   }
	if(chkStatus == "ok"){
		jQuery("#Calendergrid").setCell(id, columnNumber, tickVal,{'color':'green','font-size':'15px','text-align':'center'});
		jQuery("#hdnVal").val(nul);
		  jQuery("#hdnCol").val(nul);
		  jQuery("#hdnRow").val(nul);
		  jQuery('#chkStaus').val(nul);
	}
	else if(chkStatus =="Notok"){
		jQuery("#Calendergrid").setCell(id, columnNumber, "&#10005;",{'color':'red','font-size':'15px','text-align':'center'});
		jQuery("#hdnVal").val(nul);
		  jQuery("#hdnCol").val(nul);
		  jQuery('#chkStaus').val(nul);
		  jQuery("#hdnRow").val(nul);
		}
	else if(arg == 1){
		jQuery("#hdnVal").val(nul);
		  jQuery("#hdnCol").val(nul);
		  jQuery('#chkStaus').val(nul);
		  jQuery("#hdnRow").val(nul);
			//jQuery("#Calendergrid").setCell(id, columnNumber, "&#10005;",{'color':'red','font-size':'15px','text-align':'center'});		
		}
	
}
jQuery("#btnoplActionplan").click(function(){
	
		 openActionPlan("oplActionplan","1","JH","FNLN00000481");
 
	
	
});	
</script>
<form id="frmCalenderSafety">
	<div id="wrapperRpt">
						<div id="frmCalenderSafetyFuntKeyIds"  >							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>
								<input type="hidden" id="flid" name="flid"  value=""></input>							
							</div>
							<input type="button" id="btnoplActionplan" name="btnoplActionplan" class="easyui-button" style="width:80px;height:22px;" value="Action Plan" />
							<div id="Calenderfunloc" style="width:80%;"></div>
							<table  id='Calendergrid' >
										<tr>
											<td></td>
										</tr>
						</table>
						<div id='CalendergridPager'></div>
	</div>
<input type="hidden" id="hdnCol" value=""/>
<input type="hidden" id="hdnVal" value=""/>
<input type="hidden" id="hdnRow" value=""/>
<input type="hidden" id="hdnNul" value=""/>
<input type="hidden" id="chkStaus" value=""/>
</form>