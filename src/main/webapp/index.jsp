<%@ page import="com.pi.relay.SocketController" %>
<% 	SocketController socketController = new SocketController(); %>

<html>
	<body>
		<h1>Welcome to the Relay Switch control interface!</h1>
		<h3>This is purely for demo purposes... Terrible UI coding is involved!</h3>
		<p> Switch is currently: <% out.println(socketController.getState()); %></p>

		<form>
		       <input type="submit" id="powerOn" name="powerOn" value="On!"/>
		       <input type="submit" id="powerOff" name="powerOff" value="Off!"/>
		</form>

		<%
			if(request.getParameter("powerOn")!=null) {
			    socketController.powerOn();
			    response.sendRedirect(request.getContextPath());
			}
			if(request.getParameter("powerOff")!=null) {
			    socketController.powerOff();
			    response.sendRedirect(request.getContextPath());
			} 
		%>
	</body>
</html>
