package com.deepsingh44.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class ServerEndPoint {
	private static Set<Session> userSessions = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());

	@OnOpen
	public void openSession(Session currentSession) {
		userSessions.add(currentSession);
	}

	@OnClose
	public void closeSession(Session currentSession) {
		userSessions.remove(currentSession);
	}

	@OnMessage
	public void onMessage(String message, Session currentSession) {
		for (Session session : userSessions) {
			session.getAsyncRemote().sendText(message);
		}
	}
}
