<script type="text/javascript">
	jQuery(document).ready(function(){				
		initialiseForm('frmPcsResult');
		var dataString = jQuery('#hdnDatastr').val();
		//alert(dataString);
		processGridnew('pcsResultGrid_view.pcs',dataString,"pcsResultGrid","pcsResultPager","Production Log - Results","pcsResultdblClick","","pcsResultGrid_loadComplete","pcsResultGridError");
	});
	
	function pcsResultGrid_loadComplete() { 
		hideJqGridRow('pcsResultGrid', '1');
	}
	
</script> 
<input type="hidden" id="hdnDatastr" name="hdnDatastr" value="${requestScope.dataStr}"  ></input>
<form id="frmPcsResult" >
<!--  <div class="main-header" style="float: center;width: 95%">Production Log - Results</div>-->
<!--<div style="width:70%;margin-left:5%; ">-->
<!--	<div style="" align="center">-->
			<div style="float: center;padding-right: 40px;margin-top:5px;">
<!--			<table id="pcsResultGrid" width="200px" style="float: center;"></table> -->
			</div>
		<div id="pcsResultPager" style="float: center;"></div>
<!--	</div>-->
<!--</div>-->
	<div style="" class="pcsResultGridDiv">			
			<table id="pcsResultGrid" style="float: left: ;"></table>
	</div>
</form>