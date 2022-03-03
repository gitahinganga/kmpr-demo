/*
 * Created by JFormDesigner on Fri Feb 25 15:05:58 EAT 2022
 */

package ke.go.moh.oec.demo.ui;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.PersonIdentifier;
import ke.go.moh.oec.PersonRequest;
import ke.go.moh.oec.demo.controller.RequestSender;
import ke.go.moh.oec.demo.controller.RequestThread;
import ke.go.moh.oec.demo.util.Utils;
import ke.go.moh.oec.lib.Mediator;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class CreatePersonForm extends JPanel {
	public CreatePersonForm() {
		initComponents();
	}

	private void create() {
		Person person = new Person();
		person.setPersonIdentifierList(getPersonIdentifiers());
		person.setFirstName(textFieldFirstName.getText());
		person.setMiddleName(textFieldMiddleName.getText());
		person.setLastName(textFieldSurname.getText());
		person.setSex(Utils.parseSex((String) comboBoxSex.getSelectedItem()));
		person.setBirthdate(Utils.parseDate(textFieldDoB.getText()));
		person.setMothersMiddleName(textFieldMothersName.getText());
		person.setVillageName(textFieldPlaceOfBirth.getText());

		PersonRequest personRequest = new PersonRequest();
		personRequest.setPerson(person);
		personRequest.setRequestReference(Mediator.generateMessageId());

		try {
			RequestSender.sendRequest(
					RequestSender.RequestType.CREATE_PERSON,
					personRequest,
					RequestSender.PersonIndex.LOCAL,
					RequestSender.PersonIndex.MASTER
			);
		} catch (InterruptedException ex) {
			Logger.getLogger(RequestThread.class.getName()).log(Level.SEVERE, "RequestThread failed to join.", ex);
		}
	}

	private List<PersonIdentifier> getPersonIdentifiers() {
		List<PersonIdentifier> personIdentifiers = new ArrayList<>();
		if (!textFieldTelephoneNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.TELEPHONE_NO, textFieldTelephoneNo.getText()));
		}
		if (!textFieldNationalID.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.NATIONAL_ID, textFieldNationalID.getText()));
		}
		if (!textFieldNhifNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.NHIF_NO, textFieldNhifNo.getText()));
		}
		if (!textFieldHudumaNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.HUDUMA_NO, textFieldHudumaNo.getText()));
		}
		if (!textFieldPassportNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.PASSPORT_NO, textFieldPassportNo.getText()));
		}
		if (!textFieldBirthCertificateNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.BIRTH_CERTIFICATE_NO, textFieldBirthCertificateNo.getText()));
		}
		if (!textFieldBirthNotificationNo.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.BIRTH_NOTIFICATION_NO, textFieldBirthNotificationNo.getText()));
		}
		if (!textFieldAlienID.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.ALIEN_ID, textFieldAlienID.getText()));
		}
		if (!textFieldNemisID.getText().isEmpty()) {
			personIdentifiers.add(new PersonIdentifier(PersonIdentifier.Type.NEMIS_ID, textFieldNemisID.getText()));
		}
		return personIdentifiers;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		label1 = new JLabel();
		textFieldTelephoneNo = new JTextField();
		label2 = new JLabel();
		textFieldNationalID = new JTextField();
		label16 = new JLabel();
		textFieldNhifNo = new JTextField();
		label3 = new JLabel();
		textFieldHudumaNo = new JTextField();
		label4 = new JLabel();
		textFieldPassportNo = new JTextField();
		label5 = new JLabel();
		textFieldBirthCertificateNo = new JTextField();
		label6 = new JLabel();
		textFieldBirthNotificationNo = new JTextField();
		label7 = new JLabel();
		textFieldAlienID = new JTextField();
		label8 = new JLabel();
		textFieldNemisID = new JTextField();
		label9 = new JLabel();
		textFieldFirstName = new JTextField();
		label10 = new JLabel();
		textFieldMiddleName = new JTextField();
		label11 = new JLabel();
		textFieldSurname = new JTextField();
		label12 = new JLabel();
		comboBoxSex = new JComboBox<>();
		label13 = new JLabel();
		textFieldDoB = new JTextField();
		label14 = new JLabel();
		textFieldMothersName = new JTextField();
		label15 = new JLabel();
		textFieldPlaceOfBirth = new JTextField();
		buttonCreate = new JButton();
		buttonClear = new JButton();

		//======== this ========
		setBorder(new TitledBorder(null, bundle.getString("NewPersonForm.this.border"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
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
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label1 ----
		label1.setText(bundle.getString("NewPersonForm.label1.text"));
		add(label1, "cell 0 0,alignx left,growx 0");
		add(textFieldTelephoneNo, "cell 1 0 3 1");

		//---- label2 ----
		label2.setText(bundle.getString("NewPersonForm.label2.text"));
		add(label2, "cell 0 1,alignx left,growx 0");
		add(textFieldNationalID, "cell 1 1 3 1");

		//---- label16 ----
		label16.setText(bundle.getString("NewPersonForm.label16.text"));
		add(label16, "cell 0 2");
		add(textFieldNhifNo, "cell 1 2 3 1");

		//---- label3 ----
		label3.setText(bundle.getString("NewPersonForm.label3.text"));
		add(label3, "cell 0 3,alignx left,growx 0");
		add(textFieldHudumaNo, "cell 1 3 3 1");

		//---- label4 ----
		label4.setText(bundle.getString("NewPersonForm.label4.text"));
		add(label4, "cell 0 4,alignx left,growx 0");
		add(textFieldPassportNo, "cell 1 4 3 1");

		//---- label5 ----
		label5.setText(bundle.getString("NewPersonForm.label5.text"));
		add(label5, "cell 0 5,alignx left,growx 0");
		add(textFieldBirthCertificateNo, "cell 1 5 3 1");

		//---- label6 ----
		label6.setText(bundle.getString("NewPersonForm.label6.text"));
		add(label6, "cell 0 6,alignx left,growx 0");
		add(textFieldBirthNotificationNo, "cell 1 6 3 1");

		//---- label7 ----
		label7.setText(bundle.getString("NewPersonForm.label7.text"));
		add(label7, "cell 0 7,alignx left,growx 0");
		add(textFieldAlienID, "cell 1 7 3 1");

		//---- label8 ----
		label8.setText(bundle.getString("NewPersonForm.label8.text"));
		add(label8, "cell 0 8,alignx left,growx 0");
		add(textFieldNemisID, "cell 1 8 3 1");

		//---- label9 ----
		label9.setText(bundle.getString("NewPersonForm.label9.text"));
		add(label9, "cell 0 9,alignx left,growx 0");
		add(textFieldFirstName, "cell 1 9 3 1");

		//---- label10 ----
		label10.setText(bundle.getString("NewPersonForm.label10.text"));
		add(label10, "cell 0 10");
		add(textFieldMiddleName, "cell 1 10 3 1");

		//---- label11 ----
		label11.setText(bundle.getString("NewPersonForm.label11.text"));
		add(label11, "cell 0 11");
		add(textFieldSurname, "cell 1 11 3 1");

		//---- label12 ----
		label12.setText(bundle.getString("NewPersonForm.label12.text"));
		add(label12, "cell 0 12");

		//---- comboBoxSex ----
		comboBoxSex.setModel(new DefaultComboBoxModel<>(new String[] {
			"Male",
			"Female"
		}));
		add(comboBoxSex, "cell 1 12 3 1");

		//---- label13 ----
		label13.setText(bundle.getString("NewPersonForm.label13.text"));
		add(label13, "cell 0 13");
		add(textFieldDoB, "cell 1 13 3 1");

		//---- label14 ----
		label14.setText(bundle.getString("NewPersonForm.label14.text"));
		add(label14, "cell 0 14");
		add(textFieldMothersName, "cell 1 14 3 1");

		//---- label15 ----
		label15.setText(bundle.getString("NewPersonForm.label15.text"));
		add(label15, "cell 0 15");
		add(textFieldPlaceOfBirth, "cell 1 15 3 1");

		//---- buttonCreate ----
		buttonCreate.setText(bundle.getString("NewPersonForm.buttonCreate.text"));
		buttonCreate.addActionListener(e -> {
			create();
		});
		add(buttonCreate, "cell 1 16");

		//---- buttonClear ----
		buttonClear.setText(bundle.getString("NewPersonForm.buttonClear.text"));
		add(buttonClear, "cell 2 16");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JLabel label1;
	private JTextField textFieldTelephoneNo;
	private JLabel label2;
	private JTextField textFieldNationalID;
	private JLabel label16;
	private JTextField textFieldNhifNo;
	private JLabel label3;
	private JTextField textFieldHudumaNo;
	private JLabel label4;
	private JTextField textFieldPassportNo;
	private JLabel label5;
	private JTextField textFieldBirthCertificateNo;
	private JLabel label6;
	private JTextField textFieldBirthNotificationNo;
	private JLabel label7;
	private JTextField textFieldAlienID;
	private JLabel label8;
	private JTextField textFieldNemisID;
	private JLabel label9;
	private JTextField textFieldFirstName;
	private JLabel label10;
	private JTextField textFieldMiddleName;
	private JLabel label11;
	private JTextField textFieldSurname;
	private JLabel label12;
	private JComboBox<String> comboBoxSex;
	private JLabel label13;
	private JTextField textFieldDoB;
	private JLabel label14;
	private JTextField textFieldMothersName;
	private JLabel label15;
	private JTextField textFieldPlaceOfBirth;
	private JButton buttonCreate;
	private JButton buttonClear;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
