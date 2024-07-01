package com.financiera.ecommerceapp.features.login

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.financiera.ecommerceapp.R
import com.financiera.ecommerceapp.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val GOOGLE_SIGN_IN = 100
    private lateinit var prefs: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginGoogleButton.setOnClickListener{

            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(requireActivity(), googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)

        }

        binding.lbLoginOthersButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_storeFragment)
        }

        prefs = requireContext().getSharedPreferences("myPreferences", Context.MODE_PRIVATE).edit()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener{
                        if (it.isSuccessful){
                            val emailUser = account.email
                            val passwordUser = account.idToken
                            val nameUser = account.displayName
                            prefs.putString("email",emailUser)
                            prefs.putString("password",passwordUser)
                            prefs.putString("name",nameUser)
                            prefs.apply()
                            showSuccessDialog(nameUser)
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e: ApiException){
                e.printStackTrace()
            }
        }
    }

    private fun showAlert(){
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage("Se ha producido un error al autenticar el usuario")
            .setPositiveButton("Aceptar",null)
            .create()
        dialog.show()
    }

    private fun showSuccessDialog(name : String?){
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage("Bienvenido $name")
            .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                findNavController().navigate(R.id.action_loginFragment_to_storeFragment)
            })
            .create()
        dialog.show()
    }


}
