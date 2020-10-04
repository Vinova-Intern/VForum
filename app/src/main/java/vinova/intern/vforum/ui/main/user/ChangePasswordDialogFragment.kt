package vinova.intern.vforum.ui.main.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import vinova.intern.vforum.R

class ChangePasswordDialogFragment: DialogFragment(), View.OnClickListener {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root: View = inflater.inflate(R.layout.change_password_fragment, container, false)

        val backBtb: ImageButton = root.findViewById(R.id.change_password_back_button)
        backBtb.setOnClickListener(this)

        val confirmPassBtn: Button = root.findViewById(R.id.confirm_password)
        confirmPassBtn.setOnClickListener(this)

        return root
    }

    ////////////////////////////////Go back to UserFragment
    private fun goBackUserFragment(){}

    ////////////////////////////////////Go back to UserFragment + change password
    private fun confirmChangePassword(){}

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.change_password_back_button -> goBackUserFragment()
            R.id.confirm_password -> confirmChangePassword()
        }
    }
}