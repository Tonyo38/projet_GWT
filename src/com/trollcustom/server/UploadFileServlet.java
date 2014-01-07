package com.trollcustom.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.trollcustom.shared.XMLtools;


public class UploadFileServlet extends HttpServlet {   // �tend de HttpServlet, ce n'est pas un RPC
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      try {
	         ServletFileUpload upload = new ServletFileUpload();
	         resp.setContentType("text/html");
	         FileItemIterator iterator = upload.getItemIterator(req);
	         
	         while (iterator.hasNext()) {
	            FileItemStream item = iterator.next();
	            if (!item.isFormField()) {   // On ne s'int�resse pas aux diff�rents champs du formulaire
	               String filename = UUID.randomUUID().toString() + item.getName() ;   // Cr�ation d'un nom al�atoire pour la sauvegarde sur le serveur
	               String filePath = "uploaded/" + filename;
	               File file = new File(this.getServletContext().getRealPath(filePath));
	               // R�cup�ration du fichier soumis et sauvegarde dans le dossier "uploaded"
	               FileOutputStream out = new FileOutputStream(file);
	               InputStream in = item.openStream();
	               out.write(IOUtils.toByteArray(in));
	               out.close();
	               resp.getOutputStream().print(item.getName() + "|" + filename);   // On renvoie le nom d'origine du fichier ainsi que son nom al�atoire
	               XMLtools.ecrireImg(file.getName(),this.getServletContext().getRealPath("img.xml")); // On �crit l'url de l'image dans un fichier XML pour la r�cup�r� c�t� client
	            }
	         }
	         
	      } catch (Exception ex) {
	         throw new ServletException(ex);
	      }
	   }
	}