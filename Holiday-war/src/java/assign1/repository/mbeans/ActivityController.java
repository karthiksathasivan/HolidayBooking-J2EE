/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1.repository.mbeans;

import assign1.repository.entities.Activity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Karth
 */
@Named(value = "activityController")
@RequestScoped
public class ActivityController {

    private String pageTitle;
    private int activityId;
    private Activity activity;

    /**
     * Creates a new instance of ActivityController
     */
    public ActivityController() {
        activityId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("activityID").trim());
        activity = getActivity();
        pageTitle = activity.getActivityName().toUpperCase();
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public Activity getActivity() {
        if (activity == null) {
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            ActivityManagedBean app
                    = (ActivityManagedBean) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "activityManagedBean");
            try {
                return app.getActivityHandler().getAllActivities().get(activityId - 1);
            } catch (Exception ex) {
                Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return activity;
    }

}
