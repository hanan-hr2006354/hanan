import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/models/cart_item.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/cart_provider.dart';

class CartScreen extends ConsumerWidget {
  @override
  Widget build(BuildContext context, WidgetRef ref) {
    final cartItemsAsyncValue = ref.watch(cartProvider);
    final cart = ref.read(cartProvider.notifier);
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Text(
          "My Cart",
          style: TextStyle(
            fontSize: 20.0,
            fontWeight: FontWeight.bold,
          ),
        ),
        centerTitle: true,
        backgroundColor: Colors.white,
      ),
      body: cartItemsAsyncValue.when(
        loading: () => Center(child: CircularProgressIndicator()),
        error: (error, stackTrace) => Center(
          child: Text('Error: $error'),
        ),
        data: (cartItems) {
          return cartItems.isEmpty
              ? Center(
                  child: Text('Your cart is empty'),
                )
              : Column(
                  children: [
                    Expanded(
                      child: ListView.builder(
                        itemCount: cartItems.length,
                        itemBuilder: (context, index) {
                          final cartItem = cartItems[index];
                          return cart_card(cartItem, ref);
                        },
                      ),
                    ),
                    // Add the "Go to Checkout" box here
                    FutureBuilder<double?>(
                      future:
                          cart.totalPrice, // Assuming totalPrice is a Future
                      builder: (context, snapshot) {
                        if (snapshot.connectionState ==
                            ConnectionState.waiting) {
                          return CircularProgressIndicator();
                        } else if (snapshot.hasError) {
                          return Text('Error: ${snapshot.error}');
                        } else if (snapshot.hasData) {
                          final totalPrice = snapshot.data!;
                          return Padding(
                            padding: const EdgeInsets.all(16.0),
                            child: Container(
                              width: double
                                  .infinity, // Make the container full width
                              decoration: BoxDecoration(
                                color: Colors.green, // Green background color
                                borderRadius: BorderRadius.circular(
                                    10.0), // Rounded corners
                              ),
                              padding:
                                  const EdgeInsets.all(16.0), // Inner padding
                              child: Center(
                                // Center the text
                                child: Text(
                                  'Go to Checkout: ${totalPrice} QR', // Display total price
                                  style: TextStyle(
                                    fontSize: 20,
                                    fontWeight: FontWeight.bold,
                                    color: Colors
                                        .white, // White text color for contrast
                                  ),
                                  textAlign:
                                      TextAlign.center, // Center align the text
                                ),
                              ),
                            ),
                          );
                          //return Text('Go to Checkout: ${totalPrice} QR');
                        }
                        return Container(); // Return a default container if none of the states match
                      },
                    ),
                  ],
                );
        },
      ),
    );
  }
}

Card cart_card(CartItem cartItem, WidgetRef ref) {
  // Fetch the product asynchronously based on the cart item
  final productFuture = ref.read(cartProvider.notifier).getProduct(cartItem);

  return Card(
    elevation: 2, // Adds shadow for depth
    margin: EdgeInsets.symmetric(vertical: 8, horizontal: 16), // Spacing
    color: Colors.white, // Card background color
    child: Padding(
      padding: const EdgeInsets.all(16.0), // Add padding inside the card
      child: FutureBuilder<Product?>(
        future: productFuture, // Future<Product?> here
        builder: (context, snapshot) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return Center(child: CircularProgressIndicator());
          } else if (snapshot.hasError) {
            return Center(child: Text('Error: ${snapshot.error}'));
          } else if (!snapshot.hasData || snapshot.data == null) {
            return Center(child: Text('No product found'));
          }

          final product = snapshot.data!; // Unwrap the product data

          return Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              // Product image
              Image.asset(
                "assets/images/${product.imageName}",
                width: 80,
                height: 80,
              ),
              SizedBox(width: 15),

              // Product details
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        // Product title
                        Container(
                          constraints: BoxConstraints(maxWidth: 200),
                          child: Text(
                            product.title,
                            style: TextStyle(
                              fontSize: 18,
                              fontWeight: FontWeight.bold,
                            ),
                            maxLines: 1,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ),
                        Spacer(),
                        // Remove product button
                        IconButton(
                          icon: Icon(Icons.close),
                          onPressed: () {
                            ref
                                .read(cartProvider.notifier)
                                .removeProduct(product);
                          },
                        ),
                      ],
                    ),
                    // Price per unit
                    Text(
                      "QR ${product.price} / unit",
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Row(
                      children: [
                        // Decrease quantity button
                        Container(
                          width: 30,
                          height: 30,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            border: Border.all(
                              color: Colors.grey,
                            ),
                          ),
                          child: Center(
                            child: IconButton(
                              padding: EdgeInsets.zero,
                              iconSize: 18,
                              icon: Icon(Icons.remove),
                              onPressed: () {
                                ref
                                    .read(cartProvider.notifier)
                                    .decreaseQuantity(product);
                              },
                            ),
                          ),
                        ),
                        SizedBox(width: 10),
                        // Display quantity
                        Text("${cartItem.quantity}"),
                        SizedBox(width: 10),
                        // Increase quantity button
                        Container(
                          width: 30,
                          height: 30,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(10.0),
                            border: Border.all(
                              color: Colors.grey,
                            ),
                          ),
                          child: Center(
                            child: IconButton(
                              padding: EdgeInsets.zero,
                              iconSize: 18,
                              icon: Icon(
                                Icons.add,
                                color: Colors.green,
                              ),
                              onPressed: () {
                                ref
                                    .read(cartProvider.notifier)
                                    .addProduct(product);
                              },
                            ),
                          ),
                        ),
                        Spacer(),
                        // Total price for this product
                        Text(
                          "QR ${(product.price * cartItem.quantity).toStringAsFixed(3)}",
                          style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            ],
          );
        },
      ),
    ),
  );
}
