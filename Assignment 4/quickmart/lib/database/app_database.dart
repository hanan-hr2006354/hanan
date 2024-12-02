import 'dart:async';
import 'package:floor/floor.dart';
import 'package:quickmart/database/cart_dao.dart';
import 'package:quickmart/database/category_dao.dart';
import 'package:quickmart/database/favorite_dao.dart';
import 'package:quickmart/database/product_dao.dart';
import 'package:quickmart/database/user_dao.dart';
import 'package:quickmart/models/cart_item.dart';
import 'package:quickmart/models/favourite.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/models/user.dart';
import 'package:quickmart/models/category.dart';
import 'package:sqflite/sqflite.dart' as sqflite;

part 'app_database.g.dart';

@Database(version: 2, entities: [CartItem, CateGory, Favourite, Product, User])
abstract class AppDatabase extends FloorDatabase {
  CartDao get cartDao;
  CategoryDao get categoryDao;
  FavoriteDao get favoriteDao;
  ProductDao get productDao;
  UserDao get userDao;
}
