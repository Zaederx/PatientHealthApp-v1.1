package app.PatientHealthApp.GoogleAuth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

/**
 * Google Service Account Set Up.
 * As described and explained by Google 
 * {@linkplain https://cloud.google.com/docs/authentication/getting-started}
 * {@linkplain https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/auth/src/main/java/com/google/cloud/auth/samples/AuthExample.java}
 * <br>
 * Further Reading:
 * {@linkplain https://cloud.google.com/iam/docs/service-accounts?_ga=2.254922504.-1641126563.1588857535}
 * {@linkplain https://cloud.google.com/docs/authentication/}
 * {@linkplain https://developers.google.com/identity/protocols/oauth2/web-server}
 * @author zacharyishmael
 *
 */
public class GoogleServiceAccount {

	private static final String CRED_PATH = "patienthealthapp-1586796942280-4dc7174a1fd7.json";
	static void explicitAuthentication() throws FileNotFoundException, IOException {
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CRED_PATH)).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		 Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	
		  System.out.println("Buckets:");
		  Page<Bucket> buckets = storage.list();
		  for (Bucket bucket : buckets.iterateAll()) {
		    System.out.println(bucket.toString());
		  }

	}
}
