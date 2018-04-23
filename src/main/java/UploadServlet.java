import login.DBO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "uploadServlet", urlPatterns = { "/upload" }, loadOnStartup = 1) // loads when tomcat starts

@MultipartConfig(fileSizeThreshold = 6*1024*1024, // 6 MB. this is the limit on temp mem storage  vs file /tmp storage
        maxFileSize = 1000*1024*1024, // 10 MB, this is the max of a single uploaded file
        maxRequestSize = 2000*1024*1024 // 20 MB , this is the total max size for a request
)
public class UploadServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "imagini";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        String fileName=null;
        String numeimg=null;

        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }



        int counter=0;
        for (Part part : request.getParts()) {
            if (part != null && part.getSize() > 0) {
                fileName= part.getSubmittedFileName();
                String contentType = part.getContentType();


                if (contentType!=null && contentType.equalsIgnoreCase("image/jpeg")) {



                    fileName = System.currentTimeMillis() + fileName;


                    part.write(uploadFilePath + File.separator + fileName);
                    if(fileName!=null)
                        numeimg = fileName;


                }


            }

        }

        String moneda = request.getParameter("moneda");
        String descriere = request.getParameter("descriere");


        System.out.println("moneda="+moneda+", imagine:"+numeimg+"descriere="+descriere);




        DBO d = new DBO();

        d.insertAlbum(moneda,numeimg, descriere);

        response.sendRedirect("public.html");



    }
}


