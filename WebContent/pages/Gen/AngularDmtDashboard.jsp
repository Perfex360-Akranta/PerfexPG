<%@ page contentType="text/html;charset=UTF-8" %>

<%
    String angularOrigin =
        (String) request.getAttribute(
            "angularOrigin"
        );

    String angularDashboardUrl =
        (String) request.getAttribute(
            "angularDashboardUrl"
        );

    String dashboardBootstrapJson =
        (String) request.getAttribute(
            "dashboardBootstrapJson"
        );
%>

<div
    style="
        width:100%;
        height:100%;
        overflow:hidden;
    ">

    <iframe
        id="angularDmtDashboardFrame"
        src="<%= angularDashboardUrl %>"
        style="
            width:100%;
            height:100%;
            border:0;
            display:block;
        "
        frameborder="0">
    </iframe>

</div>

<script>

(function () {

    var frame =
        document.getElementById(
            "angularDmtDashboardFrame"
        );

    var dashboardContext =
        <%= dashboardBootstrapJson %>;

    function sendDashboardContext() {

        if (!frame ||!frame.contentWindow) {
            return;
        }
        
        var javaUserName = (jQuery("#divAngularUserName").text() || "").trim();

        var javaRoleLine = (jQuery("#divLoginUserRole") .text() || "").trim();

        dashboardContext.userName = javaUserName;
        dashboardContext.roleLine =   javaRoleLine;
        
        console.log("JAVA -> ANGULAR USER:", javaUserName);

        console.log("JAVA -> ANGULAR ROLE:",javaRoleLine );
            

        frame.contentWindow.postMessage(
            dashboardContext,
            "<%= angularOrigin %>"
        );
    }

    frame.onload = function () {

        console.log(
            "Angular DMT dashboard loaded."
        );

        sendDashboardContext();

        /*
         * Small second attempt in case Angular
         * is still completing bootstrap.
         */
        setTimeout(
            sendDashboardContext,
            500
        );
    };

})();

</script>