package cn.swust.xshop.product.adminaction;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.product.vo.Product;


/**
 * 后台商品管理的Action
 * 
 */

@Controller
@Scope("prototype")
public class AdminProductAction extends BaseAction<Product> {
	
}
