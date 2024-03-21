const url = new URLSearchParams(window.location.search);
console.log(window.location.search); // Log the entire query string
const userName = url.get('username');
console.log(userName); // Log the extracted username
window.location.href = `/public/purchase.html?username=${userName}`;

