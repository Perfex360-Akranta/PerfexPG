<script type="text/javascript">

jQuery(document).ready(function(){
  // alert(1);
	initialiseForm("frmMachine");
	
         jQuery('#submitForm').val('frmMachine');
       //  fillComboBox("frmKpov","cmbSopm","emplo.commonFilter" );
         var url = jQuery('#hiddenUrl').val();
         viewGrid("Machine_input.mac","q=2");
         jQuery("#btnnew").click(function(){
     		navigateToNextForm("");
     	});
     });
   
         function viewGrid(url,filterString)
     	{
    
     			var tableCaption = "Machine";
     			
     		//	 var po = jQuery('#frmKpov').val();
     			 
     			//processGridnew(url,filterString,"KpovGrid?po="+po,"pager",tableCaption,"doubleClickGrid","","loadComplete","","");

     			processGridnew(url,filterString,"MachineGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
     		    return true;
     			
     		
     	} 
     	</script>
     	
<form name="frmMachine" id="frmMachine">
<div id="wrapper">
<div style="float:left;">
<table id="MachineGrid"  >
</table>
		<div id="pager"></div>
		
		</div>
	 <input type="hidden" id="mode"/>
	 </div>

</form>