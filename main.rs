use std::io::{self, Write};

struct Order {
    id: i32,
    address: String,
    phone_number: String,
}

fn main() {
    let mut orders: Vec<Order> = Vec::new();

    loop {
        println!("\nChoose operation:");
        println!("1. Add Order");
        println!("2. Show Orders");
        println!("3. Update Order");
        println!("4. Delete Order");
        println!("5. Exit");

        print!("Enter your choice: ");
        io::stdout().flush().unwrap();

        let mut choice = String::new();
        io::stdin().read_line(&mut choice).expect("Failed to read line");
        let choice: u32 = match choice.trim().parse() {
            Ok(num) => num,
            Err(_) => {
                println!("Invalid input! Please enter a number.");
                continue;
            }
        };

        match choice {
            1 => add_order(&mut orders),
            2 => show_orders(&orders),
            3 => update_order(&mut orders),
            4 => delete_order(&mut orders),
            5 => {
                println!("Exiting program...");
                break;
            }
            _ => println!("Invalid choice! Try again."),
        }
    }
}

fn add_order(orders: &mut Vec<Order>) {
    println!("Enter ID:");
    let mut id = String::new();
    io::stdin().read_line(&mut id).expect("Failed to read line");
    let id: i32 = id.trim().parse().expect("Please enter a number");

    println!("Enter Address:");
    let mut address = String::new();
    io::stdin().read_line(&mut address).expect("Failed to read line");
    let address = address.trim().to_string();

    println!("Enter Phone Number:");
    let mut phone_number = String::new();
    io::stdin().read_line(&mut phone_number).expect("Failed to read line");
    let phone_number = phone_number.trim().to_string();

    let new_order = Order {
        id,
        address,
        phone_number,
    };
    orders.push(new_order);
    println!("Order added!");
}

fn show_orders(orders: &Vec<Order>) {
    println!("All Orders:");
    for order in orders {
        println!(
            "ID: {} | Address: {} | Phone Number: {}",
            order.id, order.address, order.phone_number
        );
    }
}

fn update_order(orders: &mut Vec<Order>) {
    println!("Enter Order ID to update:");
    let mut id = String::new();
    io::stdin().read_line(&mut id).expect("Failed to read line");
    let id: i32 = id.trim().parse().expect("Please enter a number");

    if let Some(order_to_update) = orders.iter_mut().find(|o| o.id == id) {
        println!("Enter new address:");
        let mut new_address = String::new();
        io::stdin().read_line(&mut new_address).expect("Failed to read line");
        let new_address = new_address.trim().to_string();

        println!("Enter new phone number:");
        let mut new_phone_number = String::new();
        io::stdin().read_line(&mut new_phone_number).expect("Failed to read line");
        let new_phone_number = new_phone_number.trim().to_string();

        order_to_update.address = new_address;
        order_to_update.phone_number = new_phone_number;
        println!("Order updated!");
    } else {
        println!("Order not found!");
    }
}

fn delete_order(orders: &mut Vec<Order>) {
    println!("Enter Order ID to delete:");
    let mut id = String::new();
    io::stdin().read_line(&mut id).expect("Failed to read line");
    let id: i32 = id.trim().parse().expect("Please enter a number");

    if let Some(index) = orders.iter().position(|o| o.id == id) {
        orders.remove(index);
        println!("Order deleted!");
    } else {
        println!("Order not found!");
    }
}
