import 'package:floor/floor.dart';

// the primary key would be the same as the category name
@Entity(tableName: "categories")
class CateGory {
  @primaryKey
  final String id;
  final String category;

  CateGory({required this.id, required this.category});
}
