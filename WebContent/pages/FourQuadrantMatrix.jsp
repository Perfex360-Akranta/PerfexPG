<script>
jQuery(document).ready(function(){ 
	initialiseForm('frmFourQuadrant');
	jQuery("#submitForm").val('frmFourQuadrant');
	fillComboBox('frmFourQuadrant','cmbDepartment', 'employeeFilter.commonFilter');
	var  shwGrd = jQuery('#hdnShwGrd').val() ;
	var filterStr ="";
	if(shwGrd.trim().length<=0){
		
		
	}
	 
	 
	fileManagerPopUp("","ABN","frmFourQuadrant","btnfilemgr","abnFilemgr");
	var factId = jQuery("#frmFourQuadrant input[id='factory']").val();
	var sectionId = jQuery("#frmFourQuadrant input[id='section']").val();
	var cellId = jQuery("#frmFourQuadrant input[id='cell']").val();
	var machId = jQuery("#frmFourQuadrant input[id='machine']").val();
    var flid = jQuery("#flid").val();
    var dataStr = "&factId=" + factId+ "&sectionId=" + sectionId+ "&cellId=" + cellId + "&machId="+ machId+"&flid="+flid;
	 loadFunctionalLocation("fourfunLocation","functionalLoc.commonFilter","fourfunLocation","frmFourQuadrant",dataStr);
	
});
function frmFourQuadrant_FuntLocHierarchy_SuccessCallBack(keyIds){
	viewGrid("","q=2&flid="+keyIds.flId+"&");
}
function viewGrid(url,filterString){
	 
	 if( validateFilterSelection(filterString))
		{
		 processGridnew("fourquadrantmatrixtraining_input.fqdm",filterString,"fourQuadrantgrid","pager","","","","fourload_complete"); 
		 return true;
		}
		return false;
	}
	function validateFilterSelection(filterString){//alert(" filterString :: "+getFilterValue(filterString, "dtFromDate"));
		
		if(filterString=="?q=2")
			return true;
      
		if(getFilterValue(filterString, "flid") == "" ){
			alert(" Select Functional Location ");
			return false;
		}
		 
		    return true;
	}	
function fourload_complete(id){ 
	var row = jQuery("#fourQuadrantgrid").jqGrid('getDataIDs'); 	
	var cm = jQuery("#fourQuadrantgrid").jqGrid("getGridParam", "colModel");
	
	 for(var i=0;i<row.length;i++)
	 {
		 for(var j=2;j<cm.length;j++)
     	 {
			 var skilRating = jQuery("#fourQuadrantgrid").jqGrid('getCell',row[i],cm[j].name);	
			// alert("skilRating  "+skilRating);
			 var fourQdImg ='';
			 if(skilRating == 0){
				 fourQdImg =  '<img id="imgquadrantimage_0" name="imgquadrantimage"  src="images/green0.jpg" width="20px" height="20px" align="middle" />';
				}
			else if(skilRating==3){
				fourQdImg = '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/green-3.jpg" width="20px" height="20px" align="middle" />';
			}else if(skilRating==4){
				fourQdImg =  '<img id="imgquadrantimage_4" name="imgquadrantimage" src="images/green4.jpg" width="20px" height="20px" align="middle" />';
			}
			else if(skilRating==2){
				fourQdImg =  '<img id="imgquadrantimage_2" name="imgquadrantimage" src="images/green2.jpg" width="20px" height="20px" align="middle" />';
			}
			else if(skilRating==1){
				fourQdImg =  '<img id="imgquadrantimage_1" name="imgquadrantimage" src="images/Green1.jpg" width="20px" height="20px" align="middle" />';
			}
			  jQuery("#fourQuadrantgrid").jqGrid('setCell',row[i],cm[j].name,fourQdImg );
     	 }
	 }
	/**/
}
function txtformatter(id, options, rowObject) {
	if(rowObject[1] == 5){
		return '<img id="imgquadrantimage_2" name="imgquadrantimage"  src="images/green-3.jpg" width="20px" height="20px" align="middle" />';
	}else if(rowObject[1]==3){
	return '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/green2.jpg" width="20px" height="20px" align="middle" />';
	}else if(rowObject[1]==4){
		return '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/Green1.jpg" width="20px" height="20px" align="middle" />';
		}
	else if(rowObject[1]==2){
		return '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/green4.jpg" width="20px" height="20px" align="middle" />';
		}
	else if(rowObject[1]==1){
		return '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/green0.jpg" width="20px" height="20px" align="middle" />';
		}
}
function formatter(id, options, rowObject) {
	if(rowObject[1] == "MCH0002057"){
		return '<img id="imgopl" name="imgquadrantimage" src="images/green-3.jpg" width="20px" height="20px" align="middle" />';
	}else if(rowObject[1]== "MCH0002061"){
	return '<img id="imgopl" name="imgquadrantimage" src="images/green0.jpg" width="20px" height="20px" align="middle" />';
	}else if(rowObject[1]=="MCH0002062"){
		return '<img id="imgopl" name="imgquadrantimage" src="images/green2.jpg" width="20px" height="20px" align="middle" />';
		}
	else if(rowObject[1]=="MCH0002059"){
		return '<img id="imgopl" name="imgquadrantimage" src="images/green4.jpg" width="20px" height="20px" align="middle" />';
		}
	else {
		return '<img id="imgquadrantimage_3" name="imgquadrantimage" src="images/Green1.jpg" width="20px" height="20px" align="middle" />';
		}
}
 


</script>
<form id="frmFourQuadrant">
<div id="wrapper" style="width:90%">
		<table>
			<tr>
			 	<td>
				<div style="padding-left:10px;">
				<div id="frmFourQuadrantFuntKeyIds" >							
												<input type="hidden" id="factory" name="cmbVcclFactoryid" value=" "  ></input>			
												<input type="hidden" id="section" name="cmbVcclSectionid" value=" "  ></input>
												<input type="hidden" id="cell" name="cmbVcclCellid" value=" "  ></input>
												<input type="hidden" id="machine" name="cmbVcclEquipmentid1" value=" "  ></input>
												<input type="hidden" id="flid" name="txtVcclFlid" value=" " />						
									</div>						
									<div id="fourfunLocation" style="width:90%;"></div>
									</div>
									
				</td>

			  
		</table>
		 <table  id='fourQuadrantgrid' > <tr> <td></td> </tr> </table>
		 <div id='pager'></div>
</div>	
<%-- ${requestScope.showGrid} --%>
<input type="hidden" value="${requestScope.showGrid} " id="hdnShwGrd"/>	
</form>