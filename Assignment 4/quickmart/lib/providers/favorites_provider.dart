import 'dart:async';
import 'dart:ffi';

import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/models/favourite.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/providers/repo_provider.dart';
import 'package:quickmart/repos/market_repo.dart';

// StateNotifier to manage favorites
class FavoritesNotifier extends AsyncNotifier<List<Product>> {
  late final MarketRepo _repo;

  @override
  Future<List<Product>> build() async {
    _repo = await ref.watch(databaseProvider.future);
    _repo.getFavourites("1234").listen((favs) async {
      state = AsyncData(favs);
    });
    return [];
  }

  Future<void> toggleFavorite(Product product) async {
    final fav = Favourite(productId: product.id, userId: "1234");
    // Toggle the favorite in the repository
    await _repo.toggleFavourite(fav);

    // After toggling, fetch the updated list of favorites and update the state
    _repo.getFavourites("1234").listen((favs) async {
      state = AsyncData(favs);
    });
  }

  Stream<bool?> isfavourite(Product product) {
    return _repo.isFavourtie(product.id, "1234");
  }

  // Add or remove product from favorites
  // void toggleFavorite(Product product) {
  //   if (state.contains(product)) {
  //     state = state.where((p) => p.id != product.id).toList();
  //   } else {
  //     state = [...state, product];
  //   }
  // }

  // // Check if a product is in favorites
  // bool isFavorite(Product product) {
  //   return state.contains(product);
  // }
}

// Provider
final favoritesProvider =
    AsyncNotifierProvider<FavoritesNotifier, List<Product>>(
        () => FavoritesNotifier());

// i am making a provider only to check if product is fav. family is when you take a method from another provider
final isFavoriteStreamProvider =
    StreamProvider.family<bool?, Product>((ref, product) {
  final notifier = ref.watch(favoritesProvider.notifier);
  // it borrows this method from the fav provider
  return notifier.isfavourite(product);
});
