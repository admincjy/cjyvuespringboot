package com.wecat.small.utils;


import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@WebServlet(name = "fileServlet",urlPatterns = "/servlet/getfile")
public class FileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        ResourceBundle resource = ResourceBundle.getBundle("application");
        String path = resource.getString("upload.path") + request.getParameter("file");

        Path source = Paths.get(path);
        try {
            response.setContentType(Files.probeContentType(source));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        int size = 0;
        try {
            size = inputStream.available();
        } catch (IOException e ) {
            System.out.println(e.getMessage());
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }

        byte[] data= new byte[size];
        try {
            inputStream.read(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            outputStream.write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
