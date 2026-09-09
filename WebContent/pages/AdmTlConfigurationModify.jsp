<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %> --%>
<script src="../js/jquery.simple-color.min.js" type="text/javascript"></script>
<script src="js/jquery.simple-color.js" type="text/javascript"></script>
<script type="text/javascript">

jQuery(document).ready(function($) {
formatDateBox('dteCnfmFromdate','dd-MMM-yyyy');
jQuery('#frmConfigModify.easyui-text').css('text-transform','uppercase');

var setvalue=jQuery("#hdnSettingVal").val();
//alert(setvalue);
var setColor=jQuery("#hdnSetClr").val();
//alert(setColor);
if(setColor=="C" )
	{
	//alert('in color');
	jQuery("#colorDiv").css("display","block");
	var color=jQuery("#hdnSetColoring").val();
	//alert(color);
	jQuery("#txtCnfmSettingvalue").css('background-color','#'+color);
	}
else if(setColor=="V")
	{
var uom=jQuery("#hdnUom").val();
//jQuery("#dteCnfmFromdate").val(fromDate);
jQuery("#txtCnfmSettingvalue").val(setvalue);
//alert(uom);
if(uom==null || uom=="" || uom=='')
	{
	}
else if(uom=="no"||uom=="nos"||uom=="count"||uom=="days"||uom=="day(s)"||uom=="min(s)"||uom=="week"||uom=="months"||uom=="min"||uom=="hours")
	{
	
	numericTextBox('txtCnfmSettingvalue');
	//jQuery("#txtCnfmSettingvalue").attr("maxlength","1");
	//formatDateBox('txtCnfmSettingvalue','dd-MMM-yyyy');
	}
	//alert("v");
	}
else if(setColor=="D")
	{
	//alert('d');
	formatDateBox('txtCnfmSettingvalue','dd-MMM-yyyy');
	//jQuery("#dteCnfmFromdate").val(fromDate);
	jQuery("#txtCnfmSettingvalue").val(setvalue);
	}
else if(setColor=="T")
	{
	//alert('t');
	var uom=jQuery("#hdnUom").val();
	//jQuery("#dteCnfmFromdate").val(fromDate);
	jQuery("#txtCnfmSettingvalue").val(setvalue);
	//alert(uom);
	if(uom==null || uom=="" || uom=='')
		{
		}
	else if(uom=="boolean")
		{
		jQuery("#txtCnfmSettingvalue").attr("maxlength","1");
		
		}
	}
$('.simple_color').simpleColor();
});

jQuery('#btnSaveData').click(function(){
	
	//alert(hexCode);
	
	var filterstring="?q=2";
	var Fromdate=getFieldValue("dteCnfmFromdate","frmConfigModify");
	//alert(Fromdate);
	if(Fromdate == " " ||  Fromdate == "" || Fromdate == null){
		alert('Select Date');
		return false;
		}
	
	filterstring+="&Fromdate="+Fromdate;
	
	
	var setColor=jQuery("#hdnSetClr").val();
	//alert(setColor);
	if(setColor=="C" || setColor=="c")
		{
		var colorCode=jQuery("#txtCnfmSettingvalue").css("background-color");
		//alert(colorCode);
		var hexCode=colorToHex(colorCode);
		if(hexCode == " " ||  hexCode == "" || hexCode == null){}
		else
			{
			hexCode=hexCode.substr(1);
			//alert(hexCode);
			filterstring+="&Settingvalue="+hexCode+"&FromColor=true";
			}
		}
	
	else
	{ 
		var Settingvalue=jQuery("#txtCnfmSettingvalue").val();
		if (Settingvalue==null || Settingvalue== " " || Settingvalue== "" )
		{
		alert('Enter Setting Value');
		return false;
		}
	else
	filterstring+="&Settingvalue="+Settingvalue+"&FromColor=false";
	}
	//alert(filterstring);
	
		
		//alert(filterstring);
	filterstring+="&fromsave=Y";	
	filterstring += '&previousDate='+jQuery("#hdnDate").val();
	saveForm('frmConfigModify','config_update.conFig'+filterstring);
		
	
	});
function frmConfigModify_successsCallback(result)
{
	//alert('in success callBack'); 
	
	closePopUpDialoge("divConfiguration");
	}

function colorToHex(color) {
    if (color.substr(0, 1) === '#') {
        return color;
    }
    var digits = /(.*?)rgb\((\d+), (\d+), (\d+)\)/.exec(color);
    
    var red = parseInt(digits[2]);
    var green = parseInt(digits[3]);
    var blue = parseInt(digits[4]);
    
    var rgb = blue | (green << 8) | (red << 16);
    return digits[1] + '#' + rgb.toString(16);
};
</script>
<style>

</style>
<form id="frmConfigModify" name="frmConfigModify">
<div style="padding-left:80px"><label class="mandatory-lbl">From Date</label></div>
<div style="padding-left:80px">
 <input id="dteCnfmFromdate" name="dteCnfmFromdate" class="easyui-datebox" clear="false"  style="width:160px;" value="${requestScope.fromDate}"  /> 
</div>
<div style="padding-left:80px;margin-top:5px" ><label class="mandatory-lbl">Setting Value(${requestScope.uom})</label></div>
<div style="padding-left:80px" ><input class="easyui-text" style="width:160px;" id="txtCnfmSettingvalue" name="txtCnfmSettingvalue" value=""/></div>
<div id="colorDiv" style='margin-top:12;margin-left:40;display:none'>

<input class="simple_color" value="" style="display: none;">
</div>
<!--<div id="simpleColorContainer">
<div id="simpleColorDisplay" style="background-color: rgb(204, 51, 51); border: 1px solid rgb(0, 0, 0); width: 115px; height: 20px; cursor: pointer;"></div>
<input id="simpleColorSelectButton " type="button" value="Select" style="display: inline;">
<input id="simpleColorCancelButton " type="button" value="Cancel" style="display: none;"></div>

-->
<div align="center"  style="margin-top:25px"><input class="easyui-button" style="width:45px;height:25px;" id="btnSaveData" name="btnSaveData" value="Save"/></div>
<input type="hidden" id="hdnSetClr" name="hdnSetClr" value="${requestScope.setColorConfig}">
<input type="hidden" id="hdnUom" name="hdnUom" value="${requestScope.uom}">
<input type="hidden" id="hdnDate" value="${requestScope.fromDate}"/>
<input type="hidden" id="hdnSettingVal" value="${requestScope.settingValue}"/>
<input type="hidden" id="hdnSetColoring" value="${requestScope.setColorfrom}"/>

</form>