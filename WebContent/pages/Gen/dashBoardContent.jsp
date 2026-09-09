<script type="text/javascript">	
	
jQuery(document).ready(function(){


	var alertDivDisplay = jQuery('.alert_content_div').css('display');
	var alertDivZindex = jQuery('.alert_content_div').css('z-index');
	if(alertDivDisplay == "block")
		jQuery(".dashboard_content_div").css("z-index",alertDivZindex+5);
	else
		jQuery(".dashboard_content_div").css("z-index",alertDivZindex-5);
	
	var accHtml = jQuery('#safetydiv').html();
	
		acordian_select();
	
	if(screen.width <= 1024){
		jQuery("#dashboard_grphcontent").css('width','82.5%');
	}
	else
		jQuery('#dashboard_grphcontent').css('width','84.8%');
});

function acordian_select(){
    var pillrCde = jQuery('.pillarClick').attr('pillarCode');
    
		if(pillrCde == '' || pillrCde == undefined || pillrCde == null)
	  	processAjaxCalls("getPillars.dashboard","","DashBoardPillars_successCallback");
		
   	/*jQuery('#chartAccordian').accordion({
		         onSelect:function(title){
		            if(title == 'Safety'){
		            	processAjaxCalls("getPillars.dashboard","","DashBoardPillars_successCallback");
			          }
		         }
		      });*/
       }
   function DashBoardPillars_successCallback(result){
    
   	var pillarDet;
   	
         	for( var i = 0; i< result.length ; i++){
         		pillarDet = result[i];
         		//alert(i+" = "+pillarDet +" -- "+pillarDet[2]);
         		var chrtDta = jQuery('#hdnchartData').val() ;
         		//var chrtDta_obj = JSON.parse(chrtDta);
         		//jQuery("#safetydiv").append('<div class="pillarDiva" style="margin-top:1%;"> <a href="#" class="pillarClick" pillarCode="'+ pillarDet[1]  +'"  onclick =btnClickgrph("'+ pillarDet[1]  +'");  >'+ pillarDet[0] +'</a></div>');'+chrtDta_obj+'
         		jQuery("#plrNameDiv").append('<input type="text" id="plrName_'+ pillarDet[1]  +'" value="'+ pillarDet[0]  +'"/>');
         		jQuery("#DBaccordian").append('<li class="clsLi" id="lidiv_'+ pillarDet[1]  + '"><span style="float:left;margin-top:0.4%;"><img id="pillarIcon" alt="" title="'+pillarDet[0]+'" src="'+pillarDet[2]+'" style="cursor: pointer;"  width="20px" height="20px"></span><div   onclick =btnClickgrph("'+ pillarDet[1]  +'");><a href="#" class="pillarClick" pillarCode="'+ pillarDet[0]  +'"    >'+ pillarDet[0] +'</a></div><ul id="'+ pillarDet[1]  +'" class=""></ul></li>');
         	}
   	
   }
   function btnClickgrph(id){
		jQuery('#spnzoomout').css('display','none');
       if(jQuery('.clsLi').hasClass('selectedLI'))
 	  jQuery('.clsLi').removeClass('selectedLI');
 	  jQuery('#lidiv_'+id).addClass('selectedLI');
 	  if ((screen.width >= 1280)&& (screen.width <1366 )){
 		  jQuery('.selectedLI').css('width','94%');
 	  }        	  
 	  jQuery('#dashboard_grphcontent').css('border-radius','7px 27px 8px 30px');
 	  jQuery('#dashboard_grphcontent').css('border-left','solid 5px #A6DDE4');
 	  jQuery('#dashboard_grphcontent').css('box-shadow',' 3px 3px 5px #000000');
			var pillar = id;
			var pillarName = jQuery('#plrName_'+pillar).val();
			//alert(" "+type);
			jQuery('#lblpillarName').html(pillarName+" - ");	
			if( pillar != null && pillar != undefined ){
     		 LoadForm("dashboard_grphcontent","","dashboard_input.dashboard?pillar="+pillar);
     		
 		}
			/*if(type == "GPH" && type != undefined ){
	    		if( pillar != null && pillar != undefined ){
	        		LoadForm("dashboard_grphcontent","","dashboard_input.dashboard?pillar="+pillar+"&type="+type);
	        		
	    		}
			}
			else{
					LoadForm("dashboard_tblcontent","","dashboard_input.dashboard?pillar="+pillar+"&type="+type);
				}*/
    }      
</script>
<div class="clearfix"></div>
	<div id="dasbrdIcon" style="width:15%;height:50%; position: absolute; "  >
		<div id='chartAccordian' class='' style='width:150px;height:auto;float:left;'>
			<div id="safetydiv"  style="width:160px;" class='accDiv'>
			<ul id="DBaccordian">
			</ul>
			</div>
			<div title="BreakDown" style="padding:0px;fit:true;hieght:200;">
			</div>
			<div id="plrNameDiv" style="display:none;">
		 	</div>
		</div>
<!--		 <div id="leftRightDiv" style="margin-top:-3;z-index:1010;float:right;margin-top:-45%;width:27;">-->
<!--			<img id="dashboardleft_header" class=""  src="images/layout_button_left.gif" title="4" style="cursor:pointer; margin-left: 83.7%;margin-top:-26;" onclick="hideAccdn();"/>-->
<!--			<img id="dashboardright_header" class=""  src="images/layout_button_right.gif" title="4" style="margin-left: 118.6%; cursor: pointer; display: block; margin-top: -6px;display:none;" onclick="showAccdn();"/>-->
<!--			-->
<!--		</div>-->
	</div>
		
		<div id="dashboard_grphcontent" style="overflow:auto; height:93%;width:84%;float:right; border:solid 1px #C1C1C1;margin-right:6;background: #fff;" ></div>
   
