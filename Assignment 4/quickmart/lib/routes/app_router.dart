import 'package:go_router/go_router.dart';
import 'package:quickmart/models/product.dart'; // Assuming Product model is here
import 'package:quickmart/screens/product_details_screen.dart';
import 'package:quickmart/screens/shell_screen.dart';

final GoRouter router = GoRouter(
  routes: [
    GoRoute(
      path: '/',
      builder: (context, state) =>
          ShellScreen(), // ShellScreen is the starting point
    ),
    GoRoute(
      path: '/product',
      builder: (context, state) {
        // Fetch the Product object from `extra`
        final product = state.extra as Product;
        return ProductDetailsScreen(product: product);
      },
    ),
  ],
);
