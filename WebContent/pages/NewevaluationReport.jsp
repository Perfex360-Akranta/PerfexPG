<script type="text/javascript">
	jQuery(document).ready(function(){

		
			processGridnew("newEvaluationrpt_input.asps","&q=2","newEvaluationGrid","newEvaluationpager","","docDoubleClick");
			jQuery('#btnAdd').click(function(){
			var type = jQuery("#hdntype").val();
			//navigateToNextForm("BenefitTrackingModify_input.bfttrc?q=2&mode=create&type="+type,"Benefit Tracking");
		});		
	});

	function txtFormatter(id, options, rowObject) {
		var id = options.rowId;
		var columnName = options.colModel.name;
		var columnNo = options.pos;
		var gid=options.gid;
	//alert("options:::::"+Object.keys(options));
  //alert("colname::colno"+columnName+"::::"+columnNo);
		
	return '<input type="button" id="btnnew_'+id+'_'+columnNo+'" name="btnnew_'+id+'_'+columnNo+'" onclick="Assessment(this.id)"  style="width:50px;  height:15px;"   class="easyui-button" value="..."/>';
	 
	  
	}	

	function Assessment(id)
	{  
		navigateToNextForm("previousEvaluation_input.asps?q=2&mode=modify","Assessment");

    }

	    
</script>
<form id="frmassesment">	
	<div id="wrapperRpt">
		<div style="padding-left:0%">
			<input type="hidden" class="easyui-button" id="btnAdd" name="btnAdd" value="New"  style=" width : 49px;"/>
		
		</div>
		<div class="clear" ></div>
		<div style="margin-top:-25px\9;">
		<label class="notes"   style="font-weight: bold; padding-left:0px; " > ${requestScope.DoubleClick}</label>
		<div style="margin-top:6px;margin-top:10px\9;">
		<table id="newEvaluationGrid" ></table>
		<div id="newEvaluationpager"></div>
		</div>
		
		</div>
	</div>	
</form>