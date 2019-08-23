package DAO;

import Entity.Goods;
import Entity.User;

import java.util.List;

public interface ILoginDAO {

    //登录
    Integer FindUserByNamePass(String name, String password);
    //注册
    Integer addNewUser(User user);
    //展示全部商品
    List<Goods> Show();
    //添加商品
    Integer addGoods(Goods goods);
    //修改商品信息
    Integer modify(Integer id,Goods goods);
    //修改页面回显商品信息
    Goods FindGoodsById(Integer id);
    //删除商品（伪删除）
    Integer delete(Integer id);
    //根据商品名搜索
    List<Goods> ShowAsName(String name);
    //根据价格区间搜索
    List<Goods> ShowAsPrice(String min,String max);
    //根据剩余库存搜索
    List<Goods>ShowAsStock(String min,String max);
}
