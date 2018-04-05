package fr.projet.ProjetLBC.dao.hsqlImpl;

import fr.projet.ProjetLBC.beans.Annonce;
import fr.projet.ProjetLBC.beans.Utilisateur;
import fr.projet.ProjetLBC.dao.IAnnonceDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.time.*;
public class AnnonceDao implements IAnnonceDao{

    @Override
    public List<Annonce> getListOfAnnoncesWithUser(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public List<Annonce> getListOfAnnoncesWithID(String id) {
        return null;
    }

    @Override
    public List<Annonce> getListOfAnnonces() {
        return null;
    }

    @Override
    public List<Annonce> getAllAnnonces() {
        String url = "127.0.0.1:9003";
        Connection con;

        List<Annonce> annonces = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://" + url, "SA", "");
            Statement stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM ANNONCES");
            while (results.next()) {
                // Parcours des annonces
                Annonce annonce = new Annonce();
                annonce.setId(results.getInt(1));
                annonce.setTitre(results.getString(2));
                annonce.setContent(results.getString(3));
                Utilisateur userDao = UtilisateurDao.getUtilisateur(results.getString(4));
                annonce.setVendeur(userDao);
                annonce.setCreation(results.getDate(5));
                annonce.setModification(results.getDate(6));
                annonce.setStatut(results.getInt(7));
                Utilisateur userDao2 = UtilisateurDao.getUtilisateur(results.getString(8));
                annonce.setAcheteur(userDao2);
                annonce.setAchat(results.getDate(9));
                annonce.setPrix(results.getDouble(10));
                annonce.setNbVues(results.getInt(11));
                annonces.add(annonce);
            }
        }catch (SQLException e1) {
            e1.printStackTrace();
        }
        return annonces;
    }

    @Override
    public Annonce getAnnonce(String loginID) {
        return null;
    }


    @Override
    public void addAnnonce(Annonce annonce) {
        long nextId = 0;
        String url = "127.0.0.1:9003";
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://" + url, "SA", "");
            PreparedStatement ps = con.prepareStatement("INSERT INTO ANNONCES(ID, TITLE, CONTENT, USER_ID, CREATION_DATE," +
                    " UPDATE_DATE, PRICE, STATUS, BUYER, PURCHASE_DATE, NBVUES) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, nextId);
            ps.setString(2, annonce.getTitre());
            ps.setString(3, annonce.getContent());
            ps.setString(4, annonce.getVendeur().getID());
            ps.setObject(5, annonce.getCreation());
            ps.setObject(6, annonce.getModification());
            ps.setDouble(7, annonce.getPrix());
            ps.setInt(8, annonce.getStatut());
            ps.setString(9, null);
            ps.setObject(10, annonce.getAchat());
            ps.setLong(11, annonce.getNbVues());
            ps.execute();

        }
        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void updateAnnonceStatus(Annonce annonce, int status) {
        String url = "127.0.0.1:9003";
        Connection con;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://" + url, "SA", "");
            PreparedStatement ps = con.prepareStatement("UPDATE annonce SET status = ? WHERE id = ?");
            ps.setInt(1, status);
            ps.setLong(2, annonce.getId());
            ps.execute();

        }catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void deleteAnnonce(Annonce annonce) {
        String url = "127.0.0.1:9003";
        Connection con;

        try {
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://" + url, "SA", "");
            PreparedStatement ps = con.prepareStatement("DELETE FROM messages WHERE id = ?");
            ps.setLong(1, annonce.getId());
            ps.execute();


        }catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
