// // import * as currentUser from "sockjs-client";
// // import * as receiverUser from "sockjs-client";
//
// var stompClient = null;
// /* Estabelecendo a conexão com o /ws,
// *  no qual é onde o server espera pelas conexões
// *  e também estamos definindo um callback que vai chamar
// *  onConnected (caso conecte/dê certo)
// *  ou o onError(caso de erro de conexão)
// * */
//
// //lembrar que eu fiz o teste de dropar as tabelas e está funfando corretamente
// const connect = () => {
//     const Stomp = require("stompjs")
//      let SockJS = require("sockjs-client")
//      let  sockJS = new SockJS("http://localhost:8080/ws")
//      let ws = new WebSocket("ws://localhost:8080/ws")
//     stompClient = Stomp.overWS(sockJS)
//     stompClient.connect({}, onConnected, onError);
//
// };
//
// const onConnected = () => {
//     console.log("connected...")
//
//     stompClient.subscribe(
//         // "/user/match/"+currentUser.id+"/queue/messages",
//         "/api/chat/app/user/send/"+currentUser.id+"/queue/messages",
//         onMessageReceived
//     );
// }
//
// function onMessageReceived(){
//
//
// }
// function onError(error){
//     console.log("Error:",error);
//     console.log("Error2:",err);
// }
//
// function sendMessage(msg) {
//     if(msg.trim() !=""){
//         const message = {
//             senderId:currentUser.id,
//             recipientId:receiverUser.id,
//             senderName: currentUser.name,
//             recipientName:receiverUser.name,
//             content:msg,
//             timestamp: new Date(),
//         }
//         stompClient.send("/api/chat/app/send",{},JSON.stringify(message))
//     }
//
// }