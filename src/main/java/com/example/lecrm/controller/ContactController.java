package com.example.lecrm.controller;

import com.example.lecrm.dao.DaoException;
import com.example.lecrm.entity.Client;
import com.example.lecrm.entity.Contact;
import com.example.lecrm.service.BllException;
import com.example.lecrm.service.CrmService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clients/{clientId}")
public class ContactController {

	private final CrmService crmService;

	@Autowired
	public ContactController(CrmService crmService) {
		this.crmService = crmService;
	}

	@GetMapping("/contacts")
	public String findAll(@PathVariable Integer clientId, Model model) throws DaoException {
		Client client = crmService.getClientById(clientId);
		List<Contact> contacts = crmService.getAllContactsOfAClient(client);
		model.addAttribute("client", client);
		model.addAttribute("contacts", contacts);
		return "contact/contacts";
	}

	@GetMapping("client/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) throws DaoException {
		Client client = crmService.getClientById(id);
		model.addAttribute("client", client);
		return "updateClient";
	}

	@PostMapping("client/update/{id}")
	public String updateClient(@PathVariable("id") Integer id, @Valid Client client, BindingResult result,
			Model model) throws BllException {
		client.setIdClient(id);
		if (result.hasErrors()) {
			return "updateClient";
		}
		crmService.updateClient(client);
		return "redirect:/client/index";
	}
	
	@GetMapping("/client/delete/{id}")
	public String deleteClient(@PathVariable("id") Integer id, Model model) throws DaoException {
		crmService.deleteClientById(id);
	    return "redirect:/client/index";
	}

}
