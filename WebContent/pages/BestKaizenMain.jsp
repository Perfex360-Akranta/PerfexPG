<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmBestKaizen');
	jQuery('#submitForm').val('frmBestKaizen');
	fillComboBox('frmBestKaizen','cmbKzbmEmployeeid', 'employee.commonFilter');
	fillComboBox('frmBestKaizen','cmbKzbmLevel', 'level_Combo.bzlv',"",false);
	formatDateBox('dteKzbmDate','dd-MMM-yyyy');
	fillWithCurrentDate('dteKzbmDate');
	var url=jQuery("#hiddenUrl").val();
	disableField("frmBestKaizen", "cmbKzbmLevel");
	jQuery("#frmBestKaizenFuntKeyIds").css('width','100%');
	//fileManagerPopUp("","ABN","frmBestKaizen","btnfilemgr","abnFilemgr");
	/* jQuery('#dteKzbmMonth').datebox({
		 formatter: function(date){ return ( getMonthStringFromInt(date.getMonth()))+'-'+date.getFullYear(); },
		 onSelect:function(date){			
				var onSelectFunctionName = 'dteKzbmMonth_onSelect';
				if( typeof eval('('+onSelectFunctionName +')') == 'function')
				{
					eval('( '+ onSelectFunctionName +'(date))');
				}
			}
	 });   */
	 
	 formatMonthBox('dteKzbmMonth');
	
	//fillWithCurrentMonth("dteKzbmMonth");
	var level=jQuery("#cmbKzbmLevel").combobox("getValue");
    var Str = "&level="+level+"&month=";

    var date=getFieldValue("dteKzbmDate").substring(0,1);
    var index=getFieldValue("dteKzbmDate").substring(0,2).indexOf("-");
    var newbtn=jQuery('#hdnbtnnew').val();
    if(newbtn.trim().length==0){
	    if (date<=7 && index=="1"){
			fillWithPreviousMonth("dteKzbmMonth");
		}
	}else
    	fillWithCurrentMonth("dteKzbmMonth");
    
    loadfunctional_Bestkaizen(Str);
});

function fillWithKznCurrentMonth(fieldId)
{
	
	var serverTime = srvTime();
	 var currentTime = new Date(serverTime);
	//var currentTime = new Date();
	var month = currentTime.getMonth();
	var year = currentTime.getFullYear();	
	month = getMonthStringFromInt(month);
	if(fieldId.substring(0,3) == "dte")		
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);	
	else
		jQuery("#"+fieldId).val(month+'-'+year);
}


function fillWithPreviousMonth(fieldId)
{
	var serverTime = srvTime();
	var dateTime = new Date(serverTime);
	var currentTime = dateTime; // new Date();

	var month = currentTime.getMonth();	
	var year = currentTime.getFullYear();
	if(month==0){
		month=month + 11;
		year = year - 1;
	}	
	else
		month = parseInt(month,10)-1;
	
	var day = currentTime.getDate();
	
		month = getMonthStringFromInt(month);
	
	var hours = currentTime.getHours();
	var minutes = currentTime.getMinutes();
	if (minutes < 10){
		minutes = "0" + minutes;
	}

	
	if(fieldId.substring(0,3) == "dte"){
		//jQuery("#"+fieldId).datebox('setValue',dd+'-'+month+'-'+year);
		jQuery("#"+fieldId).datebox('setValue',month+'-'+year);
	}		
	else if(fieldId.substring(0,3) == "spn")
		jQuery("#"+fieldId).val(hours + ":" + minutes);
	else
		jQuery("#"+fieldId).val(month+'-'+year);
	
}

function viewgrid(filter){
	 if(validateFilterSelection(filter)){ 
	
	processGridnew("bestkaizenlevel_input.bzlv",filter,"BestKaizengrid","BestKznpager","","","load_complete");

	 
	return true;
		}
		  return false;	
		}

function validateFilterSelection(filterString){
	return true;
} 
	 

function load_complete(){	
	var row=jQuery("#BestKaizengrid").jqGrid('getDataIDs');	
	var rowid="";
	for(var i=0;i<row.length;i++){
		rowid=row[i];
		var checkVal =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"CHECKVAL");
		if(checkVal=='1'){
			//alert("if");
			jQuery("#bestKaizen_checkbox_"+rowid).attr('checked',true);
	    	 jQuery("#BestKaizengrid").jqGrid('setCell',rowid,"KZNM_KEYID","",{'color':'#000','font-size':'12px','background-color':'green'});

		}
		
	}
}
function frmBestKaizen_beforeDelete(){
	var mstkeyid =jQuery("#hdnKzbmKeyid").val();
	if(!mstkeyid.trim().length>0)
		return false;
	else{
		var r=confirm("Are You Sure to Delete?");
		if(r)
			return true;
		else
			return false;
	}
}

function  frmBestKaizen_beforeSubmit(){
	/* 	var cellId = jQuery("#frmBestKaizen input[id='cell']").val();
		//alert("cell id "+cellId);
		console.log("cell id "+cellId);
		var row=jQuery("#BestKaizengrid").jqGrid('getDataIDs');	
		var rowCount = row.length;
		console.log("Total row count = " + rowCount);
		
		var selectedRow = jQuery("#BestKaizengrid").jqGrid('getGridParam', 'selrow');
		var selectedCount = selectedRow ? 1 : 0;

		console.log("Selected row = " + selectedRow);
		console.log("Selected row count = " + selectedCount); */
			
		var checkedCount = jQuery("input[name='bestKaizen_checkbox']:checked").length;

		if (checkedCount !== 1) {
		    alert("Select One Best Kaizen");
		    return false;
		}//Comment all add this only - 16 March
		var gridData="";
		
		/* if (cellId) {

		    var val = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]').text().trim();
		    console.log(val + " value");
		    

		    if (val) {
		        alert(" Only one Kaizen Per Month ");
		        return false;
		    }
		}
		else
		{
			 var val = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]').text().trim();
			 console.log("VALUE IN DMT"+val);
			 
			 var firstVal = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]')
	         .first()
	         .text()
	         .trim();

			  console.log("FIRST DETAIL VALUE = " + firstVal);

			 
			 var level = jQuery('td[aria-describedby="BestKaizengrid_KZNLEVEL"]')
	         .first()
	         .text()
	         .trim();

			 console.log("LEVEL = " + level);
			 
			 if (val && level === "D") {
				    alert(" Only one Kaizen Per Month ");
				    return false;
				}
			
		} */

		
		if(convertTojsonDetailMst().length>96){
			alert(" Select One Best Kaizen ");
			return false;
		}
		if(convertTojsonDetailMst().length>0)
			gridData+="&kaizenDetailData="+convertTojsonDetailMst();
		if(convertTojsonDltDtl().length>0)
			gridData+="&kaizenDltDtl="+convertTojsonDltDtl();
		if(!convertTojsonDltDtl().length>0 && !convertTojsonDetailMst().length>0 )
		{
			 var flid = jQuery("#frmBestKaizen input[id='flid']").val();
			 var empid=jQuery("#cmbKzbmEmployeeid").combobox("getValue");
			 var date = jQuery("#dteKzbmDate").datebox("getValue");
			 if((flid==null || flid=="" || flid==" ") || (date==null || date=="" || date==" ")  || (empid==null || empid=="" || empid==" ")   )
				 return true;
			 else
			{
				alert("Select Data");
				return false;
			}
		}
		///gridData +="&kaizenDetailData="+convertTojsonDetailMst();
		/*if(!convertTojsonDetailMst()){
			alert("Select Data");
			return false;
		}
		else*/
			return gridData;
	}
	
/* function  frmBestKaizen_beforeSubmit(){
	
	var cellId = jQuery("#frmBestKaizen input[id='cell']").val();
	//alert("cell id "+cellId);
	console.log("cell id "+cellId);

	var gridData="";
	
	if (cellId) {

	    var val = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]').text().trim();
	    console.log(val + " value");
	    

	    if (val) {
	        alert(" Only one Kaizen Per Month ");
	        return false;
	    }
	}
	else
	{
		 var val = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]').text().trim();
		 console.log("VALUE IN DMT"+val);
		 
		 var firstVal = jQuery('td[aria-describedby="BestKaizengrid_DETAIL"]')
         .first()
         .text()
         .trim();

		  console.log("FIRST DETAIL VALUE = " + firstVal);

		 
		 var level = jQuery('td[aria-describedby="BestKaizengrid_KZNLEVEL"]')
         .first()
         .text()
         .trim();

		 console.log("LEVEL = " + level);
		 
		 if (val && level === "D") {
			    alert(" Only one Kaizen Per Month ");
			    return false;
			}
		
	}
	

	
	if(convertTojsonDetailMst().length>96){
		alert(" Select One Best Kaizen ");
		return false;
	}
	if(convertTojsonDetailMst().length>0)
		gridData+="&kaizenDetailData="+convertTojsonDetailMst();
	if(convertTojsonDltDtl().length>0)
		gridData+="&kaizenDltDtl="+convertTojsonDltDtl();
	if(!convertTojsonDltDtl().length>0 && !convertTojsonDetailMst().length>0 )
	{
		 var flid = jQuery("#frmBestKaizen input[id='flid']").val();
		 var empid=jQuery("#cmbKzbmEmployeeid").combobox("getValue");
		 var date = jQuery("#dteKzbmDate").datebox("getValue");
		 if((flid==null || flid=="" || flid==" ") || (date==null || date=="" || date==" ")  || (empid==null || empid=="" || empid==" ")   )
			 return true;
		 else
		{
			alert("Select Data");
			return false;
		}
	}
	///gridData +="&kaizenDetailData="+convertTojsonDetailMst();
	
		return gridData;
} */
function convertTojsonDltDtl(){
	var row=jQuery("#BestKaizengrid").jqGrid('getDataIDs');	
	var rowid="";
	var deljsonArr='[';
	var delCnt=0;
	for(var i=0;i<row.length;i++){
	 	rowid=row[i];
 		var kznkeyid =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"KZNM_KEYID");
 		var mstkeyid =jQuery("#hdnKzbmKeyid").val();
 		var deleteDtl =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"DELETEVAL");
 		var levelGrid =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"KZNLEVEL");
 		var level=jQuery("#cmbKzbmLevel").combobox("getValue");
 		var dtlkeyid =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"DETAIL");
 		if(levelGrid.length>0){
 			if(level.trim()==levelGrid.trim())
 				delCnt++;
 		}
 		if( delCnt>0){
	 		if(deleteDtl.trim()=="0"){
	 			deljsonArr+= '{';
	 			deljsonArr += '"txtKzbdKeyid":"'+dtlkeyid+'",';
	 			deljsonArr += '"txtKzbdKzbmKeyid":"'+mstkeyid+'",';
	 			deljsonArr += '"txtKzbdKaizenid":"'+kznkeyid+'"},';
		 	}
 		}
	}
	//alert(row.length+"  row  "+delCnt);
	if( delCnt>0){
		if (deljsonArr.trim()=="["){
			deljsonArr=="";
		}
		else
			deljsonArr = deljsonArr.slice(0,-1)+ "]";
	}else 
		deljsonArr="";
	
	return deljsonArr;
}
function convertTojsonDetailMst(){
	var row=jQuery("#BestKaizengrid").jqGrid('getDataIDs');	
	var rowid="";
	var jsonArr='[';
	var count=0;
	for(var i=0;i<row.length;i++){
	 	rowid=row[i];
 		var kznkeyid =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"KZNM_KEYID");
 		var mstkeyid =jQuery("#hdnKzbmKeyid").val();
 		var checkVal =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"CHECKVAL");
 		var dtlkeyid =jQuery("#BestKaizengrid").jqGrid('getCell', rowid,"DETAIL");
	 	if(checkVal.trim()=="1"){
		 	jsonArr+= '{';
			jsonArr += '"txtKzbdKeyid":"'+dtlkeyid+'",';
			jsonArr += '"txtKzbdKzbmKeyid":"'+mstkeyid+'",';
			jsonArr += '"txtKzbdKaizenid":"'+kznkeyid+'"},';
			count++;
	 	}
	}
	if(count>0){
		if (jsonArr.trim()=="["){
			jsonArr=="";
		}
		else
			jsonArr = jsonArr.slice(0,-1)+ "]";
		return jsonArr;
	}
	else
		return false;
}
function frmBestKaizen_deleteSuccessCallback(result)
{
	alert(result.successData.msg);
	clearForm('frmBestKaizen');
	navigateToPrevForm();
}
function loadfunctional_Bestkaizen(str){
	var factId = jQuery("#frmBestKaizen input[id='factory']").val();
	var sectionId = jQuery("#frmBestKaizen input[id='section']").val();
	var cellId = jQuery("#frmBestKaizen input[id='cell']").val();
	var machId = jQuery("#frmBestKaizen input[id='machine']").val();
    var flid = jQuery("#frmBestKaizen input[id='flid']").val();
    var dataStr ="";
    var level=jQuery("#cmbKzbmLevel").combobox("getValue");
    if(flid!=null && flid!="" && flid!=" ")
		dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;//+"&level="+level;
    else
    	dataStr="";//"&level="+level;

	loadFunctionalLocation("frmBestKznfunLocation","functionalLoc.bzlv?level="+level,"kaizenfunLocationValues","frmBestKaizen",dataStr);
}
function formatterChkKaizen(id,options,rowObject){
	var rowId = options.rowId;	
	return '<input id="bestKaizen_checkbox_'+rowId+'" name="bestKaizen_checkbox" '+ (id=="1" ? 'checked':'') + ' type="checkbox" ' + 'onclick="if(this.checked){kaizenCheck(\''+rowId + '\');}else{kaizenCheckUnCheck(\''+ rowId +'\')}"/>';
}
function kaizenCheck(rowId)
{
	jQuery("#BestKaizengrid").jqGrid('setCell', rowId, 'CHECKVAL',"1");
	jQuery("#BestKaizengrid").jqGrid('setCell', rowId, 'DELETEVAL',"1");
}
function kaizenCheckUnCheck(rowId){
	jQuery("#BestKaizengrid").jqGrid('setCell', rowId, 'CHECKVAL',"0");
	jQuery("#BestKaizengrid").jqGrid('setCell', rowId, 'DELETEVAL',"0");	
}

function btnKznformatter(id, options, rowObject)
{	
	var rowId = options.rowId;
    var colId = options.pos;
    return '<input type="button" id="btnKaizen_'+rowId+'_'+colId+'" name="btnKaizenGrid_'+rowId+'_'+colId+'" onclick="kaizen('+rowId+','+colId+')"  style="width:35px;  height:15px;text-align: center;"   class="easyui-button" value="..."/>';
}

function kaizen(id, colId){

	var KZNKeyid=jQuery("#BestKaizengrid").jqGrid('getCell', id,"KZNM_KEYID");
	LoadPopUp("divIdKaizen","kaizen_input.kaizen?mode=view&frmName=kznEvtn&kznKeyid="+KZNKeyid ,true,"95%","90%","1%","1%","","View Kaizen");
}

function frmBestKaizen_FuntLocHierarchy_SuccessCallBack(result){
	var flid = jQuery("#frmBestKaizen input[id='flid']").val();
	var month = jQuery("#dteKzbmMonth").datebox("getValue");
	var level=jQuery("#cmbKzbmLevel").combobox("getValue");
	var cellId = jQuery("#frmBestKaizen input[id='cell']").val();
	//reloadCombo("frmBestKaizen","cmbKzbmEmployeeid","employee.commonFilter?cellId="+cellId);
	if(flid!=null && flid!="" && flid!=" "){
		
		viewgrid('?&flid='+flid+"&level="+level+"&month="+month);
	//	processAjaxCalls("bestkaizenlevel_recall.bzlv?flid="+flid+"&level="+level+"&month="+month,"","successCallBack","errorCallBack");
	}else
		viewgrid("");
}
function successCallBack(result){
	if(result.length>0){
		setFieldValue('hdnKzbmKeyid',result[0][0]);
		jQuery("#cmbKzbmEmployeeid").combobox('setValue',result[0][1]);
		setFieldValue('dteKzbmDate',result[0][2]);
		setFieldValue('dteKzbmMonth',result[0][3]);
		setFieldValue('cmbKzbmLevel',result[0][4]);
		setFieldValue('hdnKzbmCreatedon',result[0][5]);
	}else
	{
		setFieldValue('hdnKzbmKeyid',"");
		//jQuery("#cmbKzbmEmployeeid").combobox('setValue',"");
		//setFieldValue('dteKzbmDate',"");
		setFieldValue('hdnKzbmCreatedon',"");
	}
}
function formatMonth(date){
	let newDate = new Date(date);
		   

	const months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];

	// Format DD-MMM-YYYY
    let formatted = months[newDate.getMonth()] + '-' +newDate.getFullYear();
	return formatted;
 }
function dteKzbmMonth_onSelect(date){
	var month = formatMonth(date); // jQuery("#dteKzbmMonth").datebox("getValue");
	var flid = jQuery("#frmBestKaizen input[id='flid']").val();
	var level=jQuery("#cmbKzbmLevel").combobox("getValue");
	var cellId = jQuery("#frmBestKaizen input[id='cell']").val();
	var sectionId = jQuery("#frmBestKaizen input[id='section']").val();
	var level=jQuery("#cmbKzbmLevel").combobox("getValue");
	
	if(cellId.trim().length<=0 && level=="J"){
		alert(" Select JH ");
		return false;
    }else if(sectionId.trim().length<=0 && level=="D"){
		alert(" Select DMT ");
		return false;
    }

    if(flid!=null && flid!="" && flid!=" "){
		viewgrid('&flid='+flid+"&level="+level+"&month="+month);	
		//processAjaxCalls("bestkaizenlevel_recall.bzlv?q=2&flid="+flid+"&level="+level+"&month="+month,"","successCallBack","errorCallBack");
	}
}
function frmBestKaizencmbKzbmLevel_onSelect(record)
{
     var level = record.id;
     var flid = jQuery("#frmBestKaizen input[id='flid']").val();
     var month = jQuery("#dteKzbmMonth").datebox("getValue");
    // var level=jQuery("#cmbKzbmLevel").combobox("getValue");
     if(flid!=null && flid!="" && flid!=" ")
    	 viewgrid('&flid='+flid+"&level="+level+"&month="+month);
}

function frmBestKaizen_successsCallback(result){
	jQuery("#BestKaizengrid").trigger("reloadGrid");
	loadFunctionalLocation("frmBestKznfunLocation","functionalLoc.bzlv","kaizenfunLocationValues","frmBestKaizen","&flid="+result.successData.flid);
	processAjaxCalls("bestkaizenlevel_recall.bzlv?flid="+result.successData.flid+"&level="+result.successData.level+"&month="+result.successData.month,"","successCallBack","errorCallBack");
}
</script>
<form id="frmBestKaizen">
	<div id="wrapper" style="width:90%">
		<table>
			<tr>
				<td  colspan="5">
					<div style="padding-top: 10px;"></div>
					<div id="frmBestKaizenFuntKeyIds" >							
						<input type="hidden" id="factory" name="cmbKzbmFactory" value=""/>			
						<input type="hidden" id="section" name="cmbKzbmSection" value=""/>
						<input type="hidden" id="cell" name="cmbKzbmCell" value=""/>
						<input type="hidden" id="machine" name="cmbKzbmMachine" value=""/>
						<input type="hidden" id="flid" name="cmbKzbmFlid"  value="${requestScope.kznTlBestmst.kzbmFlid}" />						
					</div>						
					<div id="frmBestKznfunLocation" style="width:100%;"></div>
				</td>
				
			</tr>
			<tr>
				<td width="17%">
					<div><label class="mandatory-lbl" >Level</label></div>
					<div>
					<input class="easyui-combobox"	id="cmbKzbmLevel" name="cmbKzbmLevel"  style="width: 130px;/*  height: 21px; */" onchange ="getLevel()" value="${requestScope.kznTlBestmst.kzbmLevel}"/>
						<!-- <select class="easyui-combobox"	id="cmbKzbmLevel" name="cmbKzbmLevel"  style="width: 130px; height: 21px;" onchange ="getLevel()">
							<option value=''></option>											
							<option value='J'>JH</option>
							<option value='D'>DMT</option>
							<option value='P'>PBU</option>
							<option value='S'>SBU</option>
							<option value='L'>Location</option>
							<option value='C'>Company</option>
						</select> -->
					</div>
				</td>
				<td width="29%" class="easyui-paddingbfpx">
					<div><label class="mandatory-lbl" >Analyzed By</label></div>
					<div><input class="easyui-combobox" style="width:255px;/* height:21px; */" id="cmbKzbmEmployeeid" name="cmbKzbmEmployeeid"   value="${requestScope.kznTlBestmst.kzbmEmployeeid}"/></div>
				</td>
				<td width="14%" class="easyui-paddingbfpx">
					<div><label class="mandatory-lbl">Date</label></div>
					<div><input class="easyui-datebox" style="width:100px;/* height:21px; */" id="dteKzbmDate" name="dteKzbmDate"   value="${requestScope.kznTlBestmst.kzbmDate}" /></div>
				</td>
				<td width="10%" class="easyui-paddingbfpx">
					<div><label class="mandatory-lbl">Month</label></div>
					<div><input class="easyui-datebox" style="width:100px;/* height:21px; */" id="dteKzbmMonth" name="dteKzbmMonth"   value="${requestScope.kznTlBestmst.kzbmMonth}" /></div>
				</td>
				<td width="30%" class="easyui-paddingbfpx">
					<div style="position:relative; margin-left:120px;margin-top:-10px;">
						<span  id="abnFilemgr" style="position:absolute;right:5%;right:5%\9;top:0px;top:0px\9;" >
						</span> 
					</div>
				</td>
			</tr>
		</table>
		<table id='BestKaizengrid'>
			<tr>
				<td></td>
			</tr>
		</table>
		<div id='BestKznpager'></div>
	</div>
	<input type="hidden" id="mode" name="mode">
	<input type="hidden" id="hdnKzbmKeyid" name="hdnKzbmKeyid" value="${requestScope.kznTlBestmst.kzbmKeyid}">
	<input type="hidden" id="hdnKzbmCreatedon" name="hdnKzbmCreatedon" value="${requestScope.kznTlBestmst.kzbmCreatedon}">
	<input type="hidden" id="hdnbtnnew" name="hdnbtnnew" value="${requestScope.btnnew}">
</form>					