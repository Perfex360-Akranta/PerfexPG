

jQuery( "#additional_info_frm" ).dialog({
	autoOpen: false,
	show: "blind",
	hide: "explode",
	height: 400,
	width: 900,
	modal: true
});



jQuery( "#rsrc_pln_frm" ).dialog({
	autoOpen: false,
	show: "blind",
	hide: "explode",
	height: 400,
	width: 900,
	modal: true
});

function open1(objId){
	jQuery( "#"+objId ).dialog( "open" );
}
    
		function close1(objId){
			jQuery('#'+objId).dialog('close');
		}
		
		jQuery('#additional_info_frm').dialog('close');
		jQuery('#rsrc_pln_frm').dialog('close');
        /* Start script to additional form slick effect */        
        function bind_add_info_click(){    
        	if(document.getElementById('additional_info_frm_cntnt').innerHTML.trim().length == 0){        		
        		
        		jQuery("#additional_info_frm_cntnt").load('preventive_addinfo_input.prv', function(response, status, xhr) {
					  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
					});
        		
        	} 
        	open1('additional_info_frm');
        }     
        
        
        function rsrs_pln_inf_click(){
        	if(document.getElementById('rsrc_pln_frm_cntnt').innerHTML.trim().length == 0){        		
        		
        		jQuery("#rsrc_pln_frm_cntnt").load('prvnt-mntnc-rsrc_input.prv', function(response, status, xhr) {
					  if (status == "error") {
					    var msg = "Sorry but there was an error: ";
					    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
					  }
					});
        		
        	} 
        	open1('rsrc_pln_frm');
        }
        
        
        function find_wndw_click(){
        	cloase_all_main_div();
        	document.getElementById('bind_source_div_fnd').style.display="block";
        	if(document.getElementById('bind_source_div_fnd').innerHTML.trim().length == 0){
        	jQuery("#bind_source_div_fnd").load('prvntmntnc_fndfrm_input.prv', function(response, status, xhr) {
      		  if (status == "error") {
      		    var msg = "Sorry but there was an error: ";
      		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
      		  }
      		});	
        	}
        }  
        
        function source_div_actws_click(){
        	cloase_all_main_div();
        	document.getElementById('bind_source_div_actws').style.display="block";
        jQuery("#bind_source_div_actws").load('prvnt_mntnc_actv_input.prv', function(response, status, xhr) {
      	  if (status == "error") {
      	    var msg = "Sorry but there was an error: ";
      	    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
      	  }
      	});
        }
      function grid_row_click(){
    	  cloase_all_main_div();
    	document.getElementById('bind_source_div_ginfo').style.display="block";
      	jQuery("#bind_source_div_ginfo").load('prvnt_mntnc_ginfo_input.prv', function(response, status, xhr) {
      		  if (status == "error") {
      		    var msg = "Sorry but there was an error: ";
      		    jQuery("#dispErr").html(msg + xhr.status + " " + xhr.statusText);
      		  }
      		});	
      }
      
      
      source_div_actws_click();
      function cloase_all_main_div(){
    	  document.getElementById('bind_source_div_fnd').style.display="none";
    	  document.getElementById('bind_source_div_actws').style.display="none";
    	  document.getElementById('bind_source_div_ginfo').style.display="none";    	  
      }
      
      
      function find_open(){
    	  close_all_fnd_wndw();
    	  document.getElementById('find_cntnt').style.display="block";
      }
      
      
      function filter_open(){
    	  
    	  close_all_fnd_wndw();
    	  document.getElementById('fltr_cntnt').style.display="block";
      }
      
      function close_all_fnd_wndw(){
    	  document.getElementById('find_cntnt').style.display="none";
    	  document.getElementById('fltr_cntnt').style.display="none";
      }