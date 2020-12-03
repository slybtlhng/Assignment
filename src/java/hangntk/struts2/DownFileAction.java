/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ASUS
 */
public class DownFileAction extends ActionSupport {

    private InputStream inputStream;
    private String fileName;
    private long contentLength;

    public DownFileAction() {
    }

    public String execute() throws Exception {

        String path = ServletActionContext.getServletContext().getRealPath("/");
        File fileToDownload = new File(path+"//fileTribulation//"+fileName);

        inputStream = new FileInputStream(fileToDownload);
        contentLength = fileToDownload.length();

        return SUCCESS;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public long getContentLength() {
        return contentLength;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

}
