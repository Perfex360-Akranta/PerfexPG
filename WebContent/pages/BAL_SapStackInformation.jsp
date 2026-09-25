<script>
jQuery(document).ready(function(){
	initialiseForm("frmsapstackinformation");
	//fillComboBox("frmsapstackinformation","cmbWorkCenter","workCenter.commonFilter");
	/*jQuery('#btngetSapData').click(function(){
	 viewGrid('q=2&type=sap');
    });)*/
    var doctype=getFieldValue('hdnrefDocType');
	var refDocId=null;
    if(doctype=="GEN"){
    	refDocId =getFieldValue("cmbGmntKeyid");
    	
        }
    else if(doctype=="BDM"){
    	refDocId=getFieldValue("cmbbdmsKeyid");
        }
    else refDocId=getFieldValue("txtWogenwoNo");
    //alert(refDocId);
	
	jQuery('#btnSAPSPROK').click(function(){
		 
		 var colData = JqGridToJsonSelectdRows("Resultgrid","Checkbox","checkVal" );/*JqGridToJsonSelectdRows*/
		//var colData='[{"checkVal":"1","Checkbox":"","SSPM_FACTORYCODE":"","txtSspmSpareno":"62180463","txtSspmSparename":"BEARING 7914A5TYNDULP4 NSK","txtSspmStoragelocation":"PMAL","txtSspmStockAvailable":"1","txtSspmRate":"0","txtSspmValue":"0","txtSspmBin":"000"},{"checkVal":"1","Checkbox":"","SSPM_FACTORYCODE":"","txtSspmSpareno":"62180463","txtSspmSparename":"BEARING 7914A5TYNDULP4 NSK","txtSspmStoragelocation":"WS38","txtSspmStockAvailable":"1","txtSspmRate":"0","txtSspmValue":"0","txtSspmBin":"B1-53"}]';
		//alert(colData);
		//var colData='[{"checkVal":"1","txtSspmFactorycode":"WA10","txtSspmSpareno":"62731331","txtSspmSparename":"SPIDER INSERT  A TYPE FOR SP6/450","txtSspmStoragelocation":"P3SA","txtSspmStockAvailable":"1","txtSspmRate":"2895","txtSspmValue":"0","txtSspmBin":"000"}]';
		var spareSAPData=colData;//'[{"item":'+colData+'}]';
		//var spareSAPData=null;
		jQuery('#hdnSaveSpareSapInfo').val(colData);
		jQuery('#hdnSprOkBtnClick').val("Y");
		var ds = "?spareSAPData="+spareSAPData+"&refDocId="+refDocId;
		//alert(ds );
		saveForm('frmsapstackinformation',"sapspareInfo_save.sapinfo"+ds);
		//alert("Spares Data"+spareSAPData);
		
		closePopUpDialoge("divSAPSpares");
		jQuery('#sapInfoGrid').trigger('reloadGrid');
		
	});
	viewGrid("");
	fillComboBox("frmsapstackinformation","cmbPlant","factoryCombo.commonFilter");
	
	//processGridnew("Sapstackinformation_input.sapinfo","?q=2","Relatedgrid","Relatedpager");
	processGridnew("Sapstackstorage_input.sapinfo","?q=2","Storagegrid","Storagepager",null,null,null,"sparesRequestInfo_onComplete");
	disableUIButton("btngetSapData");	
	jQuery('#btngetSapData').click(function(){
		
		var dataObj = convertGridToJSONArr("Storagegrid");
		dataObj  = '{"item":'+dataObj+'}';
		var qStr = 'processCode=STOCK_REQUEST&sapPostData='+dataObj;
		disableUIButton("btngetSapData");	
		processAjaxCalls( "getSapdata.soap",qStr,"sapStockDetails_onSuccessCallback","sapStockDetails_onErrorCallback");
		
	});
	jQuery('#btnSapClear').click(function(){
		jQuery("#Storagegrid").clearGridData();
		addNewGridRow("Storagegrid");
	});
	jQuery('#btnSAPSPRCancel').click(function(){
		jQuery("#Resultgrid").clearGridData();
		});
	jQuery('#btnAddRow').click(function(){
		addNewGridRow("Storagegrid");
		});	

});
function sapStockDetails_onSuccessCallback(result){
	jQuery("div[id^=jqgh_Resultgrid]").removeClass("ui-jqgrid-sortable");
	jQuery("#Storagegrid").clearGridData();
	
	enableUIButton("btngetSapData");

	if( result.exception != undefined && result.exception ){
		alert(result.errMsg);	
	}
	else{
		if( isArray(result.OUT_DATA.item) ){
			for(var i = 0;i<result.OUT_DATA.item.length;i++){
				populateResultGridRow(result.OUT_DATA.item[i]);
				
			}
		}
		else
			populateResultGridRow(result.OUT_DATA.item);
	}	
}

function populateResultGridRow(rowObj){
	addNewGridRow("Resultgrid");
	//var re = /(?![\x00-\x7F]|[\xC0-\xDF][\x80-\xBF]|[\xE0-\xEF][\x80-\xBF]{2}|[\xF0-\xF7][\x80-\xBF]{3} |[`~!@#$%^&*()_|+\=÷¿?;:'".<>\{\}\[\]\\\/])./g;
	var str = rowObj["STK_NAME"].replace(/^[^`~!@#$%\^&*()_+={}|[\]\\:';"<>?,./1-9]*$/g, ' ');
	/^[^`~!@#$%\^&*()_+={}|[\]\\:';"<>?,./1-9]*$/
	//alert(rowObj["STK_NAME"] +""+rowObj["STK_NAME"].replace(/[&\/\\#, +()$~%.'":*?<>{}]/g, ''));
	var rowId = jQuery("#Resultgrid").getGridParam("reccount");
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmFactorycode",rowObj["STK_FACTORYCODE"]);
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmSpareno",rowObj["STK_MATERIALNO"]);
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmSparename",str);// rowObj["STK_NAME"]);          //str.replace(/[`~!@#$%^&*()_|+\=÷¿?;:'".<>\{\}\[\]\\\/]/gi," "));
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmStoragelocation",rowObj["STK_STORAGELOCATION"]);
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmStockAvailable",rowObj["STK_STOCKAVAILABLE"]);
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmRate",rowObj["STK_UNITPRICE"]);
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmValue",rowObj["STK_STOCKVALUE"]);  
	jQuery("#Resultgrid").jqGrid().setCell(rowId,"txtSspmBin",rowObj["STK_BIN"]);	
}

function sapStockDetails_onErrorCallback(result){
//	alert('errs');
	enableUIButton("btngetSapData");	
}

function sparesRequestInfo_onComplete(){

	enableUIButton("btngetSapData");	
	jQuery("input[id^=txtStorage]").keypress(function(e){
	    var p = e.which;
	    if(p==13){
	    	addNewGridRow("Storagegrid");
	    	var nxtIdx = jQuery("input:text").index(this) + 1;
	        jQuery(":input:text:eq(" + nxtIdx + ")").focus();
	    }
	});
	jQuery("input[id^=txtMaterial]").keypress(function(e){
	    var p = e.which;
	    if(p==13){
	    	var nxtIdx = jQuery("input:text").index(this) + 1;
	        jQuery(":input:text:eq(" + nxtIdx + ")").focus();
	    }
	});
}



function viewGrid(filter){
	
	
	processGridnew("SapstackResult_input.sapinfo",filter,"Resultgrid","Resultpager",null,null,null,"ResultgridInfo_onComplete");	
}

function ResultgridInfo_onComplete(){
	jQuery("div[id^=jqgh_Resultgrid]").removeClass("ui-jqgrid-sortable");
}

function fmtSapReqSpare(id, options, rowObject){

	var rowId = options.rowId;
	
	var columnid = options.pos;

	var id = "txtMaterial";
	if( columnid == 1)
		return jQuery("#txtPlant").val();
	else if( columnid != 2  )
		id = "txtStorage";
	
	id += "_"+rowId;
	
	return '<input id="'+id+'" name="'+ id +'" type="text" value="" style="width:200px;text-align:left"/>';
	

	}
	function TxtSapstorage(id, options, rowObject){

		var rowId = options.rowId;
		
		var columnid = options.pos;
		//alert(columnid);
		var id = "txtMaterial";
		if( columnid == 0)
			return jQuery("#txtPlant").val();
		else if( columnid != 1)
			id = "txtStorage";
		id += "_"+rowId;
		
		return '<input id="'+id+'" name="'+ id +'" type="text" value="" style="width:200px;text-align:left"/>';

	

	}
	function TxtSapresult(id, options, rowObject){//alert("");
		var rowId = options.rowId;
		return '<input id="chkIndividual" name="chkIndividual" type="checkbox" value=""  onclick="if(this.checked){SapchkboxCheck(\''+rowId + '\');}else{SapchkboxUnCheck(\''+ rowId +'\')}"/>';
	}
   function SapchkboxCheck(rowId){
	  // KEYID -- selected value will be in this column [1 or 0]
	   jQuery("#Resultgrid").jqGrid('setCell',rowId,'checkVal','1');
   }
   function SapchkboxUnCheck(rowId){
	   jQuery("#Resultgrid").jqGrid('setCell',rowId,'checkVal','0');
   }
  /* function getSelectdRows(){
		var allRows = jQuery("#Resultgrid").jqGrid('getRowData');
		var jsonArr='[';

		for( var i = 1; i <= allRows.length;i++){
			var isChecked = jQuery("#Checkbox").is(':checked');
			if(isChecked == true)
			{
				spareSAPData += '{';
				var txtFactory = getFieldValue("STK_FACTORY"+i);
				var txtSpareNo =  getFieldValue("STK_MATERIALNO"+i);
				var txtSpareName = getFieldValue("STK_NAME"+i);
				var txtStoragelocation = getFieldValue("STK_STORAGELOCATION"+i);
				var txtAvailStock = getFieldValue("STK_STOCKVALUE"+i);
				var txtRate  = getFieldValue("STK_UNITPRICE");
				var txtValueOfStock = getFieldValue("STK_STOCKVALUE"+i);
				var txtBinn=getFieldValue("STK_BIN"+i);							
				spareSAPData += ' "txtFactory":"' + txtFactory +'",';
				spareSAPData += '"txtSpareNo":"' + txtSpareNo +'",';
				spareSAPData += '"txtSpareName":"' + txtSpareName +'",'; 
				spareSAPData += '"txtStoragelocation":"' + txtStoragelocation +'",';
				spareSAPData += '"txtAvailStock":"' + txtAvailStock +'",';
				spareSAPData += '"txtRate":"' + txtRate +'",';
				spareSAPData += '"txtValueOfStock":"'+ txtValueOfStock +'",';
				spareSAPData += '"txtBinn":"' + txtBinn +'",';
				
				spareSAPData = jsonArr.slice(0, -1) + "},"; 
			}							
		}
		
		spareSAPData = jsonArr.slice(0, -1) + "]";
		spareSAPData = (jsonArr!= ']'?jsonArr:"");
		return spareSAPData; 
	}*/
   var sendData = function(data) {
	   var spareSAPData = JSON.stringify(data);
	   alert(" data are sending to the server:\n" + spareSAPData);
	   $.ajax({
	   type: "POST",
	   url: "sapspareInfo_save.sapinfo?spareSAPData="+spareSAPData,
	   dataType:"json",
	   data: spareSAPData,
	   contentType: "application/json; charset=utf-8"
	    });
	   };
	  
</script>
<form id="frmsapstackinformation" name="frmsapstackinformation" >
<div style="">
<div class="sub-header" style="width:930px;">
<label >Enter Part No. and Storage Location To Fetch Information From SAP</label>
</div>
<table cellspacing='1px'>
<tr>
<td valign="top">                                 
<div>
<label>Plant</label>
</div>
<div>
<input type="text" id="txtPlant" disabled="disabled" name="txtPlant" clear="false" class="easyui-text"  style="width:60px;" value="${requestScope.plantName}" >
<span style="padding-left:10px;">
<input  id="cmbPlant" name="cmbPlant" disabled="disabled" clear="false" class="easyui-text"  style="width:180px;" value="${requestScope.plantId}" >
</span>
</div>
</td>
<!--
<td valign="top">

<div>
<label style="padding-left:10px;">Work Center</label>
</div>
<div style="">
 <input   id="txtWorkCenter" disabled="disabled" name="txtWorkCenter" clear="false" class="easyui-text"  style="width:220px;" value="" >

</div>
</td>
-->
</tr>
<tr>
	<td style=" width : 391px;">
		<div style="float:left;">
			<table id='Storagegrid'><tr><td></td></tr></table>
		</div>
	</td>
	<td >
	<div>
	<!--<div>
	 <input id="btnRfrsh" name="btnRfrsh" class="easyui-button"  type="button" value="Refresh Storage Location" style="width:170px;height:21px;"/> 
	</div>-->
	<div style="left-padding:20px">
		<input id="btnSapClear" name="btnSapClear" class="easyui-button"  type="button" value="Clear Query" style="width:170px;height:21px;"/>
	</div>
	<div>
		<!-- <input id="btnRfrsh" name="btnRfrsh" class="easyui-button"  type="button" value="Remove Selected Spare" style="width:170px;height:21px;"/> -->	
	</div>
	<div style="margin-top:15px;">
	<input type="button" class="easyui-button" id="btnAddRow" name="btnAddRow" style="width:170px; height : 26px;" value="Add Row"/>
	</div>
	<div style="margin-top:15px;">
	<input type="button" class="easyui-button" id="btngetSapData" name="btngetSapData" style="width:170px; height : 26px;" value="Get SAP Data"/>
	</div>
	
	
				
</div>
	</td>
	<!-- 
	<td>
		<div style="margin-left:-30px">
			<table id='Relatedgrid'><tr><td></td></tr></table>
		</div>
	
		
	</td>
	 -->
	
</tr>
<tr>
<td colspan="4">
<div class="sub-header" style="width:930px;">
<label>Result Area</label>
</div>
<table id='Resultgrid'><tr><td></td></tr></table>
<div id='Resultpager'></div>
</td>
</tr>
<tr >
		<td colspan="4">
		<div style="margin-left:35%">
			<input type="button" class="easyui-button" id="btnSAPSPROK" name="btnSAPSPROK" style="width:170px;height:22px" value="Ok"/>
			<input type="button" class="easyui-button" id="btnSAPSPRCancel" name="btnSAPSPRCancel" style="width:170px;height:22px" value="Cancel"/>
		</div>
		
		</td>
	</tr>
</table>
 </div>
 <input type='hidden' id="hdnSprOkBtnClick" name="hdnSprOkBtnClick"/>
 <div id="hdnSaveSpareSapInfo" style="display:none;">
  
 </div>
</form>

