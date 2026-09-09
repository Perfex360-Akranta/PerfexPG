<script>
jQuery(document).ready(function(){
	initialiseForm('frmtrainingNeed');
	jQuery('#submitForm').val('frmtrainingNeed');
	formatDateBox('dteTnimDate', 'dd-MMM-yyyy');
	fillComboBox("frmtrainingNeed","cmbTnimType","traningType.tatnd","",false);
	var factId = jQuery("#frmtrainingNeed input[id='factory']").val();
    var sectionId = jQuery("#frmtrainingNeed input[id='section']").val();
	var cellId = jQuery("#frmtrainingNeed input[id='cell']").val();
	var machId = jQuery("#frmtrainingNeed input[id='machine']").val();
	var flid = jQuery("#frmtrainingNeed input[id='flid']").val(); 
	jQuery("#frmtrainingNeed input[id='flid']").val(flid);

	var dataStr = "&factId=" + factId
			+ "&sectionId=" + sectionId
			+ "&cellId=" + cellId + "&machId="
			+ machId+"&flid="+ flid;
	  loadFunctionalLocation("TaskTopicfunLocation", "functionalLoc.tatnd", "TaskTopicfunLocation", "frmtrainingNeed",dataStr);
	  var type=getFieldValue('cmbTnimType');  
	  var date =getFieldValue('dteTnimDate');
	 
	  //viewGrid('q=2&type='+type+"&flid="+flid+"&date="+date);
 
	  jQuery('#frmtrainingNeed .easyui-text').css('text-transform', 'uppercase');
	  jQuery('#frmtrainingNeed textarea').css('text-transform', 'uppercase');

	  var newdate=jQuery("#newdate").val();
	  if(newdate=="create" && newdate!="" && newdate!=" ")
	   {
	       fillWithCurrentDate('dteTnimDate');
	   }
	  jQuery("#viewbtn").click(function() {			
				  // var typeId = getFieldValue('cmbTnimType');
				   var typeId = jQuery("#cmbTnimType").combobox('getValue');
				   var flid = jQuery("#frmtrainingNeed input[id='flid']").val();
				   var date =getFieldValue('dteTnimDate');
				   if(flid==null || flid =="" || flid==" "){
						  alert("Select Functional Location");
					   }
				   else if (typeId == null || typeId=="" || typeId ==" "){
					    alert("Select Type"); }				   
				   else if(date == null || date=="" || date==" ") {
						    alert("Select Date"); }				   
				   else{ 
						viewGrid('q=2&flid='+flid+"&type="+typeId+'&Date='+date);
						//jQuery("#frmtrainingNeed input[id='flid']").val(flid); 
				      }
				 }); 
});
function frmtrainingNeed_FuntLocHierarchy_SuccessCallBack(result){
	
	//var type=getFieldValue('cmbTnimType'); 
	var type = jQuery("#cmbTnimType").combobox('getValue');
	var flid = jQuery("#frmtrainingNeed input[id='flid']").val();
	var date =getFieldValue('dteTnimDate');
	if(type !=null && type !=" " && type !=""){
      if(date ==null && date ==" " && date ==""){
           alert("Select Date");
          }
      else{
		viewGrid('q=2&flid='+flid+"&type="+type+'&Date='+date);
		jQuery("#frmtrainingNeed input[id='flid']").val(flid); 
        }
    }
	else{
		 viewGrid('');
		}
}
function viewGrid(filter){
	processGridnew("traningNeed_input.tatnd",filter,"jqGridTraining","Pager","", "NeedDoubleClick","","Load_CompleteTraining");
}
function Load_CompleteTraining()
{
	var row = jQuery("#jqGridTraining").jqGrid('getDataIDs');
	for ( var i = 0; i < row.length; i++) {
		var TopicId = jQuery("#jqGridTraining").jqGrid('getCell', row[i], "TOPICID"); 
		var TPInKeyid = TopicId.split(',');
		for(var j=0;j<TPInKeyid.length;j++){			
			var topickeyid=TPInKeyid[j];
			var colm = jQuery("#jqGridTraining").jqGrid("getGridParam","colModel");				
		  for(var k=8;k<colm.length;k++){ 
			    var tpkeyid = jQuery('#checkbox_'+row[i]+'_'+k).attr('tpkeyid');	
                if(topickeyid==tpkeyid){
               	    var checkval= jQuery('#checkbox_'+row[i]+'_'+k).attr('checkvalue','1');
               		jQuery('#checkbox_'+row[i]+'_'+k).attr('checked',true);   
                    } 
			  }
	      }
	 }
}
function NeedDoubleClick(id){
	var rowData = jQuery("#jqGridTraining").jqGrid('getRowData',id);
	var type = rowData.TYPE;
	var val=jQuery("select[id='topic'] option:selected").val();
	if(val =="Employee"){
		LoadPopUp("NeedPop","EmployeeGrid_input.empnom?&keyid=EPM0000020", true, "70%", "70%", "15%", "18%", "", "Training Need");
	}
}
function typeOnchange(val){
	//alert(val);
	var flid = jQuery("#frmtrainingNeed input[id='flid']").val(); 
	viewGrid('q=2&type='+val+'&flid='+flid);
}
function txtFormatter(id, options, rowObject,cellValue) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var tpkeyid = columnName.split('_');
	var columnNo = options.pos;
	var gid=options.gid;
	 return '<input type="checkbox" id="checkbox_'+id+'_'+columnNo+'" name="checkbox_'+id+'_'+columnNo+'"  tpkeyid='+tpkeyid[1]+'  style="margin-left:40%;margin-left:1%\0\;"  checkvalue=""  onclick="if(this.checked){chkboxCheck1('+id + ','+columnNo+');}else{chkboxUnCheck1('+id +','+columnNo+');}" />';
}
function chkboxCheck1(rowId,columnNo) {
	jQuery('#checkbox_'+rowId+'_'+columnNo+'').attr('checkvalue','1');
}
function chkboxUnCheck1(rowId,columnNo) {
	jQuery('#checkbox_'+rowId+'_'+columnNo+'').attr('checkvalue','0');
}
function frmtrainingNeed_beforeSubmit() {
	var flid = jQuery("#frmtrainingNeed input[id='flid']").val(); 
	var type = jQuery("#cmbTnimType").combobox('getValue');
	var date =getFieldValue('dteTnimDate');
	var Remarks=jQuery("#txtTnimRemarks").val();
	 if(flid==null || flid==''){
		   alert("Select Functional Location");
		   return false;
		}	
	 else if(type==null ||type==' '||type==''){ 
			alert("Select Type");
			return false ;
	    }
	 else if(date==null || date==''){
		   alert("Select Date");
		   return false ;
		}
	else{
	      if(convertUnquieTopicToJSONString()){
	    	    var gridData = '&TrainingNeed='+ convertUnquieTopicToJSONString()+'&Keyidneed='+keyidconverJson();
				return gridData;
		       }
	       else{
		          alert("Select Topic");
		          return false;
		       }		  
		}
}
function convertUnquieTopicToJSONString()
{
	var row = jQuery("#jqGridTraining").jqGrid('getDataIDs');//	row get data
	var colm = jQuery("#jqGridTraining").jqGrid("getGridParam","colModel");// col get data
	var Keyid="";
	var jsonArrO = '[';
	for (i = 0; i < row.length; i++) {
		rowid = row[i];	
		for(j=8;j< colm.length; j++)
			{
			var TypeId = jQuery("#jqGridTraining").jqGrid('getCell',rowid,"ROLEKEYID");
			var flid = jQuery("#frmtrainingNeed input[id='flid']").val();
			var type = jQuery("#cmbTnimType").combobox('getValue');
			var date=getFieldValue("dteTnimDate");
			var Remarks=jQuery("#txtTnimRemarks").val(); 
			var checkval= jQuery('#checkbox_'+rowid+'_'+j).attr('checkvalue');
			if(checkval=='1'){	
				var tpkeyid = jQuery('#checkbox_'+rowid+'_'+j).attr('tpkeyid');
				jsonArrO += '{';
				jsonArrO += '"txtTnimKeyid":"' + Keyid + '",';
				jsonArrO += '"txtTnimFlid":"' + flid + '",';
				jsonArrO += '"txtTnimType":"' + type + '",';
				jsonArrO += '"dteTnimDate":"' + date + '",';			
				jsonArrO += '"txtTnimRemarks":"' + Remarks + '",';
				jsonArrO += '"txtTnimTypeid":"' + TypeId + '",';
				jsonArrO += '"txtTnimTopicid":"' + tpkeyid + '"';
				jsonArrO += '},';			
			}	
		}
	}
	if (jsonArrO != "["){
		jsonArrO = jsonArrO.slice(0, -1) + "]";
		return jsonArrO;
	}
	else{
			jsonArrO = "";
			return false;
		}	
}
function keyidconverJson(){
	var row = jQuery("#jqGridTraining").jqGrid('getDataIDs');//	row get data
	var colm = jQuery("#jqGridTraining").jqGrid("getGridParam","colModel");// col get data
	var jsonArrO = '[';
		for (i = 0; i < row.length; i++) {
			  rowid = row[i];	
			  var cKeyId = jQuery("#jqGridTraining").jqGrid('getCell',rowid,"KEYID");
			  var tpkeyid= jQuery("#jqGridTraining").jqGrid('getCell',rowid,"TOPICID");
			  var MKeyId= cKeyId.split(',');
			  var topicid= tpkeyid.split(',');
		for(k=0;k<MKeyId.length;k++){
			var Keyid=MKeyId[k];
		    var tpKeyid=topicid[k];	
			jsonArrO += '{';
			jsonArrO += '"txtTnimKeyid":"' + Keyid + '",';
			jsonArrO += '"txtTnimTopicid":"' + tpKeyid + '"';
			jsonArrO += '},';
		   }
	    }
	if (jsonArrO != "[")
		jsonArrO = jsonArrO.slice(0, -1) + "]";
	else
		jsonArrO = "";
	return jsonArrO;
 }

/*function dteTnimDate_onSelect(record)
{
     var typeId = getFieldValue('cmbTnimType');
     var flid = jQuery("#frmtrainingNeed input[id='flid']").val();
     var date =getFieldValue('dteTnimDate');
     if((typeId == null || typeId=="" || typeId==" ") && (flid == null || flid=="" || flid==" ")){
            alert("Select Function Location and Type");
         }
     else if((typeId == null || typeId=="" || typeId==" ") && (flid != null || flid!="" || flid!=" ")){
         alert("Select Type");
         }
     else{    
 	        viewGrid('q=2&type='+typeId+'&flid='+flid+'&Date='+date);
         }
}*/
/*function frmtrainingNeedcmbTnimType_onSelect(record){
     var typeId = getFieldValue('cmbTnimType');
     var flid = jQuery("#frmtrainingNeed input[id='flid']").val();
     var date =getFieldValue('dteTnimDate');
     if((date == null || date=="" || date==" ")){
           // alert("Select Date");
         }
     else{    
 	        viewGrid('q=2&type='+typeId+'&flid='+flid+'&Date='+date);
         }
}*/
function frmtrainingNeed_successsCallback(result){	
	jQuery("#jqGridTraining").trigger("reloadGrid");	    
}
function frmtrainingNeed_beforeDelete(){
	 if(keyidconverJson()){
			var gridData = '&Keyidneed='+keyidconverJson();
			return gridData;
			}
     else{
         return false;
         }
}
function frmtrainingNeed_deleteSuccessCallback(result){	
	var keyid=result.successData.Tnimkeyid;
	if(keyid!=null && keyid !=" " && keyid !="" && keyid !="undefined"){
				alert(result.successData.msg);
				navigateToNextForm("traningLead_input.tatnd","Training Need Identification");  
		}
}
</script>
<form id="frmtrainingNeed" name="frmtrainingNeed">
<div id="wrapperRpt">
 <div id="frmRevFuntKeyIds">
					<input type="hidden" id="factory" name="factory" value=""></input> 
					<input type="hidden" id="section" name="section" value=""></input> 
					<input type="hidden" id="cell"    name="cell" value=""></input> 
					<input type="hidden" id="machine" name="machine" value=""></input>
					<input type="hidden" id="flid" name="cmbTnimFlid" value="${requestScope.flid}"></input>
</div>
  <div style="padding-left:774px;padding-top:1px;padding-left:700px\9;"></div>
  <div class="easyui-paddingbfpx" id="TaskTopicfunLocation" style="width: 95%;width:104%\9;padding-left:4px;padding-left:10px\9;"> 
  </div>
<table>
	<tr>
		<td style="padding-bottom:16px;"><label class="mandatory-lbl">Type</label>
			<div>
			<input  id="cmbTnimType" name="cmbTnimType" style=" width : 150px;" value="${requestScope.type}" /> 
			</div>
		</td>
		<td style="padding-left:5px;padding-bottom:16px;"><label class="mandatory-lbl">Date</label>
			<div><input  id="dteTnimDate" name="dteTnimDate" class="easyui-datebox" style="width:100px;" value="${requestScope.date}"  /></div>
		</td>
		<td style="padding-top: 12px;padding-left:5px;"><label>Remarks</label>
			<div><textarea id="txtTnimRemarks" name="txtTnimRemarks" rows="2"  maxlength="498"  class="easyui-text" Style="width:250px;height:50px;font-size: 12" >${requestScope.Remarks}</textarea> 
			</div>
		</td>
		<td style="padding-left:10px;">
		<input type="button" class="easyui-button" id="viewbtn" name="viewbtn" Style="width:50px;font-size: 12" value="View"/>
		</td>
	</tr>
</table>
	<div>
		<table id="jqGridTraining">
			<tr><td></td></tr>
		</table>
		<div id="Pager"></div>
	</div>

</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
<input type="hidden" id="newdate" name="newdate" value="${requestScope.NewMode}"/>
</form>