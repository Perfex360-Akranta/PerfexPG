  <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>

.grid
{
  margin-top:12px;
  margin-top:2px\9;
  width:400px;
  margin-left:40px;
 
 
}
.table 
{
 float:left\9;
 margin-left:10px\9;
 margin-top:10px;
}

.maindiv{
  width:1100px;
  border: 1px solid #a4a4a4;
  text-align: center;
  height:900px;
  margin-left:0px;
}
</style>
<script>
jQuery(document).ready(function(){
	
	fillComboBox("frmInstrumentation","cmbManufacturer","employee.commonFilter");
	fillComboBox("frmInstrumentation","cmbProcess","process.commonFilter");

	formatDateBox('manufdteDate','dd-MMM-yyyy');
	formatDateBox('instaldteDate','dd-MMM-yyyy');
	formatDateBox('testoperdteDate','dd-MMM-yyyy');
	formatDateBox('startupdteDate','dd-MMM-yyyy');
	
	var factId = jQuery("#frmInstrumentation input[id='factory']").val();
    var sectionId = jQuery("#frmInstrumentation input[id='section']").val();
    var cellId = jQuery("#frmInstrumentation input[id='cell']").val();
    var machId = jQuery("#frmInstrumentation input[id='machine']").val();
    var dataStr = "&factId="+factId+"&sectionId="+sectionId+"&cellId="+cellId+"&machId="+machId;	  					
    loadFunctionalLocation("instrumentfunLocation","functionalLoc.brdn","instrumentfunLocationValues","frmInstrumentation",dataStr);

    processGridnew("Specification_input.eqplg","?q=2","spectableid","specpagerid"," ","");
    processGridnew("Maintenance_input.eqplg","?q=2","mantcetableid","mantcepagerid"," ","");
    processGridnew("Callibration_input.eqplg","?q=2","callbtntableid","callbtnpagerid"," ","");

    fileManagerPopUp("","INS","frmInstrumentation","btnfilemgr","InstrFilemgr");
    
    if(screen.width <= 1024){
        
         jQuery('.maindiv').css('width','870');
         jQuery('.maindiv').css('margin-left','0');
         jQuery('.sub-header').css('width','89.2%');
         if (navigator.userAgent.match(/MSIE (\d+(?:\.\d+)+(?:b\d*)?)/) == "MSIE 8.0,8.0")
         jQuery('.grid').css('margin-left','1\9');
          
         
     	}	
    	else{
    		
    	}

	
});
function btnfilemgr_click()
{
	//alert("11");
   // var documentNo =jQuery("#hdnabnkeyID").val();
   
	if(1 != null && 1 != '')
		{
		fileManagerPopUp(1,"INS","","","");
	}
	
}

</script>
<form name="frmInstrumentation" id="frmInstrumentation" >
<div id="wrapperRpt">

<div class="maindiv" >

<table  class="table">
				<tr>
					<td colspan="4">

							<div id="frminstrumentFuntKeyIds" >
							
								<input type="hidden" id="factory" name="factory"  value="" ></input>
								<input type="hidden" id="section" name="section"  value=""></input>
								<input type="hidden" id="cell"    name="cell"     value=""></input>
								<input type="hidden" id="machine" name="machine"  value=""></input>
							
							</div>
						
						<div id="instrumentfunLocation" style="width:750px;margin-left:40px;"></div>
						
					</td>
					
</td>
				</tr>

<tr>
<td>
<div >
<div style="padding-top:10px;">
 <div class="easyui-paddingbfpx" style="padding-left:40px;"><label>Process</label></div>
 <div style="padding-left:40px;" >
 <input id="cmbProcess" name="cmbProcess" clear="false"  class="easyui-combobox"  style="width:155px;" value="" > 
 </div>
 </div>
 <div style="padding-left:40px;padding-top:10px;">
 <div class="easyui-paddingbfpx" ><label>Manufacturer Date</label></div>
 <div class="easyui-paddingbfpx" >
 <input	class="easyui-text" id="manufdteDate" name="manufdteDate" maxlength="30" style="width: 155px; height: 21px;" value="" />
 </div>
</div>
</div>
</td>
<td >
<div style="padding-top:10px;">
<div class="easyui-paddingbfpx" style="padding-left:40px;"><label>Records of Movements</label></div>

<div style="padding-left:40px;">
<input id="cmbRecordsofmovnt" name="txtRecordsofmovnt" clear="false"  class="easyui-text"  style="width:155px;" value="" > 
</div>
</div>

 <div style="padding-left:40px;padding-top:10px;">
  <div class="easyui-paddingbfpx" ><label>Installation Date</label></div>
	 <div class="easyui-paddingbfpx">
	 <input	class="easyui-text" id="instaldteDate" name="instaldteDate" maxlength="30" style="width: 155px; height: 21px;" value="" />
	 </div>
 </div>
</td>
<td style=" width : 250px;">


<div style="margin-left:40px;width:100px;padding-top:10px;">
  <div class="easyui-paddingbfpx" style="padding-left:0px;"><label>Manufacturer</label></div>
	 <div>
	  <input id="cmbManufacturer" name="cmbManufacturer" clear="false"  class="easyui-combobox"  style="width:155px;" value="" > 
	 </div>
 </div>
<div class="easyui-paddingbfpx" style="padding-left:40px;padding-top:10px;"><label>Test Operation Date</label></div>
	 <div style="padding-left:40px;" class="easyui-paddingbfpx">
	 <input	class="easyui-text" id="testoperdteDate" name="testoperdteDate" maxlength="30" style="width: 155px; height: 21px;" value="" />
	 </div>
</td>
<td>
<div style="margin-top:40px;padding-top:10px;">
 <div class="easyui-paddingbfpx" ><label style="padding-left:0px;">Startup Date</label></div>
	 <div style="padding-left:00px;" class="easyui-paddingbfpx">
	 <input	class="easyui-text" id="startupdteDate" name="startupdteDate" maxlength="30" style="width: 155px; height: 21px;" value="" />
	 </div>
	 </div>
</td>
<td >
                     <div style=" padding-left:26px; margin-top:36px;margin-top:16px\9;position:relative; ">
					 <span  id="InstrFilemgr" style="position:absolute;" >
     		
             </span> 
             </div>	
</tr>
</table>

<div style="padding-left:10px; margin-top:30px; margin-left:40px;margin-left:54px\9; width:94.5%; width:94%\9;  float:left\9;" class="sub-header" >
<label>Record of Specification Changes</label>
</div>
<div  class="grid"  >
<table id='spectableid'><tr><td></td></tr></table>
<div id='specpagerid'></div>
</div>

<div style="padding-left:10px; margin-left:40px; margin-left:54px\9; width:94.5%; width:94%\9; margin-top:30px; float:left\9;   " class="sub-header" >
<label>Maintenance Record </label>
</div>
<div  class="grid" >
<table id='mantcetableid'><tr><td></td></tr></table>
<div id='mantcepagerid'></div>
</div>

<div style="padding-left:10px;  width:94.5%;width:94%\9; margin-left:40px;  margin-left:54px\9; margin-top:30px; float:left\9; "  class="sub-header">
<label>Callibration Record</label>
</div>
<div  class="grid">
<table id='callbtntableid'><tr><td></td></tr></table>
<div id='callbtnpagerid'></div>
</div>

</div>

</div>

</form>