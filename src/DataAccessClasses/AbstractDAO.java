
package DataAccessClasses;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {

    protected static final Logger LOGGER=Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }



    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }catch(NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public List<T> findAll()
    {
        Connection connection=null;
         PreparedStatement statement=null;
          ResultSet resultSet=null;
        String query="SELECT * FROM schooldb."+type.getSimpleName();
        System.out.println(query);
        try
        {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);

              resultSet=statement.executeQuery();

            return createObjects(resultSet);

        }catch(SQLException e)
        {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }




   public void addObject(T instance) throws SQLException, SecurityException, IllegalArgumentException,
           InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException {

       Connection connection = null;
       PreparedStatement statement = null;
       String query="INSERT INTO schooldb."+type.getSimpleName()+" VALUES (?, ?, ?, ?)";
       System.out.println(query);
       try {

          connection=ConnectionFactory.getConnection();
          statement=connection.prepareStatement(query);

          int i=0;

          for(Field f:type.getDeclaredFields())
          {

              PropertyDescriptor propertyDescriptor=new PropertyDescriptor(f.getName(),type);

              Method method=propertyDescriptor.getReadMethod();

              Object object=method.invoke(instance);

              statement.setObject(++i,object);



          }


           statement.addBatch();
            statement.executeBatch();

       } finally {
           ConnectionFactory.close(statement);
           ConnectionFactory.close(connection);
       }


   }



    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

   public void deleteObject(int id)throws SQLException
   {
       Connection connection=null;
       PreparedStatement statement=null;
       try
       {
           connection= ConnectionFactory.getConnection();
           String query="DELETE FROM schooldb."+type.getSimpleName()+" WHERE id = ?";
           System.out.println(query);
           statement=connection.prepareStatement(query);
           statement.setString(1,Integer.toString(id));
           statement.executeUpdate();

       }catch (Exception e)
       {
           e.printStackTrace();
       }
       finally{
           ConnectionFactory.close(statement);
           ConnectionFactory.close(connection);
       }

   }


    public void test()
    {
        for(Field f:type.getDeclaredFields())
        {
            System.out.println(f.getName());
        }
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }



    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }



    public String UpdateHelper(int id,T instance) throws SQLException, SecurityException, IllegalArgumentException,
    InstantiationException, IllegalAccessException, IntrospectionException, InvocationTargetException
    {
        String s="";
        int i=2;
        for(Field f:type.getDeclaredFields()) {
            s = s + f.getName();
            s = s + "=";
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            Object object = method.invoke(instance);
            if (isNumeric(object.toString())) {
                s = s + object;
            } else {
                s = s + "'";
                s = s + object;
                s = s + "'";
            }
            s = s + ",";
        }
        return s.substring(0,s.length()-1) ;

    }




    public void updateObject(int id,T instance)
    {
        Connection connection=null;
        PreparedStatement statement=null;

        try{
            String query="UPDATE "+type.getSimpleName()+" SET "+UpdateHelper(id,instance)+" WHERE id="+id;
            System.out.println(query);
            connection= ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);

            statement.executeUpdate();

            //System.out.println(UpdateHelper(id,instance));

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            ConnectionFactory.close(connection);
            ConnectionFactory.close(statement);
        }
    }









}
