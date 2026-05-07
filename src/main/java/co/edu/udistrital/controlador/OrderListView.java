package co.edu.udistrital.controlador;


import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class OrderListView {

    @Inject
    private CountryService service;

    private List<String> cities;
    private List<Country> countries;

    @PostConstruct
    public void init() {
        //Cities
        cities = new ArrayList<>();
        cities.add("San Francisco");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");

        //Countrys
        countries = service.getCountries().subList(0, 10);
    }

    public CountryService getService() {
        return service;
    }

    public void setService(CountryService service) {
        this.service = service;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void onSelect(SelectEvent<Country> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().getName()));
    }

    public void onUnselect(UnselectEvent<Country> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().getName()));
    }

    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
}