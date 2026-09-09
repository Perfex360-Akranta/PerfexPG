<script type="text/javascript">

jQuery(document).ready(function(){

	initialiseForm("frmQPFormat");
	jQuery('#submitForm').val('frmQPFormat'); 
	formatDateBox('dteQpmPrepareddate', 'dd-MMM-yyyy');
	formatDateBox('dteQpmApproveddate', 'dd-MMM-yyyy');
	fillComboBox('frmQPFormat','cmbQpmPreparedby' ,'employee.commonFilter') ;
	fillComboBox('frmQPFormat','cmbQpmApprovedby' ,'employee.commonFilter') ;	
	var factId = jQuery("#frmQPFormat input[id='factory']").val();
    var sectionId = jQuery("#frmQPFormat input[id='section']").val();
    var cellId = jQuery("#frmQPFormat input[id='cell']").val();
    var machId = jQuery("#frmQPFormat input[id='machine']").val();
     var flid = jQuery("#txtQpmFlnid").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId  +"&flid="+flid;
 
    loadFunctionalLocation("QPform","functionalLoc.qp","frmQPFormatLocationfunLocationValues","frmQPFormat",dataStr);
    
    fileManagerPopUp("","qp","frmQPFormat","btnQPFilManager","QPFilemgr");
    var keyid=jQuery("#txtQpmKeyid").val();
    
	 viewGrid("QP_input.qp","?q=2&keyid="+keyid);

	jQuery('#btnQP').click(function(){
		 var keyid=jQuery("#txtQpmKeyid").val();//get value from text box
		 
			if(keyid.trim().length>0)//keyid not null means 
				 LoadPopUp("qpoint", "QPPopUp_input.qp?q=2&keyid="+keyid, true,"80%","90%","2%","10%", "skillResult_successCallBack","Q Point Detail","",true);
			else{
				//var CheckButton=jQuery("#txtCheckButton").val();
				saveForm("frmQPFormat","Master_save.qp");
				jQuery("#txtCheckButton").val("true");
				}
	});

	 
	});


function frmQPFormat_successsCallback(result){
	
	var keyid=result.keyid;
	var chkBtnClicked = jQuery("#txtCheckButton").val();//not show in popup
	if(keyid.length>=0  && chkBtnClicked.trim().length>0 )//
		 LoadPopUp("qpoint", "QPPopUp_input.qp?keyid="+keyid, true,"80%","90%","2%","10%", "skillResult_successCallBack","Q Point Detail","",true);
	
	  
	 	
	 
}

	
	function viewGrid(url,filterString)
{
	
		var tableCaption = "QPFormat";
		
		processGridnew(url,filterString,"QPFormatGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
		
	    return true;
	
}
	function doubleClickGrid(rowid)
	{
		var rowData = jQuery("#QPFormatGrid").jqGrid('getRowData',rowid );
		var keyid=rowData.KEYID;
		var masterkeyid=rowData.QPD_QPMKEYID;
	
		 LoadPopUp("qpoint", "QPPopUp_input.qp?detailkeyid="+keyid+"&keyid="+masterkeyid, true,"80%","90%","2%","10%", "skillResult_successCallBack","Q Point Detail","",true);
			
	}
	
	function btnQPFilManager_click(){
	        
		   var documentNo =jQuery('#txtQpmKeyid').val();
			
			if(documentNo != null && documentNo != ''){
				fileManagerPopUp(documentNo,"QP","","","");
			}
			
	 		}	
	function qpoint_onClose()//reload grid
	{

		 jQuery("#QPFormatGrid").trigger("reloadGrid");
		  
		    return true;

		}
	
	function frmQPFormat_deleteSuccessCallback(result)
	{
		 
		alert (result.successData.msg);
		
	}
	function frmQPFormat_FuntLocHierarchy_SuccessCallBack(result){
		 
		
		var flid = result.flid;
	
		jQuery("#txtQpmFlnid").val(flid);
		
	}
		
</script>


   <form id="frmQPFormat" name="frmQPFormat">

      <div id="wrapper" style="width: 70%;" align="center" >
      <table>
              <tr>
                  <td  colspan="4">
                                    <div id="frmMissedFormatFuntKeyIds" >						
									<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
									<input type="hidden" id="section" name="cmbsection"  value=""></input>
									<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
									<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
									 <input type="hidden" id= "txtQpmFlnid"  name= "txtQpmFlnid" value="${ requestScope.newQtmTlQpointmst.qpmFlnid}"/>
									<div id="QPform" style="width : 857px; width : 1060px\9; padding-left: 59px;padding-left: 55px\9;">
							
							</div>	
						</div> 
				</td>
       
         </tr>
               </table>

<div style="padding-top: 10px ; " >


      <table  >
           <tr >
              <td style="padding-left: 12%;padding-left: 15%\9"  >
                    <div class="easyui-paddingbfpx" >
	             <label  class ="mandatory-lbl">  Prepared By </label> 
	               </div>
		           <div  class="easyui-paddingbfpx" >
			<input id="cmbQpmPreparedby"  name="cmbQpmPreparedby"  class="easyui-combobox" value="${requestScope.newQtmTlQpointmst.qpmPreparedby}" maxlength="95" style="width: 250px;"/>
			
			        </div>
			   
			       <div class="easyui-paddingbfpx" style="">
                         <label class ="mandatory-lbl">Approved By</label>  
                   </div> 
             <div>
		         <input id="cmbQpmApprovedby"  name="cmbQpmApprovedby"  class="easyui-combobox"  maxlength="95" value="${requestScope.newQtmTlQpointmst.qpmApprovedby }" style="width: 250px;"/>
		
		     </div>
		 </td>
		         <td style="padding-left: 128px ; "   >
			   <div  class="easyui-paddingbfpx" > 
			          <label class ="mandatory-lbl"> Date </label>
			  </div>
			  <div class="easyui-paddingbfpx" >  
		             <input id='dteQpmPrepareddate' name="dteQpmPrepareddate" type="text" class="easyui-datebox" value="${requestScope.newQtmTlQpointmst. qpmPrepareddate}"  maxlength="50" style="width: 120px;"/>
		        
             </div>
             <div   class="easyui-paddingbfpx" style=""> 
                     <label  class ="mandatory-lbl"> Date </label>
             </div>
		     <div class="easyui-paddingbfpx" style="">    
		          <input id='dteQpmApproveddate' name="dteQpmApproveddate" type="text" class="easyui-datebox" value="${requestScope.newQtmTlQpointmst.qpmApproveddate }"   maxlength="50" style="width: 120px;"/>
		       
		          		
	     
             </div>
             
             
             </td>
             <td >
             <div style="padding-top: 30px">
                   <span  style="padding-left:21.8%;padding-left:105%\9;position:absolute;  padding-top:-3px;"> 
		       	 <span id="QPFilemgr"  >
		       	     </span>
		               	</span>
    </div>
        </td>
     </tr>
 </table>
	</div>
	
	
	
	<div style="padding-left:61px;padding-left:8%\9  ; padding-top: 10px">
						<div class="sub-header" style="text-align: left;width:818px; width:812px\9;height:25px\9;position:relative;margin-right:4%"><span style="position:absolute;"> Q Point Detail </span>
							<span id="QPbtn" style="position:absolute;right:0px;">
								
<img  class="" style="cursor: pointer;z-index:210;margin-top:-2px;margin-top:-2px\9;" src="images/addbtsub.png" title="Add Role" alt="" id="btnQP">
							</span>
							
			
	</div>
	
	
	<div  style="margin-left: -0.3% ;margin-left: 0%\9 ">
	
	<table id='QPFormatGrid'>
	
	<tr>
		<td>   </td>
	</tr>

</table>


<div id="pager"></div>
     </div>
          </div>
	            </div>
<input type="hidden" id="mode" name="mode" value=""/>
 <input id="txtQpmKeyid" type='hidden' name="txtQpmKeyid" value="${requestScope.newQtmTlQpointmst.qpmKeyid}" />
 <input id ="txtQpdKeyid" type='hidden' name="txtQpdKeyid" value="${requestScope. masterkeyid}" />
  <input id="txtCheckButton" type='hidden' name=""  />
  
   

   
</form>	
          
       