/*
 * Created by JFormDesigner on Fri Feb 25 10:20:29 EAT 2022
 */

package ke.go.moh.oec.demo.ui;

import java.util.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.border.*;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.PersonRequest;
import ke.go.moh.oec.demo.controller.RequestSender;
import ke.go.moh.oec.demo.controller.ResponseReceiver;
import ke.go.moh.oec.demo.controller.SearchResultsHolder;
import ke.go.moh.oec.demo.util.Utils;
import ke.go.moh.oec.lib.Mediator;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class ProbabilisticForm extends JPanel implements ResponseReceiver {

	private SearchResultsHolder searchResultsHolder;

	private int counter;

	public ProbabilisticForm() {
		initComponents();
	}

	private void search() {
		Person person = new Person();
		person.setFirstName(textFieldFirstName.getText());
		person.setMiddleName(textFieldMiddleName.getText());
		person.setLastName(textFieldLastName.getText());
		person.setSex(Utils.parseSex((String) comboBoxSex.getSelectedItem()));
		person.setBirthdate(Utils.parseDate(textFieldDoB.getText()));
		person.setMothersMiddleName(textFieldMotherName.getText());

		PersonRequest personRequest = new PersonRequest();
		personRequest.setPerson(person);
		personRequest.setRequestReference(Mediator.generateMessageId());

		try {
			counter = 0;
			RequestSender.sendRequest(
					RequestSender.RequestType.FIND_PERSON,
					personRequest,
					this,
					RequestSender.PersonIndex.LOCAL,
					RequestSender.PersonIndex.MASTER
			);
		} catch (InterruptedException ex) {
			Mediator.getLogger(ProbabilisticForm.class.getName()).log(Level.SEVERE, "Request thread interrupted.");
		}
	}

	@Override
	public void onResponseReceived(boolean successful, int requestTypeId, List<Person> personList) {
		counter++;
		if (searchResultsHolder == null) {
			searchResultsHolder = new SearchResultsHolder();
		}
		if (requestTypeId == 2) {
			searchResultsHolder.setLpiPersonList(personList);
		} else if (requestTypeId == 1) {
			searchResultsHolder.setMpiPersonList(personList);
		}
		if (counter > 1) {
			JSplitPane splitPane = (JSplitPane) getParent();
			if (splitPane != null) {
				splitPane.setRightComponent(new ResultsForm(searchResultsHolder));
			} else {
				System.out.println("Split pane is null");
			}
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		label1 = new JLabel();
		textFieldFirstName = new JTextField();
		label2 = new JLabel();
		textFieldMiddleName = new JTextField();
		label3 = new JLabel();
		textFieldLastName = new JTextField();
		label4 = new JLabel();
		comboBoxSex = new JComboBox<>();
		label5 = new JLabel();
		textFieldDoB = new JTextField();
		label6 = new JLabel();
		textFieldMotherName = new JTextField();
		buttonSearch = new JButton();
		buttonClear = new JButton();

		//======== this ========
		setBorder(new TitledBorder(null, bundle.getString("ProbabilisticForm.this.border"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		setLayout(new MigLayout(
				"fillx,hidemode 3",
				// columns
				"[fill]" +
						"[fill]" +
						"[fill]" +
						"[grow]",
				// rows
				"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

		//---- label1 ----
		label1.setText(bundle.getString("ProbabilisticForm.label1.text"));
		add(label1, "cell 0 0,align left center,grow 0 0");
		add(textFieldFirstName, "cell 1 0 3 1");

		//---- label2 ----
		label2.setText(bundle.getString("ProbabilisticForm.label2.text"));
		add(label2, "cell 0 1,align left center,grow 0 0");
		add(textFieldMiddleName, "cell 1 1 3 1");

		//---- label3 ----
		label3.setText(bundle.getString("ProbabilisticForm.label3.text"));
		add(label3, "cell 0 2,align left center,grow 0 0");
		add(textFieldLastName, "cell 1 2 3 1");

		//---- label4 ----
		label4.setText(bundle.getString("ProbabilisticForm.label4.text"));
		add(label4, "cell 0 3");

		//---- comboBoxSex ----
		comboBoxSex.setModel(new DefaultComboBoxModel<>(new String[]{
				"Male",
				"Female"
		}));
		add(comboBoxSex, "cell 1 3 3 1");

		//---- label5 ----
		label5.setText(bundle.getString("ProbabilisticForm.label5.text"));
		add(label5, "cell 0 4");
		add(textFieldDoB, "cell 1 4 3 1");

		//---- label6 ----
		label6.setText(bundle.getString("ProbabilisticForm.label6.text"));
		add(label6, "cell 0 5");
		add(textFieldMotherName, "cell 1 5 3 1");

		//---- buttonSearch ----
		buttonSearch.setText(bundle.getString("ProbabilisticForm.buttonSearch.text"));
		buttonSearch.addActionListener(e -> search());
		add(buttonSearch, "cell 1 6");

		//---- buttonClear ----
		buttonClear.setText(bundle.getString("ProbabilisticForm.buttonClear.text"));
		add(buttonClear, "cell 2 6");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField textFieldFirstName;
	private JLabel label2;
	private JTextField textFieldMiddleName;
	private JLabel label3;
	private JTextField textFieldLastName;
	private JLabel label4;
	private JComboBox<String> comboBoxSex;
	private JLabel label5;
	private JTextField textFieldDoB;
	private JLabel label6;
	private JTextField textFieldMotherName;
	private JButton buttonSearch;
	private JButton buttonClear;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
