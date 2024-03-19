// index.js
import Server from './server.js'; // Adjust the path if your server.js is in a different directory

const server = new Server();
server.start();


// Function to search items
function searchItems() {
    const searchInput = document.getElementById('searchInput').value.toLowerCase();
    const searchResults = document.getElementById('searchResults');
    searchResults.innerHTML = ''; // Clear previous search results

    // Construct URL with query parameter for search
    const url = new URL(window.location.origin + '/search-items');
    url.searchParams.append('query', searchInput);

    // Send search request to the server using GET method
    fetch(url, {
        method: 'GET', // Adjusted to GET
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Search failed');
        }
        return response.json(); // Parse response as JSON
    })
    .then(data => {
        // Display search results
        data.forEach(item => {
            const itemElement = document.createElement('div');
            itemElement.innerHTML = `
                <p>Name: ${item.name}</p>
                <p>Category: ${item.category}</p>
                <p>Price: $${item.price}</p>
                <p>Description: ${item.description}</p> <!-- Removed $ sign before ${item.description} -->
                <p>Seller: ${item.seller}</p>
            `;
            searchResults.appendChild(itemElement);
        });
    })
    .catch(error => {
        console.error('Error:', error); // Log any errors
        searchResults.innerHTML = `<p>Error performing search. Please try again.</p>`;
    });
}
