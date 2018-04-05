package fr.projet.ProjetLBC.dao;

import fr.projet.ProjetLBC.beans.Utilisateur;

import java.util.List;

public interface IUtilisateurDao {
    boolean checkLogin(Utilisateur utilisateur);

    List<Utilisateur> getAllUsers();

    List<Utilisateur> getListOfUtilisateurs();
    Utilisateur getUtilisateurById(String id);
    void addUtilisateur(Utilisateur utilisateur);
    void updateUtilisateur(Utilisateur utilisateur);
    void deleteUtilisateur(Utilisateur utilisateur);

}

