package com.omerglr.naviapp.ui.profilim

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Network
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import java.io.File
import retrofit2.converter.gson.GsonConverterFactory





class FotograflarimFragment : Fragment() {

    lateinit var tempFile: File;
    lateinit var mPath: String;
    lateinit var disposable: Disposable;

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


    private fun getPathFromUri(contentUri: Uri): String? {

        var filePath: String? = null
        var cursor = requireActivity().contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            filePath = contentUri.path
        } else {
            cursor.moveToFirst()
            var index = cursor.getColumnIndex("_data")
            filePath = cursor.getString(index)
            cursor.close()
        }
        return filePath
    }




    private lateinit var memoryCache: LruCache<String, Bitmap>
    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                //result.resultCode == Activity.RESULT_OK
                // There are no request codes
                val data: Intent? = result.data
                if (data != null) {
                    var selectedImageUrl = data.data
                    if (selectedImageUrl != null) {
                        try {
                            var inputStream =
                                requireActivity().contentResolver.openInputStream(selectedImageUrl)
                            val selectedImagePath = getPathFromUri(selectedImageUrl)!!
                            var bitmap = BitmapFactory.decodeStream(inputStream);

                            // yukarıdaki bitmap'i cache klasöründe yeni bir dosya oluşturarak
                            // orada sıkıştırman gerekiyor. Sonra aşağıda file.name ve file.requestBod...
                            // satırlarının olduğu yerde o file'ı kullanacaksın.
                            // böylece sunucu response olarak bize, dosya boyutu 2 mb'tan kuüçük olmalıdır hatası
                            // vermeyecek.
                            val file: File = File(selectedImagePath)


                            val imagePart = MultipartBody.Part.createFormData(
                                name = "image",
                                filename = file.name,
                                body = file.asRequestBody("image/*".toMediaType())
                            )

                            disposable = ServiceBuilder.buildService(requireActivity())
                                .sendUploadPhotoRequest(imagePart)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    { t ->
                                        println("hey2")
                                        run {
                                            println(" RESPONSE -> " + t)
                                        }
                                        //hata yukarı
                                    },
                                    {
                                        println("Error occurred!" + it)
                                    },
                                    {
                                        println("End!")
                                    }
                                );
                            println("heytt")


                        } catch (e: Exception) {
                            println("tetsts")
                            Toast.makeText(requireContext(), e.message, Toast.LENGTH_SHORT).show()
                            Log.e("Error!!", e.toString())
                        }


                    }
                }
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

            override fun sizeOf(key: String, bitmap: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.byteCount / 1024
            }
        }


        tempFile = File.createTempFile("camera", ".jpeg", requireActivity().externalCacheDir);
        mPath = tempFile.absolutePath;
        val rootView = inflater.inflate(R.layout.fragment_fotograflarim, container, false);


        val button = rootView.findViewById<Button>(R.id.sendPicture);
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                resultLauncher.launch(intent)
                // içine  gelmesi gerek ,REQUEST_CODE_IMAGE
                //startActivityForResult(intent,REQUEST_CODE_IMAGE)
            }
        }


        return rootView;
    }


}

/*

ServiceBuilder
.buildService(requireActivity())
.sendHomeRequest(HomeRequest())
.subscribeOn(Schedulers.io())
.observeOn(AndroidSchedulers.mainThread())
.subscribe(
{
    t -> kotlin.run {
    println("Income ->" + t?.data?.get(0)?.birthday)
} },
{ err -> println("Error -> " + err) }
)*/
