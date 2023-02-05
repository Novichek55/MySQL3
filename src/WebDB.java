import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class WebDB extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataBaseName=req.getParameter("dataBaseName");
        String userName=req.getParameter("user");
        String password=req.getParameter("password");
        List<String> list;
        DBConnection con=new DBConnection(dataBaseName, userName, password);
        Connection connection=con.connection;

        PreparedStatement preparedStatement;

        try {
           preparedStatement=connection.prepareStatement("SELECT * FROM mydbtest.students");
           ResultSet resultSet= preparedStatement.executeQuery();
           String id;
           String name;
           String last_name;
           list=new ArrayList<>();
           while(resultSet.next()){
              id= String.valueOf(resultSet.getInt("id"));
              name=resultSet.getString("name");
              last_name=resultSet.getString("last_name");
              list.add(id+" " + name + " " + last_name);
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter printWriter=resp.getWriter();
        for (int i=0; i<list.size(); i++){
            printWriter.println(list.get(i));
        }

        try {
            con.connectionClose();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

