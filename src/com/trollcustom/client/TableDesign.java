package com.trollcustom.client;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlowPanel;
import com.trollcustom.shared.Troll;

public class TableDesign extends Composite implements HasText {

	private static TableDesignUiBinder uiBinder = GWT
			.create(TableDesignUiBinder.class);
	@UiField FlowPanel flowPanel;
	@UiField Button buttonRetour;
	
	/**
	 * Constantes
	 */
	String race1 = "Skrim" ;
	String race2 = "Kastar";
	String race3 = "Durakuir" ;
	String race4 = "Tomawak" ;
	String race5 = "Darkling";
	
	ArrayList<Troll> listTroll = new ArrayList<Troll>();
	Troll troll;
	Tree treeTroll = new Tree();
	
	interface TableDesignUiBinder extends UiBinder<Widget, TableDesign> {
	}

	public TableDesign() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
Trollcustom.get().getGreetingService().greetServer("liste",new AsyncCallback<ArrayList<Troll>>() {
			
			@Override
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				// On récupère la list de troll
				listTroll = result;
				// On récupère les trolls seulement si il y en a au moin 1
				if(result.size() !=0){
					for(int i=0;i<result.size();i++){
						//Création du tableau d'image
						Image img = new Image(result.get(i).getURL());
						Label nom = new Label(result.get(i).getNom());
						VerticalPanel vPanel = new VerticalPanel();
						final int id=i;
						
						vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
						vPanel.setSize("150px", "150px");
						vPanel.setStyleName("inline"); // Pour ranger les panel les un a côté des autres
						img.setSize("130px", "130px");
						img.setStyleName("imagesTable");
						
						troll = result.get(i);
						img.addClickHandler(new ClickHandler() {
							// Pour créer dynamiquement les handleur et avoir le troll courant
							Troll tr = troll;
							
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								// On créer la boite de dialogue qui donne les infos du troll
								final DialogBox dial = new DialogBox();
								VerticalPanel vertPanel = new VerticalPanel();
								HorizontalPanel hPanel = new HorizontalPanel();
								
								Button close = new Button("Close");
								Button modif = new Button("Modifier");
								Button suppr = new Button("Supprimer");
								
								Image img = new Image(tr.getURL());
								
								img.setSize("250px", "250px");
								miseaJourInfos(tr);
								
								vertPanel.add(img);
								vertPanel.add(treeTroll);
							
								hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
								vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
								treeTroll.setStyleName("center");
								
								close.setStyleName("button");
								modif.setStyleName("button");
								suppr.setStyleName("button");
								
								
								close.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										dial.hide();
									}
								});
								
								modif.addClickHandler(new ClickHandler() {
									int idTroll = id;
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										Design edit = new Design("",idTroll) ;

										RootPanel.get("designPanelEdit").add(edit);
										RootPanel.get("designPanelEdit").setVisible(true);
										RootPanel.get("tablePanel").setVisible(false);
										dial.hide();
									}
								});
								
								suppr.addClickHandler(new ClickHandler() {
									int idTroll = id; 
									@Override
									public void onClick(ClickEvent event) {
										// TODO Auto-generated method stub
										
										// On passe l'id en paramêtre pour suppression
										Trollcustom.get().getGreetingService().greetServer((""+idTroll), new AsyncCallback<ArrayList<Troll>>() {
											
											@Override
											public void onSuccess(ArrayList<Troll> result) {
												// TODO Auto-generated method stub
												
												// On recharge une nouvelle insance pour la voir la modification
												TableDesign table = new TableDesign();
												RootPanel.get("tablePanel").remove(0);
												RootPanel.get("tablePanel").add(table);
												dial.hide();

											}

											@Override
											public void onFailure(Throwable caught) {
												// TODO Auto-generated method stub
												DialogBox dial = new DialogBox();
												Label error = new Label("Erreur : communication serveur");
												dial.add(error);
												dial.setAnimationEnabled(true);
												dial.center();
												dial.show();
												RootPanel.get("mainPanel").setVisible(true);
											}
										});

									}
								});
								
								hPanel.add(close);
								hPanel.add(modif);
								hPanel.add(suppr);
								vertPanel.add(hPanel);
								dial.add(vertPanel);
								dial.setAnimationEnabled(true);
								dial.center();
								dial.show();
								
							}
						});
						vPanel.add(nom);
						vPanel.add(img);
						flowPanel.add(vPanel);
					}

					
				}
								
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				DialogBox dial = new DialogBox();
				Label error = new Label("Erreur : communication serveur");
				dial.add(error);
				dial.setAnimationEnabled(true);
				dial.center();
				dial.show();
			}
		});

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}



	@UiHandler("buttonRetour")
	void onButtonRetourClick(ClickEvent event) {
		this.setVisible(false);
		Trollcustom.get().getMainPanel().setVisible(true);
		
	}
	
	private void miseaJourInfos(Troll troll){
		treeTroll.removeItems(); // Suppression des anciennes donn�es
		// On met a jour le widget Tree en fonction du troll selectionn�
		treeTroll.addTextItem("Nom : 							" + troll.getNom());
		treeTroll.addTextItem("Race : 							" + troll.getRace());
		treeTroll.addTextItem("Attaque : 						" + troll.getAttaque());
		treeTroll.addTextItem("Dégâts : 						" + troll.getDegats());
		treeTroll.addTextItem("Esquive : 						" + troll.getEsquive());
		treeTroll.addTextItem("Régénération : 					" + troll.getRegeneration());
		treeTroll.addTextItem("Vie : 							" + troll.getVie());
		
		//On r�cup�re la race du troll qui vien d'etre selectionné
		//On affiche la compétence en fonction de la race
		if(troll.getRace().equals(race1)){
			treeTroll.addTextItem("Bottes Secrètes :          " + troll.getComptence1());
			treeTroll.addTextItem("Hypnotisme :               " + troll.getComptence2());
		}
		else if(troll.getRace().equals(race2)){
			treeTroll.addTextItem("Accélération Métabolique : " + troll.getComptence1());
			treeTroll.addTextItem("Vampirisme : \t" + troll.getComptence2());
			
		}
		else if(troll.getRace().equals(race3)){
			treeTroll.addTextItem("Régénération Accrue :      " + troll.getComptence1());
			treeTroll.addTextItem("Rafale Psychique :         " + troll.getComptence2());
		
		}
		else if(troll.getRace().equals(race4)){
			treeTroll.addTextItem("Camouflage :               " + troll.getComptence1());
			treeTroll.addTextItem("Projectile Magique :       " + troll.getComptence2());
		
		}
		else if(troll.getRace().equals(race5)){
			treeTroll.addTextItem("Balayage :                 " + troll.getComptence1());
			treeTroll.addTextItem("Siphon des âmes :          " + troll.getComptence2());
		
		}
	}

}
