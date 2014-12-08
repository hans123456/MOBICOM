package com.example.plan8_ui.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.example.plan8_ui.R;
import com.example.plan8_ui.Model.HTML;
import com.example.plan8_ui.Model.User;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link ProfileFragment#newInstance} factory method to create an instance of
 * this fragment.
 *
 */
public class ProfileFragment extends Fragment {

	@InjectView(R.id.profile_fragment_pic_image_view) ImageView image_view;
	@InjectView(R.id.profile_fragment_first_name_text_view) TextView first_name_text_view;
	@InjectView(R.id.profile_fragment_last_name_text_view) TextView last_name_text_view;
	
	private View ProfileFragmentView;
	
	public static ProfileFragment newInstance(String param1, String param2) {
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public ProfileFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		
		ProfileFragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
		ButterKnife.inject(this, ProfileFragmentView);
		
		Picasso.with(ProfileFragmentView.getContext())
			.load("https://graph.facebook.com/"+HTML.userProfile.get_information(User.id_pic)+"/picture?type=large")
			.resize(300, 300)
			.centerCrop()
			.into(image_view);

		first_name_text_view.setText(HTML.userProfile.get_information(User.id_first_name));
		last_name_text_view.setText(HTML.userProfile.get_information(User.id_last_name));
		
		return ProfileFragmentView;
		
	}

}
