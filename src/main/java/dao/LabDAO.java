package dao;

import model.Lab;
import model.Tribble;
import util.DBConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class LabDAO implements GenericDAO<Lab, Integer> {

    @Override
    public Lab[] getAll() {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("select * from lab", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            rs.last();
            Lab[] labs = new Lab[rs.getRow()];
            rs.beforeFirst();

            while (rs.next()) {
                Lab lab = new Lab();
                lab.setId(rs.getInt("id"));
//                lab.setLab(rs.getInt("lab"));
                labs[rs.getRow() - 1] = lab;
            }

            return labs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Lab l) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("insert into public.lab default values");

            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " rows");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Lab get(Integer id) {
        try {
            Class.forName("org.postgresql.Driver");
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("select * from lab where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Lab lab = new Lab();
            lab.setId(rs.getInt("id"));

            List<Tribble> tribbles = Arrays.asList(new TribbleDAO().getAllByLab(id));

            lab.setTribbles(tribbles);

            return lab;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Integer id, String column, String value) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("update lab set " + column + " = ? where \"id\" = ?;");
            ps.setString(1, value);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println("Updated "+rows+" rows");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("delete from lab where \"id\" = ?;");
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println("Updated "+rows+" rows");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
