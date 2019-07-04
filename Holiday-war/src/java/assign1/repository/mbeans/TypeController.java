/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.mbeans;

import assign1.repository.entities.ActivityType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Karth
 */
@Named(value="typeController")
@FacesConverter(forClass=ActivityType.class)
public class TypeController implements Converter{
     
    @Override
    public ActivityType getAsObject(FacesContext context, UIComponent component, String value) {
        
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            ELContext elc
                    = FacesContext.getCurrentInstance().getELContext();
            ActivityManagedBean app = (ActivityManagedBean) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(elc, null, "activityManagedBean");
            for (ActivityType type : app.getActivityHandler().getAllActivityType()) {
            if (type.getTypeName().equalsIgnoreCase(value)) {               
                return type;
            }
        }

        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s Type", value)), e);
        } catch (Exception ex) {   
            Logger.getLogger(TypeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       return (value instanceof ActivityType) ? ((ActivityType) value).getTypeName() : null;
    }
}
   
   
