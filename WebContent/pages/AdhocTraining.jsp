<script>
jQuery(document).ready(function(){

	processGridnew("AdhocTraining_input.adtg","?q=2","topicgrid","topicpager");

	processGridnew("AdhocTrainingPart_input.adtg","?q=2","Participantsgrid","Participantspager");
	
	var factId = jQuery("#frmAdhoctraining input[id='factory']").val();
    var sectionId = jQuery("#frmAdhoctraining input[id='section']").val();
    var cellId = jQuery("#frmDMT input[id='cell']").val();
    var machId = jQuery("#frmAdhoctraining input[id='machine']").val();
    var flId = jQuery("#frmAdhoctraining input[id='flid']").val();

    //alert("flId" +flId);

   var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId + "&flid=" + flId;

   loadFunctionalLocation("AdhocTrainingLocation", "functionalLoc.commonFilter", "AdhocTrainigLocation", "frmAdhoctraining",dataStr);


   formatDateBox('dteAdhocTrainigdate', 'dd-MMM-yyyy');


   fillComboBox("frmAdhoctraining", "cmbAdhocTrainigTakenBy", "employee.commonFilter");

   jQuery("#btnAddTopics").click(function(){
		  var row  = jQuery("#topicgrid").jqGrid('getDataIDs');
		 
		  //var requiredColArr = ["Answer"];
			//if(JqGridAnswerValidationRequiredField('jqGridAnswer',null,requiredColArr) )
				 addRow(row);				
		});

   jQuery("#btnAddParticipants").click(function(){
		  var row  = jQuery("#Participantsgrid").jqGrid('getDataIDs');
		  //alert("row::::row3434343434"+row);
		 // var requiredColArr = ["Answer"];
			//if(JqGridAnswerValidationRequiredField('jqGridAnswer',null,requiredColArr) )
				addRows(row);				
		});

});

function addRow(row,val)
{  //alert("safk                 "+val);
	// alert("row::::row Now Testing   ="+row);
	 var i=0;
	 var val =  jQuery("#hdnVal").val();
	 //alert("val Now Testing "+val);
	 var j = parseInt(val);
	 //alert("j Now Testing "+j);
	 
	 if ( row == null || row == '' || parseInt(row) <= 0) 
	    {
			
	        //jQuery("#jqGridAnswer").jqGrid('setCell',1,'selectAnswer','1');
			emptyItem =[{txtKEYID:" ",txtTopics:" "}];
			
			
			jQuery("#topicgrid").jqGrid('addRowData',j, emptyItem[0]);
			
			
			var k = j+1;
	    	jQuery("#hdnVal").val(k);
	    	
	    }	
		else
	      {
          
		     for(i=0;i<row.length;i++)
				lastRow = row[i];
		     
				if(j==1){
					emptyItem =[{txtKEYID:" ",txtTopics:" "}];
			
		     }	
		     	else
			     {
		     		emptyItem =[{txtKEYID:" ",txtTopics:" "}];
					
				 }
			     	jQuery("#topicgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
					var k = j+1;
			    	jQuery("#hdnVal").val(k);			
	      }   
		    
	    	
}

function addRows(row,val)
{   
	 //alert("safk                 "+val);
	 // alert("row::::row Now Testing   ="+row);
	 var i=0;
	   
	 var val =  jQuery("#hdnVal").val();
	 //alert("val Now Testing "+val);
	 var j = parseInt(val);
	 //alert("j Now Testing "+j);
	 
	 if ( row == null || row == '' || parseInt(row) <= 0) 
	    {
			
			emptyItem =[{txtKEYID:" ",txtName:" ",txtAddress:" "}];
			
			jQuery("#Participantsgrid").jqGrid('addRowData',j, emptyItem[0]);
				
			var k = j+1;
		   	jQuery("#hdnVal").val(k);
	    	
	    }	else
	      {
            
		     for(i=0;i<row.length;i++)
				lastRow = row[i];
		     
				if(j==1){
				emptyItem =[{txtKEYID:" ",txtName:" ",txtAddress:" "}];
			
		     }	
		     	else
			     {
		     		emptyItem =[{txtKEYID:" ",txtName:" ",txtAddress:" "}];
					
				 }
				     	jQuery("#Participantsgrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
						var k = j+1;
				    	jQuery("#hdnVal").val(k);			
	      }
		    
		    
	    	
}

function formatterTxt(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	//alert(columnNo+"columnNo");
	var keyid = rowObject[0];
	var val =  jQuery("#hdnVal").val();
	
	if(columnNo==3 )
		return '<input id="btnDelete_'+id+'" class="grdButton" name="btnDelete" type="button"  onclick=removeRecord("'+keyid+'","'+id+'"); style="text-align:left"/>';
	if(columnNo==4 )
		return '<input id="btnDelete_'+id+'" class="grdButton" name="btnDelete" type="button"  onclick=removeRecord("'+keyid+'","'+id+'"); style="text-align:left"/>';
		
}

 function frmAdhoctraining_FuntLocHierarchy_SuccessCallBack(flIds){//alert(" Inside FunctionalSuccesscallback ");
	  setFunctionalLocWidth('frmAdhoctraining','800px');
	  
}

</script>

 <form name="frmAdhoctraining" id="frmAdhoctraining" >
 <div id="wrapper" style="width:98%">
 <div style="margin-left:100px;margin-top:40px;">
 <table>
 <tr>
 <td colspan="3">
 <div> <div id="frmadhocFuntKeyIds">
			<input type="hidden" id="factory" name="cmbadhtgfactory" value=""></input> 
			<input type="hidden" id="section" name="cmbadhtgsection" value=""></input> 
			<input type="hidden" id="cell"    name="cmbadhtgcell" value=""></input> 
			<input type="hidden" id="machine" name="cmbadhtgmachine" value=""></input>
			<input type="hidden" id="flid" name="cmbadhtgFlid" value=""></input>
	</div>
	<div  class="easyui-paddingbfpx" id="AdhocTrainingLocation" style="width:820px;"></div>

  </div>
 </td>
 </tr>
 </table>
 
 <table>
 <tr>
 <td> 
 <div>
 <label class="mandatory-lbl">Date</label>
 </div>
 <div>
 <input class="easyui-text"id="dteAdhocTrainigdate" name="dteAdhocTrainigdate" maxlength="20"  value="" style="width: 110px; height: 21px;" value=""/>
 </div>
 </td>
 <td style="padding-left:20px;">
 <div style="width: 110px;"  >
 <label class="mandatory-lbl">Duration</label>
 </div>
 <div >
 <input class="easyui-text"id="AdhocTrainigDuration" name="AdhocTrainigDuration" maxlength="20"  value="" style="width: 120px; height: 21px;" value=""/>
 </div>
 </td>
 
 
  <td>
  <div style="padding-left:200px;">
 <div>
 <label class="mandatory-lbl">Taken By</label>
 </div>
 <div>                             
 <input class="easyui-combobox"id="cmbAdhocTrainigTakenBy" name="cmbAdhocTrainigTakenBy" maxlength="20"  value="" style="width: 200px; height: 21px;" value=""/>
 </div>
 </div>
 </td>
 
 </tr>
 
 <tr>
 <td colspan="2" >
 <div style="margin-top:10px;">
 <div>
 <label>Remarks</label>
 </div>
 <div>
 <textarea rows="2" style="width:256px;height:50px; resize: none;text-transform: uppercase;" maxlength="200" cols="" id="txtAdhocTrainingRemarks" name="txtAdhocTrainingRemarks">   </textarea>
 </div>
 </div>
 </td>
 <td valign="top" style="padding-left:200px;">
 <div style="margin-top:10px;">
 <div>
 <label>Venu</label>
 </div>
 <div>
 <input type="text" id="txtAdhocTrainingVenu" name="txtAdhocTrainingVenu" clear="false" class="easyui-text"  style="width:120px;" value="" >
 </div>
 </div>
 </td>
 
 </tr>
 
 </table>
 
 <table>
 <tr>
 <td>
 <div >
     <div>
     <label>Topics</label>
     <span style="float:right;">
     <input class="easyui-button" type="button" id="btnAddTopics" name="btnAddTopics" value="Add" style=" width : 49px;height:22px;">
     </span>
     </div>
     <div style="padding-top:4px;">
	 <table id='topicgrid'><tr><td></td></tr></table>
	 <div id='topicpager'></div>
	 </div>								
 </div>
 </td>
 
 <td>
 <div style="padding-left:40px;">
 <div>
     <label>Participants</label>
     <span style="float:right;">
     <input class="easyui-button" type="button" id="btnAddParticipants" name="btnAddParticipants" value="Add" style=" width : 49px;height:22px;">
     </span>
     </div>
     <div style="padding-top:4px;">
	 <table id='Participantsgrid'><tr><td></td></tr></table>
	 <div id='Participantspager'></div>
	 </div>
 </div>
 </td>
 </tr>
 
 </table>
 </div>
 </div>
 <input type="hidden" id="hdnVal" value="1" />
 </form>