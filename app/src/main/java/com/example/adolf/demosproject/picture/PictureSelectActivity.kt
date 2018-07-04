package com.example.adolf.demosproject.picture

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.adolf.demosproject.R
import kotlinx.android.synthetic.main.activity_picture_select.*
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.provider.MediaStore
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.io.File.separator
import android.graphics.Bitmap
import android.net.Uri
import java.io.File


class PictureSelectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_select)
        mOpenBtn.setOnClickListener { openPhoto3() }
    }
    private fun openPhoto(){
        val intent = Intent(Intent.ACTION_PICK, null)
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_UNSPECIFIED)

        // 调用剪切功能
        startActivityForResult(intent, PHOTOZOOM)
    }

    private fun openPhoto2(){
        val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    private fun openPhoto3(){
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT// 打开图库获取图片
        intent.action = Intent.ACTION_PICK// 打开图库获取图片
        intent.type = "image/*"// 这个参数是确定要选择的内容为图片
        intent.putExtra("return-data", true)// 是否要返回，如果设置false取到的值就是空值
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        else {
            when (requestCode) {
                IMAGE_REQUEST_CODE -> resizeImage(data!!.data)

                RESIZE_REQUEST_CODE -> if (data != null) {
                    showResizeImage(data)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //这里增加裁剪
    fun resizeImage(uri: Uri?) {
        val intent = Intent("com.android.camera.action.CROP")
        intent.setDataAndType(uri, "image/*")
        intent.putExtra("crop", "true")
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        //裁剪的大小
        intent.putExtra("outputX", 150)
        intent.putExtra("outputY", 150)
        intent.putExtra("return-data", true)
        //设置返回码
        startActivityForResult(intent, RESIZE_REQUEST_CODE)
    }

    private fun showResizeImage(data: Intent) {
        val extras = data.extras
        if (extras != null) {
            val photo = extras.getParcelable<Bitmap>("data")
            //裁剪之后设置保存图片的路径
            val path = filesDir.path + File.separator + "feae"
            val drawable = BitmapDrawable(resources,photo)
            mImage.setImageDrawable(drawable)
        }
    }

    companion object {
        const val IMAGE_UNSPECIFIED = "image/*"//随意图片类型
        const val PHOTOZOOM = 2//随意图片类型
        const val IMAGE_REQUEST_CODE = 100
        const val RESIZE_REQUEST_CODE = 101
    }
}
