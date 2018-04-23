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

        // optional, in case we write something on output
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // obtain a path where to store files, we can also use absolute paths it depends on the  permissions
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        String fileName=null;
        String numeimg=null;
        // creates upload folder if it does not exists
        File uploadFolder = new File(uploadFilePath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }


        // write all the files in our upload folder
        int counter=0;
        for (Part part : request.getParts()) {
            if (part != null && part.getSize() > 0) {
                fileName= part.getSubmittedFileName();
                String contentType = part.getContentType();

//                    // server side validation: allows only JPEG files to be uploaded, feel free to change this behaviour
                if (contentType!=null && contentType.equalsIgnoreCase("image/jpeg")) {


                    //make each file name unique
                    fileName = System.currentTimeMillis() + fileName;

                    // actual write on the disk
                    part.write(uploadFilePath + File.separator + fileName);
                    if(fileName!=null)
                        numeimg = fileName;

//                    // feel free to remove the following, eventually in the end (after for ends) redirect to some url
//                    PrintWriter writer = response.getWriter();
//                    writer.append("File successfully uploaded to: "
//                            + uploadFolder.getAbsolutePath()
//                            + File.separator
//                            + fileName
//                            + "<br>\r\n");
                }


            }

        }

        String moneda = request.getParameter("moneda");
        String descriere = request.getParameter("descriere");


        System.out.println("moneda="+moneda+", imagine:"+numeimg+"descriere="+descriere);


        // apelez db-ul si scriu cele 3 valori in db

        DBO d = new DBO();

        d.insertAlbum(moneda,numeimg, descriere);

        response.sendRedirect("public.html");



    }
}


