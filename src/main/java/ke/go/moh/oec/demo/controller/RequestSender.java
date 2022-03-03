package ke.go.moh.oec.demo.controller;

import ke.go.moh.oec.PersonRequest;
import ke.go.moh.oec.RequestTypeId;
import ke.go.moh.oec.lib.Mediator;

public class RequestSender {

	public enum RequestType {

		FIND_PERSON,
		CREATE_PERSON,
		MODIFY_PERSON
	}

	public enum PersonIndex {

		LOCAL,
		MASTER
	}

	private static final Mediator MEDIATOR = new Mediator();

	public static void sendRequest(RequestType requestType, PersonRequest personRequest, ResponseReceiver responseReceiver, PersonIndex... personIndices) throws InterruptedException {
		for (PersonIndex personIndex : personIndices) {
			spawn(
					new RequestThread(
							MEDIATOR,
							getRequestTypeId(requestType, personIndex),
							personRequest,
							responseReceiver
					),
					requestType == RequestType.FIND_PERSON
			);
		}
	}

	public static void sendRequest(RequestType requestType, PersonRequest personRequest, PersonIndex... personIndices) throws InterruptedException {
		for (PersonIndex personIndex : personIndices) {
			spawn(
					new RequestThread(
							MEDIATOR,
							getRequestTypeId(requestType, personIndex),
							personRequest
					),
					requestType == RequestType.FIND_PERSON
			);
		}
	}

	private static int getRequestTypeId(RequestType requestType, PersonIndex personIndex) {
		if (requestType == RequestType.FIND_PERSON) {
			return personIndex == PersonIndex.LOCAL ? RequestTypeId.FIND_PERSON_LPI : RequestTypeId.FIND_PERSON_MPI;
		} else if (requestType == RequestType.CREATE_PERSON) {
			return personIndex == PersonIndex.LOCAL ? RequestTypeId.CREATE_PERSON_LPI : RequestTypeId.CREATE_PERSON_MPI;
		} else {
			return personIndex == PersonIndex.LOCAL ? RequestTypeId.MODIFY_PERSON_LPI : RequestTypeId.MODIFY_PERSON_MPI;
		}
	}

	private static void spawn(RequestThread requestThread, boolean join) throws InterruptedException {
		requestThread.start();
		if (join) {
			requestThread.join();
		}
	}
}
