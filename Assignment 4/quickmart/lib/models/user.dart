import 'package:floor/floor.dart';

@Entity(
  tableName: 'users',
)
class User {
  @PrimaryKey()
  final String? id;
  final String firstName;
  final String lastName;
  final String email;
  final String password;
  final bool isAdmin;
  final String profilePic;

  User(this.id, this.firstName, this.lastName, this.email, this.password,
      this.isAdmin, this.profilePic);
}
