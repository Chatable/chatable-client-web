package com.chatable.web.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.chatable.web.client.webService;

public class webServiceImpl extends RemoteServiceServlet implements webService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}