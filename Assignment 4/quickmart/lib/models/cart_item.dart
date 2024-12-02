import 'package:floor/floor.dart';
import 'package:quickmart/models/user.dart';
import 'product.dart';

//1: Do Entity and STuff, Primary and Foreign Key.
@Entity(tableName: 'CartItems', foreignKeys: [
  ForeignKey(
      childColumns: ['productId'], parentColumns: ['id'], entity: Product),
  ForeignKey(childColumns: ['userID'], parentColumns: ['id'], entity: User)
])
class CartItem {
  @primaryKey
  final String id;
  final String productId;
  final int quantity;
  final String userId;

  CartItem({
    required this.userId,
    required this.id,
    required this.productId,
    this.quantity = 1,
  });

  // implement this in the dao's (plz don't forget please)
  // double get totalPrice {
  //   return product.price * quantity;
  // }

  // CopyWith method
  // CartItem copyWith({
  //   Product? product,
  //   int? quantity,
  // }) {
  //   return CartItem(
  //     product: product ?? this.product, // Use existing product if not provided
  //     quantity:
  //         quantity ?? this.quantity, // Use existing quantity if not provided
  //   );
  // }
}
