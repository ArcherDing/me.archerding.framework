package me.archerding.framework.activity;

import me.archerding.framework.event.ResultEvent;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by ArcherDing on 2015/8/24.
 */
@SuppressLint({ "ShowToast", "InlinedApi" })
public abstract class BaseActivity extends FragmentActivity {
	private ProgressDialog progressDialog;
	private Toast toast;
	private boolean isCreate = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityStack.getInstance().addActivity(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getLayoutId());
		ViewUtils.inject(this);
		isCreate = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (isCreate) {
			isCreate = false;
			toast = Toast.makeText(this, "没有更多数据了", Toast.LENGTH_LONG);
			toast.setGravity(Gravity.CENTER, 0, 0);
			progressDialog = new ProgressDialog(this,
					ProgressDialog.THEME_HOLO_DARK);
			EventBus.getDefault().register(this);
			initParams();
		}
	}

	protected abstract int getLayoutId();

	protected abstract void initParams();

	public void showToast(String msg) {
		toast.setText(msg);
		toast.show();
	}

	public void showProgressDialog(String msg) {
		if (!progressDialog.isShowing()) {
			progressDialog.setMessage(msg);
			progressDialog.show();
		}
	}

	public void dismissProgressDialog() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	public void onEventMainThread(ResultEvent event) {

	}

	@Override
	protected void onDestroy() {
		EventBus.getDefault().unregister(this);
		dismissProgressDialog();
		ActivityStack.getInstance().removeActivity(this);
		super.onDestroy();
	}

}
