package driver;

import dao.GenericDAO;
import dao.LabDAO;
import dao.TribbleDAO;
import model.Lab;
import model.Tribble;
import util.DBConnection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        // Create connection instance
        try {
            DBConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /**
         *
         * \/ \/ DEMONSTRATION \/ \/
         *
         */

        // Initialize DAOs
        GenericDAO tribbleDAO = new TribbleDAO();
        GenericDAO labDAO = new LabDAO();

        // Initialize Lab and Tribble objects
        Lab lab = new Lab();
        Tribble tribble = new Tribble();

        // Modify Tribble
        tribble.setColor("Brown");
        tribble.setFluffometer(76);
        tribble.setName("Tribble #4815");

        // Add Tribble to Lab
        lab.addTribbles(tribble);

        // Persist lab
        labDAO.insert(lab);

        // Print number of lab entries
        System.out.println("Labs: " + labDAO.getAll().length);

        // Get new Lab (id 3) from database
        Lab newLab = (Lab) labDAO.get(3);

        // Print new Lab
        System.out.println("New lab: " + newLab.toString());

        // Assing Tribble to new Lab
        tribble.setLab((Lab) labDAO.get(3));
        tribbleDAO.insert(tribble);

        // Print number of tribble entries
        System.out.println("Tribbles: " + tribbleDAO.getAll().length);

        // Get new Tribble (id 11) from database
        Tribble newTribble = (Tribble) tribbleDAO.get(5);

        // Print new Tribble
        System.out.println("New tribble: " + newTribble.toString());

    }
}
