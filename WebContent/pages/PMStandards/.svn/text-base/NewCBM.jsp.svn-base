<script type="text/javascript" src="js/fileuploader.js"></script>
<script type="text/javascript">

jQuery(document).ready(function(){
    
	initialiseForm("frmcbm");

         jQuery('#submitForm').val('frmcbm');

         fillComboBox("frmcbm","cmbEquimentName","machineCombo.commonFilter");


        /* var btnName = jQuery("#hdnBtnName").val();
			jQuery("#btnnew").val(btnName);
			jQuery("#btnnew").click(function()
					{
				alert("Read From File");
				
				//processAjaxCalls("openFile.file?fileName=Cbm.xlsx", "", "", "", "", "new");	
				var uploader = new qq.FileUploader({
					  
			   	    // pass the dom node (ex. $(selector)[0] for jQuery users)
			   	    element:   document.getElementById("btnnew"),
			   	    // path to server-side upload script
			   	    action: 'file_upload.file',
			   		// additional data to send, name-value pairs
			   		params: {},
			   		numFiles:1,
			   		// validation
			   		// ex. ['jpg', 'jpeg', 'png', 'gif'] or []
			   		allowedExtensions: [],
			   		// each file size limit in bytes
			   		// this option isn't supported in all browsers
			   		sizeLimit: 5242880, // max size
			   		minSizeLimit: 1, // min size
			   		// set to true to output server response to console
			   		debug: false,
			   		// events
			   		// you can return false to abort submit
			   		onSubmit: function(id, fileName){
			   			disableUIButton("btnFileManagerInsert");
			   			if( jQuery(".qq-upload-list").length>0)
			   				jQuery(".qq-upload-list").html('');
			   		},
			   	
			   		onProgress: function(id, fileName, loaded, total){},
			   		onComplete: function(id, fileName, responseJSON){ enableUIButton("btnFileManagerInsert");},
			   		onCancel: function(id, fileName){},
			   		messages: {
			   			 sizeError: "{file} is too large, maximum file size is {sizeLimit}.",
			   	         minSizeError: "{file} is too small, minimum file size is {minSizeLimit}.",
			   	         emptyError: "{file} is empty, please select some other file."
			   		    //error messages, see qq.FileUploaderBasic for content
			   		},
			   		showMessage: function(messages){
			   			alert(messages);
			   			}
			   	});				
			});*/
			
			
          var url = jQuery('#hiddenUrl').val();
          viewGrid("cbm_input.cbm","q=2");
          var factId = jQuery("#frmcbm input[id='factory']").val();
          var sectionId = jQuery("#frmcbm input[id='section']").val();
          var cellId = jQuery("#frmcbm input[id='cell']").val();
   	      var machId = jQuery("#frmcbm input[id='machine']").val();
          var flid = jQuery("#flid").val();



   	var dataStr = "&factId=" + factId
   			+ "&sectionId=" + sectionId
   			+ "&cellId=" + cellId + "&machId="
   			+ machId+"&flid="+ flid;
   	 // alert(dataStr);
   	  loadFunctionalLocation("TrainingFBfunLocation", "functionalLoc.mom", "TrainingFBfunLocation", "frmcbm",dataStr);
   
	       /*var btnName = jQuery("#hdnBtnName").val();
		   jQuery("#btnnew").val(btnName);
	       jQuery("#btnnew").click(
	    	       function()
	    	       {
		            //alert("click");	
	    	     	processAjaxCalls("openFile.file?fileName=Kaizen_Format.pdf", "", "", "", "", "viewTemplate");
	    	     	//alert("click end");					
	    	       });
			{
				  
				  
			 
             jQuery("#cbmGrid").jqGrid('setCell',1,"txtCmdtLowerlimit","10");
			 jQuery("#cbmGrid").jqGrid('setCell',2,"txtCmdtLowerlimit","15");
			 jQuery("#cbmGrid").jqGrid('setCell',3,"txtCmdtLowerlimit","7");

	          jQuery("#cbmGrid").jqGrid('setCell',1,"txtCmdtUpperlimit","30");   
	          jQuery("#cbmGrid").jqGrid('setCell',2,"txtCmdtUpperlimit","25");
			  jQuery("#cbmGrid").jqGrid('setCell',3,"txtCmdtUpperlimit","32");
			 
			  jQuery("#cbmGrid").jqGrid('setCell',1,"txtCmdtDesirablereading","11");   
	          jQuery("#cbmGrid").jqGrid('setCell',2,"txtCmdtDesirablereading","20");
			  jQuery("#cbmGrid").jqGrid('setCell',3,"txtCmdtDesirablereading","31");
			  alert("Read From File");	
			  
			 }*/
	      // imageUpload(jQuery( "#btnnew" ),'ImageUpload.commonFilter','btnnew',"imgknowimage","imgknowImgFilename");
	       fileManagerPopUp("","ABN","frmcbm","btnfilemgr","CbmFilemgr"); 

	      
	   

  
	   	});
	
       
function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"ABN","","","");
	}
	
}
	
function viewGrid(url,filterString)
{
	
		var tableCaption = "CBM";
		
		processGridnew(url,filterString,"cbmGrid","pager",tableCaption,"doubleClickGrid","","cbmLoadComplete","","");
	    return true;
} 
function cbmLoadComplete()
{ 
	
	 var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
	 for(var j=0;j<cm.length;j++)
 	 {
	 	 //alert(cm[j].name);
     	 var zeroVal = jQuery("#cbmGrid").jqGrid('getCell',1,cm[j].name);
     	 //var check='<input type="checkbox"/>';
     	formatStr =' <input  type="checkbox" id="chksel_'+j+ '" name="chksel_'+j+ '" onclick="if(this.checked){selectData('+ j +');}else{unselectData('+j+ ');}" />';

		  if(zeroVal =='X')
		  {
			  //alert("load");	 
		  	  jQuery("#cbmGrid").jqGrid('setCell',1,cm[j].name,formatStr ,{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#ff8040'});       	
		      
	       }
 	 }
	// alert(cm);
}
function selectData(colId)
{
	//jQuery("#empEqpGrid").jqGrid('setCell',rowId,'checkempvalue','1');
	var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
	 for(var i=1;i<cm.length;i++)
	 {
		 if(colId!=i)
			 {
			 jQuery('input:checkbox[name=chksel_'+i+']').attr('checked',false);
			 //jQuery("#empEqpGrid").jqGrid('setCell',row[i],'checkempvalue','0');
		 }
		 else{
			 
			 }
	 }
	 	
}
 

 function unselectData(rowId)
 {
		var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
		for(var i=0;i<cm.length;i++)
		 {
	     if(colId!=i){
		 jQuery('input:checkbox[name=chksel_'+cm[i]+']').attr('checked',false);
		 //jQuery("#empEqpGrid").jqGrid('setCell',row[i],'checkempvalue','0');
	              }
		}
 }

 
	







/*function cbmLoadComplete(){
	var row = jQuery("#cbmGrid").jqGrid('getDataIDs');
	
	 var cm = jQuery("#cbmGrid").jqGrid("getGridParam", "colModel");
	 var green = jQuery("#cbmGrid").jqGrid('getCell',row[0],cm[0].name);
	 var yellow = jQuery("#cbmGrid").jqGrid('getCell',row[1],cm[0].name);
	 var red = jQuery("#cbmGrid").jqGrid('getCell',row[2],cm[0].name);
	 jQuery('#hdngreen').val(green);
	 jQuery('#hdnyellow').val(yellow);
	 jQuery('#hdnred').val(red);
	 //alert(green +" -- "+ yellow+" -- "+ red);	
	  jQuery("#cbmGrid").jqGrid('setCell',row[0],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#94E031'});
	  jQuery("#cbmGrid").jqGrid('setCell',row[1],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#EDED6F'});
	  jQuery("#cbmGrid").jqGrid('setCell',row[2],cm[3].name," ",{'color':'#fff','font-weight':'bold','font-size':'15px','background-color':'#E52222'});
	
}*/

function openFileOption()
{
document.getElementById("file1").click();
}

	//Excel Workbook(*.xlsx)

function allowExcel(sender) 
{
    var validExts = new Array(".xlsx", ".xls", ".csv");
    var fileExt = sender.value;
    fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
    if (validExts.indexOf(fileExt) < 0) 
        {
          alert("Invalid file selected, valid files are of " + validExts.toString() + " ");
          return false;
    }
    else return true;
}
	

</script>


<form name="frmcbm" id="frmcbm" >
<div id="wrapperRpt"  >
<div id="frmmomFuntKeyIds">
					<input type="hidden" id="factory" name="cmbMomdFactoryid" value=""></input> 
					<input type="hidden" id="section" name=cmbMomdSectionid value=""></input> 
					<input type="hidden" id="cell"    name="cmbMomdCellid" value=""></input> 
					<input type="hidden" id="machine" name="cmbMomdMachineid" value=""></input>
					<input type="hidden" id="flid" name="cmbMomsFlid" value=" "></input>
		</div>

     <div  class="easyui-paddingbfpx" id="TrainingFBfunLocation" style="width: 124%;width:109.5%\9;padding-left:0px;"></div>

   <label>Equipment</label>
   <div>
          <input class="easyui-text"id="cmbEquimentName" name="cmbEquimentName"  style="width: 200px;height: 22px;"value="" />

       <input type="file" id="file1" style="display:none" onchange="allowExcel(this);">

       <span style="padding-left:28%"><input type="button" class="easyui-button" id = "btnnew" name="btnnew" value = "Select Excel File" onclick="openFileOption();" style="height: 20px; width : 102px;" />
       </span></div>
       <div style="position:relative;">
			 <span  id="CbmFilemgr" style="position:absolute;right:-170px;right:50px\9;top:-24px;top:-28px\9;" >
     		
             </span> 
       </div> 
       


<table id="cbmGrid" ></table>
		<div id="pager"></div>
		</div>


 <input type="hidden" id="mode"/>
 <input type="hidden" id="vil" value=1/>
  <input type="hidden" id="hdnBtnName" value="XL File Format"/> 
 	<input type="hidden" id="colNo" value=""/>
 </form>