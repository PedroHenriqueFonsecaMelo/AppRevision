$(document).ready(function () {
    $("#tabstrip").kendoTabStrip({
        animation: {
            open: {
                effects: "fadeIn"
            }
        }
    });


    var required = $("#Credentials").kendoMultiSelect({ autoClose: false }).data("kendoMultiSelect");
    $("#cartoesGrid").kendoGrid({
    dataSource: {
        transport: {
            read: function (options) {
                options.success([
                    { id: 1, number_cartao: 1234567812345678, bandeira: "Visa", nome_cliente: "João Silva", cliente: 101, cv: 123, preferencial: true },
                    { id: 2, number_cartao: 9876543219876543, bandeira: "Mastercard", nome_cliente: "Maria Oliveira", cliente: 102, cv: 456, preferencial: false },
                    { id: 3, number_cartao: 5555666677778888, bandeira: "Elo", nome_cliente: "Carlos Souza", cliente: 103, cv: 789, preferencial: true },
                    { id: 4, number_cartao: 1111222233334444, bandeira: "American Express", nome_cliente: "Ana Costa", cliente: 104, cv: 234, preferencial: false },
                    { id: 5, number_cartao: 6666777788889999, bandeira: "Hipercard", nome_cliente: "Lucas Pereira", cliente: 105, cv: 567, preferencial: true },
                    { id: 6, number_cartao: 9999888877776666, bandeira: "Visa", nome_cliente: "Fernanda Lima", cliente: 106, cv: 890, preferencial: false }
                ]);
            },
            update: {
                url: function (data) {
                    return "/api/cartoes/" + data.models[0].id;
                },
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                complete: function (e) {
                    console.log("Cartão atualizado com sucesso!", e);
                }
            },
            parameterMap: function (data, type) {
                if (type === "update") {
                    return JSON.stringify(data.models[0]);
                }
            }
        },
        schema: {
            model: {
                id: "id",
                fields: {
                    number_cartao: { type: "number" },
                    bandeira: { type: "string" },
                    nome_cliente: { type: "string" },
                    cliente: { type: "number" },
                    cv: { type: "number" },
                    preferencial: { type: "boolean" }
                }
            }
        },
        pageSize: 5
    },
    pageable: true,
    height: 250,
    toolbar: ["create"],
    columns: [
        { field: "number_cartao", title: "Número do Cartão", width: "170px" },
        { field: "bandeira", title: "Bandeira", width: "120px" },
        { field: "cv", title: "CV", width: "80px" },
        { field: "preferencial", title: "Preferencial", width: "80px", editor: booleanEditor },
        { command: ["edit", "destroy"], title: "&nbsp;", width: "180px" }
    ],
    editable: "inline"
});

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

});
angular.module("KendoDemos", ["kendo.directives"])
    .controller("MyCtrl", function ($scope) {
        $scope.getType = function (x) {
            return typeof x;
        };
        $scope.IntegratedAuth = true;
        $scope.isDate = function (x) {
            return x instanceof Date;
        };
    })
    

///////////////////////////////////////////////////////////////////////////////////

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


    // Configuração do Kendo Grid
    $("#AdressGrid").kendoGrid({
        dataSource: {
            data: [
                { id: 1, cep: "01001-000", pais: "Brasil", estado: "SP", cidade: "São Paulo", rua: "Praça da Sé", bairro: "Centro", numero: 100, complemento: "Apto 101", tipo_residencia: "Apartamento" }
            ],
            schema: {
                model: {
                    id: "id",
                    fields: {
                        cep: { type: "string" },
                        pais: { type: "string" },
                        estado: { type: "string" },
                        cidade: { type: "string" },
                        rua: { type: "string" },
                        bairro: { type: "string" },
                        numero: { type: "number" },
                        complemento: { type: "string" },
                        tipo_residencia: { type: "string" }
                    }
                }
            }
        },
        pageable: true,
        scrollable: {
            virtual: true
        },
        height: 300,
        columns: [
            { field: "cep", title: "CEP", width: "120px" },
            { field: "pais", title: "País", width: "120px" },
            { field: "estado", title: "Estado", width: "120px" },
            { field: "cidade", title: "Cidade", width: "150px" },
            { field: "rua", title: "Rua", width: "200px" },
            { field: "bairro", title: "Bairro", width: "150px" },
            { field: "numero", title: "Número", width: "100px" },
            { field: "complemento", title: "Complemento", width: "180px" },
            { field: "tipo_residencia", title: "Tipo de Residência", width: "150px" },
            { command: [{ name: "edit", text: "Editar" }], title: "&nbsp;", width: "100px" }
        ],
        editable: {
            mode: "popup",
            window: {
                width: "50%" // Largura aumentada para 50%
            }
        },
        save: function (e) {
            var grid = $("#enderecosGrid").data("kendoGrid");
            var item = e.model;

            // Atualiza os dados no dataSource
            grid.dataSource.pushUpdate(item);

            // Fecha o pop-up manualmente após salvar
            $(".k-window").find(".k-grid-cancel").click();
        }
    });
});

/**                    **/

$(document).ready(function () {
    $("#grid").kendoGrid({
        dataSource: {
            type: "odata",
            transport: {
                read: "https://demos.telerik.com/kendo-ui/service/Northwind.svc/Customers"
            },
            pageSize: 20
        },
        height: "100%",
        sortable: true,
        pageable: {
            refresh: true,
            pageSizes: true,
            buttonCount: 5
        },
        columns: [{
            template: "<div class='customer-photo'" +
            "style='background-image: url(../content/web/Customers/#:data.CustomerID#.jpg);'></div>" +
            "<div class='customer-name'>#: ContactName #</div>",
            field: "ContactName",
            title: "Contact Name",
            width: 240
        }, {
            field: "ContactTitle",
            title: "Contact Title"
        }, {
            field: "CompanyName",
            title: "Company Name"
        }, {
            field: "Country",
            width: 150
        }]
    });
});