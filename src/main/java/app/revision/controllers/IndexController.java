package app.revision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.revision.entidades.usuario.Cliente;
import app.revision.entidades.usuario.Endereco;
import app.revision.repository.EntidadesRepository.UsuarioRepository.ClienteRepository;
import app.revision.repository.EntidadesRepository.UsuarioRepository.EnderecoRepository;

@Controller
@RequestMapping("/home")
public class IndexController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @GetMapping("/greetings")
    public String greeting(@RequestParam(required = false, defaultValue = "World") String name,
            Model model) {

        model.addAttribute("name", name);

        Cliente cli = new Cliente();
        Endereco ende = new Endereco();

        cli.setNome("name");
        cli.setEmail("name");
        cli.setGen("m");
        cli.setDatanasc("name");
        cli.setSenha("name");

        ende.setCliente(cli);

        clienteRepository.save(cli);

        return "greeting";
    }

    @GetMapping("/singup")
    public String sing_up() {

        return "ClienteHTML/SingUp";
    }

}
