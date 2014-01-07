package com.trollcustom.client;

import java.util.ArrayList;
import com.trollcustom.shared.Troll;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(Troll name) throws IllegalArgumentException; // Pour l'ajout d'un troll
	String greetServer(String id, Troll name) throws IllegalArgumentException; // Pour la modification et suppression d'un troll
	ArrayList<Troll> greetServer(String name) throws IllegalArgumentException; // Pour récupéré la list de troll
	String greetServer() throws IllegalArgumentException; // Pour récupéré l'url de l'image uploader
	String imgUploaded(String url) throws IllegalArgumentException; // Pour récupéré l'url de l'image uploader
}

