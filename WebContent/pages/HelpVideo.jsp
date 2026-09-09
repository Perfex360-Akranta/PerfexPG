<link rel="stylesheet" type="text/css" href="js/player/video-js/video-js.css"/>
<script type="text/javascript" src="js/player/video-js/video.js"></script>

<script>

jQuery(document).ready(function(){

	jQuery('#divBack').hide();
	fillComboBox("frmHelp","cmbPillarid","pillar.commonFilter?&frmtype=MOM");
	processGridnew("helpVideo_input.hv","?q=2","tableid","pagerid"," ");

	//jQuery('#tdPlay').hide();
	jQuery('#divVideo').html('');
	
});


function frmHelpcmbPillarid_onSelect(record) { 
	var pillar = record.id;
	processGridnew("helpVideo_input.hv","?q=2&pillar="+pillar,"tableid","pagerid"," ");
}
function btnPlayFormatter(cellVal, options, rowObject)
{
	
	// <input type="button" id="remov" class="grddownloadimg"  onclick="grddownloadimg(\''+rowId + '\');"/>
	var act='<input type="button" id="btnPlay_'+options.rowId+'" name="btnPlay_"'+options.rowId+'" ';
	act+= 'onClick=showHelp('+options.rowId+');';
	act+= 'style="" class="grdPlayBtn"  value=""/>';
	return act;
	
	//<!-- <source src="d:\clitStd.mp4" type='video/mp4' /></source> -->
    

}

function showHelp(id)  {
	//jQuery('#tdPlay').css('width','95%');
	jQuery('#tdSelection').hide();
 	jQuery('#divVideo').html('');
 		
	var vs= "";
	
	var rowData = jQuery("#tableid").jqGrid('getRowData',id);
	
	var url = rowData.Path;
	
	//alert(url);
	
	vs+= ' <link rel="stylesheet" type="text/css" href="js/player/video-js/video-js.css"/> ';
	vs+= ' <script type="text/javascript" src="js/player/video-js/video.js"> ' ;
	vs+= "</"+"script>" ;
	//alert(url);
	//vjs-fullscreen
	vs+= ' <video id="video1" class="video-js vjs-default-skin vjs-playing " width="924" height="480" ' ;
//	alert(1);
	vs = vs + " data-setup=' " + '{"controls" : true, "autoplay" : true, "preload" : "auto"}' + " '> " ;
	vs+= ' <source src="'+url+'" type="video/mp4"/></source> ';
	vs+= ' </video> ';
	
	//vs+= ' <div> <input type="button" id="btnHide" name="btnHide"> </div> ';
	
	jQuery('#divVideo').html(vs); 
	jQuery('#divBack').show();
	
	//goFullscreen("video1");
	/*
	jQuery('#video1').css('.video-js {padding-top: 56.25%}');
	jQuery('#video1').css('.vjs-fullscreen {padding-top: 0px}');
	 var video = document.getElementById("video1");
	video.mozRequestFullScreen();
	video.webkitEnterFullScreen();
	 
	jQuery('#video1').css('video:-webkit-full-screen { height: 100%; }');
	jQuery('#video1').css('video:-moz-full-screen        { height: 100%; }');
	*/
}


function goFullscreen(id) {
	  var element = document.getElementById(id);
	  if (element.mozRequestFullScreen) {
	    element.mozRequestFullScreen();
	  } else if (element.webkitRequestFullScreen) {
	    element.webkitRequestFullScreen();
	  }  
}

jQuery( "#showFLImage" ).click(function() {
	 
	jQuery('#tdSelection').show();
 	jQuery('#divVideo').html('');
	var vs=""; 	
	vs+= ' <link rel="stylesheet" type="text/css" href="js/player/video-js/video-js.css"/> ';
	vs+= ' <script type="text/javascript" src="js/player/video-js/video.js"> ' ;
	vs+= "</"+"script>" ;
	vs+= ' <video id="video1" class="video-js vjs-default-skin" width="540" height="480" ' ;
//	alert(1);
	vs = vs + " data-setup=' " + '{"controls" : false, "autoplay" : true, "preload" : "auto"}' + " '> " ;
	vs+= ' <source src="123.flv" type="video/mp4"/></source> ';
	vs+= ' </video> ';
	//jQuery('#divVideo').html(vs);
	jQuery('#divVideo').html('');
	jQuery('#divBack').hide();

	
	
});

</script>


<form id="frmHelp" name="frmHelp">

<table>
<tr>
<td id="tdSelection">
<div id='' style="width:50%;padding: 10px;">
	<label> Pillar </label>
	<input type="text" class="easyui-combobox" id="cmbPillarid" name="cmbPillarid" style="width: 210px;"  value=""/>
	<div>
		<table id='tableid'>
		<tr><td>
			<div id='pagerid'></div>
		</td>
		</table>
	</div>
</div>
</td>
<td id="tdPlay" style="width: 50%;padding: 10px;">
	<div id="divVideo">
	<video id="video1" class="video-js vjs-default-skin" width="540" height="480"
        data-setup='{"controls" : false, "autoplay" : false, "preload" : "auto"}'>
     <source src="js/player/123.flv" type="video/mp4"/></source>
    </video>
	</div>
</td>
<td id="tdBack" style="width: 20px;padding-left: 10px;">
		<div style="float:left" id="divBack">
		<img src="images/Back-.png" id="showFLImage" />
	</div>			
</td>
</tr>
</table>
</form>
