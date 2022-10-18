importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.9.2/firebase-messaging.js');

// Initialize Firebase
const firebaseConfig = {
    apiKey: "AIzaSyCcXQ3O5vYBt0gq_o6-IEEGrlQ_Vqw5RPI",
    authDomain: "mdnf-notification-system.firebaseapp.com",
    projectId: "mdnf-notification-system",
    storageBucket: "mdnf-notification-system.appspot.com",
    messagingSenderId: "572040568814",
    appId: "1:572040568814:web:bdbedb7fc5f964c22bb8ff",
    measurementId: "G-SGGB9W88HG"
};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();