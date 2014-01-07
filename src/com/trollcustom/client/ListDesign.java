/**
 * 
 */
package com.trollcustom.client;

import java.util.ArrayList;

import com.trollcustom.shared.Troll;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.event.dom.client.ChangeEvent;

/**
 * @author Tonyo
 *
 */
public class ListDesign extends Composite implements HasText {

	private static ListDesignUiBinder uiBinder = GWT
			.create(ListDesignUiBinder.class);
	@UiField ListBox listNomTroll;
	@UiField Image imgTroll;
	@UiField Button buttonRetour;
	@UiField HTMLPanel htmlPanel;
	@UiField Button buttonEditer;
	@UiField Tree treeTroll;
	@UiField Button buttonSuppr;
	
	ArrayList<Troll> listTroll;
	
	String race1 = "Skrim" ;
	String race2 = "Kastar";
	String race3 = "Durakuir" ;
	String race4 = "Tomawak" ;
	String race5 = "Darkling";
@Override
protected void onLoad() {
	// TODO Auto-generated method stub
	super.onLoad();
	
	// On prépare le widget Tree
	treeTroll.addTextItem("Nom : ");
	treeTroll.addTextItem("Race : ");
	treeTroll.addTextItem("Attaque : ");
	treeTroll.addTextItem("Dégâts : ");
	treeTroll.addTextItem("Esquive : ");
	treeTroll.addTextItem("Régénération : ");
	treeTroll.addTextItem("Vie : ");
}
	interface ListDesignUiBinder extends UiBinder<Widget, ListDesign> {
	}

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public ListDesign() {
		initWidget(uiBinder.createAndBindUi(this));
		
		Trollcustom.get().getGreetingService().greetServer("liste",new AsyncCallback<ArrayList<Troll>>() {
			
			@Override
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				// On récupère la list de troll
				listTroll = result;
				if(listTroll.size() != 0){
					for(int i=0;i<result.size();i++){
						// On met a jour la list des noms
						listNomTroll.addItem(result.get(i).getNom());
					}
					
					listNomTroll.setSelectedIndex(0);
					miseaJourInfos();
				}
				
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				DialogBox dial = new DialogBox();
				Label error = new Label("Erreur : communication serveur a la récupération de la liste de troll");
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
		this.removeFromParent();
		Trollcustom.get().getMainPanel().setVisible(true);
	}
	
	@UiHandler("listNomTroll")
	void onListNomTrollClick(ClickEvent event) {
		miseaJourInfos();
		
		
	
		

	}
	@UiHandler("buttonEditer")
	void onButtonEditerClick(ClickEvent event) {
		this.removeFromParent();
		Trollcustom.get().getGreetingService().greetServer(("liste"), new AsyncCallback<ArrayList<Troll>>() {
			
			@Override
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				// On appel le constructeur du mode edition
				Design edit = new Design(listNomTroll.getItemText(listNomTroll.getSelectedIndex()),listNomTroll.getSelectedIndex()) ;

				RootPanel.get("designPanelEdit").add(edit);
				RootPanel.get("designPanelEdit").setVisible(true);
				Trollcustom.get().getMainPanel();

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
	@UiHandler("buttonSuppr")
	void onButtonSupprClick(ClickEvent event) {
		this.removeFromParent();
		
		// On passe l'id en paramêtre pour suppression
		Trollcustom.get().getGreetingService().greetServer((""+listNomTroll.getSelectedIndex()), new AsyncCallback<ArrayList<Troll>>() {
			
			@Override
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				
				// On recharge une nouvelle insance pour la voir la modification
				ListDesign list = new ListDesign();
				RootPanel.get("listPanel").add(list);

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
	@UiHandler("listNomTroll")
	void onListNomTrollChange(ChangeEvent event) {
		
	}
	
	private void miseaJourInfos(){
		treeTroll.removeItems(); // Suppression des anciennes données
		imgTroll.setStyleName("images");
		// On met a jour le widget Tree en fonction du troll selectionné
		imgTroll.setUrl(listTroll.get(listNomTroll.getSelectedIndex()).getURL());
		treeTroll.addTextItem("Nom : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getNom());
		treeTroll.addTextItem("Race : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getRace());
		treeTroll.addTextItem("Attaque : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getAttaque());
		treeTroll.addTextItem("Dégâts : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getDegats());
		treeTroll.addTextItem("Esquive : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getEsquive());
		treeTroll.addTextItem("Régénération : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getRegeneration());
		treeTroll.addTextItem("Vie : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getVie());
		
		//On récupère la race du troll qui vien d'etre selectionné
		//On affiche la compétence en fonction de la race
		if(listTroll.get(listNomTroll.getSelectedIndex()).getRace().equals(race1)){
			treeTroll.addTextItem("Bottes Secrètes : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence1());
			treeTroll.addTextItem("Hypnotisme : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence2());
		}
		else if(listTroll.get(listNomTroll.getSelectedIndex()).getRace().equals(race2)){
			treeTroll.addTextItem("Accélération Métabolique : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence1());
			treeTroll.addTextItem("Vampirisme : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence2());
			
		}
		else if(listTroll.get(listNomTroll.getSelectedIndex()).getRace().equals(race3)){
			treeTroll.addTextItem("Régénération Accrue : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence1());
			treeTroll.addTextItem("Rafale Psychique : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence2());
		
		}
		else if(listTroll.get(listNomTroll.getSelectedIndex()).getRace().equals(race4)){
			treeTroll.addTextItem("Camouflage : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence1());
			treeTroll.addTextItem("Projectile Magique : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence2());
		
		}
		else if(listTroll.get(listNomTroll.getSelectedIndex()).getRace().equals(race5)){
			treeTroll.addTextItem("Balayage : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence1());
			treeTroll.addTextItem("Siphon des âmes : \t" + listTroll.get(listNomTroll.getSelectedIndex()).getComptence2());
		
		}
	}
}
