package fr.projet.ProjetLBC.dao.hsqlImpl;

import fr.projet.ProjetLBC.beans.Utilisateur;
import fr.projet.ProjetLBC.dao.IUtilisateurDao;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDao implements IUtilisateurDao {
private static List<Utilisateur> listOfUsers;



    @Override
    public List<Utilisateur> getAllUsers() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        Connection con;
        ResultSet resultats = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            listOfUsers = new ArrayList<>();
            Statement stm;
            stm = con.createStatement();
            String sql = "Select * From UTILISATEURS";
            ResultSet rst;
            rst = stm.executeQuery(sql);
            ArrayList<Utilisateur> listUser = new ArrayList<>();
            while (rst.next()) {
                Utilisateur usesss = new Utilisateur();
                usesss.setNom(rst.getString("NAME"));
                usesss.setPassword(rst.getString("PASSWORD"));
                usesss.setId(rst.getString("ID"));
                usesss.setAdministrateur(rst.getBoolean("ISADMINISTRATOR"));
                listOfUsers.add(usesss);
            }
            return listOfUsers;



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listOfUsers;
    }

    public static Utilisateur getUtilisateur(String userId) {
        Utilisateur u = new Utilisateur();
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        Connection con;
        ResultSet resultats = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM UTILISATEURS WHERE id = ?");
            ps.setString(1, userId);

            resultats = ps.executeQuery();
            while (resultats.next()) {
                u.setId(resultats.getString(1));
                u.setNom(resultats.getString(4));
                u.setAdministrateur(resultats.getBoolean(3));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Utilisateur> getListOfUtilisateurs() {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurById(String id) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        Connection con;
        ResultSet resultats = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE id = ?");
            ps.setString(1, id);
            Statement stmt = con.createStatement();
            resultats = ps.executeQuery();
            while (resultats.next()) {
                return new Utilisateur(resultats.getString(1),
                        resultats.getString(2),
                        resultats.getString(3),
                        resultats.getString(4),
                        resultats.getBoolean(5));
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkLogin(Utilisateur utilisateur) {
        boolean accesOk = false;
        Utilisateur existingUser = null;
        for (Utilisateur u: getAllUsers()) {
            if (u.getId().equals(utilisateur.getId())) {
                existingUser = u;
                break;
            }
        }
        if (existingUser != null) {
            accesOk = (utilisateur.getId().equals(existingUser.getId())
                    && utilisateur.getPassword().equals(existingUser.getPassword()));
        }

        return accesOk;
    }

    @Override
    public void addUtilisateur(Utilisateur utilisateur) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        Connection con;
        ResultSet resultats = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            PreparedStatement ps = con.prepareStatement("INSERT INTO UTILISATEURS VALUES (?,?,?,?)");
            ps.setString(1, utilisateur.getId());
            ps.setString(2, utilisateur.getPassword());
            ps.setBoolean(3, false);
            ps.setString(4, utilisateur.getNom());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        Connection con;
        ResultSet resultats = null;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ?, isadministrator = ? WHERE id = ?");
            ps.setString(1, utilisateur.getPassword());
            ps.setBoolean(2, utilisateur.isAdministrateur());
            ps.setString(3, utilisateur.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUtilisateur(Utilisateur utilisateur) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        Connection con;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9003");
            PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setString(1, utilisateur.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
