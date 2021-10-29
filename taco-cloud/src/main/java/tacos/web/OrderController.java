package tacos.web;

import javax.validation.Valid;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 컴파일시에 SLF4J Logger 객체를 생성할 수 있다.
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("Order submitted: " + order);
		return "redirect:/";
	}
	

}
