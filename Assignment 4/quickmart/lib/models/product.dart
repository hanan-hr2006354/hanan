import 'package:floor/floor.dart';
import 'package:quickmart/models/category.dart';

//1
@Entity(tableName: "products", foreignKeys: [
  ForeignKey(
      childColumns: ['categoryId'], parentColumns: ['id'], entity: CateGory)
])
class Product {
  @primaryKey
  final String id;
  final String title;
  final String description;
  final double price;
  final String imageName;
  final String categoryId;
  final int rating;

  Product({
    required this.id,
    required this.title,
    required this.description,
    required this.price,
    required this.imageName,
    required this.categoryId,
    required this.rating,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'],
      title: json['title'],
      description: json['description'],
      price: json['price'],
      imageName: json['imageName'],
      categoryId: json['category'],
      rating: json['rating'],
    );
  }
  Map<String, dynamic> toJson() {
    return {
      'productId': id,
      'title': title,
      'description': description,
      'price': price,
      'imageName': imageName,
      'category': categoryId,
      'rating': rating,
    };
  }
}
