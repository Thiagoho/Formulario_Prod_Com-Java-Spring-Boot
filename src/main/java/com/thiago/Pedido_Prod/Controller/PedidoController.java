package com.thiago.Pedido_Prod.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thiago.Pedido_Prod.model.PedidoForm;

@Controller
public class PedidoController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/formulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute("pedidoForm", new PedidoForm());
		return "formulario";
	}
	
	@PostMapping("/enviarEmail")
	public String enviarEmail(@ModelAttribute PedidoForm pedidoform, Model model) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("");// Adicionar o email da empresa
		message.setSubject("Novo Pedido Recebido");
		message.setText("Nome" + pedidoform.getNomeColaborador() + "\n" +
						"nomeProduto" + pedidoform.getNomeProduto() + "\n" +
						"Matricúla" + pedidoform.getMatricula() + "\n" +
						"Unidade" + pedidoform.getPedido() + "\n" +
						"Peddido" + pedidoform.getPedido());
		
		mailSender.send(message);
		
		model.addAttribute("mensagemSucesso", "Pedido foi adicionado com sucesso");
		
		return "redirect:/formulario";
	}
	
	
}
