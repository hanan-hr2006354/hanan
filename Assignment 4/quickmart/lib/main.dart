import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:quickmart/routes/app_router.dart';

void main() {
  runApp(ProviderScope(child: QuickMartApp()));
}

class QuickMartApp extends StatelessWidget {
  @override
  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: router,
      debugShowCheckedModeBanner:
          false, //Remove the debug banner. it's annoying
    );
  }
}
