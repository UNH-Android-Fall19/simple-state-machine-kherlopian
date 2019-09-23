package edu.newhaven.krikorherlopian.android.sandwich

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.anupcowkur.statelin.Machine
import com.anupcowkur.statelin.State
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val stateA = State(name = "SandwichList")
    val stateB = State(name = "Add Sandwich")
    val machine = Machine(stateA)
    var list: MutableList<Sandwich> = mutableListOf<Sandwich>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = SandwichAdapter(
            list
        )
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }


    fun onSubmitClick(view: View) {
        if (view is Button) {
            when (machine.state) {
                stateA -> {
                    machine.state = stateB
                    recyclerView.visibility = View.GONE
                    sandwichNameInputLayout.visibility = View.VISIBLE
                    button.setText(R.string.submit)
                }
                stateB -> {
                    if (!sandwichName.text.toString().equals("")) {
                        if (list.filter { it.name.toUpperCase() == sandwichName.text.toString().toUpperCase() }.size == 0) {
                            list.add(Sandwich(sandwichName.text.toString()))
                            machine.state = stateA
                            recyclerView.visibility = View.VISIBLE
                            sandwichNameInputLayout.visibility = View.GONE
                            sandwichName.setText("")
                            button.setText(R.string.add_sandwich)
                        } else
                            Toast.makeText(
                                this@MainActivity,
                                R.string.sandwich_exists,
                                Toast.LENGTH_LONG
                            ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            R.string.enter_sandwich_name,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}
