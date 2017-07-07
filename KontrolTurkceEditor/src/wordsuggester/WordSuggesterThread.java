
package wordsuggester;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import kontrolturkceeditor.connection;
import kontrolturkceeditor.editor;

/**
 *
 * @author Hasan Alp Zengin
 */
public class WordSuggesterThread extends Thread {
    
    String input;
    
    public WordSuggesterThread(String input){
        this.input=input;
    }
    
    @Override
    public void run(){
        editor gui = new editor();
        DefaultListModel model = (DefaultListModel) gui.suggestList.getModel();
        model.clear();
        try {
            //create connection with connection class
            Connection con = new connection().connect;
            Statement stm = (Statement) con.createStatement();
            //result set with static queryPrepare function which is return a query string
            ResultSet rs = (ResultSet) stm.executeQuery(queryPrepare(input));
            while(rs.next()){
                model.addElement(rs.getString("kelime"));
                System.out.println(rs.getString("kelime"));
            }
            con.close();
            stm.close();
            rs.close();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WordSuggesterThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        gui.setList(model);
    }
    
    @Override
    public void start(){
        Thread t = new Thread(this);
        t.start();
    }
    
    public String queryPrepare(String text){
        String query = "SELECT * FROM kelime WHERE kelime LIKE '%"+text+"%'";
        return query;
    }
    
}
