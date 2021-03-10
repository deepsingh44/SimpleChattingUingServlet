<head>
<script type="text/javascript">
	
	var socket = new WebSocket("ws://localhost:9191/ChattingApp/chat");
	 
	socket.onmessage = function(event) {
		var c=document.getElementById('list');
		c.innerHTML+=event.data+"\n";
	}; 

	function send() {
		var m = socket.send(document.getElementById('msg').value);
		document.getElementById('msg').value = "";
	}
</script>
</head>

<textarea rows="10" cols="40" id="list">
</textarea>
<br>
<br>
Enter Message :
<input type="text" id="msg">
<input type="button" value="Send Me" onclick="send()">