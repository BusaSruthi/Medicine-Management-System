package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Medicines;
import com.example.demo.exception.MedicinesNotFoundException;
import com.example.demo.service.MedicinesService;

@ Controller
public class MedicinesController {
	@Autowired
	private MedicinesService medicineservices;
	
	
	@GetMapping("/medicinesForm")
	public String ViewMedicinesDetailsPage(Model model) {
		model.addAttribute("listMedicines",medicineservices.getAllMedicines());
		return "Medicines";
	}
	
	
	@GetMapping("/showNewMedicinesForm")
	public String showNewMedicinesForm(Model model) {
	Medicines medicines = new Medicines();
	model.addAttribute("medicines",medicines);
	return "new_Medicines";

	}
	@PostMapping("/saveMedicines")
	public String saveMedicines(@ModelAttribute("medicines") Medicines medicines) {
		medicineservices.saveMedicines(medicines);
		return "redirect:/medicinesForm";
	}
	@GetMapping("/showFormForMedicinesUpdate/{medicine_id}")
	public String showFormForUpdate(@PathVariable (value="medicine_id") int medicine_id,Model model) throws MedicinesNotFoundException {
		Medicines medicines = medicineservices.getMedicinesById(medicine_id);
		model.addAttribute("medicines",medicines);
		return "update_Medicines";
	}
	@GetMapping("/deleteMedicines/{medicine_id}")
	public String deleteMedicines(@PathVariable(value = "medicine_id") int medicine_id) {
		this.medicineservices.deleteMedicinesById(medicine_id);
		return "redirect:/medicinesForm";
	}

	



}