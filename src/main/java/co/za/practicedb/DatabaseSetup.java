package co.za.practicedb;

// TODO: import all the neccessary modules 

import java.sql.Connection;

public class DatabaseSetup {
    // Schema
    // SQL Code
    public static void setup() {

        //  TODO: define your DB tables
        String table1 = """
                fill me in
                """;

        String table2 = """
                fill me in
                )
                """;


        // Executing SQL statement
        try(Connection connection = Database.connect();
            Statement statement = connection.createStatement()) {

                // TODO: Excute your statements
                

        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }

    // TODO: Inserting values
    String insertData = """
            fill me in
            """;

    try(Connection connection = Database.connect();
        PreparedStatement statement = connection.prepareStatement(insertData)) {

            // TODO: insertion logic store 1
            
            // Excute SQL statements
            statement.executeUpdate();

            // TODO: Insertion logic store 2
           

            // TODO: execute insertion for store 2
            


        } catch (SQLException e) {
            e.printStackTrace();
        }

        // TODO: Insert into purchaces 
        String purchaseInsert = """
                fill me in

                """;

        
        // Query into database 
        String query = "SELECT * FROM purchases";


        try("TODO: fill me in I am struggling!") {

                // Insert values
                statement.setInt(1, 200);
                statement.setInt(2, 100);
                statement.setDouble(3, 356.79);

                // Execute statemnt
                statement.executeUpdate();

                // Removed them result statement from the try catch block.
                Statement queryStatement = connection.createStatement();
                ResultSet result = queryStatement.executeQuery(query);

                // Begin while loop
                while (result.next()) {
                    
                    int purchase_id = result.getInt("purchase_id");
                    // TODO: grab the remaining columns

                    System.out.println(
                        purchase_id + " | " +
                        store_id + " | " + 
                        price
                    );
                } 


            } catch (SQLException e) {
                e.printStackTrace();
            }

    }

}
