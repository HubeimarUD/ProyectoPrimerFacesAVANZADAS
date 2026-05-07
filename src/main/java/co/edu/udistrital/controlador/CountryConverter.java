package co.edu.udistrital.controlador;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
@FacesConverter(value = "countryConverter", managed = true)
public class CountryConverter implements Converter<Country> {

    @Inject
    private CountryService countryService;

    @Override
    public Country getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return countryService.getCountriesAsMap().get(Integer.parseInt(value));
            }
            catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Country value) {
        if (value != null) {
            return String.valueOf(value.getId());
        }
        else {
            return null;
        }
    }
}