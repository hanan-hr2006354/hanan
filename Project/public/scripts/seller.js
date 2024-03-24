
const loggedin=localStorage.getItem('loggedInUser');
console.log(loggedin)
let listProductHTML = document.querySelector('.seller-category');

// Fetch the JSON data
fetch('data/users.json')
    .then(response => response.json())
    .then(data => {
        // Access the "sellings" array within the "seller" array
        const sellerSellings = data.seller.map(seller => seller.sellings).flat();
        
        // Call a function to add the data to HTML
        addDataToHTML(sellerSellings);
    })
    .catch(error => {
        console.error('Error fetching JSON data:', error);
    });

// Function to add data to HTML:
const addDataToHTML = (sellings) => {
    if(sellings.length > 0) {
        sellings.forEach(product => {
            let newProduct = document.createElement('div');
            newProduct.dataset.id = product.id;
            newProduct.classList.add('item');
            newProduct.innerHTML = 
                `<div class="card">
                <img src="/public/images/${product.image}" width="250" height="300">
                    <p>${product.name}</p>
                    <div class="price">$${product.price}</div>
                    <div class="quantity">Quantity: ${product.quantity}</div>
                </div>`;
            listProductHTML.appendChild(newProduct);
             //After Clicking Set the information to sellerDesc.html
            newProduct.addEventListener('click', () => {
                localStorage.setItem('selectedProduct', JSON.stringify(product));
                window.location.href = 'sellerDesc.html';
            });
            
        });
    }
}
