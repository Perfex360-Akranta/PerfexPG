
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>untitled</title>
<script type="text/javascript" language="javascript">

function reset() {
alert('My "Reset" called.');
document.getElementById("add").elements["num1"].value = "";
document.getElementById("add").elements["num2"].value = "";
}

function clear() {
alert('My "Clear" called.');
document.getElementById("add").elements["num1"].value = "";
document.getElementById("add").elements["num2"].value = "";
}

</script>
</head>
<body>
<form name="add">
<input type="text" name="num1" value="default" /><br />
<input type="text" name="num2" value="default" /><br />
<input type="button" value="Reset" onclick="window.reset()" />
<input type="button" value="Clear" onclick="window.clear()" />
<input type="reset" value="A Real Reset Button" />
</form>
</body>
</html>