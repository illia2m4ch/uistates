package com.ilya.uistates;

import android.util.SparseArray;
import android.view.View;

public class UIContainer {

    public final int id;
    private final SparseArray<View> UIStates = new SparseArray<>();
    private int currentState;

    private UIContainer(int id) {
        this.id = id;
    }

    public static class Builder {

        private final UIStates.Builder caller;
        private final UIContainer container;

        Builder(UIStates.Builder caller, int id) {
            this.caller = caller;
            this.container = new UIContainer(id);
        }

        public Builder registerUIState(int state, View view) {
            container.UIStates.put(state, view);
            view.setVisibility(View.GONE);
            return this;
        }

        public Builder setUIState(int state) {
            container.setUIState(state);
            return this;
        }

        public UIStates.Builder registerContainer() {
            caller.registerContainer(container);
            return caller;
        }

    }

    public void setUIState(int state) {
        if (currentState == state) return;
        View currentView = UIStates.get(currentState);
        if (currentView != null) currentView.setVisibility(View.GONE);
        View view = UIStates.get(state);
        if (view == null) throw new IllegalStateException("UIState " + state +
                " is not found. Please, use registerUIState() before call this method");
        view.setVisibility(View.VISIBLE);
        currentState = state;
    }

}
