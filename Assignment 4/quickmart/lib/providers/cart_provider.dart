import 'dart:async';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/models/cart_item.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/repo_provider.dart';
import 'package:quickmart/repos/market_repo.dart';

final cartProvider =
    AsyncNotifierProvider<CartNotifier, List<CartItem>>(() => CartNotifier());

class CartNotifier extends AsyncNotifier<List<CartItem>> {
  late final MarketRepo _repo;
  @override
  Future<List<CartItem>> build() async {
    _repo = await ref.watch(databaseProvider.future);
    _repo.getCartItems("1234").listen((cartItems) async {
      state = AsyncData(cartItems);
    });
    return [];
  }

  void addProduct(Product product) {
    _repo.addCartItem(
        CartItem(userId: "1234", id: product.id, productId: product.id));
  }

  void addProducts(Product product, int quantity) {
    for (int i = 0; i < quantity; i++) {
      addProduct(product);
    }
  }

  void decreaseQuantity(Product product) {
    _repo.decreaseQuantity(
        CartItem(userId: "1234", id: product.id, productId: product.id));
  }

  void removeProduct(Product product) {
    _repo.deleteCartItem(
        CartItem(userId: "1234", id: product.id, productId: product.id));
  }

  void updateQuantity(Product product, int quantity) {
    _repo.updateQuantity(product.id, quantity, "1234");
  }

  Future<double?> get totalPrice async {
    return await _repo.getCartTotalPrice("1234").first;
  }

  Future<int?> getProductQuantity(Product product) {
    return _repo.getCartItemQuantity(product.id, "1234").first;
  }

  Future<Product?> getProduct(CartItem cartitem) {
    return _repo.getProductInfo(cartitem.id, "1234");
  }
}
