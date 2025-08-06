package app.revision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.revision.dao.entidades.combination.CombinedUserForm;
import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.entidades.usuario.Endereco;
import app.revision.dao.repository.usuario.ClienteRepository;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/greetings")
    public String greeting(@RequestParam(required = false, defaultValue = "World") String name,
            Model model) {

        model.addAttribute("name", name);

        Cliente cli = new Cliente();
        cli.setNome("name");
        cli.setEmail("name");
        cli.setGen("m");
        cli.setDatanasc("name");
        cli.setSenha("name");
        clienteRepository.save(cli);

        return "greeting";
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute CombinedUserForm combinedForm, Model model) {

        Cliente user = combinedForm.getUser();
        Endereco ender = combinedForm.getEndereco();

        // Atribuir objetos à pagina
        model.addAttribute("user", user);
        model.addAttribute("endereco", ender);
        return "ClienteHTML/result";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("CombinedUserForm", new CombinedUserForm());
        return "ClienteHTML/form";
    }

}
