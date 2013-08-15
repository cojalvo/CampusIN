package il.ac.shenkar.cadan;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import il.ac.shenkar.common.CampusInConstant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;

public class PrefsFragment extends PreferenceFragment {
	public static final String ACTION_INTENT = "il.ac.shenkar.CHANGE";
	OnPreferenceSelectedListener mCallback;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Parse.initialize(getActivity(), "3kRz2kNhNu5XxVs3mI4o3LfT1ySuQDhKM4I6EblE",
				"UmGc3flrvIervInFbzoqGxVKapErnd9PKnXy4uMC");
		ParseFacebookUtils.initialize("635010643194002");
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preference);

		Preference testCalender = findPreference("my_calendar");
		testCalender
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference preference) {
//						startActivity(new Intent(
//								getActivity().getBaseContext(),
//								CalendarAvtivity.class));
						mCallback.onPreferenceSelected(CampusInConstant.SETTINGS_EVENTS);
						return true;
					}
				});
	}

	public interface OnPreferenceSelectedListener {
		public void onPreferenceSelected(String selected);
	}
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnPreferenceSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}