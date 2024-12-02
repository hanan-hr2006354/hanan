import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/cart_provider.dart';
import 'package:quickmart/providers/favorites_provider.dart'; // Assuming you have a ProductItem widget to display each product

class FavoritesScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Watch the favorites provider to get the current list of favorite products
    final favorites = ref.watch(favoritesProvider);

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Text(
          'Favorites',
          style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
      ),
      body: favorites.when(
        data: (favoritesList) {
          // When the data is available, display the list or empty view
          return favoritesList.isEmpty
              ? empty_list()
              : Column(
                  children: [
                    Expanded(
                      child: ListView.builder(
                        itemCount: favoritesList.length,
                        itemBuilder: (context, index) {
                          final product = favoritesList[index];
                          return product_card(product, ref);
                        },
                      ),
                    ),
                    GestureDetector(
                      onTap: () {
                        favoritesList.forEach((product) {
                          ref.read(cartProvider.notifier).addProduct(product);
                        });
                      },
                      child: checkout_button(),
                    ),
                  ],
                );
        },
        loading: () => Center(
            child:
                CircularProgressIndicator()), // Show loading indicator while waiting for data
        error: (error, stackTrace) =>
            Center(child: Text('Error: $error')), // Handle errors
      ),
    );
  }
}

Padding checkout_button() {
  return Padding(
    padding: const EdgeInsets.all(16.0),
    child: Container(
      width: double.infinity, // Make the container full width
      decoration: BoxDecoration(
        color: Colors.green, // Green background color
        borderRadius: BorderRadius.circular(10.0), // Rounded corners
      ),
      padding: const EdgeInsets.all(16.0), // Inner padding
      child: Center(
        // Center the text
        child: Text(
          'Add all to cart', // Display total price
          style: TextStyle(
            fontSize: 20,
            fontWeight: FontWeight.bold,
            color: Colors.white, // White text color for contrast
          ),
          textAlign: TextAlign.center, // Center align the text
        ),
      ),
    ),
  );
}

Card product_card(Product product, WidgetRef ref) {
  return Card(
    elevation: 2,
    margin: EdgeInsets.symmetric(vertical: 8, horizontal: 16), // Spacing
    color: Colors.white, //
    child: Padding(
      padding: const EdgeInsets.all(16.0), // Add padding inside the card
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          // Image on the left
          Image.asset(
            "assets/images/${product.imageName}",
            width: 80, // Increased image size
            height: 80,
          ),
          SizedBox(
            width: 15,
          ),

          // Title and Price in the middle
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Container(
                      constraints: BoxConstraints(
                          maxWidth: 200), // Set a max width if needed
                      child: Text(
                        product.title,
                        style: TextStyle(
                          fontSize: 18, // Make the title larger
                          fontWeight: FontWeight.bold,
                        ),
                        maxLines: 1,
                        overflow: TextOverflow.ellipsis,
                      ),
                    ),
                    const Spacer(), // Pushes the button to the end
                    IconButton(
                      icon: Icon(Icons.close),
                      onPressed: () {
                        ref
                            .read(favoritesProvider.notifier)
                            .toggleFavorite(product);
                      },
                    ),
                  ],
                ),
                Text("QR ${product.price} / unit",
                    style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey,
                        fontWeight: FontWeight.bold)),
              ],
            ),
          ),
          // X icon on the right to remove item
        ],
      ),
    ),
  );
}

Column empty_list() {
  return Column(
    mainAxisAlignment: MainAxisAlignment.center,
    children: [
      Icon(
        Icons.favorite,
        size: 100,
        color: Colors.grey.withOpacity(0.35),
      ),
      Text(
        'No products in your favorites',
        style: TextStyle(fontSize: 30, color: Colors.grey.withOpacity(0.35)),
      ),
      const Spacer(),
      Padding(
        padding: const EdgeInsets.all(16.0),
        child: Container(
          width: double.infinity, // Make the container full width
          decoration: BoxDecoration(
            color: Colors.grey.withOpacity(0.35), // Green background color
            borderRadius: BorderRadius.circular(10.0), // Rounded corners
          ),
          padding: const EdgeInsets.all(16.0), // Inner padding
          child: Center(
            // Center the text
            child: Text(
              'Add all to cart', // Display total price
              style: TextStyle(
                fontSize: 20,
                fontWeight: FontWeight.bold,
                color: Colors.white, // White text color for contrast
              ),
              textAlign: TextAlign.center, // Center align the text
            ),
          ),
        ),
      ),
    ],
  );
}
