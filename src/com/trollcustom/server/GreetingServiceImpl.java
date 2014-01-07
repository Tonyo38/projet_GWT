package com.trollcustom.server;

import java.util.ArrayList;

import com.trollcustom.client.GreetingService;
import com.trollcustom.shared.Troll;
import com.trollcustom.shared.XMLtools;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	ArrayList<Troll> list = new ArrayList<Troll>();
	String filePath = "save.xml";
	String filePathImg = "img.xml";
	String imgUploaded = "";
public String greetServer(Troll input) throws IllegalArgumentException {
	// On assigne un id au troll
	input.setId(list.size());
	// On ajoute le troll a la list
	list.add(input);
	// On écrit la list dans le fichier XML
	XMLtools.ecrire(list, this.getServletContext().getRealPath(filePath));
	
	return "OK";
	
}
@Override
public ArrayList<Troll> greetServer(String name)
		throws IllegalArgumentException {
	// TODO Auto-generated method stub
	// On récupère la list du fichier XML
	list = XMLtools.lire(this.getServletContext().getRealPath(filePath));
	// Si on demande une list on la renvoi
	if(name.equals("liste"))return list;
	else{ // sinon si on demande une maj on renvoi rien car elle a été faite précédement
		if(name.equals("maj")) return null;
		else{ // sinon c'est qu'on recoi un id et la on supprime
			int id = Integer.parseInt(name);
			list.remove(id);
			XMLtools.ecrire(list,this.getServletContext().getRealPath(filePath)); // On réécrit la list dans le XML
			return null;
		}
	}
	
}

/**
 * Modification d'un troll
 */
@Override
public String greetServer(String id, Troll troll)
		throws IllegalArgumentException {
	// TODO Auto-generated method stub
	
	// On met a jour les informations
	list.get(troll.getId()).setAttaque(troll.getAttaque());
	list.get(troll.getId()).setDegats(troll.getDegats());
	list.get(troll.getId()).setEsquive(troll.getEsquive());
	list.get(troll.getId()).setNom(troll.getNom());
	list.get(troll.getId()).setRace(troll.getRace());
	list.get(troll.getId()).setRegeneration(troll.getRegeneration());
	list.get(troll.getId()).setURL(troll.getURL());
	list.get(troll.getId()).setVie(troll.getVie());
	list.get(troll.getId()).setComptence1(troll.getComptence1());
	list.get(troll.getId()).setComptence2(troll.getComptence2());
	XMLtools.ecrire(list,this.getServletContext().getRealPath(filePath)); // On réécri le XML
	return "OK";
}
@Override
public String greetServer() throws IllegalArgumentException {
	// TODO Auto-generated method stub
	
	return XMLtools.lireImg(this.getServletContext().getRealPath(filePathImg));
	
}
@Override
public String imgUploaded(String url) throws IllegalArgumentException {
	// TODO Auto-generated method stub
	imgUploaded = url;
	return null;
}






		
	}



