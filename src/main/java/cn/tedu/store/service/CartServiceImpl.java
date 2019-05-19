package cn.tedu.store.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.store.bean.Cart;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.DataNotFoundException;
import cn.tedu.store.service.ex.ServiceException;

@Service("cartService")
public class CartServiceImpl implements ICartService {

	@Resource(name="cartMapper")
	private CartMapper cartMapper;
	
	public Integer add(Cart cart) {
		// 先查詢有沒有對應的數據
		Integer count = getRecordCount(cart.getUid(), cart.getGoodsId());
		// 判斷該用戶的購物車中是否已經有該商品
		if(count == 0) {
			// 該用戶的購物車中尚無該商品, 則在購物車中添加新數據
			Integer affectedRows = cartMapper.add(cart);
			if(affectedRows == 1) {
				return 1;
			} else {
				throw new ServiceException("將商品添加到購物車時出現問未知錯誤, 請聯繫管理員");
			}
			
		} else {
			// 該用戶購物車中已有該商品, 則增加數量即可
			changeGoodsCount(cart.getUid(), cart.getGoodsId(), cart.getGoodsCount());
			return 1;
		}
		
	}
	
	public Integer remove(Integer uid, Integer id) {
		Integer affectedRows = cartMapper.delete(uid, id);
		if(affectedRows != 1) {
			throw new DataNotFoundException("查無此商品數據, 可能已提前遭刪除");
		}
		
		return affectedRows;
	}
	
	public List<Cart> getCartList(Integer uid) {
		return cartMapper.getCartList(uid);
	}
	
	public Cart getCartById(Integer uid, Integer id) {
		return cartMapper.getCartById(uid, id);
	}

	public Integer getRecordCount(Integer uid, Integer goodsId) {
		return cartMapper.getRecordCount(uid, goodsId);
	}

	public Integer changeGoodsCount(Integer uid, Integer goodsId, Integer amount) {
		Integer affectedRows = cartMapper.changeGoodsCount(uid, goodsId, amount);
		if(affectedRows != 1) {
			throw new DataNotFoundException("購物車數據有誤!請刷新後再次嘗試!");
		}
		
		return affectedRows;
	}

}
