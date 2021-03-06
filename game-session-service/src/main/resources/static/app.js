let stompClient = null;

const setConnected = (connected) => {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#chatLine").show();
  } else {
    $("#chatLine").hide();
  }
  $("#message").html("");
};

const connect = () => {
  stompClient = Stomp.over(new SockJS("/endpoint"));
  stompClient.connect({}, (frame) => {
    setConnected(true);
    console.log("Connected: " + frame);
    stompClient.subscribe("/topic/response", (report) =>
      showReport(JSON.parse(report.body).reportId)
    );
  });
};

const disconnect = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
};

const sendMsg = () =>
  stompClient.send(
    "/app/reports" ,
    {},
    JSON.stringify({ sessionId: $("#sessionId").val() })
  );

const showReport = (messageStr) =>
  $("#chatLine").append("<tr><td>" + messageStr + "</td></tr>");

$(function () {
  $("form").on("submit", (event) => {
    event.preventDefault();
  });
  $("#connect").click(connect);
  $("#disconnect").click(disconnect);
  $("#send").click(sendMsg);
});

