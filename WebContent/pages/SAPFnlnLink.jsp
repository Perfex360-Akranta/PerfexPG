<style>
.table {
	margin-left: -36px;
	margin-left: -22px\9
}
</style>
<script type="text/javascript">
var mainKeyid = null;
var mode = null;
	jQuery.noConflict();
	jQuery(document).ready(function() {
				initialiseForm('frmSapFnlnLink');
				jQuery('#submitForm').val('frmSapFnlnLink');				
				fillComboBox("frmSapFnlnLink", "cmbgsflKeyid","sapFunctionalLocn.saplink");				
				fillComboBox("frmSapFnlnLink", "cmblocation","location.funlocn");
				fillComboBox("frmSapFnlnLink", "cmbdmt","sectionCombo.commonFilter");
				fillComboBox("frmSapFnlnLink", "cmbjh","cellCombo.commonFilter");
				
				var url = jQuery('#hiddenUrl').val();
				var data="&q=2&fnlnval="+"&sapval=";
				viewGrid(url,data);			
			
	});
	
		function frmSapFnlnLinkcmblocation_onSelect(record){
		var url = jQuery('#hiddenUrl').val();
		var location = getFieldValue('cmblocation','frmSapFnlnLink');
		processAjaxCalls("SapGsflvalue.saplink?&flId="+location,"","sapSuccess");
		clearField("cmbdmt");
		clearField("cmbjh");
		reloadCombo("frmSapFnlnLink", "cmbdmt","sectionCombo.commonFilter?locnId=" +location);
		reloadCombo("frmSapFnlnLink", "cmbjh","cellCombo.commonFilter?locnId="+location);
		//reloadCombo("frmSapFnlnLink", "cmbgsflKeyid","SapGsflvalue.saplink?flId="+location);
		//setFieldValue("cmbgsflKeyid","SapGsflvalue.saplink?flId="+location);
		//jQuery("#cmbdmt").combobox('clear');
		//jQuery("#cmbjh").combobox('clear');
	}
	function Sapfnlnvalue(id){
		
	}
	function frmSapFnlnLinkcmbdmt_onSelect(record){
		var url = jQuery('#hiddenUrl').val();
		var dmt = getFieldValue('cmbdmt','frmSapFnlnLink');
		clearField("cmbjh");
		reloadCombo("frmSapFnlnLink", "cmbjh","cellCombo.commonFilter?sectionid=" + dmt);
		processAjaxCalls("SapGsflvalue.saplink?&flId="+dmt,"","sapdmtSuccess");
		//jQuery("#cmbjh").combobox('clear');
		//var fnlnval = getFieldValue('cmbdmt','frmSapFnlnLink');
		//var data="&fnlnval="+fnlnval+"&sapval=";
		//viewGrid(url,data);
		//jQuery("#cmbgsflKeyid").combobox('clear');
	}
	function frmSapFnlnLinkcmbjh_onSelect(record){
		var url = jQuery('#hiddenUrl').val();
		
		//reloadCombo("frmSapFnlnLink", "cmbjh","cellCombo.commonFilter?sectionid=" + dmt);
		//jQuery("#cmbjh").combobox('clear');
		var fnlnval = getFieldValue('cmbjh','frmSapFnlnLink');
		processAjaxCalls("getflidvalue.saplink?&originalval="+fnlnval,"","flidIdSuccess");
		reloadCombo("frmSapFnlnLink","cmbdmt","sectionCombo.commonFilter?keyid="+ fnlnval);
		processAjaxCalls("SapGsflvalue.saplink?&flId="+fnlnval,"","sapjhSuccess");
		//setFieldValue("cmblocation",fnlnval);
		//setFieldValue("cmbdmt",fnlnval);
//		var data="&fnlnval="+fnlnval+"&sapval=";
	//	viewGrid(url,data);
		//jQuery("#cmbgsflKeyid").combobox('clear');
	}	

	function frmSapFnlnLinkcmbgsfmFlid_onSelect(record){
			
			var url = jQuery('#hiddenUrl').val();
			var fnlnval = getFieldValue('cmbgsfmFlid','frmSapFnlnLink');
			var data="&fnlnval="+fnlnval+"&sapval=";
			viewGrid(url,data);
			jQuery("#cmbgsflKeyid").combobox('clear');
			}		
	
	
	jQuery("#btnView").click(function(){
		//var url = jQuery('#hiddenUrl').val();
		var url='sapfunctionallink_input.saplink';
		var locval = getFieldValue('cmblocation','frmSapFnlnLink');
		var dmtval = getFieldValue('cmbdmt','frmSapFnlnLink');
		var jhval = getFieldValue('cmbjh','frmSapFnlnLink');
		//var sapKeyId =getFieldValue("cmbgsflKeyid","frmSapFnlnLink");
		//alert("sapKeyId : "+sapKeyId);
		var loctxt = locval.substr(0,3);
		var dmttxt = dmtval.substr(0,3);
		var jhtxt = jhval.substr(0,3);
		 /* if(loctxt=="LOC" && (dmttxt==null || dmttxt== "") && (jhtxt==null || jhtxt== ""))
			{
			alert("loc");
			var data="&fnlnval="+locval+"&sapval=";
			viewGrid(url,data);
			} */
		if(dmttxt=="SEC" && (jhtxt==null || jhtxt== "")){
			
		var data="&fnlnval="+dmtval+"&sapval=";
		viewGrid(url,data);
		if(dmtval == null)
			popupCommonErrorMsg('No Record to View');
		}
		if(jhtxt=="CEL" && (jhtxt!=null || jhtxt!= "")){
		
		var data="&fnlnval="+jhval+"&sapval=";
		viewGrid(url,data);
		if(jhval == null)
			popupCommonErrorMsg('No Record to View');
		}
		
		if((loctxt == "LCN") && (jhtxt==null || jhtxt== "") && (dmttxt==null || dmttxt=="")){
			var data="&fnlnval="+locval+"&sapval=";
		 viewGrid(url,data);
		if(locval == null)
			popupCommonErrorMsg('No Record to View');
		}
	});

		jQuery("#level").combobox({
		onSelect : function(recordid) {
			jQuery("#cmbgsfmFlid").combobox('clear');
			reloadCombo("frmSapFnlnLink", "cmbgsfmFlid",getComboUrl(recordid.text));
		}
		});
		jQuery("#btnClear").click(function() {
		jQuery('#cmblocation').combobox('clear');
		jQuery('#cmbdmt').combobox('clear');
		jQuery('#cmbjh').combobox('clear');
		jQuery('#cmbgsflKeyid').combobox('clear');
		jQuery("#chkisDefault").attr("checked",false);
		var url = jQuery('#hiddenUrl').val();
		viewGrid(url,"&q=2");
	});
	
		jQuery("#btnDelete").click(function(id) {
		deleteRecord("frmSapFnlnLink","Sapfnln_delete.saplink?&q=2&Keyid="+mainKeyid);
		
		setTimeout(function(){
			var url = jQuery('#hiddenUrl').val();
			viewGrid(url,"&q=2");
			},1150);
		clearForm("frmSapFnlnLink");
		var success = response.successData.id;
		 popupCommonErrorMsg('Confirmed: ' + success);
		 popupCommonErrorMsg(id);
	});
		jQuery("#btnShow").click(function() {
		var url = jQuery('#hiddenUrl').val();
		var  dataString="q=2";
		var jhvalue = getFieldValue("cmbjh","frmSapFnlnLink");
		var dmtvalue = getFieldValue("cmbdmt","frmSapFnlnLink");
		var locval = getFieldValue("cmblocation","frmSapFnlnLink");
		var flid=null;
		if(jhvalue == null || jhvalue =="")
		{
			if(dmtvalue == null || jhvalue =="")
			{
				if(locval== null || locval ==""){
					flid=null;
				}
				else{
				flid=locval;
				}
			}
			else{
				flid=dmtvalue;
				}
		}
		else{
				flid= dmtvalue;
			}
		
		
		//var flid =getFieldValue("cmbgsfmFlid","frmSapFnlnLink");
		var sapKeyId =getFieldValue("cmbgsflKeyid","frmSapFnlnLink");
		if( isEmpty(flid) ||isEmpty(sapKeyId) ){
			popupCommonErrorMsg("Select PERFEX and SAP Fuctional Location ");
			}
		var isdefault  = null;
		if(jQuery("#chkisDefault").is(":checked") == true )
			 isdefault ="Y";
		else
			 isdefault ="N";
		var param=null;
		if(mode == 'update')
			 param = "&flid="+flid+"&sapKeyId="+sapKeyId+"&isdefault="+isdefault+"&mode="+mode+"&mainKeyid="+mainKeyid ;
		else
			param = "&flid="+flid+"&sapKeyId="+sapKeyId+"&isdefault="+isdefault+"&mode="+mode ;
		  mode ==null;
		if( !isEmpty(flid) && !isEmpty(sapKeyId) ){
			saveForm("frmSapFnlnLink","flsap_save.saplink?"+ param);
			clearForm("frmSapFnlnLink");
			setTimeout(function(){
				viewGrid(url,dataString +"&flid="+flid +"&sapKeyId="+sapKeyId);
			},1150);
   		}
		});

	
	function docDoubleClick(id) { 
			var rowData = jQuery("#SapViewGrid").jqGrid('getRowData',id);
			 mainKeyid = rowData.GSFMKEYID;
			var keyid = rowData.FNLNORIGINALID;
			
			var flid = rowData.FNLNKEYID;
			var sapid = rowData.GSFLKEYID;
			var check = rowData.ISDEFAULT;
			setFieldValue("cmbgsflKeyid",sapid);
			checkval(check);
			processAjaxCalls("getorignalvalue.saplink?&flid="+flid,"","originalIdSuccess");
			/*processAjaxCalls("getAbnType.abnForm?abtmKeyid="+record.id,"","abnTypeOnsuccessCallback");
			var reloadval = getreloadComboUrl(flid);
			alert(flid);  */
			mode = "update";
			setTimeout(function(){
				mode == null;
				},3150);
			//jQuery("#cmbgsfmFlid").combobox('clear');
		  //reloadCombo("frmSapFnlnLink", "cmblocation",reloadval);
			//	reloadCombo("frmSapFnlnLink", "cmbdmt",reloadval);
				//reloadCombo("frmSapFnlnLink", "cmbjh",reloadval);
			
		//	reloadCombo("frmSapFnlnLink", "cmbgsflKeyid","SapFunctionalLink.commonFilter?&combokey="+sapid);				
			/*levelload(keyid);
			checkval(check);*/
	}
	function originalIdSuccess(result){
		var location = result.successData.locn;
		var section = result.successData.sect;
		var cell = result.successData.cell;
		setFieldValue("cmblocation",location);
		setFieldValue("cmbdmt",section);
		setFieldValue("cmbjh",cell);
	}
	function flidIdSuccess(result){
		var location = result.successData.locn;
		var section = result.successData.sect;
		var flid = result.successData.flid;
		//reloadCombo("frmSapFnlnLink","cmblocation","sectionCombo.commonFilter?&combokey="+location);
		//reloadCombo("frmSapFnlnLink","cmbdmt","cellCombo.commonFilter?&combokey="+section);
		setFieldValue("cmblocation",location);
		setFieldValue("cmbdmt",section);
		
	}
	function sapSuccess(result){
	var flid = result.successData.flid;
	
	if (flid == 'undefined' || flid==null ||flid== ""){
		 popupCommonErrorMsg('No SAP Record For This Functional Location');
	}
	 setFieldValue("cmbgsflKeyid",flid);
	}
	
	function sapdmtSuccess(result){
		var flid = result.successData.flid;
		
		if (flid == 'undefined' || flid==null ||flid== ""){
			 popupCommonErrorMsg('No SAP Record For This DMT Functional Location');
		}
		setFieldValue("cmbgsflKeyid",flid);
	}
	
		function sapjhSuccess(result){
			var flid = result.successData.flid;
			
			if (flid == 'undefined' || flid==null ||flid== ""){
				 popupCommonErrorMsg('No SAP Record For This DMT Functional Location');
			}

			 setFieldValue("cmbgsflKeyid",flid);
		}
	
	function checkval(id){
			if(id=="Y")
				jQuery("#chkisDefault").attr('checked',true);
			else
				jQuery("#chkisDefault").attr('checked',false);
	}
	function viewGrid(url,dataString)
	{
		
			dataString += "&active=Y";
			processGridnew(url,dataString,"SapViewGrid","pager","","docDoubleClick");
			return true;
	}
	function levelload(id){
			var keytxt = id.substr(0,3);
			if(keytxt =="CMP")
				jQuery('#level').combobox('setValue','cmp');
			if(keytxt =="LCN")
				jQuery('#level').combobox('setValue','lcn');
			if(keytxt =="SBU")
				jQuery('#level').combobox('setValue','sbu');
			if(keytxt =="PBU")
				jQuery('#level').combobox('setValue','subunt');
			if(keytxt =="SEC")
				jQuery('#level').combobox('setValue','sect');
			if(keytxt =="CEL")
				jQuery('#level').combobox('setValue','cell');
			
	}
	function getreloadComboUrl(id) {
			var keyid = id.substr(0,3);
			var locationId=jQuery("#hdnflid").val();
			if (id != null && keyid=="CMP")
				cmbUrl = "companyCombo.commonFilter?combokey=" + id;
			if (id != null && keyid=="LCN")
				cmbUrl = "location.funlocn?combokey=" + id;
			if (id != null && keyid=="SBU")
				cmbUrl = "sbuCombo.commonFilter?combokey=" + id;
			if (id != null && keyid=="PBU")
				cmbUrl = "pbuCombo.commonFilter?combokey=" + id;
			if (id != null && keyid=="SEC")
				cmbUrl = "sectionCombo.commonFilter?combokey=" + id;
			if (id != null && keyid=="CEL")
				cmbUrl = "cellCombo.commonFilter?combokey=" + id;
			return cmbUrl;

	}
	
	function getComboUrl(cmbTxt) {
			var cmbUrl = null;
			
			var locationId=jQuery("#hdnflid").val();
			if (cmbTxt == 'Company')
				cmbUrl = "companyCombo.commonFilter";
			if (cmbTxt == 'Location')
				cmbUrl = "location.funlocn";
			if (cmbTxt == 'SBU')
				cmbUrl = "sbuCombo.commonFilter?locnId=" + locationId;
			if (cmbTxt == 'PBU')
				cmbUrl = "pbuCombo.commonFilter?locnId=" + locationId;
			if (cmbTxt == 'DMT')
				cmbUrl = "sectionCombo.commonFilter?locnId=" + locationId;
			if (cmbTxt == 'JH')
				cmbUrl = "cellCombo.commonFilter?locnId=" + locationId;
			return cmbUrl;
	}
	
	 function frmSapFnlnLink_deleteSuccessCallback(result)
	 {
			 var success = response.successData.result;
			 popupCommonErrorMsg('Confirmed: ' + success);
			 popupCommonErrorMsg(result);
	 }
	function frmSapFnlnLink_successsCallback(msg){
			var success = response.successData.msg;
			popupCommonErrorMsg('Confirmed: ' + success);
			processAjaxCall("sapfunctionallink_.saplink","","");	
	}
</script>


<form action="" method="post" id="frmSapFnlnLink">
	<div id="wrapper" style="width: 100%; padding: 0%;">	
		<div style="padding-left: 3%; padding-left: 2% \9;">		
			<div class="easyui-paddingbfpx" style="padding-top: 0px;">
				<label style="padding-left: 10px; font-weight:bold; ">LOCATION	</label>
				<label style="padding-left: 85px; font-weight:bold; ">DMT</label>
				<label style="padding-left: 185px; font-weight:bold; ">JH</label>
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				<input id="cmblocation" name="cmblocation" class="easyui-combobox" style="width: 130px; " value=""/>
				    <!-- <select id="level" class="easyui-combobox" name="level" style="width: 350px; height: 20px;">
						<option value="cmp">Company</option>
						<option value="lcn">Location</option>
						<option value="sbu">SBU</option>
						<option value="subunt">PBU</option>
						<option value="sect">DMT</option>
						<option value="cell">JH</option>
					</select> -->
				</span> 
				<span style="padding-left: 10px; padding-left: 45px\9; vertical-align: top;">
					<input id="cmbdmt" name="cmbdmt" class="easyui-combobox" style="width: 202px; " value=""/>
				</span>
				<span style="padding-left: 10px; padding-left: 45px\9; vertical-align: top;">
					<input id="cmbjh" name="cmbjh" class="easyui-combobox" style="width: 230px;" value=""/>
				</span>
				<!--  <span id="msg" style="padding-left: 10px;vertical-align: top;padding-top: 1px;">
				</span> -->
				
			
			</div>
		
		
			<div class="easyui-paddingbfpx" style="padding-top: 10px;">
				<span style="padding-left: 0px;padding-top: 1px;">
					<label style="padding-left: 10px; font-weight:bold; font-align:center">SAP Functional Location</label>
				</span>
			
			
			</div>
			<div>
				<span style="vertical-align: top; padding-left: 8px;"> 
				     <input id="cmbgsflKeyid" name="cmbgsflKeyid" class="easyui-combobox" style="width: 345px; height: 0px;" value="${requestScope.genTlSapfnlnmapping.gsflKeyid}" />
				</span> 
				<span style="vertical-align: bottom; padding-left: 10px; padding-bottom: "> 
				      <input type="checkbox" id="chkisDefault" name="chkisDefault" value="${requestScope.genTlSapfnlnmapping.isDefault}"  style="border:2px dotted #00f;background:#ff0000;width:5px;height:15px;"/> 
				       <label style="padding-left: 5px; font-weight:bold; vertical-align: bottom "> Set SAP FunctionalLocation as Default</label>
				 </span> 
				<span style="padding-left: 30px;  vertical-align: top;">
				 <input type="button" class="easyui-button" id="btnView" name="btnView" value="View" style="height: 20px;" />
				</span>
				<span style="padding-left: 90px;  vertical-align: top;"> 
					
					<input type="button" class="easyui-button" id="btnShow" name="btnShow" value="Insert" style="height: 20px;" />
					<input type="button" class="easyui-button" id="btnDelete" name="btnDelete" value=" Delete " style="height: 20px;" />
					<input type="button" class="easyui-button" id="btnClear" name="btnClear" value=" Clear " style="height: 20px;" />
				</span>
			</div>		
		
	
		</div>
		<div id="wrapperRpt" >
   			<div style="margin-top: -28px; padding-top: 2px;"></div>
				<table id='SapViewGrid'>
					<tr>
						<td></td>
					<tr>
				</table>
			<div id='pager'></div>	
		</div>
	</div>
	

</form>


<input type="hidden" id="hdnGsfmKeyid" name="hdnGsfmKeyid" value="${requestScope.GsflKeyid}"/>
<input type="hidden" id="mode" name="mode" />  