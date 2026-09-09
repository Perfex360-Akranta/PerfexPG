<!-- Created By :Siddharth.A -->	

<script type="text/javascript">
jQuery(document).ready(function(){	
	 var url = jQuery('#hiddenUrl').val();
	 //viewGrid(url,"?q=");
    
	 //alert("hdnFormmode"+url);
     var btnName = jQuery("#hdnBtnName").val();
     setLoadFormCallBackFrmId("frmOplMainGrid");
     invokeAfterLoadFormCallBack();
     //viewGrid(url,"?q=");
    //viewGrid(url,dataStr);
    //viewGrid(url,dataStr);
    //viewGrid(unescape(prevDataUrl),"&q=1&flid="+flid);//
	 //alert("btnName"+btnName);
	 jQuery("#btnViewTemplate").val(btnName);
	 jQuery("#btnViewTemplate").click(function(){
		
	 processAjaxCalls("openFile.file?fileName=OPL.xls", "", "", "", "", "viewTemplate");		

	 });
     
     //alert(" Inside Ready Function "+jQuery("#hdnmode").val());
    
	jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));
		//if(url.indexOf('&bdmmode') > 0 )
		//{	
			jQuery('#btnNewBkng').css("display","block");
			jQuery('.ui-jqgrid ui-widget ui-widget-content ui-corner-all').css("height",450);
		//}

		var mode=jQuery("#hdnmode").val();
		if(mode=="view"){//alert(1);
		   jQuery('#btnNewBkng').attr('disabled','disabled');
        }
	         
		var  prevDataUrl = jQuery('#hdnPrevDataUrl').val();
	    var flid=jQuery('#hdnLoginFlid').val();
		var modifymode=jQuery('#hdnModifymode').val();
	     
		if( prevDataUrl == null || prevDataUrl.length <=0)	{
			
			if(modifymode.trim().length>0){
				var dataStr = "?q=1&firstClick=Y&mode=modify&flid="+flid;
				//viewGrid(url,dataStr);
			}else
			{   
				var dataStr = "&firstClick=Y&flid="+flid;
				//viewGrid(url,dataStr);
			}	
		
		
		}	
		else{
		     // viewGrid(unescape(prevDataUrl),"&q=1&flid="+flid);//
		}
});

function frmOplMainGrid_afterLoadCallBack(){
	toggleCommonFilter(); 

	}
function viewGrid(url,filterString)
{
   
	if( validateFilterSelection(filterString))
	{
		//alert(" validateFilterSelection :: "+filterString);  jQuery('#hdnrespnlity').val()
		
		var tableCaption = "OPL Modification";
		var prepardby=jQuery('#hdnrespnlity').val();
		var formmode=jQuery('#hdnFormmode').val();
		var modifyMode=jQuery('#hdnModifymode').val();
		if(prepardby.trim().length>0)
	         filterString+="&prepardby="+prepardby;
        
        if(formmode.trim().length>0 && formmode=="Oplmatrix")
        	filterString+="&Formmode="+jQuery('#hdnFormmode').val();
        
        //alert(url+" filterString :: "+filterString);
        processGridnew(url,filterString,"oplGrid","pager",tableCaption,"doubleClickGrid","","oplGridOnLoad");
		return true;
	}	
}

function validateFilterSelection(filterString){
	return  true;
}

function oplGridOnLoad()
{
	//alert("onload");
	//jQuery('#oplGrid').jqGrid('setGridParam', {height: 405});	

}
function doubleClickGrid(rowid)
{
	var forwardData = jQuery('#hiddenUrl').val();
	 //alert(" view:::forwardData "+forwardData);
    //if(forwardData=="modify_input.opl")
    
	var url = jQuery("#oplGrid").jqGrid('getGridParam', 'url');
	//alert(url);
	url = url.replace('getData','input');
	url = escape(url); 
	var oplmode = jQuery("#hdnoplmode").val();
	//alert(" oplmode :: "+oplmode);
	//alert(" Modification "+jQuery('#hdnbdmode').val());

	//if(forwardData=="oplVw_input.opl?mod=view")//
	  //{//alert("view");
	  if(forwardData.indexOf("oplVw_input.opl?mod=view") >=0)
		{ 
		 // alert("alert 1");		
	//		navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=view&filterButton=false","OPL View",null,{"filterString":url});
			LoadPopUp(
					  "oplView",
					  "create_input.opl?oplKeyId="+rowid+"&mod=view&filterButton=false",
					  true,"95%","90%","3%","1%","popup_callback()","OPL View","",true,true
					);}
	 else if(jQuery('#hdnbdmode').val()!=null && jQuery('#hdnbdmode').val()>0)
		{// alert("alert 2");	
		 navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=modify&bdmmode=bdmmode&closeOnSave=true&filterButton=false","OPL Modification",null,{"filterString":url});
		}	
	 else if (jQuery('#hdnbdmode').val()!=null && oplmode.trim().length > 0 ){ 
		   var Oplmatrix=jQuery('#hdnFormmode').val();
		   var formname=null;
		   var approval=null;
		   if(Oplmatrix.trim().length>0){
			   formname="Opl Matrix Updation";
			   approval="OPLMATRIX";
			}
		   else{
			  formname="Approval";
			  approval="APPROVAL";
			}
	//	   alert("alert 3");	
		   //alert(" Checking here :: "+approval);
	       //navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod="+oplmode+"&closeOnSave=true&filterButton=false&Mode="+approval,formname,null,{"filterString":url});
	       LoadPopUp(
					  "OPL",
					  "create_input.opl?oplKeyId="+rowid+"&mod="+oplmode+"&closeOnSave=true&filterButton=false&Mode="+approval,
					  true,"95%","90%","3%","1%","popup_callback()",formname,"",true,true
					);}

	 else{
		// alert("alert 4");	//&closeOnSave=false
	   
		//LoadPopUp("oplModify","create_input.opl?oplKeyId="+rowid+"&mod=modify&filterButton=false",true,"95%","90%","3%","1%","popup_callback()","OPL Modify","",true,true);
		LoadPopUp(
				  "oplModify",
				  "create_input.opl?oplKeyId="+rowid+"&mod=modify&filterButton=false&closeOnSave=true",
				  true,"95%","90%","3%","1%","popup_callback()","OPL Modify","",true,true
				);

		//	 navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=modify&closeOnSave=true&filterButton=false","OPL Modification",null,{"filterString":url});		
		 
}}
	
//Updates the specific row in oplGrid after OPL approval/rejection
//Mirrors AbnormalityRowUpdate pattern exactly
function OplRowUpdate(rowId, updatedRow) {
if (!updatedRow || typeof updatedRow !== "string") {
   return;
}
var rowArray = updatedRow.split(",&!@$", -1);
var rowData = {
   TXTOPLNO:          rowArray[0],
   OPLDATE:           rowArray[1],
   THEME:             rowArray[2],
   CLASSIFICATION:    rowArray[3],
   OPLTYPE:           rowArray[4],
   PREPAREDBY:        rowArray[5],
   STATUS:            rowArray[6],
   FUNCTIONALLOCATION:rowArray[7],
   EMPLOYEETYPE:      rowArray[8],
   FLID:              rowArray[9],
   ORDERDATE:         rowArray[10]
};
jQuery("#oplGrid").jqGrid('setRowData', rowId, rowData);
jQuery("#oplGrid").jqGrid('setCell', rowId, "STATUS", "",
   {'color':'#000', 'font-size':'12px', 'background-color':'green'}
);
}

//Called by processAjaxCalls after fetching the updated OPL row
function OPLAPPROVE_RowUpdateCallback(result) {
if (result && result.updatedRow && result.rowId) {
   OplRowUpdate(result.rowId, result.updatedRow);
}
}

		jQuery('#btnNewBkng').click(function(){
			var oplmode = jQuery("#hdnoplmode").val();
			//alert(" oplmode :: "+oplmode);
			var hdnModifymode=jQuery('#hdnModifymode').val();
			//alert(" hdnModifymode :: "+hdnModifymode);
		    var mode =jQuery('#hdnmode').val();
		    //alert(" mode :: "+mode);
			var forwardData = jQuery('#hiddenUrl').val();
			var factoryId = forwardData.substring(forwardData.indexOf("factoryId")+"factoryId".length+1 );
			if(forwardData.indexOf("factoryId")>0)
				{
				navigateToNextForm("create_input.opl?&factoryId"+factoryId+"&Mode="+hdnModifymode+"&closeOnSave=true&filterButton=false");}
			else 
				{ 
				navigateToNextForm("create_input.opl?&filterButton=false"+"&Mode="+hdnModifymode+"&Modes="+oplmode);}
			
		});
		
	

		
</script>
<form>
<div id="wrapperRpt">
  <div id="">
	
	<div class="">
	<table>
	<tr style=" height : 2px;">
	<td>
	
		<!-- <div  style="padding-left:px;margin-top:-28px;">
		<input type="button" class="easyui-button" id="btnNewBkng" value="New Booking" style="display: none;"/>
		</div> -->
		</td>
		<td style="padding-left:10px; width : 11px;">
		<input type="button" class="easyui-button" id="btnViewTemplate"	name="btnViewTemplate" value="ViewFormat" style=" width : 90px;display:none;"/>
		</td>
		<td style="padding-left:0px;">
		<div style="margin-top:-20px;margin-left: -17px">
		<label>
		Double Click the Data row to view the Details
		</label>
		</div>
		</td>
	</tr>

  </table>		
		<div class="clear"></div>
			 <div style=""> 
					 <table id="oplGrid" style="width:100%"><tr><td/></tr></table>
					 <div id="pager"></div>
			</div>
	</div>
 </div>
	<div id="viewOpl"></div>
	<input type="hidden" id="hdnbdmode" />
	<input type="hidden" id="hdnFormmode" value="${requestScope.Formmode}"  />
	<input type="hidden" id="hdnModifymode" value="${requestScope.Modifymode}"  />
	<input type="hidden" id="hdnoplmode" value="${requestScope.oplmode}"  />
	<input type="hidden" id="hdnmode" name="hdnmode" value="${requestScope.mode}" />
	<input type="hidden" id="hdnPrevDataUrl" name="hdnPrevDataUrl" value="${requestScope.filterStr}" />
	<input type="hidden" id="hdnrespnlity" name="hdnrespnlity" value="${requestScope.respnlity}" />
</div>
</form>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Excel View" />

