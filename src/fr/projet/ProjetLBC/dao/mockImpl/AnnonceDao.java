package fr.projet.ProjetLBC.dao.mockImpl;

import java.util.ArrayList;
import java.util.List;

import fr.projet.ProjetLBC.beans.Annonce;
import fr.projet.ProjetLBC.beans.Statuts;
import fr.projet.ProjetLBC.beans.Utilisateur;
import fr.projet.ProjetLBC.dao.IAnnonceDao;

public class AnnonceDao implements IAnnonceDao {

    private static List<Annonce> listOfAnnonces;

    public AnnonceDao() {
        listOfAnnonces = new ArrayList<>();
        Annonce annonce = new Annonce();
        annonce.setId(1);
        annonce.setTitre("Livre 1");
        annonce.setVendeur(new Utilisateur());
        annonce.getVendeur().setId("ADMIN");
        annonce.setStatut(Statuts.PUBLIE);
        listOfAnnonces.add(annonce);

        annonce = new Annonce();
        annonce.setId(2);
        annonce.setTitre("Livre 2");
        annonce.setVendeur(new Utilisateur());
        annonce.getVendeur().setId("ADMIN");
        annonce.setStatut(Statuts.ANNULE);
        listOfAnnonces.add(annonce);

        annonce = new Annonce();
        annonce.setId(3);
        annonce.setTitre("Livre 3");
        annonce.setVendeur(new Utilisateur());
        annonce.getVendeur().setId("TOTO");
        annonce.setStatut(Statuts.ANNULE);
        listOfAnnonces.add(annonce);
    }

    @Override
    public List<Annonce> getAnnonces(String loginId) {
        List<Annonce> myAnnonces = new ArrayList<>();
        for (Annonce annonce : listOfAnnonces) {
            // V�rifie si on peut la voir
            if (annonce.getVendeur().getId().equals(loginId)
                    || annonce.getStatut() == Statuts.PUBLIE) {
                myAnnonces.add(annonce);
            }
        }
        return myAnnonces;
    }




}
