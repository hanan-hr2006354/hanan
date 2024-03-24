document.addEventListener('DOMContentLoaded', function() {
    const addButton = document.getElementById('add');

    addButton.addEventListener('click', function() {
        // Retrieve values from input fields
        const itemImage = document.getElementById('itemimage').value;
        const itemName = document.getElementById('itemname').value;
        const itemPrice = document.getElementById('itemprice').value;
        const itemQuantity = document.getElementById('itemquantity').value;
        
        const newItem = {
            image: itemImage,
            name: itemName,
            price: itemPrice,
            quantity: itemQuantity
        };
        
        const sellerUsername = localStorage.getItem('loggedInUser');
        fetch('/update-users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newItem)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Data updated successfully:', data);
            // You can handle success response here if needed
        })
        .catch(error => {
            console.error('Error updating data:', error);
            // You can handle error here if needed
        });
        
        // Clear input fields after adding the item
        document.getElementById('itemimage').value = '';
        document.getElementById('itemname').value = '';
        document.getElementById('itemprice').value = '';
        document.getElementById('itemquantity').value = '';
    });
});
