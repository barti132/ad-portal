package pl.bartoszsredzinski.application.ui.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.bartoszsredzinski.application.backend.model.entity.Company;
import pl.bartoszsredzinski.application.backend.model.entity.Contact;
import pl.bartoszsredzinski.application.backend.service.CompanyService;
import pl.bartoszsredzinski.application.backend.service.ContactService;
import pl.bartoszsredzinski.application.ui.MainLayout;


@PageTitle("Contacts | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
@CssImport("./styles/shared-styles.css")
public class ListView extends VerticalLayout{

    private final ContactService contactService;

    private Grid<Contact> grid = new Grid<>(Contact.class);
    private TextField filterText = new TextField();
    private ContactForm form;

    public ListView(ContactService contactService, CompanyService companyService){
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();

        configureGrid();

        form = new ContactForm(companyService.findAll());
        form.addListener(ContactForm.SaveEvent.class, this::saveContact);
        form.addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        form.addListener(ContactForm.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private <T extends ComponentEvent<?>> void deleteContact(ContactForm.DeleteEvent event){
        contactService.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private <T extends ComponentEvent<?>> void saveContact(ContactForm.SaveEvent event){
        contactService.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void closeEditor(){
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar(){
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");

        return toolbar;
    }

    private void addContact(){
        grid.asSingleSelect().clear();
        editContact(new Contact());
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
        grid.asSingleSelect().addValueChangeListener(event -> editContact(event.getValue()));
    }

    private void editContact(Contact contact){
        if(contact == null){
            closeEditor();
        }
        else{
            form.setContact(contact);
            form.setVisible(true);
            addClassName("editing");
        }
    }

}
