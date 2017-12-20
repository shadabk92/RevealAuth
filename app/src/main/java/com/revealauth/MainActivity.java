package com.revealauth;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


	@BindView(R.id.fab)
	FloatingActionButton mFab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);


	}
	@OnClick(R.id.fab)
	public void onFabClicked() {
		startContactActivity();
	}

	private void startContactActivity() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityOptions options =
					ActivityOptions.makeSceneTransitionAnimation(this, mFab, mFab.getTransitionName());
			startActivity(new Intent(this, RegistrationActivity.class), options.toBundle());
		} else {
			startActivity(new Intent(this, RegistrationActivity.class));
		}
	}
}
