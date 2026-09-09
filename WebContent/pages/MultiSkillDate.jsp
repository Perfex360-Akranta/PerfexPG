<script>
jQuery(document).ready(function(){//alert("Multiassessement");

	   initialiseForm("frmMultiskill");
	   jQuery('#submitForm').val('frmMultiskill');
	   
	
	   
      // processGridnew("Multiskillassessment_input.postass","q=2&flid="+flid,"multiskillasssessmentgrid","pager","","","","MultiOncompleteLoad");
	
	formatDateBox('dteMudmDate', 'dd-MMM-yyyy');
	
	var factId = jQuery("#frmMultiskill input[id='factory']").val();
	var sectionId = jQuery("#frmMultiskill input[id='section']").val();
	var cellId = jQuery("#frmMultiskill input[id='cell']").val();
	var machId = jQuery("#frmMultiskill input[id='machine']").val();
	var flid = jQuery("#frmMultiskill input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	//alert(dataStr);
	loadFunctionalLocation("MSAfunLocation","functional.postass","MSAfunLocationValues","frmMultiskill",dataStr);
	processGridnew("MultiskillGrid_input.postass","q=2","multiskillDategrid","pager","","","","MultiOncompleteLoad");
	
	 // fillWithCurrentDate('dteMudmDate');
	  
});

/*function frmMultiskillassessement_FuntLocHierarchy_SuccessCallBack()
{
	alert(100);
	var flid = jQuery("#frmMultiskillassessement input[id='flid']").val();
	//var flid = result.flid;
	alert(flid);
	//jQuery("#cmbMSAflid").val(flid);
	processGridnew("Multiskillassessment_input.postass","q=2&flid="+flid,"multiskillasssessmentgrid","pager","","","","MultiOncompleteLoad");
}*/


function TxtMultiSkill(id, options, rowObject) {
    //alert("Inside Formmter");
	var Id = options.rowId;
	var columnid = options.pos;
	
	if(1==rowObject[columnid]){  
	return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value="" />';
	}
	else
		 return " ";
}

function MultiOncompleteLoad() {

     
	 var row = jQuery("#multiskillDategrid").jqGrid('getDataIDs');
	
	 
	 var cm = jQuery("#multiskillDategrid").jqGrid("getGridParam", "colModel");
	
	 
	 for(var i=0;i<row.length;i++)
	 {
	 for(var j=1;j<cm.length;j++)
   	 { 
	   	
	   var zeroVal = jQuery("#multiskillDategrid").jqGrid('getCell',row[i],cm[j].name);	
	   
		/*   if(zeroVal.trim().length>14){			   
			jQuery("#multiskillDategrid").jqGrid('setCell',row[i],cm[j].name.trim(),'',{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'green'});
			  // return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value="" />';
	 	 }*/
   	 }
	 }
}

function chkbox_Role(cellvalue, options, rowObject)
{ 
	//alert(cellvalue);
	var rowId = options.rowId;	
	var colId = options.pos;
	
	
	//'+  (rowObject[colId].substring(0,3) =='MUS' ? 'checked':'')  +'
	////'+  (cellvalue=='1' ? 'checked':'')  +'    ' +  checked + '
	var checked ="";
	//alert(cellvalue);
	if(cellvalue > -1)
	{    
		 if( cellvalue == 1 )
		 	checked ='checked';
	    return '<input id="Rolecheckbox_'+rowId+'_'+colId+'"  name="Rolecheckbox_'+rowId+'_'+colId+'"    ' +  checked + '   type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
	}

	return ' ';
 // return '<input id="Rolecheckbox_'+rowId+'_'+colId+'"  name="Rolecheckbox_'+rowId+'_'+colId+'"    ' +  checked + ' disabled="disabled"   type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';

   
}

 

function frmMultiskill_beforeSubmit()
{
	
   var gridData  = '&multiSkillDate='+converToJsonObject("multiskillDategrid");
  // alert(gridData);
   return gridData; 
}
function converToJsonObject(jqGridId)
{

	//alert(10);
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");
	
     var  empKeyId;
	 
	
	 var uniquePosId = ""; 
	 var jsonArrO='[';
	
	 for(var i=0;i<row.length;i++)
	 {
		 var rowid=row[i];
		
		  
	  for(var j=2;j<col.length;j++)
	  {
		  
		 // alert("column  "+col.length);
				var flid = jQuery("#frmMultiskill input[id='flid']").val();
	          empKeyId= jQuery("#multiskillDategrid").jqGrid('getCell',rowid,"empKeyid");
	          var date = jQuery("#dteMudmDate").datebox('getValue');
	         // date= jQuery("#multiskillDategrid").jqGrid('getCell',rowid,"Datess");
	         if((jQuery('#Rolecheckbox_'+rowid+'_'+j).is(':checked'))==true)
			  {
            	  	uniquePosId = col[j].name;       // Role id
            	  	/*uniquePosId=uniquePosId.split("_");
            	  	if(uniquePosId.length>1){
            	  		rlid=uniquePosId[0];
            	  		keyid=uniquePosId[1];
                	  	}
            	  	else{
            	  		keyid="";
            	  		rlid=uniquePosId;
                	  	}*/
            	  		
	               	jsonArrO+= '{';
	              // 	jsonArrO += '"txtMuseKeyid":"'+mKeyid+'",';
	        		jsonArrO += '"dteMudmDate":"'+date+'",';
	        		jsonArrO += '"txtMudmEmployeeid":"'+empKeyId+'",';
	        		jsonArrO += '"txtMudmUnipositionid":"'+uniquePosId+'",';
	        		jsonArrO += '"txtMudmFlid":"'+flid+'",';
	        	    jsonArrO+= '},';
	        		
			}
         //  alert(j+"   jsonArrO  "+jsonArrO);
		}
	 }
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		//alert('jsonArrO: '+jsonArrO);
		return jsonArrO; 

              
}

function frmMultiskill_FuntLocHierarchy_SuccessCallBack(result){
	 
	   var flid = result.flId;
	   jQuery("#cmbMudmFlid").val(flid);
        
	   var date = jQuery("#dteMudmDate").datebox('getValue');
	  
	
		processGridnew("MultiskillGrid_input.postass","q=2&flid="+flid+"&date="+date,"multiskillDategrid","pager","","","","MultiOncompleteLoad");
		 setFunctionalLocWidth('MSAfunLocation','650px');
}
</script>

<form action=" " method="post" id="frmMultiskill">
<div style="margin-left:6%;margin-top:40px;">
<div id="wrapper" style="width:96%" >

	<table>
		<tr>
		
			<td colspan="2">
			
			<div  id="frmMultiskillassessementFuntKeyIds"  >
			<div style="float: left;">
			<input type="hidden" id="factory" name="cmbMSAFactoryid" value=""  ></input>			
			<input type="hidden" id="section" name="cmbMSASectionid" value=""  ></input>
			<input type="hidden" id="cell" name="cmbMSACellid" value=""  ></input>
			<input type="hidden" id="machine" name="cmbMSAEquipmentid1" value=""  ></input>
			<input type="hidden" id="flid" name="cmbMudmFlid" value="${requestScope.flid}"  ></input>
			
			</div>
			<div id="MSAfunLocation" style="width: 846px; "></div>

             </div>	

			</td>
		
		</tr>
	
	  <tr>
	  <td>
	  <div style="width:200px;">
	  <label  class="mandatory-lbl"> Date </label>
	  </div>
	 
	  <div style="padding-left:0px;width:200px;">  
				<input id="dteMudmDate" name="dteMudmDate"   class="easyui-datebox"   value="${requestScope.date}"  style="width: 120px;"/>
	  </div>
	  
	  
	  </td>
	  </tr>
	</table>



<div style="float:left;">
<table id='multiskillDategrid' >
  
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>



	 <input type="hidden" id="mode"/>
	 <input id="txtMudmKeyid" type='hidden'   name="txtMudmKeyid" value="${requestScope.MultiskillDate.mudmKeyid}" />

</div>
</div>
</div>
</form>