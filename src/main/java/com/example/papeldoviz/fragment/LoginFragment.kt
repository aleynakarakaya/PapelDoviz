package com.example.papeldoviz.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.papeldoviz.activity.MainActivity
import com.example.papeldoviz.R
import com.example.papeldoviz.activity.ListActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*



class LoginFragment : Fragment() {


    //private lateinit var auth : FirebaseAuth
    lateinit var mAuthStateListener : FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAuthStateListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Kayıt ol butonunun işlevi için fonksiyon tanımlıyoruz.
        tvKayitOl.setOnClickListener{

            requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, RegisterFragment()).addToBackStack(null).commit()

        }
        //Giriş butonunun işlevi için fonksiyon tanımlıyoruz.

        btn_Giris.setOnClickListener {

            if (et_Mail.text.isNotEmpty() && et_Sifre.text.isNotEmpty()){

                progressBarGoster()

                // Firebase kütüphanesinden email ve password parametrelerine tanımladığımız edit textleri Stringe çeviriyoruz.

                FirebaseAuth.getInstance().signInWithEmailAndPassword(et_Mail.text.toString(),et_Sifre.text.toString())

                    .addOnCompleteListener(object: OnCompleteListener<AuthResult> {

                        // Kullanıcı başarılı giriş yaptığında bildirim oluşturuyoruz.

                        override fun onComplete(p0: Task<AuthResult>) {

                            if(p0.isSuccessful){
                                progressBarGizle()
                                //  Toast.makeText(this@LoginActivity,"Başarılı Giriş: "+FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                               //login fragmentından list activity e geçiş
                                activity!!.finish()
                                btn_Giris.setOnClickListener {
                                    val intent = Intent(requireContext(),ListActivity::class.java)
                                    startActivity(intent)
                                }


                            }else{

                                // Kullanıcı hatalı giriş yaptığında bildirim oluşturuyoruz.

                                progressBarGizle()
                                Toast.makeText(requireContext(),"Hatalı Giriş: "+p0.exception?.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    })

            }else{
                // Kullanıcı alanı boş bıraktığında bildirim oluşturuyoruz.

                Toast.makeText(requireContext(),"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }

        }
    }

    //Progress bar/yükleme ikonunu fonksiyonlar ile tanımlıyoruz
    //Buton işlemine bağlı goster/gizle şeklinde iki fonksiyon tanımluyoruz.

   private fun progressBarGoster(){
        progress_Login.visibility = View.VISIBLE
    }
    private fun progressBarGizle(){
        progress_Login.visibility = View.INVISIBLE
    }

    // Mail onaylama işlemlerinin bildirimlerini ve kontrolünü sağlıyoruz.

    private fun initAuthStateListener(){

        mAuthStateListener=object : FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                var kullanici=p0.currentUser

                if (kullanici != null){

                    if(kullanici.isEmailVerified){
                        Toast.makeText(activity,"Mail onaylandı giriş yapabilirsiniz", Toast.LENGTH_SHORT).show()
                        var intent= Intent(getActivity(), ListActivity::class.java)
                        startActivity(intent)
                        activity!!.finish()


                    }else{
                        Toast.makeText(requireContext(),"Mail adresinizi onaylamadan giriş yapamazsınız", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        FirebaseAuth.getInstance().addAuthStateListener(mAuthStateListener)
    }

    override fun onStop() {
        super.onStop()
        FirebaseAuth.getInstance().removeAuthStateListener {mAuthStateListener}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


}



