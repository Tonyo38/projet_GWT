package com.trollcustom.client;

import java.util.ArrayList;

import com.trollcustom.shared.Troll;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(Troll input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void greetServer(String id,Troll input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void greetServer(String input, AsyncCallback<ArrayList<Troll>> callback)
			throws IllegalArgumentException;
	
	void greetServer(AsyncCallback<String> callback)
			throws IllegalArgumentException;
	
	void imgUploaded(String url,AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
