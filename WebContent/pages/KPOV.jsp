<script type="text/javascript">

jQuery(document).ready(function(){

	initialiseForm("frmKpov");
	
         jQuery('#submitForm').val('frmKpov');
         fillComboBox("frmKpov","cmbSopm","emplo.commonFilter" );
         var url = jQuery('#hiddenUrl').val();
         viewGrid("kpov_input.kpov","q=2");
         jQuery("#btnnew").click(function(){
     		navigateToNextForm("");
     	});
     });
   
         function viewGrid(url,filterString)
     	{
    
     			var tableCaption = "Kpov";
     			
     			 var po = jQuery('#frmKpov').val();
     			 
     			processGridnew(url,filterString,"KpovGrid?po="+po,"pager",tableCaption,"doubleClickGrid","","loadComplete","","");

     			processGridnew(url,filterString,"KpovGrid","pager",tableCaption,"doubleClickGrid","","loadComplete","","");
     		    return true;
     			
     		
     	} 
         function checkBoxFormatter(id, options, rowObject) {
           
     		return '<input id="chexkKpov_'+options.rowId+'"  type="checkbox" style=" height:20px;"   />';
   
      }  


 
    function loadComplete(){
    	var row = jQuery("#KpovGrid").jqGrid('getDataIDs');
		
		 var cm = jQuery("#KpovGrid").jqGrid("getGridParam", "colModel");
		 for(var i=0;i<row.length;i++)
		 {
			 for(var j=0;j<cm.length;j++)
	     	 {var zeroVal = jQuery("#KpovGrid").jqGrid('getCell',row[i],cm[j].name);
	     	
				  if(zeroVal =='Cobb'){
					//  alert("zeroVal"+zeroVal);
				  		jQuery("#KpovGrid").jqGrid('setCell',row[i],cm[j].name,"Cobb",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#F7DBCF'});
				  }

				  		else if(zeroVal =='SR'){
					  		jQuery("#KpovGrid").jqGrid('setCell',row[i],cm[j].name,"SR",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#F7DBCF'});
					  }
					 
				         else if(zeroVal =='MG load'){
					  		jQuery("#KpovGrid").jqGrid('setCell',row[i],cm[j].name,"MG LOAD",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#F7DBCF'});
					  }	
				         else if(zeroVal =='flat boxes'){
						  		jQuery("#KpovGrid").jqGrid('setCell',row[i],cm[j].name,"FLAT BOXES",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#F7DBCF'});
						  }	
				         else if(zeroVal =='Bulk'){
						  		jQuery("#KpovGrid").jqGrid('setCell',row[i],cm[j].name,"BULK",{'color':'#323D3C','font-weight':'normal','font-size':'10px','background-color':'#F7DBCF'});
						  }	
	     				  		
				  }
    }
    }


</script>
<form name="frmKpov" id="frmKpov" >
<div id="wrapper">
<div style="margin-left:303px">
 <label  class ="mandatory-lbl" style="padding-top:20px" >KPOV </label>
<div class="easyui-paddingbfpx">	
<input id="cmbSopm" name="cmbSopm" class="easyui-combobox"  value="" style="width:350px;" />
<input type="button" class="easyui-button" style="width:50px;" id="btnView" name="btnView" value="..."/>
<input type="button" class="easyui-button" style="width:50px;" id="btnView" name="btnView" value="View"/>
</div>
</div>
<div style="float:left;margin-left:25%">
<table id="KpovGrid"  >
</table>
		<div id="pager"></div>
		
		</div>
	 <input type="hidden" id="mode"/>
	 </div>
</form>