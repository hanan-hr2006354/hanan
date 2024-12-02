import 'package:floor/floor.dart';
import 'package:quickmart/models/product.dart';
import 'package:quickmart/models/user.dart';

@Entity(tableName: 'favourites', foreignKeys: [
  ForeignKey(
      childColumns: ['productId'], parentColumns: ['id'], entity: Product),
  ForeignKey(childColumns: ['userId'], parentColumns: ['id'], entity: User)
])
class Favourite {
  @PrimaryKey(autoGenerate: true)
  final int? id;
  final String productId;
  final String userId;
  Favourite({this.id, required this.productId, required this.userId});
}
