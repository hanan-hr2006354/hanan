import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:path/path.dart';
import 'package:quickmart/database/app_database.dart';
import 'package:quickmart/models/user.dart';
import 'package:quickmart/repos/market_repo.dart';
import 'package:sqflite/sqflite.dart';

final databaseProvider = FutureProvider<MarketRepo>((ref) async {
  // // Delete the existing database file before rebuilding
  // final databasePath = await getDatabasesPath();
  // final dbPath = join(databasePath, "app_db");

  // // Delete the old database (if it exists)
  // await deleteDatabase(dbPath);

  final db = await $FloorAppDatabase.databaseBuilder("app_db").build();
  final repo = MarketRepo(
      cartDao: db.cartDao,
      categoryDao: db.categoryDao,
      favoriteDao: db.favoriteDao,
      productDao: db.productDao,
      userDao: db.userDao);
  await repo.initilaizeCategories();
  await repo.initializeProducts();
  try {
    await repo.addUser(User(
        "1234", "aisha", "al-kaabi", "aa2102900@", "1234", false, "image.png"));
  } catch (error) {}
  return repo;
});
