package com.vaadin.sample.backend.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthenticationService {

	public boolean validateCredentials(String inputuname, String inputpasswd) {
		Boolean isCredentialMatch = false;
		
		if (inputuname == null || inputuname.isEmpty() && inputpasswd == null || inputpasswd.isEmpty())
			return false;

		try {
			URL url = new URL("http://webservice.jelastic.servint.net/webapi/validate/get");
			URLConnection connection = url.openConnection();

			// set proxy when in office
			// System.setProperty("http.proxyHost", "10.158.17.67");
			// System.setProperty("http.proxyPort", "8080");

			String line;

			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
				JSONObject json = new JSONObject(builder.toString());
				String name = (String) json.get("username");
				String password = (String) json.get("passwd");
				System.out.println("input username:" + inputuname);
				System.out.println("input password:" + inputpasswd);
				System.out.println("object json value in name:" + name);
				System.out.println("object json value in passwd:" + password);

				if (inputuname.trim().equals(name) && inputpasswd.trim().equals(password)) {
					isCredentialMatch = true;
				} else {
					System.out.println("credential does not match 3");
				}

			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("return value of iscredential match: " + isCredentialMatch);
		return isCredentialMatch;
	}
}
