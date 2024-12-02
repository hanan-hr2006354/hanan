import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/cart_provider.dart'; // Import your cart provider
import 'package:quickmart/providers/favorites_provider.dart';

// A StateProvider to hold the local quantity with autoDispose
final productQuantityProvider = StateProvider.autoDispose<int>((ref) => 1);
final productDescriptionExpandedProvider = StateProvider.autoDispose<bool>(
    (ref) => false); // For description expansion

class ProductDetailsScreen extends ConsumerWidget {
  final Product product; // Accept the entire product object

  ProductDetailsScreen({required this.product}); // Modify constructor

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Get the current quantity state
    int quantity = ref.watch(productQuantityProvider);
    final isDescriptionExpanded = ref.watch(productDescriptionExpandedProvider);
    //this line saved my life
    ref.refresh(isFavoriteStreamProvider(product));
    final isFavoriteAsyncValue = ref.watch(isFavoriteStreamProvider(product));

    return Scaffold(
      backgroundColor: Colors.white,
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(350),
        child: AppBar(
          flexibleSpace: Container(
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage("assets/images/${product.imageName}"),
                fit: BoxFit.cover,
              ),
            ),
          ),
          actions: [Icon(Icons.share)],
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // First partition (Product title, price, quantity row)
            Expanded(
              flex: 2, // Largest partition
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Container(
                        constraints: BoxConstraints(
                            maxWidth: 300), // Set max width as needed
                        child: Text(
                          product.title,
                          style: TextStyle(
                              fontSize: 24, fontWeight: FontWeight.bold),
                          maxLines: 1, // Limit to 1 line
                          overflow: TextOverflow
                              .ellipsis, // Add ellipsis for overflow
                        ),
                      ),
                      Spacer(),
                      isFavoriteAsyncValue.when(
                        data: (isFavorite) {
                          return IconButton(
                            icon: Icon(
                              isFavorite ?? false
                                  ? Icons.favorite
                                  : Icons.favorite_border,
                              color: isFavorite == true
                                  ? Colors.green
                                  : Colors.grey,
                            ),
                            onPressed: () {
                              // Toggle favorite status
                              ref
                                  .read(favoritesProvider.notifier)
                                  .toggleFavorite(product);
                            },
                          );
                        },
                        loading: () =>
                            CircularProgressIndicator(), // Loading state
                        error: (e, stack) =>
                            Icon(Icons.favorite_border), // Error state
                      ),
                    ],
                  ),
                  Text(
                    'Price: ${product.price} QR',
                    style: TextStyle(
                        fontSize: 20, color: Colors.grey.withOpacity(0.8)),
                  ),
                  SizedBox(height: 10),
                  // Quantity row
                  Row(
                    children: [
                      Container(
                        width: 30,
                        height: 30,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(10.0),
                          border: Border.all(color: Colors.grey),
                        ),
                        child: Center(
                          child: IconButton(
                            padding: EdgeInsets.zero,
                            iconSize: 18,
                            icon: Icon(Icons.remove),
                            onPressed: () {
                              if (quantity > 1) {
                                ref
                                    .read(productQuantityProvider.notifier)
                                    .state--;
                              }
                            },
                          ),
                        ),
                      ),
                      SizedBox(width: 10),
                      Text("$quantity"), // Display current local quantity
                      SizedBox(width: 10),
                      Container(
                        width: 30,
                        height: 30,
                        decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(10.0),
                          border: Border.all(color: Colors.grey),
                        ),
                        child: Center(
                          child: IconButton(
                            padding: EdgeInsets.zero,
                            iconSize: 18,
                            icon: Icon(Icons.add, color: Colors.green),
                            onPressed: () {
                              ref
                                  .read(productQuantityProvider.notifier)
                                  .state++;
                            },
                          ),
                        ),
                      ),
                      Spacer(),
                      Text(
                        "QR ${(product.price * quantity).toStringAsFixed(3)}",
                        style: TextStyle(
                            fontSize: 20, fontWeight: FontWeight.bold),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            Divider(),

            // Product Description section
            ListTile(
              title: Text("Product Detail",
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
              trailing: Icon(
                isDescriptionExpanded ? Icons.expand_less : Icons.expand_more,
                size: 35,
              ),
              onTap: () {
                ref.read(productDescriptionExpandedProvider.notifier).state =
                    !isDescriptionExpanded;
              },
            ),
            if (isDescriptionExpanded)
              Padding(
                padding: const EdgeInsets.all(5.0),
                child: Text(
                  product
                      .description, // Assuming product has a description field
                  style: TextStyle(fontSize: 16),
                ),
              ),
            Divider(),

            // Second partition (Nutrition section)
            ListTile(
              title: Text("Nutrition",
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
              trailing:
                  Icon(Icons.arrow_forward_ios, size: 20), // Uniform arrow size
              onTap: () {
                // there are no taps >:3
              },
            ),
            Divider(),

            // Third partition (Rating section)
            ListTile(
              title: Text("Review",
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
              trailing: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  // Display stars based on the rating value (assuming product.rating is an integer)
                  RatingBarIndicator(
                    rating: product.rating.toDouble(),
                    itemBuilder: (context, index) => Icon(
                      Icons.star,
                      color: Colors.orange,
                    ),
                    itemCount: 5,
                    itemSize: 24.0,
                  ),
                  Icon(Icons.arrow_forward_ios, size: 20), // Uniform arrow size
                ],
              ),
              onTap: () {
                // nothing here :3
              },
            ),

            // Add to Cart button
            SizedBox(height: 20), // Space before the button
            Center(
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.green, // Button background color
                  padding: EdgeInsets.symmetric(
                      horizontal: 40), // Horizontal padding
                  fixedSize: Size(400, 50), // Fixed button size for uniformity
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0), // Circular edges
                  ),
                ),
                onPressed: () {
                  // Add the quantity of that item to the cart
                  ref.read(cartProvider.notifier).addProducts(
                      product, quantity); // Adds items based on quantity
                },
                child: Text(
                  "Add to Cart",
                  style: TextStyle(
                      fontSize: 18,
                      color: Colors.white), // Dark gray text color
                  textAlign: TextAlign.center, // Center align the text
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
