package map.gui;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import org.junit.Assert;
import map.model.Information;
import map.model.Knowable;

public class KnowableListModelTest {

    @Test
    public void gmed() {
        KnowableListModel<Knowable> model = new KnowableListModel<>();
        model.setGmEnabled(true);

        List<Knowable> items = new ArrayList<>();
        // one element known, one unknown
        Information<String> known = Information.newText("title1", null);
        known.setKnown(true);
        items.add(known);
        Information<String> unknown = Information.newText("title2", null);
        items.add(unknown);

        model.setItems(items);

        // all items should be shown
        Assert.assertEquals(items.size(), model.getSize());
        Assert.assertEquals(known, model.getElementAt(0));
        Assert.assertEquals(unknown, model.getElementAt(1));

        // change the title of unknown
        unknown.setTitle("aaa");
        model.onItemChanged(1);
        // now it should have changed order
        Assert.assertEquals(unknown, model.getElementAt(0));
    }

    @Test
    public void pced() {
        KnowableListModel<Knowable> model = new KnowableListModel<>();
        model.setGmEnabled(false);

        List<Knowable> items = new ArrayList<>();
        // one element known, one unknown
        Information<String> known = Information.newText("title1", null);
        known.setKnown(true);
        items.add(known);
        Information<String> unknown = Information.newText("title2", null);
        items.add(unknown);

        model.setItems(items);

        // only one item should be shown
        Assert.assertEquals(1, model.getSize());
        Assert.assertEquals(known, model.getElementAt(0));

        // change known's known field
        known.setKnown(false);
        model.onItemChanged(0);
        // now it should have 0 items
        Assert.assertEquals(0, model.getSize());
    }
    
    @Test
    public void gmChanged(){
        KnowableListModel<Knowable> model = new KnowableListModel<>();
        model.setGmEnabled(false);

        List<Knowable> items = new ArrayList<>();
        // one element known, one unknown
        Information<String> known = Information.newText("title1", null);
        known.setKnown(true);
        items.add(known);
        Information<String> unknown = Information.newText("title2", null);
        items.add(unknown);

        model.setItems(items);
        model.setGmEnabled(true);
        
        Assert.assertEquals(items.size(), model.getSize());
        model.setGmEnabled(false);
        Assert.assertEquals(1, model.getSize());
        Assert.assertEquals(known, model.getElementAt(0));
        
    }
}
