package DAO.IMPLDAO;

import DAO.ILoginDAO;
import Entity.Goods;
import Entity.User;
import Utils.DBClose;
import Utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDao implements ILoginDAO {
    @Override
    public Integer FindUserByNamePass(String name, String password) {
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet res=null;
        int i=0;
        try {
            con= DBUtils.getConnection();
            pre=con.prepareStatement("select count(*) from user where u_username=? and u_password=? and is_delete=1");
            pre.setString(1,name);
            pre.setString(2,password);
            res= pre.executeQuery();
            if(res.next())
            {
                i=res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(res,pre,con);
        }
        return i;
    }

    @Override
    public Integer addNewUser(User user) {
        Connection con=null;
        PreparedStatement pre=null;
        int i=0;
        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("insert into user(u_username,u_password,u_sex,u_hobbies,u_phone,u_email,u_address)"
                    +" values(?,?,?,?,?,?,?)");
            pre.setString(1,user.getUsername());
            pre.setString(2,user.getPassword());
            pre.setString(3,user.getSex());
            pre.setString(4,user.getHobbies());
            pre.setString(5,user.getPhone());
            pre.setString(6,user.getEmail());
            pre.setString(7,user.getAddress());
            i = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(pre,con);
        }
        return i;
    }

    @Override
    public List<Goods> Show() {
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet res=null;
        List<Goods> list=new ArrayList<>();
        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("select * from goods where is_delete=1;");
            res = pre.executeQuery();
            while (res.next())
            {
                int id = res.getInt(1);
                String name = res.getString(2);
                String pic = res.getString(3);
                String price = res.getString(4);
                String desciption = res.getString(5);
                String stock = res.getString(6);
                int is_delete = res.getInt("is_delete");
                list.add(new Goods(id,name,pic,price,desciption,stock,is_delete));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(res,pre,con);
        }
        return list;
    }

    @Override
    public Integer addGoods(Goods goods) {
        Connection con=null;
        PreparedStatement pre=null;
        int i=0;
        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("insert into goods(g_goods_name,g_goods_pic,g_goods_price,g_goods_description,g_goods_stock) " +
                    "values(?,?,?,?,?)");
            pre.setString(1,goods.getName());
            pre.setString(2,goods.getPic());
            pre.setString(3,goods.getPrice());
            pre.setString(4,goods.getDescription());
            pre.setString(5,goods.getStock());
           i = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(pre,con);
        }
        return i;
    }

    @Override
    public Integer modify(Integer id, Goods goods) {
        Connection con=null;
        PreparedStatement pre=null;
        int i=0;

        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("update goods set g_goods_name=?,g_goods_pic=?,g_goods_price=?,g_goods_description=?,g_goods_stock=? " +
                    "where g_id=?");
            pre.setString(1,goods.getName());
            pre.setString(2,goods.getPic());
            pre.setString(3,goods.getPrice());
            pre.setString(4,goods.getDescription());
            pre.setString(5,goods.getStock());
            pre.setInt(6,id);
            i= pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(pre,con);
        }
        return i;
    }

    @Override
    public Goods FindGoodsById(Integer id) {
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet res=null;
        Goods goods=new Goods();
        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("select * from goods where g_id=?");
            pre.setInt(1,id);
            res=pre.executeQuery();
            if(res.next())
            {
                String name = res.getString("g_goods_name");
                String pic = res.getString("g_goods_pic");
                String price = res.getString("g_goods_price");
                String description = res.getString("g_goods_description");
                String stock = res.getString("g_goods_stock");
                goods=new Goods(name,pic,price,description,stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(res,pre,con);
        }
        return goods;
    }

    @Override
    public Integer delete(Integer id) {
        Connection con=null;
        PreparedStatement pre=null;
        int i=0;
        try {
            con=DBUtils.getConnection();
            pre=con.prepareStatement("update goods set is_delete=0 where g_id=?");
            pre.setInt(1,id);
            i = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.closeall(pre,con);
        }
        return i;
    }
}
