package ke.go.moh.oec.demo.controller;

import ke.go.moh.oec.Person;

import java.util.List;

public class SearchResultsHolder {

	private List<Person> lpiPersonList;

	private List<Person> mpiPersonList;

	public List<Person> getLpiPersonList() {
		return lpiPersonList;
	}

	public void setLpiPersonList(List<Person> lpiPersonList) {
		this.lpiPersonList = lpiPersonList;
	}

	public List<Person> getMpiPersonList() {
		return mpiPersonList;
	}

	public void setMpiPersonList(List<Person> mpiPersonList) {
		this.mpiPersonList = mpiPersonList;
	}
}
