package pl.bartoszsredzinski.application.ui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.bartoszsredzinski.application.backend.model.entity.Company;
import pl.bartoszsredzinski.application.backend.model.entity.Contact;
import pl.bartoszsredzinski.application.backend.service.ContactService;


@PageTitle("Hello World")
@Route(value = "")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout{

    private final ContactService contactService;

    private Grid<Contact> grid = new Grid<>(Contact.class);
    private TextField filterText = new TextField();
    private ContactForm form;

    public MainView(ContactService contactService){
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        configureFilter();
        configureGrid();

        form = new ContactForm();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(filterText, content);
        updateList();
    }

    private void configureFilter(){
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());
    }

    private void updateList(){
        grid.setItems(contactService.findAll(filterText.getValue()));
    }

    private void configureGrid(){
        grid.addClassName("contact-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("company");
        grid.setColumns("firstName", "lastName", "email", "status");
        grid.addColumn(contact -> {
            Company company = contact.getCompany();
            return company == null ? "-" : company.getName();
        }).setHeader("Company");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

}
