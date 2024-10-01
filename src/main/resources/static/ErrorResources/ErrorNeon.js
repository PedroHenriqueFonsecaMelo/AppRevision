/*sCodePen Home Neon text flicker glow Johan Girod*/
const target = window.document.getElementsByTagName('h1')[0]

const flickerLetter = letter => `<span style="animation: text-flicker-in-glow ${Math.random() * 4}s linear both ">${letter}</span>`
const colorLetter = letter => `<span style="color:rgba(255, 255, 255, ${Math.random() * 1});">${letter}</span>`;
const flickerAndColorText = text =>
    text
        .split('')
        .map(flickerLetter)
        .map(colorLetter)
        .join('');

function neonGlory(target) {
    target.innerHTML = flickerAndColorText(target.textContent);
}
neonGlory(target);
target.onclick = ({ target }) => neonGlory(target);