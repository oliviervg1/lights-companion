<%@ page import="com.pi.relay.WebSocketController" %>
<% WebSocketController socketController = new WebSocketController(); %>

<html>
	<body>
		<h1>Welcome to the Relay Switch control interface!</h1>
		<h3>This is purely for demo purposes... Terrible UI coding is involved!</h3>
		<p> Switch is currently: <% out.println(socketController.getState()); %></p>

		<form>
		       <input type="submit" id="turnOn" name="turnOn" value="On!"/>
		       <input type="submit" id="turnOff" name="turnOff" value="Off!"/>
		</form>

		<%
			if(request.getParameter("turnOn")!=null) {
			    socketController.turnOn();
			    response.sendRedirect(request.getContextPath());
			}
			if(request.getParameter("turnOff")!=null) {
			    socketController.turnOff();
			    response.sendRedirect(request.getContextPath());
			} 
		%>
	</body>
</html>
