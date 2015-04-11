
package controller;

import static com.sun.codemodel.JOp.gt;
import com.sun.org.apache.xerces.internal.impl.Constants;
import dao.StudentDAO;
import dao.StudentDAOImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import static javax.management.Query.gt;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.jcp.xml.dsig.internal.dom.Utils;
import javax.management.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;



@ManagedBean
@SessionScoped
public class ImageUploadBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Part file1;
    private Part file2;
    private String message;
    public static final int BUFFER_SIZE = 1024;
    String tempPath;
    String tempName;
    
    @ManagedProperty(value="#{controller}")
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

//    @ManagedProperty(value="#{studentController}")
//    private StudentController studentController;
//
//    public StudentController getStudentController() {
//        return studentController;
//    }
//
//    public void setStudentController(StudentController studentController) {
//        this.studentController = studentController;
//    }
//    
    
    
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
            setMessage("Photo successfully uploaded");
        } else {
            /**
            * set the error message when error occurs during the file upload
            */
            setMessage("Error, select atleast one file!");
        }
        /**
        * return to the same view
        */
        //path=null;
        //tempPath="./resources/"+tempName;
        tempPath=path+"\\"+"WEB-INF"+"\\"+tempName;
        tempPath = MoveFile();
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
    
    public String findPath(){
        //System.out.println("Old Path is : "+tempPath);
        
        System.out.println("New Path is : "+tempPath);
        
        return tempPath;
    }
    
    public String MoveFile()
    {	
        String newPath="";
    	try{
 
    	   File afile =new File(tempPath);
           tempPath=tempPath.replace("\\build\\web\\WEB-INF\\"+afile.getName(), "\\web\\resources\\profilePics\\");
          
           File temp = new File(tempPath + afile.getName());
           newPath = "./resources/profilePics/" + afile.getName();
    	   if(afile.renameTo(temp)){
               System.out.println(temp);
    		System.out.println("File is moved successful!");
                Thread.sleep(2000);
    	   }else{
    		System.out.println("File is failed to move!");
    	   }
 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return newPath;
    }
    
    public String findPhotoPath(){
        String tempEmail = controller.findEmail();
        
        StudentDAO aStudentDAO = new StudentDAOImpl(); 
        
        String myTempPath = aStudentDAO.findPathByEmail(tempEmail);
        
        return myTempPath;
        
    }
    
}