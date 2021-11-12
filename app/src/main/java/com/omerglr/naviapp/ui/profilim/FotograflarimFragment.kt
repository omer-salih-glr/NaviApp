package com.omerglr.naviapp.ui.profilim

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color.parseColor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.omerglr.lovelica.api.ServiceBuilder
import com.omerglr.naviapp.R
import com.omerglr.naviapp.utils.ImageResizer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.omerglr.naviapp.api.model.upload.UploadData
import com.omerglr.naviapp.db.UserInformationDB
import okhttp3.internal.notify
import okhttp3.internal.notifyAll


class FotograflarimFragment : Fragment() {

    lateinit var tempFile: File;
    lateinit var mPath: String;
    lateinit var disposable: Disposable;
    lateinit var listData: MutableList<String>;
    var disposeDisposable = false;
    var imagesuploadList = UploadData<List<String>>()

        override fun onDestroy() {
        super.onDestroy()
        // Disposable Eğer null değilse!..
        if (::disposable.isInitialized) disposable.dispose()
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
                            //val file: File = File(selectedImagePath)

                            // Sıkıştırılacak dosyanın Yolu
                            val compressedPicture =
                                File(requireActivity().cacheDir.path + "/compressed_pic.jpeg");
                            // Hata almamak için eğer dosya yoksa oluşturdum.
                            compressedPicture.createNewFile();

                            //Fotoğrafı 640x480 boyutuna kadar düşürerek, sunucuya göndeiyoruz.
                            val compressedBitmap = ImageResizer.reduceBitmapSize(bitmap, 307200);
                            //Sıkıştırılmış fotoyu byte array'a çevirdik.
                            val byteArrayOutputStream = ByteArrayOutputStream();
                            compressedBitmap.compress(
                                Bitmap.CompressFormat.JPEG,
                                100,
                                byteArrayOutputStream
                            );
                            //Sıkış. bitmap'i yazdırdık.
                            val fileOutputStream = FileOutputStream(compressedPicture);
                            fileOutputStream.write(byteArrayOutputStream.toByteArray())
                            fileOutputStream.close();
                            //FileOutputStream ile işin bitince kapamayı unutma.


                            val imagePart = MultipartBody.Part.createFormData(
                                name = "image",
                                filename = compressedPicture.name,
                                body = compressedPicture.asRequestBody("image/*".toMediaType())
                            )


                                disposeDisposable = true;

                            disposable = ServiceBuilder.buildService(requireActivity())
                                .sendUploadPhotoRequest(imagePart)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                    { t ->

                                        println("hey2")
                                        run {
                                            println(" RESPONSE -> " + t)
                                            //listData
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

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tempFile = File.createTempFile("camera", ".jpeg", requireActivity().externalCacheDir);
        mPath = tempFile.absolutePath;
        val rootView = inflater.inflate(R.layout.fragment_fotograflarim, container, false);

        //foğraflar yükleme ve liste
        val fotograf_recyclerview = rootView.findViewById<RecyclerView>(R.id.imageRecylerView)
        fotograf_recyclerview.setHasFixedSize(true);
        val linearLayoutManager: LinearLayoutManager = GridLayoutManager(context, 3)
        fotograf_recyclerview.layoutManager = linearLayoutManager
        val userInfo = UserInformationDB.getUserInformation(requireActivity());
        var images = userInfo!!.data!!.images!!;
        listData = images.toMutableList();
        listData.add(0,"upload_photo");


        val adapter = FotograflarimAdapter(listData.toList()) {imageUrl, position ->
            run {

                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    resultLauncher.launch(intent)


                }
            }
        }

        fotograf_recyclerview.adapter = adapter;

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
