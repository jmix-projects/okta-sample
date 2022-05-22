package com.company.oks.screen.sessiondata;

import com.company.oks.session.SoapPorts;
import io.jmix.core.session.SessionData;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("SessionDataScreen")
@UiDescriptor("session-data-screen.xml")
public class SessionDataScreen extends Screen {

    @Autowired
    private TextField<String> passwordField;

    @Autowired
    private CheckBox authenticatedCb;

    @Autowired
    private SessionData sessionData;

    private static final Logger log = LoggerFactory.getLogger(SessionDataScreen.class);

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        refreshFields();
    }

    @Subscribe("refreshBtn")
    public void onRefreshBtnClick(Button.ClickEvent event) {
        refreshFields();
    }

    private void refreshFields() {
        SoapPorts soapPorts = (SoapPorts) sessionData.getAttribute("soapPorts");
        if (soapPorts != null) {
            passwordField.setValue(soapPorts.getPassword());
            authenticatedCb.setValue(soapPorts.isAuthenticated());
        } else {
            log.error("SoapPorts bean not found in session data");
        }
    }
}