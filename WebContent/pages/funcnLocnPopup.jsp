
<script type="text/javascript">
jQuery(document).ready(function(){	
//alert(1);
	jQuery('#submitForm').val('frmFuncnLocn'); 
	var url=jQuery('#hdnFuncPopupUrl').val();	
	var dataStr ='';		
	if(jQuery('#hdnFuncformField').val() != '' || jQuery('#hdnFuncformField').val() != null)
		dataStr +='?elemType='+jQuery('#hdnFuncCondition').val()+'&formField='+jQuery('#hdnFuncformField').val();
	else
		dataStr +='?elemType='+jQuery('#hdnFuncCondition').val();	

	if(jQuery('#hdnchildType').val()!= null && jQuery('#hdnchildType').val().trim().length > 0)
		dataStr+="&childType="+jQuery('#hdnchildType').val();
	if( jQuery("#cmbSearchByName").length > 0){	
		fillComboBox("frmFuncnLocn","cmbSearchByName","findCombo.funlocn"+dataStr+'&type='+jQuery('#hdnElemTypeForCombo').val() );
		fillComboBox("frmFuncnLocn","cmbSearchByCode","findCombo.funlocn"+dataStr+'&type='+jQuery('#hdnElemTypeForCombo').val()+'&Code=Y' );
	}

	processGridnew(url,dataStr,"funcnLocnGrid","funcnLocnPager","","","","funcnLocnGrid_onComplete");
	//fillComboBox("frmFuncnLocn","cmbSearchByName","findCombo.funlocn"+dataStr+'&type='+jQuery('#hdnElemTypeForCombo').val() );
	//fillComboBox("frmFuncnLocn","cmbSearchByCode","findCombo.funlocn"+dataStr+'&type='+jQuery('#hdnElemTypeForCombo').val()+'&Code=Y' );

	jQuery("#btnFilterValues").click( function()
			{
				var url=jQuery('#hdnFuncPopupUrl').val();	
				var dataStr ='';		
				if(jQuery('#hdnFuncformField').val() != '' || jQuery('#hdnFuncformField').val() != null)
					dataStr +='?elemType='+jQuery('#hdnFuncCondition').val()+'&formField='+jQuery('#hdnFuncformField').val();
				else
					dataStr +='?elemType='+jQuery('#hdnFuncCondition').val();	

				if(jQuery('#hdnchildType').val()!= null && jQuery('#hdnchildType').val().trim().length > 0)
					dataStr+="&childType="+jQuery('#hdnchildType').val();
			     var name = jQuery('#cmbSearchByName').combobox('getValue');
			     var Code = jQuery('#cmbSearchByCode').combobox('getValue');
			     var key = null;
			     if(name != null && name != '' && name != ' ')
			         key = name;
			     if(Code != null && Code != '' && Code != ' ')
			         key = Code;

			     if(key != null)
			 		processGridnew(url,dataStr+'&key='+key,"funcnLocnGrid","funcnLocnPager","","","","funcnLocnGrid_onComplete");
			});
			jQuery("#btndlgSave").click( function()
			{
				var url=jQuery('#hdnFuncPopupUrl').val();
				/*var formName = null;
				var urlFlag = url.split('.');
				
				if(urlFlag[1] == 'pcl')
					formName = 'phnCauseTree';
				else
					formName = 'frmFuncnLocn';
				
				if(formName != null)
					saveForm(formName,url);*/
				//alert(url);	
				//saveForm("frmFuncnLocn",url);	
				//jQuery('#multiselectPopUpId').dialog('close');
			});
			jQuery('#btndlgClose').click( function()
			{
				
				var cancelCallback = jQuery('#hdnFuncCancelCallBack').val(); 	
				var id=jQuery('#hdnFuncRowId').val();
				
				if( typeof eval('(' + cancelCallback +')') == 'function')
					eval('(' + cancelCallback +'(id))');
				//jQuery('#multiselectPopUpId').dialog('close');
			});	

			jQuery( "#btndlgNew" ).click(function() {
				
				//jQuery("#newMstFrm").slideToggle(200);
				//jQuery('#multiselectPopUpId').dialog('close');	
				
				var mstId = jQuery('#hdnMstId').val();
				//var pntId = jQuery('#hdnParentsNodes').val() ; changed for itc as not working
				var pntId = jQuery('#hdnFuncCondition').val();
				 
				if(mstId == null || mstId.trim()== ' ')
					mstId = jQuery('#hdnFLMstID').val();
			 
				if(pntId == null || pntId.trim()== '' ){
				 
				  pntId = jQuery('#hdnFLPNTID').val();
				}
				//  alert(mstId +" -- "+pntId);
				
				//loadMasterForm("PhenomenaMst_link.gnms?q=2",frmMode.create);
				//if(jQuery('#hdnMstId').val() != null)
					//jQuery('#storeMstFrmId').val(jQuery('#hdnMstId').val());	
				loadMasterForm(getMstUrl(mstId,pntId),frmMode.create,'','Functional Loaction');		
			});
			jQuery(document).keydown(function(e) {
			    if (e.keyCode == 27) {
			    	jQuery("#newMstFrm").hide(0);
			    }    
			    jQuery('#newMstFrm').focusout(function() { 
			 });
			});
				
});

function funcnLocnGrid_onComplete(result){
	
} 

function cboxFormatter(id, options, rowObject)
{
	var id = options.rowId;
  	return '<input  type="checkbox" onclick="if(this.checked){selectData(\''+id + '\');}else{unselectData(\''+id + '\');}"/>';
}

function selectData(rowId){

	jQuery("#funcnLocnGrid").setCell(rowId, "chkHdnFlag","1");
}

function unselectData(id){
	jQuery("#funcnLocnGrid").setCell(id, "chkHdnFlag"," ");	
}

function loadCompletePillarGrid()
{
	jQuery(":input[type=checkbox]").removeAttr('disabled');	
}

function getSelectdRows(jqGridId,checkBoxColName,ckeckForSelColName){
	
	var allRows = jQuery("#"+jqGridId).jqGrid('getRowData');
	var jsonArrO='';
	for( var i = 0; i < allRows.length;i++){
		var row = allRows[i];
		var value = row[ckeckForSelColName];
		
		if( value != null  &&  value.trim()  != ""){
			
			for(var colName in row) {
				//colName = "txtmcamkeyId";	
				
				if(colName != "chkHdnFlag")			
				if( checkBoxColName != colName )
				{
					if(colName == "txtdescription")	
						jsonArrO +=  row[colName] +'::'; 
					else
						jsonArrO +=	row[colName] +','; 
				}
			}
			
		}
	} 
	//jsonArrO = jsonArrO.slice(0, -1) ;
	jsonArrO = jsonArrO.length > 1 ?jsonArrO:"";
	return jsonArrO; 
}


function  frmFuncnLocn_beforeSubmit(){
		return 'FunctionalLocnGrid='+getSelectdRows("funcnLocnGrid","operator_checkbox","chkHdnFlag");
	//return 'FunctionalLocnGrid='+JqGridToJsonSelectdRows("funcnLocnGrid","operator_checkbox","chkHdnFlag"); 
}
/*function  phnCauseTree_beforeSubmit(){
	//alert('PhenCauseGrid='+getSelectdRows("funcnLocnGrid","operator_checkbox","chkHdnFlag"));
	//return 'PhenCauseGrid='+getSelectdRows("funcnLocnGrid","operator_checkbox","chkHdnFlag");
return 'FunctionalLocnGrid='+getSelectdRows("funcnLocnGrid","operator_checkbox","chkHdnFlag"); 
}*/

function frmFuncnLocn_successsCallback(result){	
	//alert(jQuery('#txtformFld').val());	
	 var treeFlag=jQuery('#txtformFld').val();	
	 var treeId = null;	
	 if(treeFlag == 'C' || treeFlag == 'P' || treeFlag == 'A' || treeFlag == 'S' )
		treeId = jQuery('#hdntreeId').val();
	 else
		treeId = 'flTreeComponent';
		
	 if(jQuery("#"+treeId).jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
	 {
		//alert('Close');
	//	var childLen = jQuery("#"+treeId).jstree("_get_children",jQuery('#'+jQuery('#hdnMstId').val())).length;
		var isLeaf = jQuery("#"+treeId).jstree("is_leaf", jQuery('#'+jQuery('#hdnMstId').val()));
		
		if(isLeaf == true)
		{
			jQuery("#"+treeId).jstree("load_node",jQuery('#'+jQuery('#hdnMstId').val()));
			 setTimeout(function() {jQuery("#"+treeId).jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));},1250);
		}
		else
			jQuery("#"+treeId).jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));
	  	
	 }
	 else
 	 { 
	 	 //alert('Open');
		var okCallback = jQuery("#hdnFuncokCallback").val();
		//jQuery("#"+treeId).jstree("refresh",jQuery('#'+jQuery('#hdnMstId').val()));
		refreshNode(treeId,jQuery('#hdnMstId').val());
		//jQuery("#"+treeId).jstree("load_node",jQuery('#'+jQuery('#hdnMstId').val())); 	
		//refTree(jQuery('#hdnMstId').val());//refreshTree();
	 }
	/*var tree = jQuery.jstree._reference("#flTreeComponent");
	var currentNode = tree._get_node(null, false);
	var parentNode = tree._get_parent(currentNode);
	tree.refresh(parentNode);*/	
	jQuery('#multiselectPopUpId').dialog('close');			
}
function phnCauseTree_successsCallback(result){		
	
	 if(jQuery("#phenCauseLinkTree").jstree("is_open", jQuery('#'+jQuery('#hdnMstId').val())) == false)
	 {
		jQuery("#phenCauseLinkTree").jstree("open_node",jQuery('#'+jQuery('#hdnMstId').val()));	  	
	 }
	 else
	 {
		var okCallback = jQuery("#hdnFuncokCallback").val();	
		refreshTree();
	 }
	jQuery('#multiselectPopUpId').dialog('close');			
}
function frmLocation_successsCallback(result){	
	jQuery("#newMstFrm").hide(0);
	
	//jQuery('#submitForm').val(jQuery('#prevSubmitForm').val());
	//jQuery('#hiddenUrl').val(jQuery('#prevUrl').val());		

	if(jQuery('#txtformFld').val() =='A')
	{
			var selId = jQuery('#txtelemId').val();
			
			if(jQuery('#txtformFld').val() == '-')
				selId = selId;
			else
				selId = selId+ '&formField='+jQuery('#txtformFld').val();
			
			if(selId == null || selId == '' || selId == ' ')
				var y = null;
			else
				 funcnLocnPopUp("funcnLocn_input.funlocn",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
	}
	else
		refreshTree();

}
function frmFuncnLocn_successCallback(result){
/**ITC*/	

	jQuery("#newMstFrm").hide(0);
	jQuery('#submitForm').val(jQuery('#prevSubmitForm').val());
	jQuery('#hiddenUrl').val(jQuery('#prevUrl').val());
	//alert(jQuery('#txtformFld').val());		
	if(jQuery('#txtformFld').val() =='A')
	{
			var selId = jQuery('#txtelemId').val();
			
			if(jQuery('#txtformFld').val() == '-')
				selId = selId;
			else
				selId = selId+ '&formField='+jQuery('#txtformFld').val();
			
			if(selId == null || selId == '' || selId == ' ')
				var y = null;
			else
				 funcnLocnPopUp("funcnLocn_input.funlocn",selId,"list","","","true","MultiSelectCancel_CallBack","MultiSelectOk_CallBack");
	}
	else
		refreshTree();
		
 }
function frmLocation_beforeRefreshCallback (){
	
	var url=jQuery('#hiddenUrl').val();		
	LoadForm("loadMstFrm","preloadMstFrm",url,"dispErr","loadMstFrmSuccess");
	return false;
}


function getMstUrl(mstKey,parentKey)
{
	//alert(jQuery("#txtformFld").val());
	//var mstFormField = jQuery('#hdnFuncformField').val();
	var ff = jQuery('#hdnFLFormField').val(); 
	var mstFormField = jQuery("#txtformFld").val();
	if(ff != null && ff != '' && ff != ' ')
		mstFormField = ff;
	
	
	var dataString = '?q=2&'+frmMode.frmMode+'='+frmMode.create+'&'+setValues(mstKey.substring(0,3).toLowerCase())+'Id='+mstKey.replace('_','/');
	var mstUrl = null;
	
	var parentArr = parentKey.split('-');
	for(var i=0;i<parentArr.length;i++)
	{		
		if(parentArr[i] != mstKey.replace('_','/'))
			dataString += '&'+setValues(parentArr[i].substring(0,3).toLowerCase())+'Id='+parentArr[i];		
	}
	mstKey = mstKey.substring(0,3);
	//dataString += '&'+frmMode.lockFields+'='+addToUrl(mstKey);

	if(mstKey == 'CMP')
		mstUrl = "location_input.locn"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	if(mstKey == 'LCN')
		mstUrl = "SBU_input.commonFilter"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	if(mstKey == 'SBU')
		mstUrl = "pbu_input.commonFilter"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	if(mstKey == 'PBU')
		mstUrl = "section_input.sect"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	if(mstKey == 'LIN')
		mstUrl = "cell_input.cell"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	if(mstKey == 'CEL')
	{ 
	 // if(mstFormField == 'E')
		mstUrl = "equipment_input.eqp"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
	}
	if(mstKey == 'MCH'||mstKey == 'EQG')
	{
	  if(mstFormField == 'A')
		mstUrl = "assembly_input.asb"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey,mstFormField);
	  if(mstFormField == 'S')
		mstUrl = "SparesMaster_input.sprmst"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey,mstFormField);	
	}
	if(mstKey == 'ASM')
	{
	 // if(mstFormField == 'S')
	   if(mstFormField == 'P')
		   mstUrl = "PhenomenaMst_link.gnms?q=2";
	   else
		   mstUrl = "SparesMaster_input.sprmst"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey);
		
			//mstUrl = "phenomena_input.pcl"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey,mstFormField);
	   
	}
	if(mstKey == 'PHM')
	{
		mstUrl = "CauseMst_link.gnms?q=2";
		//mstUrl = "cause_input.pcl"+dataString+'&'+frmMode.lockFields+'='+addToUrl(mstKey,mstFormField);
	}
	if(mstKey == 'QTL')
	{
		//Process
		mstUrl = "loadmst_grid.gnms?q=2&menuCaption=ProcessMaster&menuName=MNUQTPROCESSMASTER&isMMC=Y&loadFormArg=ProcessMaster_input.pro";	
	}
	if(mstKey == 'PRS')
	{
		mstUrl = "loadmst_grid.gnms?q=2&menuCaption=PhenomenaMaster&menuName=MNUMASQTPHENOMENA&isMMC=Y&loadFormArg=MNUQTPHENOMENA";	
	}
	if(mstKey == 'QPH')
	{
		//QCause
		mstUrl = "loadmst_grid.gnms?q=2&menuCaption=CauseMaster&menuName=MNUQTCAUSEMASTER&isMMC=Y&loadFormArg=causemaster.cas";
	}	
			
	return mstUrl+'&closeOnSave=true&formId='+jQuery('#submitForm').val();	
}
function setValues(key)
{
	var keyMode = '';
	if(key=='cmp')
		keyMode = 'comp';
	if(key=='fct')
		keyMode = 'fact';
	if(key=='lcn')
		keyMode = 'locn';
	if(key=='lin')
		keyMode = 'sect';
	if(key=='cel')
		keyMode = 'cell';
	if(key=='mch')
		keyMode = 'mach';
	if(key=='mch')
		keyMode = 'mach';
	if(key=='asm')
		keyMode = 'assm';
	if(key=='spr')
		keyMode = 'sprs';
	if(key=='phm')
		keyMode = 'phen';
	
	return keyMode;
}
function addToUrl(mstKey,mstFormField)
{
	var dataString=null;

	if(mstKey == 'CMP')
		dataString = "KEYID,COMPANY";
	if(mstKey == 'LCN')
		dataString = "KEYID,COMPANY,LOCATION";
	if(mstKey == 'FCT')
		dataString = "KEYID,COMPANY,FACTORY";
	if(mstKey == 'LIN')
		dataString = "KEYID,COMPANY,FACTORY,SECTION";
	if(mstKey == 'CEL')
		dataString = "KEYID,FACTORY,SECTION,CELL";
	if(mstKey == 'MCH')
	{
		if(mstFormField == 'S')
			dataString = "KEYID,FACTORY";
		else
			dataString = "KEYID";
	}
	if(mstKey == 'ASM')
	{
		if(mstFormField == 'P')
			dataString = "KEYID,ASSEMBLY";
		else
			dataString = "KEYID,FACTORY";
	}
	if(mstKey == 'PHM')
	{
		dataString = "KEYID,PHENOMENA";
	}

	return dataString;
	
}
</script>
<form id="frmFuncnLocn" name="frmFuncnLocn">

<div id="funcnLocnPopUpId" title="" >

<table>
	<tr>
		<td>
			<div>
<!--			<div>-->
<!--			  <label>Name</label>-->
<!--			  <input id="cmbSearchByName" name="cmbSearchByName" class="easyui-combobox" style="width:200px;"/>-->
<!--			  <label>Code</label>-->
<!--			  <input id="cmbSearchByCode" name="cmbSearchByCode" class="easyui-combobox" style="width:200px;"/>-->
<!--			  <input type="button"  class="easyui-button" id="btnFilterValues" value="Filter"/>-->
<!--			</div>-->
		      	
			   <div style="padding-top:10px;">
			     		<table id="funcnLocnGrid" style="width:100%"><tr><td/></tr></table>
					 	<div id="funcnLocnPager"></div>
			    </div>
				<div style="float:right;padding-top:10px;">
						<input type="button"  class="easyui-button" id="btndlgNew" value="New"/>
			      		<input type="button" class="easyui-button" id="btndlgSave" value="Save"/>
			      		<input type="button" class="easyui-button" id="btndlgClose" value="Close"/>
			    </div>
	   		</div>
	
		</td>
	</tr>	
</table>
</div>
<input type="hidden" id="hdnFuncSltedRowIds" value=" " />
<input type="hidden" id="hdnFuncPopupUrl" value="${requestScope.dataUrl}" >
<input type="hidden" id="hdnFuncGridId" value="${requestScope.gridId}" >
<input type="hidden" id="hdnFuncRowId" value="${requestScope.rowId}" >
<input type="hidden" id="hdnIsMultiselect" value="${requestScope.isMultiselect}" >
<input type="hidden" id="hdnColNames" value="${requestScope.colNames}" >
<input type="hidden" id="hdnFuncCondition" name="hdnFuncCondition" value="${requestScope.condition}" >
<input type="hidden" id="hdnFuncCancelCallBack" value="${requestScope.multiSelectCancel_Callback}" >
<input type="hidden" id="hdnFuncokCallback" value="${requestScope.multiSelectOk_Callback}"/>
<input type="hidden" id="hdnFuncformField" value="${requestScope.formField}"/>
<input type="hidden" id="hdnFuncSelNode"/>
<input id="storeMstFrmId" type="hidden"/>
<input type="hidden" id="hdnFuncTitle" name="hdnFuncTitle" value="${requestScope.title}"/> 
<input type="hidden" id="hdnFLMstID" name="hdnFLMstID" value="${requestScope.masterSelId}"/> 
<input type="hidden" id="hdnFLPNTID" name="hdnFLPNTID" value="${requestScope.pntId}"/> 
<iynput type="hidden" id="hdnFLFormField" name="hdnFLFormField" value="${requestScope.formField}"/> 
</form>

