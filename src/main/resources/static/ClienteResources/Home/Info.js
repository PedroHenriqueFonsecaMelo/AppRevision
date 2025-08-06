$(document).ready(function () {});

// Editor personalizado para booleano
function booleanEditor(container, options) {
    $('<input type="checkbox" name="' + options.field + '"/>')
        .appendTo(container)
        .kendoSwitch({
            messages: {
                checked: "Sim",
                unchecked: "Não"
            }
        });
}
  
$(document).ready(function () {
    // Aumenta a largura do pop-up em 15%
    $(".k-window").css("width", function (index, value) {
        return parseFloat(value) * 1.15 + "px"; // Aumenta 15%
    });

    async function buscarEnderecoPorCep(cep) {
    const url = `https://viacep.com.br/ws/${cep}/json/`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (data.erro) {
            alert('CEP não encontrado!');
            return;
        }

        // Obtém a referência ao modelo da janela de edição do Kendo
        var grid = $("#enderecoGrid").data("kendoGrid");
        var selectedItem = grid.dataItem(grid.select());

        if (selectedItem) {
            selectedItem.set("pais", "Brasil");
            selectedItem.set("estado", data.uf);
            selectedItem.set("cidade", data.localidade);
            selectedItem.set("bairro", data.bairro);

            // Remove "Rua" do logradouro, caso exista
            let rua = data.logradouro.replace(/^\s*Rua\s+/i, '');
            selectedItem.set("rua", rua);
        }

    } catch (error) {
        alert('Erro ao buscar o CEP. Tente novamente mais tarde.');
    }
}


// Adiciona evento de blur para o campo CEP
document.addEventListener('blur', function (event) {
    if (event.target && event.target.name === 'cep') {
        const cep = event.target.value.replace(/\D/g, '');
        if (cep.length === 8) {
            buscarEnderecoPorCep(cep);
        } else {
            alert('Por favor, insira um CEP válido.');
        }
    }
}, true);

});
