package pl.bartoszsredzinski.application.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import pl.bartoszsredzinski.application.ui.dashboard.DashboardView;
import pl.bartoszsredzinski.application.ui.list.ListView;

/**
 * Class description
 *
 * @author Bartosz Średziński
 * created on 15.06.2022
 */
@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout{
    public MainLayout(){
        createHeader();
        createDrawer();
    }

    private void createHeader(){
        H1 logo = new H1("Vaadin CRM");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer(){
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(listLink, new RouterLink("Dashboard", DashboardView.class)));
    }
}
