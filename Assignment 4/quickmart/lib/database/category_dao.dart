import 'package:floor/floor.dart';
import 'package:quickmart/models/category.dart';
import 'dart:async';

@dao
abstract class CategoryDao {
  @Query('SELECT * FROM categories')
  Future<CateGory?> getCategories();
  @insert
  Future<int> addCategory(CateGory category);
}
