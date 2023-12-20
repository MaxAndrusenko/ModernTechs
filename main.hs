import Data.List (find)

data Order = Order { orderId :: Int, address :: String, phoneNumber :: String } deriving (Show)

addOrder :: [Order] -> IO [Order]
addOrder orders = do
    putStrLn "Enter ID:"
    id <- readLn

    putStrLn "Enter Address:"
    address <- getLine

    putStrLn "Enter Phone Number:"
    phoneNumber <- getLine

    let newOrder = Order id address phoneNumber
    return (orders ++ [newOrder])

showOrders :: [Order] -> IO ()
showOrders orders = do
    putStrLn "All Orders:"
    mapM_ (printOrder) orders
    where printOrder order = putStrLn $ "ID: " ++ show (orderId order) ++ " | Address: " ++ address order ++ " | Phone Number: " ++ phoneNumber order

updateOrder :: [Order] -> IO [Order]
updateOrder orders = do
    putStrLn "Enter Order ID to update:"
    id <- readLn

    let maybeOrder = find (\o -> orderId o == id) orders
    case maybeOrder of
        Just orderToUpdate -> do
            putStrLn "Enter new address:"
            newAddress <- getLine

            putStrLn "Enter new phone number:"
            newPhoneNumber <- getLine

            let updatedOrder = orderToUpdate { address = newAddress, phoneNumber = newPhoneNumber }
                updatedOrders = map (\o -> if orderId o == id then updatedOrder else o) orders
            return updatedOrders
        Nothing -> do
            putStrLn "Order not found!"
            return orders

deleteOrder :: [Order] -> IO [Order]
deleteOrder orders = do
    putStrLn "Enter Order ID to delete:"
    id <- readLn

    let updatedOrders = filter (\o -> orderId o /= id) orders
    if length updatedOrders /= length orders
        then do
            putStrLn "Order deleted!"
            return updatedOrders
        else do
            putStrLn "Order not found!"
            return orders

main :: IO ()
main = do
    let initialOrders = []
    orderManagementLoop initialOrders

orderManagementLoop :: [Order] -> IO ()
orderManagementLoop orders = do
    putStrLn "\nChoose operation:"
    putStrLn "1. Add Order"
    putStrLn "2. Show Orders"
    putStrLn "3. Update Order"
    putStrLn "4. Delete Order"
    putStrLn "5. Exit"
    putStr "Enter your choice: "
    
    choice <- getLine
    case choice of
        "1" -> do
            updatedOrders <- addOrder orders
            orderManagementLoop updatedOrders
        "2" -> do
            showOrders orders
            orderManagementLoop orders
        "3" -> do
            updatedOrders <- updateOrder orders
            orderManagementLoop updatedOrders
        "4" -> do
            updatedOrders <- deleteOrder orders
            orderManagementLoop updatedOrders
        "5" -> putStrLn "Exiting program..."
        _ -> do
            putStrLn "Invalid choice! Try again."
            orderManagementLoop orders
1