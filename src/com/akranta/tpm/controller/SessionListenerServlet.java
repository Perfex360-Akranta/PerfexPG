package com.akranta.tpm.controller;

import com.akranta.tpm.utils.CommonMessage;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListenerServlet implements HttpSessionListener {

  private static int totalActiveSessions;
	
  public static int getTotalActiveSession(){
	return totalActiveSessions;
  }
	
  @Override
  public void sessionCreated(HttpSessionEvent arg0) {
	totalActiveSessions++;
	CommonMessage.debugMsg("sessionCreated - add one session into counter");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent arg0) {
	totalActiveSessions--;
	CommonMessage.debugMsg("sessionDestroyed - deduct one session from counter");
  }	
}