<meta http-equiv="refresh" content="1;url=dashboard.jsp">
 <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />    

<!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">
<script type="text/javascript" src="js/loader.js" ></script>
 <script type="text/javascript" src="js/json2.js" ></script>
 <style>
 .menu-bar{
 background: #666699;

 text-aligh:left:;
 }
 
/*  .menu-bar ul{
 display:inline-flex;
 list-style:none;
 color:#fff;
 height:70px;
 } */
 
  .menu-bar ul li{
  width:120px;
  margin:15px;
  padding:15px;
  }
  
  .menu-bar ul li a{
  text-decoration:none;
  color:#fff;
  
  }
  .menu-bar .fa{
  margin-left:8px;
  }
  .active, .menu-bar ul li:hover{
  background:#ccccff;
  border-radius:3px;
  }
 /* HEADER MENU BAR */
 
  .menu-bar-header{
 background: #666699;

 text-aligh:left:;
 }
 
  .menu-bar-header{
 display:inline-flex;
 list-style:none;
 color:#fff;
 height:70px;
 } 
 
  .menu-bar-header ul li{
  width:500px;
  margin:10px;
  font-size:20;
  padding:10px;
  }
  
   .menu-bar-header ul li a{
  text-decoration:none;
  color:#fff;
   
  }  
  .menu-bar-header .fa{
  margin-left:8px;
  }
  .active, .menu-bar-header ul li:hover{
 
  border-radius:3px;
  }  
  .label{
 
  font-size:20;
 
   color:#fff;
  }
  .bodybg{
  background-color:#220033; 
  }
 </style>
<script type="text/javascript">
var mode=jQuery("#mode").val();
jQuery(document).ready(function(){
	initialiseForm('frmDashbdgrid');
	
	
	//jQuery(".dialog-content panel-body panel-body-noheader panel-body-noborder").hide();

	jQuery( ".window-shadow" ).hide(); 
	//jQuery('#home_center').css("background-color","#220033");

});

function toggleClass1(){
	
	navigateToNextForm("newdashboardCharts_input.dashboard","","","");

	}
function toggleClass2(){
	alert("INSIDE");
	navigateToNextForm("newdashboardTables_input.dashboard","","","");

	}
	

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawColColors);

function drawColColors() {
      var data = new google.visualization.DataTable();
      data.addColumn('timeofday', 'Abnormality Details Cumulative');
      data.addColumn('number', 'Identified');
      data.addColumn('number', 'Completed');

      data.addRows([
        [{v: [8, 0, 0], f: '147'}, 1, 89],
        [{v: [9, 0, 0], f: '147'}, 2, 147],
        [{v: [10, 0, 0], f:'89'}, 3, 147],
        [{v: [11, 0, 0], f: '147'}, 4, 89],
        [{v: [12, 0, 0], f: '147'}, 5, 147],
        [{v: [13, 0, 0], f: '89'}, 6, 147],
        [{v: [14, 0, 0], f: '454'}, 7, 89],
       
      ]);

      var options = {
        title: 'Abnormality Cumulative Identified vs Completed',
        colors: ['#9575cd', '#33ac71'],
        hAxis: {
          title: 'Abnormality Details Cumulative',
          format: 'h:mm a',
          viewWindow: {
            min: [7, 30, 0],
            max: [17, 30, 0]
          }
        },
        vAxis: {
          title: 'Rating (Numbers)'
        }
      };

      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }

</script>
<form id ='frmDashbdgrid'>
<div class="bodybg"style="height:104%;width:115%;">
<div class="menu-bar-header" style="margin-top:0px;width:100%;height:55px;">
<ul>
<li class="newactive" onclick="toggleClass()"><a href="#"><b>PERFEX 360 DASHBOARD</b></a><i class="fa fa-home"></i>
</li>

</ul>
	<div class="easyui-paddingbfpx"style="margin-left:-200px;margin-top:20px;">
					       <span style="padding-left:-0px;"><label class="Label"><b>Department</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx"style="margin-left:10px;margin-top:25px;" >
					         <span style="position:relative;padding-left: -100px">	
						    <input class="easyui-combobox" id="cmbrfcmdmtid" name="cmbrfcmdmtid"  style="width:200px;"  value="${requestScope.mocRfcmst.rfcmdmtid}" />
				
						    </span>
					       </div>
					        	<div class="easyui-paddingbfpx"style="margin-left:10px;margin-top:20px;">
					       <span style="padding-left:0px;"><label class="label"><b>JH</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" style="margin-left:10px;margin-top:25px;">
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbRfcmjhid" name="cmbRfcmjhid"  style="width:200px;"  value="${requestScope.mocRfcmst.rfcmjhid}" />
				
						    </span>
					       </div>
					            	<div class="easyui-paddingbfpx"style="margin-left:10px;margin-top:20px;">
					       <span style="padding-left:0px;"><label class="label"><b>Location</b></label></span>	</div>
					       
					       <div class="easyui-paddingbfpx" style="margin-left:10px;margin-top:25px;">
					         <span style="position:relative;padding-left: 0px">	
						    <input class="easyui-combobox" id="cmbLocnid" name="cmbLocnid"  style="width:180px;"  value="${requestScope.mocRfcmst.rfcmjhid}" />
				
						    </span>
					       </div>
</div>
<div class="menu-bar" style="margin-top:-15px;width:150px;height:90%">
<ul>
<li class="active" onclick="toggleClass()"><a href="#"><b>Dashboard</b></a><i class="fa fa-home"></i>
</li>
<li onclick="toggleClass1()"><a href="#"><b>Charts</b></a><i class="fa fa-list"></i></li>
<li onclick="toggleClass2()"><a href="#"><b>Tables</b></a><i class="fa fa-plus"></i></li>
<li onclick="toggleClass3()" ><a href="#"><b>Reports</b></a><i class="fa fa-users" ></i></li>
<!-- <li onclick="toggleClass4()"><a href="#">DMAIC Report</a><i class="fa fa-flag-checkered"></i></li>
<li onclick="toggleClass5()"><a href="#">Status Report</a><i class="fa fa-flag-checkered"></i>
<li onclick="toggleClass6()"><a href="#">Help</a><i class="fa fa-question-circle"></i> -->
</li>

</ul>
</div>
 <!-- Breadcrumbs-->
       <!--    <ol class="breadcrumb">
            <li class="breadcrumb-item">
              <a href="index.html">Dashboard</a>
            </li>
            <li class="breadcrumb-item active">Blank Page</li>
          </ol> -->
   <!-- Icon Cards-->
          <div class="row"style="margin-top:-470px;margin-left:150px;">
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-primary o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-comments"></i>
                  </div>
                  <div class="mr-5">Jishu Hozen</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-warning o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-list"></i>
                  </div>
                  <div class="mr-5">Kobetzu Kaizen</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-success o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-shopping-cart"></i>
                  </div>
                  <div class="mr-5">EHS Pillar</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
            <div class="col-xl-3 col-sm-6 mb-3">
              <div class="card text-white bg-danger o-hidden h-100">
                <div class="card-body">
                  <div class="card-body-icon">
                    <i class="fas fa-fw fa-life-ring"></i>
                  </div>
                  <div class="mr-5">Quality Maintainance</div>
                </div>
                <a class="card-footer text-white clearfix small z-1" href="#">
                  <span class="float-left">View Details</span>
                  <span class="float-right">
                    <i class="fas fa-angle-right"></i>
                  </span>
                </a>
              </div>
            </div>
          </div>
<div id="chart_div"style="width:85%;margin-left:170px;"></div>
</div>
</form>