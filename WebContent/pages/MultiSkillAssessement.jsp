<script>
jQuery(document).ready(function(){//alert("Multiassessement");

	   initialiseForm("frmMultiskillassessement");
	   jQuery('#submitForm').val('frmMultiskillassessement');
	   
	
	   
      // processGridnew("Multiskillassessment_input.postass","q=2&flid="+flid,"multiskillasssessmentgrid","pager","","","","MultiOncompleteLoad");
	
	fillComboBox('frmMultiskillassessement','cmbMsaEmployee', 'employeeFilter.commonFilter');
	
	var factId = jQuery("#frmMultiskillassessement input[id='factory']").val();
	var sectionId = jQuery("#frmMultiskillassessement input[id='section']").val();
	var cellId = jQuery("#frmMultiskillassessement input[id='cell']").val();
	var machId = jQuery("#frmMultiskillassessement input[id='machine']").val();
	var flid = jQuery("#frmMultiskillassessement input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	//alert(dataStr);
	loadFunctionalLocation("MSAfunLocation","functionalLoc.postass","MSAfunLocationValues","frmMultiskillassessement",dataStr);
	processGridnew("MultiskillassessmentGrid_input.postass","q=2","multiskillasssessmentgrid","pager","","","","MultiOncompleteLoad");
	  
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

     //alert(" Inside Oncomplete ");
	 var row = jQuery("#multiskillasssessmentgrid").jqGrid('getDataIDs');
	 //alert(" Inside Oncomplete ::row "+row);
	 var cm = jQuery("#multiskillasssessmentgrid").jqGrid("getGridParam", "colModel");
	 //alert(" Inside Oncomplete ::cm "+cm);
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=1;j<cm.length;j++)
   	 { //alert(" Inside Oncomplete ::11111 ");
	   var zeroVal = jQuery("#multiskillasssessmentgrid").jqGrid('getCell',row[i],cm[j].name);	
	    	
		   if(zeroVal.trim().length>14){
			  
			jQuery("#multiskillasssessmentgrid").jqGrid('setCell',row[i],cm[j].name.trim(),'',{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'green'});
			  // return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value="" />';
	 	 }
   	 }
	 }
}

function chkbox_Role(cellvalue, options, rowObject)
{ 
	//alert(cellvalue);
	var rowId = options.rowId;	
	var colId = options.pos;
	var colvalue =rowObject[colId];
	//'+  (rowObject[colId].substring(0,3) =='MUS' ? 'checked':'')  +'
    return '<input id="Rolecheckbox_'+rowId+'_'+colId+'"  name="Rolecheckbox_'+rowId+'_'+colId+'"  '+  (cellvalue=='1' ? 'checked':'')  +'  type="checkbox" ' + 'onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\')}"/>';
}

 

function frmMultiskillassessement_beforeSubmit()
{
   
    var gridData  = '&multiSkill='+converToJsonObject("multiskillasssessmentgrid"); //convertGridToJSONArr
    //alert(gridData);
    return gridData; 
}
function converToJsonObject(jqGridId)
{

	//alert(10);
	var row=jQuery("#"+jqGridId).jqGrid('getDataIDs');
	var col=jQuery("#"+jqGridId).jqGrid("getGridParam","colModel");
	
     var  empKeyId;
	// var  mKeyid;
	 var  rlid;
	 var mKeyid="";
	 var uniquePosId = ""; 
	 var jsonArrO='[';
	
	 for(var i=0;i<row.length;i++)
	 {
		 var rowid=row[i];
		  
	  for(var j=2;j<col.length;j++)
	  {
		  
		 // alert("column  "+col.length);
			var flid = jQuery("#frmMultiskillassessement input[id='flid']").val();
	         empKeyId= jQuery("#multiskillasssessmentgrid").jqGrid('getCell',rowid,"empKeyid");
	         mKeyid= jQuery("#multiskillasssessmentgrid").jqGrid('getCell',rowid,"Keyid");
	         //  alert(empKeyId);
	         
	         
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
	               // jsonArrO += '"txtMuseKeyid":"'+mKeyid+'",';
	        		jsonArrO += '"txtMuseEmployeeid":"'+empKeyId+'",';
	        		jsonArrO += '"txtMuseUnipositionid":"'+uniquePosId+'",';
	        		jsonArrO += '"txtMuseFlid":"'+flid+'",';
	        	    jsonArrO+= '},';
	        		
			}
           // alert(j+"   jsonArrO  "+jsonArrO);
		}
	 }
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		jsonArrO = (jsonArrO != ']'?jsonArrO:"");
		//alert('jsonArrO: '+jsonArrO);
		return jsonArrO; 
	}

function frmMultiskillassessement_FuntLocHierarchy_SuccessCallBack(result){
	 
	   var flid = result.flId;
		 
		jQuery("#cmbMuseFlid").val(flid);
	
		processGridnew("MultiskillassessmentGrid_input.postass","q=2&flid="+flid,"multiskillasssessmentgrid","pager","","","","MultiOncompleteLoad");
		 setFunctionalLocWidth('MSAfunLocation','650px');
}
</script>

<form action=" " method="post" id="frmMultiskillassessement">
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
			<input type="hidden" id="flid" name="cmbMuseFlid" value="${requestScope.Multiskillempmap.museFlid}"  ></input>
			
			</div>
			<div id="MSAfunLocation" style="width: 846px; "></div>

             </div>	

			</td>
		
		</tr>
	
	 <!--  <tr>
	  <td>
	  <div style="width:200px;">
	  <label> Employee </label>
	  </div>
	 
	  <div style="padding-left:0px;width:200px;">  
				<input class="easyui-text" id="cmbMsaEmployee" name="cmbMsaEmployee" maxlength="75" style="width: 200px; height: 21px;" value=""/>
	  </div>
	  
	  
	  </td>
	  </tr> --> 
	</table>



<div style="float:left;">
<table id='multiskillasssessmentgrid' >
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>
 <input id="txtMuseKeyid" type='hidden' name="txtMuseKeyid" value="${requestScope.Multiskillempmap.museKeyid}" />

	 <input type="hidden" id="mode"/>

</div>
</div>
</div>
</form>