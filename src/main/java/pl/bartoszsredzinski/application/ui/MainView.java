package pl.bartoszsredzinski.application.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Hello World")
@Route(value = "")
public class MainView extends VerticalLayout{

    public MainView(){
        Button button = new Button("I'm a button");
        add(button, new DatePicker("Pick a date"));

        button.addClickListener(buttonClickEvent -> add(new Text("Clicked")));
    }

}
