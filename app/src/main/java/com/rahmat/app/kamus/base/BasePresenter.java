package com.rahmat.app.kamus.base;

import android.app.Fragment;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class BasePresenter<V> {

    protected V mView;

    protected BasePresenter() {}

    /**
     * Call this method in {@link Activity#onCreate}
     * or {@link Fragment#onAttach(Context)} to attach the MVP View object
     */
    @CallSuper
    public void attachView(@NonNull V view) {
        mView = view;
    }

    /**
     * Call this method in {@link Activity#onDestroy()}
     * or {@link Fragment#onDetach()} to detach the MVP View object
     */
    @CallSuper
    public void detachView() {
        mView = null;
    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected final boolean isViewAttached() {
        return mView != null;
    }

}
