import 'package:floor/floor.dart';
import 'package:quickmart/models/favourite.dart';
import 'dart:async';

import 'package:quickmart/models/product.dart';

@dao
abstract class FavoriteDao {
  @Query('''
  SELECT p.* FROM products p JOIN favourites f
    ON  f.productId = p.id 
    WHERE f.userId = :userId
  ''')
  Stream<List<Product>> getFavourites(String userId);
  @Query(
      'SELECT EXISTS(SELECT 1 FROM favourites WHERE productID = :pid AND userId = :userID)')
  Stream<bool?> isFavourtie(String pid, String userID);
  @insert
  Future<int> addFavourite(Favourite favourite);
  @Query('DELETE FROM favourites WHERE productID =:pid AND userId = :userID ')
  Future<void> deleteFavourite(String pid, String userID);
}
