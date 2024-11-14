document.addEventListener("DOMContentLoaded", function () {
  const slidePage = document.querySelector(".slide-page");
  const nextBtns = document.querySelectorAll(".next");  // Todos os botões 'Next'
  const prevBtns = document.querySelectorAll(".prev");  // Todos os botões 'Previous'
  const progressText = document.querySelectorAll(".step p");
  const progressCheck = document.querySelectorAll(".step .check");
  const bullet = document.querySelectorAll(".step .bullet");
  let current = 1;  // Etapa inicial (começando em 1)

  // Função para atualizar o progresso visual
  function updateProgress(isBackward = false) {
    if (isBackward) {
      // Se for voltar, removemos o progresso da etapa anterior
      bullet[current - 1].classList.remove("active");
      progressCheck[current - 1].classList.remove("active");
      progressText[current - 1].classList.remove("active");
    } else {
      // Se avançar, atualizamos o progresso da etapa atual
      bullet[current - 1].classList.add("active");
      progressCheck[current - 1].classList.add("active");
      progressText[current - 1].classList.add("active");
    }
  }

  // Função para mover as páginas com margem de 25%
  function moveSlidePage(direction) {
    slidePage.style.marginLeft = `${-25 * (current - 1)}%`; // Sempre ajusta 25% a menos para a próxima página
  }

  // Função para navegação
  function navigatePage(direction) {
    if (direction === "next" && current < 3) {  // Limite de etapas
      updateProgress();
      current++;  // Avança para a próxima etapa
      moveSlidePage(direction);  // Avança para a próxima página
    } else if (direction === "prev" && current > 1) {  // Garantir que não volte além da primeira etapa
      updateProgress(true);
      current--;  // Retrocede para a etapa anterior
      moveSlidePage(direction);  // Volta para a página anterior
    }
  }

  // Adiciona o evento de clique para os botões "Next"
  nextBtns.forEach(btn => {
    btn.addEventListener("click", function (event) {
      event.preventDefault();
      navigatePage("next");  // Avança
    });
  });

  // Adiciona o evento de clique para os botões "Previous"
  prevBtns.forEach(btn => {
    btn.addEventListener("click", function (event) {
      event.preventDefault();
      navigatePage("prev");  // Volta
    });
  });

  // Lógica para o botão "Submit" (enviar o formulário)
  const submitBtn = document.querySelector(".submit");  // Certifique-se de que esse botão existe no seu HTML
  if (submitBtn) {
    submitBtn.addEventListener("click", function (event) {
      bullet[current - 1].classList.add("active");
      progressCheck[current - 1].classList.add("active");
      progressText[current - 1].classList.add("active");

    });
  }
});
