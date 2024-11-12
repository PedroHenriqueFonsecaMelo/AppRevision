// Função para buscar dados do CEP via API
async function buscarEnderecoPorCep(cep) {
    const url = `https://viacep.com.br/ws/${cep}/json/`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        // Verifica se o CEP retornou um endereço válido
        if (data.erro) {
            alert('CEP não encontrado!');
            return;
        }

        // Preenche os campos do formulário com os dados obtidos
        document.getElementById('pais').value = 'Brasil';  // País fixo
        document.getElementById('estado').value = data.uf;
        document.getElementById('cidade').value = data.localidade;
        document.getElementById('bairro').value = data.bairro;

        // Remove a palavra 'Rua' do campo "logradouro" (caso presente)
        let rua = data.logradouro;
        rua = rua.replace(/^\s*Rua\s+/i, '');  // Remove a palavra 'Rua' no início, se existir

        document.getElementById('rua').value = rua;
    } catch (error) {
        alert('Erro ao buscar o CEP. Tente novamente mais tarde.');
    }
}

// Evento de "blur" no campo de CEP (quando o usuário sai do campo)
document.getElementById('cep').addEventListener('blur', function () {
    const cep = this.value.replace(/\D/g, '');  // Remove qualquer caractere não numérico

    // Verifica se o CEP é válido (tem 8 dígitos)
    if (cep.length === 8) {
        buscarEnderecoPorCep(cep);
    } else {
        alert('Por favor, insira um CEP válido.');
    }
});
