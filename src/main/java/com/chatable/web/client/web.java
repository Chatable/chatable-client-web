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
import javax.annotation.Nullable;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class web implements EntryPoint {

    final WebSocket webSocket = WebSocket.newWebSocketIfSupported();
    TextDisplay td = new TextDisplay();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        if ( null != webSocket ) {
            webSocket.setListener( new WebSocketListenerAdapter() {
                @Override
                public void onOpen( @Nonnull final WebSocket webSocket ) {
                    webSocket.send( "Hello! New user connected through web client!" );
                }
                @Override
                public void onMessage(@Nonnull WebSocket webSocket, @Nonnull String s) {
                    td.updateText(s);
                }
                @Override
                public void onClose( @Nonnull final WebSocket webSocket,
                                     final boolean wasClean,
                                     final int code,
                                     @Nullable final String reason ) {td.updateText("Connection closed!");}

                @Override
                public void onMessage( @Nonnull final WebSocket webSocket, @Nonnull final ArrayBuffer data ) {
                }

                @Override
                public void onError( @Nonnull final WebSocket webSocket ) {
                }

            } );
            webSocket.connect( "ws://104.236.73.126:49160" );
        }

        TextEditor tb = new TextEditor(td, this);

        DockLayoutPanel p = new DockLayoutPanel(Style.Unit.EM);
        p.addNorth(new Header(), 8);
        p.addSouth(tb, 5);
        p.add(td);
        
        RootLayoutPanel.get().add(p);

    }
    
    public void sendMessage(String s) {
        webSocket.send( s );
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
