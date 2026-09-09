<script type="text/javascript">
		//jQuery.noConflict();
		jQuery(document).ready(function(){	
							
			var url = jQuery('#hiddenUrl').val();			
			var dataString ="?q=";		
			viewGrid(url,dataString);	
			
			
			//FunctionLocation
			fillComboBox("frmEmpArea","cmbMchmKeyid","machineCombo.commonFilter");
		var factId = jQuery("#frmEmpArea input[id='factory']").val();
		var sectionId = jQuery("#frmEmpArea input[id='section']").val();
		var cellId = jQuery("#frmEmpArea input[id='cell']").val();
		var machId = jQuery("#frmEmpArea input[id='machine']").val();	
		var dataStr = "&factId="+factId+"&sectId="+sectionId+"&cellId="+cellId;
		if(machId != null && machId != '')
		{
			jQuery("#cmbMchmKeyid").combobox("disable");
			dataStr += "&machId="+machId;
		}
			
		loadFunctionalLocation("eqpmfunLocation","functionalLoc.eqp","equipfunLocationValues","frmEmpArea",dataStr);
		
		
		
		jQuery('#btnView').click(function(){	
			//alert("button Click");
			var sect = jQuery("#section").val();
			//alert("sect"+sect);
			var url = jQuery('#hiddenUrl').val();
			var dataString ="?SECTIONID="+sect;	
			viewGrid(url,dataString);	
			
			});

		
		});
		
		function viewGrid(url,filterString)
		{			
			if( validateFilterSelection(filterString))
			{				filterString += '&drillFlag=f';
				processGridnew(url,filterString,"list","pager");
				return true;
			}	
			return false;
		}
		
		function validateFilterSelection(filterString){

			return  true;
		}

		function actionFormatterC(cellvalue, options, rowObject) 
		{
			var rowId = options.rowId;
		  	//alert('s');
		  	return '<input type="checkbox" id="chkmchid" value="" onclick="removeOperator(\''+rowId + '\');"/>';
		}
		
		
</script>
<form id="frmEmpArea">


	<div id="wrapperRpt"> 
	<div  id="frmAbnormalityFuntKeyIds"  >
	<input type="hidden" id="company" name="cmbcompany" value="${requestScope.EquipmentBean.company}"></input>
	<input type="hidden" id="factory" name="cmbMchmfact" value="${requestScope.EquipmentBean.factory}"></input>
	<input type="hidden" id="section" name="cmbSection" value="${requestScope.EquipmentBean.section}"></input>
	<input type="hidden" id="cell" name="cmbMchmCellid" value="${requestScope.genTlMachinemst.mchmCellid}"  ></input>
	<input type="hidden" id="machine" name="cmbMchmKeyid" value="${requestScope.genTlMachinemst.mchmKeyid}"  ></input>
	</div>
	<div id="eqpmfunLocation" ></div>	

	
	<div class="floatright" style="padding-right:20px;">
		<input id="btnView" class="easyui-button"  type="button" value="View"/>  
		<input type="button" id="bdbtn" onclick="" class="easyui-button" value="Add Employee"/>
	 </div>
	 <div>
			 <table id="list" style="width:100%"><tr><td/></tr></table>
			 <div id="pager"></div>
			 </div>
	</div>
</form>