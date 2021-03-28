package com.arstagaev.calcbalance

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Environment
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.snackbar.Snackbar
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Colour
import jxl.write.Label
import jxl.write.WritableCellFormat
import jxl.write.WritableWorkbook
import jxl.write.WriteException
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG = "ccc"
    // Storage Permissions
    val nameOfFolder: String = "Law Firm Incorporated"
    lateinit var RightPath : File


    //Environment.getExternalStorageDirectory();
    //public final File PATHX = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    //public final String PATHY = "/data/data/in.kriscent.exceldemoandroid/cache";
    var csvFile = "Logs.xls/"

    // Storage Permissions
    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    private lateinit var headerOfDrawer: LinearLayout
    private lateinit var headerDrawerTitle: TextView
    private lateinit var chosenStation : TextView
    private lateinit var titleofActionBar: TextView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var mainNavDrawer: DrawerLayout
    private lateinit var toNavDrawer: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
        verifyStoragePermissions(this)
        val timer = object: CountDownTimer(9000, 3000) {
            override fun onTick(millisUntilFinished: Long) {
                verifyStoragePermissions(this@MainActivity)

            }
            override fun onFinish() {
                if(!checkAndRequestPermissions())
                    Snackbar.make(findViewById(android.R.id.content),"Перезайдите в приложение и одобрите работу с файлами, иначе приложение не сможет создавать Excel файлы",10)

            }
        }
        timer.start()



    }

    private fun initNavigation(){
        toolbar = findViewById(R.id.toolbar)
        toolbar.navigationIcon = null
        setSupportActionBar(toolbar)
        // Get a support ActionBar corresponding to this toolbar and enable the Up button



        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        //navController = findNavController(R.id.main_nav_host_fragment)

        //navController.navigate(R.id.mainFragment)
    }

    fun generateExcel() {
        if(checkAndRequestPermissions()){
            createFolder("/$nameOfFolder")
            createExcel()

            val timer = object: CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {

                }
                override fun onFinish() {
                    Toast.makeText(
                        applicationContext,
                        "Путь сохранения файла: " + getOutputMediaFile(),
                        Toast.LENGTH_LONG
                    ).show()
                    open_file()
                }
            }
            timer.start()
        }else{
            Snackbar.make(findViewById(android.R.id.content),"Перезайдите в приложение и одобрите работу с файлами, иначе приложение не сможет создавать Excel файлы",10)

        }
    }

    fun open_file() {
        //File path = new File();
        val file: File = RightPath

        val uri2 = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file)
        //.fromFile(file);
        Log.d("ccccyyyyyy", "URI $uri2 $file")

        // Open file with user selected app
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri2, getMimeType(file.absolutePath))
        startActivity(intent)
        //return intent;
    }

    //taken from
    // https://stackoverflow.com/questions/6265298/action-view-intent-for-a-file-with-unknown-mimetype
    private fun getMimeType(url: String): String? {
        val extension = url.substring(url.lastIndexOf(".") + 1)
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    var sizeOfLogs = 0

    /* create excel and write
    * on internal memory /sdcard
     */
    private fun createExcel(){
        RightPath = getOutputMediaFile()
        var file: File = RightPath

        Log.d(TAG, "ccc ~~~${file.toString()}")
        //val fos: FileOutputStream = FileOutputStream(file.absoluteFile)
        try {
            //file path
            //val file = File(directory, csvFile)
            val wbSettings = WorkbookSettings()
            wbSettings.locale = Locale("ru", "RU")
            val workbook: WritableWorkbook
            workbook = Workbook.createWorkbook(file, wbSettings)


            //Excel sheet name. 0 represents first sheet
            val sheet = workbook.createSheet("Measurements", 0)
            //sheet.setColumnView(0, 60)
            //sheet.addCell(Label(0, 4, "Started time: "))



            sheet.addCell(Label(0, 4, "привет"))


            val label1 = Label(0, 0, "X")
            val newFormat = WritableCellFormat()
            newFormat.setBackground(Colour.RED)
            label1.cellFormat = newFormat
            sheet.addCell(label1)

            val label2 = Label(1, 0, "Y")
            val newFormat2 = WritableCellFormat()
            newFormat2.setBackground(Colour.GREEN)
            label2.cellFormat = newFormat2
            sheet.addCell(label2)

            val label3 = Label(2, 0, "Z")
            val newFormat3 = WritableCellFormat()
            newFormat3.setBackground(Colour.BLUE)
            label3.cellFormat = newFormat3
            sheet.addCell(label3)

            //closing cursor
            workbook.write()
            workbook.close()
            Toast.makeText(
                application,
                "Data Exported in a Excel Sheet. \n Path:" + getOutputMediaFile(),
                Toast.LENGTH_LONG
            ).show()

//            if (file.mkdirs()) {
//                return file
//            }

            //image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            // fos.close()
        } catch (e: WriteException) {
            Log.e(TAG + "ccc", e.message!!)
            Toast.makeText(application, "ERROR " + e.message, Toast.LENGTH_SHORT).show()
            Log.e("error ccc", "" + e.message)
        } catch (e: IOException) {
            Log.e(TAG + "ccc", e.message!!)
            Toast.makeText(application, "ERROR " + e.message, Toast.LENGTH_SHORT).show()
        }
        //return file
    }

    private fun getOutputMediaFile(): File {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        val extStorageDirectory = Environment.getExternalStorageDirectory().toString()
        val dataPath = "$extStorageDirectory/$nameOfFolder"
        val mediaStorageDir = File(dataPath)

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir()
        }

        val sdf = SimpleDateFormat("dd.M")
        val currentDate = sdf.format(Date())

        val sdf2 = SimpleDateFormat("hh.mm.ss a")
        val currentTime = sdf2.format(Date())

        // Create a media file name
        val mediaFile: File
        val mImageName = "table (${currentDate}_ time is $currentTime)" + ".xls"
        mediaFile = File(mediaStorageDir.path + File.separator + mImageName)
        Log.d("ccc", "" + mediaFile.toString())



        return mediaFile
    }

    private fun createFolder(name: String): File {
        val baseDir: String  = Environment.getExternalStorageDirectory().toString()
        //Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();

//        if (Build.VERSION.SDK_INT < 8) {
//            baseDir = Environment.getExternalStorageDirectory();
//        } else {
//            baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        }

//        baseDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//            .toString()
        Log.d("cccx", baseDir.toString() + "")
        if (baseDir == null) return Environment.getExternalStorageDirectory()
        val folder = File(baseDir, name)

        if (folder.exists()) {
            return folder
        }
        if (folder.isFile) {
            folder.delete()
        }
        return if (folder.mkdirs()) {
            folder
        } else Environment.getExternalStorageDirectory()
    }

    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1

    private fun checkAndRequestPermissions(): Boolean {
        val camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        val storage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        val loc =
//            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//        val loc2 = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
//        if (camera != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.CAMERA)
//        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
//        if (loc2 != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//        if (loc != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_COARSE_LOCATION)
//        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }


    fun verifyStoragePermissions(activity: Activity?) {
        // Check if we have write permission
        val permission = ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                activity,
                PERMISSIONS_STORAGE,
                REQUEST_EXTERNAL_STORAGE
            )
        }
    }


}