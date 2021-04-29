package dao;

import model.Lab;
import model.Tribble;
import util.DBConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TribbleDAO implements GenericDAO<Tribble, Integer> {

    @Override
    public Tribble[] getAll() {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("select * from tribble", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            rs.last();
            Tribble[] tribbles = new Tribble[rs.getRow()];
            rs.beforeFirst();

            while (rs.next()) {
                Tribble tribble = new Tribble();
                tribble.setId(rs.getInt("id"));
//                tribble.setLab(rs.getInt("lab"));
                tribble.setFluffometer(rs.getInt("fluffometer"));
                tribble.setName(rs.getString("name"));
                tribble.setColor(rs.getString("color"));
                tribbles[rs.getRow() - 1] = tribble;
            }

            return tribbles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tribble[] getAllByLab(Integer labId) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                            .getConnection()
                            .prepareStatement("select * from tribble where lab = ?;", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1,labId);
            ResultSet rs = ps.executeQuery();
            rs.last();
            Tribble[] tribbles = new Tribble[rs.getRow()];
            rs.beforeFirst();

            while (rs.next()) {
                Tribble tribble = new Tribble();
                tribble.setId(rs.getInt("id"));
                tribble.setLab(new Lab(rs.getInt("lab")));
                tribble.setFluffometer(rs.getInt("fluffometer"));
                tribble.setName(rs.getString("name"));
                tribble.setColor(rs.getString("color"));
                tribbles[rs.getRow() - 1] = tribble;
            }

            return tribbles;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Tribble t) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("insert into tribble (\"lab\", \"fluffometer\", \"name\", \"color\") values (?, ?, ?, ?)");
            ps.setInt(1, t.getLab().getId());
            ps.setInt(2, t.getFluffometer());
            ps.setString(3, t.getName());
            ps.setString(4, t.getColor());

            int rows = ps.executeUpdate();
            System.out.println("Updated "+rows+" rows");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tribble get(Integer id) {
        try {
            PreparedStatement ps =
                    new DBConnection()
                    .getConnection()
                    .prepareStatement("select * from tribble where id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();

            Tribble tribble = new Tribble();
            tribble.setId(rs.getInt("id"));
            tribble.setLab(new Lab(rs.getInt("lab")));
            tribble.setFluffometer(rs.getInt("fluffometer"));
            tribble.setName(rs.getString("name"));
            tribble.setColor(rs.getString("color"));

            return tribble;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
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
                    .prepareStatement("update tribble set " + column + " = ? where \"id\" = ?;");
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
                    .prepareStatement("delete from tribble where \"id\" = ?;");
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println("Updated "+rows+" rows");
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
