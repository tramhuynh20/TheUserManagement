package servlets;

import daos.UserDAO;
import dtos.User;
import dtos.ErrorMsg;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

@WebServlet(name = "EditProfileController", urlPatterns = {"/EditProfileController"})
public class EditProfileController extends HttpServlet {

    private static final String DISPLAY_ALL_CONTROLLER = "DisplayAllController";
    private static final String ERROR = "error.jsp";
    private static final String EDIT_PROFILE = "editProfile.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");

            if (action == null) {
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                UserDAO dao = new UserDAO();

                String userName = request.getParameter("userName").trim();
                String email = request.getParameter("email").trim();
                String password = request.getParameter("password").trim();
                String passwordConfirm = request.getParameter("passwordConfirm").trim();
                String phoneNumber = request.getParameter("phoneNumber").trim();
                String imageURL;

                ErrorMsg error = new ErrorMsg();

                boolean check = true;
                if (!userName.matches("^[a-zA-Z ]{1,50}$")) {
                    check = false;
                    error.setUserName("Invalid Name!!!");
                }
                if (!email.matches("^[\\w.+\\-]+@gmail\\.com$")) {
                    check = false;
                    error.setEmail("Invalid Email!!!");
                }
                if (!password.equals(passwordConfirm)) {
                    check = false;
                    error.setPasswordConfirm("Password confirm is different from password!!!");
                }
                if (password.length() < 7 || password.length() > 17) {
                    check = false;
                    error.setPassword("Password requires 8-16 characters!!!");
                }
                if (!phoneNumber.matches("^[0-9]{10,12}$")) {
                    check = false;
                    error.setPhoneNumber("Phone Number requires 10-12 digits!!!");
                }
                if (check == false) {
                    imageURL=dao.getUserByID(user.getUserID()).getImageURL();
                    String userID=dao.getUserByID(user.getUserID()).getUserID();
                    User userObj = new User(userID,userName, email, phoneNumber, imageURL, password);
                    
                    request.setAttribute("error", error);
                    request.setAttribute("user", userObj);
                    url = EDIT_PROFILE;
                } else {
                    Part filePart = request.getPart("photo");
                    if (getFileName(filePart).equals("")) {
                        imageURL = request.getParameter("imageURL");
                    } else {
                        imageURL = uploadFile(request);
                    }
                    String userID=dao.getUserByID(user.getUserID()).getUserID();
                    User userObj = new User(userID,userName, email, phoneNumber, imageURL, toHexString(getSHA(password)));
                    dao.updateUser(userObj);
                    url = DISPLAY_ALL_CONTROLLER;
                    response.sendRedirect(url);
                    return;
                }
            } else if (action.equals("display")) {

                HttpSession session = request.getSession();
                User user= (User) session.getAttribute("user");
               
                UserDAO dao=new UserDAO();
                User userObj= dao.getUserByID(user.getUserID());
                
                request.setAttribute("user", userObj);
                url=EDIT_PROFILE;
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.getMessage();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName;
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);

            String applicationPath;
            String basePath;

            InputStream inputStream = null;
            OutputStream outputStream = null;
            File outputFilePath;
            int read;
            byte[] bytes;
            try {
                applicationPath = request.getServletContext().getRealPath("");
                applicationPath = applicationPath.substring(0, applicationPath.length() - 10);
                basePath = applicationPath + "web\\image\\";

                outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                read = 0;
                bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                applicationPath = request.getServletContext().getRealPath("");
                basePath = applicationPath + "image\\";

                outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                read = 0;
                bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.getMessage();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            e.getMessage();
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
