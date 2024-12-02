import 'dart:convert';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter/services.dart'; // Use this for loading assets
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/repo_provider.dart';

// Function to load products from JSON
Future<List<Product>> getProducts() async {
  final String fileContent =
      await rootBundle.loadString('assets/data/products.json');
  final List<dynamic> jsonList = jsonDecode(fileContent);
  return jsonList.map((json) => Product.fromJson(json)).toList();
}

// State provider to keep track of selected category
final selectedCategoryProvider = StateProvider<String?>((ref) => null);

// State provider to keep track of the search query
final searchQueryProvider = StateProvider<String>((ref) => '');

// Provider to expose the products, filtered by category and search query
final productsProvider = FutureProvider<List<Product>>((ref) async {
  final databaseRepo = await ref.watch(databaseProvider.future);
  final selectedCategory = ref.watch(selectedCategoryProvider);
  final searchQuery = ref.watch(searchQueryProvider).toLowerCase();

  final products =
      await databaseRepo.fetchProducts(searchQuery, selectedCategory ?? '');

  // Filter products by selected category
  // List<Product> filteredProducts = products;

  // if (selectedCategory != null && selectedCategory.isNotEmpty) {
  //   filteredProducts = filteredProducts
  //       .where((product) => product.category == selectedCategory)
  //       .toList();
  // }

  // // Further filter by search query
  // if (searchQuery.isNotEmpty) {
  //   filteredProducts = filteredProducts
  //       .where((product) => product.title.toLowerCase().contains(searchQuery))
  //       .toList();
  // }

  return products.whereType<Product>().toList();
});
