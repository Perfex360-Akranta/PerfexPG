<script type="text/javascript">
jQuery(document).ready(function(){
	
}); 
function loadSteps(url,id){
	if(jQuery('.clsLi').hasClass('LIselected'))
	 	  jQuery('.clsLi').removeClass('LIselected');
	 	  jQuery('#'+id).addClass('LIselected');
	var urlid=url+"?q=2&loadContentDivId=divSteps&mainForm=true";
	navigateToNextForm(urlid,"Form");
}
</script>
<style>

#ulFmeaSteps li:hover {
	background-color:#539DF4;
		line-height:30px; }
	
.LIselected{
		background-color:#A6DDE4;
		line-height:30px;
	}
</style>
<form id="frmFMEAformat" name="frmFMEAformat">
	<div id="wrapper" style="margin-top: 0;margin-left: 0%;">
		<div>
			<div  style="width: 15%;float:left;background-color: #FFFFFF; height: 467px" >
				<ul id="ulFmeaSteps" style="padding:10;line-height: 30px">
					<li class="clsLi" id="lidesign"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick="loadSteps('FMEADesign_input.fmeaf','lidesign')">Design FMEA</li>
					<li class="clsLi" id="liprocess"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick="loadSteps('FMEAProcess_input.fmeaf','liprocess')">Process FMEA</li>
					<li class="clsLi" id="liEquipment"  style="cursor: pointer;font-size: 14px;font-weight: bold;" onclick="loadSteps('FMEAEquipment_input.fmeaf','liEquipment')">Equipment FMEA</li>
					
				</ul>
			</div>
			<div id="divSteps" style="width:1000px;max-width:90%;height:467px;margin-top:2px;border: solid 1px #c1c1c1;overflow: auto;float: right;" >
			</div>
		</div>
	</div>
</form>
