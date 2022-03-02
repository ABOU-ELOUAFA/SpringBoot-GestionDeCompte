package pack.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pack.com.entities.Compte;
import pack.com.entities.Operation;
import pack.com.métier.IMetier;

@Controller
public class BanqueController {

	@Autowired
	private IMetier iMetier;

	@RequestMapping("/index")
	public String index() {

		return "comptes";

	}

	@GetMapping("/consultercompte")

	public String consulter(Model model, Integer codecompte, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codecompte", codecompte);
		try {
			Compte cpCompte = iMetier.consulterCompte(codecompte);
			model.addAttribute("compte", cpCompte);

			Page<Operation> pagee = iMetier.consulteerOperatio(codecompte, page, size);
			int[] pages = new int[pagee.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("listoperations", pagee.getContent());

		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return "comptes";
	}

	@PostMapping("/saveoperation")

	String saveoperation(Model model, String typeoperation, Integer codecompte1, double montant, Integer numcompte2) {
		try {
			if (typeoperation.equals("VE")) {
				iMetier.verser(codecompte1, montant);
			} else if (typeoperation.equals("RE")) {
				iMetier.retirer(codecompte1, montant);
			}
			if (typeoperation.equals("VI")) {
				iMetier.virement(codecompte1, numcompte2, montant);
			}

		} catch (Exception e) {

			model.addAttribute("error", e);
			return ("redirect:/consultercompte?codecompte=" + Integer.toString(codecompte1))
					+ ("&error=" + e.getMessage());
		}

		return "redirect:/consultercompte?codecompte=" + Integer.toString(codecompte1);
	}

}
