package map.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Both vertices and edges in our map have several common fields. We'll put them
 * here, to avoid duplicated code
 * 
 * @author dtortola
 *
 */
public abstract class MapItem extends Knowable {
    private final List<Information<?>> information = new ArrayList<>();

    public List<Information<?>> getInformation() {
        return information;
    }
}
