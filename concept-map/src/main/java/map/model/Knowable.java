package map.model;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

public class Knowable {

    private String title;
    private boolean known;

    /**
     * comparator based on alphabetical order of title (case-insensitive,
     * null-protected)
     */
    public static final Comparator<Knowable> ALPHABETIC = (k1, k2) -> {
        if (k1 == null || StringUtils.isBlank(k1.title)) {
            if (k2 == null || StringUtils.isBlank(k2.title)) {
                return 0;
            } else {
                return -1;
            }
        }
        // k1.title is not blank, k2 may be
        if (k2 == null || StringUtils.isBlank(k2.title)) {
            return 1;
        }
        // none blank
        return k1.title.compareToIgnoreCase(k2.title);
    };

    public Knowable() {
        // sin parámetros
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    /**
     * @return true if the existance of this item is known by the pcs
     */
    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

}