package com.company.oks.screen.tenantauthentication;

import com.company.oks.session.SoapPorts;
import io.jmix.core.session.SessionData;
import io.jmix.ui.Dialogs;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.TextField;
import io.jmix.ui.executor.BackgroundTask;
import io.jmix.ui.executor.TaskLifeCycle;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

@UiController("TenantAuthenticationScreen")
@UiDescriptor("tenant-authentication-screen.xml")
public class TenantAuthenticationScreen extends Screen {

    @Autowired
    private Dialogs dialogs;

    @Autowired
    private TextField<String> passwordField;

    @Autowired
    private SessionData sessionData;

    @Autowired
    private Label<String> authenticatedSuccessfullyBtn;

    private static final Logger log = LoggerFactory.getLogger(TenantAuthenticationScreen.class);

    @Subscribe("authenticateBtn")
    public void onAuthenticateBtnClick(Button.ClickEvent event) {
        //create new instance of SoapPorts object and store it as session attribute
        //if the SoapPorts needs to be a Spring bean, make it a PROTOTYPE bean (not session bean)
        SoapPorts soapPorts = new SoapPorts();
        sessionData.setAttribute("soapPorts", soapPorts);

        //pass the just created SoapPorts instance into the background task. The task will modify the soapPorts instance
        BackgroundTask<Integer, Void> task = new AuthenticationTask(soapPorts);

        //this background dialog shows indeterminate progress, no percentage is needed here
        dialogs.createBackgroundWorkDialog(this, task)
                .withCaption("Authenticating...")
                .withMessage("Please wait while we verify your credentials")
                .show();
    }

    public class AuthenticationTask extends BackgroundTask<Integer, Void> {

        private SoapPorts soapPorts;

        public AuthenticationTask(SoapPorts soapPorts) {
            super(3, TimeUnit.MINUTES, TenantAuthenticationScreen.this);
            this.soapPorts = soapPorts;
        }

        @Override
        public Void run(TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
            this.soapPorts.authenticate(passwordField.getValue());
            return null;
        }

        @Override
        public void done(Void result) {
            log.info(">>> authenticated: {}", this.soapPorts.isAuthenticated());

            //alternatively, show the notification or indicate in any other way
            authenticatedSuccessfullyBtn.setVisible(true);
        }
    }
}