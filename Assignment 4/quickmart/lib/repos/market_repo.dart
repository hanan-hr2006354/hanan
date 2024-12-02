import 'dart:ffi';

import 'package:dio/dio.dart';
import 'package:quickmart/database/cart_dao.dart';
import 'package:quickmart/database/category_dao.dart';
import 'package:quickmart/database/favorite_dao.dart';
import 'package:quickmart/database/product_dao.dart';
import 'package:quickmart/database/user_dao.dart';
import 'package:quickmart/models/cart_item.dart';
import 'package:quickmart/models/category.dart';
import 'package:quickmart/models/favourite.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/models/user.dart';

class MarketRepo {
  final _dio =
      Dio(BaseOptions(baseUrl: "https://quickmart.codedbyyou.com/api/"));
  final CartDao cartDao;
  final CategoryDao categoryDao;
  final FavoriteDao favoriteDao;
  final ProductDao productDao;
  final UserDao userDao;

  MarketRepo(
      {required this.cartDao,
      required this.categoryDao,
      required this.favoriteDao,
      required this.productDao,
      required this.userDao});

  // CartDao methods

  // this does not work, throws error maybe because of lack of await
  // Future<void> addCartItem(CartItem cartItem) async {
  //   try {
  //     cartDao.addCartItem(cartItem);
  //   } catch (e) {
  //     final newQuantity =
  //         getCartItemQuantity(cartItem.productId, cartItem.userId);
  //     // i am so stupid for this but what can i do... ya rab
  //     newQuantity.first.then((quantity) async {
  //       print("inside here");
  //       if (quantity != null) {
  //         final newQuantity = quantity + cartItem.quantity;
  //         print(newQuantity);
  //         await updateQuantity(
  //             cartItem.productId, newQuantity, cartItem.userId);
  //       }
  //     });
  //   }
  // }

  Future<void> addCartItem(CartItem cartItem) async {
    try {
      // Attempt to add the cart item
      await cartDao.addCartItem(cartItem);
    } catch (e) {
      final quantity =
          await getCartItemQuantity(cartItem.productId, cartItem.userId).first;

      if (quantity != null) {
        final newQuantity = quantity + cartItem.quantity;
        print('Updating quantity to: $newQuantity');
        await updateQuantity(cartItem.productId, newQuantity, cartItem.userId);
      } else {
        print('No existing item found to update.');
      }
    }
  }

  Future<void> decreaseQuantity(CartItem cartItem) async {
    final currentQuantity =
        getCartItemQuantity(cartItem.productId, cartItem.userId);
    currentQuantity.first.then((quantity) async {
      if (quantity != null) {
        if (quantity > 1) {
          final newQuantity = quantity - cartItem.quantity;
          await updateQuantity(
              cartItem.productId, newQuantity, cartItem.userId);
        } else {
          await deleteCartItem(cartItem);
        }
      }
    });
  }

  Future<void> deleteCartItem(CartItem cartItem) =>
      cartDao.deleteCartItem(cartItem);
  Future<void> updateQuantity(
          String productId, int newQuantity, String userId) =>
      cartDao.updateQuantity(productId, newQuantity, userId);
  Stream<List<CartItem>> getCartItems(String userId) =>
      cartDao.getCartItems(userId);
  Stream<double?> getCartTotalPrice(String userId) =>
      cartDao.getCartTotalPrice(userId);
  Stream<int?> getCartItemQuantity(String productId, String userId) =>
      cartDao.getCartItemQuantity(productId, userId);
  Stream<double?> getItemTotalPrice(String cartItemId, String userId) =>
      cartDao.getItemTotalPrice(cartItemId, userId);
  Future<Product?> getProductInfo(String cartItemId, String userID) =>
      cartDao.getProductInfo(cartItemId, userID);

  // CategoryDao methods
  Future<int> addCategory(CateGory category) =>
      categoryDao.addCategory(category);
  Future<CateGory?> getCategories() => categoryDao.getCategories();

  // FavoriteDao methods
  // Future<void> toggleFavourite(Favourite favourite) async {
  //   // Check if the item is a fav
  //   final isFavouriteStream =
  //       favoriteDao.isFavourtie(favourite.productId, favourite.userId);
  //   final isFavourite = await isFavouriteStream.first;

  //   if (isFavourite == true) {
  //     await favoriteDao.deleteFavourite(favourite);
  //   } else {
  //     await favoriteDao.addFavourite(favourite);
  //   }
  // }

  // spaghetti but works.. wish i was never born
  Future<void> toggleFavourite(Favourite favourite) async {
    // Check if the item is a fav
    final isFavouriteStream =
        favoriteDao.isFavourtie(favourite.productId, favourite.userId);
    final isFavourite = await isFavouriteStream.first;
    // it used to ignore this without the null part btw :>
    if (isFavourite != null && isFavourite) {
      await favoriteDao.deleteFavourite(favourite.productId, favourite.userId);
    } else {
      print('Adding to favorites...');
      await favoriteDao.addFavourite(favourite);
    }
  }

  Future<int> addFavourite(Favourite favourite) =>
      favoriteDao.addFavourite(favourite);
  Future<void> deleteFavourite(Favourite favourite) =>
      favoriteDao.deleteFavourite(favourite.productId, favourite.userId);
  Stream<List<Product>> getFavourites(String userID) =>
      favoriteDao.getFavourites(userID);
  Stream<bool?> isFavourtie(String pid, String userID) =>
      favoriteDao.isFavourtie(pid, userID);

  // ProductDao methods
  Future<int> addProduct(Product product) => productDao.addProduct(product);
  Future<void> deleteProduct(Product product) => deleteProduct(product);
  Future<List<Product?>> fetchProducts(String name, String categoryId) =>
      productDao.fetchProducts(name, categoryId);

  // UserDao methods
  Future<int> addUser(User user) => userDao.addUser(user);
  Future<User?> getUserById(String id) => userDao.getUserById(id);
  Future<int> deleteUser(User user) => deleteUser(user);
  Stream<User?> getUsers() => userDao.getUsers();

  // populate products
  Future<void> initializeProducts() async {
    Response response = await _dio.get("products");
    if (response.statusCode == 200) {
      final responseData = response.data;
      for (var json in responseData) {
        try {
          await addProduct(Product.fromJson(json));
        } catch (error) {}
      }
    } else {
      throw Exception("Failed to fetch data");
    }
  }

  Future<void> initilaizeCategories() async {
    Response response = await _dio.get("categories");
    if (response.statusCode == 200) {
      final responseData = response.data;
      for (var json in responseData) {
        try {
          await addCategory(CateGory(id: json, category: json));
        } catch (error) {
          addCategory(CateGory(id: "Health", category: "meal"));
        }
      }
    } else {
      throw Exception("Failed to fetch data");
    }
  }
}
