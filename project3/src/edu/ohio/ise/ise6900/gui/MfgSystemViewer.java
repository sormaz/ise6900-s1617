/**
 * 
 */
package edu.ohio.ise.ise6900.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import com.sun.javafx.application.PlatformImpl;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * @author sormaz
 *
 */
public class MfgSystemViewer extends AnchorPane {

	/**
	 * 
	 */
	public MfgSystemViewer() {
		URL fxmlURL = this.getClass().getResource("mfg-system-viewer.fxml");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlURL);
		loader.setRoot(this);
		loader.setController(this);
		try {
			PlatformImpl.startup(() -> {});
			loader.load();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
//			System.out.println(Arrays.toString(ex.getStackTrace()));
		}	
	}

	/**
	 * @param children
	 */
	public MfgSystemViewer(Node... children) {
		super(children);
		// TODO Auto-generated constructor stub
	}

}
