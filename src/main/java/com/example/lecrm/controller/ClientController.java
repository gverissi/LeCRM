package com.example.lecrm.controller;

import javax.validation.Valid;

import com.example.lecrm.dao.DaoException;
import com.example.lecrm.entity.Client;
import com.example.lecrm.service.BllException;
import com.example.lecrm.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

	private final CrmService crmService;

	@Autowired
	public ClientController(CrmService crmService) {
		this.crmService = crmService;
	}

	@GetMapping("/client/saisie")
	public String entreSaisie(Client client) {
		client.setNom("le nom");
		return "addClient";
	}

	@PostMapping("/client/add")
	public String addClient(@Valid Client client, BindingResult result, Model model) throws BllException {
		if (result.hasErrors()) {
			return "addClient";
		}
		crmService.createClient(client);
		return "redirect:/client/index"; // n'appelle pas l'html mais l'url

	}

	@GetMapping("/client/index")
	public String listClients(Model model) {
		model.addAttribute("clients", crmService.findAllClients());
		return "indexClient";
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
