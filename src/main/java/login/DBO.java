package login;

import java.sql.*;

public class DBO {
    public final static String URL = "jdbc:postgresql://54.93.65.5:5432/cozmin7";
    public final static String USERNAME = "fasttrackit_dev";
    public final static String PASSWORD = "fasttrackit_dev";

    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int login (String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);


            // 4. create a query statement
            Statement st = conn.createStatement();

            // 5. execute a query, in a not  secured way
            String query = "SELECT id FROM users where username='"+user+"' and password='"+pwd+"'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);

            // 6. iterate the result set and print the values

            // BIG HACK, DEMO PURPOSE, BECAUSE I CREATE A DEPENDENCY ON HOW THE IMPLEM IS DONE IN UI

            while (rs.next()) {
                found = rs.getInt("id");
            }

            // 7. close the objects
            rs.close();
            st.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;


    }


    /* -1 daca nu am gasit , id-ul daca am gasit */
    public int register (String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
            pSt.setString(1, user);
            pSt.setString(2, pwd);

            int rowsInserted = pSt.executeUpdate();

            pSt.close();

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return found;


    }



    public static void main(String[] args) {

        DBO d = new DBO();
        int value = d.login("cozmin", "parola1234");
        System.out.println(value);
    }

    public void insertAlbum(String moneda, String numeimg, String descriere) {
        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");

            // 3. obtain a connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO album (moneda,imagine,descriere, data) VALUES (?,?,?, now())");
            pSt.setString(1, moneda);
            pSt.setString(2, numeimg);
            pSt.setString(3, descriere);


            int rowsInserted = pSt.executeUpdate();

            pSt.close();

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}

