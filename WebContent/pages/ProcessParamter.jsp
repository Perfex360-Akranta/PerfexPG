
<html>
<head>

<script>
jQuery(document).ready(function(){
	
	initialiseForm('frmMom');
	processGridnew("CriticalProcessParameter_input.crpp","q=2","achievementgrid","pager"," ","","","numericTextBox");

	
});
function formatterTxtJHLevel(id, options, rowObject)
{	
	var rowId = options.rowId;
    //return '<input id="txtformat"'+rowId+' name="txtMaxPoint_"'+rowId+'   type="text"  value="'+id+'" style="width:375px;text-align:left"/>';
	return '<input id="txtMaxPoint_"'+rowId+' name="txtMaxPoint_"'+rowId+'   type="text"  value="'+id+'" maxlength="3" style="width:100px;text-align:right"/>';
}

function numericTextBox(id)
{
		jQuery('#'+id).keydown(function(event) {
        // Allow: backspace, delete, tab and escape
		var cVal = jQuery(this).val();
		
		if(   (  event.keyCode === 190 || event.keyCode == 110) )
		{
			if(( cVal != undefined 
            		&& cVal != null && cVal.indexOf(".") > -1 ))
				event.preventDefault();
            		
		}
		else if(event.keyCode === 46 ||  event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 110 ||
		
             // Allow: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39))  {
                 // let it happen, don't do anything
    		
                 return;
        }
        
        else {
            // Ensure that it is a number and stop the keypress
        	
            if(  ((event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )  ) ){
            	
            	event.preventDefault();
            }   
        }
    });
}

</script>
</head>
<body>
<form name="frmMom" id="frmMom">
<div id='WrapperRpt' >
<table id='achievementgrid'>
	<tr>
		<td></td>
	</tr>
</table>
<div id='pager'></div>

</div>

</form>

</body>
</html>