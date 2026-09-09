<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmUserGrid');
		jQuery('#submitForm').val('frmUserGrid');	
		var url = jQuery('#hiddenUrl').val();		
		var dataString = "?q=1";
		viewGrid(url, dataString);
	
	});

	jQuery("#btnSave").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		for(var i=1;i<=usrGrdIds.length;i++)	
		{	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
				
				var loginId = jQuery("#grdUser").jqGrid('getCell',rowid,"LOGINID");	
				jsonArrO += loginId +";";
			}			
	
		}				
		saveForm('frmUserGrid','userLocRealease_save.creat?jsonArrO='+jsonArrO);
		
	});
	jQuery("#btnReleaseLock").click(function() {		
		var loginId="";
		var jsonArrO="";
		var usrGrdIds = jQuery("#grdUser").jqGrid('getDataIDs');	
		var cm = jQuery("#grdUser").jqGrid("getGridParam", "colModel");
		for(var i=1;i<=usrGrdIds.length;i++)	
		{	
			rowid=usrGrdIds[i-1];
			if(jQuery('#chkBox_'+rowid).is(':checked')){
				
				var loginId = jQuery("#grdUser").jqGrid('getCell',rowid,"LOGINID");	
				jsonArrO += loginId +";";
			}			
	
		}				
		saveForm('frmUserGrid','userLocRealeaseOnly_save.creat?jsonArrO='+jsonArrO);
		
	});
	
	
	function viewGrid(url, filterString) {
		if (validateFilterSelection(filterString)) {
			processGridnew(url, filterString, "grdUser", "grdUserPager","","");
			return true;
		}
	}
	
	function validateFilterSelection(filterString) {
		return true;
	}
	
	
	function checkBoxUser(id, options, rowObject){
		
		return '<input type="checkbox" id="chkBox_'+options.rowId+'" name="chkBox_'+options.rowId+'" onclick="editUser('+options.rowId+')"  style="width:50px;  height:23px;"   class="easyui-button" value=""/>';
	}
	
	function editUser(id){
	
	}
	function frmUserGrid_beforeSubmit(){
			
	}
	
	function frmUserGrid_successsCallback(){
		
		jQuery('#grdUser').trigger("reloadGrid");
		
		
	}
	
	
</script>
<form id="frmUserGrid">
	<div id="wrapperRpt" style="margin-top: 10px; margin-left: 90px">
		<div>
		
			<span style="vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnSave" name="btnShow" value="Set Default" style="height: 20px;" />
				</span>
				
				<span style="padding-left: 10px; padding-left: 10px\9; vertical-align: top;"> 
					<input type="button" class="easyui-button" id="btnReleaseLock" name="btnReleaseLock" value="Release Lock " style="height: 20px;" />
				</span>
			
		</div>
		<table id="grdUser"></table>
		<div id="grdUserPager"></div>
	</div>
</form>
<input type="hidden" id="hdnFrmMode" value="" />
<input type="hidden" id="hdnLoginIds" value="" />
