package DAO;

import Entity.Goods;
import Entity.User;

import java.util.List;

public interface ILoginDAO {
    Integer FindUserByNamePass(String name, String password);

    Integer addNewUser(User user);

    List<Goods> Show();

    Integer addGoods(Goods goods);

    Integer modify(Integer id,Goods goods);

    Goods FindGoodsById(Integer id);

    Integer delete(Integer id);
}
