<script type="text/javascript">	
	jQuery(document).ready(function(){	
		var dtlId = jQuery("#hdndtlId").val();
		var dataStr = '?q=2&dtlId='+dtlId;
		processGridnew('history_input.prpo',dataStr,"historyGrid","","","","","historyLoad","historyError");
	});
	function historyLoad()
	{
		if(screen.width <=1300)
		{			
			jQuery( "#historyGrid" ).setGridHeight('70%');
			jQuery( "#historyGrid" ).setGridWidth('100%');			
		}
	}
</script>	
<form id="frmHistory" name="frmHistory">
<input type="hidden" id="hdndtlId" name="hdndtlId" value="${requestScope.dtlId}"/>
	<div  style="padding-left:2px;float:left;">			
		<table id="historyGrid" style="float: left;"></table>
<!--	<div id="mstPlanActualPager"></div>-->
	</div>
</form>   