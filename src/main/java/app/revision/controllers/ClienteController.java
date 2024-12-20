package app.revision.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.revision.dao.entidades.combination.ClienteFormData;
import app.revision.dao.entidades.usuario.Cartao;
import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.entidades.usuario.Endereco;
import app.revision.dao.repository.usuario.CartaoRepository;
import app.revision.dao.repository.usuario.ClienteRepository;
import app.revision.dao.repository.usuario.EnderecoRepository;

@Controller
@RequestMapping("/usu")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository; // Assumindo que você tem um repositório para o Cliente

    @Autowired
    private EnderecoRepository enderecoRepository; // Repositório de Endereço

    @Autowired
    private CartaoRepository cartaoRepository; // Repositório de Cartão

    @GetMapping("/signup")
    public String showForm(Model model) {

        ClienteFormData formData = new ClienteFormData();

        model.addAttribute("formData", formData);

        return "ClienteHTML/SingUp";
    }

    @GetMapping("/home")
    public String UserHome(Model model) {

        return "ClienteHTML/UserHome";
    }

    @PostMapping("/submitCliForm")
    public ResponseEntity<String> submitForm(@ModelAttribute ClienteFormData formData) {

        // Cria Cliente
        Cliente cliente = new Cliente(
                formData.getNome(),
                formData.getSenha(),
                formData.getDatanasc(),
                formData.getGen(),
                formData.getEmail());

        // Salvar Cliente no banco de dados
        cliente = clienteRepository.save(cliente); // Salva o cliente e pega a entidade com ID gerado

        // Cria Endereco
        Endereco endereco = new Endereco(
                cliente, // Associa o cliente ao endereço
                formData.getPais(),
                formData.getCep(),
                formData.getEstado(),
                formData.getCidade(),
                formData.getBairro(),
                formData.getRua(),
                formData.getNumero(),
                formData.getComplemento(),
                formData.getTipo_residencia());

        // Salvar Endereço no banco de dados
        enderecoRepository.save(endereco); // Salva o endereço

        // Cria Cartão
        Cartao cartao = new Cartao(
                cliente, // Associa o cliente ao cartão
                formData.getNumber_cartao(),
                formData.getBandeira(),
                formData.getCv(),
                formData.isPreferencial());

        // Salvar Cartão no banco de dados
        cartaoRepository.save(cartao); // Salva o cartão

        return ResponseEntity.ok("Formulário enviado com sucesso!");
    }
}
