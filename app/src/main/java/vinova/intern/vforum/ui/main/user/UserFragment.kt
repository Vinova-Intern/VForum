package vinova.intern.vforum.ui.main.user

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_user.*
import vinova.intern.vforum.R
import vinova.intern.vforum.ui.auth.AuthActivity

class UserFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val changePassBtn = view.findViewById<ImageButton>(R.id.img_btn_change_pass)
        val changePass = view.findViewById<TextView>(R.id.txt_change_pass)
        val changeAvatarBtn = view.findViewById<ImageButton>(R.id.img_btn_change_avatar)
        val changeAvatar = view.findViewById<TextView>(R.id.txt_change_avatar)
        val logOutBtn = view.findViewById<ImageButton>(R.id.img_btn_log_out)
        val logOut = view.findViewById<TextView>(R.id.txt_log_out)

        changeAvatarBtn.setOnClickListener(this)
        changeAvatar.setOnClickListener(this)
        changePassBtn.setOnClickListener(this)
        changePass.setOnClickListener(this)
        logOutBtn.setOnClickListener(this)
        logOut.setOnClickListener(this)

        return view
    }

    private fun logOut(){
        val intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // GET URI data
        val imageUri: Uri? = data?.data

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            if (imageUri != null) {
                img_user.setImageURI(imageUri)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openGalleryForImage()
                }
                else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.img_btn_log_out -> logOut()
            R.id.txt_log_out -> logOut()
            R.id.img_btn_change_avatar -> openGalleryForImage()
            R.id.txt_change_avatar -> openGalleryForImage()
        }
    }
}