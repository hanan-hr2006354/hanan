const selectedProduct=JSON.parse(localStorage.getItem('selectedProduct'));
const loggedInUser=localStorage.getItem('loggedInUser');
const users=localStorage.getItem('users');
let allusers=[];
allusers=JSON.parse(users);


const items = localStorage.getItem('items');
let arrayOfItems = []; 
arrayOfItems=JSON.parse(items);

if (selectedProduct) {
    document.getElementById('itemImage').src="/public/images/"+selectedProduct.image;
    document.getElementById('itemname').value=selectedProduct.name;
    document.getElementById('itemprice').value=selectedProduct.price;
  document.getElementById('itemquantity').value=selectedProduct.quantity; 
  console.log(selectedProduct)
  //Leting seller update quantity
  const updating=JSON.parse(localStorage.getItem('sellerUpdating'));
    if (updating) {
        setTimeout(()=>{
            const additionalQuantity=prompt(`How much more quantity do you want to add for '${selectedProduct.name}'?`);
            if (additionalQuantity!==null) { // Check if user clicked 'Cancel'
                document.getElementById('itemquantity').value=(parseInt(selectedProduct.quantity)+parseInt(additionalQuantity));
            }
            localStorage.removeItem('sellerUpdating');
            
            const sellerIndex=allusers.seller.findIndex(seller=>seller.username===loggedInUser);
            if (sellerIndex!==-1) {
                const productIndex=allusers.seller[sellerIndex].sellings.findIndex(product=>product.id===selectedProduct.id);
                if (productIndex!==-1) {
                    allusers.seller[sellerIndex].sellings[productIndex].quantity=(parseInt(selectedProduct.quantity)+parseInt(additionalQuantity));
                    // Update data in local storage
                    localStorage.setItem('users', JSON.stringify(allusers));


                      // Update quantity in the items
                      arrayOfItems.forEach(category => {
                        const items=category.items;
                        const productIndex=items.findIndex(item =>item.id===selectedProduct.id);
                        if (productIndex!==-1) {
                            const newQuantity=parseInt(items[productIndex].quantity)+parseInt(additionalQuantity);
                            items[productIndex].quantity=newQuantity;
                        }
                        localStorage.setItem('items', JSON.stringify(arrayOfItems));

                    });

                    setTimeout(()=>{
                    window.location.href="/public/seller.html"
                    },3000);
                }
            }

            localStorage.removeItem('sellerUpdating');


        }, 5000);}

} else {
    document.getElementById('middle-section').innerHTML = '<h1>No item selected</h1>';
}


