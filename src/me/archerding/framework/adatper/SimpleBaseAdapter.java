package me.archerding.framework.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import me.archerding.framework.entity.ViewHolder;

/**
 * Created by Administrator on 2015/8/24.
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {
	protected Context context = null;
	protected List<T> datas = null;
	protected int layoutId;
	protected LayoutInflater layoutInflater = null;

	public SimpleBaseAdapter(Context context, List<T> datas, int layoutId) {
		this.datas = datas;
		this.context = context;
		this.layoutId = layoutId;
		if (context != null) {
			this.layoutInflater = LayoutInflater.from(context);
		}
	}

	public void refreshDatas(List<T> datas) {
		this.datas = datas;
		this.notifyDataSetChanged();
	}

	public void appendDatas(List<T> datas) {
		this.datas.addAll(datas);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public T getItem(int i) {
		return datas.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		final ViewHolder viewHolder = getViewHolder(i, view, viewGroup);
		convert(viewHolder, getItem(i));
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder holder, T item);

	private ViewHolder getViewHolder(int i, View view, ViewGroup viewGroup) {
		return ViewHolder.get(context, view, viewGroup, layoutId, i);
	}
}
