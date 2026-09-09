package com.akranta.tpm.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.servlet.http.HttpSession;

public class SessionRegistry {

    public static class SessionInfo {
        public HttpSession session;
        public String ipAddress;
        public String machineName;
        public long lastActivity;

        public SessionInfo(HttpSession session, String ipAddress, String machineName) {
            this.session = session;
            this.ipAddress = ipAddress;
            this.machineName = machineName;
            this.lastActivity = System.currentTimeMillis();
        }
    }

    private static final Map<String, SessionInfo> activeSessions = new ConcurrentHashMap<>();

    // Check if user already has an active session
    public static SessionInfo getExistingSession(String userId) {
        SessionInfo info = activeSessions.get(userId);
        if (info == null) return null;

        // Check if the session is still valid
        try {
            info.session.getAttribute("user"); // throws if invalidated
            return info;
        } catch (IllegalStateException e) {
            activeSessions.remove(userId); // stale entry, clean up
            return null;
        }
    }

    // Force register (called after user confirms)
    public static void forceRegisterSession(String userId, HttpSession newSession,
                                             String ipAddress, String machineName) {
        SessionInfo existing = activeSessions.get(userId);
        if (existing != null) {
            try {
                existing.session.invalidate(); // invalidate old session now
            } catch (IllegalStateException ignored) {}
        }
        activeSessions.put(userId, new SessionInfo(newSession, ipAddress, machineName));
    }

    // Register without conflict (first login)
    public static void registerSession(String userId, HttpSession session,
                                        String ipAddress, String machineName) {
        activeSessions.put(userId, new SessionInfo(session, ipAddress, machineName));
    }

    public static void removeSession(String userId) {
        activeSessions.remove(userId);
    }
}