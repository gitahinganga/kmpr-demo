/*
 * Created by JFormDesigner on Fri Feb 25 10:18:19 EAT 2022
 */

package ke.go.moh.oec.demo.ui;

import java.util.*;
import java.util.logging.Level;
import javax.swing.*;
import javax.swing.border.*;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.PersonIdentifier;
import ke.go.moh.oec.PersonRequest;
import ke.go.moh.oec.demo.controller.RequestSender;
import ke.go.moh.oec.demo.controller.ResponseReceiver;
import ke.go.moh.oec.demo.controller.SearchResultsHolder;
import ke.go.moh.oec.lib.Mediator;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class DeterministicForm extends JPanel implements ResponseReceiver {

	private SearchResultsHolder searchResultsHolder;

	public DeterministicForm() {
		initComponents();
	}

	private void search() {

		PersonIdentifier.Type type = PersonIdentifier.Type.TELEPHONE_NO;
		String selected = (String) comboBoxIdentifierType.getSelectedItem();
		if (selected.equalsIgnoreCase("Telephone No")) {
			type = PersonIdentifier.Type.TELEPHONE_NO;
		} else if (selected.equalsIgnoreCase("National ID")) {
			type = PersonIdentifier.Type.NATIONAL_ID;
		} else if (selected.equalsIgnoreCase("NHIF No")) {
			type = PersonIdentifier.Type.NHIF_NO;
		} else if (selected.equalsIgnoreCase("Huduma No")) {
			type = PersonIdentifier.Type.HUDUMA_NO;
		} else if (selected.equalsIgnoreCase("Passport No")) {
			type = PersonIdentifier.Type.PASSPORT_NO;
		} else if (selected.equalsIgnoreCase("Birth Certificate No")) {
			type = PersonIdentifier.Type.BIRTH_CERTIFICATE_NO;
		} else if (selected.equalsIgnoreCase("Birth Notification No")) {
			type = PersonIdentifier.Type.BIRTH_NOTIFICATION_NO;
		} else if (selected.equalsIgnoreCase("Alien ID")) {
			type = PersonIdentifier.Type.ALIEN_ID;
		} else if (selected.equalsIgnoreCase("NEMIS ID")) {
			type = PersonIdentifier.Type.NEMIS_ID;
		}

		Person person = new Person();
		List<PersonIdentifier> personIdentifiers = new ArrayList<>();
		PersonIdentifier personIdentifier = new PersonIdentifier();
		personIdentifier.setIdentifierType(type);
		personIdentifier.setIdentifier(textFieldIdentifier.getText());
		personIdentifiers.add(personIdentifier);
		person.setPersonIdentifierList(personIdentifiers);

		PersonRequest personRequest = new PersonRequest();
		personRequest.setPerson(person);
		personRequest.setRequestReference(Mediator.generateMessageId());

		try {
			RequestSender.sendRequest(
					RequestSender.RequestType.FIND_PERSON,
					personRequest,
					this,
					RequestSender.PersonIndex.LOCAL,
					RequestSender.PersonIndex.MASTER
			);
		} catch (InterruptedException ex) {
			Mediator.getLogger(DeterministicForm.class.getName()).log(Level.SEVERE, "Request thread interrupted.");
		}
	}

	@Override
	public void onResponseReceived(boolean successful, int requestTypeId, List<Person> personList) {
		if (searchResultsHolder == null) {
			searchResultsHolder = new SearchResultsHolder();
		}
		if (requestTypeId == 2) {
			searchResultsHolder.setLpiPersonList(personList);
		} else if (requestTypeId == 1) {
			searchResultsHolder.setMpiPersonList(personList);
		}
		JSplitPane splitPane = (JSplitPane) getParent();
		if (splitPane != null) {
			splitPane.setRightComponent(new ResultsForm(searchResultsHolder));
		} else {
			System.out.println("Split pane is null");
		}
	}

	private void clearFields() {
		comboBoxIdentifierType.setSelectedIndex(0);
		textFieldIdentifier.setText("");
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		label1 = new JLabel();
		comboBoxIdentifierType = new JComboBox<>();
		label2 = new JLabel();
		textFieldIdentifier = new JTextField();
		buttonSearch = new JButton();
		buttonClear = new JButton();

		//======== this ========
		setBorder(new TitledBorder(null, bundle.getString("DeterministicForm.this.border"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
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
						"[]"));

		//---- label1 ----
		label1.setText(bundle.getString("DeterministicForm.label1.text"));
		add(label1, "cell 0 0,align left center,grow 0 0");

		//---- comboBoxIdentifierType ----
		comboBoxIdentifierType.setModel(new DefaultComboBoxModel<>(new String[]{
				"Telephone No",
				"National ID",
				"NHIF No",
				"Huduma No",
				"Passport No",
				"Birth Certificate No",
				"Birth Notification No",
				"Alien ID",
				"NEMIS ID"
		}));
		add(comboBoxIdentifierType, "cell 1 0 3 1,aligny center,growy 0");

		//---- label2 ----
		label2.setText(bundle.getString("DeterministicForm.label2.text"));
		add(label2, "cell 0 1,align left center,grow 0 0");
		add(textFieldIdentifier, "cell 1 1 3 1,aligny center,growy 0");

		//---- buttonSearch ----
		buttonSearch.setText(bundle.getString("DeterministicForm.buttonSearch.text"));
		buttonSearch.addActionListener(e -> search());
		add(buttonSearch, "cell 1 2");

		//---- buttonClear ----
		buttonClear.setText(bundle.getString("DeterministicForm.buttonClear.text"));
		buttonClear.addActionListener(e -> {
			clearFields();
			clearFields();
		});
		add(buttonClear, "cell 2 2");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JComboBox<String> comboBoxIdentifierType;
	private JLabel label2;
	private JTextField textFieldIdentifier;
	private JButton buttonSearch;
	private JButton buttonClear;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
