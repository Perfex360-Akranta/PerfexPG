	<script>
	jQuery(document).ready(function(){
	  
		initialiseForm('frmTenStepStrt');
		processGridnew("Qamatrixsteps_input.qams","q=2","tenStepAppr","tnpager","","","","load_complete","selectRowFunction");
		var mainForm = jQuery('#txtMainform').val();
		if(mainForm != true){
			jQuery("#matrix").attr('id','wrapperRpt');
			}
	});
	
	function selectRowFunction_selectAll(id,status){
		
		var row = jQuery("#tenStepAppr").jqGrid('getDataIDs');
		for(var i=0 ;i<row.length;i++){
	
				jQuery('#jqg_tenStepAppr_'+row[i]).attr('checked', false);
		 }
	
	}
	
	
	function tenStepAppr_selectRow(id){
		
		var row = jQuery("#tenStepAppr").jqGrid('getDataIDs');
	    jQuery('#hdnQaProcessid').val(jQuery("#tenStepAppr").jqGrid('getCell',id,"Processid")) ;
		for(var i=0 ;i<row.length;i++){
			if(row[i]!= id)
				jQuery('#jqg_tenStepAppr_'+row[i]).attr('checked', false);
			
		 }
	
		/*if(jQuery('#jqg_tenStepAppr_'+id).is(':checked')){		
			chkboxCheck(id);
		}
		else{		
			chkboxUnCheck(id);
		}*/
	}
	
	
	jQuery("#btnSTartProj").click(function()
			{
				//alert("approval");
				//var url = "tenStepChart.tsdi";  
				 var url = "tenstepApproval_input.tsdi";
				  
				 processAjaxCalls("updateQaMatrix_input.tsdi",updateQaMAtrix()+"&processid="+jQuery('#hdnQaProcessid').val() ,'update_OnSuccess','update_OnError');
				//showGraphData(url);
				//navigateToNextForm(url,"Approval",null,{"filterString":url});
			});
	function update_OnSuccess(result){
		  if(result.msg == "dataUpdated" )
			navigateToNextForm("tenstepApproval_input.tsdi","Approval",null,{"filterString":"tenstepApproval_input.tsdi"});
		else
			alert("Not Updatres");   
	}
	function updateQaMAtrix(){ 
		var grid = jQuery("#tenStepAppr");
		var colModels = grid.jqGrid("getGridParam", "colModel");
		var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	      if( selArray.length <= 0 ){
	    	  alert('Select Process');
	    	  return "";
	      }
	        var row=grid.jqGrid('getDataIDs');	
			var rowid="";
			var jsonArr='[';
			var QaMatrixKeyid = "";
		 	for(var i=0;i<row.length;i++)
			{
			 	rowid=row[i];
			 	var checkVal =jQuery("#jqg_tenStepAppr_"+row[i]).is(':checked');	
			 	if(true == checkVal){
			 		QaMatrixKeyid +=grid.jqGrid('getCell', rowid,"keyid")+",";
			 		
			 	}
			}
		 	 return "&qamKeyid="+QaMatrixKeyid;
	}
	</script>
	<form name="frmTenStepStrt" id="frmTenStepStrt">
	<div id="matrix">
	<div align="right" style="padding-top:2px;">
	  <input type="button" class="easyui-button" id="btnSTartProj" name="btnSTartProj" value="Approval" style='height:22px;'/>
	</div>
	
	<table id='tenStepAppr'>
		<tr>
			<td></td>
		</tr>
	</table>
	<div id='tnpager'></div>
	
	<!-- <div align="center" style="padding-top:20px;">
	  <input type="button" class="easyui-button" id="btnSelectProject" name="btnSelectProject" value="OK"/>
	</div> -->
	</div>
	<input type="hidden" name = "txtMainform" id ="txtMainform" value="${requestScopt.mainForm }"/>
	<input type="hidden" name = "hdnQaProcessid" id ="hdnQaProcessid" value=""/>
	</form>