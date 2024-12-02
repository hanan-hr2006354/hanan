import 'dart:convert';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:flutter/services.dart'; // Use this for loading assets

Future<List<String>> getCategories() async {
  final String fileContent =
      await rootBundle.loadString('assets/data/product-categories.json');
  final List<dynamic> jsonList = jsonDecode(fileContent);
  return List<String>.from(jsonList);
}

final categoriesProvider =
    FutureProvider<List<String>>((ref) async => await getCategories());
