<script type="text/javascript">
jQuery(document).ready(function(){
initialiseForm("frmpmatrix");
jQuery('#submitForm').val('frmpmatrix');

fillComboBox("frmpmatrix","cmbPM","machineCombo.commonFilter" );
var factId = jQuery("#frmpmatrix input[id='factory']").val();
var sectionId = jQuery("#frmpmatrix input[id='section']").val();
var cellId = jQuery("#frmpmatrix input[id='cell']").val();
var machId = jQuery("#frmpmatrix input[id='machine']").val();
var flid = jQuery("#frmpmatrix input[id='flid']").val();
//var cmbSopm=jQuery("#txtcmbSopm").val();
var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId  +"&flid="+flid;


loadFunctionalLocation("pmatrix","functionalLoc.commonFilter","pmatrixfunLocationValues","frmpmatrix",dataStr);
viewGrid("partproblemmatrix_input.pprm","?q=2");

imageUpload(jQuery( "#dlgImg" ),'ImageUpload.commonFilter','dlgImg',"imgmatriximage","imgmatrixImgFilename");

jQuery("#imgknowimage").load(function() {
	if((jQuery(this).width()>415)||(jQuery(this).height()>264))
	{	jQuery('#imgknowimage').attr('src', "");
		alert('Select Image with width not greater than 11 cms and height not greater than 7 cms');
		return false;
	}
});


fileManagerPopUp("","PRM","frmpmatrix","btnfilemgr","PbmmtxFilemgr");

var btnName = jQuery("#hdnBtnName").val();
jQuery("#btnViewTemplate").val(btnName);
jQuery("#btnViewTemplate").click(function(){
	
	processAjaxCalls("openFile.file?fileName=Kaizen_Format.pdf", "", "", "", "", "viewTemplate");					
});


});

function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"PRM","","","");
	}
	
}

function imgmatriximageOnComplete(response)
{		//alert(1);
	//jQuery("#hdnEmpImgUrl").val(response);
	//var dlgimage =  jQuery("#btnimgEmpAdd");
	 //imageUpload(dlgimage,'ImageUpload.commonFilter','imgEmpAdd',"imgEmployee");	
	
}

function viewGrid(url,filterString)
{

	var tableCaption = "Matrix";
	
	processGridnew(url,filterString,"MatrixGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	
    return true;

}

function txtFormatter(id, options, rowObject) {
	var id = options.rowId;
	var columnName = options.colModel.name;
	var columnNo = options.pos;
	
	if(columnNo==5){
		var idval='selRemarks_';
		return '<select id='+idval+columnNo + '_'+id +' class="easyui-text"  style="width:100px; height:20px"><option value="C">CLTI</option><option value="P">PM</option><option value="C">CBM</option> ';		}
	if(columnNo==6){
	var idval='selRemarks_';
	return '<select id='+idval+columnNo + '_'+id +' class="easyui-text"  style="width:100px; height:20px"  ><option value="R">Run</option><option value="S">Stop</option><option value="W">WorkShop</option>';
	}

	if(columnNo==9)
		
    {
		var idval='selRemarks_';
		return '<input type="button" id='+idval+columnNo + '_'+id +' name="btnknowwhyGrid" onclick=" Action('+id+','+columnNo+') "  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
    }	

	if(columnNo==8)
	{
	var idval='cmbRemarks_';

	return '<input class="easyui-combobox" id='+idval+columnNo + '_'+id +' name="cmbMomdDiscussionType"  style=" width :100px;"  value=""/>';
	}
}
function loadComplete()
{
	
	fillComboBoxWithGrid("frmpmatrix","cmbRemarks_","frequency.commonFilter");

}



function Action(rowid, colid)			
{
	
	 LoadPopUp("Action","KnowWhy_input.KnowWhy", true, "87%", "80%", "7%", "6%", " ", "KnowWhy","",true);
					
}	

function frmpmatrixcmbPM_onSelect(record)
	{
	
       loadFunctionalLocation("pmatrix","functionalLoc.commonFilter","pmatrixfunLocationValues","frmpmatrix","&machId="+ record.id );
	}

function frmKnowClear()
{
	 jQuery('#imgknowimage').attr('src', "");
}


jQuery( "#btnImgClear" ).click(function() {
	jQuery('#imgmatriximage').attr('src', "");
//	jQuery('#imgmatrixImgFilename').val("");

	
	});



</script>


 <form id="frmpmatrix" name="frmpmatrix">

      <div id="wrapper">
 	<table> 
 	<tr>
 	<td colspan="2">    
 <div id="frmpmatrixFormatFuntKeyIds">						
									<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
									<input type="hidden" id="section" name="cmbsection"  value=""></input>
									<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
									<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
						            <input type="hidden" id="txtQpmFlnid"  name= "txtQpmFlnid" value=""/>
								</div>
	<div id="pmatrix" style="width:600px; width:700px\9">
	</div>	
	</td>
<td  style="width :305px;padding-left:25px;"> 

<div class="easyui-paddingbfpx">
<label>Drawing Ref</label></div>
				
<div style="position:absolute;">
							<div  style="padding-top:10px;margin-left: 105px">
							
							
				<span style="position:relative;top:-38px;">
	                 <input type="button" class="easyui-button" id="dlgImg"	name="dlgImg" value="+" style="width:20px;height:20px"/></span>
				           <span style="padding-left: -1px;position:relative;top:-38px;">
							   <input type="button" class="easyui-button" id="btnImgClear"name="btnImgClear" value="-"  style="width:20px;height:20px"/></span>
							        </div>
							<div style="position:absolute;top:-7px;">
							<img id="imgmatriximage" name="imgmatriximage"  src="images/Gradient_ascent_(surface).png" onmouseover="ZoomImage('imgmatriximage')" onmouseout="removeZoom();" width="150px" height="91px"/>
							</div>
					
</div>

							</td>
							</tr>       	 

<tr>

	<td valign='top'>
	<div class="easyui-paddingbfpx"> <label>Equipment </label>
	</div>
		<input id="cmbPM" name="cmbPM" class="easyui-combobox"  value="${requestScope.newQtmTlSopmst. sopmPreparedby}" style="width:370px;" />
</td>
			
<td valign='top' style="padding-left:50px"> 
<div class="easyui-paddingbfpx">
  <label>Description </label>
		 </div>  	
		   <textarea rows="2" cols="80"  style="width:389px;height:50px;text-transform: uppercase;" maxlength = "50"  id="txtNmrtDescnearmiss" name="txtNmrtDescnearmiss"></textarea>  
		  </td>
		




				  </tr>				
	</table>
  
  
            <div style=" padding-left:10px; padding-top:10px;position:relative; ">
					 <span  id="PbmmtxFilemgr" style="position:absolute;right:170px;right:180px\9;" >
     		
             </span> 
             </div>	

  
<div class="easyui-paddingbfpx" style="float: left;margin-top:30px;" >
<table id="MatrixGrid"  ></table>
		<div id="pager"></div>
		</div>
</div>
<input type="hidden" id="mode"/>
<input type="easyui-text" id="hdnEmpImgUrl" name="hdnEmpImgUrl" style="display:none;" value=""/>
 </form>