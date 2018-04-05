package fr.projet.ProjetLBC.dao;

import fr.projet.ProjetLBC.beans.Annonce;
import fr.projet.ProjetLBC.beans.Utilisateur;

import java.util.List;

public interface IAnnonceDao {

    List<Annonce> getListOfAnnoncesWithUser(Utilisateur utilisateur);
    List<Annonce> getListOfAnnoncesWithID(String id);
    List<Annonce> getListOfAnnonces();
    List<Annonce> getAllAnnonces();
    Annonce getAnnonce(String loginID);
    void addAnnonce(Annonce annonce);
    void updateAnnonceStatus(Annonce annonce, int status);
    void deleteAnnonce(Annonce annonce);

}
