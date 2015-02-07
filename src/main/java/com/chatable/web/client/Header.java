package com.chatable.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import org.gwtbootstrap3.client.ui.Image;

/**
 * Created by henry on 8/02/2015.
 */
public class Header extends Composite {
    interface HeaderUiBinder extends UiBinder<HTMLPanel, Header> {
    }

    @UiField
    Image image1;
    
    private static HeaderUiBinder ourUiBinder = GWT.create(HeaderUiBinder.class);

    public Header() {
        initWidget(ourUiBinder.createAndBindUi(this));
        image1.setHeight("8em");
        
        //Image link on click
        image1.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent ev) {
                Window.Location.assign("http://chatable.io");
            }
        });
    }
}