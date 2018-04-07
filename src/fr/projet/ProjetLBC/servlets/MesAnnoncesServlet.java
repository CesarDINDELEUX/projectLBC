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

@WebServlet("/MesAnnoncesServlet")
public class MesAnnoncesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesAnnoncesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("USERID") != null) {
            String emailUtilisateur = session.getAttribute("USERID").toString();
            if (!(emailUtilisateur.equals(null) || emailUtilisateur.equals(""))) {
                AnnonceDao annonceDao = new AnnonceDao();
                List<Annonce> lol = annonceDao.getListOfAnnoncesWithID(emailUtilisateur);
                request.setAttribute("ANNONCES", lol);
                request.getRequestDispatcher("mesAnnonces.jsp").forward(request, response);
            }
            else{
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        }
        else{
            request.getRequestDispatcher("login.html").forward(request, response);
        }




    }

}
