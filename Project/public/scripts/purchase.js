document.addEventListener('DOMContentLoaded', function () {
    const url = new URLSearchParams(window.location.search);
    const nId = url.get('id');
    const nQuantity = parseInt(url.get('pquantity'));
    const nName = url.get('pname');
    const nPrice = parseFloat(url.get('price'));
    const nImage = url.get('pimage');
    const userName = url.get('cname');
    let userBalance = parseFloat(url.get('cbalance'));
    console.log((userName))
    const productDetailsDiv = document.getElementById('one-purchase-container');
    productDetailsDiv.innerHTML = `
        <img src="/public/images/${nImage}" alt="Item Image">
        <h4>Product Name: ${nName}</h4>
        <h4>Product Price: ${nPrice}</h4>
        <p>Description: </p>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required min="1" max="${nQuantity}">
        <label for="shipping_address">Shipping Address:</label>
        <input type="text" id="shipping_address" name="shipping_address" required>
        <div class="buttons">
            <button id="purchase-now-btn">Purchase Now</button>
        </div>
    `;
  
    const purchaseNowButton = document.getElementById('purchase-now-btn');
    purchaseNowButton.addEventListener('click', function () {
        const quantityChosen=parseInt(document.getElementById('quantity').value);//get Exact Quantity
        const totalPrice=nPrice*quantityChosen;
                if (userBalance>=totalPrice) {//again depending on quantity chosen
                  userBalance=userBalance-totalPrice;
                    console.log(userBalance);
  
                    const fs=require('fs');//used to read and wrie to json
                    fs.readFile('customer.json', 'utf8', (err,data) => {
                        if (err) {
                            console.error('Not Able to Read File:', err);
                            return;}
                        try {
                            const users=JSON.parse(data);
                            const userFound=users.find(u=>u.name===userName);
                            console.log(userFound.name)
                            if (userFound){
                                userFound.balance=userBalance; // Update the user balance
                                //read and write back
                                fs.writeFile('customer.json', JSON.stringify(users, null, 2),(err)=>{
                                    if (err) {
                                        console.error('Error writing to customer.json file:', err);
                                        return;}
                                    console.log('Customer balance updated successfully.');
                                });
                            } else {
                                console.error('User not found in customer.json.');
                            }
                        } catch (err) {
                            console.error('Error parsing JSON data:', err);
                        }
                    });
  
  
                } else {
                    alert('Insufficient balance to make the purchase.');
                }
            })
  
    });
  
  