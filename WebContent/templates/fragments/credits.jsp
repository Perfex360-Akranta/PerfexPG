	<div id="container">
	<div id="row">
		<div id="left">
			<div id="functLocHierarPopupId">
				<div id="functLocHierarPreLoadId"></div>
				<div id="functLocHierarLoadId"></div>
			</div>
			<div id="multiselectPopUpId" ><div id="preloadMultiSelect"></div><div id="loadMultiSelectPopUp"></div></div>			
			<div id="subformPopUpId" ><div id="preloadSubForm"></div><div id="loadSubFormPopUp"></div></div>
			<div id="multiSelectSavePopUpId" ><div id="preloadMultiSelectSave"></div><div id="loadMultiSelectSavePopUp"></div></div>
			
			
				</div>	
			 	
			
						
			</div>
		<div id="dispFrontPageErr" class="" style="display:none;margin-left:5%;left:0px;top:-8px;">
			<img id="err_popUp" alt="" src="images/callBack_img/error_save.png" style="cursor:pointer;"/>
			
		</div>
		<div id="dispFrontPageSuccess" class="" style="display:none;margin-left:5%;position:relative;left:0px;top:-8px;">
		<img id="success_popUp" alt="" src="images/callBack_img/Success.png" style="cursor:pointer;"/>
		<!-- <div id="success_msg" class='' style="display:none;color:red;background-color:blue;line-height: 17px;font-size:13px;margin: 0.5em 0 0;"></div> -->
		</div>
		<div id="right" style="text-align:right;" align="right">
			<div style="" class="credts">
				<font size="1"  >
				
				  <span id="tpm_logo" style="" class="cred_logo">
					<label class="lblfot">Licensed To</label>
					<span class="imgFotComp">
					<img alt="ITC" src="images/logo/clientlogo.png" style="scroll 0 0;height:22px">
					</span>
					<span class="lblfot" style="font-size:11px;margin-left:30px;">
					<label style="font-weight:bold;color:#32AAF2;font-size:11px;margin-right:5px;">Loc. :  </label>
					<label style="color:#8E6201;font-size: 11px;margin-right:5px;">ITC-PSPD</label><%--	${sessionScope.admDbLocation} --%> 

					<label style="font-weight:bold;color:#32AAF2;font-size:12px;margin-right:5px;">Conn. :</label><label style="color:#8E6201;font-size:11px;padding-right:5px;">${sessionScope.dbUser}</label>  <label style="font-weight:bold;color:#32AAF2;font-size:11px;">Rel. :  </label><label style="color:#8E6201;font-size: 11px;">${sessionScope.createdDate}</label></span>
				<div style="" class="fotrAkaLogo">
			<label class="lblfotaka">Developed By</label><span> 
			<img alt="Akranta Pvt Ltd" src="images/logo/aka_logo.jpg" style="scroll 0 0;height:19px">
			</span> 
			</div>
				</span> 
				</font>
				
			</div>
			
		</div>
	<div style="display: none">	
	<div id="prt-container" ></div>
	</div>			
	</div>
	
	<script src="${pageContext.request.contextPath}/js/xssValidations/input-validation.js"></script>
	
	<script>
    console.log("Context path = [" + "${pageContext.request.contextPath}" + "]");
   console.log("XSSValidator = " + typeof XSSValidator);
</script>
