package com.example.papeldoviz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.papeldoviz.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*



class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.fragment_register)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Şifrenin aynı olduğunu kontrol için fonksiyon tanımlıyoruz.

        btn_Kayit.setOnClickListener {
            if(et_Mail.text.isNotEmpty() && et_Sifre.text.isNotEmpty() && et_Sifre_Tekrar.text.isNotEmpty()){
                if (et_Sifre.text.toString().equals(et_Sifre_Tekrar.text.toString())){

                    kullaniciKayit(et_Mail.text.toString(), et_Sifre.text.toString())

                }else{
                    Toast.makeText(requireContext(),"Şifreler aynı değil", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(requireContext(),"Boş alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }
        }

    }



    //Yeni kullanıcı kayıt konrolü için fonksiyon tanımlıyoruz.

    private fun kullaniciKayit(mail: String, sifre: String) {
        progressBarGoster()

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,sifre)
            .addOnCompleteListener(object: OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {

                    if(p0.isSuccessful){
                        Toast.makeText(requireContext(),"Üye kaydedildi:"+ FirebaseAuth.getInstance().currentUser?.email, Toast.LENGTH_SHORT).show()
                        mailGonder()
                        FirebaseAuth.getInstance().signOut()
                    }else{
                        Toast.makeText(requireContext(),"Üye kaydedilirken sorun oluştu:"+p0.exception?.message, Toast.LENGTH_SHORT).show()
                    }




                }

            })
        progressBarGizle()

    }

    //Mail onaylama işlemleri için bildirimleri/onaylama sürecini oluşturuyoruz.

    private fun mailGonder(){

        var kullanici= FirebaseAuth.getInstance().currentUser
        if (kullanici != null){

            kullanici.sendEmailVerification()
                .addOnCompleteListener(object : OnCompleteListener<Void> {
                    override fun onComplete(p0: Task<Void>) {
                        if(p0.isSuccessful){
                            Toast.makeText(requireContext(),"Mailinizi kontrol edin, mailinizi onaylayın", Toast.LENGTH_SHORT).show()

                        }else{

                            Toast.makeText(requireContext(),"Mail gönderilirken sorun oluştu "+p0.exception?.message, Toast.LENGTH_SHORT).show()

                        }
                    }

                })
        }
    }

    private fun progressBarGoster(){
        progressBar.visibility = View.VISIBLE
    }
    private fun progressBarGizle(){
        progressBar.visibility = View.INVISIBLE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
}

