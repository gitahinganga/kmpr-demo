package ke.go.moh.oec.demo.controller;

import ke.go.moh.oec.Person;

import java.util.List;

public interface ResponseReceiver {

	void onResponseReceived(boolean successful, int requestTypeId, List<Person> persons);
}
