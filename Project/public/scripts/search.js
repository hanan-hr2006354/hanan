// index.js
// import Server from './server.js'; // Adjust the path if your server.js is in a different directory

// const server = new Server();
// server.start();


// // Function to search items
// function searchItems() {
//     const searchInput = document.getElementById('searchInput').value.toLowerCase();
//     const searchResults = document.getElementById('searchResults');
//     searchResults.innerHTML = ''; // Clear previous search results

//     // Construct URL with query parameter for search
//     const url = new URL(window.location.origin + '/search-items');
//     url.searchParams.append('query', searchInput);

//     // Send search request to the server using GET method
//     fetch(url, {
//         method: 'GET', // Adjusted to GET
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//     .then(response => {
//         if (!response.ok) {
//             throw new Error('Search failed');
//         }
//         return response.json(); // Parse response as JSON
//     })
//     .then(data => {
//         // Display search results
//         data.forEach(item => {
//             const itemElement = document.createElement('div');
//             itemElement.innerHTML = `
//                 <p>Name: ${item.name}</p>
//                 <p>Category: ${item.category}</p>
//                 <p>Price: $${item.price}</p>
//                 <p>Description: ${item.description}</p> <!-- Removed $ sign before ${item.description} -->
//                 <p>Seller: ${item.seller}</p>
//             `;
//             searchResults.appendChild(itemElement);
//         });
//     })
//     .catch(error => {
//         console.error('Error:', error); // Log any errors
//         searchResults.innerHTML = `<p>Error performing search. Please try again.</p>`;
//     });
// }



function searchItems() {
    // Get the search input value
    var searchText = document.getElementById('searchInput').value.toLowerCase();
    
    // Fetch the items data from items.json
    fetch('/public/data/items.json')
    .then(response => response.json())
    .then(data => {
        var items = data.items;
        
        // Filter items based on the search text
        var filteredItems = items.filter(item => {
            // Convert item name and category to lowercase for case-insensitive search
            var itemName = item.name.toLowerCase();
            var itemCategory = item.category.toLowerCase();
            
            // Check if the item name or category contains the search text
            return itemName.includes(searchText) || itemCategory.includes(searchText);
        });
        
        // Display the search results
        displaySearchResults(filteredItems);
    })
    .catch(error => console.error('Error fetching items data:', error));
}

function displaySearchResults(items) {
    var searchResultsDiv = document.getElementById('searchResults');
    
    // Clear previous search results
    searchResultsDiv.innerHTML = '';
    
    // Check if any items match the search criteria
    if (items.length === 0) {
        searchResultsDiv.textContent = 'No items found.';
        return;
    }
    
    // Create a list to display search results
    var resultList = document.createElement('ul');
    
    // Iterate over the filtered items and create list items to display them
    items.forEach(item => {
        var listItem = document.createElement('li');
        listItem.textContent = `${item.name} - Category: ${item.category}, Price: ${item.price}`;
        resultList.appendChild(listItem);
    });
    
    // Append the list of search results to the search results div
    searchResultsDiv.appendChild(resultList);
}
