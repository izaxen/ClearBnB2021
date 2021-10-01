<template>
  <head>
    <title>Document</title>
  </head>
  <body>
    <h1>live comm</h1>
    <div id="chat-output"></div>
    <input id="chat-input" v-model="input" />

    <button
      id="chat-send"
      v-bind:disabled="isEmptyOrNotConnected"
      @click="sendMessage()"
    >
      send
    </button>

    <button id="connect" v-bind:disabled="isNotLoggedIn" @click="connect()">
      connect
    </button>
    <button
      id="disconnect"
      v-bind:disabled="isNotConnected"
      @click="disconnect()"
    >
      disconnect
    </button>
  </body>
</template>>


<script>
export default {
  data() {
    return {
      div: "",
      input: "",
      socket: "",
      client: null,
      currentUser: this.$store.state.user,
    };
  },

  mounted() {
    this.div = document.querySelector("#chat-output");
  },

  computed: {
    isEmptyOrNotConnected() {
      return this.input <= 0 || this.client == null;
    },

    isNotConnected() {
      return this.client == null;
    },

    isNotLoggedIn() {
      return this.$store.state.loggedInUser == null;
    },
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
        this.addMsg("Message from server:" + JSON.parse(event.data).msg);
      };

      this.socket.onopen = (event) => {
        console.warn("Connected:", event);
        this.addMsg(
          "Connected "
          // + JSON.stringify(event)
        );
      };
    },

    connect() {
      if (this.client) return;

      this.client = 1;
      console.log("Connecting...");
      this.addMsg("Connecting...");
      this.socket = new WebSocket(
        "ws://localhost:4000/websockets/" + this.$store.state.user.id
      );

      this.addSocketEventListeners();
    },

    disconnect() {
      if (!this.client) return;
      console.log("Disconnecting...");
      this.addMsg("Disconnecting...");
      this.socket.close();
      this.client = null;
    },

    sendMessage() {
      let msg = this.input;
      let userID = this.$store.state.user.id;
      let userFirstName = this.$store.state.user.firstName;
      this.input = "";
      console.log("Sending:", msg);
      this.socket.send(
        JSON.stringify({
          msg,
          time_sent: Date.now() / 1000,
          userID,
          userFirstName,
        })
      );
      // addMsg(msg); // if locally rendered instead of reliably pushed from server
    },
  },
};
</script>