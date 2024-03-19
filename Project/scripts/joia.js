
let listProductHTML = document.querySelector('.category-necklace');
let products=[]
const initApp = () => {
    fetch('necklaces.json')
    .then(response => response.json())
    .then(data => {
        products = data;
        addDataToHTML();


    })
}
initApp();

const addDataToHTML = () => {
        if(products.length > 0) // if has data
        {
            products.forEach(product => {
                let newProduct = document.createElement('div');
                newProduct.dataset.id = product.id;
                newProduct.classList.add('item');
                newProduct.innerHTML = 
                `<div class="card">
                <img src="${product.image}" alt="bracelets"width="250" height="300">
                <p>${product.name}</p>
                <div class="price">$${product.price}</div>
                <div class="quantity">Quantity: ${product.quantity}</div>
                <button class="purchase">Purchase Item</button>
            </div> `;
                listProductHTML.appendChild(newProduct);
            });
        }
    }