/**
 * 
 */
package com.trollcustom.client;

import java.util.ArrayList;

import com.trollcustom.shared.Troll;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

/**
 * @author Tonyo
 *
 */
public class Design extends Composite implements HasText,IsSerializable {

	private static DesignUiBinder uiBinder = GWT.create(DesignUiBinder.class);
	@UiField DockPanel mainPanel;
	@UiField VerticalPanel centerPanel;
	@UiField VerticalPanel gauchePanel;
	@UiField VerticalPanel droitePanel;
	@UiField ListBox listBoxRace;
	@UiField TextBox textBoxAttaque;
	@UiField Button buttonAttMoins;
	@UiField Button buttonAttPlus;
	@UiField TextBox textBoxDegats;
	@UiField Button buttonDegMoins;
	@UiField Button buttonDegPlus;
	@UiField TextBox textBoxEsq;
	@UiField Button buttonEsqMoins;
	@UiField Button buttonEsqPlus;
	@UiField TextBox textBoxReg;
	@UiField Button buttonRegMoins;
	@UiField Button buttonRegPlus;
	@UiField TextBox textBoxVie;
	@UiField Button buttonVieMoins;
	@UiField Button buttonViePlus;
	@UiField Image imgTroll;
	@UiField TextBox textBoxCompetence1;
	@UiField TextBox textBoxCompetence2;
	@UiField Button buttonComp1Moins;
	@UiField Button buttonComp2Moins;
	@UiField Button buttonComp1Plus;
	@UiField Button buttonComp2Plus;
	@UiField Label labelCompetence1;
	@UiField Label labelCompetence2;
	@UiField Button buttonSave;
	@UiField TextBox textBoxNom;
	@UiField Label labelRestSpe;
	@UiField Label labelRest;
	@UiField Button buttonRetour;
	@UiField FileUpload file;
	@UiField FormPanel formPanel;
	@UiField Button button;
	@UiField Button buttonClear;
	
	String race1 = "Skrim" ;
	String race2 = "Kastar";
	String race3 = "Durakuir" ;
	String race4 = "Tomawak" ;
	String race5 = "Darkling";
	
	String mode = "new";
	
	String dossierUpload = "uploaded/";
	
	int compMax = 50, nbComp=0;
	int compSpeMax=20, nbCompSpe=0;
	int id=0;

	interface DesignUiBinder extends UiBinder<Widget, Design> {
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
	
	/**
	 * Constructeur par défaut
	 */
	public Design() {
		initWidget(uiBinder.createAndBindUi(this));
		//ajout des races dans la listebox
		MaJlistRace();
		
		// On configure le FormPanel pour l'envoi de fichier
		// On encode en multipart
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setAction(GWT.getModuleBaseURL() + "uploadFile");
		formPanel.setMethod(FormPanel.METHOD_POST);
		
	}
	
	/**
	 * Constructeur pour le mode édition
	 * @param Name
	 * @param id
	 */
	public Design(String Name,final int id) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// ajout des races dans la listbox
		MaJlistRace();
		
		//on récupére l'id du troll passé en parametre
		this.id=id;
		// On configure le FormPanel pour l'envoi de fichier
		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setAction(GWT.getModuleBaseURL() + "uploadFile");
		formPanel.setMethod(FormPanel.METHOD_POST);
		
		//On demande au serveur l'envoi d'une liste
		Trollcustom.get().getGreetingService().greetServer("liste", new AsyncCallback<ArrayList<Troll>>() {
			
			@Override
			public void onSuccess(ArrayList<Troll> result) {
				// TODO Auto-generated method stub
				
				//On entre en mode édition
				mode = "edit";
				
				//On remplie les champs avec le troll selectionné (on utilise l'id)
				
				textBoxAttaque.setText("" + result.get(id).getAttaque());
				textBoxDegats.setText("" + result.get(id).getDegats());
				textBoxEsq.setText("" + result.get(id).getEsquive());
				textBoxReg.setText("" + result.get(id).getRegeneration());
				textBoxVie.setText("" + result.get(id).getVie());
				textBoxCompetence1.setText("" + result.get(id).getComptence1());
				textBoxCompetence2.setText("" + result.get(id).getComptence2());
				textBoxNom.setText("" + result.get(id).getNom());
				
				// On met a jout la listbox
				if(result.get(id).getRace().equals(race1)){
					listBoxRace.setItemSelected(0, true);
				} else if(result.get(id).getRace().equals(race2)){
					listBoxRace.setItemSelected(1, true);
				}else if(result.get(id).getRace().equals(race3)){
					listBoxRace.setItemSelected(2, true);
				}else if(result.get(id).getRace().equals(race4)){
					listBoxRace.setItemSelected(3, true);
				}else if(result.get(id).getRace().equals(race5)){
					listBoxRace.setItemSelected(4, true);
				}
				imgTroll.setUrl(result.get(id).getURL());
				

				
				//On met à jour le nombre de compétence
				nbComp = Integer.parseInt(textBoxAttaque.getText()) + Integer.parseInt(textBoxDegats.getText()) + Integer.parseInt(textBoxEsq.getText()) + Integer.parseInt(textBoxVie.getText()) + Integer.parseInt(textBoxReg.getText());
				nbCompSpe = Integer.parseInt(textBoxCompetence1.getText()) + Integer.parseInt(textBoxCompetence2.getText());
				labelRest.setText("Restant : " + (compMax-nbComp));
				labelRestSpe.setText("Restant : " + (compSpeMax-nbCompSpe));
			
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				DialogBox dial = new DialogBox();
				Label error = new Label("Erreur : communication serveur");
				dial.add(error);
				dial.setAnimationEnabled(true);
				dial.center();
			}
		});
	}

	public void setText(String text) {
		
	}

	/**
	 * Gets invoked when the default constructor is called
	 * and a string is provided in the ui.xml file.
	 */
	public String getText() {
		
		return "  ";
	}

	/**
	 * @return the uiBinder
	 */
	public static DesignUiBinder getUiBinder() {
		return uiBinder;
	}

	/**
	 * @param uiBinder the uiBinder to set
	 */
	public static void setUiBinder(DesignUiBinder uiBinder) {
		Design.uiBinder = uiBinder;
	}
	
	

/**
 * Déclaration des actions sur les bouton plus et moins
 * @param event
 */

	@UiHandler("buttonAttMoins")
	void onButtonAttMoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxAttaque.getText());
		if(val>0){
			nbComp--;
			val--;
			textBoxAttaque.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonAttPlus")
	void onButtonAttPlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxAttaque.getText());
		if(nbComp<compMax){		
			nbComp++;
			val++;
			textBoxAttaque.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
		
	}
	
	@UiHandler("buttonDegMoins")
	void onButtonDegMoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxDegats.getText());
		if(val>0){
			nbComp--;
			val--;
			textBoxDegats.setText("" + val);
		}
		else textBoxDegats.setText("" + 0);
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonDegPlus")
	void onButtonDegPlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxDegats.getText());
		if(nbComp<compMax){
			nbComp++;
			val++;
			textBoxDegats.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonEsqMoins")
	void onButtonEsqMoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxEsq.getText());
		if(val>0){
			nbComp--;
			val--;
			textBoxEsq.setText("" + val);
		}
		else textBoxEsq.setText("" + 0);
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonEsqPlus")
	void onButtonEsqPlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxEsq.getText());
		if(nbComp<compMax){
			nbComp++;
			val++;
			textBoxEsq.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonRegMoins")
	void onButtonRegMoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxReg.getText());
		if(val>0){
			nbComp--;
			val--;
			textBoxReg.setText("" + val);
		}
		else textBoxReg.setText("" + 0);
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonRegPlus")
	void onButtonRegPlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxReg.getText());
		if(nbComp<compMax){
			nbComp++;
			val++;
			textBoxReg.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonVieMoins")
	void onButtonVieMoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxVie.getText());
		if(val>0){
			nbComp--;
			val--;
			textBoxVie.setText("" + val);
		}
		else textBoxVie.setText("" + 0);
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonViePlus")
	void onButtonViePlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxVie.getText());
		if(nbComp<compMax){
			nbComp++;
			val++;
			textBoxVie.setText("" + val);
		}
		labelRest.setText("Restant : " + (compMax-nbComp));
	}
	
	@UiHandler("buttonComp1Moins")
	void onButtonComp1MoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxCompetence1.getText());
		if(val>0){
			nbCompSpe--;
			val--;
			textBoxCompetence1.setText("" + val);
		}
		else textBoxCompetence1.setText("" + 0);
		labelRestSpe.setText("Restant : " + (compSpeMax-nbCompSpe));
	}
	
	@UiHandler("buttonComp1Plus")
	void onButtonComp1PlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxCompetence1.getText());
		if(nbCompSpe<compSpeMax){
			nbCompSpe++;
			val++;
			textBoxCompetence1.setText("" + val);
		}
		labelRestSpe.setText("Restant : " + (compSpeMax-nbCompSpe));
	}
	
	@UiHandler("buttonComp2Moins")
	void onButtonComp2MoinsClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxCompetence2.getText());
		if(val>0){
			nbCompSpe--;
			val--;
			textBoxCompetence2.setText("" + val);
		}
		else textBoxCompetence2.setText("" + 0);
		labelRestSpe.setText("Restant : " + (compSpeMax-nbCompSpe));
	}
	
	@UiHandler("buttonComp2Plus")
	void onButtonComp2PlusClick(ClickEvent event) {
		int val = Integer.parseInt(textBoxCompetence2.getText());
		if(nbCompSpe<compSpeMax){
			nbCompSpe++;
			val++;
			textBoxCompetence2.setText("" + val);
		}
		labelRestSpe.setText("Restant : " + (compSpeMax-nbCompSpe));
	}
	@UiHandler("listBoxRace")
	void onListBoxRaceChange(ChangeEvent event) {
		
		
		//On change les noms des compétences en fonction de la race et l'image associé
		// Si un fichier est choisi l'url de l'image n'est pas récupéré
		switch(listBoxRace.getSelectedIndex()){
		
		case 0 :	labelCompetence1.setText("Bottes Secrètes");
					labelCompetence2.setText("Hypnotisme");
					if(file.getFilename().equals(""))imgTroll.setUrl("img/troll1.jpg");
					break;
					
		case 1 :	labelCompetence1.setText("Accélération Métabolique");
					labelCompetence2.setText("Vampirisme");
					if(file.getFilename().equals(""))imgTroll.setUrl("img/troll2.jpg");
					break;
					
		case 2 :	labelCompetence1.setText("Régénération Accrue");
					labelCompetence2.setText("Rafale Psychique");
					if(file.getFilename().equals(""))imgTroll.setUrl("img/troll3.jpg");
					break;
					
		case 3 :	labelCompetence1.setText("Camouflage");
					labelCompetence2.setText("Projectile Magique");
					if(file.getFilename().equals(""))imgTroll.setUrl("img/troll4.jpg");
					break;
					
		case 4 :	labelCompetence1.setText("Balayage");
					labelCompetence2.setText("Siphon des âmes");
					if(file.getFilename().equals(""))imgTroll.setUrl("img/troll5.jpg");
					break;
		}
	}

	@UiHandler("buttonSave")
	void onButtonSaveClick(ClickEvent event) {
		// Permet de tester si l'utilisateur a entré toutes les valeurs
		if(nbComp == compMax && nbCompSpe == compSpeMax){
			
			//on rend invisible l'interface
			this.removeFromParent();
			Trollcustom.get().getMainPanel().setVisible(true);
			
			// On récupère tous les champs saisis
			int attaque = Integer.parseInt(textBoxAttaque.getText());
			int degats = Integer.parseInt(textBoxDegats.getText());
			int esquive = Integer.parseInt(textBoxEsq.getText());
			int regeneration = Integer.parseInt(textBoxReg.getText());
			int vie = Integer.parseInt(textBoxVie.getText());
			int competence1 = Integer.parseInt(textBoxCompetence1.getText());
			int competence2 = Integer.parseInt(textBoxCompetence2.getText());
			String nom = textBoxNom.getText();
			String race = listBoxRace.getItemText(listBoxRace.getSelectedIndex());
			
			String url = imgTroll.getUrl();
			
			// Si on est en mode édition on envoi au serveur le troll modifié
			// On utilise le constructeur avec un id pour qu'il garde son Id
			if(mode.equals("edit")){
				Trollcustom.get().getGreetingService().greetServer("",new Troll(attaque,degats,esquive,regeneration,vie,competence1,competence2,nom,race,url,id), new AsyncCallback<String>() {
					
					@Override
					public void onSuccess(String result) {
						// TODO Auto-generated method stub
						
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
			}else{ // Si on est en mode normal on envoi au serveur le nouveau troll
					// On utilise le constructeur sans Id car c'est le serveru qui va lui en attribuer un
				Trollcustom.get().getGreetingService().greetServer(new Troll(attaque,degats,esquive,regeneration,vie,competence1,competence2,nom,race,url), new AsyncCallback<String>() {
				
				@Override
				public void onSuccess(String result) {
					// TODO Auto-generated method stub
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
		} else {// Construction de la boite de dialogue indiquant a l'utilisateur qu'il manque des points a attribuer
			final DialogBox dial = new DialogBox();
			VerticalPanel vertPanel = new VerticalPanel();
			Label txt = new Label("Il vous reste encore des points de compétences à répartir");
			Button close = new Button("close");
			close.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					dial.hide();
				}
			});
			vertPanel.add(txt);
			vertPanel.add(close);
			dial.add(vertPanel);
			dial.setAnimationEnabled(true);
			dial.center();
			dial.show();
		}
		
		
		
		
	}
	
	

	@UiHandler("buttonRetour")
	void onButtonRetourClick(ClickEvent event) {
		this.removeFromParent();
		Trollcustom.get().getMainPanel().setVisible(true);
	}
	@UiHandler("file")
	void onFileAttachOrDetach(AttachEvent event) {
		
	}
	@UiHandler("formPanel")
	void onFormPanelSubmit(SubmitEvent event) {
		// This event is fired just before the form is submitted. We can take
        // this opportunity to perform validation.
		Window.alert("Envoi de l'image en cours");

		
	}
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {
		formPanel.submit();
		
	}
	@UiHandler("formPanel")
	void onFormPanelSubmitComplete(SubmitCompleteEvent event) {
		Trollcustom.get().getGreetingService().greetServer(new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				
				imgTroll.setUrl(dossierUpload + result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				DialogBox dial = new DialogBox();
				Label error = new Label("Erreur : communication serveur pour récupéré l'URL dans le XML");
				dial.add(error);
				dial.setAnimationEnabled(true);
				dial.center();
				dial.show();
			}
		});
		
		Window.alert("L'image est téléchargé");
	}
	@UiHandler("buttonClear")
	void onButtonClearClick(ClickEvent event) {
		
		// On remet a zero les paramêtres
		nbComp=compMax;
		nbCompSpe=compSpeMax;
		
		textBoxAttaque.setText("0");
		textBoxDegats.setText("0");
		textBoxEsq.setText("0");
		textBoxReg.setText("0");
		textBoxVie.setText("0");
		textBoxCompetence1.setText("0");
		textBoxCompetence2.setText("0");
		textBoxNom.setText("");
		listBoxRace.setSelectedIndex(0);
		file.getElement().setPropertyString("value", "");
		labelRest.setText("Restant : " + compMax);
		labelRestSpe.setText("Restant : " + compSpeMax);
		
		imgTroll.setUrl("img/troll1.jpg");
		
	}
	
	private void MaJlistRace(){
		listBoxRace.addItem(race1);
		listBoxRace.addItem(race2);
		listBoxRace.addItem(race3);
		listBoxRace.addItem(race4);
		listBoxRace.addItem(race5);
	}
}
