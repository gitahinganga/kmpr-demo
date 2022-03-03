package ke.go.moh.oec.demo.controller;

import ke.go.moh.oec.Person;
import ke.go.moh.oec.PersonRequest;
import ke.go.moh.oec.PersonResponse;
import ke.go.moh.oec.lib.Mediator;

import java.util.ArrayList;
import java.util.List;

public class RequestThread extends Thread {

	private final Mediator mediator;

	private final int requestTypeId;

	private final PersonRequest personRequest;

	private ResponseReceiver responseReceiver;

	private final boolean wait;

	public RequestThread(Mediator mediator, int requestTypeId, PersonRequest personRequest, ResponseReceiver responseReceiver) {
		this.mediator = mediator;
		this.requestTypeId = requestTypeId;
		this.personRequest = personRequest;
		this.responseReceiver = responseReceiver;
		this.wait = responseReceiver != null;
	}

	public RequestThread(Mediator mediator, int requestTypeId, PersonRequest personRequest) {
		this(mediator, requestTypeId, personRequest, null);
	}

	@Override
	public void run() {
		if (wait) {
			PersonResponse response = (PersonResponse) mediator.getData(requestTypeId, personRequest);
			if (response != null) {
				if (response.isSuccessful()) {
					List<Person> personList = response.getPersonList();
					if (personList != null) {
						responseReceiver.onResponseReceived(true, requestTypeId, personList);
					} else {
						responseReceiver.onResponseReceived(true, requestTypeId, new ArrayList<>());
					}
				} else {
					responseReceiver.onResponseReceived(false, requestTypeId, new ArrayList<>());
				}
			}
		} else {
			mediator.getData(requestTypeId, personRequest);
		}
	}
}
