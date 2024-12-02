package com.green.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentPlayer = "X"
    private var board = Array(3) { arrayOfNulls<String>(3) }
    private var playerXScore = 0
    private var playerOScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resetButton = findViewById<Button>(R.id.resetButton)
        resetButton.setOnClickListener {
            resetBoard()
        }

        // Set up click listeners for all buttons
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = resources.getIdentifier("btn${i * 3 + j + 1}", "id", packageName)
                val button = findViewById<Button>(buttonId)
                button.setOnClickListener {
                    handleButtonClick(button, i, j)
                }
            }
        }
    }

    private fun handleButtonClick(button: Button, row: Int, col: Int) {
        if (board[row][col] == null) {
            board[row][col] = currentPlayer
            button.text = currentPlayer

            if (checkWinner()) {
                if (currentPlayer == "X") playerXScore++ else playerOScore++
                updateScoreboard()
                Toast.makeText(this, "Player $currentPlayer wins!", Toast.LENGTH_SHORT).show()
                resetBoard()
            } else if (isBoardFull()) {
                Toast.makeText(this, "It's a draw!", Toast.LENGTH_SHORT).show()
                resetBoard()
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    private fun checkWinner(): Boolean {
        // Check rows, columns, and diagonals for a win
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true

        return false
    }

    private fun isBoardFull(): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == null) return false
            }
        }
        return true
    }

    private fun resetBoard() {
        board = Array(3) { arrayOfNulls<String>(3) }
        currentPlayer = "X"
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonId = resources.getIdentifier("btn${i * 3 + j + 1}", "id", packageName)
                val button = findViewById<Button>(buttonId)
                button.text = ""
            }
        }
    }

    private fun updateScoreboard() {
        findViewById<TextView>(R.id.playerXScore).text = "Player X: $playerXScore"
        findViewById<TextView>(R.id.playerOScore).text = "Player O: $playerOScore"
    }
}
