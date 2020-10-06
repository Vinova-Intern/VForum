package vinova.intern.vforum.ui.main.user

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_user.*
import vinova.intern.vforum.R
import vinova.intern.vforum.ui.auth.AuthActivity
import vinova.intern.vforum.utils.SaveSharedPreference
import java.io.*

class UserFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
//        private val mDocRef: DocumentReference = FirebaseFirestore.getInstance().document("User/UserAvatar")
//        private const val USER_AVATAR = "User_Avatar"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        val changeAvatarBtn = view.findViewById<ImageButton>(R.id.img_btn_change_avatar)
        val changeAvatar = view.findViewById<TextView>(R.id.txt_change_avatar)
        val changePassBtn = view.findViewById<ImageButton>(R.id.img_btn_change_pass)
        val changePass = view.findViewById<TextView>(R.id.txt_change_pass)
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
        SaveSharedPreference().setLoggedIn(activity?.applicationContext!!, false)
        val intent = Intent(activity, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun changePassword(){
//        val fm = activity?.supportFragmentManager
//        val filterFragment = ChangePasswordDialogFragment()
//        if (fm != null) {
//            filterFragment.show(fm, "fragment_edit_name")
//        }

        if (findNavController().currentDestination?.id == R.id.userFragment) {
            findNavController().navigate(R.id.user_to_reset_pw_action)
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGalleryForImage()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.img_btn_change_avatar -> openGalleryForImage()
            R.id.txt_change_avatar -> openGalleryForImage()
            R.id.img_btn_change_pass -> changePassword()
            R.id.txt_change_pass -> changePassword()
            R.id.img_btn_log_out -> logOut()
            R.id.txt_log_out -> logOut()
        }
    }

//    fun saveImage(uriStr: String?){
//        val dataToSave: HashMap<String, String> = HashMap()
//        if (uriStr != null) {
//            dataToSave[USER_AVATAR] = uriStr
//        }
//        mDocRef.set(dataToSave).addOnSuccessListener {
//            Log.d("FirebaseManager", "Upload Successful")
//        }
//        mDocRef.set(dataToSave).addOnFailureListener() {
//            Log.d("FirebaseManager", "Document was not saved")
//        }
//    }
//
//    private fun createFileFromInputStream(inputStream: InputStream, fileName: String): File? {
//        try {
//            val f = File(fileName)
//            f.setWritable(true, false)
//            val outputStream: OutputStream = FileOutputStream(f)
//            val buffer = ByteArray(1024)
//            var length = 0
//            while (inputStream.read(buffer).also { length = it } > 0) {
//                outputStream.write(buffer, 0, length)
//            }
//            outputStream.close()
//            inputStream.close()
//            return f
//        } catch (e: IOException) {
//            println("error in creating a file")
//            e.printStackTrace()
//        }
//        return null
//    }
}