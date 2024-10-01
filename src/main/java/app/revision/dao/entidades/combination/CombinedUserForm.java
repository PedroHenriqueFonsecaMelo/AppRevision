package app.revision.dao.entidades.combination;

import app.revision.dao.entidades.usuario.Cliente;
import app.revision.dao.entidades.usuario.Endereco;

public class CombinedUserForm {
    public Cliente user;
    public Endereco endereco;

    public CombinedUserForm(Endereco endereco, Cliente user) {
        this.endereco = endereco;
        this.user = user;
    }

    public CombinedUserForm() {
    }

    public Cliente getUser() {
        return user;
    }

    public void setUser(Cliente user) {
        this.user = user;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
