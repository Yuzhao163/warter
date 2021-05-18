package com.water.water.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class connector {
    public String URL="jdbc:mysql://101.201.101.181:3306/ncut_drain?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone = GMT";
    public String USER="root";
    public String PW="12345678";
    public Connection conn;


//    public connector()
//    {
//        SelectAllTest( ConnectToMySql());
//        InsertTest(conn);
//        SelectedUserById(conn,7);
//        UpdatePasswordByUsername(conn,"Abukuma","123456");
//        DeleteById(conn,10);
//    }

    public Connection ConnectToMySql()
    {
        try {
            //1：加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2：获得数据库连接
            conn=DriverManager.getConnection(URL,USER,PW);
            return conn;
        }catch (Exception e){
            System.out.println("连接数据库时发生异常.异常信息为："+e);
            return null;
        }

    }
//    public void SelectAllTest(Connection conn)
//    {
//        try {
//            Statement st=conn.createStatement();
//            String sqlStr="SELECT * FROM user";
//            ResultSet rs=st.executeQuery(sqlStr);
//
//            while(rs.next())
//            {
//                System.out.println("UserName:"+rs.getString("username"));
//            }
//            rs.close();
//            st.close();
//            //conn.close();
//        }catch (Exception e)
//        {
//
//        }
//
//    }
//    public void InsertTest(Connection conn)
//    {
//        try {
//            String sqlStr="INSERT INTO user(id,username,password) VALUES(?,?,?)";
//            PreparedStatement st=conn.prepareStatement(sqlStr);
//            st.setInt(1,1001);
//            st.setString(2,"Abukuma");
//            st.setString(3,"123abc456");
//            st.executeUpdate();
//
//
//            //rs.close();
//            st.close();
//            //conn.close();
//        }catch (Exception e)
//        {
//
//        }
//
//    }
//
//    public void UpdatePasswordByUsername(Connection conn,String username,String newPassword)
//    {
//        try{
//
//            String updateSqlStr="UPDATE user SET password=? WHERE username=?";
//            PreparedStatement preparedStatement=conn.prepareStatement(updateSqlStr);
//            preparedStatement.setString(1,newPassword);
//            preparedStatement.setString(2,username);
//            preparedStatement.executeUpdate();
//        }catch (Exception e){
//
//        }
//    }
//
//    public void SelectedUserById(Connection conn,int userid)
//    {
//        String sqlstr="SELECT * FROM user WHERE id=?";
//        try {
//            PreparedStatement preparedStatement=conn.prepareStatement(sqlstr);
//            preparedStatement.setInt(1,userid);
//            ResultSet rs=preparedStatement.executeQuery();
//            if(rs.next())
//            {
//                System.out.println(rs.getString("username"));
//            }
//            rs.close();
//        }catch (Exception e){
//
//        }
//
//    }
//
//    public void DeleteById(Connection conn,int id)
//    {
//        try{
//            String deleteSqrStr="DELETE FROM user WHERE id=?";
//            PreparedStatement preparedStatement=conn.prepareStatement(deleteSqrStr);
//            preparedStatement.setInt(1,10);
//            preparedStatement.executeUpdate();
//
//
//        }catch (Exception e){
//
//        }
//    }

}
