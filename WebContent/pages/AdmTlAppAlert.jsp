<!-- Author:Dhanalakshmi.R
Date:15-9-2012-->
<script type="text/javascript">

jQuery(document).ready(function(){
	viewGrid("alerts_input.alerts","?q=2");
	if(screen.width<=1024 || screen.width<=1280)
		{
		//alert('in');
		jQuery("#header").addClass("headerProp");
		}
	else 
		{
		jQuery("#header").addClass("headerProp");
		}
});
function viewGrid(url,filterString)
{
	if( validateFilterSelection(filterString))
	{
		var tableCaption = "AppAlert";
		
		processGridnew(url,filterString,"grdAppAlert","grdAppAlertpager1",tableCaption,"doubleClickGrid","","loadComplete1","","");
	
		
	}
	
}  
function loadComplete1()
{
	 if(screen.width > 1024 && screen.width<=1366)
	{
	//alert(screen.width);
	jQuery(".ui-paging-info").css("font-size","13");
	}
	 else if (screen.width<=1024)
		 {
		 jQuery(".ui-paging-info").css("font-size","9");
		 }
	}
function validateFilterSelection(filterString){
	return true;
}
function doubleClickGrid(rowid) 
{
	//alert(rowid);
	var rowData = jQuery("#grdAppAlert").jqGrid('getRowData',rowid );
	//alert(rowData.AALMKEYID);
	
	var filterString="?q=2";
	filterString+="&keyId="+rowData.AALMKEYID;
	//filterString+="&description="+rowData.Description;
	var description=rowData.Description;
	//alert(rowData.Description);
	jQuery("#hdnDesc").val(description);
	var tableCaption = "AppAlertResponse";
	//alert(filterString);
	processGridnew("alertsRes_input.alerts",filterString,"grdAppAlertResponse","grdAppAlertResponsepager1",tableCaption,"","","loadComplete","","");
	//jQuery("#grdAppAlertResponse").setGridWidth(780);
}
function loadComplete()
{
	//alert(rowid);
	var status=jQuery("#hdnStatus").val();
	//alert(status);
	if(status=="min")
		{
		if(screen.width<=1024)
			{
			//alert('in');
			jQuery("#grdAppAlertResponse").setGridWidth(840);
			}
		else
		jQuery("#grdAppAlertResponse").setGridWidth(1098);
		}
	else if (status=="max")
		{
		jQuery("#grdAppAlertResponse").setGridWidth(780);
		}
	
	
	//alert(status);
	if(status==null || status=="" || status=='' )
		{
		if(screen.width>=1360)
			{
			//alert('in');
			jQuery("#grdAppAlertResponse").setGridWidth(780);
		jQuery("#header").css("width","781px");
		jQuery("#header").css("margin-left","391px");
			}
		}
	 if(status==null || status=="" || status=='' )
		{
		//alert(status);
		if(screen.width>1024 && screen.width<=1280)
			{
		
		jQuery("#grdAppAlertResponse").setGridWidth(762);
		jQuery("#header").css("width","762px");
		jQuery("#header").css("margin-left","363px");
		}
		if(screen.width<=1024)
			{
			//alert('in');
			jQuery("#grdAppAlertResponse").setGridWidth(613);
			jQuery("#header").css("width","614px");
			jQuery("#header").css("margin-left","278px");
			
			}
		}
	if(screen.width <= 1024 && status=="max" )
	{
		jQuery("#grdAppAlertResponse").setGridWidth(575);
		jQuery("#header").css("width","576px");
		jQuery("#header").css("margin-left","323px");
		 jQuery(".ui-paging-info").css("font-size","9");
	}
	else if(screen.width>1024 && screen.width<=1280 && status=="max")
		{
		//alert('in');
		jQuery("#grdAppAlertResponse").setGridWidth(750);
		jQuery("#header").css("width","749px");
		jQuery("#header").css("margin-left","362px");
		}
	else if(screen.width>1280 && status=="max")
		{
		jQuery("#header").removeClass("headerProp");
		jQuery("#header").css("width","780px");
		jQuery("#header").css("margin-left","392px");
		}
	else if(screen.width > 1024 && screen.width<=1366 && status=="max")
		{
		//alert(screen.width);
		jQuery(".ui-paging-info").css("font-size","13");
		}
	document.getElementById('pText').innerHTML=jQuery("#hdnDesc").val();
	
	
}
function maxClicked(){
//alert('max');
jQuery("#hdnStatus").val("max");
	jQuery('#minicon').css('display','block');

	jQuery("#grdAppAlertResponse").setGridWidth(780);
	if(screen.width <= 1024)
	{
		//alert('in');
		jQuery("#grdAppAlertResponse").setGridWidth(550);
		jQuery("#grdAppAlert").setGridWidth(300);
		jQuery("#header").removeClass("headerProp");
		jQuery("#header").css("width","551px");
		jQuery("#header").css("margin-left","323px");
	
	}
	else if(screen.width > 1024 && screen.width<=1280)
	{
		jQuery("#grdAppAlertResponse").setGridWidth(760);
		jQuery("#header").css("width","761px");
		jQuery("#header").css("margin-left","362px");
	}
	
	else if(screen.width>1280 && screen.width<=1366)
		{
		
	
	jQuery("#header").css("width","780px");
	jQuery("#header").css("margin-left","392px");
	}
	jQuery('#alertDiv').css('display','block');
	jQuery('#maxicon').css('display','none');
}
function minClicked(){
//alert('min');
jQuery("#hdnStatus").val("min");
	jQuery('#minicon').css('display','none');
	//jQuery('#maxicon').css('margin-top','-200px');
	jQuery('#alertDiv').css('display','none');
	jQuery('#maxicon').css('display','block');
	jQuery("#grdAppAlertResponse").setGridWidth(1120);
	if(screen.width <= 1024)
	{
		//alert("in");
		jQuery("#grdAppAlertResponse").setGridWidth(840);
		jQuery("#grdAppAlert").setGridWidth(300);
		jQuery("#header").removeClass("headerProp");
		jQuery("#header").css("width","841px");
		jQuery("#header").css("margin-left","20px");
	}
	else if(screen.width > 1024 && screen.width<=1366)
	{
		jQuery("#grdAppAlertResponse").setGridWidth(1100);
		jQuery("#header").css("width","1100px");
		jQuery("#header").css("margin-left","20px");
	}
	
	else
		{
	jQuery("#header").css("width","1122px");
	jQuery("#header").css("margin-left","20px");
	jquery("#header").css("border-style","outset");}
	
}
</script>
<style>
.headerProp
{
height:15px;
width:577px;
margin-left:278px;
}
.headerProp1
{
height:15px;
width:577px;
margin-left:363px;
}
</style>


<form name="frmAppAlert" id="frmAppAlert" >

<div id="wrapperRpt">
<div id="header" style=""><p id="pText" style="background-color:#C0D9E8;font-size:11px;font-weight:bold;text-align:center"></p></div>
<table>
	<tr>
	<td id="alertDiv">
		<table id="grdAppAlert" ></table>
		<div id="grdAppAlertpager1"></div></td>
	
<!--	<input type="button" class="easyui-button" value="min " id="btnadd" onclick="minClicked();"/>-->
	<td valign="top"><div id="minicon" style="float:left;">
		<img id="min" src="images/layout_button_left.gif" onclick="minClicked();"/>
		
	</div>
	<div style="float:left;display:none;" id="maxicon">
					<img src="images/layout_button_right.gif" id="max"  onclick="maxClicked();"/>
				</div>
				</td>
				<td valign="top">
	
		<table id="grdAppAlertResponse"  ></table>
		<div id="grdAppAlertResponsepager1"></div>
	</td>
	</tr>
	</table>
</div>
<input type="hidden" id="hdnDesc" name="hdnDesc" value=""/>
<input type="hidden" id="hdnStatus" name="hdnStatus" value=""/>
</form> 