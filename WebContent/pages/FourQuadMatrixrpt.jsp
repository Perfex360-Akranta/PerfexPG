<script type="text/javascript">
	jQuery.noConflict();
	var glbfilterstring;
	jQuery(document).ready(
			function() {
				setLoadFormCallBackFrmId("frmFourMatrix");
				invokeAfterLoadFormCallBack();
				fillComboBox('frmFourMatrix','cmbDepartment', 'employeeFilter.commonFilter');
				var url = jQuery('#hiddenUrl').val();
				viewGrid(url,"&q=2");
				   var flid = jQuery("#hdnflid").val();
				    var frmdate=jQuery("#hdnfrmdate").val();
				 //   alert("fromdate"+frmdate);
				    var todate=jQuery("#hdntodate").val();
				   // alert("todate"+todate);
				    var keyid=jQuery("#txtEtcmKeyid").val();
					processAjaxCalls('fqmreport.newentRpt','&flid='+flid+'&frmdate='+frmdate+'&todate='+todate,'functionchk_onsuccessCallBack','functionchk_onerrorCallBack');

				jQuery('#btnGraph').click(function(){
					var rowid = jQuery("#list").jqGrid('getGridParam','selrow');
					var url = "fqmr_input.newentRpt";
				    showGraphData(url);	
			
				    
					fileManagerPopUp("","ABN","frmFourQuadrant","btnfilemgr","abnFilemgr");
					var factId = jQuery("#frmFourQuadrant input[id='factory']").val();
					var sectionId = jQuery("#frmFourQuadrant input[id='section']").val();
					var cellId = jQuery("#frmFourQuadrant input[id='cell']").val();
					var machId = jQuery("#frmFourQuadrant input[id='machine']").val();
				    var flid = jQuery("#flid").val();
				    
				    
				    var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
					 loadFunctionalLocation("fourfunLocation","functionalLoc.commonFilter","fourfunLocation","frmFourQuadrant",dataStr);
				
				});
				
				
			});
	

	function frmFourMatrix_afterLoadCallBack(){
		toggleCommonFilter();
		}	
	
	
	function viewGrid(url,filterString)
	{ 
		
		if( validateFilterSelection(filterString))
		{ 
			
			// = getFilterValue(filterString,"dtFromDate");
			//var toDate //= getFilterValue(filterString,"dtToDate");//alert("fromdate");
			var flid=jQuery("#frmFilter input[id=flid]").val();
			var fromDate=jQuery("#frmFilter input[id=dtefromMonth]").datebox("getValue");
			var toDate = jQuery("#frmFilter input[id=dtetoMonth]").datebox("getValue");
			var Cellid=jQuery("#frmFilter input[id=cell]").val();
			filterString=filterString+"&Cellid="+Cellid;
			glbfilterstring=filterString;
			//processAjaxCalls('fqmreport.newentRpt','&flid='+flid+'&frmdate='+fromDate+'&todate='+toDate,'functionchk_onsuccessCallBack','functionchk_onerrorCallBack');
             processGridnew("fqmr_input.newentRpt",filterString,"FourGrid","pager");
			
			
			//filterString += '&Flid=f&firstClick=Y';	
			//processGridnew("fqmr_input.newentRpt",filterString,"FourGrid","pager");
  //processGridnew(url,filterString,"Gridmailreport", "pager", "", "doubleClick");
  return true;
	}
return false;	
	}

/* 	function functionchk_onsuccessCallBack(result)
	{
		var cnt=result.Qdrantcnt;
		alert(cnt);
		if(cnt<=0)
		{
			popupCommonErrorMsg("No Training Calendar on this Function Location for sleected months");
			return false;
		}
		else{
		//	alert(glbfilterstring);
			filterString=glbfilterstring;
			filterString += '&Flid=f&firstClick=Y';	
			processGridnew("fqmr_input.newentRpt",filterString,"FourGrid","pager");
		}
		glbfilterstring=null;
		
	} */
	
	
  function validateFilterSelection(filterString){
    return  true;
}
  function fourquadgridimage(id, options, rowObject) {

		//alert(" id :::: "+id);
		//var id = options.rowId;
		
	    if(id==1){//alert(1);
		    return '<img  src="images/Green1.jpg"   align="center"  style="cursor: pointer"/>';
	    }
		else if(id==2){//alert(1);
			return '<img  src="images/green2.jpg"   align="center"  style="cursor: pointer"/>';
		}
		else if(id==4){
			return '<img  src="images/green4.jpg"   align="center"  style="cursor: pointer"/>';
		}
		else if(id==3){
			return '<img  src="images/green-3.jpg"  align="center"  style="cursor: pointer"/>';
		}
		else if(id==0){ 
			return '<img  src="images/green0.jpg"   align="center"  style="cursor: pointer"/>';
		}
		
	}

   
	

</script>

<form  id="frmFourMatrix">
<div id="wrapperRpt" style="margin-top: 25px; margin-left: 25px; " >
<div  style="padding-right:20px;margin-top:0px; width:500px;height:50px">
	<tr>
	<td>
	
	<input type="hidden" id="factory" name="cmbVcclFactoryid" value=" "  ></input>			
												<input type="hidden" id="section" name="cmbVcclSectionid" value=" "  ></input>
												<input type="hidden" id="cell" name="cmbVcclCellid" value=" "  ></input>
												<input type="hidden" id="machine" name="cmbVcclEquipmentid1" value=" "  ></input>
												<input type="hidden" id="flid" name="txtVcclFlid" value=" " />	
</td></tr></div><br><br>
<table id="FourGrid" ></table>
<div id="pager"></div>

</div>
<input type="hidden" value="${requestScope.showGrid} " id="hdnShwGrd"/>	
<input type="hidden" id="hdnflid" name="hdnflid" value="${requestScope.flid} "/>

<input type="hidden" id="hdnfrmdate" name="hdnfrmdate" value="${requestScope.frmdate}"/>
<input type="hidden" id="hdntodate" name="hdntodate" value="${requestScope.todate}"/>
<!-- <input type="hidden" name="enableFunctionalLocElement" id="enableFunctionalLocElement" value="LCN"/>	 -->
</form>
	



