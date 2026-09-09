<!-- Created By :Siddharth.A -->	

<script type="text/javascript">
jQuery(document).ready(function(){	
	 var url = jQuery('#hiddenUrl').val();
	 viewGrid(url,"?q=");
     var btnName = jQuery("#hdnBtnName").val();
     //setLoadFormCallBackFrmId("frmOplMainGrid");
 	 var userkeyid = jQuery('#hdnempId').val();;
	 jQuery("#btnViewTemplate").val(btnName);
	 jQuery("#btnViewTemplate").click(function(){
	 processAjaxCalls("openFile.file?fileName=OPL.xls", "", "", "", "", "viewTemplate");		

	 });
     
    
	jQuery('#hdnbdmode').val(url.indexOf('&bdmmode'));
			jQuery('#btnNewBkng').css("display","block");
			jQuery('.ui-jqgrid ui-widget ui-widget-content ui-corner-all').css("height",450);
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
			}else
			{   
				var dataStr = "&firstClick=Y&flid="+flid;
			}	
		}	
		else{
		 
		}
});

/* function frmOplMainGrid_afterLoadCallBack(){
	toggleCommonFilter(); 

	} */
function viewGrid(url,filterString)
{
   
	if( validateFilterSelection(filterString))
	{		
		var tableCaption = "OPL Modification";
		var prepardby=jQuery('#hdnrespnlity').val();
		var formmode=jQuery('#hdnFormmode').val();
		var modifyMode=jQuery('#hdnModifymode').val();
		if(prepardby.trim().length>0)
	         filterString+="&prepardby="+prepardby;
        
        if(formmode.trim().length>0 && formmode=="Oplmatrix")
        	filterString+="&Formmode="+jQuery('#hdnFormmode').val();
        processGridnew(url,filterString,"oplGrid","pager",tableCaption,"doubleClickGrid","","oplGridOnLoad");
		return true;
	}	
}

function validateFilterSelection(filterString){
	return  true;
}

function oplGridOnLoad()
{

}
function doubleClickGrid(rowid)
{
	var forwardData = jQuery('#hiddenUrl').val();
	var url = jQuery("#oplGrid").jqGrid('getGridParam', 'url');
	url = url.replace('getData','input');
	url = escape(url); 
	var oplmode = jQuery("#hdnoplmode").val();
//	alert(" oplmode :: "+oplmode);
	  if(forwardData.indexOf("individualoplVw_input.opl?mod=view") >=0)
		{
			navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=view&filterButton=false","Individual-OPL View",null,{"filterString":url});}
	 else if(jQuery('#hdnbdmode').val()!=null && jQuery('#hdnbdmode').val()>0)
		{
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
	       navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod="+oplmode+"&closeOnSave=true&filterButton=false&Mode="+approval,formname,null,{"filterString":url});
	 }
	 else{navigateToNextForm("create_input.opl?oplKeyId="+rowid+"&mod=modify&closeOnSave=true&filterButton=false","OPL Modification",null,{"filterString":url});		
}}

		jQuery('#btnNewBkng').click(function(){
			var oplmode = jQuery("#hdnoplmode").val();
			var hdnModifymode=jQuery('#hdnModifymode').val();
		    var mode =jQuery('#hdnmode').val();
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
	<input type="hidden" id="hdnempId" name="hdnempId" value="${requestScope.empId}">
</div>
</form>
<input type="hidden" class="easyui-button" id="hdnBtnName"	name="hdnBtnName" value="Excel View" />

