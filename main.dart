class Order {
  int id;
  String address;
  String phoneNumber;

  Order(this.id, this.address, this.phoneNumber);
}

void main() {
  List<Order> orders = [];

  void addOrder() {
    print('Enter ID:');
    int id = int.parse(stdin.readLineSync()!);

    print('Enter Address:');
    String address = stdin.readLineSync()!;

    print('Enter Phone Number:');
    String phoneNumber = stdin.readLineSync()!;

    Order newOrder = Order(id, address, phoneNumber);
    orders.add(newOrder);
    print('Order added!');
  }

  void showOrders() {
    print('All Orders:');
    for (var order in orders) {
      print('ID: ${order.id} | Address: ${order.address} | Phone Number: ${order.phoneNumber}');
    }
  }

  void updateOrder() {
    print('Enter Order ID to update:');
    int id = int.parse(stdin.readLineSync()!);

    var orderToUpdate = orders.firstWhere((o) => o.id == id, orElse: () => null);
    if (orderToUpdate != null) {
      print('Enter new address:');
      String newAddress = stdin.readLineSync()!;

      print('Enter new phone number:');
      String newPhoneNumber = stdin.readLineSync()!;

      orderToUpdate.address = newAddress;
      orderToUpdate.phoneNumber = newPhoneNumber;
      print('Order updated!');
    } else {
      print('Order not found!');
    }
  }

  void deleteOrder() {
    print('Enter Order ID to delete:');
    int id = int.parse(stdin.readLineSync()!);

    var orderToDelete = orders.firstWhere((o) => o.id == id, orElse: () => null);
    if (orderToDelete != null) {
      orders.remove(orderToDelete);
      print('Order deleted!');
    } else {
      print('Order not found!');
    }
  }

  void orderManagementLoop() {
    while (true) {
      print('\nChoose operation:');
      print('1. Add Order');
      print('2. Show Orders');
      print('3. Update Order');
      print('4. Delete Order');
      print('5. Exit');

      String? choice = stdin.readLineSync();

      switch (choice) {
        case '1':
          addOrder();
          break;
        case '2':
          showOrders();
          break;
        case '3':
          updateOrder();
          break;
        case '4':
          deleteOrder();
          break;
        case '5':
          print('Exiting program...');
          return;
        default:
          print('Invalid choice! Try again.');
      }
    }
  }

  orderManagementLoop();
}
