
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;



@ManagedBean
@SessionScoped
public class FileUploadMBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Part file1;
    private Part file2;
    private String message;
    public static final int BUFFER_SIZE = 1024;
    String tempPath;
    String tempName;
    
    
    public Part getFile1() {
        return file1;
    }
    public void setFile1(Part file1) {
        this.file1 = file1;
    }
    public Part getFile2() {
        return file2;
    }
    public void setFile2(Part file2) {
        this.file2 = file2;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String uploadFile() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        
        
        boolean file1Success = false;
       // if (file1.getSize() &gt; 0) {
            String fileName = getFileNameFromPart(file1);
            System.out.println("Here it is----"+file1);
            /**
            * destination where the file will be uploaded
            */
            File outputFile = new File(path + File.separator + "WEB-INF"
                    + File.separator + fileName);
            inputStream = file1.getInputStream();
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            file1Success = true;
        //}
        boolean file2Success = false;
       
        if (file1Success ) {
            System.out.println("File uploaded to : " + path);
            /**
            * set the success message when the file upload is successful
            */
            setMessage("File uploaded successfully ");
        } else {
            /**
            * set the error message when error occurs during the file upload
            */
            setMessage("Error, select atleast one file!");
        }
        /**
        * return to the same view
        */
        tempPath=path+"\\"+"WEB-INF"+"\\"+tempName;
        
        return null;
    }
    
    public String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                tempName=fileName;
                return fileName;
            }
        }
        return null;
    }
    
    public String findFilePath(){
        if(tempPath==null)
            return null;
        else
            return tempPath;
    }
    
    public void downloadFile() {
    
    tempPath = tempPath.replace("\\", "/");
    System.out.println("Finally we have this1-- "+tempPath);
    File file = new File(tempPath);
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;filename="+tempName+"");  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    }  

}
}