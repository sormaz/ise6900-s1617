package edu.ohio.ise.ise6900.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import edu.ohio.ise.ise6900.app.MfgSystemFXApp2;
import edu.ohio.ise.ise6900.draw.DrawPanel;
import edu.ohio.ise.ise6900.model.AbstractState;
import edu.ohio.ise.ise6900.model.AlreadyMemberException;
import edu.ohio.ise.ise6900.model.Job;
import edu.ohio.ise.ise6900.model.Machine;
import edu.ohio.ise.ise6900.model.MachineState;
import edu.ohio.ise.ise6900.model.MfgFeature;
import edu.ohio.ise.ise6900.model.MfgObject;
import edu.ohio.ise.ise6900.model.MfgSystem;
import edu.ohio.ise.ise6900.model.StateOption;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.collections.ObservableListWrapper;




public class MfgController {
	MfgSystem ms;
	DrawPanel canvas;


	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="saveFileBtn"
	private Button saveFileBtn; // Value injected by FXMLLoader

	@FXML // fx:id="saveMI"
	private MenuItem saveMI; // Value injected by FXMLLoader

	@FXML // fx:id="closeMI"
	private MenuItem closeMI; // Value injected by FXMLLoader

	@FXML // fx:id="addFeatureBtn"
	private Button addFeatureBtn; // Value injected by FXMLLoader

	@FXML // fx:id="addJobBtn"
	private Button addJobBtn; // Value injected by FXMLLoader

	@FXML // fx:id="addStateBtn"
	private Button addStateBtn; // Value injected by FXMLLoader

	@FXML // fx:id="openFileMI"
	private MenuItem openFileMI; // Value injected by FXMLLoader

	@FXML // fx:id="openFileBtn"
	private Button openFileBtn; // Value injected by FXMLLoader

	@FXML // fx:id="addMachBtn"
	private Button addMachBtn; // Value injected by FXMLLoader

	@FXML // fx:id="addActBtn"
	private Button addActBtn; // Value injected by FXMLLoader

	@FXML // fx:id="machineList"
	private ListView<Machine> machineList; // Value injected by FXMLLoader

	@FXML
	private TreeView<MfgObject> jobTreeView;

	@FXML // fx:id="deleteBtn"
	private Button deleteBtn; // Value injected by FXMLLoader

	protected ListProperty<Machine> machListProperty = new SimpleListProperty<Machine>();

	@FXML
	void handleOpenFileAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open MFG File");

		File existDirectory = 
				new File(".");

		if(existDirectory.exists()) {
			fileChooser.setInitialDirectory(existDirectory);
		} else {
			fileChooser.setInitialDirectory(new File("."));
		}

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("MFG File", "*.mfg"),
				new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {
			System.out.println("--->selected file is " +  selectedFile);
			try {
				ms.clear();
				ms.read(selectedFile);
				canvas.updateUI(ms);
				Window w = canvas.getScene().getWindow();
				Stage s = (Stage) w;	
				//				s.setTitle("changed title");
				machListProperty.set(FXCollections.observableArrayList(ms.getMachines()));

				jobTreeView.setRoot(makeMfgSystemTree(ms));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	void handleSaveFileAction(ActionEvent event) {
		actionNotImplemented("save file");
	}

	@FXML
	void handleCloseAction(ActionEvent event) {
		actionNotImplemented("close");
	}

	@FXML
	void handleAddJobAction(ActionEvent event) {
		actionNotImplemented("add job");

	}

	@FXML
	void handleAddMachAction(ActionEvent event) {

		String name = "dnsMachine";
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText("Look, a Text Input Dialog");
		dialog.setContentText("Please enter your name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			System.out.println("Your name: " + result.get());
			try {
				ms.addMachine(new Machine(result.get()));
				machListProperty.set(FXCollections.observableArrayList(ms.getMachines()));
			} catch (AlreadyMemberException e) {
				actionCanNotComplete(result.get());
			}
		}
	}

	@FXML
	void handleAddFeatureAction(ActionEvent event) {
		//    	actionNotImplemented("add feature");
		String name = "Add Feature";
		TreeItem<MfgObject> ti = jobTreeView.selectionModelProperty().get().getSelectedItem();

		if (ti != null ) {
			MfgObject o = ti.getValue();
			if ( o instanceof Job) {
				TextInputDialog dialog = new TextInputDialog("f1");
				dialog.setTitle(name);
				dialog.setHeaderText("Look, a Text Input Dialog");
				dialog.setContentText("Please enter your name:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
					System.out.println("Your name: " + result.get());
					try {
						Job j = (Job) o;
						MfgFeature f = new MfgFeature(result.get());
						j.addFeature(f);
						//						machListProperty.set(FXCollections.observableArrayList(ms.getMachines()));
						//						jobTreeView.setRoot(makeMfgSystemTree(ms));
						//						ObservableList<TreeItem<MfgObject>> ch = jobTreeView.getRoot().getChildren();
						TreeItem<MfgObject> n = findTreeItem(jobTreeView.getRoot(), j);
						//						jobTreeView.getRoot().getChildren().remove(n);
						n.getChildren().add(makeJobTree(f));
						//						jobTreeView.getRoot().getChildren().
						jobTreeView.refresh();
						//						jobTreeView.getSelectionModel().select(jobTreeView.getRoot());
					} catch (AlreadyMemberException e) {
						actionCanNotComplete(result.get());
					}
				}
			}
			else {
				actionCanNotComplete("Feature can not be added if job is not selected");
			}
		}
		else {
			actionCanNotComplete("Feature can not be added if job is not selected");
		}
	}



	@FXML
	void handleAddStateAction(ActionEvent event) {

		Optional<Machine> machineResult = chooseMachine();
		if (!machineResult.isPresent()){
			return;
		}
		Optional<StateOption> stateResult = chooseStateOption();  
		if (!stateResult.isPresent()) {
			return;
		}
		try {
			MachineState state = new MachineState (machineResult.get(), stateResult.get().name(), 0, 300);
			machineResult.get().addState(state);
		} catch (IllegalArgumentException e) {
			actionCanNotComplete(e.getMessage());
		}

	}

	@FXML
	void handleAddActAction(ActionEvent event) {
		actionNotImplemented("add activity");
	}

	@FXML
	void handleDeleteAction(ActionEvent event) {
		actionNotImplemented("delete");
	}

	@FXML
	void handleZoomInAction(ActionEvent event) {
		actionNotImplemented("zoom in");
	}

	@FXML
	void handleZoomOutAction(ActionEvent event) {
		MfgObject.SCALE *= 0.8;
		MfgObject.HEIGHT *= 0.8;
		canvas.getChildren().clear();
		canvas.makeShapes();
	}

	void actionNotImplemented (String action) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("I have a great message for you!" );
		alert.setHeaderText(null);
		alert.setContentText("Action for " + action + " is not implemented yet!");
		alert.showAndWait();
	}
	void actionCanNotComplete (String name) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Can not add object!" );
		alert.setHeaderText(null);
		alert.setContentText("Object with " + name + " already exists!");
		alert.showAndWait();
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		System.out.println("in initialize in controller");
		ms = new MfgSystem("empty");
		canvas = MfgSystemFXApp2.canvas;
		machineList.itemsProperty().bind(machListProperty);
		machineList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Machine>() {
			@Override
			public void changed(ObservableValue<? extends Machine> observable, Machine oldValue, Machine newValue) {
				canvas.updateUI(newValue);
			}
		});

		jobTreeView.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<TreeItem<MfgObject>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<MfgObject>> observable, TreeItem<MfgObject> oldValue,
					TreeItem<MfgObject> newValue) {
				TreeItem<MfgObject> selectedItem = newValue;
				canvas.updateUI(selectedItem.getValue());
			}
		});

		assert saveFileBtn != null : "fx:id=\"saveFileBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert saveMI != null : "fx:id=\"saveMI\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert closeMI != null : "fx:id=\"closeMI\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert addFeatureBtn != null : "fx:id=\"addFeatureBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert addJobBtn != null : "fx:id=\"addJobBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert addStateBtn != null : "fx:id=\"addStateBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert openFileMI != null : "fx:id=\"openFileMI\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert openFileBtn != null : "fx:id=\"openFileBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert addMachBtn != null : "fx:id=\"addMachBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert addActBtn != null : "fx:id=\"addActBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert machineList != null : "fx:id=\"machineList\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";
		assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'mfg-system-fxapp.fxml'.";

	}


	public TreeItem<MfgObject> makeMfgSystemTree(MfgSystem ms) {
		TreeItem<MfgObject> item = new TreeItem<MfgObject>(ms);
		for (Job j : ms.getJobs()) {
			item.getChildren().add(makeJobTree(j));
		}
		return item;

	}

	public TreeItem<MfgObject> makeJobTree (Job j) {
		TreeItem<MfgObject> item = new TreeItem<MfgObject>(j);
		for (MfgFeature f : j.getFeatures()) {
			item.getChildren().add(new TreeItem<MfgObject>(f));
		}
		return item;	
	}

	private TreeItem<MfgObject> makeJobTree(MfgFeature f) {

		return new TreeItem<MfgObject>(f);
	}

	public TreeItem<MfgObject> findTreeItem (TreeItem<MfgObject> item, MfgObject o) {


		if (item.getValue() == o) return item;
		if (item.isLeaf()) return null;
		for (TreeItem<MfgObject> i : item.getChildren()) {
			TreeItem<MfgObject> r = findTreeItem(i, o);
			if (r != null) return r;
		}
		return null;

	}
	private Optional<Machine> chooseMachine() {
		//		actionNotImplemented("add state");
		List<Machine> choices = new ArrayList<>();
		choices.addAll(ms.getMachines());
		ChoiceDialog<Machine> dialog = new ChoiceDialog<Machine>(null, choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		return dialog.showAndWait();
	}
	
	private Optional<Job> chooseJob() {
		//		actionNotImplemented("add state");
		List<Job> choices = new ArrayList<>();
		choices.addAll(ms.getJobs());
		ChoiceDialog<Job> dialog = new ChoiceDialog<Job>(null, choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		return dialog.showAndWait();
	}
	
	private Optional<MfgFeature> chooseFeature(Job j) {
		//		actionNotImplemented("add state");
		List<MfgFeature> choices = new ArrayList<>();
		choices.addAll(j.getFeatures());
		ChoiceDialog<MfgFeature> dialog = new ChoiceDialog<MfgFeature>(null, choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		return dialog.showAndWait();
	}
	
	private Optional<StateOption> chooseStateOption(){
		List<StateOption> stateChoices = new ArrayList<StateOption>();
		stateChoices.addAll(AbstractState.getStateOptions());
		
		ChoiceDialog<StateOption> stateDialog = new ChoiceDialog<StateOption>(null, stateChoices);
		stateDialog.setTitle("Choice Dialog");
		stateDialog.setHeaderText("Look, a Choice Dialog");
		stateDialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		return stateDialog.showAndWait();  
	}
}


