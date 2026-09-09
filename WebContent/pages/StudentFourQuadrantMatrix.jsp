<script>
jQuery(document).ready(function(){//alert(1);

	initialiseForm('frmStudentfourquadrant');
	processGridnew("FourQuadrantMatrix_input.sfqm","","Quadrantgrid","Quadrantpager");
	//setLoadFormCallBackFrmId("frmStudentfourquadrant");

	fillComboBox("frmStudentfourquadrant","cmbStudent","employee.commonFilter");
	
	var factId = jQuery("#frmStudentfourquadrant input[id='factory']").val();
	var sectionId = jQuery("#frmStudentfourquadrant input[id='section']").val();
	var cellId = jQuery("#frmStudentfourquadrant input[id='cell']").val();
	var machId = jQuery("#frmStudentfourquadrant input[id='machine']").val();
	var flid = jQuery("#frmStudentfourquadrant input[id='flid']").val();
	//alert(" cellId:::::::: "+jQuery('#cellId').val());
	var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+ "&flid=" +flid;
	//alert(1);
	loadFunctionalLocation("FourQuadrantfunLocation","functionalLoc.opl","FourQuadrantValues","frmStudentfourquadrant",dataStr);

	var empfield =jQuery("#frmEmpfield").val();
	//alert(" Inside :::: "+empfield.length+"  ::::  "+empfield);
	
	if(empfield.length>1){//alert(1);
		jQuery("#empfield").show();
	}
	setLoadFormCallBackFrmId("frmStudentfourquadrant");
	invokeAfterLoadFormCallBack();


	jQuery('#btnDateWise').click(function()
	{
        var flid = jQuery("#hdnflid").val();

        if(flid.length==0)
        {
            alert(" Select JH ");
            return false;
        }else
            navigateToNextForm("FourQuadrantMatrixDateWise_input.sfqm?&filterButton=true&flid="+flid,"OPL Four-Quadrant Matrix Date Wise");
	 	
	});
	
});
function frmStudentfourquadrant_afterLoadCallBack(){
	//if(jQuery('#hdnfromBackPcs').val() == "")
	toggleCommonFilter();  
	}
	//viewGrid("FourQuadrantMatrix_input.sfqm",'?q=2&Empid='+ Empid+'&cellId='+cellId);
	//viewGrid("FourQuadrantMatrix_input.sfqm",'?q=2&cellId=' + cellId+'&flid='+flid); 


function frmStudentfourquadrant_afterLoadCallBack(){
	//if(jQuery('#hdnfromBackPcs').val() == "")
		toggleCommonFilter();		
}

function  frmStudentfourquadrantcmbStudent_onSelect(record)
{   
	//alert(" record:: 1 ");
	//alert(" record:: 2 "+record);
	var Empid = getFieldValue("cmbStudent", "frmStudentfourquadrant");

	var cellId=jQuery("#hdncellid").val();
	//alert(" val::cellId:: 11 "+cellId);
	//viewGrid("FourQuadrantMatrix_input.sfqm",'?q=2&Empid='+ Empid+'&cellId='+cellId);
	//alert(" val:::: 22 ");
	
}

function frmStudentfourquadrant_FuntLocHierarchy_SuccessCallBack(keyIds)
{

	var cellId = keyIds.cellId;
	//alert(" Inside Hirerachy 1111 :::: "+cellId);
	jQuery("#hdncellid").val(cellId);
	var flid =keyIds.flid;
	
	//alert(" Inside Hirerachy 2222 :::: "+flid);

	//viewGrid("FourQuadrantMatrix_input.sfqm",'?q=2&cellId=' + cellId+'&flid='+flid);
	//alert( " Inside Hirerachy 2222 :::: " );
}

function viewGrid(url,filterString)
{
      //alert(1);
        //alert(url+" :: 1 :: ");
      
	  //processGridnew(url,filterString,"Quadrantgrid","Quadrantpager");
	  //alert(jQuery('#cell').val());
	if( validateFilterSelection(filterString))
	{   
		
		var cellId = getFilterValue(filterString+'&', 'cmbCellid');
	    var flid = getFilterValue(filterString+'&', 'flid');
	    //alert(" flid :: "+flid);
	    jQuery("#hdnflid").val(flid);

		filterString += '&cellId=' + cellId;	
		//alert(" filterString::::: "+filterString);	
		var tableCaption = "Four Quadrant Matrix";
		//processGridnew(url,filterString,"pcsPlanVsActualGrid","pcsPlanVsActualPager",tableCaption);
		processGridnew(url,filterString,"Quadrantgrid","Quadrantpager",tableCaption);		
		return true;
	}
	
	return false;
	
	
	
}
function validateFilterSelection(filterString){
	
	if(filterString=="?q=2")
		return true;

	//if(jQuery('#hdnfromBackPcs').val() == "" || jQuery('#hdnfilterClicked').val()== "true"){
		if(getFilterValue(filterString, "cmbCellid") == ""  && !checkFilterValueExist(filterString,"cmbCircle")){
			alert("Select JH");
			return false;
		//	}
	}
	   return  true;
}
function setfourquadrantimage(id, options, rowObject) {

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

<form name="frmStudentfourquadrant" id="frmStudentfourquadrant" action=" " method="post">
<div id='WrapperRpt'>
<table>
   <tr>
<!--     <td>-->
<!--<div  id="frmStudentfourquadrantFuntKeyIds"  >-->
<!--			<input type="hidden" id="factory" name="cmboplmFactoryid" value=""  ></input>-->
<!--			<input type="hidden" id="section" name="cmboplmSectionid" value=""  ></input>-->
<!--			<input type="hidden" id="cell" name="cmboplmCellid" value=""  ></input>-->
<!--			<input type="hidden" id="machine" name="cmboplmMachineid1" value=""  ></input>-->
<!--			<input type="hidden" id="flid" name="cmboplmFlid" value=""  ></input>-->
<!--			</div>-->
<!--			<div id="FourQuadrantfunLocation" style="padding-left: 20px;width:74%;">-->
<!--			-->
<!--			</div>-->
<!--			-->
<!--       </td>-->
       <td>
       <div id ='empfield' style="padding-top:10px;padding-left:10px;display:none;">
       <div>
       <label>Employee</label>
       </div>
       <div>
       <input id="cmbStudent" name="cmbStudent" class="easyui-combobox"  style="width:175px;" value="" />   
       </div>
       </div>
       </td>
       <td>
       <div style="padding-top:10px;padding-left:900px;float:right;">
           <input type="button" class="easyui-button" id="btnDateWise" name="btnDateWise" value="Date Wise" style="height:23px;width:70px;"/>   
       </div>
       </td>
  </tr>			
</table>
<div style="margin-left:16px;margin-left:-10px;">
<table id='Quadrantgrid'><tr><td></td></tr></table>
<div id='Quadrantpager'></div>
</div>
<input type="hidden" id="frmEmpfield" value="${requestScope.emp}"/>
<input type="hidden" id="hdncellid" value=""/>
<input type="hidden" id="hdnflid" value=""/>
</div>
</form>