const statusElement = document.getElementById('status');
const resetButton = document.getElementById('reset');
const cells = Array.from(document.querySelectorAll('.cell'));

let currentPlayer = 'X';
let board = ['', '', '', '', '', '', '', '', ''];
let gameActive = true;

const winningCombinations = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6],
];

function updateStatus(message) {
  statusElement.textContent = message;
}

function handleCellClick(event) {
  const clickedCell = event.target;
  const clickedIndex = cells.indexOf(clickedCell);

  if (!gameActive || board[clickedIndex] !== '') {
    return;
  }

  board[clickedIndex] = currentPlayer;
  clickedCell.textContent = currentPlayer;

  if (checkWinner()) {
    updateStatus(`Player ${currentPlayer} wins!`);
    gameActive = false;
    return;
  }

  if (board.every(cell => cell !== '')) {
    updateStatus('Draw!');
    gameActive = false;
    return;
  }

  currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
  updateStatus(`Player ${currentPlayer}'s turn`);
}

function checkWinner() {
  return winningCombinations.some(combination => {
    const [a, b, c] = combination;
    return (
      board[a] === currentPlayer &&
      board[b] === currentPlayer &&
      board[c] === currentPlayer
    );
  });
}

function resetGame() {
  currentPlayer = 'X';
  board = ['', '', '', '', '', '', '', '', ''];
  gameActive = true;
  cells.forEach(cell => {
    cell.textContent = '';
  });
  updateStatus(`Player ${currentPlayer}'s turn`);
}

cells.forEach(cell => cell.addEventListener('click', handleCellClick));
resetButton.addEventListener('click', resetGame);
updateStatus(`Player ${currentPlayer}'s turn`);



