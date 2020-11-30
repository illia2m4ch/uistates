package com.ilya.uistates;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ilya.uistates.uistates.UIContainer;
import com.ilya.uistates.uistates.UIState;
import com.ilya.uistates.uistates.UIStates;

public class MainActivity extends AppCompatActivity {

    public static final int MAIN_CONTAINER = 0;
    public static final int ADDITIONAL_CONTAINER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UIStates states = UIStates.builder()
                // Registering main container
                .createContainer(MAIN_CONTAINER)
                .registerUIState(UIState.LOADING, findViewById(R.id.main_loading))
                .registerUIState(UIState.CONTENT, findViewById(R.id.main_content))
                .setUIState(UIState.LOADING)
                .registerContainer()

                // Registering additional container
                .createContainer(ADDITIONAL_CONTAINER)
                .registerUIState(UIState.ERROR, findViewById(R.id.additional_error))
                .registerUIState(UIState.CONTENT, findViewById(R.id.additional_content))
                .setUIState(UIState.CONTENT)
                .registerContainer()

                // Getting the instance of UIStates class
                .build();

        // First way to change the UIState of a container
        UIContainer mainContainer = states.getContainer(MAIN_CONTAINER);
        mainContainer.setUIState(UIState.LOADING);

        // Second one, the same
        states.setUIState(MAIN_CONTAINER, UIState.LOADING);
    }
}