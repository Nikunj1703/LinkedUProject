/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.mail.Part;

/**
 *
 * @author nratnap
 */
public class ImageBean {
     private Part uploadedFile;

    public ImageBean() {
    }

    public ImageBean(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
}
