<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		initialiseForm('frmUserGrid');
		jQuery('#submitForm').val('frmUserGrid');	
		var url = jQuery('#hiddenUrl').val();		
		
		 var roleId = jQuery("#hdnUserRole").val();
			if(roleId != 'AROL0006' ) {
				if( roleId != 'AROL0003'){
					 jQuery('#frmUserGrid').hide();
					    if(typeof popupCommonErrorMsg === 'function') {
					        popupCommonErrorMsg("Please select the DMT LEADER OR JH LEADER. ");
					    } else {
					        alert("Please select the DMT LEADER OR JH LEADER.");
					    }
					    setTimeout(function() {
					        navigateToPrevForm();
					    }, 5000);
					    return false;
				}
			   
			}
		//var dataString = "?q=1";
		//viewGrid(url, dataString);
		var factId = jQuery("#frmUserGrid input[id='factory']").val();
		   var sectionId = jQuery("#frmUserGrid input[id='section']").val();
		   var cellId = jQuery("#frmUserGrid input[id='cell']").val();
		   var machId = jQuery("#frmUserGrid input[id='machine']").val();
		   var flid = jQuery("#frmUserGrid input[id='flid']").val();
		   var hdnflid = jQuery("#hdnflid").val();
		  
		   if (hdnflid!=null && hdnflid.trim().length>0 )
		   	flid=hdnflid;
		   
		   //viewGrid(url,"q=2&flid="+flid);
		   	
		   var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
		   loadFunctionalLocation("usrmfunLocation","functionalLoc.commonFilter","usrmfunLocationValues","frmUserGrid",dataStr);

	
	});
	
	function frmUserGrid_FuntLocHierarchy_SuccessCallBack(keyIds)
	{ 
			//alert(1);
			//alert(keyIds.flId);
			var url = jQuery('#hiddenUrl').val();
			var sectionId=keyIds.sectId;
			if(sectionId = null || sectionId.length == 0)
			{
				alert("Select JH or Dmt");
				return false;
			}
			if( keyIds.flId != undefined && keyIds.flId.trim().length>0){
				
				viewGrid(url,"q=2&flid="+keyIds.flId);
			}
			else {
				viewGrid(url,"q=1");
			}
		}

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
		//alert(2);
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
	<table  style="width:1100px;" >
<tr style=" height : 50px; position: relative;" >

<td colspan="3" valign="top" style=" left:80px;  top : 25px;">
	
			<div  id="frmUserGridFuntKeyIds"  >
			<div style="float: left;padding-right: 20px;">
			<input type="hidden" id="factory" name="factory" value=""></input>			
			<input type="hidden" id="section" name="section" value=""  ></input>
			<input type="hidden" id="cell" name="cell" value=""></input>
			<input type="hidden" id="machine" name="machine" value=""></input>
			<input type="hidden" id="flid" name="flid" value=""></input>
			
			</div>
			
			<div id="usrmfunLocation" style="width: 50%; "></div>

			<table>
			<tr>
			<td>	
			<span id="err_usrmfunLocation" class="tpm-errormsg"></span>
			</td>
			</tr>
			</table>
            
						  
							
							</div>
            
							</td>
							</tr></table>
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
