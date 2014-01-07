package com.trollcustom.client;


import java.util.ArrayList;

import com.trollcustom.shared.Troll;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Trollcustom implements EntryPoint {
	
	/*
	 * Variables
	 */
	int id=0;
	
	private static Trollcustom SINGLETON; 
	
	final VerticalPanel mainPanel = new VerticalPanel();
	VerticalPanel buttonPanel = new VerticalPanel();
	VerticalPanel corpsPanel = new VerticalPanel();
	
	
	Button bNew = new Button("Nouveau");
	Button bList = new Button("Trolls en détail");
	Button bTable = new Button("Trolls en tableau");
	Label describe = new Label("Choisissez une action : ");
	Label title = new Label("Troll Custom");
	
	String competence1="";
	String competence2="";
	
	/*
	 * Constantes
	 */
	String race1 = "Skrim" ;
	String race2 = "Kastar";
	String race3 = "Durakuir" ;
	String race4 = "Tomawak" ;
	String race5 = "Darkling";
	

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	

	/**
	 * 
	 */
	private Trollcustom() {
		super();
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		/**
		 * Déclaration du SINGLETON
		 */
		SINGLETON = this; 

		/**
		 * On demande au serveur de mettre a jour les infos avec le XML
		 * On lui envoi maj et on ne s'occupe pas de ce qu'il nous renvoi
		 */
		get().getGreetingService().greetServer("maj", new AsyncCallback<ArrayList<Troll>>() {
			
			
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				

			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		

	
		
		/**
		 * Déclaration divers
		 */
		// On aligne les éléments au centre
		describe.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		corpsPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		title.setStyleName("titre");
		
		// On ajoute les éléments aux panel
		//mainPanel.add(header);
		mainPanel.add(title);
		corpsPanel.add(buttonPanel);
		mainPanel.add(corpsPanel);
		buttonPanel.add(describe);
		buttonPanel.add(bList);
		buttonPanel.add(bTable);
		buttonPanel.add(bNew);
		
		
		// On ajoute les style au éléments
		mainPanel.setStyleName("mainPanel");
		buttonPanel.setStyleName("buttonPanel");
		bNew.setStyleName("button");
		bList.setStyleName("button");
		bTable.setStyleName("button");
		
		
		
		/**
		 * Ajout au RootPanel
		 */
		
		RootPanel.get("mainPanel").add(mainPanel);
		
		
		
		/**
		 * Handler gestion du click sur les boutons
		 */
		bNew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// On cache le panel courant et on ouvre un UIbinder
				mainPanel.setVisible(false);
				Design designPanel = new Design();
				RootPanel.get("designPanel").add(designPanel);	
				
			}
		});
		
		bList.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// On cache le panel courant et on ouvre un UIbinder
				ListDesign listPanel = new ListDesign();
				RootPanel.get("listPanel").add(listPanel);
				mainPanel.setVisible(false);
			}
		});
		
		bTable.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				// On cache le panel courant et on ouvre un UIbinder
				TableDesign tablePanel = new TableDesign();
				RootPanel.get("tablePanel").add(tablePanel);
				mainPanel.setVisible(false);
			}
		});
		
		
		
		
	
		
		
	}
	/**
	 * Méthodes Get()
	 */
	 public static Trollcustom get(){ 
		 return SINGLETON; 
		 }

		/**
	 * @return the greetingService
	 */
	public GreetingServiceAsync getGreetingService() {
		return greetingService;
	}

	/**
	 * @return the mainPanel
	 */
	public VerticalPanel getMainPanel() {
		return mainPanel;
	}


}
