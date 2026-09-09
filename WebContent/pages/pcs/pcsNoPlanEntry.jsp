<script type="text/javascript">
	
	jQuery(document).ready(function(){

		initialiseForm('frmPcsNoPlanEntry');
		jQuery('#submitForm').val('frmPcsNoPlanEntry');

		var factId = jQuery("#frmPcsNoPlanEntry input[id='factory']").val();		
		var sectionId = jQuery("#frmPcsNoPlanEntry input[id='section']").val();
		var cellId = jQuery("#frmPcsNoPlanEntry input[id='cell']").val();
		var machId = jQuery("#frmPcsNoPlanEntry input[id='machine']").val();							
  	    var dataStr = "&cmbPrlmFactoryid="+factId+"&cmbPrlmSectionid="+sectionId+"&cmbPrlmCellid="+cellId+"&cmbPrlmMachineid="+machId;	  					

	   loadFunctionalLocation("pcsNoPlanEntryfunLocation","functionalLoc_NoEntry.pcs","pcsNoPlanEntryfunLocationValues","frmPcsNoPlanEntry",dataStr);

		var factId = jQuery("#frmPcsNoPlanEntry input[id='factory']").val();
		fillComboBox("frmPcsNoPlanEntry","cmbSectionid","sectionCombo.commonFilter" );
		fillComboBox("frmPcsNoPlanEntry","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );

		processGridnew('noPlanEntryGrid_view.ire',dataString,"noPlanEntryGrid","noPlanEntryPager","PCS No Plan Entry","noPlandblClick","","noPlanEntryGrid_loadComplete","noPlanEntryGridError");
			
	});

	function noPlanEntryGrid_loadComplete() {
		hideJqGridRow('noPlanEntryGrid', '1');
	}

	function frmPcsNoPlanEntry_FuntLocHierarchy_SuccessCallBack(keyIds)
	{	
		jQuery('#cmbCellid').combobox('clear');		
		var factId = jQuery("#frmPcsNoPlanEntry input[id='factory']").val();		
		reloadCombo("frmPcsNoPlanEntry","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );				  

		if (keyIds.sectionId!="null")
		  	setFieldValue('cmbSectid',keyIds.sectionId);
		if (keyIds.cellId!="null")
			setFieldValue('cmbCellid',keyIds.cellId);
						
	}
	function loadFunctionalLocationHierarchy_successCallback() 	{		
		  var factId = jQuery("#frmPcsNoPlanEntry input[id='factory']").val();		  
		  reloadCombo("frmPcsNoPlanEntry","cmbCellid","cellCombo.commonFilter?q=2&pcsEnabled=Y&factId="+factId );
	}
</script>
	
<form id="frmPcsNoPlanEntry" >
<div style="width:90%;margin-left:2%; ">
	<div  class="easyui-paddingbfpx">
		<div  class="easyui-paddingbfpx" id="frmPcsNoPlanEntryFuntKeyIds">
			<input type="hidden" id="factory" name="factory" value="${requestScope.factId}"  ></input>
			<input type="hidden" id="section" name="section" value="${requestScope.sectId}"  ></input>
			<input type="hidden" id="cell" name="cell" value="${requestScope.cellId}" ></input>
			<input type="hidden" id="machine" name="machine" value="${requestScope.mchId}"  ></input>
		</div>
		<div id="pcsNoPlanEntryfunLocation" style="padding-left: px;" ></div>
	</div>
	
	<div class="easyui-paddingbfpx" style="padding-top: 0px">
		<label class="mandatory-lbl">Section</label>
		<label class="mandatory-lbl" style="padding-left: 200px">Line</label>
		<label class="mandatory-lbl" style="padding-left: 200px">From</label>
		<label class="mandatory-lbl" style="padding-left: 80px">To</label>		
	</div>
	<div class="easyui-paddingbfpx" style="padding-top: 0px">
		<input type="text" id="cmbSectid" name="cmbSectid" class="easyui-combobox" value="${requestScope.sectId}" style="width: 220px;"/>
		<span style="padding-left: 1%;">
			<input type="text" id="cmbCellid" name="cmbCellid" class="easyui-combobox" value="${requestScope.cellId}" style="width: 220px;"/>
		</span>
		<span style="padding-left: 1%;">
	    	<input id="dteFromDate" name="dteFromDate" class="easyui-text" readonly="readonly"  style="width: 90px;" value="${requestScope.fromDate}"/>&nbsp;
	    </span>	    
		<span style="padding-left: 1%;">
	    	<input id="dteToDate" name="dteToDate" class="easyui-text" readonly="readonly"style="width: 90px;" value="${requestScope.toDate}"/>&nbsp;
	    </span>	    
	    
	   	<div style="" class="noPlanEntryDiv">			
			<table id="noPlanEntryGrid" style="float: left: ;"></table>
		</div>
	</div>	
</div>
</form>