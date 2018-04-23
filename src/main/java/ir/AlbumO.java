package ir;

import login.DBO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AlbumO {

    private List<AlbumBean> listOfAlbum = new ArrayList<>();



    public List getListOfAlbum() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");


        Connection conn = DriverManager.getConnection(DBO.URL, DBO.USERNAME, DBO.PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT * FROM album order by data desc");
        ResultSet rs = pSt.executeQuery();
        while (rs.next()) {
            AlbumBean ir = new AlbumBean();
            ir.setId(rs.getInt("id"));
            ir.setMoneda(rs.getString("moneda"));
            ir.setPoza(rs.getString("imagine"));
            ir.setDescriere(rs.getString("descriere"));
            listOfAlbum.add(ir);
        }

        pSt.close();
        conn.close();

        return listOfAlbum;
    }
}



