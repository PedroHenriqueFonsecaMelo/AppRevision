$(document).ready(function () {
  const container = $("#content-4 > .container");
  const tabela = $("#example");

  // Guarda altura original do container para restaurar depois
  const alturaOriginal = container.height();

  // Espaçamento fixo extra na altura
  const alturaExtra = 40;

  let minhaTabela = null;

  function resetEstilosTabela() {
    container.css({
      all: "unset",
      "box-sizing": "border-box",
      display: "block",
      width: "100%",
      "overflow-x": "auto",
      padding: "10px",
      "background-color": "white",
      "min-height": "250px" // altura mínima para evitar salto
    });

    tabela.css({
      all: "unset",
      boxSizing: "border-box",
      width: "100%",
      borderCollapse: "collapse",
      display: "table",
      fontFamily: "Arial, sans-serif",
      fontSize: "14px",
      tableLayout: "fixed", // FIXA largura das colunas
      minWidth: "600px" // largura mínima para tabela
    });

    tabela.find("thead").css({
      display: "table-header-group",
      "background-color": "#f2f2f2"
    });

    tabela.find("tbody").css({
      display: "table-row-group"
    });

    tabela.find("tr").css({
      display: "table-row"
    });

    tabela.find("th, td").css({
      display: "table-cell",
      border: "1px solid #ddd",
      padding: "8px",
      textAlign: "left",
      color: "#000",
      minWidth: "100px", // largura mínima para as células
      whiteSpace: "nowrap", // impede quebra de texto dentro das células
      overflow: "hidden",
      textOverflow: "ellipsis" // mostra '...' se texto ultrapassar a célula
    });
  }

  function limparEstilosTabela() {
    container.removeAttr("style");
    container.height(alturaOriginal);

    tabela.removeAttr("style");
    tabela.find("thead, tbody, tr, th, td").removeAttr("style");
  }

  $("input[type='radio']").on("change", function () {
    const idAba = $(this).attr("id");
    const num = idAba.split("-")[1];
    const conteudoId = "#content-" + num;

    $(".content .item").hide();
    $(conteudoId).show();

    if (num === "4") {
      resetEstilosTabela();

      // Se DataTable já existe, destrói para evitar acumular bugs
      if (minhaTabela) {
        minhaTabela.destroy();
        minhaTabela = null;
      }

      // Ajusta container para height:auto para medir corretamente depois
      container.height("auto");

      // Inicializa DataTable do zero
      minhaTabela = tabela.DataTable({
        responsive: true,
        paging: true,
        searching: true,
        ordering: true,
        info: true,
        lengthChange: true,
        pageLength: 5,
        autoWidth: false
      });

      // Depois que a tabela estiver pronta, ajusta a altura do container
      // Usando callback 'init' do DataTable para garantir que está tudo renderizado
      minhaTabela.on("init", function () {
        const alturaTabela = tabela.outerHeight(true);
        container.height(alturaTabela + alturaExtra);
        console.log("Altura ajustada para:", alturaTabela + alturaExtra);
      });
    } else {
      limparEstilosTabela();

      if (minhaTabela) {
        minhaTabela.destroy();
        minhaTabela = null;
      }
    }
  });

  // Inicializa na aba 1 para deixar tudo consistente
  $("#tab-1").prop("checked", true).trigger("change");
});
