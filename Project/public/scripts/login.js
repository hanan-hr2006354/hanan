document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Get username and password
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    
    // Fetch user data from users.json (assuming it's already loaded)
    fetch('data/users.json')
    .then(response => response.json())
    .then(data => {
        var customers = data.customers;
        var seller = data.seller;
        var admin = data.admin;

        // Check if the user is a customer
        var loggedInCustomer = customers.find(customer => customer.username === username && customer.password === password);
        
        // Check if the user is a seller
        var loggedInSeller = seller.find(seller => seller.username === username && seller.password === password);
        
        // Check if the user is an admin
        var loggedInAdmin = admin.find(admin => admin.username === username && admin.password === password);
        
        // Redirect based on user type
        if (loggedInCustomer) {
            window.location.href = 'joia.html';
        } else if (loggedInSeller) {
            window.location.href = 'joia.html';
        } else if (loggedInAdmin) {
            window.location.href = 'joia.html';
        } else {
            document.getElementById('loginMessage').textContent = 'Invalid username or password.';
        }
    })
    .catch(error => console.error('Error fetching user data:', error));
});
