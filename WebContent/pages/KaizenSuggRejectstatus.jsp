<style>
	.rejected{
		display: block;
		width: 15px;
		height: 15px;
		background-color:#C0FFC0;
	}
</style>
<script>
jQuery(document).ready(function(){	
	var KzbnKeyid=jQuery('#hdnKzbnKeyid').val();
	var url = jQuery('#hiddenUrl').val();
	var ehs = jQuery('#hdnSftyacrt').val();
	var kznvw = jQuery('#hdnviewMode').val();
	initialiseForm('frmKaizenBankGrid');
	jQuery('#submitForm').val('frmKaizenSuggrejectsts');
	var title="";
	var frmType="";
	var frmmode=url.indexOf("&fromMode");
	
	jQuery("#rejectlabel").hide();
	 
	if(url=='KaizenBankAcceptReject_input.kznbnk'){
		 jQuery("#btnKaizenBank").val("Accept/Reject");
		 title="Kaizen Accept/Reject";
		 frmType="A";
		 jQuery("#rejectlabel").show();
		 jQuery("#hdnfrmType").val("A");
	 }else if((url=='KaizenBankImplement_input.kznbnk' ||url.substring(0,url.indexOf("&KzbnKeyid")) 
			 || url.substring(0,url.indexOf("&fromMode"))) && frmmode<0){
		 jQuery('#btnKaizenBank').hide();
		 title="Kaizen Implement";
		 frmType="I";
		 jQuery("#hdnfrmType").val("I");
	 }else if(url=='KaizenBankView_input.kznbnk'){
		 jQuery('#btnKaizenBank').hide();
		 title="Kaizen Suggestion View";
		 frmType="R";
		 jQuery("#hdnfrmType").val("I");
	 }else if(url=='KaizenBankcComplete_input.kznbnk'){
		 jQuery("#btnKaizenBank").val("Complete");
		 title="Kaizen Complete";
		 frmType="C";
		 jQuery("#hdnfrmType").val("C");
	 }else if((url=='KaizenBankcVerified_input.kznbnk' || url.substring(0,url.indexOf("&fromMode"))
					 || ehs.trim().length>0 || kznvw.trim().length>0)){
		 jQuery("#btnKaizenBank").val("Verify");
		 jQuery("#btnKaizenBank").val("Save");
		 
		 if(kznvw.trim().length>0)
			 jQuery('#btnKaizenBank').hide();
			 //disableField("frmKaizenBankGrid","btnKaizenBank");
		
		 title="Kaizen Verify";
		 if(kznvw.trim()=='view')
		 	frmType="V";
		 jQuery("#hdnfrmType").val("V");
	 }else if(url=='KaizenBankImplemented_input.kznbnk'){
		 jQuery('#btnKaizenBank').hide();
		 title="Kaizen Suggestion Implemented";
         frmType="M";
		 jQuery("#hdnfrmType").val("M");
	 }
	
	var flid=jQuery('#hdnLoginFlid').val();
	//viewGrid(url,"q=2&flid="+flid);	 
	viewGrid(url,"&flid="+flid);	 

	jQuery('#btnKaizenBank').click(function(){
		
		fnSaveKaizen();	 
	 });
	 
//	 setLoadFormCallBackFrmId("frmKaizenBankGrid");

});

function frmKaizenSuggrejectsts_beforeSubmit(){
	//alert(1);&kaizenDatas="+kaizenData

	var formId = jQuery('#submitForm').val();
	var url = jQuery('#hiddenUrl').val();
	var ehs = jQuery('#hdnSftyacrt').val();
	if(url=="KaizenBankcVerified_input.kznbnk" || url.substring(0,url.indexOf("&K")) 
		|| ehs.trim().length>0) {
		if(formId.length > 0 ) {
			var kaizenData = fnGetKaizenData();
			if(kaizenData==false)
				return false;
			
			return "&kaizenDatas="+kaizenData;
		}
		else
			return false;
	}
}

function fnSaveKaizen() {

	var formId = jQuery('#submitForm').val();
	var url = jQuery('#hiddenUrl').val();
	var ehs = jQuery('#hdnSftyacrt').val();
	if(url=="KaizenBankcVerified_input.kznbnk" || url.substring(0,url.indexOf("&K")) 
		|| ehs.trim().length>0) {
		if(formId.length > 0 ) {
			/*var kaizenData = getSelectdRows();
			
			if(kaizenData==false)
				return false;
			*/
			saveForm(formId,"kaizenBankAcceptVerify_save.kznbnk?q=2");
		}
	}
}



function fnGetKaizenData() {
	
	var row = jQuery("#KZBankGrid").jqGrid('getDataIDs');
	var count=0;
	
	for (var i = 0; i < row.length; i++) {
		rowid = row[i];
		chkVal = jQuery("#KZBankGrid").jqGrid('getCell', row[i],"CHECKVAL"); // Call detail Key Id
		if(chkVal=='1'){
			count++;
		}
	}	
	if(parseInt(count)!=0){
		var dataArrList =dataList();
		var formId = jQuery('#submitForm').val();
		var url = jQuery('#hiddenUrl').val();
		var ehs = jQuery('#hdnSftyacrt').val();
		if(url=="KaizenBankcVerified_input.kznbnk" || url.substring(0,url.indexOf("&K")) 
			|| ehs.trim().length>0) {
			if(formId.length > 0 ) {
				var kaizenData = getSelectdRows();
				
				if(kaizenData==false)
					return false;
				else
					return kaizenData;
				//saveForm(formId,"kaizenBankAcceptVerify_save.kznbnk?q=2&kaizenDatas="+kaizenData);
			}
		}
		else
		 LoadPopUp("divKaizenBankPop", "KaizenBankPopup_input.kznbnk?q=2&frmType="+frmType+dataArrList,true,"50%","60%","15%","10%", "LoadSuccess", title,false,true);
		
		return true;
	}
	else {
		alert("Select Check box");
		count=0;
		return false;
	}
}


function frmKaizenSuggrejectsts_afterLoadCallBack(){
	//	toggleCommonFilter();
}


function viewGrid(url, filterStr) {
	var hsesfty=jQuery('#hdnSftyacrt').val();
	var kznvw=jQuery('#hdnviewMode').val();
	var KzbnKeyid=jQuery('#hdnKzbnKeyid').val();
	
	if(hsesfty.trim().length>0)
	  filterStr +='&hsesfty=Y';
	
	if(kznvw.trim().length>0)
		  filterStr +='&kznvw='+kznvw;
	if(KzbnKeyid.trim().length>0)
		  filterStr +='&KzbnKeyid='+KzbnKeyid;
	
	processGridnew(url,filterStr,"KZBankGrid","KZBankGridPager","Kaizen Bank","doubleclickKznBank","","KZBankGridLoadComplete");
	return true;
}


function getSelectdRows(){
	var allRows = jQuery("#KZBankGrid").jqGrid('getRowData');
	var jsonArrO='[';

	for( var i = 1; i <= allRows.length;i++){
		var isChecked = jQuery("#kzbnCheckbox_"+i+"_3").is(':checked');
		if(isChecked == true)
		{
			jsonArrO += '{';
			var cmbKznmStatus =  getFieldValue("cmbKznmStatus_"+i);
			var cmbKzbnAcrejby = getFieldValue("cmbKzbnAcrejby_"+i);
			var cmbKzbnResponsibility = getFieldValue("cmbKzbnResponsibility_"+i);
			var txtKzbnVerifyremarks = getFieldValue("txtKzbnVerifyremarks_"+i);
			var txtKzbnImplementcost  = getFieldValue("txtKzbnImplementcost_"+i);
			var dteKzbnTargetdate = getFieldValue("dteKzbnTargetdate_"+i);
			
			var keyid = chkVal = jQuery("#KZBankGrid").jqGrid('getCell', i,"KEYID");

			if (cmbKznmStatus==null || cmbKznmStatus=='-' || cmbKznmStatus==' ') { 
				popupCommonErrorMsg(" Select Accept/Reject");
				return false;
			}

			if (cmbKznmStatus=='V' && (dteKzbnTargetdate==null || dteKzbnTargetdate =='' || dteKzbnTargetdate==' ')) { 
				popupCommonErrorMsg(" Select Target Date");
				return false;
			}
			
			if (cmbKzbnAcrejby==null || cmbKzbnAcrejby=='' || cmbKzbnAcrejby==' ') { 
				popupCommonErrorMsg(" Select Approved By");
				return false;
			}
			
			if ((cmbKznmStatus=='V' )&& (cmbKzbnResponsibility==null || cmbKzbnResponsibility=='' || cmbKzbnResponsibility==' ')) { 
				popupCommonErrorMsg(" Select Responsibility");
				return false;
			}
			
			if (cmbKznmStatus=='V') { 
				if (targetDateEvt(i)==false) {
					return false;
				}
			}
			
			if (txtKzbnVerifyremarks.length==0)
				txtKzbnVerifyremarks="-";
			jsonArrO += ' "txtKzbnKeyid":"' + keyid +'","cmbKzbnStatus":"' + cmbKznmStatus +'","cmbKzbnAcrejby":"' + cmbKzbnAcrejby +'",'; 
			jsonArrO += '"txtKzbnImplementcost":"' + txtKzbnImplementcost +'",';
			jsonArrO += '"dteKzbnTargetdate":"' + dteKzbnTargetdate +'",';
			jsonArrO += '"txtKzbnVerifyremarks":"' + txtKzbnVerifyremarks +'",';
			jsonArrO += '"cmbKzbnResponsibility":"' + cmbKzbnResponsibility +'",';
			
			jsonArrO = jsonArrO.slice(0, -1) + "},"; 
		}							
	}
	
	jsonArrO = jsonArrO.slice(0, -1) + "]";
	jsonArrO = (jsonArrO != ']'?jsonArrO:"");
	return jsonArrO; 
}

function frmKaizenSuggrejectsts_successsCallback(){
	//jQuery("#KZBankGrid").trigger("reloadGrid");
	refreshForm();	
}

function dataList(){
	var row = jQuery("#KZBankGrid").jqGrid('getDataIDs');
	 var rowid;
	 var chkVal ;
	 var dataFlid="";
	 var dataKeyid="";
	 var dataSuggest="";
	 
	 for (var i = 0; i < row.length; i++) {
			rowid = row[i];
			chkVal = jQuery("#KZBankGrid").jqGrid('getCell', row[i],"CHECKVAL"); // Call detail Key Id
			if(chkVal=='1'){
				dataFlid+=jQuery("#KZBankGrid").jqGrid('getCell', row[i],"FNLNID")+",";
				dataKeyid+=jQuery("#KZBankGrid").jqGrid('getCell', row[i],"KEYID")+",";
				dataSuggest+=jQuery("#KZBankGrid").jqGrid('getCell', row[i],"SUGGESTEDBYKEYID")+",";
			}
	 }			
			dataFlid = dataFlid.slice(0, -1);
			dataKeyid = dataKeyid.slice(0, -1);
			dataSuggest = dataSuggest.slice(0, -1);
		return "&dataFlid="+dataFlid+"&dataKeyid="+dataKeyid+"&dataSuggest="+dataSuggest;
}

function KZBankGridLoadComplete(){
	var row = jQuery("#KZBankGrid").jqGrid('getDataIDs');
	var status;
	for (var i = 0; i < row.length; i++) {
		var idd = parseInt(i)+1;
		numericTextBox("txtKzbnImplementcost_"+idd,true);
		
		rowid = row[i];
		status = jQuery("#KZBankGrid").jqGrid('getCell', row[i],"STATUS");
		 var cm = jQuery("#KZBankGrid").jqGrid("getGridParam", "colModel");
		 
		if(status=="VERIFIED" || status=="ACCEPTED"){
			//enableUIButton("btnKznIdeaSheet_"+row[i]+"_15");
			enableUIButton("btnKznIdeaSheet_"+row[i]+"_9");
			jQuery('#KZBankGrid input[id=kzbnCheckbox_'+row[i]+'_3]').attr('disabled',true);
		}
		else
			//disableUIButton("btnKznIdeaSheet_"+row[i]+"_15");
			disableUIButton("btnKznIdeaSheet_"+row[i]+"_9");
		
			if(status=="REJECTED"){
			for(var j=0;j<cm.length;j++)
	    	 {				
		   		//jQuery("#KZBankGrid").jqGrid('setCell',row[i],"STATUS","",{'color':'#000','font-size':'12px','background-color':'#C0FFC0'});
		   		jQuery("#KZBankGrid").jqGrid('setCell',row[i],"STATUS","",{'color':'#000','font-size':'12px','background-color':'red'});
	    	 }
		}
		
	}
}
function divKaizenBankPop_onClose()
{
	jQuery("#KZBankGrid").trigger("reloadGrid");
	return true;
}	
	
function btnformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	return '<span id="kznIdeaSheet'+rowId+'"><input type="button" class="easyui-button"  id="btnKznIdeaSheet_'+rowId+'_'+colId+'"  ' +
	' name="btnKznIdeaSheet_'+rowId+'_'+colId+'" value="..." style="height:20px;width:72px;" onclick="buttonclick('+rowId+','+colId+');" /></span>';
}


function cmbStatusformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	var str = ' ';
	var vselected = "";
	var rselected = "";	
	var eselected = "";
	var emptyselected = "";
	
	if (id=="V") 
		vselected = "selected='selected'";
	else if (id=="R")
		rselected = "selected='selected'";
	else if (id=="E")
		eselected = "selected='selected'";
	else
		emptyselected = "selected='selected'";
	
	str+="<select  id='cmbKznmStatus_"+rowId+"' style='width:80px;' name='cmbKznmStatus_"+rowId+"' disabled='disabled' class='easyui-combobox'> ";
	str+=" <option  value='-' "+emptyselected+" > </option>";
	str+=" <option  value='V' "+vselected+">Accepted</option>";
	str+="<option  value='R' "+rselected+">Rejected</option>";
	str+="<option  value='E' "+eselected+">Rework</option>";
	str+="</select>";
	return str;
}

function cmbApprovedbyformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	//return '<input type="text" class="easyui-combobox" id="cmbKzbnAcrejby" name="cmbKzbnAcrejby" value="'+id+'" style="width: 160px;"/>';
	var str = '';
	str+="<input type='text'  id='cmbKzbnAcrejby_"+rowId+"' style='width:160px;' disabled='disabled' name='cmbKzbnAcrejby_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
}

function cmbResponsbilityformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	//return '<input type="text" class="easyui-combobox" id="cmbKzbnAcrejby" name="cmbKzbnAcrejby" value="'+id+'" style="width: 160px;"/>';
	var str = '';
	str+="<input type='text'  id='cmbKzbnResponsibility_"+rowId+"' style='width:160px;' disabled='disabled' name='cmbKzbnResponsibility_"+rowId+"' value='"+id+"' class='easyui-combobox' /> ";
	return str;
}


function txtRemrksformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	
	if (id==null) 
		id=' ';
	return '<textarea  id="txtKzbnVerifyremarks_'+rowId+'" name="txtKzbnVerifyremarks_'+rowId+'" disabled="disabled" maxlength="495" style="width: 130px;height: 40px" rows="4" cols="1">'+id+'</textarea>';
}

function txtImplCostformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	if (id==null) 
		id=' ';
	
	return '<input type="text" id="txtKzbnImplementcost_'+rowId+'" name="txtKzbnImplementcost_'+rowId+'" disabled="disabled"  class="easyui-text"  style=" width : 120px; text-align: right; " value='+id+' align="right" />';
}

function dteTargetDateformatter(id, options, rowObject){
	var rowId = options.rowId;
	var colId = options.pos;	
	if (id==null) 
		id=' ';
	
	return '<input id= "dteKzbnTargetdate_'+rowId+'" style="width:100px" disabled="disabled"  class="easyui-datebox" value='+id+' >';
	
}

function targetDateEvt(rowId)
{
	//var detectionDate = jQuery('#hdnAplmPlandate').val();
	var detectionDate = jQuery("#KZBankGrid").jqGrid('getCell', rowId,"DATES");
	detectionDate = detectionDate.substr(0,12);
	var currentDate = getServerDateTime();
	var targetDate = getFieldValue("dteKzbnTargetdate_"+rowId);
	
	//alert(detectionDate);
	
	if (detectionDate=="undefined" || detectionDate=="" || detectionDate==" ") { 
		//alert(convertStringToDate(targetDate) < currentDate);
		targetDate=targetDate+"23:59";
		if(convertStringToDate(targetDate) < currentDate)
		{
			alert('Target Date Should Not Lessthan Current Date');
			fillWithCurrentDate("dteKzbnTargetdate_"+rowId);
			return false;
		}
		else
		    clearValidationErrorMsg("dteKzbnTargetdate_"+rowId);
	}
	else {
		if(convertStringToDate(targetDate) < convertStringToDate(detectionDate))
		{	
			alert('Target Date Should Not Less than Kaizen Date');
			fillWithCurrentDate("dteKzbnTargetdate_"+rowId);
			return false;
		}
		else {
		    clearValidationErrorMsg("dteKzbnTargetdate_"+rowId);
		}
	}
	return true;
}
function buttonclick(row, col){
	var KZNBKeyid=jQuery("#KZBankGrid").jqGrid('getCell', row,"KEYID");
	var FNLNID=jQuery("#KZBankGrid").jqGrid('getCell', row,"FNLNID");
	var Suggestedby=jQuery("#KZBankGrid").jqGrid('getCell', row,"SUGGESTEDBYKEYID");
	var Suggestnid=jQuery("#KZBankGrid").jqGrid('getCell', row,"KEYID");
	
	navigateToNextForm("kaizen_input.kaizen?q=2&KZNBKeyid="+KZNBKeyid+"&KZNBFlid="+FNLNID+"&Suggestedby="+Suggestedby+"&Suggestnid="+Suggestnid,"Kaizen Idea Sheet");
}

function doubleclickKznBank(id){
	var dataKeyid="";
	var frmType=jQuery("#hdnfrmType").val();
	var title="";
	if(frmType=="A"){
		dataKeyid=jQuery("#KZBankGrid").jqGrid('getCell',id,"KEYID");
		title="Kaizen Acceptance";
		navigateToNextForm("KaizenBankSuggestion_input.kznbnk?q=2&AccSingle=Y&Keyid="+dataKeyid,title);
	}
}

//*********************Select Row******************************//
function KZBankGrid_selectRow(row){ 
	  if(jQuery('#kzbnCheckbox_'+"KZBankGrid"+'_'+row).is(':checked'))
		 jQuery("#KZBankGrid").jqGrid('setCell', row, 'CHECKVAL', '1');
	     var kznsuggKeyid=jQuery("#KZBankGrid").jqGrid('getCell',row,"KEYID");
	     alert("kznsuggKeyid::::"+kznsuggKeyid);
	     var kznstatus=jQuery("#KZBankGrid").jqGrid('getCell',row,"STATUS");
	     alert("The Kaizen Status"+kznstatus);
	  //   var kznstatus=jQuery("#KZBankGrid").jqGrid('getCell',row,"STATUS");
	     
	     if(kznstatus=='REJECTED'){
	    	 alert("This Suggestion Already Rejected");
	    	 return false;
	     }
	     if(kznstatus=='REWORK'){
	    	 alert("This Suggestion Already Rework");
	    	 return false;
	     }
LoadPopUp("loadkaizenbankSugg","KaizenBankSuggStatusPopup_input.kznbnk?&Keyid="+kznsuggKeyid+"&kznstatus="+kznstatus,true,"45%","30%","10%","10%", "","Kaizen Suggestion Status","","");		
}

function chkFormatter(id, options, rowObject)
{  
//	alert("Chk Formatter");
	var rowId = options.rowId;
	var colId = options.pos;
	return '<input type="checkbox" id="kzbnCheckbox_'+rowId+'_'+colId+'" name="kzbnCheckbox_'+rowId+'_'+colId+'"  '+ (rowObject[2]=="1" ? 'checked':'') + ' style="" onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
}

function chkboxCheck(row){
	 jQuery("#KZBankGrid").jqGrid('setCell', row, 'CHECKVAL', '1');
     var kanbankkeyid=jQuery("#KZBankGrid").jqGrid('getCell',row,"KEYID");
	//alert("keyid"+kanbankkeyid);
	jQuery('#cmbKznmStatus_'+row).attr('disabled',false);
	var sts = getFieldValue("cmbKznmStatus_"+row);
	if (sts=='-' || sts==' ')
		setFieldValue("cmbKznmStatus_"+row,'V');
	
	jQuery('#cmbKzbnAcrejby_'+row).attr('disabled',false);
	jQuery('#cmbKzbnResponsibility_'+row).attr('disabled',false);
	jQuery('#txtKzbnImplementcost_'+row).attr('disabled',false);
	jQuery('#dteKzbnTargetdate_'+row).attr('disabled',false);
	jQuery('#txtKzbnVerifyremarks_'+row).attr('disabled',false);

	formatDateBox('dteKzbnTargetdate_'+row,'dd-MMM-yyyy');
	var tdate = getFieldValue('dteKzbnTargetdate_'+row);
	if (tdate=='' || tdate==null ||tdate==' ') {
		fillWithCurrentDate("dteKzbnTargetdate_"+row);
	}

	var approvedId=jQuery("#KZBankGrid").jqGrid('getCell', row,"CMBAPPROVEDBYID");
	var respId=jQuery("#KZBankGrid").jqGrid('getCell', row,"CMBRESPOSIBILITYID");

	setFieldValue("cmbKzbnAcrejby_"+row,approvedId);
	
	setFieldValue("cmbKzbnResponsibility_"+row+" ");
	
	fillComboBox("frmKaizenBankPop", "cmbKzbnAcrejby_"+row, "employee.commonFilter");
	fillComboBox("frmKaizenBankPop", "cmbKzbnResponsibility_"+row, "employee.commonFilter");
	setTimeout(function() {
		if (approvedId=='' || approvedId==' ') { 
			approvedId = jQuery('#hdnLoginUserid').val();
			jQuery('#cmbKzbnAcrejby_'+row).combobox('setValue',approvedId);
		}
		else {
			jQuery('#cmbKzbnAcrejby_'+row).combobox('setValue',approvedId);
		}
		},300);
	
	if(kanbankkeyid==null || kanbankkeyid=="" || kanbankkeyid== " ")
		return false;
	else{
		var r=confirm("Are You Sure to Delete?");
		if(r){
		 alert("Data Deleted Successfully");
		processAjaxCalls("KaizenBankSuggestion_delete.kznbnk?&kanbankkeyid="+kanbankkeyid+"&kznMode="+"kznMode"+"","","deleteSuccessCallback","errorCallBack");	
		jQuery("#KZBankGrid").trigger("reloadGrid");	
			return true;
		}
		else{
			return false;
	}
	}
	
}

 function frmKaizenBankGrid_deleteSuccessCallback(reslut){
	jQuery("#KZBankGrid").trigger("reloadGrid");	
 }

function chkboxUnCheck(row){
	jQuery("#KZBankGrid").jqGrid('setCell', row, 'CHECKVAL', '0');
}
</script>
<form id="frmKaizenSuggrejectsts" name="frmKaizenSuggrejectsts">
	<div id="WrapperRpt">
		<div>
			<table>
				<tr>
					<td  >	
						<span id="rejectlabel" style="position:reletive;"><span class="rejected" style="position:absolute;top:40px;left:160px" ></span><span style="margin-left:30px;" ><label>Rejected</label></span>
						</span>
					</td>
					
				</tr>
				<tr>
					<td>
						<div id="KaizenBankGrid">
							<!-- <div><label style="font-weight:bold;">Double Click Data to View Details</label></div> -->
							<div>
								<table id="KZBankGrid"></table> 
							</div>
							<div id="KZBankGridPager"></div> 
						</div>
					</td>
				</tr>
			</table>
			
		</div>
	</div>
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<input type="hidden" id="hdnfrmType" name="hdnfrmType" value="${requestScope.frmType}"/>
	<input type="hidden" id="hdnMode" name="hdnMode" value="${requestScope.mode}" />
	<input type="hidden" id="hdnRoleLevel" name="hdnRoleLevel" value="${requestScope.ROLELEVENO}" />
	<input type="hidden" id="hdnLoginUserid" name="hdnLoginUserid" value="${requestScope.loginUserId}" />
    <input type="hidden" id="hdnSftyacrt" name="hdnSftyacrt"  value="${requestScope.Sftyacrt}"/>
    <input type="hidden" id="hdnviewMode" name="hdnviewMode" value="${requestScope.viewMode}"/>
    <input type="hidden" id="hdnKzbnKeyid" name="hdnKzbnKeyid" value="${requestScope.kzbnKeyid}"/>
</form>