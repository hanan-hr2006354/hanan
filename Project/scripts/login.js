// Simulated users data (as if loaded from users.json)
const users = [
  { /* user objects as defined above */ }
];

function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Find the user by username and password
    const user = users.find(user => user.username === username && user.password === password);

    if (user) {
        // Redirect based on user type
        switch (user.type) {
            case 'customer':
                window.location.href = 'customer_main_page.html';
                break;
            case 'seller':
                window.location.href = 'seller_main_page.html';
                break;
            case 'admin':
                window.location.href = 'admin_dashboard.html';
                break;
            default:
                alert('Unknown user type');
        }
    } else {
        alert('Invalid username or password');
    }
}