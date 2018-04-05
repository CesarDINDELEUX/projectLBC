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
        import java.io.IOException;
        import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String passwordconfirmation = request.getParameter("passwordconfirmation");
        String email = request.getParameter("email");
        String nom = request.getParameter("nom");
        String telephone = request.getParameter("telephone");

        if(password.equals(passwordconfirmation)) {

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(email);
            utilisateur.setPassword(password);
            utilisateur.setNom(nom);
            utilisateur.setTelephone(telephone);

            IUtilisateurDao userDao = new UtilisateurDao();
            userDao.addUtilisateur(utilisateur);

            request.getRequestDispatcher("index2.html").forward(request, response);
        } else {
            request.getRequestDispatcher("register.html").forward(request, response);
        }
    }

}

