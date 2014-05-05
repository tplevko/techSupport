package cz.muni.fi.tplevko.techsupport.managedbeans.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.faces.context.FacesContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author tplevko
 */
@Component(value = "timeFormat")
//@Scope("request")
public class TimeFormat {

    private String localePattern;

    public String getLocalePattern() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        final DateFormat dateInstance
                = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        final DateFormat timeInstance = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        localePattern = ((SimpleDateFormat) dateInstance).toPattern()
                + " " + ((SimpleDateFormat) timeInstance).toPattern();
        return localePattern;
    }

    public void setLocalePattern(String localePattern) {
        this.localePattern = localePattern;
    }
}
