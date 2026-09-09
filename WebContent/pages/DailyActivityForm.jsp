<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
  <script type="text/javascript"> 
         jQuery(document).ready(function()
{
	     //DailyActivity_input.strf(url in table)
	     initialiseForm("formreport");

		 jQuery('#submitForm').val('formreport');
		
		// jQuery('#submitForm').val('keyid');
	     fillComboBox("formreport","cmbDailyresource","akranthaResource.strf");
	     // HERE CMB IS PREDEFINED URL AND  "DAILYRESOURCE" IS COMING FROM  MODEL POST- TEXT OF GET SET LIKE GET--"DAILYRESOURCE"
	     fillComboBox("formreport","cmbDailysuptype","SupportType.strf");
	 	 fillComboBox("formreport","cmbDailyisstpe","IssueType.strf");
	     fillComboBox("formreport","cmbDailyissloc","IssueLocation.strf");
         fillComboBox("formreport","cmbDailyissrespn","akranthaResource.strf");
	     fillComboBox("formreport","cmbDailyissstas","IssueStatus.strf");
	     formatDateBox("dteDailydate","DD-MMM-YYYY");
       
         formatDateBox("dteDailytarget","DD-MMM-YYYY");
          fillWithCurrentDate("dteDailydate");
          fillWithCurrentDate("dteDailytarget");
         fileManagerPopUp("","Taskactivity","formreport","btnFilManage","ActivityFilemgr");
        
});
          function formreport_beforeDelete()
         
         
         {
        	 if(!confirm("do you want to delete record "))
        	 
        	
         	
         	 return false;
         	 
         	 else
         		 var keyid=jQuery("#txtDailyKeyid").val();
         		 alert("delete record"+keyid); 
             return true;
         	 
        	//return '&keyid='+keyid;
        	
          }

function formreport_beforeSubmit()
{
	
      var id=jQuery('#submitForm').val('formreport');

      var res=jQuery('#cmbDailyresource').combobox('getValue');
      var suptype=jQuery('#cmbDailysuptype').combobox('getValue');

      var dteDailydate=jQuery('#dteDailydate').combobox('getValue');
      
      var  currentDate=new Date();
      //alert(curentDate);
     var selectedDate=jQuery('#dteDailydate').combobox('getValue'); 
     //alert(selectedDate);
    //const diffTime =parse(selectedDate);
    
  
    
     
       if(res.length==0||suptype.length==0)
	   {
    	  
        popupCommonErrorMsg("please enter all .");
    	   
    	   return false;
    	    }
       else
    	   {
    	   alert("Data Saved");
    	   
           return true;
        }
       
    
      // if(dteDailydate.)

}

//it passes which requet to be passed like url_input.strf or url_save.strf to be c
function btnFilManage_click()
{
	var keyid1 =jQuery("#txtDailyKeyid").val();

	if(keyid != null && keyid !='' ){
		
	fileManagerPopUp(keyid,"DAF","","","","create");	

} 
}
</script>



    <form name="formreport" id="formreport"  >

       <div style="height: 80px; margin-top:10px ; border:1px solid black;border-radius: 5px;;">
 
      <div style="height:0px ; width:100%; padding-top:15px;padding-left:30">
    
             <label class="mandatory-lbl" >Resource:</label><br>
  
             <input id="cmbDailyresource" name="cmbDailyresource" class="easyui-combobox" value="${requestScope.dailyActivityMst.dailyresource}"   style="width:200px "/>  

        <!--   cmb(FOR COMBOBOX) --(Dailyresource)(FOR MODEL CONNCTING GET SET METHOD POST NAME ) -->
   
        <div style="margin-left:250px;margin-top:-40px">   <label class="mandatory-lbl">Emp Code:</label> <br>
        
             <input id="txtDailycode" name="txtDailycode" class="easyui-text" value="${ requestScope.dailyActivityMst.dailycode }" style="width:150px"  >
   
         </div>
   
        <div style="margin-left:450px;margin-top:-40px">   <label class="mandatory-lbl">Support Type:</label> <br>
        
             <input id="cmbDailysuptype" name="cmbDailysuptype" class="easyui-combobox" value="${requestScope.dailyActivityMst.dailysuptype}" style="width:150px"  >
   
         </div>
   
   
         <div style="margin-left:650px;margin-top:-40px">
                 
                 <div>
                    <label class="mandatory-lbl" > Date: </label>   <br>
                    <input id="dteDailydate" name="dteDailydate" class="easyui-datebox" value="${ requestScope.dailyActivityMst.dailydate }" style="width: 100px">   
               </div>
          </div>
     
     <div style="margin-left:850px;margin-top:-40px">   <label class="">Daily Activity Id:</label> <br>
        
            <input id="txtDailyKeyid" name="txtDailyKeyid" class="easyui-text" value="${ requestScope.dailyActivityMst.dailyKeyid }" style="width:150px" disabled="disabled"  >
   
         </div>
      </div>

</div>

  

     <div  style="height:135px;margin-left: 30 ;margin-top: 10px"  >
              
               <label class="mandatory-lbl">Task Activity/Description</label><br>
               <textarea id="txtDailytaskdes" name="txtDailytaskdes" maxlength="" rows="4"  style="width:1200px"  value="">${ requestScope.dailyActivityMst.dailytaskdes }  </textarea>

         </div>


               <div style="height:200px ;width:110%; border: 1.9px solid black;border-radius: 5px "  >
                        <div  style="margin-left:30;margin-top:px; margin-top: 8px">
                        
                          <label class="mandatory-lbl" >Issue Type:</label><br>
  
                          <input id="cmbDailyisstpe" name="cmbDailyisstpe" class="easyui-combobox" value="${ requestScope.dailyActivityMst.dailyisstpe }"   style="width:200px" >  
                        </div>    
               
                       
                       
                       
                        <div  style="margin-left:250px;margin-top:-40px">
                        
                          <label class="mandatory-lbl" >Issue Location:</label><br>
  
                          <input id="cmbDailyissloc" name="cmbDailyissloc" class="easyui-combobox" value="${requestScope.dailyActivityMst.dailyissloc}"   style="width:200px" >  
                        </div>  
                         
                         
                         <div  style="margin-left:460px;margin-top:-40px">
                        
                          <label class="mandatory-lbl" >Issue responsibility:</label><br>
  
                          <input id="cmbDailyissrespn" name="cmbDailyissrespn" class="easyui-combobox" value="${ requestScope.dailyActivityMst.dailyissrespn }"   style="width:200px" >  
                        </div>  
                         
                         
                         <div  style="margin-left:670px;margin-top:-39px">
                        
                          <label class="mandatory-lbl" >Issue Status:</label><br>
  
                          <input id="cmbDailyissstas" name="cmbDailyissstas" class="easyui-combobox" value="${ requestScope.dailyActivityMst.dailyissstas }"   style="width:200px" >  
                        </div>  
               
               
                <div style="margin-left:890px;margin-top:-39px">
                
                     <label class="mandatory-lbl">Target Date:</label><br>
                    <textarea id="dteDailytarget" name="dteDailytarget"   class="easyui-databox"   maxlength="" rows="4"  style="width:100px"   value="">${requestScope.dailyActivityMst.dailytarget}</textarea>
                    
                
                </div>
                
                <div style="margin-left:1000px;margin-top:-39px">
                
                     <label class="mandatory-lbl">Time Spent:</label><br>
                    <textarea id="txtDailytmespnt" name="txtDailytmespnt"    class="easyui-text"   maxlength="" rows="4"  style="width:100px"   value="">${ requestScope.dailyActivityMst.dailytmespnt }</textarea>
                     
              <div id="ActivityFilemgr"  style="padding-left: 120 ;margin-top: -25">
			
		</div>  
                
                </div>
               
            
           <div style="margin-left: 30">
                
                     <label class="mandatory-lbl">Solution</label><br>
                    <textarea id="txtDailysoln" name="txtDailysoln" maxlength="" rows="4"  style="width:1200px"  value="">${ requestScope.dailyActivityMst.dailysoln } </textarea>
                    
           </div>
                
           </div>
              
<input type="hidden" id="mode" name="mode"/>
<input type="hidden" id="hddailykeyid" name="hddailykeyid" value="${requestScope.dailyActivityMst.dailyKeyid}" />  
<input type="hidden" id="hddailyresource" name="hddailyresource" value="${requestScope.dailyActivityMst.dailyresource}" /> 
<input type="hidden" id="hddailycode" name="hddailycode" value="${requestScope.dailyActivityMst.dailycode}" /> 
<input type="hidden" id="hddailysuptype" name="hddailysuptype" value="${requestScope.dailyActivityMst.dailysuptype}" /> 
<input type="hidden" id="hddailydate" name="hddailydate" value="${requestScope.dailyActivityMst.dailydate}" /> 
<input type="hidden" id="hddailytaskdes" name="hddailytaskdes" value="${requestScope.dailyActivityMst.dailytaskdes}" /> 
<input type="hidden" id="hddailyissloc" name="hddailyissloc" value="${requestScope.dailyActivityMst.dailyissloc}" /> 
<input type="hidden" id="hddailyissrespn" name="hddailyissrespn" value="${requestScope.dailyActivityMst.dailyissrespn}" /> 
<input type="hidden" id="dailyissstas" name="hddailyissstas" value="${requestScope.dailyActivityMst.dailyissstas}" /> 
<input type="hidden" id="hddailytarget" name="hddailytarget" value="${requestScope.dailyActivityMst.dailytarget}" /> 
<input type="hidden" id="hddailytmespnt" name="hddailytmespnt" value="${requestScope.dailyActivityMst.dailytmespnt}" /> 
<input type="hidden" id="hddailysoln" name="hddailysoln" value="${requestScope.dailyActivityMst.dailysoln}" /> 

     </form> 

