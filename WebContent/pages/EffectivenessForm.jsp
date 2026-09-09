<script>
	jQuery(document).ready(function (){
		var url = jQuery('#hiddenUrl').val();
		initialiseForm('frmYYEffectiveness');
		jQuery('#submitForm').val('frmYYEffectiveness');
		var dataStr="?q=2";			
		viewGrid(url,dataStr);
	});

	function docDoubleClick(id)
	{	
		
		var rowData = jQuery("#YYEffectivenessGrid").jqGrid('getRowData',id);
		var keyid = rowData.txtyyefWwmsKeyid;

		navigateToNextForm("whywhyanalysismodify_input.why?keyid="+keyid+"&yymode=View"+"&filterButton=false");
	    
	}
	function viewGrid(url,filterString)
	{						
		if( validateFilterSelection(filterString))
		{
			filterString += '&drillFlag=f&firstClick=Y';
			processGridnew(url ,filterString,"YYEffectivenessGrid","YYEffectivenessPager","","docDoubleClick","","LoadCompleteEffectiveness");
			return true;
		}
		return false;
	}
	function validateFilterSelection(filterString){
			return true;
	}
	
	
	function YYEffectivenessGrid_selectRow(id){
		
		if(jQuery('#jqg_YYEffectivenessGrid_'+id).is(':checked')){
			
			chkboxCheck(id);
		}
		else{
			chkboxUnCheck(id);
		}
	}
	function YYEffectivenessGrid_selectAll(id,status){
		
		for(var i=0; i<id.length; i++){
			if(status)
				chkboxCheck(id[i]);
			else
				chkboxUnCheck(id[i]);
		}
	}
	function chkboxCheck(rowId)
	{			
	
		//setFormater("YYEffectivenessGrid","frmYYEffectiveness","Combo_Trade.abnForm",rowId,"txtYyedEffectiveid","EFFECTID",100,false);
	
		jQuery("#YYEffectivenessGrid").jqGrid('setCell',rowId,'CHKBOX','1');	
		
		var rowData = jQuery("#YYEffectivenessGrid").jqGrid('getRowData',rowId);
		var status = rowData.COUNTSTATUS;
		if(status == 'COMPLETED' || status == 'ACCEPTED' || status == 'APPROVED' || status == 'REJECTED'  ){
			//disableField("txtyyedEffectiveid_YYEffectivenessGrid_"+rowId)
			
			jQuery("#txtyyedEffectiveid_YYEffectivenessGrid_"+rowId).combobox("readonly", false);
		}else{
			alert("CounterMeasure is not Completed and You can't update the status.");
			jQuery("#txtyyedEffectiveid_YYEffectivenessGrid_"+rowId).combobox("readonly", true);
		}
	}
	function chkboxUnCheck(rowId)
	{
		//jQuery("#YYEffectivenessGrid").jqGrid('setCell',rowId,'CHKBOX','0');
		removeFormater("YYEffectivenessGrid","",rowId,"","dteTargetDate");
		
	}

	/* function checkFormatter(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;	
		return '<input type="checkbox" id="chkYYEffect_'+rowId+'_'+colId+'" name="chkYYEffect_'+rowId+'_'+colId+'" style="margin-left:30%;margin-left:1%\0\;"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}
	function chkboxCheck(rowId)
	 {
		//jQuery("#SLADetGrid").jqGrid('setCell',rowId,'CHECKVAL','1');
	 }

	function chkboxUnCheck(rowId)
	 {
		// alert(rowId+" UnCheck");
		//jQuery("#YYEffectivenessGrid").jqGrid('setCell',rowId,'CHECKVAL','0');
	 }
	function dateFormatter(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;	
		return '<input  class="easyui-datebox" id="dteEft_'+rowId+'_'+colId+'" name="dteEft_'+rowId+'_'+colId+'" style="width: 95px;"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}
	function comboFormatter(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;	
		return '<input class="easyui-combobox" id="cmbEffBy_'+rowId+'_'+colId+'" name="cmbEffBy_'+rowId+'_'+colId+'" style="width: 245px;"  onclick="if(this.checked){chkboxCheck(\''+rowId + '\');}else{chkboxUnCheck(\''+ rowId +'\');}" />';
	}*/
	function LoadCompleteEffectiveness()
	 {
		formatDateBoxWithGrid("dteEft_",'dd-MMM-yyyy');
		fillComboBoxWithGrid("frmYYEffectiveness","cmbEffBy_","employee.commonFilter");
			
	 }
	/*function buttonFormatter(id, options, rowObject){
		var rowId = options.rowId;
		var colId = options.pos;	
		return '<input class="easyui-button"  id="btnViewYY_'+rowId+'_'+colId+'" name="btnViewYY_'+rowId+'_'+colId+'" value="..." style="height:20px;width:100px;" onclick="buttonclick('+rowId+','+colId+');" />';
	}
	function buttonclick(row,col){
		navigateToNextForm("whywhyanalysis_input.why","Why Why Analysis");
	}*/
	function frmYYEffectiveness_beforeSubmit()
	{
		
		var gridData =  getGridSelectArray('YYEffectivenessGrid');
		//alert(gridData);
		return "gridData="+gridData;
	}
			
	function frmYYEffectiveness_successsCallback(result) {
		jQuery("#YYEffectivenessGrid").jqGrid().trigger("reloadGrid");
	}
	
</script>
<form id="frmYYEffectiveness">
	<div id="WrapperRpt">
	<div style="margin-top: -20px">
		<table id ='YYEffectivenessGrid'><tr><td></td> </tr></table>
		<div id ='YYEffectivenessPager'></div>
	</div>
	</div>
	<input type="hidden" id="mode"  />
</form>