package me.archerding.framework.fragment;

import me.archerding.framework.event.ResultEvent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/24.
 */
@SuppressLint({ "ShowToast", "InlinedApi" })
public abstract class BaseFragment extends Fragment {
	public Context context;
	private ProgressDialog progressDialog;
	private Toast toast;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(getLayoutId(), container, false);
		ViewUtils.inject(this, view);
		toast = Toast.makeText(context, "没有更多数据了", Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		progressDialog = new ProgressDialog(context,
				ProgressDialog.THEME_HOLO_DARK);
		EventBus.getDefault().register(this);
		initParams();
		return view;
	}

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
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	protected abstract int getLayoutId();

	protected abstract void initParams();

}
