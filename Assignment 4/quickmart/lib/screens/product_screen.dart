import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:go_router/go_router.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/cart_provider.dart';
import 'package:quickmart/providers/product_provider.dart';
import 'package:quickmart/providers/category_provider.dart';
import 'package:quickmart/providers/repo_provider.dart';
//import 'package:quickmart/routes/app_router.dart';

class ProductScreen extends ConsumerWidget {
  const ProductScreen({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {
    // Watch the productsProvider for changes
    final productsAsyncValue = ref.watch(productsProvider);

    return Scaffold(
      appBar: appbar(ref), // Pass ref to the appbar method
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.all(20),
        child: productsAsyncValue.when(
          data: (productList) {
            return GridView.builder(
              itemCount: productList.length,
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2, // Static 2 columns for all screen sizes
                mainAxisSpacing: 20,
                crossAxisSpacing: 20,
                childAspectRatio: 0.7, // Adjust this to tweak card aspect ratio
              ),
              itemBuilder: (context, index) {
                return GestureDetector(
                  onTap: () {
                    // Navigate to the ProductDetailsScreen and pass the Product object
                    context.push('/product', extra: productList[index]);
                  },
                  child: product_container(productList, index, ref),
                );
              },
            );
          },
          loading: () => const Center(child: CircularProgressIndicator()),
          error: (error, stack) => Center(child: Text('Error: $error')),
        ),
      ),
    );
  }

  AppBar appbar(WidgetRef ref) {
    // Accept ref as a parameter
    return AppBar(
      title: Padding(
        padding: const EdgeInsets.all(10),
        child: TextField(
          decoration: InputDecoration(
            hintText: 'Search...',
            prefixIcon: const Icon(Icons.search),
            border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(10),
            ),
          ),
          onChanged: (value) {
            ref.read(searchQueryProvider.notifier).state =
                value; // Use read instead of watch for setting state
          },
        ),
      ),
      toolbarHeight: 80, // Set your desired height here
      actions: [
        Consumer(builder: (context, ref, child) {
          final categories = ref.watch(categoriesProvider);

          return categories.when(
            data: (cat) {
              return PopupMenuButton<String>(
                icon: const Icon(
                  Icons.tune,
                  color: Colors.green,
                ),
                onSelected: (value) {
                  ref.read(selectedCategoryProvider.notifier).state = value;
                },
                itemBuilder: (BuildContext context) {
                  return cat.map<PopupMenuItem<String>>((String value) {
                    return PopupMenuItem<String>(
                      value: value,
                      child: Text(value),
                    );
                  }).toList();
                },
              );
            },
            error: (error, stackTrace) =>
                Text('Error: ${error.toString()}'), // Handle error state
            loading: () =>
                const CircularProgressIndicator(), // Handle loading state
          );
        }),
      ],
    );
  }

  Container product_container(
      List<Product> productList, int index, WidgetRef ref) {
    return Container(
      padding: const EdgeInsets.all(10),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        border: Border.all(color: Colors.grey.withOpacity(0.4), width: 2.5),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Image.asset(
            "assets/images/${productList[index].imageName}",
            width: 200, // Static width for images
            height: 90, // Static height for images
          ),
          const SizedBox(height: 10),
          Text(
            productList[index].title,
            style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
            maxLines: 2,
            overflow: TextOverflow.ellipsis,
          ),
          const SizedBox(height: 5),
          Text(
            productList[index].categoryId,
            style: TextStyle(color: Colors.black.withOpacity(0.5)),
          ),
          const Spacer(),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                "QR ${productList[index].price}",
                style: const TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              const Spacer(),
              ElevatedButton(
                onPressed: () {
                  ref
                      .read(cartProvider.notifier)
                      .addProduct(productList[index]);
                },
                style: ElevatedButton.styleFrom(
                  padding: const EdgeInsets.symmetric(
                      horizontal: 6, vertical: 2), // Reduced padding
                  backgroundColor: Colors.green,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(5),
                  ),
                  minimumSize: const Size(24, 24), // Reduced minimum size
                ),
                child: const Icon(
                  Icons.add,
                  color: Colors.white,
                  size: 16, // Adjusted icon size
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
