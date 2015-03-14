package com.chatable.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;


/**
 * Created by henry on 8/02/2015.
 */
public class TextEditor extends Composite implements ClickHandler, KeyDownHandler {

    interface TextEditorUiBinder extends UiBinder<HTMLPanel, TextEditor> {
    }

    private static TextEditorUiBinder ourUiBinder = GWT.create(TextEditorUiBinder.class);

    private TextDisplay textDisplay;

    @UiField
    org.gwtbootstrap3.client.ui.TextBox text2;
    
    @UiField
    Button button1;
    
    @UiField
    Button button2;

    private web web;

    public TextEditor(TextDisplay textDisplay, web web) {
        this.web = web;
        this.textDisplay = textDisplay;
        initWidget(ourUiBinder.createAndBindUi(this));
        button1.addClickHandler(this);
        button2.addClickHandler(this);
        text2.addKeyDownHandler(this);
    }


    public void onClick(ClickEvent event) {
        // note that in general, events can have sources that are not Widgets.
        Widget sender = (Widget) event.getSource();

        if (!text2.getText().equals("")) {
            textDisplay.updateText(text2.getText());
            web.sendMessage(text2.getText());
            text2.clear();
        }
    }

    @Override
    public void onKeyDown(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            textDisplay.updateText(text2.getText());
            web.sendMessage(text2.getText());
            text2.clear();
        }
    }
    
    
}