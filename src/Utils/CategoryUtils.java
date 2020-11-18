package Utils;

import Model.Category;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionModel;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.List;

public class CategoryUtils {
    public static Category accessLastCategory(List<Category> categories) {
        return categories.get(categories.size()-1);
    }

    public static int getSelectedCategoryId(Object selectedItem) {

        if (selectedItem != null)
        {
            String[] categoryData = selectedItem.toString().split(": ");

            return Integer.parseInt(categoryData[0]);
        }
        return -1;
    }


}
