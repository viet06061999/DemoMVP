package com.sun_aterisk.demomvp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sun_aterisk.demomvp.R
import com.sun_aterisk.demomvp.di.Injection
import com.sun_aterisk.demomvp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override var presenter: LoginContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginPresenter(Injection.providePostsRepository(baseContext),this)
        btnLogin.setOnClickListener {
            presenter?.login(edtEmail.text.toString(),edtUsername.text.toString())
        }
    }

    override fun showMsg(message: String) {
       Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun goHome() {
        startActivity(Intent(this,MainActivity::class.java))
    }

}
