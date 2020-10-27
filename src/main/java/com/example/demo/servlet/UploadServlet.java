package com.example.demo.servlet;

import ch.qos.logback.classic.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;



@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    private final Logger logger = (Logger) LoggerFactory.getLogger(UploadServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        // 设置缓冲区大小，这里是4kb
        factory.setSizeThreshold(4096);
        // 设置缓冲区目录
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        // 设置最大文件尺寸，这里是4MB
        upload.setSizeMax(4194304);

        String error = null;
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (!item.isFormField()) {
                    String uploadPath = request.getServletContext().getRealPath("/usr.config");
                    File fold = new File(uploadPath);
                    if (!fold.exists()) {
                        fold.mkdir();
                    }
                    String fileName = item.getName();
                    try {
                        File targetFile = new File(uploadPath + "/" + fileName);
                        if (!targetFile.exists()) {
                            targetFile.createNewFile();
                        }
                        item.write(targetFile);
                    } catch (Exception ex) {
                        error = ex.getMessage();
                        if (error == null) {
                            error = ex.toString();
                        }
                        logger.error(error, ex);
                    }
                }
            }
        } catch (FileUploadException ex) {
            error = ex.getMessage();
            if (error == null) {
                error = ex.toString();
            }
            logger.error(error, ex);
        }
        try (PrintWriter pw = response.getWriter()) {
            if (error != null) {
                pw.append("faild  -> <b style=\"color:#FF0000;\">（严重）" + error + "</b>");
            } else {
                pw.append("success  -> 上传完成！");
            }
            pw.flush();
        }
    }


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
