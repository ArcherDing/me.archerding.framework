package me.archerding.framework.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by ArcherDing on 2015/8/24.
 */
public class ActivityStack {
    private static ActivityStack mSingleInstance;
    private Stack<Activity> mActivtyStack;

    public ActivityStack() {
        this.mActivtyStack = new Stack<Activity>();
    }

    public static ActivityStack getInstance() {
        if (null == mSingleInstance) {
            mSingleInstance = new ActivityStack();
        }
        return mSingleInstance;
    }

    public Stack<Activity> getStack() {
        return mActivtyStack;
    }


    public void addActivity(Activity activity) {
        mActivtyStack.push(activity);
    }

    public void removeActivity(Activity activity) {
        mActivtyStack.remove(activity);
    }

    public void finishAllActivity() {
        Activity activity;
        while (!mActivtyStack.empty()) {
            activity = mActivtyStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public boolean finishActivity(Class<? extends Activity> actCls) {
        Activity activity = findActivityByClass(actCls);
        if (null != activity && !activity.isFinishing()) {
            activity.finish();
            return true;
        }
        return false;
    }

    public Activity findActivityByClass(Class<? extends Activity> actCls) {
        Activity activity = null;
        Iterator<Activity> itr = mActivtyStack.iterator();
        while (itr.hasNext()) {
            activity = itr.next();
            if (null != activity && activity.getClass().getName().equals(actCls.getName()) && !activity.isFinishing()) {
                break;
            }
            activity = null;
        }

        return activity;
    }

    public boolean finishToActivity(Class<? extends Activity> actCls, boolean isIncludeSelf) {
        List<Activity> buf = new ArrayList<Activity>();
        int size = mActivtyStack.size();
        Activity activity = null;
        for (int i = size - 1; i >= 0; i--) {
            activity = mActivtyStack.get(i);
            if (activity.getClass().isAssignableFrom(actCls)) {
                for (Activity a : buf) {
                    a.finish();
                }
                return true;
            } else if (i == size - 1 && isIncludeSelf) {
                buf.add(activity);
            } else {
                buf.add(activity);
            }
        }

        return false;
    }
}
