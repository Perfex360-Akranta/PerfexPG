<script type="text/javascript">

jQuery(document).ready(function(){
	initialiseForm("frmsop");
	
	jQuery('#submitForm').val('frmsop');
	
	formatDateBox('dteSopmPrepareddate','dd-MMM-yyyy');
	formatDateBox('dteSopmApproveddate','dd-MMM-yyyy');
	fillComboBox("frmsop","cmbSopmPreparedby","employee.commonFilter" );
	fillComboBox("frmsop","cmbSopmApprovedby","employee.commonFilter" );
	//alert("tamil");
	var factId = jQuery("#frmsop input[id='factory']").val();
    var sectionId = jQuery("#frmsop input[id='section']").val();
    var cellId = jQuery("#frmsop input[id='cell']").val();
    var machId = jQuery("#frmsop input[id='machine']").val();
    var flid = jQuery("#frmsop input[id='flid']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId=MCH0002058"+"&flid="+flid+"";
  //  alert("tamil");
    loadFunctionalLocation("sopfunLoc","functionalLoc.sop","frmsopLocationfunLocationValues","frmsop",dataStr);

    //alert("tamil");
    fileManagerPopUp("","sop","frmsop","btnsopFilManager","voiceFilemgr");
    viewGrid("Sopmodify_input.sop","q=2");
  //  alert("tamil");
//alert("tamil");
	jQuery('#btnsop').click(function(){//button click function ,save function
		 //alert(url);
		var keyid=jQuery("#txtSopmKeyid").val();//get value from text box
		if(keyid.trim().length>0)//keyid not null means 
         LoadPopUp("sopDetail", "SopPopUp_input.sop?keyid="+keyid, true,"80%","80%","0%","10%", "skillResult_successCallBack","SOP","",true);
		else{
			//var CheckButton=jQuery("#txtCheckButton").val();
			saveForm("frmsop","Sopmodify_save.sop");
			jQuery("#txtCheckButton").val("true");
			}
			

		
	});
	//alert("tamil");
});

/*jQuery('#btnsop').click(function(){//detail master keyid
	         alert(keyid);
		var keyid=jQuery("#txtSopdKeyid").val();
	
		 LoadPopUp("sopDetail", "SopPopUp_input.sop?detailkeyid="+detaikeyid, true,"70%","80%","0%","10%", "skillResult_successCallBack","Skill","",true);*/


function frmsop_successsCallback(result){
	var keyid=result.QtmKeyid;
	var chkBtnClicked = jQuery("#txtCheckButton").val();//not show in popup
	jQuery("#txtSopmKeyid").val(keyid);//set value from text box
	if(keyid.trim().length>0 && chkBtnClicked.trim().length>0 )//
		 LoadPopUp("sopDetail", "SopPopUp_input.sop?keyid="+keyid, true,"80%","80%","0%","10%", "skillResult_successCallBack","Detail","",true);

	  
	 	
	 
}

function sopDetail_onClose()//reload grid
{

	 jQuery("#SopdtlGrid").trigger("reloadGrid");
	  
	    return true;

	}

	function viewGrid(url,filterString)
	{

		var tableCaption = "Sop";
		var keyid=jQuery("#txtSopmKeyid").val();
		filterString += "&sopGridkeyid="+keyid;
		processGridnew(url,filterString,"SopdtlGrid","Pager",tableCaption,"doubleClickDetailsGrid","","");

		
	  
	   
	}
/*	function loadComplete_detail()
	{
		alert("1230");
		}*/
	function btnsopFilManager_click(){
	        
		    var documentNo =jQuery('#txtSopmKeyid').val();
			
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"SOPM","","","");
			}
			else
				alert("Machine should be selected to view FileManager");
	 		
		}
		
	function doubleClickDetailsGrid(id){  //grid double click//MASTERKEY ID AND DETAIL KEYID
		//alert("DFJGHLFHLFK");
		var rowData = jQuery("#SopdtlGrid").jqGrid('getRowData',id);
		var detailkeyid = rowData.KEYID;
		LoadPopUp("sopDetail", "SopPopUp_input.sop?detailkeyid="+detailkeyid, true,"70%","80%","0%","10%", "skillResult_successCallBack","SOP","",true);	 	
		}
		

	
</script>

<form id="frmsop" name="frmsop">
<div id="wrapper">

<div id="frmsopFuntKeyIds"  >							
	<input type="hidden" id="factory" name="factory"  value="" ></input>
	<input type="hidden" id="section" name="section"  value=""></input>
	<input type="hidden" id="cell"    name="cell"     value=""></input>
	<input type="hidden" id="machine" name="machine"  value=""></input>
   <input type="hidden" id="flid" name="cmbSopmFlnid"  value=""></input>

</div>
	<div id="sopfunLoc" style="width:681px; width:700px\9"></div>		
<table>
<tr>

	<td>
	<div class="easyui-paddingbfpx"> <label  class ="mandatory-lbl">  Prepared By </label>
	</div>
		
		
		
				<input id="cmbSopmPreparedby" name="cmbSopmPreparedby" class="easyui-combobox"  value="${requestScope.newQtmTlSopmst. sopmPreparedby}" style="width:350px;" />
                     			
				
			
				</td>
			
				
				
	<td style="padding-left:50px"> 
	<div> 
  <label  class ="mandatory-lbl">Prepared Date </label>
		 </div>  	
		  <input id="dteSopmPrepareddate" name="dteSopmPrepareddate" class="easyui-datebox" value="${requestScope.newQtmTlSopmst.sopmPrepareddate}" style="width:10px;width:140px;\9" />
		  </td>
	</tr>
	
<tr>	
	<td>
			<div class="easyui-paddingbfpx"   style ="padding-right:20%"> 
				<label class ="mandatory-lbl"> Approved By </label>
				
			</div>
			<div  class="easyui-paddingbfpx"  style ="padding-right:10px">
				<input id="cmbSopmApprovedby" name="cmbSopmApprovedby" class="easyui-combobox" value="${requestScope.newQtmTlSopmst.sopmApprovedby}" style="width:350px;" />
				
				</div>
				
				</td>
			
             
             
             
         
				<td >
				
                 <div style="padding-left:50px" >				
				<label class ="mandatory-lbl">Approved Date</label>
 </div>
 <div  style="padding-left: 50px">
		       	<input id="dteSopmApproveddate" name="dteSopmApproveddate" class="easyui-datebox"  value="${requestScope.newQtmTlSopmst.sopmApproveddate}" style="width:110px;width:140px;\9" />
		     	
		       	 </div>
		       	 <td>
		       	 <span  style="padding-left:50px;position:absolute;padding-left: 55px\9"> 
		       	 <span id="voiceFilemgr" >
		       	</span>
		       	</span>
		       
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		        
		    </td>


</tr>


</table>

<div style="padding:left">

<div class="sub-header" style="text-align: left;width:831px;width:830px\9; height:25px\9; position:relative;margin-left:-1px;"><span style="position: absolute;">SOP</span>
		<span id="sopFileMgr" style="  position:absolute ;right:0px;right:-2\9;top:-1; ">

<img  class="" style="cursor: pointer;z-index:210;height:-10px;height:26px\9;" src="images/addbtsub.png" title="Add skill" alt="" id="btnsop"/>
								</span>
							</div>	

<div style="padding-right: 30%">
		<table id='SopdtlGrid' style="padding:left">
    
        </table>
     
<div id='pager'></div>

</div>



 <input type="hidden" id="mode"/>
 

 <input id="txtSopmKeyid" type='hidden' name="txtSopmKeyid" value="${requestScope.newQtmTlSopmst.sopmKeyid}" />
 
  <input id="txtCheckButton" type='hidden' name=""  />
 </div>
 </div>
 </form>

 
 



          

