<script>

jQuery(document).ready(function(){//alert("Multiassessement");

	var factId = jQuery("#frmMultiSkill input[id='factory']").val();
	var sectionId = jQuery("#frmMultiSkill input[id='section']").val();
	var cellId = jQuery("#frmMultiSkill input[id='cell']").val();
	var machId = jQuery("#frmMultiSkill input[id='machine']").val();
	var flid = jQuery("#frmMultiSkill input[id='flid']").val();
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
	loadFunctionalLocation("MultifunLocation","functionalLoc.commonFilter","MultifunLocationValues","frmMultiSkill",dataStr);
	
	formatDateBox('dteMultiSkilldate', 'dd-MMM-yyyy');
	
	fillComboBox("frmMultiSkill","cmbmultishift","combo_shift.brdn" );

	processGridnew("Multiskill_input.postass","q=2","MultiskillGrid","Multiskillpager","","","","loadComplete","","");
});

function TxtMulti(id, options, rowObject) {
    //alert("Inside Formmter");
	var Id = options.rowId;
	//alert("Id:1"+Id);
	//var columnName = options.colModel.name;
	//alert("columnName:        "+columnName);
	var columnid = options.pos;
	//alert("columnid:4       "+columnid);
    
    //  alert("rowObject[2] "+columnid+"  :::::::"+rowObject[columnid]);
    
	if(1 == rowObject[columnid] ){  
	return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value="" style="background-color:green;"/>';
	}
	else
		 return " ";
}

function loadComplete() {
  	 var row = jQuery("#MultiskillGrid").jqGrid('getDataIDs');
  	 var cm = jQuery("#MultiskillGrid").jqGrid("getGridParam", "colModel");

  	 
  	 for(var i=0;i<row.length;i++)
  	 {
  		 for(var j=1;j<cm.length;j++)
      	 {
  	    	 var zeroVal = jQuery("#MultiskillGrid").jqGrid('getCell',row[i],cm[j].name);	

  	    	 if(zeroVal==' '){
  	   			jQuery("#MultiskillGrid").setCell(row[i], cm[j].name.trim(), '',{'background-color':'white'});
  	   			
  	   	 	 }
  	   	 	 else if(zeroVal.trim().length>14){//alert("Inside Color::2");
  			 jQuery("#MultiskillGrid").setCell(row[i], cm[j].name.trim(),'',{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'green'});
  	 	 } 
	 		 
      	 }
  	 }
  	 
  	     
  	 
  }
</script>

<form id="frmMultiSkill" name="frmMultiSkill">
<div style="margin-top:0px;margin-left:80px;">
<table>
<tr>
<td colspan="2">
			
			<div  id="frmMultiskillassessementFuntKeyIds"  >
			<div style="float: left;">
			<input type="hidden" id="factory" name="cmbMSAFactoryid" value=""  ></input>			
			<input type="hidden" id="section" name="cmbMSASectionid" value=""  ></input>
			<input type="hidden" id="cell" name="cmbMSACellid" value=""  ></input>
			<input type="hidden" id="machine" name="cmbMSAEquipmentid1" value=""  ></input>
			<input type="hidden" id="flid" name="cmbMSAflid" value=""  ></input>
			</div>
			
			<div id="MultifunLocation" style="width: 846px; "></div>

             </div>	

			</td>
</tr>
</table>
<table>
<tr>
<td>
<div style="margin-top:10px;">
<div>
<label> Date </label>
</div>
<div>
<input id='dteMultiSkilldate' name="dteMultiSkilldate" type="text" class="easyui-datebox" value=" "  maxlength="50" style="width: 120px;"/>
</div>
</div>
</td>
<td>
<div style="margin-top:10px; margin-left:20px;">
<div >
<label>Shift</label>
</div>
<div>
<input class="easyui-text"  style="width:100px; height:25px ;text-transform: uppercase;" id="cmbmultishift"   name="cmbmultishift" value="" size="15"  / >
</div>
</div>
</td>
</tr>
</table>
<table>
<tr>
<td>
<table id="MultiskillGrid"  ></table>
		<div id="Multiskillpager"></div>
</td>
</tr>
</table>

</div>
</form>