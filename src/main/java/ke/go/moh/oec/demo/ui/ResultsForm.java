/*
 * Created by JFormDesigner on Fri Feb 25 12:26:36 EAT 2022
 */

package ke.go.moh.oec.demo.ui;

import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.PersonIdentifier;
import ke.go.moh.oec.demo.controller.SearchResultsHolder;
import ke.go.moh.oec.demo.util.Utils;
import net.miginfocom.swing.*;

import java.util.List;

/**
 * @author unknown
 */
public class ResultsForm extends JPanel {

	class PersonTableModel extends AbstractTableModel {

		private final List<Person> personList;

		public PersonTableModel(List<Person> personList) {
			this.personList = personList;
		}

		public String getColumnName(int col) {
			switch (col) {
				case 0:
					return "Match Score";
				case 1:
					return "Key ID";
				case 2:
					return "First Name";
				case 3:
					return "Middle Name";
				case 4:
					return "Last Name";
				case 5:
					return "Sex";
				case 6:
					return "DoB";
				case 7:
					return "Birth Place";
				case 8:
					return "Mother";
				default:
					return "Unknown";
			}
		}


		public Class getColumnClass(int c) {
			Object value = getValueAt(0, c);
			if (value != null) {
				return getValueAt(0, c).getClass();
			}
			return String.class;
		}


		@Override
		public int getRowCount() {
			return personList.size();
		}

		@Override
		public int getColumnCount() {
			return 9;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (rowIndex < personList.size()) {
				switch (columnIndex) {
					case 0:
						return personList.get(rowIndex).getMatchScore();
					case 1:
						Person person = personList.get(rowIndex);
						if (person.getPersonIdentifierList() != null) {
							for (PersonIdentifier identifier : person.getPersonIdentifierList()) {
								return getIdentifierTypeName(identifier) + ": " + identifier.getIdentifier();
							}
						}
						return "Local UUID: " + personList.get(rowIndex).getPersonGuid();
					case 2:
						return personList.get(rowIndex).getFirstName();
					case 3:
						return personList.get(rowIndex).getMiddleName();
					case 4:
						return personList.get(rowIndex).getLastName();
					case 5:
						return personList.get(rowIndex).getSex();
					case 6:
						return Utils.formatDate(personList.get(rowIndex).getBirthdate());
					case 7:
						return personList.get(rowIndex).getVillageName();
					case 8:
						return personList.get(rowIndex).getMothersMiddleName();
					default:
						return null;
				}
			}
			return null;
		}
	}

	private SearchResultsHolder searchResultsHolder;

	public ResultsForm(SearchResultsHolder searchResultsHolder) {
		this.searchResultsHolder = searchResultsHolder;
		initComponents();
		tableLpiResults.setModel(new PersonTableModel(searchResultsHolder.getLpiPersonList()));
		tableMpiResults.setModel(new PersonTableModel(searchResultsHolder.getMpiPersonList()));
	}

	private String getIdentifierTypeName(PersonIdentifier identifier) {
		switch (identifier.getIdentifierType()) {
			case TELEPHONE_NO:
				return "Telephone No";
			case NATIONAL_ID:
				return "National ID";
			case NHIF_NO:
				return "NHIF No";
			case HUDUMA_NO:
				return "Huduma No";
			case PASSPORT_NO:
				return "Passport No";
			case BIRTH_CERTIFICATE_NO:
				return "Birth Certificate No";
			case BIRTH_NOTIFICATION_NO:
				return "Birth Notification No";
			case ALIEN_ID:
				return "Alien ID";
			case NEMIS_ID:
				return "NEMIS ID";
			case masterPatientRegistryId:
				return "Master UUID";
			case patientRegistryId:
				return "Local UUID";
			default:
				return "Unknown";
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		ResourceBundle bundle = ResourceBundle.getBundle("ke.go.moh.oec.demo.ui.strings");
		scrollPane1 = new JScrollPane();
		tableLpiResults = new JTable();
		scrollPane2 = new JScrollPane();
		tableMpiResults = new JTable();

		//======== this ========
		setBorder(new TitledBorder(null, bundle.getString("ResultsForm.this.border"), TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		setLayout(new MigLayout(
				"fill,hidemode 3",
				// columns
				"[fill]",
				// rows
				"[]" +
						"[]" +
						"[]" +
						"[]" +
						"[]"));

		//======== scrollPane1 ========
		{
			scrollPane1.setViewportView(tableLpiResults);
		}
		add(scrollPane1, "cell 0 0");

		//======== scrollPane2 ========
		{
			scrollPane2.setViewportView(tableMpiResults);
		}
		add(scrollPane2, "cell 0 1");
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JScrollPane scrollPane1;
	private JTable tableLpiResults;
	private JScrollPane scrollPane2;
	private JTable tableMpiResults;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
