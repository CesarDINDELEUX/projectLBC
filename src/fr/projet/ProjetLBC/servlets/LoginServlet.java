package fr.projet.ProjetLBC.servlets;

import fr.projet.ProjetLBC.beans.Annonce;
import fr.projet.ProjetLBC.beans.Utilisateur;
import fr.projet.ProjetLBC.dao.IAnnonceDao;
import fr.projet.ProjetLBC.dao.IUtilisateurDao;
import fr.projet.ProjetLBC.dao.hsqlImpl.AnnonceDao;
import fr.projet.ProjetLBC.dao.hsqlImpl.UtilisateurDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("Password");
        String nom = request.getParameter("nom");
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(email);
        utilisateur.setPassword(password);
        utilisateur.setNom(nom);
        IUtilisateurDao userDao = new UtilisateurDao();
        if (userDao.checkLogin(utilisateur)) {
            HttpSession session = request.getSession();
            session.setAttribute("USERID",email);
            IAnnonceDao annonceDao = new AnnonceDao();
            List<Annonce> myAnnonces = annonceDao.getListOfAnnoncesWithID(email);
            request.setAttribute("ANNONCES", myAnnonces);
            request.getRequestDispatcher("index2.html").forward(request, response);
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

}
