const userName = localStorage.getItem('loggedInUser');

document.addEventListener('DOMContentLoaded', function () {
    const url = new URLSearchParams(window.location.search);
    const nId = url.get('id');
    const nQuantity = parseInt(url.get('pquantity'));
    const nName = url.get('pname');
    const nPrice = parseFloat(url.get('price'));
    const nImage = url.get('pimage');
    let userBalance = parseFloat(url.get('cbalance'));

    console.log(userName);

    const productDetailsDiv = document.getElementById('one-purchase-container');
    productDetailsDiv.innerHTML = `
        <img src="/public/images/${nImage}" alt="Item Image">
        <h4 class="name">Product Name: ${nName}</h4>
        <h4 class="price">Product Price: ${nPrice}</h4>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required min="1" max="${nQuantity}">
        <label for="shipping_address">Shipping Address:</label>
        <input type="text" id="shipping_address" name="shipping_address" required>
        <div class="buttons">
            <button id="purchase-now-btn">Purchase Now</button>
        </div>
    `;

    const purchaseNowButton=document.getElementById('purchase-now-btn');
    let allusers=JSON.parse(localStorage.getItem('users'));
    if (!allusers){
        allusers={customers:[],seller:[]}; // Initialize empty users object if it doesn't exist
    }

    const items = localStorage.getItem('items');
    let arrayOfItems = [];
    
    if (items) {
        arrayOfItems = JSON.parse(items); //prevents overwriting changes
    } else {
        fetch('data/items.json')
            .then(response => response.json())
            .then(data => {
                arrayOfItems = data;
                localStorage.setItem('items', JSON.stringify(arrayOfItems));
            });
        
    }
    const choosenCategory = localStorage.getItem('choosenCategory');
    console.log(choosenCategory);
   
let listOfChoosen=[];
let category = [];
if (choosenCategory == 1) {
    category = arrayOfItems.find(item =>item.category==="necklaces");
    
}
if (choosenCategory == 2) {
    category = arrayOfItems.find(item => item.category==="bracelets");
}
if (choosenCategory == 3) {
    category = arrayOfItems.find(item => item.category==="rings");
}
if (choosenCategory == 4) {
    category = arrayOfItems.find(item => item.category==="earrings");
}

if (category) { listOfChoosen = category.items;}
 

    console.log(listOfChoosen)
    purchaseNowButton.addEventListener('click', function () {
        const quantityChosen=parseInt(document.getElementById('quantity').value);
        const totalPrice=nPrice*quantityChosen;
        const loggedInUserIndex=allusers.customers.findIndex(user => user.username === userName);
        if (loggedInUserIndex!==-1) { 
            const loggedInUser=allusers.customers[loggedInUserIndex];
            if (loggedInUser.balance>=totalPrice) {
                loggedInUser.balance-=totalPrice; 
                allusers.customers[loggedInUserIndex]=loggedInUser; 
                localStorage.setItem('users',JSON.stringify(allusers)); 
                console.log("User balance updated:",loggedInUser.balance);      
                //update Quanity
                const chosenProduct = listOfChoosen.find(product=>product.id === parseInt(nId));
                console.log(chosenProduct)
                if(chosenProduct){
                    chosenProduct.quantity -= quantityChosen;
                if (chosenProduct.quantity === 0) {
                    chosenProduct.quantity = "sold"; // Set status to "sold" if quantity becomes zero
                }
                console.log("Item quantity updated:",chosenProduct.quantity);

                const indexOfChosenProduct = listOfChoosen.findIndex(product => product.id === chosenProduct.id);
                if (indexOfChosenProduct !== -1) {
                    listOfChoosen[indexOfChosenProduct] = chosenProduct;
                }
                localStorage.setItem('items', JSON.stringify(arrayOfItems));

            }

            //Add customer's purchase item in purchaselist.
            const addItemToHistory={
                id:chosenProduct.id,
                name:chosenProduct.name,
                price:chosenProduct.price*quantityChosen,
                image:chosenProduct.image,
                quantity:quantityChosen }
                console.log(addItemToHistory);

                if (!Array.isArray(loggedInUser.purchase)) {loggedInUser.purchase=[];}
                loggedInUser.purchase.push(addItemToHistory);                
                localStorage.setItem('users',JSON.stringify(allusers)); 

             // Update the quantity of seller
            allusers.seller.forEach(seller => {
            const sellingIndex=seller.sellings.findIndex(item=>item.id===parseInt(nId));
            if (sellingIndex!==-1) {
            seller.sellings[sellingIndex].quantity -=quantityChosen;
            if (seller.sellings[sellingIndex].quantity === 0) {
                seller.sellings[sellingIndex].quantity = "sold";
            }

            // Push sale information into purchaseusernames
            const saleinfo={
                username:loggedInUser.username, 
                purchase:addItemToHistory
            };
            seller.sellings[sellingIndex].purchaseusernames.push(saleinfo);
            localStorage.setItem('users', JSON.stringify(allusers));
        }
    });

                window.location.href = '/public/joia.html';

            } else {
                alert('Insufficient balance to make the purchase.');
            }
        } else {
            console.error('User not found!');
        }
    });
    
});