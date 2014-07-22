package test.com.twm.pt.robolectricsampletests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.robolectricsample.LoginActivity;
import com.example.robolectricsample.MainActivity;
import com.example.robolectricsample.MainActivity.PlaceholderFragment;
import com.example.robolectricsample.R;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

	MainActivity mainActivity;
	LoginActivity loginActivity;
	PlaceholderFragment fragment;

	@Before
	public void setup() {
		mainActivity = Robolectric.buildActivity(MainActivity.class).create().start().resume().get();
		loginActivity = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();
		fragment = new PlaceholderFragment();
		startFragment(fragment);
		
		Robolectric.setDefaultHttpResponse(200, "OK");
	}

	@Test
	public void shouldHaveHappySmiles() throws Exception {
		String hello = "Hello world!";
		assertThat(hello, equalTo("Hello world!"));
	}

	public void startFragment(Fragment fragment) {
		FragmentManager fragmentManager = mainActivity.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(fragment, null);
		fragmentTransaction.commit();
	}

	@Test
	public void shouldClickCustomButtonTransferToLoginActivity() throws Exception {

		mainActivity.findViewById(R.id.custom_login).performClick();
		Intent expectedIntent = new Intent(mainActivity, LoginActivity.class);
		assertThat(Robolectric.shadowOf(mainActivity).getNextStartedActivity(),
				equalTo(expectedIntent));
	}
	
//	@Test
//	public void shouldOnClickLoginButtonToAcessLogin() throws Exception {
//		Robolectric.setDefaultHttpResponse(200, "response body");
//		loginActivity.login();
//	}
}







