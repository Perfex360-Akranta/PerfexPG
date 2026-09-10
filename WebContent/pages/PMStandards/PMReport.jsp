<script>
jQuery(document).ready(	function(){
	initialiseForm('frmPMReport');
	var url = jQuery("#hiddenUrl").val();
	viewGrid(url,"q=2");
	jQuery("#dtnDefineZone").hide();
	jQuery("#dtnDefineZone").click(
			function() {

				LoadPopUp("divvisualsopdetpop","PMViewZone_view.prv", true,"24%", "27%", "1%", "5%","","View Zone");
				
			});
});
function viewGrid(url,filterString){
	processGridnew(url, filterString,"PMGrid", "pager", "", "", "","PMGridOnCompleteload");
}
function PMGridOnCompleteload() {

	 var row = jQuery("#PMGrid").jqGrid('getDataIDs');
	 var cm = jQuery("#PMGrid").jqGrid("getGridParam", "colModel");
	 for(var i=0;i<row.length;i++)
	 {	 
			   if(row[i]=='13' || row[i]=='3' ||row[i]=='5'  ||row[i]=='11'  ||row[i]=='9' ){
					jQuery("#PMGrid").setCell(row[i], "'CBMACTION'", "",{'background-color':'#94E031'});
	   	 	   }
			   else if(row[i]=='2' || row[i]=='7' ||row[i]=='10' || row[i]=='4' ){
					jQuery("#PMGrid").setCell(row[i], "'CBMACTION'", "",{'background-color':'#E52222'});	
	   	 	   }
			   else {
					jQuery("#PMGrid").setCell(row[i],"'CBMACTION'", " ",{'background-color':'#EDED6F'});	
			   }	
  	 	 
	 }
	 
}
function txtFormatter(id, options, rowObject) {

	return '<input type="button" class="easyui-button" id="dtnPmRpt" onclick="openFileOption()" name="dtnPmRpt" style="width:70px;height:17px;" value="...." />';
	
}	
function openFileOption()
{
document.getElementById("file1").click();
}
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
 <input type="file" id="file1" style="display:none" onchange="allowExcel(this);">
 
<form name="frmPMReport" id="frmPMReport" action=" " method="post">
<div id='wrapperRpt'>
<div>
<input type="button" class="easyui-button" id="dtnDefineZone"  name="dtnDefineZone"  value="Define Zone" />
</div>
<div style="margin-top: -15px">
	<table id="PMGrid" ><tr><td></td></tr></table>
	<div id='pager'></div>
</div>
</div>
<div id="zone" style="display: none;">

	<table border="1">
	<tr>
		
	<th style='padding:5px;border:solid 1px #c1c1c1; ' ><label>Zone</label></th>
	<th style='padding:5px;border:solid 1px #c1c1c1; ' ><label>Zone Name</label></th>
	</tr>
	<tr>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #94E031" value="" maxlength="0" />
	</td>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"  style="width:180px; height : 20px; " value=""  />
	</td>
	</tr>
	<tr>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #EDED6F" value="" maxlength="0" />
	</td>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"   style="width:180px; height : 20px; " value=""  />
	</td>
	</tr>
	<tr>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"   style="width:80px; height : 20px; background-color: #E52222" value="" maxlength="0" />
	</td>
	<td style='padding:5px;border:solid 1px #c1c1c1; ' >
	<input id="txtbtsno" name="txtbtsno"   style="width:180px; height : 20px;  " value=""  />
	</td>
	</tr>
	</table>
</div>
</form>
