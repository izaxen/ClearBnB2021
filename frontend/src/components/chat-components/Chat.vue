<template>
  <head>
    <title>Document</title>
  </head>
  <body>
    <h1>live comm</h1>
    <div id="chat-output"></div>
    <input id="chat-input" v-model="input" />
    <button id="chat-send" @click="sendMessage()">send</button>

    <button id="connect" @click="connect()">connect</button>
    <button id="disconnect" @click="disconnect()">disconnect</button>
  </body>
</template>>


<script>
export default {
  data() {
    return {
      div: "",
      input: "",
      socket: "",
    };
  },

  mounted() {
    this.div = document.querySelector("#chat-output");
  },

  methods: {
    addMsg(s) {
      this.div.insertAdjacentHTML("beforeend", `<p>${s}</p>`);
    },

    addSocketEventListeners() {
      this.socket.onclose = (event) => {
        console.warn("Disconnected:", event);
        this.addMsg("Disconnected: " + JSON.stringify(event));
      };

      this.socket.onerror = (event) => {
        console.error("Error:", event);
        this.addMsg("Error: " + JSON.stringify(event));
      };

      this.socket.onmessage = (event) => {
        console.log("Message from server:", event.data);
        this.addMsg("Message from server: " + JSON.parse(event.data).msg);
      };

      this.socket.onopen = (event) => {
        console.warn("Connected:", event);
        this.addMsg("Connected: " + JSON.stringify(event));
      };
    },

    connect() {
      console.log("Connecting...");
      this.addMsg("Connecting...");
      this.socket = new WebSocket("ws://localhost:4000/websockets");
      this.addSocketEventListeners();
    },

    disconnect() {
      console.log("Disconnecting...");
      this.addMsg("Disconnecting...");
      this.socket.close();
    },

    sendMessage() {
      let msg = this.input;
      this.input = "";
      console.log("Sending:", msg);
      this.socket.send(JSON.stringify({ msg, time_sent: Date.now() / 1000 }));
      // addMsg(msg); // if locally rendered instead of reliably pushed from server
    },
  },
};
</script>