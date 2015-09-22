package me.archerding.framework.event;

import me.archerding.framework.event.BaseEvent;

public class ResultEvent extends BaseEvent {
	public static final int OK = 0;
	public static final int CANCEL = 1;
	public static final int ERROR = 2;

	private int resultCode;

	public ResultEvent(int resultCode) {
		this.resultCode = resultCode;
	}

	public int getResultCode() {
		return resultCode;
	}

}
