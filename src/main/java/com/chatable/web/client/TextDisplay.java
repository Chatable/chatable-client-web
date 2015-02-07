package com.chatable.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;

/**
 * Created by henry on 8/02/2015.
 */
public class TextDisplay extends Composite {
    interface TextDisplayUiBinder extends UiBinder<HTMLPanel, TextDisplay> {
    }


    @UiField
    ListGroup list1;

    @UiField
    ScrollPanel scroll;

    private static TextDisplayUiBinder ourUiBinder = GWT.create(TextDisplayUiBinder.class);

    public TextDisplay() {
        initWidget(ourUiBinder.createAndBindUi(this));
        list1.setHeight("95%");
        //list1.setMarginBottom(50);
    }
    
    public void updateText(String str) {
        ListGroupItem lgi = new ListGroupItem();
        lgi.setText(str);
        list1.add(lgi);
        scroll.scrollToBottom();

    }
}