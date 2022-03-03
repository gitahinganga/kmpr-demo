/*
 * Created by JFormDesigner on Fri Feb 25 09:56:18 EAT 2022
 */

package ke.go.moh.oec.demo.ui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class MainForm extends JPanel {

	public static void main(String[] args) {
		FlatLightLaf.setup(); // See https://www.formdev.com/flatlaf/
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		JFrame frame = new JFrame();
		MainForm mainForm = new MainForm();
		frame.setContentPane(mainForm);
		frame.setTitle(bundle.getString("MainForm.title.text"));
		frame.pack();
		mainForm.searchDeterministic();
		frame.setVisible(true);
	}

	public MainForm() {
		initComponents();
	}

	private void searchBiometric() {
		splitPane.setRightComponent(new DeterministicForm());
	}

	private void searchDeterministic() {
		splitPane.setRightComponent(new DeterministicForm());
	}

	private void searchProbabilistic() {
		splitPane.setRightComponent(new ProbabilisticForm());
	}

	private void createPerson() {
		splitPane.setRightComponent(new CreatePersonForm());
	}

	private void probabilistic() {
		// TODO add your code here
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		splitPane = new JSplitPane();
		panel1 = new JPanel();
		buttonBiometric = new JButton();
		buttonDeterministic = new JButton();
		buttonProbabilistic = new JButton();
		buttonNewPerson = new JButton();

		//======== this ========
		setLayout(new BorderLayout());

		//======== splitPane ========
		{
			splitPane.setDividerLocation(225);
			splitPane.setDividerSize(2);

			//======== panel1 ========
			{
				panel1.setBorder(new TitledBorder(null, bundle.getString("MainForm.panel1.border"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
				panel1.setLayout(new MigLayout(
					"fillx,hidemode 3",
					// columns
					"[fill]",
					// rows
					"[]" +
					"[]" +
					"[]" +
					"[]"));

				//---- buttonBiometric ----
				buttonBiometric.setText(bundle.getString("MainForm.buttonBiometric.text"));
				buttonBiometric.addActionListener(e -> searchBiometric());
				panel1.add(buttonBiometric, "cell 0 0");

				//---- buttonDeterministic ----
				buttonDeterministic.setText(bundle.getString("MainForm.buttonDeterministic.text"));
				buttonDeterministic.addActionListener(e -> searchDeterministic());
				panel1.add(buttonDeterministic, "cell 0 1");

				//---- buttonProbabilistic ----
				buttonProbabilistic.setText(bundle.getString("MainForm.buttonProbabilistic.text"));
				buttonProbabilistic.addActionListener(e -> {
			probabilistic();
			searchProbabilistic();
		});
				panel1.add(buttonProbabilistic, "cell 0 2");

				//---- buttonNewPerson ----
				buttonNewPerson.setText(bundle.getString("MainForm.buttonNewPerson.text"));
				buttonNewPerson.addActionListener(e -> {
			createPerson();
			createPerson();
		});
				panel1.add(buttonNewPerson, "cell 0 3");
			}
			splitPane.setLeftComponent(panel1);
		}
		add(splitPane, BorderLayout.CENTER);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JSplitPane splitPane;
	private JPanel panel1;
	private JButton buttonBiometric;
	private JButton buttonDeterministic;
	private JButton buttonProbabilistic;
	private JButton buttonNewPerson;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
