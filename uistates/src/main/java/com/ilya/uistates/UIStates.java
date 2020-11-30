package com.ilya.uistates;

import android.util.SparseArray;

public class UIStates {

    private SparseArray<UIContainer> containers;

    private UIStates() {

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private UIStates UIStates;

        Builder() {
            UIStates = new UIStates();
        }

        public UIContainer.Builder createContainer(int id) {
            return new UIContainer.Builder(this, id);
        }

        public Builder registerContainer(UIContainer container) {
            UIStates.containers.put(container.id, container);
            return this;
        }

        public UIStates build() {
            return new UIStates();
        }

    }

    public UIContainer getContainer(int id) {
        UIContainer container = containers.get(id);
        if (container == null) throw new IllegalStateException("There is no container with id = " + id);

        return container;
    }

    public void setUIState(int containerId, int state) {
        getContainer(containerId).setUIState(state);
    }

}
