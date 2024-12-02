import 'package:floor/floor.dart';
import 'package:quickmart/models/product.dart';
import 'dart:async';

@dao
abstract class ProductDao {
  @Query(
      "SELECT * FROM products WHERE (:name IS NULL OR title LIKE '%' || :name || '%') AND (:categoryId = '' or categoryId = :categoryId) ")
  Future<List<Product?>> fetchProducts(
    String name,
    String categoryId,
  );
  @insert
  Future<int> addProduct(Product product);
  @delete
  Future<void> deleteProduct(Product product);
}
