package cn.swust.xshop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.swust.xshop.base.BaseAction;
import cn.swust.xshop.categorysecond.vo.CategorySecond;
import cn.swust.xshop.product.vo.Product;
import cn.swust.xshop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;


/**
 * 后台商品管理的Action
 * 
 */

@Controller
@Scope("prototype")
public class AdminProductAction extends BaseAction<Product> {
	private Integer page;							// 接收page参数
	
	private File upload;						    // 接受文件上传需要的三个属性:
	private String uploadFileName;
	private String uploadContentType;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	// C : 添加商品UI
	public String addPage() {
		// 查询所有的二级分类:
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将二级分类的数据显示到页面上
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "addUI";
	}

	// C : 添加商品
	public String save() throws IOException {
		model.setPdate(new Date());
		if(upload != null){
			String path = ServletActionContext.getServletContext().getRealPath("/products");  // 创建文件到服务器磁盘中
			File diskFile = new File(path + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);											  
			model.setImage("products/" + uploadFileName);			// 设置product对象的img属性
		}
		productService.save(model);
		return "toList";
	}
	
	// R : 查询所有的商品
	public String findAll() {
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "list";
	}
	
	// U : 修改商品UI
	public String edit() {
		// 根据商品id查询商品信息
		model = productService.findById(model.getPid());
		// 查询所有二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转到编辑页面:
		return "editUI";
	}

	// U : 修改商品
	public String update() throws IOException {
		// 将信息修改到数据库
		model.setPdate(new Date());
		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath("/" + model.getImage());  // 删除服务器中的文件
			File file = new File(delPath);
			file.delete();
			String path = ServletActionContext.getServletContext().getRealPath("/products");  // 创建文件到服务器磁盘中
			File diskFile = new File(path + "//" + uploadFileName);
			FileUtils.copyFile(upload, diskFile);
			model.setImage("products/" + uploadFileName);			// 设置product对象的img属性
		}
		productService.update(model);
		return "toList";
	}
	
	// D : 删除商品
	public String delete() {
		// 根据id查询商品信息
		model = productService.findById(model.getPid());
		String path = ServletActionContext.getServletContext().getRealPath("/" + model.getImage());  // 删除服务器中的文件
		File file = new File(path);
		file.delete();
		productService.delete(model);
		return "toList";
	}
}
