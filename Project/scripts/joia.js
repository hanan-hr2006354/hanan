let listProductHTML = document.querySelector('.category-necklace');
let necklaces = [];

// const addDataToHTML = () => {
//     if (necklaces.length > 0) { // if has data
//         necklaces.forEach(necklace => {
//             let newNecklace = document.createElement('div');
//             newNecklace.dataset.id = necklace.id;
//             newNecklace.classList.add('card');
//             newNecklace.innerHTML =
//                 `<img src="${necklace.image}" alt="">
//                  <p>${necklace.name}</p>
//                  <div class="price">$${necklace.price}</div>
//                  <button class="purchase">Purchase Item</button>`;
//             listProductHTML.appendChild(newNecklace);
//         });
//     }
// };

const getNecklaceData = () => {
    fetch('./necklaces.json')
    .then(response=>response.json())
    .then(data => {
        necklaces = data;
        console.log(necklaces)
        // addDataToHTML();
        
    })
}
getNecklaceData();
