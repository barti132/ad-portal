package pl.bartoszsredzinski.application.backend.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;
import pl.bartoszsredzinski.application.ui.login.LoginView;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 15.06.2022
 */
@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener{
    @Override
    public void serviceInit(ServiceInitEvent event){
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event){
        if(!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()){
            event.rerouteTo(LoginView.class);
        }
    }

}
