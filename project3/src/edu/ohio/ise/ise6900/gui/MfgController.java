package edu.ohio.ise.ise6900.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import edu.ohio.ise.ise6900.app.MfgSystemFXApp2;
import edu.ohio.ise.ise6900.draw.DrawPanel;
import edu.ohio.ise.ise6900.model.AlreadyMemberException;
import edu.ohio.ise.ise6900.model.Job;
import edu.ohio.ise.ise6900.model.Machine;
import edu.ohio.ise.ise6900.model.MfgFeature;
import edu.ohio.ise.ise6900.model.MfgObject;
import edu.ohio.ise.ise6900.model.MfgSystem;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
		fileChooser.setTitle("Open STL File");

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
				ms.read(selectedFile);
				canvas.repaint(ms);
//				machineList.setItems(new ObservableListWrapper<Machine>(ms.getMachines()));
		    	machListProperty.set(FXCollections.observableArrayList(ms.getMachines()));
//				jobTreeView = new TreeView<MfgObject>(makeMfgSystemTree(ms));
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
//    	actionNotImplemented("add machine");
    	String name = "dnsMachine";
    	try {
			ms.addMachine(new Machine(name));
			machListProperty.set(FXCollections.observableArrayList(ms.getMachines()));
		} catch (AlreadyMemberException e) {
			actinoCanNotComplete(name);
		}
    }

    @FXML
    void handleAddFeatureAction(ActionEvent event) {
    	actionNotImplemented("add feature");
    }

    @FXML
    void handleAddStateAction(ActionEvent event) {
    	actionNotImplemented("add state");
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
    	actionNotImplemented("zoom iin");
    }
    
    @FXML
    void handleZoomOutAction(ActionEvent event) {
    	actionNotImplemented("zoom out");
    }
    
    void actionNotImplemented (String action) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("I have a great message for you!" );
    	alert.setHeaderText(null);
    	alert.setContentText("Action for " + action + " is not implemented yet!");
    	alert.showAndWait();
    }
    void actinoCanNotComplete (String name) {
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
}


