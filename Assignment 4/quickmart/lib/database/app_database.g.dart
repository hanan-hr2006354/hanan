// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'app_database.dart';

// **************************************************************************
// FloorGenerator
// **************************************************************************

abstract class $AppDatabaseBuilderContract {
  /// Adds migrations to the builder.
  $AppDatabaseBuilderContract addMigrations(List<Migration> migrations);

  /// Adds a database [Callback] to the builder.
  $AppDatabaseBuilderContract addCallback(Callback callback);

  /// Creates the database and initializes it.
  Future<AppDatabase> build();
}

// ignore: avoid_classes_with_only_static_members
class $FloorAppDatabase {
  /// Creates a database builder for a persistent database.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static $AppDatabaseBuilderContract databaseBuilder(String name) =>
      _$AppDatabaseBuilder(name);

  /// Creates a database builder for an in memory database.
  /// Information stored in an in memory database disappears when the process is killed.
  /// Once a database is built, you should keep a reference to it and re-use it.
  static $AppDatabaseBuilderContract inMemoryDatabaseBuilder() =>
      _$AppDatabaseBuilder(null);
}

class _$AppDatabaseBuilder implements $AppDatabaseBuilderContract {
  _$AppDatabaseBuilder(this.name);

  final String? name;

  final List<Migration> _migrations = [];

  Callback? _callback;

  @override
  $AppDatabaseBuilderContract addMigrations(List<Migration> migrations) {
    _migrations.addAll(migrations);
    return this;
  }

  @override
  $AppDatabaseBuilderContract addCallback(Callback callback) {
    _callback = callback;
    return this;
  }

  @override
  Future<AppDatabase> build() async {
    final path = name != null
        ? await sqfliteDatabaseFactory.getDatabasePath(name!)
        : ':memory:';
    final database = _$AppDatabase();
    database.database = await database.open(
      path,
      _migrations,
      _callback,
    );
    return database;
  }
}

class _$AppDatabase extends AppDatabase {
  _$AppDatabase([StreamController<String>? listener]) {
    changeListener = listener ?? StreamController<String>.broadcast();
  }

  CartDao? _cartDaoInstance;

  CategoryDao? _categoryDaoInstance;

  FavoriteDao? _favoriteDaoInstance;

  ProductDao? _productDaoInstance;

  UserDao? _userDaoInstance;

  Future<sqflite.Database> open(
    String path,
    List<Migration> migrations, [
    Callback? callback,
  ]) async {
    final databaseOptions = sqflite.OpenDatabaseOptions(
      version: 2,
      onConfigure: (database) async {
        await database.execute('PRAGMA foreign_keys = ON');
        await callback?.onConfigure?.call(database);
      },
      onOpen: (database) async {
        await callback?.onOpen?.call(database);
      },
      onUpgrade: (database, startVersion, endVersion) async {
        await MigrationAdapter.runMigrations(
            database, startVersion, endVersion, migrations);

        await callback?.onUpgrade?.call(database, startVersion, endVersion);
      },
      onCreate: (database, version) async {
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `CartItems` (`id` TEXT NOT NULL, `productId` TEXT NOT NULL, `quantity` INTEGER NOT NULL, `userId` TEXT NOT NULL, FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION, FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION, PRIMARY KEY (`id`))');
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `categories` (`id` TEXT NOT NULL, `category` TEXT NOT NULL, PRIMARY KEY (`id`))');
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `favourites` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `productId` TEXT NOT NULL, `userId` TEXT NOT NULL, FOREIGN KEY (`productId`) REFERENCES `products` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION, FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION)');
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `products` (`id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `price` REAL NOT NULL, `imageName` TEXT NOT NULL, `categoryId` TEXT NOT NULL, `rating` INTEGER NOT NULL, FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION, PRIMARY KEY (`id`))');
        await database.execute(
            'CREATE TABLE IF NOT EXISTS `users` (`id` TEXT, `firstName` TEXT NOT NULL, `lastName` TEXT NOT NULL, `email` TEXT NOT NULL, `password` TEXT NOT NULL, `isAdmin` INTEGER NOT NULL, `profilePic` TEXT NOT NULL, PRIMARY KEY (`id`))');

        await callback?.onCreate?.call(database, version);
      },
    );
    return sqfliteDatabaseFactory.openDatabase(path, options: databaseOptions);
  }

  @override
  CartDao get cartDao {
    return _cartDaoInstance ??= _$CartDao(database, changeListener);
  }

  @override
  CategoryDao get categoryDao {
    return _categoryDaoInstance ??= _$CategoryDao(database, changeListener);
  }

  @override
  FavoriteDao get favoriteDao {
    return _favoriteDaoInstance ??= _$FavoriteDao(database, changeListener);
  }

  @override
  ProductDao get productDao {
    return _productDaoInstance ??= _$ProductDao(database, changeListener);
  }

  @override
  UserDao get userDao {
    return _userDaoInstance ??= _$UserDao(database, changeListener);
  }
}

class _$CartDao extends CartDao {
  _$CartDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database, changeListener),
        _cartItemInsertionAdapter = InsertionAdapter(
            database,
            'CartItems',
            (CartItem item) => <String, Object?>{
                  'id': item.id,
                  'productId': item.productId,
                  'quantity': item.quantity,
                  'userId': item.userId
                },
            changeListener),
        _cartItemDeletionAdapter = DeletionAdapter(
            database,
            'CartItems',
            ['id'],
            (CartItem item) => <String, Object?>{
                  'id': item.id,
                  'productId': item.productId,
                  'quantity': item.quantity,
                  'userId': item.userId
                },
            changeListener);

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<CartItem> _cartItemInsertionAdapter;

  final DeletionAdapter<CartItem> _cartItemDeletionAdapter;

  @override
  Stream<List<CartItem>> getCartItems(String userId) {
    return _queryAdapter.queryListStream(
        'SELECT * FROM CartItems WHERE userId = ?1',
        mapper: (Map<String, Object?> row) => CartItem(
            userId: row['userId'] as String,
            id: row['id'] as String,
            productId: row['productId'] as String,
            quantity: row['quantity'] as int),
        arguments: [userId],
        queryableName: 'CartItems',
        isView: false);
  }

  @override
  Stream<bool?> inCart(
    String pid,
    String userId,
  ) {
    return _queryAdapter.queryStream(
        'SELECT EXISTS(SELECT 1 FROM CartItems WHERE productID = ?1 AND userId = ?2)',
        mapper: (Map<String, Object?> row) => (row.values.first as int) != 0,
        arguments: [pid, userId],
        queryableName: 'CartItems',
        isView: false);
  }

  @override
  Future<void> updateQuantity(
    String productId,
    int newQuantity,
    String userId,
  ) async {
    await _queryAdapter.queryNoReturn(
        'UPDATE CartItems SET quantity = ?2 WHERE productId = ?1 AND userId = ?3',
        arguments: [productId, newQuantity, userId]);
  }

  @override
  Stream<double?> getItemTotalPrice(
    String cartItemId,
    String userId,
  ) {
    return _queryAdapter.queryStream(
        'SELECT p.price * c.quantity AS totalPrice    FROM CartItems c    JOIN products p ON c.productId = p.id    WHERE c.id = ?1 AND c.userId = ?2',
        mapper: (Map<String, Object?> row) => row.values.first as double,
        arguments: [cartItemId, userId],
        queryableName: 'CartItems',
        isView: false);
  }

  @override
  Stream<double?> getCartTotalPrice(String userId) {
    return _queryAdapter.queryStream(
        'SELECT SUM(p.price * c.quantity) AS cartTotal      FROM CartItems c      JOIN products p ON c.productId = p.id      WHERE c.userId = ?1',
        mapper: (Map<String, Object?> row) => row.values.first as double,
        arguments: [userId],
        queryableName: 'CartItems',
        isView: false);
  }

  @override
  Stream<int?> getCartItemQuantity(
    String productId,
    String userId,
  ) {
    return _queryAdapter.queryStream(
        'SELECT quantity FROM CartItems WHERE productId = ?1 AND userId = ?2',
        mapper: (Map<String, Object?> row) => row.values.first as int,
        arguments: [productId, userId],
        queryableName: 'CartItems',
        isView: false);
  }

  @override
  Future<Product?> getProductInfo(
    String cartItemId,
    String userID,
  ) async {
    return _queryAdapter.query(
        'SELECT p.*   FROM CartItems c   JOIN products p ON c.productId = p.id   WHERE c.id = ?1 AND userId = ?2',
        mapper: (Map<String, Object?> row) => Product(id: row['id'] as String, title: row['title'] as String, description: row['description'] as String, price: row['price'] as double, imageName: row['imageName'] as String, categoryId: row['categoryId'] as String, rating: row['rating'] as int),
        arguments: [cartItemId, userID]);
  }

  @override
  Future<int> addCartItem(CartItem cartItem) {
    return _cartItemInsertionAdapter.insertAndReturnId(
        cartItem, OnConflictStrategy.abort);
  }

  @override
  Future<void> deleteCartItem(CartItem cartItem) async {
    await _cartItemDeletionAdapter.delete(cartItem);
  }
}

class _$CategoryDao extends CategoryDao {
  _$CategoryDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database),
        _cateGoryInsertionAdapter = InsertionAdapter(
            database,
            'categories',
            (CateGory item) =>
                <String, Object?>{'id': item.id, 'category': item.category});

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<CateGory> _cateGoryInsertionAdapter;

  @override
  Future<CateGory?> getCategories() async {
    return _queryAdapter.query('SELECT * FROM categories',
        mapper: (Map<String, Object?> row) => CateGory(
            id: row['id'] as String, category: row['category'] as String));
  }

  @override
  Future<int> addCategory(CateGory category) {
    return _cateGoryInsertionAdapter.insertAndReturnId(
        category, OnConflictStrategy.abort);
  }
}

class _$FavoriteDao extends FavoriteDao {
  _$FavoriteDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database, changeListener),
        _favouriteInsertionAdapter = InsertionAdapter(
            database,
            'favourites',
            (Favourite item) => <String, Object?>{
                  'id': item.id,
                  'productId': item.productId,
                  'userId': item.userId
                });

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<Favourite> _favouriteInsertionAdapter;

  @override
  Stream<List<Product>> getFavourites(String userId) {
    return _queryAdapter.queryListStream(
        'SELECT p.* FROM products p JOIN favourites f     ON  f.productId = p.id      WHERE f.userId = ?1',
        mapper: (Map<String, Object?> row) => Product(
            id: row['id'] as String,
            title: row['title'] as String,
            description: row['description'] as String,
            price: row['price'] as double,
            imageName: row['imageName'] as String,
            categoryId: row['categoryId'] as String,
            rating: row['rating'] as int),
        arguments: [userId],
        queryableName: 'products',
        isView: false);
  }

  @override
  Stream<bool?> isFavourtie(
    String pid,
    String userID,
  ) {
    return _queryAdapter.queryStream(
        'SELECT EXISTS(SELECT 1 FROM favourites WHERE productID = ?1 AND userId = ?2)',
        mapper: (Map<String, Object?> row) => (row.values.first as int) != 0,
        arguments: [pid, userID],
        queryableName: 'favourites',
        isView: false);
  }

  @override
  Future<void> deleteFavourite(
    String pid,
    String userID,
  ) async {
    await _queryAdapter.queryNoReturn(
        'DELETE FROM favourites WHERE productID =?1 AND userId = ?2',
        arguments: [pid, userID]);
  }

  @override
  Future<int> addFavourite(Favourite favourite) {
    return _favouriteInsertionAdapter.insertAndReturnId(
        favourite, OnConflictStrategy.abort);
  }
}

class _$ProductDao extends ProductDao {
  _$ProductDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database),
        _productInsertionAdapter = InsertionAdapter(
            database,
            'products',
            (Product item) => <String, Object?>{
                  'id': item.id,
                  'title': item.title,
                  'description': item.description,
                  'price': item.price,
                  'imageName': item.imageName,
                  'categoryId': item.categoryId,
                  'rating': item.rating
                },
            changeListener),
        _productDeletionAdapter = DeletionAdapter(
            database,
            'products',
            ['id'],
            (Product item) => <String, Object?>{
                  'id': item.id,
                  'title': item.title,
                  'description': item.description,
                  'price': item.price,
                  'imageName': item.imageName,
                  'categoryId': item.categoryId,
                  'rating': item.rating
                },
            changeListener);

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<Product> _productInsertionAdapter;

  final DeletionAdapter<Product> _productDeletionAdapter;

  @override
  Future<List<Product?>> fetchProducts(
    String name,
    String categoryId,
  ) async {
    return _queryAdapter.queryList(
        'SELECT * FROM products WHERE (?1 IS NULL OR title LIKE \'%\' || ?1 || \'%\') AND (?2 = \'\' or categoryId = ?2)',
        mapper: (Map<String, Object?> row) => Product(id: row['id'] as String, title: row['title'] as String, description: row['description'] as String, price: row['price'] as double, imageName: row['imageName'] as String, categoryId: row['categoryId'] as String, rating: row['rating'] as int),
        arguments: [name, categoryId]);
  }

  @override
  Future<int> addProduct(Product product) {
    return _productInsertionAdapter.insertAndReturnId(
        product, OnConflictStrategy.abort);
  }

  @override
  Future<void> deleteProduct(Product product) async {
    await _productDeletionAdapter.delete(product);
  }
}

class _$UserDao extends UserDao {
  _$UserDao(
    this.database,
    this.changeListener,
  )   : _queryAdapter = QueryAdapter(database, changeListener),
        _userInsertionAdapter = InsertionAdapter(
            database,
            'users',
            (User item) => <String, Object?>{
                  'id': item.id,
                  'firstName': item.firstName,
                  'lastName': item.lastName,
                  'email': item.email,
                  'password': item.password,
                  'isAdmin': item.isAdmin ? 1 : 0,
                  'profilePic': item.profilePic
                },
            changeListener),
        _userDeletionAdapter = DeletionAdapter(
            database,
            'users',
            ['id'],
            (User item) => <String, Object?>{
                  'id': item.id,
                  'firstName': item.firstName,
                  'lastName': item.lastName,
                  'email': item.email,
                  'password': item.password,
                  'isAdmin': item.isAdmin ? 1 : 0,
                  'profilePic': item.profilePic
                },
            changeListener);

  final sqflite.DatabaseExecutor database;

  final StreamController<String> changeListener;

  final QueryAdapter _queryAdapter;

  final InsertionAdapter<User> _userInsertionAdapter;

  final DeletionAdapter<User> _userDeletionAdapter;

  @override
  Stream<User?> getUsers() {
    return _queryAdapter.queryStream('SELECT * FROM users',
        mapper: (Map<String, Object?> row) => User(
            row['id'] as String?,
            row['firstName'] as String,
            row['lastName'] as String,
            row['email'] as String,
            row['password'] as String,
            (row['isAdmin'] as int) != 0,
            row['profilePic'] as String),
        queryableName: 'users',
        isView: false);
  }

  @override
  Future<User?> getUserById(String id) async {
    return _queryAdapter.query('SELECT * FROM users WHERE id = ?1',
        mapper: (Map<String, Object?> row) => User(
            row['id'] as String?,
            row['firstName'] as String,
            row['lastName'] as String,
            row['email'] as String,
            row['password'] as String,
            (row['isAdmin'] as int) != 0,
            row['profilePic'] as String),
        arguments: [id]);
  }

  @override
  Future<int> addUser(User user) {
    return _userInsertionAdapter.insertAndReturnId(
        user, OnConflictStrategy.abort);
  }

  @override
  Future<int> deleteUser(User user) {
    return _userDeletionAdapter.deleteAndReturnChangedRows(user);
  }
}
