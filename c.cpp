#include <iostream>
#include <vector>
#include <algorithm>

class Order {
private:
    int id;
    std::string address;
    std::string phoneNumber;

public:
    Order(int orderId, const std::string& orderAddress, const std::string& orderPhoneNumber)
        : id(orderId), address(orderAddress), phoneNumber(orderPhoneNumber) {}

    // Getters and setters
    int getId() const { return id; }
    void setId(int newId) { id = newId; }

    std::string getAddress() const { return address; }
    void setAddress(const std::string& newAddress) { address = newAddress; }

    std::string getPhoneNumber() const { return phoneNumber; }
    void setPhoneNumber(const std::string& newPhoneNumber) { phoneNumber = newPhoneNumber; }
};

int main() {
    std::vector<Order> orders;

    int choice;
    do {
        std::cout << "\nChoose operation:\n";
        std::cout << "1. Add Order\n";
        std::cout << "2. Show Orders\n";
        std::cout << "3. Update Order\n";
        std::cout << "4. Delete Order\n";
        std::cout << "5. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1: {
                int id;
                std::string address, phoneNumber;

                std::cout << "Enter ID: ";
                std::cin >> id;
                std::cout << "Enter Address: ";
                std::cin.ignore(); // Ignore newline in buffer
                std::getline(std::cin, address);
                std::cout << "Enter Phone Number: ";
                std::getline(std::cin, phoneNumber);

                Order newOrder(id, address, phoneNumber);
                orders.push_back(newOrder);
                std::cout << "Order added!\n";
                break;
            }
            case 2:
                std::cout << "\nAll Orders:\n";
                for (const auto& order : orders) {
                    std::cout << "ID: " << order.getId() << " | Address: " << order.getAddress()
                              << " | Phone Number: " << order.getPhoneNumber() << "\n";
                }
                break;
            case 3: {
                int orderId;
                std::cout << "Enter Order ID to update: ";
                std::cin >> orderId;

                auto it = std::find_if(orders.begin(), orders.end(), [orderId](const Order& order) {
                    return order.getId() == orderId;
                });

                if (it != orders.end()) {
                    std::string newAddress, newPhoneNumber;
                    std::cout << "Enter new address: ";
                    std::cin.ignore();
                    std::getline(std::cin, newAddress);
                    std::cout << "Enter new phone number: ";
                    std::getline(std::cin, newPhoneNumber);

                    it->setAddress(newAddress);
                    it->setPhoneNumber(newPhoneNumber);
                    std::cout << "Order updated!\n";
                } else {
                    std::cout << "Order not found!\n";
                }
                break;
            }
            case 4: {
                int orderIdToDelete;
                std::cout << "Enter Order ID to delete: ";
                std::cin >> orderIdToDelete;

                auto it = std::remove_if(orders.begin(), orders.end(), [orderIdToDelete](const Order& order) {
                    return order.getId() == orderIdToDelete;
                });

                if (it != orders.end()) {
                    orders.erase(it, orders.end());
                    std::cout << "Order deleted!\n";
                } else {
                    std::cout << "Order not found!\n";
                }
                break;
            }
            case 5:
                std::cout << "Exiting program...\n";
                break;
            default:
                std::cout << "Invalid choice! Try again.\n";
        }
    } while (choice != 5);

    return 0;
}
