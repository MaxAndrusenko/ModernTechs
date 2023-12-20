using System;
using System.Collections.Generic;
using System.Linq;

class Order
{
    public int Id { get; set; }
    public string Address { get; set; }
    public string PhoneNumber { get; set; }
}

class Program
{
    static void Main()
    {
        var orders = new List<Order>();

        int choice;
        do
        {
            Console.WriteLine("\nChoose operation:");
            Console.WriteLine("1. Add Order");
            Console.WriteLine("2. Show Orders");
            Console.WriteLine("3. Update Order");
            Console.WriteLine("4. Delete Order");
            Console.WriteLine("5. Exit");
            Console.Write("Enter your choice: ");
            
            if (!int.TryParse(Console.ReadLine(), out choice))
            {
                Console.WriteLine("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice)
            {
                case 1:
                    Console.Write("Enter ID: ");
                    int id = int.Parse(Console.ReadLine());

                    Console.Write("Enter Address: ");
                    string address = Console.ReadLine();

                    Console.Write("Enter Phone Number: ");
                    string phoneNumber = Console.ReadLine();

                    var newOrder = new Order { Id = id, Address = address, PhoneNumber = phoneNumber };
                    orders.Add(newOrder);
                    Console.WriteLine("Order added!");
                    break;

                case 2:
                    Console.WriteLine("\nAll Orders:");
                    foreach (var order in orders)
                    {
                        Console.WriteLine($"ID: {order.Id} | Address: {order.Address} | Phone Number: {order.PhoneNumber}");
                    }
                    break;

                case 3:
                    Console.Write("Enter Order ID to update: ");
                    int orderIdToUpdate = int.Parse(Console.ReadLine());

                    var orderToUpdate = orders.FirstOrDefault(o => o.Id == orderIdToUpdate);
                    if (orderToUpdate != null)
                    {
                        Console.Write("Enter new address: ");
                        orderToUpdate.Address = Console.ReadLine();

                        Console.Write("Enter new phone number: ");
                        orderToUpdate.PhoneNumber = Console.ReadLine();
                        Console.WriteLine("Order updated!");
                    }
                    else
                    {
                        Console.WriteLine("Order not found!");
                    }
                    break;

                case 4:
                    Console.Write("Enter Order ID to delete: ");
                    int orderIdToDelete = int.Parse(Console.ReadLine());

                    var orderToDelete = orders.FirstOrDefault(o => o.Id == orderIdToDelete);
                    if (orderToDelete != null)
                    {
                        orders.Remove(orderToDelete);
                        Console.WriteLine("Order deleted!");
                    }
                    else
                    {
                        Console.WriteLine("Order not found!");
                    }
                    break;

                case 5:
                    Console.WriteLine("Exiting program...");
                    break;

                default:
                    Console.WriteLine("Invalid choice! Try again.");
                    break;
            }
        } while (choice != 5);
    }
}
