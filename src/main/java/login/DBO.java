package login;

import java.sql.*;

public class DBO {
    public final static String URL = "jdbc:postgresql://54.93.65.5:5432/cozmin7";
    public final static String USERNAME = "fasttrackit_dev";
    public final static String PASSWORD = "fasttrackit_dev";


    public int login (String user, String pwd) {

        int found = -1;
        try {
            Class.forName("org.postgresql.Driver");


            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);



            Statement st = conn.createStatement();


            String query = "SELECT id FROM users where username='"+user+"' and password='"+pwd+"'";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);



            while (rs.next()) {
                found = rs.getInt("id");
            }


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

