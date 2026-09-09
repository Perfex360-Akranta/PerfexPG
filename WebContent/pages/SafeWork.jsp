<script>
jQuery(document).ready(function(){

	initialiseForm('frmSafework');
	jQuery('#submitForm').val('frmSafework');
	formatDateBox('dteSwpmIssuedate','dd-MMM-yyyy');
	formatDateBox('dteSwpmPrepareddate','dd-MMM-yyyy');
	var keyid = jQuery("#hdnSwpmKeyid").val();
	processGridnew("Safeworkgrid_input.uwp","?q=2&keyid="+keyid,"safeworkgrid","","","GridPopupdoubleclick","","load_complete");
	fillComboBox('frmSafework','cmbSwpmPreparedby', 'employeeFilter.commonFilter');
	var factId = jQuery("#frmSafework input[id='factory']").val();
    var sectionId = jQuery("#frmSafework input[id='section']").val();
    var cellId = jQuery("#frmSafework input[id='cell']").val();
    var machId = jQuery("#frmSafework input[id='machine']").val();
    var flid = jQuery("#frmSafework input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId+"&flid="+flid;
    if(dataStr.trim().length<=0)
    	dataStr = "";	  					
    loadFunctionalLocation("safework","functionalLoc.uwp","frmSafeworkLocationfunLocationValues","frmSafework",dataStr);

	var btnName = jQuery("#hdnBtnName").val();
	jQuery("#btnViewTemplate").val(btnName);
	jQuery("#btnViewTemplate").click(function()
	{
		var safeid = getFieldValue("hdnSwpmKeyid");
		if(safeid != null && safeid.length > 0)
		window.open("SafeworkRpt_Excelview.uwp?safeid="+safeid);
});

	 var mode= jQuery('#mode').val();
		//alert(mode);
		if(mode=="view"){
			//alert("3");
			readOnlyFields("dteSwpmPrepareddate");
			//readOnlyFields("cmbVcclEmployeeid");
			readOnlyFields("txtSwpmSwpno");
			readOnlyFields("txtSwpmActivity");
			//readOnlyFields("btnDelete");
			disableField('frmSafework','cmbSwpmPreparedby');
		    disableField('frmSafework','btnAdd');
		    disableField('frmSafework','btnDelete');
			disableField('frmSafework','dteSwpmIssuedate');
			
			}
		var keyid = jQuery("#hdnSwpmKeyid").val();
		if(keyid== null && keyid==""){
			 fillWithCurrentDate('dteSwpmPrepareddate');	
			 fillWithCurrentDate('dteSwpmIssuedate');	
		}
    	jQuery("#btnAdd").click(function()
    		{
    			  var row  = jQuery("#safeworkgrid").jqGrid('getDataIDs');
    			   addRow(row);
    			 	 	 
    			});

    jQuery("#btnDelete").click(function()
    {
    	var selArray =  jQuery("#safeworkgrid").jqGrid('getGridParam', 'selarrrow');
    	if( selArray.length > 0 ){
        	
    	var singlekeyid ="";
		for(var i=0;i<selArray.length;i++){
				var refkeyid=jQuery("#safeworkgrid").jqGrid('getCell',selArray[i],"hdnSwpdKeyid"); 
				singlekeyid = singlekeyid + "," + refkeyid;
			}

		var keyid= singlekeyid.substring(1,singlekeyid.length);
			if (keyid != null && keyid != 'undefined' && keyid != undefined && keyid != "" && keyid.trim().length>0) 
			{
				var r = confirm("Do You Want To Delete?");
				if (r == true) {
					processAjaxCalls("Safeworkform_delete.uwp","?q=2&keyid="+keyid,"onsuccessCallBack","remove_errorCallBack");
	
				}
				 else{
						return false;
					} 
		    }
    	 }
    });

  /*  jQuery("#btnSave").click(function()
    		{
    	//var jsonData =  getGridSelectArray("safeworkgrid");
    	//var gridData =  '&selectedrowIDs='+jsonData;
    	//alert(gridData);
    	
    	saveForm("frmSafework","Safeworkgrid_save.uwp?q=2");
    		});*/
});

function frmSafework_beforeSubmit()
{
	var selArray =  jQuery("#safeworkgrid").jqGrid('getGridParam', 'selarrrow');
	if( selArray.length > 0 ){
		var jsonData =  getGridSelectArray("safeworkgrid");
		var gridData =  jsonData;
		if( gridData.length>0  )
			return '&selecteddata='+gridData ;
		else{
			return false;
			}
			
	}
	
}
function onsuccessCallBack(result){
	alert(result.successData.msg);
	jQuery("#safeworkgrid").trigger("reloadGrid");
	
}
function frmSafework_successsCallback(result){
	
}
function addRow(row)
{  
	var val =  jQuery("#hdnVal").val();
	var j = parseInt(val);
			if (row == null || row == '' || parseInt(row)<=0) 
			    { 
		 	       var emptyItem =[{hdnSwpdKeyid:" ",txtSwpdSerialno:" ",txtSwpdCheckpoints:"  "}];	
			       jQuery("#safeworkgrid").jqGrid('addRowData',j, emptyItem[0]);
			       var k =j+1;
		           jQuery("#hdnVal").val(k);
			    }	
		 else
		      {		     
			        for(var i=0;i<row.length;i++) 
							lastRow = row[i];
					
					var serialno = getFieldValue("txtSwpdSerialno_"+lastRow);
					var Checkpoints =getFieldValue("txtSwpdCheckpoints_"+lastRow);
			         var emptyItem =[{hdnSwpdKeyid:" ",txtSwpdSerialno:"  ",txtSwpdCheckpoints:"  "}];
			         jQuery("#safeworkgrid").jqGrid('addRowData',parseInt(lastRow)+1,emptyItem[0]);
			         				
		      }
  }
function GridPopupdoubleclick(id)
{
	 //var rowData = jQuery("#safeworkgrid").jqGrid('getRowData',id );
	 //setFieldValue("txtSwpdSerialno",rowData.SERIALNO);
	 //setFieldValue("txtSwpdCheckpoints",rowData.CHECKPOINTS);
	 //var dtlkeyid = rowData.KEYID;
	 //jQuery('#hdnSwpdKeyid').val(dtlkeyid);
	 
}

function frmSafework_FuntLocHierarchy_SuccessCallBack(result){
	setFunctionalLocWidth('frmKnow','745px');

}
function frmSafework_successsCallback(result)
{
	/*var keyid =result.keyid;
	jQuery("#hdnSwpmKeyid").val(keyid);
	processGridnew("Safeworkgrid_input.uwp","q=2&detailgrid="+false+"&keyid="+keyid,"safeworkgrid","","","GridPopupdoubleclick","","load_complete");
	setFieldValue('txtSwpdSerialno','');
	setFieldValue('hdnSwpdKeyid' ,'');
	setFieldValue('txtSwpdCheckpoints','');*/
}

function frmSafework_beforeDelete()
{
	var mode= jQuery('#mode').val();
	if(mode != "view"){
		var mstKeyid=jQuery("#hdnSwpmKeyid").val();
		 if(mstKeyid==null || mstKeyid=="" || mstKeyid==" ")
		 	return false;
		 else
			 var r=confirm("Are You Sure to Delete?");
			if(r)
				return true;
			else
				return false;
	}
	else
		return false;
	}

function frmSafework_deleteSuccessCallback(result){
	alert(result.successData.msg);
	clearForm("frmSafework");
	jQuery("#safeworkgrid").trigger("reloadGrid");
}

	 
</script>

<form name="frmSafework" id='frmSafework' >
 <div id="wrapperRpt" style="margin-top: -2px">
	<table cellspacing="10px" style="">
		<tr style=" ">
			<td colspan="2" style=" width : 700px;">
				<div id="frmSafeworkFuntKeyIds"  >							
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>	
								<input type="hidden" id="flid" name="cmbSwpmFlid"  value="${requestScope.ehsTlSafeworkproceduremst.swpmFlid}"></input>							
							</div>	
						<div id="safework" style="width:775px;"> 	</div>
			</td>
			
		</tr>
		<tr>
		  <td>
				 <div>  
			        <label >SwpNo</label>
			        <span style="margin-left:259px">
			    		 <label class="mandatory-lbl">Issue Date</label>
			   		</span>
				 </div>
				   <input class="easyui-text"  id="txtSwpmSwpno" name="txtSwpmSwpno"  style=" width:290px;height:25px; " value="${requestScope.ehsTlSafeworkproceduremst.swpmSwpno}"/>
			        <span style="margin-left: 10px">
			   	 	  <input class="easyui-datebox"  id="dteSwpmIssuedate" name="dteSwpmIssuedate"  style=" width:90px;height:25px; " value="${requestScope.ehsTlSafeworkproceduremst.swpmIssuedate}"/>
		            </span>
		            <span id="err_dteSwpmIssuedate" class="tpm-errormsg" style="position:absolute;right:47%;top:25%;"></span>
		 </td>
		<td>
			  <div>
			     <label class="mandatory-lbl">Prepared by</label>
			       <span style="margin-left: 120px">
			    		 <label class="mandatory-lbl">Prepared Date</label>
			   	    </span>
			   	   
			   </div>
			      <input class="easyui-combobox"  id="cmbSwpmPreparedby" name="cmbSwpmPreparedby"  style=" width:190px;height:25px; " value="${requestScope.ehsTlSafeworkproceduremst.swpmPreparedby}"/>
			    <span id="err_cmbSwpmPreparedby" class="tpm-errormsg" style="position:absolute;right:47%;top:25%;"></span>
			   
			    <span style="margin-left: 10px">
			      <input class="easyui-datebox"  id="dteSwpmPrepareddate" name="dteSwpmPrepareddate"  style=" width:90px;height:25px; " value="${requestScope.ehsTlSafeworkproceduremst.swpmPrepareddate}"/>
			    </span>
			    <span id="err_dteSwpmPrepareddate" class="tpm-errormsg" style="position:absolute;right:12%;top:25%;"></span>
		  </td>	
	</tr>
	<tr>
	   <td>
		   <div>
		     <label class="mandatory-lbl">Activity</label>
		   </div>
		      <textarea maxlength="400" rows="3" style="width: 400px; height : 52px;text-transform: uppercase;" class="limit-length" id="txtSwpmActivity" name="txtSwpmActivity" >${requestScope.ehsTlSafeworkproceduremst.swpmActivity}</textarea>
		</td>
		<td>
		   <div>
		    		<input type="button" class="easyui-button"  id="btnAdd" name="btnAdd"  style="width:50px;height:25px;" value="Add"/>
			    <!--<span>
			      <input type="button" class="easyui-button"  id="btnSave" name="btnSave"  style="width:50px;height:25px;" value="Save"/>
			    </span>
			    --><span>
			   		 <input type="button" class="easyui-button"  id="btnDelete" name="btnDelete"  style="width:50px;height:25px; " value="Delete"/>
			    </span>
			    <span>
			   		 <input type="button" class="easyui-button"  id="btnViewTemplate" name="btnViewTemplate"  style="width:70px;height:25px; " value="ViewFormat"/>
			    </span>
		   </div>
		</td>
	</tr>
	</table>
	
	<table  id='safeworkgrid' >
			<tr>
				<td>
				<div style=""></div>
				</td>
			</tr>
		</table>
		
</div>
<input type="hidden" id="mode" name="mode" value="${requestScope.mode}" /> 
<input type="hidden" id="hdnSwpmKeyid" name="hdnSwpmKeyid"  value="${requestScope.ehsTlSafeworkproceduremst.swpmKeyid}"/>
<input type="hidden" id="hdnSwpdKeyid" name="hdnSwpdKeyid" value="" />
 <input type="hidden" id="hdnVal" name="hdnVal" value="1"/><input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Excel View" />
 <input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Excel View" />
</form>
			
			