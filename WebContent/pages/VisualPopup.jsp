<script type="text/javascript">
jQuery(document).ready(function(){
	initialiseForm("frmVisualpopup");
	//numericTextBox('txtVccmSerialno');
	jQuery('#submitForm').val('frmVisualpopup');
	 viewGrid("VisualPopup_input.visc","?q=2");
 //var keyid = jQuery("#hdnVccmKeyid").val();
 //alert(keyid);
	
	var sectionId = jQuery("#frmVisualpopup input[id='section']").val();
	var cellId = jQuery("#frmVisualpopup input[id='cell']").val();
	var machId = jQuery("#frmVisualpopup input[id='machine']").val();
	var flid =jQuery("#frmVisualpopup input[id='flid']").val();
   
    //alert(flid);
    var dataStr = "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
   // alert(dataStr + " fgf");
	 loadFunctionalLocation("visualchecklistfunLocation","functionalLoc.visc","visualcheckfunlistLocation","frmVisualpopup",dataStr);
/*jQuery("#btnsave").click(function(){
		saveForm("frmVisualpopup","VisualControlPopup_save.visc");
		});*/
 });

jQuery("#btnsave").click(function(){	
	var keyid = jQuery("#hdnVccmKeyid").val();
	saveForm("frmVisualpopup","visualcheckpoints_save.visc?q=2&keyid="+keyid);
});

jQuery("#btnDelete").click(function(){
	//alert("Select CheckBox");
 removeRecord();
});
function onEnterKeypress(keycode){
	 
	if(keycode == '13'){
 		 jQuery('#btnAddVis').trigger('click');
 	  	  e.stopPropagation();  		
 	}

		}

function viewGrid(url,filterString)
{

	var tableCaption = "Visual";
	var keyid=jQuery("#hdnVccmKeyid").val();
	//alert(keyid);
	if (keyid.trim() != "" || keyid.length<0){
		filterString += "&keyid="+keyid;
	}else {
		filterString = " ";
	}

	//alert(filterString);
	processGridnew(url,filterString,"VisPopGrid","pager",tableCaption,"vpgdoubleClickGrid","","loadComplete","selectRowFunction","");
}


function vpgdoubleClickGrid(id) {
	
	var keyid = jQuery("#VisPopGrid").jqGrid('getCell',id,"KEYID");
	var criteria = jQuery("#VisPopGrid").jqGrid('getCell',id,"CRITERIA");
	var checkpoints = jQuery("#VisPopGrid").jqGrid('getCell',id,"CHECKPOINTS");
	
	var criteriaOrder = jQuery("#VisPopGrid").jqGrid('getCell',id,"CRITERIAORDER");
	var checkpointOrder = jQuery("#VisPopGrid").jqGrid('getCell',id,"SORTORDER");
	jQuery('#hdnVccdKeyid').val(keyid);	
	jQuery('#txaVccdCriteria').val(criteria);
	jQuery('#txaVccdCheckpoints').val(checkpoints);
	
	jQuery('#txtVccdSortorder').val(checkpointOrder);
	jQuery('#txtVccdOrderno').val(criteriaOrder);
}

function loadComplete (id){
	jQuery('.ui-jqgrid-hdiv').css('height','25px');
	
}

//function VisPopGrid_selectRow(id){
	function VisPopGrid_selectRow(id){

	if(jQuery('#jqg_VisPopGrid_'+id).is(':checked'))
	{		
		//alert("alert"+id);	
		visualchkboxCheck(id);	
			
	}
	else{
		visualchkboxUnCheck(id);
	}
}

function VisPopGrid_selectAll(id,status){
	
	for(var i=0; i<id.length; i++){
		if(status)
			visualchkboxCheck(id[i]);
		else
			visualchkboxUnCheck(id[i]);
	}
}

function visualchkboxCheck(rowId)
{//alert(rowId);
  //alert("asd111");
	jQuery("#VisPopGrid").jqGrid('setCell',rowId,'CHECKS','1');
	var rowData = jQuery("#VisPopGrid").jqGrid('getRowData',rowId );
	
	//setFormater("tblAbnAllocation","","dteTargetDate4","frmabnAllocation","",rowId,rowData.TARGETDT,"TARGETDT");
}
function visualchkboxUnCheck(rowId)
{
	jQuery("#VisPopGrid").jqGrid('setCell',rowId,'CHECKS','0');
	
}

function remove_successCallBack(result)
{
		if(result.deleteMsg!="" && result.deleteMsg!=" " && result.deleteMsg!=null )
			 alert(result.deleteMsg);
		else{
		  		jQuery("#VisPopGrid").trigger("reloadGrid");
		  		jQuery('#hdnVccdKeyid').val('');
		}	
}


function frmVisualpopup_successsCallback(result)
{
	jQuery('#hdnVccmKeyid').val(result.successData.keyid); 
	//jQuery("#VisPopGrid").trigger("reloadGrid");
	viewGrid("VisualPopup_input.visc","?q=2");
	
	jQuery('#hdnVccdKeyid').val('');
	
	var sortNo = jQuery('#txtVccdSortorder').val();
	sortNo = parseInt(sortNo)+1;
	jQuery('#txtVccdSortorder').val(sortNo);
	jQuery('#txaVccdCheckpoints').val('');
	
	setFocusOnField('txtVccdSortorder');
}

/* 
function txtvisualformater(id, options, rowObject) {
	var rowid="";
	 rowid = options.rowId;
	 var colval = "";
	 
	 if (rowObject[3]!= undefined && rowObject[3]!= "" )
		 colval=rowObject[3];
	 //onEnterKeypress('+event+');
	 
	 //alert(rowid+" -- "+colval);
	 
	return '<input id="visual_textbox_'+ rowid +'" type="text" value="'+ colval+'" class="easyui-text" style="width:1021px ; height:23px; text-transform:uppercase;" maxlength="495" onkeypress=" onEnterKeypress(event.keyCode );"  />';
	
}
 */

function remove_errorCallBack(result)
{
	  
	
 
}
function removeRecord()
{	
	 var rowid=jQuery("#VisPopGrid").jqGrid('getDataIDs');//		
	 var flag=true;
	 var r;
	 var keyid;
	 var Keyid = ""; 
	 var rowdata ="";
	 for(var i=0;i<rowid.length;i++)
	  {
		   var CHEKVal= jQuery("#VisPopGrid").jqGrid('getCell',rowid[i],"CHECKS");
		   if(CHEKVal=='1')
			 {   		 
			      flag= false;     
				  keyid = jQuery("#VisPopGrid").jqGrid('getCell',rowid[i],"KEYID");					  
				 if(keyid.trim().length>0)
				  {	 
					 Keyid += keyid + ",";   			                
				  } 			 
			 else{
					 
					 rowdata += rowid[i] + ",";   
                     //alert(rowid+" checks......");     
				 }
			 }
	  }
	 if(flag){	 
		        alert("Select CheckBox");
	         }
	 else
		 {
			 r=confirm("Do You Want To Delete?");
			 if(r== true)
			 {   // alert("r");
				 	var cellval =rowdata.split(",");		
		 			if(Keyid != null && Keyid != undefined && Keyid !="")
			 			{
						    Keyid=Keyid.slice(0,-1);
							processAjaxCalls("VisualPopup_delete.visc?&Keyid="+Keyid, "",'remove_successCallBack','remove_errorCallBack');	
	 					}else
		 					{		 					 
			 						if(cellval != null )
				 						{
				 							for(var i=0; i<cellval.length;i++)
					 							{		
													jQuery("#VisPopGrid").delRowData(cellval[i]); 
					 							}
		 							    }	 					    
		 					}
					 
			 } else{	 
           			return false;
				   }	 
		 }
	 
	  
}

/* jQuery("#btnAddVis").click(function()
{ 
	  var row  = jQuery("#VisPopGrid").jqGrid('getDataIDs');
   	  var requiredColArr = ["CheckPoints"];	  
	  if( JqGridValidationRequiredField('VisPopGrid',null,requiredColArr) ){    
	     addRow(row);
		   
		  }
	 	 	 
	});

function addRow(row)
{  
    var val =  jQuery("#hdnval").val();
    var j = parseInt(val);
			if (row == null || row == '' || parseInt(row)<=0) 
			    { 
		 	       var emptyItem =[{KEYID:" ",CRITERIA:" ",CHECKPOINTS:""}];		 	     
			       jQuery("#VisPopGrid").jqGrid('addRowData',j, emptyItem[0]);
			       var k=j+1;
		           var val =  jQuery("#hdnVal").val(k);		      
			    }	
		 else
		      {		     
			      //  for(var i=0;i<row.length;i++){ 
					lastRow = row.length;
			         var emptyItem =[{KEYID:" ",CRITERIA:"",CHECKPOINTS:" "}];
			         jQuery("#VisPopGrid").jqGrid('addRowData',parseInt(lastRow)+1, emptyItem[0]);
			         				
		      }
	
         jQuery("#VisPopGrid").jqGrid('setCell',row.length+1,'SafetyTalk',getFieldValue('CHECKPOINTS'));

  }

function JqGridValidationRequiredField(jqGridId,ckeckForSelColName,requiredColArr)//Validation grid
{
		var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
		
		var reqColLength=requiredColArr.length;
		for( var i = 0; i < allRows.length;i++)
		{
			var row = allRows[i];
			
			var value = ckeckForSelColName != null && ckeckForSelColName != undefined ? row[ckeckForSelColName] : null;
			if( value == null  ||  value.trim()  != "" &&  value.trim().length  != "0")
			{
				for(var colName in row) 
				{
					for(var j=0;j<reqColLength;j++)
					{
						if(requiredColArr[j]== colName )
						{
							var cellValue = parseJqGridCellValue(row[colName]);
						    if (cellValue == null || cellValue == '' || cellValue == undefined || cellValue.trim().length <= 0 )
								{
									alert("Enter the checkpoints");
									return false;
								}
						}
					}
				}
			}
		}
		return true;
	}	
	 */
/*function formattervisualcheck(id, options, rowObject)
{

	var rowId = options.rowId;
	var colId = options.pos;	
	var colvalue =rowObject[2];
	if(colvalue == undefined){
		//alert(colvalue);
		colvalue = "";
		}
	//alert(colId);
if(colId==0)
{
return '<input type="checkbox" id="vischeckbox_'+rowId+'_'+colId+'" name="vischeckbox_'+rowId+'_'+colId+'" style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){visualchkboxCheck1(\''+rowId + '\');}else{visualchkboxUnCheck1(\''+ rowId +'\');}" />';
}
if(colId==3)
{
return '<input type="text" id="vistxt_'+rowId+'_'+colId+'" name="vistxt_'+rowId+'_'+colId+'" value="'+colvalue+'" style="width:780px;  height:23px; text-transform:uppercase" onclick="if(this.checked){visualchkboxCheck1(\''+rowId + '\');}else{visualchkboxUnCheck1(\''+ rowId +'\');}" />';
}
}
function txtvisualformatter(id, options, rowObject)
{	 
	var rowId = options.rowId;
	var colId = options.pos;
	 return '<input type="checkbox" id="visualcheckbox_'+rowId+'_'+colId+'" name="visualcheckbox_'+rowId+'_'+colId+'" onclick="if(this.checked){visualchkboxCheck(\''+rowId + '\');}else{visualchkboxUnCheck(\''+ rowId +'\');}"  style="margin-left:0%;margin-left:-1%\0\;width:100px;  height:15px;"  />';
	 
	
}
*/

function frmVisualpopup_beforeSubmit()
{	
	var gridData  = '&visDetails='+convertvisualToJSONString();
   return gridData; 
}

function checkmaxlength(){
	var maxlength= jQuery("#txaVccdCriteria").val();
	
}

function convertvisualToJSONString()
{
	var cKeyId;                             //det keyid
	var jsonArrO='[';
	
	cKeyId = jQuery('#hdnVccdKeyid').val();
	
    var colkeyid = jQuery('#hdnVccmKeyid').val();                          // call master key id 
      
      var txtCriteria = jQuery('#txaVccdCriteria').val();
      var orderNo = jQuery('#txtVccdOrderno').val();
      var sortorder = jQuery('#txtVccdSortorder').val();
      var txtCheckPoint = jQuery('#txaVccdCheckpoints').val();
      
      txtCheckPoint = txtCheckPoint.trim();
      txtCriteria = txtCriteria.trim();
      
      jQuery('#txaVccdCheckpoints').val(txtCheckPoint);
      jQuery('#txaVccdCriteria').val(txtCriteria);
      
     if (checkCommonValidation()==false)
    	 return false;
     
       	jsonArrO+= '{';
		jsonArrO += '"txtVccdKeyid":"' + cKeyId+'",';
		jsonArrO += '"txtVccmKeyid":"' + colkeyid+'",';
		jsonArrO += '"txaVccdCriteria":"'+txtCriteria+'",';
		jsonArrO += '"txaVccdCheckpoints":"'+txtCheckPoint+'",';
		jsonArrO += '"txaVccdOrderno":"'+orderNo+'",';
		jsonArrO+= '"txtVccdSortorder":"'+sortorder+'",';
		jsonArrO+= '},';

	 if(jsonArrO!="[")
		jsonArrO = jsonArrO.slice(0,-1)+ "]";
	else 
		jsonArrO="";
	//alert(jsonArrO);
    return jsonArrO; 
}

function checkCommonValidation() {
    var flid =jQuery("#frmVisualpopup input[id='flid']").val();
    var txtCriteria = jQuery('#txaVccdCriteria').val();
    var txtCheckPoint = jQuery('#txaVccdCheckpoints').val();
    
    if (flid=="" || flid==" " || flid ==null) {
    	 alert('Please Select Functional Location');
    	 return false;
     }
    
    /* if (txtCriteria=="" || txtCriteria==" " || txtCriteria ==null) {
  	 alert('Please Enter Criteria');
  	 return false;
    }
  	
    if (txtCheckPoint=="" || txtCheckPoint==" " || txtCheckPoint ==null) {
   	 alert('Please Enter Criteria');
   	 return false;
     } */
    return true;
}
/* 
function convertvisualToJSONString_old()
		{
			//alert(2);
			var rowID=jQuery("#VisPopGrid").jqGrid('getDataIDs');//	row get data
			var colid=jQuery("#VisPopGrid").jqGrid("getGridParam","colModel");// col get data
			var rowid="";
			var controlId="";
			var cKeyId;                             //det keyid
			var colValue="";
			var jsonArrO='[';
			//alert("len  "+rowID.length);
			for(i=0;i<rowID.length;i++)
				{
	 
			 rowid=rowID[i];
              //alert("rowid"+rowID[i]);
              cKeyId= jQuery("#VisPopGrid").jqGrid('getCell',rowID[i],"KEYID");
               //alert("cKeyid...."+cKeyId);
              var colkeyid = jQuery('#hdnVccmKeyid').val();                          // call master key id 
                  // alert("colKeyid "+colkeyid);
              // if(cKeyId!=null || cKeyId!="")
		          controlId="visual_textbox_"+rowID[i];
		         // alert("control id"+controlId);
	              colValue=jQuery('#' +controlId).val();
                 //alert("colvalue "+colValue);
            if((colValue!=null && colValue!=""))
					{ 
				
                    jsonArrO+= '{';
					jsonArrO += '"txtVccdKeyid":"' + cKeyId+'",';
					jsonArrO += '"txtVccmKeyid":"' + colkeyid+'",';
					jsonArrO += '"txaVccdCheckpoints":"'+colValue+'",';
					jsonArrO+= '"txtVccdSortorder":"'+"1"+'",';
					jsonArrO+= '},';
				}

       	//alert("8"+jsonArrO);
		}
	
			 if(jsonArrO!="[")
				jsonArrO = jsonArrO.slice(0,-1)+ "]";
				
			else 
				jsonArrO="";
			//alert(jsonarrO);
	        return jsonArrO; 
}

 */
</script>

<form id="frmVisualpopup" name="frmVisualpopup" >


<div id="wrapperRpt" style="width: 135%;">
	<div id="frmVisualpopupFuntKeyIds"  >							
	<input type="hidden" id="factory" name="cmbVcclFactoryid" value=" "  ></input>			
	<input type="hidden" id="section" name="cmbVcclSectionid" value=" "  ></input>
	<input type="hidden" id="cell" name="cmbVcclCellid" value=" "  ></input>
	<input type="hidden" id="machine" name="cmbVcclEquipmentid1" value=" "  ></input>
	<input type="hidden" id="flid" name="cmbVccmFlid" value="${requestScope.newSopTlVisualchecklistmst.vccmFlid}" ></input>							
</div>						
<div id="visualchecklistfunLocation" style="width:120%;">
</div>
		
<div class="easyui-paddingbfpx">
			<label  class ="mandatory-lbl">Title</label>
		</div>
		<div class="easyui-paddingbfpx" style="width: 105%;">
			<input id="txtVccmTitle" type="text" class="easyui-text"  value="${requestScope.newSopTlVisualchecklistmst.vccmTitle}" size="15" name="txtVccmTitle"  maxlength="50"    style=" width : 99%;;">
		</div>	
		<div class="easyui-paddingbfpx" style="width: 130%;">
			<label  class ="mandatory-lbl">Order</label>
			<span style="padding-left: 30px;"><label  class ="mandatory-lbl">Criteria</label></span>
			<span style="padding-left: 360px;"><label  class ="mandatory-lbl">Sort Order</label></span>			
			<span style="padding-left: 10px;"> <label  class ="mandatory-lbl">Checkpoint</label> </span>
		</div>

		<div class="easyui-paddingbfpx" style="width: 130%;">
			<input type="text" id="txtVccdOrderno" name="txtVccdOrderno" value="" style="width: 50px; text-align: center; vertical-align: top;"  maxlength="2" class="easyui-text"  >
			<span style="padding-left: 10px;"> 
				 <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style=" width : 400px; text-transform: uppercase;height : 70px;" maxlength="495" id="txaVccdCriteria" class="limit-length" name="txaVccdCriteria" >${requestScope.newSopTlVisualchecklistdtl.VccdCriteria}</textarea>
			</span>
			<span style="padding-left: 10px;">  
				<input type="text" id="txtVccdSortorder" name="txtVccdSortorder" value="" style="width: 50px;vertical-align: top;"  maxlength="2" class="easyui-text"  >
				 </span>
			
			 <span style="padding-left: 10px;"> 
				 <textarea onkeyup="restrict(this.value,this.id)" onpaste="restrictPaste(this.value,this.id)" rows="2" cols="80" style=" width : 400px; text-transform: uppercase;height : 70px;" maxlength="500" id="txaVccdCheckpoints" class="limit-length" name="txaVccdCheckpoints" >${requestScope.newSopTlVisualchecklistdtl.VccdCriteria}</textarea>
			</span>
			
			<span style="padding-left: 10px;padding-top: 0px">
				<input type="button" class="easyui-button" style="width:60px;" id="btnsave" name="btnsave" value="Save" />
			</span>
			<span style="padding-left: 10px;padding-top: 0px"> 
				<input type="button" class="easyui-button" style="width:60px;" id="btnDelete" name="btnDelete" value="Delete"/>
			</span>

		</div>
		
		<table><tr><td>
			<span style="padding-left: 40px;padding-top: 0px" id="err_txaVccdCriteria" class="tpm-errormsg"></span>			
			</td>
			<td> <span style="padding-left: 500px;padding-top: 0px;" id="err_txaVccdCheckpoints" class="tpm-errormsg"></span> </td>
		</tr>
		</table>
		
		<div style="float:left">
			<div id="VisPopup">
				<table id="VisPopGrid"></table>	
				<div id="pager"></div>
					</div>
		</div>
		
<input id="hdnVccmKeyid" type='hidden' name="hdnVccmKeyid"  value="${requestScope.newSopTlVisualchecklistmst.vccmKeyid}" />
<input id="hdnVccdKeyid" type='hidden' name="hdnVccdKeyid"  value="" />
<input type="hidden" id="mode"/>
<input type="hidden" id="hdnval" name="hdnval" value="1" />
</div>
</form>





