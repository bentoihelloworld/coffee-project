package com.vaadin.samples.authentication;

import com.vaadin.sample.backend.webservice.AuthenticationService;

/**
 * Default mock implementation of {@link AccessControl}. This implementation
 * accepts any string as a password, and considers the user "admin" as the only
 * administrator.
 */
public class BasicAccessControl implements AccessControl {

	@Override
	public boolean signIn(String username, String password) {
		Boolean isValidated = false;

		if (username != null  && password != null ) {
			AuthenticationService auth = new AuthenticationService();

			isValidated = auth.validateCredentials(username, password);
			// System.out.println("Enter --- basic access control: " +
			// isValidated);

			CurrentUser.set(username);
		}
		return isValidated;
	}

	@Override
	public boolean isUserSignedIn() {
		return !CurrentUser.get().isEmpty();
	}

	@Override
	public boolean isUserInRole(String role) {
		if ("admin".equals(role)) {
			// Only the "admin" user is in the "admin" role
			return getPrincipalName().equals("admin");
		}

		// All users are in all non-admin roles
		return true;
	}

	@Override
	public String getPrincipalName() {
		return CurrentUser.get();
	}

}
