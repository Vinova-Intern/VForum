package vinova.intern.vforum.ui.main.user

import android.R
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment

class ChangePasswordDialogFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.change, container, false)

//        val datePick: ImageButton = root.findViewById(R.id.icon_date)
//        datePick.setOnClickListener(this)
//
//        val saveButton: Button = root.findViewById(R.id.save_button)
//        saveButton.setOnClickListener(this)

        return root
    }
}