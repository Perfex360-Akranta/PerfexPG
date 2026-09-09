
<script type="text/javascript">
	jQuery(document).ready(
			function() {
			
				var url = jQuery("#hiddenUrl").val();
				
				viewGrid(url,"q=2");
				
			});
	function txtFormatter(id, options, rowObject) {

	
		var id = options.rowId;
		var columnName = options.colModel.name;
		var columnNo = options.pos;
		var idval  = 'txtdpmo_';
		var txtWidth ;
		if(columnNo=="3"||columnNo=="4"||columnNo=="8"){
			txtWidth="100px;";	
		}
		else if(columnNo=="12"||columnNo=="11")
			{
			txtWidth="40px;";

			}
		else
			txtWidth="60px;";
		return '<input id='+idval+columnNo + '_'+id +' onfocus=gotFocuse(this.id);  type="text"  value="9" maxlength="3" style="width: '+txtWidth+' height:20px; text-align:right;" / >';		
	
	}
	function gotFocuse(id){
		
		numericTextBox(id);
	
	}
	function DteBoxFormatter(id, options, rowObject) {

		return '<input id="dtedpmo_'+options.rowId+'" style="width:90px; height:20px;"  class="easyui-datebox"  />';
	}

	function CmbBoxFormatter(id, options, rowObject) {

		return '<input id="cmbdpmo_'+options.rowId+'" style="width:90px;  height:20px;"  class="easyui-text" />';
	}

	function Load_Complete() {
		formatDateBoxWithGrid("dtedpmo_", 'dd-MMM-yyyy');
		fillComboBoxWithGrid("frmdpmo", "cmbdpmo_", "shift.commonFilter");
	}
	function viewGrid(url,filterString)
	{
		
		processGridnew(url,filterString, "dpmogrid","pager", "", "", "", "Load_Complete");
		
	}
</script>

	<form name="frmdpmo" id="frmdpmo" action=" " method="post">
			<div id='wrapperRpt' style=" ">
			<table id=dpmogrid>
				<tr>
					<td></td>
				</tr>
			</table>
			<div id='pager'></div>
		</div>
	</form>


