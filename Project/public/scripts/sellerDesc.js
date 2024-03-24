const selectedProduct=JSON.parse(localStorage.getItem('selectedProduct'));

if (selectedProduct) {
    document.getElementById('itemImage').src = "/public/images/"+selectedProduct.image;
    document.getElementById('itemname').value = selectedProduct.name;
    document.getElementById('itemprice').value = selectedProduct.price;
    document.getElementById('itemquantity').value = selectedProduct.quantity;
} else {
    // If no product information is found, display a message
    document.getElementById('middle-section').innerHTML = '<h1>No item selected</h1>';
}

