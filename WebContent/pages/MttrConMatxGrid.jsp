<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function(){//alert("ConversionMatrixList.jsp");
		var url = jQuery('#hiddenUrl').val();
		var mode = "VIEW";
		var dataString ="";	
		viewGrid(url,"&s=1");
		/*jQuery('#btnAdd').click(function(){
			navigateToNextForm("MttrConvMatxEntry_input.ConvMatx?q=2&mode=create","MTTR,SMED Project");
		});		*/		
	});
	function viewGrid(url,filterString)
	{	
		if( validateFilterSelection(filterString))
		{			
			processGridnew(url,"&q=2"+filterString,"list","pager","","docDoubleClick");			
			return true;
		}
		return false;
	}
	
	function docDoubleClick(id)
	{
		var rowData = jQuery("#list").jqGrid('getRowData',id);
		var keyId=rowData.keyid;
		var mode=getFieldValue("mode");
		
		navigateToNextForm('MttrConvMatxEntry_input.ConvMatx'+'?mode='+mode+'&filterButton=false&keyId='+keyId,getFormMainHeader( ));
	}
	function validateFilterSelection(filterString){
		return  true;
	}
		
	
	function docSuccessCallback()
	{
	}		
</script>
<form id="frmMttrConvMatx">	
	<div id="wrapperRpt">
		<div style="padding-left:0%;margin-top: -28px">
			<!--<input type="button" class="easyui-button" id="btnAdd" name="btnAdd" value="New"  style=" width : 49px;"/>-->
			<!--<span style="padding-left:10px; "><input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="View Report" style=" width : 90px;"/></span>-->
		<span style="padding-left:10px; ">Double Click on row to input/view details</span>
		</div>
		<div class="clear" ></div>
		<div style="margin-top:-25px\9;">
		<label class="notes"   style="font-weight: bold; padding-left:0px; " > ${requestScope.DoubleClick}</label>
		<div style="margin-top:6px;margin-top:10px\9;">
		<table id="list" ></table>
		<div id="pager"></div>
		</div>
		
		</div>
	</div>	
	<input type="hidden" id="mode" name="mode" value="${requestScope.mode}"/>
	<!--<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="View Report" />-->
</form>