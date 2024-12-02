import 'package:floor/floor.dart';
import 'package:quickmart/models/user.dart';
import 'dart:async';

@dao
abstract class UserDao {
  @Query('SELECT * FROM users')
  Stream<User?> getUsers();

  @Query('SELECT * FROM users WHERE id = :id ')
  Future<User?> getUserById(String id);
  @insert
  Future<int> addUser(User user);
  @delete
  Future<int> deleteUser(User user);
}
