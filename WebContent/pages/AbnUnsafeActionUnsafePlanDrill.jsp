<script>
jQuery(document).ready(function ()
		{
			var url = jQuery('#hiddenUrl').val();
			var dataStr="?q=2";
			viewGrid(url,dataStr);
			processGridnew("AbnUnsafeActConditionMonth_input.abnGenRpt" ,dataStr,"AbnUnsafeMonthGrid","AbnUnsafeMonthpagerid","","doubleClickAbnUnsafeMonth");
			 /*jQuery("#tabUnsafe").tabs({ onLoad:function(title){ 
					jQuery('.tabs-panels').css('height','397');	 
				}
			});*/
			jQuery("#tabUnsafe").tabs(
			 {
				 onSelect : function(title) 
				  {
					  if(title=="JH"){
						jQuery('#btnUnsafeGraph').click(function(){
							var urlun = "AbnUnsafeChart_input.abnGenRpt?q=2&ChartType=J";
							LoadPopUp("divUnsafegraph", urlun, false,"97%","90%","-1%","0%", "loadpopUpSuccessCallBack", "Chart", true);
						});
					  }
					  if(title=="Monthwise"){
						jQuery('#btnMonthGraph').click(function(){
							var urlMon = "AbnUnsafeChart_input.abnGenRpt?q=2&ChartType=M";
							LoadPopUp("divUnsafegraph", urlMon, false,"97%","90%","-1%","0%", "loadpopUpSuccessCallBack", "Chart", true);
						});
					  }
				    }
			 });										
		});

		function viewGrid(url,filterString)
		{						
			if( validateFilterSelection(filterString))
			{
				processGridnew(url ,filterString,"AbnUnsafeGrid","AbnUnsafepagerid","","doubleClickAbnUnsafe");
				return true;
			}
			return false;
		}
		function validateFilterSelection(filterString){
				return true;
		}
</script>
<form id="frmUnsafePlanActDril" name="frmUnsafePlanActDril">
	<div id="WrapperRpt" style="widht:100%">
		<div id="tabUnsafe" class="easyui-tabs" style="height: 400px;height: 390px\9; width: 1110px; width:1110px\9;  float: left">	
			<div title="JH">
				<table style="padding-left:3%;">
					<tr>
						<td>
							<span style="float:right">
								<input type="button" class="easyui-button" id="btnUnsafeGraph" value="Graph"/>
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<table id ='AbnUnsafeGrid'><tr><td></td> </tr></table>
								<div id ='AbnUnsafepagerid'></div>
							</div>
						</td>
					</tr>
				</table>
				
			</div>
			<div title="Monthwise">
				<table style="padding-left:3%;">
					<tr>
						<td>
							<span style="float:right">
								<input type="button" class="easyui-button" id="btnMonthGraph" value="Graph"/>
							</span>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<table id ='AbnUnsafeMonthGrid'><tr><td></td> </tr></table>
								<div id ='AbnUnsafeMonthpagerid'></div>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</form>