 <script type="text/javascript">
jQuery(document).ready(function(){

	/* initialiseForm('frmWhyWhy');
	 jQuery('#submitForm').val('frmIntRejEntry');
	 */
	//processGridnew("whywhy_input.tdsi","?q=2","tenStpYYGrd","tenStpYYPager");
	var qamatrixId =  jQuery('#hdnQamatrixId').val();
	processGridnew("whywhy_input.tsdi","?q=2&qammKeyid="+qamatrixId,"tenStpYYGrd","tenStpYYPager",""," ","","","","");
 });
 
 function btnFormatter(id, options, rowObject)
 {
	var id = options.rowId;
	return '<input id="whywhyBtn" class="easyui-button" type="button" value="..."  onclick="openWhywhy(\''+id+'\') "/>';
 }

 function txtQpchYYRemarks(cellVal, options, rowObject)
 {
	var id = options.rowId;
	//alert(cellVal);
	return '<textarea style="width:140px;" id="txaQpchYyremarks_'+id+'" name="txaQpchYyremarks_'+id+'" ">'+cellVal+'</textarea>';
 }
 
 
 function openWhywhy(id){
	
	 
	 var rowData = jQuery("#tenStpYYGrd").jqGrid('getRowData',id);
		//alert(rowData.processKeyid);
		//var problem =  rowData.Problem;
		//openWhyWhy("tenstpWhyWhy",false,rowData.processKeyid,"10Step",tnStpflid, " ", problem, "create");

		var keyid = rowData.qpchKeyid;
		var flid = tnStpflid;
		var problem = rowData.Problem;
		var subProcess = rowData.SubProcess;
		var refDocDate = "";//jQuery('#dteQirmInspectiondate').datebox('getValue');
		//yyMode, forwardData, persistentData, attendedBy,area,pillar
	
		jQuery('#hdnTenStepCurrentSep').val('Why Why');
		if (fnGlbSaveMode()==false) 
			openWhyWhy("divWhyWhy",false,keyid,"INR",flid, refDocDate, problem, "view",null,null, null, subProcess);
		else
			openWhyWhy("divWhyWhy",false,keyid,"INR",flid, refDocDate, problem, "create",null,null, null, subProcess);
		//navigateToNextForm("whywhyanalysismodify_input.why?&whywhyRefDocID="+rowData.processKeyid+"&problem="+problem,"Why Why",null );
} 

 jQuery("#btnSave").click(function()	{
	 
	if (fnGlbSaveMode()==false) {
			return false;
	}
	 
	var url = "updateYYremarks.tsdi?q&";
	var yyRemarksData =  getSelectedRows("tenStpYYGrd");
	var data = "yyRemarksData="+escape(yyRemarksData);
	 processAjaxCalls(url, data, "yyRemarksSuccessCallBack", "onerrorCallBack");
	 
 });


 function yyRemarksSuccessCallBack(result) {
	 alert(result.msg);
	 jQuery("#tenStpYYGrd").jqGrid().trigger("reloadGrid");
	 
	 jQuery('#hdnTenStepUrl').val("Step6Qm_input.tsdi");
	 jQuery('#nxtStepId').val("listep6");
	 jQuery("#val").text("Step 6 : Impact Assessment");
	 LoadForm("divSteps","","Step6Qm_input.sqm?q=2&mainForm=true&processid="+procesid+"&flid="+tnStpflid);
	 openTenSteps();
		
 }
 function onerrorCallBack(result) {
	 
 }
	function getSelectedRows(gridId) { 
		var grid = jQuery("#"+gridId);
		var selArray =  grid.jqGrid('getGridParam', 'selarrrow');
	    //if( selArray.length <= 0 ) return "";
	        var row=jQuery("#"+gridId).jqGrid('getDataIDs');
			var rowid="";
			var jsonArr='[';
		 	for(var i=0;i<row.length;i++)
			{
			 	rowid=row[i];
			 	var qpchYyremarks = jQuery("#txaQpchYyremarks_"+rowid).val();
			 	//alert(qpchYyremarks);
			 	var qpchKeyid = jQuery("#"+gridId).jqGrid('getCell',rowid,"qpchKeyid");
			 	var qamatrixId =  jQuery('#hdnQamatrixId').val();
			 	//var checkVal =jQuery("#jqg_Qamatrixgrid_"+row[i]).is(':checked');
			 	//if(checkVal){
			 		if(rowid>0){  
					 	jsonArr+= '{';
							jsonArr += '"txtQpchKeyid":"'+qpchKeyid+'",';
							jsonArr += '"txtQpchQammKeyid":"'+qamatrixId+'",';
							jsonArr += '"txaQpchyyremarks":"'+qpchYyremarks+'" ';
							jsonArr+='},';
			 		 }
		 	}
		 	if (jsonArr=="["){
				jsonArr=="";
				return false;
			}
			else{ 
				jsonArr = jsonArr.slice(0,-1) ;
				jsonArr = jsonArr.slice(0,-2)+ "}]";
				return jsonArr;
			}
	}

 
 jQuery("#btnFishBone").click(function()	{
		//navigateToNextForm("FishBoneTree_input.fishbone",'Fish Bone');
		//navigateToNextForm("FishBoneTree_input.fishbone",'Fish Bone' ,null,{"filterString":"null"});
		var  refDoctype = "TEN";
		var refDocid = jQuery('#hdnQamatrixId').val();
		var fishBoneid = "";jQuery('#hdnFishboneid').val();
		var dataStr = "?&grid=true&keyId="+fishBoneid+"&refDocid="+refDocid+"&refDoctype="+refDoctype;
		//alert(dataStr);
		jQuery('#hdnTenStepCurrentSep').val('Why Why');
		navigateToNextForm("FishBone_input.fishbone"+dataStr,"Fish Bone",null,null);
		//LoadPopUp("","FMEAProcess_input.fmeaf?q=2&tableId=qwe",true,"45%","80%","20px","20px","ColumnSuccessCallBack","Column Chooser");					
	});
 </script>
 
 <form id="frmWhyWhy" name="frmWhyWhy">
 <div id='' style='position;padding: 10px;'>
    <div  >
    	<div style="float:left;">
<!--     		<input type="button" class="easyui-button" value='Why Why' style='height:21px;'/>	 -->
    	</div>
    	
    	<div style="margin-top: 1px;">
    		<input type="button" class="easyui-button" value='Next' style='height:21px;' id='btnSave'/>
    		<span style="padding-left: 30px;"> 
    			<input type="button" class="easyui-button" value='Fishbone' style='height:21px;' id='btnFishBone'/>
    		</span>
    		<span style="padding-left: 30px;">
    			<input type="text" id="txtFishboneid" name="txtFishboneid" value="" disabled="disabled" style="border:1px solid black; font-size:10px ; width:150px;height:20px;color:black;font-weight:bold;text-align:center;background-color:#b7b7b7; " />
    		</span>
    		<span style="padding-left: 30px;">
    			<input type="text" id="" name=" " value="In Problem Chart if u select Gamba,Others then directly goes to Step 6" disabled="disabled" style="border:1px solid red; font-size:15px ; width:550px;height:40px;font-weight:bold;text-align:center;background-color:#ff0000; " />
    		</span>
    	</div>
    </div>
 	<table id='tenStpYYGrd'> <tr><td></td></tr></table>
 	<div id="tenStpYYPager"></div>
 </div>
 </form>