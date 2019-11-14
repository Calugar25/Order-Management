package PresentationClasses;

import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

public class TableModel {
    public TableModel() {
    }

    public <T> DefaultTableModel CreateModel(ArrayList<T> objects) {

        ArrayList<String> header =new ArrayList<String>();
        Vector<String> data;
        String value;

        for(Field f:objects.get(0).getClass().getDeclaredFields())
        {
            header.add(f.getName());

        }

        DefaultTableModel tableModel = new DefaultTableModel(header.toArray()    , 0);

        for(Object o:objects)
        {

            data=new Vector<String>();
            for(Field f:o.getClass().getDeclaredFields())
            {
                f.setAccessible(true);
                try{
                    value=f.get(o).toString();

                    data.add(value);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }



            }
            tableModel.addRow(data);

        }



        return tableModel;

    }
}