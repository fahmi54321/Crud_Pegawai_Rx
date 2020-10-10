package com.mobile.pegawai.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.mobile.pegawai.R
import com.mobile.pegawai.adapter.HomeAdapter
import com.mobile.pegawai.model.ResultItem
import com.mobile.pegawai.presenter.delete.DeletePresenter
import com.mobile.pegawai.presenter.delete.DeleteView
import com.mobile.pegawai.presenter.edit.EdirPresenter
import com.mobile.pegawai.presenter.edit.EditView
import com.mobile.pegawai.presenter.home.HomePresenter
import com.mobile.pegawai.presenter.home.HomeView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HomeView, DeleteView {

    //todo 5(selectUser) deklarasi presenter
    lateinit var homePresenter: HomePresenter

    //todo 5(deleteUser) deklarasi presenter
    lateinit var deletePresenter: DeletePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo 6(selectUser) inisialisasi presenter
        homePresenter = HomePresenter(this)

        //todo 6(deleteUser) inisialisasi presenter
        deletePresenter = DeletePresenter(this)

        //todo 8(selectUser) eksekusi presenter
        homePresenter.selectUser()

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    //todo 7(selectUser) implementasi presenter
    override fun onSuccess(message: String, result: List<ResultItem?>?) {
        val adapter = HomeAdapter(result, object : HomeAdapter.onClickListener {
            override fun detail(item: ResultItem?) {
                Toast.makeText(applicationContext, "Click" + item?.id_user, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun hapus(item: ResultItem?) {

                AlertDialog.Builder(this@MainActivity).apply {
                    setTitle("Hapus Data")
                    setMessage("Yakin mau menghapus ?")
                    setPositiveButton("Hapus") { dialog, which ->
                        //todo 8(deleteUser) eksekusi presenter
                        deletePresenter.deleteUser(item?.id_user)
                    }
                    setNegativeButton("Batal") { dialog, which ->
                        dialog.dismiss()
                    }.show()
                }
            }

            override fun edit(item: ResultItem?) {
                val intent = Intent(applicationContext, RegisterActivity::class.java)
                intent.putExtra("data",item)
                startActivity(intent)
            }
        })
        rvItem.adapter = adapter
    }

    override fun onError(message: String) {
        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
    }

    override fun dataKosong() {
    }

    //todo 7(deleteUser) implementasi presenter
    override fun onSuccessDelete(message: String) {
        Toast.makeText(this, "Berhasil Menghapus", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onErrorDelete(message: String) {
        Toast.makeText(this, "Gagal Menghapus", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        homePresenter.selectUser()
    }

}