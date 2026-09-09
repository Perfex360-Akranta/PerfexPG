
<script>
var glbRowData  = null;
jQuery(document).ready(
			function() {

				initialiseForm('frmFourMI');
				var qamatrixId= jQuery('#hdnQamatrixId').val();
				var mode = jQuery('#hdnQfmiEntryMode').val();
				mode=mode.toUpperCase();
				jQuery('#hdnQfmiEntryMode').val(mode);
				mode=mode.toUpperCase();
				var ds = "q=2&qamatrixId="+qamatrixId+"&mode="+mode;
				processGridnew("fourandmi_input.fami",ds, "frMIgrid","pager", "", "", "", "Load_Complete");
				var btnName = jQuery("#hdnBtnName").val();
					jQuery("#btnViewTemplate").val(btnName);
					jQuery("#btnViewTemplate").click(function()
							{
						processAjaxCalls("openFile.file?fileName=4M&I.xls", "", "", "", "", "new");					
					});
				var mainForm = jQuery("#mainFormVal4mi").val();
				if (mainForm !=true) {
					jQuery(".divfami").attr('id', 'wrapperRpt');
				}
				//fileManagerPopUp("","tenStep","frmFourMI","btnFilManage","tenStepFilemgr");
				jQuery('#btnNextStep').click(function(){
					saveForm("frmFourMI","4mandi_save.fami");
				});
				
				jQuery('#btnAddFourMI').click(function(){
					var row  = jQuery("#frMIgrid").jqGrid('getDataIDs');		
					addRow(row);
				});
	});
	
	function Load_Complete() {
		
		var row = jQuery("#frMIgrid").jqGrid('getDataIDs'); 	
		var cm = jQuery("#frMIgrid").jqGrid("getGridParam", "colModel");
		glbRowData = jQuery("#frMIgrid").jqGrid('getRowData');;
		 for(var i=0;i<row.length;i++)
		 {
			 var mode = jQuery('#hdnQfmiEntryMode').val();
			 
			 for(var j=10;j<cm.length;j++)
	     	 {
				 if (j % 2 == 1 ) {
					 var skilRating = jQuery("#frMIgrid").jqGrid('getCell',row[i],cm[j].name);
					 //alert(skilRating);
					 var fourQdImg ='';
					 if(skilRating == "According to Standards"){
						 fourQdImg =  '<img id="imgquadrantimage_0" name="imgquadrantimage"  src="images/tenstep/TEN_S1.jpg" width="20px" height="20px" align="middle" />';
					}
					else if(skilRating=="Standards Not Properly Followed"){
						fourQdImg = '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/tenstep/TEN_S2.jpg" width="20px" height="20px" align="middle" />';
					}else if(skilRating=="Standards needed"){
						fourQdImg =  '<img id="imgquadrantimage_4" name="imgquadrantimage" src="images/tenstep/TEN_S3.jpg" width="20px" height="20px" align="middle" />';
					}
					else if(skilRating=="Standards Impossible To Follow"){
						fourQdImg =  '<img id="imgquadrantimage_2" name="imgquadrantimage" src="images/tenstep/TEN_S4.jpg" width="20px" height="20px" align="middle" />';
					}
					else if(skilRating==1){
						fourQdImg =  '<img id="imgquadrantimage_1" name="imgquadrantimage" src="images/Green1.jpg" width="20px" height="20px" align="middle" />';
					}
					  jQuery("#frMIgrid").jqGrid('setCell',row[i],cm[j].name,fourQdImg );
			}
	     	
	     	 /*
				 if (mode=="REVIEW") {
					 var qfmiKeyid = jQuery("#frMIgrid").jqGrid('getCell',1,txtQfmiKeyid);
					 if (qfmiKeyid==null || qfmiKeyid==''){
						 var rid=parseInt(i)+parseInt(1);
						 jQuery('#jqg_frMIgrid_'+rid).attr('checked', true);
						 makeRowEditable("frMIgrid",rid);
					 }
				 }
	     	 */
	     	 
	     	 }
		 } 

		 if (glbProcessMode=="view")
			 disableForm("frmFourMI");

	}
	
	function addRow(row)
	{
		//var prblmData =	getGridSelectArray("frMIgrid");
		//alert(prblmData);
		var selectRow = jQuery("#frMIgrid").jqGrid('getGridParam', 'selrow');
		var rowData = jQuery("#frMIgrid").jqGrid('getRowData',selectRow);
			
		//var qammKeyid = glbQamatrixId;
		var qammKeyid = rowData.txtQfmiQammKeyid;
		// alert(qammKeyid);
		var processId = jQuery('#hdnProcessid').val();
		var processName = " ";
		var subProcessid = rowData.txtQfmiSubprocessid;
		var subProcessName = rowData.subprocessname;
		var defectId = rowData.txtQfmiDefectmodeid;
		var defectmode = rowData.defectmode;
		
		//selectRow = row[i];
		if (parseInt(selectRow)>0) {
			 for(var i=0;i<row.length;i++)
					selectRow = row[i]; 
			//alert(subProcessName);
			var emptyItem =[{"txtQfmiQammKeyid":qammKeyid,"txtQfmiFlid":tnStpflid,"txtQfmiKeyid":" ","txtQfmiProcessid":processId,"txtQfmiSubprocessid":subProcessid,"processname":processName,"subprocessname":subProcessName,"defectmode":defectmode,"txtQfmiMenProblem":" ","txtQfmiMenStandard":" ","txtQfmiMachineProblem":" ","txtQfmiMachineStandard":" ","txtQfmiMethodProblem":" ","txtQfmiMethodStandard":" ","txtQfmiMaterials":" ","txtQfmiMaterialsStandard":" ","txtQfmiInformation":" ","txtQfmiInformationStandard":" ","txtQfmiDefectmodeid":defectId}];
			//var emptyItem =	[{"subprocessname":subProcessName, "txtQfmiQammKeyid":qammKeyid,"txtQfmiFlid":tnStpflid,"txtQfmiKeyid":"","txtQfmiProcessid":processId,"txtQfmiSubprocessid":subProcessid,"defectmode":defectName,"txtQfmiMenProblem":"","txtQfmiMenStandard":"","txtQfmiMachineProblem":"","txtQfmiMachineStandard":"","txtQfmiMethodProblem":"","txtQfmiMethodStandard":" ","txtQfmiMaterials":"","txtQfmiMaterialsStandard":" ","txtQfmiInformation":"","txtQfmiInformationStandard":" ","txtQfmiDefectmodeid":defectId}];
			jQuery("#frMIgrid").jqGrid('addRowData',parseInt(selectRow)+1, emptyItem[0]);
			//jQuery("#frMIgrid").jqGrid('addRowData',parseInt(selectRow)+1, emptyItem[0],"before", selectRow));

			var rowId = parseInt(selectRow)+1;
			//jQuery('#jqg_frMIgrid_'+parseInt(rowId)).attr('checked',true);
			//makeRowEditable("frMIgrid",rowId);
			//setFocusOnField("txtQfmiMenProblem_frMIgrid_"+rowId);
			jQuery("#frMIgrid").setSelection(rowId);
			setFocusOnField("txtQfmiMenProblem_frMIgrid_"+rowId);

		}
		else 
			popupCommonErrorMsg("Select Row to Copy and Pase");
	}

	function btnFilManage_click(){
	    
	    var documentNo = "1"; //for prtoType use Only

		//if(documentNo != null && documentNo != ''){
			//fileManagerPopUp("","TenStep","","","");
		//}
	}

	function txtFormatter(id, options, rowObject) {
		var id = options.rowId;
		var columnName = options.colModel.name;
		var columnNo = options.pos;
		if(columnNo==4||columnNo==6||columnNo==8){
		var idval='selfmi_';
		var str =  '<select id='+idval+columnNo + '_'+id +'   class="easyui-text"  style="width:100px; height:20px"  > ';
		str+='<option value="P">According to standards</option><option value="C">Standards not properly followed</option> ';
		str+='<option value="P">Standards needed</option><option value="C">Standards impossible to flolow</option>';
		return str;
		}
		var idval='txtfmi_';
		return '<input id='+idval+columnNo + '_'+id +'  type="text"  value="" maxlength="15" style="width: 100px; height:20px; text-align:right;" / >';		

	}
	function frmFourMI_beforeSubmit(){
		if (fnGlbSaveMode()==false) {
			return false;
		}
		
		 var f4mgridData = getGridSelectArray("frMIgrid");
		 //alert(f4mgridData );
		return "&f4mgridData="+f4mgridData;
	}
	
	function frmFourMI_successsCallback(result){
		//problemchart_input.prch
		jQuery("#frMIgrid").trigger("reloadGrid");
	/*	var qamatrixId = jQuery('#hdnQamatrixId').val();
		var mode = jQuery('#hdnQfmiEntryMode').val();
		mode=mode.toUpperCase();
	*/
		

	/*	if (mode=="CREATE") {
			jQuery('#hdnTenStepUrl').val("problemchart_input.prch");
			jQuery('#nxtStepId').val("liproblmchart");
			jQuery("#val").text("Step 3 : Problem Chart");
			LoadForm("divSteps","","problemchart_input.prch?q=2&mainForm=true&qamatrixId="+qamatrixId);
			openTenSteps(); 
		}
		else {
			jQuery('#hdnTenStepUrl').val("checkpoints_input.impac");
			jQuery('#nxtStepId').val("licheckpoints");
			jQuery("#val").text("Step 9 : Check Points");
			LoadForm("divSteps","","checkpoints_input.impac?q=2&mainForm=true&qamatrixId="+qamatrixId);
			openTenSteps();
		}
	*/		
		//navigateToNextForm("tenstepsLink_input.tsdi","Problem Chart",null,{"filterString":"problemchart_input.prch"});
	}
	
	function frMIgrid_selectRow(id){//alert(" Inside "+id);
	    
		var cm = jQuery("#frMIgrid").jqGrid("getGridParam", "colModel");
		var i = parseInt(id);
		 for(var j=10;j<cm.length;j++)
     	 {
			 if (j % 2 == 1 ) {
				 var row = glbRowData[i-1];
				 var cmName = cm[j].name;
				 var skilRating = row[cmName];
				 //alert(cmName+"_frMIgrid_"+i);
				 //alert(skilRating);
				 if (skilRating =="According to Standards")
					 skilRating ="ATS";
				 else if (skilRating =="Standards Not Properly Followed")
					 skilRating ="SNCF";
				 else if (skilRating =="Standards needed")
					 skilRating ="SN";
				 else if (skilRating =="Standards Impossible To Follow")
					 skilRating ="SITF";
				 else
					 skilRating ="-";
				 setFieldValue(cmName+"_frMIgrid_"+i,skilRating);
			}
	   }
	}
</script>


	<form name="frmFourMI" id="frmFourMI" action=" " method="post">
			<div class="divfami">
				<div>
				
<!-- 					<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style="height: 25px; width : 102px;"/>  -->
				<span><input type="button" class="easyui-button" id="btnNextStep" value="Save"/>
				<span style="padding-left: 30px;"> 	
					<input type="button" class="easyui-button" id="btnAddFourMI" value="Copy Row"/>
				 </span>
				</span>
				
				<span style="padding-left: 20px;">
					<img id="img_Std_1" name="img_Std_1"  src="images/tenstep/TEN_S1.jpg" width="20px" height="20px" align="middle" />
					<span> According to Standards </span>
					<img id="img_Std_2" name="img_Std_2"  src="images/tenstep/TEN_S2.jpg" width="20px" height="20px" align="middle" />
					<span> Standards Not Properly Followed </span>
					<img id="img_Std_3" name="img_Std_3"  src="images/tenstep/TEN_S3.jpg" width="20px" height="20px" align="middle" />
					<span> Standards needed </span>
					<img id="img_Std_4" name="img_Std_4"  src="images/tenstep/TEN_S4.jpg" width="20px" height="20px" align="middle" />
					<span> Standards Impossible To Follow </span>
				</span>
				</div>
			</div>
			 <div style="position:relative;">
			 <span  id="tenStepFilemgr" style="position:absolute;right:40px;right:70px\9;top:-30px;">
             </span> 
        </div>
			
		<div style="padding-left: 15px;">
		<table id="frMIgrid">
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
		<input type="hidden" id='mode'/>
				<input type="hidden" id="mainFormVal4mi" value="${requestScope.mainForm}"/>
				<input type="hidden"  id="hdnBtnName"	name="hdnBtnName" value="View Report" />
				<input type='hidden'  id="hdnQfmiEntryMode" name="hdnQfmiEntryMode" value="${requestScope.entryMode}"/>		
	</form>


