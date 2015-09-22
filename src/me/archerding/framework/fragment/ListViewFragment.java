package me.archerding.framework.fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public abstract class ListViewFragment extends BaseFragment {
	private PullToRefreshListView listView;

	protected abstract int getLayoutId();

	protected abstract PullToRefreshListView getListView();

	protected abstract void initData();

	protected abstract void loadData();

	protected abstract void refreshData();

	@SuppressLint({ "ShowToast", "InlinedApi" })
	@Override
	protected void initParams() {

		listView = getListView();
		listView.setMode(Mode.BOTH);
		// 下拉刷新时的提示文本设置
		listView.getLoadingLayoutProxy(true, false).setLastUpdatedLabel("下拉刷新");
		listView.getLoadingLayoutProxy(true, false).setPullLabel("");
		listView.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在刷新");
		listView.getLoadingLayoutProxy(true, false).setReleaseLabel("放开以刷新");
		// 上拉加载更多时的提示文本设置
		listView.getLoadingLayoutProxy(false, true).setLastUpdatedLabel("上拉加载");
		listView.getLoadingLayoutProxy(false, true).setPullLabel("");
		listView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
				"正在加载...");
		listView.getLoadingLayoutProxy(false, true).setReleaseLabel("放开以加载");
		listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			// 下拉Pulling Down
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// 下拉的时候数据重置
				refreshData();
				new FinishRefresh().execute();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				loadData();
				new FinishRefresh().execute();
			}
		});

		initData();
	}

	private class FinishRefresh extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			listView.onRefreshComplete();
		}
	}
}
