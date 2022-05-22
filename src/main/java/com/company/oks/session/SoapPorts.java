package com.company.oks.session;

/**
 * If this calss must be a Spring bean, make it a PROTOTYPE bean
 */
public class SoapPorts {

    private String password;

    private boolean authenticated;

    public void authenticate(String password) {
        this.password = password;
        try {
            //some long-running operation
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.authenticated = true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getPassword() {
        return password;
    }
}
