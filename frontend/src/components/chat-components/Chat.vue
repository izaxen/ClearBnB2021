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
    <input
      v-if="this.$store.state.user && this.$store.state.user.id == 91"
      id="receiver-input"
      v-model="receiverID"
      required
    />

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
      receiverID: "",
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
        // this.addMsg("Disconnected: " + JSON.stringify(event));
      };

      this.socket.onerror = (event) => {
        console.error("Error:", event);
        this.addMsg("Error: " + JSON.stringify(event));
      };

      this.socket.onmessage = (event) => {
        let temp = JSON.parse(event.data);
        let sender = temp.senderID;
        let firstName = this.addMsg(temp.senderID + ": " + temp.msg);
      };

      this.socket.onopen = (event) => {
        // console.warn("Connected:", event);
        // console.log(event);
        // consolt.log(msg);
        // // this.addMsg(event.data);
        // if (this.$store.state.user.id == 91) {
        //   this.addMsg(event.data.msg + " " + event.data.senderID);
        // } else {
        //   this.addMsg(event.data.msg);
        // }
      };
    },

    connect() {
      if (this.client) return;

      this.client = 1;
      // console.log("Connecting...");
      // this.addMsg("Connecting...");
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

      this.input = "";
      this.socket.send(
        JSON.stringify({
          msg,
          time_sent: Date.now() / 1000,
          senderID: this.$store.state.user.id,
          receiverID: this.receiverID,
        })
      );
      // addMsg(msg); // if locally rendered instead of reliably pushed from server
    },
  },
};
</script>