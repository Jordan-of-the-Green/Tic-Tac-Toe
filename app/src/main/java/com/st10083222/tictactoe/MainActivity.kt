package com.st10083222.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // player winning count
    var player1Count = 0
    var player2Count = 0

    fun  btReset(view: View) {
        this.recreate();
    }


      fun buClick(view: View) {
        val buSelected = view as Button
        var cellID = 0
        when (buSelected.id) {
            R.id.bu1 -> cellID = 1
            R.id.bu2 -> cellID = 2
            R.id.bu3 -> cellID = 3
            R.id.bu4 -> cellID = 4
            R.id.bu5 -> cellID = 5
            R.id.bu6 -> cellID = 6
            R.id.bu7 -> cellID = 7
            R.id.bu8 -> cellID = 8
            R.id.bu9 -> cellID = 9
        }
        // Toast.makeText(this,"ID:"+ cellID, Toast.LENGTH_LONG).show()

        PlayGame(cellID, buSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var ActivePlayer = 1

    fun PlayGame(cellID: Int, buSelected: Button) {

        if (ActivePlayer == 1) {
            buSelected.text = "X"
            buSelected.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            ActivePlayer = 2
            AutoPlay()
        } else {
            buSelected.text = "O"
            buSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellID)
            ActivePlayer = 1
        }


        buSelected.isEnabled = false
        CheckWinner()
    }

    fun CheckWinner() {
        var winner = -1

        //column1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }

        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //column2
        //1,5,9
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }

        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //3,6,9
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //1,4,7
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //1,5,9
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }

        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2

        }

        //3,5,7
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }

        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2

        }

        ///////////////////////////////////////////
        //ADD CODE TO FIX IT CRASHING WHEN THERES NO MORE BOXES TO CLICK ON

        //Result
        if (winner == 1) {
            // Create the object of AlertDialog Builder class
            val builder = AlertDialog.Builder(this)

            // Set the message show for the restart button
            builder.setMessage("Do you want to restart ?")

            // Set Winner Title
            builder.setTitle("Player 1  wins the game")

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false)

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Yes") {
                // When the user click yes button then app will close
                    dialog, which ->  this.recreate();
            }

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No") {
                // If user click no then dialog box is canceled.
                    dialog, which -> dialog.cancel()
            }
            // Create the Alert dialog
            val alertDialog: AlertDialog = builder.create()
            // Show the Alert Dialog box
            alertDialog.show()
        }
        else if (winner == 2) {
            // Create the object of AlertDialog Builder class
            val builder = AlertDialog.Builder(this)

            // Set the message show for the restart button
            builder.setMessage("Do you want to restart ?")

            // Set Winner Title
            builder.setTitle("Player 2  wins the game")

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false)

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Yes") {
                // When the user click yes button then app will close
                    dialog, which ->  this.recreate();
            }

            // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setNegativeButton("No") {
                // If user click no then dialog box is canceled.
                    dialog, which -> dialog.cancel()
            }
            // Create the Alert dialog
            val alertDialog: AlertDialog = builder.create()
            // Show the Alert Dialog box
            alertDialog.show()
        }


    }

    fun AutoPlay() {
        var emptyCells= ArrayList<Int>()
        for ( cellID in 1..9){
            if(!( player1.contains(cellID) || player2.contains(cellID))) {
                emptyCells.add(cellID)
            }
        }

        var r=Random()
        var randIndex=r.nextInt(emptyCells.size-0)+0

        var cellID= emptyCells[randIndex]
        var buSelect:Button
        when(cellID){
            1-> buSelect = bu1
            2-> buSelect=bu2
            3-> buSelect=bu3
            4-> buSelect=bu4
            5-> buSelect=bu5
            6-> buSelect=bu6
            7-> buSelect=bu7
            8-> buSelect=bu8
            9-> buSelect=bu9
            else-> buSelect=bu1

        }
        PlayGame(cellID, buSelect)
    }
}


