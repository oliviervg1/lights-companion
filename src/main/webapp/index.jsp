<html>
	<body>

		<% SocketController = new SocketController(); %>

		<button type="button" onclick=<%SocketController.powerOn()%>>ON!</button>
		<button type="button" onclick=<%SocketController.powerOff()%>>OFF!</button>
	
	</body>
</html>
