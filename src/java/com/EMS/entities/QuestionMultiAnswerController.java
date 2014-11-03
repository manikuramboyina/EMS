package com.EMS.entities;

import com.EMS.entities.util.JsfUtil;
import com.EMS.entities.util.JsfUtil.PersistAction;
import com.EMS.enums.QuestionTypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@Named("questionMultiAnswerController")
@RequestScoped
public class QuestionMultiAnswerController implements Serializable {

    @EJB
    private com.EMS.entities.QuestionMultiAnswerFacade ejbFacade;
    private List<QuestionMultiAnswer> items = null;
    private QuestionMultiAnswer selected;
    @Inject Question genricQuestion;

    public QuestionMultiAnswerController() {
    }
    @PostConstruct
    public void init()
    {
        System.out.print("here");
        System.out.print(genricQuestion.getText());
    }
    
    public void removeChoice(int index)
    {
        List<String> choices = selected.getChoices();
        choices.remove(index);
        
    }
    public void addChoice()
    {
        
        System.out.println("add choice");
        System.out.print(genricQuestion.getText());
      
        List<String> choices = selected.getChoices();
        if(choices==null)
        {
             System.out.println("choice null");
        }
        else {
            System.out.println("choice empty");
        }
        choices.add("Hello");
        
    }

    public QuestionMultiAnswer getSelected() {
        return selected;
    }

    public void setSelected(QuestionMultiAnswer selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private QuestionMultiAnswerFacade getFacade() {
        return ejbFacade;
    }

    public QuestionMultiAnswer prepareCreate() {
        selected = new QuestionMultiAnswer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/MultiAnswerBundle").getString("QuestionMultiAnswerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/MultiAnswerBundle").getString("QuestionMultiAnswerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/MultiAnswerBundle").getString("QuestionMultiAnswerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<QuestionMultiAnswer> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if(persistAction == PersistAction.CREATE)
                {
                    selected.setTypeOfQuestion(QuestionTypes.MULTI_ANSWER);
                    getFacade().create(selected);
                }
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MultiAnswerBundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/MultiAnswerBundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public QuestionMultiAnswer getQuestionMultiAnswer(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<QuestionMultiAnswer> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<QuestionMultiAnswer> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = QuestionMultiAnswer.class)
    public static class QuestionMultiAnswerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            QuestionMultiAnswerController controller = (QuestionMultiAnswerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "questionMultiAnswerController");
            return controller.getQuestionMultiAnswer(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof QuestionMultiAnswer) {
                QuestionMultiAnswer o = (QuestionMultiAnswer) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), QuestionMultiAnswer.class.getName()});
                return null;
            }
        }

    }

}