package cn.swust.xshop.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ActionTest extends ActionSupport {
	public String execute() throws Exception {
		return "success";
	}
}
