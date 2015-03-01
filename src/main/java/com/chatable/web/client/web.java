package com.chatable.web.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.typedarrays.shared.ArrayBuffer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import org.realityforge.gwt.websockets.client.WebSocket;
import org.realityforge.gwt.websockets.client.WebSocketListener;
import org.realityforge.gwt.websockets.client.WebSocketListenerAdapter;

import javax.annotation.Nonnull;
/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class web implements EntryPoint, WebSocketListener {

    final WebSocket webSocket = WebSocket.newWebSocketIfSupported();
    TextDisplay td = new TextDisplay();



    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();
        final Button button2 = new Button("Click me AGAIN");

        if ( null != webSocket )
        {
            webSocket.setListener( new WebSocketListenerAdapter()
            {
                @Override
                public void onOpen( @Nonnull final WebSocket webSocket )
                {
                    // After we have connected we can send
                    webSocket.send( "Hello! New user connected through web client!" );
                }
                @Override
                public void onMessage(@Nonnull WebSocket webSocket, @Nonnull String s) {
                    td.updateText(s);
                }

            } );
            webSocket.connect( "ws://104.236.73.126:49160" );
        }

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    webService.App.getInstance().getMessage("Hello, World", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //


        //RootPanel.get("slot1").add(button);
        //RootPanel.get("slot2").add(label);



        TextEditor tb = new TextEditor(td, this);



       // RootPanel.get().add(button2);
        //RootLayoutPanel.get().add(tb);

        DockLayoutPanel p = new DockLayoutPanel(Style.Unit.EM);
        p.addNorth(new Header(), 8);
        p.addSouth(tb, 5);
        p.add(td);
        
        RootLayoutPanel.get().add(p);

    }
    
    public void sendMessage(String s) {
        webSocket.send( s );


    }

    @Override
    public void onOpen(@Nonnull WebSocket webSocket) {
        webSocket.send( "is this even called?" );

    }

    @Override
    public void onClose(@Nonnull WebSocket webSocket, boolean b, int i, String s) {
        System.out.println("CONNECTION CLOSING");

    }

    @Override
    public void onMessage(@Nonnull WebSocket webSocket, @Nonnull String s) {
    }

    @Override
    public void onMessage(@Nonnull WebSocket webSocket, @Nonnull ArrayBuffer arrayBuffer) {

    }

    @Override
    public void onError(@Nonnull WebSocket webSocket) {
        System.out.println("HUGE ARROR");
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
