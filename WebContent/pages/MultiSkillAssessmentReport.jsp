<script type="text/javascript">
jQuery(document).ready(function(){

	//alert("formatterTxtLossess");   
	//alert("dgodfio"+jQuery('#txtSopdKeyid').val());
	jQuery('#submitForm').val('frmskillassess');
	initialiseForm("frmskillassess"); 
	formatDateBox('dteQpmPrepareddate', 'dd-MMM-yyyy');
	fillComboBox("frmskillassess","cmbshift","combo_shift.brdn" );
	var factId = jQuery("#frmskillassess input[id='factory']").val();
    var sectionId = jQuery("#frmskillassessinput[id='section']").val();
    var cellId = jQuery("#frmskillassess input[id='cell']").val();
    var machId = jQuery("#frmskillassess input[id='machine']").val();
    var flid = jQuery("#txtQpmFlnid").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId +"&machId="+machId+"&flid="+flid;
     
    viewGrid("Multiskillassessment_input.skil","?q=2");
	loadFunctionalLocation("RiskAssessmentform","functionalLoc.skil","RiskAssessmentformvalues","frmskillassess",dataStr);
	
	
	});

function viewGrid(url,filterString)
{

	var tableCaption = "Matrix";
	
	processGridnew(url,filterString,"MatrixGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
	
    return true;
}
    function loadComplete() {
   	 var row = jQuery("#MatrixGrid").jqGrid('getDataIDs');
   	 var cm = jQuery("#MatrixGrid").jqGrid("getGridParam", "colModel");

   	 
   	 for(var i=0;i<row.length;i++)
   	 {
   		 for(var j=1;j<cm.length;j++)
       	 {
   	    	 var zeroVal = jQuery("#MatrixGrid").jqGrid('getCell',row[i],cm[j].name);	
   	    	
   			
   		   if(zeroVal==' '){
   			  
   			
   			jQuery("#MatrixGrid").setCell(row[i], cm[j].name.trim(), '',{'background-color':'#9FA0A3'});
   			
   			//alert("cm[j].sdsd"+cm[j].name);
   			// tick(row[i],'Managercompfrmtloadgrid',cm[j].name);  2col2	1col5	2col10	4col16
   	 	 }
       	 }
   	 }
   	 
   	     
   	 
   }



	</script>
	
<form id="frmskillassess" name="frmskillassess">

<div id="wrapper" style="width: 70%;" align="center" >
      <table>
              <tr>
                  <td  >
                  <div style="margin-top: -28px">
                                    <div id="RiskAssessmentformvalues" >						
									<input type="hidden" id="factory" name="cmbfactory"  value="" ></input>
									<input type="hidden" id="section" name="cmbsection"  value=""></input>
									<input type="hidden" id="cell"    name="cmbcell"     value=""></input>
									<input type="hidden" id="machine" name="cmbmachine"  value=""></input>	
									 <input type="hidden" id= "txtQpmFlnid"  name= "txtQpmFlnid" value=""/></input>
									<div id="RiskAssessmentform" style="width : 857px; width : 1060px\9; padding-left: px;padding-left: 55px\9;">
							</div>
							</div>	
						</div> 
				</td>
       
         </tr>
               </table>
              
               
            <table>
            <tr >  
            <td style="padding-left:1% ">
               <div   > 
			          <label > Date </label> <span> <label> </label></span>
			  </div>
			  <div  >  
		             <input id='dteQpmPrepareddate' name="dteQpmPrepareddate" type="text" class="easyui-datebox" value="${requestScope.newQtmTlQpointmst. qpmPrepareddate}"  maxlength="50" style="width: 120px;"/>
		        
             </div>
               </td>
               <td style="padding-left:40% ">
               
               <div> <label> Shift </label></div>
	    <div>
	    
		<input class="easyui-text"  style="width:100px; height:25px ;text-transform: uppercase;" id="cmbshift"   name="cmbshift" value="" size="15"  / >
		</div>
               </td>
               
</tr>
</table> 
<div >

<table id="MatrixGrid"  ></table>
		<div id="pager"></div>
		</div>
		</div>

</form>