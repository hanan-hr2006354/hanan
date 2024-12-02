import 'package:floor/floor.dart';
import 'package:quickmart/models/cart_item.dart';
import 'dart:async';

import 'package:quickmart/models/product.dart';
//2: Neeeded methods

// cart is unique for every user
@dao
abstract class CartDao {
  @Query('SELECT * FROM CartItems WHERE userId = :userId')
  Stream<List<CartItem>> getCartItems(String userId);
  @Query(
      'SELECT EXISTS(SELECT 1 FROM CartItems WHERE productID = :pid AND userId = :userId)')
  Stream<bool?> inCart(String pid, String userId);
  @insert
  Future<int> addCartItem(CartItem cartItem);
  @delete
  Future<void> deleteCartItem(CartItem cartItem);
  @Query(
      'UPDATE CartItems SET quantity = :newQuantity WHERE productId = :productId AND userId = :userId')
  Future<void> updateQuantity(String productId, int newQuantity, String userId);
  @Query('''
  SELECT p.price * c.quantity AS totalPrice 
  FROM CartItems c 
  JOIN products p ON c.productId = p.id 
  WHERE c.id = :cartItemId AND c.userId = :userId
''')
  Stream<double?> getItemTotalPrice(String cartItemId, String userId);
  // god save me..
  // we are using two tables here. p from product table and c from this table, thus we use JOIN

  @Query('''
    SELECT SUM(p.price * c.quantity) AS cartTotal 
    FROM CartItems c 
    JOIN products p ON c.productId = p.id 
    WHERE c.userId = :userId
  ''')
  Stream<double?> getCartTotalPrice(String userId);
  // we want to observe changes in quantity so we're using stream
  @Query(
      'SELECT quantity FROM CartItems WHERE productId = :productId AND userId = :userId')
  Stream<int?> getCartItemQuantity(String productId, String userId);
  @Query('''
  SELECT p.*
  FROM CartItems c
  JOIN products p ON c.productId = p.id
  WHERE c.id = :cartItemId AND userId = :userID
  ''')
  Future<Product?> getProductInfo(String cartItemId, String userID);
}
